package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.net.*;
import java.io.*;
import java.util.*;


public class Test extends Applet implements Runnable {

	private Image image;
	private Graphics second;

	private URL base;
	
	private Duck duck;
	private Duck duck2;
	
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
		//Graphics2D g2D = (Graphics2D) g;	     
	     
		Image duckImage = getImage(base, "data/duck.png");
		
		//BufferedImage bufferedImage = toBufferedImage(duckImage);
		
		//boolean img = g2D.drawImage(bufferedImage, 0, 0, this);
		//img = image.getGraphics();
		
		Duck d = new Duck();
		d.setDuckPic(duckImage);
		
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
	
	public static BufferedImage horizontalflip(BufferedImage img) {  
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage dimg = new BufferedImage(w, h, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);  
        g.dispose();  
        return dimg;  
    }
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

}
