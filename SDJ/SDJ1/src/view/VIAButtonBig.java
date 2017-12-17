package view;

import java.awt.Dimension;

public class VIAButtonBig extends VIAButton {

	private static final Dimension prefSize = new Dimension(350, 100);
	private static final String path = "src/resources/buttonBig.jpg";

	public VIAButtonBig(String text) {
		super(text, path, prefSize);
	}

	public VIAButtonBig(String text, int fontSize) {
		super(text, path, prefSize, fontSize);
	}
}
