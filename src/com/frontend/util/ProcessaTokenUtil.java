package com.frontend.util;

import static com.backend.api.cobranca.bradesco.ws.TokenBradescoWs.tokenBradesco;
import static com.backend.api.cobranca.santander.ws.TokenSantanderWs.tokenSantander;
import java.io.File;

public class ProcessaTokenUtil {

    public boolean processaTokenBradesco() throws Exception {
        String token = "";
        System.setProperty("bradesco.token", token);

        String pathCert = System.getProperty("pathCertificadoBradescoPfx");
        File arquivoCert = new File(pathCert);

        if (!arquivoCert.exists()) {
            System.out.println("Certificado não localizado no caminho: " + pathCert);
            return false;
        }

        VerificaConexaoInternet verifivaConexaoInternet = new VerificaConexaoInternet();

        if (verifivaConexaoInternet.isConexaoInternet()) {
            token = tokenBradesco(); // chama método que gera o token
            //System.out.println("Token Bradesco gerado: " + token);
        } else {
            //JOptionPane.showMessageDialog(this, "Sem conexão com a internet, verifique!", "Mensagem", JOptionPane.ERROR_MESSAGE);
            System.out.println("Sem conexão com a internet. Token não gerado.");
            return false;
        }

        //token = token(); // chama método que gera o token
        if (token != null && !token.trim().isEmpty()) {
            System.setProperty("bradesco.token", token);
            System.out.println("Token Bradesco gerado: " + token);
            return true;
        } else {
            System.out.println("Falha na geração do Token Bradesco");
            return false;
        }
    }

    public boolean processaTokenSantander() throws Exception {
        String token = "";
        System.setProperty("santander.token", token);

        String pathCert = System.getProperty("pathCertificadoSantanderPfx");
        File arquivoCert = new File(pathCert);

        if (!arquivoCert.exists()) {
            System.out.println("Certificado não localizado no caminho: " + pathCert);
            return false;
        }

        VerificaConexaoInternet verifivaConexaoInternet = new VerificaConexaoInternet();

        if (verifivaConexaoInternet.isConexaoInternet()) {
            token = tokenSantander(); // chama método que gera o token
            //System.out.println("Token Santander gerado: " + token);
        } else {
            //JOptionPane.showMessageDialog(this, "Sem conexão com a internet, verifique!", "Mensagem", JOptionPane.ERROR_MESSAGE);
            System.out.println("Sem conexão com a internet. Token não gerado.");
            return false;
        }

        //token = token(); // chama método que gera o token
        if (token != null && !token.trim().isEmpty()) {
            System.setProperty("santander.token", token);
            System.out.println("Token Santander gerado: " + token);
            return true;
        } else {
            System.out.println("Falha na geração do Token Santander");
            return false;
        }
    }
}
