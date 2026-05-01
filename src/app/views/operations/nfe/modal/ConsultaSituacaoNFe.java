package app.views.operations.nfe.modal;

import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;
import app.views.operations.nfe.NFe;
import app.views.operations.nfe.util.GeraChaveAcesso;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TretEvento;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.CartaCorrecaoUtil;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.criaNfeProc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.bind.JAXBException;
import lib.ConfiguracoesNfeIniciais;
import lib.MontaNfe;
import org.json.JSONObject;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.RemoveCharacterSpecials;
import util.formata;

public class ConsultaSituacaoNFe extends javax.swing.JInternalFrame {

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
    DefaultTableModel modelo1 = new DefaultTableModel();

    public ConsultaSituacaoNFe(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wAno,
            String wSerie,
            String wNFe,
            ArrayList<String> wListaNNF,
            int[] SelectedRows) throws SQLException, ParseException {
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
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(wCol3);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(735);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(wCol3);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
        JSONObject jsonNFe = new JSONObject();
        jsonNFe = NfeController.Index(gNNF.trim());
        SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (!jsonNFe.isEmpty() && jsonNFe.getString("infnfe_id") != null) {
            try {
                //tf_chave.setText(jsonNFe.getString("infnfe_id").trim().replace("NFe", ""));
                tf_chave.setText(GeraChaveAcesso.IdNFe(gNNF.trim(), jsonNFe).trim().replace("NFe", ""));
            } catch (NfeException ex) {
                Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificadoException ex) {
                Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            String text = table.getValueAt(row, 4).toString();
            if (text.contains("135-")
                    || text.contains("128-")
                    || text.contains("101-")
                    || text.contains("100-")
                    || text.contains("110-")
                    || text.contains("132-")
                    || text.contains("155-")) {
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
            // ImageIcon d = (ImageIcon) value;
            ImageIcon imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            // certifique-se da existencia da imagem "icon.gif" antes de executar
            if ("100".equals(System.getProperty("gCStat").toString().trim())
                    || "103".equals(System.getProperty("gCStat").toString().trim())) {
                //imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/alerta.jpg"));
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
        jLabel47 = new javax.swing.JLabel();
        tf_chave = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("Consulta Nota Fiscal Eletrônica na SEFAZ - Revisão: 08 - Data da Última Revisão: 20/02/2024 - Data da Elaboração: 26/07/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta Situação da Nota Fiscal Eletrônica na SEFAZ ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo das Operações:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setText("Consulta");
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

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Confirme ou Digite uma nova chave de acesso:");

        tf_chave.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_chave.setToolTipText("");
        tf_chave.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_chave.setDoubleBuffered(true);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 51, 51));
        jLabel48.setText("Atenção:  Evite consultar a mesma chave de acesso mais de 3 vezes em um período de 60 minutos.");

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_chave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel48, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane2Layout.createSequentialGroup()
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane2Layout.createSequentialGroup()
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_chave, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8)
                                        .addComponent(jButton1)))))
                        .addGap(4, 4, 4))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_chave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
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
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(836, Short.MAX_VALUE)
                        .addComponent(botao_sair))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDesktopPane2)))
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
                .addGap(4, 4, 4)
                .addComponent(botao_sair)
                .addContainerGap())
        );

        setBounds(0, 0, 923, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tf_chave.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(this, "<HTML><FONT FACE=Arial COLOR=\"#AA0000\">Número de documento não informado, Verifique!!!</FONT></HTML>", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Process process = new Process();
        process.start();
        jLabel1.setText("");
        jLabel2.setText("");
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
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tf_chave;
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

    private void ConsultarNfeSEFAZ(String nNNF, Integer nt) throws UnsupportedEncodingException, IOException, InterruptedException {

        try {
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            //Informe a chave a ser Consultada
            //TEnviNFe enviNFe1 = new TEnviNFe();
            //enviNFe1 = MontaNfe.main(nNNF);
            String chave = tf_chave.getText().trim();
            //Inicio 31/01/2024
            String sNNF = chave.substring(25, 34);
            if (!nNNF.trim().equals(sNNF)) {
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(0);
                System.setProperty("gCStat", "999");
                TableResumoOperacoes("999", "Número da NFe da chave de acesso " + sNNF + " está divergente da NFe Informada para consulta " + nNNF + ", Verifique!!!");
                Exception("");
                return;
            }
            //Fim 31/01/2024
            TRetConsSitNFe retorno = Nfe.consultaXml(configINI.iniciaConfiguracoes(), chave, DocumentoEnum.NFE);
            //Efetua a consulta
            //TRetConsSitNFe retorno = Nfe.consultaXml(configINI.iniciaConfiguracoes(), chave, DocumentoEnum.NFE);
            //Teste CCe da NFe: 0052707 2021-05-18T14:31:04-03:00
            /*
            TRetConsSitNFe retorno = XmlNfeUtil.xmlToObject(""
                    + "<retConsSitNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><tpAmb>1</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo><cUF>35</cUF><dhRecbto>2021-05-18T14:31:04-03:00</dhRecbto><chNFe>35210552703063000108550010000527071761855285</chNFe><protNFe versao=\"4.00\">"
                    + "<infProt><tpAmb>1</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><chNFe>35210552703063000108550010000527071761855285</chNFe><dhRecbto>2021-05-17T17:23:49-03:00</dhRecbto><nProt>135210543078872</nProt><digVal>s1gzWTOapkcK6Fkf3fKrujDcMoU=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe>"
                    + "<procEventoNFe versao=\"1.00\"><evento versao=\"1.00\"><infEvento Id=\"ID1101103521055270306300010855001000052707176185528501\"><cOrgao>35</cOrgao><tpAmb>1</tpAmb><CNPJ>52703063000108</CNPJ><chNFe>35210552703063000108550010000527071761855285</chNFe><dhEvento>2021-05-18T10:33:07-03:00</dhEvento><tpEvento>110110</tpEvento><nSeqEvento>1</nSeqEvento><verEvento>1.00</verEvento>"
                    + "<detEvento versao=\"1.00\"><descEvento>Carta de Correcao</descEvento><xCorrecao>TRANSPORTADORA BELLAS BRASIL LOGISTICA EXP EIRELI , CNPJ 27.162.318/0001-25, IE: 141.771.590.117, RUA DOUTOR VIDAL REIS 395, PQ NOVO MUNDO SP, FRETE COMBINADO COM ALISSON GOIANIA R$ 550.00, 60 SACOS DE MATERIAL .</xCorrecao><xCondUso>A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.</xCondUso></detEvento></infEvento>"
                    + "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#ID1101103521055270306300010855001000052707176185528501\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>DEz5eST/iSwRbw1zeUkko/7b/JY=</DigestValue></Reference></SignedInfo><SignatureValue>HL689FMFPfuHI7YLiu/UKrBkDPDDM6GV493wuwURADVS+tUkFRTSadHjRvGXVwVZQt66XS+b9qAc7tNVhX2684TYig/50RL1g7IMbr1AVKpeRZcOs4JhJqY9vHPMtLiPNf3tg5ZlyTN76yLWroapYl3qod5DnloYCMxEtZpEJWwgI9vRenA2+n8OGNqUbJyMB/v6cQglep2JSlUaHf8T9+tb4HKh/N8bTUxS2KUlEYdVAYwSIIBwd+L+JK7Lo2EZGa2pevagrlmjLsJqYgQ8irfUWUV9fHf/yaJFpUy6wbcLmvEmP5QLNrVLuZC+P0UIgrHy44R3bxxJHFBN/xdZ7Q==</SignatureValue><KeyInfo><X509Data><X509Certificate>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</X509Certificate></X509Data></KeyInfo></Signature></evento>"
                    + "<retEvento versao=\"1.00\"><infEvento><tpAmb>1</tpAmb><verAplic>SP_EVENTOS_PL_100</verAplic><cOrgao>35</cOrgao><cStat>135</cStat><xMotivo>Evento registrado e vinculado a NF-e</xMotivo><chNFe>35210552703063000108550010000527071761855285</chNFe><tpEvento>110110</tpEvento><xEvento>Carta de Correção registrada</xEvento><nSeqEvento>1</nSeqEvento><CNPJDest>08385910000141</CNPJDest><dhRegEvento>2021-05-18T10:34:29-03:00</dhRegEvento><nProt>135210546005477</nProt></infEvento></retEvento>"
                    + "</procEventoNFe></retConsSitNFe>", TRetConsSitNFe.class);
             */
            //Teste Cancelamento da NFe: 0052576 2021-05-19T13:45:32-03:00
/*
            TRetConsSitNFe retorno = XmlNfeUtil.xmlToObject(""
                    + "<retConsSitNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><tpAmb>1</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><cStat>101</cStat><xMotivo>Cancelamento de NF-e homologado</xMotivo><cUF>35</cUF><dhRecbto>2021-05-19T13:42:15-03:00</dhRecbto><chNFe>35210552703063000108550010000525761761855281</chNFe><protNFe versao=\"4.00\">"
                    + "<infProt><tpAmb>1</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><chNFe>35210552703063000108550010000525761761855281</chNFe><dhRecbto>2021-05-05T15:52:19-03:00</dhRecbto><nProt>135210492998158</nProt><digVal>Ia9tXl+2uIvTqfdH2vuh9lJwa6w=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe>"
                    + "<retCancNFe versao=\"3.10\"><infCanc><tpAmb>1</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><cStat>101</cStat><xMotivo>Cancelamento de NF-e homologado</xMotivo><cUF>35</cUF><chNFe>35210552703063000108550010000525761761855281</chNFe><dhRecbto>2021-05-05T15:52:19-03:00</dhRecbto><nProt>135210492998158</nProt></infCanc></retCancNFe><procEventoNFe versao=\"1.00\"><evento versao=\"1.00\"><infEvento Id=\"ID1101113521055270306300010855001000052576176185528101\"><cOrgao>35</cOrgao><tpAmb>1</tpAmb><CNPJ>52703063000108</CNPJ><chNFe>35210552703063000108550010000525761761855281</chNFe><dhEvento>2021-05-06T15:32:42-03:00</dhEvento><tpEvento>110111</tpEvento><nSeqEvento>1</nSeqEvento><verEvento>1.00</verEvento>"
                    + "<detEvento versao=\"1.00\"><descEvento>Cancelamento</descEvento><nProt>135210492998158</nProt><xJust>NOTA FISCAL CONTEM DADOS INCORRETOS.</xJust></detEvento></infEvento><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#ID1101113521055270306300010855001000052576176185528101\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>ca4fjAsSqwXqg9mgi2HQAXCykEM=</DigestValue></Reference></SignedInfo><SignatureValue>SaPZTNZLi5ymR/HZFg/zck81qHAh5BKfzdXCBhFldH9u+Wnt3Dwq1u3HNj3N5snW2SgQdjSn3n01Ff1hm5UW3h+UCECxqBGHV3lnmhqHcBtxjcVNh3QcDtFk4KC46aB5YAoHuS/yCpABzIhM59iqe4ui5BNwioUuZUESpxzSP3klO7NHKePTWqdVxzR+ZrAcdYdnTl53uE1OOrhj2JrWLV+VmDAlXFjzXPtdaOwoiQoI+rzT4g1VlNN/ln/ubOF9e2W9X2tIbgPzFMEJnflZMavgAkg0WmCC1yDD4nAzZLkeH78tZVHWmC9kLJs5DfLTpf7sQg8beoEBOnr/9klvfA==</SignatureValue><KeyInfo><X509Data><X509Certificate>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</X509Certificate></X509Data></KeyInfo></Signature></evento>"
                    + "<retEvento versao=\"1.00\"><infEvento><tpAmb>1</tpAmb><verAplic>SP_EVENTOS_PL_100</verAplic><cOrgao>35</cOrgao><cStat>135</cStat><xMotivo>Evento registrado e vinculado a NF-e</xMotivo><chNFe>35210552703063000108550010000525761761855281</chNFe><tpEvento>110111</tpEvento><xEvento>Cancelamento registrado</xEvento><nSeqEvento>1</nSeqEvento><CNPJDest>55940100000190</CNPJDest><dhRegEvento>2021-05-06T15:34:03-03:00</dhRegEvento><nProt>135210498173834</nProt></infEvento></retEvento>"
                    + "</procEventoNFe></retConsSitNFe>", TRetConsSitNFe.class);
             */
            info("| CStat........: " + retorno.getCStat());
            info("| DhRecbto.....: " + retorno.getDhRecbto());
            info("| TpAmb........: " + retorno.getTpAmb());
            info("| VerAplic.....: " + retorno.getVerAplic());
            info("| Versao.......: " + retorno.getVersao());
            info("| XMotivo......: " + retorno.getXMotivo());
            info("| CMsg.........: " + retorno.getCStat());
            info("| XMsg.........: " + retorno.getXMotivo());
            info("| ChNFe........: " + retorno.getChNFe().substring(34, 35));
            if (retorno.getCStat().equals("217") || retorno.getCStat().equals("656")) { //20/02/2024. 656 = Consumo indevido 
                System.setProperty("gCStat", retorno.getCStat());
                TableResumoOperacoes(retorno.getCStat(), retorno.getXMotivo());
                Exception("");
                return;
            } else {
                System.setProperty("gCStat", retorno.getProtNFe().getInfProt().getCStat());
                TableResumoOperacoes(retorno.getProtNFe().getInfProt().getCStat(), retorno.getProtNFe().getInfProt().getXMotivo());
            }
            //Autorizada
            if (retorno.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                TEnviNFe enviNFe = new TEnviNFe();
                enviNFe = MontaNfe.main(nNNF);
                //enviNFe.getNFe().get(0).getInfNFe().setId("NFe" + retorno.getChNFe());//03/02/2023
                //enviNFe.getNFe().get(0).getInfNFe().getIde().setTpEmis(retorno.getChNFe().substring(34, 35));//03/02/2023
                //enviNFe.getNFe().get(0).getInfNFe().getIde().setCNF(retorno.getChNFe().substring(35, 43));//03/02/2023
                //enviNFe.getNFe().get(0).getInfNFe().getIde().setCDV(retorno.getChNFe().substring(43, 44));//03/02/2023

                // Monta e Assina o XML
                enviNFe = Nfe.montaNfe(configINI.iniciaConfiguracoes(), enviNFe, true);
                System.out.println();
                System.out.println("# Status: " + retorno.getProtNFe().getInfProt().getCStat() + " - " + retorno.getProtNFe().getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retorno.getProtNFe().getInfProt().getNProt());
                System.out.println("# XML Final: " + criaNfeProc(enviNFe, retorno.getProtNFe()));
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(nNNF.trim());
                eNFE.setInfnfe_statusnfe("Autorizada");
                eNFE.setInfnfe_id(retorno.getChNFe());
                eNFE.setInfprot_nprot(retorno.getProtNFe().getInfProt().getNProt());
                eNFE.setInfprot_dhrecbto(retorno.getProtNFe().getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                eNFE.setIde_tpemis(enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis()); //03/02/2023
                eNFE.setIde_cnf(enviNFe.getNFe().get(0).getInfNFe().getIde().getCNF()); //03/02/2023
                eNFE.setIde_cdv(enviNFe.getNFe().get(0).getInfNFe().getIde().getCDV()); //03/02/2023
                eNFE.setXml_autorizado(criaNfeProc(enviNFe, retorno.getProtNFe()));
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                }
                NfeInfProtController.Delete(nNNF);
                NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
                NfeInfProtController.Create(nNNF.trim(), retorno.getProtNFe().getInfProt().getCStat());
                eNFE_INFPROT.setProtnfe_nnf(nNNF.trim());
                eNFE_INFPROT.setProtnfe_versao(retorno.getProtNFe().getVersao());
                eNFE_INFPROT.setProtnfe_sequencia("1");
                eNFE_INFPROT.setInfprot_cstat(retorno.getProtNFe().getInfProt().getCStat());
                eNFE_INFPROT.setInfprot_chnfe(retorno.getProtNFe().getInfProt().getChNFe());
                eNFE_INFPROT.setInfprot_dhrecbto(retorno.getProtNFe().getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                eNFE_INFPROT.setInfprot_nid(retorno.getProtNFe().getInfProt().getId());
                eNFE_INFPROT.setInfprot_nprot(retorno.getProtNFe().getInfProt().getNProt());
                eNFE_INFPROT.setInfprot_tpamb(retorno.getProtNFe().getInfProt().getTpAmb());
                eNFE_INFPROT.setInfprot_veraplic(retorno.getProtNFe().getInfProt().getVerAplic());
                eNFE_INFPROT.setInfprot_xmotivo(retorno.getProtNFe().getInfProt().getXMotivo().replace("Autorizado o uso da NF-e", "Autorização de Uso"));
                NfeInfProtController.Update(eNFE_INFPROT);
                if (veioDo_NFe_frm != null) {
                    String w_ide_xtpemis = "";
                    if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("1")) {
                        w_ide_xtpemis = "Normal";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("2")) {
                        w_ide_xtpemis = "Contingência FS-IA";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("3")) {
                        w_ide_xtpemis = "Contingência via EPEC";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("4")) {
                        w_ide_xtpemis = "Contingência via DPEC";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("5")) {
                        w_ide_xtpemis = "Contingência FS-DA";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("6")) {
                        w_ide_xtpemis = "Contingência SVC-AN";
                    } else if (enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis().equals("7")) {
                        w_ide_xtpemis = "Contingência SVC-RS";
                    }
                    SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String data = "dd/MM/yyyy HH:mm:ss";
                    String pattern = "yyyy-MM-dd HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date date = df.parse(retorno.getProtNFe().getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                    String wwDhRecbto = formatador1.format(date);
                    veioDo_NFe_frm.retornaRegistro("Autorizada", wwDhRecbto, nt, w_ide_xtpemis, null);
                }
            }

            //Cancelamento
            if (retorno.getCStat().equals("101")) {
                try {
                    //Evento registrado e vinculado a NF-e
                    NFE eNFE = new NFE();
                    NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
                    eNFE.setIde_nnf(nNNF.trim());
                    eNFE.setInfnfe_statusnfe("Cancelada");
                    //Cria ProcEvento de Cacnelamento
                    Evento cancela = new Evento();
                    //Informe a chave da Nota a ser Cancelada
                    cancela.setChave(retorno.getProcEventoNFe().get(0).getEvento().getInfEvento().getChNFe().trim());
                    //Informe o protocolo da Nota a ser Cancelada
                    cancela.setProtocolo(retorno.getProtNFe().getInfProt().getNProt());
                    //Informe o CNPJ do emitente
                    cancela.setCnpj(retorno.getProcEventoNFe().get(0).getEvento().getInfEvento().getCNPJ());
                    //Informe o Motivo do Cancelamento
                    retorno.getProcEventoNFe().forEach(resultado -> {
                        if (resultado.getEvento().getInfEvento().getTpEvento().equals("110111")) {
                            resultado.getEvento().getInfEvento().getDetEvento().getAny().forEach(detEvento -> {
                                if (detEvento.getTagName().equals("xJust")) {
                                    cancela.setMotivo(detEvento.getTextContent());
                                }
                            });
                        }

                    });
                    //Informe a data do Cancelamento
                    LocalDateTime agora = LocalDateTime.now();
                    cancela.setDataEvento(agora);
                    //Monta o Evento de Cancelamento
                    br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento enviEvento = CancelamentoUtil.montaCancelamento(cancela, configINI.iniciaConfiguracoes());
                    br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento retEnvEvento = new br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento();
                    br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento retEvento = new br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento();
                    br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento.InfEvento retEventoInfEvento = new br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento.InfEvento();

                    retorno.getProcEventoNFe().forEach(resultado -> {
                        if (resultado.getEvento().getInfEvento().getTpEvento().equals("110111")) {
                            retEventoInfEvento.setCNPJDest(resultado.getRetEvento().getInfEvento().getCNPJDest());
                            retEventoInfEvento.setCOrgao(resultado.getRetEvento().getInfEvento().getCOrgao());
                            retEventoInfEvento.setCStat(resultado.getRetEvento().getInfEvento().getCStat());
                            retEventoInfEvento.setChNFe(resultado.getRetEvento().getInfEvento().getChNFe());
                            retEventoInfEvento.setDhRegEvento(resultado.getRetEvento().getInfEvento().getDhRegEvento());
                            retEventoInfEvento.setEmailDest(resultado.getRetEvento().getInfEvento().getEmailDest());
                            retEventoInfEvento.setId(resultado.getRetEvento().getInfEvento().getId());
                            retEventoInfEvento.setNProt(resultado.getRetEvento().getInfEvento().getNProt());
                            retEventoInfEvento.setNSeqEvento(resultado.getRetEvento().getInfEvento().getNSeqEvento());
                            retEventoInfEvento.setTpAmb(resultado.getRetEvento().getInfEvento().getTpAmb());
                            retEventoInfEvento.setTpEvento(resultado.getRetEvento().getInfEvento().getTpAmb());
                            retEventoInfEvento.setVerAplic(resultado.getRetEvento().getInfEvento().getVerAplic());
                            retEventoInfEvento.setXEvento(resultado.getRetEvento().getInfEvento().getXEvento());
                            retEventoInfEvento.setXMotivo(resultado.getRetEvento().getInfEvento().getXMotivo());
                        }
                    });

                    retEvento.setInfEvento(retEventoInfEvento);

                    retEnvEvento.getRetEvento().add(retEvento);

                    String proc = CancelamentoUtil.criaProcEventoCancelamento(configINI.iniciaConfiguracoes(), enviEvento, retEnvEvento.getRetEvento().get(0));
                    eNFE.setXml_envevento_autorizado(proc);
                    System.err.println(proc);
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    System.setProperty("gCStat", retorno.getCStat());
                    TableResumoOperacoes(retorno.getRetCancNFe().getInfCanc().getCStat(), retorno.getRetCancNFe().getInfCanc().getXMotivo());
                    NfeInfProtController.Create(nNNF.trim(), retorno.getRetCancNFe().getInfCanc().getCStat());
                    eNFE_INFPROT.setProtnfe_nnf(nNNF.trim());
                    eNFE_INFPROT.setProtnfe_versao(retorno.getVersao());
                    eNFE_INFPROT.setProtnfe_sequencia("1");
                    eNFE_INFPROT.setInfprot_cstat(retorno.getRetCancNFe().getInfCanc().getCStat());
                    eNFE_INFPROT.setInfprot_chnfe(retorno.getRetCancNFe().getInfCanc().getChNFe());
                    //eNFE_INFPROT.setInfprot_dhrecbto(retorno.getRetCancNFe().getInfCanc().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                    eNFE_INFPROT.setInfprot_nid(retorno.getRetCancNFe().getInfCanc().getId());
                    eNFE_INFPROT.setInfprot_nprot(retorno.getRetCancNFe().getInfCanc().getNProt());
                    //eNFE_INFPROT.setInfprot_nprot(retorno.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getNProt());
                    eNFE_INFPROT.setInfprot_tpamb(retorno.getRetCancNFe().getInfCanc().getTpAmb());
                    eNFE_INFPROT.setInfprot_veraplic(retorno.getRetCancNFe().getInfCanc().getVerAplic());
                    eNFE_INFPROT.setInfprot_xmotivo(retorno.getRetCancNFe().getInfCanc().getXMotivo().replace("Autorizado o uso da NF-e", "Autorização de Uso"));

                    RemoveCharacterSpecials removeCharacterSpecials = new RemoveCharacterSpecials();
                    retorno.getProcEventoNFe().forEach(resultado -> {
                        if (resultado.getEvento().getInfEvento().getTpEvento().equals("110111")) {
                            eNFE_INFPROT.setInfprot_dhrecbto(resultado.getEvento().getInfEvento().getDhEvento().toString().replace("T", " ").replace("-03:00", ""));
                            eNFE_INFPROT.setInfprot_nprot(resultado.getRetEvento().getInfEvento().getNProt());
                            resultado.getEvento().getInfEvento().getDetEvento().getAny().forEach(detEvento -> {
                                if (detEvento.getTagName().equals("descEvento")) {
                                    eNFE_INFPROT.setInfprot_xmotivo(detEvento.getTextContent());
                                }
                                if (detEvento.getTagName().equals("nProt")) {
                                    //eNFE_INFPROT.setInfprot_nprot(detEvento.getTextContent());
                                }
                                if (detEvento.getTagName().equals("xJust")) {
                                    eNFE_INFPROT.setInfprot_xjust(removeCharacterSpecials.RemoveCharacterSpecials(detEvento.getTextContent()));
                                }
                            });
                        }
                    });

                    NfeInfProtController.Update(eNFE_INFPROT);
                    if (veioDo_NFe_frm != null) {
                        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String data = "dd/MM/yyyy HH:mm:ss";
                        String pattern = "yyyy-MM-dd HH:mm:ss";
                        DateFormat df = new SimpleDateFormat(pattern);
                        Date date = df.parse(retorno.getRetCancNFe().getInfCanc().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                        String wwDhRegEvento = formatador1.format(date);
                        veioDo_NFe_frm.retornaRegistro("Cancelada", "", null, null, null);
                    }

                } catch (InstantiationException ex) {
                    Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                } catch (ParseException ex) {
                    Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                }
            }

            //Carta de correção
            if (retorno.getProcEventoNFe() != null) {
                retorno.getProcEventoNFe().forEach(resultado -> {
                    if (resultado.getEvento().getInfEvento().getTpEvento().equals("110110")) {
                        try {
                            //Recebido pelo Sistema de Registro de Eventos
                            //Evento registrado e vinculado a NF-e
                            NFE eNFE = new NFE();
                            NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
                            eNFE.setIde_nnf(nNNF.trim());
                            eNFE.setInfnfe_statusnfe("Corrigida");
                            //Cria ProcEvento de Carta de Correção
                            //eNFE.setXml_envevento_autorizado(retorno.toString());
                            //Cria ProcEvento da CCe
                            Evento cce = new Evento();
                            //Informe a chave da Nota a ser feita a CArta de Correção
                            cce.setChave(resultado.getEvento().getInfEvento().getChNFe().trim());
                            //Informe o CNPJ do emitente
                            cce.setCnpj(resultado.getEvento().getInfEvento().getCNPJ().trim());
                            //Informe o Texto da Carta de Correção
                            //resultado.getEvento().getInfEvento().getDetEvento().getAny().forEach(det -> {
                            //    cce.setMotivo(det.getAttribute("xCorrecao").trim());
                            //});

                            resultado.getEvento().getInfEvento().getDetEvento().getAny().forEach(detEvento -> {
                                if (detEvento.getTagName().equals("xCorrecao")) {
                                    cce.setMotivo(detEvento.getTextContent());
                                }
                            });

                            //Informe a data da Carta de Correção
                            LocalDateTime agora = LocalDateTime.now();
                            cce.setDataEvento(agora);
                            //Informe a sequencia do Evento
                            cce.setSequencia(1);
                            // Monta o Evento
                            TEnvEvento envEvento = CartaCorrecaoUtil.montaCCe(cce, configINI.iniciaConfiguracoes());
                            TRetEnvEvento retornoEnvEvento = new TRetEnvEvento();

                            retornoEnvEvento.setCOrgao(resultado.getRetEvento().getInfEvento().getCOrgao());
                            retornoEnvEvento.setCStat(resultado.getRetEvento().getInfEvento().getCStat());
                            retornoEnvEvento.setIdLote("1");
                            retornoEnvEvento.setTpAmb(resultado.getRetEvento().getInfEvento().getTpAmb());
                            retornoEnvEvento.setVerAplic(resultado.getRetEvento().getInfEvento().getVerAplic());
                            retornoEnvEvento.setVersao(resultado.getRetEvento().getVersao());
                            retornoEnvEvento.setXMotivo(resultado.getRetEvento().getInfEvento().getXMotivo());

                            TretEvento.InfEvento retEventoInfEvento = new TretEvento.InfEvento();
                            TretEvento retEvento = new TretEvento();

                            retEventoInfEvento.setCNPJDest(resultado.getRetEvento().getInfEvento().getCNPJDest());
                            retEventoInfEvento.setCOrgao(resultado.getRetEvento().getInfEvento().getCOrgao());
                            retEventoInfEvento.setCStat(resultado.getRetEvento().getInfEvento().getCStat());
                            retEventoInfEvento.setChNFe(resultado.getRetEvento().getInfEvento().getChNFe());
                            retEventoInfEvento.setDhRegEvento(resultado.getRetEvento().getInfEvento().getDhRegEvento());
                            retEventoInfEvento.setEmailDest(resultado.getRetEvento().getInfEvento().getEmailDest());
                            retEventoInfEvento.setId(resultado.getRetEvento().getInfEvento().getId());
                            retEventoInfEvento.setNProt(resultado.getRetEvento().getInfEvento().getNProt());
                            retEventoInfEvento.setNSeqEvento(resultado.getRetEvento().getInfEvento().getNSeqEvento());
                            retEventoInfEvento.setTpAmb(resultado.getRetEvento().getInfEvento().getTpAmb());
                            retEventoInfEvento.setTpEvento(resultado.getRetEvento().getInfEvento().getTpAmb());
                            retEventoInfEvento.setVerAplic(resultado.getRetEvento().getInfEvento().getVerAplic());
                            retEventoInfEvento.setXEvento(resultado.getRetEvento().getInfEvento().getXEvento());
                            retEventoInfEvento.setXMotivo(resultado.getRetEvento().getInfEvento().getXMotivo());

                            retEvento.setInfEvento(retEventoInfEvento);

                            retornoEnvEvento.getRetEvento().add(0, retEvento);

                            String proc = CartaCorrecaoUtil.criaProcEventoCCe(configINI.iniciaConfiguracoes(), envEvento, retornoEnvEvento);
                            eNFE.setXml_envevento_autorizado(proc);
                            System.err.println(proc);

                            if (NfeController.Update(eNFE)) {
                                System.out.println("NFe atualizado com sucesso!");
                            } else {
                                System.out.println(System.getProperty("MsgInvalid"));
                            }
                            RemoveCharacterSpecials removeCharacterSpecials = new RemoveCharacterSpecials();

                            TableResumoOperacoes("110", "Carta de Correcao");
                            NfeInfProtController.Create(nNNF.trim(), "110");
                            eNFE_INFPROT.setProtnfe_nnf(nNNF.trim());
                            eNFE_INFPROT.setProtnfe_versao(retorno.getVersao());
                            eNFE_INFPROT.setProtnfe_sequencia("1");
                            eNFE_INFPROT.setInfprot_cstat("110");
                            eNFE_INFPROT.setInfprot_chnfe(retorno.getProcEventoNFe().get(0).getEvento().getInfEvento().getChNFe());
                            eNFE_INFPROT.setInfprot_dhrecbto(retorno.getProcEventoNFe().get(0).getEvento().getInfEvento().getDhEvento().toString().replace("T", " ").replace("-03:00", ""));
                            eNFE_INFPROT.setInfprot_nprot(retorno.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getNProt());
                            eNFE_INFPROT.setInfprot_tpamb(retorno.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getTpAmb());
                            eNFE_INFPROT.setInfprot_veraplic(retorno.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getVerAplic());
                            eNFE_INFPROT.setInfprot_xmotivo(retorno.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getXEvento().replace("CorreÃ§Ã£o", "Correcao"));
                            eNFE_INFPROT.setInfprot_dhrecbto(resultado.getEvento().getInfEvento().getDhEvento().toString().replace("T", " ").replace("-03:00", ""));
                            NfeInfProtController.Update(eNFE_INFPROT);
                            resultado.getEvento().getInfEvento().getDetEvento().getAny().forEach(detEvento -> {
                                if (detEvento.getTagName().equals("descEvento")) {
                                    eNFE_INFPROT.setInfprot_xmotivo(detEvento.getTextContent());
                                }
                                if (detEvento.getTagName().equals("xCorrecao")) {
                                    eNFE_INFPROT.setInfprot_xcorrecao(detEvento.getTextContent());
                                }
                                if (detEvento.getTagName().equals("xCondUso")) {
                                    //eNFE_INFPROT.setInfprot_xconduso(removeCharacterSpecials.RemoveCharacterSpecials(detEvento.getTextContent()).trim());
                                }
                            });

                            NfeInfProtController.Update(eNFE_INFPROT);
                            if (veioDo_NFe_frm != null) {
                                SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String data = "dd/MM/yyyy HH:mm:ss";
                                String pattern = "yyyy-MM-dd HH:mm:ss";
                                DateFormat df = new SimpleDateFormat(pattern);
                                Date date = df.parse(retorno.getProcEventoNFe().get(0).getEvento().getInfEvento().getDhEvento().toString().replace("T", " ").replace("-03:00", ""));
                                String wwDhRegEvento = formatador1.format(date);
                                veioDo_NFe_frm.retornaRegistro("Corrigida", "", null, null, null);
                            }

                            if (retorno.getCStat().equals("101")) {
                                eNFE.setIde_nnf(nNNF.trim());
                                eNFE.setInfnfe_statusnfe("Cancelada");
                                //Cria ProcEvento de Cacnelamento
                                eNFE.setXml_envevento_autorizado(retorno.toString());
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
                                    Date date = df.parse(retorno.getRetCancNFe().getInfCanc().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                                    String wwDhRegEvento = formatador1.format(date);
                                    veioDo_NFe_frm.retornaRegistro("Cancelada", "", null, null, null);
                                }
                            }

                        } catch (InstantiationException ex) {
                            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                            Exception(ex.getMessage());
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                            Exception(ex.getMessage());
                        } catch (ParseException ex) {
                            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                            Exception(ex.getMessage());
                        } catch (JAXBException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NfeException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CertificadoException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

        } catch (ParseException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (NfeException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }

    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDo_NFe_frm = veioDo1;
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            modelo1.addRow(new Object[]{
                "",
                gAno,
                gSerie,
                gNNF,
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

    public void TableResumoOperacoes(String wCStat, String wXMotivo) {
        modelo1.addRow(new Object[]{
            "", gAno, gSerie, gNNF, wCStat + "-" + wXMotivo});

    }

    public class Process extends Thread {

        public void run() {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            jDesktopPane2.setCursor(cursor);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            jLabel1.setText("Consultando Nota Fiscal Eletrônica na SEFAZ.");
            jLabel2.setText("Por favor, aguarde.");
            jProgressBar1.setIndeterminate(true);
            jLabel1.paintImmediately(jLabel1.getVisibleRect());
            jLabel2.paintImmediately(jLabel2.getVisibleRect());
            jLabel2.requestFocus();
            jProgressBar1.setValue(0);
            modelo1 = (DefaultTableModel) jTable1.getModel();
            System.setProperty("gError", "");
            arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
            Iterator itListaNNF = tListaNNF.iterator();
            int nt = 0;
            while (itListaNNF.hasNext()) {
                try {
                    gNNF = itListaNNF.next().toString();
                    ConsultarNfeSEFAZ(gNNF, tSelectedRows[nt]);
                    nt = nt + 1;
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConsultaSituacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            jProgressBar1.setIndeterminate(false);
            jProgressBar1.setValue(100);
            cursor = Cursor.getDefaultCursor();
            jDesktopPane2.setCursor(cursor);
            jLabel1.setText("");
            jLabel2.setText("");

        }
    }

}
