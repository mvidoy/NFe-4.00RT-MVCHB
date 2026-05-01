package app.views.operations.nfe;

import app.controllers.NfeCobrDupController;
import app.controllers.NfeController;
import app.controllers.NfeDetProdController;
import app.controllers.NfeInfProtController;
import app.controllers.NfeInformacoesPagamentoController;
import app.controllers.NfeNfRefController;
import app.controllers.NfeTranspVolController;
import app.models.NFE;
import app.models.NFE_NFREF;
import app.views.operations.nfe.modal.CancelamentoPorEvento;
import app.views.operations.nfe.modal.CartaDeCorrecaoPorEvento;
import app.views.operations.nfe.modal.ConsultaProtocoloReciboNFe;
import app.views.operations.nfe.modal.ConsultaSituacaoNFe;
import app.views.operations.nfe.modal.DetalhaEventoNFe;
import app.views.operations.nfe.modal.EnviaXMLAssinado;
import app.views.operations.nfe.modal.Modal_ViewDataEnviaXMLAssinadoService;
import app.views.operations.nfe.modal.ExportaArquivosXML;
import app.views.operations.nfe.modal.ImprimeDANFe;
import app.views.operations.nfe.modal.Modal_ViewDataEnviaEmail;
import app.views.operations.nfe.modal.Modal_ViewDataRegistraTitulo;
import app.views.operations.nfe.modal.MostraChavedeAcesso;
import app.views.operations.nfe.modal.ValidaXML;
import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;
import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.NFECBdto;
import com.backend.dtos.NFEdto;
import com.backend.exceptions.MD1Exception;
import com.frontend.config.env;
import com.frontend.tablemodels.NFEDETPRODtablemodel;
import com.frontend.util.ProcessaTokenUtil;
import com.frontend.util.ValidFieldNumeric;
import com.frontend.util.VerificaConexaoInternet;
import com.frontend.util.VerifyFileExcelOpen;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import org.json.JSONObject;
import util.AlignTableHeaderCenter;
import util.EditCellulaTable;
import util.FormatFields;
import util.aligntableheaderleft;
import util.aligntableheaderright;
import util.formata;

public class NFe extends javax.swing.JInternalFrame {

    NFE eNFE = new NFE();
    env Env = new env();
    formata formata = new formata();
    FormatFields f = new FormatFields();
    Date data = new Date();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
    EditCellulaTable editarCelulaTabela = new EditCellulaTable();
    int prow = 0;
    int pcol = 0;
    int flag_start = 0;
    int corrente = 0;
    boolean wflagInclusao = false;
    boolean wOmVErro = false;
    String ww_Infnfe_statusnfe = "";
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    private ConsultaSituacaoNFe ConsultaSituacaoNFe_frm;
    private ConsultaProtocoloReciboNFe ConsultaProtocoloReciboNFe_frm;
    private ValidaXML ValidaXML_frm;
    private Modal_ViewDataEnviaXMLAssinadoService EnviaXMLAssinado_frm;
    private ExportaArquivosXML ExportaArquivosXML_frm;
    private CancelamentoPorEvento CancelamentoPorEvento_frm;
    private MostraChavedeAcesso MostraChavedeAcesso_frm;
    private CartaDeCorrecaoPorEvento CartaDeCorrecaoPorEvento_frm;
    private DetalhaEventoNFe DetalhaEventoNFeAutorizada_frm;

    private Modal_ViewDataEnviaEmail Modal_ViewDataEnviaEmail_frm;
    private Modal_ViewDataRegistraTitulo Modal_ViewDataRegistraTitulo_frm;

    public NFEDETPRODtablemodel NFEDETPRODtableModel;
    private JTable NFEDETPRODtable;
    NFEdto nFEdto = new NFEdto();

    MontaMalote montaMalote = new MontaMalote();
    EnviaMalote enviaMalote = new EnviaMalote();

    private JComponent originalGlassPane; // Armazena o GlassPane original

    String PATH_APP = Env.getPATH_APP();
    String planPrevcaixa = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";

    public NFe(JDesktopPane jDesktop, JInternalFrame jFrame) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
        initComponents();
        this.originalGlassPane = (JComponent) this.getGlassPane();
        //this.setClosable(true);
        state();
        if (System.getProperty("tpAmb").equals("1")) {
            jLabel76.setText("");
        }
        int red = 153;
        int green = 153;
        int blue = 255;
        Color myBlue = new Color(red, green, blue);
        UIManager.put("ComboBox.disabledForeground", myBlue);
        UIManager.put("TextField.disabledForeground", myBlue);
        jTabbedPane1.setEnabledAt(11, false);
        jTabbedPane1.setEnabledAt(12, false);
        jTabbedPane1.setEnabledAt(13, false);
        jTabbedPane3.setEnabledAt(1, false);
        jTabbedPane3.setEnabledAt(5, false);
        jTabbedPane3.setEnabledAt(1, false);
        jTabbedPane4.setEnabledAt(1, false);
        jTabbedPane4.setEnabledAt(2, false);
        jP_HitoricosEventos.setVisible(false);
        jP_Contigencia.setVisible(false);
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jcbx_FiltroSituacaoNFe.setSelectedIndex(0);
        MontaTabelaGerenciamento();
        if (jTable1.getRowCount() > 0) {
            try {
                tf_InfNFe_NNF.setText(jTable1.getValueAt(0, 0).toString().trim());
                JSONObject jsonNFe = new JSONObject();
                jsonNFe = NfeController.Index(this.tf_InfNFe_NNF.getText().trim());
                if (!jsonNFe.isEmpty()) {
                    LoadFM(jsonNFe);
                }
            } catch (ParseException ex) {
                returnParams(new String[]{ex.toString()});
            } catch (SQLException ex) {
                returnParams(new String[]{ex.toString()});
            }
        }
        disableFields();
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jSeparator2 = new javax.swing.JSeparator();
        jDktInputData = new javax.swing.JDesktopPane();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        desktopPane = new javax.swing.JDesktopPane();
        jPanel7 = new javax.swing.JPanel();
        botao_Pesquisar = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        tf_FiltroChavedeAcesso = new javax.swing.JTextField();
        tf_FiltroNumeroFinal = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jDC_FiltroDataInicial = new com.toedter.calendar.JDateChooser();
        jDC_FiltroDataFinal = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jcbx_FiltroExportadaXML = new javax.swing.JComboBox();
        tf_FiltrocnpjcpfDestinatario = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tf_FiltroSerie = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tf_FiltroNumeroInicial = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jcbx_FiltroSituacaoNFe = new javax.swing.JComboBox();
        botao_NovaPesquisa = new javax.swing.JButton();
        jCheck_FiltroDanfeImpresso = new javax.swing.JCheckBox();
        jLabel51 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jcbx_FiltroUFdestinatario = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jcbx_FiltroTipo = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLb_Ide_cNF = new javax.swing.JLabel();
        tf_Ide_cNF = new javax.swing.JTextField();
        tf_Ide_cDV = new javax.swing.JTextField();
        jLb_Ide_cDV = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        //#
        //Qualquer número válido, usa Character.isDigit.
        //'
        //Caractere de escape, usado para o escape de qualquer caractere de formato especial.
        //U
        //Qualquer caractere(Character.isLetter). Todas as letras minúsculas são transformadas em maiúsculas.
        //L
        //Qualquer caractere(Character.isLetter). Todas as letras são transformadas para minúsculas.
        //A
        //Qualquer caractere ou número (Character.isLetter ou Character.isDigit).
        //?
        //Qualquer caractere.
        //*
        //Qualquer coisa.
        //H
        //Qualquer caractere hexadecimal (0-9, a-f ou A-F).
        //String s = tf_razaosocial.getText();
        //try
        //{
            //   format_tf_descricao = new MaskFormatter(repete("A",40));
            //}
        //catch (Exception e)
        //{
            //   JOptionPane.showMessageDialog(this,"Falha na formatação do campo código");
            //}
        tf_Ide_Mod = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tf_Ide_Serie = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        tf_Ide_DhSaiEnt = new javax.swing.JTextField();
        tf_Ide_DhEmi = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jcbx_Ide_tpEmis = new javax.swing.JComboBox();
        tf_Ide_NNF = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jcbx_Ide_tpNF = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        jcbx_Ide_finNFe = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        jcbx_Ide_tpImp = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jcbx_Ide_idDest = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jcbx_Ide_indFinal = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        jcbx_Ide_indPres = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        tf_Ide_natOp = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jCheck_InformaCodigoNumerico = new javax.swing.JCheckBox();
        jLabel79 = new javax.swing.JLabel();
        jcbx_Ide_tpNFDebito = new javax.swing.JComboBox();
        jLabel151 = new javax.swing.JLabel();
        jcbx_Ide_tpNFCredito = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        tf_ide_vNF = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        tf_ide_vICMS = new javax.swing.JTextField();

        tf_ide_vST = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jP_HitoricosEventos = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        botao_detalharevento = new javax.swing.JButton();
        jP_Contigencia = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        tf_Ide_DhCont = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        tf_Ide_xjust = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        tf_Ide_xMunFG = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jcbx_Ide_xUF = new javax.swing.JComboBox();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();

        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        tf_Emit_CNPJ = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_EnderEmit_IE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_EnderEmit_IEST = new javax.swing.JTextField();
        tf_Emit_xFant = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf_Emit_xNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_EnderEmit_IM = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        tf_EnderEmit_CNAE = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jcbx_EnderEmit_CRT = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_EnderEmit_CEP = new javax.swing.JTextField();
        tf_EnderEmit_xLgr = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tf_EnderEmit_fone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_EnderEmit_nro = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tf_EnderEmit_xBairro = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tf_EnderEmit_xCpl = new javax.swing.JTextField();
        jcbx_EnderEmit_xMun = new javax.swing.JComboBox();
        jcbx_EnderEmit_UF = new javax.swing.JComboBox();
        jcbx_EnderEmit_xPais = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jcbx_Dest_tpCNPJCPF = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        tf_Dest_CNPJ = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jcbx_EnderDest_IE = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        tf_EnderDest_ISUF = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        tf_EnderDest_email = new javax.swing.JTextField();
        botao_validar8 = new javax.swing.JButton();
        botao_validar14 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        tf_Dest_xNome = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jcbx_EnderDest_indIEDest = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        tf_EnderDest_IM = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        tf_EnderDest_CEP = new javax.swing.JTextField();
        tf_EnderDest_xLgr = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        tf_EnderDest_fone = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        tf_EnderDest_nro = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        tf_EnderDest_xBairro = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        tf_EnderDest_xCpl = new javax.swing.JTextField();
        jcbx_EnderDest_xMun = new javax.swing.JComboBox();
        jcbx_EnderDest_UF = new javax.swing.JComboBox();
        jcbx_EnderDest_xPais = new javax.swing.JComboBox();
        jLabel75 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jCheck_localderetiradadiferentedoemitente = new javax.swing.JCheckBox();
        botao_validar15 = new javax.swing.JButton();
        jCheck_Localdeentregadiferentedodestinatario = new javax.swing.JCheckBox();
        botao_validar16 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        tf_ValorTotalNota1 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        tf_ICMSTot_vBC = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        tf_ICMSTot_vProd = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        tf_ICMSTot_vII = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        tf_ICMSTot_vSeg = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        tf_ICMSTot_vICMS = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        tf_ICMSTot_vFrete = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        tf_ICMSTot_vIPI = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        tf_ICMSTot_vDesc = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        tf_ICMSTot_vNF = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        tf_ICMSTot_vBCST = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        tf_ICMSTot_vPartilhaRemetente = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        tf_ICMSTot_vPIS = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        tf_ICMSTot_vOutro = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        tf_ICMSTot_vST = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        tf_ICMSTot_vPartilhaDestinatario = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        tf_ICMSTot_vCOFINS = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        tf_ICMSTot_vICMSDeson = new javax.swing.JTextField();
        botao_validar17 = new javax.swing.JButton();
        jLabel141 = new javax.swing.JLabel();
        tf_ICMSTot_vFCP = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        tf_ICMSTot_vICMSUFDest = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        tf_ICMSTot_vFCPST = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        tf_ICMSTot_vFCPSTRet = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        tf_ICMSTot_vIPIDevol = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        tf_ISSQNtot_vBC = new javax.swing.JTextField();
        tf_ISSQNtot_vPIS = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        tf_ISSQNtot_vServ = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        tf_ISSQNtot_vDeducao = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        tf_ISSQNtot_vDescIncond = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        tf_ISSQNtot_vDescCond = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        tf_ISSQNtot_vISSRet = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        tf_ISSQNtot_vOutro = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        tf_ISSQNtot_vCOFINS = new javax.swing.JTextField();
        tf_ISSQNtot_vISS = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jDC_ISSQNtot_dCompet = new com.toedter.calendar.JDateChooser();
        jLabel110 = new javax.swing.JLabel();
        jcbx_ISSQNtot_cRegTrib = new javax.swing.JComboBox();
        jPanel43 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel235 = new javax.swing.JLabel();
        tf_tot_ibs_uf_vcrespres = new javax.swing.JTextField();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        tf_tot_ibs_uf_vcredprescondsus = new javax.swing.JTextField();
        tf_tot_ibs_uf_vdif = new javax.swing.JTextField();
        jLabel239 = new javax.swing.JLabel();
        tf_tot_ibs_uf_vdevtrib = new javax.swing.JTextField();
        jLabel240 = new javax.swing.JLabel();
        tf_tot_ibs_uf_vdeson = new javax.swing.JTextField();
        jLabel241 = new javax.swing.JLabel();
        tf_tot_ibs_uf_vibs = new javax.swing.JTextField();
        jPanel59 = new javax.swing.JPanel();
        jLabel242 = new javax.swing.JLabel();
        tf_tot_ibs_mun_vcrespres = new javax.swing.JTextField();
        jLabel243 = new javax.swing.JLabel();
        tf_tot_ibs_mun_vdevtrib = new javax.swing.JTextField();
        tf_tot_ibs_mun_vdeson = new javax.swing.JTextField();
        jLabel244 = new javax.swing.JLabel();
        tf_tot_ibs_mun_vcredprescondsus = new javax.swing.JTextField();
        jLabel248 = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        tf_tot_ibs_mun_vdif = new javax.swing.JTextField();
        jLabel250 = new javax.swing.JLabel();
        tf_tot_ibs_mun_vibs = new javax.swing.JTextField();
        jLabel238 = new javax.swing.JLabel();
        tf_tot_vbcibscbs = new javax.swing.JTextField();
        jLabel251 = new javax.swing.JLabel();
        tf_tot_ibs_vibs = new javax.swing.JTextField();
        jLabel245 = new javax.swing.JLabel();
        tf_tot_ibs_vcrespres = new javax.swing.JTextField();
        tf_tot_ibs_vcredprescondsus = new javax.swing.JTextField();
        jLabel246 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel258 = new javax.swing.JLabel();
        tf_tot_is_vbcsel = new javax.swing.JTextField();
        jLabel259 = new javax.swing.JLabel();
        tf_tot_is_vimpsel = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        jLabel252 = new javax.swing.JLabel();
        tf_tot_cbs_vcrespres = new javax.swing.JTextField();
        jLabel253 = new javax.swing.JLabel();
        tf_tot_cbs_vcredprescondsus = new javax.swing.JTextField();
        jLabel254 = new javax.swing.JLabel();
        tf_tot_cbs_vdevtrib = new javax.swing.JTextField();
        tf_tot_cbs_vdeson = new javax.swing.JTextField();
        jLabel255 = new javax.swing.JLabel();
        jLabel256 = new javax.swing.JLabel();
        tf_tot_cbs_vdif = new javax.swing.JTextField();
        jLabel257 = new javax.swing.JLabel();
        tf_tot_cbs_vcbs = new javax.swing.JTextField();
        jLabel260 = new javax.swing.JLabel();
        tf_tot_vtotnf = new javax.swing.JTextField();
        jLabel261 = new javax.swing.JLabel();
        tf_tot_cbs_vbc = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        tf_RetTrib_vBC = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        tf_RetTrib_vBCIRRF = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        tf_RetTrib_vBCRetPrev = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        tf_RetTrib_vRetCOFINS = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        tf_RetTrib_vIRRF = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        tf_RetTrib_vRetPrev = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        tf_RetTrib_vRetCSLL = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jcbx_Transp_modFrete = new javax.swing.JComboBox();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jcbx_Transporta_tpCNPJCPF = new javax.swing.JComboBox();
        jLabel118 = new javax.swing.JLabel();
        tf_Transporta_CNPJ = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        tf_Transporta_IE = new javax.swing.JTextField();
        jchkbx_isentodoicms = new javax.swing.JCheckBox();
        botao_validar18 = new javax.swing.JButton();
        botao_validar19 = new javax.swing.JButton();
        jLabel121 = new javax.swing.JLabel();
        tf_Transporta_xNome = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        tf_Transporta_xEnder = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jcbx_Transporta_xMun = new javax.swing.JComboBox();
        jcbx_Transporta_UF = new javax.swing.JComboBox();
        jLabel130 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        tf_RetTransp_vBCRet = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        tf_RetTransp_pICMSRet = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        tf_RetTransp_vServ = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jcbx_RetTransp_UF = new javax.swing.JComboBox();
        jLabel135 = new javax.swing.JLabel();
        jcbx_RetTransp_cMunFG = new javax.swing.JComboBox();
        jLabel136 = new javax.swing.JLabel();
        jcbx_RetTransp_CFOP = new javax.swing.JComboBox();
        jLabel137 = new javax.swing.JLabel();
        tf_RetTransp_vICMSRet = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        tf_TVeiculo_placa = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        jcbx_TVeiculo_UF = new javax.swing.JComboBox();
        jLabel140 = new javax.swing.JLabel();
        tf_TVeiculo_RNTC = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        tf_Fat_vOrig = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        tf_Fat_nFat = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        tf_Fat_vDesc = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        tf_Fat_vLiq = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();

        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_InfAdic_infAdFisco = new javax.swing.JTextArea();
        jLabel126 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_InfAdic_infCpl = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        tf_Signature_Assunto = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        tf_Signature_CNPJ = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        tf_Signature_Emissor = new javax.swing.JTextField();
        jPanel38 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        tf_Signature_DataInicial = new javax.swing.JTextField();
        jLabel150 = new javax.swing.JLabel();
        tf_Signature_DataFinal = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        tf_InfNFe_Id = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tf_InfNFe_Versao = new javax.swing.JTextField();
        botao_chave = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        tf_InfNFe_PedidoVenda = new javax.swing.JTextField();
        tf_Infnfe_statusnfe = new javax.swing.JTextField();

        tf_InfNFe_NNF = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        botao_enviarEmail = new javax.swing.JButton();
        botao_transmitir = new javax.swing.JButton();
        botao_exportar = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();
        botao_validar = new javax.swing.JButton();
        botao_gravar = new javax.swing.JButton();
        botao_alterar = new javax.swing.JButton();
        botao_chavedeacesso = new javax.swing.JButton();
        botao_consultarecibo = new javax.swing.JButton();
        botao_imprimirdanfe = new javax.swing.JButton();
        botao_cancelanfe = new javax.swing.JButton();
        botao_cartacorrecao = new javax.swing.JButton();
        botao_previsualizardanfe = new javax.swing.JButton();
        botao_excluir = new javax.swing.JButton();
        botao_consultanasefaz = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        statusbar = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        botao_registrarTitulo = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setForeground(new java.awt.Color(255, 255, 255));
        setTitle("GERENCIAMENTO DE NOTAS FISCAIS (NF-e) - Revisão: 17 - Data da Última Revisão: 31/03/2026 - Data da Elaboração: 20/06/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jDktInputData.setBackground(new java.awt.Color(236, 233, 216));
        jDktInputData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Pedido de Venda");
        jLabel12.setAutoscrolls(true);

        desktopPane.setBackground(new java.awt.Color(236, 233, 216));
        desktopPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        desktopPane.setForeground(new java.awt.Color(255, 255, 255));
        desktopPane.setFocusTraversalPolicyProvider(true);

        jPanel7.setBackground(new java.awt.Color(223, 218, 218));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(725, 92));

