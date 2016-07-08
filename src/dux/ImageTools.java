package dux;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class ImageTools {

	public static Image flipImage(Image image) {
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

	public static BufferedImage imageToBufferedImage(Image im) {
		BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bi.getGraphics();
		bg.drawImage(im, 0, 0, null);
		bg.dispose();
		
		return bi;
	}

	public static BufferedImage colorImage(BufferedImage image, int[] colors) {
		int width = image.getWidth();
		int height = image.getHeight();
		WritableRaster raster = image.getRaster();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int[] pixels = raster.getPixel(xx, yy, (int[]) null);
				pixels[0] = colors[0];
				pixels[1] = colors[1];
				pixels[2] = colors[2];
				raster.setPixel(xx, yy, pixels);
			}
		}
		
		return image;
	}
}
