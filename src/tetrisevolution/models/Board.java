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

    public void moveStone(int x, int y) {
        this.active.move(x, y);
        notifyMove();
    }

    public void rotateStone() {
        this.active.rotateRight();
        notifyMove();
    }

    public void notifyMove() {
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
