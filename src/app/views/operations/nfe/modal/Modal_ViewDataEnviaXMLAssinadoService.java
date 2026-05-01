package app.views.operations.nfe.modal;

import app.views.operations.nfe.NFe;
import app.views.operations.nfe.modal.services.NfeEnvioServices.NfeEnvioService;
import app.views.operations.nfe.modal.services.NfeEnvioServices.NfePersistenciaService;
import app.views.operations.nfe.modal.services.NfeEnvioServices.NfePosProcessamentoService;
import app.views.operations.nfe.modal.services.NfeEnvioServices.ResultadoEnvioService;
import com.backend.dtos.NFECBdto;
import com.frontend.config.env;
import static com.frontend.util.BackupUtil.criarBackupPrevCaixa;
import com.frontend.util.ProcessaTokenUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import util.AlignTableHeaderCenter;

public class Modal_ViewDataEnviaXMLAssinadoService extends javax.swing.JInternalFrame {

    private static final Logger LOG = Logger.getLogger(Modal_ViewDataEnviaXMLAssinadoService.class.getName());

    // Mantém compatibilidade
    private NFe veioDo_NFe_frm;
    private final ArrayList<String> tListaNNF = new ArrayList<>();
    private int[] tSelectedRows;

    private String gAno = "";
    private String gSerie = "";
    private String gNNF = "";

    private DefaultTableModel RETORNOtableModel = new DefaultTableModel();

    private final env Env = new env();
    private String planPrevcaixa = null;

    private final ProcessaTokenUtil ProcessaToken = new ProcessaTokenUtil(); //05/02/2026

    // IMPORTANTE: não confundir com jDesktopPane2 do formulário (painel interno)
    private final JDesktopPane desktopParent;

