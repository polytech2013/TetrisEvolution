package tetrisevolution.helpers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Music player
 * @author Mario (Crédit à Rémi Adriano)
 */

public class MusicHandler {

    public File wavFile;
    public AudioClip sound;

    public MusicHandler(String fileName) {
        this.wavFile = new File(fileName);
        try {
            this.sound = Applet.newAudioClip(wavFile.toURI().toURL());
        } catch (MalformedURLException ex) {
            System.out.println("Error while reading audio file");
        }
    }

    public void play() {
        this.sound.play();
    }

    public void loop() {
        this.sound.loop();
    }

    public void stop() {
        this.sound.stop();
    }

}
