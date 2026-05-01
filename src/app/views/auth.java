package app.views;

import java.awt.event.KeyEvent;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import org.json.JSONObject;
import app.controllers.UsersController;
import com.backend.services.ConnectionFactory;
import com.frontend.config.env;
import com.frontend.util.ProcessaTokenUtil;
import com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum;
import com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum;
import javax.persistence.EntityManager;

public class auth extends javax.swing.JFrame {

    env Env = new env();
    private static ServerSocket s;
    public String Opcao = "";
    Timer timer;
    DecimalFormat formato;

    public auth() {
        initComponents();
        System.setProperty("oldCharInterceptor", "");
        System.setProperty("newCharInterceptor", "");
        //jLabel5.setVisible(false);
        //jcbx_host.setVisible(false);
        //alteracao global usando o UIManager
        /*
         UIManager.put("MenuBar.opaque", true);
         UIManager.put("MenuBar.background", Color.RED);
         UIManager.put("Menu.background", Color.GREEN);
         UIManager.put("MenuItem.background", Color.MAGENTA);
         UIManager.put("MenuItem.opaque", true);
         */
        UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black));
        UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN, 11));

        JFrame.setDefaultLookAndFeelDecorated(true);

        System.setProperty("contigenciaSVC", "FALSE");

        System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
        //System.setProperty("webservice_ws", Env.getwebservice_ws_producao());
        System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
        System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
        System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
        System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());

        System.setProperty("gREALIZADO", "NAO");
        System.setProperty("tpAmb", Env.gettpAMB().toString());

        //ImageIcon icone = new ImageIcon("./icons/permissao.gif");
        //setIconImage(icone.getImage());
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icone_nfe.jpg")).getImage());
        this.setVisible(true);

        jt_usuario.setText(System.getProperty("user.name"));
        //jP_senha.setFocusable(true);
        //jP_senha.requestFocusInWindow();
        //jP_senha.requestFocus();
        //jP_senha.grabFocus();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jP_senha.requestFocus();
            }
        });
        jcbx_host.setSelectedIndex(1); //0=localhost (192.168.0.5), 1=localhost (192.168.0.3), 2=cpro46493.publiccloud.com.br, 3=www.homo.camarplasticos.com.br
        jcbx_ambiente.setSelectedIndex(1); //0 = Homologacao - 1=Producao

        //jLabel5.setVisible(false);
        //jcbx_host.setVisible(false);
        try {
            s = new ServerSocket(9581);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(desktopPane, "Desculpe, mas só uma aplicação pode ser executada por vez.");
           // System.exit(0);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jt_usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jP_senha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jcbx_ambiente = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jcbx_host = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jB_newuser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NFe - RT");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setIconImage(getIconImage());
        setIconImages(null);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        desktopPane.setBackground(new java.awt.Color(98, 78, 165));
        desktopPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));
        desktopPane.setAlignmentX(0.0F);
        desktopPane.setAlignmentY(0.0F);
        desktopPane.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        desktopPane.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        desktopPane.setFocusTraversalPolicyProvider(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Altera Senha");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jt_usuario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jt_usuario.setToolTipText("Entre com o Login do Usuário");
        jt_usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jt_usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_usuarioFocusLost(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Usuário:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Senha:");

        jP_senha.setColumns(6);
        jP_senha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jP_senha.setToolTipText("Entre com a Senha do Usuário");
        jP_senha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jP_senha.setFocusCycleRoot(true);
        jP_senha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jP_senhaFocusGained(evt);
            }
        });
        jP_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jP_senhaActionPerformed(evt);
            }
        });
        jP_senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jP_senhaKeyPressed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Ambiente:");

        jcbx_ambiente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2-Homologação", "1-Produção" }));
        jcbx_ambiente.setToolTipText("");
        jcbx_ambiente.setActionCommand("");
        jcbx_ambiente.setEnabled(false);
        jcbx_ambiente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbx_ambienteFocusLost(evt);
            }
        });
        jcbx_ambiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_ambienteActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Host da  Base de Dados:");

        jcbx_host.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "localhost (192.168.0.5)", "localhost (192.168.0.3)", "cpro46493.publiccloud.com.br", "www.homo.camarplasticos.com.br" }));
        jcbx_host.setToolTipText("");
        jcbx_host.setActionCommand("");
        jcbx_host.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbx_hostFocusLost(evt);
            }
        });
        jcbx_host.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_hostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbx_host, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx_ambiente, 0, 141, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jt_usuario)
                            .addComponent(jP_senha, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jP_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbx_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbx_ambiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("OK");
        jButton3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton3MouseMoved(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jB_newuser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jB_newuser.setForeground(new java.awt.Color(0, 0, 153));
        jB_newuser.setText("Criar Novo Usuário");
        jB_newuser.setEnabled(false);
        jB_newuser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jB_newuser.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jB_newuserMouseMoved(evt);
            }
        });
        jB_newuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_newuserActionPerformed(evt);
            }
        });

        desktopPane.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jB_newuser, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jB_newuser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB_newuser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane)
                .addGap(0, 0, 0))
        );

        setSize(new java.awt.Dimension(274, 299));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Opcao = "MUDAR SENHA";
        try {
            pesquisaPasswd();
        } catch (ParseException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Opcao = "LOGIN";
        try {
            pesquisaPasswd();
        } catch (ParseException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jP_senhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jP_senhaKeyPressed
        String password = new String(jP_senha.getPassword());//PasswordField      
        if (password.length() > 6) {
            JOptionPane.showConfirmDialog(this, "Máximo de 6 caracteres permitido!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            jP_senha.setText("");
            return;
        }
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            Opcao = "LOGIN";
            try {
                pesquisaPasswd();
            } catch (ParseException ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jP_senhaKeyPressed

    private void jP_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jP_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jP_senhaActionPerformed

    private void jcbx_ambienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbx_ambienteFocusLost

    }//GEN-LAST:event_jcbx_ambienteFocusLost

    private void jcbx_ambienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_ambienteActionPerformed
        if (jcbx_host.getSelectedIndex() == 3
                && jcbx_ambiente.getSelectedItem().toString().trim().equals("2-Homologação")) {
            System.setProperty("tpAmb", "2");
            System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
            System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
            System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
            System.setProperty("db_port", Env.getHOMOLOGACAO_DB_PORT());
            System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
            System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
        };
        if (jcbx_host.getSelectedIndex() == 0
                && jcbx_ambiente.getSelectedItem().toString().trim().equals("2-Homologação")) {
            System.setProperty("tpAmb", "2");
            System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
            System.setProperty("IP", Env.getPRODUCAO_5_DB_HOST());
            System.setProperty("db_base", Env.getPRODUCAO_5_DB_DATABASE());
            System.setProperty("db_port", Env.getPRODUCAO_5_DB_PORT());
            System.setProperty("db_user", Env.getPRODUCAO_5_DB_USER());
            System.setProperty("db_pass", Env.getPRODUCAO_5_DB_PASSWORD());
        };
        if (jcbx_host.getSelectedIndex() == 1
                && jcbx_ambiente.getSelectedItem().toString().trim().equals("1-Produção")) {
            System.setProperty("tpAmb", "1");
            System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
            //System.setProperty("webservice_ws", Env.getwebservice_ws_producao());
            System.setProperty("IP", Env.getPRODUCAO_DB_HOST());
            System.setProperty("db_base", Env.getPRODUCAO_DB_DATABASE());
            System.setProperty("db_port", Env.getPRODUCAO_DB_PORT());
            System.setProperty("db_user", Env.getPRODUCAO_DB_USER());
            System.setProperty("db_pass", Env.getPRODUCAO_DB_PASSWORD());
        };
    }//GEN-LAST:event_jcbx_ambienteActionPerformed

    private void jt_usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_usuarioFocusLost

    }//GEN-LAST:event_jt_usuarioFocusLost

    private void jP_senhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jP_senhaFocusGained
        jcbx_ambiente.setEnabled(false);
        if (jt_usuario.getText().equals("osvaldo")
                || jt_usuario.getText().equals("root")) {
            jcbx_ambiente.setEnabled(true);
        }
    }//GEN-LAST:event_jP_senhaFocusGained

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // try {
        //     Runtime rt = Runtime.getRuntime();
        //    Process proc = rt.exec("java -jar c:/MDFe-3.00/javadb/lib/derbyrun.jar server shutdown");
        //} catch (IOException ex) {
        //     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        // }

    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        //SkyBlue()
        //BrownSugar()
        // DarkStar()  
        //DesertGreen()
        //Silver()
        //ExperienceRoyale()
