package app.controllers;

import app.models.ESTADOS;
import app.daos.ESTADOSdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class EstadosController {

    public static JSONObject Index(String ID_SIGL) throws SQLException {
        System.out.println("Controller");
        ESTADOSdao eESTADOdao = new ESTADOSdao();
        JSONObject json = new JSONObject();
        json = eESTADOdao.Index(ID_SIGL);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "Estado não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List() throws SQLException {
        System.out.println("Controller: List");
        ESTADOSdao eESTADOSdao = new ESTADOSdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eESTADOSdao.List();
        return list;
    }

    public static boolean Create(String Est_sigl) {
        boolean validated = false;
        JSONObject json;
        ESTADOSdao eESTADOSdao = new ESTADOSdao();
        json = eESTADOSdao.Index(Est_sigl);
        if (!json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Estado já cadastrado!");
        } else {
            validated = eESTADOSdao.Insert(Est_sigl);
        }
        return validated;
    }

    public static boolean Update(ESTADOS eESTADO) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        JSONObject json = new JSONObject();
        ESTADOSdao eESTADOdao = new ESTADOSdao();
        json = eESTADOdao.Index(eESTADO.getEst_sigl());
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Estado não cadastrado!");
        } else {
            validated = eESTADOdao.Update(eESTADO);
        }
        return validated;
    }

    public static boolean Delete(String ID_SIGL) {
        boolean validated = false;
        ESTADOSdao eESTADOdao = new ESTADOSdao();
        JSONObject json = new JSONObject();
        json = eESTADOdao.Index(ID_SIGL);
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Estado não cadastrado!");
        } else {
            validated = eESTADOdao.Delete(ID_SIGL);
        }
        return validated;
    }
}
