package app.controllers;

import app.models.NFE_TRANSP_VOL;
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
public class NfeTranspVolControllerTest extends TestCase {

    private final String ID_nnf = "999999999";
    //private final String ID_item = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE_TRANSP_VOL tNFE_TRANSP_VOL = new NFE_TRANSP_VOL();

    public NfeTranspVolControllerTest(String testName) {
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
            assertEquals(53, linhasdaclasse.contador("./src/app/controllers/NfeTranspVolController.java"));
            assertTrue(NfeTranspVolController.Create(ID_nnf));
            Class theClass = tNFE_TRANSP_VOL.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(NfeTranspVolController.Index(ID_nnf).length() > 0);
            tNFE_TRANSP_VOL.setTransp_vol_nnf(ID_nnf);
            tNFE_TRANSP_VOL.setTransp_vol_pesol("1.00");
            assertTrue(NfeTranspVolController.Update(tNFE_TRANSP_VOL));
            assertTrue(NfeTranspVolController.Delete(ID_nnf));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }
}
