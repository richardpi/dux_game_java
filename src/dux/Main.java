package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.media.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.net.*;
import java.io.*;
import java.util.*;

import java.io.File;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics second;

	private URL base;

	private static Gun gun;
	public static Duck duck;

	private static Image gunPic;
	private static Image duckPic;

	@Override
	public void init() {
		
		setSize(640, 512);
		setBackground(Color.BLUE);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Dux");

		addKeyListener(this);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		gun = new Gun();
		gunPic = getImage(base, "data/gun.png");

		duck = new Duck();
		duckPic = getImage(base, "data/duck.png");
		
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn(
			"com.sun.media.codec.audio.mp3.JavaDecoder",
			new Format[]{input1, input2},
			new Format[]{output},
			PlugInManager.CODEC
		);
		try {
			//Player player = Manager.createPlayer(new MediaLocator(new File("data/main.mp3").toURI().toURL()));
			//player.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void start() {
		
		Thread thread = new Thread(this);
		thread.start();	
	}

	@Override
	public void run() {

		while (true) {
			gun.update();
			duck.update();
			
			ArrayList projectiles = gun.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}			
			
			repaint();
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(gunPic, gun.getCenterX() - 10, gun.getCenterY() - 100, this);
		g.drawImage(duckPic, duck.getCenterX(), duck.getCenterY(), this);
		
		ArrayList projectiles = gun.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 5, 15);
		}
		
		g.drawRect((int)duck.rect.getX(), (int)duck.rect.getY(), (int)duck.rect.getWidth(), (int)duck.rect.getHeight());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gun.moveLeft();
				break;
	
			case KeyEvent.VK_RIGHT:
				gun.moveRight();
				break;
	
			case KeyEvent.VK_SPACE:
				System.out.println("shoot");
				gun.shoot();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gun.moveStop();
	}

}
