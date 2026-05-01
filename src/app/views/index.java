package app.views;

/*
 Alteração: 29/07/2020
 Baixado o java-nfe-4.00.13.jar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/java-nfe-4.00.13.jar) e adicionado às bibliotecas do Projeto.
 
Alteração: 26/07/2019
 Portal do Manifesto Eletrônico de Documentos Fiscais - SVRS
 Validador Xml - https://dfe-portal.svrs.rs.gov.br/Mdfe/ValidadorXML
 */
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.json.JSONObject;
import java.awt.Component;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import services.Conection;
import com.frontend.config.env;
import app.controllers.EmitenteController;
import app.models.EMITENTE;
import app.daos.EMITENTEdaoJpa;
import app.views.entries.emitente.Emitente;
import app.views.operations.nfe.NFe;

import static com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum.CERTIFICADOBRADESCOPFXDOSANDBOX;
import static com.backend.api.cobranca.bradesco.dom.enuns.ServicosBradescoEnum.CERTIFICADOBRADESCOPFXPRODUCAO;
import static com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum.CERTIFICADOSANTANDERPFXDOSANDBOX;
import static com.backend.api.cobranca.santander.dom.enuns.ServicosSantanderEnum.CERTIFICADOSANTANDERPFXPRODUCAO;


import app.views.operations.nfe.modal.InutilizacaoNFe;
import app.views.operations.nfe.modal.InutilizacaoHistoricoNFe;
import app.views.operations.nfe.modal.ConsultaStatusServicoNaSEFAZ;
import app.views.operations.nfe.modal.SelecionaImportaArquivosTXTXML;
import app.views.operations.report.RelatorioGerencial;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicMenuBarUI;
import util.formata;

public class index extends javax.swing.JFrame {

    private ConsultaStatusServicoNaSEFAZ ConsultaStatusServicoNaSEFAZ_frm;
    private InutilizacaoNFe InutilizacaoNFe_frm;
    private InutilizacaoHistoricoNFe InutilizacaoHistoricoNFe_frm;

    private NFe NFe_frm;
    private Emitente Emitente_frm;
    private SelecionaImportaArquivosTXTXML SelecionaImportaArquivosTXTXML_frm;
    private RelatorioGerencial RelatorioGerencial_frm;
    private FrmAjudaSobre FrmAjudaSobre_frm;

    public JInternalFrame jFrame;
    public int internalFrame = 0;
    Timer timer;
    DecimalFormat formato;
    double height = 0.0;
    double width = 0.0;
    Conection con_default;
    env Env = new env();
    String getTitle = "Emisssor de Nota Fiscal Eletrônica [NF-e] - Versão 4.01RT - Última Revisão: 04/05/2026 - NetBeans 8.0.2 - PostgreSql: 9.3.5";

