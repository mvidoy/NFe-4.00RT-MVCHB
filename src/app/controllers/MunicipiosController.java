package app.controllers;

import app.models.MUNICIPIOS;
import app.daos.MUNICIPIOSdao;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class MunicipiosController {

    public static JSONObject Index(String ID_CODIGO) throws SQLException {
        System.out.println("Controller");
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        JSONObject json = new JSONObject();
        json = eMUNICIPIOSdao.Index(ID_CODIGO);
        if (json.isEmpty()) {
            System.setProperty("MsgInvalid", "Município não cadastrado!");
        }
        return json;
    }

    public static ArrayList<JSONObject> List() throws SQLException {
        System.out.println("Controller: List");
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eMUNICIPIOSdao.List();
        return list;
    }

    public static ArrayList<JSONObject> List_Cuf(String cUF) throws SQLException {
        System.out.println("Controller: List");
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eMUNICIPIOSdao.List_Cuf(cUF);
        return list;
    }

    public static boolean Create(String Mun_codigo) {
        boolean validated = false;
        JSONObject json = new JSONObject();
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        json = eMUNICIPIOSdao.Index(Mun_codigo);
        if (!json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Município já cadastrado!");
        } else {
            validated = eMUNICIPIOSdao.Insert(Mun_codigo);
        }
        return validated;
    }

    public static boolean Update(MUNICIPIOS eMUNICIPIOS) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        System.out.println("Controller");
        JSONObject json = new JSONObject();
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        json = eMUNICIPIOSdao.Index(eMUNICIPIOS.getMun_codigo());
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Município não cadastrado!");
        } else {
            validated = eMUNICIPIOSdao.Update(eMUNICIPIOS);
        }
        return validated;
    }

    public static boolean Delete(String ID_CODIGO) {
        boolean validated = false;
        MUNICIPIOSdao eMUNICIPIOSdao = new MUNICIPIOSdao();
        JSONObject json = new JSONObject();
        json = eMUNICIPIOSdao.Index(ID_CODIGO);
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "Município não cadastrado!");
        } else {
            validated = eMUNICIPIOSdao.Delete(ID_CODIGO);
        }
        return validated;
    }
}
