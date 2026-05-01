package com.backend.controllers;

import com.backend.daos.CLIENTEdaoJpa;
import com.backend.dtos.CLIENTEdto;
import com.backend.exceptions.NonexistentEntityException;
import com.backend.models.CLIENTE;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;
import java.text.ParseException;

public class CLIENTEcontroller {

    public static boolean Create(CLIENTEdto cLIENTEdto) {
        boolean validated = false;

        if (cLIENTEdto.getCLI_CODI() == null || cLIENTEdto.getCLI_CODI().trim().length() <= 0) {
            return validated;
        }
        try {
            boolean isNumeric = cLIENTEdto.getCLI_CODI().trim().chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                return validated;
            }
            CLIENTEdaoJpa cLIENTEdaoJpa = new CLIENTEdaoJpa();
            cLIENTEdto.setCLI_JUFI(cLIENTEdto.getCLI_JUFI().equals("JURIDICA") ? "J"
                    : cLIENTEdto.getCLI_JUFI().equals("FISICA")
                    ? "F"
                    : cLIENTEdto.getCLI_JUFI().equals("N/A")
                    ? ""
                    : "");
            if (cLIENTEdaoJpa.validEntity(cLIENTEdto)) {
                return false;
            }
            validated = cLIENTEdaoJpa.create(CLIENTEdto.convertCLIENTEdtotoCLIENTE(cLIENTEdto));
        } catch (Exception e) {
            System.setProperty("MsgInvalid", "Error: Exception, " + e);
            validated = false;
        }
        return validated;
    }

    public static boolean Update(CLIENTEdto CLIENTEdto) {
        boolean validated = false;
        if (CLIENTEdto.getCLI_CODI() == null || CLIENTEdto.getCLI_CODI().trim().length() <= 0) {
            return validated;
        }
        if (CLIENTEdto.getCLI_CODI() == null || CLIENTEdto.getCLI_CODI().trim().length() <= 0) {
            return validated;
        }
        CLIENTEdaoJpa cLIENTEdaoJpa = new CLIENTEdaoJpa();
        try {
            if (CLIENTEdaoJpa.findCodigo(CLIENTEdto.getCLI_CODI()) == null) {
                validated = false;
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            } else {
                CLIENTEdto.setCLI_JUFI(CLIENTEdto.getCLI_JUFI().equals("JURIDICA")
                        ? "J"
                        : CLIENTEdto.getCLI_JUFI().equals("FISICA")
                        ? "F"
                        : CLIENTEdto.getCLI_JUFI().equals("N/A")
                        ? ""
                        : "");
                if (cLIENTEdaoJpa.validEntity(CLIENTEdto)) {
                    return false;
                }
                cLIENTEdaoJpa.update(CLIENTEdto.convertCLIENTEdtotoCLIENTE(CLIENTEdto));
                validated = true;
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            return validated;
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            return validated;
        } catch (Exception e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            return validated;
        }
        return validated;
    }

    public static boolean Delete(String ID_CLIENTE) {
        boolean validated = false;
        if (ID_CLIENTE == null || ID_CLIENTE.trim().length() <= 0) {
            return validated;
        }
        CLIENTEdaoJpa cLIENTEdaoJpa = new CLIENTEdaoJpa();

        cLIENTEdaoJpa.findCodigo(ID_CLIENTE);
        if (cLIENTEdaoJpa.findCodigo(ID_CLIENTE).getCLI_CODI() == null) {
            validated = false;
            System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
        } else {
            try {
                validated = cLIENTEdaoJpa.delete(ID_CLIENTE);
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

    public static CLIENTEdto FindCodigo(String ID_CLIENTE) throws SQLException, ParseException {
        //System.out.println("Controller");
        CLIENTE cLIENTE = new CLIENTE();
        if (ID_CLIENTE == null || ID_CLIENTE.trim().length() <= 0) {
            return null;
        }
        try {
            cLIENTE = CLIENTEdaoJpa.findCodigo(ID_CLIENTE);

            if (cLIENTE.getCLI_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            }

            cLIENTE.setCLI_JUFI(cLIENTE.getCLI_JUFI().equals("J")
                    ? "JURIDICA"
                    : cLIENTE.getCLI_JUFI().equals("F")
                    ? "FISICA"
                    : cLIENTE.getCLI_JUFI().equals("")
                    ? "N/A"
                    : "N/A");
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            return null;
        }
        return CLIENTEdto.convertCLIENTEtoCLIENTEdto(cLIENTE, true);
    }

    public static CLIENTEdto findLastClicodi() throws SQLException, ParseException {
        //System.out.println("Controller");
        CLIENTE cLIENTE = new CLIENTE();
        try {
            cLIENTE = CLIENTEdaoJpa.findLastCliCodi();
            if (cLIENTE.getCLI_CODI().isEmpty()) {
                System.setProperty("MsgInvalid", "Cliente/Fornecedor/Serviço não cadastrado!");
            }
        } catch (NullPointerException e) {
            System.setProperty("MsgInvalid", e.toString());
            CLIENTEdto.convertCLIENTEtoCLIENTEdto(cLIENTE, true);
        }
        return CLIENTEdto.convertCLIENTEtoCLIENTEdto(cLIENTE, true);
    }

    public static List<CLIENTEdto> List(String filtro_cli_tiposgq,
            String filtro_cli_nome) throws SQLException, ParseException {
        //System.out.println("Controller: List");
        CLIENTEdaoJpa cLIENTEdaoJpa = new CLIENTEdaoJpa();

        List<CLIENTEdto> clientes = (List<CLIENTEdto>) cLIENTEdaoJpa.findCLIENTEEntities(filtro_cli_tiposgq, filtro_cli_nome);
        //List<CLIENTEdto> ListCLIENTEdto = new ArrayList<CLIENTEdto>();

//        String ww = clientes.get(0).getCLI_CODI();
        //CLIENTEdto CLIENTEdto = new CLIENTEdto();
        //CLIENTEdto.setCLI_CODI(ww);
        //CLIENTEdto.setCLIENTEdto(clientes);
        //ListCLIENTEdto.add(CLIENTEdto);
        //CLIENTEdto.getClientes().get(0).getCLI_CODI();        
        //CLIENTEdto.setCLIENTEdto(ListCLIENTEdto);
        return clientes;

        //CLIENTEListdto cLIENTEListdto = new CLIENTEListdto();
        //List<CLIENTEdto> cLIENTEdto = (List<CLIENTEdto>) convertListCLIENTEtoCLIENTEdto(cLIENTEdaoJpa.findCLIENTEEntities(filtro_cli_tiposgq, filtro_cli_nome));
        //return cLIENTEdto;
        //List<CLIENTEdto> clientes1 = convertListCLIENTEtoCLIENTEdto(clientes);
        //clientes1.get(0).getClientes().stream().forEach(item -> System.out.println(item));
        //String ww1 = clientes1.get(0).getCLI_CODI();

        /*
        List<CampoDto> campos = clientes.stream()
                .map(c -> {
                    Map<String, Object> map = new ObjectMapper().convertValue(c, Map.class);
                    return map.entrySet().stream().map(CampoDto::toCampo).collect(Collectors.toList());
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        campos.stream().forEach(System.out::println);
         */
        //return convertListCLIENTEtoCLIENTEdto(clientes);
    }

    public static ArrayList<JSONObject> listToArrayList(List list) {
        return list != null ? new ArrayList<>(list) : null;
    }

}
