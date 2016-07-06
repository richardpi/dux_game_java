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

import java.net.*;
import java.io.*;
import java.util.*;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics second;

	private static Gun gun;
	public static ArrayList<Duck> ducks = new ArrayList<Duck>();
	
	private static Sound sound;
	
	private int duckCounter = 0;
	
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
		gun.setGunPic(loadImage("data/gun.png"));		
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

		            @Override
		            public void run() {
		                System.out.println("duck added: " + duckCounter);
		                createDuck();
		                duckCounter++;
		                
		                if (5 <= duckCounter) {
		                    t.cancel();
		                    t.purge();
		                }
		                
		            }
		        }, 1, 300);
	}
	
	private void createDuck()
	{
		Image duckImageRight = loadImage("data/dux.png");
		Image duckImageLeft = ImageTools.flipImage(duckImageRight);
		Duck d = new Duck();
		d.setDuckRightPic(duckImageRight);
		d.setDuckLeftPic(duckImageLeft);
		d.init();
		ducks.add(d);
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
			
			for (int i = 0; i < ducks.size(); i++) {
				Duck d = (Duck) ducks.get(i);
				d.update();
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

		for (int i = 0; i < ducks.size(); i++) {
			Duck d = (Duck) ducks.get(i);
			g.drawImage(d.getDuckPic(), d.getCenterX(), d.getCenterY(), this);
		}
		
		ArrayList projectiles = gun.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 5, 15);
		}

		for (int i = 0; i < ducks.size(); i++) {
			Duck d = (Duck) ducks.get(i);
			g.drawRect((int)d.rect.getX(), (int)d.rect.getY(), (int)d.rect.getWidth(), (int)d.rect.getHeight());
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
