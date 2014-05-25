package tetrisevolution.models;

import java.awt.Color;
import java.util.Observable;
import javax.activity.InvalidActivityException;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class Board extends Observable {

    private int rows, columns;
    private Block[][] blocks;
    private int score, level, lines, goal = 0;
    private Stone active, next, hold, tmp;
    private Block[] simulated;
    private GameState state;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void newGame() {
        level = 1;
        score = 0;
        lines = 0;
        setGoal();
        this.blocks = new Block[rows][columns];
        active = StoneFactory.generateRandom();
        startStone(active);
        next = StoneFactory.generateRandom();
        state = GameState.PLAYING;
    }

    public final void startStone(Stone startStone) {
        startStone.setX(columns / 2 - active.getSize() / 2);
        startStone.setY(-1);
        if (checkCollision()) {
            state = GameState.GAMEOVER;
        }
    }

    public void nextStone() {
        active = next;
        next = StoneFactory.generateRandom();
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
        active.undoMove();
        for (Block block : active.getBlocks()) {
            if (active.getY() + block.getY() >= 0) {
                blocks[active.getY() + block.getY()][active.getX() + block.getX()] = block;
            }
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
        linePoints(n);
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

    public boolean dropStone() throws InvalidActivityException {
        checkState();
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

    public void rotateStone() throws InvalidActivityException {
        checkState();
        synchronized (active) {
            active.rotateRight();
            validateMove(false);
        }
    }

    public boolean validateMove(boolean isDropMove) {
        if (checkCollision()) {
            active.undoMove();
            if (isDropMove) {
                stoneToBlocks();
                nextStone();
            }
            return false;
        }
        // Simulated drop
        simulated = simulateHardDrop();
        // Notify
        setChanged();
        notifyObservers();
        return true;
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
