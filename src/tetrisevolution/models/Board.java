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
        this.blocks = new Block[rows][columns];
        active = StoneFactory.generateRandom();
        startStone(active);
        next = StoneFactory.generateRandom();
    }

    public final void startStone(Stone startStone) {
        startStone.setX(columns / 2 - 1);
        startStone.setY(0);
    }

    public boolean checkCollision() {
        int x, y;
        for (Block block : active.getBlocks()) {
            x = active.getX() + block.getX();
            y = active.getY() + block.getY();

            if (x >= columns || y >= rows) {
                return true;
            } else if (x < 0) {
                return true;
            } else if (blocks[y][x] != null) {
                return true;
            }
        }
        return false;
    }

    public void undoLastMove() {

    }

    public void stoneToBlocks() {

    }

    public void checkForFullRows() {

    }

    public void clearFullRows() {

    }

    public void moveStone(int x, int y) {
        synchronized (active) {
            saveOld();
            this.active.move(x, y);
            validateMove();
        }
    }

    public void rotateStone() {
        synchronized (active) {
            saveOld();
            this.active.rotateRight();
            validateMove();
        }
    }

    public void saveOld() {
        oldX = active.getX();
        oldY = active.getY();
        oldOrientation = active.getOrientation();
    }

    public void validateMove() {
        if (checkCollision()) {
            active.undoMove();
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
