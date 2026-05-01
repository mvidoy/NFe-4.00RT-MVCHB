package app.views.operations.nfe.modal;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import util.AlignTableHeaderCenter;
import app.controllers.NfeController;
import app.models.NFE;
import app.views.operations.nfe.NFe;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import static com.lowagie.text.xml.xmp.XmpWriter.UTF8;
import com.frontend.config.env;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import lib.MontaNfe;

public class ExportaArquivosXML extends javax.swing.JInternalFrame {

    private NFe NFe_frm;
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    String gChave = "";
    String gNFe = "";
    int gSelectedRow = 0;
    String xml_autorizado = "";
    String xml_envevento_autorizado = "";
    String xml_assinado = "";
    String xml_nfe = "";

    public ExportaArquivosXML(JDesktopPane jDesktop, JInternalFrame jFrame, String nNFe, String nChave, int wSelectedRow) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
        gChave = nChave;
        gNFe = nNFe;
        gSelectedRow = wSelectedRow;
        initComponents();
        botao_Exportar.setEnabled(true);
        jTable1.setEnabled(false);
        jCheckBox8.setSelected(false);
        jCheckBox8.setEnabled(false);
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
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(1720);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
        botao_Exportar.setEnabled(false);
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
        jPanel2 = new javax.swing.JPanel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        botao_Exportar = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();

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
        setTitle("EXPORTAÇÃO DE ARQUIVOS - Revisão: 05 - Data da Última Revisão: 11/03/2024 - Data da Elaboração: 09/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Exportação de Arquivos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tipo:");
        jLabel12.setAutoscrolls(true);

        tf_localdoarquivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_localdoarquivo.setToolTipText("");
        tf_localdoarquivo.setEnabled(false);

