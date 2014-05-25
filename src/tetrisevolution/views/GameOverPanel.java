package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.Board;
import tetrisevolution.models.GameState;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class GameOverPanel extends JPanel implements Observer {

    private final Board board;
    private JLabel gameOver;
    
    public GameOverPanel(Board board) {
        this.board = board;
        board.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        setPreferredSize(new Dimension(4 * BlockPanel.SIZE, 4 * BlockPanel.SIZE + 20));

        gameOver = new JLabel("Game Over", JLabel.CENTER);
        gameOver.setText("Game Over");
        gameOver.setForeground(Color.white);
        add(gameOver);
        this.setVisible(false);
    }

    private void draw(Graphics g) {

        if (board.getState() == GameState.GAMEOVER) {
            this.setVisible(true);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
