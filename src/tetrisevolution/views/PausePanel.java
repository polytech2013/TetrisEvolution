package tetrisevolution.views;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mario
 */
public class PausePanel extends JPanel {

    private JLabel pause;
    
    public PausePanel() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        pause = new JLabel("Pause", JLabel.CENTER);
        pause.setFont(new java.awt.Font("Tahoma", 0, 20));
        pause.setForeground(Color.white);
        add(pause);
        
        // Hide by default
        this.setVisible(false);
    }
}
