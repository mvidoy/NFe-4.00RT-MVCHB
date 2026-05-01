package com.frontend.views.cadastros;

import java.awt.Cursor;
import java.sql.*;
import javax.swing.*;
import javax.swing.JDesktopPane;
import com.sun.istack.NotNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.backend.controllers.EMPRESAcontroller;
import com.backend.dtos.EMPRESAdto;
import com.backend.models.Caempres;
import com.frontend.util.ValidFieldNumeric;

public class Cadastro_EMPRESA extends javax.swing.JInternalFrame {

    //EmitenteController EmitenteController = new EmitenteController();
    EMPRESAdto eMPRESAdto = new EMPRESAdto();
    public boolean AutoIncrement = false;

    public void state() {

        ID_EMPRESA.setText("");
        X_NOME.setText("");
        X_NOME.setText("");
        X_FANT.setText("");
        CNPJ.setText("");
        IE.setText("");
        X_CONTADOR.setText("");
        X_EMAILCONTADOR.setText("");
        X_LGR.setText("");
        NRO.setText("");
        X_CPL.setText("");
        X_BAIRRO.setText("");
        CEP.setText("");
        //jcbx_X_PAIS.setSelectedIndex(1);
        //jcbx_UF.setSelectedIndex(1);
        //jcbx_X_MUN.setSelectedIndex(1);
        FONE.setText("");
        X_SENHA.setText("");
        //LOCAL_CACERTS.setText("");
    }

    public void setState() {
        eMPRESAdto.setEMP_CODI(ID_EMPRESA.getText().trim());
        eMPRESAdto.setEMP_RZSO(X_NOME.getText().trim());
        eMPRESAdto.setEMP_CGC0(CNPJ.getText().trim());
        eMPRESAdto.setEMP_INSE(IE.getText().trim());
        //eMPRESAdto.setX_CONTADOR.setText(jsonEMITENTE.getString("X_CONTADOR"));
        //X_EMAILCONTADOR.setText(jsonEMITENTE.getString("X_EMAILCONTADOR"));
        eMPRESAdto.setEMP_ENDE(X_LGR.getText().trim());
        eMPRESAdto.setEMP_NUMERO(NRO.getText().trim());
        eMPRESAdto.setEMP_COMPLEMENTO(X_CPL.getText().trim());
        eMPRESAdto.setEMP_BAIR(X_BAIRRO.getText().trim());
        eMPRESAdto.setEMP_CEP0(CEP.getText().trim());
        eMPRESAdto.setEMP_PAIS(jcbx_X_PAIS.getSelectedItem().toString().trim());
        eMPRESAdto.setEMP_ESTA(jcbx_UF.getSelectedItem().toString().trim());
        eMPRESAdto.setEMP_CIDA(jcbx_X_MUN.getSelectedItem().toString().trim());
        eMPRESAdto.setEMP_FONE(FONE.getText().trim());
        //eMPRESAdto.setEMP_LOCALCERTIFICADO(LOCAL_CERTIFICADO.getText().trim());
        //eMPRESAdto.setEMP_LOCALCERTIFICADOCACERTSMDFE(LOCAL_CACERTS.getText().trim());
        //eMPRESAdto.setEMP_SENHACERTIFICADO(new String(SENHA_CERTIFICADO.getText()));
    }

    /*
    public void LoadFM(JSONObject jsonEMITENTE) {
        ID_EMPRESA.setText(jsonEMITENTE.getString("EMP_CODI"));
        X_NOME.setText(jsonEMITENTE.getString("EMP_RZSO"));
        X_FANT.setText(jsonEMITENTE.getString("EMP_RZSO"));
        CNPJ.setText(jsonEMITENTE.getString("EMP_CGC0"));
        IE.setText(jsonEMITENTE.getString("EMP_INSE"));
        //X_CONTADOR.setText(jsonEMITENTE.getString("X_CONTADOR"));
        //X_EMAILCONTADOR.setText(jsonEMITENTE.getString("X_EMAILCONTADOR"));
        X_LGR.setText(jsonEMITENTE.getString("EMP_ENDE"));
        NRO.setText(jsonEMITENTE.getString("EMP_NUMERO"));
        X_CPL.setText(jsonEMITENTE.getString("EMP_COMPLEMENTO"));
        X_BAIRRO.setText(jsonEMITENTE.getString("EMP_BAIR"));
        CEP.setText(jsonEMITENTE.getString("EMP_CEP0"));
        //jcbx_X_PAIS.setSelectedIndex(1);
        //jcbx_UF.setSelectedIndex(1);
        //jcbx_X_MUN.setSelectedIndex(1);
        FONE.setText(jsonEMITENTE.getString("EMP_FONE"));
        //LOCAL_CERTIFICADO.setText(jsonEMITENTE.getString("EMP_LOCALCERTIFICADO"));
        //SENHA_CERTIFICADO.setText(jsonEMITENTE.getString("EMP_SENHACERTIFICADO"));
        //LOCAL_CACERTS.setText(jsonEMITENTE.getString("EMP_LOCALCERTIFICADOCACERTSMDFE"));
        
    }
    */
    public void Botoes(boolean N, boolean E, boolean S, boolean R, boolean C, boolean P) {
        btn_novo.setEnabled(N);
        btn_editar.setEnabled(E);
        btn_salvar.setEnabled(S);
        btn_excluir.setEnabled(R);
        btn_clear.setEnabled(P);
    }

