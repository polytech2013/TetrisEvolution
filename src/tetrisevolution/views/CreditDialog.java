/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisevolution.views;

import java.awt.Dialog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author TD
 */
public class CreditDialog extends JDialog {

    private static final String IMAGE_URL = "/resource/background.png";

    public CreditDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Credit");
        
        try {
            add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(IMAGE_URL)))));
        } catch (IOException ex) {
            Logger.getLogger(CreditDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }
}
