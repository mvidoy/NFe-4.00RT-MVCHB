package app.views.operations.nfe.modal;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.xmlToObject;
import com.frontend.config.env;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.AlignTableHeaderCenter;

public class SelecionaImportaArquivosTXTXML extends javax.swing.JInternalFrame {

    env Env = new env();
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;

    public SelecionaImportaArquivosTXTXML(JDesktopPane jDesktop, JInternalFrame jFrame) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
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
        jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable1.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(direita);
        jTable1.setAutoCreateRowSorter(true);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(340);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable2.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable2.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(130);
        jTable2.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(430);
        jTable2.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        jTable2.setAutoCreateRowSorter(true);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
        botao_importarTXT.setEnabled(false);
        botao_importarXML.setEnabled(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel12 = new javax.swing.JLabel();
        tf_localdoarquivo = new javax.swing.JTextField();
        botao_localizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        botao_importarTXT = new javax.swing.JButton();
        botao_importarXML = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setForeground(java.awt.Color.white);
        setTitle("IMPORTAÇÃO DE ARQUIVOS - Revisão: 03 - Data da Última Revisão: 20/09/2019 - Data da Elaboração: 30/06/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Importação de Arquivos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Local do(s) Arquivo(s):");
        jLabel12.setAutoscrolls(true);

        tf_localdoarquivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_localdoarquivo.setToolTipText("");
        tf_localdoarquivo.setEnabled(false);

        botao_localizar.setMnemonic('L');
        botao_localizar.setText("Localizar");
        botao_localizar.setToolTipText("");
        botao_localizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_localizar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_localizarMouseMoved(evt);
            }
        });
        botao_localizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_localizarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Arquivos XML", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel13.setText("Filtra por:");
        jLabel13.setAutoscrolls(true);

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox1.setText("Selecionar todos");
        jCheckBox1.setEnabled(false);

        jCheckBox2.setText("Todos");
        jCheckBox2.setEnabled(false);

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("NF-e");
        jCheckBox3.setEnabled(false);

        jCheckBox4.setText("Emitente");
        jCheckBox4.setEnabled(false);

        jCheckBox5.setText("Cliente");
        jCheckBox5.setEnabled(false);

        jCheckBox6.setText("Produto");
        jCheckBox6.setEnabled(false);

        jCheckBox7.setText("Transportadora");
        jCheckBox7.setEnabled(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome  do Arquivo v ", "Tipo de Arquivo", "Identificação", "Situação", "Arquivos Vinculados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable2.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel82.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 0, 51));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("<html> <table> <td color=\"blank\" align=left> <p>\n Seleção somente de arquivo(s) com extensão XML, com o final do nome \"procnfe.xml\" e  com Situação = \"Autorizada\".<br></p> </td> </table> </html>");
        jLabel82.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel83.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 0, 51));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("<html> <table> <td color=\"blank\" align=left> <p>\n Atenção: Dependendo da quantidade de arquivos contida na pasta referente ao local do(s) arquivo(s) , o tempo de carregamento dos aquivos a serem selecionados poderá DEMORAR ALGUNS MINUTOS. Aguarde o final do processamento.<br></p> </td> </table> </html>");
        jLabel83.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(434, 434, 434)
                .addComponent(jCheckBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox5))
            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jCheckBox1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox7))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCheckBox3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Arquivos TXT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel14.setText("Filtra por:");
        jLabel14.setAutoscrolls(true);

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox8.setText("Selecionar todos");
        jCheckBox8.setEnabled(false);

        jCheckBox9.setText("Todos");
        jCheckBox9.setEnabled(false);

        jCheckBox10.setSelected(true);
        jCheckBox10.setText("NF-e");
        jCheckBox10.setEnabled(false);

        jCheckBox11.setText("Emitente");
        jCheckBox11.setEnabled(false);

        jCheckBox12.setText("Cliente");
        jCheckBox12.setEnabled(false);

        jCheckBox13.setText("Produto");
        jCheckBox13.setEnabled(false);

        jCheckBox14.setText("Transportadora");
        jCheckBox14.setEnabled(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome  do Arquivo v ", "Tipo de Arquivo", "# Registros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setOpaque(false);
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addComponent(jCheckBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox12)
                    .addComponent(jCheckBox13)
                    .addComponent(jCheckBox14))
                .addGap(7, 7, 7)
                .addComponent(jCheckBox8)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );

        botao_importarTXT.setMnemonic('L');
        botao_importarTXT.setText("Importar");
        botao_importarTXT.setToolTipText("");
        botao_importarTXT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_importarTXT.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_importarTXTMouseMoved(evt);
            }
        });
        botao_importarTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_importarTXTActionPerformed(evt);
            }
        });

        botao_importarXML.setMnemonic('L');
        botao_importarXML.setText("Importar");
        botao_importarXML.setToolTipText("");
        botao_importarXML.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_importarXML.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_importarXMLMouseMoved(evt);
            }
        });
        botao_importarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_importarXMLActionPerformed(evt);
            }
        });

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

        jDesktopPane2.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_localdoarquivo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_localizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_importarTXT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_importarXML, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_localdoarquivo)
                        .addGap(5, 5, 5)
                        .addComponent(botao_localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao_importarTXT)
                    .addComponent(botao_importarXML)))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_sair))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_localizar)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_localdoarquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(botao_importarTXT)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_importarXML)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(botao_sair))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGap(20, 20, 20))
        );

        setBounds(0, 0, 979, 573);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_localizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_localizarActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Selecione a Pasta");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new java.io.File(Env.getpath_importtxt()));
        int opt = fc.showOpenDialog(jDesktopPane2);
        if (fc.getSelectedFile() == null){
            return;
        }
        String wCaminho = fc.getSelectedFile().getPath();
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jDesktopPane2.setCursor(cursor);
        tf_localdoarquivo.setText(wCaminho);
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        modelo1.setNumRows(0);
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        modelo2.setNumRows(0);
        if (!wCaminho.trim().equals("")) {
            File arq = new File(wCaminho.trim());
            File[] arquivos = arq.listFiles();
            Arrays.sort(arquivos);
            for (int i = 0; i < arquivos.length; i += 1) {
                String wNomeArquivo = arquivos[i].getName();
                String wTamanhoArquivo = String.valueOf(i);
                if (arquivos[i].getName().endsWith(".txt") == true) {
                    wNomeArquivo = wNomeArquivo.substring(0, (wNomeArquivo.length() - 4)) + ".txt";;
                    modelo1.addRow(new Object[]{
                        wNomeArquivo,
                        "Nota Fiscal"
                    });
                }
                if (arquivos[i].getName().toLowerCase().endsWith("procnfe.xml") == true) {
                    wNomeArquivo = wNomeArquivo.substring(0, (wNomeArquivo.length() - 4)) + ".xml";;
                    String wNF = "";
                    wNF = Ler_xml(wCaminho.trim() + "\\" + wNomeArquivo);
                    modelo2.addRow(new Object[]{
                        wNomeArquivo.trim(),
                        "Nota Fiscal",
                        wNF,
                        "Autorizada"
                    });
                }
            }
        }
        if (jTable1.getRowCount() > 0) {
            jTable1.addRowSelectionInterval(0, 0);
            jTable1.setRowSelectionInterval(0, 0);
            botao_importarTXT.setEnabled(true);
        }
        if (jTable2.getRowCount() > 0) {
            jTable2.addRowSelectionInterval(0, 0);
            jTable2.setRowSelectionInterval(0, 0);
            botao_importarXML.setEnabled(true);
        }
        cursor = Cursor.getDefaultCursor();
        jDesktopPane2.setCursor(cursor);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        //sortAllRowsBy(modelo1, 0, false);
        //JTableHeader header = jTable1.getTableHeader();
        //header.setBackground(Color.BLUE);
        //header.setOpaque(true);
        //header.setForeground(Color.BLACK);
        //DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        //headerRenderer.setBackground(new java.awt.Color(204, 204, 204));
        //for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
        //    jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        //}
    }//GEN-LAST:event_botao_localizarActionPerformed

    private void botao_importarTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_importarTXTActionPerformed
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            ArrayList<String> wListaArquivosTXT = new ArrayList<String>();
            int[] SelectedRows = jTable1.getSelectedRows();
            for (int i : SelectedRows) {
                wListaArquivosTXT.add(jTable1.getValueAt(i, 0).toString().trim());
            }
            ImportaArquivoTXT frm = new ImportaArquivoTXT(jDesktopPane2, jFrame,
                    tf_localdoarquivo.getText().trim(), jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim(), wListaArquivosTXT);
            double width = frm.getSize().getWidth();
            double height = frm.getSize().getHeight();
            int wwidth = (int) width;
            int wheight = (int) height;
            frm.setLocation((this.jDesktop.getSize().width - wwidth) / 2, (((this.jDesktop.getSize().height) - wheight) / 2));
            jDesktopPane2.add(frm);
            frm.setMaximum(true);
            //frm.setClosable(true);
            frm.setVisible(true);
            frm.toFront();
            frm.grabFocus();
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(SelecionaImportaArquivosTXTXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_importarTXTActionPerformed

    private void botao_importarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_importarXMLActionPerformed
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            ImportaArquivoXML frm = new ImportaArquivoXML(jDesktopPane2, jFrame,
                    tf_localdoarquivo.getText().trim(), jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString().trim());
            double width = frm.getSize().getWidth();
            double height = frm.getSize().getHeight();
            int wwidth = (int) width;
            int wheight = (int) height;
            frm.setLocation((this.jDesktopPane2.getSize().width - wwidth) / 2, (((this.jDesktopPane2.getSize().height) - wheight) / 2));
            jDesktopPane2.add(frm);
            frm.setMaximum(true);
            //frm.setClosable(true);
            frm.setVisible(true);
            frm.toFront();
            frm.grabFocus();
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(SelecionaImportaArquivosTXTXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_importarXMLActionPerformed

    private void botao_importarTXTMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_importarTXTMouseMoved
        botao_importarTXT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_importarTXTMouseMoved

    private void botao_localizarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_localizarMouseMoved
        botao_localizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_localizarMouseMoved

    private void botao_importarXMLMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_importarXMLMouseMoved
        botao_importarXML.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_importarXMLMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_importarTXT;
    private javax.swing.JButton botao_importarXML;
    private javax.swing.JButton botao_localizar;
    private javax.swing.JButton botao_sair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField tf_localdoarquivo;
    // End of variables declaration//GEN-END:variables

    public static String escolheArquivo() {
        String arquivoWave;
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION) {
            arquivoWave = arquivo.getSelectedFile().getPath();
        } else {
            arquivoWave = "";
        }
        return arquivoWave;
    }

    public void sortAllRowsBy(DefaultTableModel model, int colIndex,
            boolean ascending) {
        Vector data = model.getDataVector();
        Collections.sort(data, new ColumnSorter(colIndex, ascending));
        model.fireTableStructureChanged();
    }

    public class ColumnSorter implements Comparator {

        int colIndex;
        boolean ascending;

        ColumnSorter(int colIndex, boolean ascending) {
            this.colIndex = colIndex;
            this.ascending = ascending;
        }

        public int compare(Object a, Object b) {
            Vector v1 = (Vector) a;
            Vector v2 = (Vector) b;
            Object o1 = v1.get(colIndex);
            Object o2 = v2.get(colIndex);
            if (o1 instanceof String && ((String) o1).length() == 0) {
                o1 = null;
            }
            if (o2 instanceof String && ((String) o2).length() == 0) {
                o2 = null;
            }
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            } else if (o1 instanceof Comparable) {
                if (ascending) {
                    return ((Comparable) o1).compareTo(o2);
                } else {
                    return ((Comparable) o2).compareTo(o1);
                }
            } else {
                if (ascending) {
                    return o1.toString().compareTo(o2.toString());
                } else {
                    return o2.toString().compareTo(o1.toString());
                }
            }
        }
    }

    class FiltroArquivo implements FilenameFilter {

        public boolean accept(File dir, String name) {
            boolean a = name.endsWith(".txt"); // método que realmente faz o trabalho :-P 
            return a;
        }
    }

    public static String Ler_xml(String xml) {
        System.out.println(xml);
        boolean success;
        String nnf = "";
        if ((new File(xml)).exists()) {
            try {
                JAXBContext context1 = JAXBContext.newInstance(TNfeProc.class);
                Unmarshaller unmarshaller1 = context1.createUnmarshaller();
                TNfeProc nfeProc = unmarshaller1.unmarshal(new StreamSource(xml), TNfeProc.class).getValue();
                //TNfeProc nfeProc = XmlNfeUtil.xmlToObject(xml, TNfeProc.class);
                //System.out.println(nfeProc.getNFe().getInfNFe().getId());
                nnf = nfeProc.getNFe().getInfNFe().getId().replace("NFe", "");
                //
            } catch (JAXBException e) {
                e.printStackTrace();

            } finally {
                return nnf;
            }
        }
        return nnf;
    }

    private String getChildTagValue(Element elem, String tagName) throws Exception {
        NodeList children = elem.getElementsByTagName(tagName);
        if (children == null) {
            return null;
        }
        Element child = (Element) children.item(0);
        if (child == null) {
            return null;
        }
        return child.getFirstChild().getNodeValue();
    }

}
