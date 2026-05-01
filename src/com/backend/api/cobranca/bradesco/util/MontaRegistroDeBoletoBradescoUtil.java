package com.backend.api.cobranca.bradesco.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.AGENCIA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.CONTA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_BAIRRO;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_CEP;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_CIDADE;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_CNPJ;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_ENDERECO;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_FONE;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_NOME;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_NUMERO;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_UF;
import com.backend.api.cobranca.bradesco.dtos.BodyJsonREGISTRODEBOLETOBRADESCOdto;
import com.backend.dtos.CANFEDUPLICdto;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MontaRegistroDeBoletoBradescoUtil {

    // Dados Sacador / Avalista
    static String empresaNome = EMPRESA_NOME.get();
    static String empresaCNPJ = EMPRESA_CNPJ.get();
    static String empresaEndereco = EMPRESA_ENDERECO.get();
    static String empresaNumero = EMPRESA_NUMERO.get();
    static String empresaBairro = EMPRESA_BAIRRO.get();
    static String empresaCidade = EMPRESA_CIDADE.get();
    static String empresaUF = EMPRESA_UF.get();
    static String empresaCEP = EMPRESA_CEP.get();
    static String empresaFone = EMPRESA_FONE.get();

    static String Agencia = AGENCIA.get();
    static String Conta = CONTA.get();

    public static String montaRegistroDeBoletoBradesco(CANFEDUPLICdto dto) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        ObjectMapper mapper = new ObjectMapper();

        BodyJsonREGISTRODEBOLETOBRADESCOdto body = new BodyJsonREGISTRODEBOLETOBRADESCOdto();

        // ========================================
        // ? Dados do Cliente
        // ========================================
        String inscricao = EMPRESA_CNPJ.get().replaceAll("[^0-9]", "");

        if (inscricao.length() < 14) {
            throw new IllegalArgumentException(
                    "Inscrição inválida para montagem do JSON: " + inscricao
            );
        }

        long raiz = Long.parseLong(inscricao.substring(0, 9));     // ? 9 dígitos
        int filial = Integer.parseInt(inscricao.substring(9, 13)); // ? 4 dígitos
        int controle = Integer.parseInt(inscricao.substring(13));  // ? 2 dígitos

        long negociacao = Long.parseLong(Agencia + "0000000" + Conta);

        String rawNumDoc = dto.getPAR_NUMDOC(); // Exemplo: "070069-1/1"
        String numeros = rawNumDoc.replaceAll("\\D", ""); // Remove não numéricos
        numeros = numeros.length() > 11 ? numeros.substring(0, 11) : String.format("%011d", Long.parseLong(numeros));
        long numeroTitulo = Long.parseLong(numeros);

        //long numeroTitulo = Long.parseLong(dto.getPAR_NUMDOC().replaceAll("[^0-9]", ""));
        // ==================================================
        // ? HOMOLOGAÇÃO = dados fixos exigidos pelo Bradesco
        // ==================================================
        // ? HOMOLOGAÇÃO = dados fixos exigidos pelo Bradesco
        if (System.getProperty("tpAmb").equals("2")) {

            body.setDebitoAutomatico("N");
            body.setCodigoUsuarioSolicitante("APISERVIC");

            body.setNuCPFCNPJ("12345678");
            body.setFilialCPFCNPJ(1018);
            body.setCtrlCPFCNPJ(38);

            body.setRegistraTitulo(1);
            body.setIdProduto(9);
            body.setNuNegociacao(386100000000041000L);
            body.setNuTitulo(0L);
            body.setNuCliente("WEBSERVICE");

            body.setDtEmissaoTitulo("07.06.2025");
            body.setDtVencimentoTitulo("07.08.2025");

            body.setTpVencimento(0);
            body.setIndicadorMoeda(0);
            body.setVlNominalTitulo("1000.00");
            body.setQmoedaNegocTitlo(0);
            body.setCdEspecieTitulo(1);
            body.setCindcdAceitSacdo("2");

            body.setTpProtestoAutomaticoNegativacao(0);
            body.setPrazoProtestoAutomaticoNegativacao(0);
            body.setTipoDiasDecursoProt(0);
            body.setTipoDecursoPrazo(0);
            body.setControleParticipante("");
            body.setCdPagamentoParcial("");
            body.setQtdePagamentoParcial(0);
            body.setTipoPrazoDecursoTres(0);

            body.setPercentualJuros(0.00);
            //body.setVlJuros(0.00);
            body.setQtdeDiasJuros(0);

            //body.setPercentualMulta(0.00);
            //body.setVlMulta(0.00);
            //body.setQtdeDiasMulta(0);
            //body.setPercentualDesconto1(0.00);
            //body.setVlDesconto1(0.00);
            //body.setDataLimiteDesconto1("");
            //body.setPercentualDesconto2(0.00);
            //body.setVlDesconto2(0.00);
            //body.setDataLimiteDesconto2("");
            //body.setPercentualDesconto3(0.00);
            //body.setVlDesconto3(0.00);
            //body.setDataLimiteDesconto3("");
            //body.setPrazoBonificacao(0);
            //body.setPercentualBonificacao(0.00);
            //body.setVlBonificacao(0.00);
            //body.setDtLimiteBonificacao("");
            //body.setVlAbatimento(0.00);
            //body.setVlIOF(0.00);
            // ? Pagador (dados fixos)
            body.setNomePagador("TESTE BE");
            body.setLogradouroPagador("AVENIDA COPACABANA");
            body.setNuLogradouroPagador("237");
            body.setComplementoLogradouroPagador("3 ANDAR");
            body.setCepPagador(6050);
            body.setComplementoCepPagador(40);
            body.setBairroPagador("ALPHAVILLE");
            body.setMunicipioPagador("BARUERI");
            body.setUfPagador("SP");
            body.setCdIndCpfcnpjPagador(1);
            body.setNuCpfcnpjPagador(11438390807L);
            body.setEndEletronicoPagador("");
            body.setDddFoneSacado(0);
            body.setFoneSacado(0);

            // ? Débito automático (não usado, mas obrigatório no JSON)
            body.setBancoDoDebAutomatico(237);
            body.setAgenciaDoDebAutomatico(2);
            body.setDigitoAgenciaDoDebAutomat(7);
            body.setContaDoDebAutomatico(5223);
            body.setRazaoDoDebAutomatico(705);

            // ? Protesto
            body.setCodBancoDoProtesto(0);
            body.setAgenciaDoProtesto(0);

            // ? Sacador/Avalista (dados fixos)
            body.setNomeSacadorAvalista("A");
            body.setLogradouroSacadorAvalista("A");
            body.setNuLogradouroSacadorAvalista("4");
            body.setComplementoLogradouroSacadorAvalista("");
            body.setCepSacadorAvalista(0);
            body.setComplementoCepSacadorAvalista(0);
            body.setBairroSacadorAvalista("D");
            body.setMunicipioSacadorAvalista("D");
            body.setUfSacadorAvalista("SP");
            body.setCdIndCpfcnpjSacadorAvalista(1);
            body.setNuCpfcnpjSacadorAvalista(0L);
            body.setEnderecoSacadorAvalista("DD");
            body.setDddFoneSacadorAvalista(11);
            body.setFoneSacadorAvalista(24144323L);

            // ? Lista mensagens
            List<BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem> msgs = new ArrayList<>();

            BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem m1
                    = new BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem();
            m1.setMensagem("Teste MSG 1");

            BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem m2
                    = new BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem();
            m2.setMensagem("Teste MSG 2");

            msgs.add(m1);
            msgs.add(m2);

            body.setListaMsgs(msgs);
        } // ==============================================
        // ? PRODUÇÃO = Dados reais do título e pagador
        // ==============================================
        else {

            body.setDebitoAutomatico("N");
            body.setCodigoUsuarioSolicitante("APISERVIC");

            body.setNuCPFCNPJ(String.valueOf(raiz));
            body.setFilialCPFCNPJ(filial);
            body.setCtrlCPFCNPJ(controle);

            body.setRegistraTitulo(1);
            body.setIdProduto(9);
            body.setNuNegociacao(negociacao);

            body.setNuTitulo(numeroTitulo);
            body.setNuCliente(dto.getPAR_NUMDOC().trim());

            body.setDtEmissaoTitulo(sdf.format(dto.getPAR_DTEM()));
            body.setDtVencimentoTitulo(sdf.format(dto.getPAR_VENC()));

            body.setTpVencimento(0);
            body.setIndicadorMoeda(0);
            body.setVlNominalTitulo(String.format(Locale.US, "%.2f", dto.getPAR_VALO()));
            body.setQmoedaNegocTitlo(0);
            //body.setCdEspecieTitulo(1); //CHEQUE - Alterado:04/02/2026
            body.setCdEspecieTitulo(2);   //DUPLICATA DE VENDA MERCANTIL - Alterado:04/02/2026
            body.setCindcdAceitSacdo("2");

            body.setTpProtestoAutomaticoNegativacao(0);
            body.setPrazoProtestoAutomaticoNegativacao(0);
            body.setTipoDiasDecursoProt(0);
            body.setTipoDecursoPrazo(0);
            body.setControleParticipante(String.format("%-25s", Optional.ofNullable(dto.getPAR_PARTICIPA()).orElse("").trim()).substring(0, 25));
            body.setCdPagamentoParcial("");
            body.setQtdePagamentoParcial(0);
            body.setTipoPrazoDecursoTres(0);

            //body.setPercentualJuros(0.033); 25/02/2026
            body.setPercentualJuros(3.33);
            //body.setVlJuros(0.00);
            body.setQtdeDiasJuros(1);

            //body.setPercentualMulta(0.00);
            //body.setVlMulta(0.00);
            //body.setQtdeDiasMulta(0);
            //body.setPercentualDesconto1(0.00);
            //body.setVlDesconto1(0.00);
            //body.setDataLimiteDesconto1("");
            //body.setPercentualDesconto2(0.00);
            //body.setVlDesconto2(Double.parseDouble("0.00"));
            //body.setDataLimiteDesconto2("");
            //body.setPercentualDesconto3(0.00);
            //body.setVlDesconto3(0.00);
            //body.setDataLimiteDesconto3("");
            //body.setPrazoBonificacao(0);
            //body.setPercentualBonificacao(0.00);
            //body.setVlBonificacao(0.00);
            //body.setDtLimiteBonificacao("");
            //body.setVlAbatimento(0.00);
            //body.setVlIOF(0.00);
        }

        // ============================
        // ? Dados do Pagador
        // ============================
        String enderecoCompleto = dto.getPAR_ENDERECO() != null ? dto.getPAR_ENDERECO() : "";

        // 1. Substitui abreviações antes de remover pontuação
        //enderecoCompleto = enderecoCompleto.replace("R.", "Rua");
        //enderecoCompleto = enderecoCompleto.replace("Av.", "Avenida");
        //enderecoCompleto = enderecoCompleto.replace("Rod.", "Rodovia");
        //enderecoCompleto = enderecoCompleto.replace("Dr.", "Doutor");
        // 2. Remove acentos
        String logradouro = Normalizer.normalize(enderecoCompleto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "") // Limpa caracteres especiais
                .trim();

        String numero = "S/N";

        // Se tiver vírgula, extrai o logradouro
        if (enderecoCompleto.contains(",")) {
            String[] partes = enderecoCompleto.split(",");
            logradouro = partes[0].trim();
            // número vai ser extraído corretamente abaixo
        }

        // Agora sim: extrai o número do **endereço completo**
        numero = extrairNumeroDoEndereco(enderecoCompleto);

        String cep = dto.getPAR_CEP().replaceAll("[^0-9]", "");
        String cep5 = cep.substring(0, Math.min(5, cep.length()));
        String cep3 = cep.length() > 5 ? cep.substring(5) : "000";

        body.setNomePagador(removeCaracteresEspeciais(dto.getPAR_NOME().replaceAll("[^a-zA-Z0-9\\s]", "")));
        body.setLogradouroPagador(removeCaracteresEspeciais(logradouro));
        body.setNuLogradouroPagador(numero);
        body.setComplementoLogradouroPagador("");
        body.setCepPagador(Integer.parseInt(cep5));
        body.setComplementoCepPagador(Integer.parseInt(cep3));
        //body.setBairroPagador(removeCaracteresEspeciais(dto.getPAR_BAIRRO().replaceAll("[^a-zA-Z0-9\\s]", "")));
        body.setBairroPagador(prepararCampoBairro(dto.getPAR_BAIRRO()));
        body.setMunicipioPagador(removeCaracteresEspeciais(dto.getPAR_CIDADE().replaceAll("[^a-zA-Z0-9\\s]", "")));
        body.setUfPagador(dto.getPAR_UF());
        body.setCdIndCpfcnpjPagador(dto.getPAR_INSCRICAO().length() == 11 ? 1 : 2);
        body.setNuCpfcnpjPagador(Long.parseLong(dto.getPAR_INSCRICAO().replaceAll("[^0-9]", "")));

        // ============================
        // ? Sacador / Avalista
        // ============================
        String ep = empresaCEP.replaceAll("[^0-9]", "");
        String ep5 = ep.substring(0, 5);
        String ep3 = ep.substring(5);

        body.setNomeSacadorAvalista(empresaNome);
        body.setLogradouroSacadorAvalista(empresaEndereco);
        body.setNuLogradouroSacadorAvalista(empresaNumero);
        body.setComplementoLogradouroSacadorAvalista("");
        body.setCepSacadorAvalista(Integer.parseInt(ep5));
        body.setComplementoCepSacadorAvalista(Integer.parseInt(ep3));
        body.setBairroSacadorAvalista(empresaBairro);
        body.setMunicipioSacadorAvalista(empresaCidade);
        body.setUfSacadorAvalista(empresaUF);
        body.setCdIndCpfcnpjSacadorAvalista(2);
        body.setNuCpfcnpjSacadorAvalista(Long.parseLong(empresaCNPJ));
        body.setEnderecoSacadorAvalista("DD");
        body.setDddFoneSacadorAvalista(19);
        body.setFoneSacadorAvalista(30264100L);

        // ============================
        // ? Lista de mensagens
        // ============================
        List<BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem> msgs = new ArrayList<>();
        BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem m1
                = new BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem();
        m1.setMensagem("* * VALORES EXPRESSOS EM REAIS **** *");

        BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem m2
                = new BodyJsonREGISTRODEBOLETOBRADESCOdto.Mensagem();
        m2.setMensagem("");

        msgs.add(m1);
        msgs.add(m2);

        body.setListaMsgs(msgs);

        // ? Serializa para JSON
        String json = mapper.writeValueAsString(body);
        System.out.println(json);

        return json;
    }

    public static String removeCaracteresEspeciais(String texto) {
        if (texto == null) {
            return null;
        }

        // Remove acentos
        String semAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        // Remove caracteres especiais (mantém apenas letras, números e espaço)
        return semAcentos.replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }

    public static String extrairNumeroDoEndereco(String enderecoCompleto) {
        if (enderecoCompleto == null || enderecoCompleto.trim().isEmpty()) {
            return "SN"; // sem barra
        }

        String endereco = enderecoCompleto.trim().toUpperCase();

        // Se houver indicação explícita de S/N ? retorna "SN"
        if (endereco.matches(".*S[\\./-]?N.*")) {
            return "SN";
        }

        // 1) Tenta extrair número após a vírgula
        if (endereco.contains(",")) {
            String[] partes = endereco.split(",");
            if (partes.length > 1) {
                String numBruto = partes[1].trim();
                String numero = numBruto.replaceAll("[^A-Z0-9]", "");

                if (!numero.isEmpty()) {
                    return numero.length() > 10 ? numero.substring(0, 10) : numero;
                }
            }
        }

        // 2) Tenta extrair número no final do logradouro
        Matcher matcher = Pattern.compile("(\\d{1,5}[A-Z]?)$").matcher(endereco);
        if (matcher.find()) {
            String numero = matcher.group(1).replaceAll("[^A-Z0-9]", "");
            return numero.length() > 10 ? numero.substring(0, 10) : numero;
        }

        // 3) Se nada encontrado ? retorna "SN"
        return "SN";
    }

    public static String prepararCampoBairro(String bairroBruto) {
        String bairro = Optional.ofNullable(bairroBruto).orElse("").trim();

        bairro = Normalizer.normalize(bairro, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") // Remove acentos
                .replaceAll("[^a-zA-Z0-9 ]", "") // Remove caracteres especiais
                .replaceAll("\\s{2,}", " ") // Remove espaços duplicados
                .trim();

        if (bairro.isEmpty()) {
            bairro = "CENTRO";
        }

        return bairro.length() > 30 ? bairro.substring(0, 30) : bairro;
    }

}
