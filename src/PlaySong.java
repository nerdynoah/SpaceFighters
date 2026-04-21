import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
/**
 * Play a WAV file, you can stop/start it.
 */
public class PlaySong 
{
    /**
     * Clip to play
     */
    private Clip clip;
    
    /**
     * Start a wave file
     * @param filePath path of sound
     * @param loop loop the file?
     */
    public void playWAV(String filePath, boolean loop) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            if (loop)
            {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        if (clip != null) clip.stop();
    }
}