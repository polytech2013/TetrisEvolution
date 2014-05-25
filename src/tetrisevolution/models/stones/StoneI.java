package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneI extends Stone {

    public static final Color COLOR = new Color(0, 240, 240);

    // <editor-fold defaultstate="collapsed" desc="StoneI Shapes">
    private static final Block[] I = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(2, 0, COLOR), new Block(3, 0, COLOR)};
    private static final Block[] I_90 = new Block[]{new Block(1, 0, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR), new Block(1, 3, COLOR)};
    // </editor-fold>

    @Override
    Block[] build() {
        super.setNbPosition(17);
        return I.clone();
    }

    @Override
    void rotate() {
        if (orientation % 180 == 90) {
            blocks = I_90.clone();
        } else {
            blocks = I.clone();
        }
    }

    @Override
    public int getSize() {
        return 4;
    }

}
