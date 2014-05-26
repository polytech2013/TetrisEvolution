package tetrisevolution.models.stones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activity.InvalidActivityException;
import javax.swing.Timer;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public abstract class StoneBonus extends Stone {

    private boolean lock = true;
    protected final Timer timer = new Timer(150, new BonusDrop());
    protected Board board;
    
    public StoneBonus(Board board) {
        super();
        this.board = board;
    }

    abstract public boolean applyBonus();

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
        timer.start();
    }

    public boolean isLocked() {
        return lock;
    }
    
    protected class BonusDrop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                board.dropStone(true);
            } catch (InvalidActivityException ex) {
                System.out.println("Unauthorised");
            }
        }
        
    }

}
