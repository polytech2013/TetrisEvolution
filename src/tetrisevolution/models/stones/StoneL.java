package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneL extends Stone {

    public static final Color COLOR = new Color(204, 160, 0);

    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] L = new Block[]{new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(2, 1, COLOR), new Block(2, 0, COLOR)};
    private static final Block[] L_90 = new Block[]{new Block(0, 0, COLOR), new Block(0, 1, COLOR), new Block(0, 2, COLOR), new Block(1, 2, COLOR)};
    private static final Block[] L_180 = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(2, 0, COLOR), new Block(0, 1, COLOR)};
    private static final Block[] L_270 = new Block[]{new Block(0, 0, COLOR), new Block(1, 0, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR)};
    
    // </editor-fold>

    @Override
    Block[] build() {
        return L.clone();
    }

    @Override
    void rotate() {
        if (orientation == 0) {
            blocks = L.clone();
        } else if (orientation == 90) {
            blocks = L_90.clone();
        } else if (orientation == 180) {
            blocks = L_180.clone();
        } else if (orientation == 270) {
            blocks = L_270.clone();
        }
    }

    @Override
    public int getSize() {
        return 3;
    }

}
