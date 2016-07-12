package dux;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.media.format.AudioFormat;
import javax.media.*;

public class Sound {
	
	private static Clip shootClip;
	private static Clip hitClip;
	
	public Sound() {
				
		shootClip = prepareClip("data/shoot.wav");
		hitClip = prepareClip("data/hit.wav");
		
		//stuff to mp3 player
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn(
			"com.sun.media.codec.audio.mp3.JavaDecoder",
			new Format[]{input1, input2},
			new Format[]{output},
			PlugInManager.CODEC
		);		
	}
	
	public static void music() {
		try {
			Player player = Manager.createPlayer(new MediaLocator(new File("data/main.mp3").toURI().toURL()));
			//player.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void shoot() {
        shootClip.setFramePosition(0);  // Must always rewind!
        shootClip.start();
	}
	
	public static void hit() {
        hitClip.setFramePosition(0);  // Must always rewind!
        hitClip.start();
	}
	
	private static Clip prepareClip(String filename) {
		
		Clip clip = null;
		
        try {
            File file = new File(filename);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + filename);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        
        return clip;
	}

}
