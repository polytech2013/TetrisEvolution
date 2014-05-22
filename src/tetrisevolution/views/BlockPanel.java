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

    public static final int SIZE = 20;

    public static void draw(Block block, Graphics g, double offsetY, double offsetX) {
        if (block != null) {
            g.setColor(block.getColor());
        } else {
            g.setColor(new Color(35, 35, 35));
        }
        g.fill3DRect((int)(SIZE * offsetX), (int)(SIZE * offsetY), SIZE, SIZE, true);
    }
    
    public static void draw(Block block, Graphics g, int offsetY, int offsetX) {
        draw(block, g, (double)offsetY, (double)offsetX);
    }

    public static void draw(Block block, Graphics g) {
        draw(block, g, 0, 0);
    }

}
