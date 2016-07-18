package dux.creatures;

import java.awt.Image;
import java.awt.Rectangle;

import dux.*;

public abstract class Creature {

	private static int margins = 40;

	final public static int ADD_POINTS = 200;
	final public static int START_RIGHT = 1024 + margins;
	final public static int START_LEFT = -margins;
	final public static int START_TOP = 170;

	public static double INIT_MOVESPEED = 0.6;
	public static double INCREASE_MOVESPEED = 0.2;
	public static double MOVESPEED = 0.6;

	public Rectangle rect = new Rectangle(0, 0, 0, 0);

	final protected int OFFSET_Y = 100;
	final protected int DIRECTION_LEFT = -1;
	final protected int DIRECTION_RIGHT = 1;

	protected Image pic;

	protected Image leftPic;
	protected Image rightPic;

	protected double speedX = 0;
	protected double centerX;
	private int centerY;

	protected int row;
	protected int lives = 1;

	protected boolean replace = false;
	protected boolean remove = false;

	public void update() {
		centerX += speedX;

		if (0 >= row) {
			end();

			if (this instanceof Blank) {
				replace = true;
			}

		} else {
			if (centerX < START_LEFT) {
				updateLeft();
				customUpdate();
			}

			if (centerX > START_RIGHT) {
				updateRight();
				customUpdate();
			}
		}

		updateDefault();
	}

	protected void updateDefault() {
		rect.setRect(centerX, centerY, 25, 47);
	}

	public void initLeft() {
		centerY += OFFSET_Y;
		initSpeedRight();
		pic = rightPic;
		row--;
	}

	public void initRight() {
		centerY += OFFSET_Y;
		initSpeedLeft();
		pic = leftPic;
		row--;
	}

	public void determineSpeedDirection() {
		if (3 == row || 1 == row) {
			initSpeedRight();
		}

		if (2 == row) {
			initSpeedLeft();
		}
	}

	public void initSpeedLeft() {
		speedX = MOVESPEED * DIRECTION_LEFT;
	}

	public void initSpeedRight() {
		speedX = MOVESPEED * DIRECTION_RIGHT;
	}

	public void init() {
		initSpeedRight();
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
		Status.points += ADD_POINTS * multiplier;
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

	public boolean isReplace() {
		return replace;
	}

	public void setReplace(boolean replace) {
		this.replace = replace;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	protected void updateLeft() {
		centerX = START_LEFT;
		initLeft();
	}

	protected void updateRight() {
		centerX = START_RIGHT;
		initRight();
	}

	protected void customUpdate() {
	}

	protected void end() {
		init();
	}
}
