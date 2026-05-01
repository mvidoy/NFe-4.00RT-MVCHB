package com.frontend.util;

import com.frontend.views.cadastros.Cadastro_EMPRESA;
import com.frontend.views.main.FrmAjudaSobre;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

public final class Launcher {

    private FrmAjudaSobre FrmAjudaSobre;
    private Cadastro_EMPRESA Cadastro_EMPRESA;
    private static final String FrmAjudaSobre_frm = "FrmAjudaSobre_frm";
    private static final String Cadastro_EMPRESA_frm = "Cadastro_EMPRESA_frm";

    public void ThreeDLauncher(JInternalFrame frm, boolean maximum, JDesktopPane jDktInputData, boolean modal) {
        try {
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            frm.setCursor(cursor);
            System.setProperty("oldCharInterceptor", "");
            System.setProperty("newCharInterceptor", "");
            if (!modal) {
                Dimension newSize = new Dimension();
                newSize.setSize(frm.getSize().getWidth(), jDktInputData.getSize().getHeight() - 10);
                frm.setSize(newSize);
            }
            int width = (int) frm.getSize().getWidth();
            int height = (int) frm.getSize().getHeight();
            frm.setLocation((jDktInputData.getSize().width - width) / 2, (((jDktInputData.getSize().height) - height) / 2));
            jDktInputData.add(frm);
            frm.setVisible(true);
            frm.setMaximum(maximum);
            frm.toFront();
            frm.setSelected(true);
            cursor = Cursor.getDefaultCursor();
            frm.setCursor(cursor);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ThreeDLauncher(JDialog jDialog, boolean maximum, JDesktopPane jDktInputData, boolean modal) {
        System.setProperty("oldCharInterceptor", "");
        System.setProperty("newCharInterceptor", "");
        if (System.getProperty("visibleLauncher").equals("true")) {;
            jDialog.setVisible(true);
            jDialog.toFront();
        }
    }

    public void ThreeDLauncher(JInternalFrame internalFrame, boolean modal) {
        System.setProperty("oldCharInterceptor", "");
        System.setProperty("newCharInterceptor", "");
        JDialog dialog = new JDialog();
        dialog.setTitle(internalFrame != null ? internalFrame.getTitle() : "");
        dialog.setContentPane(internalFrame.getContentPane());
        dialog.setSize(internalFrame.getSize());
        Container parentContainer = internalFrame.getParent();
        if (parentContainer instanceof JDesktopPane) {
            JDesktopPane desktopPane = (JDesktopPane) parentContainer;
            desktopPane.remove(internalFrame);
        }
        dialog.setAlwaysOnTop(true); // Mantém o JDialog à frente do JInternalFrame
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack(); // Redimensiona o JDialog com base no novo conteúdo
        dialog.repaint(); // Redesenha o JDialog           
        dialog.setLocationRelativeTo(internalFrame); // Centraliza o JDialog em relação ao JFrame

        internalFrame.putClientProperty("dialogContainer", dialog);

        dialog.setVisible(true);
        dialog.toFront();
    }

    public boolean VerifyClassisInstance(JInternalFrame InternalFrame, JDesktopPane jDktInputData) {
        boolean exist = false;

        for (Component c : jDktInputData.getComponents()) {
            if (c instanceof JInternalFrame) {
                try {
                    Class classe = c.getClass();
                    String cn = classe.toString().replace("class", "").trim();
                    exist = false;
                    if (Class.forName(cn).isInstance(InternalFrame)) {
                        ((JInternalFrame) c).moveToFront();
                        exist = true;
                        break;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return exist;
    }

    public boolean VerifyClassisInstance(JDialog jDialog, JDesktopPane jDktInputData) {
        boolean exist = false;
        for (Component c : jDktInputData.getComponents()) {
            if (c instanceof JInternalFrame) {
                try {
                    Class classe = c.getClass();
                    String cn = classe.toString().replace("class", "").trim();
                    exist = false;
                    if (Class.forName(cn).isInstance(jDialog)) {
                        ((JInternalFrame) c).moveToFront();
                        exist = true;
                        break;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return exist;
    }
}
