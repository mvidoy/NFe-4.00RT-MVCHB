package app.views.operations.nfe.modal.services;

import app.controllers.NfeController;
import app.models.NFE;
import app.views.operations.nfe.NFe;
import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import br.com.ovconsultoria.boleto.transformer.GeradorDaDanfe;
import br.com.ovconsultoria.boleto.transformer.GeradorDoXml;
import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;
import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.CLIENTEdto;
import com.backend.dtos.NFECBdto;
import com.backend.dtos.NFEdto;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.List;
import java.util.function.Consumer;

public class NfeEnvioEmailService {

    private NFe veioDo_NFe_frm;
    private final MontaMalote montaMalote = new MontaMalote();
    private final EnviaMalote enviaMalote = new EnviaMalote();

    private Consumer<Object[]> tableRowListener;

    public void setTableRowListener(Consumer<Object[]> listener) {
        this.tableRowListener = listener;
    }

    private void row(String numero, String mensagem) {
        if (tableRowListener != null) {
            tableRowListener.accept(new Object[]{
                "", numero, "", "", mensagem
            });
        }
    }

    // ===============================================================
    // MÉTODO PRINCIPAL (IDÊNTICO AO LEGADO)
    // ===============================================================
    public void enviarEmail(String numeroNFe) throws Exception {

        String numeroFormatado
                = String.format("%06d", Integer.parseInt(numeroNFe.trim()));

        int ideNNF = Integer.parseInt(numeroFormatado);

        row(numeroFormatado, "Preparando o envio do email...");

        NFEdto nfeDto
                = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);

        CANFENOTFISdto nota
                = CANFENOTFIScontroller.NamedQueryFindByNota(numeroFormatado);

        if (nota == null || nfeDto == null) {
            row(numeroFormatado, "Nota não encontrada.");
            return;
        }

        boolean notaCancelada = "Cancelada".equalsIgnoreCase(
                nfeDto.getINFNFE_STATUSNFE() == null
                ? ""
                : nfeDto.getINFNFE_STATUSNFE().trim()
        );

        String xml = nfeDto.getXML_AUTORIZADO();

        // ================================
        // GERA DANFE
        // ================================
        row(numeroFormatado, "Gerando a DANFE...");
        if (xml != null && !xml.isEmpty()) {
            new GeradorDaDanfe().geraDANFE(xml);
        }

        // ================================
        // GERA XML
        // ================================
        row(numeroFormatado, "Gerando XML...");
        if (xml != null && !xml.isEmpty()) {
            new GeradorDoXml().geraXML(xml);
        }

        // ================================
        // RESOLVE EMAIL
        // ================================
        String codCliente = nota.getNOT_CLIE() == null ? "" : nota.getNOT_CLIE().trim();

        CLIENTEdto cliente
                = CLIENTEcontroller.FindCodigo(codCliente);

        String email = resolveEmail(cliente);

        if (email.isEmpty()) {
            row(numeroFormatado,
                    "Destinatário sem email cadastrado, verifique!");
            return;
        }

        if (nfeDto.getIDE_TPNF() == 0) {
            email = "faturamento2@camarplasticos.com.br";
        }

        // ================================
        // MALOTE
        // ================================
        row(numeroFormatado,
                "Anexando a DANFE e XML ao Malote de envio...");

        montaMalote.addDANFEnoLoteInterno(
                numeroFormatado, email, nota.getNOT_CLIE());

        montaMalote.addXMLnoLoteInterno(
                numeroFormatado, email, nota.getNOT_CLIE());

        // =====================================================
        // SE CANCELADA ? ANEXA XML EVENTO E NÃO GERA BOLETO
        // =====================================================
        if (notaCancelada) {

            row(numeroFormatado,
                    "Nota cancelada. Anexando XML do evento de cancelamento...");

            montaMalote.addXMLEventoCancelamentoNoLoteInterno(
                    numeroFormatado, email, nota.getNOT_CLIE());

        } else {

            // ================================
            // VERIFICA BOLETOS (fluxo normal)
            // ================================
            row(numeroFormatado, "Verificando cobrança...");

            List<CANFEDUPLICdto> duplicatas
                    = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroFormatado);

            String banco = "";