/*
         try {
         PlasticLookAndFeel.setPlasticTheme(new ExperienceRoyale());
         UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
         } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
         } catch (InstantiationException ex) {
         Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedLookAndFeelException ex) {
         Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         }

         SwingUtilities.updateComponentTreeUI(this);
         this.setBackground(SystemColor.DARK_GRAY);
         */
    }//GEN-LAST:event_formWindowOpened

    private void jB_newuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_newuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jB_newuserActionPerformed

    private void jButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jButton1.setCursor(cursor);
    }//GEN-LAST:event_jButton1MouseMoved

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jButton3.setCursor(cursor);
    }//GEN-LAST:event_jButton3MouseMoved

    private void jB_newuserMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_newuserMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jB_newuser.setCursor(cursor);
    }//GEN-LAST:event_jB_newuserMouseMoved

    private void jcbx_hostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbx_hostFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_hostFocusLost

    private void jcbx_hostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_hostActionPerformed
        if (jcbx_host.getSelectedItem().toString().trim().equals("localhost (192.168.0.3)")) {
            System.setProperty("IP", Env.getPRODUCAO_DB_HOST());
            System.setProperty("db_port", Env.getPRODUCAO_DB_PORT());
            jcbx_ambiente.setSelectedIndex(0); //Producao
        }
        if (jcbx_host.getSelectedItem().toString().trim().equals("localhost (192.168.0.5)")) {
            System.setProperty("IP", Env.getPRODUCAO_5_DB_HOST());
            System.setProperty("db_port", Env.getPRODUCAO_5_DB_PORT());
            jcbx_ambiente.setSelectedIndex(0); //Producao
            System.setProperty("tpAmb", "2"); //Homologacao
        }
        if (jcbx_host.getSelectedItem().toString().trim().equals("cpro46493.publiccloud.com.br")) {
            System.setProperty("IP", Env.getPRODUCAO_CLOUD_DB_HOST());
            System.setProperty("db_port", Env.getPRODUCAO_CLOUD_DB_PORT());
            jcbx_ambiente.setSelectedIndex(0); //Producao
            System.setProperty("tpAmb", "2"); //Homologacao
        }
        if (jcbx_host.getSelectedItem().toString().trim().equals("www.homo.camarplasticos.com.br")) {
            System.setProperty("tpAmb", "2"); //Homologacao
            System.setProperty("webservice_ws", Env.getWS_HOMOLOGACAO());
            System.setProperty("IP", Env.getHOMOLOGACAO_DB_HOST());
            System.setProperty("db_base", Env.getHOMOLOGACAO_DB_DATABASE());
            System.setProperty("db_port", Env.getHOMOLOGACAO_DB_PORT());
            System.setProperty("db_user", Env.getHOMOLOGACAO_DB_USER());
            System.setProperty("db_pass", Env.getHOMOLOGACAO_DB_PASSWORD());
            jcbx_ambiente.setSelectedIndex(1); //Homologacao
        }
    }//GEN-LAST:event_jcbx_hostActionPerformed
    public void setInternalFrame(int pInternalFrame) {
        int internalFrame = pInternalFrame;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                new auth().setVisible(true);

            }
        });
    }

    @SuppressWarnings("null")
    public void pesquisaPasswd() throws ParseException, ClassNotFoundException, Exception {
        try {
            jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            int wincodi = 0;
            JSONObject jsonUSERS = new JSONObject();
            jsonUSERS = UsersController.Index(jt_usuario.getText().trim().toLowerCase());
            String passwdcadastrada = "";
            String password = "";
            boolean wflag = false;
            if (!jsonUSERS.isEmpty()) {
                wflag = true;
                passwdcadastrada = jsonUSERS.getString("sen_senh").trim();
            }
            password = new String(jP_senha.getPassword());
            if (!wflag) {
                JOptionPane.showConfirmDialog(this, "Usuário não cadastrado ou \n host não encontrado, Verifique!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            } else if (passwdcadastrada.equals(password.trim())) {
                System.setProperty("pas_usuario", jt_usuario.getText().trim().toLowerCase());
                System.setProperty("pas_nomecompleto", jsonUSERS.getString("sen_nome").trim());
                System.setProperty("pas_nivel", jsonUSERS.getString("sen_nive").trim());
                if (Opcao.toString().trim().equals("MUDAR SENHA")) {
                    changepassword frm1 = new changepassword();
                    frm1.setVisible(true);
                }
                if (Opcao.toString().trim().equals("LOGIN")) {
                    EntityManager em = null;
                    try {
                        em = new ConnectionFactory().getConnection();
                        em.close();
                    } catch (ExceptionInInitializerError ex) {
                        Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Could not initialize class services.ConnectionFactory", "Erro", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    } finally {
                        jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        // Mostra uma imagem com o título da aplicação
                        System.setProperty("tpAmb", "1");
                        System.setProperty("pathCertificadoBradescoPfx", ServicosBradescoEnum.getCertificadoPath());
                        System.setProperty("passwordBradescoPfx", ServicosBradescoEnum.getCertificadoSenha());
                        System.setProperty("pathCertificadoSantanderPfx", ServicosSantanderEnum.getCertificadoPath());
                        System.setProperty("passwordSantanderPfx", ServicosSantanderEnum.getCertificadoSenha());
                        ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil();
                        ProcessaToken.processaTokenBradesco();
                        ProcessaToken.processaTokenSantander();
                        this.dispose();
                        index frm = new index();
                        frm.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showConfirmDialog(this, "Senha Inválida!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
                jP_senha.setText("");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        jP_senha.requestFocus();
                    }
                });
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jB_newuser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jP_senha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jcbx_ambiente;
    private javax.swing.JComboBox jcbx_host;
    private javax.swing.JTextField jt_usuario;
    // End of variables declaration//GEN-END:variables

    public class Process extends Thread {

        public void run() {
            EntityManager em = null;
            try {
                em = new ConnectionFactory().getConnection();
            } catch (ExceptionInInitializerError ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
        }
    }
}
