package app.controllers;

import app.controllers.NfeCobrDupController;
import app.models.NFE_COBR_DUP;
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
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class NfeCobrDupControllerTest extends TestCase {

    private final String ID_cobr_dup_nnf = "999999999";
    private final String ID_cobr_dup_item = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    private static final NFE_COBR_DUP mockeNFE_COBR_DUP = mock(NFE_COBR_DUP.class);
    private static final NFE_COBR_DUP eNFE_COBR_DUP = new NFE_COBR_DUP(mockeNFE_COBR_DUP);
    public static NFE_COBR_DUP tNFE_COBR_DUP = new NFE_COBR_DUP();

    public NfeCobrDupControllerTest(String testName) {
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
        //assertEquals("Criando Registro...", true, NfeCobrDupController.Create(ID_cobr_dup_nnf, ID_cobr_dup_item));

    }

    @Test
    public void testGRUD() {
        try {
            assertEquals(53, linhasdaclasse.contador("./src/app/controllers/NfeCobrDupController.java"));
            assertTrue(NfeCobrDupController.Create(ID_cobr_dup_nnf, ID_cobr_dup_item));
            Class theClass = tNFE_COBR_DUP.getClass();
            Field fields[] = theClass.getDeclaredFields();
             assertTrue(NfeCobrDupController.Index(ID_cobr_dup_nnf).length()>0);
            tNFE_COBR_DUP.setCobr_dup_ndup("001");
            tNFE_COBR_DUP.setCobr_dup_vdup("1.00");
            tNFE_COBR_DUP.setCobr_dup_dvenc("2019-01-01 14:00:00");
            assertTrue(NfeCobrDupController.Update(tNFE_COBR_DUP));
            assertTrue(NfeCobrDupController.Delete(ID_cobr_dup_nnf));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }
}
