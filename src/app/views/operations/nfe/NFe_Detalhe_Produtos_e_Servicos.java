package app.views.operations.nfe;

import java.awt.Cursor;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import util.FormatFields;
import util.formata;
import app.controllers.NfeController;
import app.controllers.NfeDetProdController;
import app.models.NFE;
import app.models.NFE_DET_PROD;
import app.views.operations.nfe.util.CalculateTaxes;
import com.md1.frontend.util.ValidFieldNumeric;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import javax.swing.JTextField;

public class NFe_Detalhe_Produtos_e_Servicos extends javax.swing.JInternalFrame {

    public CalculateTaxes eCalculaImpostos = new CalculateTaxes();
    private NFe veioDoframe1;
    private JInternalFrame parent;

    String gdet_prod_nnf;
    String gdet_prod_item;
    String gdet_prod_cprod;
    String gdet_status;
    String gCla_Valor = "";
    formata formata = new formata();
    FormatFields f = new FormatFields();
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
    DecimalFormat df2 = new DecimalFormat("0.00", symbols);
    DecimalFormat df4 = new DecimalFormat("0.0000", symbols);
    private static final int SSL_PORT = 443;

    public NFe_Detalhe_Produtos_e_Servicos(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wdet_prod_nnf,
            String wdet_prod_item,
            String wdet_prod_cprod,
            String wdet_status) throws SQLException, ParseException {

        gdet_prod_nnf = wdet_prod_nnf;
        gdet_prod_item = wdet_prod_item;
        gdet_prod_cprod = wdet_prod_cprod;
        gdet_status = wdet_status;

        symbols.setDecimalSeparator(',');
        this.jDesktopPane2 = jDesktop;

        initComponents();
        enableDisableFields(false);
        getParamStart();

        jTabbedPane1.setEnabledAt(3, false);
        //jTabbedPane1.setEnabledAt(4, false);
        //jTabbedPane1.setEnabledAt(5, false);
        //jTabbedPane1.setEnabledAt(6, false);
        jTabbedPane1.setEnabledAt(7, false);
        jTabbedPane1.setEnabledAt(8, false);
        jTabbedPane1.setEnabledAt(9, false);
        //jTabbedPane2.setEnabledAt(4, false);
        //jTabbedPane2.setEnabledAt(5, false);
        //jTabbedPane2.setEnabledAt(6, false);
        jTabbedPane2.setEnabledAt(7, false);
        jTabbedPane2.setEnabledAt(8, false);
        jTabbedPane2.setEnabledAt(9, false);
        jTabbedPane2.setEnabledAt(10, false);

        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane2.setSelectedIndex(4);

        getMap();
    }

