package com.backend.controllers;

import com.backend.daos.CANFEDUPLICdaoJpa;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import com.sun.istack.NotNull;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CANFENOTFIS;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.daos.CANFENOTFISdaoJpa;
import com.backend.dtos.CANFEDUPLICdto;
import static com.backend.dtos.CANFEIRDUPLdto.convertListCANFEIRDUPLtoListCANFEIRDUPLdto;
import java.text.ParseException;

public class CANFENOTFIScontroller {

    public static boolean Create(CANFENOTFISdto caNFENOTFISdto) {
        boolean validated = false;
        if (caNFENOTFISdto.getNOT_NOTA() == null
                || caNFENOTFISdto.getNOT_NOTA().trim().length() <= 0) {
            return validated;
        }
        try {
            if (!caNFENOTFISdto.getNOT_ENSA().trim().equals("SAIDA")
                    && caNFENOTFISdto.getNOT_CLIE().trim().length() <= 0) {
                return validated;
            }
            CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();

            if (caNFENOTFISdaoJpa.validEntity(caNFENOTFISdto)) {
                return false;
            }
            validated = caNFENOTFISdaoJpa.create(CANFENOTFISdto.convertCANFENOTFISdtotoCANFENOTFIS(caNFENOTFISdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(CANFENOTFISdto CANFENOTFISdto) {
        boolean validated = false;
        if (CANFENOTFISdto.getNOT_NOTA() == null
                || CANFENOTFISdto.getNOT_NOTA().trim().length() <= 0) {
            return validated;
        }
        try {
            //if (CANFENOTFISdaoJpa.NamedQueryFindByNota(CANFENOTFISdto.getNOT_NOTA(), CANFENOTFISdto.getNOT_FORN()) == null) {
            //    validated = false;
            //    System.setProperty("MsgInvalid", "Documento Fiscal não cadastrado!");
            //} else {
            CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();

            if (caNFENOTFISdaoJpa.validEntity(CANFENOTFISdto)) {
                return false;
            }
            caNFENOTFISdaoJpa.update(CANFENOTFISdto.convertCANFENOTFISdtotoCANFENOTFIS(CANFENOTFISdto));
            validated = true;
            //}
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

    public static boolean Delete(String ID_not_nota) {
        boolean validated = false;
        if (ID_not_nota == null
                || ID_not_nota.trim().length() <= 0) {
            return validated;
        }

        CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();

        caNFENOTFISdaoJpa.NamedQueryFindByNota(ID_not_nota);
        if (caNFENOTFISdaoJpa.NamedQueryFindByNota(ID_not_nota) == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Documento Fiscal não cadastrado!");
        } else {
            try {
                validated = caNFENOTFISdaoJpa.delete(ID_not_nota);
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

    public static CANFENOTFISdto NamedQueryFindByNota(@NotNull final String ID_not_nota) throws SQLException, ParseException {
        //System.out.println("Controller");
        CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
        if (ID_not_nota == null
                || ID_not_nota.trim().length() <= 0) {
            return null;
        }
        try {
            caNFENOTFIS = CANFENOTFISdaoJpa.NamedQueryFindByNota(ID_not_nota);
            if (caNFENOTFIS == null) {
                System.setProperty("MsgInvalid", "Documento Fiscal não cadastrado!");
                return null;
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto(caNFENOTFIS);
    }

    public static CANFEDUPLICdto NamedQueryFindByNotaParc(@NotNull final String ID_PAR_CODI, @NotNull final String ID_PAR_PARC) throws SQLException, ParseException {
        //System.out.println("Controller");
        CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
        if (ID_PAR_CODI == null
                || ID_PAR_CODI.trim().length() <= 0
                || ID_PAR_PARC == null
                || ID_PAR_PARC.trim().length() <= 0) {
            return null;
        }
        try {
            caNFENOTFIS = CANFENOTFISdaoJpa.NamedQueryFindByNota(ID_PAR_CODI);
            if (caNFENOTFIS == null) {
                System.setProperty("MsgInvalid", "Documento Fiscal não cadastrado!");
                return null;
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        CANFENOTFISdto caNFENOTFISdto = new CANFENOTFISdto();
        caNFENOTFISdto = CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto(caNFENOTFIS);
        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();
        //for (int i = 0; i < caNFENOTFISdto.getCANFEDUPLICdto().size(); i++) {
        //    if (caNFENOTFISdto.getCANFEDUPLICdto().get(i).getPAR_PARC().equals(ID_PAR_PARC)) {
        //        caNFEDUPLICdto = caNFENOTFISdto.getCANFEDUPLICdto().get(i);
        //        caNFEDUPLICdto.setCANFENOTFIS(caNFENOTFISdto);
        //    }
        //}
        //if (caNFENOTFISdto.getCANFEDUPLICdto() != null && caNFENOTFISdto.getCANFEDUPLICdto().size() > 0) {
            caNFEDUPLICdto.setCANFEIRDUPLdto(convertListCANFEIRDUPLtoListCANFEIRDUPLdto(CANFEDUPLICdaoJpa.NamedQueryFindByNotaParc(ID_PAR_CODI, ID_PAR_PARC).getCANFEIRDUPL(), false));
        //}
        return caNFEDUPLICdto;
    }

    public static CANFENOTFISdto NamedQueryFindByLastNota() throws SQLException, ParseException {
        //System.out.println("Controller");
        CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
        try {
            caNFENOTFIS = CANFENOTFISdaoJpa.NamedQueryFindByLastNota();
            if (caNFENOTFIS == null) {
                System.setProperty("MsgInvalid", "Documento Fiscal não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto(caNFENOTFIS);
        }
        return CANFENOTFISdto.convertCANFENOTFIStoCANFENOTFISdto(caNFENOTFIS);
    }

    public static List<CANFENOTFISdto> List(String ID_not_clie) throws SQLException {
        //System.out.println("Controller: List");
        CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();
        List<CANFENOTFIS> caNFENOTFIS = (List<CANFENOTFIS>) caNFENOTFISdaoJpa.findCANFENOTFISEntities(ID_not_clie);
        List<CANFENOTFISdto> ListCANFENOTFISdto = new ArrayList<CANFENOTFISdto>();
        CANFENOTFISdto CANFENOTFISdto = new CANFENOTFISdto();
        //CANFENOTFISdto.setCANFENOTFIS(caNFENOTFIS);
        //CANFENOTFISdto.setM2ITEPCO(null);
        ListCANFENOTFISdto.add(CANFENOTFISdto);
        return ListCANFENOTFISdto;
    }

    public static List<CANFENOTFISdto> NamedQueryFindAll(String filtro_NOME, int pageSize, int pageNumber) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();
        List<CANFENOTFISdto> ListCANFENOTFISdto
                = (List<CANFENOTFISdto>) caNFENOTFISdaoJpa.NamedQueryFindAll(filtro_NOME, pageSize, pageNumber);
        return ListCANFENOTFISdto;
    }

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

    public static long NamedQueryCountFindAll() throws ParseException, SQLException {
        CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();
        return caNFENOTFISdaoJpa.NamedQueryCountFindAll();
    }

    public static long NamedQueryCountFindAllLike(String filtro_CLI_NOME) throws ParseException, SQLException {
        CANFENOTFISdaoJpa caNFENOTFISdaoJpa = new CANFENOTFISdaoJpa();
        return caNFENOTFISdaoJpa.NamedQueryCountFindAllLike(filtro_CLI_NOME);
    }
    
     public static int getCANFENOTFISRecordCount() { //novo
        CANFENOTFISdaoJpa cANFENOTFISdaoJpa = new CANFENOTFISdaoJpa();
        return cANFENOTFISdaoJpa.getCANFENOTFISRecordCount();
    }

}
