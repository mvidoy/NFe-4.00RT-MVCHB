package app.views.operations.nfe.util;

import app.controllers.NfeController;
import app.models.NFE;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.ConfiguracoesNfeIniciais;
import lib.MontaNfe;
import org.json.JSONObject;

public class GeraChaveAcesso {

    public static String IdNFe(String nNFe, JSONObject jsonNFe) throws NfeException, CertificadoException, FileNotFoundException, SQLException, UnsupportedEncodingException {
        String chave = "";
        String cdv = "";
        try {
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            SimpleDateFormat formatador1 = new SimpleDateFormat("yyMM");
            //Informe o Numero da NFe
            int numeroNfe = jsonNFe.getInt("ide_nnf");
            //Informe o CNPJ do Emitente da NFe
            String cnpj = jsonNFe.getString("emit_cnpj");
            //Informe a data de Emissao da NFe

            LocalDateTime dataEmissao1 = LocalDateTime.now();

            System.out.println("dataEmissao1' = " + dataEmissao1);
            System.out.println("ide_dhemi_yyyy-MM-dd = " + jsonNFe.getString("ide_dhemi_yyyy-MM-dd").replace("-03:00", "").replace(" ", "T"));

            LocalDateTime dataEmissao = LocalDateTime.parse(jsonNFe.getString("ide_dhemi_yyyy-MM-dd").replace("-03:00", "").replace(" ", "T"));

            //Informe o cnf da NFCe com 8 digitos
            Random random = new Random();
            //String cnf = formata.StrZero(String.format("%08d", random.nextInt(99999999)),8);
            String cnf = "76185528";
            //Informe o modelo da NFe
            String modelo = DocumentoEnum.NFE.getModelo();
            //Informe a Serie da NFe
            int serie = jsonNFe.getInt("ide_serie");
            //Informe o tipo de Emissao da NFe
            String tipoEmissao = jsonNFe.getString("ide_tpemis").trim();

            System.out.println("numeroNfe = " + numeroNfe);
            System.out.println("cnpj = " + cnpj);
            System.out.println("dataEmissao1' = " + dataEmissao1);
            System.out.println("dataEmissao = " + dataEmissao);
            System.out.println("cnpj = " + cnpj);
            System.out.println("cnf = " + cnf);
            System.out.println("modelo = " + modelo);
            System.out.println("serie = " + serie);
            System.out.println("tipoEmissao = " + tipoEmissao);

            System.out.println("chave = " + chave);
            System.out.println("cdv = " + cdv);

            ChaveUtil chaveUtil = new ChaveUtil(configINI.iniciaConfiguracoes().getEstado(), cnpj, modelo, serie, numeroNfe, tipoEmissao, cnf, dataEmissao);
            chave = chaveUtil.getChaveNF();
            cdv = chaveUtil.getDigitoVerificador();
            System.setProperty("cnf", cnf);
            System.setProperty("gDV", cdv);
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(nNFe.trim());
            eNFE.setInfnfe_id(chave.toString().replace("NFe", ""));
            eNFE.setIde_cnf(cnf);
            eNFE.setIde_cdv(cdv);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chave.toString();
    }

}
