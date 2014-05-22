package tetrisevolution.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import tetrisevolution.models.Board;
import tetrisevolution.models.stones.Stone;
import tetrisevolution.views.TetrisFrame;

/**
 *
 * @author Mario
 */
public class GameController {

    private TetrisFrame frame;
    private Board playingBoard;
    private Timer gameTimer;

    public GameController() {
        playingBoard = new Board(20, 10);

        frame = new TetrisFrame(playingBoard);

        gameTimer = new Timer(500, new GameListener());
        gameTimer.start();

        frame.getBoardPanel().addKeyListener(new KeyboardListener());
    }

    private class KeyboardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent ke) {
            Stone active = playingBoard.getActive();
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    playingBoard.moveStone(active.getX() - 1, active.getY());
                    break;
                case KeyEvent.VK_RIGHT:
                    playingBoard.moveStone(active.getX() + 1, active.getY());
                    break;
                case KeyEvent.VK_UP:
                    playingBoard.rotateStone();
                    break;
                case KeyEvent.VK_DOWN:
                    playingBoard.moveStone(active.getX(), active.getY() + 1);
                    gameTimer.restart();
                   break;
                case KeyEvent.VK_SPACE:
                    // Hard drop

            }
        }

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }

    }

    private class GameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Stone active = playingBoard.getActive();
            playingBoard.moveStone(active.getX(), active.getY() + 1);
        }

    }

}
