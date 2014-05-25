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

    private static final int INITIAL_DELAY = 500;

    private TetrisFrame frame;
    private Board playingBoard;
    private Timer gameTimer;
    private int delay;

    public GameController() {
        playingBoard = new Board(20, 10);
        playingBoard.newGame();

        frame = new TetrisFrame(playingBoard);

        gameTimer = new Timer(INITIAL_DELAY, new GameListener());
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
                        gameTimer.start();
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
                            playingBoard.setState(GameState.PAUSED);
                            frame.getBoardPanel().showPause();
                        } else {
                            gameTimer.start();
                            playingBoard.setState(GameState.PLAYING);
                            frame.getBoardPanel().clearPopups();
                        }
                        break;
                    case KeyEvent.VK_R:
                        frame.getBoardPanel().clearPopups();
                        playingBoard.newGame();
                        gameTimer.restart();
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
                gameTimer.stop();
                frame.getBoardPanel().showGameOver();
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
