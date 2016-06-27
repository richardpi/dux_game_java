package dux;

import java.awt.Rectangle;
import java.io.File;

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
		if(r.intersects(Main.duck.rect)){
			visible = false;
			//destroy or make it not move
			Main.duck.setCenterX(800);
			
			try {
				Player playerHit = Manager.createPlayer(new MediaLocator(new File("data/hit.mp3").toURI().toURL()));
				playerHit.start();
			}
			catch(Exception ex){
				ex.printStackTrace();
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
