package app.views.operations.nfe.modal;

import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;
import app.views.operations.nfe.NFe;
import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import br.com.ovconsultoria.boleto.transformer.EBCDICConverter;
import br.com.ovconsultoria.boleto.transformer.GeradorDaDanfe;
import br.com.ovconsultoria.boleto.transformer.GeradorDoXml;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import static br.com.swconsultoria.nfe.Nfe.consultaRecibo;
import static br.com.swconsultoria.nfe.Nfe.enviarNfe;
import static br.com.swconsultoria.nfe.Nfe.montaNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import static br.com.swconsultoria.nfe.util.RetornoUtil.isRetornoAssincrono;
import static br.com.swconsultoria.nfe.util.RetornoUtil.validaAssincrono;
import static br.com.swconsultoria.nfe.util.RetornoUtil.validaSincrono;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.criaNfeProc;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.objectToXml;
import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonREGISTRODEBOLETOBRADESCOdto;
import static com.backend.api.cobranca.bradesco.util.MontaRegistroDeBoletoBradescoUtil.montaRegistroDeBoletoBradesco;
import static com.backend.api.cobranca.bradesco.ws.EnviaJsonBradescoWs.enviaJsonBradescoWs;
import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;
import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;
import com.backend.api.cobranca.santander.dtos.ResponseJsonREGISTRODEBOLETOSANTANDERdto;
import static com.backend.api.cobranca.santander.util.MontaRegistroDeBoletoSantanderUtil.montaRegistroDeBoletoSantander;
import static com.backend.api.cobranca.santander.ws.EnviaJsonSantanderWs.enviaJsonSantanderWs;
import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.CLIENTEdto;
import com.backend.dtos.NFECBdto;
import com.backend.dtos.NFEdto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.config.env;
import static com.frontend.util.BackupUtil.criarBackupPrevCaixa;
import com.frontend.util.ProcessaTokenUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.bind.JAXBException;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lib.ConfiguracoesNfeIniciais;
import lib.MontaNfe;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.formata;

public class EnviaXMLAssinado extends javax.swing.JInternalFrame {

    private NFe veioDo_NFe_frm;
    private JInternalFrame parent;
    ArrayList<String> tListaNNF = new ArrayList<String>();
    int[] tSelectedRows;
    String gCla_Valor = "";
    formata formata = new formata();
    FormatFields f = new FormatFields();
    String arquivoXML = "";
    static ProgressMonitor pbar;
    static int counter = 0;
    String tCaminhoArquivo = "";
    String tArquivoXMLNFe = "";
    String gVersao = "";
    String gAno = "";
    String gSerie = "";
    String gNNF = "";
    String gNItem = "";
    String gCRT = "";
    boolean gFlagImport = true;
    DefaultTableModel RETORNOtableModel = new DefaultTableModel();
    MontaMalote montaMalote = new MontaMalote();
    EnviaMalote enviaMalote = new EnviaMalote();
    env Env = new env();
    String PATH_APP = Env.getPATH_APP();
    String planPrevcaixa = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";
    boolean registrado = false;
    ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil(); //05/02/2026

    public EnviaXMLAssinado(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wAno,
            String wSerie,
            String wNFe,
            ArrayList<String> wListaNNF,
            int[] SelectedRows) {
        gAno = wAno;
        gSerie = wSerie;
        gNNF = wNFe;
        this.jDesktopPane2 = jDesktop;
        tSelectedRows = SelectedRows;
        Iterator it = wListaNNF.iterator();
        while (it.hasNext()) {
            tListaNNF.add(it.next().toString());
        }
        initComponents();
        botao_Enviar.setEnabled(true);
        System.setProperty("XMLretorno", "");
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        RETORNOtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        TableCellRenderer wCol3 = new ColorirLinhaNFeSituacao();
        TableCellRenderer tcr = new Imagem();
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableColumn column = RETORNOtable.getColumnModel().getColumn(0);
        column.setCellRenderer(tcr);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(1).setCellRenderer(wCol3);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(2).setCellRenderer(wCol3);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(3).setCellRenderer(wCol3);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(1535);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(4).setCellRenderer(wCol3);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
    }

    private boolean isProducao() {
        return "1".equals(System.getProperty("tpAmb"));
    }

