package app.views.operations.nfe;

import app.controllers.NfeController;
import app.controllers.NfeDetProdController;
import app.controllers.NfeInformacoesPagamentoController;
import app.models.NFE;
import app.models.NFE_DET_PROD;
import app.models.NFE_INFORMACOESPAGAMENTO;
import java.awt.Cursor;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.JSONObject;
import util.FormatFields;
import util.formata;

public class NFe_Detalhe_Informacoes_de_Pagamento extends javax.swing.JInternalFrame {

    private NFe NFe_frm;
    public JDesktopPane jDesktop;
    public JInternalFrame jFrame;
    String gNF;
    String gITEM;

    public NFe_Detalhe_Informacoes_de_Pagamento(JDesktopPane jDesktop, JInternalFrame jFrame, String nNFe, String nITEM) {

        try {
            this.jDesktop = jDesktop;
            this.jFrame = jFrame;
            gNF = nNFe;
            gITEM = nITEM;
            initComponents();
            FormatFields Format = new FormatFields();
            JSONObject json = new JSONObject();
            json = NfeInformacoesPagamentoController.Index(nNFe.trim(), nITEM.trim());
            if (!json.isEmpty()) {
                PercorrejcbxNum(jcbx_tPag, formata.StrZero(json.getString("pag_tpag").trim(), 2), 2);
                tf_vPag.setText(Format.getFormated_2(json.getDouble("pag_vpag")));
                PercorrejcbxNum(jcbx_indPag, json.getString("pag_indpag").trim(), 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NFe_Detalhe_Informacoes_de_Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NFe_Detalhe_Informacoes_de_Pagamento.class.getName()).log(Level.SEVERE, null, ex);
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
        jcbx_tPag = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        tf_vPag = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jcbx_indPag = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        tf_dPag = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        botao_ok = new javax.swing.JButton();
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
        setTitle("Informações de Pagamento - Revisão: 04 - Data da Última Revisão: 10/09/2021 - Data da Elaboração: 09/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações de Pagamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jcbx_tPag.setEditable(true);
        jcbx_tPag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 - Dinheiro ", "02 - Cheque ", "03 - Cartão de Crédito ", "04 - Cartão de Débito ", "05 - Crédito Loja ", "10 - Vale Alimentação", "11 - Vale Refeição ", "12 - Vale Presente ", "13 - Vale Combustível ", "15 - Boleto Bancário ", "16 - Depósito Bancário", "17 - Pagamento Instantâneo (PIX)", "18 - Transferência bancária, Carteira Digital", "19 - Programa de fidelidade, Cashback, Crédito Virtual", "90 - Sem pagamento", "99 - Outros" }));
        jcbx_tPag.setToolTipText("");
        jcbx_tPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_tPagActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("* Meio de Pagamento");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("* Valor do Pagamento");

        tf_vPag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_vPag.setToolTipText("");
        tf_vPag.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_vPag.setEnabled(false);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Indicador da Forma de Pagamento ");

        jcbx_indPag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 - Pagamento à Vista ", "1 - Pagamento à Prazo" }));
        jcbx_indPag.setToolTipText("");
        jcbx_indPag.setEnabled(false);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("Descrição do Meio de Pagamento:");

        tf_dPag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_dPag.setToolTipText("");
        tf_dPag.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        tf_dPag.setEnabled(false);

        jDesktopPane2.setLayer(jcbx_tPag, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel59, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel60, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_vPag, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel61, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jcbx_indPag, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel62, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(tf_dPag, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_tPag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_vPag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_indPag, 0, 175, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_dPag, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jcbx_tPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(tf_vPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(jcbx_indPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(tf_dPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("(*) Campos de preenchimento obrigatório.");
        jLabel73.setToolTipText("");

        botao_ok.setMnemonic('L');
        botao_ok.setText("Salvar");
        botao_ok.setToolTipText("");
        botao_ok.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_ok.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_okMouseMoved(evt);
            }
        });
        botao_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_okActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botao_ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botao_sair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_ok)
                    .addComponent(botao_sair))
                .addGap(5, 5, 5))
        );

        setBounds(0, 0, 1028, 205);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_okActionPerformed
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO = new NFE_INFORMACOESPAGAMENTO();
            eNFE_INFORMACOESPAGAMENTO.setPag_nnf(gNF);
            eNFE_INFORMACOESPAGAMENTO.setPag_item(gITEM);
            // eNFE_INFORMACOESPAGAMENTO.setPag_indpag(tf_det_prod_cprod.getText().trim());
            eNFE_INFORMACOESPAGAMENTO.setPag_tpag(jcbx_tPag.getSelectedItem().toString().substring(0, 2));
            eNFE_INFORMACOESPAGAMENTO.setPag_vpag(tf_vPag.getText().trim().replace(".", "").replace(",", "."));
            if (NfeInformacoesPagamentoController.Update(eNFE_INFORMACOESPAGAMENTO)) {
                System.out.println("Detalhe dos produtos atualizado com sucesso!");
            } else {
                System.out.println(System.getProperty("MsgInvalid"));
            }
            if (NFe_frm != null) {
                NFe_frm.render();
            }
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (InstantiationException ex) {
            Logger.getLogger(NFe_Detalhe_Informacoes_de_Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NFe_Detalhe_Informacoes_de_Pagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_okActionPerformed

    private void jcbx_tPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_tPagActionPerformed
        if (jcbx_tPag.getSelectedItem().toString().substring(0, 2).equals("90")) {
            tf_vPag.setText("0,00");
        }
    }//GEN-LAST:event_jcbx_tPagActionPerformed

    private void botao_okMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_okMouseMoved
        botao_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_okMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_ok;
    private javax.swing.JButton botao_sair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JComboBox jcbx_indPag;
    private javax.swing.JComboBox jcbx_tPag;
    private javax.swing.JTextField tf_dPag;
    private javax.swing.JTextField tf_vPag;
    // End of variables declaration//GEN-END:variables

    public String PercorrejcbxNum(JComboBox jcbx, String wVar, int wInd) {

        try {
            if (wVar == null
                    || wVar.toString().trim().length() <= 0
                    || wInd <= 0) {
                jcbx.setSelectedIndex(-1);
                return wVar;
            }
            String wwVar = wVar.toString().trim();
            boolean wFlagjcbx = false;
            int wIndice = -1;
            if (wInd >= 3) {
                wInd = 3;
            }
            for (int i = 0; i < jcbx.getItemCount(); i++) {
                if (jcbx.getItemAt(i).toString().trim().substring(0, wInd).equals(wwVar.substring(0, wInd))) {
                    jcbx.setSelectedIndex(i);
                    wFlagjcbx = true;
                    wIndice = i;
                    break;
                }
            }
            if (wFlagjcbx == true) {
                jcbx.setSelectedIndex(wIndice);
            } else {
                jcbx.removeAllItems();
                jcbx.addItem(new NFe_Detalhe_Informacoes_de_Pagamento.comboMultidata(wwVar, wwVar));
                jcbx.setSelectedIndex(jcbx.getItemCount() - 1);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "falha não esperada!!! " + erro);
        }
        return wVar;
    }

    public class comboMultidata {

        private String valor;
        private String texto;

        public comboMultidata(String id, String label) {
            this.valor = id;
            this.texto = label;
        }

        public String getValor() {
            return valor.toString();
        }

        @Override
        public String toString() {
            return texto.toString();
        }
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.NFe_frm = veioDo1;
    }
}
