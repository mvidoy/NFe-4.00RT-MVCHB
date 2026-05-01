package app.views.operations.nfe;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import util.FormatFields;
import util.formata;

public class NFe_Documentos_Referenciados extends javax.swing.JInternalFrame {

    private NFe veioDoframe1;
    private JInternalFrame parent;

    private static final int SSL_PORT = 443;
    String gCla_Valor = "";
    formata formata = new formata();
    FormatFields f = new FormatFields();
    String arquivoXML = "";

    static ProgressMonitor pbar;
    static int counter = 0;
    String gNNF = "";
    String gOpcao = "";

    public NFe_Documentos_Referenciados(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wOpcao,
            String wNFe,
            String w_Tipo,
            String w_NFe,
            String w_cUF,
            String w_AAMM,
            String w_CNPJ,
            String w_mod,
            String w_serie,
            String w_nNF) {
        gOpcao = wOpcao;
        gNNF = wNFe;
        w_NFe = w_NFe.replace(" ", "");
        this.jDesktopPane2 = jDesktop;
        initComponents();

        jRBt_NFe.setSelected(false);
        jRBt_CTe.setSelected(false);
        jRBt_NotaFiscal.setSelected(false);

        jRBt_NFe.setEnabled(true);
        jRBt_CTe.setEnabled(true);
        jRBt_NotaFiscal.setEnabled(true);

        if (wOpcao.equals("Altera")) {
            jRBt_NFe.setEnabled(false);
            jRBt_CTe.setEnabled(false);
            jRBt_NotaFiscal.setEnabled(false);
        }

        if (w_Tipo.trim().equals("NF-e")) {
            jRBt_NFe.setSelected(true);
            tf_NFref_refNFe.setText(w_NFe);
            tf_NFref_refNFe.setEnabled(true);
            tf_NFref_refNFe.setEditable(true);

            tf_NFref_serie.setEnabled(false);
            tf_NFref_nNF.setEnabled(false);
            tf_NFref_mod.setEnabled(false);
            jcbx_NFref_cUF.setEnabled(false);
            tf_NFref_AAMM.setEnabled(false);
            tf_NFref_CNPJ.setEnabled(false);

            tf_NFref_serie.setEditable(false);
            tf_NFref_nNF.setEditable(false);
            tf_NFref_mod.setEditable(false);
            jcbx_NFref_cUF.setEditable(false);
            tf_NFref_AAMM.setEditable(false);
            tf_NFref_CNPJ.setEditable(false);
        }
        if (w_Tipo.trim().equals("CT-e")) {
            jRBt_CTe.setSelected(true);
            tf_NFref_refNFe.setText(w_NFe);
            tf_NFref_refNFe.setEnabled(true);
            tf_NFref_refNFe.setEditable(true);

            tf_NFref_serie.setEnabled(false);
            tf_NFref_nNF.setEnabled(false);
            tf_NFref_mod.setEnabled(false);
            jcbx_NFref_cUF.setEnabled(false);
            tf_NFref_AAMM.setEnabled(false);
            tf_NFref_CNPJ.setEnabled(false);

            tf_NFref_serie.setEditable(false);
            tf_NFref_nNF.setEditable(false);
            tf_NFref_mod.setEditable(false);
            jcbx_NFref_cUF.setEditable(false);
            tf_NFref_AAMM.setEditable(false);
            tf_NFref_CNPJ.setEditable(false);
        }
        if (w_Tipo.trim().equals("Nota Fiscal")) {
            jRBt_NotaFiscal.setSelected(true);
            tf_NFref_serie.setText(w_serie);
            tf_NFref_nNF.setText(w_nNF);
            tf_NFref_mod.setText(w_mod);
            Percorrejcbx(jcbx_NFref_cUF, w_cUF);
            tf_NFref_AAMM.setText(w_AAMM);
            tf_NFref_CNPJ.setText(w_CNPJ);

            tf_NFref_refNFe.setEnabled(false);
            tf_NFref_refNFe.setEditable(false);

            tf_NFref_serie.setEnabled(true);
            tf_NFref_nNF.setEnabled(true);
            tf_NFref_mod.setEnabled(true);
            jcbx_NFref_cUF.setEnabled(true);
            tf_NFref_AAMM.setEnabled(true);
            tf_NFref_CNPJ.setEnabled(true);

            tf_NFref_serie.setEditable(true);
            tf_NFref_nNF.setEditable(true);
            tf_NFref_mod.setEditable(true);
            tf_NFref_AAMM.setEditable(true);
            tf_NFref_CNPJ.setEditable(true);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jRBt_NFe = new javax.swing.JRadioButton();
        jRBt_CTe = new javax.swing.JRadioButton();
        jRBt_NotaFiscal = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLb_CNPJ = new javax.swing.JLabel();
        jLb_Modelo = new javax.swing.JLabel();

        tf_NFref_AAMM = new javax.swing.JTextField();
        jcbx_NFref_cUF = new javax.swing.JComboBox();
        jLb_Serie = new javax.swing.JLabel();

        tf_NFref_CNPJ = new javax.swing.JTextField();
        jLb_MesAno = new javax.swing.JLabel();

        tf_NFref_nNF = new javax.swing.JTextField();
        jLb_Numero = new javax.swing.JLabel();

        tf_NFref_mod = new javax.swing.JTextField();
        jLb_UF = new javax.swing.JLabel();

        tf_NFref_serie = new javax.swing.JTextField();
        jLb_Modelo1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLb_ChaveAcesso = new javax.swing.JLabel();

        tf_NFref_refNFe = new javax.swing.JTextField();
        botao_sair = new javax.swing.JButton();
        botao_OK = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("NF-e/CT-e/Nota Fiscal Referenciada - Revisão: 01 - Data da Última Revisão: 02/08/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas e Conhecimentos Fiscais Referenciados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N
        jDesktopPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDesktopPane2MouseClicked(evt);
            }
        });

