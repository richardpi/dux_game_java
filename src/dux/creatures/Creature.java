package dux.creatures;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Creature {

	private static int margins = 50;
	
	final public static int ADD_POINTS = 200;	
	final public static int START_RIGHT = 1024 + margins;
	final public static int START_LEFT = -margins;
	final public static int START_TOP = 170;
	
	public static int points = 0;	
	
	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	protected Image pic;
	
	protected Image leftPic;
	protected Image rightPic;	
	
	final double MOVESPEED = 0.5;
	final int OFFSET_Y = 100;
	
	final int DIRECTION_LEFT = -1;
	final int DIRECTION_RIGHT = 1;
	
	protected double speedX = 0;
	
	protected double centerX;
	private int centerY;
	
	protected int row;
	
	protected int lives = 1;
	
	public void update() {
		centerX += speedX;
		
		if (0 >= row) {
			init();
		} else {
			if (centerX < START_LEFT) {
				updateLeft();
			}
			
			if (centerX > START_RIGHT) {
				updateRight();
			}			
		}
		
		updateDefault();
	}
	
	protected void updateDefault() {
		rect.setRect(centerX , centerY, 25, 47);
	}
	
	public void initLeft() {
		centerY += OFFSET_Y;
		speedX = MOVESPEED * DIRECTION_RIGHT;
		pic = rightPic;
		row--;		
	}
	
	public void initRight() {
		centerY += OFFSET_Y;
		speedX = MOVESPEED * DIRECTION_LEFT;
		pic = leftPic;
		row--;		
	}
	
	protected void updateLeft() {
		centerX = START_LEFT;
		initLeft();
	}
	
	protected void updateRight() {
		centerX = START_RIGHT;
		initRight();
	}
	
	public void init() {
		speedX = MOVESPEED * DIRECTION_RIGHT;
		pic = rightPic;
		centerX = START_LEFT;
		centerY = START_TOP;
		row = 3;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void hit() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
