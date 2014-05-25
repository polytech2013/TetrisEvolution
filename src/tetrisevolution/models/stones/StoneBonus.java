package tetrisevolution.models.stones;

import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public abstract class StoneBonus extends Stone {

    private boolean lock = true;

    abstract public boolean applyBonus(Board board);

    public void move(int x, int y) {
        saveOld();
        if (lock) {
            this.x = x;
        }        
        if (!lock) {
            this.y = y;
        }
    }

    public void unlock() {
        this.lock = false;
    }

    public boolean isLocked() {
        return lock;
    }

}
