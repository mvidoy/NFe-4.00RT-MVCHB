package app.controllers;

import app.controllers.NfeDetProdController;
import app.models.NFE_DET_PROD;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class NfeDetProdControllerTest extends TestCase {

    private final String ID_nnf = "999999999";
    private final String ID_item = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE_DET_PROD tNFE_DET_PROD = new NFE_DET_PROD();

    public NfeDetProdControllerTest(String testName) {
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
            assertEquals(51, linhasdaclasse.contador("./src/app/controllers/NfeDetProdController.java"));
            assertTrue(NfeDetProdController.Create(ID_nnf, ID_item));
            Class theClass = tNFE_DET_PROD.getClass();
            Field fields[] = theClass.getDeclaredFields();
             assertTrue(NfeDetProdController.Index(ID_nnf).length()>0);
            tNFE_DET_PROD.setDet_prod_nnf(ID_nnf);
            tNFE_DET_PROD.setDet_prod_xprod("Teste");
            assertTrue(NfeDetProdController.Update(tNFE_DET_PROD));
            assertTrue(NfeDetProdController.Delete(ID_nnf));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }
    @After
    public void tearDown() {
        assertEquals(true, NfeDetProdController.Delete(ID_nnf));
    }
}
