package dux;

public class Duck {

	final int MOVESPEED = 3;
	
	private int speedX = 0;
	
	private int centerX = 640/2;
	private int centerY = 10;
	
	public void update() {
		centerX += speedX;
		
		if (centerX < -150) {
			centerX = 800;
		}
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

}
