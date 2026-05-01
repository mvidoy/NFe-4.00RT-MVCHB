package app.daos;

import app.models.NFE_TRANSP_VOL;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NFE_TRANSP_VOLdao {

    Conection conn;

    public ArrayList<JSONObject> List(String TRANSP_VOL_NNF) {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_TRANSP_VOL "
                    + "WHERE TRANSP_VOL_NNF = " + TRANSP_VOL_NNF + " "
                    + "ORDER BY TRANSP_VOL_NNF ASC, TRANSP_VOL_ITEM ASC";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
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

    public JSONObject Index(String TRANSP_VOL_NNF) {
        System.out.println("model");
        conn = new Conection();
        conn.conectaBs();
        JSONObject json = new JSONObject();
        try {
            String gSQL = "SELECT * FROM NFE_TRANSP_VOL "
                    + "WHERE TRANSP_VOL_NNF = " + TRANSP_VOL_NNF + "";
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

    public boolean Insert(String TRANSP_VOL_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_TRANSP_VOL (TRANSP_VOL_NNF) VALUES "
                    + "("
                    + TRANSP_VOL_NNF + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String TRANSP_VOL_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_TRANSP_VOL "
                    + "WHERE TRANSP_VOL_NNF = " + TRANSP_VOL_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_TRANSP_VOL eNFE_TRANSP_VOL) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_TRANSP_VOL.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_TRANSP_VOL SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("(eNFE_TRANSP_VOL")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_TRANSP_VOL) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_TRANSP_VOL).getClass().getSimpleName());
                                if (fields[j].get(eNFE_TRANSP_VOL).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_TRANSP_VOL).toString().trim().replace("'", " ") + "', ";
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
                    + "TRANSP_VOL_NNF = " + eNFE_TRANSP_VOL.getTransp_vol_nnf() + "";
                    //+ " AND TRANSP_VOL_ITEM = " + eNFE_TRANSP_VOL.getTransp_vol_item() + "";
            gSQL = gSQL.replace(", WHERE", " WHERE");
            if (!validatedUpdate) {
                return validatedUpdate;
            }
             System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }
}
