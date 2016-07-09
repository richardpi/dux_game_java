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
	
	public static void add(int bullets) {
		
		int addBullets = bullets;
		
		if (Bullet.bullets.size() + bullets > Bullet.INIT_BULLETS) {
			addBullets = Bullet.INIT_BULLETS - Bullet.bullets.size();
		}
		
		for (int i = 0; i < addBullets; i++) {			
			Bullet lastBullet = Bullet.bullets.get(Bullet.bullets.size() - 1);
			
			Bullet b = new Bullet();
			b.setCenterX(lastBullet.getCenterX() + Bullet.SPACING);
			Bullet.bullets.add(b);
		}
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
