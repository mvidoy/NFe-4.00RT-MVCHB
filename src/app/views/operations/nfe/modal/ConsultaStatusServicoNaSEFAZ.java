package app.views.operations.nfe.modal;

import app.views.index;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consStatServ.TRetConsStatServ;
import java.awt.Color;
import java.awt.Cursor;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import lib.ConfiguracoesNfeIniciais;

public class ConsultaStatusServicoNaSEFAZ extends javax.swing.JInternalFrame {

    private static final int SSL_PORT = 443;
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    private index veioDoframe1;

    public ConsultaStatusServicoNaSEFAZ(
            JDesktopPane jDesktop,
            JInternalFrame jFrame) {
        this.jDesktopPane2 = jDesktop;
        this.jFrame = jFrame;
        initComponents();
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        jLabel1.setText("Processando...");
        jLabel2.setText("Por favor, aguarde.");
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        cursor = Cursor.getDefaultCursor();
        jDesktop.setCursor(cursor);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        botao_sair = new javax.swing.JButton();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("Consulta Status do Serviço - Revisão: 01 - Última Revisão: 09/09/2018 - Elaboração: 25/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.jpg"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta Status do Serviço/Ativa CONTINGÊNCIA (SVC)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo da Operação:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton1.setText("Envia");
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204), 2));

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

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(1, 1, 1))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(10, 10, 10)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botao_sair)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane2)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane2)
                .addContainerGap())
        );

        jDesktopPane2.getAccessibleContext().setAccessibleName(" Data da Elaboração: 14/07/2016");

        getAccessibleContext().setAccessibleDescription(" Data da Elaboração: 14/07/2016");

        setBounds(0, 0, 677, 502);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    private void ConsultaStatusServico() {

        try {

            try {

                ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
                //Efetua Consulta
                TRetConsStatServ retorno = Nfe.statusServico(configINI.iniciaConfiguracoes(), DocumentoEnum.NFE);
                System.setProperty("contigenciaSVC", "FALSE");
                if (retorno.getCStat() != null && retorno.getCStat().equals("107")) {
                    jLabel5.setText("<html><center>"
                            + "Ambiente Normal de Autorização. \n"
                            + retorno.getXMotivo().trim() + "</center></html>");
                } else {
                    jLabel5.setText("<html><center>" + retorno.getXMotivo().trim() + "</center></html>");
                    //jLabel5.setText("<html>Erro na mensagem de retorno!!!!</html>");
                }
                //throw new Exception();
            } catch (Exception e) {

                //jLabel5.setText("<html><center>" + e.toString() + "</center></html>");
                jLabel5.setForeground(Color.red);
                jLabel5.setText("<html><center>"
                        + "Ambiente Normal de Autorização INDISPONÍVEL!!!"
                        + "</center></html>");

                int selection = JOptionPane.showConfirmDialog(this, "Deseja ATIVAR CONTINGÊNCIA - (SVC)?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (selection == JOptionPane.NO_OPTION) {
                    return;
                }
                System.setProperty("contigenciaSVC", "TRUE");
                ConfiguracoesNfeIniciais configINI = new ConfiguracoesNfeIniciais();
                //Efetua Consulta
                TRetConsStatServ retorno = Nfe.statusServico(configINI.iniciaConfiguracoes(), DocumentoEnum.NFE);
                System.setProperty("contigenciaSVC", "FALSE");
                if (retorno.getCStat() != null && retorno.getCStat().equals("107")) {
                    jLabel5.setForeground(Color.black);
                    jLabel5.setText("<html><center>"
                            + "CONTINGÊNCIA - (SVC), foi ativada com SUCESSO. \n"
                            + retorno.getXMotivo().trim() + "."
                            + "</center></html>");
                    System.setProperty("contigenciaSVC", "TRUE");
                }

                if (retorno.getCStat() != null
                        && (retorno.getCStat().equals("113")
                        || retorno.getCStat().equals("114"))) {
                    jLabel5.setText("<html><center>"
                            + "CONTINGÊNCIA - (SVC), não pode ser ativada!!!.\n"
                            + retorno.getXMotivo().trim() + "."
                            + "</center></html>");
                    System.setProperty("contigenciaSVC", "FALSE");
                }
            }

        } catch (NfeException ex) {
            Logger.getLogger(ConsultaStatusServicoNaSEFAZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificadoException ex) {
            Logger.getLogger(ConsultaStatusServicoNaSEFAZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultaStatusServicoNaSEFAZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaStatusServicoNaSEFAZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConsultaStatusServicoNaSEFAZ.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class Process extends Thread {

        public void run() {
            //Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            //jDesktopPane2.setCursor(cursor);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            jLabel1.setText("Consultando Status dos Serviços");
            jLabel2.setText("Por favor, aguarde.");
            jProgressBar1.setIndeterminate(true);
            jProgressBar1.setValue(0);
            ConsultaStatusServico();
            jProgressBar1.setIndeterminate(false);
            jLabel2.setText("Processo Finalizado.");
            jLabel1.setText("");
            jProgressBar1.setValue(100);
            //cursor = Cursor.getDefaultCursor();
            //jDesktopPane2.setCursor(cursor);
        }
    }
}
