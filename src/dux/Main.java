package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dux.creatures.*;
import java.util.*;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics second;

	private static Gun gun;
	public static ArrayList<Creature> creatures = new ArrayList<Creature>();
	
	private static Sound sound;

	private int creatureCounter = 0;
	
	private Font font;
	
	private int time = 250;

	@Override
	public void init() {

		setSize(1024, 768);
		setBackground(new Color(0, 0, 170));
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Dux");

		addKeyListener(this);

		sound = new Sound();
		Sound.music();

		gun = new Gun();
		gun.setGunPic(loadImage("data/gun2.png"));
		
		Bullet.setPic(loadImage("data/bullet.png"));
		
		font = new Font("Digital-7", Font.PLAIN, 50);

		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/digital-7.ttf")));
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}

		for (int i = 0; i < Bullet.INIT_BULLETS; i++) {
			Bullet b = new Bullet();
			b.setCenterX(Bullet.START_LEFT + Bullet.SPACING * i);
			Bullet.bullets.add(b);
		}
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {

				int rand = new Random().nextInt(3);

				switch (rand) {
				case 0:
					System.out.println("duck added: " + creatureCounter);
					createDuck();
					break;
				case 1:
					System.out.println("owl added: " + creatureCounter);
					createOwl();
					break;
				case 2:
					System.out.println("rabbit added: " + creatureCounter);
					createRabbit();
					break;
				}

				creatureCounter++;

				if (10 <= creatureCounter) {
					t.cancel();
					t.purge();
				}

			}
		}, 1, 300);
		
		java.util.Timer t1 = new java.util.Timer();
		t1.schedule(new TimerTask() {

			@Override
			public void run() {
				time--;
			}
		}, 1, 500);
				
	}

	private void createDuck() {
		createCreature("data/dux.png", new Duck());
	}

	private void createOwl() {
		createCreature("data/owl.png", new Owl());
	}

	private void createRabbit() {
		createCreature("data/rabbit.png", new Rabbit());
	}

	private void createCreature(String image, Creature c) {
		Image imageRight = loadImage(image);
		Image imageLeft = ImageTools.flipImage(imageRight);
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);
		c.init();
		creatures.add(c);
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

			for (int i = 0; i < creatures.size(); i++) {
				Creature c = (Creature) creatures.get(i);
				c.update();
			}

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
		
		g.setFont(font);
		g.setColor(new Color(238, 238, 238));
		
		g.fillRect(25, 25, 974, 2);
		g.drawString("SCORE  " + String.format("%06d", Creature.points), 25, 70);
		g.drawString("TIME  " + String.format("%03d", time), 425, 70);
		g.drawString("HIGHS  " + String.format("%06d", 0), 725, 70);
		g.fillRect(25, 80, 974, 2);
		
		g.fillRect(25, 720, 974, 2);
		
		g.drawImage(gun.getGunPic(), gun.getCenterX() - 10, gun.getCenterY() - 100, this);

		for (int i = 0; i < Bullet.bullets.size(); i++) {
			Bullet b = (Bullet) Bullet.bullets.get(i);
			g.drawImage(Bullet.getPic(), b.getCenterX(), b.getCenterY(), this);
		}
		
		for (int i = 0; i < creatures.size(); i++) {
			Creature c = (Creature) creatures.get(i);
			g.drawImage(c.getPic(), c.getCenterX(), c.getCenterY(), this);
		}

		ArrayList projectiles = gun.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(new Color(238, 238, 238));
			g.fillRect(p.getX(), p.getY(), 3, 60);
		}

		for (int i = 0; i < creatures.size(); i++) {
			Creature c = (Creature) creatures.get(i);
			g.drawRect((int) c.rect.getX(), (int) c.rect.getY(), (int) c.rect.getWidth(), (int) c.rect.getHeight());
		}
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
			gun.setMovingLeft(true);
			gun.setMovingRight(false);
			break;

		case KeyEvent.VK_RIGHT:
			gun.moveRight();
			gun.setMovingLeft(false);
			gun.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			if (gun.isReadyToFire()) {
				gun.shoot();
				gun.setReadyToFire(false);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			gun.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			gun.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			gun.setReadyToFire(true);
			break;
		}

	}

	private Image loadImage(String path) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(this);
		Image sourceImage = toolkit.getImage(path);
		tracker.addImage(sourceImage, 0);

		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}

		return sourceImage;
	}
}
