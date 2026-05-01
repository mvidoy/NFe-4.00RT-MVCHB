package app.views.operations.nfe.modal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class DetalhaEventoNFe extends javax.swing.JInternalFrame {

    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    String gChave = "";
    String gNFe = "";
    String xml_autorizado = "";
    String xml_envevento_autorizado = "";

    /*
     //Configura variaveis para detalhamento 
                System.setProperty("det_evento_xmotivo", map.get(i).getString("infprot_xmotivo").trim());
                System.setProperty("det_evento_nprot", map.get(i).getString("infprot_nprot").trim());
                System.setProperty("det_evento_sequencia", sReg);
                System.setProperty("det_evento_dhrecbto", map.get(i).getString("infprot_dhrecbto"));
                System.setProperty("det_evento_xcorrecao", map.get(i).getString("infprot_xcorrecao"));
                System.setProperty("det_evento_xjust", map.get(i).getString("infprot_xjust"));
     */
    public DetalhaEventoNFe(JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String nNFe,
            String xmotivo,
            String nprot,
            String sequencia,
            String dhrecbto,
            String xcorrecao,
            String xjust
    ) {
        this.jDesktop = jDesktop;
        this.jFrame = jFrame;
        gNFe = nNFe;
        initComponents();
        TitledBorder title = null;
        title = BorderFactory.createTitledBorder(xmotivo);
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
        title.setTitleColor(Color.BLUE);
        jDesktopPane2.setBorder(title);
        //jDesktopPane2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        //jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(xmotivo));

        tf_xmotivo.setText(xmotivo);
        tf_nprot.setText(nprot);
        tf_sequencia.setText(sequencia);
        tf_dhrecbto.setText(dhrecbto);
        jLabel47.setText("Data e Hora da " + xmotivo);
        jLabel48.setVisible(false);
        tf_xdesc.setVisible(false);
        jScrollPane1.setVisible(false);
        //jDesktopPane2.remove(jLabel48);
        //jDesktopPane2.remove(tf_xdesc);
        //jDesktopPane2.remove(jScrollPane1);
        if (xmotivo.contains("Carta")) {
            //jDesktopPane2.add(jLabel48);
            //jDesktopPane2.add(tf_xdesc);
            //jDesktopPane2.add(jScrollPane1);
            jLabel48.setVisible(true);
            tf_xdesc.setVisible(true);
            jScrollPane1.setVisible(true);
            jLabel48.setText("Descrição da " + xmotivo);
            tf_xdesc.setText(xcorrecao);
        }
        if (xmotivo.contains("Cancela")) {
            //jDesktopPane2.add(jLabel48);
            //jDesktopPane2.add(tf_xdesc);
            //jDesktopPane2.add(jScrollPane1);
            jLabel47.setText("Data e Hora do " + xmotivo);
            jLabel48.setVisible(true);
            tf_xdesc.setVisible(true);
            jScrollPane1.setVisible(true);
            jLabel48.setText("Descrição do " + xmotivo);
            tf_xdesc.setText(xjust);
        }
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
        tf_xmotivo = new javax.swing.JTextField();
        botao_sair = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tf_nprot = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        tf_sequencia = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        tf_dhrecbto = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_xdesc = new javax.swing.JTextArea();

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
        setTitle("Evento da Nota Fiscal Eletrônica - Revisão: 01 - Data da Última Revisão: 25/03/2021 - Data da Elaboração: 24/03/2021");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autorização de Uso", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tf_xmotivo.setEditable(false);
        tf_xmotivo.setBackground(new java.awt.Color(236, 233, 216));
        tf_xmotivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_xmotivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_xmotivo.setToolTipText("");
        tf_xmotivo.setBorder(null);

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

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Evento");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Número do Protocolo");

        tf_nprot.setEditable(false);
        tf_nprot.setBackground(new java.awt.Color(236, 233, 216));
        tf_nprot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_nprot.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_nprot.setToolTipText("");
        tf_nprot.setBorder(null);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Número de Sequência");

        tf_sequencia.setEditable(false);
        tf_sequencia.setBackground(new java.awt.Color(236, 233, 216));
        tf_sequencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_sequencia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_sequencia.setToolTipText("");
        tf_sequencia.setBorder(null);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Data e Hora da Autorização");

        tf_dhrecbto.setEditable(false);
        tf_dhrecbto.setBackground(new java.awt.Color(236, 233, 216));
        tf_dhrecbto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_dhrecbto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_dhrecbto.setToolTipText("");
        tf_dhrecbto.setBorder(null);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Descrição da Correção");

        jScrollPane1.setBorder(null);

        tf_xdesc.setEditable(false);
        tf_xdesc.setBackground(new java.awt.Color(236, 233, 216));
        tf_xdesc.setColumns(20);
        tf_xdesc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_xdesc.setLineWrap(true);
        tf_xdesc.setRows(3);
        tf_xdesc.setBorder(null);
        tf_xdesc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_xdesc.setEnabled(false);
        jScrollPane1.setViewportView(tf_xdesc);

        jDesktopPane2.setLayer(tf_xmotivo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_sair, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_nprot, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_sequencia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_dhrecbto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel48, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botao_sair)
                        .addGap(10, 10, 10))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_sequencia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(tf_xmotivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(64, 64, 64)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_nprot)
                                    .addComponent(tf_dhrecbto))
                                .addGap(270, 270, 270))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(148, Short.MAX_VALUE))))
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_xmotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nprot, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_sequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_dhrecbto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(botao_sair)
                .addGap(10, 10, 10))
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
                .addComponent(jDesktopPane2)
                .addContainerGap())
        );

        setBounds(0, 0, 809, 365);
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
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tf_dhrecbto;
    private javax.swing.JTextField tf_nprot;
    private javax.swing.JTextField tf_sequencia;
    private javax.swing.JTextArea tf_xdesc;
    private javax.swing.JTextField tf_xmotivo;
    // End of variables declaration//GEN-END:variables

}
