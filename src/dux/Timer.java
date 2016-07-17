package dux;

import java.util.*;

import dux.creatures.Creature;

public class Timer {

	public static int INIT_TIME = 250;
	public static int time = 0;
	
	private static java.util.Timer t;
	
	public static void start() {
		t = new java.util.Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				if (!Status.stop) {
					time--;
					
					if (0 >= time) {
						Status.gameOver();
						
						purge();						
					}
				}
			}
		}, 1, 300);		
	}
	
	public static void applyInitTime() {
		time = INIT_TIME;
	}

	public static void purge() {
		t.cancel();
		t.purge();		
	}
	
	public static void timeCountPoints() {
		
		time = time - (time % 10);
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

		            @Override
		            public void run() {

		            	time -= 10;
		            	Creature.points += 100;
		            	
		                if (0 >= time) {
		                	time = 0;
		                    t.cancel();
		                    t.purge();
		                }
		                
		            }
		        }, 1, 20);		
	}
}
