
package tetrisevolution.controllers;

import tetrisevolution.models.Board;
import tetrisevolution.views.TetrisFrame;

/**
 *
 * @author Mario
 */
public class MainController {
    
    private TetrisFrame frame;
    
    public MainController() {
        Board board = new Board(20, 10);        
        frame = new TetrisFrame(board);
    }
    
}
