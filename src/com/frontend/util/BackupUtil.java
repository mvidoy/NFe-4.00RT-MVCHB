package com.frontend.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupUtil {

    /**
     * Cria uma cópia do arquivo prevcaixa.xls na subpasta "backupsprevcaixa"
     * com timestamp. Exemplo:
     * backupsprevcaixa/prevcaixa_backup_20251110_143002.xls
     *
     * @param originalPath Caminho do arquivo original
     * @throws IOException Caso ocorra falha na cópia
     */
    public static void criarBackupPrevCaixa(String originalPath) throws IOException {
        File originalFile = new File(originalPath);

        if (!originalFile.exists()) {
            throw new IOException("Arquivo original não encontrado: " + originalPath);
        }

        // Diretório pai do arquivo original
        String parentDir = originalFile.getParent();
        // Diretório de backup
        File backupDir = new File(parentDir, "backupsprevcaixa");

        // Cria diretório de backup se não existir
        if (!backupDir.exists()) {
            if (!backupDir.mkdirs()) {
                throw new IOException("Falha ao criar diretório de backup: " + backupDir.getAbsolutePath());
            }
        }

        // Nome do arquivo de backup com timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupFileName = "prevcaixa_backup_" + timestamp + ".xls";
        File backupFile = new File(backupDir, backupFileName);

        // Realiza a cópia
        try (FileChannel sourceChannel = new FileInputStream(originalFile).getChannel();
                FileChannel destChannel = new FileOutputStream(backupFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println("[INFO] Cópia de segurança criada em: " + backupFile.getAbsolutePath());
        }
    }
}