        botao_localizar.setMnemonic('L');
        botao_localizar.setText("Localizar");
        botao_localizar.setToolTipText("");
        botao_localizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_localizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_localizarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Arquivos XMLs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox8.setText("Alterar Nomes dos XMLs");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome  do Arquivo XML", "tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jCheckBox8)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
        );

        botao_Exportar.setMnemonic('L');
        botao_Exportar.setText("Exportar");
        botao_Exportar.setToolTipText("");
        botao_Exportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_Exportar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_ExportarMouseMoved(evt);
            }
        });
        botao_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_ExportarActionPerformed(evt);
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

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Arquivo XML");
        jRadioButton1.setEnabled(false);

        jRadioButton2.setText("Arquivo TXT");
        jRadioButton2.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Local:");
        jLabel14.setAutoscrolls(true);

        jDesktopPane2.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_localdoarquivo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_localizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_Exportar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jRadioButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jRadioButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_Exportar)
                .addGap(15, 15, 15)
                .addComponent(botao_sair)
                .addContainerGap())
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_localdoarquivo)
                        .addGap(10, 10, 10)
                        .addComponent(botao_localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addGap(5, 5, 5)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_localizar)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_localdoarquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_Exportar)
                    .addComponent(botao_sair)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGap(14, 14, 14))
        );

        setBounds(0, 0, 809, 309);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_localizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_localizarActionPerformed
        try {
            env Env = new env();
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Selecione a Pasta");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setCurrentDirectory(new java.io.File(Env.getpath_exportxml()));
            int opt = fc.showOpenDialog(jDesktopPane2);
            File path;
            if (opt == JFileChooser.APPROVE_OPTION) {
                path = fc.getSelectedFile();
                tf_localdoarquivo.setText(path.getAbsolutePath());
            } else {
                tf_localdoarquivo.setText(Env.getpath_exportxml());
            }
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            jDesktopPane2.setCursor(cursor);
            if (!tf_localdoarquivo.getText().trim().equals("")) {
                jCheckBox8.setEnabled(true);
                jCheckBox8.setSelected(false);
            } else {
                jCheckBox8.setEnabled(false);
                jCheckBox8.setSelected(false);
            }
            JSONObject jsonNFe = new JSONObject();
            jsonNFe = NfeController.Index(gNFe.trim());
            if (!jsonNFe.isEmpty()) {
                if (jsonNFe.getString("xml_autorizado") != null) {
                    xml_autorizado = jsonNFe.getString("xml_autorizado").trim();
                }
                if (jsonNFe.getString("xml_envevento_autorizado") != null) {
                    xml_envevento_autorizado = jsonNFe.getString("xml_envevento_autorizado").trim();
                }
                if (jsonNFe.getString("xml_assinado") != null) {
                    xml_assinado = jsonNFe.getString("xml_assinado").trim();
                }
                if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Digitação")) {
                    TEnviNFe enviNFe = new TEnviNFe();
                    enviNFe = MontaNfe.main(gNFe.trim());
                    TNFe tNFe = new TNFe();
                    tNFe = enviNFe.getNFe().get(0);
                    xml_nfe = XmlNfeUtil.objectToXml(tNFe);
                }
            }
            DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
            modelo1.setNumRows(0);
            if (!gChave.trim().equals("")
                    && !tf_localdoarquivo.getText().trim().equals("")) {
                if (jsonNFe.getString("infnfe_statusnfe").trim().equals("Em Digitação")) {
                    modelo1.addRow(new Object[]{
                        Env.getPrefixInFiles() + gNFe.trim() + "_v" + Env.getVersao() + "-NfeRT" + ".xml",
                        xml_nfe
                    });
                    if (jTable1.getRowCount() > 0) {
                        jTable1.addRowSelectionInterval(0, 0);
                        jTable1.setRowSelectionInterval(0, 0);
                        jTable1.getSelectionModel().setSelectionInterval(0, 0);
                        botao_Exportar.setEnabled(true);
                    }
                    cursor = Cursor.getDefaultCursor();
                    jDesktopPane2.setCursor(cursor);
                    return;
                }
                if (xml_autorizado != null && xml_autorizado.trim().length() > 0) {
                    modelo1.addRow(new Object[]{
                        Env.getPrefixInFiles() + gNFe.trim() + "_v" + Env.getVersao() + "-procNfeRT" + ".xml",
                        xml_autorizado
                    });
                }
                if (xml_envevento_autorizado != null && xml_envevento_autorizado.trim().length() > 0) {
                    modelo1.addRow(new Object[]{
                        Env.getPrefixInFiles() + gNFe.trim() + "_v" + Env.getVersao() + "-procEventoNfeRT" + ".xml",
                        xml_envevento_autorizado
                    });
                }
                if (xml_autorizado == null || xml_autorizado.trim().length() <= 0) {
                    modelo1.addRow(new Object[]{
                        Env.getPrefixInFiles() + gNFe.trim() + "_v" + Env.getVersao() + "-enviaNfeRT" + ".xml",
                        xml_assinado
                    });
                }
            }
            if (jTable1.getRowCount() > 0) {
                jTable1.addRowSelectionInterval(0, 0);
                jTable1.setRowSelectionInterval(0, 0);
                botao_Exportar.setEnabled(true);
            }
            cursor = Cursor.getDefaultCursor();
            jDesktopPane2.setCursor(cursor);
        } catch (SQLException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NfeException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificadoException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_localizarActionPerformed

    private void botao_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_ExportarActionPerformed
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            jDesktopPane2.setCursor(cursor);
            botao_Exportar.setEnabled(false);
            int wTotLi = jTable1.getModel().getRowCount();
            for (int i = 0; i < wTotLi; i++) {
                if (jTable1.getValueAt(i, 0).toString().trim().length() > 0) {
                    FileWriter arquivo;
                    try {
                        String wArquivo = tf_localdoarquivo.getText().trim()
                                + "\\"
                                + jTable1.getValueAt(i, 0).toString().trim();
                        arquivo = new FileWriter(
                                new File(wArquivo));
                        arquivo.write(jTable1.getValueAt(i, 1).toString().trim());
                        arquivo.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            NFE eNFE = new NFE();
            eNFE.setIde_nnf(gNFe.trim());
            if (xml_autorizado.trim().length() > 0) {
                eNFE.setInfnfe_xmlexportado("Sim");
            }
            if (NfeController.Update(eNFE)) {
                System.out.println("NFe atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, System.getProperty("MsgInvalid"));
            }
            if (NFe_frm != null) {
                NFe_frm.retornaRegistro("Exportada", "", gSelectedRow, null, null);
            }
            cursor = Cursor.getDefaultCursor();
            jDesktopPane2.setCursor(cursor);
            JOptionPane.showMessageDialog(this,
                    Integer.toString(wTotLi) + " registro(s) exportado de " + Integer.toString(wTotLi) + "\n"
                    + Integer.toString(wTotLi) + " arquivo(s) gerado(s) com sucesso. " + "\n", "Exportação Concluída", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (InstantiationException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExportaArquivosXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_ExportarActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        if (!gChave.trim().equals("")
                && jCheckBox8.isSelected() == true
                && !tf_localdoarquivo.getText().trim().equals("")) {
            jTable1.setEnabled(true);
        } else {
            jTable1.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void botao_ExportarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_ExportarMouseMoved
        botao_Exportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_ExportarMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_Exportar;
    private javax.swing.JButton botao_localizar;
    private javax.swing.JButton botao_sair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
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
            boolean a = name.endsWith(".xml"); // método que realmente faz o trabalho :-P 
            return a;
        }
    }

    public void enviaRegistro(NFe NFe_frm, String parameter) {
        this.NFe_frm = NFe_frm;
    }
}
