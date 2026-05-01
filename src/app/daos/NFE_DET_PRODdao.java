package app.daos;

import app.models.NFE_DET_PROD;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NFE_DET_PRODdao {

    Conection conn;

    public ArrayList<JSONObject> List(String DET_PROD_NNF, String DET_PROD_ITEM) {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "";
        if (DET_PROD_ITEM != null) {
            gSQL = "SELECT * FROM NFE_DET_PROD "
                    + "WHERE DET_PROD_NNF = " + DET_PROD_NNF + " AND "
                    + "DET_PROD_ITEM = " + DET_PROD_ITEM + " "
                    + "ORDER BY DET_PROD_NNF ASC, DET_PROD_ITEM ASC";
        } else {
            gSQL = "SELECT * FROM NFE_DET_PROD "
                    + "WHERE DET_PROD_NNF = " + DET_PROD_NNF + " "
                    + "ORDER BY DET_PROD_NNF ASC, DET_PROD_ITEM ASC";
        }
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

    public JSONObject Index(String DET_PROD_NNF) {
        System.out.println("model");
        conn = new Conection();
        conn.conectaBs();
        JSONObject json = new JSONObject();
        try {
            String gSQL = "SELECT * FROM NFE_DET_PROD "
                    + "WHERE DET_PROD_NNF = " + DET_PROD_NNF + "";
            conn.executeQuery(gSQL);
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

    public boolean Insert(String DET_PROD_NNF, String DET_PROD_ITEM) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_DET_PROD (DET_PROD_NNF, DET_PROD_ITEM) VALUES "
                    + "("
                    + DET_PROD_NNF + ","
                    + DET_PROD_ITEM + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String DET_PROD_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_DET_PROD "
                    + "WHERE DET_PROD_NNF = " + DET_PROD_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_DET_PROD eNFE_DET_PROD) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_DET_PROD.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_DET_PROD SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_DET_PROD")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_DET_PROD) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_DET_PROD).getClass().getSimpleName());
                                if (fields[j].get(eNFE_DET_PROD).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_DET_PROD).toString().trim().replace("'", " ") + "', ";
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
                    + "DET_PROD_NNF = " + eNFE_DET_PROD.getDet_prod_nnf() + " AND "
                    + "DET_PROD_ITEM = " + eNFE_DET_PROD.getDet_prod_item() + "";
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