    public Modal_ViewDataEnviaXMLAssinadoService(
            JDesktopPane jDesktop,
            JInternalFrame jFrame,
            String wAno,
            String wSerie,
            String wNFe,
            ArrayList<String> wListaNNF,
            int[] SelectedRows) {

        this.desktopParent = jDesktop;

        gAno = wAno;
        gSerie = wSerie;
        gNNF = wNFe;

        this.planPrevcaixa = "\\\\" + ((isProducao()
                && Env.getHOST() != null
                && !Env.getHOST().trim().isEmpty())
                ? Env.getHOST().trim()
                : "192.168.0.5")
                + "\\geral\\prevcaixa.xls";

        tSelectedRows = SelectedRows;

        if (wListaNNF != null) {
            Iterator it = wListaNNF.iterator();
            while (it.hasNext()) {
                tListaNNF.add(String.valueOf(it.next()));
            }
        }
        initComponents();
        botao_Enviar.setEnabled(true);
        System.setProperty("XMLretorno", "");

        configurarTabela();

        // cursor default no desktop principal
        if (desktopParent != null) {
            desktopParent.setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean isProducao() {
        return "1".equals(System.getProperty("tpAmb"));
    }

    private void configurarTabela() {
        RETORNOtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        TableCellRenderer aligntableheadercenter = new AlignTableHeaderCenter();
        TableCellRenderer wCol3 = new ColorirLinhaNFeSituacao();
        TableCellRenderer tcr = new Imagem();

        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TableColumn column = RETORNOtable.getColumnModel().getColumn(0);
        column.setCellRenderer(tcr);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(1).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(2).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(100);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(esquerda);
        RETORNOtable.getColumnModel().getColumn(3).setCellRenderer(wCol3);

        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(1535);
        //RETORNOtable.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(esquerda);
        //RETORNOtable.getColumnModel().getColumn(4).setCellRenderer(wCol3);
        RETORNOtable.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane13 = new javax.swing.JScrollPane();
        RETORNOtable = new javax.swing.JTable();

        jLabel2 = new javax.swing.JLabel();
        botao_Enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        botao_sair = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setForeground(java.awt.Color.white);
        setResizable(true);
        setTitle("Envia Nota Fiscal Eletrônica - Revisão: 14 - Data da Última Revisão: 31/03/2026 - Data da Elaboração: 26/07/2016");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icone_nfe.gif"))); // NOI18N
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jDesktopPane2.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envia Nota Fiscal Eletrônica", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Resumo das Operações:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(51, 153, 0));
        jProgressBar1.setOpaque(true);

        RETORNOtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Ano", "Série", "Número", "Resultado do Envio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RETORNOtable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        RETORNOtable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RETORNOtable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        RETORNOtable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        RETORNOtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(RETORNOtable);
        if (RETORNOtable.getColumnModel().getColumnCount() > 0) {
            RETORNOtable.getColumnModel().getColumn(0).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(1).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(2).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(3).setResizable(false);
            RETORNOtable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        botao_Enviar.setText("Enviar");
        botao_Enviar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botao_EnviarMouseMoved(evt);
            }
        });
        botao_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_EnviarActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 0, 51));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setText("Atenção: Em caso de erro não catalogado, desconhecido, rejeição, durante o processo de retorno ou indisponibilidade dos serviços, você pode: consultar as informações no site: https://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx ou buscar ajuda do seu contador.");
        jScrollPane1.setViewportView(jTextArea1);

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jProgressBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(botao_Enviar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botao_Enviar))
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(3, 3, 3))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_Enviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"))); // NOI18N
        jLabel5.setText("Erro");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"))); // NOI18N
        jLabel6.setText("Sucesso");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/alerta.jpg"))); // NOI18N
        jLabel7.setText("Alerta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDesktopPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(botao_sair)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jDesktopPane2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botao_sair)
                .addContainerGap())
        );

        setBounds(0, 0, 923, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void botao_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_sairActionPerformed
        doDefaultCloseAction();
    }//GEN-LAST:event_botao_sairActionPerformed

    private void botao_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_EnviarActionPerformed
        try {

            /*
            if (sistemaVB6Aberto("sistema_modulo_99rt.exe")) {
                JOptionPane.showMessageDialog(
                        this,
                        "O sistema Modulo 01 está aberto.\nFeche o sistema antes de continuar.",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
             */
            ProcessaToken.processaTokenBradesco();
            ProcessaToken.processaTokenSantander();

            if (isProducao()) {
                criarBackupPrevCaixa(planPrevcaixa);
            }

            new Process().start();

        } catch (IOException ex) {
            Logger.getLogger(Modal_ViewDataEnviaXMLAssinadoService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Modal_ViewDataEnviaXMLAssinadoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botao_EnviarActionPerformed

    private void botao_EnviarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_EnviarMouseMoved
        botao_Enviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_EnviarMouseMoved

    private void botao_sairMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_sairMouseMoved
        botao_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_botao_sairMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RETORNOtable;
    private javax.swing.JButton botao_Enviar;
    private javax.swing.JButton botao_sair;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    // Mantém compatibilidade com quem chama
    public void enviaRegistro(NFe veioDo1, String wVar1) {
        this.veioDo_NFe_frm = veioDo1;
    }

    public class Process extends Thread {

        @Override
        public void run() {

            // ? Pega o model IMEDIATAMENTE (fora do invokeLater)
            RETORNOtableModel = (DefaultTableModel) RETORNOtable.getModel();

            SwingUtilities.invokeLater(() -> {
                jLabel1.setText("Enviando Nota Fiscal Eletrônica.");
                jLabel2.setText("Por favor, aguarde.");
                botao_Enviar.setEnabled(false);

                Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                jDesktopPane2.setCursor(cursor);
                if (desktopParent != null) {
                    desktopParent.setCursor(cursor);
                }
            });

            Consumer<Object[]> tableRowListener = criarTableRowListener();
            Consumer<String> erroListener = criarErroListener(tableRowListener);

            NfeEnvioService envioService = new NfeEnvioService();
            callSetContextIfExists(envioService, gAno, gSerie);
            envioService.setTableRowListener(tableRowListener);

            NfePersistenciaService persistenciaService = new NfePersistenciaService();
            callSetContextIfExists(persistenciaService, gAno, gSerie);
            persistenciaService.setTableRowListener(tableRowListener);

            NfePosProcessamentoService posService = new NfePosProcessamentoService();
            posService.setTableRowListener(tableRowListener);

            Iterator itListaNNF = tListaNNF.iterator();

            int total = tListaNNF.size();

            SwingUtilities.invokeLater(() -> {
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setMinimum(0);
                jProgressBar1.setMaximum(total);
                jProgressBar1.setValue(0);
            });

            int idx = 0;

            while (itListaNNF.hasNext()) {
                try {
                    gNNF = String.valueOf(itListaNNF.next());

                    int nt = (tSelectedRows != null && idx < tSelectedRows.length)
                            ? tSelectedRows[idx]
                            : idx;

                    // 1) ENVIO
                    ResultadoEnvioService resultado = envioService.enviar(gNNF);

                    // 2) PERSISTÊNCIA
                    persistenciaService.persistir(gNNF, resultado);

                    // 3) PÓS-PROCESSAMENTO
                    NFECBdto nfecb = posService.processar(gAno, gSerie, gNNF, resultado);

                    // 4) CALLBACK (igual legado)
                    callbackRetornaRegistro(resultado, nt, nfecb);

                } catch (Exception ex) {

                    LOG.log(Level.SEVERE, null, ex);
                    String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
                    erroListener.accept(msg);

                    int nt = (tSelectedRows != null && idx < tSelectedRows.length)
                            ? tSelectedRows[idx]
                            : idx;

                    ResultadoEnvioService fake = new ResultadoEnvioService();
                    fake.setRejeitada(true);
                    fake.setDataAutorizacao("");

                    callbackRetornaRegistro(fake, nt, null);

                } finally {
                    idx++;
                    int progresso = idx;

                    SwingUtilities.invokeLater(() -> {
                        jProgressBar1.setValue(progresso);
                    });
                }
            }

            SwingUtilities.invokeLater(() -> {
                jLabel2.setText("Processo Finalizado.");
                jLabel1.setText("");
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setValue(total);

                Cursor cursor = Cursor.getDefaultCursor();
                jDesktopPane2.setCursor(cursor);
                if (desktopParent != null) {
                    desktopParent.setCursor(cursor);
                }

                botao_Enviar.setEnabled(false);
            });
        }
    }

    private Consumer<Object[]> criarTableRowListener() {
        return row -> SwingUtilities.invokeLater(() -> {

            RETORNOtableModel.addRow(row);

            int lastRow = RETORNOtableModel.getRowCount() - 1;

            if (lastRow >= 0) {
                RETORNOtable.scrollRectToVisible(
                        RETORNOtable.getCellRect(lastRow, 0, true)
                );
            }
        });
    }

    private Consumer<String> criarErroListener(Consumer<Object[]> tableRowListener) {
        return msg -> {
            if (msg == null) {
                msg = "";
            }
            Scanner scanner = new Scanner(msg);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tableRowListener.accept(new Object[]{"", gAno, gSerie, gNNF, "Erro-" + line});
            }
            scanner.close();
        };
    }

    private static void callSetContextIfExists(Object service, String ano, String serie) {
        try {
            Method m = service.getClass().getMethod("setContext", String.class, String.class);
            m.invoke(service, ano, serie);
        } catch (NoSuchMethodException ignore) {
            // ok: service não tem contexto
        } catch (Exception ex) {
            Logger.getLogger(Modal_ViewDataEnviaXMLAssinadoService.class.getName()).log(Level.WARNING, "Falha ao chamar setContext()", ex);
        }
    }

    public class ColorirLinhaNFeSituacao extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Color corVerde = new Color(51, 153, 0);
            String text = String.valueOf(table.getValueAt(row, 4));

            if (text.contains("100-") || text.contains("103-") || text.contains("105-") || text.contains("104-") || text.contains("sucesso")) {
                result.setForeground(corVerde);
            } else {
                result.setForeground(Color.RED);
            }
            result.setFont(new Font("Arial", Font.BOLD, 11));
            return result;
        }
    }

    class Imagem extends JLabel implements TableCellRenderer {

        public Imagem() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            ImageIcon imagem;
            String text = String.valueOf(table.getValueAt(row, 4));

            if (text.contains("100-") || text.contains("103-") || text.contains("105-") || text.contains("104-") || text.contains("sucesso")) {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/sucesso.jpg"));
            } else {
                imagem = new javax.swing.ImageIcon(getClass().getResource("/assets/erro.png"));
            }

            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setIcon(imagem);
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    private String formatarDhRecbto(String dhRecbtoRaw) {
        if (dhRecbtoRaw == null || dhRecbtoRaw.trim().isEmpty()) {
            return "";
        }
        try {
            // mesmo padrão do legado:
            // "2026-02-26T10:20:30-03:00" -> "2026-02-26 10:20:30"
            String normalizado = dhRecbtoRaw.replace("T", " ").replace("-03:00", "");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(normalizado);
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        } catch (Exception e) {
            // fallback: não quebra o processo só por causa da data
            return dhRecbtoRaw;
        }
    }

    private void callbackRetornaRegistro(ResultadoEnvioService resultado, int nt, NFECBdto nfecb) {

        if (veioDo_NFe_frm == null || resultado == null) {
            return;
        }

        String statusTela = "";
        String dataFormatada = "";

        if (resultado.isAutorizada()) {
            statusTela = "Autorizada";
            dataFormatada = formatarDhRecbto(resultado.getDataAutorizacao());
        } else if (resultado.isRejeitada()) {
            statusTela = "Rejeitada";
        }

        final String fStatus = statusTela;
        final String fData = dataFormatada;
        final int fNt = nt;
        final NFECBdto fNfecb = nfecb;

        javax.swing.SwingUtilities.invokeLater(() -> {
            veioDo_NFe_frm.retornaRegistro(fStatus, fData, fNt, null, fNfecb);
        });
    }

    class MultiLineCellRenderer extends javax.swing.JTextArea implements TableCellRenderer {

        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(new Font("Arial", Font.BOLD, 11));
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            setText(value == null ? "" : value.toString());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());

                String text = getText();
                Color corVerde = new Color(51, 153, 0);

                if (text.contains("100-") || text.contains("103-")
                        || text.contains("105-") || text.contains("104-")
                        || text.contains("sucesso")) {
                    setForeground(corVerde);
                } else {
                    setForeground(Color.RED);
                }
            }

            // Ajusta altura da linha automaticamente
            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            int altura = getPreferredSize().height;

            if (table.getRowHeight(row) != altura) {
                table.setRowHeight(row, altura);
            }

            return this;
        }
    }

    public boolean sistemaVB6Aberto(String nomeProcesso) {

        try {

            String comando = "wmic process where \"name='" + nomeProcesso + "'\" get name";

            java.lang.Process processo = Runtime.getRuntime().exec(comando);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(processo.getInputStream()));

            String linha;

            while ((linha = reader.readLine()) != null) {

                if (linha.toLowerCase().contains(nomeProcesso.toLowerCase())) {
                    return true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
