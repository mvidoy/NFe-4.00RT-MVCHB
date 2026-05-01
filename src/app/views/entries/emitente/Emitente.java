package app.views.entries.emitente;

import java.awt.Cursor;
import java.sql.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import org.json.JSONObject;
import com.sun.istack.NotNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import app.controllers.EmitenteController;
import app.models.Caempres;
import app.models.EMITENTE;
import com.frontend.config.env;
import util.ObjetoUtil;

public class Emitente extends javax.swing.JInternalFrame {

    //EmitenteController EmitenteController = new EmitenteController();
    EMITENTE eMITENTE = new EMITENTE();
    env Env = new env();
    public boolean AutoIncrement = false;

    public void state() {

        ID_EMITENTE.setText("");
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
        LOCAL_CERTIFICADO.setText("");
        SENHA_CERTIFICADO.setText("");
        //LOCAL_CACERTS.setText("");
    }

    public void setState() {
        eMITENTE.setEMP_CODI(ID_EMITENTE.getText().trim());
        eMITENTE.setEMP_RZSO(X_NOME.getText().trim());
        eMITENTE.setEMP_CGC0(CNPJ.getText().trim());
        eMITENTE.setEMP_INSE(IE.getText().trim());
        //eMITENTE.setX_CONTADOR.setText(jsonEMITENTE.getString("X_CONTADOR"));
        //X_EMAILCONTADOR.setText(jsonEMITENTE.getString("X_EMAILCONTADOR"));
        eMITENTE.setEMP_ENDE(X_LGR.getText().trim());
        eMITENTE.setEMP_NUMERO(NRO.getText().trim());
        eMITENTE.setEMP_COMPLEMENTO(X_CPL.getText().trim());
        eMITENTE.setEMP_BAIR(X_BAIRRO.getText().trim());
        eMITENTE.setEMP_CEP0(CEP.getText().trim());
        eMITENTE.setEMP_PAIS(jcbx_X_PAIS.getSelectedItem().toString().trim());
        eMITENTE.setEMP_ESTA(jcbx_UF.getSelectedItem().toString().trim());
        eMITENTE.setEMP_CIDA(jcbx_X_MUN.getSelectedItem().toString().trim());
        eMITENTE.setEMP_FONE(FONE.getText().trim());
        //eMITENTE.setEMP_LOCALCERTIFICADO(LOCAL_CERTIFICADO.getText().trim());
        //eMITENTE.setEMP_LOCALCERTIFICADOCACERTSMDFE(LOCAL_CACERTS.getText().trim());
        //eMITENTE.setEMP_SENHACERTIFICADO(new String(SENHA_CERTIFICADO.getText()));
    }

    public void LoadFM(JSONObject jsonEMITENTE) {
        ID_EMITENTE.setText(jsonEMITENTE.getString("EMP_CODI"));
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
        jPanel3.setVisible(false);
        //btn_certficado.setVisible(false);
        LOCAL_CERTIFICADO.setText(Env.getpath_certificado() + Env.getname_certificatoemitente());
        SENHA_CERTIFICADO.setText("********");
    }

    public void Botoes(boolean N, boolean E, boolean S, boolean R, boolean C, boolean P) {
        btn_novo.setEnabled(N);
        btn_editar.setEnabled(E);
        btn_salvar.setEnabled(S);
        btn_excluir.setEnabled(R);
        btn_cancelar.setEnabled(C);
        btn_pesquisar.setEnabled(P);
    }

