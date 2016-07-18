package dux;

import java.awt.Image;
import java.util.Random;

import dux.creatures.*;
import dux.utils.ImageTools;

public class CreatureFactory {

	public static void initCreatures(int r) {
		int rand = new Random().nextInt(r);

		Creature c = null;

		switch (rand) {
		case 0:
		case 2:
		case 7:
			c = createOwl();
			break;
		case 1:
		case 3:
		case 8:
			c = createRabbit();
			break;
		case 4:
		case 6:
		case 9:
			c = createBlank();
			break;
		case 5:
			c = createPoints();
			break;
		}

		c.init();
		Assets.creatures.add(c);
	}

	public static void replaceBlankWith(Creature b) {
		int rand = new Random().nextInt(7);

		Creature c = null;

		switch (rand) {
		case 0:
		case 5:
			c = createOwl();
			break;
		case 1:
		case 3:
			c = createDuck();
			break;
		case 2:
			c = createPoints();
			break;
		case 4:
		case 6:
			c = createRabbit();
			break;
		}

		c.init();

		Assets.creatures.add(c);
	}

	public static Duck createDuck() {
		return (Duck) createCreature("data/graphics/dux.png", new Duck());
	}

	public static Owl createOwl() {
		return (Owl) createCreature("data/graphics/owl.png", new Owl());
	}

	public static Rabbit createRabbit() {
		return (Rabbit) createCreature("data/graphics/rabbit.png", new Rabbit());
	}

	public static Blank createBlank() {
		return new Blank();
	}

	public static Points createPoints() {
		Points c = new Points();
		Image imageRight = Assets.loaderTools.loadImage("data/graphics/points10.png");
		Image imageLeft = Assets.loaderTools.loadImage("data/graphics/points5.png");
		c.setRightPic(imageRight);
		c.setLeftPic(imageLeft);

		return c;
	}

	public static Creature createCreature(String image, Creature c) {
		Image imageRight = Assets.loaderTools.loadImage(image);
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
		Assets.creatures.add(blank);
	}

}
