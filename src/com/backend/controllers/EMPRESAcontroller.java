package com.backend.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.EMPRESA;
import com.backend.dtos.EMPRESAdto;
import com.backend.daos.EMPRESAdaoJpa;

public class EMPRESAcontroller {

    public static boolean Create(EMPRESAdto cLIENTEdto) {
        boolean validated = false;

        if (cLIENTEdto.getEMP_CODI() == null || cLIENTEdto.getEMP_CODI().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = cLIENTEdto.getEMP_CODI().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }
            EMPRESAdaoJpa cLIENTEdaoJpa = new EMPRESAdaoJpa();
            if (cLIENTEdaoJpa.validEntity(cLIENTEdto)) {
                return false;
            }
            validated = cLIENTEdaoJpa.create(EMPRESAdto.convertEMPRESAdtotoEMPRESA(cLIENTEdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(EMPRESAdto EMPRESAdto) {
        boolean validated = false;
        if (EMPRESAdto.getEMP_CODI() == null || EMPRESAdto.getEMP_CODI().trim().length() <= 0) {
            return validated;
        }
        if (EMPRESAdto.getEMP_CODI() == null || EMPRESAdto.getEMP_CODI().trim().length() <= 0) {
            return validated;
        }
        EMPRESAdaoJpa cLIENTEdaoJpa = new EMPRESAdaoJpa();
        try {
            if (EMPRESAdaoJpa.findCodigo(EMPRESAdto.getEMP_CODI()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            } else {
                if (cLIENTEdaoJpa.validEntity(EMPRESAdto)) {
                    return false;
                }
                cLIENTEdaoJpa.update(EMPRESAdto.convertEMPRESAdtotoEMPRESA(EMPRESAdto));
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

    public static boolean Delete(String ID_EMPRESA) {
        boolean validated = false;
        if (ID_EMPRESA == null || ID_EMPRESA.trim().length() <= 0) {
            return validated;
        }
        EMPRESAdaoJpa cLIENTEdaoJpa = new EMPRESAdaoJpa();

        cLIENTEdaoJpa.findCodigo(ID_EMPRESA);
        if (cLIENTEdaoJpa.findCodigo(ID_EMPRESA).getEMP_CODI() == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
        } else {
            try {
                validated = cLIENTEdaoJpa.delete(ID_EMPRESA);
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

    public static EMPRESAdto FindCodigo(String ID_EMPRESA) throws SQLException {
        //System.out.println("Controller");
        EMPRESA cLIENTE = new EMPRESA();
        if (ID_EMPRESA == null || ID_EMPRESA.trim().length() <= 0) {
            return null;
        }
        try {
            cLIENTE = EMPRESAdaoJpa.findCodigo(ID_EMPRESA);

            if (cLIENTE.getEMP_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return EMPRESAdto.convertEMPRESAtoEMPRESAdto(cLIENTE);
        }
        return EMPRESAdto.convertEMPRESAtoEMPRESAdto(cLIENTE);
    }

    public static EMPRESAdto findLastClicodi() throws SQLException {
        //System.out.println("Controller");
        EMPRESA cLIENTE = new EMPRESA();
        try {
            cLIENTE = EMPRESAdaoJpa.findLastCliCodi();
            if (cLIENTE.getEMP_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            EMPRESAdto.convertEMPRESAtoEMPRESAdto(cLIENTE);
        }
        return EMPRESAdto.convertEMPRESAtoEMPRESAdto(cLIENTE);
    }

    public static List<EMPRESAdto> List(String filtro_cli_tiposgq,
            String filtro_cli_nome) throws SQLException {
        //System.out.println("Controller: List");
        EMPRESAdaoJpa cLIENTEdaoJpa = new EMPRESAdaoJpa();

        List<EMPRESA> clientes = (List<EMPRESA>) cLIENTEdaoJpa.findEMPRESAEntities(filtro_cli_tiposgq, filtro_cli_nome);
        List<EMPRESAdto> ListEMPRESAdto = new ArrayList<EMPRESAdto>();
        EMPRESAdto EMPRESAdto = new EMPRESAdto();
        //EMPRESAdto.setClientes(clientes);

        ListEMPRESAdto.add(EMPRESAdto);
        return ListEMPRESAdto;
    }

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

}
