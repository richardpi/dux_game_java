package dux;

import javax.media.format.AudioFormat;
import java.io.File;

import javax.media.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public Sound() {
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
	
	public void music() {
		try {
			//Player player = Manager.createPlayer(new MediaLocator(new File("data/main.mp3").toURI().toURL()));
			//player.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void shoot() {
		try {
			Player shootPlayer = Manager.createPlayer(new MediaLocator(new File("data/shoot.mp3").toURI().toURL()));	
			shootPlayer.start();
		} 
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void hit() {
		try {
			Player playerHit = Manager.createPlayer(new MediaLocator(new File("data/hit.mp3").toURI().toURL()));
			playerHit.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
