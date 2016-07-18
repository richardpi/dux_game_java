package dux;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	public static Clip startClip;
	public static Clip completedClip;

	private static Clip gameOverClip;
	private static Clip mainClip;
	private static Clip shootClip;
	private static Clip hitClip;
	private static Clip bulletRemoveClip;

	public Sound() {
		mainClip = prepareClip("data/sfx/main.wav");
		shootClip = prepareClip("data/sfx/shoot.wav");
		hitClip = prepareClip("data/sfx/hit.wav");
		bulletRemoveClip = prepareClip("data/sfx/bulletRemove.wav");
		startClip = prepareClip("data/sfx/start.wav");
		gameOverClip = prepareClip("data/sfx/end.wav");
		completedClip = prepareClip("data/sfx/count.wav");
	}

	public static void music() {
		mainClip.setFramePosition(0); // Must always rewind!
		mainClip.start();
		mainClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void musicStop() {
		mainClip.stop();
	}

	public static void completed() {
		completedClip.setFramePosition(0); // Must always rewind!
		completedClip.start();
	}

	public static void gameOver() {
		gameOverClip.setFramePosition(0); // Must always rewind!
		gameOverClip.start();
	}

	public static void start() {
		startClip.setFramePosition(0); // Must always rewind!
		startClip.start();
	}

	public static void shoot() {
		shootClip.setFramePosition(0); // Must always rewind!
		shootClip.start();
	}

	public static void hit() {
		hitClip.setFramePosition(0); // Must always rewind!
		hitClip.start();
	}

	public static void bulletRemove() {
		bulletRemoveClip.setFramePosition(0); // Must always rewind!
		bulletRemoveClip.start();
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
			} else {
				throw new RuntimeException("Sound: file not found: " + filename);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		return clip;
	}

}
