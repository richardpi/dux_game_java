package dux.utils;

import dux.Main;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.*;
import java.net.URL;

public class LoaderTools {

	public Component comp;

	public Image loadImage(String path) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(comp);

        URL url = Main.classLoader.getResource(path);
        Image sourceImage = toolkit.getImage(url);
        tracker.addImage(sourceImage, 0);

		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}

		return sourceImage;
	}

	public void setComponent(Component comp) {
		this.comp = comp;
	}
}
