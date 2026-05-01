package com.backend.controllers;

import com.backend.daos.TRANSPORTADORAdaoJpa;
import com.backend.dtos.TRANSPORTADORAdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.TRANSPORTADORA;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import java.text.ParseException;

public class TRANSPORTADORAcontroller {

    public static boolean Create(TRANSPORTADORAdto tRANSPORTADORAdto) {
        boolean validated = false;

        if (tRANSPORTADORAdto.getTRA_CODI() == null || tRANSPORTADORAdto.getTRA_CODI().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = tRANSPORTADORAdto.getTRA_CODI().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }
            TRANSPORTADORAdaoJpa tRANSPORTADORAdaoJpa = new TRANSPORTADORAdaoJpa();

            if (tRANSPORTADORAdaoJpa.validEntity(tRANSPORTADORAdto)) {
                return false;
            }
            validated = tRANSPORTADORAdaoJpa.create(TRANSPORTADORAdto.convertTRANSPORTADORAdtotoTRANSPORTADORA(tRANSPORTADORAdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(TRANSPORTADORAdto TRANSPORTADORAdto) {
        boolean validated = false;
        if (TRANSPORTADORAdto.getTRA_CODI() == null || TRANSPORTADORAdto.getTRA_CODI().trim().length() <= 0) {
            return validated;
        }
        if (TRANSPORTADORAdto.getTRA_CODI() == null || TRANSPORTADORAdto.getTRA_CODI().trim().length() <= 0) {
            return validated;
        }
        TRANSPORTADORAdaoJpa tRANSPORTADORAdaoJpa = new TRANSPORTADORAdaoJpa();
        try {
            if (TRANSPORTADORAdaoJpa.findCodigo(TRANSPORTADORAdto.getTRA_CODI()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Transportadora não cadastrado!");
            } else {
                if (tRANSPORTADORAdaoJpa.validEntity(TRANSPORTADORAdto)) {
                    return false;
                }
                tRANSPORTADORAdaoJpa.update(TRANSPORTADORAdto.convertTRANSPORTADORAdtotoTRANSPORTADORA(TRANSPORTADORAdto));
                validated = true;
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            return validated;
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
        } catch (Exception e) {
            System.setProperty("MsgInvalid", e.toString());
        }
        return validated;
    }

    public static boolean Delete(String TRA_CODI) {
        boolean validated = false;
        if (TRA_CODI == null || TRA_CODI.trim().length() <= 0) {
            return validated;
        }
        TRANSPORTADORAdaoJpa tRANSPORTADORAdaoJpa = new TRANSPORTADORAdaoJpa();

        tRANSPORTADORAdaoJpa.findCodigo(TRA_CODI);
        if (tRANSPORTADORAdaoJpa.findCodigo(TRA_CODI).getTRA_CODI() == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Transportadora não cadastrado!");
        } else {
            try {
                validated = tRANSPORTADORAdaoJpa.delete(TRA_CODI);
                validated = true;
            } catch (NonexistentEntityException e) {
                System.setProperty("MsgInvalid", e.toString());
                validated = false;
                return validated;
            } catch (Exception e) {
                System.setProperty("MsgInvalid", e.toString());
            }
        }
        return validated;
    }

    public static TRANSPORTADORAdto FindCodigo(String TRA_CODI) throws SQLException, ParseException {
        //System.out.println("Controller");
        TRANSPORTADORA tRANSPORTADORA = new TRANSPORTADORA();
        if (TRA_CODI == null || TRA_CODI.trim().length() <= 0) {
            return null;
        }
        try {
            tRANSPORTADORA = TRANSPORTADORAdaoJpa.findCodigo(TRA_CODI);

            if (tRANSPORTADORA.getTRA_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Transportadora não cadastrado!");
            }

        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return TRANSPORTADORAdto.convertTRANSPORTADORAtoTRANSPORTADORAdto(tRANSPORTADORA);
    }

    public static TRANSPORTADORAdto findLastClicodi() throws SQLException, ParseException {
        //System.out.println("Controller");
        TRANSPORTADORA tRANSPORTADORA = new TRANSPORTADORA();
        try {
            tRANSPORTADORA = TRANSPORTADORAdaoJpa.findLastTraCodi();
            if (tRANSPORTADORA.getTRA_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Transportadora não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            TRANSPORTADORAdto.convertTRANSPORTADORAtoTRANSPORTADORAdto(tRANSPORTADORA);
        }
        return TRANSPORTADORAdto.convertTRANSPORTADORAtoTRANSPORTADORAdto(tRANSPORTADORA);
    }

    public static List<TRANSPORTADORAdto> List(String filtro_tra_tipo, String filtro_tra_desc) throws SQLException {
        //System.out.println("Controller: List");
        TRANSPORTADORAdaoJpa tRANSPORTADORAdaoJpa = new TRANSPORTADORAdaoJpa();
        List<TRANSPORTADORAdto> transportadoras = (List<TRANSPORTADORAdto>) tRANSPORTADORAdaoJpa.findTRANSPORTADORAEntities(filtro_tra_tipo, filtro_tra_desc, true, -1, -1);

        //List<TRANSPORTADORA> transportadora = (List<TRANSPORTADORA>) tRANSPORTADORAdaoJpa.findTRANSPORTADORAEntities(filtro_tra_desc);
        //List<TRANSPORTADORAdto> ListTRANSPORTADORAdto = new ArrayList<TRANSPORTADORAdto>();
        //TRANSPORTADORAdto TRANSPORTADORAdto = new TRANSPORTADORAdto();
        //TRANSPORTADORAdto.setTransportadoras(transportadora);
        //ListTRANSPORTADORAdto.add(TRANSPORTADORAdto);
        return transportadoras;
    }
    
    public static List<TRANSPORTADORAdto> List(String filtro_tra_desc) throws SQLException {
        //System.out.println("Controller: List");
        TRANSPORTADORAdaoJpa tRANSPORTADORAdaoJpa = new TRANSPORTADORAdaoJpa();
        //List<TRANSPORTADORAdto> transportadora = (List<TRANSPORTADORAdto>) tRANSPORTADORAdaoJpa.findTRANSPORTADORAEntities("TODOS",filtro_tra_desc, -1, -1);

        List<TRANSPORTADORA> transportadora = (List<TRANSPORTADORA>) tRANSPORTADORAdaoJpa.findTRANSPORTADORAEntities(filtro_tra_desc);
        List<TRANSPORTADORAdto> ListTRANSPORTADORAdto = new ArrayList<TRANSPORTADORAdto>();
        TRANSPORTADORAdto TRANSPORTADORAdto = new TRANSPORTADORAdto();
        TRANSPORTADORAdto.setTransportadoras(transportadora);
        ListTRANSPORTADORAdto.add(TRANSPORTADORAdto);
        return ListTRANSPORTADORAdto;
    }
    
    

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

}
