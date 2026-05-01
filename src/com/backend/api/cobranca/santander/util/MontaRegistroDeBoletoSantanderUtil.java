package com.backend.api.cobranca.santander.util;

import static com.backend.api.cobranca.santander.dom.enuns.SacadorSantanderEnum.CONVENIO;
import static com.backend.api.cobranca.santander.dom.enuns.SacadorSantanderEnum.gerarNossoNumeroComDV;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.api.cobranca.santander.dtos.BodyJsonREGISTRODEBOLETOSANTANDERdto;
import com.backend.dtos.CANFEDUPLICdto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.backend.api.cobranca.santander.dom.enuns.SacadorSantanderEnum.*;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.dtos.CLIENTEdto;

public class MontaRegistroDeBoletoSantanderUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static String montaRegistroDeBoletoSantander(CANFEDUPLICdto dto) throws Exception {

        CLIENTEdto cLIENTEdto = CLIENTEcontroller.FindCodigo(dto.getPAR_CLIE().trim());

        System.out.println("Codigo cliente: " + dto.getPAR_CLIE().trim());
        System.out.println("Bairro cliente: " + dto.getPAR_BAIRRO());

        // Inicio Alteração: 20/02/2026
        if (cLIENTEdto != null) {

            if (dto.getPAR_BAIRRO() == null
                    || dto.getPAR_BAIRRO().trim().isEmpty()) {

                if (cLIENTEdto.getCLI_BAIR() != null
                        && !cLIENTEdto.getCLI_BAIR().trim().isEmpty()) {

                    System.out.println("Bairro2 cliente: " + cLIENTEdto.getCLI_BAIR().trim());
                    dto.setPAR_BAIRRO(cLIENTEdto.getCLI_BAIR().trim());
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        BodyJsonREGISTRODEBOLETOSANTANDERdto body
                = new BodyJsonREGISTRODEBOLETOSANTANDERdto();

        String ambiente = Optional.ofNullable(System.getProperty("tpAmb")).orElse("1");

        if ("2".equals(ambiente)) {

            // ================== TESTE ==================
            body.setEnvironment("TESTE");
            body.setNsuCode("1014");
            body.setNsuDate("2023-05-09");
            body.setCovenantCode("3567206");
            body.setBankNumber("1014");
            body.setClientNumber("123");
            body.setDueDate("2023-05-09");
            body.setIssueDate("2023-05-09");
            body.setParticipantCode("teste liq abat");
            body.setNominalValue(new BigDecimal("1.00"));

            BodyJsonREGISTRODEBOLETOSANTANDERdto.Payer payer
                    = new BodyJsonREGISTRODEBOLETOSANTANDERdto.Payer();

            payer.setName(truncate(sanitizeAlfaNumerico("João da Silva santos"), 40));
            payer.setDocumentType("CPF");
            payer.setDocumentNumber("94620639079");
            payer.setAddress(truncate(sanitizeAlfaNumerico("rua nove de janeiro"), 40));
            payer.setNeighborhood(truncate(sanitizeAlfaNumerico("bela vista"), 30));
            payer.setCity(truncate(sanitizeAlfaNumerico("sao paulo"), 20));
            payer.setState("SP");
            payer.setZipCode("05134-897");

            body.setPayer(payer);

            body.setDocumentKind("DUPLICATA_MERCANTIL");
            body.setDeductionValue("0.10");
            body.setPaymentType("REGISTRO");
            body.setWriteOffQuantityDays("30");

            List<String> mensagens = new ArrayList<>();
            mensagens.add("mensagem um");
            mensagens.add("mensagem dois");
            body.setMessages(mensagens);

        } else {

            // ================== PRODUÇÃO ==================
            body.setEnvironment("PRODUCAO");

            String numeroDoc = Optional.ofNullable(dto.getPAR_NUMDOC()).orElse("");
            String numeroDocNumerico = numeroDoc.replaceAll("\\D", "");

            body.setNsuCode(truncate(numeroDocNumerico, 20));

            body.setNsuDate(dto.getPAR_DTEM() != null
                    ? SDF.format(dto.getPAR_DTEM())
                    : SDF.format(new java.util.Date()));

            body.setCovenantCode(CONVENIO.get());

            body.setBankNumber(
                    numeroDocNumerico.isEmpty()
                    ? ""
                    : gerarNossoNumeroComDV(numeroDocNumerico)
            );

            body.setClientNumber(truncate(numeroDocNumerico, 15));

            body.setDueDate(dto.getPAR_VENC() != null
                    ? SDF.format(dto.getPAR_VENC())
                    : SDF.format(new java.util.Date()));

            body.setIssueDate(dto.getPAR_DTEM() != null
                    ? SDF.format(dto.getPAR_DTEM())
                    : SDF.format(new java.util.Date()));

            String participantCode = Optional.ofNullable(dto.getPAR_PARTICIPA())
                    .orElse("")
                    .replaceAll("[^0-9A-Za-z ]", "");

            body.setParticipantCode(truncate(participantCode, 25));

            BigDecimal valor = BigDecimal
                    .valueOf(Optional.ofNullable(dto.getPAR_VALO()).orElse(0.0))
                    .setScale(2, RoundingMode.HALF_UP);

            body.setNominalValue(valor);

            // ===== PAGADOR =====
            BodyJsonREGISTRODEBOLETOSANTANDERdto.Payer payer
                    = new BodyJsonREGISTRODEBOLETOSANTANDERdto.Payer();

            String documento = Optional.ofNullable(dto.getPAR_INSCRICAO())
                    .orElse("")
                    .replaceAll("\\D", "");

            payer.setName(truncate(
                    sanitizeAlfaNumerico(Optional.ofNullable(dto.getPAR_NOME()).orElse("")),
                    40));

            payer.setDocumentType(documento.length() == 11 ? "CPF" : "CNPJ");
            payer.setDocumentNumber(documento);

            payer.setAddress(truncate(
                    sanitizeAlfaNumerico(Optional.ofNullable(dto.getPAR_ENDERECO()).orElse("")),
                    40));

            payer.setNeighborhood(truncate(
                    sanitizeAlfaNumerico(Optional.ofNullable(dto.getPAR_BAIRRO()).orElse("")),
                    30));

            payer.setCity(truncate(
                    sanitizeAlfaNumerico(Optional.ofNullable(dto.getPAR_CIDADE()).orElse("")),
                    20));

            payer.setState(Optional.ofNullable(dto.getPAR_UF()).orElse(""));
            payer.setZipCode(formatCep(Optional.ofNullable(dto.getPAR_CEP()).orElse("")));

            body.setPayer(payer);

            // ===== BENEFICIÁRIO =====
            BodyJsonREGISTRODEBOLETOSANTANDERdto.Beneficiary beneficiary
                    = new BodyJsonREGISTRODEBOLETOSANTANDERdto.Beneficiary();

            beneficiary.setName(truncate(EMPRESA_NOME.get(), 40));
            beneficiary.setDocumentType("CNPJ");
            beneficiary.setDocumentNumber(
                    Optional.ofNullable(EMPRESA_CNPJ_14.get())
                            .orElse("")
                            .replaceAll("\\D", ""));

            body.setBeneficiary(beneficiary);

            body.setDocumentKind("DUPLICATA_MERCANTIL");
            body.setDeductionValue("0.00");
            body.setPaymentType("REGISTRO");
            //body.setWriteOffQuantityDays("30");    //24/02/2026 Quantidade de dias para que um boleto seja baixado após a data de vencimento
            //body.setInterestPercentage("60.00");   //24/02/2026 Percentual de juros ao mês a ser aplicado para o boleto
            body.setInterestPercentage("1.00");      //25/02/2026 Percentual de juros ao mês a ser aplicado para o boleto

            List<String> mensagens = new ArrayList<>();
            mensagens.add("* * VALORES EXPRESSOS EM REAIS * *");
            body.setMessages(mensagens);
        }

        return mapper.writeValueAsString(body);
    }

    private static String truncate(String value, int max) {
        if (value == null) {
            return null;
        }
        return value.length() > max ? value.substring(0, max) : value;
    }

    private static String formatCep(String cep) {
        if (cep == null) {
            return null;
        }
        cep = cep.replaceAll("\\D", "");
        if (cep.length() == 8) {
            return cep.substring(0, 5) + "-" + cep.substring(5);
        }
        return cep;
    }

    /**
     * Remove acentos e caracteres especiais, mantendo apenas letras, números e
     * espaço.
     */
    private static String sanitizeAlfaNumerico(String value) {

        if (value == null) {
            return "";
        }

        // Remove acentos
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Remove caracteres especiais
        normalized = normalized.replaceAll("[^0-9A-Za-z ]", "");

        return normalized.trim();
    }
}