    public index() {
        initComponents();
        menuBar.setOpaque(true);
        menuBar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new java.awt.Color(98, 78, 165));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        menu_nfe.setForeground(new java.awt.Color(255, 255, 255));
        menu_cadastros.setForeground(new java.awt.Color(255, 255, 255));
        menu_operacoes.setForeground(new java.awt.Color(255, 255, 255));
        menu_utilitarios.setForeground(new java.awt.Color(255, 255, 255));
        menu_ajuda.setForeground(new java.awt.Color(255, 255, 255));
        env Env = new env();
        System.setProperty("EMITENTE", Env.getEMITENTE_DEFAULT());
        System.setProperty("CERTIFICADO", "");
        System.setProperty("boletoGerado", "");
        pesquisaEmitente();
        getViewAmbiente();
        StatusBar.setText("NFe: [database: " + System.getProperty("db_base") + "], [Host: " + System.getProperty("IP") + "]");
        this.setMaximumSize(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setResizable(true);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/icone_nfe.jpg")).getImage());
        this.setVisible(true);
        menu_ajuda.setMnemonic('A');
        formato = new DecimalFormat("00");
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarHoras();
            }
        });
        timer.start();
        usuario.setText("Usuário: " + System.getProperty("pas_usuario").trim() + " ");
        datasis.setText("Validade Certificado: " + getValidadeCertificado() + " ");
        datasis1.setText("Data: " + getDataAtual() + " ");
        JSONObject jsonEMITENTE = new JSONObject();
        //try {
        //    jsonEMITENTE = EmitenteController.Show();
        //} catch (SQLException ex) {
        //    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //jsonEMITENTE.remove("ID_EMITENTE");
        if (jsonEMITENTE.length() > 0) {
            menu_nfe.setEnabled(false);
            menu_utilitarios.setEnabled(false);
            int selection = JOptionPane.showConfirmDialog(this,
                    "Prezado(a) senhor(a), bem vindo ao aplicativo Emissor de Manifesto Eletrônico de Documentos Fiscais!\n"
                    + "Não há emitente de NFe cadastrado.\n"
                    + "Na tela seguinte, clique em 'Incluir' e informe os dados do estabelecimento emissor.",
                    "Mensagem do sistema",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.OK_OPTION) {
                System.out.println("aqui");
                try {
                    Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                    this.setCursor(cursor);
                    //Cadastro_Emitente frm = new Cadastro_Emitente(jDesktopPane1);
                    Emitente frm = new Emitente(jDktInputData);
                    double width = frm.getSize().getWidth();
                    double height = frm.getSize().getHeight();
                    int wwidth = (int) width;
                    int wheight = (int) height;
                    frm.setLocation((this.getSize().width - (wwidth + 8)) / 2, (((this.getSize().height - 110) - wheight) / 2));
                    jDktInputData.add(frm);
                    frm.setMaximum(true);
                    frm.setIconifiable(true);
                    frm.setMaximizable(true);
                    frm.setClosable(true);
                    frm.setResizable(true);
                    frm.setVisible(true);
                    frm.toFront();
                    frm.grabFocus();
                    cursor = Cursor.getDefaultCursor();
                    this.setCursor(cursor);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        validarTokensEBancos();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem15 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        tf_ie = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tf_razaosocial = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tf_cnpj = new javax.swing.JTextField();
        jDktInputData = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        StatusBar = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        datasis = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        datasis1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu_nfe = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menu_sair = new javax.swing.JMenuItem();
        menu_cadastros = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menu_operacoes = new javax.swing.JMenu();
        menu_empresa = new javax.swing.JMenuItem();
        menu_utilitarios = new javax.swing.JMenu();
        mnUtilitarios_parametros = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        menu_ajuda = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();

        jMenuItem15.setText("jMenuItem15");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(98, 78, 165));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setIconImages(null);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        desktopPane.setBackground(new java.awt.Color(0, 204, 204));
        desktopPane.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(98, 78, 165));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emitente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("IE:");

        tf_ie.setEditable(false);
        tf_ie.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_ie.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_ie.setToolTipText("");
        tf_ie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Razão Social:");

        tf_razaosocial.setEditable(false);
        tf_razaosocial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_razaosocial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_razaosocial.setToolTipText("");
        tf_razaosocial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("CNPJ:");

        tf_cnpj.setEditable(false);
        tf_cnpj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tf_cnpj.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tf_cnpj.setToolTipText("");
        tf_cnpj.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_razaosocial, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tf_cnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tf_ie, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_razaosocial)
                    .addComponent(tf_cnpj)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_ie)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jDktInputData.setBackground(new java.awt.Color(0, 204, 204));
        jDktInputData.setForeground(new java.awt.Color(0, 204, 204));
        jDktInputData.setToolTipText("");

        jLabel2.setBackground(new java.awt.Color(153, 153, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe2.jpg"))); // NOI18N
        jLabel2.setToolTipText("");
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "<html>\n<table align=center>\n<td color=\"red\" align=center>\n<br>VERSÃO TESTE<br>\n<br>Os documentos eletrônicos gerados por esse aplicativo não tem validade jurídica<br>\n</td>\n</table>\n<table align=center>\n<td color=\"white\" align=center>\n<br>Emissor de Nota Fiscal Eletrônica (NF-e)</br>\n</td>\n</table>\n</html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(415, 600));
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel2MouseMoved(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jDktInputData.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDktInputDataLayout = new javax.swing.GroupLayout(jDktInputData);
        jDktInputData.setLayout(jDktInputDataLayout);
        jDktInputDataLayout.setHorizontalGroup(
            jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDktInputDataLayout.setVerticalGroup(
            jDktInputDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDktInputDataLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        StatusBar.setBackground(new java.awt.Color(98, 78, 165));
        StatusBar.setForeground(new java.awt.Color(255, 255, 255));
        StatusBar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StatusBar.setText("NFe");
        StatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        StatusBar.setOpaque(true);
        StatusBar.setPreferredSize(new java.awt.Dimension(80, 18));

        hora.setBackground(new java.awt.Color(98, 78, 165));
        hora.setForeground(new java.awt.Color(255, 255, 255));
        hora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hora.setText("Hora:");
        hora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        hora.setOpaque(true);
        hora.setPreferredSize(new java.awt.Dimension(80, 18));

        datasis.setBackground(new java.awt.Color(98, 78, 165));
        datasis.setForeground(new java.awt.Color(255, 255, 255));
        datasis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        datasis.setText("Vencimento Certificado:");
        datasis.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        datasis.setOpaque(true);
        datasis.setPreferredSize(new java.awt.Dimension(80, 18));

        usuario.setBackground(new java.awt.Color(98, 78, 165));
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usuario.setText("Usuário:");
        usuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        usuario.setOpaque(true);
        usuario.setPreferredSize(new java.awt.Dimension(80, 18));

        datasis1.setBackground(new java.awt.Color(98, 78, 165));
        datasis1.setForeground(new java.awt.Color(255, 255, 255));
        datasis1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        datasis1.setText("Data:");
        datasis1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        datasis1.setOpaque(true);
        datasis1.setPreferredSize(new java.awt.Dimension(80, 18));

        desktopPane.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(jDktInputData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(StatusBar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(hora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(datasis, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(usuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(datasis1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDktInputData)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                .addComponent(StatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(datasis, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(datasis1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDktInputData)
                .addGap(0, 0, 0)
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(datasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(datasis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        getContentPane().add(desktopPane);

        menuBar.setBackground(new java.awt.Color(98, 78, 165));
        menuBar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                Resized(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        menu_nfe.setBackground(new java.awt.Color(98, 78, 165));
        menu_nfe.setMnemonic('N');
        menu_nfe.setText("Nota Fiscal Eletrônica");
        menu_nfe.setToolTipText("");
        menu_nfe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_nfeMouseMoved(evt);
            }
        });
        menu_nfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_nfeActionPerformed(evt);
            }
        });

        jSeparator2.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        menu_nfe.add(jSeparator2);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setText("Consulta Status do Serviço/Ativa CONTINGÊNCIA (SVC) do Emissor da NF-e");
        jMenuItem18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem18MouseMoved(evt);
            }
        });
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        menu_nfe.add(jMenuItem18);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Emitir Nova NFe");
        jMenuItem1.setEnabled(false);
        menu_nfe.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Gerenciar NFe");
        jMenuItem2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseMoved(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_nfe.add(jMenuItem2);

        jMenu1.setText("Inutilizações");
        jMenu1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu1MouseMoved(evt);
            }
        });

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Inutilizar Faixa de Numeração");
        jMenuItem4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseMoved(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Consultar Inutilização cadastrada no emissor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        menu_nfe.add(jMenu1);
        menu_nfe.add(jSeparator1);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Cancelar NF-e não cadastrada no Software");
        jMenuItem6.setEnabled(false);
        menu_nfe.add(jMenuItem6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Encerrar NF-e não cadastrada no Software");
        jMenuItem5.setEnabled(false);
        menu_nfe.add(jMenuItem5);
        menu_nfe.add(jSeparator6);

        menu_sair.setMnemonic('S');
        menu_sair.setText("Sair");
        menu_sair.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_sairMouseMoved(evt);
            }
        });
        menu_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sairActionPerformed(evt);
            }
        });
        menu_nfe.add(menu_sair);

        menuBar.add(menu_nfe);

        menu_cadastros.setText("Cadastro/Tabelas");
        menu_cadastros.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_cadastrosMouseMoved(evt);
            }
        });

        jMenu2.setText("Imposto Seletivo (IS)");
        jMenu2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu2MouseMoved(evt);
            }
        });

        jMenuItem13.setText("Situação Tributária (CST - IS)");
        jMenuItem13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem13MouseMoved(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem19.setText("Classificação Tributária (cClassTrib - IS)");
        jMenuItem19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem19MouseMoved(evt);
            }
        });
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem19);

        menu_cadastros.add(jMenu2);

        jMenu3.setText("Imposto e Contribuição de Bens e Serviços (IBS e CBS)");
        jMenu3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu3MouseMoved(evt);
            }
        });

        jMenuItem7.setText("Situação Tributária (CST - IBS e CBS)");
        jMenuItem7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseMoved(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Classificação Tributária (cClassTrib - IBS e CBS)");
        jMenuItem8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseMoved(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        menu_cadastros.add(jMenu3);

        menuBar.add(menu_cadastros);

        menu_operacoes.setBackground(new java.awt.Color(98, 78, 165));
        menu_operacoes.setMnemonic('O');
        menu_operacoes.setText("Emitente");
        menu_operacoes.setToolTipText("");
        menu_operacoes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_operacoesMouseMoved(evt);
            }
        });
        menu_operacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_operacoesActionPerformed(evt);
            }
        });

        menu_empresa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menu_empresa.setMnemonic('B');
        menu_empresa.setText("Dados do Emitente Atual");
        menu_empresa.setToolTipText("");
        menu_empresa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_empresaMouseMoved(evt);
            }
        });
        menu_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_empresaActionPerformed(evt);
            }
        });
        menu_operacoes.add(menu_empresa);

        menuBar.add(menu_operacoes);

        menu_utilitarios.setMnemonic('U');
        menu_utilitarios.setText("Sistema");
        menu_utilitarios.setToolTipText("");
        menu_utilitarios.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                teste(evt);
            }
        });
        menu_utilitarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_utilitariosMouseMoved(evt);
            }
        });

        mnUtilitarios_parametros.setText("Parâmetros");
        mnUtilitarios_parametros.setToolTipText("");
        mnUtilitarios_parametros.setEnabled(false);
        menu_utilitarios.add(mnUtilitarios_parametros);
        menu_utilitarios.add(jSeparator5);

        jMenuItem14.setText("Backup");
        jMenuItem14.setEnabled(false);
        menu_utilitarios.add(jMenuItem14);

        jMenuItem11.setText("Restaurar");
        jMenuItem11.setEnabled(false);
        menu_utilitarios.add(jMenuItem11);
        menu_utilitarios.add(jSeparator7);

        jMenuItem17.setText("Importar Arquivos");
        jMenuItem17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem17MouseMoved(evt);
            }
        });
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        menu_utilitarios.add(jMenuItem17);
        menu_utilitarios.add(jSeparator3);

        jMenuItem10.setText("Certificados");
        jMenuItem10.setEnabled(false);
        menu_utilitarios.add(jMenuItem10);
        menu_utilitarios.add(jSeparator4);

        jMenuItem12.setText("Relatório Gerencial");
        jMenuItem12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseMoved(evt);
            }
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        menu_utilitarios.add(jMenuItem12);

        menuBar.add(menu_utilitarios);

        menu_ajuda.setMnemonic('A');
        menu_ajuda.setText("Ajuda");
        menu_ajuda.setToolTipText("");
        menu_ajuda.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_ajudaMouseMoved(evt);
            }
        });
        menu_ajuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ajudaActionPerformed(evt);
            }
        });

        contentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        contentMenuItem.setMnemonic('A');
        contentMenuItem.setText("<html><u>A</u>juda</html>");
        contentMenuItem.setToolTipText("");
        contentMenuItem.setEnabled(false);
        contentMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                contentMenuItemMouseMoved(evt);
            }
        });
        menu_ajuda.add(contentMenuItem);

        aboutMenuItem.setMnemonic('S');
        aboutMenuItem.setText("<html><u>S</u>obre</html>");
        aboutMenuItem.setToolTipText("");
        aboutMenuItem.setDoubleBuffered(true);
        aboutMenuItem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                aboutMenuItemMouseMoved(evt);
            }
        });
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        menu_ajuda.add(aboutMenuItem);

        jMenuItem16.setText("Buscar informações do ambiente");
        jMenuItem16.setEnabled(false);
        menu_ajuda.add(jMenuItem16);

        menuBar.add(menu_ajuda);

        setJMenuBar(menuBar);

        getAccessibleContext().setAccessibleName("CAMAR PLÁSTICOS LTDA (Emissir de Nota Fiscal Eletrônica - NF-e - Versão 3.10) - Última Revisão: 20/05/2016 - NetBeans 8.0 - PostgreSql: 9.3.5");

        setSize(new java.awt.Dimension(1028, 743));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_empresaActionPerformed
        if (!VerifyClassisInstance()) {
            Emitente_frm = new Emitente(jDktInputData);
            ThreeDLauncher(Emitente_frm, false);
        }
    }//GEN-LAST:event_menu_empresaActionPerformed

    private void menu_nfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_nfeActionPerformed
        getViewAmbiente();
    }//GEN-LAST:event_menu_nfeActionPerformed

    private void menu_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menu_sairActionPerformed

    private void Resized(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Resized
    }//GEN-LAST:event_Resized

    private void teste(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_teste
    }//GEN-LAST:event_teste

    private void menu_ajudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ajudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_ajudaActionPerformed

    private void menu_operacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_operacoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_operacoesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        getViewAmbiente();
        if (!VerifyClassisInstance()) {
            NFe_frm = new NFe(jDktInputData, jFrame);
            ThreeDLauncher(NFe_frm, true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        if (!VerifyClassisInstance()) {
            SelecionaImportaArquivosTXTXML_frm = new SelecionaImportaArquivosTXTXML(jDktInputData, jFrame);
            ThreeDLauncher(SelecionaImportaArquivosTXTXML_frm, true);
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        if (!VerifyClassisInstance()) {
            ConsultaStatusServicoNaSEFAZ_frm = new ConsultaStatusServicoNaSEFAZ(jDktInputData, jFrame);
            ThreeDLauncher(ConsultaStatusServicoNaSEFAZ_frm, false);
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setResizable(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //  this.setDefaultLookAndFeelDecorated(true);
        //getContentPane().setBackground(Color.red); 
//  this.setUndecorated(true);
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
        //UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black));
        //UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
        //UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN, 11));
    }//GEN-LAST:event_formWindowOpened

    private void menu_nfeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_nfeMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_nfe.setCursor(cursor);
    }//GEN-LAST:event_menu_nfeMouseMoved

    private void menu_operacoesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_operacoesMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_operacoes.setCursor(cursor);
    }//GEN-LAST:event_menu_operacoesMouseMoved

    private void menu_utilitariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_utilitariosMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_utilitarios.setCursor(cursor);
    }//GEN-LAST:event_menu_utilitariosMouseMoved

    private void menu_ajudaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_ajudaMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_ajuda.setCursor(cursor);
    }//GEN-LAST:event_menu_ajudaMouseMoved

    private void jMenuItem17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem17MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem17.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem17MouseMoved

    private void jMenuItem18MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem18MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem18.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem18MouseMoved

    private void jMenuItem2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem2.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem2MouseMoved

    private void menu_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_sairMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_sair.setCursor(cursor);
    }//GEN-LAST:event_menu_sairMouseMoved

    private void menu_empresaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_empresaMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_empresa.setCursor(cursor);
    }//GEN-LAST:event_menu_empresaMouseMoved

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (!VerifyClassisInstance()) {
            InutilizacaoNFe_frm = new InutilizacaoNFe(jDktInputData, jFrame);
            ThreeDLauncher(InutilizacaoNFe_frm, true);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        if (!VerifyClassisInstance()) {
            RelatorioGerencial_frm = new RelatorioGerencial(jDktInputData, jFrame);
            ThreeDLauncher(RelatorioGerencial_frm, true);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void contentMenuItemMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentMenuItemMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_contentMenuItemMouseMoved

    private void jMenuItem12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem12.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem12MouseMoved

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed

        if (!VerifyClassisInstance()) {
            FrmAjudaSobre_frm = new FrmAjudaSobre(jDktInputData, jFrame);
            ThreeDLauncher(FrmAjudaSobre_frm, false);
        }
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void aboutMenuItemMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuItemMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        aboutMenuItem.setCursor(cursor);
    }//GEN-LAST:event_aboutMenuItemMouseMoved

    private void jMenu1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenu1.setCursor(cursor);
    }//GEN-LAST:event_jMenu1MouseMoved

    private void jMenuItem4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem4.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem4MouseMoved

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (!VerifyClassisInstance()) {
            InutilizacaoHistoricoNFe_frm = new InutilizacaoHistoricoNFe(jDktInputData, jFrame);
            ThreeDLauncher(InutilizacaoHistoricoNFe_frm, true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jLabel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseMoved
        getViewAmbiente();
    }//GEN-LAST:event_jLabel2MouseMoved

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void menu_cadastrosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_cadastrosMouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        menu_cadastros.setCursor(cursor);
    }//GEN-LAST:event_menu_cadastrosMouseMoved

    private void jMenu2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenu2.setCursor(cursor);
    }//GEN-LAST:event_jMenu2MouseMoved

    private void jMenu3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenu3.setCursor(cursor);
    }//GEN-LAST:event_jMenu3MouseMoved

    private void jMenuItem13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem13.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem13MouseMoved

    private void jMenuItem19MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem19MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem19.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem19MouseMoved

    private void jMenuItem7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem7.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem7MouseMoved

    private void jMenuItem8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseMoved
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        jMenuItem8.setCursor(cursor);
    }//GEN-LAST:event_jMenuItem8MouseMoved
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
                } catch (InstantiationException ex) {
                } catch (ClassNotFoundException ex) {
                }
                new index().setVisible(true);
            }
        });
    }

    private void atualizarHoras() {
        Calendar agora = Calendar.getInstance();
        int horas = agora.get(Calendar.HOUR_OF_DAY);
        int minutos = agora.get(Calendar.MINUTE);
        int segundos = agora.get(Calendar.SECOND);
        hora.setText("Hora: " + formato.format(horas) + ":"
                + formato.format(minutos) + ":"
                + formato.format(segundos));
    }

    private void RestauraDesktop() {
        jDktInputData.add(jLabel2);
        jDktInputData.add(hora);
        jDktInputData.add(StatusBar);
    }

    public void pesquisaEmitente() {
        try {
            JSONObject jsonEMITENTE = new JSONObject();
            jsonEMITENTE = EmitenteController.Index(System.getProperty("EMITENTE"));
            boolean wflag = false;
            if (!jsonEMITENTE.isEmpty()) {
                wflag = true;
                tf_razaosocial.setText(jsonEMITENTE.getString("EMP_RZSO").trim());
                tf_cnpj.setText(jsonEMITENTE.getString("EMP_CGC0").trim());
                tf_ie.setText(jsonEMITENTE.getString("EMP_INSE").trim());
            }
            if (!wflag) {
                JOptionPane.showConfirmDialog(this, "Emitente não cadastrado!!!", "Mensagem para " + System.getProperty("user.name"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            System.setProperty("CERTIFICADO", jsonEMITENTE.getString("EMP_CERTIFICADO"));
            System.setProperty("sistCNPJ", tf_cnpj.getText().trim().replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", ""));
            System.out.println(System.getProperty("CERTIFICADO"));
        } catch (SQLException erroSQL) {
            JOptionPane.showMessageDialog(this, "falha na consulta do registro!!! " + erroSQL);
        }
    }

    class BackgroundedDesktopPane extends JDesktopPane {

        Image img;

        public BackgroundedDesktopPane() {
            try {
                java.net.URL caminho = index.class
                        .getResource("background.JPG");
                img = javax.imageio.ImageIO.read(caminho);
            } catch (Exception e) {
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } else {
                g.drawString("Imagem não encontrada", 50, 50);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel StatusBar;
    public javax.swing.JMenuItem aboutMenuItem;
    public javax.swing.JMenuItem contentMenuItem;
    public javax.swing.JLabel datasis;
    public javax.swing.JLabel datasis1;
    public javax.swing.JDesktopPane desktopPane;
    public javax.swing.JLabel hora;
    public javax.swing.JDesktopPane jDktInputData;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem10;
    public javax.swing.JMenuItem jMenuItem11;
    public javax.swing.JMenuItem jMenuItem12;
    public javax.swing.JMenuItem jMenuItem13;
    public javax.swing.JMenuItem jMenuItem14;
    public javax.swing.JMenuItem jMenuItem15;
    public javax.swing.JMenuItem jMenuItem16;
    public javax.swing.JMenuItem jMenuItem17;
    public javax.swing.JMenuItem jMenuItem18;
    public javax.swing.JMenuItem jMenuItem19;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JMenuItem jMenuItem7;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JPopupMenu.Separator jSeparator3;
    public javax.swing.JPopupMenu.Separator jSeparator4;
    public javax.swing.JPopupMenu.Separator jSeparator5;
    public javax.swing.JPopupMenu.Separator jSeparator6;
    public javax.swing.JPopupMenu.Separator jSeparator7;
    public javax.swing.JMenuBar menuBar;
    public javax.swing.JMenu menu_ajuda;
    public javax.swing.JMenu menu_cadastros;
    public javax.swing.JMenuItem menu_empresa;
    public javax.swing.JMenu menu_nfe;
    public javax.swing.JMenu menu_operacoes;
    public javax.swing.JMenuItem menu_sair;
    public javax.swing.JMenu menu_utilitarios;
    public javax.swing.JMenuItem mnUtilitarios_parametros;
    public javax.swing.JTextField tf_cnpj;
    public javax.swing.JTextField tf_ie;
    public javax.swing.JTextField tf_razaosocial;
    public javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables

    public void ThreeDLauncher(JInternalFrame frm, boolean maximum) {
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            this.setCursor(cursor);
            int width = (int) frm.getSize().getWidth();
            int height = (int) frm.getSize().getHeight();
            frm.setLocation((this.jDktInputData.getSize().width - width) / 2, (((this.jDktInputData.getSize().height) - height) / 2));
            jDktInputData.add(frm);
            frm.setVisible(true);
            frm.setMaximum(maximum);
            frm.toFront();
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
            cursor = Cursor.getDefaultCursor();
            this.setCursor(cursor);
        } catch (PropertyVetoException ex) {
            returnParams(new String[]{ex.getMessage()});
        }
    }

    public boolean VerifyClassisInstance() {
        boolean exist = false;
        for (Component c : jDktInputData.getComponents()) {
            if (c instanceof JInternalFrame) {
                try {
                    Class classe = c.getClass();
                    String cn = classe.toString().replace("class", "").trim();
                    exist = false;
                    if (Class.forName(cn).isInstance(c)) {
                        exist = true;
                        break;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NFe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return exist;
    }

    public String getDataAtual() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(new Date());
        return data;
    }

    public String getValidadeCertificado() {
        String data = null;
        try {
            env Env = new env();

            EMITENTE eMITENTE = new EMITENTE();
            eMITENTE = EMITENTEdaoJpa.findCodigo(Env.getEMITENTE_DEFAULT());
            Certificado certificado = CertificadoService.certificadoPfxBytes(eMITENTE.getEMP_CERTIFICADO(), eMITENTE.getEMP_SENHACERTIFICADO());

            //Certificado certificado = CertificadoService.certificadoPfx(Env.getpath_certificado() + Env.getname_certificatoemitente(), Env.getpassword_certificatoemitente());
            if (certificado.getDiasRestantes() <= 40) {
                datasis.setText("");
                JOptionPane.showMessageDialog(this, "<HTML><FONT FACE=Arial COLOR=\"#AA0000\">Faltam [ " + certificado.getDiasRestantes() + " ] dias, para o vencimento do certificado, procure o responsável !!!</FONT></HTML>", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            data = formata.StrZero(Integer.toString(certificado.getVencimento().getDayOfMonth()), 2)
                    + "/" + formata.StrZero(Integer.toString(certificado.getVencimento().getMonthValue()), 2)
                    + "/" + formata.StrZero(Integer.toString(certificado.getVencimento().getYear()), 2);
        } catch (CertificadoException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void returnParams(String[] paramrs) {
        if (paramrs[0] != null) {
            String error = "<html><font color=\"black\">" + StatusBar.getText() + "</font><font color=\"red\">" + " - [Error: " + paramrs[0] + "</font></html>";
            StatusBar.setText(error);
        }
    }

    public void getViewAmbiente() {
        String wModo = System.getProperty("contigenciaSVC").equals("TRUE") ? " - MODO CONTINGÊNCIA - (SVC) ATIVADO" : " - MODO NORMAL DE AUTORIZAÇÃO (SEFAZ)";

        if (System.getProperty("tpAmb").equals("2")) {
            jLabel2.setBorder(
                    javax.swing.BorderFactory.
                            createTitledBorder(
                                    javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1),
                                    "<html><table align=center><td color=\"red\" align=center>"
                                    + "<br>VERSÃO TESTE<br>"
                                    + "<br>Os documentos eletrônicos gerados por esse aplicativo não tem validade jurídica<br>"
                                    + "</td></table><table align=center><td color=\"white\" align=center>"
                                    + "<br>Emissor de Nota Fiscal Eletrônica (NF-e)" + wModo + "</br>"
                                    + "</td></table></html>",
                                    javax.swing.border.TitledBorder.CENTER,
                                    javax.swing.border.TitledBorder.ABOVE_BOTTOM, new java.awt.Font("Arial", 1, 14))); // NOI18N
            this.setTitle(getTitle + " - VERSÃO TESTE");
        }
        if (System.getProperty("tpAmb").equals("1")) {
            jLabel2.setBorder(
                    javax.swing.BorderFactory.
                            createTitledBorder(
                                    javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1),
                                    "<html><table align=top color=\"white\">"
                                    + "Emissor de Nota Fiscal Eletrônica (NF-e)" + wModo
                                    + "</table></html>",
                                    javax.swing.border.TitledBorder.CENTER,
                                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 1, 14))); // NOI18N
            this.setTitle(getTitle + " - VERSÃO PRODUÇÃO");
        }
    }
    private void verificarTokenECertificado(String banco, String token, File certificado, String certificadoProducao, String certificadoSandbox) {
        if (token == null || token.isEmpty() || !certificado.exists()) {
            String nomeCertificado = System.getProperty("tpAmb").equals("2")
                    ? certificadoSandbox
                    : certificadoProducao;

            JLabel msg = new JLabel(
                    "<html><div style='text-align:center; font-family:Arial; font-size:12px; color:red;'>"
                    + "Falha na conexão com a Internet, <br>"
                    + "falha na obtenção do token de acesso ao " + banco + " ou <br>"
                    + nomeCertificado + " não foi localizado.<br>"
                    + "Você poderá continuar usando a aplicação normalmente,<br>"
                    + "somente a conexão com o " + banco + " estará inoperante.<br>"
                    + "Por favor, procure o responsável."
                    + "</div></html>"
            );
            msg.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(this, msg, "Mensagem: TOKEN " + banco.toUpperCase(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarTokensEBancos() {
        String tokenBradesco = System.getProperty("bradesco.token");
        File arquivoBradescoCert = new File(System.getProperty("pathCertificadoBradescoPfx"));

        String tokenSantander = System.getProperty("santander.token");
        File arquivoSantanderCert = new File(System.getProperty("pathCertificadoSantanderPfx"));

        verificarTokenECertificado(
                "Bradesco",
                tokenBradesco,
                arquivoBradescoCert,
                CERTIFICADOBRADESCOPFXPRODUCAO.getUrl(),
                CERTIFICADOBRADESCOPFXDOSANDBOX.getUrl()
        );

        verificarTokenECertificado(
                "Santander",
                tokenSantander,
                arquivoSantanderCert,
                CERTIFICADOSANTANDERPFXPRODUCAO.getUrl(),
                CERTIFICADOSANTANDERPFXDOSANDBOX.getUrl()
        );
    }
}
