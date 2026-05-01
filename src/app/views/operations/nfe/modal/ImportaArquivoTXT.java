package app.views.operations.nfe.modal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ProgressMonitor;
import org.json.JSONObject;
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
import app.models.NFE_TRANSP_VOL;
import com.backend.dtos.CALCULADORAENVIAdto;
import com.backend.dtos.CALCULADORARETORNOdto;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Locale;
import javax.swing.SwingUtilities;
import util.RemoveCharacterSpecials;

public class ImportaArquivoTXT extends javax.swing.JInternalFrame {

    String gCla_Valor = "";
    formata formata = new formata();
    FormatFields f = new FormatFields();
    static ProgressMonitor pbar;
    static int counter = 0;
    String tCaminhoArquivo = "";
    String tArquivoTXTNFe = "";
    ArrayList<String> tListaArquivosTXT = new ArrayList<String>();
    String gVersao = "";
    String gNNF = "";
    String gNItem = "";
    String gCRT = "";
    DefaultTableModel modelo1 = new DefaultTableModel();
    RemoveCharacterSpecials util = new RemoveCharacterSpecials();
    CALCULADORARETORNOdto cALCULADORARETORNOdto = new CALCULADORARETORNOdto();
    public static double totalVIBSUF = 0.0;
    public static double totalVIBSMun = 0.0;
    public static double totalVCBS = 0.0;

