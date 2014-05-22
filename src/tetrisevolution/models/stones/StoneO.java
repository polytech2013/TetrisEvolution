
package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneO extends Stone {
    
    public static final Color COLOR = new Color(240, 240, 0);
    
    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] O = new Block[] { new Block(0, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 0, COLOR), new Block(1, 1, COLOR) };
    // </editor-fold>

    @Override
    Block[] build() {
        return O.clone();
    }

    @Override
    void rotate() {
        // Nothing to do 
    }

    @Override
    public int getSize() {
        return 2;
    }
    
}
