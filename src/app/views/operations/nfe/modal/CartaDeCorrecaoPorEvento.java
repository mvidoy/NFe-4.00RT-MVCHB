package app.views.operations.nfe.modal;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envcce.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.CartaCorrecaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.json.JSONObject;
import lib.ConfiguracoesNfeIniciais;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.formata;
import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;
import app.views.operations.nfe.NFe;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;

public class CartaDeCorrecaoPorEvento extends javax.swing.JInternalFrame {

    private NFe veioDoframe1;
    private JInternalFrame parent;
    private static final int SSL_PORT = 443;
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

    public CartaDeCorrecaoPorEvento(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wAno,
            String wSerie,
            String wNFe) {
        gAno = wAno;
        gSerie = wSerie;
        gNNF = wNFe;
        this.jDesktopPane2 = jDesktop;
        initComponents();
        Date dataAtual = new Date();
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = "dd/MM/yyyy HH:mm:ss";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String wwDhRegEvento = formatador1.format(dataAtual);
        tf_DhEvento.setText(wwDhRegEvento);
        tf_nseqevento.setText("1");
        jTxtP_correcao.setText("");
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
        jTable1.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(1535);
        jTable1.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(wCol3);
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
            String text = table.getValueAt(row, 4).toString();
            if (text.contains("135")
                    || text.contains("128")
                    || text.contains("155")) {
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
            if (text.contains("135")
                    || text.contains("128")
                    || text.contains("155")) {
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
        botao_Enviar = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        tf_nseqevento = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tf_DhEvento = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtP_correcao = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLb_HitoricosEventos = new javax.swing.JLabel();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setTitle("Carta de Correção  - Revisão: 05 - Data da Última Revisão: 03/02/2023 - Data da Elaboração: 09/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carta De Correção", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

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

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("* Número de Sequência");

        tf_nseqevento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_nseqevento.setToolTipText("");
        tf_nseqevento.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_nseqevento.setDoubleBuffered(true);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("* Correção");

        tf_DhEvento.setEditable(false);
        tf_DhEvento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_DhEvento.setToolTipText("");
        tf_DhEvento.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_DhEvento.setDoubleBuffered(true);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("* Data e Hora do Evento");

        jScrollPane1.setViewportView(jTxtP_correcao);

        jLabel8.setText("mínimo de 15 e máximo de 1000 caracteres.");

        jLabel9.setText("(*) Campo de preenchimento obrigatório.");

        jLb_HitoricosEventos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLb_HitoricosEventos.setForeground(new java.awt.Color(255, 0, 0));
        jLb_HitoricosEventos.setText("<html> <table align=left> <td color=\"red\" align=left> \nA Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com:<br>&nbsp&nbsp;\nI - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao;</br> <br>&nbsp&nbsp;\nII - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario;</br> <br>&nbsp&nbsp;\nIII - a data de emissao ou de saida.\n</br> </td> </html>");
        jLb_HitoricosEventos.setToolTipText("");
        jLb_HitoricosEventos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLb_HitoricosEventos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_Enviar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_nseqevento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_DhEvento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLb_HitoricosEventos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 465, Short.MAX_VALUE)
                .addComponent(botao_Enviar)
                .addGap(2, 2, 2))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(tf_DhEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(tf_nseqevento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLb_HitoricosEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel8)
                .addGap(170, 170, 170)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(botao_Enviar))
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_DhEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_nseqevento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLb_HitoricosEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
        );

        jLb_HitoricosEventos.getAccessibleContext().setAccessibleName("<html> <table align=left> <td color=\"black\" align=left> \nA Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com:<br>&nbsp&nbsp;\nI - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao;</br> <br>&nbsp&nbsp;\nII - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario;</br> <br>&nbsp&nbsp;\nII - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario;</br>");

        botao_sair.setMnemonic('L');
        botao_sair.setText("Fechar");
        botao_sair.setToolTipText("");
        botao_sair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void botao_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_EnviarActionPerformed
        Process process = new Process();
        process.start();
    }//GEN-LAST:event_botao_EnviarActionPerformed

    private void botao_EnviarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_EnviarMouseMoved
        botao_Enviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_EnviarMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_Enviar;
    private javax.swing.JButton botao_sair;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLb_HitoricosEventos;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTxtP_correcao;
    private javax.swing.JTextField tf_DhEvento;
    private javax.swing.JTextField tf_nseqevento;
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

