package dux.creatures;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dux.*;

public class Duck extends Creature {

	private boolean bulletsRemoved = false;
	
	public void end() {
		if (!bulletsRemoved) {
			bulletsRemoved = true;
			setRemove(true);
			Main.stop = true;
			Bullet.bulletsRemove();			
		}

	}
	
	private void changeColor(int[] colors) {
		this.setPic(changeC(pic, colors));
		this.setLeftPic(changeC(leftPic, colors));
		this.setRightPic(changeC(rightPic, colors));
	}
	
	private Image changeC(Image i, int[] colors) {
		 BufferedImage bi = ImageTools.imageToBufferedImage(i);
		 BufferedImage bi2 = ImageTools.colorImage(bi, colors);
		 
		 return (Image) bi2;		
	}
	
	protected void customUpdate() {	
		setLives(getLives() + 1);
		updateColor();
	}
	
	public void hit() {
		updateColor();	
	}
	
	private void updateColor() {
		
		if (3 == getLives()) {
			int[] colors = new int[] {0, 238, 238};
			changeColor(colors);
		}		
		
		if (2 == getLives()) {
			int[] colors = new int[] {0, 238, 0};
			changeColor(colors);
		}

		if (1 == getLives()) {
			int[] colors = new int[] {238, 238, 0};
			changeColor(colors);
		}		
	}

}
