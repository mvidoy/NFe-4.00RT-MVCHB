package app.controllers;

import app.models.NFE_DET_PROD;
import app.daos.NFE_DET_PRODdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeDetProdController {

    public static JSONObject Index(String ID_NNF) throws SQLException {
        System.out.println("Controller");
        NFE_DET_PRODdao eNFE_DET_PRODdao = new NFE_DET_PRODdao();
        JSONObject json = new JSONObject();
        json = eNFE_DET_PRODdao.Index(ID_NNF);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_DET_PROD não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String ID_NNF, String ID_ITEM) throws SQLException {
        System.out.println("Controller: List");
        NFE_DET_PRODdao eNFE_DET_PRODdao = new NFE_DET_PRODdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_DET_PRODdao.List(ID_NNF, ID_ITEM);
        return list;
    }

    public static boolean Create(String COBR_DUP_NNF, String COBR_DUP_ITEM) {
        boolean validated = false;
        NFE_DET_PRODdao eNFE_DET_PRODdao = new NFE_DET_PRODdao();
        validated = eNFE_DET_PRODdao.Insert(COBR_DUP_NNF, COBR_DUP_ITEM);
        return validated;
    }

    public static boolean Update(NFE_DET_PROD eNFE_DET_PROD) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_DET_PRODdao eNFE_DET_PRODdao = new NFE_DET_PRODdao();
        validated = eNFE_DET_PRODdao.Update(eNFE_DET_PROD);
        return validated;
    }

    public static boolean Delete(String ID_NNF) {
        boolean validated = false;
        NFE_DET_PRODdao eNFE_DET_PRODdao = new NFE_DET_PRODdao();
        validated = eNFE_DET_PRODdao.Delete(ID_NNF);
        return validated;
    }
}
