
package tetrisevolution.models.stones;

/**
 *
 * @author Mario
 */
abstract public class Stone {
        
    protected Block[] blocks;
    protected int orientation = 0;
    protected int x;
    protected int y;

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
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
    
    public Stone() {
        blocks = build();
    }
    
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void rotateRight() {
        orientation = (orientation + 90) % 360;
        rotate();
    }
    
    public void rotateLeft() {
        orientation = (orientation - 90) % 360;
        orientation = (orientation < 0) ? (360 - Math.abs(orientation)) : orientation;
        rotate();
    }
    
    abstract Block[] build();
    abstract void rotate();
    public abstract int getSize();
    
}
