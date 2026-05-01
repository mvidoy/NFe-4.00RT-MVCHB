package app.daos;

import app.models.NFE;
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

public class NFEdao {

    Conection conn;

    public ArrayList<JSONObject> List(String paramers) {
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE "
                    + "WHERE LENGTH (TRIM(infnfe_statusnfe)) > 0 ";

            if (paramers.trim().length() > 0) {
                gSQL = gSQL + "AND " + paramers;
            }
            gSQL = gSQL + " ORDER BY IDE_NNF DESC LIMIT(100)";
            conn.executeQuery(gSQL);
            while (conn.resultset.next()) {
                json = new JSONObject();
                ResultSetMetaData meta = conn.resultset.getMetaData();
                int numColumns = meta.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    //System.out.println(conn.resultset.getString(meta.getColumnName(i)));
                    json.put(meta.getColumnName(i), conn.resultset.getString(meta.getColumnName(i)) == null ? "" : conn.resultset.getString(meta.getColumnName(i)).trim());

                }
                if (json.getString("ide_dhemi") != null
                        && json.getString("ide_dhemi").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = dateFormat.parse(json.getString("ide_dhemi"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("ide_dhemi_yyyy-MM-dd", json.getString("ide_dhemi"));
                    json.put("ide_dhemi", formatador1.format(timestamp));
                }

                if (json.getString("ide_dhcont") != null
                        && json.getString("ide_dhcont").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = dateFormat.parse(json.getString("ide_dhcont"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("ide_dhcont_yyyy-MM-dd", json.getString("ide_dhcont"));
                    json.put("ide_dhcont", formatador1.format(timestamp));
                }

                if (json.getString("infprot_dhrecbto") != null
                        && json.getString("infprot_dhrecbto").trim().length() > 0) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parsedDate = dateFormat.parse(json.getString("infprot_dhrecbto"));
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    json.put("infprot_dhrecbto_yyyy-MM-dd", json.getString("infprot_dhrecbto"));
                    json.put("infprot_dhrecbto", formatador1.format(timestamp));
                }

                /*
                if (json.has("ide_tpnfdebito") && json.get("ide_tpnfdebito") != JSONObject.NULL) {
                    String valor = json.getString("ide_tpnfdebito").trim();
                    if (!valor.isEmpty()) {
                        int tpDebitoInt = Integer.parseInt(valor);
                        String tpDebito = String.format("%02d", tpDebitoInt);
                        json.put("ide_tpnfdebito", tpDebito);
                    }
                }

                if (json.has("ide_tpnfcredito") && json.get("ide_tpnfcredito") != JSONObject.NULL) {
                    String valor = json.getString("ide_tpnfcredito").trim();
                    if (!valor.isEmpty()) {
                        int tpCreditoInt = Integer.parseInt(valor);
                        String tpCredito = String.format("%02d", tpCreditoInt);
                        json.put("ide_tpnfcredito", tpCredito);

                    }
                }
                */

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

    public JSONObject Index(String IDE_NNF) {
        System.out.println("model");
        JSONObject json = new JSONObject();
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "SELECT * FROM NFE "
                    + "WHERE IDE_NNF = " + IDE_NNF + "";
            conn.executeQuery(gSQL);
            System.out.println(IDE_NNF);
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
            System.out.println("json" + json);
            conn.desconecta();
        }
        return json;
    }

    public int Increment() {
        int last_IDE_NNF = 0;
        conn = new Conection();
        conn.conectaBs();
        String gSQL = "SELECT MAX(IDE_NNF) AS last_IDE_NNF FROM NFE";
        System.out.println(gSQL);
        conn.executeQuery(gSQL);
        try {
            while (conn.resultset.next()) {
                last_IDE_NNF = Integer.parseInt(conn.resultset.getString("last_IDE_NNF")) + 1;
                break;
            }
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } finally {
            conn.desconecta();
        }
        return last_IDE_NNF;
    }

    public boolean Insert(String IDE_NNF) {
        boolean validated = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            String gSQL = "INSERT INTO NFE (IDE_NNF) VALUES "
                    + "(" + IDE_NNF + ")";
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
            String gSQL = "DELETE FROM NFE "
                    + "WHERE IDE_NNF = " + IDE_NNF + "";
            System.out.println(gSQL);
            validated = conn.executeUpdate(gSQL);
        } finally {
            conn.desconecta();
        }
        return validated;
    }

    public boolean Update(NFE eNFE) {
        boolean validated = false;
        boolean validatedUpdate = false;
        conn = new Conection();
        try {
            conn.conectaBs();
            Class theClass = eNFE.getClass();
            Field fields[] = theClass.getDeclaredFields();
            if (fields.length <= 0) {
                return validated;
            }
            String gSQL = "UPDATE NFE SET ";
            for (int j = 0, m = fields.length; j < m; j++) {
                //Object ob = fields[j].getType().newInstance();
                //System.out.println("Field name: " + fields[j].getName());
                if (Modifier.isPrivate(fields[j].getModifiers())) {
                    fields[j].setAccessible(true);
                    if (!(fields[j].getName().trim().equals("eNFE")
                            || fields[j].getName().trim().equals("$jacocoData"))) {
                        try {
                            if (fields[j].get(eNFE) != null) {
                                validatedUpdate = true;
                                System.out.println("char.class: " + fields[j].get(eNFE).getClass().getSimpleName());
                                if (fields[j].get(eNFE).equals("")) {
                                    gSQL = gSQL + fields[j].getName() + " = null, ";
                                } else {
                                    gSQL = gSQL + fields[j].getName() + " = '" + fields[j].get(eNFE).toString().trim().replace("'", " ") + "', ";
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
            gSQL = gSQL + "WHERE IDE_NNF = " + eNFE.getIde_nnf() + "";
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
