package app.views.operations.nfe.modal;

import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;
import app.views.operations.nfe.NFe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
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
import util.formata;

public class ConsultaProtocoloReciboNFe extends javax.swing.JInternalFrame {

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

    public ConsultaProtocoloReciboNFe(
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
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(100);
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
        jTable1.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(535);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(wCol3);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
        JSONObject jsonNFe = new JSONObject();
        jsonNFe = NfeController.Index(gNNF.trim());
        SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (!jsonNFe.isEmpty() && jsonNFe.getString("infnfe_id") != null) {
            //tf_recibo.setText(jsonNFe.getString("infnfe_id").trim().replace("NFe", ""));
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
        tf_recibo = new javax.swing.JTextField();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("Consulta Protocolo/Recibo da Nota Fiscal Eletrônica na SEFAZ - Revisão: 02 - Data da Última Revisão: 18/10/2023 - Data da Elaboração: 10/10/2023");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta Protocolo/Recibo da Nota Fiscal Eletrônica na SEFAZ ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

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
        jLabel47.setText("Confirme ou Digite um novo protocolo/recibo:");

        tf_recibo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_recibo.setToolTipText("");
        tf_recibo.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_recibo.setDoubleBuffered(true);

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_recibo, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)
                                .addComponent(jButton1)))
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (tf_recibo.getText().trim().length() <= 0) {
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tf_recibo;
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

    private void ConsultarNfeSEFAZ(String nNNF, Integer nt) throws UnsupportedEncodingException {

        try {
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            //Informe a chave a ser Consultada
            String chave = tf_recibo.getText().trim();
            TRetConsReciNFe retorno = Nfe.consultaRecibo(configINI.iniciaConfiguracoes(), chave, DocumentoEnum.NFE);
            info("| CStat........: " + retorno.getCStat());
            info("| DhRecbto.....: " + retorno.getDhRecbto());
            info("| TpAmb........: " + retorno.getTpAmb());
            info("| VerAplic.....: " + retorno.getVerAplic());
            info("| Versao.......: " + retorno.getVersao());
            info("| XMotivo......: " + retorno.getXMotivo());
            info("| CMsg.........: " + retorno.getCStat());
            info("| XMsg.........: " + retorno.getXMotivo());
            info("| ChNFe........: " + retorno.getProtNFe().get(0).getInfProt().getChNFe().substring(34, 35));
            if (retorno.getCStat().equals("217")) {
                System.setProperty("gCStat", retorno.getCStat());
                TableResumoOperacoes(retorno.getCStat(), retorno.getXMotivo());
                Exception("");
                return;
            } else {
                System.setProperty("gCStat", retorno.getProtNFe().get(0).getInfProt().getCStat());
                TableResumoOperacoes(retorno.getProtNFe().get(0).getInfProt().getCStat(), retorno.getProtNFe().get(0).getInfProt().getXMotivo());
            }
            //Autorizada
            if (retorno.getProtNFe().get(0).getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
                TEnviNFe enviNFe = new TEnviNFe();
                enviNFe = MontaNfe.main(nNNF);
                enviNFe.getNFe().get(0).getInfNFe().setId("NFe" + retorno.getProtNFe().get(0).getInfProt().getChNFe());//03/02/2023
                enviNFe.getNFe().get(0).getInfNFe().getIde().setTpEmis(retorno.getProtNFe().get(0).getInfProt().getChNFe().substring(34, 35));//03/02/2023
                enviNFe.getNFe().get(0).getInfNFe().getIde().setCNF(retorno.getProtNFe().get(0).getInfProt().getChNFe().substring(35, 43));//03/02/2023
                enviNFe.getNFe().get(0).getInfNFe().getIde().setCDV(retorno.getProtNFe().get(0).getInfProt().getChNFe().substring(43, 44));//03/02/2023

                // Monta e Assina o XML
                enviNFe = Nfe.montaNfe(configINI.iniciaConfiguracoes(), enviNFe, true);
                System.out.println();
                System.out.println("# Status: " + retorno.getProtNFe().get(0).getInfProt().getCStat() + " - " + retorno.getProtNFe().get(0).getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retorno.getProtNFe().get(0).getInfProt().getNProt());
                System.out.println("# XML Final: " +criaNfeProc(enviNFe, retorno.getProtNFe()));
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(nNNF.trim());
                eNFE.setInfnfe_statusnfe("Autorizada");
                eNFE.setInfnfe_id(retorno.getProtNFe().get(0).getInfProt().getChNFe());
                eNFE.setInfprot_nprot(retorno.getProtNFe().get(0).getInfProt().getNProt());
                eNFE.setInfprot_dhrecbto(retorno.getProtNFe().get(0).getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                eNFE.setIde_tpemis(enviNFe.getNFe().get(0).getInfNFe().getIde().getTpEmis()); //03/02/2023
                eNFE.setIde_cnf(enviNFe.getNFe().get(0).getInfNFe().getIde().getCNF()); //03/02/2023
                eNFE.setIde_cdv(enviNFe.getNFe().get(0).getInfNFe().getIde().getCDV()); //03/02/2023
                eNFE.setXml_autorizado(criaNfeProc(enviNFe, retorno.getProtNFe()));
                //if (NfeController.Update(eNFE)) {
                //    System.out.println("NFe atualizado com sucesso!");
                //} else {
                //    System.out.println(System.getProperty("MsgInvalid"));
                //}
                //NfeInfProtController.Delete(nNNF);
                NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
                NfeInfProtController.Create(nNNF.trim(), retorno.getProtNFe().get(0).getInfProt().getCStat());
                eNFE_INFPROT.setProtnfe_nnf(nNNF.trim());
                eNFE_INFPROT.setProtnfe_versao(retorno.getProtNFe().get(0).getVersao());
                eNFE_INFPROT.setProtnfe_sequencia("1");
                eNFE_INFPROT.setInfprot_cstat(retorno.getProtNFe().get(0).getInfProt().getCStat());
                eNFE_INFPROT.setInfprot_chnfe(retorno.getProtNFe().get(0).getInfProt().getChNFe());
                eNFE_INFPROT.setInfprot_dhrecbto(retorno.getProtNFe().get(0).getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                eNFE_INFPROT.setInfprot_nid(retorno.getProtNFe().get(0).getInfProt().getId());
                eNFE_INFPROT.setInfprot_nprot(retorno.getProtNFe().get(0).getInfProt().getNProt());
                eNFE_INFPROT.setInfprot_tpamb(retorno.getProtNFe().get(0).getInfProt().getTpAmb());
                eNFE_INFPROT.setInfprot_veraplic(retorno.getProtNFe().get(0).getInfProt().getVerAplic());
                eNFE_INFPROT.setInfprot_xmotivo(retorno.getProtNFe().get(0).getInfProt().getXMotivo().replace("Autorizado o uso da NF-e", "Autorização de Uso"));
                //NfeInfProtController.Update(eNFE_INFPROT);
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
                    Date date = df.parse(retorno.getProtNFe().get(0).getInfProt().getDhRecbto().toString().replace("T", " ").replace("-03:00", ""));
                    String wwDhRecbto = formatador1.format(date);
                    veioDo_NFe_frm.retornaRegistro("Autorizada", wwDhRecbto, nt, w_ide_xtpemis, null);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (NfeException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ConsultaProtocoloReciboNFe.class.getName()).log(Level.SEVERE, null, ex);
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
