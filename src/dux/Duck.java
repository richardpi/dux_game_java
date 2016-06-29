package dux;

import java.awt.Image;
import java.awt.Rectangle;

public class Duck {

	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	private Image duckPic;
	
	final int MOVESPEED = 1;
	final int OFFSET_Y = 130;
	
	final int DIRECTION_LEFT = -1;
	final int DIRECTION_RIGHT = 1;
	
	final int START_RIGHT = 790;
	final int START_LEFT = -250;
	
	private int speedX = 0;
	
	private int centerX = START_RIGHT;
	private int centerY = 10;
	
	public void update() {
		centerX += speedX;
		
		if (centerX < START_LEFT) {
			centerX = START_LEFT;
			centerY += OFFSET_Y;
			speedX = MOVESPEED * DIRECTION_RIGHT;
		}
		
		if (centerX > START_RIGHT) {
			centerX = START_RIGHT;
			centerY += OFFSET_Y;
			speedX = MOVESPEED * DIRECTION_LEFT;			
		}
		
		rect.setRect(centerX + 10, centerY + 5, 120, 120);
	}
	
	public Duck() {
		speedX = MOVESPEED * DIRECTION_LEFT;
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
}
