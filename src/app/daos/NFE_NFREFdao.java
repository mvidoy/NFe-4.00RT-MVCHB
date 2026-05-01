package app.daos;

import app.models.NFE_NFREF;
import services.Conection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

public class NFE_NFREFdao {

    Conection conn;

    public ArrayList<JSONObject> List(String IDE_NNF) {
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_NFREF "
                    + "WHERE IDE_NNF = " + IDE_NNF + " "
                    + "ORDER BY IDE_NNF ASC, NFREF_REFNFE ASC";
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

    public JSONObject Index(String IDE_NNF) {
        System.out.println("model");
        conn = new Conection();
        conn.conectaBs();
        JSONObject json = new JSONObject();
        try {
            String gSQL = "SELECT * FROM NFE_NFREF "
                    + "WHERE IDE_NNF = " + IDE_NNF + "";
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

    public boolean Insert(String IDE_NNF, String NFREF_REFNFE) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_NFREF (IDE_NNF, NFREF_REFNFE) VALUES "
                    + "("
                    + IDE_NNF + ","
                    + NFREF_REFNFE + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String IDE_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_NFREF "
                    + "WHERE IDE_NNF = " + IDE_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_NFREF eNFE_NFREF) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_NFREF.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_NFREF SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_DET_PROD")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_NFREF) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_NFREF).getClass().getSimpleName());
                                if (fields[j].get(eNFE_NFREF).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_NFREF).toString().trim().replace("'", " ") + "', ";
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
            gSQL = gSQL + "NFREF_REFNFE = " + eNFE_NFREF.getNfref_refnfe() + " AND ";
            gSQL = gSQL + "IDE_NNF = " + eNFE_NFREF.getIde_nnf() + "";
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
