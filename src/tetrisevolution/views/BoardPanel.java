package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class BoardPanel extends JPanel implements Observer {

    private Board playingBoard;

    public BoardPanel(Board board) {
        this.playingBoard = board;
        initComponents();
    }

    private void initComponents() {
        setFocusable(true);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    public void draw(Graphics g) {
        int size = BlockPanel.SIZE;
        for (int i = 0; i < playingBoard.getRows(); i++) {
            for (int j = 0; j < playingBoard.getColumns(); j++) {
                BlockPanel.draw(playingBoard.getBlocks()[i][j], g, i, j);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    @Override
    public int getWidth() {
        return playingBoard.getColumns() * BlockPanel.SIZE;
    }

    @Override
    public int getHeight() {
        return playingBoard.getRows() * BlockPanel.SIZE;
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
