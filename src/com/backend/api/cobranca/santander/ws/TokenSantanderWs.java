package com.backend.api.cobranca.santander.ws;

import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;
import static com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum.CLIENT_ID;
import static com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum.CLIENT_SECRET;
import static com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum.CERTIFICADOSANTANDERJKSPRODUCAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class TokenSantanderWs {

    static String Client_ID = CLIENT_ID.getResolvedValue();
    static String Client_Secret = CLIENT_SECRET.getResolvedValue();

    private static String tokenAtual = null;
    private static long tokenExpiraEmMillis = 0;

    public static synchronized String tokenSantander() throws Exception {
        ServicosSantanderEnum urlEnum = ServicosSantanderEnum.TOKENSANTANDER.resolveAmbiente();
        String url = urlEnum.getUrl();
        long agora = System.currentTimeMillis();

        if (tokenAtual != null && agora < tokenExpiraEmMillis) {
            return tokenAtual; // Retorna token válido em cache
        }

        String caminhoPfx = System.getProperty("pathCertificadoSantanderPfx");
        String senhaPfx = System.getProperty("passwordSantanderPfx");
        String senhaTrustStore = "changeit";
        String caminhoTrustStore = null;

        if (!"2".equals(System.getProperty("tpAmb"))) {
            caminhoTrustStore = CERTIFICADOSANTANDERJKSPRODUCAO.getResolvedValue();
        }

        // Validação preventiva para evitar NullPointerException
        if (caminhoPfx == null || caminhoPfx.isEmpty()) {
            throw new IllegalStateException("Caminho do certificado PFX não definido.");
        }
        if (senhaPfx == null) {
            throw new IllegalStateException("Senha do certificado PFX não definida.");
        }

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(caminhoPfx)) {
            keyStore.load(fis, senhaPfx.toCharArray());
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, senhaPfx.toCharArray());

        TrustManagerFactory tmf
                = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        if (caminhoTrustStore != null && !caminhoTrustStore.isEmpty()) {
            KeyStore trustStore = KeyStore.getInstance("JKS");
            try (InputStream ts = new FileInputStream(caminhoTrustStore)) {
                trustStore.load(ts, senhaTrustStore.toCharArray());
            }
            tmf.init(trustStore);   // PRODUÇÃO
        } else {
            tmf.init((KeyStore) null); // SANDBOX (mantém funcionando)
        }

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        //HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        URL endpoint = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) endpoint.openConnection();
        conn.setSSLSocketFactory(sslContext.getSocketFactory());
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String authBody = "grant_type=client_credentials"
                + "&client_id=" + Client_ID
                + "&client_secret=" + Client_Secret;

        try (OutputStream os = conn.getOutputStream()) {
            os.write(authBody.getBytes("UTF-8"));
        }

        int responseCode = conn.getResponseCode();
        InputStream is = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        StringBuilder response = new StringBuilder();
        try (Scanner scanner = new Scanner(is, "UTF-8")) {
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
        }

        if (responseCode >= 200 && responseCode < 300) {
            // Parse JSON
            String json = response.toString();

            // Corrige parsing mesmo que o valor venha com aspas extras
            String accessToken = json.split("\"access_token\"\\s*:\\s*\"")[1].split("\"")[0];
            String expiresInStr = json.split("\"expires_in\"\\s*:\\s*")[1].split("[,}]")[0]
                    .replaceAll("\"", "").trim(); // <-- Corrige ""3600""
            int expiresIn = Integer.parseInt(expiresInStr);

            tokenAtual = accessToken;
            tokenExpiraEmMillis = System.currentTimeMillis() + ((expiresIn - 60) * 1000); // 1 min de margem
            return tokenAtual;
        } else {
            System.out.println("Erro: [" + responseCode + "] ao obter token Santander: " + response.toString());
            return null;
            //throw new RuntimeException("Erro ao obter token: " + response.toString());
        }
    }
}
