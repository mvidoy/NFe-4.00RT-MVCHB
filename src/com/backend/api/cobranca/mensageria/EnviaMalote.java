package com.backend.api.cobranca.mensageria;

import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.CLIENTEdto;
import com.backend.dtos.NFEdto;

import java.io.File;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EnviaMalote {

    Logprint logprint = new Logprint();
    MontaMalote montaMalote = new MontaMalote();

    public boolean enviaMalote(String Banco, CLIENTEdto cliente, CANFENOTFISdto nfenotfis)
            throws SQLException, ParseException {

        // ANTES (31/03/2026): boolean enviado = false;
        // Refatorado por: Claude | Aprovado por: Osvaldo
        // Motivo: variÃ¡vel era sobrescrita a cada iteraÃ§Ã£o do loop, retornando apenas o resultado
        //         do Ãºltimo e-mail. Com a inicializaÃ§Ã£o em true e acumulaÃ§Ã£o via &&,
        //         qualquer falha contamina o retorno final corretamente.
        boolean enviado = true;

        if (MontaMalote.MaloteEmail.isEmpty()) {
            System.out.println("Nenhum email enviado, malote vazio.");
            return false;
        }
        
        Map<String, List<String>> maloteCopia = new HashMap<>(MontaMalote.MaloteEmail);                       
        MontaMalote.MaloteEmail.clear();                                                                      

        for (Map.Entry<String, List<String>> entry : maloteCopia.entrySet()) {                                
                                                                               
            String emailCliente = entry.getKey();
            List<String> maloteOriginal = entry.getValue();

            if (maloteOriginal == null || maloteOriginal.isEmpty()) {
                continue;
            }

            // ===============================
            // VERIFICA STATUS DA NFE
            // ===============================
            String numeroNota = extrairNumeroNota(maloteOriginal.get(0));
            boolean notaCancelada = false;

            NFEdto nfeDto = null;
            if (numeroNota != null) {
                nfeDto = NFEcontroller.NamedQueryFindByIDENNF(Integer.parseInt(numeroNota));
                if (nfeDto != null
                        && "Cancelada".equalsIgnoreCase(nfeDto.getINFNFE_STATUSNFE().trim())) {
                    notaCancelada = true;
                }
            }
            // ===============================
            // ADICIONA EMAIL PARA ÉLCIO SE CANCELADA
            // ===============================
            if (notaCancelada) {

                String emailCancelamento = "elcio@camarplasticos.com.br";

                if (emailCliente == null || emailCliente.trim().isEmpty()) {
                    emailCliente = emailCancelamento;
                } else if (!emailCliente.toLowerCase().contains(emailCancelamento)) {
                    emailCliente = emailCliente + ";" + emailCancelamento;
                }
            }

            // ===============================
            // REMOVE BOLETOS SE CANCELADA
            // ===============================
            List<String> maloteEnvio = new ArrayList<>();

            if (notaCancelada) {

                // Remove qualquer boleto do malote
                for (String caminho : maloteOriginal) {
                    if (!caminho.contains("BOLETO_")) {
                        maloteEnvio.add(caminho);
                    }
                }

                // Segurança extra: garante que nenhum boleto ficou
                maloteEnvio.removeIf(caminho -> caminho.contains("BOLETO_"));

            } else {

                maloteEnvio = new ArrayList<>(maloteOriginal);
            }

            List<CANFEDUPLICdto> duplicatas
                    = notaCancelada ? new ArrayList<>() : obterDuplicatasDoMalote(maloteEnvio);

            // ===============================
            // ASSUNTO
            // ===============================
            String assuntoEmail = "Camar Plásticos Ltda | Nota Fiscal: "
                    + nfenotfis.getNOT_NOTA().trim();

            if (notaCancelada) {
                assuntoEmail += " - CANCELADA";
            }

            if (System.getProperty("tpAmb").equals("2")) {
                assuntoEmail = "[HOMOLOGAÇÃO] " + assuntoEmail;
                emailCliente = "mvidoy@hotmail.com";
            }

            // ===============================
            // ENVIO
            // ===============================
            SendEmail emailSender = new SendEmail();

            // ANTES (31/03/2026): enviado = emailSender.sendEmail(...)
            // Refatorado por: Claude | Aprovado por: Osvaldo
            // Motivo: sobrescrita simples fazia o retorno refletir apenas o Ãºltimo e-mail do loop.
            //         Agora acumula com && para que qualquer falha individual contamine o retorno final.
            boolean resultadoEnvio = emailSender.sendEmail(
                    emailCliente.replace(";", ",").replaceAll("\\s+", ""),
                    !notaCancelada && !duplicatas.isEmpty(),
                    assuntoEmail,
                    getCorpoDoEmail(maloteEnvio, Banco, notaCancelada),
                    getAssinaturaDoEmail(),
                    maloteEnvio
            );
            enviado = enviado && resultadoEnvio;

            if (resultadoEnvio) {
                logprint.logprint("E-mail enviado com sucesso para " + emailCliente, Banco);
                montaMalote.limpaMaloteDoLoteGerado(maloteOriginal, Banco);
            } else {
                logprint.logprint("Falha ao enviar e-mail para " + emailCliente, Banco);
            }
        }

        return enviado;
    }

    // =========================================================
    // CORPO DO EMAIL
    // =========================================================
    public String getCorpoDoEmail(List<String> Malote,
            String Banco,
            boolean notaCancelada)
            throws SQLException, ParseException {

        System.setProperty("boletoGerado", "");

        if (Malote == null || Malote.isEmpty()) {
            return "Nenhum boleto, danfe ou xml encontrado para envio.";
        }

        StringBuilder corpo = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // ==========================================
        // IDENTIFICA NÚMERO DA NOTA
        // ==========================================
        String numeroNota = null;

        for (String caminho : Malote) {
            numeroNota = extrairNumeroNota(caminho);
            if (numeroNota != null) {
                break;
            }
        }

        String numeroNFeAutorizada = String.format("%06d", Integer.parseInt(numeroNota));
        int ideNNF = Integer.parseInt(numeroNFeAutorizada);

        NFEdto nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);

        boolean tituloregistrado = false;
        if (nfeDto != null && nfeDto.getINFNFE_TITULOREGISTRADO() != null
                && nfeDto.getINFNFE_TITULOREGISTRADO().equals("Sim")) {
            tituloregistrado = true;
        }

        CANFENOTFISdto nota = null;
        CLIENTEdto cliente = null;

        if (numeroNota != null) {
            nota = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNota);
            if (nota != null) {
                cliente = CLIENTEcontroller.FindCodigo(nota.getNOT_CLIE().trim());
            }
        }

        if (cliente == null) {
            return "Cliente não localizado para envio.";
        }

        String nomeCliente = cliente.getCLI_NOME().trim();

        corpo.append("Prezado(a) <b>")
                .append(nomeCliente)
                .append("</b>,<br><br>");

        // ==========================================
        // SE NOTA CANCELADA
        // ==========================================
        if (notaCancelada) {

            corpo.append("Informamos que a Nota Fiscal nº <b>")
                    .append(numeroNota)
                    .append("</b> foi <b>CANCELADA</b> junto à SEFAZ.<br><br>");

            corpo.append("Encaminhamos em anexo:<br>")
                    .append("- DANFE da NF-e autorizada;<br>")
                    .append("- XML da NF-e autorizada;<br>")
                    .append("- XML do evento de cancelamento.<br><br>");

            if (tituloregistrado) {
                corpo.append("Os boletos anteriormente registrados foram devidamente ")
                        .append("<b>cancelados junto ao banco</b>, nâo havendo qualquer ")
                        .append("pendência financeira referente a esta operaçâo.<br><br>");
            }

            corpo.append("Esta comunicação è apenas para formalização fiscal.<br><br>")
                    .append("Permanecemos à disposição para quaisquer esclarecimentos.");

            return corpo.toString();
        }

        // ==========================================
        // COBRANÇA NORMAL
        // ==========================================
        List<CANFEDUPLICdto> duplicatas = obterDuplicatasDoMalote(Malote);

        if (duplicatas.isEmpty() && nota != null) {

            corpo.append("Encaminhamos em anexo a DANFE e o XML referentes à Nota Fiscal nº <b>")
                    .append(nota.getNOT_NOTA().trim())
                    .append("</b>.");

            return corpo.toString();
        }

        System.setProperty("boletoGerado", "Sim");

        CANFEDUPLICdto duplicataPrincipal = duplicatas.get(0);

        if (duplicatas.size() == 1) {

            CANFEDUPLICdto d = duplicataPrincipal;

            String numeroNFe = d.getPAR_CODI().trim();
            String numDocBruto = d.getPAR_NUMDOC().trim().replace("/", "-");
            String valorFormatado = nf.format(d.getPAR_VALO());
            String vencimentoFormatado = sdf.format(d.getPAR_VENC());

            String[] partesDoc = numDocBruto.split("-");
            int numeroParcela = (partesDoc.length > 1) ? Integer.parseInt(partesDoc[1]) : 1;
            int totalParcelas = (partesDoc.length > 2) ? Integer.parseInt(partesDoc[2]) : 1;

            String textoParcela = (totalParcelas == 1)
                    ? "pagamento único"
                    : numeroParcela + "º de " + totalParcelas + " parcelas";

            corpo.append("Encaminhamos, em anexo, a DANFE, o XML e o boleto bancário referente à Nota Fiscal <b>")
                    .append(numeroNFe).append("</b>, referente a <b>")
                    .append(textoParcela).append("</b>.<br><br>");

            corpo.append("Resumo da cobrança:<br>")
                    .append("- Cliente: ").append(nomeCliente).append("<br>")
                    .append(totalParcelas > 1 ? "- Parcela: " + numeroParcela + " de " + totalParcelas + "<br>" : "")
                    .append("- Nosso Número: ").append(numDocBruto).append("<br>")
                    .append("- Valor: ").append(valorFormatado).append("<br>")
                    .append("- Vencimento: ").append(vencimentoFormatado).append("<br><br>");

        } else {

            corpo.append("Encaminhamos, em anexo, a DANFE, o XML e os boletos bancários referentes às seguintes duplicatas:<br><br>");
            corpo.append("<table border='1' cellpadding='4' cellspacing='0'>")
                    .append("<tr><th>Nota Fiscal</th><th>Nosso Número</th><th>Parcela</th><th>Valor</th><th>Vencimento</th></tr>");

            for (CANFEDUPLICdto d : duplicatas) {

                String numeroNFe = d.getPAR_CODI().trim();
                String numDocBruto = d.getPAR_NUMDOC().trim().replace("/", "-");
                String valorFormatado = nf.format(d.getPAR_VALO());
                String vencimentoFormatado = sdf.format(d.getPAR_VENC());

                String[] partesDoc = numDocBruto.split("-");
                int numeroParcela = (partesDoc.length > 1) ? Integer.parseInt(partesDoc[1]) : 1;
                int totalParcelas = (partesDoc.length > 2) ? Integer.parseInt(partesDoc[2]) : 1;

                corpo.append("<tr>")
                        .append("<td>").append(numeroNFe).append("</td>")
                        .append("<td>").append(numDocBruto).append("</td>")
                        .append("<td>").append(numeroParcela).append(" de ").append(totalParcelas).append("</td>")
                        .append("<td>").append(valorFormatado).append("</td>")
                        .append("<td>").append(vencimentoFormatado).append("</td>")
                        .append("</tr>");
            }

            corpo.append("</table><br>");
        }

        corpo.append("Solicitamos a gentileza de realizar o pagamento até a data de vencimento informada,<br>")
                .append("a fim de evitar a incidência de encargos por atraso.<br><br>")
                .append("Caso o pagamento já tenha sido efetuado, favor desconsiderar este e-mail.<br><br>")
                .append("Permanecemos à disposição para quaisquer esclarecimentos.");

        return corpo.toString();
    }

    // =========================================================
    // ASSINATURA
    // =========================================================
    public String getAssinaturaDoEmail() {
        return "Atenciosamente,<br>"
                + "Departamento de Faturamento<br>"
                + "<b>Camar Plásticos Ltda.</b><br>"
                + "Tel: (019) 3026-4100<br>"
                + "E-mail: <b>faturamento2@camarplasticos.com.br</b>";
    }

    // =========================================================
    // UTIL
    // =========================================================
    private String extrairNumeroNota(String caminhoArquivo) {

        String nomeArquivo = new File(caminhoArquivo).getName();
        String numeroNota = null;

        if (nomeArquivo.startsWith("BOLETO_")) {
            numeroNota = nomeArquivo.replace("BOLETO_", "")
                    .replace(".pdf", "")
                    .split("-")[0];
        } else if (nomeArquivo.startsWith("DANFE_")) {
            numeroNota = nomeArquivo.replace("DANFE_", "")
                    .replace(".pdf", "");
        } else if (nomeArquivo.startsWith("XML_")) {
            numeroNota = nomeArquivo.replace("XML_", "")
                    .replace(".xml", "");
        }

        if (numeroNota != null) {
            try {
                numeroNota = String.format("%06d",
                        Integer.parseInt(numeroNota));
            } catch (NumberFormatException ignored) {
            }
        }

        return numeroNota;
    }

    private List<CANFEDUPLICdto> obterDuplicatasDoMalote(List<String> malote)
            throws SQLException, ParseException {

        List<CANFEDUPLICdto> duplicatas = new ArrayList<>();

        for (String caminho : malote) {

            if (!caminho.contains("BOLETO_")) {
                continue;
            }

            String nomeArquivo = new File(caminho).getName();
            String baseNome = nomeArquivo.replace("BOLETO_", "")
                    .replace(".pdf", "");
            String[] partes = baseNome.split("-");

            if (partes.length >= 2) {

                String nota = partes[0];
                String parcela
                        = String.format("%02d", Integer.parseInt(partes[1]));

                CANFEDUPLICdto d
                        = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(nota, parcela);

                if (d != null) {
                    duplicatas.add(d);
                }
            }
        }

        return duplicatas;
    }
}
