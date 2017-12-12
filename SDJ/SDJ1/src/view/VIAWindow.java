package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controler.VIAController;

public class VIAWindow {

	private JFrame frame;
	
	public VIAWindow() {
		this.frame = new JFrame("VIA - Managment system");
		this.frame.setSize(900, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new StartPanel(frame);
		this.frame.setContentPane(contentPane);
		
		this.frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener() {
		    
		    @Override
		    public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			try {
			    VIAController.performOpeningOperations();
			} catch (ClassNotFoundException | IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		    
		    @Override
		    public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		    }
		    
		    @Override
		    public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		    }
		    
		    @Override
		    public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		    }
		    
		    @Override
		    public void windowClosing(WindowEvent e) {
			try {
			    VIAController.performClosingOperations();
			} catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		    
		    @Override
		    public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		    }
		    
		    @Override
		    public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		    }
		});
	}
	
}
