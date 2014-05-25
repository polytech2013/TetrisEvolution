package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneJ extends Stone {

    public static final Color COLOR = new Color(0, 0, 204);

    // <editor-fold defaultstate="collapsed" desc="StoneJ Shapes">    
    private static final Block[] J = new Block[]{new Block(0, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(2, 1, COLOR)};
    private static final Block[] J_90 = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(0, 1, COLOR), new Block(0, 2, COLOR)};
    private static final Block[] J_180 = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(2, 0, COLOR), new Block(2, 1, COLOR)};
    private static final Block[] J_270 = new Block[]{new Block(1, 0, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR), new Block(0, 2, COLOR)};
    // </editor-fold>

    @Override
    Block[] build() {
        return J.clone();
    }

    @Override
    void rotate() {
        if (orientation == 0) {
            blocks = J.clone();
        } else if (orientation == 90) {
            blocks = J_90.clone();
        } else if (orientation == 180) {
            blocks = J_180.clone();
        } else if (orientation == 270) {
            blocks = J_270.clone();
        }
    }

    @Override
    public int getSize() {
        return 3;
    }

}
