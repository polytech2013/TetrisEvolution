package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.Board;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class NextStonePanel extends JPanel {

    private final Board board;

    public NextStonePanel(Board board) {
        this.board = board;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        setPreferredSize(new Dimension(80, 80));
    }

    private void draw(Graphics g) {
        Stone nextStone = board.getNext();
        int size = BlockPanel.SIZE;

        double offX, offY;
        if (nextStone.getSize() == 4) {
            offX = 0;
            offY = 0.5;
        } else if (nextStone.getSize() == 3) {
            offY = 1;
            offX = 0.5;
        } else {
            offX = 1;
            offY = 1;
        }
        for (Block block : nextStone.getBlocks()) {
            BlockPanel.draw(block, g, block.getX() + offX, block.getY() + offY);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

}
