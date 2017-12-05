package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpFormParticipant extends VIAPanel {

	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JButton addToList;
	private JFrame frame;
	
	public SignUpFormParticipant(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new BorderLayout());
		initializeComonents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComonents() {
		signUp = new JLabel("Sign-Up Form for Participant");
		signUp.setFont(new Font("Arial", Font.PLAIN, 30));
		name = new JLabel("Name:");
		email = new JLabel("E-mail:");

		fieldName = new JTextField(8);
		fieldEmail = new JTextField(8);

		addToList = new JButton("Add to list");
		addToList.setFont(new Font("Arial", Font.PLAIN, 15));

		Dimension prefSize = new Dimension(100, 30);
		addToList.setPreferredSize(prefSize);
	}

	public void registerEventHandlers() {

	}

	public void addComponentsToPanel() {
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

		JPanel content = new JPanel(new GridLayout(1, 2));
		content.add(left);
		content.add(right);
		content.setOpaque(false);

		JPanel button = new JPanel();
		button.add(addToList);
		button.setOpaque(false);

		JPanel logo = new JPanel();
		logo.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel components = new JPanel(new BorderLayout());
		components.add(label, BorderLayout.NORTH);
		components.add(content, BorderLayout.CENTER);
		components.add(button, BorderLayout.SOUTH);
		components.setOpaque(false);

		add(imgLab, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpFormParticipant(frame));
				frame.setVisible(true);
			}
		});

	}

}
