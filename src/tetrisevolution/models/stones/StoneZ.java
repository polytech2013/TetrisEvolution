package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneZ extends Stone {

    public static final Color COLOR = new Color(204, 0, 0);

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] Z = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(1, 1, COLOR), new Block(2, 1, COLOR)};
    private static final Block[] Z_90 = new Block[]{new Block(1, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(0, 2, COLOR)};
    // </editor-fold>

    @Override
    Block[] build() {
        return Z.clone();
    }

    @Override
    void rotate() {
        if (orientation % 180 == 90) {
            blocks = Z_90.clone();
        }
        else {
            blocks = Z.clone();
        }
    }

    @Override
    public int getSize() {
        return 3;
    }
}
