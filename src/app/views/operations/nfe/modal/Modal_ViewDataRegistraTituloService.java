package app.views.operations.nfe.modal;

import app.views.operations.nfe.modal.services.NfeEnvioServices.NfePosProcessamentoService;
import com.backend.controllers.CANFENOTFIScontroller;
import com.backend.controllers.NFEcontroller;
import com.backend.dtos.CANFENOTFISdto;
import com.backend.dtos.NFEdto;
import com.frontend.config.env;
import com.frontend.util.VerifyFileExcelOpen;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
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
import util.AlignTableHeaderCenter;

public final class Modal_ViewDataRegistraTituloService extends javax.swing.JDialog {

    private String InfNFe_NNF = "";

    private DefaultTableModel RETORNOtableModel = new DefaultTableModel();

    private final env Env = new env();
    private final String planPrevcaixa
            = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";

    // ===============================================================
    // CONSTRUTOR ORIGINAL (MANTIDO PARA COMPATIBILIDADE)
    // ===============================================================
    public Modal_ViewDataRegistraTituloService(
            String InfNFe_NNF) throws SQLException, ParseException {

        this.InfNFe_NNF = InfNFe_NNF;
        initComponents();
        montaTable();
    }

    public Modal_ViewDataRegistraTituloService(
            javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        RETORNOtable = new javax.swing.JTable();

        botao_registratitulo = new javax.swing.JButton();
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

        botao_registratitulo.setMnemonic('L');
        botao_registratitulo.setText("Registrar Título(s)");
        botao_registratitulo.setToolTipText("");
        botao_registratitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_registratitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_registratituloMouseMoved(evt);
            }
        });
        botao_registratitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_registratituloActionPerformed(evt);
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
        jDesktopPane2.setLayer(botao_registratitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
                        .addComponent(botao_registratitulo)
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
                    .addComponent(botao_registratitulo))
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

    private void botao_registratituloMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_registratituloMouseMoved
        botao_registratitulo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_registratituloMouseMoved

    private void botao_registratituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_registratituloActionPerformed
        if (!new File(planPrevcaixa).exists()) {
            JOptionPane.showMessageDialog(this,
                    "A planilha: " + planPrevcaixa + " não encontrada.",
                    "Mensagem do Sistema",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (VerifyFileExcelOpen.isOpen(planPrevcaixa)) {
            JOptionPane.showMessageDialog(this,
                    "A planilha está aberta. Feche antes de continuar.",
                    "Mensagem do Sistema",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selection = JOptionPane.showConfirmDialog(
                this,
                "Deseja registrar título(s) de Cobrança?",
                "Mensagem do Sistema",
                JOptionPane.YES_NO_OPTION);

        if (selection != JOptionPane.YES_OPTION) {
            return;
        }

        Cursor wait = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        setCursor(wait);

        try {

            String numeroNFe
                    = String.format("%06d", Integer.parseInt(InfNFe_NNF.trim()));

            NFEdto nfeDto
                    = NFEcontroller.NamedQueryFindByIDENNF(
                            Integer.parseInt(numeroNFe));

            CANFENOTFISdto nota
                    = CANFENOTFIScontroller.NamedQueryFindByNota(numeroNFe);

            if (nota == null
                    || nfeDto == null
                    || nfeDto.getIDE_TPNF() != 1
                    || !nfeDto.getIDE_NATOP().toUpperCase().contains("VENDA")) {

                JOptionPane.showMessageDialog(this,
                        "Natureza da operação não é VENDA.",
                        "Mensagem do Sistema",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // =======================================================
            // USA O SERVICE CENTRALIZADO
            // =======================================================
            NfePosProcessamentoService service
                    = new NfePosProcessamentoService();

            service.setTableRowListener(row -> {
                RETORNOtableModel.addRow(row);

                int last = RETORNOtableModel.getRowCount() - 1;
                if (last >= 0) {
                    RETORNOtable.scrollRectToVisible(
                            RETORNOtable.getCellRect(last, 0, true));
                }
            });

            service.registrarBoletosAvulso(numeroNFe);

        } catch (Exception ex) {
            Logger.getLogger(
                    Modal_ViewDataRegistraTituloService.class.getName())
                    .log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(this,
                    "Erro: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_botao_registratituloActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_closeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseMoved
        btn_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_closeMouseMoved

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modal_ViewDataRegistraTituloService dialog = new Modal_ViewDataRegistraTituloService(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RETORNOtable;
    private javax.swing.JButton botao_registratitulo;
    private javax.swing.JButton btn_close;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JScrollPane jScrollPane13;
    // End of variables declaration//GEN-END:variables

    private void montaTable() {

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
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        };

        RETORNOtable.setModel(RETORNOtableModel);
        RETORNOtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer esquerda
                = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);

        TableCellRenderer header
                = new AlignTableHeaderCenter();

        RETORNOtable.getTableHeader()
                .getColumnModel()
                .getColumn(0)
                .setHeaderRenderer(header);
    }

    // ===============================================================
    // RENDER
    // ===============================================================
    public class ColorirLinhaRetorno extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component result
                    = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);

            String text
                    = table.getValueAt(row, 4).toString();

            if (text.contains("sucesso")
                    || text.contains("ando")) {
                result.setForeground(new Color(51, 153, 0));
            } else {
                result.setForeground(Color.RED);
            }

            result.setFont(new Font("Arial", Font.BOLD, 11));
            return result;
        }
    }

    class Imagem extends JLabel implements TableCellRenderer {

        public Imagem() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            ImageIcon imagem;
            String text
                    = table.getValueAt(row, 4).toString();

            if (text.contains("sucesso")
                    || text.contains("ando")) {
                imagem = new ImageIcon(
                        getClass().getResource("/assets/sucesso.jpg"));
            } else {
                imagem = new ImageIcon(
                        getClass().getResource("/assets/erro.png"));
            }

            setIcon(imagem);
            setText(value == null ? "" : value.toString());
            return this;
        }
    }
}
