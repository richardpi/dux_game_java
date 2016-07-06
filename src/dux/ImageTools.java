package dux;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class ImageTools {
	
	public static Image flipImage(Image image)
	{	
		// Create a buffered image from the source image with a format that's
		// compatible with the screen

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();

		// If the source image has no alpha info use Transparency.OPAQUE instead

		BufferedImage img = graphicsConfiguration.createCompatibleImage(image.getWidth(null), image.getHeight(null),
				Transparency.BITMASK);

		// Copy image to buffered image

		Graphics graphics = img.createGraphics();

		// Paint the image onto the buffered image

		graphics.drawImage(image, 0, 0, null);

		graphics.dispose();

		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);

		tx.translate(-img.getWidth(null), 0);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		img = op.filter(img, null);
		
		return img;
	}
}
