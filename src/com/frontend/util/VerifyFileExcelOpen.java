package com.frontend.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class VerifyFileExcelOpen {

    public static boolean isOpen(String excelFile) {
        File file = new File(excelFile);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.getChannel().lock(0L, Long.MAX_VALUE, true);
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
