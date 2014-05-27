package tetrisevolution.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import tetrisevolution.models.Board;
import tetrisevolution.models.GameState;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class BoardPanel extends JPanel implements Observer {

    private Board playingBoard;
    private JPanel gameOverPanel, pausePanel;
    private boolean hasPopup = false;

    public BoardPanel(Board board) {
        this.playingBoard = board;
        board.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        setFocusable(true);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        
        gameOverPanel = new GameOverPanel();
        add(gameOverPanel);
        
        pausePanel = new PausePanel();
        add(pausePanel);
    }

    public void draw(Graphics g) {
        // Board
        for (int i = 0; i < playingBoard.getRows(); i++) {
            for (int j = 0; j < playingBoard.getColumns(); j++) {
                BlockPanel.draw(playingBoard.getBlocks()[i][j], g, i, j);
            }
        }
        // Simulation
        if (playingBoard.getSimulated() != null) {
            for (Block block : playingBoard.getSimulated()) {
                BlockPanel.draw(block, g, block.getY(), block.getX());
            }
        }
        // Active stone
        Stone activeStone = playingBoard.getActive();
        for (Block block : activeStone.getBlocks()) {
            BlockPanel.draw(block, g, activeStone.getY() + block.getY(), activeStone.getX() + block.getX());
        }
    }
    
    public void showGameOver() {
        gameOverPanel.setVisible(true);
    }
    
    public void showPause() {
        pausePanel.setVisible(true);
    }
    
    public void clearPopups() {
        gameOverPanel.setVisible(false);
        pausePanel.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        draw(g);
        if (playingBoard.getState() == GameState.GAMEOVER) {
            showGameOver();
        }
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
        repaint();
    }

}
