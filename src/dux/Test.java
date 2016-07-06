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

import java.net.*;
import java.io.*;
import java.util.*;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;

public class Test extends Applet implements Runnable {

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
		Image duckImage = getImage(base, "data/duck.png");
		
		Duck d = new Duck();
		d.setDuckPic(duckImage);
		
		return d;
	}
	
	private Duck createDuckInverse()
	{ 
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
		 MediaTracker tracker = new MediaTracker(this);
		 Image sourceImage = toolkit.getImage("data/duck.png");
		 tracker.addImage(sourceImage, 0);
		 
		 try {
			 tracker.waitForAll();	 
		 } catch (InterruptedException e) {
		 }

	
		// Create a buffered image from the source image with a format that's
		// compatible with the screen

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();

		// If the source image has no alpha info use Transparency.OPAQUE instead

		BufferedImage img = graphicsConfiguration.createCompatibleImage(sourceImage.getWidth(null), sourceImage.getHeight(null),
				Transparency.BITMASK);

		// Copy image to buffered image

		Graphics graphics = img.createGraphics();

		// Paint the image onto the buffered image

		graphics.drawImage(sourceImage, 0, 0, null);

		graphics.dispose();

		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);

		tx.translate(-img.getWidth(null), 0);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		img = op.filter(img, null);
		
		//Image duckImage = getImage(base, "data/duck.png");
		Duck d = new Duck();
		d.setDuckPic(img);
		
		return d;
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

		g.drawImage(duck.getDuckPic(), 500, 300, this);
		g.drawImage(duck2.getDuckPic(), 200, 300, this);
		
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
