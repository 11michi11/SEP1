package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	 
	private JButton createEvent;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;
	
	public MainWindow() {
		super("Main Page");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}
	
	public void createComponents(){
		
		createEvent = new JButton("EVENT");
		createEvent.setFont(new Font("Arial", Font.PLAIN, 40));
		
		newsletter = new JButton("NEWSLETTER");
		newsletter.setFont(new Font("Arial", Font.PLAIN, 40));
		
		signUp = new JButton("SIGN UP");
		signUp.setFont(new Font("Arial", Font.PLAIN, 40));
		
		listOf = new JButton("LIST OF");
		listOf.setFont(new Font("Arial", Font.PLAIN, 40));
		
		welcome = new JLabel("WELCOME");
		
		Dimension prefSize = new Dimension(400,70);
		
		newsletter.setPreferredSize(prefSize);
		createEvent.setPreferredSize(prefSize);
		signUp.setPreferredSize(prefSize);
		listOf.setPreferredSize(prefSize);
			
	}
	
	public void initializeComponents() {
		
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void registerEventHandlers() {
		
	}
	
	public void addComponentsToFrame() {
		
		VIAPanel components = new VIAPanel(new GridLayout(2, 2));
		
		JPanel wrapEvent = new JPanel();
		wrapEvent.setOpaque(false);
		wrapEvent.add(createEvent);

		JPanel wrapSignup = new JPanel();
		wrapSignup.setOpaque(false);
		wrapSignup.add(signUp);
		
		JPanel wrapNewsletter = new JPanel();
		wrapNewsletter.setOpaque(false);
		wrapNewsletter.add(newsletter);
		
		JPanel wrapList = new JPanel();
		wrapList.setOpaque(false);
		wrapList.add(listOf);
		
		components.add(wrapEvent);
		components.add(wrapSignup);
		components.add(wrapNewsletter);
		components.add(wrapList);		
		
		setContentPane(components);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow main = new MainWindow();
				main.setVisible(true);
			}
		});
	}
	

}
