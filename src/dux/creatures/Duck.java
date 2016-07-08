package dux.creatures;

import java.awt.Image;
import java.awt.image.BufferedImage;

import dux.ImageTools;

public class Duck extends Creature {

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
	
	public void update() {
		centerX += speedX;
		
		if (centerX < START_LEFT) {
			updateLeft();
			setLives(getLives() + 1);
			updateColor();
		}
		
		if (centerX > START_RIGHT) {
			updateRight();
			setLives(getLives() + 1);
			updateColor();
		}
		
		updateDefault();
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
