package com.backend.api.cobranca.mensageria;

import br.com.ovconsultoria.boleto.Banco;
import br.com.ovconsultoria.boleto.Beneficiario;
import br.com.ovconsultoria.boleto.Boleto;
import br.com.ovconsultoria.boleto.Datas;
import br.com.ovconsultoria.boleto.Endereco;
import br.com.ovconsultoria.boleto.Pagador;
import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import br.com.ovconsultoria.boleto.bancos.Bradesco;
import br.com.ovconsultoria.boleto.bancos.GeradorDeLinhaDigitavel;
import br.com.ovconsultoria.boleto.bancos.Santander;
import br.com.ovconsultoria.boleto.bancos.gerador.GeradorDeDigitoBradesco;
import br.com.ovconsultoria.boleto.bancos.gerador.GeradorDeDigitoSantander;
import br.com.ovconsultoria.boleto.transformer.EBCDICConverter;
import br.com.ovconsultoria.boleto.transformer.GeradorDeBoleto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonREGISTRODEBOLETOBRADESCOdto;
import static com.backend.api.cobranca.bradesco.util.MontaConsultaDeBoletoBradescoUtil.montaConsultaDeBoletoBradesco;
import static com.backend.api.cobranca.bradesco.ws.EnviaJsonBradescoWs.enviaJsonBradescoWs;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.CLIENTEdto;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonCONSULTADEBOLETOBRADESCOdto;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.NFEdto;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MontaMalote {

    private static final String PATH_TEMP_APP = "C:/COBRANCA-4.00/TEMP/";
    private static final String BENEFICIARIO_NOME = "CAMAR PLASTICOS LTDA";
    private static final String BENEFICIARIO_CNPJ = "52.703.063/0001-08";

    private static final String BENEFICIARIO_BANCO033_AGENCIA = "4502";
    private static final String BENEFICIARIO_BANCO033_AGENCIA_DIGITO = "0";
    private static final String BENEFICIARIO_BANCO033_CONVENIO = "0734843";
    private static final String BENEFICIARIO_BANCO033_CONVENIO_DIGITO = "0";
    private static final String BENEFICIARIO_BANCO033_CARTEIRA = "101";

    private static final String BENEFICIARIO_BANCO0237_AGENCIA = "3366";
    private static final String BENEFICIARIO_BANCO0237_AGENCIA_DIGITO = "0";
    private static final String BENEFICIARIO_BANCO0237_CONVENIO = "47654";
    private static final String BENEFICIARIO_BANCO0237_CONVENIO_DIGITO = "4";
    private static final String BENEFICIARIO_BANCO0237_CARTEIRA = "9";

    private static final String ENDERECO_BENEFICIARIO_LOGRADOURO = "RUA DA BLENDA, 206";
    private static final String ENDERECO_BENEFICIARIO_BAIRRO = "JARDIM PEROLA";
    private static final String ENDERECO_BENEFICIARIO_CEP = "13454-189";
    private static final String ENDERECO_BENEFICIARIO_CIDADE = "SANTA BARBARA D'OESTE";
    private static final String ENDERECO_BENEFICIARIO_UF = "SP";

    public static List<MaloteCliente> Malote = new ArrayList<>();
    public static Map<String, List<String>> MaloteEmail = new HashMap<>();

    Logprint logprint = new Logprint();

    public boolean geraBoletoSANTANDER(
            CANFEDUPLICdto caNFEDUPLICdto) {
        try {
            Banco banco = new Santander();
            GeradorDeDigitoSantander geradorDeDigitoSantander = new GeradorDeDigitoSantander();
            Datas datas = Datas.novasDatas()
                    .comDocumento(getDia(caNFEDUPLICdto.getPAR_DTEM()), getMes(caNFEDUPLICdto.getPAR_DTEM()), getAno(caNFEDUPLICdto.getPAR_DTEM()))
                    .comProcessamento(getDia(new Date()), getMes(new Date()), getAno(new Date()))
                    .comVencimento(getDia(caNFEDUPLICdto.getPAR_VENC()), getMes(caNFEDUPLICdto.getPAR_VENC()), getAno(caNFEDUPLICdto.getPAR_VENC()));

            //Datas datas = Datas.novasDatas()
            //        .comDocumento(24, 9, 2025)
            //        .comProcessamento(27, 9, 2025)
            //        .comVencimento(24, 10, 2025);
            Endereco enderecoBeneficiario = Endereco.novoEndereco()
                    .comLogradouro(ENDERECO_BENEFICIARIO_LOGRADOURO)
                    .comBairro(ENDERECO_BENEFICIARIO_BAIRRO)
                    .comCep(ENDERECO_BENEFICIARIO_CEP)
                    .comCidade(ENDERECO_BENEFICIARIO_CIDADE)
                    .comUf(ENDERECO_BENEFICIARIO_UF);

            Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                    .comNomeBeneficiario(BENEFICIARIO_NOME)
                    .comDocumento(BENEFICIARIO_CNPJ)
                    .comAgencia(BENEFICIARIO_BANCO033_AGENCIA)
                    .comDigitoAgencia(BENEFICIARIO_BANCO033_AGENCIA_DIGITO)
                    .comCodigoBeneficiario(BENEFICIARIO_BANCO033_CONVENIO)
                    .comDigitoCodigoBeneficiario(BENEFICIARIO_BANCO033_CONVENIO_DIGITO)
                    .comNumeroConvenio(BENEFICIARIO_BANCO033_CONVENIO)
                    .comCarteira(BENEFICIARIO_BANCO033_CARTEIRA)
                    .comEndereco(enderecoBeneficiario)
                    .comNossoNumero(String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NUMDOC().replace("-", "").replace("/", ""))))
                    .comDigitoNossoNumero(geradorDeDigitoSantander.calculaDVNossoNumero(String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NUMDOC().replace("-", "").replace("/", "")))));

            Endereco enderecoPagador = Endereco.novoEndereco()
                    .comLogradouro(caNFEDUPLICdto.getPAR_BAIRRO().trim())
                    .comBairro(caNFEDUPLICdto.getPAR_ENDERECO().trim())
                    .comCep(caNFEDUPLICdto.getPAR_CEP().trim())
                    .comCidade(caNFEDUPLICdto.getPAR_CIDADE().trim())
                    .comUf(caNFEDUPLICdto.getPAR_UF().trim());

            Pagador pagador = Pagador.novoPagador()
                    .comNome(caNFEDUPLICdto.getPAR_NOME().trim())
                    .comDocumento(caNFEDUPLICdto.getPAR_INSCRICAO().trim())
                    .comEndereco(enderecoPagador);

            Boleto boleto = Boleto.novoBoleto()
                    .comBanco(banco)
                    .comDatas(datas)
                    .comBeneficiario(beneficiario)
                    .comPagador(pagador)
                    .comValorBoleto(caNFEDUPLICdto.getPAR_VALO())
                    .comNumeroDoDocumento(caNFEDUPLICdto.getPAR_NUMDOC().trim())
                    .comInstrucoes(
                            "PAGÁVEL PREFERENCIALMENTE NO SANTANDER", "", "", "", "")
                    .comLocaisDePagamento(
                            "PAGÁVEL PREFERENCIALMENTE NO SANTANDER", "");

            // ? Debug de código de barras
            String codigoDeBarras = banco.geraCodigoDeBarrasPara(boleto);
            System.out.println("CÓDIGO DE BARRAS GERADO: " + codigoDeBarras);
            System.out.println("TAMANHO DO CÓDIGO: " + codigoDeBarras.length());

            // ? Linha digitável correta
            GeradorDeLinhaDigitavel geradorDeLinhaDigitavel = new GeradorDeLinhaDigitavel();
            String linhaDigitavel = geradorDeLinhaDigitavel.geraLinhaDigitavelPara(codigoDeBarras, banco);
            System.out.println("LINHA DIGITÁVEL: " + linhaDigitavel);

            // ? Gerar boleto
            GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
            gerador.geraPDF(PATH_TEMP_APP + "BOLETO_" + caNFEDUPLICdto.getPAR_NUMDOC().trim() + ".pdf");
        } catch (Exception e) {
            System.err.println("Falha ao gerar boleto: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean geraBoletoBRADESCO(CANFEDUPLICdto caNFEDUPLICdto) {
        try {
            ResponseJsonCONSULTADEBOLETOBRADESCOdto responseJson = responseconsultaDeBoletoBradesco(caNFEDUPLICdto);

            String codigoDeBarras = "";
            String linhaDigitavel = "";
            Date dataVenctoBol = caNFEDUPLICdto.getPAR_VENC();
            double valorMoedaBol = caNFEDUPLICdto.getPAR_VALO();
            BigDecimal valorDescontos
                    = BigDecimal.valueOf(caNFEDUPLICdto.getPAR_DESC() == null ? 0.0 : caNFEDUPLICdto.getPAR_DESC())
                            .add(BigDecimal.valueOf(caNFEDUPLICdto.getPAR_VLAB() == null ? 0.0 : caNFEDUPLICdto.getPAR_VLAB()));
            EBCDICConverter eBCDICConverter = new EBCDICConverter();
            if (responseJson != null && responseJson.getTitulo() != null) {
                if (responseJson.getTitulo().getDataVenctoBol() != null
                        && !responseJson.getTitulo().getDataVenctoBol().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    dataVenctoBol = new Timestamp(sdf.parse(responseJson.getTitulo().getDataVenctoBol()).getTime());
                    valorMoedaBol = responseJson.getTitulo().getValorMoedaBol() / 100.0;
                }
                linhaDigitavel = linhaDigitavelFormatadaParaBRADESCO(responseJson.getTitulo().getLinhaDig());
                if (eBCDICConverter.isEBCDIC(responseJson.getTitulo().getCodBarras().trim())) {
                    codigoDeBarras = eBCDICConverter.ebcdicToNumeric(responseJson.getTitulo().getCodBarras().trim());
                } else {
                    return false;
                }
            } else {
                if (caNFEDUPLICdto.getPAR_LINHADIGITAVEL() == null
                        || caNFEDUPLICdto.getPAR_LINHADIGITAVEL().isEmpty()
                        || caNFEDUPLICdto.getPAR_CODIGODEBARRAS() == null
                        || caNFEDUPLICdto.getPAR_CODIGODEBARRAS().isEmpty()) {
                    return false;
                }
                linhaDigitavel = linhaDigitavelFormatadaParaBRADESCO(caNFEDUPLICdto.getPAR_LINHADIGITAVEL().trim());
                if (eBCDICConverter.isEBCDIC(caNFEDUPLICdto.getPAR_CODIGODEBARRAS().trim())) {
                    codigoDeBarras = eBCDICConverter.ebcdicToNumeric(caNFEDUPLICdto.getPAR_CODIGODEBARRAS().trim());
                } else {
                    codigoDeBarras = caNFEDUPLICdto.getPAR_CODIGODEBARRAS().trim();
                }
            }

            Banco banco = new Bradesco();
            GeradorDeDigitoBradesco geradorDeDigitoBradesco = new GeradorDeDigitoBradesco();
            Datas datas = Datas.novasDatas()
                    .comDocumento(getDia(caNFEDUPLICdto.getPAR_DTEM()), getMes(caNFEDUPLICdto.getPAR_DTEM()), getAno(caNFEDUPLICdto.getPAR_DTEM()))
                    .comProcessamento(getDia(new Date()), getMes(new Date()), getAno(new Date()))
                    .comVencimento(getDia(dataVenctoBol), getMes(dataVenctoBol), getAno(dataVenctoBol));

            //Datas datas = Datas.novasDatas()
            //        .comDocumento(24, 9, 2025)
            //        .comProcessamento(27, 9, 2025)
            //        .comVencimento(24, 10, 2025);
            Endereco enderecoBeneficiario = Endereco.novoEndereco()
                    .comLogradouro(ENDERECO_BENEFICIARIO_LOGRADOURO)
                    .comBairro(ENDERECO_BENEFICIARIO_BAIRRO)
                    .comCep(ENDERECO_BENEFICIARIO_CEP)
                    .comCidade(ENDERECO_BENEFICIARIO_CIDADE)
                    .comUf(ENDERECO_BENEFICIARIO_UF);

            String nossoNumero = "";
            String digitoNossoNumero = "";
            if (caNFEDUPLICdto.getPAR_NONU() == null || caNFEDUPLICdto.getPAR_NONU().isEmpty()) {
                nossoNumero = String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NUMDOC().replace("-", "").replace("/", "")));
                digitoNossoNumero = geradorDeDigitoBradesco.calculaDVNossoNumero(String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NUMDOC().replace("-", "").replace("/", ""))));
            } else if (caNFEDUPLICdto.getPAR_NONU().trim().length() < 11) {
                nossoNumero = String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NONU().trim()));
                digitoNossoNumero = geradorDeDigitoBradesco.calculaDVNossoNumero(String.format("%012d", Long.parseLong(caNFEDUPLICdto.getPAR_NUMDOC().replace("-", "").replace("/", ""))));
            } else {
                nossoNumero = caNFEDUPLICdto.getPAR_NONU().trim().substring(0, caNFEDUPLICdto.getPAR_NONU().trim().length() - 1);
                digitoNossoNumero = caNFEDUPLICdto.getPAR_NONU().trim().substring(caNFEDUPLICdto.getPAR_NONU().trim().length() - 1);
            }

            Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                    .comNomeBeneficiario(BENEFICIARIO_NOME)
                    .comDocumento(BENEFICIARIO_CNPJ)
                    .comAgencia(BENEFICIARIO_BANCO0237_AGENCIA)
                    .comDigitoAgencia(BENEFICIARIO_BANCO0237_AGENCIA_DIGITO)
                    .comCodigoBeneficiario(BENEFICIARIO_BANCO0237_CONVENIO)
                    .comDigitoCodigoBeneficiario(BENEFICIARIO_BANCO0237_CONVENIO_DIGITO)
                    .comNumeroConvenio(BENEFICIARIO_BANCO0237_CONVENIO)
                    .comCarteira(BENEFICIARIO_BANCO0237_CARTEIRA)
                    .comEndereco(enderecoBeneficiario)
                    .comNossoNumero(nossoNumero)
                    .comDigitoNossoNumero(digitoNossoNumero);

            Endereco enderecoPagador = Endereco.novoEndereco()
                    .comLogradouro(caNFEDUPLICdto.getPAR_BAIRRO().trim())
                    .comBairro(caNFEDUPLICdto.getPAR_ENDERECO().trim())
                    .comCep(caNFEDUPLICdto.getPAR_CEP().trim())
                    .comCidade(caNFEDUPLICdto.getPAR_CIDADE().trim())
                    .comUf(caNFEDUPLICdto.getPAR_UF().trim());

            Pagador pagador = Pagador.novoPagador()
                    .comNome(caNFEDUPLICdto.getPAR_NOME().trim())
                    .comDocumento(caNFEDUPLICdto.getPAR_INSCRICAO().trim())
                    .comEndereco(enderecoPagador);

            Boleto boleto = Boleto.novoBoleto()
                    .comBanco(banco)
                    .comDatas(datas)
                    .comBeneficiario(beneficiario)
                    .comPagador(pagador)
                    .comValorDescontos(valorDescontos)
                    .comValorBoleto(valorMoedaBol)
                    .comNumeroDoDocumento(caNFEDUPLICdto.getPAR_NUMDOC().trim().replace("/", "-"))
                    .comInstrucoes(
                            "PAGÁVEL PREFERENCIALMENTE NAS AGÊNCIAS BRADESCO",
                            "* *   VALORES EXPRESSOS EM REAIS   **** *",
                            "",
                            "",
                            "")
                    .comLocaisDePagamento(
                            "PAGÁVEL PREFERENCIALMENTE NAS AGÊNCIAS BRADESCO", "");

            // ? Debug de código de barras
            //codigoDeBarras = banco.geraCodigoDeBarrasPara(boleto);
            System.out.println("CÓDIGO DE BARRAS GERADO: " + codigoDeBarras);
            System.out.println("TAMANHO DO CÓDIGO: " + codigoDeBarras.length());

            // ? Linha digitável correta
            //GeradorDeLinhaDigitavel geradorDeLinhaDigitavel = new GeradorDeLinhaDigitavel();
            //String linhaDigitavelGerada = geradorDeLinhaDigitavel.geraLinhaDigitavelPara(codigoDeBarras, banco);
            System.out.println("LINHA DIGITÁVEL (BRADESCO): " + linhaDigitavel);
            //System.out.println("LINHA DIGITÁVEL GERADA: " + linhaDigitavelGerada);

            boleto.comCodigoDeBarras(codigoDeBarras);
            boleto.comLinhaDigitavel(linhaDigitavelFormatadaParaBRADESCO(linhaDigitavel.trim()));

            // ? Gerar boleto
            GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
            //System.out.println("LINHA DIGITÁVEL: " + linhaDigitavel);

            // ? Gerar boleto
            gerador.geraPDF(PATH_TEMP_APP + "BOLETO_" + caNFEDUPLICdto.getPAR_NUMDOC().trim().replace("/", "-") + ".pdf");
        } catch (Exception e) {
            System.err.println("Falha ao gerar boleto: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static int getDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    private static int getMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1; // Janeiro é 0
    }

    private static int getAno(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public class MaloteCliente {

        private String pathAnexo;
        private String emailCliente;
        private String codCliente;

        public MaloteCliente(String pathAnexo, String emailCliente, String codCliente) {
            this.pathAnexo = pathAnexo;
            this.emailCliente = emailCliente;
            this.codCliente = codCliente;
        }

        public String getPathAnexo() {
            return pathAnexo;
        }

        public String getEmailCliente() {
            return emailCliente;
        }

        public String getCodCliente() {
            return codCliente;
        }
    }

    public boolean geraLoteMaloteAposRegistroNoBanco(String Banco, CANFEDUPLICdto caNFEDUPLICdto, ResponseJsonREGISTRODEBOLETOBRADESCOdto response) throws SQLException, ParseException {
        List<CANFEDUPLICdto> lista = new ArrayList<>();
        if (response != null) {
            caNFEDUPLICdto.setPAR_NONU(response.getNuTituloGerado().toString());
        }
        lista.add(caNFEDUPLICdto);
        return geraLoteInterno(Banco, lista);
    }

    public boolean geraLoteMaloteAposRegistroNoBanco(String Banco, List<CANFEDUPLICdto> duplicatas) throws SQLException, ParseException {
        return geraLoteInterno(Banco, duplicatas);
    }

    public boolean geraLoteMaloteAposRegistroNoBanco(String Banco, CANFEDUPLICdto caNFEDUPLICdto, String tituloGerado) throws SQLException, ParseException {
        List<CANFEDUPLICdto> lista = new ArrayList<>();
        if (tituloGerado != null && !tituloGerado.isEmpty()) {
            caNFEDUPLICdto.setPAR_NONU(tituloGerado);
        }
        lista.add(caNFEDUPLICdto);
        return geraLoteInterno(Banco, lista);
    }

    private boolean geraLoteInterno(String Banco, List<CANFEDUPLICdto> duplicatas) throws SQLException, ParseException {
        boolean lote = false;
        for (CANFEDUPLICdto duplicata : duplicatas) {

            // ==========================================
            // PROTEÇÃO: NÃO GERAR BOLETO SE NFE CANCELADA
            // ==========================================
            NFEdto nfe = NFEcontroller.NamedQueryFindByIDENNF(
                    Integer.parseInt(duplicata.getPAR_CODI().trim())
            );

            if (nfe != null
                    && "Cancelada".equalsIgnoreCase(
                            nfe.getINFNFE_STATUSNFE().trim())) {

                logprint.logprint(
                        "Boleto não gerado - NFe cancelada: "
                        + duplicata.getPAR_CODI(),
                        Banco
                );

                continue; // pula para próxima duplicata
            }

            if (SANTANDER.getNumeroDoBanco().equals(Banco) && geraBoletoSANTANDER(duplicata)) {
                String pathAnexo = PATH_TEMP_APP + "BOLETO_" + duplicata.getPAR_NUMDOC().trim().replace("/", "-") + ".pdf";
                CLIENTEdto cliente = CLIENTEcontroller.FindCodigo(duplicata.getPAR_CLIE().trim());
                String email = "";
                if (cliente != null) {
                    if (cliente.getCLI_NFE_EMAIL() != null && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
                        email = cliente.getCLI_NFE_EMAIL().trim();
                    } else if (cliente.getCLI_EMAI() != null && !cliente.getCLI_EMAI().trim().isEmpty()) {
                        email = cliente.getCLI_EMAI().trim();
                    } else if (cliente.getCLI_EMAILFINANCEIRO() != null && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
                        email = cliente.getCLI_EMAILFINANCEIRO().trim();
                    }
                }
                if (email != null && !email.isEmpty()) {
                    MaloteCliente malote = this.new MaloteCliente(pathAnexo, email, duplicata.getPAR_CLIE().trim());
                    Malote.add(malote);

                    List<String> lista = MaloteEmail.get(email);
                    if (lista == null) {
                        lista = new ArrayList<>();
                        MaloteEmail.put(email, lista);
                    }
                    if (!lista.contains(pathAnexo)) {
                        lista.add(pathAnexo);
                    }
                    lote = true;
                } else {
                    logprint.logprint("Cliente sem e-mail para envio de boleto: " + duplicata.getPAR_CLIE(), Banco);
                }
            }
            if (BRADESCO.getNumeroDoBanco().equals(Banco) && geraBoletoBRADESCO(duplicata)) {
                //if (duplicata.getPAR_LINHADIGITAVEL() == null
                //        || duplicata.getPAR_LINHADIGITAVEL().isEmpty()) {
                //    return false;
                //}
                String pathAnexo = PATH_TEMP_APP + "BOLETO_" + duplicata.getPAR_NUMDOC().trim().replace("/", "-") + ".pdf";
                CLIENTEdto cliente = CLIENTEcontroller.FindCodigo(duplicata.getPAR_CLIE().trim());
                String email = "";
                if (cliente != null) {
                    if (cliente.getCLI_NFE_EMAIL() != null && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
                        email = cliente.getCLI_NFE_EMAIL().trim();
                    } else if (cliente.getCLI_EMAI() != null && !cliente.getCLI_EMAI().trim().isEmpty()) {
                        email = cliente.getCLI_EMAI().trim();
                    } else if (cliente.getCLI_EMAILFINANCEIRO() != null && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
                        email = cliente.getCLI_EMAILFINANCEIRO().trim();
                    }
                }
                if (email != null && !email.isEmpty()) {
                    MaloteCliente boleto = this.new MaloteCliente(pathAnexo, email, duplicata.getPAR_CLIE().trim());
                    Malote.add(boleto);

                    List<String> lista = MaloteEmail.get(email);
                    if (lista == null) {
                        lista = new ArrayList<>();
                        MaloteEmail.put(email, lista);
                    }
                    if (!lista.contains(pathAnexo)) {
                        lista.add(pathAnexo);
                    }
                    lote = true;
                } else {
                    logprint.logprint("Cliente sem e-mail para envio de boleto: " + duplicata.getPAR_CLIE(), Banco);
                }
            }
        }
        return lote;
    }

    public boolean addDANFEnoLoteInterno(String numeroNFe, String email, String codigoCliente) throws SQLException, ParseException {
        boolean lote = false;
        String pathAnexoDanfe = PATH_TEMP_APP + "DANFE_" + numeroNFe.trim() + ".pdf";

        if (email != null && !email.isEmpty()) {
            MaloteCliente PdfDanfe = this.new MaloteCliente(pathAnexoDanfe, email, codigoCliente.trim());
            Malote.add(PdfDanfe);

            List<String> lista = MaloteEmail.get(email);
            if (lista == null) {
                lista = new ArrayList<>();
                MaloteEmail.put(email, lista);
            }

            if (!lista.contains(pathAnexoDanfe)) {
                lista.add(pathAnexoDanfe);
                lote = true;
            } else {
                lote = false;
            }
        } else {
            logprint.logprint("Cliente sem e-mail para envio da DANFE: " + codigoCliente.trim(), "");
        }

        return lote;
    }

    public boolean addXMLnoLoteInterno(String numeroNFe, String email, String codigoCliente) throws SQLException, ParseException {
        boolean lote = false;
        String caminhoXml = PATH_TEMP_APP + "XML_" + numeroNFe.trim() + ".xml";

        if (email != null && !email.isEmpty()) {
            MaloteCliente boleto = this.new MaloteCliente(caminhoXml, email, codigoCliente.trim());
            Malote.add(boleto);

            List<String> lista = MaloteEmail.get(email);
            if (lista == null) {
                lista = new ArrayList<>();
                MaloteEmail.put(email, lista);
            }

            if (!lista.contains(caminhoXml)) {
                lista.add(caminhoXml);
                lote = true;
            } else {
                lote = false;
            }
        } else {
            logprint.logprint("Cliente sem e-mail para envio do XML: " + codigoCliente.trim(), "");
        }

        return lote;
    }

    public boolean addXMLEventoCancelamentoNoLoteInterno(String numeroNFe,
            String email,
            String codigoCliente)
            throws SQLException, ParseException {

        boolean lote = false;

        String caminhoXmlEvento = PATH_TEMP_APP
                + "XML_EVENTO_CANCELAMENTO"
                + numeroNFe.trim()
                + ".xml";

        File arquivoEvento = new File(caminhoXmlEvento);

        // Só adiciona se realmente existir o XML de cancelamento
        if (!arquivoEvento.exists()) {
            return false; // Não altera nada no fluxo atual
        }

        if (email != null && !email.isEmpty()) {

            MaloteCliente eventoCancelamento
                    = this.new MaloteCliente(caminhoXmlEvento,
                            email,
                            codigoCliente.trim());

            Malote.add(eventoCancelamento);

            List<String> lista = MaloteEmail.get(email);
            if (lista == null) {
                lista = new ArrayList<>();
                MaloteEmail.put(email, lista);
            }

            if (!lista.contains(caminhoXmlEvento)) {
                lista.add(caminhoXmlEvento);
                lote = true;
            }
        } else {
            logprint.logprint(
                    "Cliente sem e-mail para envio do XML de cancelamento: "
                    + codigoCliente.trim(),
                    ""
            );
        }

        return lote;
    }

    public void limpaMaloteDoLoteGerado(List<String> Malote, String Banco) {
        if (Malote == null || Malote.isEmpty()) {
            return;
        }

        for (String caminho : Malote) {
            try {
                File arquivo = new File(caminho);
                if (arquivo.exists()) {
                    boolean deletado = arquivo.delete();
                    if (deletado) {
                        logprint.logprint("Boleto apagado: " + arquivo.getName(), Banco);
                    } else {
                        logprint.logprint("Falha ao apagar o boleto: " + arquivo.getName(), Banco);
                    }
                } else {
                    logprint.logprint("Boleto não encontrado para exclusão: " + caminho, Banco);
                }
            } catch (Exception ex) {
                logprint.logprint("Erro ao tentar apagar boleto: " + caminho + ". Erro: " + ex.getMessage(), Banco);
            }
        }

        // Limpa a lista após a exclusão
        Malote.clear();
    }

    public void limparMalote() {
        Malote.clear();
        MaloteEmail.clear();
    }

    public static String linhaDigitavelParaCodigoDeBarrasBRADESCO(String linhaDigitavel) {
        // Remove pontos e espaços
        String ld = linhaDigitavel.replace(".", "").replace(" ", "");

        if (ld.length() != 47) {
            throw new IllegalArgumentException("Linha digitável deve ter 47 dígitos");
        }

        String campo1 = ld.substring(0, 9);     // sem DV
        String campo2 = ld.substring(10, 20);   // sem DV
        String campo3 = ld.substring(21, 31);   // sem DV
        String dvGeral = ld.substring(32, 33);  // DV geral
        String vencimentoValor = ld.substring(33); // fator + valor

        return campo1 + campo2 + campo3 + dvGeral + vencimentoValor;
    }

    public static String linhaDigitavelFormatadaParaBRADESCO(String linhaDigitavel) {
        String linha = linhaDigitavel.replaceAll("\\s", "").replace(".", ""); // remove espaços
        if (linha.length() == 47) {
            linha = linha.substring(0, 5) + "." + linha.substring(5, 10) + " "
                    + linha.substring(10, 15) + "." + linha.substring(15, 21) + " "
                    + linha.substring(21, 26) + "." + linha.substring(26, 32) + " "
                    + linha.substring(32, 33) + " " + linha.substring(33);
        }
        return linha;
    }

    public ResponseJsonCONSULTADEBOLETOBRADESCOdto responseconsultaDeBoletoBradesco(CANFEDUPLICdto dup) throws SQLException, ParseException {
        System.out.println("[INFO] Iniciando consulta de título no Bradesco: " + dup.getPAR_CODI().trim());
        try {
            if (BRADESCO.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                String json = montaConsultaDeBoletoBradesco(dup);
                System.out.println("[DEBUG] JSON enviado ao Bradesco: " + json);

                String jsonResponse = "";
                if (System.getProperty("tpAmb").equals("1")) {
                    jsonResponse = enviaJsonBradescoWs(ServicosBradescoEnum.CONSULTADEBOLETOBRADESCO, json, "POST");
                } else {
                    jsonResponse = mockConsultaTituloBradescoHomologacao();
                }

                System.out.println("[RETORNO] Retorno da API Bradesco (duplicata " + dup.getPAR_NUMDOC() + "): " + jsonResponse);
                if (jsonResponse != null && jsonResponse.contains("status")) {
                    ObjectMapper mapper = new ObjectMapper();
                    ResponseJsonCONSULTADEBOLETOBRADESCOdto response = mapper.readValue(jsonResponse, ResponseJsonCONSULTADEBOLETOBRADESCOdto.class);
                    if (response.getTitulo() != null && response.getStatus() == 200) {
                        return response;
                    }
                } else {
                    System.out.println("Resposta não contém dados válidos: " + jsonResponse);
                    return null;
                }
            }
        } catch (Exception ex) {
            System.err.println("[ERRO] Falha ao registrar duplicata " + dup.getPAR_NUMDOC() + ": " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        System.out.println("[INFO] Consulta do título finalizada");
        return null;
    }

    private String mockConsultaTituloBradescoHomologacao() {

        return "{"
                + "\"status\":200,"
                + "\"transacao\":\"CBTTIAGS\","
                + "\"mensagem\":\"OPERAÇÃO REALIZADA COM SUCESSO\","
                + "\"causa\":\"\","
                + "\"titulo\":{"
                + "\"agencCred\":2856,"
                + "\"ctaCred\":222652,"
                + "\"digCred\":\"9\","
                + "\"razCredt\":7005,"
                + "\"cip\":263,"
                + "\"codStatus\":1,"
                + "\"status\":\"A VENCER / VENCIDO\","
                + "\"cedente\":{"
                + "\"cnpj\":31759488000055,"
                + "\"nome\":\"LOXJYMI LUJOYMUO\","
                + "\"endereco\":\"X VYSYXXO FY LYPYSYR\","
                + "\"numero\":\"178\","
                + "\"complemento\":\"\","
                + "\"bairro\":\"ALAOXOLO\","
                + "\"cep\":6028,"
                + "\"cepc\":\"220\","
                + "\"cidade\":\"IRORJI\","
                + "\"uf\":\"SP\""
                + "},"
                + "\"sacado\":{"
                + "\"cnpj\":364265228000005,"
                + "\"nome\":\"TESTE REGISTRO\","
                + "\"endereco\":\"AVENIDA COPACABANA\","
                + "\"numero\":\"\","
                + "\"complemento\":\"\","
                + "\"bairro\":\"DEZOITO DO FORTE EMP\","
                + "\"cep\":6472,"
                + "\"cepc\":\"001\","
                + "\"cidade\":\"BARUERI\","
                + "\"uf\":\"SP\""
                + "},"
                + "\"enderecoEma\":\"010\","
                + "\"cebp\":\"N\","
                + "\"debitoAuto\":\"N\","
                + "\"aceite\":\"N\","
                + "\"sacador\":{"
                + "\"cnpj\":0,"
                + "\"nome\":\"\","
                + "\"endereco\":\"\","
                + "\"numero\":\"\","
                + "\"complemento\":\"\","
                + "\"bairro\":\"\","
                + "\"cep\":0,"
                + "\"cepc\":\"0\","
                + "\"cidade\":\"\","
                + "\"uf\":\"\""
                + "},"
                + "\"cense\":2856,"
                + "\"agenOper\":2856,"
                + "\"bcoDepos\":237,"
                + "\"agenDepos\":4152,"
                + "\"snumero\":\"WEBSERVICE\","
                + "\"especDocto\":\"CH\","
                + "\"descrEspec\":\"CHEQUE\","
                + "\"dataReg\":\"23082024\","
                + "\"dataEmis\":\"20112022\","
                + "\"dataVencto\":\"17/12/2024\","
                + "\"especMoeda\":\"R$\","
                + "\"qtdeMoeda\":0,"
                + "\"qtdeCas\":2,"
                + "\"descrMoeda\":\"R$\","
                + "\"valMoeda\":500.0,"
                + "\"valorIof\":0.0,"
                + "\"valAbat\":0.0,"
                + "\"dataMulta\":\"\","
                + "\"diasMulta\":0,"
                + "\"valMulta\":0.0,"
                + "\"qtdeCasMul\":2,"
                + "\"codValMul\":0,"
                + "\"descrMulta\":\"\","
                + "\"dataPerm\":\"\","
                + "\"diasJuros\":0,"
                + "\"valPerm\":0.0,"
                + "\"codComisPerm\":0,"
                + "\"dataDesc1\":\"\","
                + "\"valDesc1\":0.0,"
                + "\"qtdeCasDe1\":2,"
                + "\"codValDe1\":0,"
                + "\"descrDesc1\":\"\","
                + "\"dataDesc2\":\"\","
                + "\"valDesc2\":0.0,"
                + "\"qtdeCasDe2\":2,"
                + "\"codValDe2\":0,"
                + "\"descrDesc2\":\"\","
                + "\"dataDesc3\":\"\","
                + "\"valDesc3\":0.0,"
                + "\"qtdeCasDe3\":2,"
                + "\"codValDe3\":0,"
                + "\"descrDesc3\":\"\","
                + "\"dataInstr\":\"\","
                + "\"diasProt\":0,"
                + "\"dataCartor\":\"\","
                + "\"numCartor\":\"\","
                + "\"numProtoc\":\"\","
                + "\"dataPedSus\":\"\","
                + "\"dataSust\":\"\","
                + "\"despCart\":0.0,"
                + "\"bcoCentr\":0,"
                + "\"ageCentr\":0,"
                + "\"acessEsc\":30531,"
                + "\"tipEndo\":\"M\","
                + "\"oriProt\":0,"
                + "\"corige35\":\"N\","
                + "\"ctpoVencto\":0,"
                + "\"codInscrProt\":0,"
                + "\"qtdDiasDecurPrz\":10,"
                + "\"ctrlPartic\":\"\","
                + "\"diasComisPerm\":0,"
                + "\"qmoedaComisPerm\":0,"
                + "\"indTitParceld\":\"N\","
                + "\"indParcelaPrin\":\"N\","
                + "\"indBoletoDda\":\"N\","
                + "\"codBarras\":\"<NWnnwnNnWwWwnnNwWnNnnnNWwnnWWnwWnNnwNnnWwnNNwNNwwnwWNnnnwWNnnwNWnnnWWnnnwWNWnnnWnnNwWWnnnWnnWWnnnwNWNwWnnnnWNw>\","
                + "\"linhaDig\":\"23792.85600 94236.000009 24022.265201 2 99330000000500\","
                + "\"valorMoedaBol\":500.0,"
                + "\"dataVenctoBol\":\"17/12/2024\","
                + "\"dataLimitePgt\":\"27/12/2024\","
                + "\"dataImpressao\":\"23082024\","
                + "\"horaImpressao\":\"150243\","
                + "\"identTitDda\":24082305157736467,"
                + "\"exibeLinDig\":\"S\","
                + "\"permitePgtoParcial\":\"N\","
                + "\"qtdePgtoParcial\":0,"
                + "\"dtPagto\":\"0\","
                + "\"vlrPagto\":0.0,"
                + "\"qtdPagto\":0,"
                + "\"bcoProc\":0,"
                + "\"ageProc\":0,"
                + "\"baixa\":{"
                + "\"codigo\":0,"
                + "\"descricao\":\"\","
                + "\"data\":\"0\""
                + "}"
                + "},"
                + "\"quantidadeMensagens\":0,"
                + "\"lista\":[]"
                + "}";
    }

}
