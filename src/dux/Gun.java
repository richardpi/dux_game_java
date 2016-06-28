package dux;

import java.io.File;
import java.util.ArrayList;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class Gun {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	final int MOVESPEED = 2;
	
	private int speedX = 0;
	
	private int centerX = 640/2;
	private int centerY = 480;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	public void update() {
		centerX += speedX;
	}
	
 	public void shoot() {
		Projectile p = new Projectile(centerX, centerY - 130);
		projectiles.add(p);
		
		try {
			Player playerShoot = Manager.createPlayer(new MediaLocator(new File("data/shoot.mp3").toURI().toURL()));
			playerShoot.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
 	
	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

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
 	
	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

}
