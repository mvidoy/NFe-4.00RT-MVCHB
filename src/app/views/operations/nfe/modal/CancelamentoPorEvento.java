package app.views.operations.nfe.modal;

import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;
import app.views.operations.nfe.NFe;
import app.views.operations.nfe.modal.services.NfeBaixaTitulosService;
import app.views.operations.nfe.modal.services.NfeEnvioEmailService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.NFEdto;
import com.frontend.util.ProcessaTokenUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lib.ConfiguracoesNfeIniciais;
import org.json.JSONObject;
import util.AlignTableHeaderCenter;
import util.FormatFields;
import util.formata;

public class CancelamentoPorEvento extends javax.swing.JInternalFrame {

    private static final String PATH_TEMP_APP = "C:/COBRANCA-4.00/TEMP/";
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
    private final ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil(); //05/02/2026
    /** Caminho da planilha de previsão de caixa no servidor (mesmo padrão do EnviaXMLAssinado linha 128).
     *  Em homologação HOST=192.168.0.5 (SERVLENO); em produção HOST=192.168.0.3 (SERVIDOR). */
    private final String planPrevcaixa = "\\\\" + new com.frontend.config.env().getHOST() + "\\geral\\prevcaixa.xls";

    public CancelamentoPorEvento(
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
        jButton1.setEnabled(true);
        Date dataAtual = new Date();
        SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = "dd/MM/yyyy HH:mm:ss";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String wwDhRegEvento = formatador1.format(dataAtual);
        tf_DhEvento.setText(wwDhRegEvento);
        tf_nseqevento.setText("1");
        jTxtP_justificativa.setText("NOTA FISCAL CONTEM DADOS INCORRETOS.");
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
                    || text.contains("155")
                    || text.toLowerCase().contains("sucesso")
                    || text.toLowerCase().contains("verificando")
                    || text.toLowerCase().contains("preparando")
                    || text.toLowerCase().contains("gerando")
                    || text.toLowerCase().contains("anexando")
                    || text.toLowerCase().contains("efetuada")
                    || text.toLowerCase().contains("registrada")) {
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
                    || text.contains("155")
                    || text.toLowerCase().contains("sucesso")
                    || text.toLowerCase().contains("verificando")
                    || text.toLowerCase().contains("preparando")
                    || text.toLowerCase().contains("gerando")
                    || text.toLowerCase().contains("anexando")
                    || text.toLowerCase().contains("efetuada")
                    || text.toLowerCase().contains("registrada")) {
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

        tf_nseqevento = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        tf_DhEvento = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtP_justificativa = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setTitle("Cancelamento Por Evento - Revisão: 06 - Data da Última Revisão: 04/05/2026 - Data da Elaboração: 03/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.PNG"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cancelamento por Evento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N
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

        jButton1.setText("Enviar");
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
        jLabel44.setText("* Número de Sequência");

        tf_nseqevento.setEditable(false);
        tf_nseqevento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_nseqevento.setToolTipText("");
        tf_nseqevento.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_nseqevento.setDoubleBuffered(true);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("* Justificatica");

        tf_DhEvento.setEditable(false);
        tf_DhEvento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_DhEvento.setToolTipText("");
        tf_DhEvento.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_DhEvento.setDoubleBuffered(true);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("* Data e Hora do Evento");

        jTxtP_justificativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtP_justificativaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTxtP_justificativa);

        jLabel8.setText("mínimo de 15 e máximo de 255 caracteres.");

        jLabel9.setText("(*) Campo de preenchimento obrigatório.");

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_nseqevento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_DhEvento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(tf_DhEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tf_nseqevento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465)
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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13))
                .addGap(3, 3, 3))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_DhEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nseqevento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        try {
            ProcessaToken.processaTokenBradesco();
            ProcessaToken.processaTokenSantander();
            Process process = new Process();
            process.start();
        } catch (java.lang.Exception ex) {
            Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private javax.swing.JTextField tf_DhEvento;
    private javax.swing.JTextField tf_nseqevento;
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

    private void CancelarNfeSEFAZ(String nNNF) throws SQLException, UnsupportedEncodingException {

        try {
            NFE eNFE = new NFE();
            NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
            ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(nNNF.trim());
            if (jsonNFe.isEmpty()) {
                return;
            }
            Evento cancela = new Evento();
            //Informe a chave da Nota a ser Cancelada
            cancela.setChave(jsonNFe.getString("infnfe_id").trim());
            //Informe o protocolo da Nota a ser Cancelada
            cancela.setProtocolo(jsonNFe.getString("infprot_nprot").trim());
            //Informe o CNPJ do emitente
            cancela.setCnpj(jsonNFe.getString("emit_cnpj").trim());
            //Informe o Motivo do Cancelamento
            cancela.setMotivo(jTxtP_justificativa.getText().trim());
            //Informe a data do Cancelamento
            LocalDateTime agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")); //03/02/2023
            cancela.setDataEvento(agora);
            //Monta o Evento de Cancelamento
            TEnvEvento enviEvento = CancelamentoUtil.montaCancelamento(cancela, configINI.iniciaConfiguracoes());
            //Envia o Evento de Cancelamento
            TRetEnvEvento retorno = Nfe.cancelarNfe(configINI.iniciaConfiguracoes(), enviEvento, true, DocumentoEnum.NFE);
            //Valida o Retorno do Cancelamento
            RetornoUtil.validaCancelamento(retorno);
            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach(resultado -> {
                info("| CStat........: " + resultado.getInfEvento().getCStat());
                info("| COrgao.......: " + resultado.getInfEvento().getCOrgao());
                info("| TpAmb........: " + resultado.getInfEvento().getTpAmb());
                info("| VerAplic.....: " + resultado.getInfEvento().getVerAplic());
                info("| Versao.......: " + resultado.getVersao());
                info("| XMotivo......: " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
                if (resultado.getInfEvento().getCStat().equals("155") //Cancelamento homologado fora de prazo
                        || resultado.getInfEvento().getCStat().equals("135")) {
                    try {
                        //Evento registrado e vinculado a NF-e
                        eNFE.setIde_nnf(gNNF.trim());
                        eNFE.setInfnfe_statusnfe("Cancelada");
                        //Cria ProcEvento de Cacnelamento

                        String proc = CancelamentoUtil.criaProcEventoCancelamento(configINI.iniciaConfiguracoes(), enviEvento, retorno.getRetEvento().get(0));
                        eNFE.setXml_envevento_autorizado(proc);
                        salvarXmlEventoCancelamento(gNNF, proc);
                        System.err.println(proc);

                        if (NfeController.Update(eNFE)) {
                            System.out.println("NFe atualizado com sucesso!");
                        } else {
                            System.out.println(System.getProperty("MsgInvalid"));
                        }
                        TableResumoOperacoes(resultado.getInfEvento().getCStat(), resultado.getInfEvento().getXMotivo());
                        //NfeInfProtController.Create(gNNF.trim(), resultado.getInfEvento().getCStat()); //07/06/2022
                        NfeInfProtController.Create(gNNF.trim(), "136"); //07/06/2022
                        eNFE_INFPROT.setProtnfe_nnf(gNNF.trim());
                        eNFE_INFPROT.setProtnfe_versao(resultado.getVersao());
                        eNFE_INFPROT.setProtnfe_sequencia("1");
                        //eNFE_INFPROT.setInfprot_cstat(resultado.getInfEvento().getCStat()); //07/06/2022
                        eNFE_INFPROT.setInfprot_cstat("136"); //07/06/2022
                        eNFE_INFPROT.setInfprot_chnfe(resultado.getInfEvento().getChNFe());
                        eNFE_INFPROT.setInfprot_dhrecbto(resultado.getInfEvento().getDhRegEvento().toString().replace("T", " ").replace("-03:00", ""));
                        eNFE_INFPROT.setInfprot_nid(resultado.getInfEvento().getId());
                        eNFE_INFPROT.setInfprot_nprot(resultado.getInfEvento().getNProt());
                        eNFE_INFPROT.setInfprot_tpamb(resultado.getInfEvento().getTpAmb());
                        eNFE_INFPROT.setInfprot_veraplic(resultado.getInfEvento().getVerAplic());
                        eNFE_INFPROT.setInfprot_xmotivo("Cancelamento");
                        eNFE_INFPROT.setInfprot_xjust(jTxtP_justificativa.getText().trim());

                        NfeInfProtController.Update(eNFE_INFPROT);

                        //Inicio Alteracao 02/03/2026
                        String numeroFormatado
                                = String.format("%06d", Integer.parseInt(gNNF.trim()));
                        CANFENOTFISdto caNFENOTFISdto
                                = CANFENOTFIScontroller.NamedQueryFindByNota(numeroFormatado);
                        if (caNFENOTFISdto != null) {
                            caNFENOTFISdto.setNOT_SITU("S");
                            CANFENOTFIScontroller.Update(caNFENOTFISdto);
                        }
                        //Fim Alteracao

                        modelo1.addRow(new Object[]{
                            "",
                            gAno,
                            gSerie,
                            gNNF.trim(),
                            "Nota Fiscal Cancela na SEFAZ com sucesso"
                        });

                        String numeroNFe = String.format("%06d", Integer.parseInt(gNNF.trim()));
                        int ideNNF = Integer.parseInt(numeroNFe);
                        NFEdto nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);

                        if (nfeDto.getIDE_TPNF() == 1
                                && nfeDto.getIDE_NATOP() != null
                                && nfeDto.getIDE_NATOP().toUpperCase().contains("VENDA")) {
                            try {
                                NfeBaixaTitulosService baixaService = new NfeBaixaTitulosService();

                                // Aqui a tabela do cancelamento tem colunas: {"", Ano, Série, Número, Mensagem}
                                // Então mapeamos as mensagens da baixa (que são por título/parcela/nossoNumero) para "Número".
                                baixaService.setTableRowListener(r -> {
                                    // r = {"", titulo, parcela, nossoNumero, mensagem}
                                    String titulo = r[1] == null ? "" : r[1].toString();
                                    String parcela = r[2] == null ? "" : r[2].toString();
                                    String nossoNumero = r[3] == null ? "" : r[3].toString();
                                    String msg = r[4] == null ? "" : r[4].toString();

                                    modelo1.addRow(new Object[]{
                                        "",
                                        gAno,
                                        gSerie,
                                        titulo,
                                        msg.toLowerCase().contains("verificando") ? msg : "Boleto: Parcela: " + parcela + " / NN " + nossoNumero + " - " + msg
                                    });

                                    int last = modelo1.getRowCount() - 1;
                                    if (last >= 0) {
                                        jTable1.scrollRectToVisible(jTable1.getCellRect(last, 0, true));
                                    }
                                });

                                // baixa por NFe (cancelada)
                                baixaService.baixarTitulosPorNFe(gNNF);

                            } catch (Exception ex) {
                                modelo1.addRow(new Object[]{
                                    "", gAno, gSerie, gNNF,
                                    "BAIXA: Exceção ao baixar títulos: " + ex.getMessage()
                                });
                            }
                        }

                        // ENVIA EMAIL APÓS CANCELAMENTO
                        enviarEmailCancelamento(gNNF.trim());

                        // Inicio Alteracao 30/04/2026 — Remove duplicatas da planilha prevcaixa
                        // Executado APÓS o e-mail: se a remoção falhar, o cancelamento e o e-mail
                        // já foram concluídos com sucesso. Falha aqui apenas loga WARNING.
                        removerDuplicatasDaPlanilha(numeroFormatado);
                        // Fim Alteracao 30/04/2026

                        if (veioDoframe1 != null) {
                            SimpleDateFormat formatador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String data = "dd/MM/yyyy HH:mm:ss";
                            String pattern = "yyyy-MM-dd HH:mm:ss";
                            DateFormat df = new SimpleDateFormat(pattern);
                            Date date = df.parse(resultado.getInfEvento().getDhRegEvento().replace("T", " ").replace("-03:00", ""));
                            String wwDhRegEvento = formatador1.format(date);
                            veioDoframe1.retornaRegistro("Cancelada", "", null, null, null);
                        }
                    } catch (JAXBException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (NfeException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (CertificadoException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (InstantiationException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                        Exception(ex.getMessage());
                    } catch (ParseException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (ParseException ex) {
            Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (NfeException ex) {
            Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (CertificadoException ex) {
            Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            Exception(ex.getMessage());
        }

    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
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
            System.out.println(line);
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
                jLabel1.setText("Cancelando Nota Fiscal Eletronica.");
                jLabel2.setText("Por favor, aguarde.");
                jProgressBar1.setIndeterminate(true);
                jButton1.setEnabled(false);
                jLabel2.requestFocus();
                jProgressBar1.setValue(0);
                modelo1 = (DefaultTableModel) jTable1.getModel();
                System.setProperty("gError", "");
                arquivoXML = tCaminhoArquivo + "\\" + tArquivoXMLNFe;
                CancelarNfeSEFAZ(gNNF);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(100);
                cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
            } catch (SQLException ex) {
                Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CancelamentoPorEvento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    // ========================================================
    // SALVAR XML DO EVENTO DE CANCELAMENTO (MÍNIMO IMPACTO)
    // ========================================================
    private void salvarXmlEventoCancelamento(String numeroNFe, String xmlEvento) {

        try {

            String numeroNFeFormatado = String.format("%06d",
                    Integer.parseInt(numeroNFe.trim()));

            String caminhoEvento = PATH_TEMP_APP
                    + "XML_EVENTO_CANCELAMENTO"
                    + numeroNFeFormatado
                    + ".xml";

            java.nio.file.Files.write(
                    java.nio.file.Paths.get(caminhoEvento),
                    xmlEvento.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );

            System.out.println("XML de cancelamento salvo em: " + caminhoEvento);

        } catch (Exception exSalvarXml) {

            System.err.println("Erro ao salvar XML de cancelamento: "
                    + exSalvarXml.getMessage());
        }
    }

    // ========================================================
    // ENVIAR EMAIL APÓS CANCELAMENTO (MÍNIMO IMPACTO)
    // ========================================================
    private void enviarEmailCancelamento(String numeroNFe) {

        try {

            NfeEnvioEmailService emailService = new NfeEnvioEmailService();

            // reaproveita a mesma tabela de resumo
            emailService.setTableRowListener(r -> {
                modelo1.addRow(new Object[]{
                    "",
                    gAno,
                    gSerie,
                    numeroNFe,
                    r[4] == null ? "" : r[4].toString()
                });

                int last = modelo1.getRowCount() - 1;
                if (last >= 0) {
                    jTable1.scrollRectToVisible(
                            jTable1.getCellRect(last, 0, true));
                }
            });

            emailService.enviarEmail(numeroNFe);

        } catch (Exception ex) {

            modelo1.addRow(new Object[]{
                "",
                gAno,
                gSerie,
                numeroNFe,
                "Erro ao enviar email de cancelamento: "
                + ex.getMessage()
            });
        }
    }

    // =========================================================================
    // Inicio Alteracao 30/04/2026
    // =========================================================================

    /**
     * Remove da planilha prevcaixa.xls as linhas correspondentes às duplicatas
     * da NF-e cancelada.
     *
     * <p>Chamado imediatamente após a confirmação do cancelamento pelo SEFAZ
     * (CStat 135 ou 155). O cancelamento já está persistido; este método é
     * pós-processamento. Em caso de falha (share indisponível, planilha aberta
     * por outro usuário etc.), o erro é apenas logado — o cancelamento NÃO é
     * revertido.</p>
     *
     * <p>Padrão de abertura/salvamento/fechamento idêntico ao utilizado em
     * {@code EnviaXMLAssinado#RegistroDeBoletoBradesco} (linhas 1268-1325).</p>
     *
     * <p><b>Planilha:</b> {@code \\<HOST>\geral\prevcaixa.xls}<br>
     * Em homologação HOST = 192.168.0.5 (SERVLENO).<br>
     * Em produção   HOST = 192.168.0.3 (SERVIDOR).</p>
     *
     * <p><b>Coluna de identificação:</b> coluna 2 = PAR_NUMDOC.</p>
     *
     * @param numeroNFe número da nota formatado em 6 dígitos (ex: "000050")
     *
     * @author Osvaldo — 30/04/2026
     */
    private void removerDuplicatasDaPlanilha(String numeroNFe) {
        try {
            // 1. Busca todas as duplicatas da nota no banco de dados
            List<CANFEDUPLICdto> duplicatas
                    = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);

            if (duplicatas == null || duplicatas.isEmpty()) {
                // Nota sem duplicatas registradas — nada a remover da planilha
                return;
            }

            // 2. Cria cópia de segurança da planilha antes de modificá-la
            String ts = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
            String backupPath = planPrevcaixa.replace(".xls", "_" + ts + ".xls");
            Files.copy(Paths.get(planPrevcaixa), Paths.get(backupPath),
                    StandardCopyOption.REPLACE_EXISTING);

            // Verifica se a planilha está aberta por outro processo (ex: Excel)
            try (FileOutputStream testStream = new FileOutputStream(planPrevcaixa, true)) {
                // conseguiu abrir em escrita — arquivo não está bloqueado
            } catch (IOException lockEx) {
                modelo1.addRow(new Object[]{
                    "",
                    gAno,
                    gSerie,
                    gNNF.trim(),
                    "Planilha prevcaixa está aberta, remova manual."
                });
                return;
            }

            // 3. Abre a planilha — padrão idêntico ao EnviaXMLAssinado linha 1268
            FileInputStream file = new FileInputStream(planPrevcaixa);
            WorkbookSettings ws = new WorkbookSettings();
            ws.setSuppressWarnings(true);                                         // suprime warnings JXL (padrão do projeto)
            Workbook workbook = Workbook.getWorkbook(file, ws);                   // leitura
            WritableWorkbook writableWorkbook
                    = Workbook.createWorkbook(new java.io.File(planPrevcaixa), workbook); // edição
            WritableSheet sheet2 = writableWorkbook.getSheet(0);                 // primeira aba

            // 4. Para cada duplicata, localiza e remove a linha correspondente
            for (CANFEDUPLICdto dup : duplicatas) {
                String numdocAlvo = dup.getPAR_NUMDOC().trim();

                // Varre linhas 1–1000 (linha 0 é cabeçalho)
                for (int k = 1; k <= 1000; k++) {
                    Cell[] row = sheet2.getRow(k);

                    // Linha nula ou vazia = fim dos dados
                    if (row == null || row.length == 0) {
                        break;
                    }
                    if (row[0].getContents().isEmpty() && row[2].getContents().isEmpty()) {
                        break;
                    }

                    // Coluna 2 = PAR_NUMDOC — chave de identificação da duplicata
                    if (row[2].getContents().trim().equals(numdocAlvo)) {
                        sheet2.removeRow(k); // JXL desloca automaticamente as linhas seguintes para cima
                        break;
                    }
                }
            }

            // 5. Salva e fecha — padrão idêntico ao EnviaXMLAssinado linhas 1323-1325
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();

            // 6. Informa sucesso na tabela de operações
            modelo1.addRow(new Object[]{
                "",
                gAno,
                gSerie,
                gNNF.trim(),
                "Planilha prevcaixa: duplicatas da nota removidas com sucesso"
            });

        } catch (Exception e) {
            // Falha no pós-processamento NÃO reverte o cancelamento já confirmado pelo SEFAZ
            Logger.getLogger(CancelamentoPorEvento.class.getName())
                    .log(Level.WARNING,
                            "Erro ao remover duplicatas da planilha prevcaixa apos cancelamento NFe "
                            + numeroNFe, e);
            // Informa falha na tabela de operações
            modelo1.addRow(new Object[]{
                "",
                gAno,
                gSerie,
                gNNF.trim(),
                "Planilha prevcaixa: falha ao remover duplicatas - " + e.getMessage()
            });
        }
    }

    // =========================================================================
    // Fim Alteracao 30/04/2026
    // =========================================================================

}
