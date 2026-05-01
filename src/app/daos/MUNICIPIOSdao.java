package app.daos;

import app.models.MUNICIPIOS;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class MUNICIPIOSdao {

    Conection conn;

    public ArrayList<JSONObject> List() {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CANFEMUNICIPIOS "
                    + "ORDER BY MUN_DESCRICAO";
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

    public ArrayList<JSONObject> List_Cuf(String cUF) {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CANFEMUNICIPIOS "
                    + "WHERE substr(CAST(mun_codigo AS VARCHAR (7)),1,2) = "
                    + "'" + cUF + "' "
                    + "ORDER BY MUN_DESCRICAO";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i),
                            conn.resultset.getString(meta.getColumnName(i)));
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

    public JSONObject Index(String MUN_CODIGO) {
        System.out.println("model");
        JSONObject json = new JSONObject();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM CANFEMUNICIPIOS "
                    + "WHERE MUN_CODIGO = '" + MUN_CODIGO + "'";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
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

    public boolean Insert(String Mun_codigo) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO CANFEMUNICIPIOS ("
                    + "MUN_CODIGO)"
                    + "VALUES ("
                    + Mun_codigo + ")";

            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String MUN_CODIGO) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM CANFEMUNICIPIOS "
                    + "WHERE MUN_CODIGO = '" + MUN_CODIGO + "'";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(MUNICIPIOS eCANFEMUNICIPIOS) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eCANFEMUNICIPIOS.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE CANFEMUNICIPIOS SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eCANFEMUNICIPIOS")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eCANFEMUNICIPIOS) != null) {
                                System.out.println("fields[j].get(eCANFEMUNICIPIOS) " + fields[j].get(eCANFEMUNICIPIOS));
                                System.out.println("gSQL " + gSQL);
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eCANFEMUNICIPIOS).getClass().getSimpleName());
                                if (fields[j].get(eCANFEMUNICIPIOS).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eCANFEMUNICIPIOS).toString().trim().replace("'", " ") + "', ";
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
            gSQL = gSQL + "MUN_CODIGO = '" + eCANFEMUNICIPIOS.getMun_codigo() + "'";
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
