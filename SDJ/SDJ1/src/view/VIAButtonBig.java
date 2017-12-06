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

public class VIAButtonBig extends VIAButton{

	private static final Dimension prefSize = new Dimension(350,100);
	private static final String path = "src/resources/buttonBig.jpg";

	public VIAButtonBig(String text) {
		super(text, path, prefSize);
	}
}

