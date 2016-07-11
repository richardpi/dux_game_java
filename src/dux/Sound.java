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
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;

import javax.media.*;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;


/*
import java.io.*;
import javax.sound.sampled.*;
*/

public class Sound {
	
	private static Clip clip;
	private static Clip clip2;
	
	//public static File file;
	/*
	public static URL url;
	public static MediaLocator ml;
	public static Player shootPlayer;
	*/
	
	public Sound() {
		
		//file = new File("data/shoot.wav");
		
		String fileName = "data/shoot.wav";
		String fileName2 = "data/hit.wav";
		
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
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
            		
		
        
        
        
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName2);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip2 = AudioSystem.getClip();
                clip2.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName2);
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
        
        
		
		
		/*
		file = new File("data/shoot.wav");
		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ml = new MediaLocator(url);
		try {
			shootPlayer = Manager.createPlayer(ml);
		} catch (NoPlayerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
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
			player.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void shoot() {
		
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
		
		/*
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	    */		
		
		
		/*
		try {
		    //File yourFile = new File("data/shoot.wav");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;
		
		    stream = AudioSystem.getAudioInputStream(file);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
				
		
		/*
		try {
			shootPlayer.start();
		} 
		catch(Exception ex){
			ex.printStackTrace();
		}
		*/
	}
	
	public static void hit() {
		
        clip2.setFramePosition(0);  // Must always rewind!
        clip2.start();
		
		try {
			//Player playerHit = Manager.createPlayer(new MediaLocator(new File("data/hit.wav").toURI().toURL()));
			//playerHit.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
