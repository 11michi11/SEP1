package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SignUpWindow extends JFrame{
	
	private JButton member;
	private JButton lecturer;
	private JButton participant;
	
	public SignUpWindow() {
		super("SignUp");
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
		
		member = new JButton("MEMBER");
		member.setFont(new Font("Arial", Font.PLAIN, 30));
		lecturer = new JButton("LECTURER");
		lecturer.setFont(new Font("Arial", Font.PLAIN, 30));
		participant = new JButton("PARTICIPANT");
		participant.setFont(new Font("Arial", Font.PLAIN, 30));
		
		Dimension prefSize = new Dimension(300, 100);
		
		member.setPreferredSize(prefSize);
		lecturer.setPreferredSize(prefSize);
		participant.setPreferredSize(prefSize);
	}
	
	public void registerEventHandlers() {}
	
	public void addComponentsToFrame() {
		
		JPanel first = new JPanel();
		first.add(member);
		
		JPanel second = new JPanel();
		second.add(lecturer);
		
		JPanel third = new JPanel();
		third.add(participant);
		
		JPanel component = new JPanel();
		component.add(first);
		component.add(second);
		component.add(third);
		
		setContentPane(component);
		
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SignUpWindow main = new SignUpWindow();
				main.setVisible(true);
			}
		});
	}

}
