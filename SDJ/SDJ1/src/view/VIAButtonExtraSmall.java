package view;

import java.awt.Dimension;

public class VIAButtonExtraSmall extends VIAButton {

	private static final Dimension prefSize = new Dimension(135, 35);
	private static final String path = "src/resources/buttonExtraSmall.jpg";

	public VIAButtonExtraSmall(String text) {
		super(text, path, prefSize);
	}

	public VIAButtonExtraSmall(String text, int fontSize) {
		super(text, path, prefSize, fontSize);
	}

}
