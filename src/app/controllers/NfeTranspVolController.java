package app.controllers;

import app.models.NFE_TRANSP_VOL;
import app.daos.NFE_TRANSP_VOLdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeTranspVolController {
    
    public String vPublica;

    public static JSONObject Index(String TRANSP_VOL_NNF) throws SQLException {
        System.out.println("Controller");
        NFE_TRANSP_VOLdao eNFE_TRANSP_VOLdao = new NFE_TRANSP_VOLdao();
        JSONObject json = new JSONObject();
        json = eNFE_TRANSP_VOLdao.Index(TRANSP_VOL_NNF);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_TRANSP_VOL não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String TRANSP_VOL_NNF) throws SQLException {
        System.out.println("Controller: List");
        NFE_TRANSP_VOLdao eNFE_TRANSP_VOLdao = new NFE_TRANSP_VOLdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_TRANSP_VOLdao.List(TRANSP_VOL_NNF);
        return list;
    }

    public static boolean Create(String TRANSP_VOL_NNF) {
        boolean validated = false;
        NFE_TRANSP_VOLdao eNFE_TRANSP_VOLdao = new NFE_TRANSP_VOLdao();
        validated = eNFE_TRANSP_VOLdao.Insert(TRANSP_VOL_NNF);
        return validated;
    }

    public static boolean Update(NFE_TRANSP_VOL eNFE_TRANSP_VOL) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_TRANSP_VOLdao eNFE_TRANSP_VOLdao = new NFE_TRANSP_VOLdao();
        validated = eNFE_TRANSP_VOLdao.Update(eNFE_TRANSP_VOL);
        return validated;
    }

    public static boolean Delete(String TRANSP_VOL_NNF) {
        boolean validated = false;
        NFE_TRANSP_VOLdao eNFE_TRANSP_VOLdao = new NFE_TRANSP_VOLdao();
        validated = eNFE_TRANSP_VOLdao.Delete(TRANSP_VOL_NNF);
        return validated;
    }
}
