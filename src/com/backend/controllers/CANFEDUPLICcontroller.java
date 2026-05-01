package com.backend.controllers;

import com.backend.daos.CANFEDUPLICdaoJpa;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CANFEDUPLIC;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sun.istack.NotNull;
import java.text.ParseException;

public class CANFEDUPLICcontroller {

    public static boolean Create(CANFEDUPLICdto caNFEDUPLICdto) {
        boolean validated = false;

        if (caNFEDUPLICdto.getPAR_CODI() == null
                || caNFEDUPLICdto.getPAR_CODI().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = caNFEDUPLICdto.getPAR_PARC().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }

            CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();

            if (caNFEDUPLICdaoJpa.validEntity(caNFEDUPLICdto)) {
                return false;
            }
            validated = caNFEDUPLICdaoJpa.create(CANFEDUPLICdto.convertCANFEDUPLICdtotoCANFEDUPLIC(caNFEDUPLICdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(CANFEDUPLICdto CANFEDUPLICdto) {
        boolean validated = false;
        if (CANFEDUPLICdto.getPAR_PARC() == null
                || CANFEDUPLICdto.getPAR_PARC().trim().length() <= 0) {
            return validated;
        }
        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        try {
            if (CANFEDUPLICdaoJpa.NamedQueryFindByNotaParc(CANFEDUPLICdto.getPAR_CODI(), CANFEDUPLICdto.getPAR_PARC()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            } else {
                if (caNFEDUPLICdaoJpa.validEntity(CANFEDUPLICdto)) {
                    return false;
                }
                caNFEDUPLICdaoJpa.update(CANFEDUPLICdto.convertCANFEDUPLICdtotoCANFEDUPLIC(CANFEDUPLICdto));
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

    public static boolean Delete(String PAR_CODI, String PAR_PARC) {
        boolean validated = false;
        if (PAR_CODI == null
                || PAR_CODI.trim().length() <= 0
                || PAR_PARC == null
                || PAR_PARC.trim().length() <= 0) {
            return validated;
        }

        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();

        if (caNFEDUPLICdaoJpa.NamedQueryFindByNotaParc(PAR_CODI, PAR_PARC) == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Parcela não cadastrado!");
        } else {
            try {
                validated = caNFEDUPLICdaoJpa.delete(PAR_CODI);
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

    public static CANFEDUPLICdto NamedQueryFindByNotaParc(@NotNull final String PAR_CODI, String PAR_PARC) throws SQLException, ParseException {
        //System.out.println("Controller");
        CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
        if (PAR_CODI == null
                || PAR_CODI.trim().length() <= 0
                || PAR_PARC == null
                || PAR_PARC.trim().length() <= 0) {
            return null;
        }
        try {
            caNFEDUPLIC = CANFEDUPLICdaoJpa.NamedQueryFindByNotaParc(PAR_CODI, PAR_PARC);

            if (caNFEDUPLIC == null) {
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            }

        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
    }

    public static CANFEDUPLICdto findByLastParCodi() throws SQLException, ParseException {
        //System.out.println("Controller");
        CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
        try {
            caNFEDUPLIC = CANFEDUPLICdaoJpa.findByLastParCodi();
            if (caNFEDUPLIC == null) {
                System.setProperty("MsgInvalid", "Parcela não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
        }
        return CANFEDUPLICdto.convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, false);
    }

    public static List<CANFEDUPLICdto> List(String PAR_CODI, String PAR_PARC) throws SQLException {
        //System.out.println("Controller: List");
        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        //List<CANFEDUPLIC> caNFEDUPLIC = (List<CANFEDUPLIC>) caNFEDUPLICdaoJpa.findCANFEDUPLICEntities(PAR_PARC, PAR_CODI);
        List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<CANFEDUPLICdto>();
        CANFEDUPLICdto CANFEDUPLICdto = new CANFEDUPLICdto();
        //CANFEDUPLICdto.setCANFEDUPLIC(caNFEDUPLIC);
        ListCANFEDUPLICdto.add(CANFEDUPLICdto);
        return ListCANFEDUPLICdto;
    }

    public static List<CANFEDUPLICdto> NamedQueryFindAllNota(String PAR_CODI) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        List<CANFEDUPLICdto> ListCANFEDUPLICdto = (List<CANFEDUPLICdto>) caNFEDUPLICdaoJpa.NamedQueryFindAllNota(PAR_CODI);
        return ListCANFEDUPLICdto;
    }

    public static List<CANFEDUPLICdto> NamedQueryFindAll(
            String filtro_NOME,
            String filtro_CONTA,
            String filtro_STATUS,
            String filtro_PAR_CODI_inical,
            String filtro_PAR_CODI_final,
            int pageSize,
            int pageNumber) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        List<CANFEDUPLICdto> ListCANFEDUPLICdto
                = (filtro_NOME.trim().length() > 0
                || filtro_CONTA.trim().length() > 0
                || filtro_STATUS.trim().length() > 0
                || (filtro_PAR_CODI_inical.trim().length() > 0
                && filtro_PAR_CODI_final.trim().length() > 0))
                ? (List<CANFEDUPLICdto>) caNFEDUPLICdaoJpa.NamedQueryFindAll(
                        filtro_NOME,
                        filtro_CONTA,
                        filtro_STATUS,
                        filtro_PAR_CODI_inical,
                        filtro_PAR_CODI_final,
                        pageSize,
                        pageNumber)
                : null;
        //caNFEDUPLICdaoJpa.NamedQueryFindAll(pageSize, pageNumber);
        return ListCANFEDUPLICdto;
    }
    
    public static int getCANFEDUPLICRecordCount() { //novo
        CANFEDUPLICdaoJpa cANFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        return cANFEDUPLICdaoJpa.getCANFEDUPLICRecordCount();
    }
    
    public static List<CANFEDUPLICdto> NamedQueryFindByArquivoRemessa(String fileName) throws SQLException, ParseException {
        CANFEDUPLICdaoJpa caNFEDUPLICdaoJpa = new CANFEDUPLICdaoJpa();
        List<CANFEDUPLICdto> ListCANFEDUPLICdto = (List<CANFEDUPLICdto>) caNFEDUPLICdaoJpa.NamedQueryFindByArquivoRemessa(fileName);
        return ListCANFEDUPLICdto;
    }

}
