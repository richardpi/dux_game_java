package dux.creatures;

import java.awt.Image;
import java.util.Random;

import dux.*;

public class CreatureFactory {

	public static void initCreatures(int r) {
		int rand = new Random().nextInt(r);

		Creature c = null;
		
		switch (rand) {
		case 0:
		case 2:
			c = createOwl();
			break;
		case 1:
		case 3:
			c = createRabbit();
			break;
		case 4:
		case 6:
			c = createBlank();
			break;
		case 5:
			c = createPoints();
			break;
		//case 8:
		//	c = createDuck();
		//	break;
		}
		
		c.init();
		Main.creatures.add(c);
	}
	
	public static void replaceBlankWith(Creature b) {
		int rand = new Random().nextInt(5);

		Creature c = null;
				
		switch (rand) {
		case 0:
			c = createOwl();
			break;			
		case 1:
			c = createDuck();
			break;
		case 2:
			c = createPoints();
			break;
		case 3:
			c = createDuck();
			break;
		case 4:
			c = createRabbit();
			break;
		}
		
		c.init();
		
		Main.creatures.add(c);
	}

	public static Duck createDuck() {
		return (Duck) createCreature("data/dux.png", new Duck());
	}

	public static Owl createOwl() {
		return (Owl) createCreature("data/owl.png", new Owl());
	}

	public static Rabbit createRabbit() {
		return (Rabbit) createCreature("data/rabbit.png", new Rabbit());
	}
	
	public static Blank createBlank() {
		return new Blank();		
	}
	
	public static Points createPoints() {
		Points c = new Points();
		Image imageRight = Main.loaderTools.loadImage("data/points10.png");
		Image imageLeft = Main.loaderTools.loadImage("data/points5.png");
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);

		return c;
	}

	public static Creature createCreature(String image, Creature c) {
		Image imageRight = Main.loaderTools.loadImage(image);
		Image imageLeft = ImageTools.flipImage(imageRight);
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);
		
		return c;
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
