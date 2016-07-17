package dux;

import java.awt.Image;
import java.util.ArrayList;
import java.util.TimerTask;

import dux.creatures.Creature;

public class Bullet {

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public static int INIT_BULLETS = 80;
	public static int START_LEFT = 33;
	public static int SPACING = 12;

	public static boolean countHelper = false;
	
	private static Image pic;
	
	private static int bulletCounter = 0;
	
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

	//bullets removed after duck reaches end
	public static void bulletsRemove() {
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

		            @Override
		            public void run() {

		            	bulletCounter++;
		            	Bullet.remove();
		            	Sound.bulletRemove();
		            	
		            	//System.out.println(bulletCounter);
		                if (5 <= bulletCounter) {
		                	Status.stop = false;
		                	bulletCounter = 0;
		                    t.cancel();
		                    t.purge();
		                }
		                
		            }
		        }, 1, 300);

	}
	
	//when level finished, add points for each bullet
	public static void bulletsCountPoints() {

		countHelper = false;
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

		            @Override
		            public void run() {

		            	Bullet.remove();
		            	Creature.points += 100;
		            	
		                if (0 >= bullets.size()) {
		                    t.cancel();
		                    t.purge();
		
		                    countHelper = true;		                    
		                }
		                
		            }
		        }, 1, 20);
		
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
