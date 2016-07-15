package dux;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;

import dux.creatures.Creature;
import dux.creatures.LoaderTools;

public class Assets {

	public static ArrayList<Creature> creatures = new ArrayList<Creature>();
	public static LoaderTools loaderTools;

	public static Image keyboard;
	public static Gun gun;
	public static Sound sound;
	
	public Assets(Component comp) {
		loaderTools = new LoaderTools();
		loaderTools.setComponent(comp);

		sound = new Sound();

		gun = new Gun();
		gun.setGunPic(loaderTools.loadImage("data/gun2.png"));
		Bullet.setPic(loaderTools.loadImage("data/bullet.png"));
		keyboard = loaderTools.loadImage("data/keyboard.png");
	}
}
