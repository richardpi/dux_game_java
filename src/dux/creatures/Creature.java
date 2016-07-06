package dux.creatures;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Creature {

	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	private Image pic;
	
	private Image leftPic;
	private Image rightPic;	
	
	final int MOVESPEED = 1;
	final int OFFSET_Y = 130;
	
	final int DIRECTION_LEFT = -1;
	final int DIRECTION_RIGHT = 1;
	
	final int START_RIGHT = 790;
	final int START_LEFT = -250;
	
	private int speedX = 0;
	
	private int centerX;
	private int centerY = 10;
	
	public void update() {
		centerX += speedX;
		
		if (centerX < START_LEFT) {
			centerX = START_LEFT;
			centerY += OFFSET_Y;
			speedX = MOVESPEED * DIRECTION_RIGHT;
			pic = rightPic;
		}
		
		if (centerX > START_RIGHT) {
			centerX = START_RIGHT;
			centerY += OFFSET_Y;
			speedX = MOVESPEED * DIRECTION_LEFT;
			pic = leftPic;
		}
		
		rect.setRect(centerX , centerY, 25, 47);
	}
	
	public void init() {
		speedX = MOVESPEED * DIRECTION_RIGHT;
		pic = rightPic;
		centerX = START_LEFT;
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
}
