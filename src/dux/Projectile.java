package dux;

import java.awt.Rectangle;
import java.util.ArrayList;

import dux.creatures.*;

public class Projectile {

	private int x, y, speedY;
	private boolean visible;
	
	private Rectangle r;
	
	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedY = 9;
		visible = true;
		
		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		y -= speedY;
		r.setBounds(x, y, 3, 60);
		
		if (y < 90) {
			removeBullet();
		}
		
		if (y > 90) {
			checkCollision();
		}
	}
	
	private void checkCollision() {
		
		ArrayList creatures = Assets.creatures;
		for (int i = 0; i < creatures.size(); i++) {
			Creature c = (Creature) creatures.get(i);
			
			if (r != null) {
				if(r.intersects(c.rect)){

					c.setLives(c.getLives() - 1);
					c.hit();
					Creature.addPoints(c.getRow());
					Sound.hit();
					
					if (0 >= c.getLives()) {						
						CreatureFactory.createReplacementBlank(c);
						creatures.remove(i);
						c = null;
					}
					
					removeBullet();				
				}				
			}
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}	
	
	private void removeBullet() {
		visible = false;
		r = null;		
	}
}
