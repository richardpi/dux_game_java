package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dux.creatures.Duck;

import java.net.*;
import java.io.*;
import java.util.*;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;

public class Test2 extends Applet implements Runnable {

	private Image image;
	private Graphics second;

	private URL base;
	
	private Duck duck;
	private Duck duck2;
	
	Boolean imageLoaded = false;
	
	@Override
	public void init() {
		
		setSize(960, 540);
		setBackground(new Color(0, 0, 170));
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Test");

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		duck = createDuck();
		duck2 = createDuckInverse();
	}
	
	private Duck createDuck()
	{
		Image duckImage = getImage(base, "data/dux.png");
		
		Duck d = new Duck();
		d.setPic(duckImage);
		
		return d;
	}
	
	private Duck createDuckInverse()
	{ 
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
		 MediaTracker tracker = new MediaTracker(this);
		 Image sourceImage = toolkit.getImage("data/dux.png");
		 tracker.addImage(sourceImage, 0);
		 
		 try {
			 tracker.waitForAll();	 
		 } catch (InterruptedException e) {
		 }

		 BufferedImage bi = imageToBufferedImage(sourceImage);

		 BufferedImage bi2 = colorImage(bi);
		 
		 Image image = (Image) bi2;
		
		//
		Duck d = new Duck();
		d.setPic(image);
		
		return d;
	}
	
	  public static BufferedImage imageToBufferedImage(Image im) {
		     BufferedImage bi = new BufferedImage
		        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		     Graphics bg = bi.getGraphics();
		     bg.drawImage(im, 0, 0, null);
		     bg.dispose();
		     return bi;
		  }	
	
    private static BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = 255;
                pixels[1] = 0;
                pixels[2] = 0;
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
	
	@Override
	public void start() {
		
		Thread thread = new Thread(this);
		thread.start();	
		
	}

	@Override
	public void run() {
				
		while (true) {
			
			repaint();
			
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {

		g.drawImage(duck.getPic(), 500, 300, this);
		g.drawImage(duck2.getPic(), 200, 300, this);
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}