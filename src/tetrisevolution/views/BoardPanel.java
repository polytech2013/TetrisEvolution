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

    public static int SIZE = 20;

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
        System.out.println("hey");
        for (int i = 0; i < playingBoard.getRows(); i++) {
            for (int j = 0; j < playingBoard.getColumns(); j++) {
                if (playingBoard.getBlocks()[i][j] != null) {
                    g.setColor(playingBoard.getBlocks()[i][j].getColor());
                } else {
                    g.setColor(new Color(35, 35, 35));
                }
                g.fill3DRect(SIZE * j, SIZE * i, SIZE, SIZE, true);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("hey");
        super.paintComponents(g);
        draw(g);
    }

    @Override
    public int getWidth() {
        return playingBoard.getColumns() * SIZE;
    }

    @Override
    public int getHeight() {
        return playingBoard.getRows() * SIZE;
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