            if (duplicatas != null
                    && !duplicatas.isEmpty()
                    && duplicatas.get(0) != null
                    && duplicatas.get(0).getPAR_BANCO() != null) {

                String bancoDup = duplicatas.get(0).getPAR_BANCO();

                if (bancoDup != null) {
                    banco = bancoDup.trim();
                }

                if (!banco.isEmpty()) {
                    gerarBoletoSeRegistrado(numeroFormatado, duplicatas);
                }

            } else {

                row(numeroFormatado,
                        "Nenhuma duplicata encontrada");
            }

            boolean enviado
                    = enviaMalote.enviaMalote(
                            banco == null ? "" : banco.trim(),
                            cliente,
                            nota
                    );

            montaMalote.limparMalote();

            if (enviado) {

                row(numeroFormatado,
                        "Email enviado com sucesso para o destinatário...");

                NFE eNFE = new NFE();
                eNFE.setIde_nnf(numeroFormatado);
                eNFE.setInfnfe_danfexmlenviado("Sim");

                if ("Sim".equals(System.getProperty("boletoGerado"))) {
                    eNFE.setInfnfe_boletoenviado("Sim");
                }

                NfeController.Update(eNFE);

                NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroFormatado);
                if (veioDo_NFe_frm != null) {
                    veioDo_NFe_frm.retornaRegistro("", "", null, "", nfecb);
                }

            } else {

                row(numeroFormatado,
                        "Nenhum email enviado...");
            }

            return;
        }

        // =====================================================
        // ENVIO PARA NOTA CANCELADA (SEM BOLETO)
        // =====================================================
        boolean enviado
                = enviaMalote.enviaMalote(
                        "", cliente, nota);

        montaMalote.limparMalote();

        if (enviado) {

            row(numeroFormatado,
                    "Email de cancelamento enviado com sucesso...");

        } else {

            row(numeroFormatado,
                    "Falha ao enviar email de cancelamento...");
        }
    }

    // ===============================================================
    // BOLETOS JÁ REGISTRADOS (IDÊNTICO AO LEGADO)
    // ===============================================================
    private void gerarBoletoSeRegistrado(
            String numeroFormatado,
            List<CANFEDUPLICdto> duplicatas) throws SQLException, ParseException {

        for (CANFEDUPLICdto dup : duplicatas) {

            if (dup == null) {
                continue;
            }

            String status = dup.getPAR_STATUS() == null
                    ? ""
                    : dup.getPAR_STATUS().trim();

            String banco = dup.getPAR_BANCO() == null
                    ? ""
                    : dup.getPAR_BANCO().trim();

            if (!status.isEmpty()
                    && "REGISTRADO".equals(status)
                    && SANTANDER.getNumeroDoBanco().equals(banco)) {

                boolean ok = montaMalote
                        .geraLoteMaloteAposRegistroNoBanco(
                                SANTANDER.getNumeroDoBanco(),
                                dup,
                                "");

                if (ok) {
                    row(numeroFormatado,
                            "Boleto de cobrança "
                            + SANTANDER.getNomeDoBanco()
                            + " gerado com sucesso");
                }

            } else if (!status.isEmpty()
                    && "REGISTRADO".equals(status)
                    && BRADESCO.getNumeroDoBanco().equals(banco)) {

                boolean ok = montaMalote
                        .geraLoteMaloteAposRegistroNoBanco(
                                BRADESCO.getNumeroDoBanco(),
                                dup,
                                "");

                if (ok) {
                    row(numeroFormatado,
                            "Boleto de cobrança "
                            + BRADESCO.getNomeDoBanco()
                            + " gerado com sucesso");
                }

            } else {
                row(numeroFormatado,
                        "Duplicata ainda sem REGISTRO de cobrança. Banco: "
                        + banco
                        + ".Verifique!");
            }
        }
    }

    private String resolveEmail(CLIENTEdto cliente) {

        if (cliente == null) {
            return "";
        }

        if (cliente.getCLI_NFE_EMAIL() != null
                && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
            return cliente.getCLI_NFE_EMAIL().trim();
        }

        if (cliente.getCLI_EMAI() != null
                && !cliente.getCLI_EMAI().trim().isEmpty()) {
            return cliente.getCLI_EMAI().trim();
        }

        if (cliente.getCLI_EMAILFINANCEIRO() != null
                && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
            return cliente.getCLI_EMAILFINANCEIRO().trim();
        }

        return "";
    }
}
