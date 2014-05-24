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
public class HoldStonePanel extends JPanel implements Observer {

    private final Board board;

    public HoldStonePanel(Board board) {
        this.board = board;
        board.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(35, 35, 35));
        setPreferredSize(new Dimension(4 * BlockPanel.SIZE, 4 * BlockPanel.SIZE + 20));
        
        JLabel nextLabel = new JLabel("Hold");
        nextLabel.setForeground(Color.white);
        add(nextLabel);
    }

    private void draw(Graphics g) {
        Stone holdStone = board.getHold();
        
        if (holdStone == null) {
            return;
        }
        
        int size = BlockPanel.SIZE;

        double offX, offY;
        if (holdStone.getSize() == 4) {
            offX = 0;
            offY = 1.5;
        } else if (holdStone.getSize() == 3) {
            offY = 1;
            offX = 0.5;
        } else {
            offX = 1;
            offY = 1;
        }
        for (Block block : holdStone.getBlocks()) {
            BlockPanel.draw(block, g, block.getY() + offY + 0.5, block.getX() + offX);
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
