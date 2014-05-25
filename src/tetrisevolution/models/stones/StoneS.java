
package tetrisevolution.models.stones;

import java.awt.Color;

/**
 *
 * @author Mario
 */
public class StoneS extends Stone {
    
    public static final Color COLOR = new Color(0, 240, 0);
    
    // <editor-fold defaultstate="collapsed" desc="Stone Shapes">
    private static final Block[] S = new Block[] { new Block(1, 0, COLOR), new Block(2, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR) };
    private static final Block[] S_90 = new Block[] { new Block(0, 0, COLOR), new Block(0, 1, COLOR), new Block(1, 1, COLOR), new Block(1, 2, COLOR) };
    // </editor-fold>

    @Override
    Block[] build() {
        super.setNbPosition(17);
        return S.clone();
    }

    @Override
    void rotate() {
        if (orientation % 180 == 90) {
            blocks = S_90.clone();
        }
        else {
            blocks = S.clone();
        }
    }

    @Override
    public int getSize() {
        return 3;
    }
    
}
