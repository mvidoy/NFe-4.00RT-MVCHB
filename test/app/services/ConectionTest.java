package app.services;

import com.frontend.config.env;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import services.Conection;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ConectionTest extends TestCase {

    env Env = new env();

    public ConectionTest(String testName) {
        super(testName);
        System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
        System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
        System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
        System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
        System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
        System.setProperty("tpAmb", Env.gettpAMB().toString());
    }

    @Before
    public void testconectaBs() {
        Conection conn = new Conection();
        TestCase.assertNotNull(conn.conectaBs());
    }
}
