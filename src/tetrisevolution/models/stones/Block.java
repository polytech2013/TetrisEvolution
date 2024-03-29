package tetrisevolution.models.stones;

import java.awt.Color;

/**
 * Simple block
 *
 * @author Mario
 */
public class Block implements Cloneable {

    private int x;
    private int y;
    private Color color;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    @Override
    public Object clone() {
        return new Block(this.x, this.y, this.color);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
