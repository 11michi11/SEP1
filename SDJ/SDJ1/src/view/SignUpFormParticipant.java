package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpFormParticipant extends JFrame{
	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JButton add;
	
	public SignUpFormParticipant() {

		super("Sign-Up Form for Participant");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();

	}

	public void createComponents() {
	
	signUp = new JLabel("Sign-Up Form for MEMBER");
	signUp.setFont(new Font("Arial", Font.PLAIN, 30));
	name = new JLabel("Name:");
	email = new JLabel("E-mail:");
	
	fieldName = new JTextField(8);
	fieldEmail = new JTextField(8);
	
	add = new JButton("Add to list");
	add.setFont(new Font("Arial", Font.PLAIN, 15));
	
	Dimension prefSize = new Dimension(100, 30);
	add.setPreferredSize(prefSize);
	
	
	
	}
	
	public void initializeComponents() {
		
		setSize(700, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerEventHandlers() {

	}

	public void addComponentsToFrame() {
		
		VIAPanel components = new VIAPanel(new BorderLayout());
		
		JPanel label = new JPanel();
		label.add(signUp);
		label.setOpaque(false);
		
		JPanel left = new JPanel();
		left.add(name);
		left.add(fieldName);
		left.setOpaque(false);
		
		JPanel right = new JPanel();
		right.add(email);
		right.add(fieldEmail);
		right.setOpaque(false);
		
		JPanel content = new JPanel(new GridLayout(1,2));
		content.add(left);
		content.add(right);
		content.setOpaque(false);
		
		JPanel button = new JPanel();
		button.add(add);
		button.setOpaque(false);
		
		components.add(label, BorderLayout.NORTH);
		components.add(content, BorderLayout.CENTER);
		components.add(button, BorderLayout.SOUTH);
		
		
		setContentPane(components);
		
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SignUpFormParticipant main = new SignUpFormParticipant();
				main.setVisible(true);
			}
		});

	}


}
