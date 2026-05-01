
package app.views.operations.nfe.modal;
import java.awt.Cursor;
import javax.swing.*;

public class MostraChavedeAcesso extends javax.swing.JInternalFrame {

    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    String gChave = "";
    String gNFe = "";
    String xml_autorizado = "";
    String xml_envevento_autorizado = "";
    
    public MostraChavedeAcesso(JDesktopPane jDesktop, JInternalFrame jFrame, String nNFe, String nChave) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
        gChave = nChave;
        gNFe = nNFe;
        initComponents();     
        tf_ChaveDeAcesso.setText(gChave);

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
        tf_ChaveDeAcesso = new javax.swing.JTextField();
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
        setTitle("Chave de Acesso - Revisão: 02 - Data da Última Revisão: 18/09/2019 - Data da Elaboração: 09/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chave de Acesso", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tf_ChaveDeAcesso.setEditable(false);
        tf_ChaveDeAcesso.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tf_ChaveDeAcesso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_ChaveDeAcesso.setToolTipText("");

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

        jDesktopPane2.setLayer(tf_ChaveDeAcesso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(tf_ChaveDeAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botao_sair)))
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(tf_ChaveDeAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(botao_sair))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 809, 171);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
         doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
           botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_sair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JTextField tf_ChaveDeAcesso;
    // End of variables declaration//GEN-END:variables

}
