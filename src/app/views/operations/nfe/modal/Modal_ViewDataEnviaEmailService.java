package app.views.operations.nfe.modal;

import app.views.operations.nfe.modal.services.NfeEnvioEmailService;
import com.backend.api.cobranca.mensageria.EnviaMalote;
import com.backend.api.cobranca.mensageria.MontaMalote;
import com.backend.exceptions.MD1Exception;
import com.frontend.config.env;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
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

public final class Modal_ViewDataEnviaEmailService extends javax.swing.JDialog {

    String InfNFe_NNF = "";

    DefaultTableModel RETORNOtableModel = new DefaultTableModel();
    MontaMalote montaMalote = new MontaMalote();
    EnviaMalote enviaMalote = new EnviaMalote();
    env Env = new env();
    String PATH_APP = Env.getPATH_APP();
    String planPrevcaixa = "\\\\" + Env.getHOST() + "\\geral\\prevcaixa.xls";

    public Modal_ViewDataEnviaEmailService(
            String InfNFe_NNF) throws MD1Exception, SQLException, ParseException {
        this.InfNFe_NNF = InfNFe_NNF;
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("/com/frontend/assets/icone_modulo1.jpg")).getImage());
        montaTable();
    }

    public Modal_ViewDataEnviaEmailService(javax.swing.JFrame parent, boolean modal) {
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
        setTitle("Envia Email para o Destinatário - Revisão: 02 - Data da Última Revisão: 27/11/2025");
        setModal(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envia Email da DANFE, XML e Boleto(s) de Cobrança Para o Destinatário", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N
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
        botao_enivaremail.setText("Envia Email");
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
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);

        try {

            int selection = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja enviar email para o destinatário?",
                    "Mensagem do Sistema",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (selection != JOptionPane.YES_OPTION) {
                return;
            }

            botao_enivaremail.setCursor(cursor);
            jPanel22.setCursor(cursor);

            NfeEnvioEmailService service = new NfeEnvioEmailService();

            service.setTableRowListener(row -> {
                RETORNOtableModel.addRow(row);

                int last = RETORNOtableModel.getRowCount() - 1;
                if (last >= 0) {
                    RETORNOtable.scrollRectToVisible(
                            RETORNOtable.getCellRect(last, 0, true));
                }
            });

            service.enviarEmail(this.InfNFe_NNF);

        } catch (Exception ex) {

            Logger.getLogger(Modal_ViewDataEnviaEmailService.class.getName())
                    .log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(
                    this,
                    "Erro: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);

        } finally {

            Cursor defaultCursor = Cursor.getDefaultCursor();
            botao_enivaremail.setCursor(defaultCursor);
            jPanel22.setCursor(defaultCursor);
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
                Modal_ViewDataEnviaEmailService dialog = new Modal_ViewDataEnviaEmailService(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botao_enivaremail;
    private javax.swing.JButton btn_close;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JScrollPane jScrollPane13;
    // End of variables declaration//GEN-END:variables

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

}
