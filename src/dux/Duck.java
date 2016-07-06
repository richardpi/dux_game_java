package dux;

import java.awt.Image;
import java.awt.Rectangle;

public class Duck {

	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	private Image duckPic;
	
	private Image duckLeftPic;
	private Image duckRightPic;	
	
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
			duckPic = duckRightPic;
		}
		
		if (centerX > START_RIGHT) {
			centerX = START_RIGHT;
			centerY += OFFSET_Y;
			speedX = MOVESPEED * DIRECTION_LEFT;
			duckPic = duckLeftPic;
		}
		
		rect.setRect(centerX , centerY, 25, 47);
	}
	
	public Duck() {
	}
	
	public void init() {
		speedX = MOVESPEED * DIRECTION_RIGHT;
		duckPic = duckRightPic;
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

	public Image getDuckPic() {
		return duckPic;
	}

	public void setDuckPic(Image duckPic) {
		this.duckPic = duckPic;
	}

	public Image getDuckLeftPic() {
		return duckLeftPic;
	}

	public void setDuckLeftPic(Image duckLeftPic) {
		this.duckLeftPic = duckLeftPic;
	}

	public Image getDuckRightPic() {
		return duckRightPic;
	}

	public void setDuckRightPic(Image duckRightPic) {
		this.duckRightPic = duckRightPic;
	}
}
