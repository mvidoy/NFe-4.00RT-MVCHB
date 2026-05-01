/* http://www.javac.com.br/jc/posts/list/134-nfe-exemplo-de-geracao-da-chave-de-acesso-nfe-20.page
 */
package lib;

import app.controllers.NfeController;
import app.models.NFE;

public class GeraChaveAcesso {

    public String main(
            String cUF,
            String dataAAMM,
            String cnpjCpf,
            String mod,
            String serie,
            String tpEmis,
            String nNF,
            String cNF
    ) {
        StringBuilder chave = new StringBuilder();
        try {
            chave.append(lpadTo(cUF, 2, '0'));
            chave.append(lpadTo(dataAAMM, 4, '0'));
            chave.append(lpadTo(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
            chave.append(lpadTo(mod, 2, '0'));
            chave.append(lpadTo(serie, 3, '0'));
            chave.append(lpadTo(String.valueOf(nNF), 9, '0'));
            chave.append(lpadTo(tpEmis, 1, '0'));
            chave.append(lpadTo(cNF, 8, '0'));
            System.setProperty("gDV", String.valueOf(modulo11(chave.toString())));
            chave.append(modulo11(chave.toString()));
            chave.insert(0, "NFe");
            NFE eNFE = new NFE();
            eNFE.setInfnfe_id(chave.toString().replace("NFe", ""));
            eNFE.setIde_cnf(lpadTo(cNF, 8, '0'));
            eNFE.setIde_cdv(System.getProperty("gDV"));
            eNFE.setIde_nnf(nNF.trim());
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
                return "";
            }
            /*
            String gSQL = "UPDATE nfe SET "
                    + "infnfe_id = '" + chave.toString().replace("NFe", "") + "', "
                    + "ide_cnf = '" + lpadTo(cNF, 8, '0') + "', "
                    + "ide_cdv = '" + System.getProperty("gDV") + "' "
                    + "WHERE ide_nNF = '" + nNF.trim() + "'";
            conexao con_default;
            con_default = new conexao();
            con_default.conectaBs();
            con_default.executeSQL(gSQL);
            con_default.desconecta();
             */
            info("Chave NF-e: " + chave.toString());
        } catch (Exception e) {
            error(e.toString());
        }
        return chave.toString();
    }

    public static int modulo11(String chave) {
        int total = 0;
        int peso = 2;

        for (int i = 0; i < chave.length(); i++) {
            total += (chave.charAt((chave.length() - 1) - i) - '0') * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int resto = total % 11;
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);
    }

    public static String lpadTo(String input, int width, char ch) {
        String strPad = "";
        StringBuffer sb = new StringBuffer(input.trim());
        while (sb.length() < width) {
            sb.insert(0, ch);
        }
        strPad = sb.toString();

        if (strPad.length() > width) {
            strPad = strPad.substring(0, width);
        }
        return strPad;
    }

    private static void error(String error) {
        System.out.println("| ERROR: " + error);
    }

    private static void info(String info) {
        System.out.println("| INFO: " + info);
    }
}
