package app.views;

import app.controllers.UsersController;
import app.models.USERS;
import java.net.ServerSocket;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.IOException;

public class changepassword extends javax.swing.JFrame {

    private static ServerSocket s;

    public changepassword() {
        initComponents();
        ImageIcon icone = new ImageIcon("./icons/permissao.gif");
        setIconImage(icone.getImage());
        nova_senha.grabFocus();
    }

    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        confirma_senha = new javax.swing.JPasswordField();
        nova_senha = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Altera");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setIconImage(getIconImage());
        setIconImages(null);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);

        desktopPane.setBackground(new java.awt.Color(51, 102, 0));
        desktopPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));
        desktopPane.setAlignmentX(0.0F);
        desktopPane.setAlignmentY(0.0F);
        desktopPane.setFocusTraversalPolicyProvider(true);

        jButton2.setText("Cancela");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton2);
        jButton2.setBounds(120, 100, 80, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jLabel1.setText("Nova Senha:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Confirma Senha:");

        confirma_senha.setColumns(6);
        confirma_senha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        confirma_senha.setToolTipText("Confirme a nova senha");
        confirma_senha.setFocusCycleRoot(true);
        confirma_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirma_senhaActionPerformed(evt);
            }
        });

        nova_senha.setColumns(6);
        nova_senha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nova_senha.setToolTipText("Confirme a nova senha");
        nova_senha.setFocusCycleRoot(true);
        nova_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nova_senhaActionPerformed(evt);
            }
        });
        nova_senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nova_senhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nova_senha, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                        .addComponent(confirma_senha))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nova_senha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirma_senha)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
        );

        desktopPane.add(jPanel1);
        jPanel1.setBounds(10, 10, 190, 80);

        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton3);
        jButton3.setBounds(10, 100, 80, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(218, 171));
        setLocationRelativeTo(null);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        index frm = new index();
        frm.setVisible(true);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            pesquisaPasswd();
        } catch (ParseException ex) {
            Logger.getLogger(changepassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nova_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nova_senhaActionPerformed

    }

    private void nova_senhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nova_senhaKeyPressed
        String password = new String(nova_senha.getPassword());//PasswordField      
        if (password.length() > 5) {
            JOptionPane.showConfirmDialog(this, "Máximo de 6 caracteres permitido!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            nova_senha.setText("");
            return;
        }
    }//GEN-LAST:event_nova_senhaKeyPressed

    private void confirma_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirma_senhaActionPerformed
        String password = new String(confirma_senha.getPassword());//PasswordField      
        if (password.length() > 5) {
            JOptionPane.showConfirmDialog(this, "Máximo de 6 caracteres permitido!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            confirma_senha.setText("");
            return;
        }
    }

    public void setInternalFrame(int pInternalFrame) {
        int internalFrame = pInternalFrame;
    }

    public static void main(String args[]) {
        if (args.length > 0) {
            System.setProperty("tf_MDFe", args[0]);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new auth().setVisible(true);
            }
        });
    }

    private void atualizarHoras() {
        Calendar agora = Calendar.getInstance();
        int horas = agora.get(Calendar.HOUR_OF_DAY);
        int minutos = agora.get(Calendar.MINUTE);
        int segundos = agora.get(Calendar.SECOND);
    }

    public void pesquisaPasswd() throws ParseException {

        try {
            String nova_pass = new String(nova_senha.getPassword());//PasswordField
            String confirma_pass = new String(confirma_senha.getPassword());//PasswordField
            if (!nova_pass.trim().equals(confirma_pass.trim())) {
                JOptionPane.showConfirmDialog(this, "Senhas divergentes!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
                return;
            }
            USERS eUSERS = new USERS();
            eUSERS.setSen_oper(System.getProperty("pas_usuario").trim());
            eUSERS.setSen_senh(confirma_pass.trim());
            if (UsersController.Update(eUSERS)) {
                JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o Emitente!");
            }
            this.dispose();
            index frm = new index();
            frm.setVisible(true);
        } catch (InstantiationException ex) {
            Logger.getLogger(changepassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(changepassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private javax.swing.JPasswordField confirma_senha;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField nova_senha;

}