    private void CartaDeCorrecao(String nNNF) throws SQLException, UnsupportedEncodingException {

        try {
            NFE eNFE = new NFE();
            NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(nNNF.trim());
            if (jsonNFe.isEmpty()) {
                return;
            }
            Evento cce = new Evento();
            //Informe a chave da Nota a ser feita a CArta de Correção
            cce.setChave(jsonNFe.getString("infnfe_id").trim());
            //Informe o CNPJ do emitente
            cce.setCnpj(jsonNFe.getString("emit_cnpj").trim());
            //Informe o Texto da Carta de Correção
            cce.setMotivo(jTxtP_correcao.getText().trim());
            //Informe a data da Carta de Correção
            LocalDateTime agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));//03/02/2023
            cce.setDataEvento(agora);
            //Informe a sequencia do Evento
            cce.setSequencia(1);
            // Monta o Evento
            TEnvEvento envEvento = CartaCorrecaoUtil.montaCCe(cce, configINI.iniciaConfiguracoes());
            //Envia a CCe
            TRetEnvEvento retorno = Nfe.cce(configINI.iniciaConfiguracoes(), envEvento, true);
            //Valida o Retorno do Carta de Correção
            RetornoUtil.validaCartaCorrecao(retorno);
            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach(resultado -> {
                if (resultado.getInfEvento().getCStat().equals("135") //Recebido pelo Sistema de Registro de Eventos, com vinculação do evento na NF-e
                        || resultado.getInfEvento().getCStat().equals("136")) {
                    try {
                        //Recebido pelo Sistema de Registro de Eventos
                        //Evento registrado e vinculado a NF-e
                        eNFE.setIde_nnf(gNNF.trim());
                        eNFE.setInfnfe_statusnfe("Corrigida");
                        //Cria ProcEvento de Cacnelamento

                        String proc = CartaCorrecaoUtil.criaProcEventoCCe(configINI.iniciaConfiguracoes(), envEvento, retorno);
                        eNFE.setXml_envevento_autorizado(proc);
                        System.err.println(proc);

                        if (NfeController.Update(eNFE)) {
                            System.out.println("NFe atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                        TableResumoOperacoes(resultado.getInfEvento().getCStat(), resultado.getInfEvento().getXMotivo());
                        NfeInfProtController.Create(gNNF.trim(), resultado.getInfEvento().getCStat());
                        eNFE_INFPROT.setProtnfe_nnf(gNNF.trim());
                        eNFE_INFPROT.setProtnfe_versao(resultado.getVersao());
                        eNFE_INFPROT.setProtnfe_sequencia("1");
                        eNFE_INFPROT.setInfprot_cstat(resultado.getInfEvento().getCStat());
                        eNFE_INFPROT.setInfprot_chnfe(resultado.getInfEvento().getChNFe());
                        eNFE_INFPROT.setInfprot_dhrecbto(resultado.getInfEvento().getDhRegEvento().toString().replace("T", " ").replace("-03:00", ""));
                        eNFE_INFPROT.setInfprot_nid(resultado.getInfEvento().getId());
                        eNFE_INFPROT.setInfprot_nprot(resultado.getInfEvento().getNProt());
                        eNFE_INFPROT.setInfprot_tpamb(resultado.getInfEvento().getTpAmb());
                        eNFE_INFPROT.setInfprot_veraplic(resultado.getInfEvento().getVerAplic());
                        eNFE_INFPROT.setInfprot_xmotivo("Carta de Correcao");
                        eNFE_INFPROT.setInfprot_xcorrecao(jTxtP_correcao.getText().trim());
                        NfeInfProtController.Update(eNFE_INFPROT);
                        if (veioDoframe1 != null) {
                            SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String data = "dd/MM/yyyy HH:mm:ss";
                            String pattern = "yyyy-MM-dd HH:mm:ss";
                            DateFormat df = new SimpleDateFormat(pattern);
                            Date date = df.parse(resultado.getInfEvento().getDhRegEvento().replace("T", " ").replace("-03:00", ""));
                            String wwDhRegEvento = formatador1.format(date);
                            veioDoframe1.retornaRegistro("Corrigida", "", null, null, null);
                        }
                    } catch (NfeException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (CertificadoException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (InstantiationException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (JAXBException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (ParseException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

        } catch (ParseException ex) {
            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (NfeException ex) {
            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }

    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDoframe1 = veioDo1;
    }

    public void TableResumoOperacoes(String wCStat, String wXMotivo) {
        modelo1.addRow(new Object[]{
            "", gAno, gSerie, gNNF, wCStat + "-" + wXMotivo});
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            modelo1.addRow(new Object[]{
                "",
                gNNF,
                gSerie,
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
                jLabel1.setText("Corrigindo Nota Fiscal Eletronica.");
                jLabel2.setText("Por favor, aguarde.");
                jProgressBar1.setIndeterminate(true);
                jLabel2.requestFocus();
                jProgressBar1.setValue(0);
                modelo1 = (DefaultTableModel) jTable1.getModel();
                System.setProperty("gError", "");
                arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
                CartaDeCorrecao(gNNF);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(100);
                cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
                botao_Enviar.setEnabled(false);
            } catch (SQLException ex) {
                Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CartaDeCorrecaoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
