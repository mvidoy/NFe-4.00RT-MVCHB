package app.views.operations.nfe.modal;

import app.controllers.NfeController;
import app.models.NFE;
import app.views.operations.nfe.NFe;
import br.com.swconsultoria.impressao.model.Impressao;
import br.com.swconsultoria.impressao.service.ImpressaoService;
import br.com.swconsultoria.impressao.util.ImpressaoUtil;
import com.frontend.config.env;
import java.awt.Cursor;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import util.formata;

public class ImprimeDANFe extends javax.swing.JInternalFrame {

    env Env = new env();
    private NFe veioDoframe1;
    private JInternalFrame parent;
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    private javax.swing.JTextField tf_campo;
    formata formata = new formata();
    String gNF = "";
    String gTpImp = "";
    int selection = 0;
    String outFileName = "";

    public ImprimeDANFe(JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wNF, String wTpImp) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
        gNF = wNF;
        gTpImp = wTpImp;
        this.jDesktopPane2 = jDesktop;
        initComponents();
        if (gTpImp.equals("0")) {
            botao_imprimir.setEnabled(false);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        botao_imprimir = new javax.swing.JButton();
        botao_visualizar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("IMPRESSÃO DA DANFE. iReport 5.0.1 (layout) - Revisão: 08 - Data da Última Revisão: 06/02/2023 - Data da Elaboração: 20/06/2016");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N

        jDesktopPane1.setAutoscrolls(true);

        botao_imprimir.setText("Gera/Imprime PDF");
        botao_imprimir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_imprimirMouseMoved(evt);
            }
        });
        botao_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_imprimirActionPerformed(evt);
            }
        });
        jDesktopPane1.add(botao_imprimir);
        botao_imprimir.setBounds(30, 10, 120, 23);

        botao_visualizar.setText("Visualiza");
        botao_visualizar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_visualizarMouseMoved(evt);
            }
        });
        botao_visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_visualizarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(botao_visualizar);
        botao_visualizar.setBounds(170, 10, 90, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botao_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_imprimirActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Selecione a Pasta");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new java.io.File(Env.getpath_exportpdf()));
        int opt = fc.showOpenDialog(jDesktopPane2);
        outFileName = fc.getSelectedFile().getPath();
        this.setCursor(new java.awt.Cursor(3));
        gTpImp = "1";
        imprimeDANFE();
        this.setCursor(new java.awt.Cursor(0));
    }//GEN-LAST:event_botao_imprimirActionPerformed
    private void imprimeDANFE() {

        selection = 0;
        if (gTpImp.equals("1")) {
            selection = JOptionPane.showConfirmDialog(this, "Envia para impressora ?", "Confirmação de Impressão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        }
        String xml = "";
        try {
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(gNF.trim());
            if (jsonNFe.getString("xml_autorizado") != null
                    && jsonNFe.getString("xml_autorizado").trim().length() > 0) {
                xml = jsonNFe.getString("xml_autorizado").trim();
            } else if (jsonNFe.getString("xml_assinado") != null
                    && jsonNFe.getString("xml_assinado").trim().length() > 0) {
                xml = jsonNFe.getString("xml_assinado").trim();
                xml = xml.replace("<enviNFe", "<nfeProc");
                xml = xml.replace("/enviNFe>", "/nfeProc>");
            } else {
                JOptionPane.showMessageDialog(this, "Xml autorizado não consta na base de dados!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //imprimeDANFEsamuel(xml);
            imprimeDANFEosvaldo(xml);

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDoframe1 = veioDo1;
    }

    private void botao_visualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_visualizarActionPerformed
        this.setCursor(new java.awt.Cursor(3));
        gTpImp = "0";
        imprimeDANFE();
        this.setCursor(new java.awt.Cursor(0));
    }//GEN-LAST:event_botao_visualizarActionPerformed

    private void botao_imprimirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_imprimirMouseMoved
        botao_imprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_imprimirMouseMoved

    private void botao_visualizarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_visualizarMouseMoved
        botao_visualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_visualizarMouseMoved
    private javax.swing.JDesktopPane jDesktopPane2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_imprimir;
    private javax.swing.JButton botao_visualizar;
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables

    private void imprimeDANFEsamuel(String xml) throws JRException, ParserConfigurationException, IOException, SAXException, InstantiationException, IllegalAccessException {
        Impressao impressaoNFe = new Impressao();
        impressaoNFe = ImpressaoUtil.impressaoPadraoNFe(xml);
        String PARAM_LOGO_NFE = "Logo";
        String PATH_LOGO_NFE = "/assets/logo_camar.bmp";
        impressaoNFe.getParametros().put(PARAM_LOGO_NFE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_NFE));
        JasperViewer viewer = ImpressaoService.impressaoPreview(impressaoNFe);
        viewer.setZoomRatio(1.00F);
        String nNF = xml.substring(xml.indexOf("<nNF>") + 5, xml.indexOf("</nNF>"));
        nNF = formata.StrZero(String.valueOf(Integer.parseInt(nNF)), 8);
        outFileName = outFileName + "\\" + Env.getPrefixInFiles() + nNF + "_v" + Env.getVersao() + "-procnfeRT.pdf";
        outFileName = outFileName.replace("\\", "/");

        if (gTpImp.equals("1")) {//Faz a impressão em pdf File passando o caminho do Arquivo
            impressaoNFe.getParametros().put(PARAM_LOGO_NFE, ImpressaoService.class.getResourceAsStream(PATH_LOGO_NFE));
            ImpressaoService.impressaoPdfArquivo(impressaoNFe, outFileName);
        }
        double width = this.getSize().getWidth();
        double height = this.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        viewer.setSize((wwidth), (wheight));
        this.jDesktopPane1.removeAll();
        viewer.setVisible(true);
        this.jDesktopPane1.add(viewer.getContentPane());
        viewer.dispose();
        if (gTpImp.equals("1")) {
            if (selection == JOptionPane.YES_OPTION) {
                for (int j = 0; j <= 2; j++) {
                    JasperPrintManager.printPage(ImpressaoService.geraImpressao(impressaoNFe), 0, false); //18/08/2016
                }
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNF.trim());
                eNFE.setInfnfe_danfeimpresso("Sim");
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.getProperty("MsgInvalid");
                }
                if (veioDoframe1 != null) {
                    veioDoframe1.retornaRegistro("Impressa", "", null, null, null);
                }
            }
        }
    }

    private void imprimeDANFEosvaldo(String xml) throws JRException, ParserConfigurationException, IOException, SAXException, InstantiationException, IllegalAccessException {
        HashMap params = new HashMap();
        System.err.println(xml);
        InputSource iss = new InputSource();
        iss.setCharacterStream(new StringReader(xml));
        DocumentBuilderFactory dbff = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbb = dbff.newDocumentBuilder();
        Document documnet = dbb.parse(iss);
        params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, documnet);
        params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "yyyy-MM-dd");
        params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#.##0,##");
        params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.US);
        params.put("isCancelada", false);
        params.put("LOGO_EMPRESA", Env.getpath_pastaAplicacao() + "/assets/logo_camar.bmp");
        //params.put("LOGO_EMPRESA", getClass().getResource("/main/resources").getPath() + "/assets/logo_camar.bmp");
        params.put("SUBREPORT_DUPLICATAS", Env.getpath_pastaAplicacao() + "/relatorios/danfe_fatura.jasper");
        String isStream = Env.getpath_pastaAplicacao() + "/relatorios/DANFE_NFE_RETRATO.jasper";
        //params.put("SUBREPORT_DUPLICATAS", getClass().getResource("/main/resources").getPath() + "/report/danfe_fatura.jasper");
        //String isStream = getClass().getResource("/main/resources").getPath() + "/report/DANFE_NFE_RETRATO.jasper";
        JRXmlDataSource xmlDataSource = new JRXmlDataSource(documnet, "/nfeProc/NFe/infNFe/det");
        xmlDataSource.setNumberPattern("");
        JasperPrint print = JasperFillManager.fillReport(
                isStream,
                params,
                xmlDataSource);
        String nNF = xml.substring(xml.indexOf("<nNF>") + 5, xml.indexOf("</nNF>"));
        nNF = formata.StrZero(String.valueOf(Integer.parseInt(nNF)), 8);
        outFileName = outFileName + "\\" + Env.getPrefixInFiles() + nNF + "_v" + Env.getVersao() + "-procnfeRT.pdf";
        outFileName = outFileName.replace("\\", "/");
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setZoomRatio(1.00F);//Define o tamanho do ZOOM ao abrir o relatório

        if (gTpImp.equals("1")) {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outFileName));
            exporter.exportReport();
        }
        double width = this.getSize().getWidth();
        double height = this.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        viewer.setSize((wwidth), (wheight));
        this.jDesktopPane1.removeAll();
        viewer.setVisible(true);
        this.jDesktopPane1.add(viewer.getContentPane());
        viewer.dispose();
        if (gTpImp.equals("1")) {
            if (selection == JOptionPane.YES_OPTION) {//30/11/2016
                for (int j = 0; j <= 2; j++) {//30/11/2016
                    JasperPrintManager.printPage(print, 0, false); //18/08/2016
                }
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNF.trim());
                eNFE.setInfnfe_danfeimpresso("Sim");
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.getProperty("MsgInvalid");
                }
                if (veioDoframe1 != null) {
                    veioDoframe1.retornaRegistro("Impressa", "", null, null, null);
                }
            }
        }
    }
}
