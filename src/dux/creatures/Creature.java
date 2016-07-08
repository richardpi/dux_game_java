package dux.creatures;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Creature {

	public static int points = 0;
	
	public static int ADD_POINTS = 200;	
	
	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	protected Image pic;
	
	protected Image leftPic;
	protected Image rightPic;	
	
	final int MOVESPEED = 1;
	final int OFFSET_Y = 80;
	
	final int DIRECTION_LEFT = -1;
	final int DIRECTION_RIGHT = 1;
	
	final int START_RIGHT = 790;
	final int START_LEFT = -250;
	
	protected int speedX = 0;
	
	protected int centerX;
	private int centerY = 200;
	
	private int pointsMultiplier;
	
	/* temp? */
	protected int row = 3;
	
	protected int lives = 1;
	
	public void update() {
		centerX += speedX;
		
		if (centerX < START_LEFT) {
			updateLeft();
		}
		
		if (centerX > START_RIGHT) {
			updateRight();
		}
		
		updateDefault();
	}
	
	protected void updateDefault() {
		rect.setRect(centerX , centerY, 25, 47);
	}
	
	protected void updateLeft() {
		centerX = START_LEFT;
		centerY += OFFSET_Y;
		speedX = MOVESPEED * DIRECTION_RIGHT;
		pic = rightPic;
		pointsMultiplier--;
		row--;		
	}
	
	protected void updateRight() {
		centerX = START_RIGHT;
		centerY += OFFSET_Y;
		speedX = MOVESPEED * DIRECTION_LEFT;
		pic = leftPic;
		pointsMultiplier--;
		row--;		
	}
	
	public void init() {
		speedX = MOVESPEED * DIRECTION_RIGHT;
		pic = rightPic;
		centerX = START_LEFT;
		pointsMultiplier = 3;
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

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public Image getLeftPic() {
		return leftPic;
	}

	public void setLeftPic(Image leftPic) {
		this.leftPic = leftPic;
	}

	public Image getRightPic() {
		return rightPic;
	}

	public void setRightPic(Image rightPic) {
		this.rightPic = rightPic;
	}
	
	public static void addPoints(int multiplier) {
		points += ADD_POINTS * multiplier;
	}

	public int getPointsMultiplier() {
		return pointsMultiplier;
	}

	public void setPointsMultiplier(int pointsMultiplier) {
		this.pointsMultiplier = pointsMultiplier;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void hit() {
	}
}
