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
    private LeftSidePanel leftSidePanel;
    private RightSidePanel rightSidePanel;

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
        leftSidePanel = new LeftSidePanel(playingBoard);
        add(leftSidePanel, BorderLayout.WEST);

        // Create Right side panel
        rightSidePanel = new RightSidePanel(playingBoard);
        add(rightSidePanel, BorderLayout.EAST);
        
        setResizable(false);

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

}
