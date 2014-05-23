
package tetrisevolution.models.stones;

/**
 *
 * @author Mario
 */
abstract public class Stone {
        
    protected Block[] blocks;
    protected int orientation = 0, x, y;
    protected int oldX, oldY, oldOrientation;
            
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
        oldOrientation = this.orientation;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        oldX = this.x;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        oldY = this.y;
        this.y = y;
    }
    
    public Stone() {
        blocks = build();
    }
    
    public void move(int x, int y) {
        setX(x);
        setY(y);
    }
    
    public void undoMove() {
        x = oldX;
        y = oldY;
        orientation = oldOrientation;
        rotate();
    }
    
    public void rotateRight() {
        setOrientation((orientation + 90) % 360);
        rotate();
    }
    
    public void rotateLeft() {
        orientation = (orientation - 90) % 360;
        setOrientation((orientation < 0) ? (360 - Math.abs(orientation)) : orientation);
        rotate();
    }
    
    abstract Block[] build();
    abstract void rotate();
    public abstract int getSize();
    
}
