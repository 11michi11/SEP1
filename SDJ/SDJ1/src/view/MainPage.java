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
		
		JPanel first = new JPanel();
		first.add(createEvent);
		first.add(signUp);
		
		JPanel second = new JPanel();
		second.add(newsletter);
		second.add(listOf);
		
		JPanel component = new JPanel();
		component.add(first);
		component.add(second);
		
		setContentPane(component);
		
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
