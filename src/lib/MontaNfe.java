package lib;

import app.controllers.NfeCobrDupController;
import app.controllers.NfeController;
import app.controllers.NfeDetProdController;
import app.controllers.NfeInformacoesPagamentoController;
import app.controllers.NfeNfRefController;
import app.controllers.NfeTranspVolController;
import app.models.NFE;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TCIBS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TCIBS.GCBS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TCIBS.GIBSMun;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIBSCBSMonoTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIBSCBSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TInfRespTec;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPINT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPITrib;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TLocal;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Dup;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Fat;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMSUFDest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TTribNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.IbsCbsUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.backend.dtos.CALCULADORARETORNOdto.GIBSCBS;
import com.frontend.config.env;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.json.JSONObject;
import util.FormatFields;
import util.formata;

public class MontaNfe {

    // Inicio Alteração: 15/01/2026
    // Alíquotas (fixas conforme regra DIFAL - RJ)
    static String UFDest = "RJ";
    static BigDecimal pICMSDest = new BigDecimal("0.20"); // 20%
    static BigDecimal pFCP = new BigDecimal("0.02");      // 2%
    static BigDecimal pICMSInt = new BigDecimal("0.12");  // 12%
    static BigDecimal pPartilhaFixa100 = new BigDecimal("100.00");// 100% = Vigente 
    static ICMSTot icmstotInfCompl = new ICMSTot();
    // Fim Alteração: 15/01/2026

    public static TEnviNFe main(String nNFe) throws NfeException, CertificadoException, FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException, SQLException {
        TEnviNFe enviNFe = new TEnviNFe();
        try {
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(nNFe.trim());

            TNFe nFe = new TNFe();
            InfNFe infNFe = new InfNFe();
            infNFe.setId(IdNFe(nNFe, jsonNFe));
            //Alteracao: 17/10/2023
            /*
            if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Digitação")) {
                infNFe.setId(IdNFe(nNFe, jsonNFe));
            } else {
                infNFe.setId("NFe" + jsonNFe.getString("infnfe_id").trim());
            }
             */
            //Fim Alteracao
            infNFe.setVersao("4.00"); //01/08/2018

            //infNFe.setInfRespTec(infRespTec(nNFe, jsonNFe));
            infNFe.setIde(dadosDeIdentificacao(nNFe, jsonNFe));
            infNFe.setEmit(dadosDoEmitente(nNFe, jsonNFe));
            infNFe.setDest(dadosDoDestinatario(nNFe, jsonNFe));
            formata formata = new formata();
            SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FormatFields f = new FormatFields();
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeDetProdController.List(nNFe.trim(), null);
            for (int i = 0; i < map.size(); i++) {
                infNFe.getDet().add(dadosDoProduto(nNFe, map, i, jsonNFe));
            }
            infNFe.setTotal(totaisDaNFe(nNFe, jsonNFe));
            infNFe.setTransp(dadosDoTransporte(nNFe, jsonNFe));
            JSONObject json = new JSONObject();
            json = NfeInformacoesPagamentoController.Index(nNFe.trim(), "1");
            if (!json.isEmpty() && !json.getString("pag_indpag").trim().equals("0")) {
                infNFe.setCobr(dadosDeCobranca_fat(nNFe, jsonNFe));
            }
            infNFe.setInfAdic(informacoesAdicionais(nNFe, jsonNFe));
            Pag pag = new Pag();
            Pag.DetPag detPag = new Pag.DetPag();
            map = NfeInformacoesPagamentoController.List(nNFe.trim().trim());
            for (int i = 0; i < map.size(); i++) {
                if (map.get(i).getString("pag_indpag") != null) {
                    detPag.setIndPag(map.get(i).getString("pag_indpag").trim());
                    System.out.println(formata.StrZero(map.get(i).getString("pag_tpag").trim(), 2));
                    detPag.setTPag(formata.StrZero(map.get(i).getString("pag_tpag").trim(), 2));
                    detPag.setVPag(map.get(i).getString("pag_vpag").trim());
                    if (formata.StrZero(map.get(i).getString("pag_tpag").trim(), 2).equals("99")) {
                        detPag.setXPag("Outros Pagamento");
                    }
                    pag.getDetPag().add(detPag);
                }
            }
            infNFe.setPag(pag);

            //Inicio Alteração: 01/08/2025
            if (jsonNFe.getString("ide_finnfe").trim().equals("999")
                    || jsonNFe.getString("ide_finnfe").trim().equals("999")) {

                Det det = new Det();
                infNFe.getDet().add(det);

                TLocal local = new TLocal();
                local.setCNPJ(infNFe.getDest().getCNPJ());
                local.setCPF(infNFe.getDest().getCPF()); // caso aplicável
                local.setXNome(infNFe.getDest().getXNome());
                local.setXLgr(infNFe.getDest().getEnderDest().getXLgr());
                local.setNro(infNFe.getDest().getEnderDest().getNro());
                local.setXCpl(infNFe.getDest().getEnderDest().getXCpl());
                local.setXBairro(infNFe.getDest().getEnderDest().getXBairro());
                local.setCMun(infNFe.getDest().getEnderDest().getCMun());
                local.setXMun(infNFe.getDest().getEnderDest().getXMun());
                local.setUF(infNFe.getDest().getEnderDest().getUF());
                local.setCEP(infNFe.getDest().getEnderDest().getCEP());
                local.setXPais(infNFe.getDest().getEnderDest().getXPais());
                local.setCPais(infNFe.getDest().getEnderDest().getCPais());
                local.setFone(infNFe.getDest().getEnderDest().getFone());
                local.setEmail(infNFe.getDest().getEmail());
                local.setIE(infNFe.getDest().getIE());
                infNFe.setEntrega(local);

                TNFe.InfNFe.AutXML autXML = new TNFe.InfNFe.AutXML();
                autXML.setCNPJ(infNFe.getDest().getCNPJ());
                infNFe.getAutXML().add(autXML);

                Transp transp = new Transp();
                infNFe.setTransp(transp);
                infNFe.setCobr(null);
                infNFe.setInfAdic(null);
                infNFe.setPag(null);

                infNFe.setRetirada(null);

                Total total = new Total();
                infNFe.setTotal(total);
            }
            //Fim Alteração

            nFe.setInfNFe(infNFe);
            enviNFe.setVersao("4.00"); //01/08/2018
            enviNFe.setIdLote("0000001");
            enviNFe.setIndSinc("0"); // Assincrono. Por padrão, SP é assincrono(Quando dois ou mais processos são realizados ao mesmo tempo).
            //INFO: | Erro XML: Foi detectado um conteúdo inválido começando com o elemento 'NFe'. Era esperado um dos 'indSinc'.

            //Inclui transportadora autorizada para download do xml.
            if (jsonNFe.getString("transporta_cnpj").trim().length() > 0) {
                TNFe.InfNFe.AutXML autXML = new TNFe.InfNFe.AutXML();
                autXML.setCNPJ(jsonNFe.getString("transporta_cnpj").trim());
                nFe.getInfNFe().getAutXML().add(autXML);
            }

            enviNFe.getNFe().add(nFe);
        } catch (SQLException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        //Inicio Calculo dos Totais com IbsCbs com a Lib do Samuel
        //Carrega Json TabelaCclasstrib
        //Ver metodo ConsultaTributacao Teste
        String json = XmlNfeUtil.leXml("d:/teste/ibscbs.json");
        //Instancia a Classe Util de Calculo
        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);
        ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
        enviNFe = addTotaisIbsCbs(ibsCbsUtil, enviNFe, configINI.iniciaConfigurações());
        //Fim Calculo dos Totais com IbsCbs com a Lib do Samuel
         */
        return enviNFe;
    }

    /**
     * B - Identificação da Nota Fiscal eletrônica
     *
     * @return
     */
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