    public ImportaArquivoTXT(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wCaminhoArquivo,
            String wArquivoTXTNFe,
            ArrayList<String> wListaArquivosTXT) {
        Iterator it = wListaArquivosTXT.iterator();
        while (it.hasNext()) {
            tListaArquivosTXT.add(it.next().toString());
        }
        tCaminhoArquivo = wCaminhoArquivo;
        tArquivoTXTNFe = wArquivoTXTNFe;
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
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
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
        jTable1.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(900);
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
            Object text = table.getValueAt(row, 3);
            if (text != null && "Rejeitado".equals(text.toString().trim())) {
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
        setTitle("Importação de Arquivos TXT da NF-e  Gerado Via  ERP - Revisão: 09 - Data da Última Revisão: 30/07/2025 - Data da Elaboração: 02/07/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Importação de NF-e com Validação do IBSCBS via API da Receita Federal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13)))
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
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(845, Short.MAX_VALUE)
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

    private void importarNfeTxt(String caminho, String arquivo) {
        try {

            String wNivel = "";
            FileInputStream stream = new FileInputStream(caminho + arquivo);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            boolean wFlagdadosNfe_B = true;
            while (linha != null) {
                linha = retornaBarra(linha);
                wNivel = linha.substring(0, linha.indexOf('|')).trim();
                if (wNivel.equals("A")) {
                    VersaoNFe_A(linha);
                }
                if (wNivel.equals("B")) {
                    wFlagdadosNfe_B = dadosNfe_B(linha, arquivo);
                    if (wFlagdadosNfe_B == false) {
                        break;
                    }
                }
                if (wNivel.equals("C")) {
                    dadosDoEmitente_C(linha);
                }
                if (wNivel.equals("C02")) {
                    dadosDoEmitente_C02(linha);
                }
                if (wNivel.equals("C05")) {
                    dadosDoEmitente_C05(linha);
                }
                if (wNivel.equals("E")) {
                    dadosDoDestinatario_E(linha);
                }
                if (wNivel.equals("E02")) {
                    dadosDoDestinatario_E02(linha);
                }
                if (wNivel.equals("E03")) {
                    dadosDoDestinatario_E03(linha);
                }
                if (wNivel.equals("E05")) {
                    dadosDoDestinatario_E05(linha);
                }

                while (linha != null
                        && wNivel.equals("H")) {
                    totalVIBSUF = 0.0;
                    totalVIBSMun = 0.0;
                    totalVCBS = 0.0;
                    System.out.println(wNivel);
                    while (linha != null
                            && wNivel.equals("H") || wNivel.equals("I") || wNivel.equals("M")
                            || wNivel.equals("N") || wNivel.equals("N02") || wNivel.equals("N04")
                            || wNivel.equals("N06") || wNivel.equals("N07") || wNivel.equals("N08")
                            || wNivel.equals("O") || wNivel.equals("O07") || wNivel.equals("O08") || wNivel.equals("O10")
                            || wNivel.equals("Q") || wNivel.equals("Q02") || wNivel.equals("Q04") || wNivel.equals("Q05") || wNivel.equals("Q07") || wNivel.equals("Q10")
                            || wNivel.equals("S") || wNivel.equals("S02") || wNivel.equals("S04") || wNivel.equals("S05") || wNivel.equals("S07")
                            || wNivel.equals("RT") || wNivel.equals("RT01") || wNivel.equals("RT02") || wNivel.equals("RT03") || wNivel.equals("RT04") || wNivel.equals("RT05")) {
                        wNivel = linha.substring(0, linha.indexOf('|'));
                        if (wNivel.equals("H")) {
                            System.out.println(wNivel);
                            dadosDosProdutosServicos_H(linha);
                        }
                        if (wNivel.equals("I")) {
                            System.out.println(wNivel);
                            dadosDosProdutosServicos_I(linha);
                        }
                        if (wNivel.equals("M")) {
                            System.out.println(wNivel);
                            dadosDosProdutosServicos_M(linha);
                        }
                        if (wNivel.equals("N")) {
                            System.out.println(wNivel);
                            dadosDosProdutosServicos_N(linha);
                        }
                        if (wNivel.length() == 3) {
                            if (wNivel.substring(0, 2).equals("N0")) {
                                while (linha != null
                                        && wNivel.substring(0, 2).equals("N0")) {
                                    dadosDosProdutosServicos_N0(linha);
                                    linha = br.readLine();
                                    linha = retornaBarra(linha);
                                    wNivel = linha.substring(0, linha.indexOf('|'));
                                    System.out.println(wNivel);
                                    if (wNivel.length() < 3) {
                                        break;
                                    }
                                }
                            }
                        }
                        if (wNivel.equals("O")) {
                            dadosDosProdutosServicos_O(linha);
                        }
                        if (wNivel.equals("O07")) {
                            dadosDosProdutosServicos_O07(linha);
                        }
                        if (wNivel.equals("O08")) {
                            dadosDosProdutosServicos_O08(linha);
                        }
                        if (wNivel.equals("O10")) {
                            dadosDosProdutosServicos_O10(linha);
                        }
                        if (wNivel.equals("Q")) {
                            dadosDosProdutosServicos_Q(linha);
                        }
                        if (wNivel.equals("Q02")) {
                            dadosDosProdutosServicos_Q02(linha);
                        }
                        if (wNivel.equals("Q04")) {
                            dadosDosProdutosServicos_Q04(linha);
                        }
                        if (wNivel.equals("Q05")) {
                            dadosDosProdutosServicos_Q05(linha);
                        }
                        if (wNivel.equals("Q07")) {
                            dadosDosProdutosServicos_Q07(linha);
                        }

                        if (wNivel.equals("S")) {
                            dadosDosProdutosServicos_S(linha);
                        }
                        if (wNivel.equals("S02")) {
                            dadosDosProdutosServicos_S02(linha);
                        }
                        if (wNivel.equals("S04")) {
                            dadosDosProdutosServicos_S04(linha);
                        }
                        if (wNivel.equals("S05")) {
                            dadosDosProdutosServicos_S05(linha);
                        }
                        if (wNivel.equals("S07")) {
                            dadosDosProdutosServicos_S07(linha);
                        }

                        if (wNivel.equals("RT01")) {
                            dadosDosProdutosServicos_RT01(linha);
                        }
                        if (wNivel.equals("RT02")) {
                            dadosDosProdutosServicos_RT02(linha);
                        }
                        if (wNivel.equals("RT03")) {
                            dadosDosProdutosServicos_RT03(linha);
                        }
                        if (wNivel.equals("RT04")) {
                            dadosDosProdutosServicos_RT04(linha);
                        }
                        if (wNivel.equals("RT05")) {
                            dadosDosProdutosServicos_RT05(linha);
                        }
                        linha = br.readLine();
                        linha = retornaBarra(linha);
                        wNivel = linha.substring(0, linha.indexOf('|')).trim();
                    }
                }

                if (wNivel.equals("W")) {
                    totaisDaNFe_W(linha);
                }
                if (wNivel.equals("W02")) {
                    totaisDaNFe_W02(linha);
                }
                if (wNivel.equals("RTW01")) {
                    totaisDaNFe_RTW01(linha);
                }
                if (wNivel.equals("RTW02")) {
                    totaisDaNFe_RTW02(linha);
                }
                if (wNivel.equals("X")) {
                    dadosDoTransporte_X(linha);
                }
                if (wNivel.equals("X03")) {
                    dadosDoTransporte_X03(linha);
                }
                if (wNivel.equals("X04")) {
                    dadosDoTransporte_X04(linha);
                }
                if (wNivel.equals("X26")) {
                    while (linha != null
                            && wNivel.equals("X26")) {
                        dadosDoTransporte_X26(linha);
                        linha = br.readLine();
                        linha = retornaBarra(linha);
                        wNivel = linha.substring(0, linha.indexOf('|'));
                    }
                }

                if (wNivel.equals("Y")) {
                    dadosDeCobranca_Y(linha);
                }

                if (wNivel.equals("Y02")) {
                    dadosDeCobranca_Y02(linha);
                }

                if (wNivel.equals("Y07")) {
                    int i = 1;
                    while (linha != null
                            && wNivel.equals("Y07")) {
                        dadosDeCobranca_Y07(linha, i);
                        linha = br.readLine();
                        linha = retornaBarra(linha);
                        wNivel = linha.substring(0, linha.indexOf('|'));
                        i++;
                    }
                }
                if (wNivel.equals("YA")) {
                    dadosInformacoesPagamento_YA(linha);
                }
                if (wNivel.equals("YA01")) {
                    dadosInformacoesPagamento_YA01(linha);
                }
                if (wNivel.equals("Z")) {
                    InfAdicionais_Z(linha);
                }
                linha = br.readLine();
                if (linha != null) {
                    linha = retornaBarra(linha);
                    wNivel = linha.substring(0, linha.indexOf('|'));
                }
            }
            jProgressBar1.setValue(100);
        } catch (IOException ex) {
            Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void VersaoNFe_A(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wA = scan.next();
            gVersao = scan.next();
            break;
        }
    }

    /**
     * B - Dados da Nota Fiscal eletrônica.
     */
    public boolean dadosNfe_B(String linha, String arquivo) {
        modelo1 = (DefaultTableModel) jTable1.getModel();
        //modelo1.setNumRows(0);
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wB = scan.next();
                String wCUF = scan.next();
                String wCNF = scan.next();
                String wNatOp = scan.next();
                //String wIndPag = scan.next();
                String wMod = scan.next();
                String wSerie = scan.next();
                String wNNF = scan.next();
                String wDEmi = scan.next();
                String wDSaiEnt = scan.next();
                String wTpNF = scan.next();
                String widDest = scan.next();
                String wCMunFG = scan.next();
                String wTpImp = scan.next();
                String wTpEmis = scan.next();
                String wCDV = scan.next();
                String wTpAmb = scan.next();
                String wFinNFe = scan.next();
                String windFinal = scan.next();
                String windPres = scan.next();
                String windIntermed = scan.next(); //11/11/2021'
                String wProcEmi = scan.next();
                String wVerProc = scan.next();
                gNNF = wNNF;
                if (wNatOp.trim().equals("DEVOLUCAO")) {
                    wFinNFe = "4";
                }
                if (wCNF.trim().length() <= 0) {
                    wCNF = null;
                }
                //if (wTpAmb.trim() != System.getProperty("tpAmb").trim()) {
                if (!wTpAmb.trim().equals(System.getProperty("tpAmb").trim())) { //18/10/2023
                    jProgressBar1.setIndeterminate(false);
                    int selection = 0;
                    if (System.getProperty("tpAmb").equals("2")) {
                        //selection = JOptionPane.showConfirmDialog(this,
                        //        "Tipo do ambiente da NFe a ser importada difere do tipo de ambiente do sitema.\n"
                        //        + "Deseja alterar de Produção para Homologação?\n"
                        //        + "A NF-e será gerada no sistema com a situação ''Em Digitação''", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        wTpAmb = "2";
                    }
                    if (System.getProperty("tpAmb").equals("1")) {
                        selection = JOptionPane.showConfirmDialog(this,
                                "Tipo do ambiente da NFe a ser importada difere do tipo de ambiente do sitema.\n"
                                + "Deseja alterar de Homologação para Produção?\n"
                                + "A NF-e será gerada no sistema com a situação ''Em Digitação''", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        wTpAmb = "1";
                    }
                    if (selection == JOptionPane.NO_OPTION) {
                        modelo1.addRow(new Object[]{
                            "",
                            arquivo,
                            "",
                            "Rejeitado",
                            "Tipo de Ambiente",
                            "Tipo do ambiente da NFe a ser importada, difere do tipo de ambiente do sitema."
                        });
                        return true;
                    }
                }
                jProgressBar1.setIndeterminate(true);
                boolean wFlagInclusao = false;
                boolean wFlagAutorizada = false;
                boolean wFlagProcessada = false;
                JSONObject jsonNFe = new JSONObject();
                jsonNFe = NfeController.Index(gNNF.trim());
                if (!jsonNFe.isEmpty()) {
                    wFlagInclusao = true;
                    if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Autorizada")) {
                        wFlagAutorizada = true;
                    }
                    if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Processamento na SEFAZ")) {
                        wFlagProcessada = true;
                    }
                }
                if (wFlagAutorizada == true) {
                    modelo1.addRow(new Object[]{
                        "",
                        arquivo,
                        "",
                        "Rejeitado",
                        "",
                        "Nota Fiscal já Autorizada."
                    });
                    return false;
                }
                if (wFlagProcessada == true) {
                    modelo1.addRow(new Object[]{
                        "",
                        arquivo,
                        "",
                        "Rejeitado",
                        "",
                        "Nota Fiscal Em Processamento."
                    });
                    return false;
                }

                boolean wFlagAlteracao = false;
                if (wFlagInclusao == true) {
                    jProgressBar1.setIndeterminate(false);
                    int selection = JOptionPane.showConfirmDialog(this,
                            "Nota Fiscal já cadastrada.\n"
                            + "Deseja sobrescrever?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (selection == JOptionPane.YES_OPTION) {
                        wFlagAlteracao = true;
                    } else {
                        modelo1.addRow(new Object[]{
                            "",
                            arquivo,
                            "",
                            "Rejeitado",
                            "",
                            "Nota Fiscal já cadastrada."
                        });
                        return true;
                    }
                }
                jProgressBar1.setIndeterminate(true);
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
                    wCNF = "76185528";
                    eNFE.setIde_cnf(wCNF);
                    eNFE.setIde_natop(wNatOp);
                    eNFE.setIde_mod(wMod);
                    eNFE.setIde_serie(wSerie);
                    eNFE.setIde_nnf(wNNF);
                    eNFE.setIde_dhemi(wDEmi.replace("T", " ").replace("-03:00", ""));
                    eNFE.setIde_dhsaient(wDSaiEnt.replace("T", " ").replace("-03:00", ""));
                    eNFE.setIde_tpnf(wTpNF);
                    eNFE.setIde_iddest(widDest);
                    eNFE.setIde_cmunfg(wCMunFG);
                    eNFE.setIde_xmunfg("");
                    eNFE.setIde_tpimp(wTpImp);
                    eNFE.setIde_tpemis(wTpEmis);

                    //Inicio 31/01/2024
                    eNFE.setXml_autorizado(null);
                    eNFE.setXml_envevento_autorizado(null);
                    eNFE.setXml_assinado(null);
                    //Fim 31/01/2024

                    //Inicio 06/02/2023
                    if (System.getProperty("contigenciaSVC").equals("TRUE")) {
                        eNFE.setIde_tpemis("6");
                        eNFE.setIde_dhcont(wDEmi.replace("T", " ").replace("-03:00", ""));
                        eNFE.setIde_xjust("Indisponibilidade do ambiente normal de autorização");
                    } else {
                        eNFE.setIde_dhcont(null);
                        eNFE.setIde_xjust("");
                    }
                    //Fim

                    eNFE.setIde_cdv(wCDV);
                    eNFE.setIde_tpamb(wTpAmb);
                    eNFE.setIde_finnfe(wFinNFe);
                    eNFE.setIde_indfinal(windFinal);
                    eNFE.setIde_indpres(windPres);
                    eNFE.setIde_procemi(wProcEmi);
                    eNFE.setIde_verproc(wVerProc);
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wFlagInclusao == false && wFlagAlteracao == false) {
                    modelo1.addRow(new Object[]{
                        "",
                        arquivo,
                        "",
                        "Inserido",
                        "",
                        "Nota Fiscal inserida com sucesso no sistema."
                    });
                }
                if (wFlagAlteracao == true) {
                    modelo1.addRow(new Object[]{
                        "",
                        arquivo,
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

                break;
            } catch (NumberFormatException ex) {
                Exception(ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
        return true;
    }

    /**
     * C - Identificação do Emitente da Nota Fiscal eletrônica
     */
    private void dadosDoEmitente_C(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wC = scan.next();
                String wXNome = scan.next();
                String wXFant = scan.next();
                String wIE = scan.next();
                String wIEST = scan.next();
                String wIM = scan.next();
                String wCNAE = scan.next();
                String wCRT = scan.next();
                gCRT = wCRT;
                if (wIEST.trim().length() <= 0) {
                    wIEST = null;
                }
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setEmit_xnome(wXNome);
                eNFE.setEmit_xfant(wXFant);
                eNFE.setEnderemit_ie(wIE.replace(".", "").replace("/", "").replace("-", ""));
                //eNFE.setEnderemit_iest(new BigInteger(wIEST));
                eNFE.setEnderemit_im(wIM);
                eNFE.setEnderemit_cnae(wCNAE);
                eNFE.setEnderemit_crt(wCRT);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoEmitente_C02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wC02 = scan.next();
                String wCNPJ = scan.next();
                JSONObject jsonNFe = new JSONObject();
                jsonNFe = NfeController.Index(gNNF.trim());
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setEmit_cnpj(wCNPJ);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (SQLException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoEmitente_C05(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wC05 = scan.next();
                String wXLgr = scan.next();
                String wNro = scan.next();
                String wXCpl = scan.next();
                String wXBairro = scan.next();
                String wCMun = scan.next();
                String wXMun = scan.next();
                String wUF = scan.next();
                String wCEP = scan.next();
                String wCPais = scan.next();
                String wXPais = scan.next();
                String wFone = scan.next();
                if (wCMun.trim().length() <= 0) {
                    wCMun = null;
                }
                if (wCEP.trim().length() <= 0) {
                    wCEP = null;
                }
                if (wCPais.trim().length() <= 0) {
                    wCPais = null;
                }
                if (wFone.trim().length() <= 0) {
                    wFone = null;
                }
                char letra_ap = 39;
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    /**
     * E - Identificação do Destinatário da Nota Fiscal eletrônica
     */
    private void dadosDoDestinatario_E(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wE = scan.next();
                String wXNome = scan.next();
                String windIEDest = scan.next();
                String wIE = scan.next();

                wIE = wIE.replace(".", "").replace("/", "").replace("-", "");
                System.out.println("aQUI :" + wIE);

                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setDest_xnome(wXNome);
                eNFE.setEnderdest_indiedest(windIEDest);
                eNFE.setEnderdest_ie(wIE);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoDestinatario_E02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wE02 = scan.next();
                String wCNPJ = scan.next();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setDest_cnpj(wCNPJ);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoDestinatario_E03(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wE03 = scan.next();
                String wCPF = scan.next();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setDest_cnpj(wCPF);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void dadosDoDestinatario_E05(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wE05 = scan.next();
                String wXLgr = scan.next();
                String wNro = scan.next();
                String wXCpl = scan.next();
                String wXBairro = scan.next();
                String wCMun = scan.next();
                String wXMun = scan.next();
                String wUF = scan.next();
                String wCEP = scan.next();
                String wCPais = scan.next();
                String wXPais = scan.next();
                String wFone = "";
                wNro = util.RemoveCharacterSpecials(wNro);
                if (scan.hasNext() == true) {
                    wFone = scan.next();
                }
                if (wCMun.trim().length() <= 0) {
                    wCMun = null;
                }
                if (wCEP.trim().length() <= 0) {
                    wCEP = null;
                }
                if (wCPais.trim().length() <= 0) {
                    wCPais = null;
                }
                if (wFone.trim().length() <= 0) {
                    wFone = null;
                }

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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    /**
     * I - Produtos e Serviços da NF-e.
     */
    private void dadosDosProdutosServicos_H(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wH = scan.next();
            String wNItem = scan.next();
            gNItem = wNItem;
            if (NfeDetProdController.Create(gNNF.trim(), gNItem.trim())) {
                System.out.println("Detalhe dos produtos inserido com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
                Exception(System.getProperty("MsgInvalid"));
            }
            break;
        }
    }

    private void dadosDosProdutosServicos_I(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wI = scan.next();
                String wCProd = scan.next();
                String wCEAN = scan.next();
                String wII = scan.next(); //10/09/2021
                String wXProd = scan.next();
                String wNCM = scan.next();
                String wEXTIPI = scan.next();
                String wCFOP = scan.next();
                String wUCom = scan.next();
                String wQCom = scan.next();
                String wVUnCom = scan.next();
                String wVProd = scan.next();
                String wCEANTrib = scan.next();
                String wIII = scan.next(); //10/09/2021
                String wUTrib = scan.next();
                String wQTrib = scan.next();
                String wVUnTrib = scan.next();
                String wVFrete = scan.next();
                String wVSeg = scan.next();
                String wVDesc = scan.next();
                String wVOutro = scan.next();
                String wIndTot = scan.next();
                wXProd = util.RemoveCharacterSpecials(wXProd);
                if (wCEAN.trim().length() <= 0) {
                    wCEAN = null;
                }
                if (wXProd.trim().length() <= 0) {
                    wXProd = null;
                }
                if (wNCM.trim().length() <= 0) {
                    wNCM = null;
                }
                if (wEXTIPI.trim().length() <= 0) {
                    wEXTIPI = null;
                }
                if (wCFOP.trim().length() <= 0) {
                    wCFOP = null;
                }
                if (wUCom.trim().length() <= 0) {
                    wUCom = null;
                }
                if (wQCom.trim().length() <= 0) {
                    wQCom = null;
                }
                if (wVUnCom.trim().length() <= 0) {
                    wVUnCom = null;
                }
                if (wVProd.trim().length() <= 0) {
                    wVProd = null;
                }
                if (wCEANTrib.trim().length() <= 0) {
                    wCEANTrib = null;
                }
                if (wUTrib.trim().length() <= 0) {
                    wUTrib = null;
                }
                if (wQTrib.trim().length() <= 0) {
                    wQTrib = null;
                }
                if (wVUnTrib.trim().length() <= 0) {
                    wVUnTrib = null;
                }
                if (wVFrete.trim().length() <= 0) {
                    wVFrete = null;
                }
                if (wVSeg.trim().length() <= 0) {
                    wVSeg = null;
                }
                if (wVDesc.trim().length() <= 0) {
                    wVDesc = null;
                }
                if (wVOutro.trim().length() <= 0) {
                    wVOutro = null;
                }
                if (wIndTot.trim().length() <= 0) {
                    wIndTot = null;
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_M(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wM = scan.next();
            break;
        }
    }

    private void dadosDosProdutosServicos_N(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wN = scan.next();
            break;
        }
    }

    private void dadosDosProdutosServicos_N0(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wN0 = scan.next();
                String wOrig = scan.next();
                String wCST = scan.next();
                if (wOrig.trim().length() <= 0) {
                    wOrig = null;
                }
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                if (wN0.equals("N02")) {
                    String wModBC = scan.next();
                    String wVBC = scan.next();
                    String wPICMS = scan.next();
                    String wVICMS = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wPICMS.trim().length() <= 0) {
                        wPICMS = null;
                    }
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N03")) {
                    String wModBC = scan.next();
                    String wVBC = scan.next();
                    String wPICMS = scan.next();
                    String wVICMS = scan.next();
                    String wModBCST = scan.next();
                    String wPMVAST = scan.next();
                    String wPRedBCST = scan.next();
                    String wVBCST = scan.next();
                    String wPICMSST = scan.next();
                    String wVICMSST = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wPICMS.trim().length() <= 0) {
                        wPICMS = null;
                    }
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    if (wModBCST.trim().length() <= 0) {
                        wModBCST = null;
                    }
                    if (wPMVAST.trim().length() <= 0) {
                        wPMVAST = null;
                    }
                    if (wPRedBCST.trim().length() <= 0) {
                        wPRedBCST = null;
                    }
                    if (wVBCST.trim().length() <= 0) {
                        wVBCST = null;
                    }
                    if (wPICMSST.trim().length() <= 0) {
                        wPICMSST = null;
                    }
                    if (wVICMSST.trim().length() <= 0) {
                        wVICMSST = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N04")) {
                    String wModBC = scan.next();
                    String wPRedBC = scan.next();
                    String wVBC = scan.next();
                    String wPICMS = scan.next();
                    String wVICMS = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wPRedBC.trim().length() <= 0) {
                        wPRedBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wPICMS.trim().length() <= 0) {
                        wPICMS = null;
                    }
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N05")) {
                    String wModBCST = scan.next();
                    String wPMVAST = scan.next();
                    String wPRedBCST = scan.next();
                    String wVBCST = scan.next();
                    String wPICMSST = scan.next();
                    String wVICMSST = scan.next();
                    if (wModBCST.trim().length() <= 0) {
                        wModBCST = null;
                    }
                    if (wPMVAST.trim().length() <= 0) {
                        wPMVAST = null;
                    }
                    if (wPRedBCST.trim().length() <= 0) {
                        wPRedBCST = null;
                    }
                    if (wVBCST.trim().length() <= 0) {
                        wVBCST = null;
                    }
                    if (wPICMSST.trim().length() <= 0) {
                        wPICMSST = null;
                    }
                    if (wVICMSST.trim().length() <= 0) {
                        wVICMSST = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N06")) {
                    String wVICMS = scan.next();
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
                    eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                    eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                    eNFE_DET_PROD.setDet_icms40_orig(wOrig);
                    eNFE_DET_PROD.setDet_icms40_cst(wCST);
                    eNFE_DET_PROD.setDet_icms40_vicmsdeson(wVICMS);
                    if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                        System.out.println("Detalhe dos produtos atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N07")) {
                    String wModBC = scan.next();
                    String wpRedBC = scan.next();
                    String wVBC = scan.next();
                    String wpICMS = scan.next();
                    String wvICMSOp = scan.next();
                    String wpDif = scan.next();
                    String wvICMSDif = scan.next();
                    String wvICMS = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wpRedBC.trim().length() <= 0) {
                        wpRedBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wpICMS.trim().length() <= 0) {
                        wpICMS = null;
                    }
                    if (wvICMSOp.trim().length() <= 0) {
                        wvICMSOp = null;
                    }
                    if (wpDif.trim().length() <= 0) {
                        wpDif = null;
                    }
                    if (wvICMSDif.trim().length() <= 0) {
                        wvICMSDif = null;
                    }
                    if (wvICMS.trim().length() <= 0) {
                        wvICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N08")) {
                    String wvBCSTRet = scan.next();
                    String wvICMSSTRet = scan.next();
                    if (wvBCSTRet.trim().length() <= 0) {
                        wvBCSTRet = null;
                    }
                    if (wvICMSSTRet.trim().length() <= 0) {
                        wvICMSSTRet = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N09")) {
                    String wModBC = scan.next();
                    String wpRedBC = scan.next();
                    String wVBC = scan.next();
                    String wPICMS = scan.next();
                    String wVICMS = scan.next();
                    String wModBCST = scan.next();
                    String wPMVAST = scan.next();
                    String wpRedBCST = scan.next();
                    String wVBCST = scan.next();
                    String wPICMSST = scan.next();
                    String wVICMSST = scan.next();
                    String wvICMSDeson = scan.next();
                    String wmotDesICMS = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wpRedBC.trim().length() <= 0) {
                        wpRedBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wPICMS.trim().length() <= 0) {
                        wPICMS = null;
                    }
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    if (wModBCST.trim().length() <= 0) {
                        wModBCST = null;
                    }
                    if (wPMVAST.trim().length() <= 0) {
                        wPMVAST = null;
                    }
                    if (wpRedBCST.trim().length() <= 0) {
                        wpRedBCST = null;
                    }
                    if (wVBCST.trim().length() <= 0) {
                        wVBCST = null;
                    }
                    if (wPICMSST.trim().length() <= 0) {
                        wPICMSST = null;
                    }
                    if (wVICMSST.trim().length() <= 0) {
                        wVICMSST = null;
                    }
                    if (wvICMSDeson.trim().length() <= 0) {
                        wvICMSDeson = null;
                    }
                    if (wmotDesICMS.trim().length() <= 0) {
                        wmotDesICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                if (wN0.equals("N10")) {
                    String wModBC = scan.next();
                    String wpRedBC = scan.next();
                    String wVBC = scan.next();
                    String wPICMS = scan.next();
                    String wVICMS = scan.next();
                    String wModBCST = scan.next();
                    String wPMVAST = scan.next();
                    String wpRedBCST = scan.next();
                    String wVBCST = scan.next();
                    String wPICMSST = scan.next();
                    String wVICMSST = scan.next();
                    String wvICMSDeson = scan.next();
                    String wmotDesICMS = scan.next();
                    if (wModBC.trim().length() <= 0) {
                        wModBC = null;
                    }
                    if (wpRedBC.trim().length() <= 0) {
                        wpRedBC = null;
                    }
                    if (wVBC.trim().length() <= 0) {
                        wVBC = null;
                    }
                    if (wPICMS.trim().length() <= 0) {
                        wPICMS = null;
                    }
                    if (wVICMS.trim().length() <= 0) {
                        wVICMS = null;
                    }
                    if (wModBCST.trim().length() <= 0) {
                        wModBCST = null;
                    }
                    if (wPMVAST.trim().length() <= 0) {
                        wPMVAST = null;
                    }
                    if (wpRedBCST.trim().length() <= 0) {
                        wpRedBCST = null;
                    }
                    if (wVBCST.trim().length() <= 0) {
                        wVBCST = null;
                    }
                    if (wPICMSST.trim().length() <= 0) {
                        wPICMSST = null;
                    }
                    if (wVICMSST.trim().length() <= 0) {
                        wVICMSST = null;
                    }
                    if (wvICMSDeson.trim().length() <= 0) {
                        wvICMSDeson = null;
                    }
                    if (wmotDesICMS.trim().length() <= 0) {
                        wmotDesICMS = null;
                    }
                    eNFE_DET_PROD = new NFE_DET_PROD();
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
                        Exception(System.getProperty("MsgInvalid"));
                    }
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_O(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wO = scan.next();
                String wClEnq = scan.next();
                String wCNPJProd = scan.next();
                String wCSelo = scan.next();
                String wQSelo = scan.next();
                String wCEnq = scan.next();
                if (wCEnq.trim().length() <= 0) {
                    wCEnq = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_ipi_cenq(wCEnq);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_O07(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wO07 = scan.next();
                String wCST = scan.next();
                String wVBC = "0.00";
                String wPIPI = "0.0000";
                String wVIPI = "0.00";
                if (scan.hasNext() == true) {
                    wVIPI = scan.next();
                }
                if (scan.hasNext() == true) {
                    wVBC = scan.next();
                }
                if (scan.hasNext() == true) {
                    wPIPI = scan.next();
                }
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                if (wVIPI.trim().length() <= 0) {
                    wVIPI = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_O08(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wO08 = scan.next();
                String wCST = scan.next();
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_ipitrib_cst(wCST);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_O10(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wO10 = scan.next();
                String wVBC = scan.next();
                String wPIPI = scan.next();
                if (wVBC.trim().length() <= 0) {
                    wVBC = null;
                }
                if (wPIPI.trim().length() <= 0) {
                    wPIPI = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_ipitrib_vbc(wVBC);
                eNFE_DET_PROD.setDet_ipitrib_pipi(wPIPI);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_Q(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wQ = scan.next();
            break;
        }
    }

    private void dadosDosProdutosServicos_Q02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wQ02 = scan.next();
                String wCST = scan.next();
                String wVBC = scan.next();
                String wPPIS = scan.next();
                String wVPIS = scan.next();
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                if (wVBC.trim().length() <= 0) {
                    wVBC = null;
                }
                if (wPPIS.trim().length() <= 0) {
                    wPPIS = null;
                }
                if (wVPIS.trim().length() <= 0) {
                    wVPIS = null;
                }
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_Q04(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wQ04 = scan.next();
                String wCST = scan.next();
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_pisaliq_cst(wCST);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_Q05(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wQ05 = scan.next();
                String wCST = scan.next();
                String wVPIS = scan.next();
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                if (wVPIS.trim().length() <= 0) {
                    wVPIS = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_pisoutr_cst(wCST);
                eNFE_DET_PROD.setDet_pisoutr_vpis(wVPIS);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_Q07(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wQ07 = scan.next();
                String wVBC = scan.next();
                String wPPIS = "0.0000";
                if (scan.hasNext() == true) {
                    wPPIS = scan.next();
                }
                if (wVBC.trim().length() <= 0) {
                    wVBC = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_pisoutr_vbc(wVBC);
                eNFE_DET_PROD.setDet_pisoutr_ppis(wPPIS);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_RT01(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRT01 = scan.next();
                String wncm = scan.next();
                String wcst = scan.next();
                String wcclasstrib = scan.next();
                String wquantidade = scan.next();
                String wibscbs_vbc = scan.next();
                String wibsuf_pibsuf = scan.next();
                String wibsuf_vibsuf = scan.next();
                String wibsmun_pibsmun = scan.next();
                String wibsmun_vibsmun = scan.next();
                String wcbs_pcbs = scan.next();
                String wcbs_vcbs = scan.next();

                if (wibsuf_pibsuf.trim().length() <= 0) {
                    wibsuf_pibsuf = null;
                }
                if (wibsuf_vibsuf.trim().length() <= 0) {
                    wibsuf_vibsuf = null;
                }
                if (wibsmun_pibsmun.trim().length() <= 0) {
                    wibsmun_pibsmun = null;
                }
                if (wibsmun_vibsmun.trim().length() <= 0) {
                    wibsmun_vibsmun = null;
                }
                if (wcbs_pcbs.trim().length() <= 0) {
                    wcbs_pcbs = null;
                }
                if (wcbs_vcbs.trim().length() <= 0) {
                    wcbs_vcbs = null;
                }

                if (wcst.trim().length() <= 0) {
                    wcst = "000";
                }
                if (wcclasstrib.trim().length() <= 0) {
                    wcclasstrib = "000001";
                }
                if (wibscbs_vbc.trim().length() <= 0) {
                    wibscbs_vbc = null;
                }
                             
                /*
                CALCULADORAENVIAdto.ItemEnvio itemEnvio = new CALCULADORAENVIAdto.ItemEnvio();
                itemEnvio.setNumero(1);
                itemEnvio.setNcm(wncm);
                itemEnvio.setBaseCalculo(
                        (wibscbs_vbc != null && !wibscbs_vbc.trim().isEmpty() && wibscbs_vbc.trim().matches("[-+]?\\d*\\.?\\d+"))
                        ? Double.parseDouble(wibscbs_vbc.replace(",", "."))
                        : 0.0
                );

                itemEnvio.setQuantidade(
                        (wquantidade != null && !wquantidade.trim().isEmpty() && wquantidade.trim().matches("[-+]?\\d*\\.?\\d+"))
                        ? Double.parseDouble(wquantidade.replace(",", "."))
                        : 0.0
                );
                cALCULADORARETORNOdto = dadosCALCULADORA(itemEnvio);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat df = new DecimalFormat("0.0000", symbols);
                String pIBSUF = df.format(
                        Double.parseDouble(
                                cALCULADORARETORNOdto.getObjetos().get(0)
                                        .getTribCalc()
                                        .getIbscbs()
                                        .getGibscbs()
                                        .getGibSUF()
                                        .getpIBSUF()
                        )
                );

                String vIBSUF = cALCULADORARETORNOdto.getObjetos().get(0).
                        getTribCalc().
                        getIbscbs().
                        getGibscbs().
                        getGibSUF().
                        getvIBSUF();

                double valorVIBSUF = Double.parseDouble(
                        cALCULADORARETORNOdto.getObjetos().get(0)
                                .getTribCalc()
                                .getIbscbs()
                                .getGibscbs()
                                .getGibSUF()
                                .getvIBSUF()
                );
                totalVIBSUF += valorVIBSUF;
                String pIBSMun = df.format(
                        Double.parseDouble(cALCULADORARETORNOdto.getObjetos().get(0).
                                getTribCalc().
                                getIbscbs().
                                getGibscbs().
                                getGibSMun().
                                getpIBSMun()));

                String vIBSMun = cALCULADORARETORNOdto.getObjetos().get(0).
                        getTribCalc().
                        getIbscbs().
                        getGibscbs().
                        getGibSMun().
                        getvIBSMun();

                double valorVIBSMun = Double.parseDouble(
                        cALCULADORARETORNOdto.getObjetos().get(0)
                                .getTribCalc()
                                .getIbscbs()
                                .getGibscbs()
                                .getGibSMun()
                                .getvIBSMun()
                );
                totalVIBSMun += valorVIBSMun;

                String pCBS = df.format(
                        Double.parseDouble(cALCULADORARETORNOdto.getObjetos().get(0).
                                getTribCalc().
                                getIbscbs().
                                getGibscbs().
                                getGcbs().getpCBS()));

                String vCBS = cALCULADORARETORNOdto.getObjetos().get(0).
                        getTribCalc().
                        getIbscbs().
                        getGibscbs().
                        getGcbs().getvCBS();

                double valorVCBS = Double.parseDouble(
                        cALCULADORARETORNOdto.getObjetos().get(0)
                                .getTribCalc()
                                .getIbscbs()
                                .getGibscbs()
                                .getGcbs()
                                .getvCBS()
                );
                totalVCBS += valorVCBS;

                if (!pIBSUF.equals(wibsuf_pibsuf)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Alíquota [" + wibsuf_pibsuf + "] da UF do IBS informada no Módulo 01, difere da alíquota [" + pIBSUF + "] de referência."
                    });
                }

                if (!vIBSUF.equals(wibsuf_vibsuf)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Valor [" + wibsuf_vibsuf + "] da UF do IBS informado no Módulo 01, difere do valor [" + vIBSUF + "] calculado pela Receita."
                    });
                }

                if (!pIBSMun.equals(wibsmun_pibsmun)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Alíquota [" + wibsmun_pibsmun + "] do Município do IBS informada no Módulo 01, difere da alíquota [" + pIBSMun + "] de referência."
                    });
                }

                if (!vIBSMun.equals(wibsmun_vibsmun)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Valor [" + wibsmun_vibsmun + "] do Município do IBS informado no Módulo 01, difere do valor [" + vIBSMun + "] calculado pela Receita."
                    });
                }

                if (!pCBS.equals(wcbs_pcbs)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Alíquota [" + wcbs_pcbs + "] do CBS informada no Módulo 01, difere da alíquota [" + pCBS + "] de referência."
                    });
                }

                if (!vCBS.equals(wcbs_vcbs)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "",
                        gNNF,
                        "Item " + gNItem,
                        "Rejeitado",
                        "Grupo IBSCBS",
                        "Valor [" + wcbs_vcbs + "] do CBS informado no Módulo 01, difere do valor [" + vCBS + "] calculado pela Receita."
                    });
                }
                 */
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());

                eNFE_DET_PROD.setDet_ibs_cst(wcst);
                eNFE_DET_PROD.setDet_ibs_cclasstrib(wcclasstrib);
                eNFE_DET_PROD.setDet_ibscbs_vbc(wibscbs_vbc);

                eNFE_DET_PROD.setDet_is_cst(wcst);
                eNFE_DET_PROD.setDet_is_cclasstrib(wcclasstrib);

                eNFE_DET_PROD.setDet_cbs_cst(wcst);
                eNFE_DET_PROD.setDet_cbs_cclasstrib(wcclasstrib);

                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_RT02(String linha) throws SQLException, IOException, InterruptedException {

        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRT02 = scan.next();
                String wibsuf_vbcuf = scan.next();
                String wibsuf_pibsuf = scan.next();
                String wibsuf_vibsuf = scan.next();
                if (wibsuf_vbcuf.trim().length() <= 0) {
                    wibsuf_vbcuf = null;
                }
                if (wibsuf_pibsuf.trim().length() <= 0) {
                    wibsuf_pibsuf = null;
                }
                if (wibsuf_vibsuf.trim().length() <= 0) {
                    wibsuf_vibsuf = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_ibsuf_vbc(wibsuf_vbcuf);
                eNFE_DET_PROD.setDet_ibsuf_pibsuf(wibsuf_pibsuf);
                eNFE_DET_PROD.setDet_ibsuf_vibsuf(wibsuf_vibsuf);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_RT03(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRT03 = scan.next();
                String wibsmun_vbcmun = scan.next();
                String wibsmun_pibsmun = scan.next();
                String wibsmun_vibsmun = scan.next();
                if (wibsmun_vbcmun.trim().length() <= 0) {
                    wibsmun_vbcmun = null;
                }
                if (wibsmun_pibsmun.trim().length() <= 0) {
                    wibsmun_pibsmun = null;
                }
                if (wibsmun_vibsmun.trim().length() <= 0) {
                    wibsmun_vibsmun = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_ibsmun_vbc(wibsmun_vbcmun);
                eNFE_DET_PROD.setDet_ibsmun_pibsmun(wibsmun_pibsmun);
                eNFE_DET_PROD.setDet_ibsmun_vibsmun(wibsmun_vibsmun);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_RT04(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRT03 = scan.next();
                String wis_vbcimpsel = scan.next();
                String wis_pimpsel = scan.next();
                String wis_vimpsel = scan.next();
                if (wis_vbcimpsel.trim().length() <= 0) {
                    wis_vbcimpsel = null;
                }
                if (wis_pimpsel.trim().length() <= 0) {
                    wis_pimpsel = null;
                }
                if (wis_vimpsel.trim().length() <= 0) {
                    wis_vimpsel = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_is_vbcimpsel(wis_vbcimpsel);
                eNFE_DET_PROD.setDet_is_pimpsel(wis_pimpsel);
                eNFE_DET_PROD.setDet_is_vimpsel(wis_vimpsel);

                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_RT05(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRT03 = scan.next();
                String wcbs_vbc = scan.next();
                String wcbs_pcbs = scan.next();
                String wcbs_vcbs = scan.next();
                if (wcbs_vbc.trim().length() <= 0) {
                    wcbs_vbc = null;
                }
                if (wcbs_pcbs.trim().length() <= 0) {
                    wcbs_pcbs = null;
                }
                if (wcbs_vcbs.trim().length() <= 0) {
                    wcbs_vcbs = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_cbs_vbc(wcbs_vbc);
                eNFE_DET_PROD.setDet_cbs_pcbs(wcbs_pcbs);
                eNFE_DET_PROD.setDet_cbs_vcbs(wcbs_vcbs);

                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_S(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wS = scan.next();
            break;
        }
    }

    private void dadosDosProdutosServicos_S02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wS02 = scan.next();
                String wCST = scan.next();
                String wVBC = scan.next();
                String wPCOFINS = scan.next();
                String wVCOFINS = scan.next();
                if (wVBC.trim().length() <= 0) {
                    wVBC = null;
                }
                if (wPCOFINS.trim().length() <= 0) {
                    wPCOFINS = null;
                }
                if (wVCOFINS.trim().length() <= 0) {
                    wVCOFINS = null;
                }
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_S04(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wS04 = scan.next();
                String wCST = scan.next();
                if (wCST.trim().length() <= 0) {
                    wCST = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_cofinsaliq_cst(wCST);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_S05(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wS05 = scan.next();
                String wCST = scan.next();
                String wVCOFINS = scan.next();
                if (wVCOFINS.trim().length() <= 0) {
                    wVCOFINS = null;
                }

                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_cofinsoutr_cst(wCST);
                eNFE_DET_PROD.setDet_cofinsoutr_vcofins(wVCOFINS);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDosProdutosServicos_S07(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wS07 = scan.next();
                String wVBC = scan.next();
                String wPCOFINS = "0.0000";
                if (scan.hasNext() == true) {
                    wPCOFINS = scan.next();
                }
                if (wVBC.trim().length() <= 0) {
                    wVBC = null;
                }
                NFE_DET_PROD eNFE_DET_PROD = new NFE_DET_PROD();
                eNFE_DET_PROD.setDet_prod_nnf(gNNF.trim());
                eNFE_DET_PROD.setDet_prod_item(gNItem.trim());
                eNFE_DET_PROD.setDet_cofinsoutr_vbc(wVBC);
                eNFE_DET_PROD.setDet_cofinsoutr_pcofins(wPCOFINS);
                if (NfeDetProdController.Update(eNFE_DET_PROD)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void totaisDaNFe_W(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wW = scan.next();
            break;
        }
    }

    private void totaisDaNFe_W02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wW02 = scan.next();
                String wVBC = scan.next();
                String wVICMS = scan.next();
                String wvICMSDeson = scan.next();
                String wvvFCP = scan.next();
                String wvFCPUFDest = scan.next();
                String wvICMSUFDest = scan.next();
                String wVBCST = scan.next();
                String wVST = scan.next();
                String wVProd = scan.next();
                String wVFrete = scan.next();
                String wVSeg = scan.next();
                String wVDesc = scan.next();
                String wVII = scan.next();
                String wVIPI = scan.next();
                String wvIPIDevol = scan.next();
                String wVPIS = scan.next();
                String wVCOFINS = scan.next();
                String wVOutro = scan.next();
                String wVNF = scan.next();
                String wvtottrib = scan.next();

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
                eNFE.setIcmstot_vtottrib(wvtottrib);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void totaisDaNFe_RTW01(String linha, Boolean flag) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRTW01 = scan.next();
                String wTOT_VBCIBSCBS = scan.next();
                String wTOT_IS_VIMPSEL = scan.next();
                String wTOT_IBS_UF_VIBS = scan.next();
                String wTOT_IBS_MUN_VIBS = scan.next();
                String wTOT_CBS_VCBS = scan.next();

                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());

                eNFE.setTot_vbcibscbs(wTOT_VBCIBSCBS);
                eNFE.setTot_is_vimpsel(wTOT_IS_VIMPSEL);
                eNFE.setTot_ibs_uf_vibs(wTOT_IBS_UF_VIBS);
                eNFE.setTot_ibs_mun_vibs(wTOT_IBS_MUN_VIBS);
                eNFE.setTot_cbs_vcbs(wTOT_CBS_VCBS);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void totaisDaNFe_RTW01(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", symbols);

        while (scan.hasNext()) {
            try {
                String wRTW01 = scan.next();
                String wTOT_VBCIBSCBS = scan.next();
                String wTOT_IS_VIMPSEL = scan.next();
                String wTOT_IBS_UF_VIBS = scan.next();
                String wTOT_IBS_MUN_VIBS = scan.next();
                String wTOT_CBS_VCBS = scan.next();

                /*
                if (!df.format(totalVIBSUF).equals(wTOT_IBS_UF_VIBS)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "", gNNF, "Total ", "Rejeitado", "Grupo IBSCBS",
                        "Valor total [" + wTOT_IBS_UF_VIBS + "] da UF do IBS informada no Módulo 01, difere do valor total [" + df.format(totalVIBSUF) + "] calculado pela Receita."
                    });
                }

                if (!df.format(totalVIBSMun).equals(wTOT_IBS_MUN_VIBS)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "", gNNF, "Total ", "Rejeitado", "Grupo IBSCBS",
                        "Valor total [" + wTOT_IBS_MUN_VIBS + "] do Município do IBS informada no Módulo 01, difere do valor total [" + df.format(totalVIBSMun) + "] calculado pela Receita."
                    });
                }

                if (!df.format(totalVCBS).equals(wTOT_CBS_VCBS)) {
                    System.out.println(System.getProperty("MsgInvalid"));
                    modelo1.addRow(new Object[]{
                        "", gNNF, "Total ", "Rejeitado", "Grupo IBSCBS",
                        "Valor total [" + wTOT_CBS_VCBS + "] da CBS informada no Módulo 01, difere do valor total [" + df.format(totalVCBS) + "] calculado pela Receita."
                    });
                }
                 */
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setTot_vbcibscbs(wTOT_VBCIBSCBS);
                eNFE.setTot_is_vimpsel(wTOT_IS_VIMPSEL);
                eNFE.setTot_ibs_uf_vibs(wTOT_IBS_UF_VIBS);
                eNFE.setTot_ibs_mun_vibs(wTOT_IBS_MUN_VIBS);
                eNFE.setTot_cbs_vcbs(wTOT_CBS_VCBS);

                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;

            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void totaisDaNFe_RTW02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wRTW02 = scan.next();
                String wTOT_VTOTNF = scan.next();

                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());

                eNFE.setTot_vtotnf(wTOT_VTOTNF);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_X(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wX = scan.next();
                String wModFrete = scan.next();
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setTransp_modfrete(wModFrete);
                if (NfeController.Update(eNFE)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_X03(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wX03 = scan.next();
                String wXNome = scan.next();
                String wIE = scan.next();
                String wXEnder = scan.next();
                String wXMun = scan.next();
                String wUF = scan.next();
                if (wIE.trim().length() <= 0) {
                    wIE = null;
                }
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_X04(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wX04 = scan.next();
                String wCNPJ = scan.next();

                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setTransporta_cnpj(wCNPJ);
                if (NfeController.Update(eNFE)) {
                    System.out.println("Detalhe dos produtos atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDoTransporte_X26(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wX26 = scan.next();
                String wQVol = scan.next();
                String wEsp = scan.next();
                String wMarca = scan.next();
                String wNVol = scan.next();
                String wPesoL = scan.next();
                String wPesoB = scan.next();
                if (wQVol.trim().length() <= 0) {
                    wQVol = null;
                }
                if (wPesoL.trim().length() <= 0) {
                    wPesoL = null;
                }
                if (wPesoB.trim().length() <= 0) {
                    wPesoB = null;
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDeCobranca_Y(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wY = scan.next();
            break;
        }
    }

    private void dadosInformacoesPagamento_YA(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            String wYA = scan.next();
            break;
        }
    }

    private void dadosInformacoesPagamento_YA01(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wYA01 = scan.next();
                String windPag = scan.next();
                String wtpag = scan.next();
                String wYA011 = scan.next();
                String wvPag = scan.next();

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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDeCobranca_Y02(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wY02 = scan.next();
                String wNFat = scan.next();
                String wVOrig = scan.next();
                String wVDesc = scan.next();
                String wVLiq = scan.next();
                if (wVOrig.length() <= 0) {
                    wVOrig = "0.0";
                }
                if (wVDesc.length() <= 0) {
                    wVDesc = "0.0";
                }
                if (wVLiq.length() <= 0) {
                    wVLiq = "0.0";
                }
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
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void dadosDeCobranca_Y07(String linha, int i) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wY07 = scan.next();
                String wNDup = scan.next();
                String wDVenc = scan.next();
                String wVDup = scan.next();
                if (wVDup.length() <= 0) {
                    wVDup = "0.0";
                }
                NfeCobrDupController.Create(gNNF.trim(), Integer.toString(i));
                NFE_COBR_DUP eNFE_COBR_DUP = new NFE_COBR_DUP();
                eNFE_COBR_DUP.setCobr_dup_item(Integer.toString(i));
                eNFE_COBR_DUP.setCobr_dup_nnf(gNNF.trim());
                eNFE_COBR_DUP.setCobr_dup_ndup(wNDup);
                eNFE_COBR_DUP.setCobr_dup_dvenc(wDVenc.replace("T", " ").replace("-03:00", ""));
                eNFE_COBR_DUP.setCobr_dup_vdup(wVDup);
                if (NfeCobrDupController.Update(eNFE_COBR_DUP)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    private void InfAdicionais_Z(String linha) {
        Scanner scan = new Scanner(linha).useDelimiter("\\||\\n");
        while (scan.hasNext()) {
            try {
                String wZ = scan.next();
                String winfAdFisco = scan.next();
                String winfCpl = scan.next();
                winfAdFisco = util.RemoveCharacterSpecials(winfAdFisco);
                winfCpl = util.RemoveCharacterSpecials(winfCpl);
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(gNNF.trim());
                eNFE.setInfadic_infadfisco(winfAdFisco);
                eNFE.setInfadic_infcpl(winfCpl);
                if (NfeController.Update(eNFE)) {
                    System.out.println("NFe atualizado com sucesso!");
                } else {
                    System.out.println(System.getProperty("MsgInvalid"));
                    Exception(System.getProperty("MsgInvalid"));
                }
                break;
            } catch (InstantiationException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ImportaArquivoTXT.class.getName()).log(Level.SEVERE, null, ex);
                Exception(ex.getMessage());
            }
        }
    }

    public String retornaBarra(String linha) {
        char c = '|';
        boolean wpro = false;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == c) {
                wpro = true;
            }
        }
        if (wpro == false) {
            linha = linha + "|";
        }
        return linha;
    }

    public void Exception(String log) {
        if (log == null) {
            return;
        }

        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner scan = new Scanner(line).useDelimiter("\\||\\n");
            String wGrupo = "";
            while (scan.hasNext()) {
                wGrupo = scanner.next();
            }
            modelo1.addRow(new Object[]{
                "Grupo",
                gNNF,
                "Erro",
                wGrupo
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
            // Início: Atualiza a UI no thread da interface
            SwingUtilities.invokeLater(() -> {
                Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                jDesktopPane2.setCursor(cursor);
                jLabel1.setText("Importando Nota Fiscal (NF-e)");
                jLabel2.setText("Por favor, aguarde.");
                jProgressBar1.setIndeterminate(true);
                jProgressBar1.setMinimum(0);
                jProgressBar1.setMaximum(tListaArquivosTXT.size());
                jProgressBar1.setValue(0);
                jButton1.setEnabled(false);
                jLabel1.setVisible(true);
                jLabel2.setVisible(true);
            });
            Iterator itArquivoTXTNFe = tListaArquivosTXT.iterator();
            int i = 0;

            while (itArquivoTXTNFe.hasNext()) {
                jProgressBar1.setIndeterminate(true);
                jProgressBar1.setValue(i);
                importarNfeTxt(tCaminhoArquivo + "\\", itArquivoTXTNFe.next().toString());
                i++;
                jProgressBar1.setIndeterminate(false);
            }
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            jDesktopPane2.setCursor(cursor);
        }
    }

    private CALCULADORARETORNOdto dadosCALCULADORA(CALCULADORAENVIAdto.ItemEnvio itemEnvio) throws SQLException, IOException, InterruptedException {
        // 1. Montar DTO de envio para a calculadora com os dados do item

        itemEnvio.setUnidade("tn");
        itemEnvio.setCst("000"); // ou map.get(i).getString("det_ibscbs_cst") se houver
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
        //CALCULADORARETORNOdto resultado = getCalculadoraRetorno(calculadoraEnvio);
        return null;
    }
}
