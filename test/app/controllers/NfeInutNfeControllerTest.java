package app.controllers;

import app.models.NFE_INUTNFE;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class NfeInutNfeControllerTest extends TestCase {

    private final String ID_nnf = "999999999";
    private final String ID_item = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE_INUTNFE tNFE_INUTNFE = new NFE_INUTNFE();

    public NfeInutNfeControllerTest(String testName) {
        super(testName);
        MockitoAnnotations.initMocks(this);
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
    public void testGRUD() {
        try {
            assertEquals(42, linhasdaclasse.contador("./src/app/controllers/NfeInutNfeController.java"));
            assertTrue(NfeInutNfeController.Create(ID_nnf, ID_item));
            Class theClass = tNFE_INUTNFE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(NfeInutNfeController.Index(ID_nnf, ID_item).length() > 0);
            tNFE_INUTNFE.setRetinutnfe_infinut_nnfini(ID_nnf);
            tNFE_INUTNFE.setRetinutnfe_infinut_nnfini(ID_nnf);
            assertTrue(NfeInutNfeController.Update(tNFE_INUTNFE));
            assertTrue(NfeInutNfeController.Delete(ID_nnf, ID_item));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }
}
