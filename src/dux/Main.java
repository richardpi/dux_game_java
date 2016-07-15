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
	
	private Image image;
	private Graphics second;
	private Font font;

	@Override
	public void init() {

		new Assets(this);
		
		setSize(1024, 768);
		setBackground(new Color(0, 0, 170));
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Dux");

		addKeyListener(this);
		font = new Font("Digital-7", Font.PLAIN, 50);

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/digital-7.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		Status.startScreen();		
	}

	@Override
	public void start() {

		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {

		while (true) {

			if (!Status.stop) {

				if (Status.showCreatures) {
					Assets.gun.update();

					for (int i = 0; i < Assets.creatures.size(); i++) {
						Creature c = (Creature) Assets.creatures.get(i);

						if (c.isRemove()) {
							CreatureFactory.createReplacementBlank(c);
							Assets.creatures.remove(i);
						}

						if (c instanceof Points) {
							if (1 >= c.getRow()) {
								CreatureFactory.createReplacementBlank(c);
								Assets.creatures.remove(i);
							}
						}

						if (5 <= checkNumberCreatures()) {
							if (c.isReplace()) {
								//System.out.println("try replace");
								c.setReplace(false);
								int rand = new Random().nextInt(3);

								if (2 == rand) {
									//System.out.println("replaced");
									CreatureFactory.replaceBlankWith(c);
									Assets.creatures.remove(i);
								}
							}						
						}

						c.update();
					}

					ArrayList projectiles = Assets.gun.getProjectiles();
					for (int i = 0; i < projectiles.size(); i++) {
						Projectile p = (Projectile) projectiles.get(i);
						if (p.isVisible() == true) {
							p.update();
						} else {
							projectiles.remove(i);
						}
					}					
				}
				
				
				if (0 >= Bullet.bullets.size()) {
					Status.gameOver();
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
		
	private int checkNumberCreatures() {
		int number = 0;
		
		for (int i = 0; i < Assets.creatures.size(); i++) {
			Creature c = (Creature) Assets.creatures.get(i);
			
			if (false == c instanceof Blank) {
				number++;
			}
		}
		
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
		g.drawString("TIME  " + String.format("%03d", Timer.time), 425, 70);
		g.drawString("HIGHS  " + String.format("%06d", 0), 725, 70);
		g.fillRect(25, 80, 974, 2);

		g.fillRect(25, 720, 974, 2);

		for (int i = 0; i < Bullet.bullets.size(); i++) {
			Bullet b = (Bullet) Bullet.bullets.get(i);
			g.drawImage(Bullet.getPic(), b.getCenterX(), b.getCenterY(), this);
		}
		
		if (Status.loadLevelLabel) {
			g.fillRect(430, 280, 173, 2);
			g.drawString("LEVEL " + String.format("%02d", Status.level), 435, 325);
			g.fillRect(430, 336, 173, 2);			
		}
		
		if (Status.keyboardImage) {
			g.drawImage(Assets.keyboard, 695, 630, this);
		}
		
		
		if (Status.showCreatures) {
			
			g.drawImage(Assets.gun.getGunPic(), Assets.gun.getCenterX() - 10, Assets.gun.getCenterY() - 100, this);
			
			for (int i = 0; i < Assets.creatures.size(); i++) {
				Creature c = (Creature) Assets.creatures.get(i);
				g.drawImage(c.getPic(), (int) c.getCenterX(), c.getCenterY(), this);
			}

			ArrayList projectiles = Assets.gun.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(new Color(238, 238, 238));
				g.fillRect(p.getX(), p.getY(), 3, 60);
			}

			if (Status.test) {
				for (int i = 0; i < Assets.creatures.size(); i++) {
					Creature c = (Creature) Assets.creatures.get(i);
					g.drawRect((int) c.rect.getX(), (int) c.rect.getY(), (int) c.rect.getWidth(), (int) c.rect.getHeight());
				}
			}			
		}
		
		if (Status.gameOverLabel) {
			g.setColor(new Color(0, 0, 170));
			g.fillRect(400, 260, 230, 76);
			g.setColor(new Color(238, 238, 238));
			g.fillRect(400, 280, 230, 2);
			g.drawString("GAME  OVER", 405, 325);			
			g.fillRect(400, 336, 230, 2);
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

		if (!Status.stop && Status.showCreatures) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				Assets.gun.moveLeft();
				Assets.gun.setMovingLeft(true);
				Assets.gun.setMovingRight(false);
				break;

			case KeyEvent.VK_RIGHT:
				Assets.gun.moveRight();
				Assets.gun.setMovingLeft(false);
				Assets.gun.setMovingRight(true);
				break;

			case KeyEvent.VK_SPACE:
				if (Assets.gun.isReadyToFire()) {
					Assets.gun.shoot();
					Assets.gun.setReadyToFire(false);
				}
				break;
			}	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (Status.readyToPlay) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Status.loadLevel();
			}
		} else if (Status.gameOver && Status.reset) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Status.startScreen();
			}
		} else if (!Status.stop && Status.showCreatures) {
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				Assets.gun.stopLeft();
				break;

			case KeyEvent.VK_RIGHT:
				Assets.gun.stopRight();
				break;

			case KeyEvent.VK_SPACE:
				Assets.gun.setReadyToFire(true);
				break;
			}

		}

	}

}
