package app.controllers;

import app.models.NFE_INFPROT;
import app.daos.NFE_INFPROTdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeInfProtController {

    public static JSONObject Index(String PROTNFE_NNF) throws SQLException {
        System.out.println("Controller");
        NFE_INFPROTdao eNFE_INFPROTdao = new NFE_INFPROTdao();
        JSONObject json = new JSONObject();
        json = eNFE_INFPROTdao.Index(PROTNFE_NNF);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_INFPROT não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String PROTNFE_NNF) throws SQLException {
        System.out.println("Controller: List");
        NFE_INFPROTdao eNFE_INFPROTdao = new NFE_INFPROTdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_INFPROTdao.List(PROTNFE_NNF);
        return list;
    }

    public static boolean Create(String PROTNFE_NNF, String infprot_cstat) {
        boolean validated = false;
        NFE_INFPROTdao eNFE_INFPROTdao = new NFE_INFPROTdao();
        validated = eNFE_INFPROTdao.Insert(PROTNFE_NNF, infprot_cstat);
        return validated;
    }

    public static boolean Update(NFE_INFPROT eNFE_INFPROT) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_INFPROTdao eNFE_INFPROTdao = new NFE_INFPROTdao();
        validated = eNFE_INFPROTdao.Update(eNFE_INFPROT);
        return validated;
    }

    public static boolean Delete(String PROTNFE_NNF) {
        boolean validated = false;
        NFE_INFPROTdao eNFE_INFPROTdao = new NFE_INFPROTdao();
        validated = eNFE_INFPROTdao.Delete(PROTNFE_NNF);
        return validated;
    }
}
