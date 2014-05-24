
package tetrisevolution.models.stones;

/**
 *
 * @author Mario
 */
abstract public class Stone {
        
    protected Block[] blocks;
    protected int x, y, orientation = 0;
    protected int oldX, oldY, oldOrientation = 0;

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
        this.rotate();
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
        saveOld();
        this.x = x;
        this.y = y;
    }
    
    public void rotateRight() {
        saveOld();
        orientation = (orientation + 90) % 360;
        rotate();
    }
    
    public void rotateLeft() {
        saveOld();
        orientation = (orientation - 90) % 360;
        orientation = (orientation < 0) ? (360 - Math.abs(orientation)) : orientation;
        rotate();
    }
    
    public void undoMove() {
        x = oldX;
        y = oldY;
        orientation = oldOrientation;
        rotate();
    }
    
    private void saveOld() {
        oldX = this.x;
        oldY = this.y;
        oldOrientation = this.orientation;
    }
    
    abstract Block[] build();
    abstract void rotate();
    public abstract int getSize();
    
}