    public void getMap() throws SQLException, ParseException {
        ArrayList<JSONObject> map = new ArrayList<JSONObject>();
        map = NfeDetProdController.List(gdet_prod_nnf.trim(), gdet_prod_item.trim());
        for (int i = 0; i < map.size(); i++) {
            tf_det_prod_cprod.setText(map.get(i).getString("det_prod_cprod").trim());
            tf_det_prod_xprod.setText(map.get(i).getString("det_prod_xprod").trim());
            tf_det_prod_ncm.setText(map.get(i).getString("det_prod_ncm").trim());
            if (map.get(i).getString("det_prod_cest") != null) {
                tf_det_prod_cest.setText(map.get(i).getString("det_prod_cest").trim());
            }
            if (map.get(i).getString("det_prod_cean") != null) {
                tf_det_prod_cean.setText(map.get(i).getString("det_prod_cean").trim());
            }
            if (map.get(i).getString("det_prod_ceantrib") != null) {
                tf_det_prod_ceantrib.setText(map.get(i).getString("det_prod_ceantrib").trim());
            }

            PercorrejcbxNum(jcbx_det_prod_cfop, map.get(i).getString("det_prod_cfop").trim(), map.get(i).getString("det_prod_cfop").trim().length());
            tf_det_prod_ucom.setText(map.get(i).getString("det_prod_ucom").trim());
            tf_det_prod_utrib.setText(map.get(i).getString("det_prod_utrib").trim());
            tf_det_prod_qcom.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_prod_qcom")));
            tf_det_prod_qtrib.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_prod_qtrib")));
            tf_det_prod_vuncom.setText(f.getNumeroFormatado10cd(map.get(i).getString("det_prod_vuncom")));
            tf_det_prod_vuntrib.setText(f.getNumeroFormatado10cd(map.get(i).getString("det_prod_vuntrib")));
            tf_det_prod_vprod.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_prod_vprod")));
            jCheck_det_prod_indtot.setSelected(false);
            if (map.get(i).getString("det_prod_indtot").trim().equals("1")) {
                jCheck_det_prod_indtot.setSelected(true);
            }
            //Tributos.
            PercorrejcbxNum(jcbx_det_icms_crt, formata.StrZero(map.get(i).getString("det_icms_crt").trim(), 1), 1);
            PercorrejcbxNum(jcbx_det_icms_cst, formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2), 2);
            PercorrejcbxNum(jcbx_det_icms_orig, formata.StrZero(map.get(i).getString("det_icms_orig").trim(), 1), 1);
            //Tributos. ICMS
            jTabbedPane3.setEnabledAt(0, false);
            jTabbedPane3.setEnabledAt(1, false);
            jTabbedPane3.setEnabledAt(2, false);
            jTabbedPane3.setEnabledAt(3, false);
            jTabbedPane3.setEnabledAt(4, false);
            jTabbedPane3.setEnabledAt(5, false);
            jTabbedPane3.setEnabledAt(6, false);
            jTabbedPane3.setEnabledAt(7, false);
            jTabbedPane3.setEnabledAt(8, false);

            if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("00")) {
                jTabbedPane3.setEnabledAt(0, true);
                if (map.get(i).getString("det_icms00_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms00_modbc, formata.StrZero(map.get(i).getString("det_icms00_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms00_vbc") != null
                        && !map.get(i).getString("det_icms00_vbc").isEmpty()) {
                    tf_det_icms00_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms00_vbc")));
                }
                if (map.get(i).getString("det_icms00_picms") != null) {
                    tf_det_icms00_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms00_picms")));
                }
                if (map.get(i).getString("det_icms00_vicms") != null) {
                    tf_det_icms00_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms00_vicms")));
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("10")) {
                jTabbedPane3.setEnabledAt(1, true);
                jTabbedPane3.setSelectedIndex(1);
                if (map.get(i).getString("det_icms10_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms10_modbc, formata.StrZero(map.get(i).getString("det_icms10_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms10_vbc") != null) {
                    tf_det_icms10_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vbc")));
                }
                if (map.get(i).getString("det_icms10_picms") != null) {
                    tf_det_icms10_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms10_picms")));
                }
                if (map.get(i).getString("det_icms10_vicms") != null) {
                    tf_det_icms10_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vicms")));
                }
                if (map.get(i).getString("det_icms10_modbcst") != null) {
                    PercorrejcbxNum(jcbx_det_icms10_modbcst, formata.StrZero(map.get(i).getString("det_icms10_modbcst").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms10_predbcst") != null) {
                    tf_det_icms10_predbcst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms10_predbcst")));
                }
                if (map.get(i).getString("det_icms10_pmvast") != null) {
                    tf_det_icms10_pmvast.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms10_pmvast")));
                }
                if (map.get(i).getString("det_icms10_vbcst") != null) {
                    tf_det_icms10_vbcst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vbcst")));
                }
                if (map.get(i).getString("det_icms10_picmsst") != null) {
                    tf_det_icms10_picmsst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms10_picmsst")));
                }
                if (map.get(i).getString("det_icms10_vicmsst") != null) {
                    tf_det_icms10_vicmsst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms10_vicmsst")));
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("20")) {
                jTabbedPane3.setEnabledAt(2, true);
                jTabbedPane3.setSelectedIndex(2);
                if (map.get(i).getString("det_icms20_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms20_modbc, formata.StrZero(map.get(i).getString("det_icms20_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms20_vbc") != null) {
                    tf_det_icms20_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms20_vbc")));
                }
                if (map.get(i).getString("det_icms20_picms") != null) {
                    tf_det_icms20_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms20_picms")));
                }
                if (map.get(i).getString("det_icms20_vicms") != null) {
                    tf_det_icms20_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms20_vicms")));
                }
                if (map.get(i).getString("det_icms20_vicmsdeson") != null) {
                    tf_det_icms20_vicmsdeson.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms20_vicmsdeson")));
                }
                if (map.get(i).getString("det_icms20_motdesicms") != null) {
                    PercorrejcbxNum(jcbx_det_icms20_motdesicms, formata.StrZero(map.get(i).getString("det_icms20_motdesicms").trim(), 2), 2);
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("30")) {
                jTabbedPane3.setEnabledAt(3, true);
                jTabbedPane3.setSelectedIndex(3);
                if (map.get(i).getString("det_icms30_modbcst") != null) {
                    PercorrejcbxNum(jcbx_det_icms30_modbcst, formata.StrZero(map.get(i).getString("det_icms30_modbcst").trim(), 1), 1);
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("40")
                    || formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("41")
                    || formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("50")) {
                jTabbedPane3.setEnabledAt(4, true);
                jTabbedPane3.setSelectedIndex(4);
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("51")) {
                jTabbedPane3.setEnabledAt(5, true);
                jTabbedPane3.setSelectedIndex(5);
                if (map.get(i).getString("det_icms51_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms51_modbc, formata.StrZero(map.get(i).getString("det_icms51_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms51_predbc") != null) {
                    tf_det_icms51_predbc.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms51_predbc")));
                }
                if (map.get(i).getString("det_icms51_vbc") != null) {
                    tf_det_icms51_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vbc")));
                }
                if (map.get(i).getString("det_icms51_picms") != null) {
                    tf_det_icms51_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms51_picms")));
                }
                if (map.get(i).getString("det_icms51_vicms") != null) {
                    tf_det_icms51_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vicms")));
                }
                if (map.get(i).getString("det_icms51_vicmsop") != null) {
                    tf_det_icms51_vicmsop.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vicmsop")));
                }
                if (map.get(i).getString("det_icms51_pdif") != null) {
                    tf_det_icms51_pdif.setText(f.getFormated_2(map.get(i).getDouble("det_icms51_pdif")));
                }
                if (map.get(i).getString("det_icms51_vicmsdif") != null) {
                    tf_det_icms51_vicmsdif.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms51_vicmsdif")));
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("60")) {
                jTabbedPane3.setEnabledAt(6, true);
                jTabbedPane3.setSelectedIndex(6);
                if (map.get(i).getString("det_icms60_vbcstret") != null) {
                    tf_det_icms60_vbcstret.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms60_vbcstret")));
                }
                if (map.get(i).getString("det_icms60_vicmsstret") != null) {
                    tf_det_icms60_vicmsstret.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms60_vicmsstret")));
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("70")) {
                jTabbedPane3.setEnabledAt(7, true);
                jTabbedPane3.setSelectedIndex(7);
                if (map.get(i).getString("det_icms70_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms70_modbc, formata.StrZero(map.get(i).getString("det_icms70_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms70_vbc") != null) {
                    tf_det_icms70_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vbc")));
                }
                if (map.get(i).getString("det_icms70_picms") != null) {
                    tf_det_icms70_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms70_picms")));
                }
                if (map.get(i).getString("det_icms70_vicms") != null) {
                    tf_det_icms70_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vicms")));
                }
                if (map.get(i).getString("det_icms70_vicmsdeson") != null) {
                    tf_det_icms70_vicmsdeson.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vicmsdeson")));
                }
                if (map.get(i).getString("det_icms70_motdesicms") != null) {
                    PercorrejcbxNum(jcbx_det_icms70_motdesicms, formata.StrZero(map.get(i).getString("det_icms70_motdesicms").trim(), 2), 2);
                }
                if (map.get(i).getString("det_icms70_modbcst") != null) {
                    PercorrejcbxNum(jcbx_det_icms70_modbcst, formata.StrZero(map.get(i).getString("det_icms70_modbcst").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms70_predbcst") != null) {
                    tf_det_icms70_predbcst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms70_predbcst")));
                }
                if (map.get(i).getString("det_icms70_pmvast") != null) {
                    tf_det_icms70_pmvast.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms70_pmvast")));
                }
                if (map.get(i).getString("det_icms70_vbcst") != null) {
                    tf_det_icms70_vbcst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vbcst")));
                }
                if (map.get(i).getString("det_icms70_picmsst") != null) {
                    tf_det_icms70_picmsst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms70_picmsst")));
                }
                if (map.get(i).getString("det_icms70_vicmsst") != null) {
                    tf_det_icms70_vicmsst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms70_vicmsst")));
                }
            } else if (formata.StrZero(map.get(i).getString("det_icms_cst").trim(), 2).equals("90")) {
                jTabbedPane3.setEnabledAt(8, true);
                jTabbedPane3.setSelectedIndex(8);
                if (map.get(i).getString("det_icms90_modbc") != null) {
                    PercorrejcbxNum(jcbx_det_icms90_modbc, formata.StrZero(map.get(i).getString("det_icms90_modbc").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms90_vbc") != null) {
                    tf_det_icms90_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vbc")));
                }
                if (map.get(i).getString("det_icms90_picms") != null) {
                    tf_det_icms90_picms.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms90_picms")));
                }
                if (map.get(i).getString("det_icms90_vicms") != null) {
                    tf_det_icms90_vicms.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vicms")));
                }
                if (map.get(i).getString("det_icms90_vicmsdeson") != null) {
                    tf_det_icms90_vicmsdeson.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vicmsdeson")));
                }
                if (map.get(i).getString("det_icms90_motdesicms") != null) {
                    PercorrejcbxNum(jcbx_det_icms90_motdesicms, formata.StrZero(map.get(i).getString("det_icms90_motdesicms").trim(), 2), 2);
                }
                if (map.get(i).getString("det_icms90_modbcst") != null) {
                    PercorrejcbxNum(jcbx_det_icms90_modbcst, formata.StrZero(map.get(i).getString("det_icms90_modbcst").trim(), 1), 1);
                }
                if (map.get(i).getString("det_icms90_predbcst") != null) {
                    tf_det_icms90_predbcst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms90_predbcst")));
                }
                if (map.get(i).getString("det_icms90_pmvast") != null) {
                    tf_det_icms90_pmvast.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms90_pmvast")));
                }
                if (map.get(i).getString("det_icms90_vbcst") != null) {
                    tf_det_icms90_vbcst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vbcst")));
                }
                if (map.get(i).getString("det_icms90_picmsst") != null) {
                    tf_det_icms90_picmsst.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_icms90_picmsst")));
                }
                if (map.get(i).getString("det_icms90_vicmsst") != null) {
                    tf_det_icms90_vicmsst.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_icms90_vicmsst")));
                }
            }
            //Tributos. IPI
            if (map.get(i).getString("det_ipitrib_cst") != null) {
                PercorrejcbxNum(jcbx_det_ipitrib_cst, formata.StrZero(map.get(i).getString("det_ipitrib_cst").trim(), 2), 2);
            }
            if (map.get(i).getString("det_ipi_cenq") != null) {
                tf_det_ipi_cenq.setText(map.get(i).getString("det_ipi_cenq"));
            }
            if (map.get(i).getString("det_ipitrib_vbc") != null) {
                PercorrejcbxNum(jcbx_det_ipitrib_tipocalculo, formata.StrZero("0", 1), 1);
                tf_det_ipitrib_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vbc")));
            }
            if (map.get(i).getString("det_ipitrib_pipi") != null) {
                tf_det_ipitrib_pipi.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_ipitrib_pipi")));
            }
            if (map.get(i).getString("det_ipitrib_vipi") != null) {
                tf_det_ipitrib_vipi.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ipitrib_vipi")));
            }
            //Tributos. PIS
            System.out.println("map.get(i).getString(\"det_pisaliq_cst\") " + map.get(i).getString("det_pisaliq_cst"));
            if (map.get(i).getString("det_pisaliq_cst") != null
                    && map.get(i).getString("det_pisaliq_cst").trim().length() > 0) {
                if (map.get(i).getString("det_pisaliq_cst") != null) {
                    PercorrejcbxNum(jcbx_det_pisaliq_cst, formata.StrZero(map.get(i).getString("det_pisaliq_cst").trim(), 2), 2);
                }
                //PercorrejcbxNum(jcbx_det_pisaliq_tipocalculo, formata.StrZero("0", 1), 1);
                if (map.get(i).getString("det_pisaliq_vbc") != null) {
                    PercorrejcbxNum(jcbx_det_pisaliq_tipocalculo, formata.StrZero("0", 1), 1);
                    tf_det_pisaliq_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_pisaliq_vbc")));
                }
                if (map.get(i).getString("det_pisaliq_ppis") != null) {
                    tf_det_pisaliq_ppis.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_pisaliq_ppis")));
                }
                if (map.get(i).getString("det_pisaliq_vpis") != null) {
                    tf_det_pisaliq_vpis.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_pisaliq_vpis")));
                }
            }
            if (map.get(i).getString("det_pisoutr_cst") != null
                    && map.get(i).getString("det_pisoutr_cst").trim().length() > 0) {
                if (map.get(i).getString("det_pisoutr_cst") != null) {
                    PercorrejcbxNum(jcbx_det_pisaliq_cst, formata.StrZero(map.get(i).getString("det_pisoutr_cst").trim(), 2), 2);
                }
                if (map.get(i).getString("det_pisoutr_vbc") != null) {
                    PercorrejcbxNum(jcbx_det_pisaliq_tipocalculo, formata.StrZero("0", 1), 1);
                    tf_det_pisaliq_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_pisoutr_vbc")));
                }
                if (map.get(i).getString("det_pisoutr_ppis") != null) {
                    tf_det_pisaliq_ppis.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_pisoutr_ppis")));
                }
                if (map.get(i).getString("det_pisoutr_vpis") != null) {
                    tf_det_pisaliq_vpis.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_pisoutr_vpis")));
                }
            }
            //Tributos. COFINS
            if (map.get(i).getString("det_cofinsaliq_cst") != null) {
                if (map.get(i).getString("det_cofinsaliq_cst") != null) {
                    PercorrejcbxNum(jcbx_det_cofinsaliq_cst, formata.StrZero(map.get(i).getString("det_cofinsaliq_cst").trim(), 2), 2);
                }
                if (map.get(i).getString("det_cofinsaliq_vbc") != null) {
                    PercorrejcbxNum(jcbx_det_cofinsaliq_tipocalculo, formata.StrZero("0", 1), 1);
                    tf_det_cofinsaliq_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cofinsaliq_vbc")));
                }
                if (map.get(i).getString("det_cofinsaliq_pcofins") != null) {
                    tf_det_cofinsaliq_pcofins.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_cofinsaliq_pcofins")));
                }
                if (map.get(i).getString("det_cofinsaliq_vcofins") != null) {
                    tf_det_cofinsaliq_vcofins.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cofinsaliq_vcofins")));
                }
            }
            if (map.get(i).getString("det_cofinsoutr_cst") != null
                    && map.get(i).getString("det_cofinsoutr_cst").trim().length() > 0) {
                if (map.get(i).getString("det_cofinsoutr_cst") != null) {
                    PercorrejcbxNum(jcbx_det_cofinsaliq_cst, formata.StrZero(map.get(i).getString("det_cofinsoutr_cst").trim(), 2), 2);
                }
                if (map.get(i).getString("det_cofinsoutr_vbc") != null) {
                    PercorrejcbxNum(jcbx_det_cofinsaliq_tipocalculo, formata.StrZero("0", 1), 1);
                    tf_det_cofinsaliq_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cofinsoutr_vbc")));
                }
                if (map.get(i).getString("det_cofinsoutr_pcofins") != null) {
                    tf_det_cofinsaliq_pcofins.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_cofinsoutr_pcofins")));
                }
                if (map.get(i).getString("det_cofinsoutr_vcofins") != null) {
                    tf_det_cofinsaliq_vcofins.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cofinsoutr_vcofins")));
                }
            }
            //Inicio: 11/07/2025
            if (map.get(i).getString("det_ibscbs_vbc") != null
                    && !map.get(i).getString("det_ibscbs_vbc").isEmpty()) {
                tf_det_ibsuf_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibscbs_vbc")));
                if (map.get(i).getString("det_ibsuf_pibsuf") == null
                        || map.get(i).getString("det_ibsuf_pibsuf").isEmpty()) {
                    tf_det_ibsuf_pibsuf.setText("0,1000");
                    calcula_ibsuf_vibsuf();
                }
            }
            if (map.get(i).getString("det_ibscbs_vbc") != null
                    && !map.get(i).getString("det_ibscbs_vbc").isEmpty()) {
                tf_det_ibsmun_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibscbs_vbc")));
                if (map.get(i).getString("det_ibsmun_pibsmun") == null
                        || map.get(i).getString("det_ibsmun_pibsmun").isEmpty()) {
                    tf_det_ibsmun_pibsmun.setText("0,0000");
                    calcula_ibsmun_vibsmun();
                }
            }
            if (map.get(i).getString("det_is_vbcimpsel") != null
                    && !map.get(i).getString("det_is_vbcimpsel").isEmpty()) {
                tf_det_is_vbcimpsel.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_is_vbcimpsel")));
                if (map.get(i).getString("det_is_pimpsel") == null
                        || map.get(i).getString("det_is_pimpsel").isEmpty()) {
                    tf_det_is_pimpsel.setText("0,0000");
                    calcula_is_vimpsel();
                }
            }
            if (map.get(i).getString("det_ibscbs_vbc") != null
                    && !map.get(i).getString("det_ibscbs_vbc").isEmpty()) {
                tf_det_cbs_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibscbs_vbc")));

                if (map.get(i).getString("det_cbs_pcbs") == null
                        || map.get(i).getString("det_cbs_pcbs").isEmpty()) {
                    tf_det_cbs_pcbs.setText("0,9000");
                    calcula_cbs_vcbs();
                }
            }
            //Fim: 11/07/2025
            //Informações Adicionais
            if (map.get(i).getString("det_infadprod") != null) {
                jtxt_det_infadprod.setText(map.get(i).getString("det_infadprod").trim());
            }
            //Tributos. IBS
            //if (map.get(i).getString("det_ibs_cst") != null
            //        && map.get(i).getString("det_ibs_cclasstrib") != null) {
            if (map.get(i).getString("det_ibs_cst") != null
                    && !map.get(i).getString("det_ibs_cst").isEmpty()) {
                PercorrejcbxNum(jcbx_det_ibs_cst, formata.StrZero(map.get(i).getString("det_ibs_cst").trim(), 3), 3);
            } else {
                jcbx_det_ibs_cst.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_ibs_cclasstrib") != null
                    && !map.get(i).getString("det_ibs_cclasstrib").isEmpty()) {
                PercorrejcbxNum(jcbx_det_ibs_cclasstrib, formata.StrZero(map.get(i).getString("det_ibs_cclasstrib").trim(), 6), 6);
            } else {
                jcbx_det_ibs_cclasstrib.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_ibs_pibstotal") != null
                    && !map.get(i).getString("det_ibs_pibstotal").isEmpty()) {
                tf_det_ibs_pibstotal.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_ibs_pibstotal")));
            }
            if (map.get(i).getString("det_ibs_pibsuf") != null
                    && !map.get(i).getString("det_ibs_pibsuf").isEmpty()) {
                tf_det_ibs_pibsuf.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibs_pibsuf")));
                double det_ibs_pibsuf = getDoubleFromTextField(tf_det_ibs_pibsuf) / 100;
                tf_det_ibs_pibsuf.setText(df2.format(det_ibs_pibsuf));
            }
            if (map.get(i).getString("det_ibs_pibsmun") != null
                    && !map.get(i).getString("det_ibs_pibsmun").isEmpty()) {
                tf_det_ibs_pibsmun.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibs_pibsmun")));
                double det_ibs_pibsmun = getDoubleFromTextField(tf_det_ibs_pibsmun) / 100;
                tf_det_ibs_pibsmun.setText(df2.format(det_ibs_pibsmun));
            }
            if (map.get(i).getString("det_ibsuf_vbc") != null
                    && !map.get(i).getString("det_ibsuf_vbc").isEmpty()) {
                // tf_det_ibsuf_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibsuf_vbc")));
            }
            if (map.get(i).getString("det_ibsuf_pibsuf") != null
                    && !map.get(i).getString("det_ibsuf_pibsuf").isEmpty()) {
                tf_det_ibsuf_pibsuf.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_ibsuf_pibsuf")));
            }
            if (map.get(i).getString("det_ibsuf_vibsuf") != null
                    && !map.get(i).getString("det_ibsuf_vibsuf").isEmpty()) {
                tf_det_ibsuf_vibsuf.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibsuf_vibsuf")));
            }
            if (map.get(i).getString("det_ibsmun_vbc") != null
                    && !map.get(i).getString("det_ibsmun_vbc").isEmpty()) {
                // tf_det_ibsmun_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibsmun_vbc")));
            }
            if (map.get(i).getString("det_ibsmun_pibsmun") != null
                    && !map.get(i).getString("det_ibsmun_pibsmun").isEmpty()) {
                tf_det_ibsmun_pibsmun.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_ibsmun_pibsmun")));
            }
            if (map.get(i).getString("det_ibsmun_vibsmun") != null
                    && !map.get(i).getString("det_ibsmun_vibsmun").isEmpty()) {
                tf_det_ibsmun_vibsmun.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_ibsmun_vibsmun")));
            }
            //}
            //Tributos. IS
            //if (map.get(i).getString("det_is_cst") != null
            //        && map.get(i).getString("det_is_cclasstrib") != null) {
            if (map.get(i).getString("det_is_cst") != null
                    && !map.get(i).getString("det_is_cst").isEmpty()) {
                PercorrejcbxNum(jcbx_det_is_cst, formata.StrZero(map.get(i).getString("det_is_cst").trim(), 3), 3);
            } else {
                jcbx_det_is_cst.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_is_cclasstrib") != null
                    && !map.get(i).getString("det_is_cclasstrib").isEmpty()) {
                PercorrejcbxNum(jcbx_det_is_cclasstrib, formata.StrZero(map.get(i).getString("det_is_cclasstrib").trim(), 6), 6);
            } else {
                jcbx_det_is_cclasstrib.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_is_vbcimpsel") != null
                    && !map.get(i).getString("det_is_vbcimpsel").isEmpty()) {
                // tf_det_is_vbcimpsel.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_is_vbcimpsel")));
            }
            if (map.get(i).getString("det_is_pimpsel") != null
                    && !map.get(i).getString("det_is_pimpsel").isEmpty()) {
                tf_det_is_pimpsel.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_is_pimpsel")));
            } else {
                tf_det_is_vbcimpsel.setText("");
            }
            if (map.get(i).getString("det_is_vimpsel") != null
                    && !map.get(i).getString("det_is_vimpsel").isEmpty()) {
                tf_det_is_vimpsel.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_is_vimpsel")));
            }
            if (map.get(i).getString("det_is_pimpselespec") != null
                    && !map.get(i).getString("det_is_pimpselespec").isEmpty()) {
                tf_det_is_pimpselespec.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_is_pimpselespec")));
            }
            if (map.get(i).getString("det_is_utrib") != null
                    && !map.get(i).getString("det_is_utrib").isEmpty()) {
                tf_det_is_utrib.setText(map.get(i).getString("det_is_utrib").trim());
            }
            if (map.get(i).getString("det_is_qtrib") != null
                    && !map.get(i).getString("det_is_qtrib").isEmpty()) {
                tf_det_is_qtrib.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_is_qtrib")));
            }
            if (map.get(i).getString("det_is_cstibscbs") != null
                    && !map.get(i).getString("det_is_cstibscbs").isEmpty()) {
                PercorrejcbxNum(jcbx_det_is_cstibscbs, formata.StrZero(map.get(i).getString("det_is_cstibscbs").trim(), 3), 3);
            } else {
                jcbx_det_is_cstibscbs.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_is_cclasstribibscbs") != null
                    && !map.get(i).getString("det_is_cclasstribibscbs").isEmpty()) {
                PercorrejcbxNum(jcbx_det_is_cclasstribibscbs, formata.StrZero(map.get(i).getString("det_is_cclasstribibscbs").trim(), 6), 6);
            } else {
                jcbx_det_is_cclasstribibscbs.setSelectedIndex(0);
            }
            //Tributos. CBS
            //if (map.get(i).getString("det_cbs_cst") != null
            //        && map.get(i).getString("det_cbs_cst") != null) {
            if (map.get(i).getString("det_cbs_cst") != null
                    && !map.get(i).getString("det_cbs_cst").isEmpty()) {
                PercorrejcbxNum(jcbx_det_cbs_cst, formata.StrZero(map.get(i).getString("det_cbs_cst").trim(), 3), 3);
            } else {
                jcbx_det_cbs_cst.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_cbs_cclasstrib") != null
                    && !map.get(i).getString("det_cbs_cclasstrib").isEmpty()) {
                PercorrejcbxNum(jcbx_det_cbs_cclasstrib, formata.StrZero(map.get(i).getString("det_cbs_cclasstrib").trim(), 6), 6);
            } else {
                jcbx_det_cbs_cclasstrib.setSelectedIndex(0);
            }
            if (map.get(i).getString("det_cbs_pcbsref") != null
                    && !map.get(i).getString("det_cbs_pcbsref").isEmpty()) {
                tf_det_cbs_pcbsref.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_cbs_pcbsref")));
            }
            if (map.get(i).getString("det_cbs_vbc") != null
                    && !map.get(i).getString("det_cbs_vbc").isEmpty()) {
                // tf_det_cbs_vbc.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cbs_vbc")));
            }
            if (map.get(i).getString("det_cbs_pcbs") != null
                    && !map.get(i).getString("det_cbs_pcbs").isEmpty()) {
                tf_det_cbs_pcbs.setText(f.getNumeroFormatado4cd(map.get(i).getString("det_cbs_pcbs")));
            }
            if (map.get(i).getString("det_cbs_vcbs") != null
                    && !map.get(i).getString("det_cbs_vcbs").isEmpty()) {
                tf_det_cbs_vcbs.setText(f.getNumeroFormatado2cd(map.get(i).getString("det_cbs_vcbs")));
            }
            //}
            java.awt.event.ActionEvent evt = null;
            jcbx_det_ibs_pibstesteActionPerformed(evt);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        tf_det_prod_cprod = new javax.swing.JTextField();
        tf_det_prod_xprod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        botao_Pesquisar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tf_det_prod_xPed = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_det_prod_nFCI = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_det_prod_cest = new javax.swing.JTextField();
        tf_det_prod_ucom = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tf_det_prod_utrib = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_det_prod_vseg = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tf_det_prod_cean = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_det_prod_vprod = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jcbx_det_prod_cfop = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        tf_det_prod_ncm = new javax.swing.JTextField();
        jcbx_det_prod_produtoespecifico = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        tf_det_prod_voutro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tf_det_prod_qcom = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tf_det_prod_qtrib = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tf_det_prod_vdesc = new javax.swing.JTextField();
        jCheck_det_prod_indtot = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        tf_det_prod_ceantrib = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tf_det_prod_nItemPed = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tf_det_prod_EXTIPI = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        tf_det_prod_vuncom = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        tf_det_prod_vuntrib = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tf_det_prod_vfrete = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        tf_det_imposto_vTotTrib = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jcbx_det_icms_crt = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcbx_det_icms_cst = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jcbx_det_icms_orig = new javax.swing.JComboBox();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanelICMS_00 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jcbx_det_icms00_modbc = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        tf_det_icms00_vbc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        tf_det_icms00_picms = new javax.swing.JTextField();
        tf_det_icms00_vicms = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jPanelICMS_10 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jcbx_det_icms10_modbc = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        tf_det_icms10_vbc = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        tf_det_icms10_picms = new javax.swing.JTextField();
        tf_det_icms10_vicms = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jcbx_det_icms10_modbcst = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        tf_det_icms10_predbcst = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        tf_det_icms10_pmvast = new javax.swing.JTextField();
        tf_det_icms10_vbcst = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tf_det_icms10_picmsst = new javax.swing.JTextField();
        tf_det_icms10_vicmsst = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jPanelICMS_20 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jcbx_det_icms20_motdesicms = new javax.swing.JComboBox();
        tf_det_icms20_predbc = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        tf_det_icms20_vbc = new javax.swing.JTextField();
        tf_det_icms20_vicmsdeson = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        tf_det_icms20_picms = new javax.swing.JTextField();
        tf_det_icms20_vicms = new javax.swing.JTextField();
        jcbx_det_icms20_modbc = new javax.swing.JComboBox();
        jLabel81 = new javax.swing.JLabel();
        jPanelICMS_30 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jcbx_det_icms30_motdesicms = new javax.swing.JComboBox();
        tf_det_icms30_predbcst = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        tf_det_icms30_pmvast = new javax.swing.JTextField();
        tf_det_icms30_vicmsdeson = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        tf_det_icms30_picmsst = new javax.swing.JTextField();
        tf_det_icms30_vicmsst = new javax.swing.JTextField();
        jcbx_det_icms30_modbcst = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        tf_ICMSTot_vBC40 = new javax.swing.JTextField();
        jPanelICMS_40 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jcbx_det_icms40_motdesicms = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        tf_det_icms40_vicmsdeson = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        tf_det_icms40_vicms = new javax.swing.JTextField();
        jPanelICMS_51 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        tf_det_icms51_predbc = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        tf_det_icms51_vbc = new javax.swing.JTextField();
        tf_det_icms51_pdif = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        tf_det_icms51_vicmsop = new javax.swing.JTextField();
        tf_det_icms51_vicms = new javax.swing.JTextField();
        jcbx_det_icms51_modbc = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        tf_det_icms51_picms = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        tf_det_icms51_vicmsdif = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jPanelICMS_60 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        tf_det_icms60_vicmsstret = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        tf_det_icms60_vbcstret = new javax.swing.JTextField();
        jPanelICMS_70 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jcbx_det_icms70_modbc = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        tf_det_icms70_predbc = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        tf_det_icms70_vbc = new javax.swing.JTextField();
        tf_det_icms70_vicms = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        tf_det_icms70_vicmsdeson = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jcbx_det_icms70_motdesicms = new javax.swing.JComboBox();
        tf_det_icms70_picms = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jcbx_det_icms70_modbcst = new javax.swing.JComboBox();
        jLabel83 = new javax.swing.JLabel();
        tf_det_icms70_predbcst = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        tf_det_icms70_pmvast = new javax.swing.JTextField();
        tf_det_icms70_vbcst = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        tf_det_icms70_picmsst = new javax.swing.JTextField();
        tf_det_icms70_vicmsst = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jPanelICMS_90 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jcbx_det_icms90_modbcst = new javax.swing.JComboBox();
        jLabel94 = new javax.swing.JLabel();
        tf_det_icms90_predbcst = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        tf_det_icms90_pmvast = new javax.swing.JTextField();
        tf_det_icms90_vbcst = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        tf_det_icms90_picmsst = new javax.swing.JTextField();
        tf_det_icms90_vicmsst = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jcbx_det_icms90_modbc = new javax.swing.JComboBox();
        jLabel100 = new javax.swing.JLabel();
        tf_det_icms90_predbc = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        tf_det_icms90_vbc = new javax.swing.JTextField();
        tf_det_icms90_vicms = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        tf_det_icms90_vicmsdeson = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jcbx_det_icms90_motdesicms = new javax.swing.JComboBox();
        tf_det_icms90_picms = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jcbx_det_ipitrib_tipocalculo = new javax.swing.JComboBox();
        jLabel107 = new javax.swing.JLabel();
        tf_det_ipitrib_vipi = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        tf_det_ipi_clenq = new javax.swing.JTextField();
        tf_det_ipi_cnpjprod = new javax.swing.JTextField();
        tf_det_ipi_cselo = new javax.swing.JTextField();
        tf_det_ipi_qselo = new javax.swing.JTextField();
        tf_det_ipitrib_vbc = new javax.swing.JTextField();
        tf_det_ipitrib_pipi = new javax.swing.JTextField();
        tf_det_ipitrib_qunid = new javax.swing.JTextField();
        tf_det_ipitrib_vunid = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        tf_det_ipi_cenq = new javax.swing.JTextField();
        jcbx_det_ipitrib_cst = new javax.swing.JComboBox();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jcbx_det_pisaliq_tipocalculo = new javax.swing.JComboBox();
        jLabel121 = new javax.swing.JLabel();
        tf_det_pisaliq_vpis = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        tf_ICMSTot_vBC83 = new javax.swing.JTextField();
        tf_det_pisaliq_ppis = new javax.swing.JTextField();
        tf_det_pisqtde_valiqprod = new javax.swing.JTextField();
        tf_det_pisaliq_vbc = new javax.swing.JTextField();
        jcbx_det_pisaliq_cst = new javax.swing.JComboBox();
        jPanel38 = new javax.swing.JPanel();
        jcbx_det_pisst_tipocalculo = new javax.swing.JComboBox();
        jLabel130 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        tf_det_pisst_ppis = new javax.swing.JTextField();
        tf_det_pisst_vbc = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        tf_det_pisst_valiqprod = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        tf_det_pisst_qbcprod = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        tf_det_pisst_vpis = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jcbx_det_cofinsaliq_tipocalculo = new javax.swing.JComboBox();
        jLabel135 = new javax.swing.JLabel();
        tf_det_cofinsaliq_vcofins = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        tf_det_cofinsqtde_qbcprod = new javax.swing.JTextField();
        tf_det_cofinsaliq_pcofins = new javax.swing.JTextField();
        tf_det_cofinsqtde_valiqprod = new javax.swing.JTextField();
        tf_det_cofinsaliq_vbc = new javax.swing.JTextField();
        jcbx_det_cofinsaliq_cst = new javax.swing.JComboBox();
        jPanel40 = new javax.swing.JPanel();
        jcbx_det_cofinsst_tipocalculo = new javax.swing.JComboBox();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        tf_det_cofinsst_pcofins = new javax.swing.JTextField();
        tf_det_cofinsst_vbc = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        tf_det_cofinsst_valiqprod = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        tf_det_cofinsst_qbcprod = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        tf_det_cofinsst_vcofins = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jcbx_det_ibs_crt = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jcbx_det_ibs_cst = new javax.swing.JComboBox();
        jLabel156 = new javax.swing.JLabel();
        jcbx_det_ibs_cclasstrib = new javax.swing.JComboBox();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanelICMS_71 = new javax.swing.JPanel();
        jPanelICMS_13 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel235 = new javax.swing.JLabel();
        tf_det_ibsuf_vbc = new javax.swing.JTextField();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        tf_det_ibsuf_pibsuf = new javax.swing.JTextField();
        tf_det_ibsuf_vibsuf = new javax.swing.JTextField();
        jLabel238 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel245 = new javax.swing.JLabel();
        tf_det_ibsmun_vbc = new javax.swing.JTextField();
        jLabel246 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        tf_det_ibsmun_pibsmun = new javax.swing.JTextField();
        tf_det_ibsmun_vibsmun = new javax.swing.JTextField();
        jLabel249 = new javax.swing.JLabel();
        tf_det_ibs_pibstotal = new javax.swing.JTextField();
        tf_det_ibs_pibsuf = new javax.swing.JTextField();
        jLabel253 = new javax.swing.JLabel();
        tf_det_ibs_pibsmun = new javax.swing.JTextField();
        jLabel254 = new javax.swing.JLabel();
        jLabel255 = new javax.swing.JLabel();
        jcbx_det_ibs_pibsteste = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        jcbx_det_is_cst = new javax.swing.JComboBox();
        jLabel161 = new javax.swing.JLabel();
        jcbx_det_is_cclasstrib = new javax.swing.JComboBox();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanelICMS_73 = new javax.swing.JPanel();
        jPanelICMS_15 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel243 = new javax.swing.JLabel();
        tf_det_is_vbcimpsel = new javax.swing.JTextField();
        jLabel244 = new javax.swing.JLabel();
        tf_det_is_pimpsel = new javax.swing.JTextField();
        jLabel250 = new javax.swing.JLabel();
        tf_det_is_pimpselespec = new javax.swing.JTextField();
        jLabel251 = new javax.swing.JLabel();
        tf_det_is_utrib = new javax.swing.JTextField();
        jLabel248 = new javax.swing.JLabel();
        tf_det_is_vimpsel = new javax.swing.JTextField();
        tf_det_is_qtrib = new javax.swing.JTextField();
        jLabel252 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jcbx_det_is_cstibscbs = new javax.swing.JComboBox();
        jLabel167 = new javax.swing.JLabel();
        jcbx_det_is_cclasstribibscbs = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jcbx_det_cbs_cst = new javax.swing.JComboBox();
        jLabel159 = new javax.swing.JLabel();
        jcbx_det_cbs_cclasstrib = new javax.swing.JComboBox();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanelICMS_72 = new javax.swing.JPanel();
        jPanelICMS_14 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jLabel239 = new javax.swing.JLabel();
        tf_det_cbs_vbc = new javax.swing.JTextField();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        tf_det_cbs_pcbs = new javax.swing.JTextField();
        tf_det_cbs_vcbs = new javax.swing.JTextField();
        jLabel242 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        jcbx_det_cbs_pcbsteste = new javax.swing.JComboBox<>();
        jLabel258 = new javax.swing.JLabel();
        tf_det_cbs_pcbsref = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        tf_ICMSUFDest_vICMSUFDest = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        tf_ICMSUFDest_pFCPUFDest = new javax.swing.JTextField();
        tf_ICMSUFDest_vBCUFDest = new javax.swing.JTextField();
        tf_ICMSUFDest_pICMSUFDest = new javax.swing.JTextField();
        tf_ICMSUFDest_vFCPUFDest = new javax.swing.JTextField();
        tf_ICMSUFDest_vICMSUFRemet = new javax.swing.JTextField();
        jcbx_ICMSUFDest_pICMSInterPart = new javax.swing.JComboBox();
        jcbx_ICMSUFDest_pICMSInter = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel256 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxt_det_infadprod = new javax.swing.JTextArea();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        botao_sair = new javax.swing.JButton();
        botao_editar = new javax.swing.JButton();
        botao_salvar = new javax.swing.JButton();

        setForeground(java.awt.Color.white);
        setTitle("Produto e Serviço - Revisão: 05 - Data da Última Revisão: 24/11/2025 - Data da Elaboração: 27/06/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto e Serviço", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jPanel22.setBackground(new java.awt.Color(223, 218, 218));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel22.setDoubleBuffered(false);
        jPanel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPanel22.setOpaque(false);

        tf_det_prod_cprod.setEditable(false);
        tf_det_prod_cprod.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_cprod.setToolTipText("");
        tf_det_prod_cprod.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_det_prod_cprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_cprod.setEnabled(false);

        tf_det_prod_xprod.setEditable(false);
        tf_det_prod_xprod.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_xprod.setToolTipText("");
        tf_det_prod_xprod.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tf_det_prod_xprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_xprod.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tot. Seguro");

        botao_Pesquisar.setMnemonic('V');
        botao_Pesquisar.setText("Pesquisar");
        botao_Pesquisar.setToolTipText("");
        botao_Pesquisar.setEnabled(false);
        botao_Pesquisar.setPreferredSize(new java.awt.Dimension(73, 23));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("* Código");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("* Descrição");

        tf_det_prod_xPed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_xPed.setToolTipText("");
        tf_det_prod_xPed.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_xPed.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Número de Controle da FCI");

        tf_det_prod_nFCI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_nFCI.setToolTipText("");
        tf_det_prod_nFCI.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_nFCI.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("EAN");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Produto específico");

        tf_det_prod_cest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_cest.setToolTipText("");
        tf_det_prod_cest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_cest.setEnabled(false);

        tf_det_prod_ucom.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_ucom.setToolTipText("");
        tf_det_prod_ucom.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_ucom.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("* Un. Trib.");

        tf_det_prod_utrib.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_utrib.setToolTipText("");
        tf_det_prod_utrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_utrib.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("CEST");

        tf_det_prod_vseg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vseg.setToolTipText("");
        tf_det_prod_vseg.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vseg.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("* Un. Comercial");

        tf_det_prod_cean.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_cean.setToolTipText("");
        tf_det_prod_cean.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_cean.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("* Valor Tot. Bruto");

        tf_det_prod_vprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vprod.setToolTipText("");
        tf_det_prod_vprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vprod.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Pedido de Compra");

        jcbx_det_prod_cfop.setToolTipText("Selecione o Segmento");
        jcbx_det_prod_cfop.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("* NCM");

        tf_det_prod_ncm.setEditable(false);
        tf_det_prod_ncm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_ncm.setToolTipText("");
        tf_det_prod_ncm.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_ncm.setEnabled(false);

        jcbx_det_prod_produtoespecifico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "         ", "Veículo", "Medicamento", "Armamento", "Combustível", "Papel Imune" }));
        jcbx_det_prod_produtoespecifico.setToolTipText("Selecione o Segmento");
        jcbx_det_prod_produtoespecifico.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("* CFOP");

        tf_det_prod_voutro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_voutro.setToolTipText("");
        tf_det_prod_voutro.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_voutro.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("* Qtd. Comercial");

        tf_det_prod_qcom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_qcom.setToolTipText("");
        tf_det_prod_qcom.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_qcom.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("* Qtd. Trib.");

        tf_det_prod_qtrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_qtrib.setToolTipText("");
        tf_det_prod_qtrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_qtrib.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Desconto");

        tf_det_prod_vdesc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vdesc.setToolTipText("");
        tf_det_prod_vdesc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vdesc.setEnabled(false);

        jCheck_det_prod_indtot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheck_det_prod_indtot.setText("Valor Total Bruto compõe o Valor Total dos Produtos e Serviços.");
        jCheck_det_prod_indtot.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("EAN Trib.");

        tf_det_prod_ceantrib.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_det_prod_ceantrib.setToolTipText("");
        tf_det_prod_ceantrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_ceantrib.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Número do Item do Pedido de Compra");

        tf_det_prod_nItemPed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_nItemPed.setToolTipText("");
        tf_det_prod_nItemPed.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_nItemPed.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("EX TIPI");

        tf_det_prod_EXTIPI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_EXTIPI.setToolTipText("");
        tf_det_prod_EXTIPI.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_EXTIPI.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("* Valor Unit. Comercial");

        tf_det_prod_vuncom.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vuncom.setToolTipText("");
        tf_det_prod_vuncom.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vuncom.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("* Valor Unit. Trib.");

        tf_det_prod_vuntrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vuntrib.setToolTipText("");
        tf_det_prod_vuntrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vuntrib.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Tot. Frete");

        tf_det_prod_vfrete.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_prod_vfrete.setToolTipText("");
        tf_det_prod_vfrete.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_prod_vfrete.setEnabled(false);

        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel128.setText("Outras Desp. Acessórias");

        jLabel147.setForeground(new java.awt.Color(255, 51, 51));
        jLabel147.setText("<html> <table> <td color=\"blank\" align=left> <p>Desativado, ZERA Valor Total do(s) produto(s) e o Valor Total da Nota.<br></p> </td> </table> </html>");
        jLabel147.setToolTipText("");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_cprod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botao_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_det_prod_utrib, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_det_prod_qtrib, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_det_prod_vuntrib, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_det_prod_ucom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_det_prod_qcom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_det_prod_vuncom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_det_prod_xprod, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_ncm, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(tf_det_prod_EXTIPI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_cest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jcbx_det_prod_cfop, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_vseg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_prod_vdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_det_prod_vfrete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_cean, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_prod_ceantrib, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_det_prod_voutro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_det_prod_vprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_prod_xPed, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheck_det_prod_indtot)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(tf_det_prod_nItemPed, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(380, 380, 380)
                                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_prod_produtoespecifico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_nFCI)
                        .addGap(18, 18, 18))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_det_prod_cprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botao_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_xprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_ncm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_EXTIPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_cest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_prod_cfop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_ucom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_qcom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_det_prod_vuncom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_utrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_qtrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_det_prod_vuntrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_vseg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_vdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_vfrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_cean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_ceantrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_voutro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(tf_det_prod_vprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_prod_xPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jCheck_det_prod_indtot)
                        .addGap(7, 7, 7)
                        .addComponent(tf_det_prod_nItemPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_prod_produtoespecifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_prod_nFCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dados", jPanel1);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setText("Valor Total dos Tributos");

        tf_det_imposto_vTotTrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_imposto_vTotTrib.setToolTipText("");
        tf_det_imposto_vTotTrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_imposto_vTotTrib.setEnabled(false);

        jLabel78.setFont(new java.awt.Font("Arial", 2, 9)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("<html> <table> <td color=\"blank\" align=left> <p>Valor aproximado total tributos federais, estaduais e municipais conforme<br>disposto na Lei nº 12.741/12. <br></p> </td> </table> </html>");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("* Regime");

        jcbx_det_icms_crt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Simples Nacional", "2 - Simples Nacional, excesso sublimite de receita bruta", "3 - Tributação Normal" }));
        jcbx_det_icms_crt.setSelectedIndex(2);
        jcbx_det_icms_crt.setEnabled(false);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Tributação Normal"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("* Situação tributária");

        jcbx_det_icms_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00 - Tributada integralmente", "10 - Tributada e com cobrança do ICMS por substituição tributária", "20 - Com redução de base de cálculo", "30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária", "40 - Isenta", "41 - Não tributada", "50 - Suspensão", "51 - Diferimento", "60 - ICMS cobrado anteriormente por substituição tributária", "70 - Com redução de base de cálculo e cobrança do ICMS por substituição tributária", "90 - Outros" }));
        jcbx_det_icms_cst.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("* Origem");

        jcbx_det_icms_orig.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Nacional, exceto as indicadas nos códigos 3, 4, 5 e 8", "1 - Estrangeira - Importação direta, exceto a indicada no código 6", "2 - Estrangeira - Adquirida no mercado interno, exceto a indicada no código 7", "3 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40% e inferior ou igual a 70%", "4 - Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam as legislações citadas nos Ajustes", "5 - Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%", "6 - Estrangeira - Importação direta, sem similar nacional, constante em lista da CAMEX e gás natural", "7 - Estrangeira - Adquirida no mercado interno, sem similar nacional, constante lista CAMEX e gás natural", "8 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 70%" }));
        jcbx_det_icms_orig.setEnabled(false);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("* Modalid. de determ. da BC ICMS");

        jcbx_det_icms00_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Margem Valor Agregado (%)", "1 - Pauta (Valor)", "2 - Preço Tabelado Máx. (valor)", "3 - Valor da Operação" }));
        jcbx_det_icms00_modbc.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* BC ICMS ¹");

        tf_det_icms00_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms00_vbc.setToolTipText("");
        tf_det_icms00_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms00_vbc.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("* Aliquota do ICMS");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("* ICMS");

        tf_det_icms00_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms00_picms.setToolTipText("");
        tf_det_icms00_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms00_picms.setEnabled(false);

        tf_det_icms00_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms00_vicms.setToolTipText("");
        tf_det_icms00_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms00_vicms.setEnabled(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms00_modbc, 0, 687, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms00_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_det_icms00_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_det_icms00_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms00_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms00_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms00_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms00_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel79.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos (frete, seguro, desconto, IPI, Outras Despesas Acessórias, etc).<br></p> </td> </table> </html>");

        javax.swing.GroupLayout jPanelICMS_00Layout = new javax.swing.GroupLayout(jPanelICMS_00);
        jPanelICMS_00.setLayout(jPanelICMS_00Layout);
        jPanelICMS_00Layout.setHorizontalGroup(
            jPanelICMS_00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_00Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelICMS_00Layout.setVerticalGroup(
            jPanelICMS_00Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_00Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel79)
                .addGap(16, 16, 16))
        );

        jTabbedPane3.addTab("ICMS=00", jPanelICMS_00);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("* Modalid. de determ. da BC ICMS");

        jcbx_det_icms10_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Margem Valor Agregado (%)", "1 - Pauta (Valor)", "2 - Preço Tabelado Máx. (valor)", "3 - Valor da Operação" }));
        jcbx_det_icms10_modbc.setEnabled(false);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("* BC ICMS ¹");

        tf_det_icms10_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_vbc.setToolTipText("");
        tf_det_icms10_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_vbc.setEnabled(false);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("* Aliquota do ICMS");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("* ICMS");

        tf_det_icms10_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_picms.setToolTipText("");
        tf_det_icms10_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_picms.setEnabled(false);

        tf_det_icms10_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_vicms.setToolTipText("");
        tf_det_icms10_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_vicms.setEnabled(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jcbx_det_icms10_modbc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(tf_det_icms10_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms10_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms10_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms10_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel80.setFont(new java.awt.Font("Arial", 2, 9)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos (frete, seguro, desconto, IPI, Outras Despesas Acessórias, etc).<br></p> </td> </table> </html>");

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS ST"));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("* Modalid. de determ. da BC ICMS ST");

        jcbx_det_icms10_modbcst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Preço tabelado ou máximo sugerido", "1 - Lista Negativa (valor)", "2 - Lista Positiva (valor)", "3 - Lista Neutra (valor)", "4 - Margem Valor Agregado (%)", "5 - Pauta (valor)" }));
        jcbx_det_icms10_modbcst.setEnabled(false);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("* BC ICMS ¹");

        tf_det_icms10_predbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_predbcst.setToolTipText("");
        tf_det_icms10_predbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_predbcst.setEnabled(false);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("* Aliquota do ICMS");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("* ICMS");

        tf_det_icms10_pmvast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_pmvast.setToolTipText("");
        tf_det_icms10_pmvast.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_pmvast.setEnabled(false);

        tf_det_icms10_vbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_vbcst.setToolTipText("");
        tf_det_icms10_vbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_vbcst.setEnabled(false);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("% margem de valor adic. ICMS ST");

        tf_det_icms10_picmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_picmsst.setToolTipText("");
        tf_det_icms10_picmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_picmsst.setEnabled(false);

        tf_det_icms10_vicmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms10_vicmsst.setToolTipText("");
        tf_det_icms10_vicmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms10_vicmsst.setEnabled(false);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("% redução da BC ICMS ST");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms10_modbcst, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(tf_det_icms10_vicmsst, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(tf_det_icms10_picmsst))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(tf_det_icms10_vbcst))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_det_icms10_predbcst))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_det_icms10_pmvast)))
                        .addGap(60, 60, 60))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms10_modbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_vbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms10_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelICMS_10Layout = new javax.swing.GroupLayout(jPanelICMS_10);
        jPanelICMS_10.setLayout(jPanelICMS_10Layout);
        jPanelICMS_10Layout.setHorizontalGroup(
            jPanelICMS_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelICMS_10Layout.createSequentialGroup()
                .addGap(422, 422, 422)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelICMS_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelICMS_10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanelICMS_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(504, Short.MAX_VALUE)))
        );
        jPanelICMS_10Layout.setVerticalGroup(
            jPanelICMS_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_10Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(jPanelICMS_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_10Layout.createSequentialGroup()
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane3.addTab("ICMS=10", jPanelICMS_10);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("* Modalid. de determ. da BC ICMS");

        jcbx_det_icms20_motdesicms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "03 - Uso na agropecuária", "09 - Outros", "12 - Órgão de fomento e desenvolvimento agropecuário" }));
        jcbx_det_icms20_motdesicms.setEnabled(false);

        tf_det_icms20_predbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms20_predbc.setToolTipText("");
        tf_det_icms20_predbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms20_predbc.setEnabled(false);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("* Aliquota do ICMS");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Motivo da Desoneração do ICMS");

        tf_det_icms20_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms20_vbc.setToolTipText("");
        tf_det_icms20_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms20_vbc.setEnabled(false);

        tf_det_icms20_vicmsdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms20_vicmsdeson.setToolTipText("");
        tf_det_icms20_vicmsdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms20_vicmsdeson.setEnabled(false);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("* BC ICMS ¹");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("* % redução da BC ICMS");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("* ICMS");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Valor ICMS Desonerado");

        tf_det_icms20_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms20_picms.setToolTipText("");
        tf_det_icms20_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms20_picms.setEnabled(false);

        tf_det_icms20_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms20_vicms.setToolTipText("");
        tf_det_icms20_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms20_vicms.setEnabled(false);

        jcbx_det_icms20_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Margem Valor Agregado (%)", "1 - Pauta (Valor)", "2 - Preço Tabelado Máx. (valor)", "3 - Valor da Operação" }));
        jcbx_det_icms20_modbc.setEnabled(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms20_modbc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_det_icms20_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(tf_det_icms20_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_det_icms20_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(jcbx_det_icms20_motdesicms, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_det_icms20_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_det_icms20_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)))
                .addGap(6, 6, 6))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms20_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms20_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms20_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms20_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_det_icms20_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_det_icms20_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcbx_det_icms20_motdesicms)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );

        jLabel81.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos (frete, seguro, desconto, IPI, Outras Despesas Acessórias, etc).<br></p> </td> </table> </html>");
        jLabel81.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanelICMS_20Layout = new javax.swing.GroupLayout(jPanelICMS_20);
        jPanelICMS_20.setLayout(jPanelICMS_20Layout);
        jPanelICMS_20Layout.setHorizontalGroup(
            jPanelICMS_20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelICMS_20Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelICMS_20Layout.setVerticalGroup(
            jPanelICMS_20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelICMS_20Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("ICMS=20", jPanelICMS_20);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("* Modalid. de determ. da BC ICMS ST");

        jcbx_det_icms30_motdesicms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "6 - Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio", "7 - SUFRAMA", "9 - Outros" }));
        jcbx_det_icms30_motdesicms.setEnabled(false);

        tf_det_icms30_predbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms30_predbcst.setToolTipText("");
        tf_det_icms30_predbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms30_predbcst.setEnabled(false);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("* Aliquota do ICMS ST");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Motivo da Desoneração do ICMS");

        tf_det_icms30_pmvast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms30_pmvast.setToolTipText("");
        tf_det_icms30_pmvast.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms30_pmvast.setEnabled(false);

        tf_det_icms30_vicmsdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms30_vicmsdeson.setToolTipText("");
        tf_det_icms30_vicmsdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms30_vicmsdeson.setEnabled(false);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("* BC ICMS ST");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setText("* % margem de valor adic. ICMS ST");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setText("* ICMS ST");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("Valor ICMS Desonerado");

        tf_det_icms30_picmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms30_picmsst.setToolTipText("");
        tf_det_icms30_picmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms30_picmsst.setEnabled(false);

        tf_det_icms30_vicmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms30_vicmsst.setToolTipText("");
        tf_det_icms30_vicmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms30_vicmsst.setEnabled(false);

        jcbx_det_icms30_modbcst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Preço tabelado ou máximo sugerido", "1 - Lista Negativa (valor)", "2 - Lista Positiva (valor)", "3 - Lista Neutra (valor)", "4 - Margem Valor Agregado (%)", "5 - Pauta (valor)" }));
        jcbx_det_icms30_modbcst.setEnabled(false);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("* % redução da BC ICMS ST");

        tf_ICMSTot_vBC40.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vBC40.setToolTipText("");
        tf_ICMSTot_vBC40.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vBC40.setEnabled(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms30_modbcst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbx_det_icms30_motdesicms, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_ICMSTot_vBC40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 234, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_det_icms30_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_det_icms30_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_det_icms30_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_det_icms30_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_det_icms30_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms30_modbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms30_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms30_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vBC40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_det_icms30_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_icms30_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_icms30_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms30_motdesicms))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelICMS_30Layout = new javax.swing.GroupLayout(jPanelICMS_30);
        jPanelICMS_30.setLayout(jPanelICMS_30Layout);
        jPanelICMS_30Layout.setHorizontalGroup(
            jPanelICMS_30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelICMS_30Layout.setVerticalGroup(
            jPanelICMS_30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_30Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane3.addTab("ICMS=30", jPanelICMS_30);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jcbx_det_icms40_motdesicms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "01 - Táxi", "03 - Produtor Agropecuário", "04 - Frotista/Locadora", "05 - Diplomático/Consular", "06 - Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio", "07 - SUFRAMA", "08 - Venda a Órgão Público", "09 - Outros. (NT 2011/004)", "10 - Deficiente Condutor (Convênio ICMS 38/12)", "11 - Deficiente Não Condutor (Convênio ICMS 38/12)" }));
        jcbx_det_icms40_motdesicms.setEnabled(false);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Motivo da Desoneração do ICMS");

        tf_det_icms40_vicmsdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms40_vicmsdeson.setToolTipText("");
        tf_det_icms40_vicmsdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms40_vicmsdeson.setEnabled(false);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setText("* ICMS");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setText("Valor ICMS Desonerado");

        tf_det_icms40_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms40_vicms.setToolTipText("");
        tf_det_icms40_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms40_vicms.setEnabled(false);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_det_icms40_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(tf_det_icms40_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jcbx_det_icms40_motdesicms, 0, 682, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms40_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms40_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms40_motdesicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelICMS_40Layout = new javax.swing.GroupLayout(jPanelICMS_40);
        jPanelICMS_40.setLayout(jPanelICMS_40Layout);
        jPanelICMS_40Layout.setHorizontalGroup(
            jPanelICMS_40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelICMS_40Layout.setVerticalGroup(
            jPanelICMS_40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("ICMS=40, 41 e 50", jPanelICMS_40);

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("Modalid. de determ. da BC ICMS");

        tf_det_icms51_predbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_predbc.setToolTipText("");
        tf_det_icms51_predbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_predbc.setEnabled(false);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("Aliquota do ICMS");

        tf_det_icms51_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_vbc.setToolTipText("");
        tf_det_icms51_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_vbc.setEnabled(false);

        tf_det_icms51_pdif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_pdif.setToolTipText("");
        tf_det_icms51_pdif.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_pdif.setEnabled(false);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setText("BC ICMS ¹");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setText("ICMS da Operação");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("% do Diferimento");

        tf_det_icms51_vicmsop.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_vicmsop.setToolTipText("");
        tf_det_icms51_vicmsop.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_vicmsop.setEnabled(false);

        tf_det_icms51_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_vicms.setToolTipText("");
        tf_det_icms51_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_vicms.setEnabled(false);

        jcbx_det_icms51_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Margem Valor Agregado (%)", "1 - Pauta (Valor)", "2 - Preço Tabelado Máx. (valor)", "3 - Valor da Operação" }));
        jcbx_det_icms51_modbc.setEnabled(false);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("% redução da BC ICMS ");

        tf_det_icms51_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_picms.setToolTipText("");
        tf_det_icms51_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_picms.setEnabled(false);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("ICMS");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setText("Valor ICMS Diferido");

        tf_det_icms51_vicmsdif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms51_vicmsdif.setToolTipText("");
        tf_det_icms51_vicmsdif.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms51_vicmsdif.setEnabled(false);

        jLabel82.setFont(new java.awt.Font("Arial", 2, 9)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos (frete, seguro, desconto, IPI, Outras Despesas Acessórias, etc).<br></p> </td> </table> </html>");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms51_modbc, 0, 675, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_det_icms51_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tf_det_icms51_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms51_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(tf_det_icms51_pdif, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_det_icms51_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_det_icms51_vicmsop, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_icms51_vicmsdif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms51_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_vicmsop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_pdif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms51_vicmsdif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelICMS_51Layout = new javax.swing.GroupLayout(jPanelICMS_51);
        jPanelICMS_51.setLayout(jPanelICMS_51Layout);
        jPanelICMS_51Layout.setHorizontalGroup(
            jPanelICMS_51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelICMS_51Layout.setVerticalGroup(
            jPanelICMS_51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("ICMS=51", jPanelICMS_51);

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        tf_det_icms60_vicmsstret.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms60_vicmsstret.setToolTipText("");
        tf_det_icms60_vicmsstret.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms60_vicmsstret.setEnabled(false);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setText("BC ICMS ST retido anteriormente");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setText("ICMS ST retido anteriormente");

        tf_det_icms60_vbcstret.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms60_vbcstret.setToolTipText("");
        tf_det_icms60_vbcstret.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms60_vbcstret.setEnabled(false);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms60_vbcstret, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms60_vicmsstret, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms60_vbcstret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms60_vicmsstret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelICMS_60Layout = new javax.swing.GroupLayout(jPanelICMS_60);
        jPanelICMS_60.setLayout(jPanelICMS_60Layout);
        jPanelICMS_60Layout.setHorizontalGroup(
            jPanelICMS_60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelICMS_60Layout.setVerticalGroup(
            jPanelICMS_60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_60Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("ICMS=60", jPanelICMS_60);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("* Modalid. de determ. da BC ICMS");

        jcbx_det_icms70_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Preço tabelado ou máximo sugerido", "1 - Lista Negativa (valor)", "2 - Lista Positiva (valor)", "3 - Lista Neutra (valor)", "4 - Margem Valor Agregado (%)", "5 - Pauta (valor)" }));
        jcbx_det_icms70_modbc.setEnabled(false);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setText("* BC ICMS ¹");

        tf_det_icms70_predbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_predbc.setToolTipText("");
        tf_det_icms70_predbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_predbc.setEnabled(false);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setText("* Aliquota do ICMS");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setText("* ICMS");

        tf_det_icms70_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_vbc.setToolTipText("");
        tf_det_icms70_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_vbc.setEnabled(false);

        tf_det_icms70_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_vicms.setToolTipText("");
        tf_det_icms70_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_vicms.setEnabled(false);

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setText("* % redução da BC ICMS ");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setText("Valor ICMS Desonerado");

        tf_det_icms70_vicmsdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_vicmsdeson.setToolTipText("");
        tf_det_icms70_vicmsdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_vicmsdeson.setEnabled(false);

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setText("Motivo da Desoneração do ICMS");

        jcbx_det_icms70_motdesicms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "03 - Uso na agropecuária", "09 - Outros", "12 - Órgão de fomento e desenvolvimento agropecuário" }));
        jcbx_det_icms70_motdesicms.setEnabled(false);

        tf_det_icms70_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_picms.setToolTipText("");
        tf_det_icms70_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_picms.setEnabled(false);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jcbx_det_icms70_modbc, 0, 237, Short.MAX_VALUE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(tf_det_icms70_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms70_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms70_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms70_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms70_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jcbx_det_icms70_motdesicms, 0, 0, Short.MAX_VALUE))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms70_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms70_motdesicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS ST"));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setText("* Modalid. de determ. da BC ICMS ST");

        jcbx_det_icms70_modbcst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "    ", "0 - Preço tabelado ou máximo sugerido", "1 - Lista Negativa (valor)", "2 - Lista Positiva (valor)", "3 - Lista Neutra (valor)", "4 - Margem Valor Agregado (%)", "5 - Pauta (valor)" }));
        jcbx_det_icms70_modbcst.setEnabled(false);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setText("* BC ICMS ST");

        tf_det_icms70_predbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_predbcst.setToolTipText("");
        tf_det_icms70_predbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_predbcst.setEnabled(false);

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setText("* Aliquota do ICMS ST");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setText("* ICMS ST");

        tf_det_icms70_pmvast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_pmvast.setToolTipText("");
        tf_det_icms70_pmvast.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_pmvast.setEnabled(false);

        tf_det_icms70_vbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_vbcst.setToolTipText("");
        tf_det_icms70_vbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_vbcst.setEnabled(false);

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setText("% margem de valor adic.  ICMS ST");

        tf_det_icms70_picmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_picmsst.setToolTipText("");
        tf_det_icms70_picmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_picmsst.setEnabled(false);

        tf_det_icms70_vicmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms70_vicmsst.setToolTipText("");
        tf_det_icms70_vicmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms70_vicmsst.setEnabled(false);

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setText("% redução da BC ICMS ST");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms70_modbcst, 0, 229, Short.MAX_VALUE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_icms70_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_icms70_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms70_vbcst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(tf_det_icms70_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(tf_det_icms70_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms70_modbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_vbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms70_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelICMS_70Layout = new javax.swing.GroupLayout(jPanelICMS_70);
        jPanelICMS_70.setLayout(jPanelICMS_70Layout);
        jPanelICMS_70Layout.setHorizontalGroup(
            jPanelICMS_70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_70Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelICMS_70Layout.setVerticalGroup(
            jPanelICMS_70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_70Layout.createSequentialGroup()
                .addGroup(jPanelICMS_70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(102, 102, 102))
        );

        jTabbedPane3.addTab("ICMS=70", jPanelICMS_70);

        jLabel92.setFont(new java.awt.Font("Arial", 2, 8)); // NOI18N
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos (frete, seguro, desconto, IPI, Outras Despesas Acessórias, etc).<br></p> </td> </table> </html>");

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS ST"));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setText("Modalid. de determ. da BC ICMS ST");

        jcbx_det_icms90_modbcst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Preço tabelado ou máximo sugerido", "1 - Lista Negativa (valor)", "2 - Lista Positiva (valor)", "3 - Lista Neutra (valor)", "4 - Margem Valor Agregado (%)", "5 - Pauta (valor)" }));
        jcbx_det_icms90_modbcst.setEnabled(false);

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setText("BC ICMS ST");

        tf_det_icms90_predbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_predbcst.setToolTipText("");
        tf_det_icms90_predbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_predbcst.setEnabled(false);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setText("Aliquota do ICMS ST");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setText("ICMS ST");

        tf_det_icms90_pmvast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_pmvast.setToolTipText("");
        tf_det_icms90_pmvast.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_pmvast.setEnabled(false);

        tf_det_icms90_vbcst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_vbcst.setToolTipText("");
        tf_det_icms90_vbcst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_vbcst.setEnabled(false);

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setText("% margem de valor adic.  ICMS ST");

        tf_det_icms90_picmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_picmsst.setToolTipText("");
        tf_det_icms90_picmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_picmsst.setEnabled(false);

        tf_det_icms90_vicmsst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_vicmsst.setToolTipText("");
        tf_det_icms90_vicmsst.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_vicmsst.setEnabled(false);

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setText("% redução da BC ICMS ST");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_icms90_modbcst, 0, 229, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_icms90_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_icms90_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_icms90_vbcst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(tf_det_icms90_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(tf_det_icms90_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms90_modbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_predbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_pmvast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_vbcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_picmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_vicmsst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("ICMS"));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setText("Modalid. de determ. da BC ICMS");

        jcbx_det_icms90_modbc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Margem Valor Agregado (%)", "1 - Pauta (Valor)", "2 - Preço Tabelado Máx. (valor)", "3 - Valor da Operação" }));
        jcbx_det_icms90_modbc.setEnabled(false);

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setText("BC ICMS ¹");

        tf_det_icms90_predbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_predbc.setToolTipText("");
        tf_det_icms90_predbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_predbc.setEnabled(false);

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setText("Aliquota do ICMS");

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setText("ICMS");

        tf_det_icms90_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_vbc.setToolTipText("");
        tf_det_icms90_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_vbc.setEnabled(false);

        tf_det_icms90_vicms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_vicms.setToolTipText("");
        tf_det_icms90_vicms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_vicms.setEnabled(false);

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setText("% redução da BC ICMS ");

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setText("Valor ICMS Desonerado");

        tf_det_icms90_vicmsdeson.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_vicmsdeson.setToolTipText("");
        tf_det_icms90_vicmsdeson.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_vicmsdeson.setEnabled(false);

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setText("Motivo da Desoneração do ICMS");

        jcbx_det_icms90_motdesicms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "03 - Uso na agropecuária", "09 - Outros", "12 - Órgão de fomento e desenvolvimento agropecuário" }));
        jcbx_det_icms90_motdesicms.setEnabled(false);

        tf_det_icms90_picms.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_icms90_picms.setToolTipText("");
        tf_det_icms90_picms.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_icms90_picms.setEnabled(false);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jcbx_det_icms90_modbc, 0, 237, Short.MAX_VALUE))
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(tf_det_icms90_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms90_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms90_picms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms90_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_icms90_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jcbx_det_icms90_motdesicms, 0, 0, Short.MAX_VALUE))))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbx_det_icms90_modbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_predbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_picms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_vicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_icms90_vicmsdeson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms90_motdesicms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelICMS_90Layout = new javax.swing.GroupLayout(jPanelICMS_90);
        jPanelICMS_90.setLayout(jPanelICMS_90Layout);
        jPanelICMS_90Layout.setHorizontalGroup(
            jPanelICMS_90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_90Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelICMS_90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelICMS_90Layout.setVerticalGroup(
            jPanelICMS_90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_90Layout.createSequentialGroup()
                .addGroup(jPanelICMS_90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("ICMS=90", jPanelICMS_90);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jcbx_det_icms_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jcbx_det_icms_orig, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms_orig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_det_icms_crt, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_icms_crt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ICMS", jPanel4);

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("IPI"));

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel106.setText("Valor do IPI");

        jcbx_det_ipitrib_tipocalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Percentual", "1 - Em Valor" }));
        jcbx_det_ipitrib_tipocalculo.setEnabled(false);

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel107.setText("Situação tributária");

        tf_det_ipitrib_vipi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipitrib_vipi.setToolTipText("");
        tf_det_ipitrib_vipi.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipitrib_vipi.setEnabled(false);

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setText("Classe de enquadramento ¹ ");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setText("CNPJ do Produtor");

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setText("Código do selo de controle ²");

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setText("Qtd. do selo de controle ");

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setText("Tipo de Cálculo");

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel113.setText("Valor da base de cálculo");

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel114.setText("Alíquota");

        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel115.setText("Qtd. total unidade padrão");

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setText("Valor por unidade");

        tf_det_ipi_clenq.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipi_clenq.setToolTipText("");
        tf_det_ipi_clenq.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipi_clenq.setEnabled(false);

        tf_det_ipi_cnpjprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipi_cnpjprod.setToolTipText("");
        tf_det_ipi_cnpjprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipi_cnpjprod.setEnabled(false);

        tf_det_ipi_cselo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipi_cselo.setToolTipText("");
        tf_det_ipi_cselo.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipi_cselo.setEnabled(false);

        tf_det_ipi_qselo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipi_qselo.setToolTipText("");
        tf_det_ipi_qselo.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipi_qselo.setEnabled(false);

        tf_det_ipitrib_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipitrib_vbc.setToolTipText("");
        tf_det_ipitrib_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipitrib_vbc.setEnabled(false);

        tf_det_ipitrib_pipi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipitrib_pipi.setToolTipText("");
        tf_det_ipitrib_pipi.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipitrib_pipi.setEnabled(false);

        tf_det_ipitrib_qunid.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipitrib_qunid.setToolTipText("");
        tf_det_ipitrib_qunid.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipitrib_qunid.setEnabled(false);

        tf_det_ipitrib_vunid.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipitrib_vunid.setToolTipText("");
        tf_det_ipitrib_vunid.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipitrib_vunid.setEnabled(false);

        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setText("* Código de enquadramento");

        tf_det_ipi_cenq.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ipi_cenq.setToolTipText("");
        tf_det_ipi_cenq.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ipi_cenq.setEnabled(false);

        jcbx_det_ipitrib_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "00 - Entrada com recuperação de crédito", "01 - Entrada tributada com alíquota zero", "02 - Entrada isenta", "03 - Entrada não-tributada", "04 - Entrada imune", "05 -Entrada com suspensão", "49 - Outras entradas", "50 - Saída tributada", "51 -Saída tributada com alíquota zero", "52 - Saída isenta", "53 - Saída não-tributada", "54 - Saída imune", "55 -Saída com suspensão", "99 - Outras saídas" }));
        jcbx_det_ipitrib_cst.setEnabled(false);

        jLabel118.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("<html> <table> <td color=\"blank\" align=left> <p>\n² A informação do código de selo ,  quando aplicavel, deve ser informada utilizando a codificação prevista nos Atos Normativos editados pela Receita Federal.<br></p> </td> </table> </html>");

        jLabel119.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("<html> <table> <td color=\"blank\" align=left> <p>\n¹ A informação da classe de eqnuadramento do IPI para cigarros e bebidas, quando aplicavel,  \ndeve ser informada utilizando a codificação prevista nos Atos Normativos editados pela Receita Federal.<br></p> </td> </table> </html>");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jcbx_det_ipitrib_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_ipi_clenq)
                        .addGap(230, 230, 230)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_ipi_cenq, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_ipi_cnpjprod, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                        .addGap(564, 564, 564))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_ipi_cselo)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_ipi_qselo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jcbx_det_ipitrib_tipocalculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_det_ipitrib_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_det_ipitrib_pipi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_det_ipitrib_qunid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_det_ipitrib_vunid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(tf_det_ipitrib_vipi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ipitrib_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_det_ipi_clenq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_ipi_cenq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ipi_cnpjprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ipi_cselo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ipi_qselo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ipitrib_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(tf_det_ipitrib_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_det_ipitrib_pipi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_det_ipitrib_qunid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_det_ipitrib_vunid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_det_ipitrib_vipi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        jTabbedPane2.addTab("IPI", jPanel5);

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("PIS"));

        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel120.setText("Valor do PIS");

        jcbx_det_pisaliq_tipocalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Percentual", "1 - Em Valor" }));
        jcbx_det_pisaliq_tipocalculo.setEnabled(false);
        jcbx_det_pisaliq_tipocalculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbx_det_pisaliq_tipocalculoFocusLost(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel121.setText("* Situação tributária");

        tf_det_pisaliq_vpis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisaliq_vpis.setToolTipText("");
        tf_det_pisaliq_vpis.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisaliq_vpis.setEnabled(false);

        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel122.setText("Quantidade vendida");

        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel124.setText("Alíquota (percentual)");

        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setText("Alíquota (em reais)");

        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel126.setText("Tipo de cálculo");

        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel127.setText("Valor da base de cálculo");

        tf_ICMSTot_vBC83.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSTot_vBC83.setToolTipText("");
        tf_ICMSTot_vBC83.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSTot_vBC83.setEnabled(false);

        tf_det_pisaliq_ppis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisaliq_ppis.setToolTipText("");
        tf_det_pisaliq_ppis.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisaliq_ppis.setEnabled(false);
        tf_det_pisaliq_ppis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_pisaliq_ppisFocusLost(evt);
            }
        });

        tf_det_pisqtde_valiqprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisqtde_valiqprod.setToolTipText("");
        tf_det_pisqtde_valiqprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisqtde_valiqprod.setEnabled(false);

        tf_det_pisaliq_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisaliq_vbc.setToolTipText("");
        tf_det_pisaliq_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisaliq_vbc.setEnabled(false);
        tf_det_pisaliq_vbc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_pisaliq_vbcFocusLost(evt);
            }
        });

        jcbx_det_pisaliq_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "01 - Operação Tributável (base de cálculo = valor da operação alíquota normal (cumulativo/não cumulativo))", "02 - Operação Tributável (base de cálculo = valor da operação (alíquota diferenciada))", "03 - Operação Tributável (base de cálculo = quantidade vendida x alíquota por unidade de produto)", "04 - Operação Tributável (tributação monofásica (alíquota zero))", "05 - Operação Tributável (Substituição Tributária)", "06 - Operação Tributável (alíquota zero)", "07 - Operação Isenta da Contribuição", "08 - Operação Sem Incidência da Contribuição", "09 - Operação com Suspensão da Contribuição", "49 - Outras Operações de Saída;", "50 - Operação com Direito a Crédito - Vinculada Exclusivamente a Receita Tributada no Mercado Interno", "51 - Operação com Direito a Crédito - Vinculada Exclusivamente a Receita Não Tributada no Mercado Interno", "52 - Operação com Direito a Crédito – Vinculada Exclusivamente a Receita de Exportação", "53 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno", "54 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas no Mercado Interno e de Exportação", "55 - Operação com Direito a Crédito - Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação", "56 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação", "60 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Tributada no Mercado Interno", "61 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Não-Tributada no Mercado Interno", "62 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita de Exportação", "63 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno", "64 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas no Mercado Interno e de Exportação", "65 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação", "66 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação", "67 - Crédito Presumido - Outras Operações", "70 - Operação de Aquisição sem Direito a Crédito", "71 - Operação de Aquisição com Isenção", "72 - Operação de Aquisição com Suspensão", "73 - Operação de Aquisição a Alíquota Zero", "74 - Operação de Aquisição; sem Incidência da Contribuição", "75 - Operação de Aquisição por Substituição Tributária", "98 - Outras Operações de Entrada", "99 - Outras Operações", " ", " " }));
        jcbx_det_pisaliq_cst.setEnabled(false);
        jcbx_det_pisaliq_cst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_det_pisaliq_cstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jcbx_det_pisaliq_cst, 0, 802, Short.MAX_VALUE)))
                .addGap(4, 4, 4))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jcbx_det_pisaliq_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tf_det_pisaliq_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tf_det_pisaliq_ppis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tf_det_pisqtde_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(tf_det_pisaliq_vpis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tf_ICMSTot_vBC83, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbx_det_pisaliq_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_pisaliq_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisaliq_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisaliq_ppis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisqtde_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisaliq_vpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSTot_vBC83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("PIS ST"));

        jcbx_det_pisst_tipocalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Percentual", "1 - Em Valor" }));
        jcbx_det_pisst_tipocalculo.setEnabled(false);

        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setText("Alíquota (percentual)");

        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setText("Tipo de cálculo");

        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel133.setText("Valor da base de cálculo");

        tf_det_pisst_ppis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisst_ppis.setToolTipText("");
        tf_det_pisst_ppis.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisst_ppis.setEnabled(false);

        tf_det_pisst_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisst_vbc.setToolTipText("");
        tf_det_pisst_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisst_vbc.setEnabled(false);

        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel129.setText("Alíquota (em reais)");

        tf_det_pisst_valiqprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisst_valiqprod.setToolTipText("");
        tf_det_pisst_valiqprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisst_valiqprod.setEnabled(false);

        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel123.setText("Quantidade vendida");

        tf_det_pisst_qbcprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisst_qbcprod.setToolTipText("");
        tf_det_pisst_qbcprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisst_qbcprod.setEnabled(false);

        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel131.setText("Valor do PIS ST");

        tf_det_pisst_vpis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_pisst_vpis.setToolTipText("");
        tf_det_pisst_vpis.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_pisst_vpis.setEnabled(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_det_pisst_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_det_pisst_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_pisst_ppis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_pisst_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_pisst_vpis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_pisst_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_pisst_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisst_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisst_ppis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisst_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisst_vpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_pisst_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );

        jTabbedPane2.addTab("PIS", jPanel6);

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("COFINS"));

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel134.setText("Valor do COFINS");

        jcbx_det_cofinsaliq_tipocalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Percentual", "1 - Em Valor" }));
        jcbx_det_cofinsaliq_tipocalculo.setEnabled(false);
        jcbx_det_cofinsaliq_tipocalculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbx_det_cofinsaliq_tipocalculoFocusLost(evt);
            }
        });

        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel135.setText("* Situação tributária");

        tf_det_cofinsaliq_vcofins.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsaliq_vcofins.setToolTipText("");
        tf_det_cofinsaliq_vcofins.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsaliq_vcofins.setEnabled(false);

        jLabel136.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel136.setText("Quantidade vendida");

        jLabel137.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel137.setText("Alíquota (percentual)");

        jLabel138.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel138.setText("Alíquota (em reais)");

        jLabel139.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel139.setText("Tipo de cálculo");

        jLabel140.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel140.setText("Valor da base de cálculo");

        tf_det_cofinsqtde_qbcprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsqtde_qbcprod.setToolTipText("");
        tf_det_cofinsqtde_qbcprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsqtde_qbcprod.setEnabled(false);

        tf_det_cofinsaliq_pcofins.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsaliq_pcofins.setToolTipText("");
        tf_det_cofinsaliq_pcofins.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsaliq_pcofins.setEnabled(false);
        tf_det_cofinsaliq_pcofins.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_cofinsaliq_pcofinsFocusLost(evt);
            }
        });

        tf_det_cofinsqtde_valiqprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsqtde_valiqprod.setToolTipText("");
        tf_det_cofinsqtde_valiqprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsqtde_valiqprod.setEnabled(false);

        tf_det_cofinsaliq_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsaliq_vbc.setToolTipText("");
        tf_det_cofinsaliq_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsaliq_vbc.setEnabled(false);
        tf_det_cofinsaliq_vbc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_cofinsaliq_vbcFocusLost(evt);
            }
        });

        jcbx_det_cofinsaliq_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "01 - Operação Tributável (base de cálculo = valor da operação alíquota normal (cumulativo/não cumulativo))", "02 - Operação Tributável (base de cálculo = valor da operação (alíquota diferenciada))", "03 - Operação Tributável (base de cálculo = quantidade vendida x alíquota por unidade de produto)", "04 - Operação Tributável (tributação monofásica, alíquota zero)", "05 - Operação Tributável (Substituição Tributária)", "06 - Operação Tributável (alíquota zero)", "07 - Operação Isenta da Contribuição", "08 - Operação Sem Incidência da Contribuição", "09 - Operação com Suspensão da Contribuição", "49 - Outras Operações de Saída", "50 - Operação com Direito a Crédito - Vinculada Exclusivamente a Receita Tributada no Mercado Interno", "51 - Operação com Direito a Crédito - Vinculada Exclusivamente a Receita Não Tributada no Mercado Interno", "52 - Operação com Direito a Crédito – Vinculada Exclusivamente a Receita de Exportação", "53 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno", "54 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas no Mercado Interno e de Exportação", "55 - Operação com Direito a Crédito - Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação", "56 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação", "60 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Tributada no Mercado Interno", "61 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita Não-Tributada no Mercado Interno", "62 - Crédito Presumido - Operação de Aquisição Vinculada Exclusivamente a Receita de Exportação", "63 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno", "64 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas no Mercado Interno e de Exportação", "65 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação", "66 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação", "67 - Crédito Presumido - Outras Operações", "70 - Operação de Aquisição sem Direito a Crédito", "71 - Operação de Aquisição com Isenção", "72 - Operação de Aquisição com Suspensão", "73 - Operação de Aquisição a Alíquota Zero", "74 - Operação de Aquisição; sem Incidência da Contribuição", "75 - Operação de Aquisição por Substituição Tributária", "98 - Outras Operações de Entrada", "99 - Outras Operações", " " }));
        jcbx_det_cofinsaliq_cst.setEnabled(false);
        jcbx_det_cofinsaliq_cst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_det_cofinsaliq_cstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(tf_det_cofinsaliq_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsaliq_pcofins, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsqtde_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsaliq_vcofins, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsqtde_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbx_det_cofinsaliq_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jcbx_det_cofinsaliq_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbx_det_cofinsaliq_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_cofinsaliq_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsaliq_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsaliq_pcofins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsqtde_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsaliq_vcofins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsqtde_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder("COFINS ST"));

        jcbx_det_cofinsst_tipocalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 - Percentual", "1 - Em Valor" }));
        jcbx_det_cofinsst_tipocalculo.setEnabled(false);

        jLabel141.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel141.setText("Alíquota (percentual)");

        jLabel142.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel142.setText("Tipo de cálculo");

        jLabel143.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel143.setText("Valor da base de cálculo");

        tf_det_cofinsst_pcofins.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsst_pcofins.setToolTipText("");
        tf_det_cofinsst_pcofins.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsst_pcofins.setEnabled(false);

        tf_det_cofinsst_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsst_vbc.setToolTipText("");
        tf_det_cofinsst_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsst_vbc.setEnabled(false);

        jLabel144.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel144.setText("Alíquota (em reais)");

        tf_det_cofinsst_valiqprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsst_valiqprod.setToolTipText("");
        tf_det_cofinsst_valiqprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsst_valiqprod.setEnabled(false);

        jLabel145.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel145.setText("Quantidade vendida");

        tf_det_cofinsst_qbcprod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsst_qbcprod.setToolTipText("");
        tf_det_cofinsst_qbcprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsst_qbcprod.setEnabled(false);

        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setText("Valor do COFINS ST");

        tf_det_cofinsst_vcofins.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cofinsst_vcofins.setToolTipText("");
        tf_det_cofinsst_vcofins.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cofinsst_vcofins.setEnabled(false);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsst_pcofins, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsst_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsst_vcofins, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_det_cofinsst_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_det_cofinsst_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_cofinsst_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbx_det_cofinsst_tipocalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsst_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsst_pcofins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsst_valiqprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsst_vcofins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cofinsst_qbcprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("COFINS", jPanel7);

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setText("* Regime");

        jcbx_det_ibs_crt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Simples Nacional", "2 - Simples Nacional, excesso sublimite de receita bruta", "3 - Tributação Normal" }));
        jcbx_det_ibs_crt.setSelectedIndex(2);
        jcbx_det_ibs_crt.setEnabled(false);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Tributação Normal"));

        jLabel155.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel155.setText("* Situação tributária ");

        jcbx_det_ibs_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000 - Tributação integral", "200 - Alíquota zero / reduzida", "400 - Isenção", "410 - Imunidade e não incidência", "510 - Diferimento", "550 - Suspensão", "620 - Tributação monofásica", "800 - Transferência de crédito", "900 - Outros" }));
        jcbx_det_ibs_cst.setEnabled(false);

        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel156.setText("* Classificação tributária");

        jcbx_det_ibs_cclasstrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000001 - Situações tributadas integralmente pelo IBS e CBS", "410999 - Operações não onerosas sem previsão de tributação" }));
        jcbx_det_ibs_cclasstrib.setEnabled(false);

        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder("IBS da UF"));

        jLabel235.setText("Base de Cálculo do IBS ¹");

        tf_det_ibsuf_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsuf_vbc.setToolTipText("");
        tf_det_ibsuf_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsuf_vbc.setEnabled(false);

        jLabel236.setText("Alíquota Aplicada do IBS da UF");

        jLabel237.setText("Valor Total do IBS Aplicado");

        tf_det_ibsuf_pibsuf.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsuf_pibsuf.setToolTipText("");
        tf_det_ibsuf_pibsuf.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsuf_pibsuf.setEnabled(false);
        tf_det_ibsuf_pibsuf.setDocument(new ValidFieldNumeric());

        tf_det_ibsuf_vibsuf.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsuf_vibsuf.setToolTipText("");
        tf_det_ibsuf_vibsuf.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsuf_vibsuf.setEnabled(false);
        tf_det_ibsuf_vibsuf.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel235, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(tf_det_ibsuf_vbc, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel236, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_ibsuf_pibsuf))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel237, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(tf_det_ibsuf_vibsuf)))
                .addGap(53, 53, 53))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel235, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsuf_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel236, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsuf_pibsuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel237, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsuf_vibsuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tf_det_ibsuf_vbc.setDocument(new ValidFieldNumeric());

        jLabel238.setFont(new java.awt.Font("Arial", 2, 10)); // NOI18N
        jLabel238.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel238.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos.<br></p> </td> </table> </html>");

        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder("IBS do Município"));

        jLabel245.setText("Base de Cálculo do IBS ¹");

        tf_det_ibsmun_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsmun_vbc.setToolTipText("");
        tf_det_ibsmun_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsmun_vbc.setEnabled(false);

        jLabel246.setText("Aliquota Aplicada do IBS do Mun.");

        jLabel247.setText("Valor Total do IBS Aplicado");

        tf_det_ibsmun_pibsmun.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsmun_pibsmun.setToolTipText("");
        tf_det_ibsmun_pibsmun.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsmun_pibsmun.setEnabled(false);

        tf_det_ibsmun_vibsmun.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibsmun_vibsmun.setToolTipText("");
        tf_det_ibsmun_vibsmun.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibsmun_vibsmun.setEnabled(false);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel245, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel247, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel246))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_det_ibsmun_vibsmun, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(tf_det_ibsmun_pibsmun, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(tf_det_ibsmun_vbc, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel245, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsmun_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel246, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsmun_pibsmun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel247, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibsmun_vibsmun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tf_det_ibsmun_vbc.setDocument(new ValidFieldNumeric());
        tf_det_ibsmun_pibsmun.setDocument(new ValidFieldNumeric());
        tf_det_ibsmun_vibsmun.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanelICMS_13Layout = new javax.swing.GroupLayout(jPanelICMS_13);
        jPanelICMS_13.setLayout(jPanelICMS_13Layout);
        jPanelICMS_13Layout.setHorizontalGroup(
            jPanelICMS_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelICMS_13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelICMS_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelICMS_13Layout.createSequentialGroup()
                        .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel238, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelICMS_13Layout.setVerticalGroup(
            jPanelICMS_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelICMS_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel238, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelICMS_71Layout = new javax.swing.GroupLayout(jPanelICMS_71);
        jPanelICMS_71.setLayout(jPanelICMS_71Layout);
        jPanelICMS_71Layout.setHorizontalGroup(
            jPanelICMS_71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
            .addGroup(jPanelICMS_71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_71Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanelICMS_71Layout.setVerticalGroup(
            jPanelICMS_71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
            .addGroup(jPanelICMS_71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_71Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane4.addTab("IBS (Imposto sobre Bens e Serviços) = 000", jPanelICMS_71);

        jLabel249.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel249.setText("* Alíquota Referenciada do IBS");

        tf_det_ibs_pibstotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibs_pibstotal.setToolTipText("");
        tf_det_ibs_pibstotal.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibs_pibstotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_ibs_pibstotalFocusLost(evt);
            }
        });
        tf_det_ibs_pibstotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_det_ibs_pibstotalActionPerformed(evt);
            }
        });
        tf_det_ibs_pibstotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_det_ibs_pibstotalKeyPressed(evt);
            }
        });

        tf_det_ibs_pibsuf.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibs_pibsuf.setToolTipText("");
        tf_det_ibs_pibsuf.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibs_pibsuf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_ibs_pibsufFocusLost(evt);
            }
        });
        tf_det_ibs_pibsuf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_det_ibs_pibsufKeyPressed(evt);
            }
        });

        jLabel253.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel253.setText("*  Estadual (%)");

        tf_det_ibs_pibsmun.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_ibs_pibsmun.setToolTipText("");
        tf_det_ibs_pibsmun.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_ibs_pibsmun.setEnabled(false);

        jLabel254.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel254.setText("* Municipal (%)");

        jLabel255.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel255.setText("* Ano de Aplicação da Alíquota do IBS");

        jcbx_det_ibs_pibsteste.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2026 à 2028", "Após 2029", " " }));
        jcbx_det_ibs_pibsteste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_det_ibs_pibstesteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel255, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_det_ibs_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbx_det_ibs_cclasstrib, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jcbx_det_ibs_pibsteste, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel249)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_det_ibs_pibstotal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel253, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_det_ibs_pibsuf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel254)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_det_ibs_pibsmun, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ibs_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ibs_cclasstrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel255, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ibs_pibsteste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel249, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibs_pibstotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel254, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibs_pibsmun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel253, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_ibs_pibsuf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane4))
        );

        tf_det_ibs_pibstotal.setDocument(new ValidFieldNumeric());
        tf_det_ibs_pibsuf.setDocument(new ValidFieldNumeric());
        tf_det_ibs_pibsmun.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_det_ibs_crt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_ibs_crt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );

        jTabbedPane2.addTab("IBS", jPanel15);

        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder("Tributação Normal"));

        jLabel160.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel160.setText("* Situação tributária (IS)");

        jcbx_det_is_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000 - Tributação integral", "200 - Alíquota zero / reduzida", "400 - Isenção", "410 - Imunidade e não incidência", "510 - Diferimento", "550 - Suspensão", "620 - Tributação monofásica", "800 - Transferência de crédito", "900 - Outros" }));
        jcbx_det_is_cst.setEnabled(false);

        jLabel161.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel161.setText("* Classificação tributária (IS)");

        jcbx_det_is_cclasstrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000001 - Situações tributadas integralmente pelo IBS e CBS" }));
        jcbx_det_is_cclasstrib.setEnabled(false);

        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel243.setText("Base de Cálculo do IS¹");

        tf_det_is_vbcimpsel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_vbcimpsel.setToolTipText("");
        tf_det_is_vbcimpsel.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_vbcimpsel.setEnabled(false);

        jLabel244.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel244.setText("* Alíquota Aplicada do IS");

        tf_det_is_pimpsel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_pimpsel.setToolTipText("");
        tf_det_is_pimpsel.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_pimpsel.setEnabled(false);
        tf_det_is_pimpsel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_is_pimpselFocusLost(evt);
            }
        });
        tf_det_is_pimpsel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_det_is_pimpselKeyPressed(evt);
            }
        });

        jLabel250.setText("Aliquota específica por unidade Trib.");

        tf_det_is_pimpselespec.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_pimpselespec.setToolTipText("");
        tf_det_is_pimpselespec.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_pimpselespec.setEnabled(false);

        jLabel251.setText("Unidade de Medida Trib.");

        tf_det_is_utrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_utrib.setToolTipText("");
        tf_det_is_utrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_utrib.setEnabled(false);

        jLabel248.setText("Valor do IS");

        tf_det_is_vimpsel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_vimpsel.setToolTipText("");
        tf_det_is_vimpsel.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_vimpsel.setEnabled(false);

        tf_det_is_qtrib.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_is_qtrib.setToolTipText("");
        tf_det_is_qtrib.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_is_qtrib.setEnabled(false);

        jLabel252.setText("Qtd. total unidade trib.");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel243, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel244, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel248, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_det_is_vbcimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_is_pimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_is_vimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel251, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel250))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_det_is_qtrib)
                    .addComponent(tf_det_is_pimpselespec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(tf_det_is_utrib))
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_det_is_pimpselespec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel250, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel251, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_is_utrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_det_is_qtrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel243, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_is_vbcimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel244, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_det_is_pimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_det_is_vimpsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel248, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tf_det_is_vbcimpsel.setDocument(new ValidFieldNumeric());
        tf_det_is_pimpsel.setDocument(new ValidFieldNumeric());
        tf_det_is_pimpselespec.setDocument(new ValidFieldNumeric());
        tf_det_is_utrib.setDocument(new ValidFieldNumeric());
        tf_det_is_vimpsel.setDocument(new ValidFieldNumeric());
        tf_det_is_qtrib.setDocument(new ValidFieldNumeric());

        jLabel166.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel166.setText("* Situação tributária (IBS e CBS)");

        jcbx_det_is_cstibscbs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000 - Tributação integral", "200 - Alíquota zero / reduzida", "400 - Isenção", "410 - Imunidade e não incidência", "510 - Diferimento", "550 - Suspensão", "620 - Tributação monofásica", "800 - Transferência de crédito", "900 - Outros" }));
        jcbx_det_is_cstibscbs.setEnabled(false);

        jLabel167.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel167.setText("* Classificação tributária (IBS e CBS)");

        jcbx_det_is_cclasstribibscbs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000001 - Situações tributadas integralmente pelo IBS e CBS" }));
        jcbx_det_is_cclasstribibscbs.setEnabled(false);

        javax.swing.GroupLayout jPanelICMS_15Layout = new javax.swing.GroupLayout(jPanelICMS_15);
        jPanelICMS_15.setLayout(jPanelICMS_15Layout);
        jPanelICMS_15Layout.setHorizontalGroup(
            jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_15Layout.createSequentialGroup()
                .addGroup(jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelICMS_15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel167, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel166, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_det_is_cstibscbs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbx_det_is_cclasstribibscbs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelICMS_15Layout.setVerticalGroup(
            jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_15Layout.createSequentialGroup()
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_is_cstibscbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelICMS_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbx_det_is_cclasstribibscbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelICMS_73Layout = new javax.swing.GroupLayout(jPanelICMS_73);
        jPanelICMS_73.setLayout(jPanelICMS_73Layout);
        jPanelICMS_73Layout.setHorizontalGroup(
            jPanelICMS_73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
            .addGroup(jPanelICMS_73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_73Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanelICMS_73Layout.setVerticalGroup(
            jPanelICMS_73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
            .addGroup(jPanelICMS_73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_73Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane6.addTab("IS (Imposto Seletivo)", jPanelICMS_73);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel161, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_det_is_cclasstrib, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbx_det_is_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane6)))
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_is_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_is_cclasstrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );

        jTabbedPane2.addTab("IS", jPanel19);

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Tributação Normal"));

        jLabel158.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel158.setText("* Situação tributária ");

        jcbx_det_cbs_cst.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000 - Tributação integral", "200 - Alíquota zero / reduzida", "400 - Isenção", "410 - Imunidade e não incidência", "510 - Diferimento", "550 - Suspensão", "620 - Tributação monofásica", "800 - Transferência de crédito", "900 - Outros" }));
        jcbx_det_cbs_cst.setEnabled(false);

        jLabel159.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel159.setText("* Classificação tributária");

        jcbx_det_cbs_cclasstrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000001 - Situações tributadas integralmente pelo IBS e CBS" }));
        jcbx_det_cbs_cclasstrib.setEnabled(false);

        jPanel58.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel239.setText("Base de Cálculo do CBS ¹");

        tf_det_cbs_vbc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cbs_vbc.setToolTipText("");
        tf_det_cbs_vbc.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cbs_vbc.setEnabled(false);

        jLabel240.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel240.setText("* Alíquota Aplicada do CBS");

        jLabel241.setText("Valor Total do CBS");

        tf_det_cbs_pcbs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cbs_pcbs.setToolTipText("");
        tf_det_cbs_pcbs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cbs_pcbs.setEnabled(false);
        tf_det_cbs_pcbs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_det_cbs_pcbsFocusLost(evt);
            }
        });
        tf_det_cbs_pcbs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_det_cbs_pcbsKeyPressed(evt);
            }
        });

        tf_det_cbs_vcbs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cbs_vcbs.setToolTipText("");
        tf_det_cbs_vcbs.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_det_cbs_vcbs.setEnabled(false);

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(jLabel241, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_det_cbs_vcbs, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel239, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel240, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_det_cbs_vbc, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(tf_det_cbs_pcbs))))
                .addGap(66, 514, Short.MAX_VALUE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel239, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cbs_vbc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel240, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cbs_pcbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel241, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_det_cbs_vcbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tf_det_cbs_vbc.setDocument(new ValidFieldNumeric());
        tf_det_cbs_pcbs.setDocument(new ValidFieldNumeric());
        tf_det_cbs_vcbs.setDocument(new ValidFieldNumeric());

        jLabel242.setFont(new java.awt.Font("Arial", 2, 10)); // NOI18N
        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel242.setText("<html> <table> <td color=\"blank\" align=left> <p>¹ Atenção: a Base de Cálculo do produto dever ser informada contemplando  todos os valores previstos na legislação pertinente, ainda que algumas de suas parcelas tenham sido informadas em campos  específicos.<br></p> </td> </table> </html>");

        javax.swing.GroupLayout jPanelICMS_14Layout = new javax.swing.GroupLayout(jPanelICMS_14);
        jPanelICMS_14.setLayout(jPanelICMS_14Layout);
        jPanelICMS_14Layout.setHorizontalGroup(
            jPanelICMS_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_14Layout.createSequentialGroup()
                .addGroup(jPanelICMS_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel242, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                    .addGroup(jPanelICMS_14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelICMS_14Layout.setVerticalGroup(
            jPanelICMS_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelICMS_14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel242, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanelICMS_72Layout = new javax.swing.GroupLayout(jPanelICMS_72);
        jPanelICMS_72.setLayout(jPanelICMS_72Layout);
        jPanelICMS_72Layout.setHorizontalGroup(
            jPanelICMS_72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
            .addGroup(jPanelICMS_72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_72Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanelICMS_72Layout.setVerticalGroup(
            jPanelICMS_72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
            .addGroup(jPanelICMS_72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelICMS_72Layout.createSequentialGroup()
                    .addComponent(jPanelICMS_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane5.addTab("CBS (Contribuição sobre Bens e Serviços)", jPanelICMS_72);

        jLabel259.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel259.setText("* Ano de Aplicação da Alíquota da CBS");

        jcbx_det_cbs_pcbsteste.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2026 à 2028", "2029 = Alíquota Integral a Ser Informada" }));
        jcbx_det_cbs_pcbsteste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_det_cbs_pcbstesteActionPerformed(evt);
            }
        });

        jLabel258.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel258.setText("* Alíquota de Referência do CBS");

        tf_det_cbs_pcbsref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_det_cbs_pcbsref.setToolTipText("");
        tf_det_cbs_pcbsref.setDisabledTextColor(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbx_det_cbs_cclasstrib, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbx_det_cbs_cst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane5))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel259, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_det_cbs_pcbsteste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel258, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_det_cbs_pcbsref, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_cbs_cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_det_cbs_cclasstrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel258, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_det_cbs_pcbsref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel259, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbx_det_cbs_pcbsteste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane5)
                .addContainerGap())
        );

        tf_det_cbs_pcbsref.setDocument(new ValidFieldNumeric());

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );

        jTabbedPane2.addTab("CBS", jPanel16);

        jPanel8.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Imposto de Importação", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("ISSQN", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("IPI Devolvido", jPanel10);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Valor ICMS relativo ao FCP da UF de destino");

        tf_ICMSUFDest_vICMSUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_vICMSUFDest.setToolTipText("");
        tf_ICMSUFDest_vICMSUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_vICMSUFDest.setEnabled(false);

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel148.setText("Percentual ICMS relativo ao FCP na UF de destino");

        jLabel149.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel149.setText("Valor da base de cálculo na UF do destinatário");

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel150.setText("Aliquota interna da UF do destinatário");

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel151.setText("Aliquota interestadual");

        jLabel152.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel152.setText("Percentual provisório de partilha");

        jLabel153.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel153.setText("Valor ICMS de partilha para UF do destinatário");

        jLabel154.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel154.setText("Valor ICMS de partilha para UF do remetente");

        tf_ICMSUFDest_pFCPUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_pFCPUFDest.setToolTipText("");
        tf_ICMSUFDest_pFCPUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_pFCPUFDest.setEnabled(false);

        tf_ICMSUFDest_vBCUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_vBCUFDest.setToolTipText("");
        tf_ICMSUFDest_vBCUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_vBCUFDest.setEnabled(false);

        tf_ICMSUFDest_pICMSUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_pICMSUFDest.setToolTipText("");
        tf_ICMSUFDest_pICMSUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_pICMSUFDest.setEnabled(false);

        tf_ICMSUFDest_vFCPUFDest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_vFCPUFDest.setToolTipText("");
        tf_ICMSUFDest_vFCPUFDest.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_vFCPUFDest.setEnabled(false);

        tf_ICMSUFDest_vICMSUFRemet.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_ICMSUFDest_vICMSUFRemet.setToolTipText("");
        tf_ICMSUFDest_vICMSUFRemet.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_ICMSUFDest_vICMSUFRemet.setEnabled(false);

        jcbx_ICMSUFDest_pICMSInterPart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "  40% em 2016", "  60% em 2017", "  80% em 2018", "100% a partir de 2019" }));
        jcbx_ICMSUFDest_pICMSInterPart.setEnabled(false);

        jcbx_ICMSUFDest_pICMSInter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "  4% alíquota interestadual para produtos importados", "  7% para os Estados de origem do Sul e Sudeste (exceto ES), destinado para os Estados do Norte, Nordeste, Centro-Oeste e Espírito Santo", "12% para os demais casos" }));
        jcbx_ICMSUFDest_pICMSInter.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jcbx_ICMSUFDest_pICMSInter, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_pFCPUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_vBCUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_pICMSUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jcbx_ICMSUFDest_pICMSInterPart, 0, 642, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_vFCPUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_vICMSUFRemet, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_ICMSUFDest_vICMSUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_pFCPUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_vBCUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_pICMSUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_ICMSUFDest_pICMSInter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_ICMSUFDest_pICMSInterPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_vFCPUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_vICMSUFRemet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ICMSUFDest_vICMSUFDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane2.addTab("ICMS em Operações Interestadual", jPanel11);

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("ICMS");
        jRadioButton1.setEnabled(false);

        jRadioButton2.setText("ISSQN");
        jRadioButton2.setEnabled(false);

        jLabel256.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel256.setText("* Percentual Reduzido de ICMS  (%)");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025 a 2028 = N/A", "2029 = 10%", "2030 = 20%", "2031 = 30%", "2032 = 40%", "2033 = 100%" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane2)
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_det_imposto_vTotTrib, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel256)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel78)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_det_imposto_vTotTrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel256, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tf_det_imposto_vTotTrib.setDocument(new ValidFieldNumeric());

        jTabbedPane1.addTab("Tributos", jPanel2);

        jtxt_det_infadprod.setEditable(false);
        jtxt_det_infadprod.setColumns(20);
        jtxt_det_infadprod.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jtxt_det_infadprod.setRows(5);
        jtxt_det_infadprod.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        jtxt_det_infadprod.setEnabled(false);
        jScrollPane1.setViewportView(jtxt_det_infadprod);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Informações Adicionais", jPanel3);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Declarações de Importação", jPanel41);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Exportação", jPanel42);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Veículo Novo", jPanel43);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Medicamentos/Matérias-primas Farmaceutica", jPanel44);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Armamentos", jPanel45);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Combustivel", jPanel46);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Papel Imune", jPanel47);

        jDesktopPane2.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        botao_sair.setMnemonic('L');
        botao_sair.setText("Fechar");
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

        botao_editar.setMnemonic('L');
        botao_editar.setText("Editar");
        botao_editar.setToolTipText("");
        botao_editar.setEnabled(false);
        botao_editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_editar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_editarMouseMoved(evt);
            }
        });
        botao_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_editarActionPerformed(evt);
            }
        });

        botao_salvar.setMnemonic('L');
        botao_salvar.setText("Salvar");
        botao_salvar.setToolTipText("");
        botao_salvar.setEnabled(false);
        botao_salvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_salvar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_salvarMouseMoved(evt);
            }
        });
        botao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_salvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botao_editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botao_salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botao_sair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_salvar)
                        .addComponent(botao_editar))
                    .addComponent(botao_sair))
                .addContainerGap())
        );

        setBounds(0, 0, 1031, 609);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        enableDisableFields(false);
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_salvarActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        try {
            String wdet_prod_indtot = "0";
            if (jCheck_det_prod_indtot.isSelected() == true) {
                wdet_prod_indtot = "1";
            }
            if (jCheck_det_prod_indtot.isSelected() == false) {
                wdet_prod_indtot = "0";
            }
            if (tf_det_prod_cest.getText().trim().length() <= 0) {
                tf_det_prod_cest.setText(null);
            } else {
                tf_det_prod_cest.setText(tf_det_prod_cest.getText().trim().replace(".", ""));
            }
            NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
            eNFE_DET_PROD.setDet_prod_nnf(gdet_prod_nnf.trim());
            eNFE_DET_PROD.setDet_prod_item(gdet_prod_item.trim());
            eNFE_DET_PROD.setDet_prod_cprod((tf_det_prod_cprod.getText()));
            eNFE_DET_PROD.setDet_prod_ncm(tf_det_prod_ncm.getText().trim());
            if (tf_det_prod_cest.getText().trim().length() > 0) {
                eNFE_DET_PROD.setDet_prod_cest(tf_det_prod_cest.getText());
            }
            eNFE_DET_PROD.setDet_prod_ucom(tf_det_prod_ucom.getText().trim());
            eNFE_DET_PROD.setDet_prod_utrib(tf_det_prod_utrib.getText().trim());
            eNFE_DET_PROD.setDet_prod_indtot(wdet_prod_indtot);

            eNFE_DET_PROD.setDet_pisaliq_cst(jcbx_det_pisaliq_cst.getSelectedItem().toString().trim().substring(0, 2));
            eNFE_DET_PROD.setDet_pisaliq_vbc(tf_det_pisaliq_vbc.getText().replace(".", "").replace(",", "."));
            eNFE_DET_PROD.setDet_pisaliq_ppis(tf_det_pisaliq_ppis.getText().replace(".", "").replace(",", "."));
            eNFE_DET_PROD.setDet_pisaliq_vpis(tf_det_pisaliq_vpis.getText().replace(".", "").replace(",", "."));

            eNFE_DET_PROD.setDet_cofinsaliq_cst((jcbx_det_cofinsaliq_cst.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_cofinsaliq_cst.getSelectedItem().toString().trim().substring(0, 2)));
            eNFE_DET_PROD.setDet_cofinsaliq_vbc((tf_det_cofinsaliq_vbc.getText().trim().length() <= 0 ? null : tf_det_cofinsaliq_vbc.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_cofinsaliq_pcofins((tf_det_cofinsaliq_pcofins.getText().trim().length() <= 0 ? null : tf_det_cofinsaliq_pcofins.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_cofinsaliq_vcofins((tf_det_cofinsaliq_vcofins.getText().trim().length() <= 0 ? null : tf_det_cofinsaliq_vcofins.getText().replace(".", "").replace(",", ".")));

            //ibs
            eNFE_DET_PROD.setDet_ibs_cst((jcbx_det_ibs_cst.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_ibs_cst.getSelectedItem().toString().trim().substring(0, 3)));
            eNFE_DET_PROD.setDet_ibs_cclasstrib((jcbx_det_ibs_cclasstrib.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_ibs_cclasstrib.getSelectedItem().toString().trim().substring(0, 6)));

            eNFE_DET_PROD.setDet_ibs_pibstotal((tf_det_ibs_pibstotal.getText().trim().length() <= 0 ? null : tf_det_ibs_pibstotal.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibs_pibsuf((tf_det_ibs_pibsuf.getText().trim().length() <= 0 ? null : tf_det_ibs_pibsuf.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibs_pibsmun((tf_det_ibs_pibsmun.getText().trim().length() <= 0 ? null : tf_det_ibs_pibsmun.getText().replace(".", "").replace(",", ".")));

            eNFE_DET_PROD.setDet_ibsuf_vbc((tf_det_ibsuf_vbc.getText().trim().length() <= 0 ? null : tf_det_ibsuf_vbc.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibsuf_pibsuf((tf_det_ibsuf_pibsuf.getText().trim().length() <= 0 ? null : tf_det_ibsuf_pibsuf.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibsuf_vibsuf((tf_det_ibsuf_vibsuf.getText().trim().length() <= 0 ? null : tf_det_ibsuf_vibsuf.getText().replace(".", "").replace(",", ".")));

            eNFE_DET_PROD.setDet_ibsmun_vbc((tf_det_ibsmun_vbc.getText().trim().length() <= 0 ? null : tf_det_ibsmun_vbc.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibsmun_pibsmun((tf_det_ibsmun_pibsmun.getText().trim().length() <= 0 ? null : tf_det_ibsmun_pibsmun.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_ibsmun_vibsmun((tf_det_ibsmun_vibsmun.getText().trim().length() <= 0 ? null : tf_det_ibsmun_vibsmun.getText().replace(".", "").replace(",", ".")));

            //is
            eNFE_DET_PROD.setDet_is_cst((jcbx_det_is_cst.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_is_cst.getSelectedItem().toString().trim().substring(0, 3)));
            eNFE_DET_PROD.setDet_is_cclasstrib((jcbx_det_is_cclasstrib.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_is_cclasstrib.getSelectedItem().toString().trim().substring(0, 6)));

            eNFE_DET_PROD.setDet_is_vbcimpsel((tf_det_is_vbcimpsel.getText().trim().length() <= 0 ? null : tf_det_is_vbcimpsel.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_is_pimpsel((tf_det_is_pimpsel.getText().trim().length() <= 0 ? null : tf_det_is_pimpsel.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_is_vimpsel((tf_det_is_vimpsel.getText().trim().length() <= 0 ? null : tf_det_is_vimpsel.getText().replace(".", "").replace(",", ".")));

            eNFE_DET_PROD.setDet_is_pimpselespec((tf_det_is_pimpselespec.getText().trim().length() <= 0 ? null : tf_det_is_pimpselespec.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_is_utrib((tf_det_is_utrib.getText().trim().length() <= 0 ? null : tf_det_is_utrib.getText().trim()));
            eNFE_DET_PROD.setDet_is_qtrib((tf_det_is_qtrib.getText().trim().length() <= 0 ? null : tf_det_is_qtrib.getText().replace(".", "").replace(",", ".")));

            eNFE_DET_PROD.setDet_is_cstibscbs((jcbx_det_is_cstibscbs.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_is_cstibscbs.getSelectedItem().toString().trim().substring(0, 3)));
            eNFE_DET_PROD.setDet_is_cclasstribibscbs((jcbx_det_is_cclasstribibscbs.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_is_cclasstribibscbs.getSelectedItem().toString().trim().substring(0, 6)));

            //cbs
            eNFE_DET_PROD.setDet_cbs_cst((jcbx_det_cbs_cst.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_cbs_cst.getSelectedItem().toString().trim().substring(0, 3)));
            eNFE_DET_PROD.setDet_cbs_cclasstrib((jcbx_det_cbs_cclasstrib.getSelectedItem().toString().trim().length() <= 0 ? null : jcbx_det_cbs_cclasstrib.getSelectedItem().toString().trim().substring(0, 6)));

            eNFE_DET_PROD.setDet_cbs_pcbsref((tf_det_cbs_pcbsref.getText().trim().length() <= 0 ? null : tf_det_cbs_pcbsref.getText().replace(".", "").replace(",", ".")));

            eNFE_DET_PROD.setDet_cbs_vbc((tf_det_cbs_vbc.getText().trim().length() <= 0 ? null : tf_det_cbs_vbc.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_cbs_pcbs((tf_det_cbs_pcbs.getText().trim().length() <= 0 ? null : tf_det_cbs_pcbs.getText().replace(".", "").replace(",", ".")));
            eNFE_DET_PROD.setDet_cbs_vcbs((tf_det_cbs_vcbs.getText().trim().length() <= 0 ? null : tf_det_cbs_vcbs.getText().replace(".", "").replace(",", ".")));

            if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                System.out.println("Detalhe dos produtos atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
            if (jCheck_det_prod_indtot.isSelected() == false) {
                try {
                    NFE eNFE = new NFE();
                    eNFE.setIde_nnf(gdet_prod_nnf.trim());
                    eNFE.setIcmstot_vnf(null);
                    eNFE.setIcmstot_vprod(null);
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, System.getProperty("MsgInvalid"));
                    }
                } catch (InstantiationException ex) {
                    System.setProperty("MsgInvalid", ex.getMessage());
                } catch (IllegalAccessException ex) {
                    System.setProperty("MsgInvalid", ex.getMessage());
                }
            }
            enableDisableFields(false);
            if (veioDoframe1 != null) {
                veioDoframe1.retornaRegistro("", "", null, null, null);
                veioDoframe1.getNFEtable(gdet_prod_nnf.trim());
            }
        } catch (InstantiationException ex) {
            System.setProperty("MsgInvalid", ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.setProperty("MsgInvalid", ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(NFe_Detalhe_Produtos_e_Servicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NFe_Detalhe_Produtos_e_Servicos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid")});
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        }
    }//GEN-LAST:event_botao_salvarActionPerformed

    private void jcbx_det_pisaliq_cstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_det_pisaliq_cstActionPerformed
        if (jcbx_det_pisaliq_cst.getSelectedItem().toString().trim().substring(0, 2).equals("50")) {
            tf_det_pisaliq_vbc.setText(tf_det_icms00_vbc.getText());
        }
    }//GEN-LAST:event_jcbx_det_pisaliq_cstActionPerformed

    private void tf_det_pisaliq_ppisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_pisaliq_ppisFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_pisaliq_vpis.setText("");
            tf_det_pisaliq_vpis.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_pisaliq_vbc.getText().trim(), tf_det_pisaliq_ppis.getText().trim(), Integer.toString(jcbx_det_pisaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid")});
        }
        /*
     
            tf_det_pisaliq_vpis.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_pisaliq_vbc.getText().trim(), tf_det_pisaliq_ppis.getText().trim(), "1"));
            /*
            if (tf_det_pisaliq_vbc.getText().trim().length() > 0
            && tf_det_pisaliq_ppis.getText().trim().length() > 0
            && jcbx_det_pisaliq_tipocalculo.getSelectedIndex() == 1) {
            try {
            Double ww_det_pisaliq_vpis = (Double.parseDouble(tf_det_pisaliq_vbc.getText().trim().replace(".", "").replace(",", "."))
            * Double.parseDouble(tf_det_pisaliq_ppis.getText().trim().replace(".", "").replace(",", ".")) / 100);
            tf_det_pisaliq_vpis.setText(f.getFormated_2(ww_det_pisaliq_vpis));
            } catch (ParseException ex) {
            Logger.getLogger(NFe_Detalhe_Produtos_e_Servicos.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
         */

    }//GEN-LAST:event_tf_det_pisaliq_ppisFocusLost

    private void botao_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_editarActionPerformed
        enableDisableFields(true);
    }//GEN-LAST:event_botao_editarActionPerformed

    private void jcbx_det_cofinsaliq_cstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_det_cofinsaliq_cstActionPerformed
        if (jcbx_det_cofinsaliq_cst.getSelectedItem().toString().trim().substring(0, 2).equals("50")) {
            tf_det_cofinsaliq_vbc.setText(tf_det_icms00_vbc.getText());
        }
    }//GEN-LAST:event_jcbx_det_cofinsaliq_cstActionPerformed

    private void tf_det_cofinsaliq_pcofinsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_cofinsaliq_pcofinsFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_cofinsaliq_vcofins.setText("");
            tf_det_cofinsaliq_vcofins.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_cofinsaliq_vbc.getText().trim(), tf_det_cofinsaliq_pcofins.getText().trim(), Integer.toString(jcbx_det_cofinsaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid"), ex.getMessage()});
        }
    }//GEN-LAST:event_tf_det_cofinsaliq_pcofinsFocusLost

    private void botao_editarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_editarMouseMoved
        botao_editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_editarMouseMoved

    private void botao_salvarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_salvarMouseMoved
        botao_salvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_salvarMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    private void tf_det_pisaliq_vbcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_pisaliq_vbcFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_pisaliq_vpis.setText("");
            tf_det_pisaliq_vpis.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_pisaliq_vbc.getText().trim(), tf_det_pisaliq_ppis.getText().trim(), Integer.toString(jcbx_det_pisaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid"), ex.getMessage()});
        }
    }//GEN-LAST:event_tf_det_pisaliq_vbcFocusLost

    private void jcbx_det_pisaliq_tipocalculoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbx_det_pisaliq_tipocalculoFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_pisaliq_vpis.setText("");
            tf_det_pisaliq_vpis.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_pisaliq_vbc.getText().trim(), tf_det_pisaliq_ppis.getText().trim(), Integer.toString(jcbx_det_pisaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid"), ex.getMessage()});
        }
    }//GEN-LAST:event_jcbx_det_pisaliq_tipocalculoFocusLost

    private void jcbx_det_cofinsaliq_tipocalculoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbx_det_cofinsaliq_tipocalculoFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_cofinsaliq_vcofins.setText("");
            tf_det_cofinsaliq_vcofins.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_cofinsaliq_vbc.getText().trim(), tf_det_cofinsaliq_pcofins.getText().trim(), Integer.toString(jcbx_det_cofinsaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid"), ex.getMessage()});
        }
    }//GEN-LAST:event_jcbx_det_cofinsaliq_tipocalculoFocusLost

    private void tf_det_cofinsaliq_vbcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_cofinsaliq_vbcFocusLost
        try {
            veioDoframe1.returnParams(new String[]{""});
            tf_det_cofinsaliq_vcofins.setText("");
            tf_det_cofinsaliq_vcofins.setText(eCalculaImpostos.CalculaImpostoGenerico(tf_det_cofinsaliq_vbc.getText().trim(), tf_det_cofinsaliq_pcofins.getText().trim(), Integer.toString(jcbx_det_cofinsaliq_tipocalculo.getSelectedIndex())));
        } catch (Exception ex) {
            veioDoframe1.returnParams(new String[]{System.getProperty("MsgInvalid"), ex.getMessage()});
        }
    }//GEN-LAST:event_tf_det_cofinsaliq_vbcFocusLost

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        try {
            int anoAtual = LocalDate.now().getYear();
            switch (jComboBox2.getSelectedIndex()) {
                case 1:
                    anoAtual = 2029;
                    break;
                case 2:
                    anoAtual = 2030;
                    break;
                case 3:
                    anoAtual = 2031;
                    break;
                case 4:
                    anoAtual = 2032;
                    break;
                case 5:
                    anoAtual = 2033;
                    break;
                default:
                    anoAtual = anoAtual; // Mantém o ano atual para "N/A" ou outros valores inesperados
            }
            ajustaICMS(anoAtual);
        } catch (ParseException ex) {
            Logger.getLogger(NFe_Detalhe_Produtos_e_Servicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NFe_Detalhe_Produtos_e_Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jcbx_det_ibs_pibstesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_det_ibs_pibstesteActionPerformed
        if (tf_det_ibsuf_vibsuf.getText().trim().length() > 0) {
            return;
        }
        int anoAtual = LocalDate.now().getYear();
        tf_det_ibs_pibstotal.setEditable(false);
        tf_det_ibs_pibstotal.setEnabled(false);

        tf_det_ibs_pibsuf.setText("50,0");
        tf_det_ibs_pibsmun.setText("50,0");
        switch (jcbx_det_ibs_pibsteste.getSelectedIndex()) {
            case 0:
                anoAtual = 2026;
                //if (tf_det_ibs_pibsuf.getText().trim().isEmpty()) {
                tf_det_ibs_pibsuf.setText("50,0");
                tf_det_ibs_pibsmun.setText("50,0");
                //}
                break;
            case 1:
                anoAtual = 2029;
                break;
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df4 = new DecimalFormat("0.0000", symbols);
        if (anoAtual <= 2028) {
            tf_det_ibs_pibstotal.setText(df4.format(0.1000));
        } else {
            tf_det_ibs_pibstotal.setEditable(true);
            tf_det_ibs_pibstotal.setEnabled(true);
            tf_det_ibs_pibstotal.requestFocusInWindow(); // Melhor que grabFocus()
        }
        calcula_ibs_pibsmun();
        calcula_ibsuf_vibsuf();
        calcula_ibsmun_vibsmun();
    }//GEN-LAST:event_jcbx_det_ibs_pibstesteActionPerformed

    private void tf_det_ibs_pibsufFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_ibs_pibsufFocusLost
        calcula_ibs_pibsmun();
        calcula_ibsuf_vibsuf();
        calcula_ibsmun_vibsmun();
    }//GEN-LAST:event_tf_det_ibs_pibsufFocusLost

    private void tf_det_ibs_pibstotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_ibs_pibstotalFocusLost
        calcula_ibs_pibsmun();
        calcula_ibsuf_vibsuf();
        calcula_ibsmun_vibsmun();
    }//GEN-LAST:event_tf_det_ibs_pibstotalFocusLost

    private void tf_det_ibs_pibsufKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_det_ibs_pibsufKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcula_ibs_pibsmun();
            calcula_ibsuf_vibsuf();
            calcula_ibsmun_vibsmun();
        }
    }//GEN-LAST:event_tf_det_ibs_pibsufKeyPressed

    private void tf_det_ibs_pibstotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_det_ibs_pibstotalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcula_ibs_pibsmun();
            calcula_ibsuf_vibsuf();
            calcula_ibsmun_vibsmun();
        }
    }//GEN-LAST:event_tf_det_ibs_pibstotalKeyPressed

    private void jcbx_det_cbs_pcbstesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_det_cbs_pcbstesteActionPerformed
        int anoAtual = LocalDate.now().getYear();
        tf_det_cbs_pcbsref.setEditable(false);
        tf_det_cbs_pcbs.setText("0,9000");
        switch (jcbx_det_cbs_pcbsteste.getSelectedIndex()) {
            case 0:
                anoAtual = 2026;
                //if (tf_det_ibs_pibsuf.getText().trim().isEmpty()) {
                tf_det_cbs_pcbs.setText("0,9000");
                //}
                break;
            case 1:
                anoAtual = 2029;
                break;
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df4 = new DecimalFormat("0.0000", symbols);
        if (anoAtual <= 2028) {
            tf_det_cbs_pcbs.setText(df4.format(0.9000));
        } else {
            tf_det_cbs_pcbs.setEditable(true);
            tf_det_cbs_pcbs.requestFocusInWindow(); // Melhor que grabFocus()
        }
        calcula_cbs_vcbs();
    }//GEN-LAST:event_jcbx_det_cbs_pcbstesteActionPerformed

    private void tf_det_cbs_pcbsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_det_cbs_pcbsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcula_cbs_vcbs();
        }
    }//GEN-LAST:event_tf_det_cbs_pcbsKeyPressed

    private void tf_det_cbs_pcbsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_cbs_pcbsFocusLost
        calcula_cbs_vcbs();
    }//GEN-LAST:event_tf_det_cbs_pcbsFocusLost

    private void tf_det_ibs_pibstotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_det_ibs_pibstotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_det_ibs_pibstotalActionPerformed

    private void tf_det_is_pimpselFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_det_is_pimpselFocusLost
        calcula_is_vimpsel();
    }//GEN-LAST:event_tf_det_is_pimpselFocusLost

    private void tf_det_is_pimpselKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_det_is_pimpselKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcula_is_vimpsel();
        }
    }//GEN-LAST:event_tf_det_is_pimpselKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_Pesquisar;
    private javax.swing.JButton botao_editar;
    private javax.swing.JButton botao_sair;
    private javax.swing.JButton botao_salvar;
    private javax.swing.JCheckBox jCheck_det_prod_indtot;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDesktopPane jDesktopPane2;
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
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
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
    private javax.swing.JLabel jLabel247;
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
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel27;
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
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelICMS_00;
    private javax.swing.JPanel jPanelICMS_10;
    private javax.swing.JPanel jPanelICMS_13;
    private javax.swing.JPanel jPanelICMS_14;
    private javax.swing.JPanel jPanelICMS_15;
    private javax.swing.JPanel jPanelICMS_20;
    private javax.swing.JPanel jPanelICMS_30;
    private javax.swing.JPanel jPanelICMS_40;
    private javax.swing.JPanel jPanelICMS_51;
    private javax.swing.JPanel jPanelICMS_60;
    private javax.swing.JPanel jPanelICMS_70;
    private javax.swing.JPanel jPanelICMS_71;
    private javax.swing.JPanel jPanelICMS_72;
    private javax.swing.JPanel jPanelICMS_73;
    private javax.swing.JPanel jPanelICMS_90;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JComboBox jcbx_ICMSUFDest_pICMSInter;
    private javax.swing.JComboBox jcbx_ICMSUFDest_pICMSInterPart;
    private javax.swing.JComboBox jcbx_det_cbs_cclasstrib;
    private javax.swing.JComboBox jcbx_det_cbs_cst;
    private javax.swing.JComboBox<String> jcbx_det_cbs_pcbsteste;
    private javax.swing.JComboBox jcbx_det_cofinsaliq_cst;
    private javax.swing.JComboBox jcbx_det_cofinsaliq_tipocalculo;
    private javax.swing.JComboBox jcbx_det_cofinsst_tipocalculo;
    private javax.swing.JComboBox jcbx_det_ibs_cclasstrib;
    private javax.swing.JComboBox jcbx_det_ibs_crt;
    private javax.swing.JComboBox jcbx_det_ibs_cst;
    private javax.swing.JComboBox<String> jcbx_det_ibs_pibsteste;
    private javax.swing.JComboBox jcbx_det_icms00_modbc;
    private javax.swing.JComboBox jcbx_det_icms10_modbc;
    private javax.swing.JComboBox jcbx_det_icms10_modbcst;
    private javax.swing.JComboBox jcbx_det_icms20_modbc;
    private javax.swing.JComboBox jcbx_det_icms20_motdesicms;
    private javax.swing.JComboBox jcbx_det_icms30_modbcst;
    private javax.swing.JComboBox jcbx_det_icms30_motdesicms;
    private javax.swing.JComboBox jcbx_det_icms40_motdesicms;
    private javax.swing.JComboBox jcbx_det_icms51_modbc;
    private javax.swing.JComboBox jcbx_det_icms70_modbc;
    private javax.swing.JComboBox jcbx_det_icms70_modbcst;
    private javax.swing.JComboBox jcbx_det_icms70_motdesicms;
    private javax.swing.JComboBox jcbx_det_icms90_modbc;
    private javax.swing.JComboBox jcbx_det_icms90_modbcst;
    private javax.swing.JComboBox jcbx_det_icms90_motdesicms;
    private javax.swing.JComboBox jcbx_det_icms_crt;
    private javax.swing.JComboBox jcbx_det_icms_cst;
    private javax.swing.JComboBox jcbx_det_icms_orig;
    private javax.swing.JComboBox jcbx_det_ipitrib_cst;
    private javax.swing.JComboBox jcbx_det_ipitrib_tipocalculo;
    private javax.swing.JComboBox jcbx_det_is_cclasstrib;
    private javax.swing.JComboBox jcbx_det_is_cclasstribibscbs;
    private javax.swing.JComboBox jcbx_det_is_cst;
    private javax.swing.JComboBox jcbx_det_is_cstibscbs;
    private javax.swing.JComboBox jcbx_det_pisaliq_cst;
    private javax.swing.JComboBox jcbx_det_pisaliq_tipocalculo;
    private javax.swing.JComboBox jcbx_det_pisst_tipocalculo;
    private javax.swing.JComboBox jcbx_det_prod_cfop;
    private javax.swing.JComboBox jcbx_det_prod_produtoespecifico;
    private javax.swing.JTextArea jtxt_det_infadprod;
    private javax.swing.JTextField tf_ICMSTot_vBC40;
    private javax.swing.JTextField tf_ICMSTot_vBC83;
    private javax.swing.JTextField tf_ICMSUFDest_pFCPUFDest;
    private javax.swing.JTextField tf_ICMSUFDest_pICMSUFDest;
    private javax.swing.JTextField tf_ICMSUFDest_vBCUFDest;
    private javax.swing.JTextField tf_ICMSUFDest_vFCPUFDest;
    private javax.swing.JTextField tf_ICMSUFDest_vICMSUFDest;
    private javax.swing.JTextField tf_ICMSUFDest_vICMSUFRemet;
    private javax.swing.JTextField tf_det_cbs_pcbs;
    private javax.swing.JTextField tf_det_cbs_pcbsref;
    private javax.swing.JTextField tf_det_cbs_vbc;
    private javax.swing.JTextField tf_det_cbs_vcbs;
    private javax.swing.JTextField tf_det_cofinsaliq_pcofins;
    private javax.swing.JTextField tf_det_cofinsaliq_vbc;
    private javax.swing.JTextField tf_det_cofinsaliq_vcofins;
    private javax.swing.JTextField tf_det_cofinsqtde_qbcprod;
    private javax.swing.JTextField tf_det_cofinsqtde_valiqprod;
    private javax.swing.JTextField tf_det_cofinsst_pcofins;
    private javax.swing.JTextField tf_det_cofinsst_qbcprod;
    private javax.swing.JTextField tf_det_cofinsst_valiqprod;
    private javax.swing.JTextField tf_det_cofinsst_vbc;
    private javax.swing.JTextField tf_det_cofinsst_vcofins;
    private javax.swing.JTextField tf_det_ibs_pibsmun;
    private javax.swing.JTextField tf_det_ibs_pibstotal;
    private javax.swing.JTextField tf_det_ibs_pibsuf;
    private javax.swing.JTextField tf_det_ibsmun_pibsmun;
    private javax.swing.JTextField tf_det_ibsmun_vbc;
    private javax.swing.JTextField tf_det_ibsmun_vibsmun;
    private javax.swing.JTextField tf_det_ibsuf_pibsuf;
    private javax.swing.JTextField tf_det_ibsuf_vbc;
    private javax.swing.JTextField tf_det_ibsuf_vibsuf;
    private javax.swing.JTextField tf_det_icms00_picms;
    private javax.swing.JTextField tf_det_icms00_vbc;
    private javax.swing.JTextField tf_det_icms00_vicms;
    private javax.swing.JTextField tf_det_icms10_picms;
    private javax.swing.JTextField tf_det_icms10_picmsst;
    private javax.swing.JTextField tf_det_icms10_pmvast;
    private javax.swing.JTextField tf_det_icms10_predbcst;
    private javax.swing.JTextField tf_det_icms10_vbc;
    private javax.swing.JTextField tf_det_icms10_vbcst;
    private javax.swing.JTextField tf_det_icms10_vicms;
    private javax.swing.JTextField tf_det_icms10_vicmsst;
    private javax.swing.JTextField tf_det_icms20_picms;
    private javax.swing.JTextField tf_det_icms20_predbc;
    private javax.swing.JTextField tf_det_icms20_vbc;
    private javax.swing.JTextField tf_det_icms20_vicms;
    private javax.swing.JTextField tf_det_icms20_vicmsdeson;
    private javax.swing.JTextField tf_det_icms30_picmsst;
    private javax.swing.JTextField tf_det_icms30_pmvast;
    private javax.swing.JTextField tf_det_icms30_predbcst;
    private javax.swing.JTextField tf_det_icms30_vicmsdeson;
    private javax.swing.JTextField tf_det_icms30_vicmsst;
    private javax.swing.JTextField tf_det_icms40_vicms;
    private javax.swing.JTextField tf_det_icms40_vicmsdeson;
    private javax.swing.JTextField tf_det_icms51_pdif;
    private javax.swing.JTextField tf_det_icms51_picms;
    private javax.swing.JTextField tf_det_icms51_predbc;
    private javax.swing.JTextField tf_det_icms51_vbc;
    private javax.swing.JTextField tf_det_icms51_vicms;
    private javax.swing.JTextField tf_det_icms51_vicmsdif;
    private javax.swing.JTextField tf_det_icms51_vicmsop;
    private javax.swing.JTextField tf_det_icms60_vbcstret;
    private javax.swing.JTextField tf_det_icms60_vicmsstret;
    private javax.swing.JTextField tf_det_icms70_picms;
    private javax.swing.JTextField tf_det_icms70_picmsst;
    private javax.swing.JTextField tf_det_icms70_pmvast;
    private javax.swing.JTextField tf_det_icms70_predbc;
    private javax.swing.JTextField tf_det_icms70_predbcst;
    private javax.swing.JTextField tf_det_icms70_vbc;
    private javax.swing.JTextField tf_det_icms70_vbcst;
    private javax.swing.JTextField tf_det_icms70_vicms;
    private javax.swing.JTextField tf_det_icms70_vicmsdeson;
    private javax.swing.JTextField tf_det_icms70_vicmsst;
    private javax.swing.JTextField tf_det_icms90_picms;
    private javax.swing.JTextField tf_det_icms90_picmsst;
    private javax.swing.JTextField tf_det_icms90_pmvast;
    private javax.swing.JTextField tf_det_icms90_predbc;
    private javax.swing.JTextField tf_det_icms90_predbcst;
    private javax.swing.JTextField tf_det_icms90_vbc;
    private javax.swing.JTextField tf_det_icms90_vbcst;
    private javax.swing.JTextField tf_det_icms90_vicms;
    private javax.swing.JTextField tf_det_icms90_vicmsdeson;
    private javax.swing.JTextField tf_det_icms90_vicmsst;
    private javax.swing.JTextField tf_det_imposto_vTotTrib;
    private javax.swing.JTextField tf_det_ipi_cenq;
    private javax.swing.JTextField tf_det_ipi_clenq;
    private javax.swing.JTextField tf_det_ipi_cnpjprod;
    private javax.swing.JTextField tf_det_ipi_cselo;
    private javax.swing.JTextField tf_det_ipi_qselo;
    private javax.swing.JTextField tf_det_ipitrib_pipi;
    private javax.swing.JTextField tf_det_ipitrib_qunid;
    private javax.swing.JTextField tf_det_ipitrib_vbc;
    private javax.swing.JTextField tf_det_ipitrib_vipi;
    private javax.swing.JTextField tf_det_ipitrib_vunid;
    private javax.swing.JTextField tf_det_is_pimpsel;
    private javax.swing.JTextField tf_det_is_pimpselespec;
    private javax.swing.JTextField tf_det_is_qtrib;
    private javax.swing.JTextField tf_det_is_utrib;
    private javax.swing.JTextField tf_det_is_vbcimpsel;
    private javax.swing.JTextField tf_det_is_vimpsel;
    private javax.swing.JTextField tf_det_pisaliq_ppis;
    private javax.swing.JTextField tf_det_pisaliq_vbc;
    private javax.swing.JTextField tf_det_pisaliq_vpis;
    private javax.swing.JTextField tf_det_pisqtde_valiqprod;
    private javax.swing.JTextField tf_det_pisst_ppis;
    private javax.swing.JTextField tf_det_pisst_qbcprod;
    private javax.swing.JTextField tf_det_pisst_valiqprod;
    private javax.swing.JTextField tf_det_pisst_vbc;
    private javax.swing.JTextField tf_det_pisst_vpis;
    private javax.swing.JTextField tf_det_prod_EXTIPI;
    private javax.swing.JTextField tf_det_prod_cean;
    private javax.swing.JTextField tf_det_prod_ceantrib;
    private javax.swing.JTextField tf_det_prod_cest;
    private javax.swing.JTextField tf_det_prod_cprod;
    private javax.swing.JTextField tf_det_prod_nFCI;
    private javax.swing.JTextField tf_det_prod_nItemPed;
    private javax.swing.JTextField tf_det_prod_ncm;
    private javax.swing.JTextField tf_det_prod_qcom;
    private javax.swing.JTextField tf_det_prod_qtrib;
    private javax.swing.JTextField tf_det_prod_ucom;
    private javax.swing.JTextField tf_det_prod_utrib;
    private javax.swing.JTextField tf_det_prod_vdesc;
    private javax.swing.JTextField tf_det_prod_vfrete;
    private javax.swing.JTextField tf_det_prod_voutro;
    private javax.swing.JTextField tf_det_prod_vprod;
    private javax.swing.JTextField tf_det_prod_vseg;
    private javax.swing.JTextField tf_det_prod_vuncom;
    private javax.swing.JTextField tf_det_prod_vuntrib;
    private javax.swing.JTextField tf_det_prod_xPed;
    private javax.swing.JTextField tf_det_prod_xprod;
    // End of variables declaration//GEN-END:variables

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    public String processText(String wText) {
        tf_det_prod_cprod.setText(wText);
        return "";
    }

    public String PercorrejcbxNum(JComboBox jcbx, String wVar, int wInd) {
        try {
            if (wVar == null || wVar.toString().trim().length() <= 0) {
                jcbx.setSelectedIndex(-1);
                return wVar;
            }
            String wwVar = wVar.toString().trim();
            boolean wFlagjcbx = false;
            int wIndice = -1;
            for (int i = 0; i < jcbx.getItemCount(); i++) {
                if (jcbx.getItemAt(i).toString().trim().length() > 0) {
                    if (jcbx.getItemAt(i).toString().trim().substring(0, wInd).equals(wwVar)) {
                        jcbx.setSelectedIndex(i);
                        wFlagjcbx = true;
                        wIndice = i;
                        break;
                    }
                }
            }
            if (wFlagjcbx == true) {
                jcbx.setSelectedIndex(wIndice);
            } else {
                jcbx.addItem(new comboMultidata(wwVar, wwVar));
                jcbx.setSelectedIndex(jcbx.getItemCount() - 1);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "falha não esperada!!! " + erro);
        }
        return wVar;

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

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDoframe1 = veioDo1;
        if (this.veioDoframe1.ww_Infnfe_statusnfe.equals("Em Digitação")) {
            botao_editar.setEnabled(true);
        }
    }

    public void enableDisableFields(boolean flag) {
        tf_det_prod_cprod.setEnabled(flag);
        tf_det_prod_cprod.setEditable(flag);
        tf_det_prod_ncm.setEnabled(flag);
        tf_det_prod_ncm.setEditable(flag);
        tf_det_prod_cest.setEnabled(flag);
        tf_det_prod_cest.setEditable(flag);
        tf_det_prod_ucom.setEnabled(flag);
        tf_det_prod_ucom.setEditable(flag);
        tf_det_prod_utrib.setEnabled(flag);
        tf_det_prod_utrib.setEditable(flag);
        jCheck_det_prod_indtot.setEnabled(flag);

        jcbx_det_pisaliq_cst.setEnabled(flag);
        tf_det_pisaliq_vbc.setEnabled(flag);
        jcbx_det_pisaliq_tipocalculo.setEnabled(flag);
        tf_det_pisaliq_ppis.setEnabled(flag);
        tf_det_pisaliq_vpis.setEnabled(flag);

        jcbx_det_cofinsaliq_cst.setEnabled(flag);
        tf_det_cofinsaliq_vbc.setEnabled(flag);
        jcbx_det_cofinsaliq_tipocalculo.setEnabled(flag);
        tf_det_cofinsaliq_pcofins.setEnabled(flag);
        tf_det_cofinsaliq_vcofins.setEnabled(flag);

        jComboBox2.setEnabled(flag);
        jcbx_det_ibs_cst.setEnabled(flag);
        jcbx_det_ibs_cclasstrib.setEnabled(flag);
        jcbx_det_ibs_pibsteste.setEnabled(flag);
        tf_det_ibs_pibstotal.setEnabled(flag);
        tf_det_ibs_pibsuf.setEnabled(flag);

        jcbx_det_is_cst.setEnabled(flag);
        jcbx_det_is_cclasstrib.setEnabled(flag);
        tf_det_is_pimpsel.setEnabled(flag);
        jcbx_det_is_cstibscbs.setEnabled(flag);
        jcbx_det_is_cclasstribibscbs.setEnabled(flag);

        jcbx_det_cbs_cst.setEnabled(flag);
        jcbx_det_cbs_cclasstrib.setEnabled(flag);
        jcbx_det_cbs_pcbsteste.setEnabled(flag);
        tf_det_cbs_pcbsref.setEnabled(flag);
        tf_det_cbs_pcbs.setEnabled(flag);

        botao_salvar.setEnabled(flag);
        botao_editar.setEnabled(false);
    }

    public void getParamStart() {
        int anoAtual = LocalDate.now().getYear();

        if (anoAtual >= 2025 && anoAtual <= 2028) {
            jComboBox2.setSelectedIndex(0); // "N/A"
            jcbx_det_ibs_pibsteste.setSelectedIndex(0);
        } else if (anoAtual == 2029) {
            jComboBox2.setSelectedIndex(1); // "10%"
        } else if (anoAtual == 2030) {
            jComboBox2.setSelectedIndex(2); // "20%"
        } else if (anoAtual == 2031) {
            jComboBox2.setSelectedIndex(3); // "30%"
        } else if (anoAtual == 2032) {
            jComboBox2.setSelectedIndex(4); // "40%"
        } else if (anoAtual == 2033) {
            jComboBox2.setSelectedIndex(5); // "100%"
        } else {
            jComboBox2.setSelectedIndex(0); // "N/A" (Padrão)
        }
    }

    private double calculaReducaoICMS(int ano) {
        if (ano >= 2025 && ano <= 2028) {
            return 0.0;  // Nenhuma redução
        } else if (ano == 2029) {
            return 0.10; // 10% de redução
        } else if (ano == 2030) {
            return 0.20; // 20% de redução
        } else if (ano == 2031) {
            return 0.30; // 30% de redução
        } else if (ano == 2032) {
            return 0.40; // 40% de redução
        } else if (ano == 2033) {
            return 1.00; // 100% de redução
        } else {
            return 0.0;  // Caso padrão (sem redução)
        }
    }

    public void ajustaICMS(int anoAtual) throws ParseException, SQLException {
        getMap();
        double reducaoICMS = calculaReducaoICMS(anoAtual);
        if (tf_det_icms00_picms.getText() != null && !tf_det_icms00_picms.getText().trim().isEmpty()) {
            double picmsOriginal = getDoubleFromTextField(tf_det_icms00_picms);
            double picmsAjustado = picmsOriginal * (1 - reducaoICMS);
            tf_det_icms00_picms.setText(df4.format(picmsAjustado));
        }
        if (tf_det_icms00_vicms.getText() != null && !tf_det_icms00_vicms.getText().trim().isEmpty() && tf_det_icms00_vicms.getText().trim().length() > 0) {
            double vicmsOriginal = getDoubleFromTextField(tf_det_icms00_vicms);
            double vicmsAjustado = vicmsOriginal * (1 - reducaoICMS);
            tf_det_icms00_vicms.setText(df2.format(vicmsAjustado));
        }
    }

    public void calcula_ibs_pibsmun() {
        double det_ibs_pibsuf = 0.0;
        if (!tf_det_ibs_pibsuf.getText().trim().isEmpty()) {
            det_ibs_pibsuf = getDoubleFromTextField(tf_det_ibs_pibsuf);
            if (det_ibs_pibsuf > 100) {
                det_ibs_pibsuf = 100;
            }
            tf_det_ibs_pibsuf.setText(df2.format(det_ibs_pibsuf));
        }
        if (det_ibs_pibsuf > 0.0) {
            double det_ibs_pibsmun = 100 - det_ibs_pibsuf;
            tf_det_ibs_pibsmun.setText(df2.format(det_ibs_pibsmun));
        }
    }

    public void calcula_det_ibsuf_pibsuf() {
        double det_ibsuf_pibsuf = 0.0;
        double det_ibs_pibsuf = 0.0;
        if (!tf_det_ibs_pibstotal.getText().trim().isEmpty() && !tf_det_ibs_pibsuf.getText().trim().isEmpty()) {
            det_ibs_pibsuf = getDoubleFromTextField(tf_det_ibs_pibsuf);
            det_ibsuf_pibsuf = getDoubleFromTextField(tf_det_ibs_pibstotal) * det_ibs_pibsuf;
            tf_det_ibsuf_pibsuf.setText(df4.format(det_ibsuf_pibsuf));
        }
    }

    public void calcula_ibsuf_vibsuf() {
        double det_ibsuf_vibsuf = 0.0;
        double det_ibs_pibsuf = 0.0;
        double det_ibsuf_pibsuf = 0.0;
        double det_ibs_pibstotal = 0.0;
        if (!tf_det_ibs_pibsuf.getText().trim().isEmpty()) {
            det_ibs_pibsuf = getDoubleFromTextField(tf_det_ibs_pibsuf);
        }
        if (!tf_det_ibs_pibstotal.getText().trim().isEmpty()) {
            det_ibs_pibstotal = getDoubleFromTextField(tf_det_ibs_pibstotal);
            det_ibsuf_pibsuf = (det_ibs_pibstotal * det_ibs_pibsuf) / 100;
            tf_det_ibsuf_pibsuf.setText(df4.format(det_ibsuf_pibsuf));
        }
        if (det_ibsuf_pibsuf > 0.0 && !tf_det_ibsuf_vbc.getText().trim().isEmpty() && tf_det_ibsuf_vbc.getText().trim().length() > 0) {
            double det_ibsuf_vbc = getDoubleFromTextField(tf_det_ibsuf_vbc);
            det_ibsuf_vibsuf = (det_ibsuf_vbc * det_ibsuf_pibsuf) / 100;
            tf_det_ibsuf_vibsuf.setText(df2.format(det_ibsuf_vibsuf));
        }
    }

    public void calcula_ibsmun_vibsmun() {
        double det_ibsmun_vibsmun = 0.0;
        double det_ibs_pibsmun = 0.0;
        double det_ibsmun_pibsmun = 0.0;
        double det_ibs_pibstotal = 0.0;
        if (!tf_det_ibs_pibsmun.getText().trim().isEmpty()) {
            det_ibs_pibsmun = getDoubleFromTextField(tf_det_ibs_pibsmun);
        }
        if (!tf_det_ibs_pibstotal.getText().trim().isEmpty()) {
            det_ibs_pibstotal = getDoubleFromTextField(tf_det_ibs_pibstotal);
            det_ibsmun_pibsmun = (det_ibs_pibstotal * det_ibs_pibsmun) / 100;
            tf_det_ibsmun_pibsmun.setText(df4.format(det_ibsmun_pibsmun));
        }
        if (det_ibsmun_pibsmun > 0.0 && !tf_det_ibsmun_vbc.getText().trim().isEmpty()) {
            double det_ibsmun_vbc = getDoubleFromTextField(tf_det_ibsmun_vbc);
            det_ibsmun_vibsmun = (det_ibsmun_vbc * det_ibsmun_pibsmun) / 100;
            tf_det_ibsmun_vibsmun.setText(df2.format(det_ibsmun_vibsmun));
        }
    }

    public void calcula_cbs_vcbs() {
        double det_cbs_vcbs = 0.0;
        double det_cbs_pcbs = 0.0;
        if (!tf_det_cbs_pcbs.getText().trim().isEmpty()) {
            det_cbs_pcbs = getDoubleFromTextField(tf_det_cbs_pcbs);
            tf_det_cbs_pcbs.setText(df4.format(det_cbs_pcbs));
        }
        if (det_cbs_pcbs > 0.0 && !tf_det_cbs_vbc.getText().trim().isEmpty()) {
            double det_cbs_vbc = getDoubleFromTextField(tf_det_cbs_vbc);
            det_cbs_vcbs = (det_cbs_vbc * det_cbs_pcbs) / 100;
            tf_det_cbs_vcbs.setText(df2.format(det_cbs_vcbs));
        }
    }

    public void calcula_is_vimpsel() {
        double det_is_vimpsel = 0.0;
        double det_is_pimpsel = 0.0;
        if (!tf_det_is_pimpsel.getText().trim().isEmpty()) {
            det_is_pimpsel = getDoubleFromTextField(tf_det_is_pimpsel);
            tf_det_is_pimpsel.setText(df4.format(det_is_pimpsel));
        }
        if (det_is_pimpsel > 0.0 && !tf_det_is_vbcimpsel.getText().trim().isEmpty()) {
            double det_is_vbcimpsel = getDoubleFromTextField(tf_det_is_vbcimpsel);
            det_is_vimpsel = (det_is_vbcimpsel * det_is_pimpsel) / 100;
            tf_det_is_vimpsel.setText(df2.format(det_is_vimpsel));
        }
    }

    public void calcula_icmstot_vnf() {

    }

    private double getDoubleFromTextField(JTextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(text.replace(".", "").replace(",", "."));
    }
}
