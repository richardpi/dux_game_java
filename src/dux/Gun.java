package dux;

import java.util.ArrayList;

public class Gun {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	final int MOVESPEED = 5;
	
	private int speedX = 0;
	
	private int centerX = 640/2;
	private int centerY = 480;
	
	public void update() {
		centerX += speedX;
	}
	
 	public void shoot() {
		Projectile p = new Projectile(centerX, centerY - 130);
		projectiles.add(p);
	}
	
	public int getCenterX() {
		return centerX;
	}
	
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	
	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public void moveRight() {
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
	}
	
	public void moveStop() {
		speedX = 0;
	}
	
 	public ArrayList getProjectiles() {
		return projectiles;
	}	
}
