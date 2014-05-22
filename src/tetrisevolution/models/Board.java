package tetrisevolution.models;

/**
 *
 * @author Mario
 */
public class Board {

    private int rows, columns;
    private Block[][] blocks;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.blocks = new Block[rows][columns];
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
