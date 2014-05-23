package tetrisevolution.models;

import java.util.Observable;
import tetrisevolution.models.stones.Block;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class Board extends Observable {

    private int rows, columns;
    private Block[][] blocks;
    private int score, level, lines;
    private Stone active, next, hold;
    private GameState state;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void newGame() {
        level = 1;
        score = 0;
        lines = 0;
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

    public boolean dropStone() {
        synchronized (active) {
            active.move(active.getX(), active.getY() + 1);
            return validateMove(true);
        }
    }

    public void moveStone(int x, int y) {
        synchronized (active) {
            active.move(x, y);
            validateMove(false);
        }
    }

    public void rotateStone() {
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
        setChanged();
        notifyObservers();
        return true;
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

    public int getLines() {
        return lines;
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
}
