package com.md1.frontend.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPanelWithBackground extends JPanel {

    private BufferedImage backgroundImage;
    private int imageWidth;
    private int imageHeight;

    public JPanelWithBackground(URL imageUrl, int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
        try {
            if (imageUrl != null) {
                backgroundImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("URL da imagem é inválida.");
            }
        } catch (IOException ex) {
            Logger.getLogger(JPanelWithBackground.class.getName()).log(Level.SEVERE, "Erro ao carregar imagem do JAR.", ex);
        }
    }

    public JPanelWithBackground(String externalPath, int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
        try {
            File imageFile = new File(externalPath);
            if (imageFile.exists()) {
                backgroundImage = ImageIO.read(imageFile);
            } else {
                throw new IOException("Imagem não encontrada no caminho externo: " + externalPath);
            }
        } catch (IOException ex) {
            Logger.getLogger(JPanelWithBackground.class.getName()).log(Level.SEVERE, "Erro ao carregar imagem externa.", ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (getWidth() - imageWidth) / 2;
            int y = (getHeight() - imageHeight) / 2;
            g.drawImage(backgroundImage, x, y, imageWidth, imageHeight, this);
        }
    }
}
