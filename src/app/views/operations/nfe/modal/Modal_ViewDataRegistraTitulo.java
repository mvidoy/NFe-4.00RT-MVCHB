package app.views.operations.nfe.modal;

import app.views.operations.nfe.NFe;
import static br.com.ovconsultoria.boleto.bancos.Bancos.BRADESCO;
import static br.com.ovconsultoria.boleto.bancos.Bancos.SANTANDER;
import br.com.ovconsultoria.boleto.transformer.EBCDICConverter;
import com.backend.api.cobranca.bradesco.dtos.ResponseJsonREGISTRODEBOLETOBRADESCOdto;
import static com.backend.api.cobranca.bradesco.util.MontaRegistroDeBoletoBradescoUtil.montaRegistroDeBoletoBradesco;
import static com.backend.api.cobranca.bradesco.ws.EnviaJsonBradescoWs.enviaJsonBradescoWs;
import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;
import com.backend.controllers.CANFEDUPLICcontroller;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.dtos.NFECBdto;
import com.backend.dtos.NFEdto;
import com.backend.exceptions.MD1Exception;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.config.env;
import com.frontend.util.VerifyFileExcelOpen;
import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import util.AlignTableHeaderCenter;

public final class Modal_ViewDataRegistraTitulo extends javax.swing.JDialog {

    String InfNFe_NNF = "";

    DefaultTableModel RETORNOtableModel = new DefaultTableModel();
    MontaMalote montaMalote = new MontaMalote();
    EnviaMalote enviaMalote = new EnviaMalote();
    env Env = new env();
    String PATH_APP = Env.getPATH_APP();
    String planPrevcaixa = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";

