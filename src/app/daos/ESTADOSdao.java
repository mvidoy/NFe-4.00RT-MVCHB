package app.daos;

import app.models.ESTADOS;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class ESTADOSdao {

    Conection conn;

    public ArrayList<JSONObject> List() {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CAESTADO "
                    + "ORDER BY est_sigl ASC";
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

    public JSONObject Index(String EST_SIGL) {
        System.out.println("model");
        JSONObject json = new JSONObject();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CAESTADO "
                    + "WHERE EST_SIGL = '" + EST_SIGL + "'";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
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

    public boolean Insert(String Est_sigl) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO CAESTADO ("
                    + "EST_SIGL)"
                    + "VALUES ('"
                    + Est_sigl + "')";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String EST_SIGL) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM CAESTADO "
                    + "WHERE EST_SIGL = '" + EST_SIGL + "'";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(ESTADOS eCAESTADO) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eCAESTADO.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE CAESTADO SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eCAESTADO")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eCAESTADO) != null) {
                                System.out.println("fields[j].get(eCAESTADO) " + fields[j].get(eCAESTADO));
                                System.out.println("gSQL: " + gSQL);
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eCAESTADO).getClass().getSimpleName());
                                if (fields[j].get(eCAESTADO).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eCAESTADO).toString().trim().replace("'", " ") + "', ";
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
            gSQL = gSQL + "WHERE ";
            gSQL = gSQL + "EST_SIGL = '" + eCAESTADO.getEst_sigl() + "'";
            gSQL = gSQL.replace(", WHERE", " WHERE");

            if (!validatedUpdate) {
                return validatedUpdate;
            }
            validated = conn.executeUpdate(gSQL);
        } catch (IllegalArgumentException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
        }
        return validated;
    }

}
