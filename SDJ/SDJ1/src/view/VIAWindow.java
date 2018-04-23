package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controler.VIAController;

public class VIAWindow implements ViewManager {

	private JFrame frame;

	public VIAWindow() {
		this.frame = new JFrame("VIA - Managment system");
		this.frame.setSize(900, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new StartPanel(frame);
		this.frame.setContentPane(contentPane);

		this.frame.setVisible(true);

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				try {
					VIAController.performOpeningOperations();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					VIAController.performClosingOperations();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}
		});
	}

	
	@Override
	public void showStartPanel() {
		
	}

}
