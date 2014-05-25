package tetrisevolution.models.stones;

import java.awt.Color;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class StoneHammer extends StoneBonus {

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] HAMMER = new Block[]{
        new Block(1, 0, new Color(134, 34, 4)),
        new Block(1, 1, new Color(134, 34, 4)),
        new Block(0, 2, Color.white),
        new Block(1, 2, Color.white),
        new Block(2, 2, Color.white),
        new Block(0, 3, Color.white),
        new Block(1, 3, Color.white),
        new Block(2, 3, Color.white)};
    // </editor-fold>

    @Override
    Block[] build() {
        return HAMMER.clone();
    }

    @Override
    void rotate() {
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public boolean applyBonus(Board board) {
        y++;
        Block[][] boardBlocks = board.getBlocks();
        for (Block b : blocks) {
            if (y + b.getY() < board.getRows()) {
                boardBlocks[y + b.getY()][x + b.getX()] = null;
            }
        }
        return y >= board.getRows();
    }

}
