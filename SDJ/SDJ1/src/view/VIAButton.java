package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class VIAButton extends JButton {

	BufferedImage image;
	
	
	public VIAButton(String text) {
		super(text);
	}

	private void loadImage() {
	    try {
	        image = ImageIO.read(new File("buttonJava.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	protected void paintComponent(Graphics g) {
		loadImage();
		g.drawImage(image, 0, 0, null);
		super.paintComponent(g);
	}
}
