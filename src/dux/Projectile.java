package dux;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class Projectile {

	private int x, y, speedY;
	private boolean visible;
	
	private Rectangle r;
	
	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedY = 3;
		visible = true;
		
		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		y -= speedY;
		r.setBounds(x, y, 5, 15);
		
		if (y < -20) {
		   visible = false;
		   r = null;
		}
		
		if (y > 0){
			checkCollision();
		}
	}
	
	private void checkCollision() {
		
		ArrayList ducks = Main.ducks;
		for (int i = 0; i < ducks.size(); i++) {
			Duck d = (Duck) ducks.get(i);
			
			if(r.intersects(d.rect)){
				ducks.remove(i);
				d = null;
				
				Sound.hit();
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
	
}
