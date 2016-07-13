package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import dux.creatures.*;
import java.util.*;

public class Main extends Applet implements Runnable, KeyListener {

	public static LoaderTools loaderTools;
	
	public static boolean test = false;
	public static boolean stop = false;
	public static boolean playGame = false;
	public static boolean gameOver = true;

	private Image image;
	private Graphics second;
	
	private static Image keyboard;

	private static Gun gun;
	public static ArrayList<Creature> creatures = new ArrayList<Creature>();

	private static Sound sound;

	private Font font;

	private int time = 0;

	@Override
	public void init() {

		setSize(1024, 768);
		setBackground(new Color(0, 0, 170));
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Dux");

		addKeyListener(this);

		loaderTools = new LoaderTools();
		loaderTools.setComponent(this);

		sound = new Sound();
		Sound.music();

		gun = new Gun();
		gun.setGunPic(loaderTools.loadImage("data/gun2.png"));
		Bullet.setPic(loaderTools.loadImage("data/bullet.png"));

		keyboard = loaderTools.loadImage("data/keyboard.png");
		
		font = new Font("Digital-7", Font.PLAIN, 50);

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/digital-7.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < Bullet.INIT_BULLETS; i++) {
			Bullet b = new Bullet();
			b.setCenterX(Bullet.START_LEFT + Bullet.SPACING * i);
			Bullet.bullets.add(b);
		}

		for (int i = 0; i < 40; i++) {
			CreatureFactory.initCreatures(7);
		}

		for (int i = 40; i < 60; i++) {
			CreatureFactory.initCreatures(6);
		}

		// row 1
		int pos = 0;
		for (int i = 0; i < 20; i++) {
			pos++;
			Creature c = (Creature) creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
		}

		// row 2
		pos = 0;
		for (int i = 20; i < 40; i++) {
			pos++;
			Creature c = (Creature) creatures.get(i);
			c.setCenterX(Creature.START_RIGHT - 55 * pos);
			c.initRight();
		}

		// row 3
		pos = 0;
		for (int i = 40; i < 60; i++) {
			pos++;
			Creature c = (Creature) creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
			c.initRight();
			c.initLeft();
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

			if (!stop) {

				if (playGame) {
					gun.update();

					for (int i = 0; i < creatures.size(); i++) {
						Creature c = (Creature) creatures.get(i);

						if (c.isRemove()) {
							CreatureFactory.createReplacementBlank(c);
							creatures.remove(i);
						}

						if (c instanceof Points) {
							if (1 >= c.getRow()) {
								CreatureFactory.createReplacementBlank(c);
								creatures.remove(i);
							}
						}

						if (5 <= checkNumberCreatures()) {
							if (c.isReplace()) {
								System.out.println("try replace");
								c.setReplace(false);
								int rand = new Random().nextInt(4);

								if (2 == rand) {
									System.out.println("replaced");
									CreatureFactory.replaceBlankWith(c);
									creatures.remove(i);
								}
							}						
						}

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
	
	private void playGame() {
		
		time = 250;
		
		java.util.Timer t1 = new java.util.Timer();
		t1.schedule(new TimerTask() {

			@Override
			public void run() {
				if (!stop) {
					time--;
				}
			}
		}, 1, 300);
		
	}
	
	private int checkNumberCreatures() {
		int number = 0;
		
		for (int i = 0; i < creatures.size(); i++) {
			Creature c = (Creature) creatures.get(i);
			
			if (false == c instanceof Blank) {
				number++;
			}
		}
		
		//System.out.println(number);
		return number;
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

		for (int i = 0; i < Bullet.bullets.size(); i++) {
			Bullet b = (Bullet) Bullet.bullets.get(i);
			g.drawImage(Bullet.getPic(), b.getCenterX(), b.getCenterY(), this);
		}
		
		if (gameOver) {
			g.fillRect(400, 280, 230, 2);
			g.drawString("GAME  OVER", 405, 325);
			g.fillRect(400, 336, 230, 2);
			g.drawImage(keyboard, 695, 630, this);
		}
		
		if (playGame) {
			
			g.drawImage(gun.getGunPic(), gun.getCenterX() - 10, gun.getCenterY() - 100, this);
			
			for (int i = 0; i < creatures.size(); i++) {
				Creature c = (Creature) creatures.get(i);
				g.drawImage(c.getPic(), (int) c.getCenterX(), c.getCenterY(), this);
			}

			ArrayList projectiles = gun.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(new Color(238, 238, 238));
				g.fillRect(p.getX(), p.getY(), 3, 60);
			}

			if (test) {
				for (int i = 0; i < creatures.size(); i++) {
					Creature c = (Creature) creatures.get(i);
					g.drawRect((int) c.rect.getX(), (int) c.rect.getY(), (int) c.rect.getWidth(), (int) c.rect.getHeight());
				}
			}			
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
				
		if (!stop) {
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

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				gameOver = false;
				playGame = true;
				playGame();
			}
		}
		
		if (!stop) {
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

	}

}
