package app.controllers;

import app.models.NFE_INUTNFE;
import app.daos.NFE_INUTNFEdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeInutNfeController {

    public static JSONObject Index(String INFINUT_NNFINI, String INFINUT_NNFFIN) throws SQLException {
        System.out.println("Controller");
        NFE_INUTNFEdao eNFE_INUTNFEdao = new NFE_INUTNFEdao();
        JSONObject json = new JSONObject();
        json = eNFE_INUTNFEdao.Index(INFINUT_NNFINI, INFINUT_NNFFIN);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_INUTNFE não cadastrado!");
        }
        return json;
    }

    public static boolean Create(String INFINUT_NNFINI, String INFINUT_NNFFIN) {
        boolean validated = false;
        NFE_INUTNFEdao eNFE_INUTNFEdao = new NFE_INUTNFEdao();
        validated = eNFE_INUTNFEdao.Insert(INFINUT_NNFINI, INFINUT_NNFFIN);
        return validated;
    }

    public static boolean Update(NFE_INUTNFE eNFE_INUTNFE) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_INUTNFEdao eNFE_INUTNFEdao = new NFE_INUTNFEdao();
        validated = eNFE_INUTNFEdao.Update(eNFE_INUTNFE);
        return validated;
    }

    public static boolean Delete(String INFINUT_NNFINI, String INFINUT_NNFFIN) {
        boolean validated = false;
        NFE_INUTNFEdao eNFE_INUTNFEdao = new NFE_INUTNFEdao();
        validated = eNFE_INUTNFEdao.Delete(INFINUT_NNFINI, INFINUT_NNFFIN);
        return validated;
    }
    
     public static ArrayList<JSONObject> List() throws SQLException {
        System.out.println("Controller: List");
        NFE_INUTNFEdao eNFE_INUTNFEdao = new NFE_INUTNFEdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_INUTNFEdao.List();
        return list;
    }
}
