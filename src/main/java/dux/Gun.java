package dux;

import java.awt.Image;
import java.util.ArrayList;

public class Gun {

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	final static int BORDER_LEFT = 30;
	final static int BORDER_RIGHT = 996;

	final private int MOVESPEED = 2;

	private Image gunPic;

	private int speedX = 0;
	private int centerX = 1024 / 2;
	private int centerY = 770;

	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean readyToFire = true;

	public void update() {

		if (centerX > BORDER_RIGHT) {
			centerX = BORDER_RIGHT;
		}

		if (centerX < BORDER_LEFT) {
			centerX = BORDER_LEFT;
		}

		centerX += speedX;
	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX - 8, centerY - 140);
			projectiles.add(p);

			Bullet.remove();
			Sound.shoot();
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

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public void setGunPic(Image gunPic) {
		this.gunPic = gunPic;
	}

	public Image getGunPic() {
		return this.gunPic;
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

}
