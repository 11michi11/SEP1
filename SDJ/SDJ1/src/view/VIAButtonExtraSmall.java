package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.VIAButtonBack.BackButtonListaner;

public class VIAButtonExtraSmall extends VIAButton{

	private static final Dimension prefSize = new Dimension(135,35);
	private static final String path = "src/resources/buttonExtraSmall.jpg";

	public VIAButtonExtraSmall(String text) {
		super(text, path , prefSize);
	}
	
	public VIAButtonExtraSmall(String text, int fontSize) {
		super(text, path , prefSize, fontSize);
	}


}
