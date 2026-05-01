package com.backend.controllers;

import com.backend.daos.CANFEIRDUPLdaoJpa;
import com.backend.dtos.CANFEIRDUPLdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CANFEIRDUPL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sun.istack.NotNull;
import java.text.ParseException;

public class CANFEIRDUPLcontroller {

    public static boolean Create(CANFEIRDUPLdto caNFEIRDUPLdto) {
        boolean validated = false;

        if (caNFEIRDUPLdto.getIRE_CODI() == null
                || caNFEIRDUPLdto.getIRE_CODI().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = caNFEIRDUPLdto.getIRE_PARC().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }

            CANFEIRDUPLdaoJpa caNFEIRDUPLdaoJpa = new CANFEIRDUPLdaoJpa();

            if (caNFEIRDUPLdaoJpa.validEntity(caNFEIRDUPLdto)) {
                return false;
            }
            validated = caNFEIRDUPLdaoJpa.create(CANFEIRDUPLdto.convertCANFEIRDUPLdtotoCANFEIRDUPL(caNFEIRDUPLdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(CANFEIRDUPLdto CANFEIRDUPLdto) {
        boolean validated = false;
        if (CANFEIRDUPLdto.getIRE_PARC() == null
                || CANFEIRDUPLdto.getIRE_PARC().trim().length() <= 0) {
            return validated;
        }
        CANFEIRDUPLdaoJpa caNFEIRDUPLdaoJpa = new CANFEIRDUPLdaoJpa();
        try {
            if (CANFEIRDUPLdaoJpa.NamedQueryFindByNotaParc(CANFEIRDUPLdto.getIRE_CODI(), CANFEIRDUPLdto.getIRE_PARC()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            } else {
                if (caNFEIRDUPLdaoJpa.validEntity(CANFEIRDUPLdto)) {
                    return false;
                }
                caNFEIRDUPLdaoJpa.update(CANFEIRDUPLdto.convertCANFEIRDUPLdtotoCANFEIRDUPL(CANFEIRDUPLdto));
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

    public static boolean Delete(String IRE_CODI, String IRE_PARC) {
        boolean validated = false;
        if (IRE_CODI == null
                || IRE_CODI.trim().length() <= 0
                || IRE_PARC == null
                || IRE_PARC.trim().length() <= 0) {
            return validated;
        }

        CANFEIRDUPLdaoJpa caNFEIRDUPLdaoJpa = new CANFEIRDUPLdaoJpa();

        List<CANFEIRDUPL> caNFEIRDUPL = caNFEIRDUPLdaoJpa.NamedQueryListByNota(IRE_CODI);

        if (caNFEIRDUPL == null || caNFEIRDUPL.size() <= 0) {
            validated = false;
            System.setProperty("MsgInvalid", "Parcela não cadastrado!");
        } else {
            try {
                validated = caNFEIRDUPLdaoJpa.delete(IRE_CODI);
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

    public static CANFEIRDUPLdto NamedQueryFindByNotaParc(@NotNull final String IRE_CODI, String IRE_PARC) throws SQLException {
        //System.out.println("Controller");
        CANFEIRDUPL caNFEIRDUPL = new CANFEIRDUPL();
        if (IRE_CODI == null
                || IRE_CODI.trim().length() <= 0
                || IRE_PARC == null
                || IRE_PARC.trim().length() <= 0) {
            return null;
        }
        try {
            caNFEIRDUPL = CANFEIRDUPLdaoJpa.NamedQueryFindByNotaParc(IRE_CODI, IRE_PARC);

            if (caNFEIRDUPL == null) {
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            }

        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return CANFEIRDUPLdto.convertCANFEIRDUPLtoCANFEIRDUPLdto(caNFEIRDUPL);
    }

    public static CANFEIRDUPLdto findByLastParCodi() throws SQLException {
        //System.out.println("Controller");
        CANFEIRDUPL caNFEIRDUPL = new CANFEIRDUPL();
        try {
            caNFEIRDUPL = CANFEIRDUPLdaoJpa.findByLastParCodi();
            if (caNFEIRDUPL == null) {
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            CANFEIRDUPLdto.convertCANFEIRDUPLtoCANFEIRDUPLdto(caNFEIRDUPL);
        }
        return CANFEIRDUPLdto.convertCANFEIRDUPLtoCANFEIRDUPLdto(caNFEIRDUPL);
    }

    public static List<CANFEIRDUPLdto> List(String IRE_CODI, String IRE_PARC) throws SQLException {
        //System.out.println("Controller: List");
        CANFEIRDUPLdaoJpa caNFEIRDUPLdaoJpa = new CANFEIRDUPLdaoJpa();
        //List<CANFEIRDUPL> caNFEIRDUPL = (List<CANFEIRDUPL>) caNFEIRDUPLdaoJpa.findCANFEIRDUPLEntities(IRE_PARC, IRE_CODI);
        List<CANFEIRDUPLdto> ListCANFEIRDUPLdto = new ArrayList<CANFEIRDUPLdto>();
        CANFEIRDUPLdto CANFEIRDUPLdto = new CANFEIRDUPLdto();
        //CANFEIRDUPLdto.setCANFEIRDUPL(caNFEIRDUPL);
        ListCANFEIRDUPLdto.add(CANFEIRDUPLdto);
        return ListCANFEIRDUPLdto;
    }

    public static List<CANFEIRDUPLdto> NamedQueryFindAllNota(String IRE_CODI) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        CANFEIRDUPLdaoJpa caNFEIRDUPLdaoJpa = new CANFEIRDUPLdaoJpa();
        List<CANFEIRDUPLdto> ListCANFEIRDUPLdto = (List<CANFEIRDUPLdto>) caNFEIRDUPLdaoJpa.NamedQueryFindAllNota(IRE_CODI);
        return ListCANFEIRDUPLdto;
    }

}
