package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.Board;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class BonusPanel extends JPanel implements Observer {

    private final Board board;

    public BonusPanel(Board board) {
        this.board = board;
        board.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        setPreferredSize(new Dimension(4 * BlockPanel.SIZE, 4 * BlockPanel.SIZE + 20));

        JLabel levelText = new JLabel("Bonus");
        levelText.setForeground(Color.white);
        levelText.setHorizontalAlignment(JLabel.CENTER);
        add(levelText);
    }

    private void draw(Graphics g) {
        double offY = 2;
        double offX = 0.5;
        for (Stone stone : board.getBonuses()) {
            for (Block block : stone.getBlocks()) {
                BlockPanel.draw(block, g, block.getY() + offY, block.getX() + offX);
            }
            offY += 5;
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
