package com.backend.api.cobranca.bradesco.ws;

import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import static com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum.CLIENT_ID;
import static com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum.CLIENT_SECRET;
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

public class TokenBradescoWs {

    //Aplicação = Cobrança Teste
    //24/10/2025 - Nome da Credencial = COBRANÇA
    //static String Client_ID = "4a6e34d6-5997-4b06-8513-8db3879ea6ee";     //Homologação
    //static String Client_Secret = "984a7570-f785-43d4-859d-5e15b157d224"; //Homologação
    //Aplicação = Cobrança Homologação - 29/10/2025
    //Nome da Credencial = Cobrança Homologação4 - 29/10/2025
    //static String Client_ID = "c22fe833-707d-41fa-92f2-b3bd9cb94419";     //Homologação
    //static String Client_Secret = "4f388d4c-e408-4ca8-8831-fc1ce7de5daf"; //Homologação
    //Nome da Credencial = Cobranca Producao - 29/10/2025
    static String Client_ID = CLIENT_ID.getResolvedValue(); 
    static String Client_Secret = CLIENT_SECRET.getResolvedValue();
    //openssl genpkey -algorithm RSA -out privatekey.key -pkeyopt rsa_keygen_bits:2048
    //openssl req -new -key privatekey.key -out certificate.csr  
    //openssl x509 -req -days 365 -in certificate.csr -signkey privatekey.key -out certificate.crt
    //openssl pkcs12 -export -out client-prebanco.pfx -inkey privatekey.key -in certificate.crt -name "client-mtls" -passout pass:261042jp
    // TokenBradescoWs cache
    private static String tokenAtual = null;
    private static long tokenExpiraEmMillis = 0;

    public static synchronized String tokenBradesco() throws Exception {
        ServicosBradescoEnum urlEnum = ServicosBradescoEnum.TOKENBRADESCO.resolveAmbiente();
        String url = urlEnum.getUrl();
        long agora = System.currentTimeMillis();

        if (tokenAtual != null && agora < tokenExpiraEmMillis) {
            return tokenAtual; // Retorna token válido em cache
        }

        String caminhoPfx = System.getProperty("pathCertificadoBradescoPfx");
        String senhaPfx = System.getProperty("passwordBradescoPfx");

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

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        URL endpoint = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) endpoint.openConnection();
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
            System.out.println("Erro ao obter token: " + response.toString());
            return null;
            //throw new RuntimeException("Erro ao obter token: " + response.toString());
        }
    }
}