    private static Ide dadosDeIdentificacao(String nNFe, JSONObject jsonNFe) {
        Ide ide = new Ide();
        try {
            ide.setCUF("35");
            ide.setCDV("0");
            formata formata = new formata();
            SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //ide.setCNF(formata.FormatNumberInt(jsonNFe.getInt("ide_nnf"), "00000000"));
            ide.setNatOp(jsonNFe.getString("ide_natop").trim());
            //ide.setIndPag(con_NFe.resultset.getString("ide_indpag").trim());
            ide.setMod(jsonNFe.getString("ide_mod").trim());
            ide.setSerie(formata.FormatNumberInt(jsonNFe.getInt("ide_serie"), "0"));
            ide.setNNF(formata.FormatNumberInt(jsonNFe.getInt("ide_nnf"), "0"));
            ide.setDhEmi(jsonNFe.getString("ide_dhemi_yyyy-MM-dd").replace(" ", "T") + "-03:00");
            ide.setDhSaiEnt(jsonNFe.getString("ide_dhsaient_yyyy-MM-dd").replace(" ", "T") + "-03:00");
            ide.setIdDest(jsonNFe.getString("ide_iddest").trim());
            ide.setTpNF(jsonNFe.getString("ide_tpnf").trim());
            ide.setCMunFG(jsonNFe.getString("ide_cmunfg").trim());
            ide.setTpImp(jsonNFe.getString("ide_tpimp").trim());
            ide.setTpEmis(jsonNFe.getString("ide_tpemis").trim());

            if (!jsonNFe.getString("ide_tpemis").trim().equals("1")) {
                ide.setDhCont(jsonNFe.getString("ide_dhcont_yyyy-MM-dd").replace(" ", "T") + "-03:00");
                ide.setXJust(jsonNFe.getString("ide_xjust").trim());
            }

            if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Digitação")) {
                if (System.getProperty("gDV").trim() != null) {
                    ide.setCDV(System.getProperty("gDV").trim());
                }
                if (System.getProperty("cnf").trim() != null) {
                    ide.setCNF(System.getProperty("cnf").trim());
                }
            } else {
                ide.setCNF(jsonNFe.getString("ide_cnf").trim());
                ide.setCDV(jsonNFe.getString("ide_cdv").trim());
            }
            ide.setTpAmb(jsonNFe.getString("ide_tpamb").trim());
            ide.setFinNFe(jsonNFe.getString("ide_finnfe").trim());

            //Inicio Alteração: 01/08/2025
            ide.setTpNFCredito(null);
            ide.setTpNFDebito(null);
            if (jsonNFe.getString("ide_finnfe").trim().equals("5")) {
                String valor = jsonNFe.getString("ide_tpnfcredito").trim();
                if (!valor.isEmpty()) {
                    int tpCreditoInt = Integer.parseInt(valor);
                    if (tpCreditoInt >= 1) {
                        String tpCredito = String.format("%02d", tpCreditoInt);
                        ide.setTpNFCredito(tpCredito);
                    }
                }

            }
            if (jsonNFe.getString("ide_finnfe").trim().equals("6")) {
                String valor = jsonNFe.getString("ide_tpnfdebito").trim();
                if (!valor.isEmpty()) {
                    int tpDebitoInt = Integer.parseInt(valor);
                    if (tpDebitoInt >= 1) {
                        String tpDebito = String.format("%02d", tpDebitoInt);
                        ide.setTpNFDebito(tpDebito);
                    }
                }
            }
            //Fim Alteração

            ide.setProcEmi(jsonNFe.getString("ide_procemi").trim());
            ide.setIndFinal(jsonNFe.getString("ide_indfinal").trim());
            if (jsonNFe.getString("ide_indfinal").trim().equals("4")) {
                if (System.getProperty("gDV").trim() != null
                        && System.getProperty("gDV").trim().length() > 0) {
                    ide.setCDV(System.getProperty("gDV").trim());
                }
            }
            ide.setIndPres(jsonNFe.getString("ide_indpres").trim());
            //ide.setIndIntermed("0");
            //Inicio Alteração: 21/03/2025
            if (jsonNFe.getString("ide_indpres").trim().equals("5")) {
                if (System.getProperty("ide_cmunfgibs").trim() != null
                        && System.getProperty("ide_cmunfgibs").trim().length() > 0) {
                    ide.setCMunFGIBS(jsonNFe.getString("ide_cmunfgibs").trim());
                }
            }
            //Fim Alteração: 21/03/2025
            ide.setVerProc(jsonNFe.getString("ide_verproc").trim());
            int iI = 0;
            NFref nfref = new NFref();
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeNfRefController.List(nNFe.trim());
            for (int i = 0; i < map.size(); i++) {
                // while (con_default.resultset.next()) {                
                if (map.get(i).getString("nfref_tipo").trim().equals("NF-e")) {
                    nfref.setRefNFe(map.get(i).getString("nfref_refnfe"));
                    //nfref.setNITEM(map.get(i).getString("nfref_nitem"));
                }
                if (map.get(i).getString("nfref_tipo").trim().equals("CT-e")) {
                    nfref.setRefCTe(map.get(i).getString("nfref_refnfe"));
                }
                if (map.get(i).getString("nfref_tipo").trim().equals("Nota Fiscal")) {
                    nfref.getRefNF().setAAMM(map.get(i).getString("nfref_aamm"));
                    nfref.getRefNF().setCNPJ(map.get(i).getString("nfref_cnpj"));
                    nfref.getRefNF().setCUF(map.get(i).getString("nfref_cuf"));
                    nfref.getRefNF().setMod(map.get(i).getString("nfref_mod"));
                    nfref.getRefNF().setNNF(map.get(i).getString("nfref_nnf"));
                    nfref.getRefNF().setSerie(map.get(i).getString("nfref_serie"));
                }
                ide.getNFref().add(nfref);
                iI = iI + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ide;
    }

    /**
     * C - Identificação do Emitente da Nota Fiscal eletrônica
     */
    private static Emit dadosDoEmitente(String nNFe, JSONObject jsonNFe) {
        Emit emit = new Emit();
        TEnderEmi enderEmit = new TEnderEmi();
        emit.setCRT("1");
        formata formata = new formata();
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FormatFields f = new FormatFields();
        emit.setCNPJ(jsonNFe.getString("emit_cnpj"));
        emit.setXNome(jsonNFe.getString("emit_xnome").trim());
        emit.setXFant(jsonNFe.getString("emit_xfant").trim());
        enderEmit.setXLgr(jsonNFe.getString("enderemit_xlgr").trim());
        enderEmit.setNro(jsonNFe.getString("enderemit_nro").trim());
        enderEmit.setXBairro(jsonNFe.getString("enderemit_xbairro").trim());
        enderEmit.setCMun(jsonNFe.getString("enderemit_cmun").trim());
        enderEmit.setXMun(jsonNFe.getString("enderemit_xmun").trim().trim());
        enderEmit.setUF(TUfEmi.valueOf(jsonNFe.getString("enderemit_uf").trim()));
        enderEmit.setCEP(f.getCEPFormatado(jsonNFe.getString("enderemit_cep").trim()).replace("-", ""));
        //enderEmit.setCEP(con_NFe.resultset.getString("enderemit_cep").trim());
        enderEmit.setCPais(jsonNFe.getString("enderemit_cpais").trim());
        enderEmit.setXPais(jsonNFe.getString("enderemit_xpais").trim());
        enderEmit.setFone(jsonNFe.getString("enderemit_fone").trim());
        emit.setIE(jsonNFe.getString("enderemit_ie").trim());
        emit.setCRT(jsonNFe.getString("enderemit_crt").trim());
        emit.setEnderEmit(enderEmit);
        return emit;
    }

    /**
     * E - Identificação do Destinatário da Nota Fiscal eletrônica
     *
     * @return
     */
    private static Dest dadosDoDestinatario(String nNFe, JSONObject jsonNFe) {
        Dest dest = new Dest();
        //try {
        TEndereco enderDest = new TEndereco();
        formata formata = new formata();
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FormatFields f = new FormatFields();
        if (jsonNFe.getString("dest_cnpj").trim().length() <= 11) {
            dest.setCPF(jsonNFe.getString("dest_cnpj").trim());
            if (jsonNFe.getString("enderdest_indiedest").trim().equals("1")) { //10/09/2021
                dest.setIE(jsonNFe.getString("enderdest_ie").trim()); //10/09/2021
            }//10/09/2021
        } else {
            dest.setCNPJ(jsonNFe.getString("dest_cnpj").trim());
            dest.setIE(jsonNFe.getString("enderdest_ie").trim()); //10/09/2021
        }
        if (jsonNFe.getString("ide_tpamb").trim().equals("2")) {
            dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        } else {
            dest.setXNome(jsonNFe.getString("dest_xnome").trim());
        }
        dest.setIndIEDest(jsonNFe.getString("enderdest_indiedest").trim());
        //dest.setIE(jsonNFe.getString("enderdest_ie").trim()); //10/09/2021
        enderDest.setXLgr(jsonNFe.getString("enderdest_xlgr").trim());
        enderDest.setNro(jsonNFe.getString("enderdest_nro").trim());
        enderDest.setXBairro(jsonNFe.getString("enderdest_xbairro").trim());
        enderDest.setCMun(jsonNFe.getString("enderdest_cmun").trim());
        enderDest.setXMun(jsonNFe.getString("enderdest_xmun").trim());
        enderDest.setUF(TUf.valueOf(jsonNFe.getString("enderdest_uf").trim()));
        enderDest.setCEP(f.getCEPFormatado(jsonNFe.getString("enderdest_cep").trim()).replace("-", ""));
        //enderDest.setCEP(con_NFe.resultset.getString("enderdest_cep").trim());
        enderDest.setXPais(jsonNFe.getString("enderdest_xpais").trim());
        if (jsonNFe.getString("enderdest_fone") != null
                && jsonNFe.getString("enderdest_fone").trim().length() > 0) {
            enderDest.setFone(jsonNFe.getString("enderdest_fone").trim());
        }
        dest.setEnderDest(enderDest);
        if (jsonNFe.getString("enderdest_email") != null
                && jsonNFe.getString("enderdest_email").trim().length() > 0) {
            dest.setEmail(jsonNFe.getString("enderdest_email").trim());
        }
        return dest;
    }

    /**
     * H - Detalhamento de Produtos e Serviços da NF-e I - Produtos e Serviços
     * da NF-e M - Tributos incidentes no Produto ou Serviço V - Informações
     * adicionais
     *
     * @return
     */
    private static Det dadosDoProduto(String nNFe, ArrayList<JSONObject> map, int i, JSONObject jsonNFe) throws IOException, InterruptedException, NfeException {
        Det det = new Det();
        formata formata = new formata();
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FormatFields f = new FormatFields();
        Prod prod = new Prod();
        det.setNItem(map.get(i).getString("det_prod_item").trim());
        prod.setCProd(map.get(i).getString("det_prod_cprod").trim());
        prod.setCEAN(map.get(i).getString("det_prod_cean").trim());
        prod.setXProd(map.get(i).getString("det_prod_xprod").trim());
        prod.setNCM(formata.FormatNumberInt(map.get(i).getInt("det_prod_ncm"), "00000000"));
        prod.setCFOP(map.get(i).getString("det_prod_cfop").trim());
        prod.setUCom(map.get(i).getString("det_prod_ucom").trim());
        prod.setQCom(map.get(i).getString("det_prod_qcom"));
        prod.setVUnCom(map.get(i).getString("det_prod_vuncom"));
        prod.setVProd(map.get(i).getString("det_prod_vprod"));
        prod.setUTrib(map.get(i).getString("det_prod_utrib").trim());
        prod.setQTrib(map.get(i).getString("det_prod_qtrib"));
        prod.setCEANTrib(map.get(i).getString("det_prod_ceantrib").trim());
        prod.setVUnTrib(map.get(i).getString("det_prod_vuntrib"));
        prod.setIndTot(map.get(i).getString("det_prod_indtot").trim());

        //Inicio alteração: 03/12/2025
        // Chama o método separado para calcular o desconto
        // Obtém os valores como String
        String qComStr = map.get(i).getString("det_prod_qcom");
        String vUnComStr = map.get(i).getString("det_prod_vuncom");
        String vProdStr = map.get(i).getString("det_prod_vprod");
        // Chama o método separado para calcular o desconto
        String desconto = calcularVDescDadosDoProduto(qComStr, vUnComStr, vProdStr);

        // Aplica o desconto somente se o valor retornado não for nulo
        // (Garante que "0.00" não será setado)
        if (desconto != null) {
            prod.setVDesc(desconto);
        }
        //Fim alteração: 03/12/2025

        //det.setProd(prod);//11/20/2016
        // Imposto ICMS
        Imposto imposto = new Imposto();
        ICMS icms = new ICMS();

// Verifica se CST = 00 (tributado integralmente)
        if (map.get(i).getString("det_icms00_cst") != null
                && map.get(i).getString("det_icms00_cst").trim().equals("0")) {

            ICMS00 icms00 = new ICMS00();
            icms00.setOrig(map.get(i).getString("det_icms00_orig"));
            icms00.setCST(formata.StrZero(map.get(i).getString("det_icms00_cst"), 2));
            icms00.setModBC(map.get(i).getString("det_icms00_modbc"));

            // === Início Alteração: 15/01/2026 ===
            if ("2".equals(jsonNFe.getString("ide_iddest").trim())
                    && UFDest.equals(jsonNFe.getString("enderdest_uf").trim())
                    && jsonNFe.getString("enderdest_indiedest").trim().equals("9")) {

                // Converte campos para BigDecimal
                BigDecimal vBC = new BigDecimal(map.get(i).getString("det_icms00_vbc"));
                BigDecimal vIPI = new BigDecimal(map.get(i).getString("det_ipitrib_vipi"));
                BigDecimal pICMS = new BigDecimal(map.get(i).getString("det_icms00_picms"));

                // Soma a base de cálculo com o IPI
                BigDecimal vBCFinal = vBC.add(vIPI);

                // Calcula o valor do ICMS
                BigDecimal vICMS = vBCFinal.multiply(pICMS).divide(BigDecimal.valueOf(100));
                DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
                DecimalFormat dfAliq = new DecimalFormat("0.0000", DecimalFormatSymbols.getInstance(Locale.US));
                icms00.setVBC(df.format(vBCFinal));
                icms00.setVICMS(df.format(vICMS));
                icms00.setPICMS(dfAliq.format(pICMS));
            } else {
                // Caso não seja operação interestadual para consumidor final,
                // mantém os valores originais do MAP
                icms00.setVBC(map.get(i).getString("det_icms00_vbc"));
                icms00.setVICMS(map.get(i).getString("det_icms00_vicms"));
                icms00.setPICMS(map.get(i).getString("det_icms00_picms"));
            }
            // === Fim Alteração ===

            icms.setICMS00(icms00);
        }

        if (map.get(i).getString("det_icms10_cst") != null
                && map.get(i).getString("det_icms10_cst").trim().equals("10")) {
            ICMS10 icms10 = new ICMS10();
            icms10.setOrig(map.get(i).getString("det_icms10_orig"));
            icms10.setCST(formata.StrZero(map.get(i).getString("det_icms10_cst"), 2));
            icms10.setModBC(map.get(i).getString("det_icms10_modbc"));
            icms10.setVBC(map.get(i).getString("det_icms10_vbc"));
            icms10.setPICMS(map.get(i).getString("det_icms10_picms"));
            icms10.setVICMS(map.get(i).getString("det_icms10_vicms"));
            icms10.setModBCST(map.get(i).getString("det_icms10_modbcst"));
            icms10.setPMVAST(map.get(i).getString("det_icms10_pmvast"));
            icms10.setPRedBCST(map.get(i).getString("det_icms10_predbcst"));
            //icms10.setVBCST(map.get(i).getString("det_icms10_vbcst"));  // 01/08/2018
            icms10.setPICMSST(map.get(i).getString("det_icms10_picmsst"));
            icms10.setVICMSST(map.get(i).getString("det_icms10_vicmsst"));
            icms.setICMS10(icms10);
            prod.setCEST(formata.FormatNumberInt(map.get(i).getInt("det_prod_cest"), "0000000"));//11/10/2016

        }
        if (map.get(i).getString("det_icms20_cst") != null
                && map.get(i).getString("det_icms20_cst").trim().equals("20")) {
            ICMS20 icms20 = new ICMS20();
            icms20.setOrig(map.get(i).getString("det_icms20_orig"));
            icms20.setCST(formata.StrZero(map.get(i).getString("det_icms20_cst"), 2));
            icms20.setModBC(map.get(i).getString("det_icms20_modbc"));
            icms20.setPRedBC(map.get(i).getString("det_icms20_predbc"));
            icms20.setVBC(map.get(i).getString("det_icms20_vbc"));
            icms20.setPICMS(map.get(i).getString("det_icms20_picms"));
            icms20.setVICMS(map.get(i).getString("det_icms20_vicms"));
            icms20.setVICMSDeson(map.get(i).getString("det_icms20_vicmsdeson"));
            icms20.setMotDesICMS(map.get(i).getString("det_icms20_motdesicms"));
            icms.setICMS20(icms20);
        }
        if (map.get(i).getString("det_icms30_cst") != null
                && map.get(i).getString("det_icms30_cst").trim().equals("30")) {
            ICMS30 icms30 = new ICMS30();
            icms30.setOrig(map.get(i).getString("det_icms30_orig"));
            icms30.setCST(formata.StrZero(map.get(i).getString("det_icms30_cst"), 2));
            icms30.setModBCST(map.get(i).getString("det_icms30_modbcst"));
            icms30.setPMVAST(map.get(i).getString("det_icms30_pmvast"));
            icms30.setPRedBCST(map.get(i).getString("det_icms30_predbcst"));
            //icms30.setVBCST(map.get(i).getString("det_icms30_vbcst"));  // 01/08/2018
            icms30.setPICMSST(map.get(i).getString("det_icms30_picmsst"));
            icms30.setVICMSST(map.get(i).getString("det_icms30_vicmsst"));
            icms30.setVICMSDeson(map.get(i).getString("det_icms30_vicmsdeson"));
            icms30.setMotDesICMS(map.get(i).getString("det_icms30_motdesicms"));
            icms.setICMS30(icms30);
            prod.setCEST(formata.FormatNumberInt(map.get(i).getInt("det_prod_cest"), "0000000"));//11/10/2016
        }
        if ((map.get(i).getString("det_icms40_cst") != null
                && map.get(i).getString("det_icms40_cst").trim().length() > 0)
                && (map.get(i).getString("det_icms40_cst").trim().equals("40")
                || map.get(i).getString("det_icms40_cst").trim().equals("41")
                || map.get(i).getString("det_icms40_cst").trim().equals("50"))) {
            ICMS40 icms40 = new ICMS40();
            icms40.setOrig(map.get(i).getString("det_icms40_orig"));
            icms40.setCST(formata.StrZero(map.get(i).getString("det_icms40_cst"), 2));
            icms40.setVICMSDeson((map.get(i).getString("det_icms40_vicmsdeson").trim().length() <= 0 ? null : map.get(i).getString("det_icms40_vicmsdeson")));
            icms40.setMotDesICMS((map.get(i).getString("det_icms40_motdesicms").trim().length() <= 0 ? null : map.get(i).getString("det_icms40_motdesicms")));
            icms.setICMS40(icms40);
        }

        if (map.get(i).getString("det_icms51_cst") != null
                && map.get(i).getString("det_icms51_cst").trim().equals("51")) {
            ICMS51 icms51 = new ICMS51();
            icms51.setOrig(map.get(i).getString("det_icms51_orig"));
            icms51.setCST(formata.StrZero(map.get(i).getString("det_icms51_cst"), 2));
            icms51.setModBC((map.get(i).getString("det_icms51_modbc").trim().length() <= 0 ? null : map.get(i).getString("det_icms51_modbc")));
            icms51.setPRedBC(map.get(i).getString("det_icms51_predbc"));
            icms51.setVBC(map.get(i).getString("det_icms51_vbc"));
            icms51.setPICMS(map.get(i).getString("det_icms51_picms"));
            icms51.setVICMSOp((map.get(i).getString("det_icms51_vicmsop").trim().length() <= 0 ? null : map.get(i).getString("det_icms51_vicmsop")));
            icms51.setPDif(map.get(i).getString("det_icms51_pdif"));
            icms51.setVICMSDif(map.get(i).getString("det_icms51_vicmsdif"));
            icms51.setVICMS(map.get(i).getString("det_icms51_vicms"));
            icms.setICMS51(icms51);
        }
        if (map.get(i).getString("det_icms60_cst") != null
                && map.get(i).getString("det_icms60_cst").trim().equals("60")) {
            ICMS60 icms60 = new ICMS60();
            icms60.setOrig(map.get(i).getString("det_icms60_orig"));
            icms60.setCST(formata.StrZero(map.get(i).getString("det_icms60_cst"), 2));
            //icms60.setVBCSTRet(map.get(i).getString("det_icms60_vbcstret"));  // 01/08/2018
            //icms60.setVICMSSTRet(map.get(i).getString("det_icms60_vicmsstret")); //20/06/2024
            //icms60.setPST("0.0000");
            //icms60.setVBCSTRet("0.00");
            //icms60.setVBCFCPSTRet("0.00");
            //icms60.setPICMSEfet("0.0000");
            //icms60.setPRedBCEfet("0.0000");
            //icms60.setVBCEfet("0.00");
            //icms60.setVFCPSTRet("0.00");
            //icms60.setPFCPSTRet("0.0000");
            //icms60.setVICMSEfet("0.00");
            icms.setICMS60(icms60);
            //prod.setCEST("2400100");
            if (map.get(i).getString("det_prod_cest").trim().length() > 0) { //20/06/2024
                prod.setCEST(map.get(i).getString("det_prod_cest").trim());//11/10/2016
            }
        }

        if (map.get(i).getString("det_icms70_cst") != null
                && map.get(i).getString("det_icms70_cst").trim().equals("70")) {
            ICMS70 icms70 = new ICMS70();
            icms70.setOrig(map.get(i).getString("det_icms70_orig"));
            icms70.setCST(formata.StrZero(map.get(i).getString("det_icms70_cst"), 2));
            icms70.setModBC(map.get(i).getString("det_icms70_modbc"));
            icms70.setPRedBC(map.get(i).getString("det_icms70_predbc"));
            icms70.setVBC(map.get(i).getString("det_icms70_vbc"));
            icms70.setPICMS(map.get(i).getString("det_icms70_picms"));
            icms70.setVICMS(map.get(i).getString("det_icms70_vicms"));
            icms70.setModBCST(map.get(i).getString("det_icms70_modbcst"));
            icms70.setPMVAST(map.get(i).getString("det_icms70_pmvast"));
            icms70.setPRedBC(map.get(i).getString("det_icms70_predbcst"));
            //icms70.setVBCST(map.get(i).getString("det_icms70_vbcst"));  // 01/08/2018
            icms70.setPICMSST(map.get(i).getString("det_icms70_picmsst"));
            icms70.setVICMSST(map.get(i).getString("det_icms70_vicmsst"));
            icms70.setVICMSDeson(map.get(i).getString("det_icms70_vicmsdeson"));
            icms70.setMotDesICMS(map.get(i).getString("det_icms70_motdesicms"));
            icms.setICMS70(icms70);
            prod.setCEST(formata.FormatNumberInt(map.get(i).getInt("det_prod_cest"), "0000000"));//11/10/2016
        }
        if (map.get(i).getString("det_icms90_cst") != null
                && map.get(i).getString("det_icms90_cst").trim().equals("90")) {
            ICMS90 icms90 = new ICMS90();
            icms90.setOrig(map.get(i).getString("det_icms90_orig"));
            icms90.setCST(formata.StrZero(map.get(i).getString("det_icms90_cst"), 2));
            icms90.setModBC(map.get(i).getString("det_icms90_modbc"));
            icms90.setPRedBC(map.get(i).getString("det_icms90_predbc"));
            icms90.setVBC(map.get(i).getString("det_icms90_vbc"));
            icms90.setPICMS(map.get(i).getString("det_icms90_picms"));
            icms90.setVICMS(map.get(i).getString("det_icms90_vicms"));
            icms90.setModBCST(map.get(i).getString("det_icms90_modbcst"));
            icms90.setPMVAST(map.get(i).getString("det_icms90_pmvast"));
            icms90.setPRedBC(map.get(i).getString("det_icms90_predbcst"));
            //icms90.setVBCST(map.get(i).getString("det_icms90_vbcst"));  // 01/08/2018
            icms90.setPICMSST(map.get(i).getString("det_icms90_picmsst"));
            icms90.setVICMSST(map.get(i).getString("det_icms90_vicmsst"));
            icms90.setVICMSDeson(map.get(i).getString("det_icms90_vicmsdeson"));
            icms90.setMotDesICMS(map.get(i).getString("det_icms90_motdesicms"));
            icms.setICMS90(icms90);
            prod.setCEST(formata.FormatNumberInt(map.get(i).getInt("det_prod_cest"), "0000000"));//11/10/2016
        }

        det.setProd(prod); //11/10/2016
        TIpi ipi = new TIpi();
        if (map.get(i).getString("det_ipi_cenq") != null) {
            ipi.setCEnq(map.get(i).getString("det_ipi_cenq"));
            if (map.get(i).getString("det_ipitrib_cst") != null
                    && map.get(i).getString("det_ipitrib_vipi") != null
                    && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0
                    // && Double.parseDouble(map.get(i).getString("det_ipitrib_vipi")) > 0
                    && (formata.StrZero(map.get(i).getString("det_ipitrib_cst"), 2).equals("00")
                    || map.get(i).getString("det_ipitrib_cst").equals("49")
                    || map.get(i).getString("det_ipitrib_cst").equals("50")
                    || map.get(i).getString("det_ipitrib_cst").equals("99"))) {
                IPITrib ipitrib = new IPITrib();
                ipitrib.setCST(formata.StrZero(map.get(i).getString("det_ipitrib_cst"), 2));
                ipitrib.setVBC(map.get(i).getString("det_ipitrib_vbc"));
                ipitrib.setPIPI(map.get(i).getString("det_ipitrib_pipi"));
                ipitrib.setVIPI(map.get(i).getString("det_ipitrib_vipi"));
                ipi.setIPITrib(ipitrib);
            } else {
                IPINT ipiNT = new IPINT();
                if (map.get(i).getString("det_ipitrib_cst") != null
                        && formata.StrZero(map.get(i).getString("det_ipitrib_cst"), 2).equals("00")
                        && map.get(i).getString("det_ipitrib_vipi") != null
                        && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0
                        && Double.parseDouble(map.get(i).getString("det_ipitrib_vipi")) <= 0.0) {
                    ipiNT.setCST("51");
                } else {
                    ipiNT.setCST(formata.StrZero(map.get(i).getString("det_ipitrib_cst"), 2));
                }
                ipiNT.setCST("01");
                ipi.setIPINT(ipiNT);
            }
        }

        PIS pis = new PIS();

        String cstAliqRaw = map.get(i).getString("det_pisaliq_cst");
        String cstOutrRaw = map.get(i).getString("det_pisoutr_cst");
        String vPIS = map.get(i).getString("det_pisaliq_vpis");

        String cstAliq = (cstAliqRaw != null) ? cstAliqRaw.trim() : null;
        String cstOutr = (cstOutrRaw != null) ? cstOutrRaw.trim() : null;

        // CST 09 - Suspensão
        if ("09".equals(formata.StrZero(cstAliq, 2))) {
            PISNT pisNT = new PISNT();
            pisNT.setCST("09");
            pis.setPISNT(pisNT);

            // CST 01 e 02 com valor informado
        } else if (("1".equals(cstAliq) || "2".equals(cstAliq))
                && vPIS != null && !vPIS.trim().isEmpty()) {

            PISAliq pisAliq = new PISAliq();
            pisAliq.setCST(formata.StrZero(cstAliq, 2));
            pisAliq.setVBC(map.get(i).getString("det_pisaliq_vbc"));
            pisAliq.setPPIS(map.get(i).getString("det_pisaliq_ppis"));
            pisAliq.setVPIS(vPIS);
            pis.setPISAliq(pisAliq);

            // CST 50 com valor informado
        } else if ("50".equals(cstAliq)
                && vPIS != null && !vPIS.trim().isEmpty()) {

            PISOutr pisOutr = new PISOutr();
            pisOutr.setCST("50");
            pisOutr.setVBC(map.get(i).getString("det_pisaliq_vbc"));
            pisOutr.setPPIS(map.get(i).getString("det_pisaliq_ppis"));
            pisOutr.setVPIS(vPIS);
            pis.setPISOutr(pisOutr);

            // Fallback: usa det_pisoutr_* se existir
        } else if ((cstAliq != null && !cstAliq.isEmpty() && vPIS != null && !vPIS.trim().isEmpty())
                || (cstOutr != null && !cstOutr.isEmpty())) {

            PISOutr pisOutr = new PISOutr();
            pisOutr.setCST(formata.StrZero(cstOutr, 2));
            pisOutr.setVBC(map.get(i).getString("det_pisoutr_vbc"));
            pisOutr.setPPIS(map.get(i).getString("det_pisoutr_ppis"));
            pisOutr.setVPIS(map.get(i).getString("det_pisoutr_vpis"));
            pis.setPISOutr(pisOutr);

            // Fallback final - CST vazio ou inválido
        } else {
            PISNT pisNT = new PISNT();
            pisNT.setCST(cstAliq != null ? formata.StrZero(cstAliq, 2) : "07");
            pis.setPISNT(pisNT);
        }

        COFINS cofins = new COFINS();
        if (map.get(i).getString("det_cofinsaliq_cst") != null
                && map.get(i).getString("det_cofinsaliq_cst").trim().length() > 0
                && (map.get(i).getString("det_cofinsaliq_cst").trim().equals("1")
                || map.get(i).getString("det_cofinsaliq_cst").trim().equals("2"))
                && map.get(i).getString("det_cofinsaliq_vcofins").trim().length() > 0) {
            COFINSAliq cofinsAliq = new COFINSAliq();
            cofinsAliq.setCST(formata.StrZero(map.get(i).getString("det_cofinsaliq_cst"), 2));
            cofinsAliq.setVBC(map.get(i).getString("det_cofinsaliq_vbc"));
            cofinsAliq.setPCOFINS(map.get(i).getString("det_cofinsaliq_pcofins"));
            cofinsAliq.setVCOFINS(map.get(i).getString("det_cofinsaliq_vcofins"));
            cofins.setCOFINSAliq(cofinsAliq);
        } else if (map.get(i).getString("det_cofinsaliq_cst") != null
                && map.get(i).getString("det_cofinsaliq_cst").trim().length() > 0
                && (map.get(i).getString("det_cofinsaliq_cst").trim().equals("50"))
                && map.get(i).getString("det_cofinsaliq_vcofins").trim().length() > 0) {
            COFINSOutr cofinsOutr = new COFINSOutr();
            cofinsOutr.setCST(formata.StrZero(map.get(i).getString("det_cofinsaliq_cst"), 2));
            cofinsOutr.setVBC(map.get(i).getString("det_cofinsaliq_vbc"));
            cofinsOutr.setPCOFINS(map.get(i).getString("det_cofinsaliq_pcofins"));
            cofinsOutr.setVCOFINS(map.get(i).getString("det_cofinsaliq_vcofins"));
            cofins.setCOFINSOutr(cofinsOutr);
        } else if (map.get(i).getString("det_cofinsoutr_cst") != null
                && map.get(i).getString("det_cofinsoutr_cst").trim().length() > 0
                && map.get(i).getString("det_cofinsoutr_vcofins").trim().length() > 0) {
            COFINSOutr cofinsOutr = new COFINSOutr();
            cofinsOutr.setCST(formata.StrZero(map.get(i).getString("det_cofinsoutr_cst"), 2));
            cofinsOutr.setVBC(map.get(i).getString("det_cofinsoutr_vbc"));
            cofinsOutr.setPCOFINS(map.get(i).getString("det_cofinsoutr_pcofins"));
            cofinsOutr.setVCOFINS(map.get(i).getString("det_cofinsoutr_vcofins"));
            cofins.setCOFINSOutr(cofinsOutr);
        } else {
            if (map.get(i).getString("det_pisaliq_cst") != null
                    && map.get(i).getString("det_pisaliq_cst").trim().length() > 0) {
                {
                    COFINSNT cofinsNT = new COFINSNT();
                    cofinsNT.setCST(formata.StrZero(map.get(i).getString("det_pisaliq_cst"), 2));
                    cofins.setCOFINSNT(cofinsNT);
                }
            } else {
                COFINSNT cofinsnt = new COFINSNT();
                cofinsnt.setCST("07");
                cofins.setCOFINSNT(cofinsnt);
            }
        }
        //COFINSNT cofinsnt = new COFINSNT();
        //cofinsnt.setCST("07");
        //cofins.setCOFINSNT(cofinsnt);

        /*
        // 1. Montar DTO de envio para a calculadora com os dados do item
        CALCULADORAENVIAdto.ItemEnvio itemEnvio = new CALCULADORAENVIAdto.ItemEnvio();
        itemEnvio.setNumero(i + 1);
        itemEnvio.setNcm(map.get(i).getString("det_prod_ncm"));
        itemEnvio.setCst("000"); // ou map.get(i).getString("det_ibscbs_cst") se houver
        itemEnvio.setBaseCalculo(map.get(i).getDouble("det_ibscbs_vbc"));
        itemEnvio.setQuantidade(map.get(i).getDouble("det_prod_qtrib"));
        itemEnvio.setUnidade("tn");
        itemEnvio.setcClassTrib("000001");

        CALCULADORAENVIAdto calculadoraEnvio = new CALCULADORAENVIAdto();
        calculadoraEnvio.setId("0");
        calculadoraEnvio.setVersao("0.0.1");

        OffsetDateTime agoraComFuso = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        calculadoraEnvio.setDataHoraEmissao(agoraComFuso.toString());

        calculadoraEnvio.setUf("SP");
        calculadoraEnvio.setMunicipio(Integer.parseInt("3545803"));
        calculadoraEnvio.setItens(Collections.singletonList(itemEnvio));

        // 2. Chamada à API da calculadora
        CALCULADORARETORNOdto resultado = getCalculadoraRetorno(calculadoraEnvio);
         */
        TIS is = new TIS();
        if (map.get(i).getString("det_is_cst") != null
                && map.get(i).getString("det_is_vimpsel") != null
                && map.get(i).getString("det_is_vimpsel").trim().length() > 0
                && (formata.StrZero(map.get(i).getString("det_is_cst"), 2).equals("000"))) {

            is.setCSTIS(formata.StrZero(map.get(i).getString("det_is_cst"), 3));
            is.setVBCIS(map.get(i).getString("det_is_vbcimpsel"));
            is.setPIS(map.get(i).getString("det_is_pimpsel"));
            is.setVIS(map.get(i).getString("det_is_vimpsel"));

        }
        /*
        //** Inicio Calculo do CalculosIbsCbs com a Lib do Samuel
        //Carrega Json TabelaCclasstrib
        //Ver metodo ConsultaTributacao Teste
        String json = getIbsCbsJson();
        String cclassTrib = "000001";
        //Instancia a Classe Util de Calculo
        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);
        TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(cclassTrib, det);
        JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
        if (ibsCbs.getCST() != null
                && !ibsCbs.getCST().equals("410")) {
           det.getImposto().getContent().add(ibsCbsElement);
        }
        //** Fim Calculo do CalculosIbsCbs com a Lib do Samuel
         */

        TTribNFe tribNFe = new TTribNFe();
        TCIBS cibs = new TCIBS();
        tribNFe.setCST("000");
        tribNFe.setCClassTrib("000001");

        //Alterado: 24/11/2025
        if (map.get(i).getString("det_ibs_cst") != null
                && map.get(i).getString("det_ibs_cst").equals("410")) {
            tribNFe.setCST(map.get(i).getString("det_ibs_cst"));
            tribNFe.setCClassTrib(map.get(i).getString("det_ibs_cclasstrib"));
        }

        TCIBS.GIBSUF gibsuf = new TCIBS.GIBSUF();
        //TDif dif = new TDif();
        //dif.setPDif("0.00");
        //dif.setVDif("0.00");
        //gibsuf.setGDif(dif);

        //TDevTrib devtrib = new TDevTrib();
        //devtrib.setVDevTrib("0.00");
        //gibsuf.setGDevTrib(devtrib);
        //TRed red = new TRed();
        //red.setPAliqEfet("0.00");
        //red.setPRedAliq("0.00");
        //gibsuf.setGRed(red);
        cibs.setVBC(map.get(i).getString("det_ibscbs_vbc"));
        String vibsuf = map.get(i).getString("det_ibsuf_vibsuf");

        if (vibsuf != null && !vibsuf.trim().isEmpty()) {
            gibsuf.setPIBSUF(map.get(i).getString("det_ibsuf_pibsuf"));
            gibsuf.setVIBSUF(vibsuf);
        }
        cibs.setGIBSUF(gibsuf);

        GIBSCBS gibscbs = new GIBSCBS();

        GIBSMun gibsmun = new GIBSMun();

        //TDif dif = new TDif();
        //dif.setPDif("0.00");
        //dif.setVDif("0.00");
        //gibsmun.setGDif(dif);
        //TDevTrib devtrib = new TDevTrib();
        //devtrib.setVDevTrib("0.00");
        //gibsmun.setGDevTrib(devtrib);
        //TRed red = new TRed();
        //red.setPAliqEfet("0.00");
        //red.setPRedAliq("0.00");
        //gibsuf.setGRed(red);
        String vibsmun = map.get(i).getString("det_ibsmun_vibsmun");
        if (vibsmun != null && !vibsmun.trim().isEmpty()) {
            gibsmun.setPIBSMun(map.get(i).getString("det_ibsmun_pibsmun"));
            gibsmun.setVIBSMun(vibsmun);
        }
        cibs.setGIBSMun(gibsmun);

        GCBS cbs = new GCBS();
        String vcbs = map.get(i).getString("det_cbs_vcbs");
        if (vcbs != null && !vcbs.trim().isEmpty()) {
            cbs.setPCBS(map.get(i).getString("det_cbs_pcbs"));
            cbs.setVCBS(vcbs);
        }
        cibs.setGCBS(cbs);

        //Alterado: 24/11/2025
        if (tribNFe.getCST() != null
                && !tribNFe.getCST().equals("410")) {
            tribNFe.setGIBSCBS(cibs);
        }

        //Inicio Alteração: 31/07/2025
        /*
        boolean createTNFeInfNFeDetImposto = true;

        if (map.get(i).getString("det_prod_cfop").trim().equals("5927")) { //Nota de Quebra
            createTNFeInfNFeDetImposto = false;
            
            prod.setIndTot("0");

            ICMS90 icms90 = new ICMS90();
            icms90.setOrig("0");
            icms90.setCST("90");
            icms.setICMS90(icms90);

            ipi.setCEnq("999");
            IPITrib ipitrib = new IPITrib();
            ipitrib.setCST("50");
            ipitrib.setVBC("0.00");
            ipitrib.setPIPI("0.0000");
            ipitrib.setVIPI("0.00");
            ipi.setIPINT(null);
            ipi.setIPITrib(ipitrib);

            PISAliq pisAliq = new PISAliq();
            pisAliq.setCST("01");
            pisAliq.setVBC("0.00");
            pisAliq.setPPIS("0.0000");
            pisAliq.setVPIS("0.00");
            pis.setPISNT(null);
            pis.setPISOutr(null);
            pis.setPISAliq(pisAliq);

            COFINSAliq cofinsAliq = new COFINSAliq();
            cofinsAliq.setCST("01");
            cofinsAliq.setVBC("0.00");
            cofinsAliq.setPCOFINS("0.0000");
            cofinsAliq.setVCOFINS("0.00");
            cofins.setCOFINSNT(null);
            cofins.setCOFINSOutr(null);
            cofins.setCOFINSAliq(cofinsAliq);
        }
        //Fim Alteração:
         */
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipi));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));

        // Início Alteração: 15/01/2026
        if ("2".equals(jsonNFe.getString("ide_iddest").trim())
                && UFDest.equals(jsonNFe.getString("enderdest_uf").trim())
                && jsonNFe.getString("enderdest_indiedest").trim().equals("9")) {

            // ===== Alíquotas em porcentagem (ex: 20.00) =====
            BigDecimal pICMSUFDest = pICMSDest.multiply(BigDecimal.valueOf(100));  // em %
            BigDecimal pFCPUFDest = pFCP.multiply(BigDecimal.valueOf(100));        // em %
            BigDecimal pICMSInter = pICMSInt.multiply(BigDecimal.valueOf(100));    // em %
            BigDecimal pPartilha = pPartilhaFixa100;                                  // em %

            // ===== Valores base da operação =====
            BigDecimal vProd = new BigDecimal(map.get(i).getString("det_prod_vprod"));
            BigDecimal vIPI = new BigDecimal(map.get(i).optString("det_ipitrib_vipi", "0.00"));
            BigDecimal valorBaseInicial = vProd.add(vIPI);

            // ===== Conversão de alíquotas para fração (ex: 0.20) =====
            BigDecimal aliqICMSDest = pICMSUFDest.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
            BigDecimal aliqFCP = pFCPUFDest.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
            BigDecimal aliqInter = pICMSInter.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
            BigDecimal partilhaFracao = pPartilha.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);

            BigDecimal baseDIFAL = valorBaseInicial;

            // ===== Cálculos =====
            BigDecimal vICMSDestinoTotal = baseDIFAL.multiply(aliqICMSDest);
            BigDecimal vICMSOrigem = vProd.multiply(aliqInter); // Aqui usamos a base sem IPI, conforme NT
            BigDecimal vICMSUFDest = (baseDIFAL.multiply(aliqICMSDest)
                    .subtract(valorBaseInicial.multiply(aliqInter))).multiply(
                    pPartilhaFixa100.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP)
            ).setScale(2, RoundingMode.HALF_UP);

            BigDecimal vFCPUFDest = baseDIFAL.multiply(aliqFCP).setScale(2, RoundingMode.HALF_UP);

            // ===== Preenchimento do ICMSUFDest =====
            ICMSUFDest icmsUFDest = new ICMSUFDest();

            icmsUFDest.setVBCUFDest(baseDIFAL.toPlainString());
            icmsUFDest.setPICMSUFDest(pICMSUFDest.toPlainString());
            icmsUFDest.setVICMSUFDest(vICMSUFDest.toPlainString());

            icmsUFDest.setVBCFCPUFDest(baseDIFAL.toPlainString());
            icmsUFDest.setPFCPUFDest(pFCPUFDest.toPlainString());
            icmsUFDest.setVFCPUFDest(vFCPUFDest.toPlainString());

            icmsUFDest.setPICMSInter(pICMSInter.toPlainString());
            icmsUFDest.setPICMSInterPart(pPartilha.toPlainString());

            // A partir de 2019 o valor do remetente sempre será 0,00
            icmsUFDest.setVICMSUFRemet("0.00");

            imposto.getContent().add(
                    new ObjectFactory().createTNFeInfNFeDetImpostoICMSUFDest(icmsUFDest)
            );
        }
        // Fim Alteração: 15/01/2026

        if (is.getCSTIS() != null) {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIS(is));
        }

        //12/09/2025 
        if (vibsmun != null
                && vibsuf != null
                && !vibsmun.isEmpty()
                && !vibsuf.isEmpty()) { //09/01/2026
            cibs.setVIBS(String.format(Locale.US, "%.2f",
                    Double.parseDouble(vibsmun) + Double.parseDouble(vibsuf))
            );
        }
        //tribNFe.setGIBSCBS(cibs);

        //Alterado: 24/11/2025
        if (tribNFe.getCST() != null
                && !tribNFe.getCST().equals("410")) {
            tribNFe.setGIBSCBS(cibs);
        }

        if (tribNFe.getCST() != null) {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIBSCBS(tribNFe));
        }
        det.setImposto(imposto);

        //12/09/2025
        det.setVItem(String.format(Locale.US, "%.2f",
                setStringtoDouble(prod.getVProd())
                + setStringtoDouble(ipi.getIPITrib() != null ? ipi.getIPITrib().getVIPI() : null)//16/09/2025
                + setStringtoDouble(cibs.getVIBS())
                + setStringtoDouble(cbs.getVCBS())
                + setStringtoDouble(is.getVIS())
        ));

        return det;
    }

    private static double setStringtoDouble(String s) {
        if (s == null || (s = s.trim()).isEmpty()) {
            return 0d;
        }
        try {
            return Double.parseDouble(s.replace(',', '.'));
        } catch (Exception e) {
            return 0d;
        }
    }

    /**
     * W - Valores Totais da NF-e
     *
     * @return
     */
    private static Total totaisDaNFe(String nNFe, JSONObject jsonNFe) {
        Total total = new Total();
        ICMSTot icmstot = new ICMSTot();
        icmstot.setVICMSDeson("0.00"); //INFO: | Erro XML: Foi detectado um conteúdo inválido começando com o elemento 'vBCST'. Era esperado um dos 'vICMSDeson'.
        icmstot.setVBC("0.00");
        icmstot.setVICMS("0.00");
        icmstot.setVBCST("0.00");
        icmstot.setVST("0.00");
        icmstot.setVProd("180.00");
        icmstot.setVFrete("0.00");
        icmstot.setVSeg("0.00");
        icmstot.setVDesc("0.00");
        icmstot.setVII("0.00");
        icmstot.setVIPI("0.00");
        icmstot.setVPIS("0.00");
        icmstot.setVCOFINS("0.00");
        icmstot.setVOutro("0.00");
        icmstot.setVNF("180.00");
        total.setICMSTot(icmstot);
        formata formata = new formata();
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FormatFields f = new FormatFields();

        // ===== Início Alteração 15/01/2026 - DIFAL =====
        // Ajuste de vBC e vICMS conforme regra do DIFAL
        if ("2".equals(jsonNFe.getString("ide_iddest").trim())
                && UFDest.equals(jsonNFe.getString("enderdest_uf").trim())
                && jsonNFe.getString("enderdest_indiedest").trim().equals("9")) {

            BigDecimal vProd = new BigDecimal(jsonNFe.getString("icmstot_vprod"));
            BigDecimal vIPI = new BigDecimal(jsonNFe.optString("icmstot_vipi", "0.00"));
            BigDecimal vBC = vProd.add(vIPI);
            BigDecimal pICMS = pICMSInt; // alíquota interestadual (ex: 0.12)

            BigDecimal vICMS = vBC.multiply(pICMS).setScale(2, RoundingMode.HALF_UP);

            icmstot.setVBC(vBC.toPlainString());
            icmstot.setVICMS(vICMS.toPlainString());

        } else {
            // Operações normais, usa os valores existentes
            icmstot.setVBC(jsonNFe.getString("icmstot_vbc"));
            icmstot.setVICMS(jsonNFe.getString("icmstot_vicms"));
        }
        // ===== Fim Alteração 15/01/2026 - DIFAL =====

        icmstot.setVICMSDeson(jsonNFe.getString("icmstot_vicmsdeson"));
        icmstot.setVBCST(jsonNFe.getString("icmstot_vbcst"));
        icmstot.setVProd(jsonNFe.getString("icmstot_vprod"));
        icmstot.setVFrete(jsonNFe.getString("icmstot_vfrete"));
        icmstot.setVSeg(jsonNFe.getString("icmstot_vseg"));
        if (jsonNFe.getString("icmstot_vdesc") != null
                && !jsonNFe.getString("icmstot_vdesc").trim().equals("0.00")) {
            icmstot.setVDesc(jsonNFe.getString("icmstot_vdesc"));
        }
        icmstot.setVII(jsonNFe.getString("icmstot_vii"));
        icmstot.setVIPI(jsonNFe.getString("icmstot_vipi"));
        icmstot.setVPIS(jsonNFe.getString("icmstot_vpis"));
        icmstot.setVCOFINS(jsonNFe.getString("icmstot_vcofins"));
        icmstot.setVOutro(jsonNFe.getString("icmstot_voutro"));
        icmstot.setVNF(jsonNFe.getString("icmstot_vnf"));
        icmstot.setVTotTrib(jsonNFe.getString("icmstot_vtottrib"));

        //icmstot.setVFCPUFDest("0.00");    // 01/08/2018
        //icmstot.setVICMSUFDest("0.00");   // 01/08/2018
        //icmstot.setVICMSUFRemet("0.00");  // 01/08/2018
        icmstot.setVFCP("0.00");          // 01/08/2018
        icmstot.setVFCPST("0.00");        // 01/08/2018
        icmstot.setVFCPSTRet("0.00");     // 01/08/2018
        icmstot.setVIPIDevol("0.00");     // 01/08/2018

        // ===== Início Alteração 15/01/2026 - DIFAL =====
        if ("2".equals(jsonNFe.getString("ide_iddest").trim())
                && UFDest.equals(jsonNFe.getString("enderdest_uf").trim())
                && jsonNFe.getString("enderdest_indiedest").trim().equals("9")) {

            // Base de cálculo DIFAL com IPI
            BigDecimal vBC = new BigDecimal(jsonNFe.getString("icmstot_vprod"));
            BigDecimal vIPI = new BigDecimal(jsonNFe.optString("icmstot_vipi", "0.00"));
            BigDecimal vBCFinal = vBC.add(vIPI); // Incluir IPI

            // Alíquotas
            BigDecimal aliqICMSDest = pICMSDest;
            BigDecimal aliqICMSInter = pICMSInt;
            BigDecimal aliqFCP = pFCP;
            BigDecimal partilha = pPartilhaFixa100.divide(BigDecimal.valueOf(100));

            // Cálculos
            BigDecimal vFCPUFDest = vBCFinal.multiply(aliqFCP).setScale(2, RoundingMode.HALF_UP);
            BigDecimal vICMSUFDest = vBCFinal
                    .multiply(aliqICMSDest.subtract(aliqICMSInter))
                    .multiply(partilha)
                    .setScale(2, RoundingMode.HALF_UP);

            // Atribuições
            icmstot.setVICMSUFDest(vICMSUFDest.toPlainString());
            icmstot.setVFCPUFDest(vFCPUFDest.toPlainString());
            icmstot.setVICMSUFRemet("0.00");

            icmstotInfCompl = icmstot;
        }
// ===== Fim Alteração 15/01/2026 =====


        /*
        icmstot.setVBCSE(jsonNFe.getString("tot_is_vbcse"));
        icmstot.setVIMPSEL(jsonNFe.getString("tot_is_vimpsel"));
        icmstot.setVCRESPRES(jsonNFe.getString("tot_is_vcrespres"));

        icmstot.setVCRESPRESUF(jsonNFe.getString("tot_ibs_uf_vcrespres"));
        icmstot.setVCREDPRECONDSUSUF(jsonNFe.getString("tot_ibs_uf_vcredprescondsus"));
        icmstot.setVDIFUF(jsonNFe.getString("tot_ibs_uf_vdif"));
        icmstot.setVDEVTRIBUF(jsonNFe.getString("tot_ibs_uf_vdevtrib"));
        icmstot.setVDESONUF(jsonNFe.getString("tot_ibs_uf_vdeson"));
        icmstot.setVIBSUF(jsonNFe.getString("tot_ibs_uf_vibs"));

        icmstot.setVCRESPRESMUN(jsonNFe.getString("tot_ibs_mun_vcrespres"));
        icmstot.setVCREDPRECONDSUSMUN(jsonNFe.getString("tot_ibs_mun_vcredprescondsus"));
        icmstot.setVDIFMUN(jsonNFe.getString("tot_ibs_mun_vdif"));
        icmstot.setVDEVTRIBMUN(jsonNFe.getString("tot_ibs_mun_vdevtrib"));
        icmstot.setVDESONMUN(jsonNFe.getString("tot_ibs_mun_vdeson"));
        icmstot.setVIBSMUN(jsonNFe.getString("tot_ibs_mun_vibs"));

        icmstot.setVCRESPRESCBS(jsonNFe.getString("tot_cbs_vcrespres"));
        icmstot.setVCREDPRECONDSUSCBS(jsonNFe.getString("tot_cbs_vcredprescondsus"));
        icmstot.setVDIFCBS(jsonNFe.getString("tot_cbs_vdif"));
        icmstot.setVDEVTRIBCBS(jsonNFe.getString("tot_cbs_vdevtrib"));
        icmstot.setVDESONCBS(jsonNFe.getString("tot_cbs_vdeson"));
        icmstot.setVCBS(jsonNFe.getString("tot_cbs_vcbs"));

        icmstot.setVBCIBSCBS(jsonNFe.getString("tot_vbcibscbs"));
        icmstot.setVTOTNF(jsonNFe.getString("tot_vtotnf"));
         */
        TIBSCBSTot ibscbsTot = new TIBSCBSTot();

        ibscbsTot.setVBCIBSCBS(jsonNFe.getString("tot_vbcibscbs"));

        TIBSCBSTot.GIBS gibs = new TIBSCBSTot.GIBS();
        TIBSCBSTot.GIBS.GIBSUF gibsuf = new TIBSCBSTot.GIBS.GIBSUF();
        gibsuf.setVDif(jsonNFe.getString("tot_ibs_uf_vdif"));
        gibsuf.setVDevTrib(jsonNFe.getString("tot_ibs_uf_vdevtrib"));
        gibsuf.setVIBSUF(jsonNFe.getString("tot_ibs_uf_vibs"));
        gibs.setGIBSUF(gibsuf);

        TIBSCBSTot.GIBS.GIBSMun gibsmun = new TIBSCBSTot.GIBS.GIBSMun();
        gibsmun.setVDif(jsonNFe.getString("tot_ibs_mun_vdif"));
        gibsmun.setVDevTrib(jsonNFe.getString("tot_ibs_mun_vdevtrib"));
        gibsmun.setVIBSMun(jsonNFe.getString("tot_ibs_mun_vibs"));
        gibs.setGIBSMun(gibsmun);

        gibs.setVIBS(
                String.format(Locale.US, "%.2f",
                        Double.parseDouble(gibsuf.getVIBSUF()) + Double.parseDouble(gibsmun.getVIBSMun()))
        );

        //gibs.setVCredPres(jsonNFe.getString("tot_ibs_vcrespres")); //20/01/2026
        //gibs.setVCredPresCondSus(jsonNFe.getString("tot_ibs_vcredprescondsus")); //20/01/2026
        ibscbsTot.setGIBS(gibs);

        TIBSCBSTot.GCBS gcbs = new TIBSCBSTot.GCBS();
        gcbs.setVDif(jsonNFe.getString("tot_cbs_vdif"));
        gcbs.setVDevTrib(jsonNFe.getString("tot_cbs_vdevtrib"));
        gcbs.setVCBS(jsonNFe.getString("tot_cbs_vcbs"));
        //gcbs.setVCredPres(jsonNFe.getString("tot_cbs_vcrespres")); //20/01/2026
        //gcbs.setVCredPresCondSus(jsonNFe.getString("tot_cbs_vcredprescondsus")); //20/01/2026

        gcbs.setVCBS(jsonNFe.getString("tot_cbs_vcbs"));

        ibscbsTot.setGCBS(gcbs);

        //Inicio Alteraçao: 12/09/2025
        TIBSCBSMonoTot IBSCBSMonoTot = new TIBSCBSMonoTot();
        TIBSCBSMonoTot.GIBS BSCBSMonoTotGibs = new TIBSCBSMonoTot.GIBS();

        TIBSCBSMonoTot.GIBS.GIBSUF BSCBSMonoTotGibsUf = new TIBSCBSMonoTot.GIBS.GIBSUF();
        BSCBSMonoTotGibsUf.setVDevTrib("0.00");
        BSCBSMonoTotGibsUf.setVDif("0.00");
        BSCBSMonoTotGibsUf.setVIBSUF(gibsuf.getVIBSUF());
        BSCBSMonoTotGibs.setGIBSUF(BSCBSMonoTotGibsUf);

        TIBSCBSMonoTot.GIBS.GIBSMun BSCBSMonoTotGibsMun = new TIBSCBSMonoTot.GIBS.GIBSMun();
        BSCBSMonoTotGibsMun.setVDevTrib("0.00");
        BSCBSMonoTotGibsMun.setVDif("0.00");
        BSCBSMonoTotGibsMun.setVIBSMun(gibsmun.getVIBSMun());
        BSCBSMonoTotGibs.setGIBSMun(BSCBSMonoTotGibsMun);

        BSCBSMonoTotGibs.setVCredPres("0.00");
        BSCBSMonoTotGibs.setVCredPresCondSus("0.00");
        BSCBSMonoTotGibs.setVIBS(gibs.getVIBS());

        TIBSCBSMonoTot.GCBS BSCBSMonoTotGcbs = new TIBSCBSMonoTot.GCBS();
        BSCBSMonoTotGcbs.setVCredPres("0.00");
        BSCBSMonoTotGcbs.setVCredPresCondSus("0.00");
        BSCBSMonoTotGcbs.setVDevTrib("0.00");
        BSCBSMonoTotGcbs.setVDif("0.00");
        BSCBSMonoTotGcbs.setVCBS(gcbs.getVCBS());

        IBSCBSMonoTot.setVBCIBSCBS(jsonNFe.getString("tot_vbcibscbs"));
        IBSCBSMonoTot.setGIBS(BSCBSMonoTotGibs);
        IBSCBSMonoTot.setGCBS(BSCBSMonoTotGcbs);

        total.setIBSCBSTot(IBSCBSMonoTot);
        //Fim Alteraçao

        //total.setVNFTot(jsonNFe.getString("tot_vtotnf"));
        total.setVNFTot(String.format(Locale.US, "%.2f",
                setStringtoDouble(icmstot.getVNF())
                + setStringtoDouble(gibsuf.getVIBSUF())
                + setStringtoDouble(gibsmun.getVIBSMun())
                + setStringtoDouble(gcbs.getVCBS())
        ));
        return total;
    }

    /**
     * X - Informações do Transporte da NF-e
     *
     * @return
     */
    private static Transp dadosDoTransporte(String nNFe, JSONObject jsonNFe) {

        Transp transp = new Transp();
        Transporta transporta = new Transporta();
        try {
            formata formata = new formata();
            SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FormatFields f = new FormatFields();
            transp.setModFrete(jsonNFe.getString("transp_modfrete"));
            if (jsonNFe.getString("transporta_xnome") != null
                    && jsonNFe.getString("transporta_xnome").trim().length() > 0) {
                transporta.setCNPJ(jsonNFe.getString("transporta_cnpj").trim());
                transporta.setXNome(jsonNFe.getString("transporta_xnome").trim());
                if (jsonNFe.getString("transporta_ie") != null
                        && jsonNFe.getString("transporta_ie").trim().length() > 0) { //08/11/2016
                    transporta.setIE(jsonNFe.getString("transporta_ie").trim());
                }
                transporta.setXEnder(jsonNFe.getString("transporta_xender").trim());
                transporta.setXMun(jsonNFe.getString("transporta_xmun").trim());
                transporta.setUF(TUf.valueOf(jsonNFe.getString("transporta_uf").trim()));
                transp.setTransporta(transporta);
            }
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeTranspVolController.List(nNFe.trim());
            for (int i = 0; i < map.size(); i++) {
                if (map.get(i).getString("transp_vol_qvol") != null) {
                    Vol vol = new Vol();
                    if (map.get(i).getString("transp_vol_esp") != null
                            && map.get(i).getString("transp_vol_esp").trim().length() > 0) {
                        vol.setEsp(map.get(i).getString("transp_vol_esp").trim());
                    }
                    if (map.get(i).getString("transp_vol_marca") != null
                            && map.get(i).getString("transp_vol_marca").trim().length() > 0) {
                        vol.setMarca(map.get(i).getString("transp_vol_marca").trim());
                    }
                    if (map.get(i).getString("transp_vol_nvol") != null
                            && map.get(i).getString("transp_vol_nvol").trim().length() > 0) {
                        vol.setNVol(map.get(i).getString("transp_vol_nvol").trim());
                    }
                    vol.setPesoB(map.get(i).getString("transp_vol_pesob"));
                    vol.setPesoL(map.get(i).getString("transp_vol_pesol"));
                    vol.setQVol(map.get(i).getString("transp_vol_qvol"));
                    transp.getVol().add(vol);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transp;
    }

    /**
     * X - Informações do Transporte da NF-e
     *
     * @return
     */
    private static Cobr dadosDeCobranca_fat(String nNFe, JSONObject jsonNFe) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        Cobr cobr = new Cobr();

        try {
            //transp.setModFrete("9");
            //Fat fat = new Fat();
            //Dup dup = new Dup();
            formata formata = new formata();
            SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd");
            FormatFields f = new FormatFields();
            if (jsonNFe.getString("fat_nfat") != null
                    && jsonNFe.getString("fat_nfat").trim().length() > 0) {
                //Inicio alteração: 08/08/2018  
                double vOrig = 0.0;
                double vDesc = 0.0;
                if (System.getProperty("tpAmb").equals("2")) {
                    //vDesc = 0.01;
                    //vDesc = 0.00; // 03/09/2018
                }

                //vOrig = con_NFe.resultset.getDouble("fat_vliq") + vDesc;
                //Fim alteração
                //vOrig = Double.parseDouble(formato.format(vOrig).replace(".", "").replace(",", "."));
                Fat fat = new Fat();

                cobr.setFat(fat);

                //fat.setNFat(con_NFe.resultset.getString("fat_nfat").trim());
                //fat.setVOrig(con_NFe.resultset.getString("fat_vorig"));  //08/08/2018
                //fat.setVOrig(f.getNumeroFormatado2cd(Double.toString(vOrig)).replace(".", "").replace(",", "."));    //08/08/2018   
                //String ww = f.getNumeroFormatado2cd(Double.toString(vOrig)).replace(".", "").replace(",", ".");
                if (jsonNFe.getString("fat_vdesc") != null) {
                    // vDesc = jsonNFe.getDouble("fat_vdesc");
                }

                vOrig = jsonNFe.getDouble("fat_vliq") + vDesc;
                //Inicio alteração: 08/08/2018  
                //if (System.getProperty("tpAmb").equals("2")) {
                //    fat.setVDesc(f.getNumeroFormatado2cd(Double.toString(vDesc)).replace(".", "").replace(",", "."));
                //fat.setVDesc("0.00");                       
                //}
                //Fim alteração
                fat.setNFat(jsonNFe.getString("fat_nfat").trim());
                //fat.setVOrig(formato.format(vOrig).replace(".", "").replace(",", "."));
                fat.setVOrig(jsonNFe.getString("fat_vliq"));
                //fat.setVDesc(formato.format(vDesc).replace(".", "").replace(",", "."));
                //fat.setVDesc(f.getNumeroFormatado2cd(Double.toString(vDesc)).replace(".", "").replace(",", "."));
                //fat.setVLiq(formato.format(con_NFe.resultset.getDouble("fat_vliq")).replace(".", "").replace(",", "."));
                fat.setVLiq(jsonNFe.getString("fat_vliq"));
                //cobr.setFat(fat);
                ArrayList<JSONObject> map = new ArrayList<JSONObject>();
                map = NfeCobrDupController.List(nNFe.trim());
                for (int i = 0; i < map.size(); i++) {
                    //while (con_default.resultset.next()) {
                    if (map.get(i).getString("cobr_dup_ndup") != null) {
                        Dup dup = new Dup();
                        dup.setNDup(map.get(i).getString("cobr_dup_ndup").trim());
                        dup.setDVenc(map.get(i).getString("cobr_dup_dvenc_yyyy-MM-dd"));
                        //dup.setDVenc(con_default.resultset.getString("cobr_dup_dvenc").trim());
                        dup.setVDup(map.get(i).getString("cobr_dup_vdup").trim());
                        cobr.getDup().add(dup);
                    }
                }
            }
            //}
            //con_NFe.resultset.close();
            //con_NFe.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(MontaNfe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobr;
    }

    /**
     * Z - Informações Adicionais da NF-e
     */
    private static InfAdic informacoesAdicionais(String nNFe, JSONObject jsonNFe) {
        InfAdic infAdic = new InfAdic();
        //infAdic.setInfCpl("Informacoes Adicionais da NFe");
        formata formata = new formata();
        SimpleDateFormat formatador1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FormatFields f = new FormatFields();
        String Winfadic_infcpl = "";
        if (!jsonNFe.getString("ide_tpemis").trim().equals("1")) {
            Winfadic_infcpl = "Data e Hora de Entrada em Contingencia: " + jsonNFe.getString("ide_dhcont") + ";";
            Winfadic_infcpl = Winfadic_infcpl
                    + "Justificativa de Entrada em Contingencia: " + jsonNFe.getString("ide_xjust") + ";";
        }
        Winfadic_infcpl = Winfadic_infcpl
                + jsonNFe.getString("infadic_infcpl");
        if (Winfadic_infcpl != null
                && Winfadic_infcpl.trim().length() > 0) {
            infAdic.setInfCpl(removeAcentos(Winfadic_infcpl.trim()));
        }
        if (jsonNFe.getString("infadic_infadfisco") != null
                && jsonNFe.getString("infadic_infadfisco").trim().length() > 0) {
            infAdic.setInfAdFisco(removeAcentos(jsonNFe.getString("infadic_infadfisco").trim()));
        }

        // ===== Início Alteração 15/01/2026 - DIFAL =====
        if ("2".equals(jsonNFe.getString("ide_iddest").trim())
                && UFDest.equals(jsonNFe.getString("enderdest_uf").trim())
                && jsonNFe.getString("enderdest_indiedest").trim().equals("9")) {
            // Locale para formato monetário brasileiro
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            // Formatação dos valores
            String vICMSUFDestFormatado = nf.format(new BigDecimal(icmstotInfCompl.getVICMSUFDest()));
            String vFCPUFDestFormatado = nf.format(new BigDecimal(icmstotInfCompl.getVFCPUFDest()));
            String vICMSUFRemetFormatado = nf.format(new BigDecimal(icmstotInfCompl.getVICMSUFRemet()));

            // Construção do texto
            String infCplTexto = "Valores totais do ICMS Interestadual: DIFAL da UF destino "
                    + vICMSUFDestFormatado
                    + " + FCP "
                    + vFCPUFDestFormatado
                    + "; DIFAL da UF Origem "
                    + vICMSUFRemetFormatado + ".";

            // Define no campo infCpl
            infAdic.setInfCpl(infCplTexto);
            System.out.println(infCplTexto);
        }
        // ===== Fim Alteração 15/01/2026 - DIFAL =====
        return infAdic;
    }

    private static TInfRespTec infRespTec(String nNFe, JSONObject jsonNFe) {
        env Env = new env();
        TInfRespTec infRespTec = new TInfRespTec();
        infRespTec.setCNPJ(Env.getinfRespTec_CNPJ());
        infRespTec.setXContato(Env.getinfRespTec_XContato());
        infRespTec.setEmail(Env.getinfRespTec_Email());
        infRespTec.setFone(Env.getinfRespTec_Fone());
        return infRespTec;
    }

    private static String strValueOf(TEnviNFe enviNFe) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);
        Marshaller marshaller = context.createMarshaller();
        JAXBElement<TEnviNFe> element = new ObjectFactory().createEnviNFe((enviNFe));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        StringWriter sw = new StringWriter();
        marshaller.marshal(element, sw);
        String xml = sw.toString();
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
        xml = xml.replaceAll("<enviNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\">", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><enviNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
        xml = xml.replaceAll("Nº", "No");
        xml = xml.replaceAll("°", "o");
        xml = xml.replaceAll("º", "o");
        xml = xml.replaceAll("ª", "a");
        xml = xml.replaceAll("Â", "A");
        xml = xml.replaceAll("Ã", "A");
        xml = xml.replaceAll("Ç", "C");
        xml = xml.replaceAll("ç", "c");
        xml = semAcento(xml);
        return xml;
    }

    private static void error(String error) {
        System.out.println("| ERROR: " + error);
    }

    private static void info(String info) {
        System.out.println("| INFO: " + info);
    }

    public static String removeAcentos(String str) {
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        String ret = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(ret).replaceAll("");
    }

    public static String semAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
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

    public static String calcularVDescDadosDoProduto(String qComStr, String vUnComStr, String vProdStr) {
        try {
            // Conversão para BigDecimal para garantir precisão monetária
            BigDecimal qCom = new BigDecimal(qComStr);
            BigDecimal vUnCom = new BigDecimal(vUnComStr);
            BigDecimal vProd = new BigDecimal(vProdStr);

            // 1. Calcular o Valor Bruto (Multiplicação)
            // Usamos uma escala maior (ex: 4) na multiplicação para garantir precisão na comparação,
            // antes de arredondar o desconto final.
            BigDecimal valorMultiplicacao = qCom.multiply(vUnCom).setScale(4, RoundingMode.HALF_UP);

            // 2. Compara: verifica se a multiplicação (Valor Bruto) é diferente de VProd (Valor Líquido)
            if (valorMultiplicacao.compareTo(vProd.setScale(4, RoundingMode.HALF_UP)) != 0) {

                // Desconto = Valor Multiplicação - VProd.
                // O resultado é arredondado para 2 casas decimais para o campo VDesc.
                BigDecimal descontoCalculado = valorMultiplicacao.subtract(vProd).setScale(2, RoundingMode.HALF_UP);

                // 3. Verifica se o valor calculado é um DESCONTO (positivo) e não zero
                if (descontoCalculado.compareTo(BigDecimal.ZERO) > 0) {
                    return descontoCalculado.toPlainString();
                }
            }
            // Retorna null nos casos em que:
            // a) ValorBruto == VProd (sem diferença)
            // b) ValorBruto < VProd (acréscimo)
            // c) Valor calculado é 0.00
            return null;
        } catch (NumberFormatException e) {
            // Em caso de erro de formato, retorna null para não setar o campo de desconto
            System.err.println("Erro ao calcular desconto: Formato numérico inválido para QCom, VUnCom ou VProd: " + e.getMessage());
            return null;
        }
    }

    private static String getIbsCbsJson() throws IOException {
        return XmlNfeUtil.leXml("src/test/resources/ibscbs.json");
    }

    private static TEnviNFe addTotaisIbsCbs(IbsCbsUtil ibsCbsUtil, TEnviNFe enviNFe, ConfiguracoesNfe config) throws NfeException {
        BigDecimal vNfTot = ibsCbsUtil.calculaVnfTot(enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVNF());
        enviNFe.getNFe().get(0).getInfNFe().getTotal().setVNFTot(ObjetoUtil.getValor2Casas(vNfTot));

        TIBSCBSMonoTot totaisIbsCsb = ibsCbsUtil.preencheTotaisIbsCsb();
        enviNFe.getNFe().get(0).getInfNFe().getTotal().setIBSCBSTot(totaisIbsCsb);
        return Nfe.montaNfe(config, enviNFe, true);
    }

}
