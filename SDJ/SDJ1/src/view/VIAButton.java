package view;

import java.awt.Dimension;
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

public abstract class VIAButton extends JButton {

	protected BufferedImage image;
	protected static Font font = loadFont();
	protected int fontSize;
	protected String FILE_PATH;
	private String darkPath;
	protected Dimension prefSize;

	public VIAButton(String text, String path, Dimension prefSize) {
		super(text);
		this.FILE_PATH = path;
		darkPath = getDarkPath();
		setContentAreaFilled(false);
		loadImage(FILE_PATH);
		setFont(this.font);
		this.fontSize = 40;
		this.prefSize = prefSize;
		setPreferredSize(prefSize);
		
		VIAButton btn = this;

		getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				ButtonModel model = (ButtonModel) event.getSource();

				if (model.isPressed()) {
					loadImage(darkPath);
				} else {
					loadImage(FILE_PATH);
				}
			}
		});
	}

	public void setFontSize(int size) {
		this.fontSize = size;
		setFont(new Font("VIAFont", Font.PLAIN, size));
	}

	protected void loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, prefSize.width, prefSize.height, this);
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
	
	private String getDarkPath() {
		String dark = String.valueOf(this.FILE_PATH);
		dark = dark.substring(0, dark.length()-4);
		dark += "Dark.jpg";
		return dark;
	}

}
