package app.controllers;

import app.models.NFE_INFORMACOESPAGAMENTO;
import app.daos.NFE_INFORMACOESPAGAMENTOdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NfeInformacoesPagamentoController {

    public static JSONObject Index(String PAG_NNF, String PAG_ITEM) throws SQLException {
        System.out.println("Controller");
        NFE_INFORMACOESPAGAMENTOdao eNFE_INFORMACOESPAGAMENTOdao = new NFE_INFORMACOESPAGAMENTOdao();
        JSONObject json = new JSONObject();
        json = eNFE_INFORMACOESPAGAMENTOdao.Index(PAG_NNF, PAG_ITEM);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "NFE_INFORMACOESPAGAMENTO não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List(String PAG_NNF) throws SQLException {
        System.out.println("Controller: List");
        NFE_INFORMACOESPAGAMENTOdao eNFE_INFORMACOESPAGAMENTOdao = new NFE_INFORMACOESPAGAMENTOdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFE_INFORMACOESPAGAMENTOdao.List(PAG_NNF);
        return list;
    }

    public static boolean Create(String COBR_DUP_NNF, String COBR_DUP_ITEM) {
        boolean validated = false;
        NFE_INFORMACOESPAGAMENTOdao eNFE_INFORMACOESPAGAMENTOdao = new NFE_INFORMACOESPAGAMENTOdao();
        validated = eNFE_INFORMACOESPAGAMENTOdao.Insert(COBR_DUP_NNF, COBR_DUP_ITEM);
        return validated;
    }

    public static boolean Update(NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        NFE_INFORMACOESPAGAMENTOdao eNFE_INFORMACOESPAGAMENTOdao = new NFE_INFORMACOESPAGAMENTOdao();
        validated = eNFE_INFORMACOESPAGAMENTOdao.Update(eNFE_INFORMACOESPAGAMENTO);
        return validated;
    }

    public static boolean Delete(String PAG_NNF) {
        boolean validated = false;
        NFE_INFORMACOESPAGAMENTOdao eNFE_INFORMACOESPAGAMENTOdao = new NFE_INFORMACOESPAGAMENTOdao();
        validated = eNFE_INFORMACOESPAGAMENTOdao.Delete(PAG_NNF);
        return validated;
    }
}
