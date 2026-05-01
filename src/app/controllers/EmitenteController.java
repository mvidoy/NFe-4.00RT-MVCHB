package app.controllers;

import app.models.EMITENTE;
import app.daos.EMITENTEdao;
import app.daos.EMITENTEdaoJpa;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class EmitenteController {

    private String ID_EMITENTE;

    public boolean Create2(String ID_EMITENTE) {
        System.out.println("Executando............");
        return false;
    }

    public static boolean Create(String ID_EMITENTE) {
        boolean validated = false;

        if (ID_EMITENTE == null || ID_EMITENTE.trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = ID_EMITENTE.chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }
            EMITENTEdao eMITENTEdao = new EMITENTEdao();
            //JSONObject json = new JSONObject();
            //json = eMITENTE.Find(ID_EMITENTE);
            //if (!json.isEmpty()) {
            //    validated = false;
            //    System.setProperty("MsgInvalid", "Emitente já cadastrado!");
            //    System.out.println(System.getProperty("MsgInvalid"));
            // } else {
            validated = eMITENTEdao.Insert(ID_EMITENTE);
            //}
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }

        return validated;
    }

    public static JSONObject Index(String ID_EMITENTE) throws SQLException {
        System.out.println("Controller");
        EMITENTEdao eMITENTEdao = new EMITENTEdao();
        JSONObject json = new JSONObject();
        if (ID_EMITENTE == null || ID_EMITENTE.trim().length() <= 0) {
            return json;
        }
        try {
            json = eMITENTEdao.Index(ID_EMITENTE);
            if (json.isEmpty()) {
                System.setProperty("MsgInvalid", "Emitente não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return json;
        }
        return json;
    }

    public static ArrayList<JSONObject> List() throws SQLException {
        System.out.println("Controller: List");
        EMITENTEdao eEMITENTEdao = new EMITENTEdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eEMITENTEdao.List();
        return list;
    }

    public static boolean Update(EMITENTE eMITENTE) {
        boolean validated = false;
        if (eMITENTE.getEMP_CODI() == null || eMITENTE.getEMP_CODI().trim().length() <= 0) {
            return validated;
        }
        //EMITENTEdao eMITENTEdao = new EMITENTEdao();
        EMITENTEdaoJpa eMITENTEdaoJpa = new EMITENTEdaoJpa();
        //JSONObject json = new JSONObject();
        try {
            //json = eMITENTEdao.Index(eMITENTE.getEMP_CODI());
            //if (json.isEmpty()) {
            if (EMITENTEdaoJpa.findCodigo(eMITENTE.getEMP_CODI()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Emitente não cadastrado!");
            } else {
                eMITENTEdaoJpa.edit(eMITENTE);
                validated = true;
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            return validated;
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } catch (Exception e) {
            System.setProperty("MsgInvalid", e.toString());
        }
        return validated;
    }

    public static boolean Delete(String ID_EMITENTE) {
        boolean validated = false;
        try {
            if (ID_EMITENTE == null || ID_EMITENTE.trim().length() <= 0) {
                return validated;
            }
            EMITENTEdao eMITENTEdao = new EMITENTEdao();
            JSONObject json = new JSONObject();
            json = eMITENTEdao.Index(ID_EMITENTE);
            if (json.isEmpty()) {
                validated = false;
                System.setProperty("MsgInvalid", "Emitente não cadastrado!");
            } else {
                validated = eMITENTEdao.Delete(ID_EMITENTE);
            }
            return validated;
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            return validated;
        }
    }
}
