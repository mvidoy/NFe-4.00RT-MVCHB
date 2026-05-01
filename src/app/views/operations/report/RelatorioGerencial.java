package app.views.operations.report;

import services.Conection;
import com.frontend.config.env;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioGerencial extends javax.swing.JInternalFrame {

    env Env = new env();
    private JInternalFrame parent;
    private int selection;
    private final JDesktopPane jDesktop;
    public String wprod_numero;

    public RelatorioGerencial(JDesktopPane jDesktop, JInternalFrame p) {
        this.jDesktop = jDesktop;
        initComponents();
        double width = this.getSize().getWidth();
        double height = this.getSize().getHeight();
        int wwidth = (int) width;
        int wheight = (int) height;
        this.setLocation((this.getSize().width - wwidth) / 2, (((this.getSize().height - 100) - wheight) / 2) + 10);
        jPanel1.setLocation((this.getSize().width - 100) / 2, (((this.getSize().height) / 2)));
        jDC_DataInicial.grabFocus();
        try {
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Dados não foram localizados");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        botao_imprimir = new javax.swing.JButton();
        botao_cancela = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jDC_DataInicial = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jDC_DataFinal = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setResizable(true);
        setTitle("Relatório Gerencial de NFes Emitidas no Período (Autorizadas) - Revisão: 02 - Data da Última Revisão: 01/10/2019 - Data da Elaboração: 09/11/2016");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        botao_imprimir.setText("Ok");
        botao_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_imprimirActionPerformed(evt);
            }
        });

        botao_cancela.setText("Cancela");
        botao_cancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cancelaActionPerformed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Período de:");

        jDC_DataInicial.setToolTipText("Entre com a Data Inicial");
        jDC_DataInicial.setFocusCycleRoot(true);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("a");

        jDC_DataFinal.setToolTipText("Entre com a Data Inicial");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botao_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDC_DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_cancela, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jDC_DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDC_DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDC_DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_cancela)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_imprimir)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(320, 320, 320))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botao_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_imprimirActionPerformed
        if (jDC_DataInicial.getDate() == null || jDC_DataFinal.getDate() == null) {
            JOptionPane.showMessageDialog(jDesktop, "Data Inválida, Verifique!!!", "Mensagem", 1);
        } else {
            ImprimeRelatorio();
        }
    }//GEN-LAST:event_botao_imprimirActionPerformed

    private void botao_cancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cancelaActionPerformed
        this.dispose();
    }//GEN-LAST:event_botao_cancelaActionPerformed
    private javax.swing.JDesktopPane jDesktopPane2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_cancela;
    private javax.swing.JButton botao_imprimir;
    private com.toedter.calendar.JDateChooser jDC_DataFinal;
    private com.toedter.calendar.JDateChooser jDC_DataInicial;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    public void enviaRegistro(String wVar1) {
        this.setTitle("RELATÓRIO: " + wVar1);
    }

    public void ImprimeRelatorio() {
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jPanel1.setCursor(cursor);
        Conection con_default;
        con_default = new Conection();
        con_default.conectaBs();
        int selection = JOptionPane.showConfirmDialog(jDesktop, "Envia para impressora ?", "Dialogo de Impressão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        try {
            String formatoData = "yyyy-MM-dd";
            SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData);
            String gSQL = "SELECT * FROM nfe "
                    + "WHERE "
                    + "(to_char(ide_dhemi, 'YYYY-MM-DD') >= '" + dataFormatada.format(jDC_DataInicial.getDate()) + "' AND "
                    + "to_char(ide_dhemi, 'YYYY-MM-DD') <= '" + dataFormatada.format(jDC_DataFinal.getDate()) + "') AND "
                    + "trim(infnfe_statusnfe) = 'Autorizada' "
                    + "ORDER BY ide_nnf ASC";
            con_default.executeQuery(gSQL);
            boolean wFlag = false;
            while (con_default.resultset.next()) {
                wFlag = true;
            }
            if (wFlag != true) {
                JOptionPane.showMessageDialog(jDesktopPane1, "Dados não foram localizados");
                con_default.resultset.close();
                con_default.desconecta();
                cursor = Cursor.getDefaultCursor();
                jPanel1.setCursor(cursor);
                return;
            }
            con_default.resultset.close();
            con_default.executeQuery(gSQL);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_default.resultset);
            HashMap parametros = new HashMap();
            formatoData = "dd/MM/yyyy";
            dataFormatada = new SimpleDateFormat(formatoData);
            parametros.put("wDataInicial", dataFormatada.format(jDC_DataInicial.getDate()));
            parametros.put("wDataFinal", dataFormatada.format(jDC_DataFinal.getDate()));
            //JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResource("/main/resources").getPath() + "/report/crtrelatoriogerencial.jasper", parametros, jrRS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(Env.getpath_pastaAplicacao() + "/relatorios/crtrelatoriogerencial.jasper", parametros, jrRS);
            
            Locale lingua = new Locale("pt", "pt");
            JasperViewer viewer = new JasperViewer(jasperPrint, false, lingua);
            this.setMaximum(true);
            this.setSelected(true);
            viewer.setZoomRatio(0.80F);//Define o tamanho do ZOOM ao abrir o relatório
            double width = this.getSize().getWidth();
            double height = this.getSize().getHeight();
            int wwidth = (int) width;
            int wheight = (int) height;
            viewer.setSize((wwidth), (wheight));
            this.jDesktopPane1.removeAll();
            viewer.setVisible(true);
            this.jDesktopPane1.add(viewer.getContentPane());
            viewer.dispose();
            con_default.desconecta();
            cursor = Cursor.getDefaultCursor();
            jPanel1.setCursor(cursor);
            if (selection == JOptionPane.YES_OPTION) {
                JasperPrintManager.printPages(jasperPrint, 0, jasperPrint.getPages().size() - 1, false);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(jDesktop, "Dados não foram localizados: " + erro);
        }
    }
}
