/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Librerias;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author Derwin
 */
public class ImagenCocina implements Border{

    BufferedImage fondo;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public ImagenCocina() throws IOException{
        try {
            URL url = new URL(getClass().getResource("/QuickMeal/Imagenes/mantel.jpg").toString());
            fondo = ImageIO.read(url);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImagenCocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(fondo, 0, 0, ancho, alto, null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
