/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisevolution.models;

/**
 *
 * @author TD
 */
import java.io.Serializable;

public class Score implements Serializable, Comparable {

    private int score;

    public Score(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        Score scr = (Score)o;
        if (this.getScore() > scr.getScore()) {
            return -1;
        } else if (this.getScore() < scr.getScore()) {
            return +1;
        } else {
            return 0;
        }
    }

    public int getScore() {
        return score;
    }

}
