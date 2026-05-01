package app.controllers;

import app.models.NFE_NFREF;
import app.daos.NFE_NFREFdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeNfRefController {

    public static JSONObject Index(String IDE_NNF) throws SQLException {
        System.out.println("Controller");
        NFE_NFREFdao eNFE_NFREFdao = new NFE_NFREFdao();
        JSONObject json = new JSONObject();
        json = eNFE_NFREFdao.Index(IDE_NNF);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_NFREF não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String IDE_NNF) throws SQLException {
        System.out.println("Controller: List");
        NFE_NFREFdao eNFE_NFREFdao = new NFE_NFREFdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_NFREFdao.List(IDE_NNF);
        return list;
    }

    public static boolean Create(String IDE_NNF, String NFREF_REFNFE) {
        boolean validated = false;
        NFE_NFREFdao eNFE_NFREFdao = new NFE_NFREFdao();
        validated = eNFE_NFREFdao.Insert(IDE_NNF, NFREF_REFNFE);
        return validated;
    }

    public static boolean Update(NFE_NFREF eNFE_NFREF) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_NFREFdao eNFE_NFREFdao = new NFE_NFREFdao();
        validated = eNFE_NFREFdao.Update(eNFE_NFREF);
        return validated;
    }

    public static boolean Delete(String IDE_NNF) {
        boolean validated = false;
        NFE_NFREFdao eNFE_NFREFdao = new NFE_NFREFdao();
        validated = eNFE_NFREFdao.Delete(IDE_NNF);
        return validated;
    }
}
