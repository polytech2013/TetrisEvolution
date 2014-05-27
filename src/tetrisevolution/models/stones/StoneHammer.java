package tetrisevolution.models.stones;

import java.awt.Color;
import tetrisevolution.helpers.MusicHandler;
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

    public StoneHammer(Board board) {
        super(board);
    }

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
    public boolean applyBonus() {
        y++;
        Block[][] boardBlocks = board.getBlocks();
        boolean sound = false;
        for (Block b : blocks) {
            if (y + b.getY() < board.getRows()) {
                if (boardBlocks[y + b.getY()][x + b.getX()] != null) {
                    if (!sound) {
                        MusicHandler.playHammerSound();
                        sound = false;
                    }
                    boardBlocks[y + b.getY()][x + b.getX()] = null;
                    board.dropPoints(50);
                }
            }
        }
        if (y >= board.getRows()) {
            timer.stop();
            return true;
        }
        return false;
    }

}
