package app.controllers;

import app.models.NFE_COBR_DUP;
import app.daos.NFE_COBR_DUPdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeCobrDupController {

    public static JSONObject Index(String ID_NMDF) throws SQLException {
        System.out.println("Controller-Index");
        NFE_COBR_DUPdao eNFE_COBR_DUPdao = new NFE_COBR_DUPdao();
        JSONObject json;
        json = eNFE_COBR_DUPdao.Index(ID_NMDF);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_COBR_DUP não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String ID_NMDF) throws SQLException {
        System.out.println("Controller: List");
        NFE_COBR_DUPdao eNFE_COBR_DUPdao = new NFE_COBR_DUPdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_COBR_DUPdao.List(ID_NMDF);
        return list;
    }

    public static boolean Create(String COBR_DUP_NNF, String COBR_DUP_ITEM) {
        System.out.println("Controller-Create");
        boolean validated = false;
        NFE_COBR_DUPdao eNFE_COBR_DUPdao = new NFE_COBR_DUPdao();
        validated = eNFE_COBR_DUPdao.Insert(COBR_DUP_NNF, COBR_DUP_ITEM);
        return validated;
    }

    public static boolean Update(NFE_COBR_DUP eNFE_COBR_DUP) throws InstantiationException, IllegalAccessException {
        System.out.println("Controller-Update");
        boolean validated = false;
        NFE_COBR_DUPdao eNFE_COBR_DUPdao = new NFE_COBR_DUPdao();
        validated = eNFE_COBR_DUPdao.Update(eNFE_COBR_DUP);
        return validated;
    }

    public static boolean Delete(String ID_NMDF) {
        System.out.println("Controller-Delete");
        boolean validated = false;
        NFE_COBR_DUPdao eNFE_COBR_DUPdao = new NFE_COBR_DUPdao();
        validated = eNFE_COBR_DUPdao.Delete(ID_NMDF);
        return validated;
    }
}
