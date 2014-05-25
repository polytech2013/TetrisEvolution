package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class LeftSidePanel extends JPanel implements Observer {

    private JLabel scoreLabel, levelLabel, lineLabel;
    private Board board;
    private NextStonePanel nextStonePanel;

    public LeftSidePanel(Board board) {
        this.board = board;
        board.addObserver(this);
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(220, 220, 220));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(getWidth(), getHeight()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        nextStonePanel = new NextStonePanel(board);
        add(nextStonePanel, gbc);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(getWidth() - 40, getHeight() - 240));
        infoPanel.setBackground(new Color(35, 35, 35));
        infoPanel.setLayout(new GridLayout(6, 1, 0, 0));

        JLabel levelText = new JLabel("Level");
        levelLabel = new JLabel("" + board.getLevel());
        levelText.setForeground(Color.white);
        levelText.setHorizontalAlignment(JLabel.CENTER);
        levelLabel.setHorizontalAlignment(JLabel.CENTER);
        levelLabel.setForeground(Color.white);
        infoPanel.add(levelText);
        infoPanel.add(levelLabel);

        JLabel scoreText = new JLabel("Points");
        scoreLabel = new JLabel("" + board.getScore());
        scoreText.setForeground(Color.white);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreText.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(scoreText);
        infoPanel.add(scoreLabel);

        JLabel lineText = new JLabel("Lines");
        lineLabel = new JLabel("" + board.getLines());
        lineLabel.setForeground(Color.white);
        lineText.setForeground(Color.white);
        lineLabel.setHorizontalAlignment(JLabel.CENTER);
        lineText.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(lineText);
        infoPanel.add(lineLabel);

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 4;
        add(infoPanel, gbc);
    }

    @Override
    public int getHeight() {
        return board.getRows() * BlockPanel.SIZE;
    }

    @Override
    public int getWidth() {
        return 40 + 4 * BlockPanel.SIZE;
    }

    @Override
    public void update(Observable o, Object o1) {
        scoreLabel.setText("" + board.getScore());
        levelLabel.setText("" + board.getLevel());
        lineLabel.setText("" + board.getLines());
    }

}
