package app.views.operations.nfe.modal.services.NfeEnvioServices;

import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import br.com.ovconsultoria.boleto.transformer.EBCDICConverter;

import br.com.ovconsultoria.boleto.transformer.GeradorDaDanfe;
import br.com.ovconsultoria.boleto.transformer.GeradorDoXml;

import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonREGISTRODEBOLETOBRADESCOdto;
import static com.backend.api.cobranca.bradesco.util.MontaRegistroDeBoletoBradescoUtil.montaRegistroDeBoletoBradesco;
import static com.backend.api.cobranca.bradesco.ws.EnviaJsonBradescoWs.enviaJsonBradescoWs;

import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;

import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;
import com.backend.api.cobranca.santander.dtos.ResponseJsonREGISTRODEBOLETOSANTANDERdto;
import static com.backend.api.cobranca.santander.util.MontaRegistroDeBoletoSantanderUtil.montaRegistroDeBoletoSantander;
import static com.backend.api.cobranca.santander.ws.EnviaJsonSantanderWs.enviaJsonSantanderWs;

import com.backend.controllers.*;
import com.backend.dtos.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.config.env;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class NfePosProcessamentoService {

    private final MontaMalote montaMalote;
    private final EnviaMalote enviaMalote;
    private final env Env;

    private final String planPrevcaixa;

    private boolean registrado = false;

    private Consumer<Object[]> tableRowListener;

    public NfePosProcessamentoService() {
        this.montaMalote = new MontaMalote();
        this.enviaMalote = new EnviaMalote();
        this.Env = new env();
        this.planPrevcaixa = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";
    }

    public void setTableRowListener(Consumer<Object[]> tableRowListener) {
        this.tableRowListener = tableRowListener;
    }

    private void row(String ano, String serie, String numero, String mensagem) {
        if (tableRowListener != null) {
            tableRowListener.accept(new Object[]{"", ano, serie, numero, mensagem});
        }
    }

    private boolean isProducao() {
        return "1".equals(System.getProperty("tpAmb"));
    }

    private boolean isHomologacao() {
        return "2".equals(System.getProperty("tpAmb"));
    }

    // ===============================================================
    // PROCESSAMENTO PRINCIPAL
    // ===============================================================
    public NFECBdto processar(String ano, String serie, String nNF, ResultadoEnvioService resultado) throws Exception {

        if (resultado == null || !resultado.isAutorizada()) {
            return null;
        }

        this.registrado = false;

        String numeroNFe = String.format("%06d", Integer.parseInt(nNF.trim()));
        int ideNNF = Integer.parseInt(numeroNFe);

        NFEdto nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);
        CANFENOTFISdto nota = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNFe);

        if (nota == null || nfeDto == null) {
            row(ano, serie, numeroNFe, "XML autorizado não encontrado para a NFe.");
            return null;
        }

        String xmlAutorizado = resultado.getXmlAutorizado();

        if (xmlAutorizado == null || xmlAutorizado.isEmpty()) {
            row(ano, serie, numeroNFe, "XML autorizado não encontrado para a NFe.");
            return null;
        }

        new GeradorDaDanfe().geraDANFE(xmlAutorizado);
        new GeradorDoXml().geraXML(xmlAutorizado);

        CLIENTEdto cliente = CLIENTEcontroller.FindCodigo(nota.getNOT_CLIE().trim());
        String email = resolveEmail(cliente);

        if (email == null || email.trim().isEmpty() || nfeDto.getIDE_TPNF() == 0) {
            email = "faturamento2@camarplasticos.com.br";
        }

        boolean addDANFE = montaMalote.addDANFEnoLoteInterno(numeroNFe, email, nota.getNOT_CLIE());
        boolean addXML = montaMalote.addXMLnoLoteInterno(numeroNFe, email, nota.getNOT_CLIE());

        if (addDANFE) {
            row(ano, serie, numeroNFe, "DANFE gerada com sucesso");
        }
        if (addXML) {
            row(ano, serie, numeroNFe, "XML gerado com sucesso");
        }

        String banco = "";

        boolean gerado = false;

        if (nfeDto.getIDE_TPNF() == 1
                && nfeDto.getIDE_NATOP() != null
                && nfeDto.getIDE_NATOP().toUpperCase().contains("VENDA")) {

            List<CANFEDUPLICdto> duplicatas
                    = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);

            if (duplicatas != null && !duplicatas.isEmpty()) {

                banco = duplicatas.get(0).getPAR_BANCO().trim();

                for (CANFEDUPLICdto dup : duplicatas) {

                    boolean ok = false;

                    if (banco.equals(BRADESCO.getNumeroDoBanco())) {
                        ok = registroDeBoletoBradesco(ano, serie, dup);
                    } else if (banco.equals(SANTANDER.getNumeroDoBanco())) {
                        ok = registroDeBoletoSantander(ano, serie, dup);
                    }

                    if (ok) {

                        gerado = montaMalote
                                .geraLoteMaloteAposRegistroNoBanco(banco, dup, numeroNFe);

                        if (gerado) {

                            String nomeBanco = banco.equals(BRADESCO.getNumeroDoBanco())
                                    ? "Bradesco"
                                    : "Santander";

                            row(ano, serie, dup.getPAR_NUMDOC(),
                                    "Boleto gerado com sucesso (" + nomeBanco + ")");

                            System.out.println("[MALOTE] Boleto gerado com sucesso para duplicata "
                                    + dup.getPAR_NUMDOC() + " (" + nomeBanco + ")");
                        }

                        registrado = true;
                    }
                }

                if (registrado) {
                    atualizarTituloRegistrado(numeroNFe);
                }
            }
        }

        // ANTES (31/03/2026): enviaMalote.enviaMalote(banco, cliente, nota);
        // Refatorado por: Claude | Aprovado por: Osvaldo
        // Motivo: retorno boolean era ignorado, fazendo com que os campos infnfe_danfexmlenviado
        //         e infnfe_boletoenviado fossem atualizados mesmo quando o e-mail nÃ£o era enviado.
        boolean emailEnviado = enviaMalote.enviaMalote(banco, cliente, nota);

        String msgEnvioDanfeXmlBoleto = "";

        // ANTES (31/03/2026):
        // if (addDANFE && addXML && !gerado) {
        //     msgEnvioDanfeXmlBoleto = "Danfe e Xml enviado(s)";
        //     atualizarDanfeXmlEnviados(numeroNFe);
        // }
        // Refatorado por: Claude | Aprovado por: Osvaldo
        // Motivo: atualizarDanfeXmlEnviados() era chamado sem verificar se o e-mail foi enviado.
        if (addDANFE && addXML && !gerado && emailEnviado) {
            msgEnvioDanfeXmlBoleto = "Danfe e Xml enviado(s)";
            atualizarDanfeXmlEnviados(numeroNFe);
        }

        // ANTES (31/03/2026):
        // if (addDANFE && addXML && gerado) {
        //     msgEnvioDanfeXmlBoleto = "Danfe, Xml e Boleto(s)";
        //     atualizarDanfeXmlEnviados(numeroNFe);
        //     atualizarBoletoEnviado(numeroNFe);
        // }
        // Refatorado por: Claude | Aprovado por: Osvaldo
        // Motivo: atualizarDanfeXmlEnviados() e atualizarBoletoEnviado() eram chamados sem
        //         verificar se o e-mail foi enviado. Campos infnfe_danfexmlenviado e
        //         infnfe_boletoenviado sÃ³ devem ser marcados como "Sim" apÃ³s confirmaÃ§Ã£o de envio.
        if (addDANFE && addXML && gerado && emailEnviado) {
            msgEnvioDanfeXmlBoleto = "Danfe, Xml e Boleto(s)";
            atualizarDanfeXmlEnviados(numeroNFe);
            atualizarBoletoEnviado(numeroNFe);
        }

        row(ano, serie, numeroNFe,
                msgEnvioDanfeXmlBoleto + " com sucesso para o(s) email(s): "
                + email);

        System.out.println("[ENVIO] " + msgEnvioDanfeXmlBoleto + " enviados para: " + email);

        montaMalote.limparMalote();

        return NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
    }

    // ================================================================================
    // REGISTRO AVULSO DE BOLETOS (usado pela tela Modal_ViewDataRegistraTituloService)
    // ================================================================================
    public void registrarBoletosAvulso(String numeroNFe) throws Exception {

        String numeroFormatado = String.format("%06d", Integer.parseInt(numeroNFe.trim()));

        List<CANFEDUPLICdto> duplicatas
                = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroFormatado);

        if (duplicatas == null || duplicatas.isEmpty()) {
            row("", "", numeroFormatado, "Nenhuma duplicata encontrada");
            return;
        }

        String banco = duplicatas.get(0).getPAR_BANCO().trim();

        for (CANFEDUPLICdto dup : duplicatas) {

            boolean ok = false;

            if (banco.equals(BRADESCO.getNumeroDoBanco())) {
                ok = registroDeBoletoBradesco("", "", dup);
            } else if (banco.equals(SANTANDER.getNumeroDoBanco())) {
                ok = registroDeBoletoSantander("", "", dup);
            }

            if (ok) {

                boolean gerado = montaMalote
                        .geraLoteMaloteAposRegistroNoBanco(banco, dup, numeroFormatado);

                if (gerado) {

                    String nomeBanco = banco.equals(BRADESCO.getNumeroDoBanco())
                            ? "Bradesco"
                            : "Santander";

                    row("", "", dup.getPAR_NUMDOC(),
                            "Boleto gerado com sucesso (" + nomeBanco + ")");

                    System.out.println("[MALOTE] Boleto gerado com sucesso para duplicata "
                            + dup.getPAR_NUMDOC() + " (" + nomeBanco + ")");
                }
            }
        }
    }

    // ===============================================================
    // REGISTRO BRADESCO + PLANILHA
    // ===============================================================
    private boolean registroDeBoletoBradesco(String ano, String serie, CANFEDUPLICdto dup) {

        try {

            if (!"DIGITADO".equalsIgnoreCase(dup.getPAR_STATUS())) {
                return false;
            }

            String json = montaRegistroDeBoletoBradesco(dup);

            System.out.println("[ENVIO] JSON enviado ao Bradesco: " + json);

            String jsonResponse = isProducao()
                    ? enviaJsonBradescoWs(ServicosBradescoEnum.REGISTRODEBOLETOBRADESCO, json, "POST")
                    : simulaRegistroBoletoBradescoHomologacao();

            System.out.println("[RETORNO] Retorno da API Bradesco (duplicata " + dup.getPAR_NUMDOC() + "): " + jsonResponse);

            row(ano, serie, dup.getPAR_NUMDOC(), "Solicitação de registro do Boleto enviado com sucesso (Bradesco)");

            if (jsonResponse != null && jsonResponse.contains("linhaDigitavel")) {

                row(ano, serie, dup.getPAR_NUMDOC(),
                        "Boleto registrado com sucesso (Bradesco)");

                ObjectMapper mapper = new ObjectMapper();
                ResponseJsonREGISTRODEBOLETOBRADESCOdto response
                        = mapper.readValue(jsonResponse,
                                ResponseJsonREGISTRODEBOLETOBRADESCOdto.class);

                // Remove máscara da linha digitável
                String linhaDigitavel = response.getLinhaDigitavel() != null
                        ? response.getLinhaDigitavel().replaceAll("\\D", "")
                        : "";

                // Converte código de barras N/W para numérico
                EBCDICConverter converter = new EBCDICConverter();
                String codigoDeBarras = response.getCdBarras() != null
                        ? converter.ebcdicToNumeric(response.getCdBarras())
                        : "";

                // Centraliza toda a atualização aqui
                atualizaDuplicataPosRegistro(
                        dup,
                        linhaDigitavel,
                        codigoDeBarras,
                        response.getNuTituloGerado() != null
                        ? String.valueOf(response.getNuTituloGerado())
                        : "",
                        ano,
                        serie
                );

                atualizarPlanilha(dup, "API-BRADESCO");

                return true;
            }

        } catch (Exception ex) {
            row(ano, serie, dup.getPAR_NUMDOC(),
                    "Erro registro Bradesco: " + ex.getMessage());
        }

        return false;
    }

    // ===============================================================
    // REGISTRO SANTANDER + PLANILHA
    // ===============================================================
    private boolean registroDeBoletoSantander(String ano, String serie, CANFEDUPLICdto dup) {

        try {

            if (!"DIGITADO".equalsIgnoreCase(dup.getPAR_STATUS())) {
                return false;
            }

            String json = isProducao()
                    ? montaRegistroDeBoletoSantander(dup)
                    : mockBodyRegistroBoletoSantanderHomologacao();

            System.out.println("[ENVIO] JSON enviado ao Santander: " + json);

            String jsonResponse = isProducao()
                    ? enviaJsonSantanderWs(ServicosSantanderEnum.REGISTRODEBOLETOSANTANDER, json, "POST", null)
                    : mockResponseRegistroBoletoSantanderHomologacao();

            System.out.println("[RETORNO] Retorno da API Santander (duplicata " + dup.getPAR_NUMDOC() + "): " + jsonResponse);

            if (jsonResponse != null && jsonResponse.contains("digitableLine")) {

                row(ano, serie, dup.getPAR_NUMDOC(), "Boleto registrado com sucesso (Santander)");

                ObjectMapper mapper = new ObjectMapper();
                ResponseJsonREGISTRODEBOLETOSANTANDERdto response
                        = mapper.readValue(jsonResponse, ResponseJsonREGISTRODEBOLETOSANTANDERdto.class);

                String nossoNumero = "";

                if (response.getBankNumber() != null) {
                    nossoNumero = String.format("%013d",
                            Long.parseLong(response.getBankNumber()));
                }

                //row(ano, serie, dup.getPAR_NUMDOC(), "Título de cobrança registrado com sucesso"
                //);
                //if (System.getProperty("tpAmb").equals("2")) { // 20/01/2026
                //    row(ano, serie, dup.getPAR_NUMDOC(), "Ambiente de Homologação (API do Santander não consumida)."
                //    );
                //}
                //dup.setPAR_STATUS("REGISTRADO");
                //dup.setPAR_LINHADIGITAVEL(response.getDigitableLine());
                //dup.setPAR_CODIGODEBARRAS(response.getBarcode());
                //if (isProducao()) {
                //    CANFEDUPLICcontroller.Update(dup);
                //}
                atualizaDuplicataPosRegistro(
                        dup,
                        response.getDigitableLine(),
                        response.getBarcode(),
                        nossoNumero,
                        ano,
                        serie
                );

                atualizarPlanilha(dup, "API-SANTANDER");

                return true;
            }

        } catch (Exception ex) {
            row(ano, serie, dup.getPAR_NUMDOC(),
                    "Erro registro Santander: " + ex.getMessage());
        }

        return false;
    }

    // ===============================================================
    // ATUALIZA PLANILHA (IDÊNTICO AO LEGADO)
    // ===============================================================
    private void atualizarPlanilha(CANFEDUPLICdto dup, String origem) throws Exception {

        FileInputStream file = new FileInputStream(planPrevcaixa);
        WorkbookSettings ws = new WorkbookSettings();
        ws.setSuppressWarnings(true);

        Workbook workbook = Workbook.getWorkbook(file, ws);
        WritableWorkbook writableWorkbook
                = Workbook.createWorkbook(new File(planPrevcaixa), workbook);

        WritableSheet sheet2 = writableWorkbook.getSheet(0);

        try {

            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate venc
                    = dup.getPAR_VENC().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

            String dataVenc = venc.format(formatter);

            for (int k = 1; k <= 1000; k++) {

                Cell[] row = sheet2.getRow(k);

                String numDoc = row[2].getContents();

                if (numDoc.equals(dup.getPAR_NUMDOC().trim())) {
                    break;
                }

                if (row[0].getContents().isEmpty()
                        && row[2].getContents().isEmpty()) {

                    sheet2.addCell(new Label(0, k, dataVenc));
                    sheet2.addCell(new Label(1, k, " " + dup.getPAR_NOME().trim()));
                    sheet2.addCell(new Label(2, k, dup.getPAR_NUMDOC().trim()));
                    sheet2.addCell(new Label(3, k, origem));
                    sheet2.addCell(new Number(5, k, dup.getPAR_VALO()));

                    break;
                }
            }

        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
        }
    }

    // ===============================================================
    // AUXILIARES
    // ===============================================================
    private void atualizarTituloRegistrado(String numero) throws Exception {
        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numero);
        if (nfecb != null) {
            nfecb.setINFNFE_TITULOREGISTRADO("Sim");
            NFEcontroller.Update(nfecb);
        }
    }

    private void atualizarDanfeXmlEnviados(String numero) throws Exception {
        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numero);
        if (nfecb != null) {
            nfecb.setINFNFE_DANFEXMLENVIADO("Sim");
            NFEcontroller.Update(nfecb);
        }
    }

    private void atualizarBoletoEnviado(String numero) throws Exception {
        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numero);
        if (nfecb != null) {
            nfecb.setINFNFE_BOLETOENVIADO("Sim");
            NFEcontroller.Update(nfecb);
        }
    }

    private boolean todasParcelasRegistradas(String ano, String serie, String numeroNFe) throws Exception {

        if (isHomologacao()) {
            return false;
        }

        List<CANFEDUPLICdto> parcelas
                = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);

        for (CANFEDUPLICdto dup : parcelas) {
            if (!"REGISTRADO".equalsIgnoreCase(dup.getPAR_STATUS())) {
                return false;
            }
        }
        return true;
    }

    private String resolveEmail(CLIENTEdto cliente) {
        if (cliente == null) {
            return "";
        }
        if (cliente.getCLI_NFE_EMAIL() != null && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
            return cliente.getCLI_NFE_EMAIL().trim();
        }
        if (cliente.getCLI_EMAI() != null && !cliente.getCLI_EMAI().trim().isEmpty()) {
            return cliente.getCLI_EMAI().trim();
        }
        if (cliente.getCLI_EMAILFINANCEIRO() != null && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
            return cliente.getCLI_EMAILFINANCEIRO().trim();
        }
        return "";
    }

    // =========================================================================================
    // Mocks (iguais ao antigo)
    // =========================================================================================
    private String simulaRegistroBoletoBradescoHomologacao() {

        return "{"
                + "\"idProduto\":9,"
                + "\"negociacao\":336600000000047654,"
                + "\"cpssoaJuridContr\":2269651,"
                + "\"ctpoContrNegoc\":48,"
                + "\"nseqContrNegoc\":237202,"
                + "\"cprodtServcOper\":1730,"
                + "\"nuTituloGerado\":7016211,"
                + "\"codStatus10\":1,"
                + "\"status10\":\"A VENCER/VENCIDO\","
                + "\"linhaDigitavel\":\"23793.36601 90000.701624 11004.765407 6 12840000000103\","
                + "\"cdBarras\":\"NWnnwnNnWwNwwnNNwnNwnnWNwnnWWnnnWWnnnWWnwnNNwWWnnnwWNnnnwWNnnwNWnnnWWnnnwWNNnwwNnWwnNWnnnWnnWWnnnwNWNwWnnnnWNw\""
                + "}";
    }

    private String mockResponseRegistroBoletoSantanderHomologacao() {
        return "{"
                + "\"environment\": \"TESTE\","
                + "\"nsuCode\": \"TST123\","
                + "\"nsuDate\": \"2023-05-11\","
                + "\"covenantCode\": 1234567,"
                + "\"bankNumber\": \"1021\","
                + "\"clientNumber\": \"1021-1-1\","
                + "\"dueDate\": \"2023-05-11\","
                + "\"issueDate\": \"2023-05-09\","
                + "\"participantCode\": \"CODIGO 1234\","
                + "\"nominalValue\": 10.00,"
                + "\"payer\": {"
                + "    \"name\": \"João da Silva\","
                + "    \"documentType\": \"CPF\","
                + "    \"documentNumber\": \"12345678901\","
                + "    \"address\": \"rua nove de janeiro\","
                + "    \"neighborhood\": \"bela vista\","
                + "    \"city\": \"sao paulo\","
                + "    \"state\": \"SP\","
                + "    \"zipCode\": \"05134-897\""
                + "},"
                + "\"documentKind\": \"DUPLICATA_MERCANTIL\","
                + "\"deductionValue\": \"0.10\","
                + "\"paymentType\": \"REGISTRO\","
                + "\"writeOffQuantityDays\": \"30\","
                + "\"messages\": ["
                + "    \"mensagem um\","
                + "    \"mensagem dois\""
                + "],"
                + "\"barcode\": \"03396939700000001009356720600000000123450101\","
                + "\"digitableLine\": \"03399356782060000000201234501011693970000000100\","
                + "\"entryDate\": \"2023-05-11\","
                + "\"qrCodePix\": \"00020101021226920014br.gov.bcb.pix2570pix.santander.com.br/qr/v2/cobv/9fa03dbd-0b9c-4910-8ab3-14f6bf48a246520400005303986540410.005802BR5925TESTE CONECTIVIDADE API6009SAO PAULO62070503***63041110\","
                + "\"qrCodeUrl\": \"pix.santander.com.br/qr/v2/cobv/9fa03dbd-0b9c-4910-8ab3-14f6bf48a246\""
                + "}";
    }

    private String mockBodyRegistroBoletoSantanderHomologacao() {
        return "{"
                + "\"environment\": \"TESTE\","
                + "\"nsuCode\": \"TST123\","
                + "\"nsuDate\": \"2023-05-11\","
                + "\"covenantCode\": 1234567,"
                + "\"bankNumber\": \"1021\","
                + "\"clientNumber\": \"1021-1-1\","
                + "\"dueDate\": \"2023-05-11\","
                + "\"issueDate\": \"2023-05-09\","
                + "\"participantCode\": \"CODIGO 1234\","
                + "\"nominalValue\": 10.00,"
                + "\"payer\": {"
                + "    \"name\": \"João da Silva\","
                + "    \"documentType\": \"CPF\","
                + "    \"documentNumber\": \"12345678901\","
                + "    \"address\": \"rua nove de janeiro\","
                + "    \"neighborhood\": \"bela vista\","
                + "    \"city\": \"sao paulo\","
                + "    \"state\": \"SP\","
                + "    \"zipCode\": \"05134-897\""
                + "},"
                + "\"documentKind\": \"DUPLICATA_MERCANTIL\","
                + "\"deductionValue\": \"0.10\","
                + "\"paymentType\": \"REGISTRO\","
                + "\"writeOffQuantityDays\": \"30\","
                + "\"messages\": ["
                + "    \"mensagem um\","
                + "    \"mensagem dois\""
                + "],"
                + "\"key\": {"
                + "    \"type\": \"EMAIL\","
                + "    \"dictKey\": \"suachave@suachave.com.br\""
                + "},"
                + "\"discount\": {"
                + "    \"type\": \"VALOR_DATA_FIXA\","
                + "    \"discountOne\": {"
                + "        \"value\": \"1.00\","
                + "        \"limitDate\": \"2023-05-10\""
                + "    }"
                + "},"
                + "\"interestPercentage\": \"30.00\""
                + "}";
    }

    private void atualizaDuplicataPosRegistro(
            CANFEDUPLICdto dup,
            String linhaDigitavel,
            String codigoDeBarras,
            String tituloGerado,
            String ano,
            String serie) throws Exception {

        if (dup == null) {
            return;
        }

        String bancoDuplicata = dup.getPAR_BANCO() != null
                ? dup.getPAR_BANCO().trim()
                : "";

        String nomeBanco = bancoDuplicata.equals(BRADESCO.getNumeroDoBanco())
                ? BRADESCO.getNomeDoBanco()
                : SANTANDER.getNomeDoBanco();

        //boolean gerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(
        //        bancoDuplicata, dup, tituloGerado);
        String ld = linhaDigitavel != null
                ? linhaDigitavel.replaceAll("\\D", "")
                : "";

        String cb = codigoDeBarras != null
                ? codigoDeBarras.replaceAll("\\D", "")
                : "";

        // ? Regra específica Bradesco
        //if (BRADESCO.getNumeroDoBanco().equals(bancoDuplicata)) {
        //    EBCDICConverter converter = new EBCDICConverter();
        //    cb = codigoDeBarras != null
        //            ? converter.ebcdicToNumeric(codigoDeBarras)
        //            : "";
        //}
        //if (!gerado) {
        //    row(ano, serie, dup.getPAR_NUMDOC(),
        //            "Falha na geração do boleto no banco: " + nomeBanco);
        //    return;
        //}
        //row(ano, serie, dup.getPAR_NUMDOC(),
        //        "Boleto de cobrança gerado com sucesso no " + nomeBanco);
        // ? Atualiza duplicata (igual legado)
        dup.setPAR_ARQREMESSA("API");
        dup.setPAR_DATAREMESSA(new Date());
        dup.setPAR_VENCTITULO(dup.getPAR_VENC());
        dup.setPAR_STATUS("REGISTRADO");
        dup.setPAR_LINHADIGITAVEL(ld);
        dup.setPAR_CODIGODEBARRAS(cb);
        dup.setPAR_NONU(
                tituloGerado != null ? String.valueOf(tituloGerado) : ""
        );

        //if (isProducao()) {
        CANFEDUPLICcontroller.Update(dup);

        String numeroNFe = String.format("%06d",
                Integer.parseInt(dup.getPAR_CODI().trim()));

        NFECBdto nfecb
                = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);

        if (nfecb != null) {
            nfecb.setINFNFE_TITULOREGISTRADO("Sim");
            NFEcontroller.Update(nfecb);
        }
        //}

        row(ano, serie, dup.getPAR_NUMDOC(),
                "Base de dados LOCAL da duplicata atualizado com sucesso");
    }

}