    public void LoadFirstReg() {
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        state();
        try {
            EMPRESAdto formEMPRESAdto = new EMPRESAdto();
            this.ID_EMPRESA.setText(System.getProperty("EMITENTE"));
            if (this.ID_EMPRESA.getText().trim().length() > 0) {
                formEMPRESAdto = EMPRESAcontroller.FindCodigo(this.ID_EMPRESA.getText().trim());
            }
            if (formEMPRESAdto == null) {
                JOptionPane.showMessageDialog(this, "Registro não encontrado!", "Mensagem do Sistema",
                        JOptionPane.ERROR_MESSAGE);
                this.ID_EMPRESA.setText("");
                cursor = Cursor.getDefaultCursor();
                this.setCursor(cursor);
                return;
            }
            ID_EMPRESA.setText(formEMPRESAdto.getEMP_CODI().trim());
            X_NOME.setText(formEMPRESAdto.getEMP_RZSO().trim());
            X_FANT.setText(formEMPRESAdto.getEMP_RZSO().trim());
            CNPJ.setText(formEMPRESAdto.getEMP_CGC0().trim());
            IE.setText(formEMPRESAdto.getEMP_INSE().trim());
            X_LGR.setText(formEMPRESAdto.getEMP_ENDE().trim());
            NRO.setText(formEMPRESAdto.getEMP_NUMERO().trim());
            X_CPL.setText(formEMPRESAdto.getEMP_COMPLEMENTO().trim());
            X_BAIRRO.setText(formEMPRESAdto.getEMP_BAIR().trim());
            CEP.setText(formEMPRESAdto.getEMP_CEP0().trim());
            FONE.setText(formEMPRESAdto.getEMP_FONE().trim());
        } catch (SQLException ex) {
            returnParams(new String[]{ex.toString()});
        }
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
    }

    public JDesktopPane jDesktop;

    public Cadastro_EMPRESA(JDesktopPane jDesktop) {

        this.jDesktop = jDesktop;
        initComponents();
        LoadFirstReg();
        Botoes(false, true, false, false, false, false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        ID_EMPRESA = new javax.swing.JTextField();
        botao_pesquisa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        X_NOME = new javax.swing.JTextField();
        X_FANT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CNPJ = new javax.swing.JTextField();
        X_EMAILCONTADOR = new javax.swing.JTextField();
        X_CONTADOR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        IE = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        CEP = new javax.swing.JTextField();
        X_LGR = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        FONE = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        NRO = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        X_BAIRRO = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        X_CPL = new javax.swing.JTextField();
        jcbx_X_MUN = new javax.swing.JComboBox();
        jcbx_UF = new javax.swing.JComboBox();
        jcbx_X_PAIS = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        X_SENHA = new javax.swing.JPasswordField();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_primeiro = new javax.swing.JToggleButton();
        btn_anterior = new javax.swing.JToggleButton();
        statusbar = new javax.swing.JLabel();
        btn_proximo = new javax.swing.JButton();
        btn_ultimo = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btn_editar = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("CADASTRO DA EMPRESA/EMITENTE (NFe) - Revisão: 01 - Data da Última Revisão: 10/08/2022 - Data da Elaboração: 10/08/2022");
        setFrameIcon(null);
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(227, 231, 233));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("* Código:");
        jLabel1.setAutoscrolls(true);

        ID_EMPRESA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ID_EMPRESA.setToolTipText("Entre com o código");
        ID_EMPRESA.setEnabled(false);

        botao_pesquisa.setText("OK");
        botao_pesquisa.setToolTipText("Pesquisa código");
        botao_pesquisa.setEnabled(false);
        botao_pesquisa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_pesquisaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Emitente"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome Fantasia");

