package app.views.operations.nfe.modal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ProgressMonitor;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.formata;
import app.controllers.NfeCobrDupController;
import app.controllers.NfeController;
import app.controllers.NfeDetProdController;
import app.controllers.NfeInfProtController;
import app.controllers.NfeInformacoesPagamentoController;
import app.controllers.NfeTranspVolController;
import app.models.NFE;
import app.models.NFE_COBR_DUP;
import app.models.NFE_DET_PROD;
import app.models.NFE_INFORMACOESPAGAMENTO;
import app.models.NFE_INFPROT;
import app.models.NFE_TRANSP_VOL;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import com.frontend.config.env;
import javax.xml.transform.stream.StreamSource;

public class ImportaArquivoXML extends javax.swing.JInternalFrame {

    env Env = new env();
    String gCla_Valor = "";
    formata formata = new formata();
    FormatFields f = new FormatFields();
    String arquivoXML = "";
    static ProgressMonitor pbar;
    static int counter = 0;
    String tCaminhoArquivo = "";
    String tArquivoXMLNFe = "";
    String gVersao = "";
    String gNNF = "";
    String gNItem = "";
    String gCRT = "";
    boolean gFlagImport = true;
    DefaultTableModel modelo1 = new DefaultTableModel();

    public ImportaArquivoXML(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wCaminhoArquivo,
            String wArquivoXMLNFe) {
        tCaminhoArquivo = wCaminhoArquivo;
        tArquivoXMLNFe = wArquivoXMLNFe;
        this.jDesktopPane2 = jDesktop;
        initComponents();
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jLabel1.setText("Importando arquivo...");
        jLabel2.setText("Por favor, aguarde.");
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        TableCellRenderer wCol3 = new ColorirLinhaNFeSituacao();
        TableCellRenderer tcr = new Imagem();
        jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableColumn column = jTable1.getColumnModel().getColumn(0);
        column.setCellRenderer(tcr);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(190);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(445);
        jTable1.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(wCol3);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
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
            Object text = table.getValueAt(row, 3);
            if (text != null && "Rejeitado".equals(text.toString().trim())) {
                c = Color.RED;
            }
            if (text != null && "Inserido".equals(text.toString().trim())) {
                c = CorVerde;
            }
            if (text != null && "Alterado".equals(text.toString().trim())) {
                c = Color.BLACK;
            }
            result.setForeground(c);
            //abel.setFont(ft);
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
            // ImageIcon d = (ImageIcon) value;
            ImageIcon imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            Object text = table.getValueAt(row, 3);
            // certifique-se da existencia da imagem "icon.gif" antes de executar
            if (text != null && "Rejeitada".equals(text.toString().trim())) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"));
            }
            if (text != null && "Inserido".equals(text.toString().trim())) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            }
            if (text != null && "Alterado".equals(text.toString().trim())) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/alerta.jpg"));
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setTitle("Importação de Arquivos XML Autorizados - Revisão: 05 - Data da Última Revisão: 09/04/2021 - Data da Elaboração: 09/07/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Importação de Arquivos XML Autorizados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo das Operações:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setStringPainted(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nome  do Arquivo ", "Identif. Reg.", "Situação do Reg.", "Campo", "Erro/Alerta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setText("Processa");
        jButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1MouseMoved(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
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
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botao_sair)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_sair)
                .addContainerGap())
        );

        setBounds(0, 0, 923, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selection = 0;
        selection = JOptionPane.showConfirmDialog(this,
                "Comfirma processamento?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selection == JOptionPane.NO_OPTION) {
            return;
        }
        Process process = new Process();
        process.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseMoved
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton1MouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_sair;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
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

    private void importarNfeXML(String arquivo) throws SQLException {
        try {
            JAXBContext context1 = JAXBContext.newInstance(TNfeProc.class);
            Unmarshaller unmarshaller1 = context1.createUnmarshaller();
            TNfeProc nfeProc = unmarshaller1.unmarshal(new StreamSource(arquivo), TNfeProc.class).getValue();
            if (nfeProc != null) {
                VersaoNFe_A(nfeProc);
                dadosNfe_ide(nfeProc);
                if (gFlagImport == false) {
                    return;
                }
                dadosDoEmitente_C(nfeProc);
                dadosDoEmitente_C02(nfeProc);
                dadosDoEmitente_C05(nfeProc);
                dadosDoDestinatario_E(nfeProc);
                dadosDoDestinatario_E02(nfeProc);
                dadosDoDestinatario_E03(nfeProc);
                dadosDoDestinatario_E05(nfeProc);
                for (int i = 0; i < nfeProc.getNFe().getInfNFe().getDet().size(); i++) {
                    dadosDosProdutosServicos_H(nfeProc, i);
                    dadosDosProdutosServicos_I(nfeProc, i);
                    dadosDosProdutosServicos_N(nfeProc, i);
                    dadosDosProdutosServicos_Impostos(nfeProc, i);
                }
                totaisDaNFe_icmstot(nfeProc);
                dadosDoTransporte_transp(nfeProc);
                dadosDoTransporte_transporta(nfeProc);
                if (nfeProc.getNFe().getInfNFe().getTransp() != null) {
                    for (int i = 0; i < nfeProc.getNFe().getInfNFe().getTransp().getVol().size(); i++) {
                        dadosDoTransporte_transp_vol(nfeProc, i);
                    }
                }

                if (nfeProc.getNFe().getInfNFe().getCobr() != null) {
                    dadosDeCobranca_fat(nfeProc);
                }

                if (nfeProc.getNFe().getInfNFe().getCobr() != null) {
                    if (nfeProc.getNFe().getInfNFe().getCobr().getDup() != null) {
                        for (int i = 0; i < nfeProc.getNFe().getInfNFe().getCobr().getDup().size(); i++) {
                            dadosDeCobranca_cobr_dup(nfeProc, i);
                        }
                    }
                }
                
                InfAdicionais_infAdic(nfeProc);
                InfAdicionais_Pag(nfeProc);
                InfProt_NFe(nfeProc);
                VerificaDadosCertificado_NFe(arquivoXML);
            }
            jProgressBar1.setValue(100);
        } catch (JAXBException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void VersaoNFe_A(TNfeProc nfeProc) {
        gVersao = nfeProc.getNFe().getInfNFe().getVersao();
    }

    /**
     * B - Dados da Nota Fiscal eletrônica.
     */
    public void dadosNfe_ide(TNfeProc nfeProc) throws SQLException {
        try {
            modelo1 = (DefaultTableModel) jTable1.getModel();
            String wStatus = nfeProc.getProtNFe().getInfProt().getCStat();
            String wMsg = nfeProc.getProtNFe().getInfProt().getCMsg();
            String wProt = nfeProc.getProtNFe().getInfProt().getNProt();
            String wDhrecbto = nfeProc.getProtNFe().getInfProt().getDhRecbto();
            String wCUF = nfeProc.getNFe().getInfNFe().getIde().getCUF();
            String wId = nfeProc.getNFe().getInfNFe().getId();
            String wCNF = nfeProc.getNFe().getInfNFe().getIde().getCNF();
            String wNatOp = nfeProc.getNFe().getInfNFe().getIde().getNatOp();
            //String wIndPag = nfeProc.getNFe().getInfNFe().getIde().getIndPag();
            String wMod = nfeProc.getNFe().getInfNFe().getIde().getMod();
            String wSerie = nfeProc.getNFe().getInfNFe().getIde().getSerie();
            String wNNF = nfeProc.getNFe().getInfNFe().getIde().getNNF();
            String wDEmi = nfeProc.getNFe().getInfNFe().getIde().getDhEmi();
            String wDSaiEnt = nfeProc.getNFe().getInfNFe().getIde().getDhSaiEnt();
            String wTpNF = nfeProc.getNFe().getInfNFe().getIde().getTpNF();
            String widDest = nfeProc.getNFe().getInfNFe().getIde().getIdDest();
            String wCMunFG = nfeProc.getNFe().getInfNFe().getIde().getCMunFG();
            String wTpImp = nfeProc.getNFe().getInfNFe().getIde().getTpImp();
            String wTpEmis = nfeProc.getNFe().getInfNFe().getIde().getTpEmis();
            String wCDV = nfeProc.getNFe().getInfNFe().getIde().getCDV();
            String wTpAmb = nfeProc.getNFe().getInfNFe().getIde().getTpAmb();
            String wFinNFe = nfeProc.getNFe().getInfNFe().getIde().getFinNFe();
            String windFinal = nfeProc.getNFe().getInfNFe().getIde().getIndFinal();
            String windPres = nfeProc.getNFe().getInfNFe().getIde().getIndPres();
            String wProcEmi = nfeProc.getNFe().getInfNFe().getIde().getProcEmi();
            String wVerProc = nfeProc.getNFe().getInfNFe().getIde().getVerProc();
            String wCNPJ = nfeProc.getNFe().getInfNFe().getEmit().getCNPJ();
            String wsistCNPJ = System.getProperty("sistCNPJ");
            gNNF = wNNF;
            if (wCNF.trim().length() <= 0) {
                wCNF = null;
            }
            if (wTpAmb.trim() != Env.gettpAMB().toString()) {
                int selection = 0;
                if (Env.gettpAMB().toString().equals("2")) {
                    selection = JOptionPane.showConfirmDialog(this,
                            "Tipo do ambiente da NFe a ser importada diferente do tipo de ambiente do sitema.\n"
                            + "Deseja alterar de Produção para Homologação?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    wTpAmb = "2";
                }
                if (Env.gettpAMB().toString().equals("1")) {
                    selection = JOptionPane.showConfirmDialog(this,
                            "Tipo do ambiente da NFe a ser importada diferente do tipo de ambiente do sitema.\n"
                            + "Deseja alterar de Homologação para Produção?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    wTpAmb = "1";
                }
                if (selection == JOptionPane.NO_OPTION) {
                    modelo1.addRow(new Object[]{
                        "",
                        tArquivoXMLNFe,
                        "",
                        "Rejeitado",
                        "Tipo de Ambiente",
                        "Tipo do ambiente da NFe a ser importada diferente do tipo de ambiente do sitema."
                    });
                    return;
                }
            }

            if (!wCNPJ.trim().equals(System.getProperty("sistCNPJ").trim())) {
                JOptionPane.showMessageDialog(this,
                        "CNPJ Emitente da NFe a ser importada diferente do CNPJ do sitema.\n"
                        + "A NF-e não podera ser importada!!!", "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);

                modelo1.addRow(new Object[]{
                    "",
                    tArquivoXMLNFe,
                    "",
                    "Rejeitado",
                    "CNPJ do Emitente",
                    "CNPJ Emitente da NFe a ser importada diferente do CNPJ do sitema."
                });
                gFlagImport = false;
            }

            if (!gVersao.trim().equals(Env.getVersao())) {
                JOptionPane.showMessageDialog(this,
                        "Versão da NFe a ser importada diferente da Versão 4.00\n"
                        + "A NF-e não podera ser importada!!!", "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);

                modelo1.addRow(new Object[]{
                    "",
                    tArquivoXMLNFe,
                    "",
                    "Rejeitado",
                    "Versão",
                    "Versão da NFe a ser importada diferente da Versão 4.00."
                });
                gFlagImport = false;
            }

            if (gFlagImport == false) {
                return;
            }
            boolean wFlagInclusao = false;
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(gNNF.trim());
            if (!jsonNFe.isEmpty()) {
                wFlagInclusao = true;
            }
            boolean wFlagAlteracao = false;
            if (wFlagInclusao == true) {
                int selection = JOptionPane.showConfirmDialog(this,
                        "Nota Fiscal já cadastrada.\n"
                        + "Deseja sobrescrever?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    wFlagAlteracao = true;
                } else {
                    modelo1.addRow(new Object[]{
                        "",
                        tArquivoXMLNFe,
                        "",
                        "Rejeitado",
                        "",
                        "Nota Fiscal já cadastrada."
                    });
                    gFlagImport = false;
                    return;
                }
            }
            if (wFlagInclusao == false) {
                NfeController.Create(gNNF.trim());
            }
            if (wFlagInclusao == false || wFlagAlteracao == true) {
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setInfnfe_statusnfe("Em Digitação");
                eNFE.setInfnfe_versao(gVersao.trim());
                eNFE.setInfnfe_id("");

                eNFE.setInfnfe_pedidovenda(null);
                eNFE.setInfnfe_danfeimpresso("Não");
                eNFE.setInfnfe_xmlexportado("Não");
                eNFE.setIde_cuf(wCUF);
                eNFE.setIde_xuf("");
                eNFE.setIde_cnf(wCNF);
                eNFE.setIde_natop(wNatOp);
                eNFE.setIde_mod(wMod);
                eNFE.setIde_serie(wSerie);
                eNFE.setIde_nnf(wNNF);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date parsedDate = dateFormat.parse(wDEmi.replace("T", " ").replace("-03:00", ""));
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                eNFE.setIde_dhemi(wDEmi.replace("T", " ").replace("-03:00", ""));
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                parsedDate = dateFormat.parse(wDSaiEnt.replace("T", " ").replace("-03:00", ""));
                timestamp = new java.sql.Timestamp(parsedDate.getTime());
                eNFE.setIde_dhsaient(wDSaiEnt.replace("T", " ").replace("-03:00", ""));

                if (wStatus.equals("100")) {
                    eNFE.setInfnfe_statusnfe("Autorizada");
                    eNFE.setInfnfe_id(wId);
                    eNFE.setInfprot_nprot(wProt);
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    parsedDate = dateFormat.parse(wDhrecbto.replace("T", " ").replace("-03:00", ""));
                    timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    eNFE.setInfprot_dhrecbto(wDhrecbto.replace("T", " ").replace("-03:00", ""));
                }

                eNFE.setIde_tpnf(wTpNF);
                eNFE.setIde_iddest(widDest);
                eNFE.setIde_cmunfg(wCMunFG);
                eNFE.setIde_xmunfg("");
                eNFE.setIde_tpimp(wTpImp);
                eNFE.setIde_tpemis(wTpEmis);
                eNFE.setIde_cdv(wCDV);
                eNFE.setIde_tpamb(wTpAmb);
                eNFE.setIde_finnfe(wFinNFe);
                eNFE.setIde_indfinal(windFinal);
                eNFE.setIde_indpres(windPres);
                eNFE.setIde_procemi(wProcEmi);
                eNFE.setIde_verproc(wVerProc);
                eNFE.setIde_dhcont(null);
                eNFE.setIde_xjust("");
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            }
            if (wFlagInclusao == false && wFlagAlteracao == false) {
                modelo1.addRow(new Object[]{
                    "",
                    tArquivoXMLNFe,
                    "",
                    "Inserido",
                    "",
                    "Nota Fiscal inserida com sucesso no sistema."
                });
            }
            if (wFlagAlteracao == true) {
                modelo1.addRow(new Object[]{
                    "",
                    tArquivoXMLNFe,
                    "",
                    "Alterado",
                    "",
                    "Nota Fiscal já cadastrada."
                });
                NfeDetProdController.Delete(gNNF.trim());
                NfeCobrDupController.Delete(gNNF.trim());
                NfeTranspVolController.Delete(gNNF.trim());
                NfeInfProtController.Delete(gNNF.trim());
                NfeInformacoesPagamentoController.Delete(gNNF.trim());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    /**
     * C - Identificação do Emitente da Nota Fiscal eletrônica
     */
    private void dadosDoEmitente_C(TNfeProc nfeProc) {
        try {
            String wXNome = nfeProc.getNFe().getInfNFe().getEmit().getXNome();
            String wXFant = nfeProc.getNFe().getInfNFe().getEmit().getXFant();
            String wIE = nfeProc.getNFe().getInfNFe().getEmit().getIE();
            String wIEST = nfeProc.getNFe().getInfNFe().getEmit().getIEST();
            String wIM = nfeProc.getNFe().getInfNFe().getEmit().getIM();
            String wCNAE = nfeProc.getNFe().getInfNFe().getEmit().getCNAE();
            String wCRT = nfeProc.getNFe().getInfNFe().getEmit().getCRT();
            gCRT = wCRT;
            if (wIM == null) {
                wIM = "";
            }
            if (wCNAE == null) {
                wCNAE = "";
            }
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setEmit_xnome(wXNome);
            eNFE.setEmit_xfant(wXFant);
            eNFE.setEnderemit_ie(wIE);
            eNFE.setEnderemit_iest(wIEST);
            eNFE.setEnderemit_im(wIM);
            eNFE.setEnderemit_cnae(wCNAE);
            eNFE.setEnderemit_crt(wCRT);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void dadosDoEmitente_C02(TNfeProc nfeProc) {
        try {
            String wCNPJ = nfeProc.getNFe().getInfNFe().getEmit().getCNPJ();

            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setEmit_cnpj(wCNPJ);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void dadosDoEmitente_C05(TNfeProc nfeProc) {
        try {
            String wXLgr = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr();
            String wNro = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro();
            String wXCpl = "";
            if (nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl() != null) {
                wXCpl = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl();
            }
            String wXBairro = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro();
            String wCMun = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCMun();
            String wXMun = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun();
            String wUF = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF().value();
            String wCEP = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCEP();
            String wCPais = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCPais();
            String wXPais = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXPais();
            String wFone = nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getFone();
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setEnderemit_xlgr(wXLgr);
            eNFE.setEnderemit_nro(wNro);
            eNFE.setEnderemit_xcpl(wXCpl);
            eNFE.setEnderemit_xbairro(wXBairro);
            eNFE.setEnderemit_cmun(wCMun);
            eNFE.setEnderemit_xmun(wXMun.replace("'", " "));
            eNFE.setEnderemit_uf(wUF);
            eNFE.setEnderemit_cep(wCEP);
            eNFE.setEnderemit_cpais(wCPais);
            eNFE.setEnderemit_xpais(wXPais);
            eNFE.setEnderemit_fone(wFone);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    /**
     * E - Identificação do Destinatário da Nota Fiscal eletrônica
     */
    private void dadosDoDestinatario_E(TNfeProc nfeProc) {
        try {
            String wXNome = nfeProc.getNFe().getInfNFe().getDest().getXNome();
            String windIEDest = nfeProc.getNFe().getInfNFe().getDest().getIndIEDest();
            String wIE = nfeProc.getNFe().getInfNFe().getDest().getIE();
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setDest_xnome(wXNome);
            eNFE.setEnderdest_indiedest(windIEDest);
            eNFE.setEnderdest_ie(wIE);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void dadosDoDestinatario_E02(TNfeProc nfeProc) {
        try {
            String wCNPJ = nfeProc.getNFe().getInfNFe().getDest().getCNPJ();

            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setDest_cnpj(wCNPJ);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void dadosDoDestinatario_E03(TNfeProc nfeProc) {
        String wCPF = nfeProc.getNFe().getInfNFe().getDest().getCPF();
    }

    private void dadosDoDestinatario_E05(TNfeProc nfeProc) {
        try {
            String wXLgr = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXLgr();
            String wNro = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getNro();
            String wXCpl = "";
            if (nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXCpl() != null) {
                wXCpl = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXCpl();
            }
            String wXBairro = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXBairro();
            String wCMun = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getCMun();
            String wXMun = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXMun();
            String wUF = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getUF().value();
            String wCEP = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getCEP();
            String wCPais = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getCPais();
            String wXPais = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getXPais();
            String wFone = nfeProc.getNFe().getInfNFe().getDest().getEnderDest().getFone();
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setEnderdest_xlgr(wXLgr);
            eNFE.setEnderdest_nro(wNro);
            eNFE.setEnderdest_xcpl(wXCpl);
            eNFE.setEnderdest_xbairro(wXBairro);
            eNFE.setEnderdest_cmun(wCMun);
            eNFE.setEnderdest_xmun(wXMun.replace("'", " "));
            eNFE.setEnderdest_uf(wUF);
            eNFE.setEnderdest_cep(wCEP);
            eNFE.setEnderdest_cpais(wCPais);
            eNFE.setEnderdest_xpais(wXPais);
            eNFE.setEnderdest_fone(wFone);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    /**
     * I - Produtos e Serviços da NF-e.
     */
    private void dadosDosProdutosServicos_H(TNfeProc nfeProc, int i) {
        String wNItem = nfeProc.getNFe().getInfNFe().getDet().get(i).getNItem();
        gNItem = wNItem;
        if (NfeDetProdController.Create(gNNF.trim(), gNItem.trim())) {
            System.out.println("Detalhe dos produtos inserido com sucesso!");
        } else {
            System.out.println(System.getProperty("MsgInvalid"));
        }
    }

    private void dadosDosProdutosServicos_I(TNfeProc nfeProc, int i) {
        try {
            String wCProd = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getCProd();
            String wCEAN = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getCEAN();
            String wXProd = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getXProd();
            String wNCM = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getNCM();
            String wEXTIPI = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getEXTIPI();
            String wCFOP = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getCFOP();
            String wUCom = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getUCom();
            String wQCom = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getQCom();
            String wVUnCom = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVUnCom();
            String wVProd = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVProd();
            String wCEANTrib = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getCEANTrib();
            String wUTrib = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getUTrib();
            String wQTrib = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getQTrib();
            String wVUnTrib = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVUnTrib();
            String wVFrete = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVFrete();
            String wVSeg = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVSeg();
            String wVDesc = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVDesc();
            String wVOutro = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getVOutro();
            String wIndTot = nfeProc.getNFe().getInfNFe().getDet().get(i).getProd().getIndTot();
            if (wCEAN.trim().length() <= 0) {
                wCEAN = null;
            }
            if (wCEANTrib.trim().length() <= 0) {
                wCEANTrib = null;
            }
            NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
            eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
            eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
            eNFE_DET_PROD.setDet_prod_cprod(wCProd);
            eNFE_DET_PROD.setDet_prod_xprod(wXProd);
            eNFE_DET_PROD.setDet_prod_cean(wCEAN);
            eNFE_DET_PROD.setDet_prod_ncm(wNCM);
            eNFE_DET_PROD.setDet_prod_cfop(wCFOP);
            eNFE_DET_PROD.setDet_prod_ucom(wUCom);
            eNFE_DET_PROD.setDet_prod_qcom(wQCom);
            eNFE_DET_PROD.setDet_prod_vuncom(wVUnCom);
            eNFE_DET_PROD.setDet_prod_vprod(wVProd);
            eNFE_DET_PROD.setDet_prod_ceantrib(wCEANTrib);
            eNFE_DET_PROD.setDet_prod_utrib(wUTrib);
            eNFE_DET_PROD.setDet_prod_qtrib(wQTrib);
            eNFE_DET_PROD.setDet_prod_vuntrib(wVUnTrib);
            eNFE_DET_PROD.setDet_prod_vfrete(wVFrete);
            eNFE_DET_PROD.setDet_prod_vseg(wVSeg);
            eNFE_DET_PROD.setDet_prod_vdesc(wVDesc);
            eNFE_DET_PROD.setDet_prod_voutro(wVOutro);
            eNFE_DET_PROD.setDet_prod_indtot(wIndTot);
            if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                System.out.println("Detalhe dos produtos atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    private void dadosDosProdutosServicos_M(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wM = scan.next();
            break;
        }
    }

    private void dadosDosProdutosServicos_N(TNfeProc nfeProc, int i) {
        // String wN = nfeProc.getNFe().getInfNFe().getDet().get(i).getImposto().
    }

    private void dadosDosProdutosServicos_Impostos(TNfeProc nfeProc, int i) {
        for (JAXBElement<?> e : nfeProc.getNFe().getInfNFe().getDet().get(i).getImposto().getContent()) {
            if (e.getValue() instanceof ICMS) {
                System.out.println("e.getValue() " + e.getValue().toString());
                ICMS icms = (ICMS) e.getValue();
                String wOrig = null;
                String wCST = null;
                String gSQL = "";
                System.out.println("icms.getICMS00() " + icms.getICMS00());
                if (icms.getICMS00() != null) {
                    try {
                        wOrig = icms.getICMS00().getOrig();
                        wCST = icms.getICMS00().getCST();
                        String wModBC = icms.getICMS00().getModBC();
                        String wVBC = icms.getICMS00().getVBC();
                        String wPICMS = icms.getICMS00().getPICMS();
                        String wVICMS = icms.getICMS00().getVICMS();

                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms00_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms00_cst(wCST);
                        eNFE_DET_PROD.setDet_icms00_modbc(wModBC);
                        eNFE_DET_PROD.setDet_icms00_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms00_picms(wPICMS);
                        eNFE_DET_PROD.setDet_icms00_vicms(wVICMS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (icms.getICMS10() != null) {
                    try {
                        wOrig = icms.getICMS10().getOrig();
                        wCST = icms.getICMS10().getCST();
                        String wModBC = icms.getICMS10().getModBC();
                        String wVBC = icms.getICMS10().getVBC();
                        String wPICMS = icms.getICMS10().getPICMS();
                        String wVICMS = icms.getICMS10().getVICMS();
                        String wModBCST = icms.getICMS10().getModBCST();
                        String wPMVAST = icms.getICMS10().getPMVAST();
                        String wPRedBCST = icms.getICMS10().getPRedBCST();
                        String wVBCST = icms.getICMS10().getVBCST();
                        String wPICMSST = icms.getICMS10().getPICMSST();
                        String wVICMSST = icms.getICMS10().getVICMSST();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms10_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms10_modbc(wModBC);
                        eNFE_DET_PROD.setDet_icms10_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms10_picms(wPICMS);
                        eNFE_DET_PROD.setDet_icms10_vicms(wVICMS);
                        eNFE_DET_PROD.setDet_icms10_modbcst(wModBCST);
                        eNFE_DET_PROD.setDet_icms10_pmvast(wPMVAST);
                        eNFE_DET_PROD.setDet_icms10_predbcst(wPRedBCST);
                        eNFE_DET_PROD.setDet_icms10_vbcst(wVBCST);
                        eNFE_DET_PROD.setDet_icms10_picmsst(wPICMSST);
                        eNFE_DET_PROD.setDet_icms10_vicmsst(wVICMSST);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (icms.getICMS20() != null) {
                    try {
                        wOrig = icms.getICMS20().getOrig();
                        wCST = icms.getICMS20().getCST();
                        String wModBC = icms.getICMS20().getModBC();
                        String wPRedBC = icms.getICMS20().getPRedBC();
                        String wVBC = icms.getICMS20().getVBC();
                        String wPICMS = icms.getICMS20().getPICMS();
                        String wVICMS = icms.getICMS20().getVICMS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms20_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms20_modbc(wModBC);
                        eNFE_DET_PROD.setDet_icms20_predbc(wPRedBC);
                        eNFE_DET_PROD.setDet_icms20_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms20_picms(wPICMS);
                        eNFE_DET_PROD.setDet_icms20_vicms(wVICMS);
                        eNFE_DET_PROD.setDet_icms20_vicmsdeson(null);
                        eNFE_DET_PROD.setDet_icms20_motdesicms(null);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (icms.getICMS30() != null) {
                    try {
                        wOrig = icms.getICMS30().getOrig();
                        wCST = icms.getICMS30().getCST();
                        String wModBCST = icms.getICMS30().getModBCST();
                        String wPMVAST = icms.getICMS30().getPMVAST();
                        String wPRedBCST = icms.getICMS30().getPRedBCST();
                        String wVBCST = icms.getICMS30().getVBCST();
                        String wPICMSST = icms.getICMS30().getPICMSST();
                        String wVICMSST = icms.getICMS30().getVICMSST();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms30_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms30_modbcst(wModBCST);
                        eNFE_DET_PROD.setDet_icms30_pmvast(wPMVAST);
                        eNFE_DET_PROD.setDet_icms30_vbcst(wVBCST);
                        eNFE_DET_PROD.setDet_icms30_picmsst(wPICMSST);
                        eNFE_DET_PROD.setDet_icms30_vicmsst(wVICMSST);
                        eNFE_DET_PROD.setDet_icms30_vicmsdeson(null);
                        eNFE_DET_PROD.setDet_icms30_motdesicms(null);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }

                if (icms.getICMS40() != null) {
                    try {
                        wOrig = icms.getICMS40().getOrig();
                        wCST = icms.getICMS40().getCST();
                        String wVICMSDeson = icms.getICMS40().getVICMSDeson();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms40_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms40_cst(wCST);
                        //eNFE_DET_PROD.setDet_icms40_motdesicms(wMotDesICMS);
                        eNFE_DET_PROD.setDet_icms40_vicmsdeson(wVICMSDeson);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (icms.getICMS51() != null) {
                    try {
                        wOrig = icms.getICMS51().getOrig();
                        wCST = icms.getICMS51().getCST();
                        String wModBC = icms.getICMS51().getModBC();
                        String wpRedBC = icms.getICMS51().getPRedBC();
                        String wVBC = icms.getICMS51().getVBC();
                        String wpICMS = icms.getICMS51().getPICMS();
                        String wvICMSOp = icms.getICMS51().getVICMSOp();
                        String wpDif = icms.getICMS51().getPDif();
                        String wvICMSDif = icms.getICMS51().getVICMSDif();
                        String wvICMS = icms.getICMS51().getVICMS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms51_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms51_cst(wCST);
                        eNFE_DET_PROD.setDet_icms51_predbc(wpRedBC);
                        eNFE_DET_PROD.setDet_icms51_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms51_picms(wpICMS);
                        eNFE_DET_PROD.setDet_icms51_pdif(wpDif);
                        eNFE_DET_PROD.setDet_icms51_vicmsdif(wvICMSDif);
                        eNFE_DET_PROD.setDet_icms51_vicms(wvICMS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }

                if (icms.getICMS60() != null) {
                    try {
                        wOrig = icms.getICMS60().getOrig();
                        wCST = icms.getICMS60().getCST();
                        String wvBCSTRet = icms.getICMS60().getVBCSTRet();
                        String wvICMSSTRet = icms.getICMS60().getVICMSSTRet();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms60_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms60_cst(wCST);
                        eNFE_DET_PROD.setDet_icms60_vbcstret(wvBCSTRet);
                        eNFE_DET_PROD.setDet_icms60_vicmsstret(wvICMSSTRet);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }

                if (icms.getICMS70() != null) {
                    try {
                        wOrig = icms.getICMS70().getOrig();
                        wCST = icms.getICMS70().getCST();
                        String wModBC = icms.getICMS70().getModBC();
                        String wpRedBC = icms.getICMS70().getPRedBC();
                        String wVBC = icms.getICMS70().getVBC();
                        String wPICMS = icms.getICMS70().getPICMS();
                        String wVICMS = icms.getICMS70().getVICMS();
                        String wModBCST = icms.getICMS70().getModBCST();
                        String wPMVAST = icms.getICMS70().getPMVAST();
                        String wpRedBCST = icms.getICMS70().getPRedBCST();
                        String wVBCST = icms.getICMS70().getVBCST();
                        String wPICMSST = icms.getICMS70().getPICMSST();
                        String wVICMSST = icms.getICMS70().getVICMSST();
                        String wvICMSDeson = icms.getICMS70().getVICMSDeson();
                        String wmotDesICMS = icms.getICMS70().getMotDesICMS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms70_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms70_modbc(wModBC);
                        eNFE_DET_PROD.setDet_icms70_predbc(wpRedBC);
                        eNFE_DET_PROD.setDet_icms70_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms70_vicms(wVICMS);
                        eNFE_DET_PROD.setDet_icms70_modbcst(wModBCST);
                        eNFE_DET_PROD.setDet_icms70_pmvast(wPMVAST);
                        eNFE_DET_PROD.setDet_icms70_predbcst(wpRedBCST);
                        eNFE_DET_PROD.setDet_icms70_vbcst(wVBCST);
                        eNFE_DET_PROD.setDet_icms70_picmsst(wPICMSST);
                        eNFE_DET_PROD.setDet_icms70_vicmsst(wVICMSST);
                        eNFE_DET_PROD.setDet_icms70_vicmsdeson(wvICMSDeson);
                        eNFE_DET_PROD.setDet_icms70_motdesicms(wmotDesICMS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }

                if (icms.getICMS90() != null) {
                    try {
                        wOrig = icms.getICMS90().getOrig();
                        wCST = icms.getICMS90().getCST();
                        String wModBC = icms.getICMS90().getModBC();
                        String wpRedBC = icms.getICMS90().getPRedBC();
                        String wVBC = icms.getICMS90().getVBC();
                        String wPICMS = icms.getICMS90().getPICMS();
                        String wVICMS = icms.getICMS90().getVICMS();
                        String wModBCST = icms.getICMS90().getModBCST();
                        String wPMVAST = icms.getICMS90().getPMVAST();
                        String wpRedBCST = icms.getICMS90().getPRedBCST();
                        String wVBCST = icms.getICMS90().getVBCST();
                        String wPICMSST = icms.getICMS90().getPICMSST();
                        String wVICMSST = icms.getICMS90().getVICMSST();
                        String wvICMSDeson = icms.getICMS90().getVICMSDeson();
                        String wmotDesICMS = icms.getICMS90().getMotDesICMS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms90_orig(wOrig);
                        eNFE_DET_PROD.setDet_icms90_cst(wCST);
                        eNFE_DET_PROD.setDet_icms90_predbc(wpRedBC);
                        eNFE_DET_PROD.setDet_icms90_vbc(wVBC);
                        eNFE_DET_PROD.setDet_icms90_picms(wPICMS);
                        eNFE_DET_PROD.setDet_icms90_vicms(wVICMS);
                        eNFE_DET_PROD.setDet_icms90_modbcst(wModBCST);
                        eNFE_DET_PROD.setDet_icms90_pmvast(wPMVAST);
                        eNFE_DET_PROD.setDet_icms90_predbcst(wpRedBCST);
                        eNFE_DET_PROD.setDet_icms90_vbcst(wVBCST);
                        eNFE_DET_PROD.setDet_icms90_picmsst(wPICMSST);
                        eNFE_DET_PROD.setDet_icms90_vicmsst(wVICMSST);
                        eNFE_DET_PROD.setDet_icms90_vicmsdeson(wvICMSDeson);
                        eNFE_DET_PROD.setDet_icms90_motdesicms(wmotDesICMS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }

                if (gCRT != null && wCST != null && wOrig != null) {

                    try {
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_icms_crt(gCRT);
                        eNFE_DET_PROD.setDet_icms_cst(wCST);
                        eNFE_DET_PROD.setDet_icms_orig(wOrig);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
            }
            if (e.getValue() instanceof TIpi) {
                TIpi ipi = (TIpi) e.getValue();
                if (ipi.getCEnq() != null) {
                    try {
                        String wCNPJProd = ipi.getCNPJProd();
                        String wCSelo = ipi.getCSelo();
                        String wQSelo = ipi.getQSelo();
                        String wCEnq = ipi.getCEnq();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_ipi_cenq(wCEnq);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                        System.out.println("ipi.getIPITrib().getCST() :" + ipi.getIPITrib());
                        if (ipi.getIPITrib() != null) {
                            String wCST = ipi.getIPITrib().getCST();
                            System.out.println("Aqui..: " + wCST);
                            String wVBC = ipi.getIPITrib().getVBC();
                            String wPIPI = ipi.getIPITrib().getPIPI();
                            String wVIPI = ipi.getIPITrib().getVIPI();
                            eNFE_DET_PROD = new NFE_DET_PROD();
                            eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                            eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                            eNFE_DET_PROD.setDet_ipitrib_cst(wCST);
                            eNFE_DET_PROD.setDet_ipitrib_vbc(wVBC);
                            eNFE_DET_PROD.setDet_ipitrib_pipi(wPIPI);
                            eNFE_DET_PROD.setDet_ipitrib_vipi(wVIPI);
                            if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                                System.out.println("Detalhe dos produtos atualizado com sucesso!");
                            } else {
                                System.out.println(System.getProperty("MsgInvalid"));
                            }
                        }
                        System.out.println("ipi.getIPINT().getCST() :" + ipi.getIPINT());
                        if (ipi.getIPINT() != null) {
                            String wCST = ipi.getIPINT().getCST();
                            eNFE_DET_PROD = new NFE_DET_PROD();
                            eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                            eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                            eNFE_DET_PROD.setDet_ipitrib_cst(wCST);
                            if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                                System.out.println("Detalhe dos produtos atualizado com sucesso!");
                            } else {
                                System.out.println(System.getProperty("MsgInvalid"));
                            }
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
            }

            if (e.getValue() instanceof PIS) {
                PIS pis = (PIS) e.getValue();
                if (pis.getPISNT() != null) {
                    try {
                        String wCST = pis.getPISNT().getCST();

                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_pisaliq_cst(wCST);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (pis.getPISAliq() != null) {
                    try {
                        String wCST = pis.getPISAliq().getCST();
                        String wVBC = pis.getPISAliq().getVBC();
                        String wPPIS = pis.getPISAliq().getPPIS();
                        String wVPIS = pis.getPISAliq().getVPIS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_pisaliq_cst(wCST);
                        eNFE_DET_PROD.setDet_pisaliq_vbc(wVBC);
                        eNFE_DET_PROD.setDet_pisaliq_ppis(wPPIS);
                        eNFE_DET_PROD.setDet_pisaliq_vpis(wVPIS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (pis.getPISOutr() != null) {
                    try {
                        String wCST = pis.getPISOutr().getCST();
                        String wVBC = pis.getPISOutr().getVBC();
                        String wPPIS = pis.getPISOutr().getPPIS();
                        String wVPIS = pis.getPISOutr().getVPIS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_pisoutr_cst(wCST);
                        eNFE_DET_PROD.setDet_pisoutr_vbc(wVBC);
                        eNFE_DET_PROD.setDet_pisoutr_ppis(wPPIS);
                        eNFE_DET_PROD.setDet_pisoutr_vpis(wVPIS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
            }

            if (e.getValue() instanceof COFINS) {
                COFINS cofins = (COFINS) e.getValue();
                if (cofins.getCOFINSNT() != null) {
                    try {
                        String wCSTNT = cofins.getCOFINSNT().getCST();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_cofinsaliq_cst(wCSTNT);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (cofins.getCOFINSAliq() != null) {
                    try {
                        String wCST = cofins.getCOFINSAliq().getCST();
                        String wVBC = cofins.getCOFINSAliq().getVBC();
                        String wPCOFINS = cofins.getCOFINSAliq().getPCOFINS();
                        String wVCOFINS = cofins.getCOFINSAliq().getVCOFINS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_cofinsaliq_cst(wCST);
                        eNFE_DET_PROD.setDet_cofinsaliq_vbc(wVBC);
                        eNFE_DET_PROD.setDet_cofinsaliq_pcofins(wPCOFINS);
                        eNFE_DET_PROD.setDet_cofinsaliq_vcofins(wVCOFINS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
                if (cofins.getCOFINSOutr() != null) {
                    try {
                        String wCST = cofins.getCOFINSOutr().getCST();
                        String wVBC = cofins.getCOFINSOutr().getPCOFINS();
                        String wPCOFINS = cofins.getCOFINSOutr().getPCOFINS();
                        String wVCOFINS = cofins.getCOFINSOutr().getVCOFINS();
                        NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                        eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                        eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                        eNFE_DET_PROD.setDet_cofinsaliq_cst(wCST);
                        eNFE_DET_PROD.setDet_cofinsaliq_vbc(wVBC);
                        eNFE_DET_PROD.setDet_cofinsaliq_pcofins(wPCOFINS);
                        eNFE_DET_PROD.setDet_cofinsaliq_vcofins(wVCOFINS);
                        if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                            System.out.println("Detalhe dos produtos atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    }
                }
            }
        }

    }

    private void totaisDaNFe_icmstot(TNfeProc nfeProc) {
        if (nfeProc.getNFe().getInfNFe().getTotal().getICMSTot() != null) {
            try {
                String wVBC = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVBC();
                String wVICMS = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVICMS();
                String wvICMSDeson = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVICMSDeson();
                String wVBCST = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVBCST();
                String wVST = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVST();
                String wVProd = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVProd();
                String wVFrete = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVFrete();
                String wVSeg = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVSeg();
                String wVDesc = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVDesc();
                String wVII = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVII();
                String wVIPI = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVIPI();
                String wVPIS = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVPIS();
                String wVCOFINS = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVCOFINS();
                String wVOutro = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVOutro();
                String wVNF = nfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF();
                String wicmstot_vtottrib = "0.00";
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setIcmstot_vbc(wVBC);
                eNFE.setIcmstot_vicms(wVICMS);
                eNFE.setIcmstot_vicmsdeson(wvICMSDeson);
                eNFE.setIcmstot_vbcst(wVBCST);
                eNFE.setIcmstot_vst(wVST);
                eNFE.setIcmstot_vprod(wVProd);
                eNFE.setIcmstot_vfrete(wVFrete);
                eNFE.setIcmstot_vseg(wVSeg);
                eNFE.setIcmstot_vdesc(wVDesc);
                eNFE.setIcmstot_vii(wVII);
                eNFE.setIcmstot_vipi(wVIPI);
                eNFE.setIcmstot_vpis(wVPIS);
                eNFE.setIcmstot_vcofins(wVCOFINS);
                eNFE.setIcmstot_voutro(wVOutro);
                eNFE.setIcmstot_vnf(wVNF);
                eNFE.setIcmstot_vtottrib(wicmstot_vtottrib);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_transp(TNfeProc nfeProc) {
        if (nfeProc.getNFe().getInfNFe().getTransp() != null) {
            try {
                String wModFrete = nfeProc.getNFe().getInfNFe().getTransp().getModFrete();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setTransp_modfrete(wModFrete);
                if (NfeController.Update(eNFE)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_transporta(TNfeProc nfeProc) {
        if (nfeProc.getNFe().getInfNFe().getTransp().getTransporta() != null) {
            try {
                String wCNPJ = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getCNPJ();
                String wXNome = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getXNome();
                String wIE = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getIE();
                String wXEnder = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getXEnder();
                String wXMun = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getXMun();
                String wUF = nfeProc.getNFe().getInfNFe().getTransp().getTransporta().getUF().value();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setTransporta_xnome(wXNome);
                eNFE.setTransporta_ie(wIE);
                eNFE.setTransporta_xender(wXEnder);
                eNFE.setTransporta_xmun(wXMun);
                eNFE.setTransporta_uf(wUF);
                if (NfeController.Update(eNFE)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_transp_vol(TNfeProc nfeProc, int i) {
        if (nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i) != null) {
            try {
                String wQVol = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getQVol();
                String wEsp = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getEsp();
                String wMarca = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getMarca();
                String wNVol = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getNVol();
                String wPesoL = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getPesoL();
                String wPesoB = nfeProc.getNFe().getInfNFe().getTransp().getVol().get(i).getPesoB();
                if (wEsp == null) {
                    wEsp = "";
                }
                if (wMarca == null) {
                    wMarca = "";
                }
                NfeTranspVolController.Create(gNNF.trim());
                NFE_TRANSP_VOL eNFE_TRANSP_VOL = new NFE_TRANSP_VOL();
                eNFE_TRANSP_VOL.setTransp_vol_nnf(gNNF.trim());
                eNFE_TRANSP_VOL.setTransp_vol_item("1");
                eNFE_TRANSP_VOL.setTransp_vol_qvol(wQVol);
                eNFE_TRANSP_VOL.setTransp_vol_esp(wEsp);
                eNFE_TRANSP_VOL.setTransp_vol_marca(wMarca);
                eNFE_TRANSP_VOL.setTransp_vol_nvol(wNVol);
                eNFE_TRANSP_VOL.setTransp_vol_pesol(wPesoL);
                eNFE_TRANSP_VOL.setTransp_vol_pesob(wPesoB);
                if (NfeTranspVolController.Update(eNFE_TRANSP_VOL)) {
                    System.out.println("Transporte atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDeCobranca_fat(TNfeProc nfeProc) {
        if (nfeProc.getNFe().getInfNFe().getCobr().getFat() != null) {
            try {
                String wNFat = nfeProc.getNFe().getInfNFe().getCobr().getFat().getNFat();
                String wVOrig = nfeProc.getNFe().getInfNFe().getCobr().getFat().getVOrig();
                String wVDesc = nfeProc.getNFe().getInfNFe().getCobr().getFat().getVDesc();
                String wVLiq = nfeProc.getNFe().getInfNFe().getCobr().getFat().getVLiq();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setFat_nfat(wNFat);
                eNFE.setFat_vorig(wVOrig);
                eNFE.setFat_vdesc(wVDesc);
                eNFE.setFat_vliq(wVLiq);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDeCobranca_cobr_dup(TNfeProc nfeProc, int i) {
        if (nfeProc.getNFe().getInfNFe().getCobr().getDup().get(i) != null) {
            try {
                String wNDup = nfeProc.getNFe().getInfNFe().getCobr().getDup().get(i).getNDup();
                String wDVenc = nfeProc.getNFe().getInfNFe().getCobr().getDup().get(i).getDVenc();
                String wVDup = nfeProc.getNFe().getInfNFe().getCobr().getDup().get(i).getVDup();
                if (wVDup == null || wVDup.length() <= 0) {
                    wVDup = "0.0";
                }
                NfeCobrDupController.Create(gNNF.trim(), Integer.toString(i));
                NFE_COBR_DUP eNFE_COBR_DUP = new NFE_COBR_DUP();
                eNFE_COBR_DUP.setCobr_dup_nnf(gNNF.trim());
                eNFE_COBR_DUP.setCobr_dup_ndup(wNDup);
                eNFE_COBR_DUP.setCobr_dup_item(Integer.toString(i));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(wDVenc.replace("T", " ").replace("-03:00", ""));
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                eNFE_COBR_DUP.setCobr_dup_dvenc(wDVenc.replace("T", " ").replace("-03:00", ""));
                eNFE_COBR_DUP.setCobr_dup_vdup(wVDup);
                if (NfeCobrDupController.Update(eNFE_COBR_DUP)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (ParseException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void InfAdicionais_infAdic(TNfeProc nfeProc) {

        if (nfeProc.getNFe().getInfNFe().getInfAdic() != null) {
            try {
                String winfAdFisco = nfeProc.getNFe().getInfNFe().getInfAdic().getInfAdFisco();
                String winfCpl = nfeProc.getNFe().getInfNFe().getInfAdic().getInfCpl();
                if (winfAdFisco == null) {
                    winfAdFisco = "";
                }
                if (winfCpl == null) {
                    winfCpl = "";
                }
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setInfadic_infadfisco(winfAdFisco);
                eNFE.setInfadic_infcpl(winfCpl);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }

    }

    private void InfAdicionais_Pag(TNfeProc nfeProc) {
        if (nfeProc.getNFe().getInfNFe().getPag() != null) {
            for (int i = 0; i < nfeProc.getNFe().getInfNFe().getPag().getDetPag().size(); i++) {
                try {
                    String windPag = nfeProc.getNFe().getInfNFe().getPag().getDetPag().get(i).getIndPag();
                    String wtpag = nfeProc.getNFe().getInfNFe().getPag().getDetPag().get(i).getTPag();
                    String wvPag = nfeProc.getNFe().getInfNFe().getPag().getDetPag().get(i).getVPag();
                    if (windPag == null) {
                        windPag = "";
                    }
                    if (wtpag == null) {
                        wtpag = "";
                    }
                    if (wvPag == null) {
                        wvPag = "0.00";
                    }
                    NfeInformacoesPagamentoController.Create(gNNF.trim(), "1");
                    NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO = new NFE_INFORMACOESPAGAMENTO();
                    eNFE_INFORMACOESPAGAMENTO.setPag_nnf(gNNF.trim());
                    eNFE_INFORMACOESPAGAMENTO.setPag_item("1");
                    eNFE_INFORMACOESPAGAMENTO.setPag_indpag(windPag);
                    eNFE_INFORMACOESPAGAMENTO.setPag_tpag(wtpag);
                    eNFE_INFORMACOESPAGAMENTO.setPag_vpag(wvPag);
                    if (NfeInformacoesPagamentoController.Update(eNFE_INFORMACOESPAGAMENTO)) {
                        System.out.println("Transporte atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    break;
                } catch (InstantiationException ex) {
                    Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                }

            }
        }
    }

    private void InfProt_NFe(TNfeProc nfeProc) {
        if (nfeProc.getProtNFe().getInfProt().getCStat() != null) {
            try {
                String wVersao = nfeProc.getProtNFe().getVersao();
                String wCStat = nfeProc.getProtNFe().getInfProt().getCStat();
                String wChNFe = nfeProc.getProtNFe().getInfProt().getChNFe();
                String wDhRecbto = nfeProc.getProtNFe().getInfProt().getDhRecbto();
                String wId = nfeProc.getProtNFe().getInfProt().getId();
                String wNProt = nfeProc.getProtNFe().getInfProt().getNProt();
                String wTpAmb = nfeProc.getProtNFe().getInfProt().getTpAmb();
                String wVerAplic = nfeProc.getProtNFe().getInfProt().getVerAplic();
                String wXMotivo = nfeProc.getProtNFe().getInfProt().getXMotivo();
                byte[] wbDigVal = nfeProc.getProtNFe().getInfProt().getDigVal();
                String wsDigVal = new String(wbDigVal, "ISO-8859-1");
                if (wVersao == null) {
                    wVersao = "";
                }
                if (wId == null) {
                    wId = "";
                }
                if (wXMotivo == null) {
                    wXMotivo = "";
                }
                if (wVerAplic == null) {
                    wVerAplic = "";
                }
                NfeInfProtController.Create(gNNF.trim(), wCStat);
                NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
                eNFE_INFPROT.setProtnfe_nnf(gNNF.trim());
                eNFE_INFPROT.setProtnfe_versao(wVersao);
                eNFE_INFPROT.setProtnfe_sequencia("1");
                eNFE_INFPROT.setInfprot_cstat(wCStat);
                eNFE_INFPROT.setInfprot_chnfe(wChNFe);
                eNFE_INFPROT.setInfprot_dhrecbto(wDhRecbto.replace("T", " ").replace("-03:00", ""));
                eNFE_INFPROT.setInfprot_nid(wId);
                eNFE_INFPROT.setInfprot_nprot(wNProt);
                eNFE_INFPROT.setInfprot_tpamb(wTpAmb);
                eNFE_INFPROT.setInfprot_veraplic(wVerAplic);
                eNFE_INFPROT.setInfprot_xmotivo(wXMotivo.replace("Autorizado o uso da NF-e", "Autorização de Uso"));
                NfeInfProtController.Update(eNFE_INFPROT);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void VerificaDadosCertificado_NFe(String arquivoXML) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(arquivoXML));
            NodeList nl = doc.getElementsByTagName("X509Certificate");
            if (nl.getLength() == 0) {
                throw new Exception("Não foi possível encontrar o elemente Signature");
            }
            String certPart = nl.item(0).getFirstChild().getNodeValue();
            byte[] bencoded = javax.xml.bind.DatatypeConverter.parseBase64Binary(certPart);
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            InputStream in = new ByteArrayInputStream(bencoded);
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(in);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String wEmissor = cert.getIssuerDN().getName();
            String wAssunto = cert.getSubjectDN().getName();
            String wDataInicial = dateFormat.format(cert.getNotBefore());
            String wDataFinal = dateFormat.format(cert.getNotAfter());
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(wDataInicial);
            Timestamp timestampwDataInicial = new java.sql.Timestamp(parsedDate.getTime());
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parsedDate = dateFormat.parse(wDataFinal);
            Timestamp timestampwDataFinal = new java.sql.Timestamp(parsedDate.getTime());
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNNF.trim());
            eNFE.setSignature_emissor(cert.getIssuerDN().getName());
            eNFE.setSignature_assunto(wAssunto);
            eNFE.setSignature_datainicial(wDataInicial);
            eNFE.setSignature_datafinal(wDataFinal);
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            modelo1.addRow(new Object[]{
                "",
                gNNF,
                "Erro",
                line
            });
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

            try {
                Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                jDesktopPane2.setCursor(cursor);
                jProgressBar1.setIndeterminate(true);
                jLabel1.setVisible(true);
                jLabel2.setVisible(true);
                arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
                importarNfeXML(tCaminhoArquivo + "\\" + tArquivoXMLNFe);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(100);
                cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
            } catch (SQLException ex) {
                Logger.getLogger(ImportaArquivoXML.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }
}
