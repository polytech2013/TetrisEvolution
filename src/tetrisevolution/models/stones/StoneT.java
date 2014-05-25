package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneT extends Stone {

    public static final Color COLOR = new Color(204, 0, 204);

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] T = new Block[]{new Block(1, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(2, 1, COLOR)};
    private static final Block[] T_90 = new Block[]{new Block(1, 0, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR), new Block(2, 1, COLOR)};
    private static final Block[] T_180 = new Block[]{new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(2, 1, COLOR), new Block(1, 2, COLOR)};
    private static final Block[] T_270 = new Block[]{new Block(1, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR)};
    // </editor-fold>

    @Override
    Block[] build() {
        return T.clone();
    }

    @Override
    void rotate() {
        if (orientation == 0) {
            blocks = T.clone();
        } else if (orientation == 90) {
            blocks = T_90.clone();
        } else if (orientation == 180) {
            blocks = T_180.clone();
        } else if (orientation == 270) {
            blocks = T_270.clone();
        }
    }

    @Override
    public int getSize() {
        return 3;
    }
}
