package com.backend.api.cobranca.mensageria;

import com.frontend.config.env;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.util.Arrays.asList;

public class Logprint {

    public void logprint(String sMessage, String sBanco) {
        String stamp = new java.text.SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new java.util.Date());
        String line = (sMessage != null && !sMessage.isEmpty() ? stamp + " - " : "") + (sMessage == null ? "" : sMessage);
        env Env = new env();
        String baseDir = Env.getHOST() + "\\bd_modulo01";
        Path logDir = Paths.get(baseDir, "banco"+sBanco, "log");
        Path logFile = logDir.resolve("logCobranca.txt");
        try {
            Files.createDirectories(logDir);
            Files.write(
                    logFile,
                    asList(line),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Falha ao gravar log: " + e.getMessage());
        }
    }

}
