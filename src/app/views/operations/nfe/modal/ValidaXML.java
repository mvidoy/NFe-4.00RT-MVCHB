package app.views.operations.nfe.modal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import util.AlignTableHeaderCenter;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lib.ConfiguracoesNfeIniciais;
import lib.MontaNfe;
import app.controllers.NfeController;
import app.models.NFE;
import app.views.operations.nfe.NFe;
import br.com.ovconsultoria.boleto.transformer.GeradorDaDanfe;
import br.com.ovconsultoria.boleto.transformer.GeradorDoXml;
import static br.com.swconsultoria.nfe.Nfe.montaNfe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.CLIENTEcontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.CLIENTEdto;
import com.backend.dtos.NFECBdto;
import com.backend.dtos.NFEdto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidaXML extends javax.swing.JInternalFrame {

    private NFe veioDoframe1;
    private JInternalFrame parent;
    ArrayList<String> tListaNNF = new ArrayList<String>();
    int[] tSelectedRows;
    private static final int SSL_PORT = 443;
    String gNF = "";
    DefaultTableModel modelo1 = new DefaultTableModel();

    public ValidaXML(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wNF,
            ArrayList<String> wListaNNF,
            int[] SelectedRows) {
        gNF = wNF;
        tSelectedRows = SelectedRows;
        Iterator it = wListaNNF.iterator();
        while (it.hasNext()) {
            tListaNNF.add(it.next().toString());
        }
        this.jDesktopPane2 = jDesktop;
        initComponents();
        jButton1.setEnabled(true);
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
        jTable1.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(1610);
        jTable1.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(wCol3);
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
            Object text = table.getValueAt(row, 2);
            if (text != null && "Erro".equals(text.toString().trim())) {
                c = Color.RED;
            }
            if (text != null && ("Validada".equals(text.toString().trim())
                    || "Assinada".equals(text.toString().trim()))) {
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
            Object text = table.getValueAt(row, 2);
            if (text != null && "Erro".equals(text.toString().trim())) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"));
            }
            if (text != null && ("Validada".equals(text.toString().trim())
                    || "Assinada".equals(text.toString().trim()))) {
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
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setTitle(" Valida Nota Fiscal (NF-e) - Revisão: 03 - Data da Última Revisão: 27/11/2025 - Data da Elaboração: 14/07/2016");
        setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valida e Assina Nota Fiscal (NF-e)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo da Operação:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Número NFe", "Tipo", "Descrição Erro/Alerta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        }

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)))
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/alerta.jpg"))); // NOI18N
        jLabel7.setText("Alerta");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"))); // NOI18N
        jLabel6.setText("Sucesso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_sair)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botao_sair)))
                .addGap(7, 7, 7))
        );

        jDesktopPane2.getAccessibleContext().setAccessibleName(" Data da Elaboração: 14/07/2016");

        getAccessibleContext().setAccessibleDescription(" Data da Elaboração: 14/07/2016");

        setBounds(0, 0, 900, 489);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
    private static transient javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        System.out.println("ERROR: " + log);
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDoframe1 = veioDo1;
    }

    public void Exception(String log) {
        Scanner scanner = new Scanner(log);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            modelo1.addRow(new Object[]{
                "",
                gNF,
                "Erro",
                line
            });
        }
        scanner.close();
        jLabel2.setText("Processo Finalizado.");
        jLabel1.setText("");
        jProgressBar1.setIndeterminate(false);
        jProgressBar1.setValue(100);
        Cursor cursor = Cursor.getDefaultCursor();
        jDesktopPane2.setCursor(cursor);
    }

    public class Process extends Thread {

        public void run() {
            try {
                jLabel1.setText("Validando Nota Fiscal (NF-e)");
                jLabel2.setText("Por favor, aguarde.");
                jProgressBar1.setIndeterminate(true);
                jButton1.setEnabled(false);
                jLabel1.setVisible(true);
                jLabel2.setVisible(true);
                Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                jDesktopPane2.setCursor(cursor);
                jProgressBar1.setValue(0);
                modelo1 = (DefaultTableModel) jTable1.getModel();
                System.setProperty("gError", "");
                Iterator itListaNNF = tListaNNF.iterator();
                int nt = 0;
                ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
                List<String> errosValidacao = null;
                while (itListaNNF.hasNext()) {
                    gNF = itListaNNF.next().toString();
                    String numeroNFe = String.format("%06d", Integer.parseInt(gNF.trim()));
                    CANFENOTFISdto cANFENOTFISdto = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNFe);
                    String email = "";
                    if (cANFENOTFISdto != null) {
                        CLIENTEdto cliente = CLIENTEcontroller.FindCodigo(cANFENOTFISdto.getNOT_CLIE().trim());
                        if (cliente != null) {
                            if (cliente.getCLI_NFE_EMAIL() != null && !cliente.getCLI_NFE_EMAIL().trim().isEmpty()) {
                                email = cliente.getCLI_NFE_EMAIL().trim();
                            } else if (cliente.getCLI_EMAI() != null && !cliente.getCLI_EMAI().trim().isEmpty()) {
                                email = cliente.getCLI_EMAI().trim();
                            } else if (cliente.getCLI_EMAILFINANCEIRO() != null && !cliente.getCLI_EMAILFINANCEIRO().trim().isEmpty()) {
                                email = cliente.getCLI_EMAILFINANCEIRO().trim();
                            }
                        }
                    }

                    if (cANFENOTFISdto != null && cANFENOTFISdto.getNOT_ENSA().trim().equals("E")) {
                        email = "faturamento2@camarplasticos.com.br";
                    }

                    if (email.isEmpty()) {
                        modelo1.addRow(new Object[]{
                            "",
                            gNF,
                            "Erro",
                            "Cliente sem email cadastrado, verifique!"
                        });
                        continue;
                    }
                    // Monta EnviNfe
                    TEnviNFe enviNFe = new TEnviNFe();
                    enviNFe = MontaNfe.main(gNF);
                    // Monta e Assina o XML
                    enviNFe = montaNfe(configINI.iniciaConfiguracoes(), enviNFe, true);
                    modelo1.addRow(new Object[]{
                        "",
                        gNF,
                        "Validada",
                        "NF-e Validada com Sucesso"
                    });
                    modelo1.addRow(new Object[]{
                        "",
                        gNF,
                        "Assinada",
                        "NF-e Assinada com Sucesso"
                    });
                    info("| OK: XML Validado com Sucesso!");
                    TNFe tNFe = new TNFe();
                    NFE eNFE = new NFE();
                    eNFE.setInfnfe_statusnfe("Assinada");
                    tNFe = enviNFe.getNFe().get(0);
                    eNFE.setXml_assinado(XmlNfeUtil.objectToXml(enviNFe)); //08/11/2023 //20/06/2024
                    //eNFE.setXml_assinado(XmlNfeUtil.objectToXml(tNFe)); // 08/11/2023 //20/06/2024
                    eNFE.setIde_nnf(gNF.trim());
                    if (NfeController.Update(eNFE)) {
                        System.out.println("NFe atualizado com sucesso!");
                    } else {
                        System.out.println(System.getProperty("MsgInvalid"));
                    }
                    if (veioDoframe1 != null) {
                        veioDoframe1.retornaRegistro("Assinada", "", tSelectedRows[nt], "", null);
                    }
                    nt = nt + 1;
                }
                jLabel2.setText("Processo Finalizado.");
                jLabel1.setText("");
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(100);
                cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
            } catch (NfeException ex) {
                Exception(ex.getMessage());
            } catch (CertificadoException ex) {
                Exception(ex.getMessage());
            } catch (FileNotFoundException ex) {
                Exception(ex.getMessage());
            } catch (InstantiationException ex) {
                Exception(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Exception(ex.getMessage());
            } catch (JAXBException ex) {
                Exception(ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ValidaXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
