package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Ignore;
@Ignore
public class CountLinesClasseTest {

    public int contador(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        BufferedReader arquivo = new BufferedReader(new FileReader(f));
        int somaAux = 0;
        while ((arquivo.readLine()) != null) {
            somaAux++;
        }
        return somaAux;
    }
}
