package dux.creatures;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dux.*;
import dux.utils.ImageTools;

public class Duck extends Creature {

	private boolean bulletsRemoved = false;

	public void hit() {
		updateColor();
	}

	protected void end() {
		if (!bulletsRemoved) {
			bulletsRemoved = true;
			setRemove(true);
			Status.stop = true;
			Bullet.bulletsRemove();
		}

	}

	protected void customUpdate() {
		setLives(getLives() + 1);
		updateColor();
	}

	private void changeColor(int[] colors) {
		this.setPic(changeC(pic, colors));
		this.setLeftPic(changeC(leftPic, colors));
		this.setRightPic(changeC(rightPic, colors));
	}

	private Image changeC(Image i, int[] colors) {
		BufferedImage bi = ImageTools.imageToBufferedImage(i);
		BufferedImage bi2 = ImageTools.colorImage(bi, colors);

		return bi2;
	}

	private void updateColor() {

		if (3 == getLives()) {
			int[] colors = new int[] { 0, 238, 238 };
			changeColor(colors);
		}

		if (2 == getLives()) {
			int[] colors = new int[] { 0, 238, 0 };
			changeColor(colors);
		}

		if (1 == getLives()) {
			int[] colors = new int[] { 238, 238, 0 };
			changeColor(colors);
		}
	}

}
