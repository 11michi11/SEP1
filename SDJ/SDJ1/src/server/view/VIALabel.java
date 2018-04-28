package server.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class VIALabel extends JLabel {

	public VIALabel(String text, int fontSize) {
		super(text);
		setFont(new Font("Bernard MT Condensed", Font.PLAIN, fontSize));
		setForeground(Color.BLACK);
	}

	public VIALabel(String text) {
		super(text);
		setFont(new Font("Bernard MT Condensed", Font.PLAIN, 35));
		setForeground(Color.BLACK);
	}

}
