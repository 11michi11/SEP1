package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VIAButton extends JButton {

	BufferedImage image;

	public VIAButton(String text) {
		super(text);
		setContentAreaFilled(false);

		VIAButton btn = this;

		getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();

				if (model.isRollover()) {
					btn.setBorderPainted(true);
				} else if (model.isPressed()) {

				} else {
					
				}
			}
		});
	}

	private void loadImage() {
		try {
			image = ImageIO.read(new File("src/resources/buttonJava.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		loadImage();
		g.drawImage(image, 0, 0, 400, 70, this);
		super.paintComponent(g);
	}
}
