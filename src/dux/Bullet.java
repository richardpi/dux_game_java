package dux;

import java.awt.Image;
import java.util.ArrayList;

public class Bullet {

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public static int INIT_BULLETS = 80;
	public static int START_LEFT = 33;
	public static int SPACING = 12;

	private static Image pic;
	
	private int centerX;
	private int centerY = 731;

	public static void remove() {
		bullets.remove(bullets.size() - 1);
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
	
	public static void setPic(Image p) {
		pic = p;
	}
	
	public static Image getPic() {
		return pic;
	}
}
