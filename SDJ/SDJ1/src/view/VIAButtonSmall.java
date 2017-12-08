package view;

import java.awt.Dimension;

public class VIAButtonSmall extends VIAButton{
	
	private static final Dimension prefSize = new Dimension(300,100);
	private static final String path = "src/resources/buttonSmall.jpg";

	public VIAButtonSmall(String text) {
		super(text, path , prefSize);
	}
	
	public VIAButtonSmall(String text, int fontSize) {
		super(text, path , prefSize, fontSize);
	}


}