        jRBt_NFe.setText("NF-e");
        jRBt_NFe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jRBt_NFeMouseMoved(evt);
            }
        });
        jRBt_NFe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBt_NFeActionPerformed(evt);
            }
        });

        jRBt_CTe.setText("CT-e");
        jRBt_CTe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBt_CTeActionPerformed(evt);
            }
        });

        jRBt_NotaFiscal.setText("Nota Fiscal");
        jRBt_NotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBt_NotaFiscalActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nota Fiscal"));

        jLb_CNPJ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_CNPJ.setText("CNPJ");

        jLb_Modelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_Modelo.setText("Modelo");

        tf_NFref_AAMM.setEditable(false);
        tf_NFref_AAMM.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_AAMM.setToolTipText("");
        tf_NFref_AAMM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_AAMM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_AAMMKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_AAMMKeyReleased(evt);
            }
        });

        jcbx_NFref_cUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "AC", "AL", "AP", "AM", "BA", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " ", " ", " ", " " }));
        jcbx_NFref_cUF.setToolTipText("");
        jcbx_NFref_cUF.setEnabled(false);

        jLb_Serie.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_Serie.setText("Série");

        tf_NFref_CNPJ.setEditable(false);
        tf_NFref_CNPJ.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_CNPJ.setToolTipText("");
        tf_NFref_CNPJ.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_CNPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_CNPJKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_CNPJKeyReleased(evt);
            }
        });

        jLb_MesAno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_MesAno.setText("Mês e ano de emissão (mm/aa)");

        tf_NFref_nNF.setEditable(false);
        tf_NFref_nNF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_nNF.setToolTipText("");
        tf_NFref_nNF.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_nNF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_nNFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_nNFKeyReleased(evt);
            }
        });

        jLb_Numero.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_Numero.setText("Número");

        tf_NFref_mod.setEditable(false);
        tf_NFref_mod.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_mod.setToolTipText("");
        tf_NFref_mod.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_modKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_modKeyReleased(evt);
            }
        });

        jLb_UF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_UF.setText("UF");

        tf_NFref_serie.setEditable(false);
        tf_NFref_serie.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_serie.setToolTipText("");
        tf_NFref_serie.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_serie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_serieKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_serieKeyReleased(evt);
            }
        });

        jLb_Modelo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLb_Modelo1.setText("( Sem máscara, só numeros)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLb_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(tf_NFref_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLb_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tf_NFref_nNF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLb_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_NFref_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLb_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jcbx_NFref_cUF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLb_MesAno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tf_NFref_AAMM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLb_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(tf_NFref_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLb_Modelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLb_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_NFref_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLb_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_NFref_nNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLb_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_NFref_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLb_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_NFref_cUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLb_MesAno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_NFref_AAMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLb_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_NFref_CNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLb_Modelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("NF-e / CT-e"));

        jLb_ChaveAcesso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLb_ChaveAcesso.setText("* Chave de Acesso");

        tf_NFref_refNFe.setEditable(false);
        tf_NFref_refNFe.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_NFref_refNFe.setToolTipText("");
        tf_NFref_refNFe.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tf_NFref_refNFe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_NFref_refNFeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_NFref_refNFeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLb_ChaveAcesso)
                    .addComponent(tf_NFref_refNFe, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLb_ChaveAcesso)
                .addGap(6, 6, 6)
                .addComponent(tf_NFref_refNFe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDesktopPane2.setLayer(jRBt_NFe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jRBt_CTe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jRBt_NotaFiscal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jRBt_NFe)
                        .addGap(21, 21, 21)
                        .addComponent(jRBt_CTe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jRBt_NotaFiscal))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBt_NFe)
                    .addComponent(jRBt_CTe)
                    .addComponent(jRBt_NotaFiscal))
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        botao_OK.setMnemonic('L');
        botao_OK.setText("OK");
        botao_OK.setToolTipText("");
        botao_OK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botao_OK.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_OKMouseMoved(evt);
            }
        });
        botao_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_OKActionPerformed(evt);
            }
        });

        jLabel30.setText("(*) Campo de preenchimento obrigatório.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botao_OK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_sair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_OK)
                        .addComponent(botao_sair))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 658, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
    }//GEN-LAST:event_formFocusLost

    private void jDesktopPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane2MouseClicked

    }//GEN-LAST:event_jDesktopPane2MouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

    }//GEN-LAST:event_formInternalFrameClosed

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_OKActionPerformed
        if (veioDoframe1 != null) {
            if (jRBt_NFe.isSelected()) {
                veioDoframe1.retornaRegistro2(
                        gOpcao,
                        "NF-e",
                        f.getIdNFeFormatado(tf_NFref_refNFe.getText().trim().replace("NFe", "")),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "");
            }
            if (jRBt_CTe.isSelected()) {
                veioDoframe1.retornaRegistro2(
                        gOpcao,
                        "CT-e",
                        f.getIdNFeFormatado(tf_NFref_refNFe.getText().trim().replace("NFe", "")),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "");
            }
            if (jRBt_NotaFiscal.isSelected()) {
                veioDoframe1.retornaRegistro3(
                        gOpcao,
                        "Nota Fiscal",
                        "",
                        Percorrejcbx(jcbx_NFref_cUF, jcbx_NFref_cUF.getSelectedItem().toString()),
                        tf_NFref_AAMM.getText().trim(),
                        tf_NFref_CNPJ.getText().trim(),
                        tf_NFref_mod.getText().trim(),
                        tf_NFref_serie.getText().trim(),
                        tf_NFref_nNF.getText().trim());
            }
        }
        dispose();
    }//GEN-LAST:event_botao_OKActionPerformed

    private void tf_NFref_refNFeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_refNFeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_refNFeKeyPressed

    private void tf_NFref_refNFeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_refNFeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_refNFeKeyReleased

    private void tf_NFref_AAMMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_AAMMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_AAMMKeyPressed

    private void tf_NFref_AAMMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_AAMMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_AAMMKeyReleased

    private void tf_NFref_CNPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_CNPJKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_CNPJKeyPressed

    private void tf_NFref_CNPJKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_CNPJKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_CNPJKeyReleased

    private void tf_NFref_nNFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_nNFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_nNFKeyPressed

    private void tf_NFref_nNFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_nNFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_nNFKeyReleased

    private void tf_NFref_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_modKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_modKeyPressed

    private void tf_NFref_modKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_modKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_modKeyReleased

    private void tf_NFref_serieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_serieKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_serieKeyPressed

    private void tf_NFref_serieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_NFref_serieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NFref_serieKeyReleased

    private void jRBt_NotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBt_NotaFiscalActionPerformed
        jRBt_NFe.setSelected(false);
        jRBt_CTe.setSelected(false);
        jLb_Serie.setText("* Série");
        jLb_Numero.setText("* Número");
        jLb_Modelo.setText("* Modelo");
        jLb_UF.setText("* UF");
        jLb_MesAno.setText("* Mês e ano de emissão (mm/aa)");
        jLb_CNPJ.setText("* CNPJ");

        tf_NFref_refNFe.setEnabled(false);
        tf_NFref_refNFe.setEditable(false);

        tf_NFref_serie.setEnabled(true);
        tf_NFref_nNF.setEnabled(true);
        tf_NFref_mod.setEnabled(true);
        jcbx_NFref_cUF.setEnabled(true);
        tf_NFref_AAMM.setEnabled(true);
        tf_NFref_CNPJ.setEnabled(true);

        tf_NFref_serie.setEditable(true);
        tf_NFref_nNF.setEditable(true);
        tf_NFref_mod.setEditable(true);
        tf_NFref_AAMM.setEditable(true);
        tf_NFref_CNPJ.setEditable(true);
    }//GEN-LAST:event_jRBt_NotaFiscalActionPerformed

    private void jRBt_CTeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBt_CTeActionPerformed
        jRBt_NFe.setSelected(false);
        jRBt_NotaFiscal.setSelected(false);
        jLb_Serie.setText("Série");
        jLb_Numero.setText("Número");
        jLb_Modelo.setText("Modelo");
        jLb_UF.setText("UF");
        jLb_MesAno.setText("Mês e ano de emissão (mm/aa)");
        jLb_CNPJ.setText("CNPJ");

        tf_NFref_refNFe.setEnabled(true);
        tf_NFref_refNFe.setEditable(true);

        tf_NFref_serie.setEnabled(false);
        tf_NFref_nNF.setEnabled(false);
        tf_NFref_mod.setEnabled(false);
        jcbx_NFref_cUF.setEnabled(false);
        tf_NFref_AAMM.setEnabled(false);
        tf_NFref_CNPJ.setEnabled(false);

        tf_NFref_serie.setEditable(false);
        tf_NFref_nNF.setEditable(false);
        tf_NFref_mod.setEditable(false);
        jcbx_NFref_cUF.setEditable(false);
        tf_NFref_AAMM.setEditable(false);
        tf_NFref_CNPJ.setEditable(false);
    }//GEN-LAST:event_jRBt_CTeActionPerformed

    private void jRBt_NFeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBt_NFeActionPerformed
        jRBt_CTe.setSelected(false);
        jRBt_NotaFiscal.setSelected(false);
        jLb_Serie.setText("Série");
        jLb_Numero.setText("Número");
        jLb_Modelo.setText("Modelo");
        jLb_UF.setText("UF");
        jLb_MesAno.setText("Mês e ano de emissão (mm/aa)");
        jLb_CNPJ.setText("CNPJ");

        tf_NFref_refNFe.setEnabled(true);
        tf_NFref_refNFe.setEditable(true);

        tf_NFref_serie.setEnabled(false);
        tf_NFref_nNF.setEnabled(false);
        tf_NFref_mod.setEnabled(false);
        jcbx_NFref_cUF.setEnabled(false);
        tf_NFref_AAMM.setEnabled(false);
        tf_NFref_CNPJ.setEnabled(false);

        tf_NFref_serie.setEditable(false);
        tf_NFref_nNF.setEditable(false);
        tf_NFref_mod.setEditable(false);
        jcbx_NFref_cUF.setEditable(false);
        tf_NFref_AAMM.setEditable(false);
        tf_NFref_CNPJ.setEditable(false);
    }//GEN-LAST:event_jRBt_NFeActionPerformed

    private void botao_OKMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_OKMouseMoved
        botao_OK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_OKMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    private void jRBt_NFeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBt_NFeMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBt_NFeMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_OK;
    private javax.swing.JButton botao_sair;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLb_CNPJ;
    private javax.swing.JLabel jLb_ChaveAcesso;
    private javax.swing.JLabel jLb_MesAno;
    private javax.swing.JLabel jLb_Modelo;
    private javax.swing.JLabel jLb_Modelo1;
    private javax.swing.JLabel jLb_Numero;
    private javax.swing.JLabel jLb_Serie;
    private javax.swing.JLabel jLb_UF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRBt_CTe;
    private javax.swing.JRadioButton jRBt_NFe;
    private javax.swing.JRadioButton jRBt_NotaFiscal;
    private javax.swing.JComboBox jcbx_NFref_cUF;
    private javax.swing.JTextField tf_NFref_AAMM;
    private javax.swing.JTextField tf_NFref_CNPJ;
    private javax.swing.JTextField tf_NFref_mod;
    private javax.swing.JTextField tf_NFref_nNF;
    private javax.swing.JTextField tf_NFref_refNFe;
    private javax.swing.JTextField tf_NFref_serie;
    // End of variables declaration//GEN-END:variables

    public void FechaTodasTabelas() {
    }

    public void enviaRegistro(NFe veioDo1, String wVar1) {
        //tf_codigo.setText(wVar1);
        //tf_descricao.setText(wVar2);
        this.veioDoframe1 = veioDo1;
        //MontaTabelaComposicao();
    }

    public String Percorrejcbx(JComboBox jcbx, String wVar) {
        try {
            if (wVar == null
                    || wVar.toString().trim().length() <= 0) {
                jcbx.setSelectedIndex(-1);
                return wVar;
            }
            String wwVar = wVar.toString().trim();
            boolean wFlagjcbx = false;
            int wIndice = -1;
            for (int i = 0; i < jcbx.getItemCount(); i++) {
                if (jcbx.getItemAt(i).toString().trim().equals(wwVar)) {
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
                jcbx.addItem(new comboMultidata(wwVar, wwVar));
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

}
