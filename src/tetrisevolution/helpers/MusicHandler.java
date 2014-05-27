package tetrisevolution.helpers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Music player
 * @author Mario (Thanks to Rémi Adriano for base class)
 */

public class MusicHandler {

    public File wavFile;
    public AudioClip clip;
    
    private static MusicHandler drop = new MusicHandler("src/tetrisevolution/resources/Drop.wav");
    private static MusicHandler line = new MusicHandler("src/tetrisevolution/resources/Line.wav");
    private static MusicHandler bomb = new MusicHandler("src/tetrisevolution/resources/Bomb.wav");
    private static MusicHandler gameOver = new MusicHandler("src/tetrisevolution/resources/GameOver.wav");
    private static MusicHandler hammer = new MusicHandler("src/tetrisevolution/resources/Hammer.wav");
    

    public MusicHandler(String fileName) {
        this.wavFile = new File(fileName);
        try {
            this.clip = Applet.newAudioClip(wavFile.toURI().toURL());
        } catch (MalformedURLException ex) {
            System.out.println("Error while reading audio file");
        }
    }

    public void play() {
        this.clip.play();
    }

    public void loop() {
        this.clip.loop();
    }

    public void stop() {
        this.clip.stop();
    }
    
    public static void playLineSound() {
        line.play();
    }
    
    public static void playBombSound() {
        bomb.play();
    }
    
    public static void playDropSound() {
        drop.play();
    }
    
    public static void playGameOverSound() {
        gameOver.play();
    }
    
    public static void playHammerSound() {
        hammer.play();
    }

}
