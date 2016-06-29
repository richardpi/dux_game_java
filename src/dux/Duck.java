package dux;

import java.awt.Image;
import java.awt.Rectangle;

public class Duck {

	public Rectangle rect = new Rectangle(0, 0, 0, 0);
	
	private Image duckPic;
	
	final int MOVESPEED = 1;
	final int INIT_START_X = 790;
	
	private int speedX = 0;
	
	private int centerX = INIT_START_X;
	private int centerY = 10;
	
	public void update() {
		centerX += speedX;
		
		if (centerX < -150) {
			centerX = INIT_START_X;
		}
		
		rect.setRect(centerX + 10, centerY + 5, 120, 120);
	}
	
	public Duck() {
		speedX = -MOVESPEED;
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
