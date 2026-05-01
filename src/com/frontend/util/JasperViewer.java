package com.md1.frontend.util;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

public class JasperViewer {

    public void Viewer(net.sf.jasperreports.view.JasperViewer viewer, JDialog jDialog, JDesktopPane jDesktopPane) {
        float zoomRatio = 1.00F;
        viewer.setZoomRatio(zoomRatio);
        int width = viewer.getWidth() + 20;
        int height = jDesktopPane.getHeight() - 60;

        JDialog relatorioDialog = new JDialog();
        relatorioDialog.setTitle(jDialog.getTitle());
        relatorioDialog.setBackground(jDialog.getBackground());

        relatorioDialog.setModal(true);
        relatorioDialog.setSize((int) (width), (int) (height * zoomRatio));
        relatorioDialog.setLocationRelativeTo(null);
        relatorioDialog.setResizable(false);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBorder(BorderFactory.createEmptyBorder());
        desktopPane.setBackground(jDialog.getBackground());
        desktopPane.setForeground(jDialog.getForeground());
        viewer.setVisible(true);
        desktopPane.add(viewer.getContentPane());
        relatorioDialog.setContentPane(desktopPane);

        desktopPane.revalidate();
        desktopPane.repaint();               
        
        viewer.setVisible(false);
        relatorioDialog.setVisible(true); 
        
        viewer.dispose();
    }    
    
}
