package com.frontend.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificaConexaoInternet {

    public boolean isConexaoInternet() {
        HttpURLConnection conexao = null;
        try {
            URL url = new URL("http://www.google.com");
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("HEAD");
            conexao.setConnectTimeout(3000);
            conexao.setReadTimeout(3000);
            conexao.setRequestProperty("User-Agent", "Java Connection Test");
            int responseCode = conexao.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException e) {
            return false;
        } finally {
            if (conexao != null) {
                conexao.disconnect(); // libera os recursos
            }
        }
    }
}
