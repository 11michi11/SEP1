package server.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.controler.VIAController;

public class ListsPanel extends VIAPanel {

	private JButton memberList;
	private JButton lecturerList;
	private JButton eventList;
	private JButton participantList;
	private JButton back;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAController controller;

	public ListsPanel(JFrame frame, JPanel parentPanel) {
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
		lecturerList = new VIAButtonBig("LECTURER LIST");
		memberList = new VIAButtonBig("MEMBER LIST");
		eventList = new VIAButtonBig("EVENT LIST");
		participantList = new VIAButtonBig("PARTICIPANT LIST");
		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;

		lecturerList.addActionListener(e -> controller.showLecturerListPanel(currentPanel));

		memberList.addActionListener(e -> controller.showMemberListPanel(currentPanel));

		eventList.addActionListener(e -> controller.showEventListPanel(currentPanel));

		participantList.addActionListener(e -> controller.showParticipantListPanel(currentPanel));

	}

	public void addComponentsToPanel() {
		JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.setOpaque(false);

		JPanel wrapLecturer = new JPanel();
		wrapLecturer.setOpaque(false);
		wrapLecturer.add(lecturerList);

		JPanel wrapMembers = new JPanel();
		wrapMembers.setOpaque(false);
		wrapMembers.add(memberList);

		JPanel wrapEvent = new JPanel();
		wrapEvent.setOpaque(false);
		wrapEvent.add(eventList);

		JPanel wrapParticipant = new JPanel();
		wrapParticipant.setOpaque(false);
		wrapParticipant.add(participantList);

		buttons.add(wrapMembers);
		buttons.add(wrapLecturer);
		buttons.add(wrapEvent);
		buttons.add(wrapParticipant);

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
