package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneHammer extends Stone {

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] HAMMER = new Block[]{
        new Block(1, 0, new Color(134, 34, 4)),
        new Block(1, 1, new Color(134, 34, 4)),
        new Block(0, 2, Color.darkGray),
        new Block(1, 2, Color.darkGray),
        new Block(2, 2, Color.darkGray),
        new Block(0, 3, Color.darkGray),
        new Block(1, 3, Color.darkGray),
        new Block(2, 3, Color.darkGray)};
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

}
