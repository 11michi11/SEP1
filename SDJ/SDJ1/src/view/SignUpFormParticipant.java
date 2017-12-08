package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel parentPanel;
	private VIAButtonBack back;

	public SignUpFormParticipant(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComonents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComonents() {
		signUp = new VIALabel("Sign-Up Form for Participant", 40);
		name = new JLabel("Name:");
		email = new JLabel("E-mail:");

		fieldName = new JTextField(8);
		fieldEmail = new JTextField(8);

		addToList = new VIAButtonSmall("Add to list");

		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		addToList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parentPanel instanceof EventListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parentPanel instanceof EventListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
			}
		});
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

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);
		;

		JPanel components = new JPanel(new BorderLayout());
		components.add(label, BorderLayout.NORTH);
		components.add(content, BorderLayout.CENTER);
		components.add(button, BorderLayout.SOUTH);
		components.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpFormParticipant(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

}
