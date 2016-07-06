package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import dux.creatures.*;

import java.util.*;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics second;

	private static Gun gun;
	public static ArrayList<Creature> creatures = new ArrayList<Creature>();
	
	private static Sound sound;
	
	private int creatureCounter = 0;
	
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
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

		            @Override
		            public void run() {
		            	
		                double rand = Math.random() * 1;
		                if (Math.round(rand) == 1) {
			                System.out.println("duck added: " + creatureCounter);
			                createDuck();
		                } else {
			                System.out.println("owl added: " + creatureCounter);
			                createOwl();
		                }

		                creatureCounter++;
		                
		                if (10 <= creatureCounter) {
		                    t.cancel();
		                    t.purge();
		                }
		                
		            }
		        }, 1, 300);
	}
	
	private void createDuck() {
		Image duckImageRight = loadImage("data/dux.png");
		Image duckImageLeft = ImageTools.flipImage(duckImageRight);
		Duck d = new Duck();
		d.setRightPic(duckImageRight);
		d.setLeftPic(duckImageLeft);
		d.init();
		creatures.add(d);
	}
	
	private void createOwl() {
		Image owlImageRight = loadImage("data/owl.png");
		Image owlImageLeft = ImageTools.flipImage(owlImageRight);
		Owl o = new Owl();
		o.setRightPic(owlImageRight);
		o.setLeftPic(owlImageLeft);
		o.init();
		creatures.add(o);		
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
		g.drawImage(gun.getGunPic(), gun.getCenterX() - 10, gun.getCenterY() - 100, this);

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
			g.drawRect((int)c.rect.getX(), (int)c.rect.getY(), (int)c.rect.getWidth(), (int)c.rect.getHeight());
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
