package app.daos;

import app.models.EMITENTE;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class EMITENTEdao {

    Conection conn;

    public boolean Insert(String ID_EMITENTE) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO CAEMPRES (EMP_CODI) VALUES "
                    + "('" + ID_EMITENTE + "')";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public ArrayList<JSONObject> List() {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CAEMPRES";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                EMITENTE eMITENTE = new EMITENTE();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    Class theClass = eMITENTE.getClass();
                    Field fields[] = theClass.getDeclaredFields();
                    for (int j = 0, m = fields.length; j < m; j++) {
                        if (fields[j].getName().toUpperCase().equals(meta.getColumnName(i).toUpperCase())) {
                            json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                        }
                    }
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

    public JSONObject Index(String ID_EMITENTE) throws SQLException {
        System.out.println("model");
        JSONObject json = new JSONObject();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CAEMPRES "
                    + "WHERE EMP_CODI = '" + ID_EMITENTE + "'";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                ResultSetMetaData meta = conn.resultset.getMetaData();
                EMITENTE eMITENTE = new EMITENTE();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    Class theClass = eMITENTE.getClass();
                    Field fields[] = theClass.getDeclaredFields();
                    for (int j = 1, m = fields.length; j < m; j++) {
                        if (fields[j].getName().toUpperCase().equals(meta.getColumnName(i).toUpperCase())) {
                            json.put(meta.getColumnName(i).toUpperCase(), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                            break;
                        }
                    }
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

    public boolean Delete(String ID_EMITENTE) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM CAEMPRES "
                    + "WHERE EMP_CODI = '" + ID_EMITENTE + "'";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(EMITENTE eMITENTE) {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eMITENTE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE CAEMPRES SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("fields.length: " + fields.length);
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eEMITENTE")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eMITENTE) != null) {
                                System.out.println("fields[j].get(eMITENTE) " + fields[j].get(eMITENTE));
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eMITENTE).getClass().getSimpleName());
                                if (fields[j].get(eMITENTE).getClass().getSimpleName().equals("byte[]")) {
                                    //System.out.println("emp_certificado: " + eMITENTE.getEMP_CERTIFICADO());
                                    //gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eMITENTE.getEMP_CERTIFICADO().toString()) + "', ";
                                } else {
                                    if (fields[j].get(eMITENTE).equals("")) {
                                        gSQL = gSQL + fields[j].getName() + " = null, ";
                                    } else {
                                        gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eMITENTE).toString().trim().replace("'", " ") + "', ";
                                    }
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
            gSQL = gSQL + "WHERE EMP_CODI = '" + eMITENTE.getEMP_CODI() + "'";
            gSQL = gSQL.replace(", WHERE", " WHERE");
            if (!validatedUpdate) {
                return validatedUpdate;
            }
            System.out.println("gSQL: " + gSQL);
            validated = conn.executeUpdate(gSQL);
        } catch (IllegalArgumentException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
        }
        return validated;
    }
}
