package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignUpPanel extends VIAPanel {

	private JButton member;
	private JButton lecturer;
	private JButton participant;
	private JButton back;
	private JFrame frame;
	private JPanel parentPanel;

	public SignUpPanel(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {
		member = new VIAButtonSmall("MEMBER");
		lecturer = new VIAButtonSmall("LECTURER");
		participant = new VIAButtonSmall("PARTICIPANT");
		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;

		member.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpFormMember(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

		participant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpFormParticipant(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

		lecturer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpFormLecturer(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
	}

	public void addComponentsToPanel() {
		JPanel first = new JPanel();
		first.add(member);
		first.setOpaque(false);

		JPanel second = new JPanel();
		second.add(lecturer);
		second.setOpaque(false);

		JPanel third = new JPanel();
		third.add(participant);
		third.setOpaque(false);

		JPanel buttons = new JPanel(new GridLayout(3, 1));
		buttons.add(first);
		buttons.add(second);
		buttons.add(third);
		buttons.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		add(logo, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpPanel(frame, new JPanel()));
				frame.setVisible(true);
			}
		});
	}
}
