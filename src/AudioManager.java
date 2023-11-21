import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

public class AudioManager {
	private static final String PLANE_CRASH = "..\\sounds\\plane-crash.au";
	private static final String EXPLOSION = "..\\sounds\\explosion.au";
	private static final String BACKGROUND = "..\\sounds\\background.au";
	
	private static AudioManager instance;
	
	public AudioManager(){
		instance = this;
	}
	
	public static void playBackground(){
		instance.play(BACKGROUND);
	}
	
	public static void playExplosion(){
		instance.play(EXPLOSION);
	}
	
	public static void playPlaneCrash(){
		instance.play(PLANE_CRASH);
	}
	
	public void play(String filePath){
    	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
			Clip audioClip;
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
			audioClip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
