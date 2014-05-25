/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisevolution.views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author TD
 */
public class MenuBar extends JMenuBar {

    private JMenu menuGame, menuHelp;
    private JMenuItem menuItemNewGame, menuItemExit,
            menuItemCommand, menuItemCredit;

    public MenuBar() {
        initComponents();
    }

    private void initComponents() {

        menuGame = new JMenu("Game");
        menuItemNewGame = new JMenuItem("New Game");
        menuItemExit = new JMenuItem("Exit");
        menuGame.add(menuItemNewGame);
        menuGame.add(menuItemExit);

        menuHelp = new JMenu("Help");
        menuItemCommand = new JMenuItem("Command");
        menuItemCredit = new JMenuItem("Credit");
        menuHelp.add(menuItemCommand);
        menuHelp.add(menuItemCredit);

        add(menuGame);
        add(menuHelp);
    }

    public JMenu getMenuGame() {
        return menuGame;
    }

    public JMenu getMenuHelp() {
        return menuHelp;
    }

    public JMenuItem getMenuItemNewGame() {
        return menuItemNewGame;
    }

    public JMenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public JMenuItem getMenuItemCommand() {
        return menuItemCommand;
    }

    public JMenuItem getMenuItemCredit() {
        return menuItemCredit;
    }

    
    
}
