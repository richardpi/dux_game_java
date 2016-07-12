package dux.creatures;

import java.awt.Image;
import java.util.Random;

import dux.*;

public class CreatureFactory {

	public static void initCreatures(int r) {
		int rand = new Random().nextInt(r);

		switch (rand) {
		case 0:
		case 2:
			createOwl();
			break;
		case 1:
		case 3:
			createRabbit();
			break;
		case 4:
		case 6:
			createBlank();
			break;
		case 5:
			createPoints();
			break;
		case 7:
			createDuck();
			break;
		}
	}

	public static void createDuck() {
		createCreature("data/dux.png", new Duck());
	}

	public static void createOwl() {
		createCreature("data/owl.png", new Owl());
	}

	public static void createRabbit() {
		createCreature("data/rabbit.png", new Rabbit());
	}
	
	public static void createBlank() {
		Creature c = new Blank();
		c.init();
		Main.creatures.add(c);		
	}
	
	public static void createPoints() {
		Creature c = new Points();
		Image imageRight = Main.loaderTools.loadImage("data/points10.png");
		Image imageLeft = Main.loaderTools.loadImage("data/points5.png");
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);
		c.init();
		Main.creatures.add(c);
	}

	public static void createCreature(String image, Creature c) {
		Image imageRight = Main.loaderTools.loadImage(image);
		Image imageLeft = ImageTools.flipImage(imageRight);
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);
		c.init();
		Main.creatures.add(c);
	}
	
	public static void createReplacementBlank(Creature c) {
		Blank blank = new Blank();
		blank.setCenterX(c.getCenterX());
		blank.setCenterY(c.getCenterY());
		blank.setRow(c.getRow());
		blank.determineSpeedDirection();
		Main.creatures.add(blank);
	}

}
