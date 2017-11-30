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

public class MainPage extends JFrame{
	 
	private JButton createEvent;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;
	
	public MainPage() {
		super("Main Page");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}
	
	public void createComponents(){
		
		createEvent = new JButton("EVENT");
		createEvent.setFont(new Font("Arial", Font.PLAIN, 40));
		createEvent.setBackground(Color.RED);
		
		newsletter = new JButton("NEWSLETTER");
		newsletter.setFont(new Font("Arial", Font.PLAIN, 40));
		newsletter.setBackground(Color.RED);
		
		signUp = new JButton("SIGN UP");
		signUp.setFont(new Font("Arial", Font.PLAIN, 40));
		signUp.setBackground(Color.RED);
		
		listOf = new JButton("LIST OF");
		listOf.setFont(new Font("Arial", Font.PLAIN, 40));
		listOf.setBackground(Color.RED);
		
		welcome = new JLabel("WELCOME");
		
		Dimension prefSize = newsletter.getPreferredSize();
		
		createEvent.setPreferredSize(prefSize);
		signUp.setPreferredSize(prefSize);
		listOf.setPreferredSize(prefSize);
			
	}
	
	public void initializeComponents() {
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void registerEventHandlers() {
		
	}
	
	public void addComponentsToFrame() {
		
		JPanel components = new JPanel(new GridLayout(2, 2));
		
		JPanel wrapEvent = new JPanel();
		wrapEvent.add(createEvent);

		JPanel wrapSignup = new JPanel();
		wrapSignup.add(signUp);
		
		JPanel wrapNewsletter = new JPanel();
		wrapNewsletter.add(newsletter);
		
		JPanel wrapList = new JPanel();
		wrapList.add(listOf);
		
		
		components.add(wrapEvent);
		components.add(wrapSignup);
		components.add(wrapNewsletter);
		components.add(wrapList);
		
		Dimension prefSize = newsletter.getPreferredSize();
		
		createEvent.setSize(prefSize);
		setContentPane(components);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainPage main = new MainPage();
				main.setVisible(true);
			}
		});
	}
	

}
