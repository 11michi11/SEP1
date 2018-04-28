package server.view;

import server.controler.VIAController;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends VIAPanel {

	private JButton member;
	private JButton lecturer;
	private JButton back;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAController controller;

	public SignUpPanel(JFrame frame, JPanel parentPanel) {
		super();
		controller = VIAController.getInstance();
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
		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;

		member.addActionListener(e -> controller.showSignUpFormMember(currentPanel));

		lecturer.addActionListener(e -> controller.showSignUpFormLecturer(currentPanel));
	}

	public void addComponentsToPanel() {
		JPanel first = new JPanel();
		first.add(member);
		first.setOpaque(false);

		JPanel second = new JPanel();
		second.add(lecturer);
		second.setOpaque(false);

		JPanel buttons = new JPanel(new GridLayout(2, 1));
		buttons.add(first);
		buttons.add(second);
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
}
