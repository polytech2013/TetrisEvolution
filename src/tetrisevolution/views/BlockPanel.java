package tetrisevolution.views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import tetrisevolution.models.stones.Block;

/**
 *
 * @author Mario
 */
public class BlockPanel extends JComponent {

    public static final int SIZE = 30;

    public static void draw(Block block, Graphics g, double offsetY, double offsetX) {
        if (block != null) {
            g.setColor(block.getColor());
            g.fill3DRect((int)(SIZE * offsetX), (int)(SIZE * offsetY), SIZE, SIZE, true);
        } else {
            g.setColor(new Color(40, 40, 40));
            g.fill3DRect((int)(SIZE * offsetX), (int)(SIZE * offsetY), SIZE, SIZE, false);
        }
        
    }
    
    public static void draw(Block block, Graphics g, int offsetY, int offsetX) {
        draw(block, g, (double)offsetY, (double)offsetX);
    }

    public static void draw(Block block, Graphics g) {
        draw(block, g, 0, 0);
    }

}