        X_NOME.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_NOME.setToolTipText("");
        X_NOME.setEnabled(false);

        X_FANT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_FANT.setToolTipText("");
        X_FANT.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("* CNPJ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("* E-mail Contador");

        CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CNPJ.setToolTipText("");
        CNPJ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CNPJ.setEnabled(false);

        X_EMAILCONTADOR.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_EMAILCONTADOR.setToolTipText("");
        X_EMAILCONTADOR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        X_EMAILCONTADOR.setEnabled(false);

        X_CONTADOR.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_CONTADOR.setToolTipText("");
        X_CONTADOR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        X_CONTADOR.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("* Inscrição Estadual");

        IE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        IE.setToolTipText("");
        IE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        IE.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("* Nome/Razão Social");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("* Nome do Contador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(X_NOME))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(X_FANT))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(CNPJ)
                .addGap(10, 10, 10)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(IE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(X_CONTADOR)
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(X_EMAILCONTADOR))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_NOME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(X_FANT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_CONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_EMAILCONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Telefone");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("* Logradouro");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("CEP");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("País");

        CEP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        CEP.setToolTipText("");
        CEP.setEnabled(false);

        X_LGR.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_LGR.setToolTipText("");
        X_LGR.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Complemento");

        FONE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        FONE.setToolTipText("");
        FONE.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("* UF");

        NRO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NRO.setToolTipText("");
        NRO.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("* Número");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("* Município");

        X_BAIRRO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_BAIRRO.setToolTipText("");
        X_BAIRRO.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("* E-mail");

        X_CPL.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        X_CPL.setToolTipText("");
        X_CPL.setEnabled(false);

        jcbx_X_MUN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Santa Barbara d'Oeste" }));
        jcbx_X_MUN.setEnabled(false);

        jcbx_UF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SP" }));
        jcbx_UF.setEnabled(false);
        jcbx_UF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbx_UFActionPerformed(evt);
            }
        });

        jcbx_X_PAIS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Brasil" }));
        jcbx_X_PAIS.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("* Bairro");

