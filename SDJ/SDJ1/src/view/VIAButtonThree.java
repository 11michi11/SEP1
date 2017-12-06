package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VIAButtonThree extends JButton{

	private BufferedImage image;
	private static Font font = loadFont();

	public VIAButtonThree(String text) {
		super(text);
		setContentAreaFilled(false);
		loadImage("src/resources/buttonJava.jpg");
		setFont(this.font);
		VIAButtonThree btn = this;

		getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				ButtonModel model = (ButtonModel) event.getSource();

				if (model.isPressed()) {
					loadImage("src/resources/buttonJavaDark350px.jpg");
				} else {
					loadImage("src/resources/buttonJava350px.jpg");
				}
			}
		});
	}

	private void loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, 350, 100, this);
		super.paintComponent(g);
	}

	private static Font loadFont() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/VIAFont.ttf")));
			Font font = new Font("VIAFont", Font.PLAIN, 40);
			return font;
		} catch (IOException | FontFormatException e) {
			return new Font("Arial", Font.PLAIN, 40);
		}
	}

}

