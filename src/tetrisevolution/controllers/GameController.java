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

    private TetrisFrame frame;
    private Board playingBoard;
    
    private MusicHandler music;
    private boolean sonOnOff;

    public GameController() {
        playingBoard = new Board(20, 10);
        playingBoard.newGame();

        sonOnOff = true;

        frame = new TetrisFrame(playingBoard);
        frame.getBoardPanel().addKeyListener(new KeyboardListener());
        frame.getMenu().getMenuItemPlaySon().addActionListener(new PlaySonActionListener());
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
            playingBoard.getGameTimer().stop();
            playingBoard.setState(GameState.PAUSED);
            frame.getBoardPanel().showPause();
            creditDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    playingBoard.getGameTimer().start();
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
            playingBoard.getGameTimer().stop();
            playingBoard.setState(GameState.PAUSED);
            frame.getBoardPanel().showPause();
            commandDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    playingBoard.getGameTimer().start();
                    playingBoard.setState(GameState.PLAYING);
                    frame.getBoardPanel().clearPopups();
                }
            });
        }
    }

    private class PlaySonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (sonOnOff) {
                music.stop();
                sonOnOff = false;
            } else {
                music.loop();
                sonOnOff = true;
            }
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
            playingBoard.getGameTimer().setDelay(Board.INITIAL_DELAY);
            playingBoard.getGameTimer().restart();
        }
    }

    private class KeyboardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent ke) {
            Stone active = playingBoard.getActive();
            if (Konami.checkKonami(ke.getKeyCode())) {
                if (playingBoard.getState() == GameState.KONAMI) {
                    playingBoard.deactivateKonami();
                } else {
                    playingBoard.activateKonami();
                }
            }
            if (playingBoard.getState() != GameState.KONAMI) {
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
                            playingBoard.getGameTimer().start();
                            break;
                        case KeyEvent.VK_ENTER:
                            playingBoard.holdStone();
                            break;
                        case KeyEvent.VK_SPACE:
                            if (playingBoard.getActive() instanceof StoneBonus) {
                                StoneBonus bonus = (StoneBonus) playingBoard.getActive();
                                bonus.unlock();
                                playingBoard.setState(GameState.BONUS);
                            } else {
                                while (playingBoard.dropStone(false)) {
                                    playingBoard.dropPoints(2);
                                }
                            }
                            break;
                        case KeyEvent.VK_P:
                            if (playingBoard.getGameTimer().isRunning()) {
                                playingBoard.getGameTimer().stop();
                                playingBoard.setState(GameState.PAUSED);
                                frame.getBoardPanel().showPause();
                            } else {
                                playingBoard.getGameTimer().start();
                                playingBoard.setState(GameState.PLAYING);
                                frame.getBoardPanel().clearPopups();
                            }
                            break;
                        case KeyEvent.VK_R:
                            frame.getBoardPanel().clearPopups();
                            playingBoard.newGame();
                            playingBoard.getGameTimer().setDelay(Board.INITIAL_DELAY);
                            playingBoard.getGameTimer().restart();
                            break;
                        case KeyEvent.VK_SHIFT:
                            playingBoard.playBonus();
                            break;
                    }
                } catch (InvalidActivityException e) {
                    //System.out.println("Unauthorised action");
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }

    }
}
