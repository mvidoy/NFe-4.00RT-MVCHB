package app.controllers;

import app.controllers.MunicipiosController;
import app.models.MUNICIPIOS;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class MunicipiosControllerTest extends TestCase {

    private final String ID_CODIGO = "9999999";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    private static final MUNICIPIOS mockeMUNICIPIOS = mock(MUNICIPIOS.class);
    private static final MUNICIPIOS eMUNICIPIOS = new MUNICIPIOS(mockeMUNICIPIOS);
    public static MUNICIPIOS tMUNICIPIOS = new MUNICIPIOS();

    public MunicipiosControllerTest(String testName) {
        super(testName);
    }

    @Before
    public void setUp() {
        System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
        System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
        System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
        System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
        System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
        System.setProperty("tpAmb", Env.gettpAMB().toString());
    }

    @Test
    public void testGRUD() throws InstantiationException, IllegalAccessException, SQLException {
        try {
            assertEquals(80, linhasdaclasse.contador("./src/app/controllers/MunicipiosController.java"));
            assertTrue(MunicipiosController.Create(ID_CODIGO));

            Class theClass = tMUNICIPIOS.getClass();
            Field fields[] = theClass.getDeclaredFields();
             assertTrue(MunicipiosController.Index(ID_CODIGO).length()>0);

            tMUNICIPIOS.setMun_codigo(ID_CODIGO);
            tMUNICIPIOS.setMun_descricao("Teste");

            assertTrue(MunicipiosController.Update(tMUNICIPIOS));
            assertTrue(MunicipiosController.Delete(ID_CODIGO));
        } catch (IOException | SecurityException e) {
            Assert.assertTrue(true);
        }
    }
}
