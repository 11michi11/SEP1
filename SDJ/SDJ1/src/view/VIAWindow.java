package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VIAWindow {

	private JFrame frame;
	//private JPanel contentPane;
	
	public VIAWindow() {
		this.frame = new JFrame("VIA - Managment system");
		this.frame.setSize(900, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new StartPanel(frame);
		this.frame.setContentPane(contentPane);
		
		this.frame.setVisible(true);
	}
	
	
	public  void showWindow() {
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				VIAWindow window = new VIAWindow();
				window.frame.setVisible(true);
			}
		});
	}
}
