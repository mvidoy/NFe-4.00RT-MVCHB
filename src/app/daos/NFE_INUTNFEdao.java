package app.daos;

import app.models.*;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class NFE_INUTNFEdao {

    Conection conn;

    public JSONObject Index(String INFINUT_NNFINI, String INFINUT_NNFFIN) {
        System.out.println("model");
        conn = new Conection();
        JSONObject json = new JSONObject();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_INUTNFE "
                    + "WHERE "
                    + "INFINUT_NNFINI = " + INFINUT_NNFINI + " AND "
                    + "INFINUT_NNFFIN = " + INFINUT_NNFFIN + "";
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

    public boolean Insert(String INFINUT_NNFINI, String INFINUT_NNFFIN) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_INUTNFE (INFINUT_NNFINI, INFINUT_NNFFIN) VALUES "
                    + "("
                    + INFINUT_NNFINI + ", "
                    + INFINUT_NNFFIN + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String INFINUT_NNFINI, String INFINUT_NNFFIN) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_INUTNFE "
                    + "WHERE "
                    + "INFINUT_NNFINI = " + INFINUT_NNFINI + " AND "
                    + "INFINUT_NNFFIN = " + INFINUT_NNFFIN + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_INUTNFE eNFE_INUTNFE) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_INUTNFE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_INUTNFE SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_INUTNFE")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_INUTNFE) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_INUTNFE).getClass().getSimpleName());
                                if (fields[j].get(eNFE_INUTNFE).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_INUTNFE).toString().trim().replace("'", " ") + "', ";
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
                    + "INFINUT_NNFINI = " + eNFE_INUTNFE.getRetinutnfe_infinut_nnfini() + " AND "
                    + "INFINUT_NNFFIN = " + eNFE_INUTNFE.getRetinutnfe_infinut_nnffin() + "";
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

    public ArrayList<JSONObject> List() {
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_INUTNFE ORDER BY INFINUT_NNFINI DESC";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    //System.out.println(conn.resultset.getString(meta.getColumnName(i)));
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i)) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                }
                if (json.getString("retinutnfe_infinut_dhrecbto") != null
                        && json.getString("retinutnfe_infinut_dhrecbto").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date parsedDate = dateFormat.parse(json.getString("retinutnfe_infinut_dhrecbto"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("retinutnfe_infinut_dhrecbto_yyyy-MM-dd", json.getString("retinutnfe_infinut_dhrecbto"));
                    json.put("retinutnfe_infinut_dhrecbto", formatador1.format(timestamp));
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

}
