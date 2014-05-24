package tetrisevolution.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.activity.InvalidActivityException;
import javax.swing.Timer;
import tetrisevolution.models.Board;
import tetrisevolution.models.GameState;
import tetrisevolution.models.Konami;
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
    private int delay;

    public GameController() {
        playingBoard = new Board(20, 10);
        playingBoard.newGame();

        frame = new TetrisFrame(playingBoard);

        gameTimer = new Timer(500, new GameListener());
        gameTimer.start();

        frame.getBoardPanel().addKeyListener(new KeyboardListener());
    }

    private class KeyboardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent ke) {
            Stone active = playingBoard.getActive();
            if (Konami.checkKonami(ke.getKeyCode())) {
                System.out.println("Konami code!");
            }
            try {
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
                    playingBoard.dropPoints(1);
                    gameTimer.restart();
                    break;
                case KeyEvent.VK_ENTER:
                    playingBoard.holdStone();
                    break;
                case KeyEvent.VK_SPACE:
                    while (playingBoard.dropStone()) {
                        playingBoard.dropPoints(2);
                    }
                    break;
                case KeyEvent.VK_P:
                    if (gameTimer.isRunning()) {
                        gameTimer.stop();
                        playingBoard.setState(GameState.PAUSE);
                    } else {
                        gameTimer.start();
                        playingBoard.setState(GameState.PLAYING);
                    }
                    break;
            }
            } catch (InvalidActivityException e) {
                System.out.println("Unauthorised action");
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
            if (playingBoard.getState() == GameState.GAMEOVER) {

                playingBoard.newGame();
                gameTimer.restart();
            } else {
                Stone active = playingBoard.getActive();
                try {
                playingBoard.dropStone();
                } catch (InvalidActivityException e) {
                    System.out.println("Unauthorised action");
                }
                if (playingBoard.getLines() == playingBoard.getGoal()) {
                    playingBoard.setLevel(playingBoard.getLevel() + 1);
                    gameTimer.setDelay(gameTimer.getDelay() - 20);
                }
            }
        }

    }

}
