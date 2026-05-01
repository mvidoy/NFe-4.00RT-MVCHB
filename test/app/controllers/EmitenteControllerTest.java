package app.controllers;

import app.models.EMITENTE;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
public class EmitenteControllerTest extends TestCase {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    private final String IDEMP_CODI = "9999"; //Id a ser criato no BD.

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    @Spy // mock it partially
    @InjectMocks
    EmitenteController mEmitenteController;

    private static final EMITENTE mockeEMITENTE = mock(EMITENTE.class);
    private static final EMITENTE eEMITENTE = new EMITENTE(mockeEMITENTE);
    private EMITENTE tEMITENTE;

    public EmitenteControllerTest(String testName) {
        super(testName);
    }

    @Before
    public void setUp() {
        tEMITENTE = new EMITENTE();
        System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
        System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
        System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
        System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
        System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
        System.setProperty("tpAmb", Env.gettpAMB().toString());
    }

    @Test
    public void testGRUD() throws SQLException {
        try {
            //EmitenteController mEmitenteController = Mockito.mock(EmitenteController.class);
            assertEquals(123, linhasdaclasse.contador("./src/app/controllers/EmitenteController.java"));
            //Mockito.when(mEmitenteController.Create(IDEMP_CODI)).thenReturn(true);
            assertTrue(EmitenteController.Create(IDEMP_CODI));
            Class theClass = tEMITENTE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(EmitenteController.Index(IDEMP_CODI).length() > 0);
            tEMITENTE.setEMP_CODI(IDEMP_CODI);
            tEMITENTE.setEMP_RZSO("Teste");
            assertTrue(EmitenteController.Update(tEMITENTE));
            assertTrue(EmitenteController.Delete(IDEMP_CODI));
        } catch (IOException | SecurityException e) {
            Assert.assertTrue(true);
        }
    }
}
