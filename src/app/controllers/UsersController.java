package app.controllers;

import app.models.USERS;
import app.daos.USERSdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class UsersController {

    public static JSONObject Index(String ID_USER) throws SQLException {
        System.out.println("Controller");
        USERSdao eUSERSdao = new USERSdao();
        JSONObject json = new JSONObject();
        json = eUSERSdao.Index(ID_USER);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "Usuário não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List() throws SQLException {
        System.out.println("Controller: List");
        USERSdao eUSERSdao = new USERSdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eUSERSdao.List();
        return list;
    }

    public static boolean Create(String Sen_oper) {
        boolean validated = false;
        JSONObject json = new JSONObject();
        USERSdao eUSERSdao = new USERSdao();
        json = eUSERSdao.Index(Sen_oper);
        if (!json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Usuário já cadastrado!");
        } else {
            validated = eUSERSdao.Insert(Sen_oper);
        }
        return validated;
    }

    public static boolean Update(USERS eUSERS) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        JSONObject json = new JSONObject();
        USERSdao eUSERSdao = new USERSdao();
        json = eUSERSdao.Index(eUSERS.getSen_oper());
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Usuário não cadastrado!");
        } else {
            validated = eUSERSdao.Update(eUSERS);
        }
        return validated;
    }

    public static boolean Delete(String ID_USER) {
        boolean validated = false;
        USERSdao eUSERSdao = new USERSdao();
        JSONObject json = new JSONObject();
        json = eUSERSdao.Index(ID_USER);
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Usuário não cadastrado!");
        } else {
            validated = eUSERSdao.Delete(ID_USER);
        }
        return validated;
    }
}
