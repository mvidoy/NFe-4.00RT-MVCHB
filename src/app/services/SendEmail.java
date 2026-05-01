package app.services;

import java.io.File;
import java.util.Date;
import java.util.Properties;
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
    private static final int SMTP_PORT = 465;
    private static final String SMTP_USER = "gestao@camarplasticos.com.br";
    private static final String SMTP_PASSWORD = "!@#$261042OmV";

    public boolean sendEmail(String emailDest, String assunto, String mensagemCorpo, String assinatura, String anexo)
            {
        boolean envioSucesso = true;

        // Configuração das propriedades SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "email-ssl.com.br");
        props.put("mail.smtp.port", "587"); // ? Use porta 587 para STARTTLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // ? Habilita STARTTLS
        props.put("mail.smtp.ssl.trust", "email-ssl.com.br"); // ? Confia no servidor
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.mime.charset", "ISO-8859-1");

        // Autenticação do servidor SMTP
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
            }
        });

        session.setDebug(true); // Ativa logs para depuração

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER)); // Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDest)); // Destinatário
            message.setSubject(assunto); // Assunto

            // Corpo do e-mail
            MimeBodyPart corpo = new MimeBodyPart();
            corpo.setText(mensagemCorpo + "\n\n" + assinatura);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpo);

            // Adicionar anexo, se houver
            if (anexo != null && !anexo.trim().isEmpty()) {
                File arquivo = new File(anexo);
                if (arquivo.exists()) {
                    MimeBodyPart anexoPart = new MimeBodyPart();
                    anexoPart.setDataHandler(new DataHandler(new FileDataSource(arquivo)));
                    anexoPart.setFileName(arquivo.getName());
                    multipart.addBodyPart(anexoPart);
                } else {
                    System.err.println("?? Arquivo anexo não encontrado: " + anexo);
                }
            }

            message.setContent(multipart);
            message.setSentDate(new Date());

            // Enviar e-mail
            Transport.send(message);
            System.out.println("? Email enviado com sucesso para " + emailDest);

        } catch (MessagingException e) {
            envioSucesso = false;
            System.err.println("? Erro ao enviar e-mail: " + e.getMessage());
        }

        return envioSucesso;
    }

}