    public Modal_ViewDataRegistraTitulo(
            String InfNFe_NNF) throws MD1Exception, SQLException, ParseException {
        this.InfNFe_NNF = InfNFe_NNF;
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("/com/frontend/assets/icone_modulo1.jpg")).getImage());
        montaTable();
    }

    public Modal_ViewDataRegistraTitulo(javax.swing.JFrame parent, boolean modal) {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        RETORNOtable = new javax.swing.JTable();

        botao_enivaremail = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registra Título(s) - Revisão: 02 - Data da Última Revisão: 05/02/2026");
        setModal(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registra Título(s) de Cobrança", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N
        jDesktopPane2.setMinimumSize(new java.awt.Dimension(9, 32));
        jDesktopPane2.setOpaque(false);

        jPanel22.setBackground(new java.awt.Color(223, 218, 218));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel22.setDoubleBuffered(false);
        jPanel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPanel22.setOpaque(false);

        RETORNOtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Título", "Parcela", "Nosso Número", "Resultado do Retorno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RETORNOtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        RETORNOtable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RETORNOtable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        RETORNOtable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        RETORNOtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(RETORNOtable);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        botao_enivaremail.setMnemonic('L');
        botao_enivaremail.setText("Registrar Título(s)");
        botao_enivaremail.setToolTipText("");
        botao_enivaremail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_enivaremail.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_enivaremailMouseMoved(evt);
            }
        });
        botao_enivaremail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_enivaremailActionPerformed(evt);
            }
        });

        btn_close.setMnemonic('L');
        btn_close.setText("Fechar");
        btn_close.setToolTipText("");
        btn_close.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_close.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_closeMouseMoved(evt);
            }
        });
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        jDesktopPane2.setLayer(jPanel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_enivaremail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(btn_close, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botao_enivaremail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_close)
                    .addComponent(botao_enivaremail))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(917, 369));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_enivaremailMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_enivaremailMouseMoved
        botao_enivaremail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_enivaremailMouseMoved

    private void botao_enivaremailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_enivaremailActionPerformed
        if (!new File(planPrevcaixa).exists()) {
            JOptionPane.showMessageDialog(this,
                    "A planilha: " + planPrevcaixa + " não encontrada, verifique!!!",
                    "Mensagem do Sistema",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isOpen = VerifyFileExcelOpen.isOpen(planPrevcaixa);
        if (isOpen) {
            JOptionPane.showMessageDialog(this,
                    "A planilha: " + planPrevcaixa + " está aberta, verifique!!!",
                    "Mensagem do Sistema",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int selection = JOptionPane.showConfirmDialog(
                this, "Deseja registrar título(s) de Cobrança?",
                "Mensagem do Sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (selection != JOptionPane.YES_OPTION) {
            return;
        }

        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        botao_enivaremail.setCursor(cursor);
        jPanel22.setCursor(cursor);

        try {
            String numeroNFe = String.format("%06d", Integer.parseInt(InfNFe_NNF.trim()));
            String numeroNFeFormatado = String.format("%06d", Integer.parseInt(InfNFe_NNF.trim()));
            int ideNNF = Integer.parseInt(numeroNFeFormatado);

            NFEdto nfeDto = new NFEdto();

            nfeDto = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);
            com.backend.dtos.CANFENOTFISdto cANFENOTFISdto = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNFeFormatado);

            if (cANFENOTFISdto != null
                    && nfeDto != null
                    && nfeDto.getIDE_TPNF() == 1
                    && nfeDto.getIDE_NATOP().toUpperCase().contains("VENDA")) { //05/02/2026

                List<CANFEDUPLICdto> duplicatas = CANFEDUPLICcontroller.NamedQueryFindAllNota(numeroNFe);
                RETORNOtableModel.addRow(new Object[]{
                    "", numeroNFeFormatado, "", "",
                    "Verificando cobrança..."
                });

                if (duplicatas != null && !duplicatas.isEmpty()) {
                    String pAR_BANCO = duplicatas.get(0).getPAR_BANCO().trim();
                    if (pAR_BANCO.equals(BRADESCO.getNumeroDoBanco())) {
                        RegistroDeBoletoBradesco(duplicatas);
                    }
                } else {
                    System.out.println("[AVISO] Nenhuma duplicata encontrada para a NFe " + numeroNFeFormatado);
                    RETORNOtableModel.addRow(new Object[]{
                        "", numeroNFeFormatado, "", "", "Nenhuma duplicata encontrada"});
                }
                cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
                botao_enivaremail.setCursor(cursor);
                jPanel22.setCursor(cursor);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Natureza da operação não é VENDA, verifique!!!",
                        "Mensagem do Sistema",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botao_enivaremailActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_closeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseMoved
        btn_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_closeMouseMoved

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modal_ViewDataRegistraTitulo dialog = new Modal_ViewDataRegistraTitulo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void KeyTab(java.awt.event.KeyEvent evt) {
        try {
            Robot robot = new Robot();
            int key = evt.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
                robot.keyPress(KeyEvent.VK_TAB);
            }
        } catch (AWTException ex) {
            Logger.getLogger(Modal_ViewDataRegistraTitulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RETORNOtable;
    private javax.swing.JButton botao_enivaremail;
    private javax.swing.JButton btn_close;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JScrollPane jScrollPane13;
    // End of variables declaration//GEN-END:variables

    private void ConsultaDeBoletoBradesco(String Banco) throws IOException, SQLException, ParseException {
        Cursor cursorWait = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursorWait);
        botao_enivaremail.setCursor(cursorWait);
        jPanel22.setCursor(cursorWait);

        Cursor cursorDefault = Cursor.getDefaultCursor();
        setCursor(cursorDefault);
        botao_enivaremail.setCursor(cursorDefault);
        jPanel22.setCursor(cursorDefault);
    }

    public void montaTable() {
        // Define RETORNOtableModel com as colunas e adiciona ao jTableRetorno
        RETORNOtableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "",
                    "Título",
                    "Parcela",
                    "Nosso Número",
                    "Resultado do Retorno/Processo"}
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        RETORNOtable.setModel(RETORNOtableModel); // VINCULA RETORNOtableModel à tabela

        // Configura renderizadores e estilos da tabela
        RETORNOtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        TableCellRenderer aligntableheadercenter = new AlignTableHeaderCenter();
        TableCellRenderer wCol3 = new ColorirLinhaRetorno();
        TableCellRenderer tcr = new Imagem();

        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centralizado);
        RETORNOtable.getColumnModel().getColumn(0).setCellRenderer(tcr);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(1).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(2).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(200);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(3).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(900);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(4).setCellRenderer(wCol3);
    }

    public void consultaDeBoletoBradesco(CANFEDUPLICdto dup) throws SQLException, ParseException {
        System.out.println("[INFO] Iniciando consulta de título no Bradesco: " + dup.getPAR_CODI().trim());

        System.out.println("[INFO] Consulta do título finalizada");
    }

    public class ColorirLinhaRetorno extends DefaultTableCellRenderer {

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
            if (text.contains("sucesso")
                    || text.contains("ando")
                    || text.contains("Status:")) {
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
            ImageIcon imagem = new javax.swing.ImageIcon(getClass().getResource("/com/frontend/assets/sucesso.jpg"));
            String text = table.getValueAt(row, 4).toString();
            if (text.contains("sucesso")
                    || text.contains("ando")
                    || text.contains("Status:")) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/com/frontend/assets/sucesso.jpg"));
            } else {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/com/frontend/assets/erro.png"));
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

    private void gerarBoletoDeCobranca(String numeroNFe, List<CANFEDUPLICdto> duplicatas) {
        System.out.println("[INFO] Iniciando registro de boletos para a NFe: " + duplicatas.get(0).getPAR_CODI().trim());
        String numeroFormatadoNFe = String.format("%09d", Integer.parseInt(numeroNFe));

        try {
            for (int i = 0; i < duplicatas.size(); i++) {
                boolean maloteGerado = false;
                CANFEDUPLICdto dup = duplicatas.get(i);
                String nonu = dup.getPAR_NONU().replaceAll("\\D", "");
                if (dup.getPAR_NONU() != null && !dup.getPAR_NONU().trim().isEmpty()) {
                    nonu = dup.getPAR_NONU().replaceAll("\\D", "");
                    if (dup.getPAR_BANCO().equals(BRADESCO.getNumeroDoBanco())) {
                        nonu = String.format("%013d", Long.parseLong(nonu)); // completa com zeros à esquerda
                    } else if (nonu.length() > 12) {
                        nonu = nonu.substring(0, 12); // pega os 12 primeiros dígitos
                    }
                }
                if ("REGISTRADO".equals(dup.getPAR_STATUS().trim())
                        && SANTANDER.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                    maloteGerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(SANTANDER.getNumeroDoBanco(), dup, "");
                    if (maloteGerado) {
                        RETORNOtableModel.addRow(new Object[]{
                            "", numeroNFe, dup.getPAR_PARC().trim(), nonu,
                            "Boleto de cobrança " + SANTANDER.getNomeDoBanco() + " gerado com sucesso"
                        });
                    } else {
                        RETORNOtableModel.addRow(new Object[]{
                            "", numeroNFe, dup.getPAR_PARC().trim(), nonu,
                            "Falha na geracao do Boleto de cobrança " + SANTANDER.getNomeDoBanco()
                        });
                    }
                } else if ("REGISTRADO".equals(dup.getPAR_STATUS().trim())
                        && BRADESCO.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                    maloteGerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(BRADESCO.getNumeroDoBanco(), dup, "");
                    if (maloteGerado) {
                        RETORNOtableModel.addRow(new Object[]{
                            "", numeroNFe, dup.getPAR_PARC().trim(), nonu,
                            "Boleto de cobrança " + BRADESCO.getNomeDoBanco() + " gerado com sucesso"
                        });
                    } else {
                        RETORNOtableModel.addRow(new Object[]{
                            "", numeroNFe, dup.getPAR_PARC().trim(), dup.getPAR_NONU().trim(),
                            "Falha na geracao do Boleto de cobrança " + BRADESCO.getNomeDoBanco()
                        });
                    }
                } else {
                    RETORNOtableModel.addRow(new Object[]{
                        "", numeroNFe, dup.getPAR_PARC().trim(), dup.getPAR_NONU().trim(),
                        "Duplicata ainda sem REGISTRO de cobrança. Banco: " + dup.getPAR_BANCO() + ".Verifique!"
                    });
                }
            }

        } catch (Exception ex) {
            System.err.println("[ERRO] Falha ao gerar duplicata" + ex.getMessage());
            ex.printStackTrace();
            RETORNOtableModel.addRow(new Object[]{
                "", numeroNFe, "", "",
                "Exceção ao gerar boleto"
            });
        }
        System.out.println("[INFO] Registro de boletos finalizado para a NFe " + numeroFormatadoNFe);
    }

    private void RegistroDeBoletoBradesco(List<CANFEDUPLICdto> duplicatas) throws IOException, SQLException, ParseException, BiffException, WriteException {
        Cursor cursorWait = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(cursorWait);
        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();
        FileInputStream file = new FileInputStream(planPrevcaixa);
        WorkbookSettings ws = new WorkbookSettings();
        ws.setSuppressWarnings(true);
        Workbook workbook = Workbook.getWorkbook(file, ws);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(planPrevcaixa), workbook);
        WritableSheet sheet2 = writableWorkbook.getSheet(0);
        try {
            for (int i = 0; i < duplicatas.size(); i++) {
                String pAR_CODI = duplicatas.get(i).getPAR_CODI();
                String pAR_PARC = duplicatas.get(i).getPAR_PARC();
                caNFEDUPLICdto = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(pAR_CODI.trim(), pAR_PARC.trim());
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                Date parVencDate = caNFEDUPLICdto.getPAR_VENC();
                LocalDate parVenc = parVencDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String parVencString2 = parVenc.format(formatter2);
                boolean registrado = registroDeBoletoBradesco(caNFEDUPLICdto);
                if (!registrado) {
                    continue;
                }
                //}
                // Abre a planilha
                for (int k = 1; k <= 1000; k++) {
                    Cell[] row = sheet2.getRow(k);
                    String sPAR_NUMDOC = row[2].getContents().toString();
                    //System.out.println("Valor da célula: " + sPAR_NUMDOC);
                    if (sPAR_NUMDOC.equals(caNFEDUPLICdto.getPAR_NUMDOC().trim())) {
                        break;
                    } else if (row[0].getContents().isEmpty() && row[2].getContents().isEmpty()) {
                        sheet2.addCell(new Label(0, k, parVencString2));
                        sheet2.addCell(new Label(1, k, " " + caNFEDUPLICdto.getPAR_NOME().trim()));
                        sheet2.addCell(new Label(2, k, caNFEDUPLICdto.getPAR_NUMDOC().trim()));
                        sheet2.addCell(new Label(3, k, "API(FAT)-BRADESCO"));
                        sheet2.addCell(new jxl.write.Number(5, k, caNFEDUPLICdto.getPAR_VALO()));
                        break;
                    }
                }
                if (registrado) {
                    NFECBdto nfecb = new NFECBdto();
                    String numeroNFe = String.format("%06d", Integer.parseInt(caNFEDUPLICdto.getPAR_CODI().trim()));
                    nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                }
            }
            /*
            boolean enviado = enviaMalote.enviaMalote(Banco, null, null);
            if (enviado) {
                RETORNOtableModel.addRow(new Object[]{
                    "", "", "", "",
                    "DANFE(s), XML(s), e Boleto(s) de cobranca enviado(s) com sucesso para o(s) Cliente(s)."
                });
            }
            montaMalote.limparMalote();
             */
        } finally {
            writableWorkbook.write();
            writableWorkbook.close();
            workbook.close();
            Cursor cursorDefault = Cursor.getDefaultCursor();
            setCursor(cursorDefault);
        }
    }

    public boolean registroDeBoletoBradesco(CANFEDUPLICdto dup) throws SQLException, ParseException {
        String numeroNFe = String.format("%06d", Integer.parseInt(dup.getPAR_CODI().trim()));
        String numeroNFeFormatado = String.format("%06d", Integer.parseInt(dup.getPAR_CODI().trim()));
        int ideNNF = Integer.parseInt(numeroNFeFormatado);

        CANFEDUPLICdto ccNFEDUPLICdto = new CANFEDUPLICdto();
        String pAR_CODI = dup.getPAR_CODI();
        String pAR_PARC = dup.getPAR_PARC();
        ccNFEDUPLICdto = CANFEDUPLICcontroller.NamedQueryFindByNotaParc(pAR_CODI.trim(), pAR_PARC.trim());
        NFEdto nfe = NFEcontroller.NamedQueryFindByIDENNF(ideNNF);
        if (nfe == null) {
            RETORNOtableModel.addRow(new Object[]{
                "", numeroNFe, dup.getPAR_PARC().trim(), "",
                "A nota fiscal eletrônica (NFe) ainda não foi gerada nem autorizada pela SEFAZ. Verifique no emissor da NFe."
            });
            return false;
        }
        System.out.println("[INFO] Iniciando registro de boletos para a NFe: " + dup.getPAR_CODI().trim());
        try {
            if ("DIGITADO".equals(dup.getPAR_STATUS().trim()) && BRADESCO.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                String json = montaRegistroDeBoletoBradesco(dup);
                System.out.println("[DEBUG] JSON enviado ao Bradesco: " + json);
                String jsonResponse = "";
                if (System.getProperty("tpAmb").equals("1")) { //Ambiente de Produção
                    jsonResponse = enviaJsonBradescoWs(ServicosBradescoEnum.REGISTRODEBOLETOBRADESCO, json, "POST");
                } else {
                    //Json teste para Ambiente de Homologação
                    //jsonResponse = "{  \"idProduto\" : 9,  \"negociacao\" : 336600000000047654,  \"cpssoaJuridContr\" : 2269651,  \"ctpoContrNegoc\" : 48,  \"nseqContrNegoc\" : 237202,  \"cprodtServcOper\" : 1730,  \"nuTituloGerado\" : 7016211,  \"tp08Reg1\" : 1,  \"agencCred10\" : 0,  \"ctaCred10\" : 0,  \"digCred10\" : \"00\",  \"cip10\" : 0,  \"codStatus10\" : 1,  \"status10\" : \"A VENCER/VENCIDO\",  \"nomeBeneficiario\" : \"CAMAR PLASTICOS LTDA\",  \"logradouroBeneficiario\" : \"R BLENDA              206\",  \"nuLogradouroBeneficiario\" : \"\",  \"complementoLogradouroBeneficiario\" : \"\",  \"bairroBeneficiario\" : \"JARDIM PEROLA\",  \"cepBeneficiario\" : 13454,  \"cepComplementoBeneficiario\" : 189,  \"municipioBeneficiario\" : \"SANTA BARBARA D'OEST\",  \"ufBeneficiario\" : \"SP\",  \"razCredt10\" : 0,  \"nomePagador\" : \"MARIA OCELIA IRANI DE OLIVEIRA ME\",  \"cpfcnpjPagador\" : 3107970000189,  \"enderecoPagador\" : \"RUACOMENDADOR JOAO MARICATO\",  \"bairroPagador\" : \"ALTO\",  \"municipioPagador\" : \"JABOTICABAL\",  \"ufPagador\" : \"SP\",  \"cepPagador\" : 14875,  \"cepComplementoPagador\" : \"600\",  \"cebp10\" : \"\",  \"debitoAuto10\" : \"\",  \"aceite10\" : \"N\",  \"endEletronicoPagador\" : \"\",  \"nomeSacadorAvalista\" : \"CAMAR PLASTICOS LTDA\",  \"cnpjCpfSacadorAvalista\" : 52703063000108,  \"enderecoSacadorAvalista\" : \"RUA DA BLENDA\",  \"municipioSacadorAvalista\" : \"SANTA BARBARA D'OESTE\",  \"ufSacadorAvalista\" : \"SP\",  \"cepSacadorAvalista\" : 13454,  \"cepComplementoSacadorAvalista\" : 189,  \"tp08Reg2\" : 2,  \"cense10\" : 0,  \"agenOper10\" : 0,  \"bcoDepos10\" : 0,  \"agenDepos10\" : 0,  \"seuNumeroTitulo\" : \"02987\",  \"dtRegistro\" : \"04112025\",  \"especieDocumentoTitulo\" : \"CH\",  \"descEspecie\" : \"\",  \"vlIOF\" : 0,  \"dtEmissao\" : \"04112025\",  \"codigoMoedaTitulo\" : \"R$\",  \"quantidadeMoeda\" : 0,  \"quantidadeCasas\" : 2,  \"dtVencimento\" : \"03.12.2025\",  \"descricacaoMoeda\" : \"R$\",  \"vlTitulo\" : 103,  \"vlAbatimento\" : 0,  \"dtInstrucaoProtestoNegativação\" : \"\",  \"diasInstrucaoProtestoNegativação\" : 0,  \"dataEnvioCartorio\" : \"\",  \"numeroCartorio\" : \"\",  \"numeroProtocoloCartorio\" : \"\",  \"dataPedidoSustacao\" : \"\",  \"dataSustacao\" : \"\",  \"dtMulta\" : \"0\",  \"vlMulta\" : 0,  \"qtdeCasasDecimaisMulta\" : 0,  \"cdValorMulta\" : 0,  \"descCdMulta\" : \"\",  \"dtJuros\" : \"03122025\",  \"vlJurosAoDia\" : 0,  \"dtDesconto1Bonificacao\" : \"\",  \"vlDesconto1Bonificacao\" : 0,  \"qtdeCasasDecimaisDesconto1Bonificacao\" : 0,  \"cdValorDesconto1Bonificacao\" : 0,  \"descCdDesconto1Bonificacao\" : \"\",  \"dtDesconto2\" : \"\",  \"vlDesconto2\" : 0,  \"qtdeCasasDecimaisDesconto2\" : 0,  \"cdValorDesconto2\" : 0,  \"descCdDesconto2\" : \"\",  \"dtDesconto3\" : \"\",  \"vlDesconto3\" : 0,  \"qtdeCasasDecimaisDesconto3\" : 0,  \"cdValorDesconto3\" : 0,  \"descCdDesconto3\" : \"\",  \"diasDispensaMulta\" : 0,  \"diasDispensaJuros\" : 1,  \"cdBarras\" : \"<NWnnwnNnWwNwwnNNwnNwnnWNwnnWWnnnWWnnnWWnwnNNwWWnnnwWNnnnwWNnnwNWnnnWWnnnwWNNnwwNnWwnNWnnnWnnWWnnnwNWNwWnnnnWNw>\",  \"linhaDigitavel\" : \"23793.36601 90000.701624 11004.765407 6 12840000000103\",  \"despCart10\" : 0,  \"bcoCentr10\" : 0,  \"ageCentr10\" : 0,  \"acessEsc10\" : 0,  \"tipoEndosso\" : \"\",  \"codigoOrigemProtesto\" : 0,  \"codigoOrigemTitulo\" : \"\",  \"tpVencimento\" : 0,  \"indInstrucaoProtesto\" : 0,  \"indicadorDecurso\" : 0,  \"quantidadeDiasDecurso\" : 0,  \"ctpoAbat10\" : 0,  \"cdValorJuros\" : 1,  \"tpDesconto1\" : 0,  \"tpDesconto2\" : 0,  \"tpDesconto3\" : 0,  \"nuControleParticipante\" : \"\",  \"diasJuros\" : 1,  \"cdJuros\" : 1,  \"vlJuros\" : 0,  \"cpfcnpjBeneficiário\" : \"052703063000108\",  \"vlTituloEmitidoBoleto\" : 0,  \"dtVencimentoBoleto\" : \"03/12/2025\",  \"indTitParceld10\" : \"\",  \"indParcelaPrin10\" : \"\",  \"indBoletoDda10\" : \"\",  \"dtLimitePagamentoBoleto\" : \"03/12/2025\",  \"dataImpressao10\" : 4112025,  \"horaImpressao10\" : 0,  \"identTitDda10\" : 0,  \"exibeLinDig10\" : \"N\",  \"permPgtoParcial\" : \"\",  \"qtdePgtoParcial\" : 0,  \"bancoDeb\" : 0,  \"agenciaDeb\" : 0,  \"agenciaDebDv\" : 0,  \"contaDeb\" : 0,  \"razaoContaDebito\" : 0}";
                    jsonResponse = simulaRegistroBoletoBradescoHomologacao();
                }
                RETORNOtableModel.addRow(new Object[]{
                    "", numeroNFe, dup.getPAR_PARC().trim(), "",
                    "Título de cobrança enviado para o Bradesco com sucesso"
                });
                System.out.println("[RETORNO] Retorno da API Bradesco (duplicata " + dup.getPAR_NUMDOC() + "): " + jsonResponse);
                // Verifica sucesso pela presença de campos-chave
                if (jsonResponse != null && jsonResponse.contains("linhaDigitavel")) {
                    ObjectMapper mapper = new ObjectMapper();
                    ResponseJsonREGISTRODEBOLETOBRADESCOdto response = mapper.readValue(jsonResponse, ResponseJsonREGISTRODEBOLETOBRADESCOdto.class);
                    // Atualiza status da duplicata
                    RETORNOtableModel.addRow(new Object[]{
                        "", numeroNFe, dup.getPAR_PARC().trim(), response.getNuTituloGerado(),
                        "Título de cobrança registrado com sucesso"
                    });
                    System.out.println("Linha digitável retornado com sucesso: " + response.getLinhaDigitavel());
                    System.out.println("Código de barras retornado com sucesso: " + response.getCdBarras());
                    boolean gerado = montaMalote.geraLoteMaloteAposRegistroNoBanco(BRADESCO.getNumeroDoBanco(), dup, response);
                    String linhaDigitavel = response.getLinhaDigitavel().replaceAll("\\D", "");
                    EBCDICConverter eBCDICConverter = new EBCDICConverter();
                    String codigoDeBarras = eBCDICConverter.ebcdicToNumeric(response.getCdBarras());
                    if (gerado) {
                        RETORNOtableModel.addRow(new Object[]{
                            "", numeroNFe, dup.getPAR_PARC().trim(), response.getNuTituloGerado(),
                            "Boleto de cobranca gerado com sucesso"
                        });
                        if (ccNFEDUPLICdto != null) {
                            ccNFEDUPLICdto.setPAR_ARQREMESSA("API");
                            ccNFEDUPLICdto.setPAR_DATAREMESSA(new Date());
                            ccNFEDUPLICdto.setPAR_VENCTITULO(ccNFEDUPLICdto.getPAR_VENC());
                            ccNFEDUPLICdto.setPAR_STATUS("REGISTRADO"); //REGISTRADO
                            ccNFEDUPLICdto.setPAR_LINHADIGITAVEL(linhaDigitavel);
                            ccNFEDUPLICdto.setPAR_CODIGODEBARRAS(codigoDeBarras);
                            ccNFEDUPLICdto.setPAR_NONU(response.getNuTituloGerado() != null ? String.valueOf(response.getNuTituloGerado()) : "");
                            CANFEDUPLICcontroller.Update(ccNFEDUPLICdto);
                            RETORNOtableModel.addRow(new Object[]{
                                "", numeroNFe, dup.getPAR_PARC().trim(), response.getNuTituloGerado(),
                                "Base de dados LOCAL da duplicata atualizado com sucesso"
                            });
                            numeroNFe = String.format("%06d", Integer.parseInt(ccNFEDUPLICdto.getPAR_CODI().trim()));
                            NFECBdto nfecb = NFEcontroller.NamedQueryCBFindByIDENNF(numeroNFe);
                            if (nfecb != null) {
                                nfecb.setINFNFE_TITULOREGISTRADO("Sim");
                                NFEcontroller.Update(nfecb);
                            }
                        }
                    }
                } else {
                    RETORNOtableModel.addRow(new Object[]{
                        "", numeroNFe, dup.getPAR_PARC().trim(), "",
                        "Erro no retorno da API do Bradesco (serviço fora do ar ou indisponível)."
                    });
                    System.out.println("Resposta não contém dados válidos: " + jsonResponse);
                    return false;
                }
            }
            if ("REGISTRADO".equals(dup.getPAR_STATUS().trim()) && BRADESCO.getNumeroDoBanco().equals(dup.getPAR_BANCO())) {
                RETORNOtableModel.addRow(new Object[]{
                    "", numeroNFe, dup.getPAR_PARC().trim(), dup.getPAR_NONU().trim(),
                    "Título ja está REGISTRADO no Banco: " + BRADESCO.getNomeDoBanco() + ", verifique"
                });
                return true;
            }

        } catch (Exception ex) {
            System.err.println("[ERRO] Falha ao registrar duplicata " + dup.getPAR_NUMDOC() + ": " + ex.getMessage());
            ex.printStackTrace();
            RETORNOtableModel.addRow(new Object[]{
                "", numeroNFe, dup.getPAR_PARC().trim(), "",
                "Exceção ao registrar o titulo de cobranca (API Bradesco): " + ex.getMessage()
            });
        }
        System.out.println("[INFO] Registro do boleto finalizado para a NFe ");
        return true;
    }

    private String simulaRegistroBoletoBradescoHomologacao() {

        return "{"
                + "\"idProduto\":9,"
                + "\"negociacao\":336600000000047654,"
                + "\"cpssoaJuridContr\":2269651,"
                + "\"ctpoContrNegoc\":48,"
                + "\"nseqContrNegoc\":237202,"
                + "\"cprodtServcOper\":1730,"
                + "\"nuTituloGerado\":7016211,"
                + "\"codStatus10\":1,"
                + "\"status10\":\"A VENCER/VENCIDO\","
                + "\"linhaDigitavel\":\"23793.36601 90000.701624 11004.765407 6 12840000000103\","
                + "\"cdBarras\":\"NWnnwnNnWwNwwnNNwnNwnnWNwnnWWnnnWWnnnWWnwnNNwWWnnnwWNnnnwWNnnwNWnnnWWnnnwWNNnwwNnWwnNWnnnWnnWWnnnwNWNwWnnnnWNw\""
                + "}";
    }

}
