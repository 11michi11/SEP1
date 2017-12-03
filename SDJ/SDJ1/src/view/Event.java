package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Event extends JFrame{
	
	private JButton lectures;
	private JButton seminars;
	private JButton workshop;
	private JButton trips;
	
	public Event() {
		super("Type");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}
	
	public void createComponents(){
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initializeComponents() {
		
		lectures = new JButton("LECTURES");
		lectures.setFont(new Font("Arial", Font.PLAIN, 30));
		seminars = new JButton("SEMINARS");
		seminars.setFont(new Font("Arial", Font.PLAIN, 30));
		workshop = new JButton("WORKSHOP");
		workshop.setFont(new Font("Arial", Font.PLAIN, 30));
		trips = new JButton("TRIPS");
		trips.setFont(new Font("Arial", Font.PLAIN, 30));
		
		Dimension prefSize = new Dimension(300, 100);
		
		lectures.setPreferredSize(prefSize);
		seminars.setPreferredSize(prefSize);
		workshop.setPreferredSize(prefSize);
		trips.setPreferredSize(prefSize);
		
		
	}
	
	public void registerEventHandlers() {}
	
	public void addComponentsToFrame() {
		
		JPanel first = new JPanel();
		first.add(lectures);
		first.add(seminars);
		
		JPanel second = new JPanel();
		second.add(workshop);
		second.add(trips);
		
		JPanel component = new JPanel();
		component.add(first);
		component.add(second);
		
		setContentPane(component);
	
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Event main = new Event();
				main.setVisible(true);
			}
		});
	}
	

}
