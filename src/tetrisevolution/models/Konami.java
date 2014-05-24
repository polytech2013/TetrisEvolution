package tetrisevolution.models;

/**
 *
 * @author Mario
 */
import java.awt.event.KeyEvent;

public class Konami {

    static int[] sequence = {KeyEvent.VK_UP, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, 
        KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_B, KeyEvent.VK_A};
    static int currentButton = 0;

    public static boolean checkKonami(int keyPressed) {
        //Key sequence pressed is correct thus far
        if (keyPressed == sequence[currentButton]) {
            currentButton++;

            //return true when last button is pressed
            if (currentButton == sequence.length) {

            //Important! Next call to checkKonami()
                //would result in ArrayIndexOutOfBoundsException otherwise
                currentButton = 0;

                return true;
            }
        } else {
            //Reset currentButton
            currentButton = 0;
        }

        return false;
    }
}
