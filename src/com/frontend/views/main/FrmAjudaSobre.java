package com.frontend.views.main;

import javax.swing.*;

public class FrmAjudaSobre extends javax.swing.JInternalFrame {

    private final JDesktopPane jDesktop;
    public String wprod_numero;
    public String wprod_item;
    public String w_escopo;
    public String w_dataalteracao;
    public String w_descricaoalteracao;
    public String w_racp;
    public String w_revisao;

    public FrmAjudaSobre(JDesktopPane jDesktop, JInternalFrame p
    ) {
        this.jDesktop = jDesktop;
        initComponents();
        //ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("assets/icone_nfe.jpg"));
        //this.setFrameIcon(icon);
        try {
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(jDesktopPane1, "Dados não foram localizados");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel72 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tf_descricaosolicitacaoprojeto = new javax.swing.JTextArea();
        jLabel73 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(217, 228, 241));
        setClosable(true);
        setResizable(true);
        setTitle("Sobre MÓDULO 01 - Última Revisão: 27/01/2025");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/md1/frontend/assets/icone_modulo1.jpg"))); // NOI18N
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(98, 78, 165));
        jDesktopPane1.setAutoscrolls(true);

        jLabel72.setBackground(new java.awt.Color(0, 0, 0));
        jLabel72.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("Resolução de vídeo Recomendada: 1280 x 720");

        jLabel76.setBackground(new java.awt.Color(0, 0, 0));
        jLabel76.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Versão do JDK: 8, Atualização 131 (build 1.8.0_131)");

        jScrollPane3.setBorder(null);

        tf_descricaosolicitacaoprojeto.setEditable(false);
        tf_descricaosolicitacaoprojeto.setBackground(new java.awt.Color(98, 78, 165));
        tf_descricaosolicitacaoprojeto.setColumns(20);
        tf_descricaosolicitacaoprojeto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tf_descricaosolicitacaoprojeto.setForeground(new java.awt.Color(255, 255, 255));
        tf_descricaosolicitacaoprojeto.setLineWrap(true);
        tf_descricaosolicitacaoprojeto.setRows(2);
        tf_descricaosolicitacaoprojeto.setToolTipText("Descrição da Solicitação do Projeto");
        tf_descricaosolicitacaoprojeto.setAutoscrolls(false);
        tf_descricaosolicitacaoprojeto.setCaretColor(new java.awt.Color(255, 255, 255));
        tf_descricaosolicitacaoprojeto.setFocusable(false);
        tf_descricaosolicitacaoprojeto.setRequestFocusEnabled(false);
        tf_descricaosolicitacaoprojeto.setSelectionColor(new java.awt.Color(255, 255, 255));
        tf_descricaosolicitacaoprojeto.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(tf_descricaosolicitacaoprojeto);

        jLabel73.setBackground(new java.awt.Color(0, 0, 0));
        jLabel73.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Versão 1.00 (27/01/2025)");

        jLabel78.setBackground(new java.awt.Color(0, 0, 0));
        jLabel78.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Versão do Apache NetBeans: 14, Versão do PostgreSql: 9.3.5, Versão do jdbc: postgresql-9.4-1212");

        jLabel71.setBackground(new java.awt.Color(0, 0, 0));
        jLabel71.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel74.setBackground(new java.awt.Color(0, 0, 0));
        jLabel74.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel75.setBackground(new java.awt.Color(0, 0, 0));
        jLabel75.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Autor: Osvaldo Vidoy - Engenheiro de Software");

        jDesktopPane1.setLayer(jLabel72, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel76, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel73, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel78, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel71, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel74, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel75, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private javax.swing.JDesktopPane jDesktopPane2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea tf_descricaosolicitacaoprojeto;
    // End of variables declaration//GEN-END:variables
}
