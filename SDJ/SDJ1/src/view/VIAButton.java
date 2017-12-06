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

public class VIAButton extends JButton {

	private BufferedImage image;
	private static Font font = loadFont();
	private int fontSize;

	public VIAButton(String text) {
		super(text);
		setContentAreaFilled(false);
		loadImage("src/resources/button.jpg");
		setFont(this.font);
		this.fontSize = 40;
		VIAButton btn = this;

		getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				ButtonModel model = (ButtonModel) event.getSource();

				if (model.isPressed()) {
					loadImage("src/resources/buttonDark.jpg");
				} else {
					loadImage("src/resources/button.jpg");
				}
			}
		});
	}
	
	public void setFontSize(int size) {
		this.fontSize = size;
		setFont(new Font("VIAFont", Font.PLAIN, size));
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
		g.drawImage(image, 0, 0, 400, 70, this);
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
