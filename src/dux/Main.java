package dux;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Graphics second;

	private URL base;

	private static Gun gun;
	private static Duck duck;

	private static Image gunPic;
	private static Image duckPic;

	@Override
	public void init() {

		setSize(640, 512);
		setBackground(Color.BLUE);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Dux");

		addKeyListener(this);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		gun = new Gun();
		gunPic = getImage(base, "data/gun.png");

		duck = new Duck();
		duckPic = getImage(base, "data/duck.png");
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (true) {
			gun.update();
			duck.update();
			repaint();
			try {
				Thread.sleep(17);
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
		g.drawImage(gunPic, gun.getCenterX() - 10, gun.getCenterY() - 100, this);
		g.drawImage(duckPic, duck.getCenterX(), duck.getCenterY(), this);
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gun.moveLeft();
				break;
	
			case KeyEvent.VK_RIGHT:
				gun.moveRight();
				break;
	
			case KeyEvent.VK_SPACE:
				System.out.println("shoot");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gun.moveStop();
	}

}
