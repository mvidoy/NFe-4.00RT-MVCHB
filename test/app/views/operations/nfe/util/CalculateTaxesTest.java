package app.views.operations.nfe.util;

import app.models.NFE_DET_PROD;
import app.views.operations.nfe.exceptions.FieldsNullOrEmptyException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import util.FormatFields;

@FixMethodOrder(MethodSorters.DEFAULT)
public class CalculateTaxesTest extends TestCase {

    public CalculateTaxesTest(String testName) {
        super(testName);
    }

    public NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
    public CalculateTaxes eCalculaImpostos = new CalculateTaxes();
    FormatFields f = new FormatFields();

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        //eNFE_DET_PROD = new NFE_DET_PROD();
    }
    
    @Test(expected = FieldsNullOrEmptyException.class)
    public void test_shouldcalculatethevalueofageneric() throws FieldsNullOrEmptyException, Exception {
        //cenario       
        String vBase = "1000,01";
        String pAliq = "5,00";
        String tCalc = "1";
        //acao
        //verificacao     
        assertEquals("50,00", eCalculaImpostos.CalculaImpostoGenerico(vBase, pAliq, tCalc));
    }
}
