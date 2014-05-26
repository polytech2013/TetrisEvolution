package tetrisevolution.models.stones;

import java.awt.Color;
import tetrisevolution.helpers.MusicHandler;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class StoneBomb extends StoneBonus {

    private int size = 1;

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] BOMB = new Block[]{
        new Block(0, 0, StoneO.COLOR),
        new Block(1, 1, Color.darkGray),
        new Block(0, 2, Color.darkGray),
        new Block(1, 2, StoneZ.COLOR),
        new Block(2, 2, Color.darkGray),
        new Block(1, 3, Color.darkGray)};
    private static final Block[] BOMB_2 = new Block[]{
        new Block(0, 2, StoneZ.COLOR),
        new Block(1, 1, StoneZ.COLOR),
        new Block(1, 2, StoneL.COLOR),
        new Block(1, 3, StoneZ.COLOR),
        new Block(2, 0, StoneZ.COLOR),
        new Block(2, 1, StoneL.COLOR),
        new Block(2, 2, StoneO.COLOR),
        new Block(2, 3, StoneL.COLOR),
        new Block(2, 4, StoneZ.COLOR),
        new Block(3, 1, StoneZ.COLOR),
        new Block(3, 2, StoneL.COLOR),
        new Block(3, 3, StoneZ.COLOR),
        new Block(4, 2, StoneZ.COLOR)};
    private static final Block[] BOMB_3 = new Block[]{
        new Block(0, 3, StoneZ.COLOR),
        new Block(1, 2, StoneZ.COLOR),
        new Block(1, 3, StoneL.COLOR),
        new Block(1, 4, StoneZ.COLOR),
        new Block(2, 1, StoneZ.COLOR),
        new Block(2, 2, StoneL.COLOR),
        new Block(2, 3, StoneO.COLOR),
        new Block(2, 4, StoneL.COLOR),
        new Block(2, 5, StoneZ.COLOR),
        new Block(3, 0, StoneZ.COLOR),
        new Block(3, 1, StoneO.COLOR),
        new Block(3, 2, StoneL.COLOR),
        new Block(3, 3, Color.darkGray),
        new Block(3, 4, StoneO.COLOR),
        new Block(3, 5, StoneL.COLOR),
        new Block(3, 6, StoneZ.COLOR),
        new Block(4, 1, StoneZ.COLOR),
        new Block(4, 2, StoneL.COLOR),
        new Block(4, 3, StoneO.COLOR),
        new Block(4, 4, StoneL.COLOR),
        new Block(4, 5, StoneZ.COLOR),
        new Block(5, 2, StoneZ.COLOR),
        new Block(5, 3, StoneL.COLOR),
        new Block(5, 4, StoneZ.COLOR),
        new Block(6, 3, StoneZ.COLOR),};
    // </editor-fold>

    public StoneBomb(Board board) {
        super(board);
    }

    @Override
    Block[] build() {
        return BOMB.clone();
    }

    @Override
    void rotate() {
    }

    @Override
    public int getSize() {
        return 4;
    }

    private boolean inLimit(int x, int y, Board board) {
        if (x < 0 || x >= board.getColumns()) {
            return false;
        } else if (y < 0 || y >= board.getRows()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean applyBonus() {
        size++;
        if (size == 2) {
            MusicHandler.playBombSound();
            blocks = BOMB_2.clone();
            x--;
        } else if (size == 3) {
            blocks = BOMB_3.clone();
            x--;
            Block[][] boardBlocks = board.getBlocks();
            for (Block b : blocks) {
                if (inLimit(x + b.getX(), y + b.getY(), board)) {
                    if (boardBlocks[y + b.getY()][x + b.getX()] != null) {
                        boardBlocks[y + b.getY()][x + b.getX()] = null;
                        board.dropPoints(50);
                    }
                }
            }
        }
        if (size == 4) {
            timer.stop();
            return true;
        }
        return false;
    }

}
