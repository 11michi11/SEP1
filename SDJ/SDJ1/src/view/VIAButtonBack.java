package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VIAButtonBack extends VIAButton{

	private static final Dimension prefSize = new Dimension(50, 50);
	private static final String path = "src/resources/buttonBack.jpg";
	private JPanel parentPanel;
	private JFrame mainFrame;
	private ActionListener listener;
	
	public VIAButtonBack(JFrame mainFrame, JPanel parentPanel) {
		super("", path, prefSize);
		this.actionListener = new BackButtonListener();
		super.addActionListener(this.actionListener);
		this.parentPanel = parentPanel;
		this.mainFrame = mainFrame;
	}
	
	void goBack() {
		mainFrame.setContentPane(parentPanel);
		mainFrame.revalidate();
	}
	
	void changeListener(ActionListener listener) {
		super.removeActionListener(actionListener);
		super.addActionListener(listener);
	}
	
	class BackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainFrame.setContentPane(parentPanel);
			mainFrame.revalidate();
		}
		
	}
}
