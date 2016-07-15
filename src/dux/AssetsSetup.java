package dux;

import dux.creatures.Creature;
import dux.creatures.CreatureFactory;

public class AssetsSetup {

	private static int ROW1 = 2;
	private static int ROW2 = 4;
	private static int ROW3 = 6;
	
	public static void setup() {
		for (int i = 0; i < Bullet.INIT_BULLETS; i++) {
			Bullet b = new Bullet();
			b.setCenterX(Bullet.START_LEFT + Bullet.SPACING * i);
			Bullet.bullets.add(b);
		}

		for (int i = 0; i < ROW1; i++) {
			CreatureFactory.initCreatures(10);
		}

		for (int i = ROW1; i < ROW2; i++) {
			CreatureFactory.initCreatures(10);
		}		
		
		for (int i = ROW2; i < ROW3; i++) {
			CreatureFactory.initCreatures(10);
		}

		// row 1
		int pos = 0;
		for (int i = 0; i < ROW1; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
		}

		// row 2
		pos = 0;
		for (int i = ROW1; i < ROW2; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_RIGHT - 55 * pos);
			c.initRight();
		}

		// row 3
		pos = 0;
		for (int i = ROW2; i < ROW3; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
			c.initRight();
			c.initLeft();
		}
	}

}
