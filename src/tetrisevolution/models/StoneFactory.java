package tetrisevolution.models;

import java.util.Random;
import tetrisevolution.models.stones.*;

/**
 *
 * @author Mario
 */
public class StoneFactory {

    public static Stone generateRandom() {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                return new StoneI();
            case 1:
                return new StoneO();
            case 2:
                return new StoneT();
            case 3:
                return new StoneJ();
            case 4:
                return new StoneL();
            case 5:
                return new StoneS();
            case 6:
                return new StoneZ();
            default:
                return new StoneO();
        }
    }

    public static Stone generateCube() {
        return new StoneO();
    }

}
