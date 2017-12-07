package view;

import java.awt.Dimension;

public class VIAButtonBack extends VIAButton{

	private static final Dimension prefSize = new Dimension(50, 50);
	private static final String path = "src/resources/buttonBack.jpg";
	
	public VIAButtonBack(String text) {
		super(text, path, prefSize);
	}
}
