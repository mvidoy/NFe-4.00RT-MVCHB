package app.views.operations.nfe.modal.services;

import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;

import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonBAIXADEBOLETOBRADESCOdto;
import static com.backend.api.cobranca.bradesco.util.MontaBaixaDeBoletoBradescoUtil.montaBaixaDeBoletoBradesco;
import static com.backend.api.cobranca.bradesco.ws.EnviaJsonBradescoWs.enviaJsonBradescoWs;

import com.backend.api.cobranca.santander.dom.enuns.SacadorSantanderEnum;
import static com.backend.api.cobranca.santander.dom.enuns.SacadorSantanderEnum.gerarNossoNumeroComDV;
import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;
import com.backend.api.cobranca.santander.dtos.BodyJsonBAIXADEBOLETOSANTANDERdto;
import com.backend.api.cobranca.santander.dtos.ResponseJsonINSTRUCAOSANTANDERdto;
import com.backend.api.cobranca.santander.ws.EnviaJsonSantanderWs;

import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.dtos.CANFEDUPLICdto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class NfeBaixaTitulosService {

    private Consumer<Object[]> tableRowListener;

    public void setTableRowListener(Consumer<Object[]> tableRowListener) {
        this.tableRowListener = tableRowListener;
    }

    // Padrão tabela de Cobrança: {"", Título, Parcela, NossoNúmero, Mensagem}
    private void rowTitulo(String titulo, String parcela, String nossoNumero, String mensagem) {
        if (tableRowListener != null) {
            tableRowListener.accept(new Object[]{"", titulo, parcela, nossoNumero, mensagem});
        }
    }

    private boolean isProducao() {
        return "1".equals(System.getProperty("tpAmb"));
    }

    // ===============================================================
    // USO 1: BAIXA POR NFE (Cancelamento chama aqui)
    // ===============================================================
    public void baixarTitulosPorNFe(String nNF) throws Exception {
        String numeroNFe = String.format("%06d", Integer.parseInt(nNF.trim()));
        List<CANFEDUPLICdto> duplicatas = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);

        rowTitulo(numeroNFe, "", "", "Verificando títulos para baixa...");

        if (duplicatas == null || duplicatas.isEmpty()) {
            rowTitulo(numeroNFe, "", "", "Nenhuma duplicata encontrada para a NFe.");
            return;
        }

        for (CANFEDUPLICdto dup : duplicatas) {
            baixarTitulo(dup);
        }
    }

    // ===============================================================
    // USO 2: BAIXA POR LISTA (Cobrança/selecionadas)
    // ===============================================================
    public void baixarTitulos(List<CANFEDUPLICdto> duplicatas) throws Exception {
        if (duplicatas == null || duplicatas.isEmpty()) {
            return;
        }
        for (CANFEDUPLICdto dup : duplicatas) {
            baixarTitulo(dup);
        }
    }

    // ===============================================================
    // ROTEAMENTO POR BANCO (idêntico ao legado)
    // ===============================================================
    private void baixarTitulo(CANFEDUPLICdto dup) throws Exception {
        if (dup == null) {
            return;
        }

        String banco = Optional.ofNullable(dup.getPAR_BANCO()).orElse("").trim();
        String titulo = Optional.ofNullable(dup.getPAR_CODI()).orElse("").trim();
        String parcela = Optional.ofNullable(dup.getPAR_PARC()).orElse("").trim();
        String nossoNumero = Optional.ofNullable(dup.getPAR_NONU()).orElse("").trim();

        if (banco.isEmpty()) {
            rowTitulo(titulo, parcela, nossoNumero, "Banco não informado na duplicata, verifique!");
            return;
        }

        if (BRADESCO.getNumeroDoBanco().equals(banco)) {
            baixaDeBoletoBradesco(dup);
            return;
        }

        if (SANTANDER.getNumeroDoBanco().equals(banco)) {
            baixaDeBoletoSantander(dup);
            return;
        }

        rowTitulo(titulo, parcela, nossoNumero, "Banco não suportado para baixa: " + banco);
    }

    // ===============================================================
    // BRADESCO (copiado do legado, com mínimo ajuste)
    // ===============================================================
    private void baixaDeBoletoBradesco(CANFEDUPLICdto dup) {
        String titulo = safe(dup.getPAR_CODI());
        String parcela = safe(dup.getPAR_PARC());
        String nossoNumero = safe(dup.getPAR_NONU());

        try {
            if (nossoNumero.isEmpty() && isProducao()) {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Nosso número não localizado ou contém caracteres não numéricos, verifique!");
                return;
            }

            String json = montaBaixaDeBoletoBradesco(dup);
            
            System.out.println("[ENVIO] API Bradesco: " + json);

            rowTitulo(titulo, parcela, nossoNumero,
                    "Baixa do título enviada para o Bradesco com sucesso");

            String jsonResponse = isProducao()
                    ? enviaJsonBradescoWs(ServicosBradescoEnum.BAIXADEBOLETOBRADESCO, json, "POST")
                    : mockBaixaTituloBradescoHomologacaoOk();

            System.out.println("[RETORNO] API Bradesco: " + jsonResponse);
            
            ObjectMapper mapper = new ObjectMapper();

            // === Igual ao legado: valida status/mensagem/causa quando existir ===
            JsonNode root = mapper.readTree(jsonResponse);
            int statusCode = root.has("status") ? root.get("status").asInt() : 0;
            String mensagem = root.has("mensagem") ? root.get("mensagem").asText() : "Mensagem não informada";
            String causa = root.has("causa") ? root.get("causa").asText() : "";

            if (statusCode != 200) {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Erro Bradesco " + statusCode + " - " + mensagem + (causa.isEmpty() ? "" : " | " + causa));
                return;
            }

            if (jsonResponse != null && jsonResponse.contains("status")) {
                ResponseJsonBAIXADEBOLETOBRADESCOdto response
                        = mapper.readValue(jsonResponse, ResponseJsonBAIXADEBOLETOBRADESCOdto.class);

                rowTitulo(titulo, parcela, nossoNumero,
                        response.getStatus() + " - " + response.getMensagem());

                if (response.getStatus() == 200) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");

                    // === Igual ao legado ===
                    if (response.getDados() != null && response.getDados().getStatus() == 57) {
                        dup.setPAR_STATUS("BAIXADO");
                        dup.setPAR_VLPG(dup.getPAR_VALO());
                        dup.setPAR_VLAB(0.0);
                        dup.setPAR_DESC(0.0);
                        dup.setPAR_VALORPAGO(dup.getPAR_VALO());

                        String dataStr = response.getDados().getDataHoraSolicitacao();
                        dup.setPAR_DTPG((dataStr == null || dataStr.trim().isEmpty())
                                ? new Date()
                                : sdf.parse(dataStr));

                        rowTitulo(titulo, parcela, nossoNumero,
                                "Baixa do título efetuado com sucesso");

                    } else if (response.getDados() != null && response.getDados().getStatusAnterior() == 1) {
                        dup.setPAR_STATUS("REGISTRADO");
                        dup.setPAR_VLPG(0.0);
                        dup.setPAR_VLAB(0.0);
                        dup.setPAR_DESC(0.0);
                        dup.setPAR_DTPG(null);
                        dup.setPAR_VALORPAGO(0.0);
                    }

                    dup.setPAR_ARQREMESSA("API");
                    dup.setPAR_DATAREMESSA(new Date());
                    dup.setPAR_VENCTITULO(dup.getPAR_VENC());
                    //if (isProducao()) {
                    CANFEDUPLICcontroller.Update(dup);
                    //}

                    rowTitulo(titulo, parcela, nossoNumero,
                            "Baixa registrada com sucesso no Bradesco");

                }

            } else {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Erro no retorno do título (API Bradesco): " + jsonResponse);
            }

        } catch (Exception ex) {
            rowTitulo(titulo, parcela, nossoNumero,
                    "Exceção ao baixar o título (API Bradesco): " + ex.getMessage());
        }
    }

    // ===============================================================
    // SANTANDER (copiado do legado, com mínimo ajuste)
    // ===============================================================
    private void baixaDeBoletoSantander(CANFEDUPLICdto dup) {
        String titulo = safe(dup.getPAR_CODI());
        String parcela = safe(dup.getPAR_PARC());
        String nossoNumero = safe(dup.getPAR_NONU());

        try {

            if (nossoNumero.trim().isEmpty() && isProducao()) {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Nosso número não informado, verifique!");
                return;
            }

            BodyJsonBAIXADEBOLETOSANTANDERdto body = new BodyJsonBAIXADEBOLETOSANTANDERdto();

            String numeroDoc = Optional.ofNullable(dup.getPAR_NUMDOC()).orElse("");
            String numeroDocNumerico = numeroDoc.replaceAll("\\D", "");

            body.setCovenantCode(SacadorSantanderEnum.CONVENIO.get());
            body.setBankNumber(numeroDocNumerico.isEmpty() ? "" : gerarNossoNumeroComDV(numeroDocNumerico));
            body.setOperation("BAIXAR");

            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(body);
            
            System.out.println("[ENVIO] API Santander: " + jsonRequest);

            rowTitulo(titulo, parcela, nossoNumero,
                    "Solicitação de baixa enviada com sucesso ao Santander");

            String jsonResponse = isProducao()
                    ? EnviaJsonSantanderWs.enviaJsonSantanderWs(
                            ServicosSantanderEnum.BAIXADEBOLETOSANTANDER,
                            jsonRequest,
                            "PATCH",
                            null
                    )
                    : mockBaixaTituloSantanderHomologacaoOk();
            
            System.out.println("[RETORNO] API Santander: " + jsonResponse);

            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Erro: resposta vazia da API Santander");
                return;
            }

            ResponseJsonINSTRUCAOSANTANDERdto response
                    = mapper.readValue(jsonResponse, ResponseJsonINSTRUCAOSANTANDERdto.class);

            // === Igual ao legado ===
            if (response.getMessage() != null
                    && response.getMessage().trim().equals("Alteração realizada com sucesso")) {
                dup.setPAR_STATUS("BAIXADO");
                dup.setPAR_VLPG(dup.getPAR_VALO());
                dup.setPAR_VLAB(0.0);
                dup.setPAR_DESC(0.0);
                dup.setPAR_VALORPAGO(dup.getPAR_VALO());
                dup.setPAR_DTPG(new Date());
                dup.setPAR_ARQREMESSA("API");
                dup.setPAR_DATAREMESSA(new Date());
                dup.setPAR_VENCTITULO(dup.getPAR_VENC());

                //if (isProducao()) {
                CANFEDUPLICcontroller.Update(dup);
                //}

                rowTitulo(titulo, parcela, nossoNumero,
                        "Baixa registrada com sucesso no Santander");

            } else {
                rowTitulo(titulo, parcela, nossoNumero,
                        "Erro Santander (" + safe(response.getReturnCode()) + ") - " + safe(response.getMessage()));
            }

        } catch (Exception ex) {
            rowTitulo(titulo, parcela, nossoNumero,
                    "Exceção ao baixar título Santander: " + ex.getMessage());
        }
    }

    // ===============================================================
    // UTIL
    // ===============================================================
    private String safe(String v) {
        return v == null ? "" : v.trim();
    }

    // ===============================================================
    // MOCKS (homologação)
    // ===============================================================
    private String mockBaixaTituloSantanderHomologacaoOk() {
        return "{"
                + "\"returnCode\": \"000\","
                + "\"message\": \"Instrução registrada com sucesso\","
                + "\"operation\": \"BAIXAR\","
                + "\"covenantCode\": \"3567206\","
                + "\"bankNumber\": \"123\","
                + "\"clientNumber\": \"12345\","
                + "\"participantCode\": \"123456\","
                + "\"nominalValue\": \"400.00\","
                + "\"deductionValue\": \"10.00\","
                + "\"finePercentage\": \"2.00\","
                + "\"fineDate\": \"2023-04-20\","
                + "\"protestQuantityDays\": \"15\","
                + "\"writeOffQuantityDays\": \"5\","
                + "\"paymentValueType\": \"PERCENTUAL\","
                + "\"minValueOrPercentage\": \"10.00\","
                + "\"maxValueOrPercentage\": \"90.00\","
                + "\"discount\": {"
                + "  \"type\": \"VALOR_DATA_FIXA\","
                + "  \"discountOne\": {"
                + "    \"value\": \"1.00\","
                + "    \"limitDate\": \"2023-02-01\""
                + "  }"
                + "},"
                + "\"interest\": {"
                + "  \"interestPercentage\": \"1.00\","
                + "  \"interestValue\": \"100.00\","
                + "  \"interestToleranceDate\": \"2023-04-20\""
                + "},"
                + "\"entryDate\": \"2026-01-09T15:12:45-03:00\","
                + "\"traceId\": \"7d9a1e33-4f1a-4c3e-9c3f-abc123456789\""
                + "}";
    }

    private String mockBaixaTituloBradescoHomologacaoOk() {

        return "{"
                + "\"status\":200,"
                + "\"transacao\":\"CBTTIAGQ\","
                + "\"mensagem\":\"CBTT0532 - SOLICITACAO DE BAIXA EFETUADA\","
                + "\"dados\":{"
                + "\"dataHoraSolicitacao\":\"2024-08-23-14.58.13.290548\","
                + "\"status\":57,"
                + "\"statusAnterior\":1"
                + "}"
                + "}";
    }
}
