package tetrisevolution.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.PopupMenu;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrisevolution.models.Board;
import tetrisevolution.old.SidePanel;

/**
 *
 * @author Mario
 */
public class LeftSidePanel extends JPanel {

    private JLabel scoreLabel, levelLabel, lineLabel;
    private Board board;
    private NextStonePanel nextStonePanel;

    public LeftSidePanel(Board board) {
        this.board = board;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(220, 220, 220));
        setPreferredSize(new Dimension(120, getHeight()));

        nextStonePanel = new NextStonePanel(board);
        nextStonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nextStonePanel);

        levelLabel = new JLabel("Level: " + board.getLevel());
        levelLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(levelLabel);

        scoreLabel = new JLabel("Points: " + board.getScore());
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(scoreLabel);

        lineLabel = new JLabel("Lines: " + board.getLines());
        lineLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(lineLabel);

    }

    @Override
    public int getHeight() {
        return board.getRows() * BlockPanel.SIZE;
    }

    @Override
    public int getWidth() {
        return 120;
    }

}
