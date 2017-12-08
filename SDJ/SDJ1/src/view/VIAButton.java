package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
	protected Font font;
	protected String FILE_PATH;
	protected Dimension prefSize;
	private String darkPath;
	private int fontSize;

	public VIAButton(String text, String path, Dimension prefSize) {
		super(text);
		this.fontSize = 40;
		initializeVIAButton(path, prefSize);
	}
	
	public VIAButton(String text, String path, Dimension prefSize, int fontSize) {
		super(text);
		this.fontSize = fontSize;
		initializeVIAButton(path, prefSize);
	}
	
	
	private void initializeVIAButton(String path, Dimension prefSize) {
		this.FILE_PATH = path;
		this.font = new Font("Bernard MT Condensed", Font.PLAIN, fontSize);
		setFont(font);
		setForeground(Color.BLACK);
		darkPath = getDarkPath();
		setContentAreaFilled(false);
		loadImage(FILE_PATH);
		this.prefSize = prefSize;
		setPreferredSize(prefSize);
	
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
		this.font = new Font("Bernard MT Condensed", Font.PLAIN, fontSize);
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
		g.drawImage(image, 0, 0, prefSize.width, prefSize.height, this);
		super.paintComponent(g);
	}
	
	private String getDarkPath() {
		String dark = String.valueOf(this.FILE_PATH);
		dark = dark.substring(0, dark.length()-4);
		dark += "Dark.jpg";
		return dark;
	}

}
