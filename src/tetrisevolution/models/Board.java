package tetrisevolution.models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import javax.activity.InvalidActivityException;
import tetrisevolution.helpers.MusicHandler;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.stones.Stone;
import tetrisevolution.models.stones.StoneBonus;

/**
 *
 * @author Mario
 */
public class Board extends Observable {

    private int rows, columns;
    private Block[][] blocks;
    private int score, level, lines, goal;
    private Stone active, next, hold, tmp;
    private Block[] simulated;
    private GameState state;
    private ArrayList<Stone> bonuses;

    private int konamiPosition = 0;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void newGame() {
        score = 0;
        lines = 0;
        goal = 0;
        setLevel(1);
        hold = null;
        this.blocks = new Block[rows][columns];
        active = StoneFactory.generateRandom();
        startStone(active);
        next = StoneFactory.generateRandom();
        state = GameState.PLAYING;
        bonuses = new ArrayList<>(2);
        bonuses.add(StoneFactory.generateBonus(this));
    }

    public final void startStone(Stone startStone) {
        if (state == GameState.KONAMI) {
            startStone.setX(konamiPosition);
            konamiPosition = (konamiPosition + 2) % 10;
        } else {
            startStone.setX(columns / 2 - active.getSize() / 2);
            startStone.setY(-1);
        }
        if (checkCollision()) {
            MusicHandler.playGameOverSound();
            state = GameState.GAMEOVER;
        }
    }

    public void nextStone() {
        active = next;
        if (state == GameState.KONAMI) {
            next = StoneFactory.generateCube();
        } else {
            next = StoneFactory.generateRandom();
        }
        startStone(active);
    }

    public void holdStone() {

        if (hold == null) {
            hold = active;
            hold.setOrientation(0);
            this.nextStone();
        } else {
            tmp = active;
            active = hold;
            active.setX(tmp.getX());
            active.setY(tmp.getY());
            if (checkCollision()) {
                hold = active;
                active = tmp;
            } else {
                hold = tmp;
                hold.setOrientation(0);
            }
        }
    }

    public boolean checkCollision() {
        int x, y;
        for (Block block : active.getBlocks()) {
            x = active.getX() + block.getX();
            y = active.getY() + block.getY();

            if (x >= columns) {
                return true;
            } else if (y >= rows) {
                return true;
            } else if (x < 0) {
                return true;
            } else if (y >= 0 && blocks[y][x] != null) {
                return true;
            }
        }
        return false;
    }

    public void stoneToBlocks() {
        MusicHandler.playDropSound();
        active.undoMove();
        synchronized (active) {
            for (Block block : active.getBlocks()) {
                if (active.getY() + block.getY() >= 0) {
                    blocks[active.getY() + block.getY()][active.getX() + block.getX()] = block;
                }
            }
        }
        if (lines >= 18) {
            System.out.println("plop");
        }
        clearFullRows();
    }

    public void clearFullRows() {
        boolean fullRow;
        int n = 0;
        for (int i = 0; i < rows; i++) {
            fullRow = true;
            for (int j = 0; j < columns; j++) {
                if (blocks[i][j] == null) {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) {
                lines++;
                n++;
                for (int k = i; k > 0; k--) {
                    blocks[k] = blocks[k - 1];
                }
            }
        }
        if (n > 0) {
            MusicHandler.playLineSound();
            linePoints(n);
        }
    }

    public void linePoints(int n) {
        switch (n) {
            case 1:
                score += 40 * level;
                break;
            case 2:
                score += 100 * level;
                break;
            case 3:
                score += 300 * level;
                break;
            case 4:
                score += 1200 * level;
                break;
        }
    }

    public void dropPoints(int points) {
        score += points;
    }

    public boolean dropStone(boolean skipCheck) throws InvalidActivityException {
        if (!skipCheck && state != GameState.KONAMI) {
            checkState();
        }
        simulated = null;
        synchronized (active) {
            active.move(active.getX(), active.getY() + 1);
            return validateMove(true);
        }
    }

    public void moveStone(int x, int y) throws InvalidActivityException {
        checkState();
        synchronized (active) {
            active.move(x, y);
            validateMove(false);
        }
    }

    public void rotateRightStone() throws InvalidActivityException {
        checkState();
        synchronized (active) {
            active.rotateRight();
            validateMove(false);
        }
    }

    public void rotateLeftStone() throws InvalidActivityException {
        checkState();
        synchronized (active) {
            active.rotateLeft();
            validateMove(false);
        }
    }

    public boolean validateMove(boolean isDropMove) {
        if (checkCollision()) {
            active.undoMove();
            if (isDropMove) {
                if (active instanceof StoneBonus) {
                    if (((StoneBonus) active).applyBonus()) {
                        // If bonus finished
                        state = GameState.PLAYING;
                        nextStone();
                    }
                    setChanged();
                    notifyObservers();
                } else {
                    stoneToBlocks();
                    nextStone();
                }

            }
            return false;
        }
        // Simulated drop
        simulated = simulateHardDrop();
        // Notify
        notifyGUI();
        return true;
    }

    public void notifyGUI() {
        setChanged();
        notifyObservers();
    }

    public void levelUp() {
        this.setLevel(level + 1);
        if (bonuses.size() < 2) {
            bonuses.add(StoneFactory.generateBonus(this));
        }
    }

    public void playBonus() {
        if (!bonuses.isEmpty()) {
            active = bonuses.remove(0);
            simulated = null;
            startStone(active);
        }
    }

    public void activateKonami() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                blocks[i][j] = null;
            }
        }
        active = StoneFactory.generateCube();
        next = StoneFactory.generateCube();
        state = GameState.KONAMI;
        startStone(active);
    }

    public void deactivateKonami() {
        state = GameState.PLAYING;
    }

    private Block[] simulateHardDrop() {
        int x = active.getX();
        int y = active.getY();
        boolean collision = false;
        while (!collision) {
            for (Block block : active.getBlocks()) {
                if (y + block.getY() >= rows || (y >= 0 && blocks[y + block.getY()][x + block.getX()] != null)) {
                    y--;
                    collision = true;
                    Block[] copy = active.copyBlocks();
                    for (Block b : copy) {
                        b.setColor(Color.darkGray);
                        b.setY(b.getY() + y);
                        b.setX(b.getX() + x);
                    }
                    return copy;
                }
            }
            y++;
        }
        return null;
    }

    private void checkState() throws InvalidActivityException {
        if (state != GameState.PLAYING) {
            throw new InvalidActivityException();
        }
    }

    public Stone getActive() {
        return active;
    }

    public Stone getNext() {
        return next;
    }

    public Stone getHold() {
        return hold;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Stone> getBonuses() {
        return bonuses;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setGoal();
    }

    public int getLines() {
        return lines;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal() {
        this.goal += (level * 5);
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Block[] getSimulated() {
        return simulated;
    }
}
