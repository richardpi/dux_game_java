package dux;

import java.util.TimerTask;

public class Status {

	public static boolean test = false;

	public static boolean stop = false;
	public static boolean showCreatures = false;
	public static boolean gameOverLabel = false;
	public static boolean gameOver = false;
	public static boolean loadLevelLabel = false;
	public static boolean keyboardImage = false;
	public static boolean readyToPlay = false;
	public static boolean reset = false;
	
	public static int level = 1;	
	
	public static void startScreen() {
		Assets.creatures.clear();
		Bullet.bullets.clear();
		AssetsSetup.setup();
		Timer.applyInitTime();
		
		stop = true;
		showCreatures = false;
		gameOverLabel = true;
		gameOver = false;
		loadLevelLabel = false;
		keyboardImage = true;
		readyToPlay = true;
		reset = false;
	}
	
	public static void gameOver() {
		stop = true;
		showCreatures = true;
		gameOverLabel = true;
		gameOver = true;
		loadLevelLabel = false;
		keyboardImage = false;
		readyToPlay = false;
		reset = false;
		
		Timer.purge();
		
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				reset = true;
				t.cancel();
				t.purge();					
			}
		}, 2500, 1);
		
		Sound.gameOver();
	}
	
	public static void playGame() {
		stop = false;
		showCreatures = true;
		gameOverLabel = false;
		gameOver = false;
		loadLevelLabel = false;
		keyboardImage = false;
		readyToPlay = false;
		reset = false;
		
		Sound.music();
		Timer.start();
	}

	public static void loadLevel() {
		stop = false;
		showCreatures = false;
		gameOverLabel = false;
		gameOver = false;
		loadLevelLabel = true;
		keyboardImage = false;		
		readyToPlay = false;
		reset = false;
		
		Sound.start();

		Thread waitToPlay = new Thread() {
		    public void run() {
				boolean wait = true;

				while (wait) {
					
					if (Sound.startClip.getMicrosecondLength() <= Sound.startClip.getMicrosecondPosition()) {
						wait = false;
						playGame();
					}
					
				}
		    }  
		};

		waitToPlay.start();
	}

}
