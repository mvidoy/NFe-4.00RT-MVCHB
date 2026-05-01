package app.daos;

import app.models.NFE_COBR_DUP;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;

public class NFE_COBR_DUPdao {

    Conection conn;

    public ArrayList<JSONObject> List(String COBR_DUP_NNF) {
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatador2 = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "SELECT * FROM NFE_COBR_DUP "
                + "WHERE COBR_DUP_NNF = " + COBR_DUP_NNF + " "
                + "ORDER BY COBR_DUP_NNF ASC, COBR_DUP_ITEM ASC";
        conn.executeQuery(gSQL);
        try {
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                }
                if (json.getString("cobr_dup_dvenc") != null
                        && json.getString("cobr_dup_dvenc").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(json.getString("cobr_dup_dvenc"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("cobr_dup_dvenc_yyyy-MM-dd", formatador2.format(timestamp));
                    json.put("cobr_dup_dvenc", formatador1.format(timestamp));
                }
                list.add(json);
            }
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } catch (ParseException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
        }
        return list;
    }

    public JSONObject Index(String COBR_DUP_NNF) {
        System.out.println("model");
        JSONObject json = new JSONObject();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_COBR_DUP "
                    + "WHERE COBR_DUP_NNF = " + COBR_DUP_NNF + "";
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
            return json;
        } finally {
            conn.desconecta();
            System.out.println("json" + json);
        }
        return json;
    }

    public boolean Insert(String COBR_DUP_NNF, String COBR_DUP_ITEM) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_COBR_DUP (COBR_DUP_NNF, COBR_DUP_ITEM) VALUES "
                    + "("
                    + COBR_DUP_NNF + ","
                    + COBR_DUP_ITEM + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String COBR_DUP_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_COBR_DUP "
                    + "WHERE COBR_DUP_NNF = " + COBR_DUP_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_COBR_DUP eNFE_COBR_DUP) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_COBR_DUP.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_COBR_DUP SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("fields.length: " + fields.length);
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_COBR_DUP")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_COBR_DUP) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_COBR_DUP).getClass().getSimpleName());
                                if (fields[j].get(eNFE_COBR_DUP).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_COBR_DUP).toString().trim().replace("'", " ") + "', ";
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
                    + "COBR_DUP_NNF = " + eNFE_COBR_DUP.getCobr_dup_nnf() + " AND "
                    + "COBR_DUP_ITEM = " + eNFE_COBR_DUP.getCobr_dup_item() + "";
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
