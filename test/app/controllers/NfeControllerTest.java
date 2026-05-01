package app.controllers;

import app.models.NFE;
import com.frontend.config.env;
import java.io.IOException;
import java.lang.reflect.Field;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.Date;

import org.mockito.MockitoAnnotations;



import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import util.CountLinesClasseTest;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(PowerMockRunner.class)
public class NfeControllerTest extends TestCase {

    private final String ID = "999999999";

    env Env = new env();

    CountLinesClasseTest linhasdaclasse = new CountLinesClasseTest();

    public static NFE tNFE = new NFE();

    public NfeControllerTest(String testName) {
        super(testName);
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
        System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
        System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
        System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
        System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
        System.setProperty("tpAmb", Env.gettpAMB().toString());
        //assertTrue(NfeController.Delete(ID));
        assertTrue(NfeController.Create(ID));
    }

    @After
    public void tearDown() {
        assertTrue(NfeController.Delete(ID));
    }

    @Test
    public void testGRUD() {
        try {
            assertEquals(151, linhasdaclasse.contador("./src/app/controllers/NfeController.java"));
            //assertTrue(NfeController.Create(ID));
            Class theClass = tNFE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            assertTrue(NfeController.Index(ID).length() > 0);
            tNFE.setIde_nnf(ID);
            tNFE.setDest_xnome(null);
            assertTrue(NfeController.Update(tNFE));
            //assertTrue(NfeController.Delete(ID));
        } catch (IOException | IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            Assert.assertTrue(true);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testIndex_shouldnotallowemptynnf() throws SQLException, ParseException {
        assertTrue(NfeController.Index("").length() <= 0);
    }

    @Test
    public void testIndex_shouldreturnemptyfocused() throws SQLException, ParseException {
        assertTrue(NfeController.Index("888888").length() <= 0);
    }

    @Test
    public void testIndex_shouldreturnfield_infprot_dhrecbto_addressta_ddMMyyyy_HHmmss() throws SQLException, ParseException, InstantiationException, IllegalAccessException, Exception {
        tNFE.setIde_nnf(ID);
        tNFE.setDest_xnome(null);
        tNFE.setInfprot_dhrecbto("2019-10-24 10:08:34");
        assertTrue(NfeController.Update(tNFE));
        assertEquals("24/10/2019 10:08:34", NfeController.Index(ID).getString("infprot_dhrecbto"));
    }

    @Test
    public void testIndex_shouldreturnfield_ide_dhemi_addressta_ddMMyyyy_HHmmss() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        tNFE.setIde_nnf(ID);
        tNFE.setDest_xnome(null);
        tNFE.setIde_dhemi("2019-10-24 10:08:34");
        assertTrue(NfeController.Update(tNFE));
        assertEquals("24/10/2019 10:08:34", NfeController.Index(ID).getString("ide_dhemi"));
    }

    @Test
    public void testIndex_shouldreturnfield_ide_dhsaient_addressta_ddMMyyyy_HHmmss() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        tNFE.setIde_nnf(ID);
        tNFE.setDest_xnome(null);
        tNFE.setIde_dhsaient("2019-10-24 10:08:34");
        assertTrue(NfeController.Update(tNFE));
        assertEquals("24/10/2019 10:08:34", NfeController.Index(ID).getString("ide_dhsaient"));
    }

    @Test
    public void testIndex_shouldreturnfield_signature_datainicial_addressta_ddMMyyyy_HHmmss() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        tNFE.setIde_nnf(ID);
        tNFE.setDest_xnome(null);
        tNFE.setSignature_datainicial("2019-10-24 10:08:34");
        assertTrue(NfeController.Update(tNFE));
        assertEquals("24/10/2019 10:08:34", NfeController.Index(ID).getString("signature_datainicial"));
    }

    @Test
    public void testIndex_shouldreturnfield_signature_datafinal_addressta_ddMMyyyy_HHmmss() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        tNFE.setIde_nnf(ID);
        tNFE.setDest_xnome(null);
        tNFE.setSignature_datafinal("2019-10-24 10:08:34");
        assertTrue(NfeController.Update(tNFE));
        assertEquals("24/10/2019 10:08:34", NfeController.Index(ID).getString("signature_datafinal"));
    }

    @Test
    public void testIndex_shouldnotcreateemptynnfrecord() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        assertTrue(NfeController.Delete(ID));
        assertFalse(NfeController.Create(""));
        assertTrue(NfeController.Create(ID));
    }

    @Test
    public void testIndex_shouldnotupdateemptynnfrecord() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        assertTrue(NfeController.Delete(ID));
        tNFE.setIde_nnf("");
        tNFE.setDest_xnome(null);
        assertFalse(NfeController.Update(tNFE));
        assertTrue(NfeController.Create(ID));
    }

    @Test
    public void testIndex_shouldnotdeleteemptynnfrecord() throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        assertTrue(NfeController.Delete(ID));
        assertFalse(NfeController.Delete(""));
        assertTrue(NfeController.Create(ID));
    }

    @Test
    public void testmockeddate() throws Exception {
        //PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(obterData(29, 4, 2017));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.YEAR, 2017);
        PowerMockito.mockStatic(Calendar.class);

    }

    /**
     * Retorna uma instância de <code>Date</code> refletindo os valores
     * passados por parametro
     *
     * @param dia
     * @param mes
     * @param ano
     * @return
     */
    public static Date obterData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, dia);
        calendar.set(MONTH, mes - 1);
        calendar.set(YEAR, ano);
        return calendar.getTime();
    }
}
