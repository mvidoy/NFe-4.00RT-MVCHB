package app.controllers;

import app.controllers.NfeInfProtController;
import app.models.NFE_INFPROT;
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
public class NfeInfProtControllerTest extends TestCase {

    private final String ID_nnf = "999999999";
    private final String ID_item = "999";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE_INFPROT tNFE_INFPROT = new NFE_INFPROT();

    public NfeInfProtControllerTest(String testName) {
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
            assertEquals(51, linhasdaclasse.contador("./src/app/controllers/NfeInfProtController.java"));
            assertTrue(NfeInfProtController.Create(ID_nnf, ID_item));
            Class theClass = tNFE_INFPROT.getClass();
            Field fields[] = theClass.getDeclaredFields();
             assertTrue(NfeInfProtController.Index(ID_nnf).length()>0);
            tNFE_INFPROT.setProtnfe_nnf(ID_nnf);
            tNFE_INFPROT.setInfprot_cstat(ID_item);
            tNFE_INFPROT.setInfprot_xmotivo("Teste");
            assertTrue(NfeInfProtController.Update(tNFE_INFPROT));
            assertTrue(NfeInfProtController.Delete(ID_nnf));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }

    /*
    @Test(expected = NullPointerException.class)
    public void testIndex_IdExistOrNotOrNull() {
        try {
            Class theClass = tNFE_INFPROT.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertEquals(fields.length - 1, NfeInfProtController.Index(ID_cobr_dup_nnf).length());
            TestCase.assertEquals(0, NfeInfProtController.Index(null).length());
        } catch (SQLException ex) {
            Logger.getLogger(NfeInfProtControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
    @After
    public void tearDown() {
        assertEquals(true, NfeInfProtController.Delete(ID_nnf));
    }
}
