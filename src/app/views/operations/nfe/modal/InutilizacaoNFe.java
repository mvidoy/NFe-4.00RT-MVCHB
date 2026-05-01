package app.views.operations.nfe.modal;

import app.controllers.EmitenteController;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ProgressMonitor;
import javax.xml.bind.JAXBException;
import lib.ConfiguracoesNfeIniciais;
import org.json.JSONObject;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.formata;
import app.controllers.NfeInutNfeController;
import app.models.NFE_INUTNFE;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.InutilizacaoUtil;
import java.io.UnsupportedEncodingException;

public class InutilizacaoNFe extends javax.swing.JInternalFrame {

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

    public InutilizacaoNFe(
            JDesktopPane jDesktop,
            JInternalFrame jFrame) {
        this.jDesktopPane2 = jDesktop;
        initComponents();
        jButton1.setEnabled(false);
        Date dataAtual = new Date();
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //String data = "dd/MM/yyyy HH:mm:ss";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        //DateFormat df = new SimpleDateFormat(pattern);
        String wwDhRegEvento = formatador1.format(dataAtual);
        tf_DhInutilizacao.setText(wwDhRegEvento);
        tf_numeroInicial.setText("");
        tf_numeroFinal.setText("");
        jTxtP_justificativa.setText("NOTA FISCAL INUTILIZADA");
        System.setProperty("XMLretorno", "");
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
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
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(1835);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(wCol3);
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
            String text = table.getValueAt(row, 1).toString();
            if (text.contains("102")) {
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
            String text = table.getValueAt(row, 1).toString();
            if (text.contains("102")) {
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
        jLabel44 = new javax.swing.JLabel();

        tf_numeroInicial = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tf_DhInutilizacao = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtP_justificativa = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel47 = new javax.swing.JLabel();

        tf_numeroFinal = new javax.swing.JTextField();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setTitle("Inutilização - Revisão: 01 - Data da Última Revisão: 10/06/2021 - Data da Elaboração: 10/06/2021");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.PNG"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inutilização de Faixas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N
        jDesktopPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDesktopPane2MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo das Operações:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

        jScrollPane13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane13KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane13KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Resultado da Inutilização"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setText("Inutilizar");
        jButton1.setEnabled(false);
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

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("* Número Inicial");

        tf_numeroInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_numeroInicial.setToolTipText("");
        tf_numeroInicial.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_numeroInicial.setDoubleBuffered(true);
        tf_numeroInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_numeroInicialFocusLost(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("* Justificatica");

        tf_DhInutilizacao.setEditable(false);
        tf_DhInutilizacao.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_DhInutilizacao.setToolTipText("");
        tf_DhInutilizacao.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_DhInutilizacao.setDoubleBuffered(true);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("* Data e Hora");

        jTxtP_justificativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtP_justificativaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTxtP_justificativa);

        jLabel8.setText("mínimo de 15 e máximo de 255 caracteres.");

        jLabel9.setText("(*) Campo de preenchimento obrigatório.");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("* Número Final");

        tf_numeroFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_numeroFinal.setToolTipText("");
        tf_numeroFinal.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_numeroFinal.setDoubleBuffered(true);
        tf_numeroFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_numeroFinalFocusLost(evt);
            }
        });

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_numeroInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_DhInutilizacao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_numeroFinal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13))
                .addGap(3, 3, 3))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_DhInutilizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_numeroInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_numeroFinal)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(465, 465, 465)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_DhInutilizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_numeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tf_numeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
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
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(3, 3, 3))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(840, Short.MAX_VALUE)
                .addComponent(botao_sair)
                .addGap(2, 2, 2))
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
                .addGap(16, 16, 16)
                .addComponent(botao_sair))
        );

        setBounds(0, 0, 923, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void jDesktopPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane2MouseClicked

    }//GEN-LAST:event_jDesktopPane2MouseClicked

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void jScrollPane13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane13KeyPressed

    private void jScrollPane13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane13KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Process process = new Process();
        process.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTxtP_justificativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtP_justificativaKeyPressed
        if (jTxtP_justificativa.getText().trim().length() >= 15) {
            jButton1.setEnabled(true);
        } else {
            jButton1.setEnabled(false);
        }
    }//GEN-LAST:event_jTxtP_justificativaKeyPressed

    private void jButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseMoved
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton1MouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    private void tf_numeroInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_numeroInicialFocusLost
        if (tf_numeroInicial.getText().trim().length() > 0
                && tf_numeroFinal.getText().trim().length() > 0) {
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_tf_numeroInicialFocusLost

    private void tf_numeroFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_numeroFinalFocusLost
        if (tf_numeroInicial.getText().trim().length() > 0
                && tf_numeroFinal.getText().trim().length() > 0) {
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_tf_numeroFinalFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_sair;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTxtP_justificativa;
    private javax.swing.JTextField tf_DhInutilizacao;
    private javax.swing.JTextField tf_numeroFinal;
    private javax.swing.JTextField tf_numeroInicial;
    // End of variables declaration//GEN-END:variables

    public void FechaTodasTabelas() {
    }

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

    private void InutilizarNfeSEFAZ(String nNNF) throws SQLException, UnsupportedEncodingException {

        try {
            NFE_INUTNFE eNFE_INUTNFE = new NFE_INUTNFE();
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();

            JSONObject jsonEMITENTE = new JSONObject();
            jsonEMITENTE = EmitenteController.Index(System.getProperty("EMITENTE"));
            //Informe o CNPJ do emitente
            String cnpj = "";
            if (!jsonEMITENTE.isEmpty()) {
                cnpj = jsonEMITENTE.getString("EMP_CGC0").trim().replace("/", "").replace(".", "").replace("-", "");
            }
            //Informe a serie
            int serie = 1;
            //Informe a numeracao Inicial
            int numeroInicial = Integer.parseInt(tf_numeroInicial.getText().trim());
            //Informe a numeracao Final
            int numeroFinal = Integer.parseInt(tf_numeroFinal.getText().trim());
            //Informe a Justificativa da Inutilizacao
            String justificativa = jTxtP_justificativa.getText().trim();
            //Informe a data do Cancelamento
            LocalDateTime dataInutilizacao = LocalDateTime.now();

            //Monta Inutilização
            TInutNFe inutNFe = InutilizacaoUtil.montaInutilizacao(DocumentoEnum.NFE, cnpj, serie, numeroInicial, numeroFinal, justificativa, dataInutilizacao, configINI.iniciaConfiguracoes());

            //Envia Inutilização
            TRetInutNFe retorno = Nfe.inutilizacao(configINI.iniciaConfiguracoes(), inutNFe, DocumentoEnum.NFE, true);
            //String xml = "<retInutNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><infInut><tpAmb>2</tpAmb><verAplic>SP_NFE_PL009_V4</verAplic><cStat>102</cStat><xMotivo>Inutilização de número homologado</xMotivo><cUF>35</cUF><ano>21</ano><CNPJ>52703063000108</CNPJ><mod>55</mod><serie>1</serie><nNFIni>52734</nNFIni><nNFFin>52734</nNFFin><dhRecbto>2021-06-10T14:26:26-03:00</dhRecbto><nProt>135210004205528</nProt></infInut></retInutNFe>";
            //TRetInutNFe retorno = XmlNfeUtil.xmlToObject(xml, TRetInutNFe.class);

            //Valida o Retorno da Inutilização
            RetornoUtil.validaInutilizacao(retorno);
            //Resultado
            System.out.println();
            System.out.println("# Status: " + retorno.getInfInut().getCStat() + " - " + retorno.getInfInut().getXMotivo());
            System.out.println("# Protocolo: " + retorno.getInfInut().getNProt());

            //Cria ProcEvento da Inutilização
            String proc = InutilizacaoUtil.criaProcInutilizacao(configINI.iniciaConfiguracoes(), inutNFe, retorno);
            System.out.println();
            System.out.println("# ProcInutilizacao : " + proc);
            if (retorno.getInfInut().getCStat().equals("102")) //Cancelamento homologado fora de prazo
            {
                try {
                    TableResumoOperacoes(retorno.getInfInut().getCStat(), retorno.getInfInut().getXMotivo());
                    NfeInutNfeController.Create(tf_numeroInicial.getText().trim(), tf_numeroFinal.getText().trim());
                    eNFE_INUTNFE.setRetinutnfe_infinut_ano(retorno.getInfInut().getAno());
                    eNFE_INUTNFE.setRetinutnfe_infinut_cnpj(retorno.getInfInut().getCNPJ());
                    eNFE_INUTNFE.setRetinutnfe_infinut_cuf(retorno.getInfInut().getCUF());
                    eNFE_INUTNFE.setRetinutnfe_infinut_id(inutNFe.getInfInut().getId());
                    eNFE_INUTNFE.setRetinutnfe_infinut_mod(retorno.getInfInut().getMod());
                    eNFE_INUTNFE.setRetinutnfe_infinut_nnffin(retorno.getInfInut().getNNFFin());
                    eNFE_INUTNFE.setRetinutnfe_infinut_nnfini(retorno.getInfInut().getNNFIni());
                    eNFE_INUTNFE.setRetinutnfe_infinut_nprot(retorno.getInfInut().getNProt());
                    eNFE_INUTNFE.setRetinutnfe_infinut_serie(retorno.getInfInut().getSerie());
                    eNFE_INUTNFE.setRetinutnfe_infinut_tpamb(retorno.getInfInut().getTpAmb());
                    eNFE_INUTNFE.setRetinutnfe_infinut_veraplic(retorno.getInfInut().getVerAplic());
                    eNFE_INUTNFE.setRetinutnfe_infinut_cstat(retorno.getInfInut().getCStat());
                    eNFE_INUTNFE.setRetinutnfe_infinut_xmotivo(retorno.getInfInut().getXMotivo());
                    eNFE_INUTNFE.setRetinutnfe_infinut_dhrecbto(retorno.getInfInut().getDhRecbto());
                    eNFE_INUTNFE.setInfinut_xjust(inutNFe.getInfInut().getXJust());
                    eNFE_INUTNFE.setRetinutnfe_versao(retorno.getVersao());
                    eNFE_INUTNFE.setXml_retinutnfe_autorizado(proc);

                    NfeInutNfeController.Update(eNFE_INUTNFE);

                } catch (InstantiationException ex) {
                    Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                    Exception(ex.getMessage());
                    Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
                }

            };
        } catch (NfeException ex) {
            Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (JAXBException ex) {
            Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    public void TableResumoOperacoes(String wCStat, String wXMotivo) {
        modelo1.addRow(new Object[]{
            "", wCStat + "-" + wXMotivo});
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            modelo1.addRow(new Object[]{
                "",
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
                jLabel1.setText("Inutilizando Nota Fiscal Eletronica.");
                jLabel2.setText("Por favor, aguarde.");
                jProgressBar1.setIndeterminate(true);
                jButton1.setEnabled(false);
                jLabel2.requestFocus();
                jProgressBar1.setValue(0);
                modelo1 = (DefaultTableModel) jTable1.getModel();
                System.setProperty("gError", "");
                arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
                InutilizarNfeSEFAZ(gNNF);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(100);
                cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
            } catch (SQLException ex) {
                Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(InutilizacaoNFe.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
