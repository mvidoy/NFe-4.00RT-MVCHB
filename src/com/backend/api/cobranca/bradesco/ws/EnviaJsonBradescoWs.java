package com.backend.api.cobranca.bradesco.ws;

import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class EnviaJsonBradescoWs {

    public static String enviaJsonBradescoWs(ServicosBradescoEnum servicosEnum, String jsonBody, String requestMethod) throws Exception {
        // Resolve a URL correta com base no ambiente (tpAmb)
        ServicosBradescoEnum resolvedEnum = servicosEnum.resolveAmbiente();
        String url = resolvedEnum.getUrl();

        String bearerToken = System.getProperty("bradesco.token");
        String caminhoPfx = System.getProperty("pathCertificadoBradescoPfx");
        String senhaPfx = System.getProperty("passwordBradescoPfx");

        if (bearerToken == null || caminhoPfx == null || senhaPfx == null) {
            throw new IllegalStateException("Token, caminho do certificado ou senha do certificado não definidos");
        }

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(caminhoPfx)) {
            keyStore.load(fis, senhaPfx.toCharArray());
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, senhaPfx.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        HttpsURLConnection conn = null;
        try {
            URL endpoint = new URL(url);
            conn = (HttpsURLConnection) endpoint.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + bearerToken);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes("UTF-8"));
            }

            int responseCode = conn.getResponseCode();
            Scanner scanner = new Scanner(responseCode >= 200 && responseCode < 300 ? conn.getInputStream() : conn.getErrorStream(), "UTF-8");

            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            String responseBody = response.toString(); // conversão correta
            System.out.println("Codigo de retorno: " + responseCode + " - json: " + responseBody);
            return responseBody;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
