import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundUtil {
    private static final File sound = new File("resources/shot.wav");
    private static AudioInputStream audioIn;
    private static Clip clip;

    public static void initSound() {
        try {
            audioIn = AudioSystem.getAudioInputStream(sound.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static void playSound() {
        clip.start();
        clip.close();
    }

    public static boolean clipIsOpen() {
        return clip.isOpen();
    }

    public void openClip() {
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
