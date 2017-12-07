package view;

import java.awt.Font;
import javax.swing.JLabel;

public class VIALabel extends JLabel{
	
	public VIALabel(String text, int fontSize) {
		super(text);
		setFont(new Font("Bernard MT Condensed", Font.PLAIN, fontSize));
	}

	public VIALabel(String text) {
		super(text);
		setFont(new Font("Bernard MT Condensed", Font.PLAIN, 35));
	}

}
