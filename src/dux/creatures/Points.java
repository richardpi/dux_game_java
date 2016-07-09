package dux.creatures;

import dux.*;

public class Points extends Creature {

	public void hit() {

		if (3 == row) {
			Bullet.add(10);
		}

		if (2 == row) {
			Bullet.add(5);
		}

	}
	
	public int getCenterY() {
		return super.getCenterY() + 7;
	}

}
