package tetrisevolution.models;

import tetrisevolution.models.stones.Block;
import tetrisevolution.models.stones.Stone;

/**
 *
 * @author Mario
 */
public class Board {

    private int rows, columns;
    private Block[][] blocks;

    private int score, level, lines;

    private Stone active, next, hold;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.blocks = new Block[rows][columns];
        next = StoneFactory.generateRandom();
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
