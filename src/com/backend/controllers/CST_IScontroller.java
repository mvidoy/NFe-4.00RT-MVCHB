package com.backend.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import app.exceptions.NonexistentEntityException;
import app.models.CST_IS;
import com.backend.dtos.CST_ISdto;
import com.backend.daos.CST_ISdaoJpa;
import java.text.ParseException;

public class CST_IScontroller {

    public static boolean Create(CST_ISdto cST_ISdto) {
        boolean validated = false;

        if (cST_ISdto.getCST_CODIGO() == null || cST_ISdto.getCST_CODIGO().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = cST_ISdto.getCST_CODIGO().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                System.setProperty("MsgInvalid", "Error: ID " + cST_ISdto.getCST_CODIGO().trim() + " is not Numeric");
                return validated;
            }
            CST_ISdaoJpa cST_ISdaoJpa = new CST_ISdaoJpa();

            if (cST_ISdaoJpa.validEntity(cST_ISdto)) {
                return false;
            }
            validated = cST_ISdaoJpa.create(CST_ISdto.convertCST_ISdtotoCST_IS(cST_ISdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(CST_ISdto CST_ISdto) {
        boolean validated = false;
        if (CST_ISdto.getCST_CODIGO() == null || CST_ISdto.getCST_CODIGO().trim().length() <= 0) {
            return validated;
        }
        if (CST_ISdto.getCST_CODIGO() == null || CST_ISdto.getCST_CODIGO().trim().length() <= 0) {
            return validated;
        }
        CST_ISdaoJpa cST_ISdaoJpa = new CST_ISdaoJpa();
        try {
            if (CST_ISdaoJpa.NamedQueryFindByCodigo(CST_ISdto.getCST_CODIGO()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Unidade não cadastrada!");
            } else {
                if (cST_ISdaoJpa.validEntity(CST_ISdto)) {
                    return false;
                }
                cST_ISdaoJpa.update(CST_ISdto.convertCST_ISdtotoCST_IS(CST_ISdto));
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

    public static boolean Delete(String CST_CODIGO) {
        boolean validated = false;
        if (CST_CODIGO == null || CST_CODIGO.trim().length() <= 0) {
            return validated;
        }
        CST_ISdaoJpa cST_ISdaoJpa = new CST_ISdaoJpa();

        cST_ISdaoJpa.NamedQueryFindByCodigo(CST_CODIGO);
        if (cST_ISdaoJpa.NamedQueryFindByCodigo(CST_CODIGO).getCST_CODIGO() == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Unidade não cadastrada!");
        } else {
            try {
                validated = cST_ISdaoJpa.delete(CST_CODIGO);
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

    public static CST_ISdto NamedQueryFindByCodigo(String CST_CODIGO) throws SQLException, ParseException {
        //System.out.println("Controller");
        CST_IS cST_IS = new CST_IS();
        if (CST_CODIGO == null || CST_CODIGO.trim().length() <= 0) {
            return null;
        }
        try {
            cST_IS = CST_ISdaoJpa.NamedQueryFindByCodigo(CST_CODIGO);

            if (cST_IS.getCST_CODIGO().isEmpty()) {
                System.setProperty("MsgInvalid", "Unidade não cadastrada!");
            }

        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return CST_ISdto.convertCST_IStoCST_ISdto(cST_IS);
    }

    public static CST_ISdto NamedQueryFindLastCodigo() throws SQLException, ParseException {
        CST_IS cST_IS = new CST_IS();
        try {
            cST_IS = CST_ISdaoJpa.NamedQueryFindLastCodigo();
            if (cST_IS.getCST_CODIGO().isEmpty()) {
                System.setProperty("MsgInvalid", "Unidade não cadastrada!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            CST_ISdto.convertCST_IStoCST_ISdto(cST_IS);
        }
        return CST_ISdto.convertCST_IStoCST_ISdto(cST_IS);
    }

    public static List<CST_ISdto> NamedQueryFindAll(String filtro_CST_DESCRICAO) throws SQLException, ParseException {
        CST_ISdaoJpa cST_ISdaoJpa = new CST_ISdaoJpa();
        List<CST_ISdto> ListCST_ISdto = (List<CST_ISdto>) cST_ISdaoJpa.NamedQueryFindAll(filtro_CST_DESCRICAO);
        return ListCST_ISdto;
    }

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

}