        botao_Pesquisar.setMnemonic('V');
        botao_Pesquisar.setText("Pesquisar");
        botao_Pesquisar.setToolTipText("");
        botao_Pesquisar.setPreferredSize(new java.awt.Dimension(73, 23));
        botao_Pesquisar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_PesquisarMouseMoved(evt);
            }
        });
        botao_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_PesquisarActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Nota Autorizada Exportada para XML");

        tf_FiltroChavedeAcesso.setEditable(false);
        tf_FiltroChavedeAcesso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_FiltroChavedeAcesso.setToolTipText("");
        tf_FiltroChavedeAcesso.setEnabled(false);

        tf_FiltroNumeroFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_FiltroNumeroFinal.setToolTipText("");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("CNPJ/CPF do Destinatário");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Período de");
        jLabel26.setToolTipText("");

        jDC_FiltroDataInicial.setToolTipText("");
        jDC_FiltroDataInicial.setEnabled(false);

        jDC_FiltroDataFinal.setToolTipText("");
        jDC_FiltroDataFinal.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("a");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Situação da NF-e");
        jLabel29.setToolTipText("");

        jcbx_FiltroExportadaXML.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "Sim", "Não" }));
        jcbx_FiltroExportadaXML.setToolTipText("");
        jcbx_FiltroExportadaXML.setEnabled(false);

        tf_FiltrocnpjcpfDestinatario.setEditable(false);
        tf_FiltrocnpjcpfDestinatario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_FiltrocnpjcpfDestinatario.setToolTipText("");
        tf_FiltrocnpjcpfDestinatario.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Serie");
        jLabel31.setToolTipText("");

        tf_FiltroSerie.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_FiltroSerie.setToolTipText("");
        tf_FiltroSerie.setEnabled(false);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("a");

        tf_FiltroNumeroInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_FiltroNumeroInicial.setToolTipText("");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Número");
        jLabel33.setToolTipText("");

        jcbx_FiltroSituacaoNFe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "Assinada", "Autorizada", "Cancelada", "Denegada", "Em Digitação", "Em Processamento na SEFAZ", "Rejeitada", "Pendente Conciliação", "Validada" }));
        jcbx_FiltroSituacaoNFe.setToolTipText("");
        jcbx_FiltroSituacaoNFe.setEnabled(false);

        botao_NovaPesquisa.setMnemonic('V');
        botao_NovaPesquisa.setText("Nova Pesquisa");
        botao_NovaPesquisa.setToolTipText("");
        botao_NovaPesquisa.setPreferredSize(new java.awt.Dimension(73, 23));
        botao_NovaPesquisa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_NovaPesquisaMouseMoved(evt);
            }
        });
        botao_NovaPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_NovaPesquisaActionPerformed(evt);
            }
        });

        jCheck_FiltroDanfeImpresso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheck_FiltroDanfeImpresso.setText("c/ DANFE impresso");
        jCheck_FiltroDanfeImpresso.setEnabled(false);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("Chave de Acesso NF-e");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("UF Destinatário");
        jLabel30.setToolTipText("");

        jcbx_FiltroUFdestinatario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " ", " ", " ", " " }));
        jcbx_FiltroUFdestinatario.setToolTipText("");
        jcbx_FiltroUFdestinatario.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Tipo de NF-e");
        jLabel28.setToolTipText("");

        jcbx_FiltroTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Normal", "Contingência FS-IA", "Contingência com SCAN", "Contingência via EPEC", "Contingência via DPEC", "Contingência FS-DA", "Contingência SVC-AN", "Contingência SVC-RS" }));
        jcbx_FiltroTipo.setToolTipText("");
        jcbx_FiltroTipo.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(10, 10, 10)
                                .addComponent(jDC_FiltroDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jDC_FiltroDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_FiltroChavedeAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jcbx_FiltroSituacaoNFe, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_FiltrocnpjcpfDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbx_FiltroUFdestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jCheck_FiltroDanfeImpresso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbx_FiltroExportadaXML, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botao_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botao_NovaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_FiltroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_FiltroNumeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tf_FiltroNumeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbx_FiltroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDC_FiltroDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDC_FiltroDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_FiltroChavedeAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_FiltroSituacaoNFe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_FiltrocnpjcpfDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbx_FiltroUFdestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_FiltroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_FiltroNumeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_FiltroNumeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbx_FiltroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheck_FiltroDanfeImpresso)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_FiltroExportadaXML, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_NovaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel18.setBackground(new java.awt.Color(223, 218, 218));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("NFe(s) Filtradas"));
        jPanel18.setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Número", "Série", "Data Emissão", "Data Autorização", "CNPJ/CPF do Destinatário", "UF Dest.", "Tipo", "Situação", "DANFE Impresso", "DANFE e Xml Enviados", "Título(s) Registrado(s)", "Boleto(s) Enviado(s)", "Xml Exp."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
            jTable1.getColumnModel().getColumn(10).setResizable(false);
            jTable1.getColumnModel().getColumn(11).setResizable(false);
            jTable1.getColumnModel().getColumn(12).setResizable(false);
        }

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
        );

        desktopPane.setLayer(jPanel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jPanel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGap(9, 9, 9))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("Gerenciar", desktopPane);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        jLb_Ide_cNF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_Ide_cNF.setText("Código Numérico");

        tf_Ide_cNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_cNF.setToolTipText("");
        tf_Ide_cNF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_cNF.setDoubleBuffered(true);
        tf_Ide_cNF.setEnabled(false);

        tf_Ide_cDV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_cDV.setToolTipText("");
        tf_Ide_cDV.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_cDV.setDoubleBuffered(true);
        tf_Ide_cDV.setEnabled(false);

        jLb_Ide_cDV.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_Ide_cDV.setText("DV");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Modelo");

        tf_Ide_Mod.setBackground(new java.awt.Color(236, 233, 216));
        tf_Ide_Mod.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Ide_Mod.setToolTipText("");
        tf_Ide_Mod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_Mod.setDoubleBuffered(true);
        tf_Ide_Mod.setEnabled(false);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("* Série");

        tf_Ide_Serie.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_Serie.setToolTipText("");
        tf_Ide_Serie.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_Serie.setDoubleBuffered(true);
        tf_Ide_Serie.setEnabled(false);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("* Número");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("* Data e Hora de emissão");

        tf_Ide_DhSaiEnt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_DhSaiEnt.setToolTipText("");
        tf_Ide_DhSaiEnt.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_DhSaiEnt.setDoubleBuffered(true);
        tf_Ide_DhSaiEnt.setEnabled(false);

        tf_Ide_DhEmi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_DhEmi.setToolTipText("");
        tf_Ide_DhEmi.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_DhEmi.setDoubleBuffered(true);
        tf_Ide_DhEmi.setEnabled(false);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("* Tipo Impressão");

        jcbx_Ide_tpEmis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Normal", "2 - Contingência FS-IA", "3 - Contingência via EPEC", "4 - Contingência via DPEC", "5 - Contingência FS-DA", "6 - Contingência SVC-AN", "7 - Contingência SVC-RS" }));
        jcbx_Ide_tpEmis.setToolTipText("");
        jcbx_Ide_tpEmis.setEnabled(false);
        jcbx_Ide_tpEmis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_Ide_tpEmisActionPerformed(evt);
            }
        });

        tf_Ide_NNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_NNF.setToolTipText("");
        tf_Ide_NNF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_NNF.setEnabled(false);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Data e Hora de saída/entrada");

        jcbx_Ide_tpNF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Entrada", "1 - Saída" }));
        jcbx_Ide_tpNF.setToolTipText("");
        jcbx_Ide_tpNF.setEnabled(false);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("* Forma de emissão");

        jcbx_Ide_finNFe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - NF-e normal", "2 - NF-e complementar", "3 - NF-e de ajuste", "4 - Devolução de mercadoria", "5 - Nota de crédito", "6 - Nota de débito" }));
        jcbx_Ide_finNFe.setToolTipText("");
        jcbx_Ide_finNFe.setEnabled(false);
        jcbx_Ide_finNFe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_Ide_finNFeActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("* Finalidade de Emissão");

        jcbx_Ide_tpImp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Retrato", "2 - Paisagem" }));
        jcbx_Ide_tpImp.setToolTipText("");
        jcbx_Ide_tpImp.setEnabled(false);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("* Tipo do documento");

        jcbx_Ide_idDest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Operacao Interna", "2 - Operacao Interestadual", "3 - Operacao com Exterior" }));
        jcbx_Ide_idDest.setToolTipText("");
        jcbx_Ide_idDest.setEnabled(false);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("* Consumidor Final");

        jcbx_Ide_indFinal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Não", "1 - Sim" }));
        jcbx_Ide_indFinal.setToolTipText("");
        jcbx_Ide_indFinal.setEnabled(false);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("* Destino da operação");

        jcbx_Ide_indPres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Não se aplica", "1 - Operacao Presencial", "2 - Operacao NÃO Presencial, pela Internet", "3 - Operacao NÃO Presencial, Teleatendimento", "4 - NFC-e em Operação com entrega a domicílio", "5 - Operação Presencial, fora do estabelecimento", "9 - Operacao NÃO Presencial, outros" }));
        jcbx_Ide_indPres.setToolTipText("");
        jcbx_Ide_indPres.setEnabled(false);
        jcbx_Ide_indPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_Ide_indPresActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("* Tipo Atendimento");

        tf_Ide_natOp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Ide_natOp.setToolTipText("");
        tf_Ide_natOp.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_natOp.setDoubleBuffered(true);
        tf_Ide_natOp.setEnabled(false);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setText("* Natureza da operação");

        jCheck_InformaCodigoNumerico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheck_InformaCodigoNumerico.setText("Informar Código Numérico");
        jCheck_InformaCodigoNumerico.setEnabled(false);

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setText("* Tipo de Nota de Débito (Finalidade = 6)");

        jcbx_Ide_tpNFDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Não se aplica", "01 - Transferência de créditos para Cooperativas", "02 - Anulação de Crédito por Saídas Imunes/Isentas", "03 - Débitos de notas fiscais não processadas na apuração", "04 - Multa e juros", "05 - Transferência de crédito de sucessão", "06 - Pagamento antecipado", "07 - Perda em estoque" }));
        jcbx_Ide_tpNFDebito.setToolTipText("");
        jcbx_Ide_tpNFDebito.setEnabled(false);

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel151.setText("* Tipo de Nota de Crédito (Finalidade = 5)");

        jcbx_Ide_tpNFCredito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Não se aplica", "01 - Multa e juros", "02 - Apropriação de crédito presumido de IBS sobre o saldo devedor na ZFM", "03 - Retorno" }));
        jcbx_Ide_tpNFCredito.setToolTipText("");
        jcbx_Ide_tpNFCredito.setEnabled(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Ide_Mod, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Ide_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Ide_NNF, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Ide_DhEmi, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheck_InformaCodigoNumerico, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLb_Ide_cNF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Ide_cNF, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Ide_cDV, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(jLb_Ide_cDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbx_Ide_tpNF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_Ide_DhSaiEnt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbx_Ide_tpEmis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_Ide_finNFe, 0, 1, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_Ide_tpNFDebito, 0, 1, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbx_Ide_tpNFCredito, 0, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jcbx_Ide_tpImp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jcbx_Ide_indFinal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jcbx_Ide_idDest, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbx_Ide_indPres, 0, 1, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_Ide_natOp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tf_Ide_Mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tf_Ide_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tf_Ide_NNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Ide_DhEmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheck_InformaCodigoNumerico)))
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLb_Ide_cNF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tf_Ide_cNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tf_Ide_cDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLb_Ide_cDV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_Ide_tpNFCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Ide_DhSaiEnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_Ide_tpNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbx_Ide_indPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_Ide_natOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jcbx_Ide_tpImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jcbx_Ide_tpEmis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbx_Ide_finNFe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbx_Ide_tpNFDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbx_Ide_idDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbx_Ide_indFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(723, 50));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setText("Valor total ");

        tf_ide_vNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ide_vNF.setToolTipText("");
        tf_ide_vNF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ide_vNF.setEnabled(false);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setText("Valor ICMS");

        tf_ide_vICMS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ide_vICMS.setToolTipText("");
        tf_ide_vICMS.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ide_vICMS.setEnabled(false);

        tf_ide_vST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ide_vST.setToolTipText("");
        tf_ide_vST.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ide_vST.setEnabled(false);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setText("Valor ICMS ST");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ide_vNF)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ide_vICMS)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ide_vST)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(tf_ide_vNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_ide_vST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ide_vICMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
        );

        jP_HitoricosEventos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Históricos de Eventos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N
        jP_HitoricosEventos.setPreferredSize(new java.awt.Dimension(944, 80));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Protocolo/Recibo", "Data/Hora", "Evento", "Sequência", "Correcao", "Justificativa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable5.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable5.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
            jTable5.getColumnModel().getColumn(2).setResizable(false);
            jTable5.getColumnModel().getColumn(3).setResizable(false);
            jTable5.getColumnModel().getColumn(4).setResizable(false);
            jTable5.getColumnModel().getColumn(5).setResizable(false);
        }

        botao_detalharevento.setText("Detalhar");
        botao_detalharevento.setToolTipText("");
        botao_detalharevento.setAutoscrolls(true);
        botao_detalharevento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_detalharevento.setInheritsPopupMenu(true);
        botao_detalharevento.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_detalharevento.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_detalhareventoMouseMoved(evt);
            }
        });
        botao_detalharevento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_detalhareventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_HitoricosEventosLayout = new javax.swing.GroupLayout(jP_HitoricosEventos);
        jP_HitoricosEventos.setLayout(jP_HitoricosEventosLayout);
        jP_HitoricosEventosLayout.setHorizontalGroup(
            jP_HitoricosEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13)
            .addGroup(jP_HitoricosEventosLayout.createSequentialGroup()
                .addComponent(botao_detalharevento, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jP_HitoricosEventosLayout.setVerticalGroup(
            jP_HitoricosEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_HitoricosEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_detalharevento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jP_Contigencia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contingência", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("* Data e Hora de Entrada");

        tf_Ide_DhCont.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Ide_DhCont.setToolTipText("");
        tf_Ide_DhCont.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_DhCont.setDoubleBuffered(true);
        tf_Ide_DhCont.setEnabled(false);

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setText("*Justificativa");

        tf_Ide_xjust.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Ide_xjust.setToolTipText("");
        tf_Ide_xjust.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_xjust.setDoubleBuffered(true);
        tf_Ide_xjust.setEnabled(false);

        javax.swing.GroupLayout jP_ContigenciaLayout = new javax.swing.GroupLayout(jP_Contigencia);
        jP_Contigencia.setLayout(jP_ContigenciaLayout);
        jP_ContigenciaLayout.setHorizontalGroup(
            jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_ContigenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_Ide_DhCont)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_Ide_xjust)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );
        jP_ContigenciaLayout.setVerticalGroup(
            jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_ContigenciaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel84))
                .addGap(1, 1, 1)
                .addGroup(jP_ContigenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Ide_DhCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Ide_xjust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setText("* Município de ocorrência");

        tf_Ide_xMunFG.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Ide_xMunFG.setToolTipText("");
        tf_Ide_xMunFG.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Ide_xMunFG.setDoubleBuffered(true);
        tf_Ide_xMunFG.setEnabled(false);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("* UF");

        jcbx_Ide_xUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " ", " ", " ", " " }));
        jcbx_Ide_xUF.setToolTipText("");
        jcbx_Ide_xUF.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_HitoricosEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_Ide_xUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Ide_xMunFG)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jP_Contigencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jP_Contigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_Ide_xMunFG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbx_Ide_xUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jP_HitoricosEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("Dados da NF-e", jPanel1);

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Tipo", "Chave de acesso", "UF", "Emissão", "CNPJ", "Modelo", "Série", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable6.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable6.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(1).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setResizable(false);
            jTable6.getColumnModel().getColumn(3).setResizable(false);
            jTable6.getColumnModel().getColumn(4).setResizable(false);
            jTable6.getColumnModel().getColumn(4).setHeaderValue("Emissão");
            jTable6.getColumnModel().getColumn(5).setResizable(false);
            jTable6.getColumnModel().getColumn(5).setHeaderValue("CNPJ");
            jTable6.getColumnModel().getColumn(6).setResizable(false);
            jTable6.getColumnModel().getColumn(6).setHeaderValue("Modelo");
            jTable6.getColumnModel().getColumn(7).setResizable(false);
            jTable6.getColumnModel().getColumn(7).setHeaderValue("Série");
            jTable6.getColumnModel().getColumn(8).setResizable(false);
            jTable6.getColumnModel().getColumn(8).setHeaderValue("Número");
        }

        jButton24.setText("Incluir");
        jButton24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton24.setEnabled(false);
        jButton24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton24MouseMoved(evt);
            }
        });
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Excluir");
        jButton25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton25.setEnabled(false);
        jButton25.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton25MouseMoved(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Detalhar");
        jButton26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton26.setEnabled(false);
        jButton26.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton26MouseMoved(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 751, Short.MAX_VALUE))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Notas e Conhecimentos Fiscais Referenciados", jPanel39);

        jPanel22.setBackground(new java.awt.Color(223, 218, 218));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Identificação"));
        jPanel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel22.setDoubleBuffered(false);
        jPanel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPanel22.setOpaque(false);

        tf_Emit_CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Emit_CNPJ.setToolTipText("");
        tf_Emit_CNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Emit_CNPJ.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Emit_CNPJ.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nome Fantasia");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* Inscrisão Estadual");

        tf_EnderEmit_IE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_IE.setToolTipText("");
        tf_EnderEmit_IE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderEmit_IE.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_IE.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Insc.Est.do Subst.Tributário");

        tf_EnderEmit_IEST.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_IEST.setToolTipText("");
        tf_EnderEmit_IEST.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderEmit_IEST.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_IEST.setEnabled(false);

        tf_Emit_xFant.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Emit_xFant.setToolTipText("");
        tf_Emit_xFant.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Emit_xFant.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Emit_xFant.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* CNPJ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("* Razão Social");

        tf_Emit_xNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Emit_xNome.setToolTipText("");
        tf_Emit_xNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Emit_xNome.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Emit_xNome.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText(" Inscrisão Municipal");

        tf_EnderEmit_IM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_IM.setToolTipText("");
        tf_EnderEmit_IM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderEmit_IM.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_IM.setEnabled(false);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 153, 153));
        jLabel34.setText("CNAE ");

        tf_EnderEmit_CNAE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_CNAE.setToolTipText("");
        tf_EnderEmit_CNAE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderEmit_CNAE.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_CNAE.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("* Regime Tributário");

        jcbx_EnderEmit_CRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Regime Nacional", "2 - Regime Nacional - execesso de sublimite de receita bruta", "3 - Regime Normal" }));
        jcbx_EnderEmit_CRT.setEnabled(false);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(tf_EnderEmit_IM)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_EnderEmit_CNAE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_Emit_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbx_EnderEmit_CRT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_EnderEmit_IE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_EnderEmit_IEST, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(tf_Emit_xNome))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(tf_Emit_xFant)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Emit_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Emit_xNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Emit_xFant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_IE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_EnderEmit_IEST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_IM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_EnderEmit_CNAE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderEmit_CRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Fone");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("* Logradouro");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("* CEP");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("País");

        tf_EnderEmit_CEP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderEmit_CEP.setToolTipText("");
        tf_EnderEmit_CEP.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_CEP.setEnabled(false);

        tf_EnderEmit_xLgr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_xLgr.setToolTipText("");
        tf_EnderEmit_xLgr.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_xLgr.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Complemento");

        tf_EnderEmit_fone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderEmit_fone.setToolTipText("");
        tf_EnderEmit_fone.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_fone.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("* UF");

        tf_EnderEmit_nro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderEmit_nro.setToolTipText("");
        tf_EnderEmit_nro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_nro.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("* Número");

        tf_EnderEmit_xBairro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_xBairro.setToolTipText("");
        tf_EnderEmit_xBairro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_xBairro.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("* Bairro");

        tf_EnderEmit_xCpl.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderEmit_xCpl.setToolTipText("");
        tf_EnderEmit_xCpl.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderEmit_xCpl.setEnabled(false);

        jcbx_EnderEmit_xMun.setEnabled(false);

        jcbx_EnderEmit_UF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jcbx_EnderEmit_UF.setEnabled(false);

        jcbx_EnderEmit_xPais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Brasil" }));
        jcbx_EnderEmit_xPais.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("* Município");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(jcbx_EnderEmit_xMun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_EnderEmit_fone, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jcbx_EnderEmit_xPais, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jcbx_EnderEmit_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tf_EnderEmit_xCpl)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(tf_EnderEmit_xBairro))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_EnderEmit_xLgr)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_EnderEmit_CEP, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(tf_EnderEmit_nro))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_xLgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_EnderEmit_nro)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_xCpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_xBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_EnderEmit_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderEmit_xPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderEmit_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderEmit_xMun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderEmit_fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Emitente", jPanel3);

        jPanel23.setBackground(new java.awt.Color(223, 218, 218));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Identificação"));
        jPanel23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel23.setDoubleBuffered(false);
        jPanel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPanel23.setOpaque(false);
        jPanel23.setPreferredSize(new java.awt.Dimension(902, 160));

        jcbx_Dest_tpCNPJCPF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CNPJ", "CPF" }));
        jcbx_Dest_tpCNPJCPF.setToolTipText("Selecione o Segmento");
        jcbx_Dest_tpCNPJCPF.setEnabled(false);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("* Tipo de documento");

        tf_Dest_CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Dest_CNPJ.setToolTipText("");
        tf_Dest_CNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Dest_CNPJ.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Dest_CNPJ.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText(" * CNPJ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Inscrição Estadual");

        jcbx_EnderDest_IE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jcbx_EnderDest_IE.setToolTipText("");
        jcbx_EnderDest_IE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcbx_EnderDest_IE.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        jcbx_EnderDest_IE.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Inscrisão SUFRAMA");

        tf_EnderDest_ISUF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderDest_ISUF.setToolTipText("");
        tf_EnderDest_ISUF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderDest_ISUF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_ISUF.setEnabled(false);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(153, 153, 153));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("E-mail");

        tf_EnderDest_email.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderDest_email.setToolTipText("Entre com o Email comercial");
        tf_EnderDest_email.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_email.setEnabled(false);

        botao_validar8.setText("Consultar Cadastro");
        botao_validar8.setToolTipText("");
        botao_validar8.setAutoscrolls(true);
        botao_validar8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar8.setInheritsPopupMenu(true);
        botao_validar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar8ActionPerformed(evt);
            }
        });

        botao_validar14.setText("Pesquisar");
        botao_validar14.setToolTipText("");
        botao_validar14.setAutoscrolls(true);
        botao_validar14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar14.setInheritsPopupMenu(true);
        botao_validar14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar14ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("* Razão Social/Nome");

        tf_Dest_xNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Dest_xNome.setToolTipText("");
        tf_Dest_xNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Dest_xNome.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Dest_xNome.setEnabled(false);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Tipo de contribuinte");

        jcbx_EnderDest_indIEDest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Contribuinte ICMS", "2 - Contribuinte ISENTO", "9 - Não Contribuinte" }));
        jcbx_EnderDest_indIEDest.setToolTipText("Selecione o Segmento");
        jcbx_EnderDest_indIEDest.setEnabled(false);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 153, 153));
        jLabel39.setText("Inscrição Municipal");

        tf_EnderDest_IM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderDest_IM.setToolTipText("");
        tf_EnderDest_IM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_EnderDest_IM.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_IM.setEnabled(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(5, 5, 5)
                        .addComponent(tf_EnderDest_email))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jcbx_Dest_tpCNPJCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_Dest_CNPJ)
                        .addGap(5, 5, 5)
                        .addComponent(botao_validar14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(botao_validar8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jcbx_EnderDest_indIEDest, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jcbx_EnderDest_IE, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(tf_EnderDest_ISUF)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_EnderDest_IM, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tf_Dest_xNome)))
                .addGap(0, 0, 0))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_validar14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_validar8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_Dest_tpCNPJCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Dest_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Dest_xNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_EnderDest_indIEDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbx_EnderDest_IE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_EnderDest_ISUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_EnderDest_IM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Fone");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("* Logradouro");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("* CEP");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("País");

        tf_EnderDest_CEP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderDest_CEP.setToolTipText("");
        tf_EnderDest_CEP.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_CEP.setEnabled(false);

        tf_EnderDest_xLgr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderDest_xLgr.setToolTipText("");
        tf_EnderDest_xLgr.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_xLgr.setEnabled(false);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("Complemento");

        tf_EnderDest_fone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderDest_fone.setToolTipText("");
        tf_EnderDest_fone.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_fone.setEnabled(false);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setText("* UF");

        tf_EnderDest_nro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_EnderDest_nro.setToolTipText("");
        tf_EnderDest_nro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_nro.setEnabled(false);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setText("* Número");

        tf_EnderDest_xBairro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderDest_xBairro.setToolTipText("");
        tf_EnderDest_xBairro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_xBairro.setEnabled(false);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setText("* Bairro");

        tf_EnderDest_xCpl.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_EnderDest_xCpl.setToolTipText("");
        tf_EnderDest_xCpl.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_EnderDest_xCpl.setEnabled(false);

        jcbx_EnderDest_xMun.setEnabled(false);

        jcbx_EnderDest_UF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jcbx_EnderDest_UF.setEnabled(false);

        jcbx_EnderDest_xPais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Brasil" }));
        jcbx_EnderDest_xPais.setEnabled(false);

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("* Município");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_EnderDest_xCpl, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_EnderDest_xBairro)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_EnderDest_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jcbx_EnderDest_xPais, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jcbx_EnderDest_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jcbx_EnderDest_xMun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_EnderDest_fone, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_EnderDest_xLgr, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_EnderDest_nro, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_xLgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_xCpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_xBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderDest_xPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderDest_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_EnderDest_xMun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_EnderDest_fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Local Retirada/Entrega", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 102, 255))); // NOI18N

        jCheck_localderetiradadiferentedoemitente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheck_localderetiradadiferentedoemitente.setForeground(new java.awt.Color(153, 153, 153));
        jCheck_localderetiradadiferentedoemitente.setText("Local de retirada diferente do emitente");

        botao_validar15.setText("Endereço");
        botao_validar15.setToolTipText("");
        botao_validar15.setAutoscrolls(true);
        botao_validar15.setEnabled(false);
        botao_validar15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar15.setInheritsPopupMenu(true);
        botao_validar15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar15ActionPerformed(evt);
            }
        });

        jCheck_Localdeentregadiferentedodestinatario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheck_Localdeentregadiferentedodestinatario.setForeground(new java.awt.Color(153, 153, 153));
        jCheck_Localdeentregadiferentedodestinatario.setText("Local de entrega diferente do destinatário");

        botao_validar16.setText("Endereço");
        botao_validar16.setToolTipText("");
        botao_validar16.setAutoscrolls(true);
        botao_validar16.setEnabled(false);
        botao_validar16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar16.setInheritsPopupMenu(true);
        botao_validar16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jCheck_localderetiradadiferentedoemitente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_validar15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheck_Localdeentregadiferentedodestinatario)
                .addGap(18, 18, 18)
                .addComponent(botao_validar16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheck_localderetiradadiferentedoemitente)
                .addComponent(botao_validar15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheck_Localdeentregadiferentedodestinatario)
                .addComponent(botao_validar16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Destinatário/Remetente", jPanel4);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos e Serviços da NF-e", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Código", "Descrição", "NCM", "CFOP", "Un", "Qtde.", "V. Unit.", "V. Total", "BS ICMS", "V. ICMS", "V. IPI", "Aliq. ICMS", "Aliq. IPI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable2.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(7).setResizable(false);
            jTable2.getColumnModel().getColumn(8).setResizable(false);
            jTable2.getColumnModel().getColumn(9).setResizable(false);
            jTable2.getColumnModel().getColumn(10).setResizable(false);
            jTable2.getColumnModel().getColumn(11).setResizable(false);
            jTable2.getColumnModel().getColumn(12).setResizable(false);
            jTable2.getColumnModel().getColumn(13).setResizable(false);
        }

        jButton15.setText("Incluir");
        jButton15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton15.setEnabled(false);
        jButton15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton15MouseMoved(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Excluir");
        jButton16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton16.setEnabled(false);
        jButton16.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton16MouseMoved(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Detalhar");
        jButton17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton17.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton17MouseMoved(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Produtos e Serviços", jPanel5);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(153, 153, 153));
        jLabel77.setText("Total dos Tributos¹");

        tf_ValorTotalNota1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ValorTotalNota1.setToolTipText("");
        tf_ValorTotalNota1.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ValorTotalNota1.setEnabled(false);

        jLabel78.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("<html> <table> <td color=\"blank\" align=left> <p>¹Valor aproximado total tributos federais, estaduais e<br>municipais conforme disposto na Lei nº 12.741/12. <br></p> </td> </table> </html>");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setText("* Base de cálculo");

        tf_ICMSTot_vBC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vBC.setToolTipText("");
        tf_ICMSTot_vBC.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vBC.setEnabled(false);

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setText("* Total de produtos e serviços");

        tf_ICMSTot_vProd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vProd.setToolTipText("");
        tf_ICMSTot_vProd.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vProd.setEnabled(false);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setText("* Total do II");

        tf_ICMSTot_vII.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vII.setToolTipText("");
        tf_ICMSTot_vII.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vII.setEnabled(false);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setText("* Total do seguro");

        tf_ICMSTot_vSeg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vSeg.setToolTipText("");
        tf_ICMSTot_vSeg.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vSeg.setEnabled(false);

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setText("* Total do ICMS");

        tf_ICMSTot_vICMS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vICMS.setToolTipText("");
        tf_ICMSTot_vICMS.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vICMS.setEnabled(false);

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setText("* Total do frete");

        tf_ICMSTot_vFrete.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vFrete.setToolTipText("");
        tf_ICMSTot_vFrete.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vFrete.setEnabled(false);

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setText("* Total do IPI");

        tf_ICMSTot_vIPI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vIPI.setToolTipText("");
        tf_ICMSTot_vIPI.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vIPI.setEnabled(false);

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setText("* Total do desconto");

        tf_ICMSTot_vDesc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vDesc.setToolTipText("");
        tf_ICMSTot_vDesc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vDesc.setEnabled(false);

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setText("* Total da nota");

        tf_ICMSTot_vNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vNF.setToolTipText("");
        tf_ICMSTot_vNF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vNF.setEnabled(false);

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setText("* Base de cálculo do ICMS ST");

        tf_ICMSTot_vBCST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vBCST.setToolTipText("");
        tf_ICMSTot_vBCST.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vBCST.setEnabled(false);

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(153, 153, 153));
        jLabel91.setText("* Total do ICMS partilha remetente");

        tf_ICMSTot_vPartilhaRemetente.setEditable(false);
        tf_ICMSTot_vPartilhaRemetente.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vPartilhaRemetente.setToolTipText("");
        tf_ICMSTot_vPartilhaRemetente.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vPartilhaRemetente.setEnabled(false);

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel92.setText("* PIS");

        tf_ICMSTot_vPIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vPIS.setToolTipText("");
        tf_ICMSTot_vPIS.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vPIS.setEnabled(false);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setText("* Outras despesas acessórias");

        tf_ICMSTot_vOutro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vOutro.setToolTipText("");
        tf_ICMSTot_vOutro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vOutro.setEnabled(false);

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setText("* Total do ICMS ST");

        tf_ICMSTot_vST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vST.setToolTipText("");
        tf_ICMSTot_vST.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vST.setEnabled(false);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(153, 153, 153));
        jLabel95.setText("* Total do ICMS partilha destinatário");

        tf_ICMSTot_vPartilhaDestinatario.setEditable(false);
        tf_ICMSTot_vPartilhaDestinatario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vPartilhaDestinatario.setToolTipText("");
        tf_ICMSTot_vPartilhaDestinatario.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vPartilhaDestinatario.setEnabled(false);

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setText("* COFINS");

        tf_ICMSTot_vCOFINS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vCOFINS.setToolTipText("");
        tf_ICMSTot_vCOFINS.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vCOFINS.setEnabled(false);

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setText("* Total do ICMS Desonerado");

        tf_ICMSTot_vICMSDeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vICMSDeson.setToolTipText("");
        tf_ICMSTot_vICMSDeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vICMSDeson.setEnabled(false);

        botao_validar17.setText("Calcular");
        botao_validar17.setToolTipText("");
        botao_validar17.setAutoscrolls(true);
        botao_validar17.setEnabled(false);
        botao_validar17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar17.setInheritsPopupMenu(true);
        botao_validar17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_validar17MouseMoved(evt);
            }
        });
        botao_validar17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar17ActionPerformed(evt);
            }
        });

        jLabel141.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(153, 153, 153));
        jLabel141.setText("* Total do ICMS FCP ");

        tf_ICMSTot_vFCP.setEditable(false);
        tf_ICMSTot_vFCP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vFCP.setToolTipText("");
        tf_ICMSTot_vFCP.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vFCP.setEnabled(false);

        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(153, 153, 153));
        jLabel142.setText("* Total do ICMS FCP UF destino");

        tf_ICMSTot_vICMSUFDest.setEditable(false);
        tf_ICMSTot_vICMSUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vICMSUFDest.setToolTipText("");
        tf_ICMSTot_vICMSUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vICMSUFDest.setEnabled(false);

        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(153, 153, 153));
        jLabel143.setText("* Total do ICMS-ST FCP");

        tf_ICMSTot_vFCPST.setEditable(false);
        tf_ICMSTot_vFCPST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vFCPST.setToolTipText("");
        tf_ICMSTot_vFCPST.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vFCPST.setEnabled(false);

        jLabel144.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(153, 153, 153));
        jLabel144.setText("* Total do ICMS-ST FCP Retido Anterior");

        tf_ICMSTot_vFCPSTRet.setEditable(false);
        tf_ICMSTot_vFCPSTRet.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vFCPSTRet.setToolTipText("");
        tf_ICMSTot_vFCPSTRet.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vFCPSTRet.setEnabled(false);

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(153, 153, 153));
        jLabel148.setText("* Total do IPI Devolvido");

        tf_ICMSTot_vIPIDevol.setEditable(false);
        tf_ICMSTot_vIPIDevol.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vIPIDevol.setToolTipText("");
        tf_ICMSTot_vIPIDevol.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vIPIDevol.setEnabled(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(tf_ICMSTot_vII, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_ICMSTot_vIPI, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(tf_ICMSTot_vPIS, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                .addGap(33, 33, 33)
                                .addComponent(tf_ICMSTot_vCOFINS, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vBC))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vICMS, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tf_ICMSTot_vBCST, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(10, 10, 10)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_ICMSTot_vST)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                        .addGap(5, 5, 5))
                                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_ICMSTot_vProd, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_ICMSTot_vFrete)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vPartilhaRemetente))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_ICMSTot_vPartilhaDestinatario)
                                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vSeg, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_ICMSTot_vDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_ICMSTot_vOutro, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vICMSDeson, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vFCP, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_ICMSTot_vFCPST, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jLabel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(tf_ICMSTot_vFCPSTRet)
                                        .addGap(8, 8, 8))
                                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel148, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ICMSTot_vIPIDevol, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
                        .addGap(5, 5, 5))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_ICMSTot_vICMSUFDest, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tf_ICMSTot_vNF, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(botao_validar17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(402, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ICMSTot_vBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_ICMSTot_vICMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ICMSTot_vBCST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ICMSTot_vProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_ICMSTot_vFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ICMSTot_vPartilhaRemetente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vPartilhaDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ICMSTot_vII, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_ICMSTot_vIPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ICMSTot_vPIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vCOFINS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ICMSTot_vSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_ICMSTot_vDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ICMSTot_vOutro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vICMSDeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ICMSTot_vFCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ICMSTot_vFCPSTRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_ICMSTot_vFCPST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ICMSTot_vIPIDevol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_validar17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_ICMSTot_vICMSUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ICMSTot_vNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jTabbedPane3.addTab("ICMS", jPanel20);

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(153, 153, 153));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText(" Base de cálculo do ISS");

        tf_ISSQNtot_vBC.setEditable(false);
        tf_ISSQNtot_vBC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vBC.setToolTipText("");
        tf_ISSQNtot_vBC.setEnabled(false);

        tf_ISSQNtot_vPIS.setEditable(false);
        tf_ISSQNtot_vPIS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vPIS.setToolTipText("");
        tf_ISSQNtot_vPIS.setEnabled(false);

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(153, 153, 153));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("PIS sobre serviços");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(153, 153, 153));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("Total dos serviços sob não-incidência ou não tributados pelo ICMS");

        tf_ISSQNtot_vServ.setEditable(false);
        tf_ISSQNtot_vServ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vServ.setToolTipText("");
        tf_ISSQNtot_vServ.setEnabled(false);

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(153, 153, 153));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Valor Total das Deduções");

        tf_ISSQNtot_vDeducao.setEditable(false);
        tf_ISSQNtot_vDeducao.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vDeducao.setToolTipText("");
        tf_ISSQNtot_vDeducao.setEnabled(false);

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(153, 153, 153));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Valor Total Descontos Incondicionado");

        tf_ISSQNtot_vDescIncond.setEditable(false);
        tf_ISSQNtot_vDescIncond.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vDescIncond.setToolTipText("");
        tf_ISSQNtot_vDescIncond.setEnabled(false);

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(153, 153, 153));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("Valor Total Descontos Condicionado");

        tf_ISSQNtot_vDescCond.setEditable(false);
        tf_ISSQNtot_vDescCond.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vDescCond.setToolTipText("");
        tf_ISSQNtot_vDescCond.setEnabled(false);

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(153, 153, 153));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("Valor Total Retenção ISS");

        tf_ISSQNtot_vISSRet.setEditable(false);
        tf_ISSQNtot_vISSRet.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vISSRet.setToolTipText("");
        tf_ISSQNtot_vISSRet.setEnabled(false);

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(153, 153, 153));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("Valor Total Outras Retenções");

        tf_ISSQNtot_vOutro.setEditable(false);
        tf_ISSQNtot_vOutro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vOutro.setToolTipText("");
        tf_ISSQNtot_vOutro.setEnabled(false);

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(153, 153, 153));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel107.setText("Regime especial de tributação");

        tf_ISSQNtot_vCOFINS.setEditable(false);
        tf_ISSQNtot_vCOFINS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vCOFINS.setToolTipText("");
        tf_ISSQNtot_vCOFINS.setEnabled(false);

        tf_ISSQNtot_vISS.setEditable(false);
        tf_ISSQNtot_vISS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ISSQNtot_vISS.setToolTipText("");
        tf_ISSQNtot_vISS.setEnabled(false);

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(153, 153, 153));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText(" Total ISS");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(153, 153, 153));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Data da prestação de serviço");
        jLabel109.setToolTipText("");

        jDC_ISSQNtot_dCompet.setToolTipText("");
        jDC_ISSQNtot_dCompet.setEnabled(false);

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(153, 153, 153));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("COFINS sobre serviços");

        jcbx_ISSQNtot_cRegTrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Microempresa Municipal", "Estimativa", "Sociedade de Profissionais", "Cooperativa", "Microempresário individual(MEI)", "Microempresário(MEI) e Empresa de Pequeno Porte(EPP)", " " }));
        jcbx_ISSQNtot_cRegTrib.setToolTipText("Selecione o Segmento");
        jcbx_ISSQNtot_cRegTrib.setEnabled(false);
        jcbx_ISSQNtot_cRegTrib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_ISSQNtot_cRegTribActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(tf_ISSQNtot_vPIS, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(tf_ISSQNtot_vCOFINS))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(tf_ISSQNtot_vBC, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(tf_ISSQNtot_vISS)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_ISSQNtot_cRegTrib, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDC_ISSQNtot_dCompet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(tf_ISSQNtot_vDescIncond, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(tf_ISSQNtot_vDescCond)
                        .addGap(471, 471, 471))
                    .addComponent(tf_ISSQNtot_vServ, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(tf_ISSQNtot_vDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(tf_ISSQNtot_vISSRet)
                        .addGap(164, 164, 164)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ISSQNtot_vOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel109)))
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ISSQNtot_vBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ISSQNtot_vISS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDC_ISSQNtot_dCompet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ISSQNtot_vPIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ISSQNtot_vCOFINS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbx_ISSQNtot_cRegTrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(tf_ISSQNtot_vServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(tf_ISSQNtot_vDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_ISSQNtot_vISSRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ISSQNtot_vOutro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ISSQNtot_vDescIncond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ISSQNtot_vDescCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane3.addTab("ISSQN", jPanel21);

        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totais do IBS da UF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel235.setText("Total do Crédito Presumido");

        tf_tot_ibs_uf_vcrespres.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vcrespres.setToolTipText("");
        tf_tot_ibs_uf_vcrespres.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vcrespres.setEnabled(false);

        jLabel236.setText("Total do Crédito Presumido C.Susp.");

        jLabel237.setText("Total do Diferimento");

        tf_tot_ibs_uf_vcredprescondsus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vcredprescondsus.setToolTipText("");
        tf_tot_ibs_uf_vcredprescondsus.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vcredprescondsus.setEnabled(false);
        tf_tot_ibs_uf_vcredprescondsus.setDocument(new ValidFieldNumeric());

        tf_tot_ibs_uf_vdif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vdif.setToolTipText("");
        tf_tot_ibs_uf_vdif.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vdif.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel239.setText("Total de Devolução de Tributos");

        tf_tot_ibs_uf_vdevtrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vdevtrib.setToolTipText("");
        tf_tot_ibs_uf_vdevtrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vdevtrib.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel240.setText("Total de Desoneração");

        tf_tot_ibs_uf_vdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vdeson.setToolTipText("");
        tf_tot_ibs_uf_vdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vdeson.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel241.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel241.setText("Total do IBS da UF");

        tf_tot_ibs_uf_vibs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_tot_ibs_uf_vibs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_uf_vibs.setToolTipText("");
        tf_tot_ibs_uf_vibs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_uf_vibs.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel237, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel236, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel235, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_tot_ibs_uf_vdif)
                    .addComponent(tf_tot_ibs_uf_vcrespres)
                    .addComponent(tf_tot_ibs_uf_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel241, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel240, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel239, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_tot_ibs_uf_vdevtrib, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_ibs_uf_vdeson, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_ibs_uf_vibs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(tf_tot_ibs_uf_vdevtrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_uf_vdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_uf_vibs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(tf_tot_ibs_uf_vcrespres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_uf_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_uf_vdif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());

        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totais do IBS do Município", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel242.setText("Total do Crédito Presumido");

        tf_tot_ibs_mun_vcrespres.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vcrespres.setToolTipText("");
        tf_tot_ibs_mun_vcrespres.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vcrespres.setEnabled(false);

        jLabel243.setText("Total de Devolução de Tributos");

        tf_tot_ibs_mun_vdevtrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vdevtrib.setToolTipText("");
        tf_tot_ibs_mun_vdevtrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vdevtrib.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        tf_tot_ibs_mun_vdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vdeson.setToolTipText("");
        tf_tot_ibs_mun_vdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vdeson.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel244.setText("Total de Desoneração");

        tf_tot_ibs_mun_vcredprescondsus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vcredprescondsus.setToolTipText("");
        tf_tot_ibs_mun_vcredprescondsus.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vcredprescondsus.setEnabled(false);
        tf_tot_ibs_uf_vcredprescondsus.setDocument(new ValidFieldNumeric());

        jLabel248.setText("Total do Crédito Presumido C.Susp.");

        jLabel249.setText("Total do Diferimento");

        tf_tot_ibs_mun_vdif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vdif.setToolTipText("");
        tf_tot_ibs_mun_vdif.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vdif.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel250.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel250.setText("Total do IBS do Município");

        tf_tot_ibs_mun_vibs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_tot_ibs_mun_vibs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_mun_vibs.setToolTipText("");
        tf_tot_ibs_mun_vibs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_mun_vibs.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel249, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel248, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel242, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_tot_ibs_mun_vdif)
                    .addComponent(tf_tot_ibs_mun_vcrespres)
                    .addComponent(tf_tot_ibs_mun_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel250, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel244, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel243, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_tot_ibs_mun_vdevtrib, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_ibs_mun_vdeson, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_ibs_mun_vibs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addComponent(tf_tot_ibs_mun_vdevtrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_mun_vdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_mun_vibs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel59Layout.createSequentialGroup()
                            .addComponent(tf_tot_ibs_mun_vcrespres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_tot_ibs_mun_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_tot_ibs_mun_vdif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel59Layout.createSequentialGroup()
                            .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel242, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());

        jLabel238.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel238.setText("Total da Base de Cálculo do IBS e da CBS");

        tf_tot_vbcibscbs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_vbcibscbs.setToolTipText("");
        tf_tot_vbcibscbs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_vbcibscbs.setEnabled(false);

        jLabel251.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel251.setText("Total do IBS");

        tf_tot_ibs_vibs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_tot_ibs_vibs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_vibs.setToolTipText("");
        tf_tot_ibs_vibs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_vibs.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel245.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel245.setText("T.Crédito Presumido");

        tf_tot_ibs_vcrespres.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_vcrespres.setToolTipText("");
        tf_tot_ibs_vcrespres.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_vcrespres.setEnabled(false);

        tf_tot_ibs_vcredprescondsus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_ibs_vcredprescondsus.setToolTipText("");
        tf_tot_ibs_vcredprescondsus.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_ibs_vcredprescondsus.setEnabled(false);

        jLabel246.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel246.setText("T. Crédito Presumido C.S");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel238, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_vbcibscbs, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel245)
                        .addGap(13, 13, 13)
                        .addComponent(tf_tot_ibs_vcrespres, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel246, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel251, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_tot_ibs_vibs, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel238, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_tot_vbcibscbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_tot_ibs_vibs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_tot_ibs_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_tot_ibs_vcrespres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());
        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());
        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());

        jTabbedPane3.addTab("IBS", jPanel43);

        jLabel258.setText("Total da Base de Cálculo do IS");

        tf_tot_is_vbcsel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_is_vbcsel.setToolTipText("");
        tf_tot_is_vbcsel.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_is_vbcsel.setEnabled(false);

        jLabel259.setText("Total do IS");

        tf_tot_is_vimpsel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_is_vimpsel.setToolTipText("");
        tf_tot_is_vimpsel.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_is_vimpsel.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel258)
                    .addComponent(jLabel259, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_tot_is_vimpsel)
                    .addComponent(tf_tot_is_vbcsel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_is_vbcsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tot_is_vimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(230, Short.MAX_VALUE))
        );

        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());

        jTabbedPane3.addTab("IS", jPanel46);

        jLabel252.setText("Total do Crédito Presumido");

        tf_tot_cbs_vcrespres.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vcrespres.setToolTipText("");
        tf_tot_cbs_vcrespres.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vcrespres.setEnabled(false);

        jLabel253.setText("Total do Crédito Presumido C.Susp.");

        tf_tot_cbs_vcredprescondsus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vcredprescondsus.setToolTipText("");
        tf_tot_cbs_vcredprescondsus.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vcredprescondsus.setEnabled(false);
        tf_tot_ibs_uf_vcredprescondsus.setDocument(new ValidFieldNumeric());

        jLabel254.setText("Total de Devolução de Tributos");

        tf_tot_cbs_vdevtrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vdevtrib.setToolTipText("");
        tf_tot_cbs_vdevtrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vdevtrib.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        tf_tot_cbs_vdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vdeson.setToolTipText("");
        tf_tot_cbs_vdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vdeson.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel255.setText("Total de Desoneração");

        jLabel256.setText("Total do Diferimento");

        tf_tot_cbs_vdif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vdif.setToolTipText("");
        tf_tot_cbs_vdif.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vdif.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel257.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel257.setText("Total da CBS");

        tf_tot_cbs_vcbs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_tot_cbs_vcbs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vcbs.setToolTipText("");
        tf_tot_cbs_vcbs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vcbs.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel260.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel260.setText("Total da NF-e com IBS / CBS / IS");

        tf_tot_vtotnf.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_vtotnf.setToolTipText("");
        tf_tot_vtotnf.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_vtotnf.setEnabled(false);
        tf_tot_ibs_uf_vdif.setDocument(new ValidFieldNumeric());

        jLabel261.setText("Total da Base de Cálculo do CBS");

        tf_tot_cbs_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_tot_cbs_vbc.setToolTipText("");
        tf_tot_cbs_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_tot_cbs_vbc.setEnabled(false);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel253, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel256, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel254, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel255, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel257, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel260, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf_tot_vtotnf, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tf_tot_cbs_vcbs, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_tot_cbs_vdeson)
                                    .addComponent(tf_tot_cbs_vdevtrib)
                                    .addComponent(tf_tot_cbs_vdif)
                                    .addComponent(tf_tot_cbs_vcrespres)
                                    .addComponent(tf_tot_cbs_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel261, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_tot_cbs_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(498, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel261, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_cbs_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_cbs_vcrespres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_cbs_vcredprescondsus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_tot_cbs_vdif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tot_cbs_vdevtrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tot_cbs_vdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tot_cbs_vcbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel257, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tot_vtotnf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel260, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());
        tf_tot_ibs_uf_vcrespres.setDocument(new ValidFieldNumeric());

        jTabbedPane3.addTab("CBS", jPanel44);

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(153, 153, 153));
        jLabel111.setText("Valor Retido de PIS");

        tf_RetTrib_vBC.setEditable(false);
        tf_RetTrib_vBC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vBC.setToolTipText("");
        tf_RetTrib_vBC.setEnabled(false);

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(153, 153, 153));
        jLabel112.setText("Base de cálculo do IRRF");

        tf_RetTrib_vBCIRRF.setEditable(false);
        tf_RetTrib_vBCIRRF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vBCIRRF.setToolTipText("");
        tf_RetTrib_vBCIRRF.setEnabled(false);

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(153, 153, 153));
        jLabel113.setText("BC da Retenção da Prev. Social");

        tf_RetTrib_vBCRetPrev.setEditable(false);
        tf_RetTrib_vBCRetPrev.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vBCRetPrev.setToolTipText("");
        tf_RetTrib_vBCRetPrev.setEnabled(false);

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(153, 153, 153));
        jLabel114.setText("Valor retido de COFINS");

        tf_RetTrib_vRetCOFINS.setEditable(false);
        tf_RetTrib_vRetCOFINS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vRetCOFINS.setToolTipText("");
        tf_RetTrib_vRetCOFINS.setEnabled(false);

        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(153, 153, 153));
        jLabel115.setText("Valor retido do IRRF");

        tf_RetTrib_vIRRF.setEditable(false);
        tf_RetTrib_vIRRF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vIRRF.setToolTipText("");
        tf_RetTrib_vIRRF.setEnabled(false);

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(153, 153, 153));
        jLabel116.setText("Retenção da Prev. Social");

        tf_RetTrib_vRetPrev.setEditable(false);
        tf_RetTrib_vRetPrev.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vRetPrev.setToolTipText("");
        tf_RetTrib_vRetPrev.setEnabled(false);

        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(153, 153, 153));
        jLabel117.setText("Valor retido de CSLL");

        tf_RetTrib_vRetCSLL.setEditable(false);
        tf_RetTrib_vRetCSLL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_RetTrib_vRetCSLL.setToolTipText("");
        tf_RetTrib_vRetCSLL.setEnabled(false);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(tf_RetTrib_vBC, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(tf_RetTrib_vRetCOFINS, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_RetTrib_vRetCSLL, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(tf_RetTrib_vBCIRRF, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(tf_RetTrib_vIRRF, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(tf_RetTrib_vBCRetPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(tf_RetTrib_vRetPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(468, 468, 468)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_RetTrib_vBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_RetTrib_vRetCOFINS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_RetTrib_vRetCSLL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_RetTrib_vBCIRRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_RetTrib_vIRRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_RetTrib_vBCRetPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_RetTrib_vRetPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane3.addTab("Retenção de Tributos", jPanel24);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf_ValorTotalNota1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_ValorTotalNota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        jTabbedPane1.addTab("Totais", jPanel6);

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("* Modalidade do frete");

        jcbx_Transp_modFrete.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Contratação do frete por conta do Remetente (CIF)", "1 - Contratação do frete por conta do Destinatário (FOB)", "2 - Contratação do frete por conta de Terceiros", "3 - Transporte próprio por conta do Remetente", "4 - Transporte próprio por conta do Destinatário", "9 - Sem frete" }));
        jcbx_Transp_modFrete.setToolTipText("Selecione o Segmento");
        jcbx_Transp_modFrete.setEnabled(false);
        jcbx_Transp_modFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_Transp_modFreteActionPerformed(evt);
            }
        });

        jPanel30.setBackground(new java.awt.Color(223, 218, 218));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Identificação"));
        jPanel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel30.setDoubleBuffered(false);
        jPanel30.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPanel30.setOpaque(false);

        jcbx_Transporta_tpCNPJCPF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "CNPJ", "CPF" }));
        jcbx_Transporta_tpCNPJCPF.setToolTipText("Selecione o Segmento");
        jcbx_Transporta_tpCNPJCPF.setEnabled(false);
        jcbx_Transporta_tpCNPJCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_Transporta_tpCNPJCPFActionPerformed(evt);
            }
        });

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("Tipo de documento");

        tf_Transporta_CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Transporta_CNPJ.setToolTipText("");
        tf_Transporta_CNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Transporta_CNPJ.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Transporta_CNPJ.setEnabled(false);

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setText(" * CNPJ");

        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel120.setText("* Inscrisão Estadual");

        tf_Transporta_IE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Transporta_IE.setToolTipText("");
        tf_Transporta_IE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Transporta_IE.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Transporta_IE.setEnabled(false);

        jchkbx_isentodoicms.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jchkbx_isentodoicms.setText("Isento do ICMS");
        jchkbx_isentodoicms.setEnabled(false);

        botao_validar18.setText("Pesquisar");
        botao_validar18.setToolTipText("");
        botao_validar18.setAutoscrolls(true);
        botao_validar18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar18.setInheritsPopupMenu(true);
        botao_validar18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_validar18MouseMoved(evt);
            }
        });
        botao_validar18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar18ActionPerformed(evt);
            }
        });

        botao_validar19.setText("Consultar Cadastro");
        botao_validar19.setToolTipText("");
        botao_validar19.setAutoscrolls(true);
        botao_validar19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao_validar19.setInheritsPopupMenu(true);
        botao_validar19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_validar19MouseMoved(evt);
            }
        });
        botao_validar19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validar19ActionPerformed(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel121.setText("* Razão Social");

        tf_Transporta_xNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Transporta_xNome.setToolTipText("");
        tf_Transporta_xNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Transporta_xNome.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Transporta_xNome.setEnabled(false);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jcbx_Transporta_tpCNPJCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tf_Transporta_CNPJ)
                .addGap(8, 8, 8)
                .addComponent(botao_validar18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(botao_validar19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(tf_Transporta_xNome))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(tf_Transporta_IE)
                .addGap(530, 530, 530))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jchkbx_isentodoicms, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_validar18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_validar19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx_Transporta_tpCNPJCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Transporta_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Transporta_xNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Transporta_IE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jchkbx_isentodoicms))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel123.setText("Logradouro");

        tf_Transporta_xEnder.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Transporta_xEnder.setToolTipText("");
        tf_Transporta_xEnder.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Transporta_xEnder.setEnabled(false);

        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel127.setText("* UF");

        jcbx_Transporta_xMun.setEnabled(false);

        jcbx_Transporta_UF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jcbx_Transporta_UF.setEnabled(false);

        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("Município");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Transporta_xEnder))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jcbx_Transporta_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_Transporta_xMun, 0, 701, Short.MAX_VALUE))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Transporta_xEnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_Transporta_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_Transporta_xMun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        jTabbedPane4.addTab("Transportador", jPanel26);

        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel122.setText("Base de cálculo");

        tf_RetTransp_vBCRet.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_RetTransp_vBCRet.setToolTipText("");
        tf_RetTransp_vBCRet.setEnabled(false);

        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel128.setText("Alíquota");

        tf_RetTransp_pICMSRet.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_RetTransp_pICMSRet.setToolTipText("");
        tf_RetTransp_pICMSRet.setEnabled(false);

        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel129.setText("Valor do Serviço");

        tf_RetTransp_vServ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_RetTransp_vServ.setToolTipText("");
        tf_RetTransp_vServ.setEnabled(false);

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel134.setText("* UF");

        jcbx_RetTransp_UF.setEnabled(false);

        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("Município");

        jcbx_RetTransp_cMunFG.setEnabled(false);

        jLabel136.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel136.setText("CFOP");

        jcbx_RetTransp_CFOP.setEnabled(false);

        jLabel137.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel137.setText("ICMS Retido");

        tf_RetTransp_vICMSRet.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_RetTransp_vICMSRet.setToolTipText("");
        tf_RetTransp_vICMSRet.setEnabled(false);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_RetTransp_vICMSRet, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jcbx_RetTransp_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jcbx_RetTransp_cMunFG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(104, 104, 104))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_RetTransp_vBCRet, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_RetTransp_pICMSRet, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbx_RetTransp_CFOP, 0, 164, Short.MAX_VALUE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(tf_RetTransp_vServ)))
                        .addGap(15, 15, 15))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_RetTransp_vBCRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_RetTransp_pICMSRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_RetTransp_vServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_RetTransp_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_RetTransp_cMunFG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbx_RetTransp_CFOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_RetTransp_vICMSRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane4.addTab("Retenção ICMS", jPanel27);

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículo/Reboque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel138.setText("* Placa");

        tf_TVeiculo_placa.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_TVeiculo_placa.setToolTipText("");
        tf_TVeiculo_placa.setEnabled(false);

        jLabel139.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel139.setText("* UF");

        jcbx_TVeiculo_UF.setEnabled(false);

        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel140.setText("RNTC");

        tf_TVeiculo_RNTC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_TVeiculo_RNTC.setToolTipText("");
        tf_TVeiculo_RNTC.setEnabled(false);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tf_TVeiculo_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jcbx_TVeiculo_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tf_TVeiculo_RNTC, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_TVeiculo_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_TVeiculo_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_TVeiculo_RNTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Veículo/Reboque/Balsa/Vagão", jPanel28);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Volume Transportado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Qtde. Vol.", "Espécie", "Marca", "Número Vol.", "Peso Liq.", "Peso Bruto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable3.setEnabled(false);
        jTable3.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable3.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setHeaderValue("Número Vol.");
            jTable3.getColumnModel().getColumn(5).setResizable(false);
            jTable3.getColumnModel().getColumn(5).setHeaderValue("Peso Liq.");
            jTable3.getColumnModel().getColumn(6).setResizable(false);
            jTable3.getColumnModel().getColumn(6).setHeaderValue("Peso Bruto");
        }

        jButton18.setText("Incluir");
        jButton18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton18.setEnabled(false);
        jButton18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton18MouseMoved(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Excluir");
        jButton19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton19.setEnabled(false);
        jButton19.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton19MouseMoved(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Alterar");
        jButton20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton20.setEnabled(false);
        jButton20.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton20MouseMoved(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Volume", jPanel29);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane4)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbx_Transp_modFrete, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_Transp_modFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Transporte", jPanel8);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("Fatura"));

        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setText("Número");

        tf_Fat_vOrig.setEditable(false);
        tf_Fat_vOrig.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Fat_vOrig.setToolTipText("");
        tf_Fat_vOrig.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Fat_vOrig.setEnabled(false);

        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("Valor Original");

        tf_Fat_nFat.setEditable(false);
        tf_Fat_nFat.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Fat_nFat.setToolTipText("");
        tf_Fat_nFat.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Fat_nFat.setEnabled(false);

        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Valor do desconto");
        jLabel132.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel132AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        tf_Fat_vDesc.setEditable(false);
        tf_Fat_vDesc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Fat_vDesc.setToolTipText("");
        tf_Fat_vDesc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Fat_vDesc.setEnabled(false);

        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel133.setText("Valor líquido");

        tf_Fat_vLiq.setEditable(false);
        tf_Fat_vLiq.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_Fat_vLiq.setToolTipText("");
        tf_Fat_vLiq.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Fat_vLiq.setEnabled(false);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Fat_nFat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_Fat_vOrig)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_Fat_vDesc)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_Fat_vLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Fat_nFat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Fat_vOrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_Fat_vDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_Fat_vLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Duplicatas (120 no Máximo)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "Número", "Data Vencimento", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable4.setEnabled(false);
        jTable4.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable4.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getColumnModel().getColumn(1).setResizable(false);
            jTable4.getColumnModel().getColumn(2).setResizable(false);
            jTable4.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton21.setText("Incluir");
        jButton21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton21.setEnabled(false);
        jButton21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton21MouseMoved(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Excluir");
        jButton22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton22.setEnabled(false);
        jButton22.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton22MouseMoved(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Alterar");
        jButton23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton23.setEnabled(false);
        jButton23.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton23MouseMoved(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cobrança", jPanel9);

        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Meio de pagamento", "Valor do pagamento", "Descrição da Forma de Pagamento", "Indicador da Forma de pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable7.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable7.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable7.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(0).setResizable(false);
            jTable7.getColumnModel().getColumn(1).setResizable(false);
            jTable7.getColumnModel().getColumn(2).setResizable(false);
            jTable7.getColumnModel().getColumn(3).setResizable(false);
            jTable7.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton27.setText("Incluir");
        jButton27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton27.setEnabled(false);
        jButton27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton27MouseMoved(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("Excluir");
        jButton28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton28.setEnabled(false);
        jButton28.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton28.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton28MouseMoved(evt);
            }
        });
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText("Detalhar");
        jButton29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton29.setEnabled(false);
        jButton29.setPreferredSize(new java.awt.Dimension(33, 19));
        jButton29.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton29MouseMoved(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 751, Short.MAX_VALUE))))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Informações de Pagamento", jPanel41);

        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setText("Informações adicionais de interesse do fisco");

        tf_InfAdic_infAdFisco.setEditable(false);
        tf_InfAdic_infAdFisco.setColumns(20);
        tf_InfAdic_infAdFisco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tf_InfAdic_infAdFisco.setLineWrap(true);
        tf_InfAdic_infAdFisco.setRows(5);
        tf_InfAdic_infAdFisco.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_InfAdic_infAdFisco.setEnabled(false);
        jScrollPane1.setViewportView(tf_InfAdic_infAdFisco);

        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setText("Informações complementares de interesse do contribuinte");

        tf_InfAdic_infCpl.setEditable(false);
        tf_InfAdic_infCpl.setColumns(20);
        tf_InfAdic_infCpl.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tf_InfAdic_infCpl.setLineWrap(true);
        tf_InfAdic_infCpl.setRows(5);
        tf_InfAdic_infCpl.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_InfAdic_infCpl.setEnabled(false);
        jScrollPane2.setViewportView(tf_InfAdic_infCpl);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 570, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informações Adicionais", jPanel10);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 977, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Exportações e Compras", jPanel14);

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Certificado Digital da NF-e"));

        tf_Signature_Assunto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Signature_Assunto.setToolTipText("");
        tf_Signature_Assunto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Signature_Assunto.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Signature_Assunto.setEnabled(false);

        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel145.setText("Assunto");

        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setText("CNPJ");

        tf_Signature_CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Signature_CNPJ.setToolTipText("");
        tf_Signature_CNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Signature_CNPJ.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Signature_CNPJ.setEnabled(false);

        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel147.setText("Emissor");

        tf_Signature_Emissor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Signature_Emissor.setToolTipText("");
        tf_Signature_Emissor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Signature_Emissor.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Signature_Emissor.setEnabled(false);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Signature_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Signature_Emissor, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_Signature_Assunto)))
                .addGap(12, 12, 12))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Signature_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Signature_Emissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Signature_Assunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("Validade do Certificado"));

        jLabel149.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel149.setText("Data Inicial");

        tf_Signature_DataInicial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Signature_DataInicial.setToolTipText("");
        tf_Signature_DataInicial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Signature_DataInicial.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Signature_DataInicial.setEnabled(false);

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel150.setText("Data Final");

        tf_Signature_DataFinal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Signature_DataFinal.setToolTipText("");
        tf_Signature_DataFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_Signature_DataFinal.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_Signature_DataFinal.setEnabled(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Signature_DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_Signature_DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Signature_DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Signature_DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Certificado Digital", jPanel11);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(748, Short.MAX_VALUE)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Autorização para DownLoad", jPanel12);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Versão XML");

        tf_InfNFe_Id.setEditable(false);
        tf_InfNFe_Id.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_InfNFe_Id.setToolTipText("");
        tf_InfNFe_Id.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Chave de Acesso");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Número");

        tf_InfNFe_Versao.setEditable(false);
        tf_InfNFe_Versao.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_InfNFe_Versao.setToolTipText("");
        tf_InfNFe_Versao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_InfNFe_Versao.setEnabled(false);

        botao_chave.setToolTipText("");
        botao_chave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_chave.setIcon(new javax.swing.ImageIcon("./icons/botao_chave.GIF"));
        botao_chave.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_chaveMouseMoved(evt);
            }
        });
        botao_chave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_chaveActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Status");
        jLabel25.setAutoscrolls(true);

        tf_InfNFe_PedidoVenda.setEditable(false);
        tf_InfNFe_PedidoVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_InfNFe_PedidoVenda.setToolTipText("");
        tf_InfNFe_PedidoVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        tf_Infnfe_statusnfe.setEditable(false);
        tf_Infnfe_statusnfe.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_Infnfe_statusnfe.setToolTipText("");
        tf_Infnfe_statusnfe.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_Infnfe_statusnfe.setEnabled(false);

        tf_InfNFe_NNF.setEditable(false);
        tf_InfNFe_NNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_InfNFe_NNF.setToolTipText("");
        tf_InfNFe_NNF.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 51, 51));
        jLabel76.setText("Os documentos eletrônicos gerados por esse aplicativo não tem validade jurídica.");
        jLabel76.setToolTipText("");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("(*) Campos de preenchimento obrigatório.");
        jLabel73.setToolTipText("");

        jDktInputData.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(tf_InfNFe_Id, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(tf_InfNFe_Versao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(botao_chave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(tf_InfNFe_PedidoVenda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(tf_Infnfe_statusnfe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(tf_InfNFe_NNF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel76, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDktInputData.setLayer(jLabel73, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDktInputDataLayout = new javax.swing.GroupLayout(jDktInputData);
        jDktInputData.setLayout(jDktInputDataLayout);
        jDktInputDataLayout.setHorizontalGroup(
            jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDktInputDataLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDktInputDataLayout.createSequentialGroup()
                        .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDktInputDataLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jDktInputDataLayout.createSequentialGroup()
                                .addComponent(tf_Infnfe_statusnfe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_InfNFe_PedidoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_InfNFe_Id)))
                        .addGap(10, 10, 10)
                        .addComponent(botao_chave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDktInputDataLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(55, 55, 55))
                            .addGroup(jDktInputDataLayout.createSequentialGroup()
                                .addComponent(tf_InfNFe_NNF)
                                .addGap(18, 18, 18)))
                        .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(tf_InfNFe_Versao, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jDktInputDataLayout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        jDktInputDataLayout.setVerticalGroup(
            jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDktInputDataLayout.createSequentialGroup()
                .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_Infnfe_statusnfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_InfNFe_PedidoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_InfNFe_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_chave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_InfNFe_NNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_InfNFe_Versao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        botao_enviarEmail.setText("Enviar E-mail");
        botao_enviarEmail.setToolTipText("<html>\n<table align=left>\n<td color=\"black\" align=left>\n1)Para enviar a NFe ao cliente:\n<br>&nbsp&nbsp;a) Exporte a NF-e autorizada no formato XML - clique em \"Exportar\" e selecione \"Arquivo XML\" como o tipo de arquivo a ser gerado, ou;</br>\n<br>&nbsp&nbsp;b) Encaminhe o PDF e XML gerado a partir da NF-e autorizada para o cliente - clique em \"Envia E-mail\".</br>\n</td>\n</html>");
        botao_enviarEmail.setAutoscrolls(true);
        botao_enviarEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_enviarEmail.setInheritsPopupMenu(true);
        botao_enviarEmail.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_enviarEmail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_enviarEmailMouseMoved(evt);
            }
        });
        botao_enviarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_enviarEmailActionPerformed(evt);
            }
        });

        botao_transmitir.setText("Transmitir");
        botao_transmitir.setToolTipText("");
        botao_transmitir.setAutoscrolls(true);
        botao_transmitir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_transmitir.setInheritsPopupMenu(true);
        botao_transmitir.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_transmitir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_transmitirMouseMoved(evt);
            }
        });
        botao_transmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_transmitirActionPerformed(evt);
            }
        });

        botao_exportar.setText("Exportar");
        botao_exportar.setToolTipText("<html>\n<table align=left>\n<td color=\"black\" align=left>\n1)Para enviar a NFe ao cliente:\n<br>&nbsp&nbsp;a) Exporte a NF-e autorizada no formato XML - clique em \"Exportar\" e selecione \"Arquivo XML\" como o tipo de arquivo a ser gerado, ou;</br>\n<br>&nbsp&nbsp;b) Encaminhe o PDF e XML gerado a partir da NF-e autorizada para o cliente - clique em \"Envia E-mail\".</br>\n</td>\n</html>");
        botao_exportar.setAutoscrolls(true);
        botao_exportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_exportar.setInheritsPopupMenu(true);
        botao_exportar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_exportar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_exportarMouseMoved(evt);
            }
        });
        botao_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_exportarActionPerformed(evt);
            }
        });

        botao_sair.setMnemonic('L');
        botao_sair.setText("Sair");
        botao_sair.setToolTipText("");
        botao_sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_sair.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_sairMouseMoved(evt);
            }
        });
        botao_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_sairActionPerformed(evt);
            }
        });

        botao_validar.setText("Validar e Assinar");
        botao_validar.setToolTipText("");
        botao_validar.setAutoscrolls(true);
        botao_validar.setFocusable(false);
        botao_validar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_validar.setInheritsPopupMenu(true);
        botao_validar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botao_validar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_validarMouseMoved(evt);
            }
        });
        botao_validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_validarActionPerformed(evt);
            }
        });

        botao_gravar.setText("Salvar");
        botao_gravar.setToolTipText("");
        botao_gravar.setEnabled(false);
        botao_gravar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_gravar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_gravar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_gravarMouseMoved(evt);
            }
        });
        botao_gravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_gravarActionPerformed(evt);
            }
        });

        botao_alterar.setText("Editar");
        botao_alterar.setToolTipText("");
        botao_alterar.setAutoscrolls(true);
        botao_alterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_alterar.setInheritsPopupMenu(true);
        botao_alterar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_alterar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_alterarMouseMoved(evt);
            }
        });
        botao_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_alterarActionPerformed(evt);
            }
        });

        botao_chavedeacesso.setText("Chave de Acesso");
        botao_chavedeacesso.setToolTipText("");
        botao_chavedeacesso.setAutoscrolls(true);
        botao_chavedeacesso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_chavedeacesso.setInheritsPopupMenu(true);
        botao_chavedeacesso.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_chavedeacesso.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_chavedeacessoMouseMoved(evt);
            }
        });
        botao_chavedeacesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_chavedeacessoActionPerformed(evt);
            }
        });

        botao_consultarecibo.setMnemonic('L');
        botao_consultarecibo.setText("Consultar Recibo");
        botao_consultarecibo.setToolTipText("");
        botao_consultarecibo.setEnabled(false);
        botao_consultarecibo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_consultarecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_consultareciboActionPerformed(evt);
            }
        });

        botao_imprimirdanfe.setText("Imprimir DANFE");
        botao_imprimirdanfe.setToolTipText("");
        botao_imprimirdanfe.setAutoscrolls(true);
        botao_imprimirdanfe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_imprimirdanfe.setInheritsPopupMenu(true);
        botao_imprimirdanfe.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_imprimirdanfe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_imprimirdanfeMouseMoved(evt);
            }
        });
        botao_imprimirdanfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_imprimirdanfeActionPerformed(evt);
            }
        });

        botao_cancelanfe.setText("Cancela NF-e");
        botao_cancelanfe.setToolTipText("");
        botao_cancelanfe.setAutoscrolls(true);
        botao_cancelanfe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_cancelanfe.setInheritsPopupMenu(true);
        botao_cancelanfe.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_cancelanfe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_cancelanfeMouseMoved(evt);
            }
        });
        botao_cancelanfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cancelanfeActionPerformed(evt);
            }
        });

        botao_cartacorrecao.setText("Carta Correção");
        botao_cartacorrecao.setToolTipText("");
        botao_cartacorrecao.setAutoscrolls(true);
        botao_cartacorrecao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_cartacorrecao.setInheritsPopupMenu(true);
        botao_cartacorrecao.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_cartacorrecao.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_cartacorrecaoMouseMoved(evt);
            }
        });
        botao_cartacorrecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cartacorrecaoActionPerformed(evt);
            }
        });

        botao_previsualizardanfe.setText("Pré-Visualizar DANFE");
        botao_previsualizardanfe.setToolTipText("");
        botao_previsualizardanfe.setAutoscrolls(true);
        botao_previsualizardanfe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_previsualizardanfe.setInheritsPopupMenu(true);
        botao_previsualizardanfe.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_previsualizardanfe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_previsualizardanfeMouseMoved(evt);
            }
        });
        botao_previsualizardanfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_previsualizardanfeActionPerformed(evt);
            }
        });

        botao_excluir.setText("Excluir");
        botao_excluir.setToolTipText("");
        botao_excluir.setEnabled(false);
        botao_excluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_excluirActionPerformed(evt);
            }
        });

        botao_consultanasefaz.setText("Consultar Situação");
        botao_consultanasefaz.setToolTipText("");
        botao_consultanasefaz.setAutoscrolls(true);
        botao_consultanasefaz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_consultanasefaz.setInheritsPopupMenu(true);
        botao_consultanasefaz.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_consultanasefaz.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_consultanasefazMouseMoved(evt);
            }
        });
        botao_consultanasefaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_consultanasefazActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        statusbar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText(" Status:");
        jLabel35.setToolTipText("");
        jLabel35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        botao_registrarTitulo.setText("Registar Título(s)");
        botao_registrarTitulo.setToolTipText("<html>\n<table align=left>\n<td color=\"black\" align=left>\n1)Para enviar a NFe ao cliente:\n<br>&nbsp&nbsp;a) Exporte a NF-e autorizada no formato XML - clique em \"Exportar\" e selecione \"Arquivo XML\" como o tipo de arquivo a ser gerado, ou;</br>\n<br>&nbsp&nbsp;b) Encaminhe o PDF e XML gerado a partir da NF-e autorizada para o cliente - clique em \"Envia E-mail\".</br>\n</td>\n</html>");
        botao_registrarTitulo.setAutoscrolls(true);
        botao_registrarTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_registrarTitulo.setInheritsPopupMenu(true);
        botao_registrarTitulo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        botao_registrarTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_registrarTituloMouseMoved(evt);
            }
        });
        botao_registrarTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_registrarTituloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDktInputData))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botao_gravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_exportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_cancelanfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botao_chavedeacesso, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_cartacorrecao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_consultarecibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botao_alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botao_validar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_transmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botao_previsualizardanfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_consultanasefaz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_imprimirdanfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(111, 111, 111)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botao_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_registrarTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao_enviarEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botao_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)))
                .addGap(10, 10, 10))
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(statusbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jDktInputData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botao_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_validar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_transmitir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_previsualizardanfe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_imprimirdanfe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_consultanasefaz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botao_enviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_registrarTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_gravar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_cancelanfe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_chavedeacesso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_cartacorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_consultarecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        setBounds(0, 0, 1029, 607);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_excluirActionPerformed
        int selection = JOptionPane.showConfirmDialog(this, "Deseja excluir registro ?", "Confirmação de Exclusão", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (selection == JOptionPane.OK_OPTION) {
            if (NfeController.Delete(this.tf_InfNFe_NNF.getText().trim())) {
                NfeCobrDupController.Delete(tf_InfNFe_NNF.getText());
                NfeDetProdController.Delete(tf_InfNFe_NNF.getText());
                NfeInfProtController.Delete(tf_InfNFe_NNF.getText());
                NfeInformacoesPagamentoController.Delete(tf_InfNFe_NNF.getText());
                NfeNfRefController.Delete(tf_InfNFe_NNF.getText());
                NfeTranspVolController.Delete(tf_InfNFe_NNF.getText());
                JOptionPane.showMessageDialog(this, "NFe excluído com sucesso");
            } else {
                JOptionPane.showMessageDialog(this, System.getProperty("MsgInvalid"));
            }
            state();
            PreencheTabelaGerenciamento();
            jjTable1MouseClicked();
        }
}//GEN-LAST:event_botao_excluirActionPerformed

    private void botao_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_PesquisarActionPerformed
        LimpaTela(); //31/01/2024
        state();     //31/01/2024
        MontaTabelaGerenciamento();
        //Inicio 31/01/2024
        if (jTable1.getRowCount() > 0) {
            try {
                tf_InfNFe_NNF.setText(jTable1.getValueAt(0, 0).toString().trim());
                JSONObject jsonNFe = new JSONObject();
                jsonNFe = NfeController.Index(this.tf_InfNFe_NNF.getText().trim());
                if (!jsonNFe.isEmpty()) {
                    LoadFM(jsonNFe);
                }
            } catch (ParseException ex) {
                returnParams(new String[]{ex.toString()});
            } catch (SQLException ex) {
                returnParams(new String[]{ex.toString()});
            }
        }
        //Fim 31/01/2024
        PesquisaTable(tf_InfNFe_NNF.getText());
    }//GEN-LAST:event_botao_PesquisarActionPerformed

    private void botao_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_alterarActionPerformed
        if (!tf_Infnfe_statusnfe.getText().trim().equals("Em Digitação")) {
            int selection = JOptionPane.showConfirmDialog(this, "A situação da Nota Fiscal voltará para 'Em Digitação'.\n"
                    + "Deseja continuar?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                try {
                    String wCodiAnt = tf_InfNFe_NNF.getText().trim();
                    NFE eNFE = new NFE();
                    eNFE.setIde_nnf(wCodiAnt);
                    eNFE.setInfnfe_id("");
                    eNFE.setIde_cdv(null);
                    eNFE.setInfnfe_statusnfe("Em Digitação");
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, System.getProperty("MsgInvalid"));
                    }
                    MontaTabelaGerenciamento();
                    //jjTable1MouseClicked();
                    tf_InfAdic_infCpl.setEditable(true);
                    tf_InfAdic_infCpl.setEnabled(true);
                    jcbx_Ide_tpEmis.setEnabled(true);
                    jcbx_Ide_finNFe.setEnabled(true); //20/06/2024
                    jcbx_Ide_tpNFDebito.setEnabled(false); //31/07/2025
                    jcbx_Ide_tpNFCredito.setEnabled(false); //31/07/2025
                    botao_gravar.setEnabled(true);
                    botao_validar.setEnabled(false);
                    jButton24.setEnabled(true);
                    jButton25.setEnabled(true);
                    jButton26.setEnabled(true);
                    tf_InfNFe_NNF.setText(wCodiAnt);
                    if (this.tf_InfNFe_NNF.getText().trim().length() > 0) {
                        JSONObject jsonNFe = NfeController.Index(this.tf_InfNFe_NNF.getText().trim());
                        if (!jsonNFe.isEmpty()) {
                            LoadFM(jsonNFe);
                        }
                        disableFields();
                        PesquisaTable(tf_InfNFe_NNF.getText());
                    }
                } catch (InstantiationException ex) {
                    returnParams(new String[]{ex.toString()});
                } catch (IllegalAccessException ex) {
                    returnParams(new String[]{ex.toString()});
                } catch (SQLException ex) {
                    returnParams(new String[]{ex.toString()});
                } catch (ParseException ex) {
                    returnParams(new String[]{ex.toString()});
                } catch (Exception ex) {
                    returnParams(new String[]{ex.toString()});
                }
            }
        } else {
            botao_gravar.setEnabled(true);
            jButton29.setEnabled(true);
            tf_ICMSTot_vPIS.setEnabled(true);
            tf_ICMSTot_vCOFINS.setEnabled(true);
            jButton24.setEnabled(true);
            jButton26.setEnabled(true);
            jButton25.setEnabled(true);
            jcbx_EnderDest_indIEDest.setEnabled(true); //10/09/2021
            jcbx_Ide_tpEmis.setEnabled(true); //03/02/2023
            if (!jcbx_Ide_tpEmis.getSelectedItem().toString().substring(0, 1).equals("1")) {
                jP_Contigencia.setVisible(true);
                tf_Ide_DhCont.setEnabled(true);
                //tf_Ide_xjust.setEnabled(true);
            }
            jcbx_Ide_finNFe.setEnabled(true); //20/06/2024
            jTabbedPane1.setSelectedIndex(1);
        }

    }//GEN-LAST:event_botao_alterarActionPerformed

    private void botao_consultareciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_consultareciboActionPerformed
        if (jTable1.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Consulta em Lote de NFes não permitido, verifique!!!");
            return;
        }
        ArrayList<String> wListaNNF = new ArrayList<String>(); //10/10/2023
        int[] SelectedRows = jTable1.getSelectedRows();
        for (int i : SelectedRows) {
            wListaNNF.add(jTable1.getValueAt(i, 0).toString().trim());
        }

        if (!VerifyClassisInstance()) {
            try {
                ConsultaProtocoloReciboNFe_frm = new ConsultaProtocoloReciboNFe(
                        jDktInputData,
                        jFrame,
                        tf_Ide_DhEmi.getText().trim().substring(8, 10),
                        tf_Ide_Serie.getText().trim(),
                        tf_InfNFe_NNF.getText().trim(),
                        wListaNNF,
                        SelectedRows);
                ConsultaProtocoloReciboNFe_frm.enviaRegistro(this, "");
                ThreeDLauncher(ConsultaProtocoloReciboNFe_frm, true);  // 10/10/2023
            } catch (SQLException ex) {
                Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_botao_consultareciboActionPerformed

    private void botao_gravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_gravarActionPerformed
        //NFEdto nfeDto = new NFEdto();
        //int ideNNF = Integer.parseInt(tf_InfNFe_NNF.getText());
        //try {
        //    nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);
        //} catch (SQLException ex) {
        //    Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (ParseException ex) {
        //    Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //nfeDto.setINFNFE_DANFEXMLENVIADO("Sim");
        //NFEcontroller.Update(nfeDto);
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        try {
            UpdateNFe();
            tf_InfAdic_infCpl.setEditable(false);
            tf_InfAdic_infCpl.setEnabled(false);
            //********* Atualiza Tabela ********
            NfeNfRefController.Delete(tf_InfNFe_NNF.getText());
            int wTotLi = jTable6.getModel().getRowCount();
            String w_tipo = null;
            String w_refnfe = null;
            String w_cuf = null;
            String w_aamm = null;
            String w_cnpj = null;
            String w_mod = null;
            String w_serie = null;
            String w_nnf = null;
            for (int i = 0; i < wTotLi; i++) {
                if (jTable6.getValueAt(i, 0).toString().trim().length() > 0
                        && jTable6.getValueAt(i, 1).toString().trim().length() > 0) {
                    if (jTable6.getValueAt(i, 1).toString().trim().length() > 0) {
                        w_tipo = jTable6.getValueAt(i, 1).toString().trim();
                    }
                    if (jTable6.getValueAt(i, 2).toString().trim().length() > 0) {
                        w_refnfe = jTable6.getValueAt(i, 2).toString().trim().replace(" ", "");
                    }
                    if (jTable6.getValueAt(i, 3).toString().trim().length() > 0) {
                        w_cuf = jTable6.getValueAt(i, 3).toString().trim();
                    }
                    if (jTable6.getValueAt(i, 4).toString().trim().length() > 0
                            && jTable6.getValueAt(i, 4).toString().trim().length() <= 5) {
                        w_aamm = jTable6.getValueAt(i, 4).toString().trim().substring(3, 5)
                                + jTable6.getValueAt(i, 4).toString().trim().substring(0, 2);
                    }
                    if (jTable6.getValueAt(i, 5).toString().trim().length() > 0) {
                        w_cnpj = jTable6.getValueAt(i, 5).toString().trim();
                    }
                    if (jTable6.getValueAt(i, 6).toString().trim().length() > 0) {
                        w_mod = jTable6.getValueAt(i, 6).toString().trim();
                    }
                    if (jTable6.getValueAt(i, 7).toString().trim().length() > 0) {
                        w_serie = jTable6.getValueAt(i, 7).toString().trim();
                    }
                    if (jTable6.getValueAt(i, 8).toString().trim().length() > 0) {
                        w_nnf = jTable6.getValueAt(i, 8).toString().trim();
                    }
                    if (w_tipo.trim().equals("Nota Fiscal")) {
                        NFE_NFREF eNFE_NFREF = new NFE_NFREF();
                        eNFE_NFREF.setIde_nnf(tf_InfNFe_NNF.getText().trim());
                        eNFE_NFREF.setNfref_tipo(w_tipo);
                        eNFE_NFREF.setNfref_refnfe(w_refnfe);
                        eNFE_NFREF.setNfref_cuf(w_cuf);
                        eNFE_NFREF.setNfref_aamm(w_aamm);
                        eNFE_NFREF.setNfref_cnpj(w_cnpj);
                        eNFE_NFREF.setNfref_mod(w_mod);
                        eNFE_NFREF.setNfref_serie(w_serie);
                        eNFE_NFREF.setNfref_nnf(w_nnf);
                        NfeNfRefController.Create(tf_InfNFe_NNF.getText().trim(), w_refnfe);
                        NfeNfRefController.Update(eNFE_NFREF);
                    } else {
                        NFE_NFREF eNFE_NFREF = new NFE_NFREF();
                        eNFE_NFREF.setIde_nnf(tf_InfNFe_NNF.getText().trim());
                        eNFE_NFREF.setNfref_tipo(w_tipo);
                        eNFE_NFREF.setNfref_refnfe(w_refnfe);
                        NfeNfRefController.Create(tf_InfNFe_NNF.getText().trim(), w_refnfe);
                        NfeNfRefController.Update(eNFE_NFREF);
                    }
                }
            }
            if (!tf_tot_is_vbcsel.getText().trim().isEmpty()) {
                eNFE.setTot_is_vbcsel(tf_tot_is_vbcsel.getText().trim().replace(".", "").replace(",", "."));
            }
            // **** Fim ****
            PreencheTabelaGerenciamento();
            PesquisaTable(tf_InfNFe_NNF.getText());
            botao_gravar.setEnabled(false);
            botao_alterar.setEnabled(true);
            botao_validar.setEnabled(true);
            disableFields();
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (InstantiationException ex) {
            returnParams(new String[]{ex.toString()});
        } catch (IllegalAccessException ex) {
            returnParams(new String[]{ex.toString()});
        } catch (ParseException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        }
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
    }//GEN-LAST:event_botao_gravarActionPerformed

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_chaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_chaveActionPerformed
        wOmVErro = true;
    }//GEN-LAST:event_botao_chaveActionPerformed

    private void botao_cartacorrecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cartacorrecaoActionPerformed
        if (!VerifyClassisInstance()) {
            CartaDeCorrecaoPorEvento_frm = new CartaDeCorrecaoPorEvento(jDktInputData, jFrame, tf_Ide_DhEmi.getText().trim().substring(8, 10), tf_Ide_Serie.getText().trim(), tf_InfNFe_NNF.getText().trim());
            CartaDeCorrecaoPorEvento_frm.enviaRegistro(this, "");
            ThreeDLauncher(CartaDeCorrecaoPorEvento_frm, true);
        }
    }//GEN-LAST:event_botao_cartacorrecaoActionPerformed

    private void botao_validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validarActionPerformed
        //if (jTable1.getSelectedRowCount() > 1) {
        //    JOptionPane.showMessageDialog(this, "Validação em Lote de NFes não permitido, verifique!!!");
        //    return;
        //}
        ArrayList<String> wListaNNF = new ArrayList<String>();
        int[] SelectedRows = jTable1.getSelectedRows();
        for (int i : SelectedRows) {
            wListaNNF.add(jTable1.getValueAt(i, 0).toString().trim());
        }
        if (!VerifyClassisInstance()) {
            Collections.sort(wListaNNF);
            ValidaXML_frm = new ValidaXML(jDktInputData, jFrame, tf_InfNFe_NNF.getText().trim(), wListaNNF, SelectedRows);
            ValidaXML_frm.enviaRegistro(this, "");
            ThreeDLauncher(ValidaXML_frm, true);
        }
    }//GEN-LAST:event_botao_validarActionPerformed

    private void botao_transmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_transmitirActionPerformed
        try {
            //if (jTable1.getSelectedRowCount() > 1) {
            //    JOptionPane.showMessageDialog(this, "Envio em Lote de NFes não permitido, verifique!!!");
            //    return;
            //}
            if (duplicatasBanco(tf_InfNFe_NNF.getText().trim())) {
                if (!new File(planPrevcaixa).exists()) {
                    JOptionPane.showMessageDialog(this,
                            "A planilha: " + planPrevcaixa + " não encontrada, verifique!!!",
                            "Mensagem do Sistema",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean isOpen = VerifyFileExcelOpen.isOpen(planPrevcaixa);
                if (isOpen) {
                    JOptionPane.showMessageDialog(this,
                            "A planilha: " + planPrevcaixa + " está aberta, verifique!!!",
                            "Mensagem do Sistema",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil();
                ProcessaToken.processaTokenBradesco();
                if (System.getProperty("bradesco.token").isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Falha na geração do Token Bradesco, verifique!!!");
                    return;
                };
            }
            VerificaConexaoInternet verifivaConexaoInternet = new VerificaConexaoInternet();

            if (!verifivaConexaoInternet.isConexaoInternet()) {
                JOptionPane.showMessageDialog(this, "Sem conexão com a Internet, verifique!!!");
                return;
            }

            ArrayList<String> wListaNNF = new ArrayList<String>();
            int[] SelectedRows = jTable1.getSelectedRows();
            for (int i : SelectedRows) {
                wListaNNF.add(jTable1.getValueAt(i, 0).toString().trim());
            }
            if (!VerifyClassisInstance()) {
                Collections.sort(wListaNNF);
                EnviaXMLAssinado_frm = new Modal_ViewDataEnviaXMLAssinadoService(
                        jDktInputData,
                        jFrame,
                        tf_Ide_DhEmi.getText().trim().substring(8, 10),
                        tf_Ide_Serie.getText().trim(),
                        tf_InfNFe_NNF.getText().trim(),
                        wListaNNF,
                        SelectedRows);
                EnviaXMLAssinado_frm.enviaRegistro(this, "");
                ThreeDLauncher(EnviaXMLAssinado_frm, true);
            }
        } catch (Exception ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_transmitirActionPerformed

    private void botao_previsualizardanfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_previsualizardanfeActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        ImprimeDANFe frm = new ImprimeDANFe(jDktInputData, jFrame, tf_InfNFe_NNF.getText().trim(), "0");
        Boolean valor = false;
        JInternalFrame[] results;
        results = jDesktop.getAllFrames();
        for (int i = 0; i
                < results.length; i++) {
            if (results[i].equals(frm)) {
                if (i == 1) {
                    valor = true;
                    break;
                }
            }
            if (!valor) {
                this.jDesktop.add(frm);
                try {
                    frm.setMaximum(true);
                    frm.setSelected(true);
                } catch (Exception ex) {
                    returnParams(new String[]{ex.getMessage()});
                }
                frm.setVisible(true);
            }
            this.toBack();
        }
        this.setMaximizable(true);
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
    }//GEN-LAST:event_botao_previsualizardanfeActionPerformed

    private void botao_imprimirdanfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_imprimirdanfeActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        ImprimeDANFe frm = new ImprimeDANFe(jDktInputData, jFrame, tf_InfNFe_NNF.getText().trim(), "1");
        Boolean valor = false;
        JInternalFrame[] results;
        results = jDesktop.getAllFrames();
        for (int i = 0; i
                < results.length; i++) {
            if (results[i].equals(frm)) {
                if (i == 1) {
                    valor = true;
                    break;
                }
            }
            if (!valor) {
                this.jDesktop.add(frm);
                try {
                    frm.setMaximum(true);
                    frm.setSelected(true);
                } catch (Exception ex) {
                    returnParams(new String[]{ex.getMessage()});
                }
                frm.setVisible(true);
            }
            this.toBack();
        }
        this.setMaximizable(true);
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
    }//GEN-LAST:event_botao_imprimirdanfeActionPerformed

    private void botao_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_exportarActionPerformed
        if (tf_tot_ibs_uf_vibs == null || tf_tot_ibs_uf_vibs.getText().isEmpty()
                || tf_tot_cbs_vcbs == null || tf_tot_cbs_vcbs.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Nota Fiscal sem IBS/CBS, verifique!", "Mensagem", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!VerifyClassisInstance()) {
            ExportaArquivosXML_frm = new ExportaArquivosXML(jDktInputData, jFrame, tf_InfNFe_NNF.getText().trim(), tf_InfNFe_Id.getText().trim().replace(" ", ""), jTable1.getSelectedRow());
            ExportaArquivosXML_frm.enviaRegistro(this, "");
            ThreeDLauncher(ExportaArquivosXML_frm, true);
        }
    }//GEN-LAST:event_botao_exportarActionPerformed

    private void botao_cancelanfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cancelanfeActionPerformed
        if (!VerifyClassisInstance()) {
            CancelamentoPorEvento_frm = new CancelamentoPorEvento(jDktInputData, jFrame, tf_Ide_DhEmi.getText().trim().substring(8, 10), tf_Ide_Serie.getText().trim(), tf_InfNFe_NNF.getText().trim());
            CancelamentoPorEvento_frm.enviaRegistro(this, "");
            ThreeDLauncher(CancelamentoPorEvento_frm, true);
        }
    }//GEN-LAST:event_botao_cancelanfeActionPerformed

    private void botao_chavedeacessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_chavedeacessoActionPerformed
        if (!VerifyClassisInstance()) {
            MostraChavedeAcesso_frm = new MostraChavedeAcesso(jDktInputData, jFrame, tf_InfNFe_NNF.getText().trim(), tf_InfNFe_Id.getText().trim().replace(" ", ""));
            ThreeDLauncher(MostraChavedeAcesso_frm, false);
        }
    }//GEN-LAST:event_botao_chavedeacessoActionPerformed

    private void botao_NovaPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_NovaPesquisaActionPerformed
        tf_FiltroNumeroInicial.setText("");
        tf_FiltroNumeroFinal.setText("");
    }//GEN-LAST:event_botao_NovaPesquisaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jjTable1MouseClicked();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jjTable1MouseClicked() {
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            prow = jTable1.getSelectedRow();
            state();
            tf_InfNFe_NNF.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim());
            if (this.tf_InfNFe_NNF.getText().trim().length() > 0) {
                JSONObject jsonNFe = new JSONObject();
                jsonNFe = NfeController.Index(this.tf_InfNFe_NNF.getText().trim());
                LoadFM(jsonNFe);
                disableFields();
            }
            Status_Navegacao(prow, jTable1.getRowCount());
            ConsultaSituacaoNFe_frm = null;
            ValidaXML_frm = null;
            EnviaXMLAssinado_frm = null;
            ExportaArquivosXML_frm = null;
            CancelamentoPorEvento_frm = null;
            MostraChavedeAcesso_frm = null;
            CartaDeCorrecaoPorEvento_frm = null;

            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.toString()});
        } catch (ParseException ex) {
            returnParams(new String[]{ex.toString()});
        }

    }


    private void botao_validar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar8ActionPerformed

    private void botao_validar14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar14ActionPerformed

    private void botao_validar15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar15ActionPerformed

    private void botao_validar16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            ww_Infnfe_statusnfe = tf_Infnfe_statusnfe.getText().trim();
            NFe_Detalhe_Produtos_e_Servicos frm = new NFe_Detalhe_Produtos_e_Servicos(
                    jDktInputData, jFrame,
                    tf_InfNFe_NNF.getText().trim(),
                    jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString().trim(),
                    jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString().trim(),
                    tf_Infnfe_statusnfe.getText().trim()
            );
            double width = frm.getSize().getWidth();
            double height = frm.getSize().getHeight();
            int wwidth = (int) width;
            int wheight = (int) height;
            frm.setLocation((this.jDktInputData.getSize().width - wwidth) / 2, (((this.jDktInputData.getSize().height) - wheight) / 2));
            jDktInputData.remove(frm);
            jDktInputData.add(frm);
            //frm.setClosable(true);
            frm.setMaximum(true);
            frm.toFront();
            frm.enviaRegistro(this, "");
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);

        } catch (PropertyVetoException ex) {
            returnParams(new String[]{ex.getMessage()});
        } catch (SQLException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton17ActionPerformed

    private void botao_validar17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar17ActionPerformed

    private void jcbx_Transp_modFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_Transp_modFreteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_Transp_modFreteActionPerformed

    private void jcbx_ISSQNtot_cRegTribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_ISSQNtot_cRegTribActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_ISSQNtot_cRegTribActionPerformed

    private void jcbx_Transporta_tpCNPJCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_Transporta_tpCNPJCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_Transporta_tpCNPJCPFActionPerformed

    private void botao_validar18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar18ActionPerformed

    private void botao_validar19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_validar19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_validar19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void botao_consultanasefazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_consultanasefazActionPerformed
        if (tf_tot_ibs_uf_vibs == null || tf_tot_ibs_uf_vibs.getText().isEmpty()
                || tf_tot_cbs_vcbs == null || tf_tot_cbs_vcbs.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Nota Fiscal sem IBS/CBS, verifique!", "Mensagem", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jTable1.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Consulta em Lote de NFes não permitido, verifique!!!");
            return;
        }
        ArrayList<String> wListaNNF = new ArrayList<String>();
        int[] SelectedRows = jTable1.getSelectedRows();
        for (int i : SelectedRows) {
            wListaNNF.add(jTable1.getValueAt(i, 0).toString().trim());
        }

        if (!VerifyClassisInstance()) {
            try {
                ConsultaSituacaoNFe_frm = new ConsultaSituacaoNFe(
                        jDktInputData,
                        jFrame,
                        tf_Ide_DhEmi.getText().trim().substring(8, 10),
                        tf_Ide_Serie.getText().trim(),
                        tf_InfNFe_NNF.getText().trim(),
                        wListaNNF,
                        SelectedRows);
                ConsultaSituacaoNFe_frm.enviaRegistro(this, "");
                ThreeDLauncher(ConsultaSituacaoNFe_frm, true);
            } catch (SQLException ex) {
                Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        double width = frm.getSize().getWidth();
        double height = frm.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        frm.setLocation((this.jDktInputData.getSize().width - wwidth) / 2, (((this.jDktInputData.getSize().height) - wheight) / 2));
        jDktInputData.remove(frm);
        jDktInputData.add(frm);
        //frm.setClosable(false);
        frm.setMaximum(true);
        frm.toFront();
        frm.enviaRegistro(this, "");
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
         */

    }//GEN-LAST:event_botao_consultanasefazActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        NFe_Documentos_Referenciados frm = new NFe_Documentos_Referenciados(
                jDktInputData,
                jFrame,
                "Inclui",
                tf_InfNFe_NNF.getText().trim(),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
        double width = frm.getSize().getWidth();
        double height = frm.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        frm.setLocation((this.jDktInputData.getSize().width - wwidth) / 2, (((this.jDktInputData.getSize().height) - wheight) / 2));
        frm.enviaRegistro(this, "");
        jDktInputData.remove(frm);
        jDktInputData.add(frm);
        frm.setClosable(false);
        frm.setVisible(true);
        frm.setResizable(false);
        frm.toFront();
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);

    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int selection = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Mensagem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selection != JOptionPane.OK_OPTION) {
            return;
        }
        DefaultTableModel modelo = (DefaultTableModel) jTable6.getModel();
        jTable6.moveColumn(0, 0);
        modelo.removeRow(jTable6.getSelectedRow());
        jTable6.clearSelection();
        jTable6.changeSelection(jTable6.getRowCount() - 1, 0, false, false);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        NFe_Documentos_Referenciados frm = new NFe_Documentos_Referenciados(
                jDktInputData,
                jFrame,
                "Altera",
                tf_InfNFe_NNF.getText().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 1).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 2).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 3).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 4).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 5).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 6).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 7).toString().trim(),
                jTable6.getValueAt(jTable6.getSelectedRow(), 8).toString().trim());
        double width = frm.getSize().getWidth();
        double height = frm.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        frm.setLocation((this.jDktInputData.getSize().width - wwidth) / 2, (((this.jDktInputData.getSize().height) - wheight) / 2));
        jDktInputData.remove(frm);
        jDktInputData.add(frm);
        frm.setClosable(false);
        frm.enviaRegistro(this, "");
        frm.setVisible(true);
        frm.setResizable(false);
        frm.toFront();
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) jTable7.getModel();
        Vector<String> wVector = new Vector<String>();
        modelo.addRow(wVector);
        jTable7.updateUI(); // outro código achado na net que tbm não funcionou.
        jTable7.clearSelection();
        jTable7.changeSelection(jTable6.getRowCount() - 1, 0, false, false);
        jTable7.setValueAt(jTable7.getRowCount() - 1, jTable7.getSelectedRow(), 0);
        jTable7.setValueAt("", jTable6.getSelectedRow(), 1);
        jTable7.setValueAt("", jTable6.getSelectedRow(), 2);
        jTable7.setValueAt("", jTable6.getSelectedRow(), 3);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        NFe_Detalhe_Informacoes_de_Pagamento frm = new NFe_Detalhe_Informacoes_de_Pagamento(
                jDktInputData, jFrame,
                tf_InfNFe_NNF.getText().trim(),
                jTable7.getValueAt(jTable7.getSelectedRow(), 0).toString().trim()
        );
        double width = frm.getSize().getWidth();
        double height = frm.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        frm.setLocation((this.jDktInputData.getSize().width - wwidth) / 2, (((this.jDktInputData.getSize().height) - wheight) / 2));
        jDktInputData.remove(frm);
        jDktInputData.add(frm);
        frm.toFront();
        frm.enviaRegistro(this, "");
        cursor = Cursor.getDefaultCursor();
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);

    }//GEN-LAST:event_jButton29ActionPerformed

    private void jLabel132AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel132AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel132AncestorAdded

    private void botao_alterarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_alterarMouseMoved
        botao_alterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_alterarMouseMoved

    private void botao_validarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_validarMouseMoved
        botao_validar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_validarMouseMoved

    private void botao_transmitirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_transmitirMouseMoved
        botao_transmitir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_transmitirMouseMoved

    private void botao_previsualizardanfeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_previsualizardanfeMouseMoved
        botao_previsualizardanfe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_previsualizardanfeMouseMoved

    private void botao_imprimirdanfeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_imprimirdanfeMouseMoved
        botao_imprimirdanfe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_imprimirdanfeMouseMoved

    private void botao_consultanasefazMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_consultanasefazMouseMoved
        botao_consultanasefaz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_consultanasefazMouseMoved

    private void botao_enviarEmailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_enviarEmailMouseMoved
        botao_enviarEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_enviarEmailMouseMoved

    private void botao_gravarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_gravarMouseMoved
        botao_gravar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_gravarMouseMoved

    private void botao_exportarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_exportarMouseMoved
        botao_exportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_exportarMouseMoved

    private void botao_cancelanfeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_cancelanfeMouseMoved
        botao_cancelanfe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_cancelanfeMouseMoved

    private void botao_chavedeacessoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_chavedeacessoMouseMoved
        botao_chavedeacesso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_chavedeacessoMouseMoved

    private void botao_cartacorrecaoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_cartacorrecaoMouseMoved
        botao_cartacorrecao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_cartacorrecaoMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    private void botao_PesquisarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_PesquisarMouseMoved
        botao_Pesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_PesquisarMouseMoved

    private void botao_NovaPesquisaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_NovaPesquisaMouseMoved
        botao_NovaPesquisa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_NovaPesquisaMouseMoved

    private void jButton24MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseMoved
        jButton24.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton24MouseMoved

    private void jButton26MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseMoved
        jButton26.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton26MouseMoved

    private void jButton25MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseMoved
        jButton25.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton25MouseMoved

    private void jButton15MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseMoved
        jButton15.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton15MouseMoved

    private void jButton17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseMoved
        jButton17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton17MouseMoved

    private void jButton16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseMoved
        jButton16.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton16MouseMoved

    private void botao_validar17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_validar17MouseMoved
        botao_validar17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_validar17MouseMoved

    private void botao_validar18MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_validar18MouseMoved
        botao_validar18.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_validar18MouseMoved

    private void botao_validar19MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_validar19MouseMoved
        botao_validar19.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_validar19MouseMoved

    private void jButton18MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseMoved
        jButton18.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton18MouseMoved

    private void jButton20MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseMoved
        jButton20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton20MouseMoved

    private void jButton19MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseMoved
        jButton19.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton19MouseMoved

    private void jButton21MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseMoved
        jButton21.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton21MouseMoved

    private void jButton23MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseMoved
        jButton23.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton23MouseMoved

    private void jButton22MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseMoved
        jButton22.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton22MouseMoved

    private void jButton27MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseMoved
        jButton27.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton27MouseMoved

    private void jButton29MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseMoved
        jButton29.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton29MouseMoved

    private void jButton28MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseMoved
        jButton28.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton28MouseMoved

    private void botao_chaveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_chaveMouseMoved
        botao_chave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_chaveMouseMoved

    private void botao_detalhareventoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_detalhareventoMouseMoved
        botao_detalharevento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_detalhareventoMouseMoved

    private void botao_detalhareventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_detalhareventoActionPerformed

        if (!VerifyClassisInstance()) {
            DetalhaEventoNFeAutorizada_frm
                    = new DetalhaEventoNFe(
                            jDktInputData,
                            jFrame,
                            tf_InfNFe_NNF.getText().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 2).toString().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 0).toString().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 3).toString().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 1).toString().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 4).toString().trim(),
                            jTable5.getValueAt(jTable5.getSelectedRow(), 5).toString().trim()
                    );
            ThreeDLauncher(DetalhaEventoNFeAutorizada_frm, false);
        }
    }//GEN-LAST:event_botao_detalhareventoActionPerformed

    private void jcbx_Ide_indPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_Ide_indPresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_Ide_indPresActionPerformed

    private void jcbx_Ide_tpEmisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_Ide_tpEmisActionPerformed
        if (!jcbx_Ide_tpEmis.getSelectedItem().toString().substring(0, 1).equals("1")) {
            jP_Contigencia.setVisible(true);
            if (tf_Ide_DhCont.getText().trim().length() <= 0) {
                //Date dataAtual = new Date();
                //SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                //String wwDhRegEvento = formatador.format(dataAtual);

                tf_Ide_DhCont.setText(tf_Ide_DhEmi.getText().trim());
                tf_Ide_DhCont.setEnabled(true);
            }
            if (tf_Ide_xjust.getText().trim().length() <= 0) {
                tf_Ide_xjust.setText("Indisponibilidade do ambiente normal de autorização");
            }
        } else {
            jP_Contigencia.setVisible(false);
            tf_Ide_DhCont.setText("");
            tf_Ide_xjust.setText("");
        }
    }//GEN-LAST:event_jcbx_Ide_tpEmisActionPerformed

    private void jcbx_Ide_finNFeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_Ide_finNFeActionPerformed
        jcbx_Ide_tpNFDebito.setEnabled(false);
        jcbx_Ide_tpNFCredito.setEnabled(false);
        jcbx_Ide_tpNFDebito.setSelectedIndex(0);
        jcbx_Ide_tpNFCredito.setSelectedIndex(0);

        Object selectedItem = jcbx_Ide_finNFe.getSelectedItem();
        if (selectedItem != null) {
            String value = selectedItem.toString().trim();
            if (!value.isEmpty()) {
                char firstChar = value.charAt(0);
                if (firstChar == '5') {
                    jcbx_Ide_tpNFCredito.setEnabled(true);
                } else if (firstChar == '6') {
                    jcbx_Ide_tpNFDebito.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_jcbx_Ide_finNFeActionPerformed

    private void botao_enviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_enviarEmailActionPerformed
        try {
            Modal_ViewDataEnviaEmail_frm = new Modal_ViewDataEnviaEmail(
                    tf_InfNFe_NNF.getText().trim()
            );
            if (!VerifyClassisInstance()) {
                ThreeDLauncher(this, Modal_ViewDataEnviaEmail_frm, true);
                this.setEnabled(true);
                renderStatusDocumentos();
            }

        } catch (MD1Exception ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botao_enviarEmailActionPerformed

    private void botao_registrarTituloMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_registrarTituloMouseMoved
        botao_registrarTitulo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_registrarTituloMouseMoved

    private void botao_registrarTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_registrarTituloActionPerformed
        try {
            ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil();
            ProcessaToken.processaTokenBradesco();

            if (System.getProperty("bradesco.token").isEmpty()) {
                JOptionPane.showMessageDialog(this, "Falha na geração do Token Bradesco, verifique!!!");
                return;
            };

            Modal_ViewDataRegistraTitulo_frm = new Modal_ViewDataRegistraTitulo(
                    tf_InfNFe_NNF.getText().trim()
            );
            if (!VerifyClassisInstance()) {
                ThreeDLauncher(this, Modal_ViewDataRegistraTitulo_frm, true);
                this.setEnabled(true);
                renderStatusDocumentos();
            }
        } catch (MD1Exception ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_registrarTituloActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_NovaPesquisa;
    private javax.swing.JButton botao_Pesquisar;
    private javax.swing.JButton botao_alterar;
    private javax.swing.JButton botao_cancelanfe;
    private javax.swing.JButton botao_cartacorrecao;
    private javax.swing.JButton botao_chave;
    private javax.swing.JButton botao_chavedeacesso;
    private javax.swing.JButton botao_consultanasefaz;
    private javax.swing.JButton botao_consultarecibo;
    private javax.swing.JButton botao_detalharevento;
    private javax.swing.JButton botao_enviarEmail;
    private javax.swing.JButton botao_excluir;
    private javax.swing.JButton botao_exportar;
    private javax.swing.JButton botao_gravar;
    private javax.swing.JButton botao_imprimirdanfe;
    private javax.swing.JButton botao_previsualizardanfe;
    private javax.swing.JButton botao_registrarTitulo;
    private javax.swing.JButton botao_sair;
    private javax.swing.JButton botao_transmitir;
    private javax.swing.JButton botao_validar;
    private javax.swing.JButton botao_validar14;
    private javax.swing.JButton botao_validar15;
    private javax.swing.JButton botao_validar16;
    private javax.swing.JButton botao_validar17;
    private javax.swing.JButton botao_validar18;
    private javax.swing.JButton botao_validar19;
    private javax.swing.JButton botao_validar8;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JCheckBox jCheck_FiltroDanfeImpresso;
    private javax.swing.JCheckBox jCheck_InformaCodigoNumerico;
    private javax.swing.JCheckBox jCheck_Localdeentregadiferentedodestinatario;
    private javax.swing.JCheckBox jCheck_localderetiradadiferentedoemitente;
    private com.toedter.calendar.JDateChooser jDC_FiltroDataFinal;
    private com.toedter.calendar.JDateChooser jDC_FiltroDataInicial;
    private com.toedter.calendar.JDateChooser jDC_ISSQNtot_dCompet;
    private javax.swing.JDesktopPane jDktInputData;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLb_Ide_cDV;
    private javax.swing.JLabel jLb_Ide_cNF;
    private javax.swing.JPanel jP_Contigencia;
    private javax.swing.JPanel jP_HitoricosEventos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JComboBox jcbx_Dest_tpCNPJCPF;
    private javax.swing.JTextField jcbx_EnderDest_IE;
    private javax.swing.JComboBox jcbx_EnderDest_UF;
    private javax.swing.JComboBox jcbx_EnderDest_indIEDest;
    private javax.swing.JComboBox jcbx_EnderDest_xMun;
    private javax.swing.JComboBox jcbx_EnderDest_xPais;
    private javax.swing.JComboBox jcbx_EnderEmit_CRT;
    private javax.swing.JComboBox jcbx_EnderEmit_UF;
    private javax.swing.JComboBox jcbx_EnderEmit_xMun;
    private javax.swing.JComboBox jcbx_EnderEmit_xPais;
    private javax.swing.JComboBox jcbx_FiltroExportadaXML;
    private javax.swing.JComboBox jcbx_FiltroSituacaoNFe;
    private javax.swing.JComboBox jcbx_FiltroTipo;
    private javax.swing.JComboBox jcbx_FiltroUFdestinatario;
    private javax.swing.JComboBox jcbx_ISSQNtot_cRegTrib;
    private javax.swing.JComboBox jcbx_Ide_finNFe;
    private javax.swing.JComboBox jcbx_Ide_idDest;
    private javax.swing.JComboBox jcbx_Ide_indFinal;
    private javax.swing.JComboBox jcbx_Ide_indPres;
    private javax.swing.JComboBox jcbx_Ide_tpEmis;
    private javax.swing.JComboBox jcbx_Ide_tpImp;
    private javax.swing.JComboBox jcbx_Ide_tpNF;
    private javax.swing.JComboBox jcbx_Ide_tpNFCredito;
    private javax.swing.JComboBox jcbx_Ide_tpNFDebito;
    private javax.swing.JComboBox jcbx_Ide_xUF;
    private javax.swing.JComboBox jcbx_RetTransp_CFOP;
    private javax.swing.JComboBox jcbx_RetTransp_UF;
    private javax.swing.JComboBox jcbx_RetTransp_cMunFG;
    private javax.swing.JComboBox jcbx_TVeiculo_UF;
    private javax.swing.JComboBox jcbx_Transp_modFrete;
    private javax.swing.JComboBox jcbx_Transporta_UF;
    private javax.swing.JComboBox jcbx_Transporta_tpCNPJCPF;
    private javax.swing.JComboBox jcbx_Transporta_xMun;
    private javax.swing.JCheckBox jchkbx_isentodoicms;
    private javax.swing.JLabel statusbar;
    private javax.swing.JTextField tf_Dest_CNPJ;
    private javax.swing.JTextField tf_Dest_xNome;
    private javax.swing.JTextField tf_Emit_CNPJ;
    private javax.swing.JTextField tf_Emit_xFant;
    private javax.swing.JTextField tf_Emit_xNome;
    private javax.swing.JTextField tf_EnderDest_CEP;
    private javax.swing.JTextField tf_EnderDest_IM;
    private javax.swing.JTextField tf_EnderDest_ISUF;
    private javax.swing.JTextField tf_EnderDest_email;
    private javax.swing.JTextField tf_EnderDest_fone;
    private javax.swing.JTextField tf_EnderDest_nro;
    private javax.swing.JTextField tf_EnderDest_xBairro;
    private javax.swing.JTextField tf_EnderDest_xCpl;
    private javax.swing.JTextField tf_EnderDest_xLgr;
    private javax.swing.JTextField tf_EnderEmit_CEP;
    private javax.swing.JTextField tf_EnderEmit_CNAE;
    private javax.swing.JTextField tf_EnderEmit_IE;
    private javax.swing.JTextField tf_EnderEmit_IEST;
    private javax.swing.JTextField tf_EnderEmit_IM;
    private javax.swing.JTextField tf_EnderEmit_fone;
    private javax.swing.JTextField tf_EnderEmit_nro;
    private javax.swing.JTextField tf_EnderEmit_xBairro;
    private javax.swing.JTextField tf_EnderEmit_xCpl;
    private javax.swing.JTextField tf_EnderEmit_xLgr;
    private javax.swing.JTextField tf_Fat_nFat;
    private javax.swing.JTextField tf_Fat_vDesc;
    private javax.swing.JTextField tf_Fat_vLiq;
    private javax.swing.JTextField tf_Fat_vOrig;
    private javax.swing.JTextField tf_FiltroChavedeAcesso;
    private javax.swing.JTextField tf_FiltroNumeroFinal;
    private javax.swing.JTextField tf_FiltroNumeroInicial;
    private javax.swing.JTextField tf_FiltroSerie;
    private javax.swing.JTextField tf_FiltrocnpjcpfDestinatario;
    private javax.swing.JTextField tf_ICMSTot_vBC;
    private javax.swing.JTextField tf_ICMSTot_vBCST;
    private javax.swing.JTextField tf_ICMSTot_vCOFINS;
    private javax.swing.JTextField tf_ICMSTot_vDesc;
    private javax.swing.JTextField tf_ICMSTot_vFCP;
    private javax.swing.JTextField tf_ICMSTot_vFCPST;
    private javax.swing.JTextField tf_ICMSTot_vFCPSTRet;
    private javax.swing.JTextField tf_ICMSTot_vFrete;
    private javax.swing.JTextField tf_ICMSTot_vICMS;
    private javax.swing.JTextField tf_ICMSTot_vICMSDeson;
    private javax.swing.JTextField tf_ICMSTot_vICMSUFDest;
    private javax.swing.JTextField tf_ICMSTot_vII;
    private javax.swing.JTextField tf_ICMSTot_vIPI;
    private javax.swing.JTextField tf_ICMSTot_vIPIDevol;
    private javax.swing.JTextField tf_ICMSTot_vNF;
    private javax.swing.JTextField tf_ICMSTot_vOutro;
    private javax.swing.JTextField tf_ICMSTot_vPIS;
    private javax.swing.JTextField tf_ICMSTot_vPartilhaDestinatario;
    private javax.swing.JTextField tf_ICMSTot_vPartilhaRemetente;
    private javax.swing.JTextField tf_ICMSTot_vProd;
    private javax.swing.JTextField tf_ICMSTot_vST;
    private javax.swing.JTextField tf_ICMSTot_vSeg;
    private javax.swing.JTextField tf_ISSQNtot_vBC;
    private javax.swing.JTextField tf_ISSQNtot_vCOFINS;
    private javax.swing.JTextField tf_ISSQNtot_vDeducao;
    private javax.swing.JTextField tf_ISSQNtot_vDescCond;
    private javax.swing.JTextField tf_ISSQNtot_vDescIncond;
    private javax.swing.JTextField tf_ISSQNtot_vISS;
    private javax.swing.JTextField tf_ISSQNtot_vISSRet;
    private javax.swing.JTextField tf_ISSQNtot_vOutro;
    private javax.swing.JTextField tf_ISSQNtot_vPIS;
    private javax.swing.JTextField tf_ISSQNtot_vServ;
    private javax.swing.JTextField tf_Ide_DhCont;
    private javax.swing.JTextField tf_Ide_DhEmi;
    private javax.swing.JTextField tf_Ide_DhSaiEnt;
    private javax.swing.JTextField tf_Ide_Mod;
    private javax.swing.JTextField tf_Ide_NNF;
    private javax.swing.JTextField tf_Ide_Serie;
    private javax.swing.JTextField tf_Ide_cDV;
    private javax.swing.JTextField tf_Ide_cNF;
    private javax.swing.JTextField tf_Ide_natOp;
    private javax.swing.JTextField tf_Ide_xMunFG;
    private javax.swing.JTextField tf_Ide_xjust;
    private javax.swing.JTextArea tf_InfAdic_infAdFisco;
    private javax.swing.JTextArea tf_InfAdic_infCpl;
    private javax.swing.JTextField tf_InfNFe_Id;
    private javax.swing.JTextField tf_InfNFe_NNF;
    private javax.swing.JTextField tf_InfNFe_PedidoVenda;
    private javax.swing.JTextField tf_InfNFe_Versao;
    private javax.swing.JTextField tf_Infnfe_statusnfe;
    private javax.swing.JTextField tf_RetTransp_pICMSRet;
    private javax.swing.JTextField tf_RetTransp_vBCRet;
    private javax.swing.JTextField tf_RetTransp_vICMSRet;
    private javax.swing.JTextField tf_RetTransp_vServ;
    private javax.swing.JTextField tf_RetTrib_vBC;
    private javax.swing.JTextField tf_RetTrib_vBCIRRF;
    private javax.swing.JTextField tf_RetTrib_vBCRetPrev;
    private javax.swing.JTextField tf_RetTrib_vIRRF;
    private javax.swing.JTextField tf_RetTrib_vRetCOFINS;
    private javax.swing.JTextField tf_RetTrib_vRetCSLL;
    private javax.swing.JTextField tf_RetTrib_vRetPrev;
    private javax.swing.JTextField tf_Signature_Assunto;
    private javax.swing.JTextField tf_Signature_CNPJ;
    private javax.swing.JTextField tf_Signature_DataFinal;
    private javax.swing.JTextField tf_Signature_DataInicial;
    private javax.swing.JTextField tf_Signature_Emissor;
    private javax.swing.JTextField tf_TVeiculo_RNTC;
    private javax.swing.JTextField tf_TVeiculo_placa;
    private javax.swing.JTextField tf_Transporta_CNPJ;
    private javax.swing.JTextField tf_Transporta_IE;
    private javax.swing.JTextField tf_Transporta_xEnder;
    private javax.swing.JTextField tf_Transporta_xNome;
    private javax.swing.JTextField tf_ValorTotalNota1;
    private javax.swing.JTextField tf_ide_vICMS;
    private javax.swing.JTextField tf_ide_vNF;
    private javax.swing.JTextField tf_ide_vST;
    private javax.swing.JTextField tf_tot_cbs_vbc;
    private javax.swing.JTextField tf_tot_cbs_vcbs;
    private javax.swing.JTextField tf_tot_cbs_vcredprescondsus;
    private javax.swing.JTextField tf_tot_cbs_vcrespres;
    private javax.swing.JTextField tf_tot_cbs_vdeson;
    private javax.swing.JTextField tf_tot_cbs_vdevtrib;
    private javax.swing.JTextField tf_tot_cbs_vdif;
    private javax.swing.JTextField tf_tot_ibs_mun_vcredprescondsus;
    private javax.swing.JTextField tf_tot_ibs_mun_vcrespres;
    private javax.swing.JTextField tf_tot_ibs_mun_vdeson;
    private javax.swing.JTextField tf_tot_ibs_mun_vdevtrib;
    private javax.swing.JTextField tf_tot_ibs_mun_vdif;
    private javax.swing.JTextField tf_tot_ibs_mun_vibs;
    private javax.swing.JTextField tf_tot_ibs_uf_vcredprescondsus;
    private javax.swing.JTextField tf_tot_ibs_uf_vcrespres;
    private javax.swing.JTextField tf_tot_ibs_uf_vdeson;
    private javax.swing.JTextField tf_tot_ibs_uf_vdevtrib;
    private javax.swing.JTextField tf_tot_ibs_uf_vdif;
    private javax.swing.JTextField tf_tot_ibs_uf_vibs;
    private javax.swing.JTextField tf_tot_ibs_vcredprescondsus;
    private javax.swing.JTextField tf_tot_ibs_vcrespres;
    private javax.swing.JTextField tf_tot_ibs_vibs;
    private javax.swing.JTextField tf_tot_is_vbcsel;
    private javax.swing.JTextField tf_tot_is_vimpsel;
    private javax.swing.JTextField tf_tot_vbcibscbs;
    private javax.swing.JTextField tf_tot_vtotnf;
    // End of variables declaration//GEN-END:variables

    public String repete(String suaStr, int vezes) {
        String ret = "";
        for (int i = 1; i <= vezes; i++) {
            ret = ret + suaStr;
        }
        return ret;
    }

    public void pesquisaCodigo() {
        try {
            if (tf_InfNFe_NNF.getText().toString().trim().length() <= 0) {
                return;
            }
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(this.tf_InfNFe_NNF.getText().trim());
            if (jsonNFe.isEmpty()) {
                JOptionPane.showConfirmDialog(this, "Fornecedor/Cliente não cadastrado!!!", "Mensagem", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            } else {
                LoadFM(jsonNFe);
            }
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        } catch (ParseException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void pesquisaDescricao() {

    }

    public void Status_Navegacao(int wRegPos, int wTotReg) {
        statusbar.setText(" Registro:   " + String.valueOf(wRegPos + 1) + " / " + String.valueOf(wTotReg));
    }

    public void LimpaTela() {
        disableFields();
        tf_InfNFe_NNF.setEnabled(true);
        tf_InfNFe_Id.setEnabled(true);

        tf_InfNFe_NNF.setText("");
        tf_InfNFe_Id.setText("");
        tf_InfNFe_NNF.setEnabled(true);
        tf_InfNFe_NNF.grabFocus();
    }

    protected MaskFormatter mascara(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
        return formatter;
    }

    public void Carrega_jcbx_fluidez_carga_teorumidade() {
        for (double i = 0; i <= 70.1; i = i + 0.1) {
            String shortString;
            String doubleString = Double.toString(i);
            String[] stringArray = doubleString.split("\\.");
            String decimals = stringArray[1].substring(0, 1);
            shortString = stringArray[0] + "." + decimals;
        }
    }

    public class comboMultidata {

        private String valor;
        private String texto;

        public comboMultidata(String id, String label) {
            this.valor = id;
            this.texto = label;
        }

        public String getValor() {
            return valor.toString();
        }

        @Override
        public String toString() {
            return texto.toString();
        }
    }

    public String PercorrejcbxNum(JComboBox jcbx, String wVar, int wInd) {
        if (tf_InfNFe_NNF.getText().trim().equals("000031812")
                && wVar.trim().equals("MOGI GUACU")) {
            String hw = "";
        }
        try {
            if (wVar == null
                    || wVar.toString().trim().length() <= 0
                    || wInd <= 0) {
                jcbx.setSelectedIndex(-1);
                return wVar;
            }
            String wwVar = wVar.toString().trim();
            boolean wFlagjcbx = false;
            int wIndice = -1;
            if (wInd >= 3) {
                wInd = 3;
            }
            for (int i = 0; i < jcbx.getItemCount(); i++) {
                if (jcbx.getItemAt(i).toString().trim().substring(0, wInd).equals(wwVar.substring(0, wInd))) {
                    jcbx.setSelectedIndex(i);
                    wFlagjcbx = true;
                    wIndice = i;
                    break;
                }
            }
            if (wFlagjcbx == true) {
                jcbx.setSelectedIndex(wIndice);
            } else {
                jcbx.removeAllItems();
                jcbx.addItem(new comboMultidata(wwVar, wwVar));
                jcbx.setSelectedIndex(jcbx.getItemCount() - 1);
            }
        } catch (Exception ex) {
            returnParams(new String[]{ex.getMessage()});
        }
        return wVar;
    }

    class jTable10ButtonColumn7 extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, ActionListener {

        JTable table;
        JButton renderButton;
        JButton editButton;
        String text;

        public jTable10ButtonColumn7(JTable table, int column) {
            this.table = table;
            renderButton = new JButton();
            editButton = new JButton();
            editButton.setFocusPainted(false);
            editButton.addActionListener(this);
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer(this);
            columnModel.getColumn(column).setCellEditor(this);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (hasFocus) {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            } else if (isSelected) {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            } else {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            table.setValueAt("Ver", row, column);
            table.setToolTipText("Visualiza Documento");
            renderButton.setText((value == null) ? "" : value.toString());
            return renderButton;
        }

        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column) {
            text = (value == null) ? "" : value.toString();
            editButton.setText(text);
            return editButton;
        }

        public Object getCellEditorValue() {
            return text;
        }

        public void actionPerformed(ActionEvent e) {
            jTable10botao_imprimeDocumentoActionPerformed(e);
        }
    }

    private void jTable10botao_imprimeDocumentoActionPerformed(java.awt.event.ActionEvent evt) {

    }

    class MudarFonte extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setOpaque(true);
            if (isSelected) {
                FormatacaoConteudo corNomes = new FormatacaoConteudo();
                Color nomesCor = new Color(176, 202, 227);
                setBackground(nomesCor);
            } else {
                setBackground(Color.white);
            }
            if (value != null) {
                setFont(new Font("Tahoma", Font.PLAIN, 8));
                setHorizontalAlignment(SwingConstants.LEFT);
                setText(value.toString());

            }
            if (value == null) {
                setText("");
            } else {
                setText(value.toString());
            }
            return this;
        }
    }

    class JDateChooserRenderer extends JDateChooser implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Date) {
                this.setDate((Date) value);
            } else if (value instanceof Calendar) {
                this.setCalendar((Calendar) value);
            }
            this.setDateFormatString("dd/MM/yyyy");
            return this;
        }
    }

    public class CustomCellEditor extends DefaultCellEditor {

        public CustomCellEditor(JTextField textField) {
            super(textField);
        }
    }

    class Colorir21 extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setOpaque(true);
            Color CorVerde = new Color(51, 153, 0);
            if (isSelected) {
                FormatacaoConteudo corNomes = new FormatacaoConteudo();
                Color nomesCor = new Color(176, 202, 227);
                setBackground(nomesCor);
            } else {
                setBackground(Color.white);
            }
            if (value != null) {

                if (value.toString().trim().equals("Rejeitada")) {
                    setForeground(CorVerde);
                };
                setText(value.toString());
            }
            if (value == null) {
                setText("");
            } else {
                setText(value.toString());
            }
            return this;
        }
    }

    public class ColorirLinhaNFeRejeita extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            Component result = super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column
            );
            Color c = Color.BLACK;
            Color CorLaranja = new Color(255, 102, 51);
            Color CorVerde = new Color(0, 204, 51);
            Font ft = new Font("Arial", Font.BOLD, 11);
            Object text = table.getValueAt(row, 7);
            Object textNFeXmlEnviados = table.getValueAt(row, 9);
            if (text != null && ("Rejeitada".equals(text.toString().trim())
                    || "Denegada".equals(text.toString().trim()))) {
                c = Color.RED;
            }
            if (text != null && "Cancelada".equals(text.toString().trim())) {
                c = CorLaranja;
            }
            if (text != null && "Corrigida".equals(text.toString().trim())) {
                c = CorVerde;
            }
            if (("Autorizada".equals(text.toString().trim())
                    && "Não".equals(textNFeXmlEnviados.toString().trim()))) {
                c = Color.MAGENTA;
            }
            result.setForeground(c);
            return result;
        }
    }

    public class ColorirEventoNFe extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            Component result = super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column
            );
            Color CorVerde = new Color(51, 153, 0);
            Color c = Color.RED;
            Font ft = new Font("Arial", Font.BOLD, 11);
            Object text = table.getValueAt(row, 2);
            if (text != null && "Rejeitado".equals(text.toString().trim())) {
                c = Color.RED;
            }
            if (text != null && text.toString().trim().contains("Autori")) {
                c = CorVerde;
            }
            if (text != null && "Carta de Correcao".equals(text.toString().trim())) {
                //c = CorVerde;
            }
            if (text != null && "Alterado".equals(text.toString().trim())) {
                c = Color.BLACK;
            }
            result.setForeground(c);
            return result;
        }
    }

    class DateCellEditor extends AbstractCellEditor implements
            TableCellEditor {

        private JDateChooser dateChooser = new JDateChooser();

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            JDateChooser JDCdate = new JDateChooser(new Date(), "dd/MM/yyyy");
            JDCdate.setDate(null);
            if (table.getValueAt(row, column) != null) {
                String wdata = table.getValueAt(row, column).toString();
                if (wdata.toString().length() > 0) {
                    try {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date date1 = null;

                        date1 = df.parse(wdata);

                        JDCdate.setDate((Date) date1);
                        dateChooser.setDate(JDCdate.getDate());
                    } catch (ParseException ex) {
                        returnParams(new String[]{ex.getMessage()});
                    }
                }
            }
            if (dateChooser.getDate() == null) {
                Date data = new Date();
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                dateChooser.setDate(JDCdate.getDate());
            }
            if (value instanceof Date) {
                dateChooser.setDate((Date) value);
            } else if (value instanceof Calendar) {
                dateChooser.setCalendar((Calendar) value);
            }
            dateChooser.setDateFormatString("dd/MM/yyyy");
            return dateChooser;
        }

        @Override
        public Object getCellEditorValue() {
            SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
            if (dateChooser.getDate() == null) {
                return "";
            } else {
                return sformat.format(dateChooser.getDate());
            }
        }
    }

    public class CustomTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof Integer) {
                Integer amount = (Integer) value;
                if (amount.intValue() < 0) {
                    cell.setBackground(Color.red);
                } else {
                    cell.setBackground(Color.white);
                }
            } else if (value instanceof String) {
                String valor = (String) value;
            }
            return cell;
        }
    }

    class Colorir extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setOpaque(true);
            if (isSelected) {
                FormatacaoConteudo corNomes = new FormatacaoConteudo();
                Color nomesCor = new Color(176, 202, 227);
                setBackground(nomesCor);
            } else {
                setBackground(Color.white);
            }
            if (value != null) {
                setForeground(Color.GREEN);
                if (Integer.parseInt(value.toString().trim()) <= 30) {
                    setForeground(Color.RED);
                };

                setText(value.toString());
            }
            if (value == null) {
                setText("");
            } else {
                setText(value.toString());
            }
            setHorizontalAlignment(SwingConstants.RIGHT);
            return this;
        }
    }

    public class FormatacaoConteudo extends DefaultTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }

    public void disableFields() {

        tf_InfNFe_NNF.setEnabled(false);
        botao_gravar.setEnabled(false);
        //botao_consultarecibo.setEnabled(false);
        tf_InfNFe_Id.setEnabled(false);
        jcbx_Ide_finNFe.setEnabled(false);
        jcbx_Ide_tpNFDebito.setEnabled(false); //31/07/2025
        jcbx_Ide_tpNFCredito.setEnabled(false); //31/07/2025
        tf_InfAdic_infCpl.setEnabled(false);
        tf_Emit_CNPJ.setEnabled(false);
        tf_EnderEmit_IE.setEnabled(false);
        tf_EnderEmit_IEST.setEnabled(false);
        jcbx_EnderDest_indIEDest.setEnabled(false);
        tf_EnderEmit_xLgr.setEnabled(false);
        tf_EnderEmit_nro.setEnabled(false);
        tf_EnderEmit_xCpl.setEnabled(false);
        tf_EnderEmit_xBairro.setEnabled(false);
        tf_EnderEmit_CEP.setEnabled(false);
        jcbx_EnderEmit_xPais.setEnabled(false);
        jcbx_EnderEmit_UF.setEnabled(false);
        jcbx_EnderEmit_xMun.setEnabled(false);
        jcbx_EnderEmit_xMun.setEditable(false);
        tf_EnderEmit_fone.setEnabled(false);
        jButton24.setEnabled(false);
        jButton25.setEnabled(false);
        jButton26.setEnabled(false);
        jButton29.setEnabled(false);
        tf_ICMSTot_vPIS.setEnabled(false);
        tf_ICMSTot_vCOFINS.setEnabled(false);
        jButton24.setEnabled(false);
        jButton26.setEnabled(false);
        jButton25.setEnabled(false);
        jcbx_Ide_tpEmis.setEnabled(false);
        jcbx_Ide_finNFe.setEnabled(false); //20/06/2024
        jcbx_Ide_tpNFDebito.setEnabled(false); //31/07/2025
        jcbx_Ide_tpNFCredito.setEnabled(false); //31/07/2025
        tf_Ide_DhCont.setEnabled(false);
        tf_Ide_xjust.setEnabled(false);
    }

    public void enableFields() {
        tf_InfNFe_NNF.setEnabled(false);
        botao_excluir.setEnabled(false);
        //botao_consultarecibo.setEnabled(false);
        tf_InfNFe_Id.setEnabled(true);
        tf_Emit_CNPJ.setEnabled(true);
        tf_EnderEmit_IE.setEnabled(true);
        tf_EnderEmit_IEST.setEnabled(true);
        tf_EnderEmit_xLgr.setEnabled(true);
        tf_EnderEmit_nro.setEnabled(true);
        tf_EnderEmit_xCpl.setEnabled(true);
        tf_EnderEmit_xBairro.setEnabled(true);
        tf_EnderEmit_CEP.setEnabled(true);
        jcbx_EnderEmit_xPais.setEnabled(false);
        jcbx_EnderEmit_UF.setEnabled(false);
        jcbx_EnderEmit_xMun.setEnabled(false);
        jcbx_EnderEmit_xMun.setEditable(false);
        tf_EnderEmit_fone.setEnabled(true);
        jButton29.setEnabled(true);
        jcbx_EnderDest_indIEDest.setEnabled(true); //10/09/2021        
        jcbx_Ide_tpEmis.setEnabled(true); //20/06/2024
        jcbx_Ide_finNFe.setEnabled(true);
        jcbx_Ide_tpNFDebito.setEnabled(false); //31/07/2025
        jcbx_Ide_tpNFCredito.setEnabled(false); //31/07/2025
        tf_Ide_DhCont.setEnabled(true);
        //tf_Ide_xjust.setEnabled(false);
    }

    public void MontaTabelaGerenciamento() {
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheaderleft = (TableCellRenderer) new aligntableheaderleft();
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        TableCellRenderer wCol7 = new ColorirLinhaNFeRejeita();
        jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheaderleft);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(wCol7);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheaderleft);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(wCol7);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheaderleft);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(wCol7);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheaderleft);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(110);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(centralizado);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(wCol7);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(130);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(30);
        jTable1.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(6).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(7).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(7).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(7).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(8).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(8).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(8).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(8).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(9).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(9).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(9).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(9).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(10).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(10).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(10).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(10).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(11).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(11).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(11).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(11).setCellRenderer(wCol7);

        jTable1.getTableHeader().getColumnModel().getColumn(12).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(12).setPreferredWidth(90);
        jTable1.getTableHeader().getColumnModel().getColumn(12).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(12).setCellRenderer(wCol7);
        JTableHeader header = jTable1.getTableHeader();
        PreencheTabelaGerenciamento();
    }

    public void PreencheTabelaGerenciamento() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            jDktInputData.setCursor(cursor);
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            String formatoData = "yyyy-MM-dd";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);

            String paramers = "";
            if (jDC_FiltroDataInicial.getDate() != null
                    && jDC_FiltroDataFinal.getDate() != null) {
                paramers = "to_char(ide_dhemi, 'YYYY-MM-DD') >= '" + dataFormatada.format(jDC_FiltroDataInicial.getDate()) + "' AND "
                        + "to_char(ide_dhemi, 'YYYY-MM-DD') <= '" + dataFormatada.format(jDC_FiltroDataFinal.getDate()) + "'";
            }
            if (tf_FiltroNumeroInicial.getText().trim().length() > 0
                    && tf_FiltroNumeroFinal.getText().trim().length() > 0
                    && tf_FiltroNumeroInicial.getText().trim().matches("-?\\d+")
                    && tf_FiltroNumeroFinal.getText().trim().matches("-?\\d+")) {
                paramers = "ide_nnf >= '" + Integer.parseInt(tf_FiltroNumeroInicial.getText()) + "' AND "
                        + "ide_nnf <= '" + Integer.parseInt(tf_FiltroNumeroFinal.getText()) + "'";
            }
            map = NfeController.List(paramers);
            String w_ide_tpemis = "";
            String w_ide_xtpemis = "";
            String w_data_Autorizacao = "";
            for (int i = 0; i < map.size(); i++) {
                w_ide_tpemis = map.get(i).get("ide_tpemis").toString().trim();
                if (w_ide_tpemis.equals("1")) {
                    w_ide_xtpemis = "Normal";
                } else if (w_ide_tpemis.equals("2")) {
                    w_ide_xtpemis = "Contingência FS-IA";
                } else if (w_ide_tpemis.equals("3")) {
                    w_ide_xtpemis = "Contingência via EPEC";
                } else if (w_ide_tpemis.equals("4")) {
                    w_ide_xtpemis = "Contingência via DPEC";
                } else if (w_ide_tpemis.equals("5")) {
                    w_ide_xtpemis = "Contingência FS-DA";
                } else if (w_ide_tpemis.equals("6")) {
                    w_ide_xtpemis = "Contingência SVC-AN";
                } else if (w_ide_tpemis.equals("7")) {
                    w_ide_xtpemis = "Contingência SVC-RS";
                }
                w_data_Autorizacao = "";
                if (map.get(i).getString("infnfe_statusnfe").equals("Autorizada")
                        || map.get(i).getString("infnfe_statusnfe").equals("Cancelada")
                        || map.get(i).getString("infnfe_statusnfe").equals("Corrigida")) {
                    if (map.get(i).getString("infprot_dhrecbto") != null) {
                        w_data_Autorizacao = map.get(i).getString("infprot_dhrecbto");
                    }
                }

                System.out.println(map.get(i).getString("ide_nnf"));

                modelo.addRow(new Object[]{
                    formata.StrZero(map.get(i).getString("ide_nnf"), 9),
                    formata.StrZero(map.get(i).getString("ide_serie"), 3),
                    map.get(i).getString("ide_dhemi"),
                    w_data_Autorizacao,
                    f.getCpfCnpjFormatado(map.get(i).getString("dest_cnpj")),
                    map.get(i).getString("enderdest_uf").trim(),
                    w_ide_xtpemis,
                    map.get(i).getString("infnfe_statusnfe").trim(),
                    map.get(i).getString("infnfe_danfeimpresso").trim(),
                    map.get(i).getString("infnfe_danfexmlenviado").trim().isEmpty() ? "Não" : map.get(i).getString("infnfe_danfexmlenviado").trim(),
                    map.get(i).getString("infnfe_tituloregistrado").trim().isEmpty() ? "Não" : map.get(i).getString("infnfe_tituloregistrado").trim(),
                    map.get(i).getString("infnfe_boletoenviado").trim().isEmpty()
                    || map.get(i).getString("infnfe_tituloregistrado").trim().equals("Não")
                    || map.get(i).getString("infnfe_tituloregistrado").trim().isEmpty()
                    ? "Não" : map.get(i).getString("infnfe_boletoenviado").trim(),
                    map.get(i).getString("infnfe_xmlexportado").trim()
                });
            }
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
            cursor = Cursor.getDefaultCursor();
            jDktInputData.setCursor(cursor);

        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void PesquisaTable(String wCodigo) {
        try {
            Robot robot = new Robot();
            int prow = jTable1.getRowCount();
            int pcol = jTable1.getColumnCount() - 1;
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < prow; i++) {
                if (modelo.getValueAt(i, 0).toString().equals(wCodigo)) {
                    Status_Navegacao(i, jTable1.getRowCount());
                    jTable1.setRowSelectionInterval(i, i);
                    jTable1.changeSelection(i, 0, true, true);
                    jTable1.editCellAt(i, 0);
                    Component celula = jTable1.getEditorComponent();
                    if (celula != null) {
                        celula.requestFocus();
                        robot.keyPress(KeyEvent.VK_ESCAPE);
                    }
                    jTable1.getSelectionModel().setSelectionInterval(i, i);
                    break;
                }
            }
        } catch (Exception ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaProdutosServicosNFe() {
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        jTable2.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(85);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(153);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(55);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(centralizado);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(35);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(centralizado);
        jTable2.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(35);
        jTable2.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(centralizado);
        jTable2.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(6).setPreferredWidth(55);
        jTable2.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(7).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(7).setPreferredWidth(70);
        jTable2.getTableHeader().getColumnModel().getColumn(7).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(8).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(8).setPreferredWidth(70);
        jTable2.getTableHeader().getColumnModel().getColumn(8).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(9).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(9).setPreferredWidth(67);
        jTable2.getTableHeader().getColumnModel().getColumn(9).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(10).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(10).setPreferredWidth(67);
        jTable2.getTableHeader().getColumnModel().getColumn(10).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(11).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(11).setPreferredWidth(67);
        jTable2.getTableHeader().getColumnModel().getColumn(11).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(12).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(12).setPreferredWidth(55);
        jTable2.getTableHeader().getColumnModel().getColumn(12).setCellRenderer(direita);
        jTable2.getTableHeader().getColumnModel().getColumn(13).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(13).setPreferredWidth(55);
        jTable2.getTableHeader().getColumnModel().getColumn(13).setCellRenderer(direita);
        PreencheTabelaProdutosServicosNFe();
    }

    public void PreencheTabelaProdutosServicosNFe() {
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
        modelo.setNumRows(0);
        try {
            String formatoData = "dd/MM/yyyy";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);
            String www_icms00_vbc = "";
            String www_icms00_vicms = "";
            String www_ipitrib_vipi = "";
            String www_icms00_picms = "";
            String www_ipitrib_pipi = "";
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeDetProdController.List(tf_InfNFe_NNF.getText().toString().trim(), null);
            for (int i = 0; i < map.size(); i++) {
                www_icms00_vbc = "";
                www_icms00_vicms = "";
                www_ipitrib_vipi = "";
                www_icms00_picms = "";
                www_ipitrib_pipi = "";
                if (map.get(i).getString("det_icms_cst").trim().equals("0")) {
                    if (map.get(i).getString("det_icms00_vbc") != null) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms00_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms00_vicms") != null) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms00_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms00_picms") != null) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms00_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("10")) {
                    if (map.get(i).getString("det_icms10_vbc") != null
                            && map.get(i).getString("det_icms10_vbc").trim().length() > 0) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms10_vicms") != null
                            && map.get(i).getString("det_icms10_vicms").trim().length() > 0) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms10_picms") != null
                            && map.get(i).getString("det_icms10_picms").trim().length() > 0) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms10_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("20")) {
                    if (map.get(i).getString("det_icms20_vbc") != null
                            && map.get(i).getString("det_icms20_vbc").trim().length() > 0) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms20_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms20_vicms") != null
                            && map.get(i).getString("det_icms20_vicms").trim().length() > 0) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms20_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms20_picms") != null
                            && map.get(i).getString("det_icms20_picms").trim().length() > 0) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms20_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("30")) {
                    if (map.get(i).getString("det_icms30_vbcst") != null
                            && map.get(i).getString("det_icms30_vbcst").trim().length() > 0) {
                        // www_icms00_vbc = f.getNumeroFormatado2cd(con_default.resultset.getString("det_icms30_vbcst").trim());
                    }
                    if (map.get(i).getString("det_icms30_vicmsst") != null
                            && map.get(i).getString("det_icms30_vicmsst").trim().length() > 0) {
                        // www_icms00_vicms = f.getNumeroFormatado2cd(con_default.resultset.getString("det_icms30_vicmsst").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms30_picmsst") != null
                            && map.get(i).getString("det_icms30_picmsst").trim().length() > 0) {
                        //  www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(con_default.resultset.getString("det_icms30_picmsst")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("40")) {
                    if (map.get(i).getString("det_icms40_vicmsdeson") != null
                            && map.get(i).getString("det_icms40_vicmsdeson").trim().length() > 0) {
                        //  www_icms00_vicms = f.getNumeroFormatado2cd(con_default.resultset.getString("det_icms40_vicmsdeson").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("51")) {
                    if (map.get(i).getString("det_icms51_vbc") != null
                            && map.get(i).getString("det_icms51_vbc").trim().length() > 0) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms51_vicms") != null) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms51_picms") != null
                            && map.get(i).getString("det_icms51_picms").trim().length() > 0) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms51_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("60")) {
                    if (map.get(i).getString("det_icms60_vbcstret") != null
                            && map.get(i).getString("det_icms60_vbcstret").trim().length() > 0) {
                        // www_icms00_vbc = f.getNumeroFormatado2cd(con_default.resultset.getString("det_icms60_vbcstret").trim());
                    }
                    if (map.get(i).getString("det_icms60_vicmsstret") != null
                            && map.get(i).getString("det_icms60_vicmsstret").trim().length() > 0) {
                        // www_icms00_vicms = f.getNumeroFormatado2cd(con_default.resultset.getString("det_icms60_vicmsstret").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null //&& map.get(i).getString("det_ipitrib_vipidet_ipitrib_vipi") != null
                            //&& map.get(i).getString("det_ipitrib_vipidet_ipitrib_vipi").trim().length() > 0
                            ) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("70")) {
                    if (map.get(i).getString("det_icms70_vbc") != null
                            && map.get(i).getString("det_icms70_vbc").trim().length() > 0) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms70_vicms") != null
                            && map.get(i).getString("det_icms70_vicms").trim().length() > 0) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms70_picms") != null
                            && map.get(i).getString("det_icms70_picms").trim().length() > 0) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms70_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_icms_cst").trim().equals("90")) {
                    if (map.get(i).getString("det_icms90_vbc") != null
                            && map.get(i).getString("det_icms90_vbc").trim().length() > 0) {
                        www_icms00_vbc = f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vbc").trim());
                    }
                    if (map.get(i).getString("det_icms90_vicms") != null
                            && map.get(i).getString("det_icms90_vicms").trim().length() > 0) {
                        www_icms00_vicms = f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vicms").trim());
                    }
                    if (map.get(i).getString("det_ipitrib_vipi") != null
                            && map.get(i).getString("det_ipitrib_vipi").trim().length() > 0) {
                        www_ipitrib_vipi = f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi").trim());
                    }
                    if (map.get(i).getString("det_icms90_picms") != null
                            && map.get(i).getString("det_icms90_picms").trim().length() > 0) {
                        www_icms00_picms = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_icms90_picms")), "0.00").replace(".", ","));
                    }
                    if (map.get(i).getString("det_ipitrib_pipi") != null
                            && map.get(i).getString("det_ipitrib_pipi").trim().length() > 0) {
                        www_ipitrib_pipi = f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("det_ipitrib_pipi")), "0.00").replace(".", ","));
                    }
                }
                if (map.get(i).getString("det_prod_item") != null) {
                    String w_ncm = "";
                    String w_cfop = "";
                    String w_ucom = "";
                    if (map.get(i).getString("det_prod_ncm") != null
                            && map.get(i).getString("det_prod_ncm").trim().length() > 0) {
                        w_ncm = formata.FormatNumberInt(map.get(i).getInt("det_prod_ncm"), "00000000");
                    }
                    if (map.get(i).getString("det_prod_cfop") != null) {
                        w_cfop = map.get(i).getString("det_prod_cfop").trim();
                    }
                    if (map.get(i).getString("det_prod_ucom") != null) {
                        w_ucom = map.get(i).getString("det_prod_ucom").trim();
                    }
                    modelo.addRow(new Object[]{
                        map.get(i).getString("det_prod_item").trim(),
                        map.get(i).getString("det_prod_cprod").trim(),
                        map.get(i).getString("det_prod_xprod").trim(),
                        w_ncm,
                        w_cfop,
                        w_ucom,
                        f.getNumeroFormatado4cd(map.get(i).getString("det_prod_qcom")),
                        f.getFormated_2(map.get(i).getDouble("det_prod_vuncom")),
                        f.getFormated_2(map.get(i).getDouble("det_prod_vprod")),
                        www_icms00_vbc,
                        www_icms00_vicms,
                        www_ipitrib_vipi,
                        www_icms00_picms,
                        www_ipitrib_pipi
                    });
                }
            }
            jTable2.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        } catch (ParseException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaTransporteVolNFe() {
        jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        jTable3.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable3.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(130);
        jTable3.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(135);
        jTable3.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(135);
        jTable3.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(135);
        jTable3.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(135);
        jTable3.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(esquerda);
        jTable3.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(aligntableheadercenter);
        jTable3.getTableHeader().getColumnModel().getColumn(6).setPreferredWidth(135);
        jTable3.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(esquerda);
        PreencheTabelaTransporteVolNFe();
    }

    public void PreencheTabelaTransporteVolNFe() {
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        modelo.setNumRows(0);
        try {
            String formatoData = "dd/MM/yyyy";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);
            int reg = 1;
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeTranspVolController.List(tf_InfNFe_NNF.getText().toString().trim());
            for (int i = 0; i < map.size(); i++) {
                String sReg = new Integer(reg).toString();
                if (map.get(i).getString("transp_vol_qvol") != null) {
                    modelo.addRow(new Object[]{
                        sReg,
                        map.get(i).getString("transp_vol_qvol").trim(),
                        map.get(i).getString("transp_vol_esp").trim(),
                        map.get(i).getString("transp_vol_marca").trim(),
                        map.get(i).getString("transp_vol_nvol").trim(),
                        formata.FormatNumber(Double.parseDouble(map.get(i).getString("transp_vol_pesol")), "0.000").replace(".", ","),
                        formata.FormatNumber(Double.parseDouble(map.get(i).getString("transp_vol_pesob")), "0.000").replace(".", ",")
                    });
                    reg++;
                }
            }
            jTable3.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaCobrancaNFe() {
        jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        jTable4.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable4.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable4.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable4.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable4.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(283);
        jTable4.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable4.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable4.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(283);
        jTable4.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(centralizado);
        jTable4.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable4.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(283);
        jTable4.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(direita);
        PreencheTabelaCobrancaNFe();
    }

    public void PreencheTabelaCobrancaNFe() {
        DefaultTableModel modelo = (DefaultTableModel) jTable4.getModel();
        modelo.setNumRows(0);
        try {
            String formatoData = "dd/MM/yyyy";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);
            int reg = 1;
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeCobrDupController.List(tf_InfNFe_NNF.getText().toString().trim());
            for (int i = 0; i < map.size(); i++) {
                String sReg = new Integer(reg).toString();
                if (map.get(i).getString("cobr_dup_ndup") != null) {
                    modelo.addRow(new Object[]{
                        sReg,
                        map.get(i).getString("cobr_dup_ndup").trim(),
                        map.get(i).getString("cobr_dup_dvenc"),
                        f.getNumeroFormatado2cd(formata.FormatNumber(Double.parseDouble(map.get(i).getString("cobr_dup_vdup")), "0.00").replace(".", ","))
                    });
                    reg++;
                }
            }
            jTable4.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        } catch (ParseException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaHistoricoEventosNFe() {
        jTable5.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableCellRenderer wColor = new ColorirEventoNFe();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheaderleft = (TableCellRenderer) new aligntableheaderleft();
        TableCellRenderer aligntableheaderright = (TableCellRenderer) new aligntableheaderright();
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        jTable5.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable5.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable5.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable5.getColumnModel().getColumn(0).setCellRenderer(wColor);
        jTable5.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable5.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable5.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable5.getColumnModel().getColumn(1).setCellRenderer(wColor);
        jTable5.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable5.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(590);
        jTable5.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable5.getColumnModel().getColumn(2).setCellRenderer(wColor);
        jTable5.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable5.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable5.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable5.getColumnModel().getColumn(3).setCellRenderer(wColor);
        jTable5.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        jTable5.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        jTable5.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        jTable5.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        PreencheTabelaHistoricoEventosNFe();
    }

    public void PreencheTabelaHistoricoEventosNFe() {
        DefaultTableModel modelo = (DefaultTableModel) jTable5.getModel();
        modelo.setNumRows(0);
        try {
            String formatoData = "dd/MM/yyyy";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);
            int reg = 1;
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeInfProtController.List(tf_InfNFe_NNF.getText().toString().trim());
            for (int i = 0; i < map.size(); i++) {
                String sReg = new Integer(reg).toString();
                String nProt = "";
                if (map.get(i).getString("protnfe_sequencia") == null) {
                    sReg = "1";
                } else {
                    sReg = map.get(i).getString("protnfe_sequencia").trim();
                }
                if (map.get(i).getString("infprot_nprot") == null) {
                    nProt = "";
                } else {
                    nProt = map.get(i).getString("infprot_nprot").trim();
                }
                if (nProt.trim().length() > 0) {
                    modelo.addRow(new Object[]{
                        nProt,
                        map.get(i).getString("infprot_dhrecbto"),
                        map.get(i).getString("infprot_xmotivo").trim(),
                        sReg,
                        map.get(i).getString("infprot_xcorrecao").trim(),
                        map.get(i).getString("infprot_xjust").trim()
                    });
                }

            }
            jTable5.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaNotasConhecimentosReferenciados() {
        jTable6.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        final JComboBox jcbx_tipo = new JComboBox();
        jcbx_tipo.removeAllItems();
        jcbx_tipo.addItem("NF-e");
        jcbx_tipo.addItem("CT-e");
        jcbx_tipo.addItem("Nota Fiscal");
        jTable6.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable6.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable6.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        TableColumn jcbx_tipoColumn = jTable6.getColumnModel().getColumn(1);
        jcbx_tipoColumn.setCellEditor(new DefaultCellEditor(jcbx_tipo));
        jTable6.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(358);
        jTable6.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable6.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable6.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(130);
        jTable6.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(6).setPreferredWidth(50);
        jTable6.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(7).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(7).setPreferredWidth(50);
        jTable6.getTableHeader().getColumnModel().getColumn(7).setCellRenderer(esquerda);
        jTable6.getTableHeader().getColumnModel().getColumn(8).setHeaderRenderer(aligntableheadercenter);
        jTable6.getTableHeader().getColumnModel().getColumn(8).setPreferredWidth(70);
        jTable6.getTableHeader().getColumnModel().getColumn(8).setCellRenderer(esquerda);
        PreencheTabelaNotasConhecimentosReferenciados();
    }

    public void PreencheTabelaNotasConhecimentosReferenciados() {
        DefaultTableModel modelo = (DefaultTableModel) jTable6.getModel();
        modelo.setNumRows(0);
        try {
            int reg = 1;
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeNfRefController.List(tf_InfNFe_NNF.getText().toString().trim());
            for (int i = 0; i < map.size(); i++) {
                String sReg = new Integer(reg).toString();
                String w_tipo = "";
                String w_refnfe = "";
                String w_cuf = "";
                String w_aamm = "";
                String w_cnpj = "";
                String w_mod = "";
                String w_serie = "";
                String w_nnf = "";
                if (map.get(i).getString("nfref_tipo") != null) {
                    w_tipo = map.get(i).getString("nfref_tipo").trim();
                }
                if (map.get(i).getString("nfref_refnfe") != null
                        && map.get(i).getString("nfref_refnfe").trim().length() > 0) {
                    w_refnfe = f.getIdNFeFormatado(map.get(i).getString("nfref_refnfe"));
                }
                if (map.get(i).getString("nfref_cuf") != null) {
                    w_cuf = map.get(i).getString("nfref_cuf");
                }
                if (map.get(i).getString("nfref_aamm") != null
                        && map.get(i).getString("nfref_aamm").trim().length() > 0) {
                    w_aamm = formata.FormatNumberInt(map.get(i).getInt("nfref_aamm"), "0000").substring(2, 4) + "/"
                            + formata.FormatNumberInt(map.get(i).getInt("nfref_aamm"), "0000").substring(0, 2);
                }
                if (map.get(i).getString("nfref_cnpj") != null) {
                    w_cnpj = map.get(i).getString("nfref_cnpj");
                }
                if (map.get(i).getString("nfref_mod") != null
                        && map.get(i).getString("nfref_mod").trim().length() > 0) {
                    w_mod = formata.FormatNumberInt(map.get(i).getInt("nfref_mod"), "00");
                }
                if (map.get(i).getString("nfref_serie") != null
                        && map.get(i).getString("nfref_serie").trim().length() > 0) {
                    w_serie = formata.FormatNumberInt(map.get(i).getInt("nfref_serie"), "000");
                }
                if (map.get(i).getString("nfref_nnf") != null
                        && map.get(i).getString("nfref_nnf").trim().length() > 0) {
                    w_nnf = formata.FormatNumberInt(map.get(i).getInt("nfref_nnf"), "0");
                }
                modelo.addRow(new Object[]{
                    sReg,
                    w_tipo.trim(),
                    w_refnfe,
                    w_cuf,
                    w_aamm,
                    w_cnpj,
                    w_mod,
                    w_serie,
                    w_nnf
                });
                reg++;

            }
            jTable6.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public void MontaTabelaInformacoesPagamento() {
        jTable7.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        final JComboBox jcbx_tPag = new JComboBox();
        jcbx_tPag.removeAllItems();
        jcbx_tPag.addItem("0 - Dinheiro");
        jcbx_tPag.addItem("2 - Cheque");
        jcbx_tPag.addItem("3 - Cartão de Crédito");
        jcbx_tPag.addItem("4 - Cartão de Débito");
        jcbx_tPag.addItem("5 - Crédito Loja");
        jcbx_tPag.addItem("10 - Vale Alimentação");
        jcbx_tPag.addItem("11 - Vale Refeição");
        jcbx_tPag.addItem("12 - Vale Presente");
        jcbx_tPag.addItem("13 - Vale Combustível");
        jcbx_tPag.addItem("15 - Boleto Bancário");
        jcbx_tPag.addItem("90 - Sem Pagamento");
        jcbx_tPag.addItem("99 - Outros");
        final JComboBox jcbx_fPag = new JComboBox();
        jcbx_fPag.removeAllItems();
        jcbx_fPag.addItem("0 - Pagamento à Vista");
        jcbx_fPag.addItem("1 - Pagamento à Prazo");
        jTable7.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable7.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable7.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable7.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable7.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(283);
        jTable7.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        TableColumn jcbx_tipoColumn = jTable7.getColumnModel().getColumn(1);
        jcbx_tipoColumn.setCellEditor(new DefaultCellEditor(jcbx_tPag));
        jTable7.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable7.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(283);
        jTable7.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(direita);
        jTable7.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable7.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(283);
        jTable7.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable7.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable7.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(283);
        jTable7.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        TableColumn jcbx_formaColumn = jTable7.getColumnModel().getColumn(4);
        jcbx_formaColumn.setCellEditor(new DefaultCellEditor(jcbx_fPag));

        PreencheTabelaInformacoesPagamento();
    }

    public void PreencheTabelaInformacoesPagamento() {
        DefaultTableModel modelo = (DefaultTableModel) jTable7.getModel();
        modelo.setNumRows(0);
        try {
            int reg = 1;
            ArrayList<JSONObject> map = new ArrayList<JSONObject>();
            map = NfeInformacoesPagamentoController.List(tf_InfNFe_NNF.getText().toString().trim());
            for (int i = 0; i < map.size(); i++) {
                String sReg = new Integer(reg).toString();
                String w_tPag = "";
                String w_vPag = "";
                String w_dPag = "";
                String w_fPag = "";
                if (map.get(i).getString("pag_tpag") != null) {
                    w_tPag = map.get(i).getString("pag_tpag").trim();
                }
                if (map.get(i).getString("pag_vpag") != null
                        && map.get(i).getString("pag_vpag").trim().length() > 0) {
                    w_vPag = f.getNumeroFormatado2cd(map.get(i).getString("pag_vpag").trim());
                }
                if (map.get(i).getString("pag_indpag") != null) {
                    w_fPag = map.get(i).getString("pag_indpag").trim();
                    if (w_fPag.equals("0")) {
                        w_fPag = "Pagamento à Vista";
                    }
                    if (w_fPag.equals("1")) {
                        w_fPag = "Pagamento à Prazo";
                    }
                }
                modelo.addRow(new Object[]{
                    sReg,
                    w_tPag,
                    w_vPag,
                    w_dPag,
                    w_fPag
                });
                reg++;

            }
            jTable7.getSelectionModel().setSelectionInterval(0, 0);
        } catch (SQLException ex) {
            returnParams(new String[]{ex.getMessage()});

        } catch (ParseException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public String retornacaracterendereco(String Valor) {
        String retornacaracterendereco = "";
        for (int i = 0; i < Valor.trim().length(); i++) {
            retornacaracterendereco = Valor.trim().substring(i, i + 1);
            if (Valor.trim().substring(i, i + 1).equals(",")) {
                retornacaracterendereco = Valor.trim().substring(0, i);
                break;
            }
        }
        return retornacaracterendereco;
    }

    public String retornanumero(String Valor) {
        String retorna = "";
        for (int i = 0; i < Valor.trim().length(); i++) {
            retorna = Valor.trim().substring(i, i + 1);
            if (Valor.trim().substring(i, i + 1).equals(",")) {
                retorna = Valor.trim().substring(i + 1, Valor.trim().length());
                break;
            }
        }
        return retorna;
    }

    public Timestamp stringToTimeStamp(String strDate) throws Exception {
        SimpleDateFormat formated = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String strDateBegin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(formated.parse(strDate));
        Timestamp datetimeBegin = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDateBegin).getTime());
        return datetimeBegin;
    }

    public void render() {
        jjTable1MouseClicked();
    }

    public void retornaRegistro(
            String wCol7, String wDhRecbto, Integer wLin7, String wCol6, NFECBdto nfecb) {
        if (wLin7 == null) {
            wLin7 = jTable1.getSelectedRow();
        }
        if (wCol6 == null) {
            wCol6 = "";
        }
        //System.out.println(wCol7);
        if (wCol7.equals("Exportada")) {
            //jTable1.setValueAt("Sim", jTable1.getSelectedRow(), 9);
            jTable1.setValueAt("Sim", wLin7, 12);
        } else if (wCol7.equals("Impressa")) {
            //jTable1.setValueAt("Sim", jTable1.getSelectedRow(), 8);
            jTable1.setValueAt("Sim", wLin7, 8);
        } else {
            if (wDhRecbto.trim().length() > 0) {
                jTable1.setValueAt(wDhRecbto, wLin7, 3);
            }
            if (wCol7.length() > 0) {
                jTable1.setValueAt(wCol7, wLin7, 7);
            }
        }
        if (wCol6.length() > 0) {
            jTable1.setValueAt(wCol6, wLin7, 6);
        }
        //Inicio alteração: 02/12/2025
        if (nfecb != null) {
            if (nfecb.getINFNFE_DANFEXMLENVIADO() != null
                    && !nfecb.getINFNFE_DANFEXMLENVIADO().trim().isEmpty()) {
                jTable1.setValueAt(nfecb.getINFNFE_DANFEXMLENVIADO().trim(), wLin7, 9);
            }
            if (nfecb.getINFNFE_TITULOREGISTRADO() != null
                    && !nfecb.getINFNFE_TITULOREGISTRADO().trim().isEmpty()) {
                jTable1.setValueAt(nfecb.getINFNFE_TITULOREGISTRADO().trim(), wLin7, 10);
            }
            if (nfecb.getINFNFE_BOLETOENVIADO() != null
                    && !nfecb.getINFNFE_BOLETOENVIADO().trim().isEmpty()) {
                jTable1.setValueAt(nfecb.getINFNFE_BOLETOENVIADO().trim(), wLin7, 11);
            }
        }
        //Fim alteração: 02/12/2025
        jjTable1MouseClicked();
    }

    public void retornaRegistro2(
            String wOpcao,
            String wCol1,
            String wCol2,
            String wCol3,
            String wCol4,
            String wCol5,
            String wCol6,
            String wCol7,
            String wCol8) {
        if (wOpcao.trim().equals("Inclui")) {
            DefaultTableModel modelo = (DefaultTableModel) jTable6.getModel();
            Vector<String> wVector = new Vector<String>();
            modelo.addRow(wVector);
            //MontaTabelaNotasConhecimentosReferenciados();
            jTable6.updateUI(); // outro código achado na net que tbm não funcionou.
            jTable6.clearSelection();
            jTable6.changeSelection(jTable6.getRowCount() - 1, 0, false, false);
            jTable6.setValueAt(jTable6.getRowCount() - 1, jTable6.getSelectedRow(), 0);
        }
        jTable6.setValueAt(wCol1, jTable6.getSelectedRow(), 1);
        jTable6.setValueAt(wCol2, jTable6.getSelectedRow(), 2);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 3);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 4);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 5);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 6);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 7);
        jTable6.setValueAt("", jTable6.getSelectedRow(), 8);

    }

    public void retornaRegistro3(
            String wOpcao,
            String wCol1,
            String wCol2,
            String wCol3,
            String wCol4,
            String wCol5,
            String wCol6,
            String wCol7,
            String wCol8) {
        if (wOpcao.trim().equals("Inclui")) {
            DefaultTableModel modelo = (DefaultTableModel) jTable6.getModel();
            Vector<String> wVector = new Vector<String>();
            modelo.addRow(wVector);
            jTable6.updateUI(); // outro código achado na net que tbm não funcionou.
            jTable6.clearSelection();
            jTable6.changeSelection(jTable6.getRowCount() - 1, 0, false, false);
            jTable6.setValueAt(jTable6.getRowCount() - 1, jTable6.getSelectedRow(), 0);
        }
        jTable6.setValueAt(wCol1, jTable6.getSelectedRow(), 1);
        jTable6.setValueAt(wCol2, jTable6.getSelectedRow(), 2);
        jTable6.setValueAt(wCol3, jTable6.getSelectedRow(), 3);
        jTable6.setValueAt(wCol4, jTable6.getSelectedRow(), 4);
        jTable6.setValueAt(wCol5, jTable6.getSelectedRow(), 5);
        jTable6.setValueAt(wCol6, jTable6.getSelectedRow(), 6);
        jTable6.setValueAt(wCol7, jTable6.getSelectedRow(), 7);
        jTable6.setValueAt(wCol8, jTable6.getSelectedRow(), 8);

    }

    public void returnParams(String[] paramrs) {
        if (paramrs[0] != null && paramrs[0].trim().length() > 0) {
            String error = "<html><font color=\"black\">" + statusbar.getText() + "</font><font color=\"red\">" + " Erro: " + paramrs[0] + "</font></html>";
            statusbar.setText(error);
        } else {
            statusbar.setText("");
        }
    }

    public void state() {
        //limpa os campos  
        Component[] components;
        components = jPanel16.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel13.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel22.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel2.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel23.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel15.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel17.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel20.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel21.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel24.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel30.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel27.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel28.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel10.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JTextArea) {
                ((JTextArea) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel37.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel38.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel30.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel30.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        components = jPanel33.getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getItemCount() > 0) {
                    ((JComboBox) c).setSelectedIndex(0);
                }
            }
        }
        DefaultTableModel modelo1 = (DefaultTableModel) jTable5.getModel();
        modelo1.setNumRows(0);
        DefaultTableModel modelo2 = (DefaultTableModel) jTable6.getModel();
        modelo2.setNumRows(0);
        DefaultTableModel modelo3 = (DefaultTableModel) jTable7.getModel();
        modelo3.setNumRows(0);
        DefaultTableModel modelo4 = (DefaultTableModel) jTable3.getModel();
        modelo4.setNumRows(0);
        DefaultTableModel modelo5 = (DefaultTableModel) jTable4.getModel();
        modelo5.setNumRows(0);
        DefaultTableModel modelo6 = (DefaultTableModel) jTable2.getModel();
        modelo6.setNumRows(0);

    }

    public final void LoadFM(JSONObject jsonNFe) throws ParseException, SQLException {

        disableFields();
        jP_HitoricosEventos.setVisible(false);
        jP_Contigencia.setVisible(false);
        jTabbedPane1.setEnabledAt(11, false);
        jTabbedPane1.setEnabledAt(12, false);
        jTabbedPane1.setEnabledAt(13, false);
        botao_alterar.setEnabled(false);
        botao_validar.setEnabled(false);
        botao_transmitir.setEnabled(false);
        botao_previsualizardanfe.setEnabled(false);
        botao_imprimirdanfe.setEnabled(false);
        botao_consultanasefaz.setEnabled(false);
        botao_consultarecibo.setEnabled(false);
        botao_enviarEmail.setEnabled(false);
        botao_registrarTitulo.setEnabled(false);
        botao_gravar.setEnabled(false);
        botao_exportar.setEnabled(false);
        botao_cancelanfe.setEnabled(false);
        botao_chavedeacesso.setEnabled(false);
        botao_cartacorrecao.setEnabled(false);
        //botao_consultarecibo.setEnabled(false);
        botao_excluir.setEnabled(false);
        jButton24.setEnabled(false);
        jButton25.setEnabled(false);
        jButton26.setEnabled(false);
        if (wOmVErro == true) {
            wOmVErro = false;
        }
        jCheck_InformaCodigoNumerico.setVisible(true);
        jLb_Ide_cNF.setVisible(true);
        jLb_Ide_cDV.setVisible(true);
        tf_Ide_cNF.setVisible(true);
        tf_Ide_cDV.setVisible(true);
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Autorizada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Processamento na SEFAZ")
                //|| jsonNFe.getString("infnfe_statusnfe").trim().equals("Rejeitada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Denegada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Cancelada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Corrigida")) {
            if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Autorizada")) {
                if (jsonNFe.getString("xml_autorizado") != null
                        && jsonNFe.getString("xml_autorizado").trim().length() > 0) {
                    botao_imprimirdanfe.setEnabled(true);
                    botao_enviarEmail.setEnabled(true); //31/03/2026
                    if (!jsonNFe.getString("infnfe_danfexmlenviado").trim().equals("Sim")
                            && jsonNFe.getString("ide_tpnf").trim().equals("1")) {
                        botao_enviarEmail.setEnabled(true);
                    }
                    if (!jsonNFe.getString("infnfe_danfexmlenviado").trim().equals("Não")
                            && jsonNFe.getString("ide_tpnf").trim().equals("0")) {
                        botao_enviarEmail.setEnabled(true);
                    }
                }
                botao_consultanasefaz.setEnabled(true);
                //botao_consultarecibo.setEnabled(true);
                botao_exportar.setEnabled(true);
                botao_cancelanfe.setEnabled(true);
                botao_chavedeacesso.setEnabled(true);
                botao_cartacorrecao.setEnabled(true);
                //botao_previsualizardanfe.setEnabled(true);
            }
            // Início Alteração: 07/06/2022
            if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Corrigida")) {
                botao_cancelanfe.setEnabled(true);
            }
            // Fim Alteração
            if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Cancelada")
                    || jsonNFe.getString("infnfe_statusnfe").trim().equals("Corrigida")) {
                //jTabbedPane1.setEnabledAt(11, true);
                botao_consultanasefaz.setEnabled(false);
                //botao_consultarecibo.setEnabled(true);
                botao_exportar.setEnabled(true);
                botao_chavedeacesso.setEnabled(true);
            }
            jP_HitoricosEventos.setVisible(true);
        }
        if (!jsonNFe.getString("infnfe_statusnfe").trim().equals("Autorizada")) {
            botao_alterar.setEnabled(true);
        }

        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Validada")) {
            jCheck_InformaCodigoNumerico.setVisible(true);
            jLb_Ide_cNF.setVisible(true);
            tf_Ide_cNF.setVisible(true);
            jLb_Ide_cDV.setVisible(true);
            tf_Ide_cDV.setVisible(true);
            botao_previsualizardanfe.setEnabled(true);
            botao_exportar.setEnabled(true);
            botao_chavedeacesso.setEnabled(true);
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Assinada")) {
            jCheck_InformaCodigoNumerico.setVisible(true);
            jLb_Ide_cNF.setVisible(true);
            tf_Ide_cNF.setVisible(true);
            jLb_Ide_cDV.setVisible(true);
            tf_Ide_cDV.setVisible(true);
            botao_previsualizardanfe.setEnabled(true);
            botao_transmitir.setEnabled(true);
            botao_exportar.setEnabled(true);
            botao_chavedeacesso.setEnabled(true);
            botao_consultanasefaz.setEnabled(true);
            //botao_consultarecibo.setEnabled(true);
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Digitação")) {
            botao_validar.setEnabled(true);
            botao_exportar.setEnabled(false);
            botao_excluir.setEnabled(true);
            jCheck_InformaCodigoNumerico.setVisible(false);
            jLb_Ide_cNF.setVisible(false);
            tf_Ide_cNF.setVisible(false);
            jLb_Ide_cDV.setVisible(false);
            tf_Ide_cDV.setVisible(false);
            jButton29.setEnabled(true);
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Rejeitada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Validada")
                || jsonNFe.getString("infnfe_statusnfe").trim().equals("Assinada")) {
            botao_alterar.setEnabled(true);
            botao_excluir.setEnabled(true);
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Denegada")) {
            botao_alterar.setEnabled(true);
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Processamento na SEFAZ")) {
            botao_alterar.setEnabled(true);
            botao_consultanasefaz.setEnabled(true);
            //botao_consultarecibo.setEnabled(true);
        }
        if (jsonNFe.getString("xml_autorizado") == null || jsonNFe.getString("xml_autorizado").trim().length() <= 0) {
            botao_alterar.setEnabled(true);
            //botao_exportar.setEnabled(false); '31/01/2023
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Cancelada")) {
            botao_alterar.setEnabled(false);
        }
        botao_exportar.setEnabled(true);
        //Preenche campos. Geral
        tf_InfNFe_NNF.setText(formata.FormatNumberInt(jsonNFe.getInt("ide_nnf"), "000000000"));
        tf_Infnfe_statusnfe.setText(jsonNFe.getString("infnfe_statusnfe").trim());
        tf_InfNFe_Id.setText("");
        SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
        if (jsonNFe.getString("infnfe_id") != null
                && jsonNFe.getString("infnfe_id").trim().length() > 0) {
            tf_InfNFe_Id.setText(f.getIdNFeFormatado(jsonNFe.getString("infnfe_id").trim().replace("NFe", "")));
        }
        tf_InfNFe_Versao.setText(jsonNFe.getString("infnfe_versao").trim());
        //Preenche campos. Dados da NF-e
        tf_Ide_Mod.setText(jsonNFe.getString("ide_mod").trim());
        tf_Ide_Serie.setText(formata.FormatNumberInt(jsonNFe.getInt("ide_serie"), "000"));
        tf_Ide_NNF.setText(formata.FormatNumberInt(jsonNFe.getInt("ide_nnf"), "000000000"));
        System.out.println(jsonNFe.getString("ide_dhemi"));
        tf_Ide_DhEmi.setText(jsonNFe.getString("ide_dhemi"));
        System.out.println("Data de Contingência: " + jsonNFe.getString("ide_dhcont"));
        tf_Ide_DhCont.setText(jsonNFe.getString("ide_dhcont"));
        tf_Ide_xjust.setText(jsonNFe.getString("ide_xjust"));
        tf_Ide_cNF.setText("");
        if (jsonNFe.getString("ide_cnf") != null
                && jsonNFe.getString("ide_cnf").trim().length() > 0) {
            tf_Ide_cNF.setText(formata.FormatNumberInt(jsonNFe.getInt("ide_cnf"), "00000000"));
        }
        tf_Ide_cDV.setText("");
        if (jsonNFe.getString("ide_cdv") != null) {
            tf_Ide_cDV.setText(jsonNFe.getString("ide_cdv"));
        }
        tf_Ide_DhSaiEnt.setText(jsonNFe.getString("ide_dhsaient"));
        PercorrejcbxNum(jcbx_Ide_tpNF, jsonNFe.getString("ide_tpnf").trim(), 1);
        //PercorrejcbxNum(jcbx_Ide_indPag, jsonNFe.getString("ide_indpag").trim(), 1);
        PercorrejcbxNum(jcbx_Ide_tpEmis, jsonNFe.getString("ide_tpemis").trim(), 1);

        PercorrejcbxNum(jcbx_Ide_finNFe, jsonNFe.getString("ide_finnfe").trim(), 1);

        if (jsonNFe.has("ide_tpnfdebito") && jsonNFe.get("ide_tpnfdebito") != JSONObject.NULL) {
            String valor = jsonNFe.getString("ide_tpnfdebito").trim();
            if (!valor.isEmpty()) {
                int tpDebitoInt = Integer.parseInt(valor);
                if (tpDebitoInt >= 1) {
                    String tpDebito = String.format("%02d", tpDebitoInt);
                    PercorrejcbxNum(jcbx_Ide_tpNFDebito, tpDebito, 2);
                }
            }
        }

        if (jsonNFe.has("ide_tpnfcredito") && jsonNFe.get("ide_tpnfcredito") != JSONObject.NULL) {
            String valor = jsonNFe.getString("ide_tpnfcredito").trim();
            if (!valor.isEmpty()) {
                int tpCreditoInt = Integer.parseInt(valor);
                if (tpCreditoInt >= 1) {
                    String tpCredito = String.format("%02d", tpCreditoInt);
                    PercorrejcbxNum(jcbx_Ide_tpNFCredito, tpCredito, 2);
                }
            }
        }

        PercorrejcbxNum(jcbx_Ide_tpImp, jsonNFe.getString("ide_tpimp").trim(), 1);
        PercorrejcbxNum(jcbx_Ide_indFinal, jsonNFe.getString("ide_indfinal").trim(), 1);
        PercorrejcbxNum(jcbx_Ide_idDest, jsonNFe.getString("ide_iddest").trim(), 1);
        PercorrejcbxNum(jcbx_Ide_indPres, jsonNFe.getString("ide_indpres").trim(), 1);
        tf_Ide_natOp.setText(jsonNFe.getString("ide_natop").trim());
        PercorrejcbxNum(jcbx_Ide_xUF, "SP", 2);
        tf_Ide_xMunFG.setText("Santa Barbara d'Oeste");
        tf_ide_vNF.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vnf").trim()));
        tf_ide_vICMS.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vicms").trim()));
        tf_ide_vST.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vst").trim()));
        //Preenche campos. Emitente
        tf_Emit_CNPJ.setText("");
        if (jsonNFe.getString("emit_cnpj") != null) {
            tf_Emit_CNPJ.setText(f.getCpfCnpjFormatado(jsonNFe.getString("emit_cnpj")));
        }
        tf_Emit_xNome.setText("");
        if (jsonNFe.getString("emit_xnome") != null) {
            tf_Emit_xNome.setText(jsonNFe.getString("emit_xnome").trim());
        }
        tf_Emit_xFant.setText("");
        if (jsonNFe.getString("emit_xfant") != null) {
            tf_Emit_xFant.setText(jsonNFe.getString("emit_xfant").trim());
        }
        tf_EnderEmit_IE.setText("");
        if (jsonNFe.getString("enderemit_ie") != null
                && jsonNFe.getString("enderemit_ie").trim().length() > 0) {
            tf_EnderEmit_IE.setText(f.getIEFormatado(jsonNFe.getString("enderemit_ie").trim()));
        }
        PercorrejcbxNum(jcbx_EnderEmit_CRT, "", 1);
        if (jsonNFe.getString("enderemit_crt") != null) {
            PercorrejcbxNum(jcbx_EnderEmit_CRT, jsonNFe.getString("enderemit_crt").trim(), 1);
        }
        tf_EnderEmit_xLgr.setText("");
        if (jsonNFe.getString("enderemit_xlgr") != null) {
            tf_EnderEmit_xLgr.setText(jsonNFe.getString("enderemit_xlgr").trim());
        }
        tf_EnderEmit_nro.setText("");
        if (jsonNFe.getString("enderemit_nro") != null) {
            tf_EnderEmit_nro.setText(jsonNFe.getString("enderemit_nro").trim());
        }
        tf_EnderEmit_xBairro.setText("");
        if (jsonNFe.getString("enderemit_xbairro") != null) {
            tf_EnderEmit_xBairro.setText(jsonNFe.getString("enderemit_xbairro").trim());
        }
        tf_EnderEmit_CEP.setText("");
        if (jsonNFe.getString("enderemit_cep") != null
                && jsonNFe.getString("enderemit_cep").trim().length() > 0) {
            tf_EnderEmit_CEP.setText(f.getCEPFormatado(jsonNFe.getString("enderemit_cep").trim()));
        }
        PercorrejcbxNum(jcbx_EnderEmit_xPais, "", 1);
        if (jsonNFe.getString("enderemit_xpais") != null) {
            PercorrejcbxNum(jcbx_EnderEmit_xPais, jsonNFe.getString("enderemit_xpais").trim(), jsonNFe.getString("enderemit_xpais").trim().length());
        }
        PercorrejcbxNum(jcbx_EnderEmit_UF, "", 1);
        if (jsonNFe.getString("enderemit_uf") != null) {
            PercorrejcbxNum(jcbx_EnderEmit_UF, jsonNFe.getString("enderemit_uf").trim(), 2);
        }
        PercorrejcbxNum(jcbx_EnderEmit_xMun, "", 1);
        if (jsonNFe.getString("enderemit_xmun") != null) {
            PercorrejcbxNum(jcbx_EnderEmit_xMun, jsonNFe.getString("enderemit_xmun").trim(), jsonNFe.getString("enderemit_xmun").trim().length());
        }
        tf_EnderEmit_fone.setText("");
        if (jsonNFe.getString("enderemit_fone") != null) {
            tf_EnderEmit_fone.setText(jsonNFe.getString("enderemit_fone").trim());
        }
        //Preenche campos. Destinatario
        if (jsonNFe.getString("dest_cnpj") != null
                && jsonNFe.getString("dest_cnpj").trim().length() <= 11) {
            jcbx_Dest_tpCNPJCPF.setSelectedIndex(1);
            jLabel19.setText("*CPF");
        }
        tf_Dest_CNPJ.setText("");
        if (jsonNFe.getString("dest_cnpj") != null
                && jsonNFe.getString("dest_cnpj").trim().length() > 0) {
            tf_Dest_CNPJ.setText(f.getCpfCnpjFormatado(jsonNFe.getString("dest_cnpj")));
        }
        tf_Dest_xNome.setText("");
        if (jsonNFe.getString("dest_xnome") != null) {
            tf_Dest_xNome.setText(jsonNFe.getString("dest_xnome").trim());
        }
        PercorrejcbxNum(jcbx_EnderDest_indIEDest, "", 1);
        if (jsonNFe.getString("enderdest_indiedest") != null) {
            PercorrejcbxNum(jcbx_EnderDest_indIEDest, jsonNFe.getString("enderdest_indiedest").trim(), 1);
        }
        jcbx_EnderDest_IE.setText("");
        if (jsonNFe.getString("enderdest_ie") != null
                && jsonNFe.getString("enderdest_ie").trim().length() > 0) {
            jcbx_EnderDest_IE.setText(jsonNFe.getString("enderdest_ie").trim());
        }
        tf_EnderDest_xLgr.setText("");
        if (jsonNFe.getString("enderdest_xlgr") != null) {
            tf_EnderDest_xLgr.setText(jsonNFe.getString("enderdest_xlgr").trim());
        }
        tf_EnderDest_nro.setText("");
        if (jsonNFe.getString("enderdest_nro") != null) {
            tf_EnderDest_nro.setText(jsonNFe.getString("enderdest_nro").trim());
        }
        tf_EnderDest_xBairro.setText("");
        if (jsonNFe.getString("enderdest_xbairro") != null) {
            tf_EnderDest_xBairro.setText(jsonNFe.getString("enderdest_xbairro").trim());
        }
        tf_EnderDest_CEP.setText("");
        if (jsonNFe.getString("enderdest_cep") != null
                && jsonNFe.getString("enderdest_cep").trim().length() > 0) {
            tf_EnderDest_CEP.setText(f.getCEPFormatado(jsonNFe.getString("enderdest_cep").trim()));
        }
        PercorrejcbxNum(jcbx_EnderDest_xPais, "", 1);
        if (jsonNFe.getString("enderdest_xpais") != null) {
            PercorrejcbxNum(jcbx_EnderDest_xPais, jsonNFe.getString("enderdest_xpais").trim(), jsonNFe.getString("enderdest_xpais").trim().length());
        }
        PercorrejcbxNum(jcbx_EnderDest_UF, "", 1);
        if (jsonNFe.getString("enderdest_uf") != null) {
            PercorrejcbxNum(jcbx_EnderDest_UF, jsonNFe.getString("enderdest_uf").trim(), 2);
        }
        //String ww = jsonNFe.getString("enderdest_xmun");
        PercorrejcbxNum(jcbx_EnderDest_xMun, "", 1);
        if (jsonNFe.getString("enderdest_xmun") != null
                && jsonNFe.getString("enderdest_xmun").trim().length() > 0) {
            PercorrejcbxNum(jcbx_EnderDest_xMun, jsonNFe.getString("enderdest_xmun").trim(), jsonNFe.getString("enderdest_xmun").trim().length());
        }
        tf_EnderDest_fone.setText("");
        if (jsonNFe.getString("enderdest_fone") != null) {
            tf_EnderDest_fone.setText(jsonNFe.getString("enderdest_fone").trim());
        }
        //Preenche campos. Totais - ICMS
        tf_ICMSTot_vBC.setText("");
        if (jsonNFe.getString("icmstot_vbc") != null
                && jsonNFe.getString("icmstot_vbc").trim().length() > 0) {
            tf_ICMSTot_vBC.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vbc").trim()));
        }
        tf_ICMSTot_vProd.setText("");
        if (jsonNFe.getString("icmstot_vprod") != null
                && jsonNFe.getString("icmstot_vprod").trim().length() > 0) {
            tf_ICMSTot_vProd.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vprod").trim()));
        }
        tf_ICMSTot_vII.setText("");
        if (jsonNFe.getString("icmstot_vii") != null
                && jsonNFe.getString("icmstot_vii").trim().length() > 0) {
            tf_ICMSTot_vII.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vii").trim()));
        }
        tf_ICMSTot_vSeg.setText("");
        if (jsonNFe.getString("icmstot_vseg") != null
                && jsonNFe.getString("icmstot_vseg").trim().length() > 0) {
            tf_ICMSTot_vSeg.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vseg").trim()));
        }
        tf_ICMSTot_vICMS.setText("");
        if (jsonNFe.getString("icmstot_vicms") != null
                && jsonNFe.getString("icmstot_vicms").trim().length() > 0) {
            tf_ICMSTot_vICMS.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vicms").trim()));
        }
        tf_ICMSTot_vFrete.setText("");
        if (jsonNFe.getString("icmstot_vfrete") != null) {
            tf_ICMSTot_vFrete.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vfrete").trim()));
        }
        tf_ICMSTot_vIPI.setText("");
        if (jsonNFe.getString("icmstot_vipi") != null) {
            tf_ICMSTot_vIPI.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vipi").trim()));
        }
        tf_ICMSTot_vDesc.setText("");
        if (jsonNFe.getString("icmstot_vdesc") != null) {
            tf_ICMSTot_vDesc.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vdesc").trim()));
        }
        tf_ICMSTot_vNF.setText("");
        if (jsonNFe.getString("icmstot_vnf") != null) {
            tf_ICMSTot_vNF.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vnf").trim()));;
        }
        tf_ICMSTot_vBCST.setText("");
        if (jsonNFe.getString("icmstot_vbcst") != null) {
            tf_ICMSTot_vBCST.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vbcst").trim()));
        }
        tf_ICMSTot_vPIS.setText("");
        if (jsonNFe.getString("icmstot_vpis") != null) {
            tf_ICMSTot_vPIS.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vpis").trim()));
        }
        tf_ICMSTot_vOutro.setText("");
        if (jsonNFe.getString("icmstot_voutro") != null) {
            tf_ICMSTot_vOutro.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_voutro").trim()));
        }
        tf_ICMSTot_vST.setText("");
        if (jsonNFe.getString("icmstot_vst") != null) {
            tf_ICMSTot_vST.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vst").trim()));
        }
        tf_ICMSTot_vCOFINS.setText("");
        if (jsonNFe.getString("icmstot_vcofins") != null) {
            tf_ICMSTot_vCOFINS.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vcofins").trim()));
        }
        tf_ICMSTot_vICMSDeson.setText("");
        if (jsonNFe.getString("icmstot_vicmsdeson") != null) {
            tf_ICMSTot_vICMSDeson.setText(f.getNumeroFormatado2cd(jsonNFe.getString("icmstot_vicmsdeson").trim()));
        }
        //Preenche campos. Transporte
        PercorrejcbxNum(jcbx_Transp_modFrete, jsonNFe.getString("transp_modfrete").trim(), 1);
        tf_Transporta_CNPJ.setText("");
        if (jsonNFe.getString("transporta_cnpj") != null) {
            tf_Transporta_CNPJ.setText(f.getCpfCnpjFormatado(jsonNFe.getString("transporta_cnpj")));
        }
        tf_Transporta_xNome.setText("");
        jcbx_Transporta_tpCNPJCPF.setSelectedIndex(-1);
        if (jsonNFe.getString("transporta_xnome") != null) {
            if (jsonNFe.getString("transporta_xnome").trim().length() > 0) {
                tf_Transporta_xNome.setText(jsonNFe.getString("transporta_xnome"));
                jcbx_Transporta_tpCNPJCPF.setSelectedIndex(1);
            }
        }
        tf_Transporta_IE.setText("");
        if (jsonNFe.getString("transporta_ie") != null) {
            tf_Transporta_IE.setText(jsonNFe.getString("transporta_ie"));
        }
        tf_Transporta_xEnder.setText(jsonNFe.getString("transporta_xender"));
        PercorrejcbxNum(jcbx_Transporta_xMun, "", 1);
        if (jsonNFe.getString("transporta_xmun") != null) {
            PercorrejcbxNum(jcbx_Transporta_xMun, jsonNFe.getString("transporta_xmun").trim(), jsonNFe.getString("transporta_xmun").trim().length());
        } else {
            jcbx_Transporta_xMun.removeAllItems();
        }
        PercorrejcbxNum(jcbx_Transporta_UF, "", 1);
        if (jsonNFe.getString("transporta_uf") != null
                && jsonNFe.getString("transporta_uf").trim().length() > 0) {
            PercorrejcbxNum(jcbx_Transporta_UF, jsonNFe.getString("transporta_uf").trim(), 2);
        } else {
            jcbx_Transporta_UF.removeAllItems();
        }
        //Preenche campos. Cobrança - Fatura
        tf_Fat_nFat.setText("");
        if (jsonNFe.getString("fat_nfat") != null) {
            tf_Fat_nFat.setText(jsonNFe.getString("fat_nfat"));
        }
        tf_Fat_vOrig.setText("");
        if (jsonNFe.getString("fat_vorig") != null) {
            tf_Fat_vOrig.setText(f.getNumeroFormatado2cd(jsonNFe.getString("fat_vorig")));
        }
        tf_Fat_vDesc.setText("");
        if (jsonNFe.getString("fat_vdesc") != null) {
            //if (!jsonNFe.getString("fat_vdesc").trim().equals("0.00")) {
            tf_Fat_vDesc.setText(f.getNumeroFormatado2cd(jsonNFe.getString("fat_vdesc")));
            //}
        }
        tf_Fat_vLiq.setText("");
        if (jsonNFe.getString("fat_vliq") != null) {
            tf_Fat_vLiq.setText(f.getNumeroFormatado2cd(jsonNFe.getString("fat_vliq")));
        }
        //Preenche campos. Informações Adicionais
        tf_InfAdic_infAdFisco.setText("");
        if (jsonNFe.getString("infadic_infadfisco") != null) {
            tf_InfAdic_infAdFisco.setText(jsonNFe.getString("infadic_infadfisco"));
        }
        tf_InfAdic_infCpl.setText("");
        if (jsonNFe.getString("infadic_infcpl") != null) {
            tf_InfAdic_infCpl.setText(jsonNFe.getString("infadic_infcpl"));
        }
        //Preenche campos. dados do certificado digital
        tf_Signature_CNPJ.setText("");
        if (jsonNFe.getString("signature_cnpj") != null) {
            tf_Signature_CNPJ.setText(jsonNFe.getString("signature_cnpj").trim());
        }
        tf_Signature_CNPJ.setText(tf_Emit_CNPJ.getText());

        tf_Signature_Emissor.setText("");
        if (jsonNFe.getString("signature_emissor") != null) {
            tf_Signature_Emissor.setText(jsonNFe.getString("signature_emissor").trim());
        }
        tf_Signature_Assunto.setText("");
        if (jsonNFe.getString("signature_assunto") != null) {
            tf_Signature_Assunto.setText(jsonNFe.getString("signature_assunto").trim());
        }
        tf_Signature_DataInicial.setText("");
        if (jsonNFe.getString("signature_datainicial") != null) {
            tf_Signature_DataInicial.setText(jsonNFe.getString("signature_datainicial"));
        }
        tf_Signature_DataFinal.setText("");
        if (jsonNFe.getString("signature_datafinal") != null) {
            tf_Signature_DataFinal.setText(jsonNFe.getString("signature_datafinal"));
        }
        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Cancelada")) {
            //botao_consultanasefaz.setEnabled(false);
        }
        if (jsonNFe.getString("infnfe_xmlexportado").trim().equals("Sim")) {
            //botao_consultanasefaz.setEnabled(false);
        }
        if (!jsonNFe.getString("ide_tpemis").trim().equals("1")) {
            jP_Contigencia.setVisible(true);
        }

        if (!jsonNFe.getString("tot_is_vbcsel").trim().isEmpty()) {
            tf_tot_is_vbcsel.setText(jsonNFe.getString("tot_is_vbcsel"));
        }

        if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Autorizada")) {
            if (!jsonNFe.getString("infnfe_danfexmlenviado").trim().equals("Sim")
                    && jsonNFe.getString("ide_tpnf").trim().equals("1")) {
                botao_enviarEmail.setEnabled(true);
            }
            if (jsonNFe.getString("ide_tpnf").trim().equals("1")
                    && duplicatasBanco(tf_InfNFe_NNF.getText().trim())
                    && !jsonNFe.getString("infnfe_tituloregistrado").trim().equals("Sim")) {
                botao_registrarTitulo.setEnabled(true);
            } else {
                botao_registrarTitulo.setEnabled(false);
            }
            if (jsonNFe.getString("infnfe_tituloregistrado").trim().equals("Sim")
                    && !jsonNFe.getString("infnfe_boletoenviado").trim().equals("Sim")) {
                botao_enviarEmail.setEnabled(true);
            }
        } else {
            botao_registrarTitulo.setEnabled(false);
            botao_enviarEmail.setEnabled(false);
        }

        setFieldsFromJson(jsonNFe);

        MontaTabelaProdutosServicosNFe();
        MontaTabelaTransporteVolNFe();
        MontaTabelaCobrancaNFe();
        MontaTabelaHistoricoEventosNFe();
        MontaTabelaNotasConhecimentosReferenciados();
        MontaTabelaInformacoesPagamento();

        //getNFEtable(this.tf_InfNFe_NNF.getText().trim());
    }

    public void ThreeDLauncher(JInternalFrame frm, boolean wMaximun) {
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            int width = (int) frm.getSize().getWidth();
            int height = (int) frm.getSize().getHeight();
            frm.setLocation((this.jDktInputData.getSize().width - width) / 2, (((this.jDktInputData.getSize().height) - height) / 2));
            jDktInputData.add(frm);
            frm.setVisible(true);
            frm.setMaximum(wMaximun);
            frm.toFront();
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (PropertyVetoException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public boolean VerifyClassisInstance() {
        boolean exist = false;
        //String NamePackage = this.getClass().getPackage().getName() + ".calledapi.";
        for (Component c : jDktInputData.getComponents()) {
            if (c instanceof JInternalFrame) {
                try {
                    //for (int i = 0; i < frmClass.length - 1; i++) {
                    //System.out.println("minha.1: " + frmClass[i]);
                    Class classe = c.getClass();
                    String cn = classe.toString().replace("class", "").trim();
                    //System.out.println(cn);
                    //Object o = classe.newInstance();
                    //System.out.println("minha.2: " + o.getClass().getPackage().getName());
                    //System.out.println(form.getName());
                    exist = false;
                    if (Class.forName(cn).isInstance(c)) {
                        //if (Class.forName(NamePackage + frmClass[i]).isInstance(c)) {
                        //exist = false;                      
                        //if (Class.forName(frm[i]).isInstance(c)) {
                        //c.setVisible(true);
                        //c.transferFocus();
                        //System.out.println(c);
                        //System.out.println(Class.class.getTypeName());
                        exist = true;
                        break;

                    }
                    //}
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NFe.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            //if (exist) {
            //    break;
            //}
        }
        return exist;
    }

    public void getNFEtable(String nNF) throws SQLException, ParseException, InstantiationException, IllegalAccessException {
        initTables();
        getHeaderNFEtable(nNF);
        setState();
        setNFeTotIBSISCBS(eNFE);
        UpdateNFe();
    }

    public void initTables() {
        NFEDETPRODtable = new javax.swing.JTable();
        NFEDETPRODtableModel = new NFEDETPRODtablemodel(NFEDETPRODtable,
                null, null);
        NFEDETPRODtable.setModel(NFEDETPRODtableModel);
        NFEDETPRODtable = NFEDETPRODtableModel.NFEDETPRODtable(NFEDETPRODtable);
        NFEDETPRODtableModel.clearListNFEDETPRODdto();
    }

    public void getHeaderNFEtable(String nNF) throws SQLException, ParseException {
        int nnf = Integer.parseInt(nNF);
        NFEDETPRODtableModel = new NFEDETPRODtablemodel(NFEDETPRODtable,
                NFEcontroller.NamedQueryFindByIDENNF(nnf).getNFEDETPRODdto(), null);
    }

    public void setState() {
        // Define os símbolos para o formato brasileiro
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        // Define o formato correto: 0.00 (monetário)
        DecimalFormat df2 = new DecimalFormat("#,##0.00", symbols);

        // Obtém os valores antes de setar na DTO, evitando chamadas repetitivas
        Double totVbcIbscbs = NFEDETPRODtableModel.getTOT_VBCIBSCBS();
        Double totIbsUfVibs = NFEDETPRODtableModel.getTOT_UF_VIBS();
        Double totIbsMunVibs = NFEDETPRODtableModel.getTOT_MUN_VIBS();
        Double totCbsVcbs = NFEDETPRODtableModel.getTOT_CBS_VCBS();
        Double totIsVbcsel = NFEDETPRODtableModel.getTOT_IS_VBCSEL();
        Double totIsVimpsel = NFEDETPRODtableModel.getTOT_IS_VIMPSEL();
        Double totVtotNf = NFEDETPRODtableModel.getTOT_IBSCBSIS_VALORNOTA();

        // Armazena os valores na DTO
        nFEdto.setTOT_VBCIBSCBS(totVbcIbscbs);
        nFEdto.setTOT_IBS_UF_VIBS(totIbsUfVibs);
        nFEdto.setTOT_IBS_MUN_VIBS(totIbsMunVibs);
        nFEdto.setTOT_CBS_VCBS(totCbsVcbs);
        nFEdto.setTOT_IS_VBCSEL(totIsVbcsel);
        nFEdto.setTOT_IS_VIMPSEL(totIsVimpsel);
        nFEdto.setTOT_VTOTNF(totVtotNf);

        // Formata e define os valores nos campos de texto (apenas se o imposto for > 0)
        setFormattedValue(tf_tot_vbcibscbs, totVbcIbscbs, totIbsMunVibs, df2);
        setFormattedValue(tf_tot_ibs_uf_vibs, totIbsUfVibs, totIbsUfVibs, df2);
        setFormattedValue(tf_tot_ibs_mun_vibs, totIbsMunVibs, totIbsMunVibs, df2);

        // Soma os valores de IBS UF e IBS MUN para o campo total de IBS VIBS
        Double totIbsVibs = (totIbsUfVibs != null ? totIbsUfVibs : 0.0)
                + (totIbsMunVibs != null ? totIbsMunVibs : 0.0);
        setFormattedValue(tf_tot_ibs_vibs, totIbsVibs, totIbsVibs, df2);

        setFormattedValue(tf_tot_is_vbcsel, totIsVbcsel, totIsVimpsel, df2);
        setFormattedValue(tf_tot_is_vimpsel, totIsVimpsel, totIsVimpsel, df2);

        setFormattedValue(tf_tot_cbs_vbc, totVbcIbscbs, totCbsVcbs, df2);
        setFormattedValue(tf_tot_cbs_vcbs, totCbsVcbs, totCbsVcbs, df2);

        setFormattedValue(tf_tot_vtotnf, totVtotNf, totVtotNf, df2);
    }

    private void setFormattedValue(javax.swing.JTextField textField, Double value, Double vtrib, DecimalFormat formatter) {
        if (value != null && value > 0.0 && vtrib != null && vtrib > 0.0) {
            textField.setText(formatter.format(value));
        } else {
            textField.setText(""); // Limpa o campo se o valor ou imposto forem inválidos
        }
    }

    private void setNFeTotIBSISCBS(NFE eNFE) {
        // Converte e define valores para todos os campos (mantendo como Strings)
        convertAndSetStringValue(tf_tot_is_vbcsel, eNFE::setTot_is_vbcsel);
        convertAndSetStringValue(tf_tot_is_vimpsel, eNFE::setTot_is_vimpsel);

        convertAndSetStringValue(tf_tot_ibs_uf_vcrespres, eNFE::setTot_ibs_uf_vcrespres);
        convertAndSetStringValue(tf_tot_ibs_uf_vcredprescondsus, eNFE::setTot_ibs_uf_vcredprescondsus);
        convertAndSetStringValue(tf_tot_ibs_uf_vdif, eNFE::setTot_ibs_uf_vdif);
        convertAndSetStringValue(tf_tot_ibs_uf_vdevtrib, eNFE::setTot_ibs_uf_vdevtrib);
        convertAndSetStringValue(tf_tot_ibs_uf_vdeson, eNFE::setTot_ibs_uf_vdeson);
        convertAndSetStringValue(tf_tot_ibs_uf_vibs, eNFE::setTot_ibs_uf_vibs);

        convertAndSetStringValue(tf_tot_ibs_mun_vcrespres, eNFE::setTot_ibs_mun_vcrespres);
        convertAndSetStringValue(tf_tot_ibs_mun_vcredprescondsus, eNFE::setTot_ibs_mun_vcredprescondsus);
        convertAndSetStringValue(tf_tot_ibs_mun_vdif, eNFE::setTot_ibs_mun_vdif);
        convertAndSetStringValue(tf_tot_ibs_mun_vdevtrib, eNFE::setTot_ibs_mun_vdevtrib);
        convertAndSetStringValue(tf_tot_ibs_mun_vdeson, eNFE::setTot_ibs_mun_vdeson);
        convertAndSetStringValue(tf_tot_ibs_mun_vibs, eNFE::setTot_ibs_mun_vibs);

        convertAndSetStringValue(tf_tot_cbs_vcrespres, eNFE::setTot_cbs_vcrespres);
        convertAndSetStringValue(tf_tot_cbs_vcredprescondsus, eNFE::setTot_cbs_vcredprescondsus);
        convertAndSetStringValue(tf_tot_cbs_vdif, eNFE::setTot_cbs_vdif);
        convertAndSetStringValue(tf_tot_cbs_vdevtrib, eNFE::setTot_cbs_vdevtrib);
        convertAndSetStringValue(tf_tot_cbs_vdeson, eNFE::setTot_cbs_vdeson);
        convertAndSetStringValue(tf_tot_cbs_vcbs, eNFE::setTot_cbs_vcbs);

        convertAndSetStringValue(tf_tot_vbcibscbs, eNFE::setTot_vbcibscbs);
        convertAndSetStringValue(tf_tot_vtotnf, eNFE::setTot_vtotnf);
    }

    private void convertAndSetStringValue(javax.swing.JTextField textField, java.util.function.Consumer<String> setter) {
        String text = textField.getText().trim();
        if (!text.isEmpty()) {
            String valorFormatado = text.replace(".", "").replace(",", ".");
            setter.accept(valorFormatado); // Define o valor formatado como String no objeto eNFE
        }
    }

    private void setFieldsFromJson(JSONObject json) {
        Map<String, JTextField> fields = new HashMap<>();
        fields.put("tot_is_vbcsel", tf_tot_is_vbcsel);
        fields.put("tot_is_vimpsel", tf_tot_is_vimpsel);

        fields.put("tot_ibs_uf_vcrespres", tf_tot_ibs_uf_vcrespres);
        fields.put("tot_ibs_uf_vcredprescondsus", tf_tot_ibs_uf_vcredprescondsus);
        fields.put("tot_ibs_uf_vdif", tf_tot_ibs_uf_vdif);
        fields.put("tot_ibs_uf_vdevtrib", tf_tot_ibs_uf_vdevtrib);
        fields.put("tot_ibs_uf_vdeson", tf_tot_ibs_uf_vdeson);
        fields.put("tot_ibs_uf_vibs", tf_tot_ibs_uf_vibs);

        fields.put("tot_ibs_mun_vcrespres", tf_tot_ibs_mun_vcrespres);
        fields.put("tot_ibs_mun_vcredprescondsus", tf_tot_ibs_mun_vcredprescondsus);
        fields.put("tot_ibs_mun_vdif", tf_tot_ibs_mun_vdif);
        fields.put("tot_ibs_mun_vdevtrib", tf_tot_ibs_mun_vdevtrib);
        fields.put("tot_ibs_mun_vdeson", tf_tot_ibs_mun_vdeson);
        fields.put("tot_ibs_mun_vibs", tf_tot_ibs_mun_vibs);

        fields.put("tot_cbs_vcrespres", tf_tot_cbs_vcrespres);
        fields.put("tot_cbs_vcredprescondsus", tf_tot_cbs_vcredprescondsus);
        fields.put("tot_cbs_vdif", tf_tot_cbs_vdif);
        fields.put("tot_cbs_vdevtrib", tf_tot_cbs_vdevtrib);
        fields.put("tot_cbs_vdeson", tf_tot_cbs_vdeson);
        fields.put("tot_cbs_vcbs", tf_tot_cbs_vcbs);

        fields.put("tot_vbcibscbs", tf_tot_vbcibscbs);
        fields.put("tot_vtotnf", tf_tot_vtotnf);

        for (Map.Entry<String, JTextField> entry : fields.entrySet()) {
            setFieldFromJson(json, entry.getKey(), entry.getValue());
        }
        setSumFieldFromJson(json, "tot_ibs_uf_vibs", "tot_ibs_mun_vibs", tf_tot_ibs_vibs);
        tf_tot_cbs_vbc.setText(tf_tot_vbcibscbs.getText().trim());

    }

    private void setFieldFromJson(JSONObject json, String key, JTextField textField) {
        String value = json.optString(key, "").trim(); // Pega o valor do JSON ou "" se não existir
        textField.setText(value.replace(".", ",")); // Preenche o campo ou limpa caso o valor seja vazio
    }

    private void setSumFieldFromJson(JSONObject json, String key1, String key2, JTextField textField) {
        double value1 = parseDouble(json.optString(key1, "0"));
        double value2 = parseDouble(json.optString(key2, "0"));

        double sum = value1 + value2;
        if (sum > 0) {
            textField.setText(String.format("%.2f", sum).replace(".", ",")); // Formata com 2 casas decimais
        } else {
            textField.setText(""); // Limpa o campo se o valor for 0
        }
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", ".")); // Substitui vírgula por ponto para conversão
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void UpdateNFe() throws ParseException, InstantiationException, IllegalAccessException {
        String w_finNFe = jcbx_Ide_finNFe.getSelectedItem().toString().substring(0, 1);
        String w_tpNFDebito = "0";
        String w_tpNFCredito = "0";
        if (jcbx_Ide_tpNFDebito.getSelectedIndex() > 0) {
            w_tpNFDebito = jcbx_Ide_tpNFDebito.getSelectedItem().toString().substring(0, 2);
        }
        if (jcbx_Ide_tpNFCredito.getSelectedIndex() > 0) {
            w_tpNFCredito = jcbx_Ide_tpNFCredito.getSelectedItem().toString().substring(0, 2);
        }

        NFE eNFE = new NFE();
        eNFE.setEnderdest_indiedest(jcbx_EnderDest_indIEDest.getSelectedItem().toString().trim().substring(0, 1)); //10/09/2021
        eNFE.setIde_nnf(tf_InfNFe_NNF.getText().trim());
        eNFE.setInfadic_infcpl(tf_InfAdic_infCpl.getText().trim());
        eNFE.setIcmstot_vpis(tf_ICMSTot_vPIS.getText().trim().replace(".", "").replace(",", "."));
        eNFE.setIcmstot_vcofins(tf_ICMSTot_vCOFINS.getText().trim().replace(".", "").replace(",", "."));
        eNFE.setIde_finnfe(w_finNFe);
        eNFE.setIde_tpnfdebito(w_tpNFDebito);
        eNFE.setIde_tpnfcredito(w_tpNFCredito);
        eNFE.setIde_tpemis(jcbx_Ide_tpEmis.getSelectedItem().toString().substring(0, 1));
        if (!jcbx_Ide_tpEmis.getSelectedItem().toString().substring(0, 1).equals("1")) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date parsedDate = dateFormat.parse(tf_Ide_DhCont.getText().trim().replace("T", " ").replace("-03:00", ""));
            SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String wIde_dhcont = formatador.format(parsedDate);

            eNFE.setIde_dhcont(wIde_dhcont);
            eNFE.setIde_xjust(tf_Ide_xjust.getText().trim());
        };
        eNFE.setIde_indfinal(jcbx_Ide_indFinal.getSelectedItem().toString().substring(0, 1));
        setNFeTotIBSISCBS(eNFE); //18/03/2025
        if (NfeController.Update(eNFE)) {
            System.out.println("NFe atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, System.getProperty("MsgInvalid"));
        }
    }

    public void mostrarMensagemTemporaria(JDesktopPane panelDestino, String mensagem, int duracaoMs) {
        JLabel msgLabel = new JLabel(mensagem);
        msgLabel.setOpaque(true);
        msgLabel.setBackground(new Color(220, 248, 198)); // Verde claro
        msgLabel.setForeground(Color.BLACK);
        msgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        msgLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Define tamanho e posição central no painel
        int largura = 650;
        int altura = 60;
        int x = (panelDestino.getWidth() - largura) / 2;
        int y = (panelDestino.getHeight() - altura) / 2;
        msgLabel.setBounds(x, y, largura, altura);

        // Define layout nulo se necessário
        LayoutManager originalLayout = panelDestino.getLayout();

        panelDestino.add(msgLabel);
        panelDestino.setComponentZOrder(msgLabel, 0); // Garantir visibilidade
        panelDestino.repaint();

        // Remove a mensagem após a duração especificada
        new javax.swing.Timer(duracaoMs, e -> {
            panelDestino.remove(msgLabel);
            panelDestino.repaint();
        }).start();
    }

    private void gerarBoletoDeCobranca(String numeroNFe, List<CANFEDUPLICdto> duplicatas) {
        System.out.println("[INFO] Iniciando registro de boletos para a NFe: " + duplicatas.get(0).getPAR_CODI().trim());
        String numeroFormatadoNFe = String.format("%09d", Integer.parseInt(numeroNFe));
        CANFEDUPLICdto dup = duplicatas.get(0);
        boolean maloteGerado = false;
        try {
            if ("REGISTRADO".equals(dup.getPAR_STATUS().trim()) && SANTANDER.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                maloteGerado = montaMalote.geraLoteMaloteAposRegistroNoBanco("033", duplicatas);
                if (maloteGerado) {
                    mostrarMensagemTemporaria(jDktInputData, "Boleto de cobrança gerado com sucesso | Nosso Número: " + dup.getPAR_NUMDOC().trim(), 3000);
                } else {
                    mostrarMensagemTemporaria(jDktInputData, "Falha na geracao do Boleto de cobrança | Nosso Número: " + dup.getPAR_NUMDOC().trim(), 3000);
                }
            }
        } catch (Exception ex) {
            System.err.println("[ERRO] Falha ao registrar duplicata " + dup.getPAR_NUMDOC() + ": " + ex.getMessage());
            ex.printStackTrace();
            mostrarMensagemTemporaria(jDktInputData, "Exceção ao registrar boleto" + dup.getPAR_NUMDOC().trim(), 3000);

        }
        System.out.println("[INFO] Registro de boletos finalizado para a NFe " + numeroFormatadoNFe);
    }

    public boolean VerifyClassisInstance(JDialog jDialog, JDesktopPane jDktInputData) throws ClassNotFoundException {
        boolean exist = false;
        for (Component c : jDktInputData.getComponents()) {
            if (c instanceof JInternalFrame) {
                Class classe = c.getClass();
                String cn = classe.toString().replace("class", "").trim();
                exist = false;
                if (Class.forName(cn).isInstance(jDialog)) {
                    ((JInternalFrame) c).moveToFront();
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }

    public void ThreeDLauncher(JInternalFrame internalFrame, JDialog jDialog, boolean maximum) {
        JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class,
                this);

        jDialog.setAlwaysOnTop(true); // Mantém o JDialog à frente do JInternalFrame
        jDialog.setModal(true);
        jDialog.pack();
        jDialog.setLocationRelativeTo(this); // Centraliza o JDialog em relação ao JFrame
        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                setEnabled(false); // Desabilita o JFrame quando o JDialog é aberto
            }

            @Override
            public void windowClosed(WindowEvent e) {
                setEnabled(true); // Habilita o JFrame quando o JDialog é fechado
            }
        });

        frame.setEnabled(false); // Desabilita o JFrame quando o JDialog é aberto
        jDialog.setVisible(true);
        frame.setEnabled(true); // Habilita o JFrame quando o JDialog é fechado    
    }

    public boolean duplicatasBanco(String numeroNFe) throws SQLException, ParseException {
        if (tf_InfNFe_NNF.getText().trim().isEmpty()) {
            return false;
        }
        numeroNFe = String.format("%06d", Integer.parseInt(numeroNFe));
        List<CANFEDUPLICdto> duplicatas = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);
        if (duplicatas == null || duplicatas.isEmpty()) {
            return false;
        }
        if (duplicatas.get(0).getPAR_BANCO() == null || duplicatas.get(0).getPAR_BANCO().isEmpty()) {
            return false;
        }
        String pAR_BANCO = "";
        if (duplicatas != null && !duplicatas.isEmpty()
                && duplicatas.size() > 0) {
            System.out.println(numeroNFe);
            pAR_BANCO = duplicatas.get(0).getPAR_BANCO().trim();
            if (pAR_BANCO.equals(BRADESCO.getNumeroDoBanco())) {
                return true;
            }
        }
        return false;
    }

    public void renderStatusDocumentos() throws SQLException, ParseException, ParseException {
        NFECBdto nfecb = new NFECBdto();
        String numeroNFe = String.format("%06d", Integer.parseInt(tf_InfNFe_NNF.getText().trim()));
        nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
        if (nfecb != null) {
            jTable1.setValueAt(
                    nfecb.getINFNFE_DANFEXMLENVIADO() != null && !nfecb.getINFNFE_DANFEXMLENVIADO().trim().isEmpty()
                    ? nfecb.getINFNFE_DANFEXMLENVIADO().trim() : "Não",
                    jTable1.getSelectedRow(), 9
            );

            jTable1.setValueAt(
                    nfecb.getINFNFE_TITULOREGISTRADO() != null && !nfecb.getINFNFE_TITULOREGISTRADO().trim().isEmpty()
                    ? nfecb.getINFNFE_TITULOREGISTRADO().trim() : "Não",
                    jTable1.getSelectedRow(), 10
            );

            jTable1.setValueAt(
                    nfecb.getINFNFE_BOLETOENVIADO() != null
                    && !nfecb.getINFNFE_BOLETOENVIADO().trim().isEmpty()
                    && !nfecb.getINFNFE_TITULOREGISTRADO().trim().equals("Sim")
                    && !nfecb.getINFNFE_TITULOREGISTRADO().trim().isEmpty()
                    ? nfecb.getINFNFE_BOLETOENVIADO().trim() : "Não",
                    jTable1.getSelectedRow(), 11
            );

        }
    }
}