    private boolean isHomologacao() {
        return "2".equals(System.getProperty("tpAmb"));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane13 = new javax.swing.JScrollPane();
        RETORNOtable = new javax.swing.JTable();

        jLabel2 = new javax.swing.JLabel();
        botao_Enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("Envia Nota Fiscal Eletrônica - Revisão: 12 - Data da Última Revisão: 24/02/2026 - Data da Elaboração: 26/07/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envia Nota Fiscal Eletrônica", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo das Operações:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

        RETORNOtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Ano", "Série", "Número", "Resultado do Envio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RETORNOtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        RETORNOtable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RETORNOtable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        RETORNOtable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        RETORNOtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(RETORNOtable);
        if (RETORNOtable.getColumnModel().getColumnCount() > 0) {
            RETORNOtable.getColumnModel().getColumn(0).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(1).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(2).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(3).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        botao_Enviar.setText("Enviar");
        botao_Enviar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_EnviarMouseMoved(evt);
            }
        });
        botao_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_EnviarActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 0, 51));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setText("Atenção: Em caso de erro não catalogado, desconhecido, rejeição, durante o processo de retorno ou indisponibilidade dos serviços, você pode: consultar as informações no site: https://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx ou buscar ajuda do seu contador.");
        jScrollPane1.setViewportView(jTextArea1);

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_Enviar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botao_Enviar))
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(3, 3, 3))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_Enviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"))); // NOI18N
        jLabel5.setText("Erro");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"))); // NOI18N
        jLabel6.setText("Sucesso");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/alerta.jpg"))); // NOI18N
        jLabel7.setText("Alerta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDesktopPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(botao_sair)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_sair)
                .addContainerGap())
        );

        setBounds(0, 0, 923, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_EnviarActionPerformed
        try {
            /*
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
             */

            ProcessaToken.processaTokenBradesco(); //05/02/2026
            ProcessaToken.processaTokenSantander(); //23/02/2026

            if (isProducao()) {
                criarBackupPrevCaixa(planPrevcaixa);
            }
            Process process = new Process();
            process.start();
        } catch (IOException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_EnviarActionPerformed

    private void botao_EnviarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_EnviarMouseMoved
        botao_Enviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_EnviarMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RETORNOtable;
    private javax.swing.JButton botao_Enviar;
    private javax.swing.JButton botao_sair;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    public class JTableRenderer extends DefaultTableCellRenderer {

        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                if (value != null) {
                    ImageIcon d = (ImageIcon) value;
                    setIcon(d);
                } else {
                    setText("");
                    setIcon(null);
                }
            } else {
                super.setValue(value);
            }
        }
    }

    private String EnviaNfeXML(String nNF, Integer nt) throws SQLException, UnsupportedEncodingException, IOException, BiffException, WriteException, java.lang.Exception {
        try {
            String numeroFormatadoNFe = String.format("%09d", Integer.parseInt(nNF));
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            // Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe = MontaNfe.main(nNF);
            if (enviNFe.getNFe().size() > 1) {
                enviNFe.setIndSinc("0"); // Mais de uma nota ? assíncrono
            } else {
                enviNFe.setIndSinc("1"); // Apenas uma nota ? síncrono
            }

            enviNFe = montaNfe(configINI.iniciaConfiguracoes(), enviNFe, true);
            String xml = objectToXml(enviNFe);
            info("| xmlEnvi........: " + xml);
            if (xml.trim().length() > 0) {
                //    return "";
            }
            TRetEnviNFe retEnviNFe = enviarNfe(configINI.iniciaConfiguracoes(), enviNFe, DocumentoEnum.NFE);
            TableResumoOperacoes(retEnviNFe.getCStat(), retEnviNFe.getXMotivo());
            NFE eNFE = new NFE();
            NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
            NfeInfProtController.Delete(nNF.trim());

            if (isRetornoAssincrono(retEnviNFe)) {
                //Pega o Recibo
                String recibo = retEnviNFe.getInfRec().getNRec();
                int tentativa = 0;
                TRetConsReciNFe retornoNfe = null;
                //Define Numero de tentativas que irá tentar a Consulta
                while (tentativa < 4) {
                    retornoNfe = consultaRecibo(configINI.iniciaConfiguracoes(), recibo, DocumentoEnum.NFE);
                    info("| CStat........: " + retornoNfe.getCStat());
                    info("| ChNFe........: " + retornoNfe.getCMsg());
                    info("| DhRecbto.....: " + retornoNfe.getCUF());
                    info("| Id...........: " + retornoNfe.getDhRecbto());
                    info("| NRec.........: " + retornoNfe.getNRec());
                    info("| TpAmb........: " + retornoNfe.getTpAmb());
                    info("| VerAplic.....: " + retornoNfe.getVerAplic());
                    info("| Versao.......: " + retornoNfe.getVersao());
                    info("| XMotivo......: " + retornoNfe.getXMotivo());
                    info("| CMsg.........: " + retornoNfe.getCMsg());
                    info("| XMsg.........: " + retornoNfe.getXMsg());
                    //TableResumoOperacoes(retornoNfe.getCStat(), retornoNfe.getXMotivo());
                    if (retornoNfe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())
                            || retornoNfe.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())
                            || retornoNfe.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {
                        System.out.println("INFO: Lote Em Processamento, vai tentar novamente apos 1 Segundo.");
                        eNFE.setIde_nnf(nNF.trim());
                        eNFE.setRetenvinfe_nrec(retEnviNFe.getInfRec().getNRec());
                        eNFE.setInfnfe_statusnfe("Em Processamento na SEFAZ");
                        if (NfeController.Update(eNFE)) {
                            System.out.println("NFe atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                        if (retornoNfe.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                            break;
                        }
                        Thread.sleep(5000);
                        tentativa++;
                    } else {
                        break;
                    }
                }
                //Valida se o Retorno é Assincrono
                info("| CStat........: " + retEnviNFe.getCStat());
                info("| CUF..........: " + retEnviNFe.getCUF());
                info("| TpAmb........: " + retEnviNFe.getTpAmb());
                info("| VerAplic.....: " + retEnviNFe.getVerAplic());
                info("| Versao.......: " + retEnviNFe.getVersao());
                info("| XMotivo......: " + retEnviNFe.getXMotivo());
                info("| DhRecbto.....: " + retEnviNFe.getDhRecbto());
                info("*** InfRec *** ------------------------------");
                info("| Numero Recibo: " + retEnviNFe.getInfRec().getNRec());
                info("| Med..........: " + retEnviNFe.getInfRec().getTMed());
                if (retornoNfe.getCStat().equals("225")
                        || !retornoNfe.getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                    eNFE.setIde_nnf(nNF.trim());
                    eNFE.setInfnfe_statusnfe("Rejeitada");
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    if (veioDo_NFe_frm != null) {
                        veioDo_NFe_frm.retornaRegistro("Rejeitada", "", nt, null, null);
                    }
                    if (retornoNfe.getCStat().equals("225") || !retornoNfe.getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                        TableResumoOperacoes(retornoNfe.getCStat(), retornoNfe.getXMotivo()); //22/04/2021
                    } else {
                        TableResumoOperacoes(retornoNfe.getProtNFe().get(0).getInfProt().getCStat(), retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo()); //22/04/2021
                    }
                    return "";
                }
                if (!retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                    eNFE.setIde_nnf(nNF.trim());
                    eNFE.setInfnfe_statusnfe("Rejeitada");
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    if (veioDo_NFe_frm != null) {
                        veioDo_NFe_frm.retornaRegistro("Rejeitada", "", nt, null, null);
                    }
                    TableResumoOperacoes(retornoNfe.getProtNFe().get(0).getInfProt().getCStat(), retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo()); //22/04/2021
                    return "";
                }
                TableResumoOperacoes(retornoNfe.getProtNFe().get(0).getInfProt().getCStat(), retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
                System.out.println("Retorno: " + objectToXml(retornoNfe));
                validaAssincrono(retornoNfe);
                TableResumoOperacoes(retornoNfe.getProtNFe().get(0).getInfProt().getCStat(), retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
                System.out.println();
                System.out.println("# Status: " + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retornoNfe.getProtNFe().get(0).getInfProt().getNProt());
                System.out.println("# XML Final: " + criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0)));
                NfeInfProtController.Create(nNF.trim(), retornoNfe.getProtNFe().get(0).getInfProt().getCStat());
                eNFE_INFPROT.setProtnfe_nnf(nNF.trim());
                eNFE_INFPROT.setProtnfe_versao(retornoNfe.getProtNFe().get(0).getVersao());
                eNFE_INFPROT.setProtnfe_sequencia("1");
                eNFE_INFPROT.setInfprot_cstat(retornoNfe.getProtNFe().get(0).getInfProt().getCStat());
                eNFE_INFPROT.setInfprot_chnfe(retornoNfe.getProtNFe().get(0).getInfProt().getChNFe());
                eNFE_INFPROT.setInfprot_dhrecbto(retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                eNFE_INFPROT.setInfprot_nid(retornoNfe.getProtNFe().get(0).getInfProt().getId());
                eNFE_INFPROT.setInfprot_nprot(retornoNfe.getNRec());
                eNFE_INFPROT.setInfprot_tpamb(retornoNfe.getProtNFe().get(0).getInfProt().getTpAmb());
                eNFE_INFPROT.setInfprot_veraplic(retornoNfe.getProtNFe().get(0).getInfProt().getVerAplic());
                eNFE_INFPROT.setInfprot_xmotivo(retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo().replace("Autorizado o uso da NF-e", "Autorização de Uso"));
                NfeInfProtController.Update(eNFE_INFPROT);
                eNFE = new NFE();
                eNFE.setIde_nnf(nNF.trim());
                eNFE.setInfnfe_statusnfe("Autorizada");
                eNFE.setInfprot_nprot(retornoNfe.getProtNFe().get(0).getInfProt().getNProt());
                eNFE.setInfprot_dhrecbto(retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                eNFE.setXml_autorizado(criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0)));
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }

                if (veioDo_NFe_frm != null) {
                    SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String data = "dd/MM/yyyy HH:mm:ss";
                    String pattern = "yyyy-MM-dd HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date date = df.parse(retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                    String wwDhRecbto = formatador1.format(date);
                    veioDo_NFe_frm.retornaRegistro("Autorizada", wwDhRecbto, nt, null, null);
                }
            } else {
                if (retEnviNFe.getCStat().equals("225")
                        || !retEnviNFe.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                    eNFE.setIde_nnf(nNF.trim());
                    eNFE.setInfnfe_statusnfe("Rejeitada");
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    if (veioDo_NFe_frm != null) {
                        veioDo_NFe_frm.retornaRegistro("Rejeitada", "", nt, null, null);
                    }
                    TableResumoOperacoes(retEnviNFe.getProtNFe().getInfProt().getCStat(), retEnviNFe.getProtNFe().getInfProt().getXMotivo());
                    return "";
                }
                //Se for else o Retorno é Sincrono
                //TableResumoOperacoes(retEnviNFe.getCStat(), retEnviNFe.getXMotivo());
                NfeInfProtController.Create(nNF.trim(), retEnviNFe.getProtNFe().getInfProt().getCStat());
                eNFE_INFPROT.setProtnfe_nnf(nNF.trim());
                eNFE_INFPROT.setProtnfe_versao(retEnviNFe.getProtNFe().getVersao());
                eNFE_INFPROT.setProtnfe_sequencia("1");
                eNFE_INFPROT.setInfprot_cstat(retEnviNFe.getProtNFe().getInfProt().getCStat());
                eNFE_INFPROT.setInfprot_chnfe(retEnviNFe.getProtNFe().getInfProt().getChNFe());
                eNFE_INFPROT.setInfprot_dhrecbto(retEnviNFe.getProtNFe().getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                eNFE_INFPROT.setInfprot_nid(retEnviNFe.getProtNFe().getInfProt().getId());
                eNFE_INFPROT.setInfprot_nprot(retEnviNFe.getProtNFe().getInfProt().getNProt());
                eNFE_INFPROT.setInfprot_tpamb(retEnviNFe.getProtNFe().getInfProt().getTpAmb());
                eNFE_INFPROT.setInfprot_veraplic(retEnviNFe.getProtNFe().getInfProt().getVerAplic());
                eNFE_INFPROT.setInfprot_xmotivo(retEnviNFe.getProtNFe().getInfProt().getXMotivo().replace("Autorizado o uso da NF-e", "Autorização de Uso"));
                NfeInfProtController.Update(eNFE_INFPROT);
                info("| CStat........: " + retEnviNFe.getCStat());
                info("| CUF..........: " + retEnviNFe.getCUF());
                info("| TpAmb........: " + retEnviNFe.getTpAmb());
                info("| VerAplic.....: " + retEnviNFe.getVerAplic());
                info("| Versao.......: " + retEnviNFe.getVersao());
                info("| XMotivo......: " + retEnviNFe.getXMotivo());
                info("| DhRecbto.....: " + retEnviNFe.getDhRecbto());
                eNFE = new NFE();
                eNFE.setIde_nnf(nNF.trim());
                if (!retEnviNFe.getProtNFe().getInfProt().getCStat().equals("100")) {
                    eNFE.setInfnfe_statusnfe("Rejeitada");
                }
                //Valida Retorno Sincrono
                validaSincrono(retEnviNFe);
                NfeInfProtController.Create(nNF.trim(), retEnviNFe.getProtNFe().getInfProt().getCStat());
                System.out.println();
                System.out.println("# Status: " + retEnviNFe.getProtNFe().getInfProt().getCStat() + " - " + retEnviNFe.getProtNFe().getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retEnviNFe.getProtNFe().getInfProt().getNProt());
                System.out.println("# Xml Final :" + criaNfeProc(enviNFe, retEnviNFe.getProtNFe()));
                eNFE.setInfnfe_statusnfe("Autorizada");
                eNFE.setXml_autorizado(criaNfeProc(enviNFe, retEnviNFe.getProtNFe()));
                eNFE.setInfprot_nprot(retEnviNFe.getProtNFe().getInfProt().getNProt());
                eNFE.setInfprot_dhrecbto(retEnviNFe.getProtNFe().getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                TableResumoOperacoes(retEnviNFe.getProtNFe().getInfProt().getCStat(), retEnviNFe.getProtNFe().getInfProt().getXMotivo());

                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                    String numeroNFe = String.format("%06d", Integer.parseInt(nNF.trim()));
                    String numeroNFeFormatado = String.format("%06d", Integer.parseInt(nNF.trim()));
                    int ideNNF = Integer.parseInt(numeroNFeFormatado);
                    NFEdto nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);
                    CANFENOTFISdto cANFENOTFISdto = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNFeFormatado);

                    if (cANFENOTFISdto != null
                            && nfeDto != null) {
                        GeradorDaDanfe geradorDaDanfe = new GeradorDaDanfe();
                        geradorDaDanfe.geraDANFE(eNFE.getXml_autorizado());
                        GeradorDoXml geradorDoXml = new GeradorDoXml();
                        geradorDoXml.geraXML(eNFE.getXml_autorizado());
                        CLIENTEdto cliente = CLIENTEcontroller.FindCodigo(cANFENOTFISdto.getNOT_CLIE().trim());
                        String email = "";
                        if (cliente != null) {
                            if (cliente.getCLI_NFE_EMAIL() != null && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
                                email = cliente.getCLI_NFE_EMAIL().trim();
                            } else if (cliente.getCLI_EMAI() != null && !cliente.getCLI_EMAI().trim().isEmpty()) {
                                email = cliente.getCLI_EMAI().trim();
                            } else if (cliente.getCLI_EMAILFINANCEIRO() != null && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
                                email = cliente.getCLI_EMAILFINANCEIRO().trim();
                            }
                        }
                        if (nfeDto.getIDE_TPNF() == 0 || email.isEmpty()) {
                            email = "faturamento2@camarplasticos.com.br";
                        }
                        boolean addDANFE = montaMalote.addDANFEnoLoteInterno(numeroNFe, email, cANFENOTFISdto.getNOT_CLIE());
                        boolean addXML = montaMalote.addXMLnoLoteInterno(numeroNFe, email, cANFENOTFISdto.getNOT_CLIE());
                        if (addDANFE) {
                            RETORNOtableModel.addRow(new Object[]{
                                "", gAno, gSerie, numeroNFe,
                                "DANFE gerada com sucesso"
                            });
                        }
                        if (addXML) {
                            RETORNOtableModel.addRow(new Object[]{
                                "", gAno, gSerie, numeroNFe,
                                "XML gerado com sucesso"
                            });
                        }

                        boolean enviado = false;
                        String pAR_BANCO = "";
                        if (cANFENOTFISdto != null
                                && nfeDto != null
                                && nfeDto.getIDE_TPNF() == 1
                                && nfeDto.getIDE_NATOP().toUpperCase().contains("VENDA")) { //04/02/2026
                            List<CANFEDUPLICdto> duplicatas = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);
                            if (duplicatas != null && !duplicatas.isEmpty()) {
                                pAR_BANCO = duplicatas.get(0).getPAR_BANCO().trim();
                                if (pAR_BANCO.equals(BRADESCO.getNumeroDoBanco())) {
                                    RegistroDeBoletoBradesco(pAR_BANCO, duplicatas);
                                    if (todasParcelasRegistradas(numeroNFe)) {
                                        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                                        if (nfecb != null) {
                                            nfecb.setINFNFE_TITULOREGISTRADO("Sim");
                                            NFEcontroller.Update(nfecb);
                                        }
                                    }
                                } else if (pAR_BANCO.equals(SANTANDER.getNumeroDoBanco())) {

                                    RegistroDeBoletoSantander(pAR_BANCO, duplicatas);

                                    if (todasParcelasRegistradas(numeroNFe)) {
                                        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                                        if (nfecb != null) {
                                            nfecb.setINFNFE_TITULOREGISTRADO("Sim");
                                            NFEcontroller.Update(nfecb);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("[AVISO] Nenhuma duplicata encontrada para a NFe " + numeroFormatadoNFe);
                                RETORNOtableModel.addRow(new Object[]{"", gAno, gSerie, numeroFormatadoNFe, "Nenhuma duplicata encontrada"});
                            }
                        }
                        enviado = enviaMalote.enviaMalote(pAR_BANCO, cliente, cANFENOTFISdto);
                        montaMalote.limparMalote();
                        if (enviado) {
                            RETORNOtableModel.addRow(new Object[]{
                                "", gAno, "", numeroNFe,
                                "DANFE, XML " + (!this.registrado ? "" : "e boleto(s) ") + "enviado(s) com sucesso para o(s) Email(s): " + email
                            });
                            NFECBdto nfecb = new NFECBdto();
                            nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                            if (nfecb != null && addDANFE && addXML) {
                                nfecb.setINFNFE_DANFEXMLENVIADO("Sim");
                            }
                            if (this.registrado
                                    && nfecb != null) {
                                nfecb.setINFNFE_BOLETOENVIADO("Sim");
                            }
                            if (nfecb != null) {
                                NFEcontroller.Update(nfecb);
                            }
                            if (veioDo_NFe_frm != null) {
                                SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String data = "dd/MM/yyyy HH:mm:ss";
                                String pattern = "yyyy-MM-dd HH:mm:ss";
                                DateFormat df = new SimpleDateFormat(pattern);
                                Date date = df.parse(retEnviNFe.getProtNFe().getInfProt().getDhRecbto().replace("T", " ").replace("-03:00", ""));
                                String wwDhRecbto = formatador1.format(date);
                                veioDo_NFe_frm.retornaRegistro("Autorizada", wwDhRecbto, nt, null, nfecb);
                            }
                        } else {
                            RETORNOtableModel.addRow(new Object[]{
                                "", gAno, "", numeroNFe,
                                "DANFE, XML " + (!this.registrado ? "" : "e boleto(s) ") + "NÃO ENVIADO(s) para o(s) Email(s): " + email
                            });
                        }
                    } else {
                        RETORNOtableModel.addRow(new Object[]{
                            "", gAno, gSerie, numeroNFe,
                            "XML autorizado não encontrado para a NFe."
                        });
                    }
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }

                //TableResumoOperacoes(retEnviNFe.getProtNFe().getInfProt().getCStat(), retEnviNFe.getProtNFe().getInfProt().getXMotivo());
            }
        } catch (NfeException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
        return "";
    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDo_NFe_frm = veioDo1;
    }

    public void TableResumoOperacoes(String wCStat, String wXMotivo) {
        RETORNOtableModel.addRow(new Object[]{
            "", gAno, gSerie, gNNF, wCStat + "-" + wXMotivo});
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, gNNF, "Erro" + "-" + line});
        }
        scanner.close();
        jLabel2.setText("Processo Finalizado.");
        jLabel1.setText("");
        jProgressBar1.setValue(100);
        Cursor cursor = Cursor.getDefaultCursor();
        jDesktopPane2.setCursor(cursor);
    }

    public class Process extends Thread {

        public void run() {
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            jLabel1.setText("Enviando Nota Fiscal Eletrônica.");
            jLabel2.setText("Por favor, aguarde.");
            jProgressBar1.setIndeterminate(true);
            botao_Enviar.setEnabled(false);
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            jDesktopPane2.setCursor(cursor);
            jProgressBar1.setValue(0);
            RETORNOtableModel = (DefaultTableModel) RETORNOtable.getModel();
            System.setProperty("gError", "");
            arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
            Iterator itListaNNF = tListaNNF.iterator();
            int nt = 0;
            while (itListaNNF.hasNext()) {
                try {
                    gNNF = itListaNNF.next().toString();
                    EnviaNfeXML(gNNF, tSelectedRows[nt]);
                    nt = nt + 1;
                } catch (SQLException ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BiffException ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriteException ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.lang.Exception ex) {
                    Logger.getLogger(EnviaXMLAssinado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            jLabel2.setText("Processo Finalizado.");
            jLabel1.setText("");
            jProgressBar1.setIndeterminate(false);
            jProgressBar1.setValue(100);
            cursor = Cursor.getDefaultCursor();
            jDesktopPane2.setCursor(cursor);
            botao_Enviar.setEnabled(false);
        }
    }

    public boolean registroDeBoletoBradesco(CANFEDUPLICdto dup) throws SQLException, ParseException {
        String numeroNFe = String.format("%06d", Integer.parseInt(dup.getPAR_CODI().trim()));
        String numeroNFeFormatado = String.format("%06d", Integer.parseInt(dup.getPAR_CODI().trim()));
        int ideNNF = Integer.parseInt(numeroNFeFormatado);
        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();

        //CLIENTEdto cLIENTEdto = CLIENTEcontroller.FindCodigo(dup.getPAR_CLIE().trim());
        //CANFENOTFISdto cANFENOTFISdto = CANFENOTFIScontroller.NamedQueryFindByNota(dup.getPAR_CODI().trim());
        NFEdto nfe = NFEcontroller.NamedQueryFindByIDENNF(ideNNF); //Recebe xml
        if (nfe == null) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_CODI().trim(),
                "A nota fiscal eletrônica (NFe) ainda não foi gerada nem autorizada pela SEFAZ. Verifique no emissor da NFe."
            });
            return false;
        }
        System.out.println("[INFO] Iniciando registro de boletos para a NFe: " + dup.getPAR_CODI().trim());
        boolean maloteGerado = false;
        try {
            if ("DIGITADO".equals(dup.getPAR_STATUS().trim()) && BRADESCO.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                String json = montaRegistroDeBoletoBradesco(dup);
                System.out.println("[DEBUG] JSON enviado ao Bradesco: " + json);
                String jsonResponse = "";
                if (isProducao()) { //Ambiente de Produção
                    jsonResponse = enviaJsonBradescoWs(ServicosBradescoEnum.REGISTRODEBOLETOBRADESCO, json, "POST");
                } else {
                    //Json teste para Ambiente de Homologação
                    //jsonResponse = "{  \"idProduto\" : 9,  \"negociacao\" : 336600000000047654,  \"cpssoaJuridContr\" : 2269651,  \"ctpoContrNegoc\" : 48,  \"nseqContrNegoc\" : 237202,  \"cprodtServcOper\" : 1730,  \"nuTituloGerado\" : 7016211,  \"tp08Reg1\" : 1,  \"agencCred10\" : 0,  \"ctaCred10\" : 0,  \"digCred10\" : \"00\",  \"cip10\" : 0,  \"codStatus10\" : 1,  \"status10\" : \"A VENCER/VENCIDO\",  \"nomeBeneficiario\" : \"CAMAR PLASTICOS LTDA\",  \"logradouroBeneficiario\" : \"R BLENDA              206\",  \"nuLogradouroBeneficiario\" : \"\",  \"complementoLogradouroBeneficiario\" : \"\",  \"bairroBeneficiario\" : \"JARDIM PEROLA\",  \"cepBeneficiario\" : 13454,  \"cepComplementoBeneficiario\" : 189,  \"municipioBeneficiario\" : \"SANTA BARBARA D'OEST\",  \"ufBeneficiario\" : \"SP\",  \"razCredt10\" : 0,  \"nomePagador\" : \"MARIA OCELIA IRANI DE OLIVEIRA ME\",  \"cpfcnpjPagador\" : 3107970000189,  \"enderecoPagador\" : \"RUACOMENDADOR JOAO MARICATO\",  \"bairroPagador\" : \"ALTO\",  \"municipioPagador\" : \"JABOTICABAL\",  \"ufPagador\" : \"SP\",  \"cepPagador\" : 14875,  \"cepComplementoPagador\" : \"600\",  \"cebp10\" : \"\",  \"debitoAuto10\" : \"\",  \"aceite10\" : \"N\",  \"endEletronicoPagador\" : \"\",  \"nomeSacadorAvalista\" : \"CAMAR PLASTICOS LTDA\",  \"cnpjCpfSacadorAvalista\" : 52703063000108,  \"enderecoSacadorAvalista\" : \"RUA DA BLENDA\",  \"municipioSacadorAvalista\" : \"SANTA BARBARA D'OESTE\",  \"ufSacadorAvalista\" : \"SP\",  \"cepSacadorAvalista\" : 13454,  \"cepComplementoSacadorAvalista\" : 189,  \"tp08Reg2\" : 2,  \"cense10\" : 0,  \"agenOper10\" : 0,  \"bcoDepos10\" : 0,  \"agenDepos10\" : 0,  \"seuNumeroTitulo\" : \"02987\",  \"dtRegistro\" : \"04112025\",  \"especieDocumentoTitulo\" : \"CH\",  \"descEspecie\" : \"\",  \"vlIOF\" : 0,  \"dtEmissao\" : \"04112025\",  \"codigoMoedaTitulo\" : \"R$\",  \"quantidadeMoeda\" : 0,  \"quantidadeCasas\" : 2,  \"dtVencimento\" : \"03.12.2025\",  \"descricacaoMoeda\" : \"R$\",  \"vlTitulo\" : 103,  \"vlAbatimento\" : 0,  \"dtInstrucaoProtestoNegativação\" : \"\",  \"diasInstrucaoProtestoNegativação\" : 0,  \"dataEnvioCartorio\" : \"\",  \"numeroCartorio\" : \"\",  \"numeroProtocoloCartorio\" : \"\",  \"dataPedidoSustacao\" : \"\",  \"dataSustacao\" : \"\",  \"dtMulta\" : \"0\",  \"vlMulta\" : 0,  \"qtdeCasasDecimaisMulta\" : 0,  \"cdValorMulta\" : 0,  \"descCdMulta\" : \"\",  \"dtJuros\" : \"03122025\",  \"vlJurosAoDia\" : 0,  \"dtDesconto1Bonificacao\" : \"\",  \"vlDesconto1Bonificacao\" : 0,  \"qtdeCasasDecimaisDesconto1Bonificacao\" : 0,  \"cdValorDesconto1Bonificacao\" : 0,  \"descCdDesconto1Bonificacao\" : \"\",  \"dtDesconto2\" : \"\",  \"vlDesconto2\" : 0,  \"qtdeCasasDecimaisDesconto2\" : 0,  \"cdValorDesconto2\" : 0,  \"descCdDesconto2\" : \"\",  \"dtDesconto3\" : \"\",  \"vlDesconto3\" : 0,  \"qtdeCasasDecimaisDesconto3\" : 0,  \"cdValorDesconto3\" : 0,  \"descCdDesconto3\" : \"\",  \"diasDispensaMulta\" : 0,  \"diasDispensaJuros\" : 1,  \"cdBarras\" : \"<NWnnwnNnWwNwwnNNwnNwnnWNwnnWWnnnWWnnnWWnwnNNwWWnnnwWNnnnwWNnnwNWnnnWWnnnwWNNnwwNnWwnNWnnnWnnWWnnnwNWNwWnnnnWNw>\",  \"linhaDigitavel\" : \"23793.36601 90000.701624 11004.765407 6 12840000000103\",  \"despCart10\" : 0,  \"bcoCentr10\" : 0,  \"ageCentr10\" : 0,  \"acessEsc10\" : 0,  \"tipoEndosso\" : \"\",  \"codigoOrigemProtesto\" : 0,  \"codigoOrigemTitulo\" : \"\",  \"tpVencimento\" : 0,  \"indInstrucaoProtesto\" : 0,  \"indicadorDecurso\" : 0,  \"quantidadeDiasDecurso\" : 0,  \"ctpoAbat10\" : 0,  \"cdValorJuros\" : 1,  \"tpDesconto1\" : 0,  \"tpDesconto2\" : 0,  \"tpDesconto3\" : 0,  \"nuControleParticipante\" : \"\",  \"diasJuros\" : 1,  \"cdJuros\" : 1,  \"vlJuros\" : 0,  \"cpfcnpjBeneficiário\" : \"052703063000108\",  \"vlTituloEmitidoBoleto\" : 0,  \"dtVencimentoBoleto\" : \"03/12/2025\",  \"indTitParceld10\" : \"\",  \"indParcelaPrin10\" : \"\",  \"indBoletoDda10\" : \"\",  \"dtLimitePagamentoBoleto\" : \"03/12/2025\",  \"dataImpressao10\" : 4112025,  \"horaImpressao10\" : 0,  \"identTitDda10\" : 0,  \"exibeLinDig10\" : \"N\",  \"permPgtoParcial\" : \"\",  \"qtdePgtoParcial\" : 0,  \"bancoDeb\" : 0,  \"agenciaDeb\" : 0,  \"agenciaDebDv\" : 0,  \"contaDeb\" : 0,  \"razaoContaDebito\" : 0}";
                    jsonResponse = simulaRegistroBoletoBradescoHomologacao();
                }
                RETORNOtableModel.addRow(new Object[]{
                    "", gAno, gSerie, dup.getPAR_NUMDOC(),
                    "Título de cobrança enviado para o Bradesco com sucesso"
                });
                System.out.println("[RETORNO] Retorno da API Bradesco (duplicata " + dup.getPAR_NUMDOC() + "): " + jsonResponse);
                // Verifica sucesso pela presença de campos-chave
                if (jsonResponse != null && jsonResponse.contains("linhaDigitavel")) {
                    ObjectMapper mapper = new ObjectMapper();
                    ResponseJsonREGISTRODEBOLETOBRADESCOdto response = mapper.readValue(jsonResponse, ResponseJsonREGISTRODEBOLETOBRADESCOdto.class);
                    // Atualiza status da duplicata
                    RETORNOtableModel.addRow(new Object[]{
                        "", gAno, gSerie, dup.getPAR_NUMDOC(),
                        "Título de cobrança registrado com sucesso"
                    });
                    System.out.println("Linha digitável retornado com sucesso: " + response.getLinhaDigitavel());
                    System.out.println("Código de barras retornado com sucesso: " + response.getCdBarras());
                    boolean gerado = false;
                    if (isHomologacao()) { // 20/01/2026
                        RETORNOtableModel.addRow(new Object[]{
                            "", gAno, gSerie, dup.getPAR_NUMDOC(),
                            "Ambiente de Homologação (API do Bradesco não consumida)."
                        });
                        System.out.println("Resposta não contém dados válidos: " + jsonResponse);
                        //gerado = true;
                    } else {
                        //gerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(BRADESCO.getNumeroDoBanco(), dup, response);
                    }
                    gerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(BRADESCO.getNumeroDoBanco(), dup, response);
                    String linhaDigitavel = response.getLinhaDigitavel().replaceAll("\\D", "");
                    EBCDICConverter eBCDICConverter = new EBCDICConverter();
                    String codigoDeBarras = "";
                    if (eBCDICConverter.isEBCDIC(response.getCdBarras().trim())) {
                        codigoDeBarras = eBCDICConverter.ebcdicToNumeric(response.getCdBarras().trim());
                    }
                    if (gerado) {
                        RETORNOtableModel.addRow(new Object[]{
                            "", gAno, gSerie, dup.getPAR_NUMDOC(),
                            "Boleto de cobranca gerado com sucesso"
                        });
                        String pAR_CODI = dup.getPAR_CODI();
                        String pAR_PARC = dup.getPAR_PARC();
                        caNFEDUPLICdto = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(pAR_CODI.trim(), pAR_PARC.trim());
                        caNFEDUPLICdto.setPAR_ARQREMESSA("API");
                        caNFEDUPLICdto.setPAR_DATAREMESSA(new Date());
                        caNFEDUPLICdto.setPAR_VENCTITULO(caNFEDUPLICdto.getPAR_VENC());
                        caNFEDUPLICdto.setPAR_STATUS("REGISTRADO"); //REGISTRADO
                        caNFEDUPLICdto.setPAR_LINHADIGITAVEL(linhaDigitavel);
                        caNFEDUPLICdto.setPAR_CODIGODEBARRAS(codigoDeBarras);
                        caNFEDUPLICdto.setPAR_NONU(response.getNuTituloGerado() != null ? String.valueOf(response.getNuTituloGerado()) : "");
                        if (isProducao()) {
                            CANFEDUPLICcontroller.Update(caNFEDUPLICdto);
                        }
                        //NFECBdto nfecb = new NFECBdto();
                        //nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                        //if (nfecb != null) {
                        //    nfecb.setINFNFE_TITULOREGISTRADO("Sim");
                        //    NFEcontroller.Update(nfecb);
                        //}
                        RETORNOtableModel.addRow(new Object[]{
                            "", gAno, gSerie,
                            response.getNuTituloGerado() != null ? String.valueOf(response.getNuTituloGerado()) : "",
                            "Base de dados LOCAL da duplicata atualizado com sucesso"});
                    }
                } else {
                    RETORNOtableModel.addRow(new Object[]{
                        "", gAno, gSerie, dup.getPAR_NUMDOC(),
                        "Erro no retorno da API do Bradesco (serviço fora do ar ou indisponível)."
                    });
                    System.out.println("Resposta não contém dados válidos: " + jsonResponse);
                    return false;
                }
            }

        } catch (Exception ex) {
            System.err.println("[ERRO] Falha ao registrar duplicata " + dup.getPAR_NUMDOC() + ": " + ex.getMessage());
            ex.printStackTrace();
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "Exceção ao registrar o titulo de cobranca (API Bradesco): " + ex.getMessage()
            });
        }
        /*
        String xmlNFe = nfe.getXML_AUTORIZADO();
        GeradorDaDanfe geradorDaDanfe = new GeradorDaDanfe();
        geradorDaDanfe.geraDANFE(xmlNFe);
        GeradorDoXml geradorDoXml = new GeradorDoXml();
        geradorDoXml.geraXML(xmlNFe);
        String email = "";
        if (cLIENTEdto != null) {
            if (cLIENTEdto.getCLI_NFE_EMAIL() != null && !cLIENTEdto.getCLI_NFE_EMAIL().trim().isEmpty()) {
                email = cLIENTEdto.getCLI_NFE_EMAIL().trim();
            } else if (cLIENTEdto.getCLI_EMAI() != null && !cLIENTEdto.getCLI_EMAI().trim().isEmpty()) {
                email = cLIENTEdto.getCLI_EMAI().trim();
            } else if (cLIENTEdto.getCLI_EMAILFINANCEIRO() != null && !cLIENTEdto.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
                email = cLIENTEdto.getCLI_EMAILFINANCEIRO().trim();
            }
        }
        boolean addDANFE = montaMalote.addDANFEnoLoteInterno(dup.getPAR_CODI().trim(), email, dup.getPAR_CLIE());
        boolean addXML = montaMalote.addXMLnoLoteInterno(dup.getPAR_CODI().trim(), email, dup.getPAR_CLIE());
        if (addDANFE) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_CODI().trim(),
                "DANFE gerada com sucesso"
            });
        }
        if (addXML) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_CODI().trim(),
                "XML gerado com sucesso"
            });
        }
        if (nfecb != null && addDANFE && addXML) {
            nfecb.setINFNFE_DANFEXMLENVIADO("Sim");
            NFEcontroller.Update(nfecb);
        }
         */
        System.out.println("[INFO] Registro do boleto finalizado para a NFe ");
        return true;
    }

    private boolean registroDeBoletoSantander(CANFEDUPLICdto dup) throws Exception {
        //String json = montaRegistroDeBoletoSantander(dup);

        if (!"DIGITADO".equals(dup.getPAR_STATUS().trim())) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "Duplicata não está com status DIGITADO."
            });
            return false;
        }

        CLIENTEdto clienteDto = CLIENTEcontroller.FindCodigo(dup.getPAR_CLIE().trim());
        CANFENOTFISdto notaFiscalDto = CANFENOTFIScontroller.NamedQueryFindByNota(dup.getPAR_CODI().trim());
        NFEdto nfe = NFEcontroller.NamedQueryFindByIDENNF(Integer.parseInt(dup.getPAR_CODI().trim()));

        String json = isProducao()
                ? montaRegistroDeBoletoSantander(dup)
                : mockBodyRegistroBoletoSantanderHomologacao();

        System.out.println("[DEBUG] JSON enviado ao Santander: " + json);

        //Map<String, String> params = new HashMap<>();
        //params.put("beneficiaryCode", SacadorSantanderEnum.CONVENIO.get());
        //params.put("bankNumber", gerarNossoNumeroSantanderComDV(dup.getPAR_NUMDOC()));
        String jsonResponse = isProducao()
                ? enviaJsonSantanderWs(
                        ServicosSantanderEnum.REGISTRODEBOLETOSANTANDER,
                        json,
                        "POST",
                        null
                )
                : mockResponseRegistroBoletoSantanderHomologacao();

        System.out.println("[DEBUG] JSON RETORNADO do Santander: " + jsonResponse);

        RETORNOtableModel.addRow(new Object[]{
            "", gAno, gSerie, dup.getPAR_NUMDOC(),
            "Título de cobrança enviado para o Santander com sucesso"
        });

        //addLinhaRetorno(new Object[]{
        //    "", dup.getPAR_CODI(), dup.getPAR_PARC(), "",
        //    "Título de cobrança enviado para o Santander com sucesso"});
        if (jsonResponse != null && jsonResponse.contains("digitableLine")) {
            ObjectMapper mapper = new ObjectMapper();
            ResponseJsonREGISTRODEBOLETOSANTANDERdto response
                    = mapper.readValue(jsonResponse, ResponseJsonREGISTRODEBOLETOSANTANDERdto.class);

            String nossoNumero = "";

            if (response.getBankNumber() != null) {
                nossoNumero = String.format("%013d",
                        Long.parseLong(response.getBankNumber()));
            }

            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "Título de cobrança registrado com sucesso"
            });

            if (isHomologacao()) { // 20/01/2026
                RETORNOtableModel.addRow(new Object[]{
                    "", gAno, "", dup.getPAR_NUMDOC(),
                    "Ambiente de Homologação (API do Santander não consumida)."
                });
            }

            geraDanfeXmlMalote(dup, clienteDto);

            atualizaDuplicataPosRegistro(
                    dup,
                    response.getDigitableLine(),
                    response.getBarcode(),
                    nossoNumero,
                    SANTANDER.getNumeroDoBanco()
            );

            atualizaNfe(dup.getPAR_CODI().trim());

            return true;
        }

        RETORNOtableModel.addRow(new Object[]{
            "", gAno, gSerie, dup.getPAR_NUMDOC(),
            "Erro no retorno do título de cobrança (API Santander): " + jsonResponse
        });

        return false;
    }

    private void atualizaDuplicataPosRegistro(CANFEDUPLICdto caNFEDUPLICdto, String linhaDigitavel, String codigoDeBarras, String tituloGerado, String banco) throws Exception {
        boolean gerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(banco, caNFEDUPLICdto, tituloGerado);
        String ld = linhaDigitavel != null ? linhaDigitavel.replaceAll("\\D", "") : "";
        String cb = codigoDeBarras != null ? codigoDeBarras.replaceAll("\\D", "") : "";
        if (BRADESCO.getNumeroDoBanco().equals(caNFEDUPLICdto.getPAR_BANCO().trim())) {
            EBCDICConverter eBCDICConverter = new EBCDICConverter();
            cb = eBCDICConverter.ebcdicToNumeric(codigoDeBarras);
        }
        if (gerado && caNFEDUPLICdto != null) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, "", caNFEDUPLICdto.getPAR_NUMDOC(),
                "Boleto de cobranca gerado com sucesso"
            });
            caNFEDUPLICdto.setPAR_ARQREMESSA("API");
            caNFEDUPLICdto.setPAR_DATAREMESSA(new Date());
            caNFEDUPLICdto.setPAR_VENCTITULO(caNFEDUPLICdto.getPAR_VENC());
            caNFEDUPLICdto.setPAR_STATUS("REGISTRADO");
            caNFEDUPLICdto.setPAR_LINHADIGITAVEL(ld);
            caNFEDUPLICdto.setPAR_CODIGODEBARRAS(cb);
            caNFEDUPLICdto.setPAR_NONU(tituloGerado != null ? String.valueOf(tituloGerado) : "");
            if (isProducao()) {
                CANFEDUPLICcontroller.Update(caNFEDUPLICdto);
            }

            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie,
                tituloGerado,
                "Base de dados LOCAL da duplicata atualizado com sucesso"});
        }
    }

    public class ColorirLinhaNFeSituacao extends DefaultTableCellRenderer {

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
            Color c = Color.BLACK;
            Font ft = new Font("Arial", Font.BOLD, 11);
            //Object text = table.getValueAt(row, 4);
            String text = table.getValueAt(row, 4).toString();
            if (text.contains("100-")
                    || text.contains("103-")
                    || text.contains("105-")
                    || text.contains("104-")
                    || text.contains("sucesso")) {
                c = CorVerde;
            } else {
                c = Color.RED;
            }
            result.setForeground(c);
            return result;
        }
    }

    class Imagem extends JLabel implements TableCellRenderer {

        public Imagem() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            ImageIcon imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            String text = table.getValueAt(row, 4).toString();
            if (text.contains("100-")
                    || text.contains("103-")
                    || text.contains("105-")
                    || text.contains("104-")
                    || text.contains("sucesso")) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            } else {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"));
            }
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }
            setIcon(imagem);
            setText(value.toString());
            return this;
        }
    }

    private void RegistroDeBoletoBradesco(String Banco, List<CANFEDUPLICdto> duplicatas) throws IOException, SQLException, ParseException, BiffException, WriteException {
        Cursor cursorWait = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursorWait);

        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();
        CANFEDUPLICdto filteredCANFEDUPLICdto = new CANFEDUPLICdto();
        filteredCANFEDUPLICdto.setCANFEDUPLICdto(duplicatas);

        FileInputStream file = new FileInputStream(planPrevcaixa);
        WorkbookSettings ws = new WorkbookSettings();
        ws.setSuppressWarnings(true);
        Workbook workbook = Workbook.getWorkbook(file, ws);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(planPrevcaixa), workbook);
        WritableSheet sheet2 = writableWorkbook.getSheet(0);
        try {
            List<String> rEGISTRO = new ArrayList<>();
            int wtl_numseq = 0;
            for (int i = 0; i < filteredCANFEDUPLICdto.getCANFEDUPLICdto().size(); i++) {
                String pAR_CODI = filteredCANFEDUPLICdto.getCANFEDUPLICdto().get(i).getPAR_CODI();
                String pAR_PARC = filteredCANFEDUPLICdto.getCANFEDUPLICdto().get(i).getPAR_PARC();
                caNFEDUPLICdto = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(pAR_CODI.trim(), pAR_PARC.trim());
                wtl_numseq++;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                Date parVencDate = caNFEDUPLICdto.getPAR_VENC();
                LocalDate parVenc = parVencDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String parVencString = parVenc.format(formatter);
                String parVencString2 = parVenc.format(formatter2);
                boolean registrado = registroDeBoletoBradesco(caNFEDUPLICdto);
                this.registrado = registrado;
                //Modal_ViewDataREGISTRATITULOmock mock = new Modal_ViewDataREGISTRATITULOmock();
                //registrado = mock.registroDeBoletoBradesco(caNFEDUPLICdto);
                if (!registrado) {
                    continue;
                }
                //}
                // Abre a planilha
                for (int k = 1; k <= 1000; k++) {
                    Cell[] row = sheet2.getRow(k);
                    String sPAR_NUMDOC = row[2].getContents().toString();
                    //System.out.println("Valor da célula: " + sPAR_NUMDOC);
                    if (sPAR_NUMDOC.equals(caNFEDUPLICdto.getPAR_NUMDOC().trim())) {
                        break;
                    } else if (row[0].getContents().isEmpty() && row[2].getContents().isEmpty()) {
                        sheet2.addCell(new Label(0, k, parVencString2));
                        sheet2.addCell(new Label(1, k, " " + caNFEDUPLICdto.getPAR_NOME().trim()));
                        sheet2.addCell(new Label(2, k, caNFEDUPLICdto.getPAR_NUMDOC().trim()));
                        sheet2.addCell(new Label(3, k, "API-BRADESCO"));
                        sheet2.addCell(new Number(5, k, caNFEDUPLICdto.getPAR_VALO()));
                        break;
                    }
                }
            }
            wtl_numseq += 2;
            //boolean enviado = enviaMalote.enviaMalote(Banco, null, null);
            //if (enviado) {
            //    RETORNOtableModel.addRow(new Object[]{
            //        "", gAno, gSerie, "",
            //        "DANFE(s), XML(s), e Boleto(s) de cobranca enviado(s) com sucesso para o(s) Cliente(s)."
            //    });
            //}
            //montaMalote.limparMalote();
        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
            Cursor cursorDefault = Cursor.getDefaultCursor();
            setCursor(cursorDefault);
        }
    }

    private void RegistroDeBoletoSantander(String Banco, List<CANFEDUPLICdto> duplicatas) throws IOException, SQLException, ParseException, BiffException, WriteException, java.lang.Exception {
        Cursor cursorWait = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursorWait);

        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();
        CANFEDUPLICdto filteredCANFEDUPLICdto = new CANFEDUPLICdto();
        filteredCANFEDUPLICdto.setCANFEDUPLICdto(duplicatas);

        FileInputStream file = new FileInputStream(planPrevcaixa);
        WorkbookSettings ws = new WorkbookSettings();
        ws.setSuppressWarnings(true);
        Workbook workbook = Workbook.getWorkbook(file, ws);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(planPrevcaixa), workbook);
        WritableSheet sheet2 = writableWorkbook.getSheet(0);
        try {
            List<String> rEGISTRO = new ArrayList<>();
            int wtl_numseq = 0;
            for (int i = 0; i < filteredCANFEDUPLICdto.getCANFEDUPLICdto().size(); i++) {
                String pAR_CODI = filteredCANFEDUPLICdto.getCANFEDUPLICdto().get(i).getPAR_CODI();
                String pAR_PARC = filteredCANFEDUPLICdto.getCANFEDUPLICdto().get(i).getPAR_PARC();
                caNFEDUPLICdto = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(pAR_CODI.trim(), pAR_PARC.trim());
                wtl_numseq++;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                Date parVencDate = caNFEDUPLICdto.getPAR_VENC();
                LocalDate parVenc = parVencDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String parVencString = parVenc.format(formatter);
                String parVencString2 = parVenc.format(formatter2);
                boolean registrado = registroDeBoletoSantander(caNFEDUPLICdto);
                this.registrado = registrado;
                //Modal_ViewDataREGISTRATITULOmock mock = new Modal_ViewDataREGISTRATITULOmock();
                //registrado = mock.registroDeBoletoBradesco(caNFEDUPLICdto);
                if (!registrado) {
                    continue;
                }
                //}
                // Abre a planilha
                for (int k = 1; k <= 1000; k++) {
                    Cell[] row = sheet2.getRow(k);
                    String sPAR_NUMDOC = row[2].getContents().toString();
                    //System.out.println("Valor da célula: " + sPAR_NUMDOC);
                    if (sPAR_NUMDOC.equals(caNFEDUPLICdto.getPAR_NUMDOC().trim())) {
                        break;
                    } else if (row[0].getContents().isEmpty() && row[2].getContents().isEmpty()) {
                        sheet2.addCell(new Label(0, k, parVencString2));
                        sheet2.addCell(new Label(1, k, " " + caNFEDUPLICdto.getPAR_NOME().trim()));
                        sheet2.addCell(new Label(2, k, caNFEDUPLICdto.getPAR_NUMDOC().trim()));
                        sheet2.addCell(new Label(3, k, "API-SANTANDER"));
                        sheet2.addCell(new Number(5, k, caNFEDUPLICdto.getPAR_VALO()));
                        break;
                    }
                }
            }
            wtl_numseq += 2;
            //boolean enviado = enviaMalote.enviaMalote(Banco, null, null);
            //if (enviado) {
            //    RETORNOtableModel.addRow(new Object[]{
            //        "", gAno, gSerie, "",
            //        "DANFE(s), XML(s), e Boleto(s) de cobranca enviado(s) com sucesso para o(s) Cliente(s)."
            //    });
            //}
            //montaMalote.limparMalote();
        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
            Cursor cursorDefault = Cursor.getDefaultCursor();
            setCursor(cursorDefault);
        }
    }

    private boolean todasParcelasRegistradas(String numeroNFe) throws SQLException, ParseException {
        if (isHomologacao()) {
            return false;
        }

        List<CANFEDUPLICdto> parcelas = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);

        for (CANFEDUPLICdto dup : parcelas) {
            boolean naoRegistrado = !"REGISTRADO".equalsIgnoreCase(dup.getPAR_STATUS());
            boolean linhaDigitavelVazia = dup.getPAR_LINHADIGITAVEL() == null || dup.getPAR_LINHADIGITAVEL().trim().isEmpty();
            boolean codigoDeBarrasVazio = dup.getPAR_CODIGODEBARRAS() == null || dup.getPAR_CODIGODEBARRAS().trim().isEmpty();

            if (naoRegistrado || linhaDigitavelVazia || codigoDeBarrasVazio) {
                RETORNOtableModel.addRow(new Object[]{
                    "", gAno, "", numeroNFe,
                    "ATENÇÃO: Parcela " + dup.getPAR_PARC() + " da nota " + numeroNFe + " NÃO foi registrada. Registre manualmente!"
                });
                return false;
            }
        }
        return true;
    }

    private String simulaRegistroBoletoBradescoHomologacao() {

        return "{"
                + "\"idProduto\":9,"
                + "\"negociacao\":336600000000047654,"
                + "\"cpssoaJuridContr\":2269651,"
                + "\"ctpoContrNegoc\":48,"
                + "\"nseqContrNegoc\":237202,"
                + "\"cprodtServcOper\":1730,"
                + "\"nuTituloGerado\":7016211,"
                + "\"codStatus10\":1,"
                + "\"status10\":\"A VENCER/VENCIDO\","
                + "\"linhaDigitavel\":\"23793.36601 90000.701624 11004.765407 6 12840000000103\","
                + "\"cdBarras\":\"NWnnwnNnWwNwwnNNwnNwnnWNwnnWWnnnWWnnnWWnwnNNwWWnnnwWNnnnwWNnnwNWnnnWWnnnwWNNnwwNnWwnNWnnnWnnWWnnnwNWNwWnnnnWNw\""
                + "}";
    }

    private String mockResponseRegistroBoletoSantanderHomologacao() {
        return "{"
                + "\"environment\": \"TESTE\","
                + "\"nsuCode\": \"TST123\","
                + "\"nsuDate\": \"2023-05-11\","
                + "\"covenantCode\": 1234567,"
                + "\"bankNumber\": \"1021\","
                + "\"clientNumber\": \"1021-1-1\","
                + "\"dueDate\": \"2023-05-11\","
                + "\"issueDate\": \"2023-05-09\","
                + "\"participantCode\": \"CODIGO 1234\","
                + "\"nominalValue\": 10.00,"
                + "\"payer\": {"
                + "    \"name\": \"João da Silva\","
                + "    \"documentType\": \"CPF\","
                + "    \"documentNumber\": \"12345678901\","
                + "    \"address\": \"rua nove de janeiro\","
                + "    \"neighborhood\": \"bela vista\","
                + "    \"city\": \"sao paulo\","
                + "    \"state\": \"SP\","
                + "    \"zipCode\": \"05134-897\""
                + "},"
                + "\"documentKind\": \"DUPLICATA_MERCANTIL\","
                + "\"deductionValue\": \"0.10\","
                + "\"paymentType\": \"REGISTRO\","
                + "\"writeOffQuantityDays\": \"30\","
                + "\"messages\": ["
                + "    \"mensagem um\","
                + "    \"mensagem dois\""
                + "],"
                + "\"barcode\": \"03396939700000001009356720600000000123450101\","
                + "\"digitableLine\": \"03399356782060000000201234501011693970000000100\","
                + "\"entryDate\": \"2023-05-11\","
                + "\"qrCodePix\": \"00020101021226920014br.gov.bcb.pix2570pix.santander.com.br/qr/v2/cobv/9fa03dbd-0b9c-4910-8ab3-14f6bf48a246520400005303986540410.005802BR5925TESTE CONECTIVIDADE API6009SAO PAULO62070503***63041110\","
                + "\"qrCodeUrl\": \"pix.santander.com.br/qr/v2/cobv/9fa03dbd-0b9c-4910-8ab3-14f6bf48a246\""
                + "}";
    }

    private String mockBodyRegistroBoletoSantanderHomologacao() {
        return "{"
                + "\"environment\": \"TESTE\","
                + "\"nsuCode\": \"TST123\","
                + "\"nsuDate\": \"2023-05-11\","
                + "\"covenantCode\": 1234567,"
                + "\"bankNumber\": \"1021\","
                + "\"clientNumber\": \"1021-1-1\","
                + "\"dueDate\": \"2023-05-11\","
                + "\"issueDate\": \"2023-05-09\","
                + "\"participantCode\": \"CODIGO 1234\","
                + "\"nominalValue\": 10.00,"
                + "\"payer\": {"
                + "    \"name\": \"João da Silva\","
                + "    \"documentType\": \"CPF\","
                + "    \"documentNumber\": \"12345678901\","
                + "    \"address\": \"rua nove de janeiro\","
                + "    \"neighborhood\": \"bela vista\","
                + "    \"city\": \"sao paulo\","
                + "    \"state\": \"SP\","
                + "    \"zipCode\": \"05134-897\""
                + "},"
                + "\"documentKind\": \"DUPLICATA_MERCANTIL\","
                + "\"deductionValue\": \"0.10\","
                + "\"paymentType\": \"REGISTRO\","
                + "\"writeOffQuantityDays\": \"30\","
                + "\"messages\": ["
                + "    \"mensagem um\","
                + "    \"mensagem dois\""
                + "],"
                + "\"key\": {"
                + "    \"type\": \"EMAIL\","
                + "    \"dictKey\": \"suachave@suachave.com.br\""
                + "},"
                + "\"discount\": {"
                + "    \"type\": \"VALOR_DATA_FIXA\","
                + "    \"discountOne\": {"
                + "        \"value\": \"1.00\","
                + "        \"limitDate\": \"2023-05-10\""
                + "    }"
                + "},"
                + "\"interestPercentage\": \"30.00\""
                + "}";
    }

    private void atualizaNfe(String codigo) throws Exception {
        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(codigo);
        if (nfecb != null) {
            nfecb.setINFNFE_TITULOREGISTRADO("Sim");
            if (isProducao()) {
                NFEcontroller.Update(nfecb);
            }
        }
    }

    private void geraDanfeXmlMalote(CANFEDUPLICdto dup, CLIENTEdto clienteDto) throws SQLException, ParseException {

        NFEdto nfeDto = NFEcontroller.NamedQueryFindByIDENNF(
                Integer.parseInt(dup.getPAR_CODI().trim())
        );

        if (nfeDto == null || nfeDto.getXML_AUTORIZADO() == null) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "XML autorizado não encontrado para a NFe."
            });
            return;
        }

        String xmlNFe = nfeDto.getXML_AUTORIZADO();

        new GeradorDaDanfe().geraDANFE(xmlNFe);
        new GeradorDoXml().geraXML(xmlNFe);

        String email = Optional.ofNullable(clienteDto)
                .map(c -> Optional.ofNullable(c.getCLI_NFE_EMAIL())
                .orElse(Optional.ofNullable(c.getCLI_EMAI())
                        .orElse(c.getCLI_EMAILFINANCEIRO())))
                .orElse("");

        boolean danfe = montaMalote.addDANFEnoLoteInterno(dup.getPAR_CODI().trim(), email, dup.getPAR_CLIE());
        boolean xml = montaMalote.addXMLnoLoteInterno(dup.getPAR_CODI().trim(), email, dup.getPAR_CLIE());

        if (danfe) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "DANFE gerada com sucesso"});
        }
        if (xml) {
            RETORNOtableModel.addRow(new Object[]{
                "", gAno, gSerie, dup.getPAR_NUMDOC(),
                "XML gerado com sucesso"});
        }

        NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(dup.getPAR_CODI().trim());
        if (nfecb != null && danfe && xml) {
            nfecb.setINFNFE_DANFEXMLENVIADO("Sim");
            nfecb.setINFNFE_BOLETOENVIADO("Sim");
            if (isProducao()) {
                NFEcontroller.Update(nfecb);
            }
        }
    }

}
