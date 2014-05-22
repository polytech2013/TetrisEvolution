package tetrisevolution.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class TetrisFrame extends JFrame {
    
    private BoardPanel boardPanel;
    
    private Board playingBoard;

    public TetrisFrame(Board playingBoard) {
        this.playingBoard = playingBoard;
        initComponents();
    }

    private void initComponents() {
        setTitle("Tetris Evolution");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create Board panel
        boardPanel = new BoardPanel(playingBoard);
        add(boardPanel, BorderLayout.CENTER);
        // Create Left side panel
        
        // Create Right side panel
        
        setResizable(false);
        setVisible(true);
        pack();
    }

}
