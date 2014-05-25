package tetrisevolution.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrisevolution.models.Board;

/**
 *
 * @author Mario
 */
public class RightSidePanel extends JPanel {

    private Board board;
    private HoldStonePanel holdStonePanel;

    public RightSidePanel(Board board) {
        this.board = board;
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
        holdStonePanel = new HoldStonePanel(board);
        add(holdStonePanel, gbc);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(getWidth() - 40, getHeight() - 240));
        infoPanel.setBackground(new Color(35, 35, 35));
        infoPanel.setLayout(new GridLayout(6, 1, 0, 0));

        JLabel levelText = new JLabel("Bonus");
        levelText.setForeground(Color.white);
        levelText.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(levelText);

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

}
