package com.backend.api.cobranca.santander.ws;

import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class EnviaJsonSantanderWs {

    /* =====================================================
     * MÉTODO PRINCIPAL
     * ===================================================== */
    public static String enviaJsonSantanderWs(
            ServicosSantanderEnum servicosEnum,
            String jsonBody,
            String requestMethod,
            Map<String, String> queryParams
    ) throws Exception {

        return enviaJsonSantanderWs(
                servicosEnum,
                jsonBody,
                requestMethod,
                queryParams,
                null
        );
    }

    /* =====================================================
     * OVERLOAD COM pathParam
     * ===================================================== */
    public static String enviaJsonSantanderWs(
            ServicosSantanderEnum servicosEnum,
            String jsonBody,
            String requestMethod,
            Map<String, String> queryParams,
            String pathParam
    ) throws Exception {

        ServicosSantanderEnum resolvedEnum = servicosEnum.resolveAmbiente();
        StringBuilder urlBuilder = new StringBuilder(resolvedEnum.getUrl());

        if (pathParam != null && !pathParam.isEmpty()) {
            if (!urlBuilder.toString().endsWith("/")) {
                urlBuilder.append("/");
            }
            urlBuilder.append(pathParam);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(
                        URLEncoder.encode(entry.getKey(), "UTF-8")
                ).append("=")
                        .append(
                                URLEncoder.encode(entry.getValue(), "UTF-8")
                        ).append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        String url = urlBuilder.toString();
        System.out.println("[DEBUG] URL SANTANDER FINAL: " + url);

        if ("PATCH".equalsIgnoreCase(requestMethod)) {
            return executarPatchReal(url, jsonBody);
        }

        return executarMetodoPadrao(url, jsonBody, requestMethod);
    }

    /* =====================================================
     * MÉTODO PATCH REAL (Apache HttpClient)
     * ===================================================== */
    private static String executarPatchReal(String url, String jsonBody) throws Exception {

        String bearerToken = System.getProperty("santander.token");
        String caminhoPfx = System.getProperty("pathCertificadoSantanderPfx");
        String senhaPfx = System.getProperty("passwordSantanderPfx");
        String appKey = ServicosSantanderEnum.CLIENT_ID.getResolvedValue();

        SSLContext sslContext = montarSSLContext(caminhoPfx, senhaPfx);

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        HttpPatch patch = new HttpPatch(url);

        patch.setHeader("Authorization", "Bearer " + bearerToken.trim());
        patch.setHeader("X-Application-Key", appKey);
        patch.setHeader("Content-Type", "application/json");
        patch.setHeader("Accept", "application/json");

        patch.setEntity(new StringEntity(jsonBody, "UTF-8"));

        CloseableHttpResponse response = client.execute(patch);

        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

        System.out.println("[DEBUG] HTTP PATCH | Santander response: " + responseBody);

        response.close();
        client.close();

        return responseBody;
    }

    /* =====================================================
     * MÉTODO PADRÃO (POST/GET)
     * ===================================================== */
    private static String executarMetodoPadrao(
            String url,
            String jsonBody,
            String requestMethod
    ) throws Exception {

        String bearerToken = System.getProperty("santander.token");
        String caminhoPfx = System.getProperty("pathCertificadoSantanderPfx");
        String senhaPfx = System.getProperty("passwordSantanderPfx");
        String appKey = ServicosSantanderEnum.CLIENT_ID.getResolvedValue();

        SSLContext sslContext = montarSSLContext(caminhoPfx, senhaPfx);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        HttpsURLConnection conn = null;

        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();

            conn.setRequestMethod(requestMethod);
            conn.setUseCaches(false);
            conn.setDoInput(true);

            conn.setRequestProperty("Authorization", "Bearer " + bearerToken.trim());
            conn.setRequestProperty("X-Application-Key", appKey);
            conn.setRequestProperty("Accept", "application/json");

            if (!"GET".equalsIgnoreCase(requestMethod) && jsonBody != null) {
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonBody.getBytes("UTF-8"));
                }
            }

            int responseCode = conn.getResponseCode();

            Scanner scanner = new Scanner(
                    responseCode >= 200 && responseCode < 300
                            ? conn.getInputStream()
                            : conn.getErrorStream(),
                    "UTF-8"
            );

            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            System.out.println(
                    "[DEBUG] HTTP " + responseCode + " | Santander response: " + response
            );

            return response.toString();

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /* =====================================================
     * MONTA SSL CONTEXT (mTLS)
     * ===================================================== */
    private static SSLContext montarSSLContext(String caminhoPfx, String senhaPfx) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(caminhoPfx)) {
            keyStore.load(fis, senhaPfx.toCharArray());
        }

        return SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, senhaPfx.toCharArray())
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();
    }
}
