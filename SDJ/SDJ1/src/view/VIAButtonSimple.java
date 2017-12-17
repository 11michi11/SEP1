package view;

import java.awt.Dimension;

public class VIAButtonSimple extends VIAButton {

	private static final Dimension prefSize = new Dimension(350, 50);
	private static final String path = "src/resources/button.jpg";

	public VIAButtonSimple(String text) {
		super(text, path, prefSize);
	}

	public VIAButtonSimple(String text, int fontSize) {
		super(text, path, prefSize, fontSize);
	}
}