    public void LoadFirstReg() {
        try {
            state();
            this.ID_EMITENTE.setText(System.getProperty("EMITENTE"));
            JSONObject jsonEMITENTE = new JSONObject();
            jsonEMITENTE = EmitenteController.Index(this.ID_EMITENTE.getText());
            LoadFM(jsonEMITENTE);
        } catch (SQLException ex) {
            Logger.getLogger(Emitente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JDesktopPane jDesktop;

    public Emitente(JDesktopPane jDesktop) {

        this.jDesktop = jDesktop;
        initComponents();
        LoadFirstReg();
        Botoes(true, true, false, false, false, false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        ID_EMITENTE = new javax.swing.JTextField();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        LOCAL_CERTIFICADO = new javax.swing.JTextField();
        SENHA_CERTIFICADO = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_pesquisar = new javax.swing.JButton();
        botoa_primeiro = new javax.swing.JToggleButton();
        botao_anterior = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        botao_proximo = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btn_editar = new javax.swing.JButton();
        botao_sair = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_certficado = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setTitle("CADASTRO DE EMITENTE - Revisão: 01 - Data da Última Revisão: 05/04/2021 - Data da Elaboração: 28/05/2016");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.JPG"))); // NOI18N
        setNormalBounds(new java.awt.Rectangle(30, 20, 128, 0));
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("* Código:");
        jLabel1.setAutoscrolls(true);

        ID_EMITENTE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ID_EMITENTE.setToolTipText("Entre com o código");
        ID_EMITENTE.setEnabled(false);

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
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(X_FANT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_CONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_EMAILCONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(X_CPL, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
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
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_CPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(X_BAIRRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_X_PAIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_X_MUN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FONE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(X_SENHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Certificado (* O local, nome e senha do certificado estão parametrizados no emissor)."));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("* Local/Nome:");

        LOCAL_CERTIFICADO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        LOCAL_CERTIFICADO.setToolTipText("");
        LOCAL_CERTIFICADO.setEnabled(false);

        SENHA_CERTIFICADO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        SENHA_CERTIFICADO.setToolTipText("");
        SENHA_CERTIFICADO.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("* Senha:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(LOCAL_CERTIFICADO, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGap(10, 10, 10)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(SENHA_CERTIFICADO, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LOCAL_CERTIFICADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SENHA_CERTIFICADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(ID_EMITENTE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(botao_pesquisa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                        .addComponent(ID_EMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(botao_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID_EMITENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

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

        btn_pesquisar.setMnemonic('L');
        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.setToolTipText("");
        btn_pesquisar.setEnabled(false);
        btn_pesquisar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_pesquisarMouseMoved(evt);
            }
        });
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        botoa_primeiro.setSelected(true);
        botoa_primeiro.setToolTipText("Move para o primeiro registro");
        botoa_primeiro.setIcon(new javax.swing.ImageIcon("./icons/bt_primeiro.GIF"));

        botao_anterior.setToolTipText("Move para o registro anterior");
        botao_anterior.setIcon(new javax.swing.ImageIcon("./icons/bt_anterior.GIF"));

        jLabel5.setText(" Registro:");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botao_proximo.setToolTipText("Move para o próximo registro");
        botao_proximo.setIcon(new javax.swing.ImageIcon("./icons/bt_proximo.GIF"));

        jToggleButton1.setToolTipText("Move para o último registro");
        jToggleButton1.setIcon(new javax.swing.ImageIcon("./icons/bt_ultimo.GIF"));

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

        btn_cancelar.setText("Cancelar");
        btn_cancelar.setToolTipText("");
        btn_cancelar.setEnabled(false);
        btn_cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cancelarMousePressed(evt);
            }
        });
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_certficado.setMnemonic('L');
        btn_certficado.setText("Upload Certificado (BD)");
        btn_certficado.setToolTipText("");
        btn_certficado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_certficadoMouseMoved(evt);
            }
        });
        btn_certficado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_certficadoActionPerformed(evt);
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
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_certficado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_sair)
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
                .addComponent(botoa_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botao_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(botao_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_certficado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botao_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jDesktopPane1)
                .addGap(12, 12, 12)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botoa_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_proximo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        getAccessibleContext().setAccessibleName("CADASTRO DO EMITENTE - Data da Última Revisão: 28/05/2016");

        setBounds(0, 0, 829, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        LoadFirstReg();
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        if (EmitenteController.Delete(ID_EMITENTE.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Emitente excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o Emitente!");
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
            if (EmitenteController.Create(ID_EMITENTE.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Emitente criado com sucesso!");
                AutoIncrement = false;
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao criar um novo Emitente!");
            }
        } else {
            if (EmitenteController.Update(eMITENTE)) {
                JOptionPane.showMessageDialog(this, "Emitente salvo com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o Emitente!");
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

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        disableFields();
        Botoes(false, true, false, false, false, false);
        AutoIncrement = false;
    }//GEN-LAST:event_btn_cancelarActionPerformed

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

    private void btn_cancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMousePressed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_cancelar.setCursor(cursor);
    }//GEN-LAST:event_btn_cancelarMousePressed

    private void btn_pesquisarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pesquisarMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_pesquisar.setCursor(cursor);
    }//GEN-LAST:event_btn_pesquisarMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        botao_sair.setCursor(cursor);
    }//GEN-LAST:event_botao_sairMouseMoved

    private void btn_certficadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_certficadoActionPerformed
        Cursor cursor = null;
        try {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Selecione a Pasta");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.setCurrentDirectory(new java.io.File((Env.getpath_certificado())));
            int opt = fc.showOpenDialog(jDesktopPane1);
            String wPathName = fc.getSelectedFile().getPath();
            ObjetoUtil objetoUtil = new ObjetoUtil();
            setState();

            if (wPathName.trim().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Nenhum certificado selecionado!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            eMITENTE.setEMP_CERTIFICADO(objetoUtil.FileToByte(wPathName));

            JLabel label1 = new JLabel("Digite a senha do certificado:");
            JPasswordField jpf = new JPasswordField();

            JOptionPane.showConfirmDialog(this,
                    new Object[]{label1, jpf}, "Senha:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            String wPassword = new String(jpf.getPassword());

            if (wPassword.trim().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Senha não pode ser nula!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            eMITENTE.setEMP_SENHACERTIFICADO(wPassword.trim());
            cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            EmitenteController.Update(eMITENTE);

            JOptionPane.showMessageDialog(this, "Certificado salvo com sucesso!");

        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Nenhum certificado selecionado!!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Emitente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao salvar o Certificado!");
        } finally {
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        }

    }//GEN-LAST:event_btn_certficadoActionPerformed


    private void btn_certficadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_certficadoMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btn_certficado.setCursor(cursor);
    }//GEN-LAST:event_btn_certficadoMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CEP;
    private javax.swing.JTextField CNPJ;
    private javax.swing.JTextField FONE;
    private javax.swing.JTextField ID_EMITENTE;
    private javax.swing.JTextField IE;
    private javax.swing.JTextField LOCAL_CERTIFICADO;
    private javax.swing.JTextField NRO;
    private javax.swing.JTextField SENHA_CERTIFICADO;
    private javax.swing.JTextField X_BAIRRO;
    private javax.swing.JTextField X_CONTADOR;
    private javax.swing.JTextField X_CPL;
    private javax.swing.JTextField X_EMAILCONTADOR;
    private javax.swing.JTextField X_FANT;
    private javax.swing.JTextField X_LGR;
    private javax.swing.JTextField X_NOME;
    private javax.swing.JPasswordField X_SENHA;
    private javax.swing.JToggleButton botao_anterior;
    private javax.swing.JButton botao_pesquisa;
    private javax.swing.JButton botao_proximo;
    private javax.swing.JButton botao_sair;
    private javax.swing.JToggleButton botoa_primeiro;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_certficado;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    public javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JButton btn_salvar;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox jcbx_UF;
    private javax.swing.JComboBox jcbx_X_MUN;
    private javax.swing.JComboBox jcbx_X_PAIS;
    // End of variables declaration//GEN-END:variables

    public void Status_Navegacao(int wRegPos, int wTotReg) {
        jLabel5.setText(" Registro:   " + String.valueOf(wRegPos + 1) + " / " + String.valueOf(wTotReg));
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
        ID_EMITENTE.setEnabled(false);
        botao_pesquisa.setEnabled(false);
        btn_novo.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_pesquisar.setEnabled(false);
        X_NOME.setEnabled(true);
        X_FANT.setEnabled(true);
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
        X_EMAILCONTADOR.setEnabled(true);
        X_CONTADOR.setEnabled(true);
        //LOCAL_CERTIFICADO.setEnabled(true);
        //LOCAL_CACERTS.setEnabled(true);
        //SENHA_CERTIFICADO.setEnabled(true);
    }

    public void disableFields() {
        ID_EMITENTE.setEnabled(false);
        //botao_alterar.setEnabled(false);
        botao_pesquisa.setEnabled(false);
        btn_novo.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_pesquisar.setEnabled(false);
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
        LOCAL_CERTIFICADO.setEnabled(false);
        //LOCAL_CACERTS.setEnabled(false);
        SENHA_CERTIFICADO.setEnabled(false);
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
}
