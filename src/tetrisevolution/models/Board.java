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

    private int oldX, oldY, oldOrientation;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;      
    }
    
    public void newGame() {
        this.blocks = new Block[rows][columns];
        active = StoneFactory.generateRandom();
        startStone(active);
        next = StoneFactory.generateRandom();
    }

    public final void startStone(Stone startStone) {
        startStone.setX(columns / 2 - 1);
        startStone.setY(0);
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
            } else if (blocks[y][x] != null) {
                return true;
            }
        }
        return false;
    }

    public void stoneToBlocks() {
        active.undoMove();
        for (Block block : active.getBlocks()) {
            blocks[active.getY() + block.getY()][active.getX() + block.getX()] = block;
        }
        clearFullRows();
    }

    public void clearFullRows() {
        boolean fullRow;
        for (int i = 0; i < rows; i++) {
            fullRow = true;
            for (int j = 0; j < columns; j++) {
                if (blocks[i][j] == null) {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) {
                /*for (int j = 0; j < columns; j++) {
                    blocks[i][j] = null;
                }*/
                for (int k = i; k > 0; k--) {
                    blocks[k] = blocks[k - 1];
                }
            }
        }
    }

    public void dropStone() {
        synchronized (active) {
            saveOld();
            active.move(active.getX(), active.getY() + 1);
            validateMove(true);
        }
    }

    public void moveStone(int x, int y) {
        synchronized (active) {
            saveOld();
            active.move(x, y);
            validateMove(false);
        }
    }

    public void rotateStone() {
        synchronized (active) {
            saveOld();
            active.rotateRight();
            validateMove(false);
        }
    }

    public void saveOld() {
        oldX = active.getX();
        oldY = active.getY();
        oldOrientation = active.getOrientation();
    }

    public void validateMove(boolean isDropMove) {
        if (checkCollision()) {
            active.undoMove();
            if (isDropMove) {
                stoneToBlocks();
                nextStone();
            }
        }
        setChanged();
        notifyObservers();
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
}
