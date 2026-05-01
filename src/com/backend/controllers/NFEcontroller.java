package com.backend.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import com.backend.dtos.NFEdto;
import com.backend.daos.NFEdaoJpa;
import com.backend.dtos.NFECBdto;
import com.backend.models.NFECB;
import java.text.ParseException;

public class NFEcontroller {

    public static boolean Update(NFEdto NFEdto) {
        boolean validated = false;
        if (NFEdto.getIDE_NNF() == null) {
            return validated;
        }
        NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
        try {
            if (nFEdaoJpa.validEntity(NFEdto)) {
                return false;
            }
            nFEdaoJpa.update(NFEdto.convertNFEdtotoNFE(NFEdto));
            validated = true;
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

    public static List<NFEdto> NamedQueryFindAllByDestCnpj(int pageSize, int currentPage, String filtro_dest_cnpj) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
        List<NFEdto> ListNFEdto = (List<NFEdto>) nFEdaoJpa.NamedQueryFindAllByDestCnpj(pageSize, currentPage, filtro_dest_cnpj, 1);
        return ListNFEdto;
    }

    public static NFEdto NamedQueryFindByIDENNF(int ide_nnf) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
        NFEdto nFEdto = new NFEdto();
        nFEdto = nFEdaoJpa.NamedQueryFindByIDENNF(ide_nnf);
        //nFEdto.setNFEDETPRODdto(nFEdaoJpa.NamedQueryFindByDetProdNNF(ide_nnf));
        return nFEdto;
    }

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

    public static NFECBdto NamedQueryCBFindByIDENNF(String ide_nnf) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
        NFECBdto nFECBdto = new NFECBdto();
        nFECBdto = null;
        NFECB NFe = nFEdaoJpa.NamedQueryCBFindByIDENNF(ide_nnf);
        if (NFe != null) {
            nFECBdto = NFECBdto.convertNFECBtoNFECBdto(nFEdaoJpa.NamedQueryCBFindByIDENNF(ide_nnf));
        }
        return nFECBdto;
    }

    public static boolean Update(NFECBdto NFEdto) {
        boolean validated = false;
        if (NFEdto.getIDE_NNF() == null) {
            return validated;
        }
        NFEdaoJpa nFEdaoJpa = new NFEdaoJpa();
        try {
            nFEdaoJpa.update(NFECBdto.convertNFECBdtotoNFECB(NFEdto));
            validated = true;
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

}
