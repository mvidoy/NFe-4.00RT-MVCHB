package app.daos;

import app.models.NFE_INFORMACOESPAGAMENTO;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NFE_INFORMACOESPAGAMENTOdao {

    Conection conn;

    public ArrayList<JSONObject> List(String PAG_NNF) {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "SELECT * FROM NFE_INFORMACOESPAGAMENTO "
                + "WHERE PAG_NNF = " + PAG_NNF + " "
                + "ORDER BY PAG_NNF ASC, PAG_ITEM ASC";
        conn.executeQuery(gSQL);
        try {
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i)) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                }
                list.add(json);
            }
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
        }
        return list;
    }

    public JSONObject Index(String PAG_NNF, String PAG_ITEM) {
        System.out.println("model");
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "SELECT * FROM NFE_INFORMACOESPAGAMENTO "
                + "WHERE PAG_NNF = " + PAG_NNF + " AND PAG_ITEM = " + PAG_ITEM + "";
        conn.executeQuery(gSQL);
        JSONObject json = new JSONObject();
        try {
            while (conn.resultset.next()) {
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    //System.out.println("Column Name: " + meta.getColumnName(i).toUpperCase());
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i)) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                }
                break;
            }
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
            System.out.println("json" + json);
            conn.desconecta();
        }
        return json;
    }

    public boolean Insert(String PAG_NNF, String PAG_ITEM) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_INFORMACOESPAGAMENTO (PAG_NNF, PAG_ITEM) VALUES "
                    + "(" + PAG_NNF + ","
                    + PAG_ITEM + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String PAG_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_INFORMACOESPAGAMENTO "
                    + "WHERE PAG_NNF = " + PAG_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_INFORMACOESPAGAMENTO.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_INFORMACOESPAGAMENTO SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_INFORMACOESPAGAMENTO")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_INFORMACOESPAGAMENTO) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_INFORMACOESPAGAMENTO).getClass().getSimpleName());
                                if (fields[j].get(eNFE_INFORMACOESPAGAMENTO).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_INFORMACOESPAGAMENTO).toString().trim().replace("'", " ") + "', ";
                                }
                            }
                        } catch (IllegalAccessException e) {
                            System.setProperty("MsgInvalid", e.toString());
                            validated = false;
                            return validated;
                        }
                    }
                }
            }
            gSQL = gSQL + "WHERE "
                    + "PAG_NNF = " + eNFE_INFORMACOESPAGAMENTO.getPag_nnf() + " AND "
                    + "PAG_ITEM = " + eNFE_INFORMACOESPAGAMENTO.getPag_item() + "";
            gSQL = gSQL.replace(", WHERE", " WHERE");
            if (!validatedUpdate) {
                return validatedUpdate;
            }
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }
}
