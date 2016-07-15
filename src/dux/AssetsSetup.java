package dux;

import dux.creatures.Creature;
import dux.creatures.CreatureFactory;

public class AssetsSetup {

	public static void setup() {
		for (int i = 0; i < Bullet.INIT_BULLETS; i++) {
			Bullet b = new Bullet();
			b.setCenterX(Bullet.START_LEFT + Bullet.SPACING * i);
			Bullet.bullets.add(b);
		}

		for (int i = 0; i < 40; i++) {
			CreatureFactory.initCreatures(7);
		}

		for (int i = 40; i < 60; i++) {
			CreatureFactory.initCreatures(6);
		}

		// row 1
		int pos = 0;
		for (int i = 0; i < 20; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
		}

		// row 2
		pos = 0;
		for (int i = 20; i < 40; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_RIGHT - 55 * pos);
			c.initRight();
		}

		// row 3
		pos = 0;
		for (int i = 40; i < 60; i++) {
			pos++;
			Creature c = (Creature) Assets.creatures.get(i);
			c.setCenterX(Creature.START_LEFT + 55 * pos);
			c.initRight();
			c.initLeft();
		}
	}

}