        X_SENHA.setEditable(false);
        X_SENHA.setText("jPasswordField1");
        X_SENHA.setEnabled(false);
        X_SENHA.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jcbx_X_PAIS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jcbx_X_MUN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(FONE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(X_SENHA, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(X_LGR)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(X_CPL, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(X_BAIRRO, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CEP, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(NRO, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_LGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_CPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_BAIRRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_X_PAIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_X_MUN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FONE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(X_SENHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(ID_EMPRESA, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(botao_pesquisa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ID_EMPRESA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botao_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID_EMPRESA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ID_EMPRESA.setDocument(new ValidFieldNumeric());

        btn_novo.setText("Novo");
        btn_novo.setToolTipText("");
        btn_novo.setAutoscrolls(true);
        btn_novo.setEnabled(false);
        btn_novo.setInheritsPopupMenu(true);
        btn_novo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_novoMouseMoved(evt);
            }
        });
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setText("Salvar");
        btn_salvar.setToolTipText("");
        btn_salvar.setEnabled(false);
        btn_salvar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_salvarMouseMoved(evt);
            }
        });
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.setToolTipText("");
        btn_excluir.setEnabled(false);
        btn_excluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_excluir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_excluirMouseMoved(evt);
            }
        });
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_clear.setMnemonic('L');
        btn_clear.setText("Limpa");
        btn_clear.setToolTipText("");
        btn_clear.setEnabled(false);
        btn_clear.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_clearMouseMoved(evt);
            }
        });
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_primeiro.setSelected(true);
        btn_primeiro.setToolTipText("Move para o primeiro registro");
        btn_primeiro.setIcon(new javax.swing.ImageIcon("./icons/bt_primeiro.GIF"));
        btn_primeiro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_primeiroMouseMoved(evt);
            }
        });

        btn_anterior.setToolTipText("Move para o registro anterior");
        btn_anterior.setIcon(new javax.swing.ImageIcon("./icons/bt_anterior.GIF"));
        btn_anterior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_anteriorMouseMoved(evt);
            }
        });

        statusbar.setText("  Registro: 1/1");
        statusbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_proximo.setToolTipText("Move para o próximo registro");
        btn_proximo.setIcon(new javax.swing.ImageIcon("./icons/bt_proximo.GIF"));
        btn_proximo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_proximoMouseMoved(evt);
            }
        });

        btn_ultimo.setToolTipText("Move para o último registro");
        btn_ultimo.setIcon(new javax.swing.ImageIcon("./icons/bt_ultimo.GIF"));
        btn_ultimo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_ultimoMouseMoved(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.setToolTipText("");
        btn_editar.setEnabled(false);
        btn_editar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_editarMouseMoved(evt);
            }
        });
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        botao_sair.setMnemonic('L');
        botao_sair.setText("Sair");
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
                .addGap(3, 3, 3)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSeparator4)
                .addGap(3, 3, 3))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jDesktopPane1)
                .addGap(13, 13, 13))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSeparator3)
                .addGap(3, 3, 3))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(statusbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botao_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        getAccessibleContext().setAccessibleName("CADASTRO DO EMITENTE - Data da Última Revisão: 28/05/2016");

        setBounds(0, 0, 829, 456);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        LoadFirstReg();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        if (EMPRESAcontroller.Delete(ID_EMPRESA.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Empresa/Emitente excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o Empresa/Emitente!");
        }
        disableFields();
        LoadFirstReg();
        Botoes(false, true, false, false, false, false);
    }//GEN-LAST:event_btn_excluirActionPerformed


    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        this.setCursor(cursor);
        setState();
        if (AutoIncrement) {
            if (EMPRESAcontroller.Create(eMPRESAdto)) {
                JOptionPane.showMessageDialog(this, "Empresa/Emitente criado com sucesso!");
                AutoIncrement = false;
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao criar uma nova Empresa/Emitente!");
            }
        } else {
            if (EMPRESAcontroller.Update(eMPRESAdto)) {
                JOptionPane.showMessageDialog(this, "Empresa/Emitente salvo com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o Empresa/Emitente!");
            }
        }
        disableFields();
        Botoes(false, true, false, false, false, false);
        cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        this.setCursor(cursor);
    }//GEN-LAST:event_btn_salvarActionPerformed


    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        enableFields();
        Botoes(false, false, true, false, true, false);
    }//GEN-LAST:event_btn_editarActionPerformed

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        dispose();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void jcbx_UFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbx_UFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbx_UFActionPerformed

    private void botao_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_pesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_pesquisaActionPerformed

    private void btn_novoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_novoMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_novo.setCursor(cursor);
    }//GEN-LAST:event_btn_novoMouseMoved

    private void btn_editarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editarMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_editar.setCursor(cursor);
    }//GEN-LAST:event_btn_editarMouseMoved

    private void btn_salvarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salvarMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_salvar.setCursor(cursor);
    }//GEN-LAST:event_btn_salvarMouseMoved

    private void btn_excluirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_excluir.setCursor(cursor);
    }//GEN-LAST:event_btn_excluirMouseMoved

    private void btn_clearMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_clear.setCursor(cursor);
    }//GEN-LAST:event_btn_clearMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        botao_sair.setCursor(cursor);
    }//GEN-LAST:event_botao_sairMouseMoved


    private void btn_primeiroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_primeiroMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_primeiro.setCursor(cursor);
    }//GEN-LAST:event_btn_primeiroMouseMoved

    private void btn_anteriorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_anteriorMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_anterior.setCursor(cursor);
    }//GEN-LAST:event_btn_anteriorMouseMoved

    private void btn_proximoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_proximoMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_proximo.setCursor(cursor);
    }//GEN-LAST:event_btn_proximoMouseMoved

    private void btn_ultimoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ultimoMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_ultimo.setCursor(cursor);
    }//GEN-LAST:event_btn_ultimoMouseMoved

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_novoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CEP;
    private javax.swing.JTextField CNPJ;
    private javax.swing.JTextField FONE;
    private javax.swing.JTextField ID_EMPRESA;
    private javax.swing.JTextField IE;
    private javax.swing.JTextField NRO;
    private javax.swing.JTextField X_BAIRRO;
    private javax.swing.JTextField X_CONTADOR;
    private javax.swing.JTextField X_CPL;
    private javax.swing.JTextField X_EMAILCONTADOR;
    private javax.swing.JTextField X_FANT;
    private javax.swing.JTextField X_LGR;
    private javax.swing.JTextField X_NOME;
    private javax.swing.JPasswordField X_SENHA;
    private javax.swing.JButton botao_pesquisa;
    private javax.swing.JButton botao_sair;
    private javax.swing.JToggleButton btn_anterior;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    public javax.swing.JButton btn_novo;
    private javax.swing.JToggleButton btn_primeiro;
    private javax.swing.JButton btn_proximo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JToggleButton btn_ultimo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox jcbx_UF;
    private javax.swing.JComboBox jcbx_X_MUN;
    private javax.swing.JComboBox jcbx_X_PAIS;
    private javax.swing.JLabel statusbar;
    // End of variables declaration//GEN-END:variables

    public void Status_Navegacao(int wRegPos, int wTotReg) {
        statusbar.setText(" Registro: 1/1 " + String.valueOf(wRegPos + 1) + " / " + String.valueOf(wTotReg));
    }

    public String Percorrejcbx(JComboBox jcbx, String wVar) {
        try {
            jcbx.setSelectedIndex(0);
            if (wVar == null) {
                return wVar;
            }
            String wwVar = wVar.toString();
            for (int i = 0; i < jcbx.getItemCount(); i++) {
                if (jcbx.getItemAt(i).toString().equals(wwVar)) {
                    jcbx.setSelectedIndex(i);
                    break;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "falha não esperada!!! " + erro);
        }
        return wVar;
    }

    public void enableFields() {
        ID_EMPRESA.setEnabled(false);
        botao_pesquisa.setEnabled(false);
        btn_novo.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_clear.setEnabled(false);
        X_NOME.setEnabled(true);
        X_FANT.setEnabled(false);
        CNPJ.setEnabled(true);
        IE.setEnabled(true);
        X_LGR.setEnabled(true);
        NRO.setEnabled(true);
        X_CPL.setEnabled(true);
        X_BAIRRO.setEnabled(true);
        CEP.setEnabled(true);
        jcbx_X_PAIS.setEnabled(true);
        jcbx_UF.setEnabled(true);
        jcbx_X_MUN.setEnabled(true);
        FONE.setEnabled(true);
        X_EMAILCONTADOR.setEnabled(false);
        X_CONTADOR.setEnabled(false);
        //LOCAL_CERTIFICADO.setEnabled(true);
        //LOCAL_CACERTS.setEnabled(true);
        //SENHA_CERTIFICADO.setEnabled(true);
    }

    public void disableFields() {
        ID_EMPRESA.setEnabled(false);
        //botao_alterar.setEnabled(false);
        botao_pesquisa.setEnabled(false);
        btn_novo.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_clear.setEnabled(false);
        X_NOME.setEnabled(false);
        X_FANT.setEnabled(false);
        CNPJ.setEnabled(false);
        IE.setEnabled(false);
        X_LGR.setEnabled(false);
        NRO.setEnabled(false);
        X_CPL.setEnabled(false);
        X_BAIRRO.setEnabled(false);
        CEP.setEnabled(false);
        jcbx_X_PAIS.setEnabled(false);
        jcbx_UF.setEnabled(false);
        jcbx_X_MUN.setEnabled(false);
        FONE.setEnabled(false);
        X_EMAILCONTADOR.setEnabled(false);
        X_CONTADOR.setEnabled(false);
    }

    public static Caempres findCodigo(@NotNull
            final Long empcodi) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MVCHBPU");
        EntityManager entityManager = factory.createEntityManager();

        /*
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Caempres> criteria = builder.createQuery(Caempres.class);
        Root<Caempres> from = criteria.from(Caempres.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Caempres_.empCodi), scn));
        TypedQuery<Caempres> typed = entityManager.createQuery(criteria);
         */
        TypedQuery<Caempres> typed = entityManager
                .createNamedQuery("Caempres.findByEmpCodi", Caempres.class
                )
                .setParameter("EMP_CODI", empcodi);

        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public void returnParams(String[] paramrs) {
        if (paramrs[0] != null && paramrs[0].trim().length() > 0) {
            String error = "<html><font color=\"black\">" + statusbar.getText() + "</font><font color=\"red\">" + " Erro: " + paramrs[0] + "</font></html>";
            statusbar.setText(error);
        } else {
            statusbar.setText("");
        }
    }
}
