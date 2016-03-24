package dux;

public class Gun {

	final int MOVESPEED = 5;
	
	private int centerX = 640/2;
	private int centerY = 480;
	
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
