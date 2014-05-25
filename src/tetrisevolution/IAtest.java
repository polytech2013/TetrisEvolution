package tetrisevolution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activity.InvalidActivityException;
import tetrisevolution.models.Board;
import tetrisevolution.models.stones.Stone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TD
 */
public class IAtest {

    Board playingBoard;

    public IAtest(Board playingBoard) {
        this.playingBoard = playingBoard;
    }

    // search BestPosition
    public void searchBestPosition() {

        playingBoard.getNext();

        searchPosition(playingBoard.getActive());
        moveStone();
    }

    public void searchPosition(Stone active) {

        for (int i = 0; i < active.getNbPosition(); i++) {
            
        }

    }

    public void moveStone() {
        try {
            playingBoard.moveStone(6, 0);
        } catch (InvalidActivityException ex) {
            Logger.getLogger(IAtest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
