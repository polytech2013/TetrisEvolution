package tetrisevolution.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.activity.InvalidActivityException;
import javax.swing.JDialog;
import javax.swing.Timer;
import tetrisevolution.helpers.MusicHandler;
import tetrisevolution.models.Board;
import tetrisevolution.models.GameState;
import tetrisevolution.models.HighscoreManager;
import tetrisevolution.models.Konami;
import tetrisevolution.models.stones.Stone;
import tetrisevolution.models.stones.StoneBonus;
import tetrisevolution.views.CommandDialog;
import tetrisevolution.views.CreditDialog;
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
    private MusicHandler music;

    public GameController() {
        playingBoard = new Board(20, 10);
        playingBoard.newGame();

        frame = new TetrisFrame(playingBoard);

        delay = INITIAL_DELAY;
        gameTimer = new Timer(INITIAL_DELAY, new GameListener());
        gameTimer.start();

        frame.getBoardPanel().addKeyListener(new KeyboardListener());
        frame.getMenu().getMenuItemNewGame().addActionListener(new NewGameActionListener());
        frame.getMenu().getMenuItemExit().addActionListener(new ExitActionListener());
        frame.getMenu().getMenuItemCommand().addActionListener(new CommandActionListener());
        frame.getMenu().getMenuItemCredit().addActionListener(new CreditActionListener());

        music = new MusicHandler("src/tetrisevolution/resources/Tetris.wav");
        music.loop();
    }

    private class CreditActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog creditDialog = new CreditDialog(frame, false);
            creditDialog.setVisible(true);
            gameTimer.stop();
            playingBoard.setState(GameState.PAUSED);
            frame.getBoardPanel().showPause();
            creditDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gameTimer.start();
                    playingBoard.setState(GameState.PLAYING);
                    frame.getBoardPanel().clearPopups();
                }
            });
        }
    }

    private class CommandActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog commandDialog = new CommandDialog(frame, false);
            commandDialog.setVisible(true);
            gameTimer.stop();
            playingBoard.setState(GameState.PAUSED);
            frame.getBoardPanel().showPause();
            commandDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gameTimer.start();
                    playingBoard.setState(GameState.PLAYING);
                    frame.getBoardPanel().clearPopups();
                }
            });
        }
    }

    private class ExitActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
            frame.dispatchEvent(windowClosing);
        }
    }

    private class NewGameActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getBoardPanel().clearPopups();
            playingBoard.newGame();
            gameTimer.restart();
        }
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
                    case KeyEvent.VK_A:
                        // AI
                        break;
                    case KeyEvent.VK_LEFT:
                        playingBoard.moveStone(active.getX() - 1, active.getY());
                        break;
                    case KeyEvent.VK_RIGHT:
                        playingBoard.moveStone(active.getX() + 1, active.getY());
                        break;
                    case KeyEvent.VK_UP:
                        playingBoard.rotateRightStone();
                        break;
                    case KeyEvent.VK_C:
                        playingBoard.rotateRightStone();
                        break;
                    case KeyEvent.VK_V:
                        playingBoard.rotateLeftStone();
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
                        if (playingBoard.getActive() instanceof StoneBonus) {
                            StoneBonus bonus = (StoneBonus) playingBoard.getActive();
                            bonus.unlock();
                            playingBoard.setState(GameState.BONUS);
                            delay = gameTimer.getDelay();
                            gameTimer.setDelay(150);
                        } else {
                            while (playingBoard.dropStone()) {
                                playingBoard.dropPoints(2);
                            }
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
                    case KeyEvent.VK_SHIFT:
                        playingBoard.playBonus();
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
                music.stop();
                HighscoreManager hm = new HighscoreManager();
                hm.addScore("BeonLive", playingBoard.getScore());
                System.out.print(hm.getScores().get(0).toString());
                gameTimer.stop();
                frame.getBoardPanel().showGameOver();
            } else {
                if (playingBoard.getState() == GameState.PLAYING) {
                    gameTimer.setDelay(delay);
                }
                try {
                    playingBoard.dropStone();
                } catch (InvalidActivityException e) {
                    System.out.println("Unauthorised action");
                }
                if (playingBoard.getLines() == playingBoard.getGoal()) {
                    playingBoard.levelUp();
                    if (gameTimer.getDelay() > 100) {
                        gameTimer.setDelay(gameTimer.getDelay() - 50);
                    }
                }
            }
        }

    }

}
