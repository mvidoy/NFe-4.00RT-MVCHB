package com.backend.api.cobranca.mensageria;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    private static final String SMTP_HOST = "email-ssl.com.br";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_USER_FINANCEIRO = "faturamento2@camarplasticos.com.br";
    private static final String SMTP_PASSWORD_FINANCEIRO = "!@#$261042OmV";
    private String SMTP_EMAILCOPIA = null;

    public boolean sendEmail(
            String emailDest,
            Boolean emailFinanceiro,
            String assunto,
            String mensagemCorpo,
            String assinatura,
            List<String> anexos) {
        boolean envioSucesso = true;

        SMTP_EMAILCOPIA
                = "osvaldo@camarplasticos.com.br";

        if (emailFinanceiro && System.getProperty("tpAmb").equals("1")) {
            SMTP_EMAILCOPIA = SMTP_EMAILCOPIA + ",simone2@camarplasticos.com.br";
        }

        if (System.getProperty("tpAmb").equals("1")) {
            SMTP_EMAILCOPIA = SMTP_EMAILCOPIA
                    + ",vinicius@ecpcontabil.com.br"
                    + ",faturamento2@camarplasticos.com.br";
        }

        // Cria uma cópia dinâmica da lista de emails
        String smtpEmailCopia = SMTP_EMAILCOPIA;

        // Se o destinatário for "faturamento2", remove dos emails de cópia
        if (emailDest != null && emailDest.trim().equalsIgnoreCase("faturamento2@camarplasticos.com.br")) {
            smtpEmailCopia = smtpEmailCopia.replaceAll("(?i),?\\s*faturamento2@camarplasticos\\.com\\.br\\s*", "");
        }

        SMTP_EMAILCOPIA = smtpEmailCopia;

        // Configuração das propriedades SMTP
        System.setProperty("mail.mime.encodeparameters", "false");
        Properties props = new Properties();
        props.put("mail.smtp.host", "email-ssl.com.br");
        props.put("mail.smtp.ssl.trust", "email-ssl.com.br"); // ? Confia no servidor
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");// ? Habilita STARTTLS
        props.put("mail.smtp.ssl.enable", "false"); // desabilita SSL direto
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.mime.charset", "ISO-8859-1");
        props.put("mail.mime.encodeparameters", "false");
        props.put("mail.debug", "true");

        // Autenticação do servidor SMTP
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER_FINANCEIRO, SMTP_PASSWORD_FINANCEIRO);
            }
        });

        session.setDebug(true); // Ativa logs para depuração

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER_FINANCEIRO)); // Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDest)); // Destinatário
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(SMTP_EMAILCOPIA));
            message.setSubject(assunto); // Assunto

            // Corpo do e-mail
            MimeBodyPart corpo = new MimeBodyPart();
            //corpo.setText(mensagemCorpo + "\n\n" + assinatura);
            corpo.setContent(mensagemCorpo + "<br><br>" + assinatura, "text/html; charset=ISO-8859-1");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpo);

            // Adiciona todos os anexos do lote
            if (anexos != null && !anexos.isEmpty()) {
                for (String anexo : anexos) {
                    if (anexo == null || anexo.trim().isEmpty()) {
                        continue;
                    }

                    File arquivo = new File(anexo);
                    if (arquivo.exists()) {
                        MimeBodyPart anexoPart = new MimeBodyPart();
                        anexoPart.setDataHandler(new DataHandler(new FileDataSource(arquivo)));
                        anexoPart.setFileName(arquivo.getName());
                        multipart.addBodyPart(anexoPart);
                        System.out.println("Anexo adicionado: " + arquivo.getName());
                    } else {
                        System.err.println("Arquivo anexo não encontrado: " + anexo);
                    }
                }
            }

            message.setContent(multipart);
            message.setSentDate(new Date());

            // Enviar e-mail
            Transport.send(message);
            System.out.println("? Email enviado com sucesso para " + emailDest);

        } catch (MessagingException e) {
            envioSucesso = false;
            System.err.println("? Erro ao enviar e-mail (" + emailDest + "): " + e.getMessage());
        }

        return envioSucesso;
    }

    public String getIp() {
        String wIp = "";
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) ias.nextElement();
                    if (ia.getHostAddress().contains(".")) {
                        wIp = ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wIp;
    }

}
