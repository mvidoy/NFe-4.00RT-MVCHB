package app.controllers;

import app.models.NFE;
import app.daos.NFEdao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;

public class NfeController {

    public static JSONObject Index(String ID_NNF) throws SQLException, ParseException {
        boolean validated = false;
        System.out.println("Controller");
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        NFEdao eNFEdao = new NFEdao();
        JSONObject json = new JSONObject();

        if (ID_NNF.trim().length() <= 0) {
            validated = false;
            System.setProperty("MsgInvalid", "Parâmetro inválido!");
            return json;
        }

        json = eNFEdao.Index(ID_NNF);
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "NFe não cadastrado!");
            return json;
        }

        // Verifica e atribui valor default se estiver ausente, nulo ou não numérico
        String debito = json.optString("ide_tpnfdebito", "").trim();
        if (debito.isEmpty() || !debito.matches("\\d+")) {
            json.put("ide_tpnfdebito", "0");
        }

        String credito = json.optString("ide_tpnfcredito", "").trim();
        if (credito.isEmpty() || !credito.matches("\\d+")) {
            json.put("ide_tpnfcredito", "0");
        }

        if (json.getString("infprot_dhrecbto") != null
                && json.getString("infprot_dhrecbto").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("infprot_dhrecbto"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("infprot_dhrecbto_yyyy-MM-dd", json.getString("infprot_dhrecbto"));
            json.put("infprot_dhrecbto", formatador1.format(timestamp));
        }

        if (json.getString("ide_dhemi") != null
                && json.getString("ide_dhemi").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("ide_dhemi"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("ide_dhemi_yyyy-MM-dd", json.getString("ide_dhemi"));
            json.put("ide_dhemi", formatador1.format(timestamp));
        }

        if (json.getString("ide_dhcont") != null
                && json.getString("ide_dhcont").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("ide_dhcont"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("ide_dhcont_yyyy-MM-dd", json.getString("ide_dhcont"));
            json.put("ide_dhcont", formatador1.format(timestamp));
        }

        if (json.getString("ide_dhsaient") != null
                && json.getString("ide_dhsaient").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("ide_dhsaient"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("ide_dhsaient_yyyy-MM-dd", json.getString("ide_dhsaient"));
            json.put("ide_dhsaient", formatador1.format(timestamp));
        }

        if (json.getString("signature_datainicial") != null
                && json.getString("signature_datainicial").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("signature_datainicial"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("signature_datainicial", formatador1.format(timestamp));
        }

        if (json.getString("signature_datafinal") != null
                && json.getString("signature_datafinal").trim().length() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(json.getString("signature_datafinal"));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            json.put("signature_datafinal", formatador1.format(timestamp));
        }

        return json;
    }

    public static ArrayList<JSONObject> List(String paramers) throws SQLException {
        System.out.println("Controller: List");
        NFEdao eNFEdao = new NFEdao();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = eNFEdao.List(paramers);
        return list;
    }

    public static Number Increment() {
        NFEdao eNFEdao = new NFEdao();
        Number last_ID_NNF = eNFEdao.Increment();
        return last_ID_NNF;
    }

    public static boolean Create(String ID_NNF) {
        boolean validated = false;
        if (ID_NNF.trim().length() <= 0) {
            validated = false;
            System.setProperty("MsgInvalid", "Parâmetro inválido!");
            return validated;
        }
        NFEdao eNFEdao = new NFEdao();
        JSONObject json = eNFEdao.Index(ID_NNF);
        if (!json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "NFe já cadastrado!");
        } else {
            validated = eNFEdao.Insert(ID_NNF);
        }
        return validated;
    }

    public static boolean Update(NFE eNFE) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        if (eNFE.getIde_nnf().trim().length() <= 0) {
            validated = false;
            System.setProperty("MsgInvalid", "Parâmetro inválido!");
            return validated;
        }
        System.out.println("Controller");
        JSONObject json = new JSONObject();
        NFEdao eNFEdao = new NFEdao();
        json = eNFEdao.Index(eNFE.getIde_nnf());
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "NFe não cadastrado!");
        } else {
            validated = eNFEdao.Update(eNFE);
        }
        return validated;
    }

    public static boolean Delete(String ID_NNF) {
        boolean validated = false;
        if (ID_NNF.trim().length() <= 0) {
            validated = false;
            System.setProperty("MsgInvalid", "Parâmetro inválido!");
            return validated;
        }
        NFEdao eNFEdao = new NFEdao();
        JSONObject json = new JSONObject();
        json = eNFEdao.Index(ID_NNF);
        if (json.isEmpty()) {
            validated = false;
            System.setProperty("MsgInvalid", "NFe não cadastrado!");
        } else {
            validated = eNFEdao.Delete(ID_NNF);
        }
        return validated;
    }
}
