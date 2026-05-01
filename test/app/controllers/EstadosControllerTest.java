package app.controllers;

import app.models.ESTADOS;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.mockito.Mockito.*;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class EstadosControllerTest extends TestCase {

    private final String ID_SIGL = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    private static final ESTADOS mockeESTADOS = mock(ESTADOS.class);
    private static final ESTADOS eESTADOS = new ESTADOS(mockeESTADOS);
    public static ESTADOS tESTADOS = new ESTADOS();

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
            assertEquals(72, linhasdaclasse.contador("./src/app/controllers/EstadosController.java"));
            assertTrue(EstadosController.Create(ID_SIGL));

            Class theClass = tESTADOS.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(EstadosController.Index(ID_SIGL).length() > 0);

            tESTADOS.setEst_sigl(ID_SIGL);
            tESTADOS.setEst_desc("Teste");

            assertTrue(EstadosController.Update(tESTADOS));
            assertTrue(EstadosController.Delete(ID_SIGL));
        } catch (IOException | SecurityException | SQLException e) {
            fail();
        } catch (InstantiationException ex) {
            Logger.getLogger(EstadosControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EstadosControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
