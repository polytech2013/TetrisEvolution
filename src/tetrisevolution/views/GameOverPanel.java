package tetrisevolution.views;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mario
 */
public class GameOverPanel extends JPanel {

    private JLabel gameOver;
    
    public GameOverPanel() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        gameOver = new JLabel("Game Over", JLabel.CENTER);
        gameOver.setForeground(Color.white);
        add(gameOver);
        
        // Hide by default
        this.setVisible(false);
    }
}
