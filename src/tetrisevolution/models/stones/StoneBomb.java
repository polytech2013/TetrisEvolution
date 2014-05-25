package tetrisevolution.models.stones;

import java.awt.Color;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class StoneBomb extends StoneBonus {

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] BOMB = new Block[]{
        new Block(0, 0, StoneO.COLOR),
        new Block(1, 1, Color.darkGray),
        new Block(0, 2, Color.darkGray),
        new Block(1, 2, StoneZ.COLOR),
        new Block(2, 2, Color.darkGray),
        new Block(1, 3, Color.darkGray)};
    // </editor-fold>

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

    @Override
    public boolean applyBonus(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
