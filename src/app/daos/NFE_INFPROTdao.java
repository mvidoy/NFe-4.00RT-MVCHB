package app.daos;

import app.models.NFE_INFPROT;
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

public class NFE_INFPROTdao {

    Conection conn;

    public ArrayList<JSONObject> List(String PROTNFE_NNF) {
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "SELECT * FROM NFE_INFPROT "
                + "WHERE PROTNFE_NNF = " + PROTNFE_NNF + " "
                + "ORDER BY PROTNFE_NNF ASC, INFPROT_DHRECBTO ASC";
        conn.executeQuery(gSQL);
        try {
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i).toUpperCase()) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());
                }
                if (json.getString("infprot_dhrecbto") != null
                        && json.getString("infprot_dhrecbto").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = dateFormat.parse(json.getString("infprot_dhrecbto"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("infprot_dhrecbto_yyyy-MM-dd", json.getString("infprot_dhrecbto"));
                    json.put("infprot_dhrecbto", formatador1.format(timestamp));
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

    public JSONObject Index(String PROTNFE_NNF) {
        System.out.println("model");
        conn = new Conection();
        JSONObject json = new JSONObject();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE_INFPROT "
                    + "WHERE PROTNFE_NNF = " + PROTNFE_NNF + "";
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

    public boolean Insert(String PROTNFE_NNF, String INFPROT_CSTAT) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE_INFPROT (PROTNFE_NNF, INFPROT_CSTAT) VALUES "
                    + "("
                    + PROTNFE_NNF + ","
                    + INFPROT_CSTAT + ")";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Delete(String PROTNFE_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "DELETE FROM NFE_INFPROT "
                    + "WHERE PROTNFE_NNF = " + PROTNFE_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE_INFPROT eNFE_INFPROT) throws InstantiationException, IllegalAccessException {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE_INFPROT.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE_INFPROT SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE_INFPROT")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE_INFPROT) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE_INFPROT).getClass().getSimpleName());
                                if (fields[j].get(eNFE_INFPROT).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE_INFPROT).toString().trim().replace("'", " ") + "', ";
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
            gSQL = gSQL + "PROTNFE_NNF = " + eNFE_INFPROT.getProtnfe_nnf() + " AND ";
            gSQL = gSQL + "INFPROT_CSTAT = " + eNFE_INFPROT.getInfprot_cstat() + "";
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
