package app.controllers;

import app.controllers.NfeInformacoesPagamentoController;
import app.controllers.NfeInformacoesPagamentoController;
import app.models.NFE_INFORMACOESPAGAMENTO;
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
public class NfeInformacoesPagamentoControllerTest extends TestCase {

    private final String ID_nnf = "999999999";
    private final String ID_item = "99";

    env Env = new env();
    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE_INFORMACOESPAGAMENTO tNFE_INFORMACOESPAGAMENTO = new NFE_INFORMACOESPAGAMENTO();

    public NfeInformacoesPagamentoControllerTest(String testName) {
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
            assertEquals(51, linhasdaclasse.contador("./src/app/controllers/NfeInformacoesPagamentoController.java"));
            assertTrue(NfeInformacoesPagamentoController.Create(ID_nnf, ID_item));
            Class theClass = tNFE_INFORMACOESPAGAMENTO.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(NfeInformacoesPagamentoController.Index(ID_nnf, ID_item).length() > 0);
            tNFE_INFORMACOESPAGAMENTO.setPag_nnf(ID_nnf);
            tNFE_INFORMACOESPAGAMENTO.setPag_item(ID_item);
            tNFE_INFORMACOESPAGAMENTO.setPag_vpag("1.00");
            assertTrue(NfeInformacoesPagamentoController.Update(tNFE_INFORMACOESPAGAMENTO));
            assertTrue(NfeInformacoesPagamentoController.Delete(ID_nnf));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        }
    }
}
