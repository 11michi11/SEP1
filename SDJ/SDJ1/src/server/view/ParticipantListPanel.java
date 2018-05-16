package server.view;

import server.controler.VIAController;
import server.domain.model.Event;
import server.domain.model.Member;
import server.domain.model.Participant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ParticipantListPanel extends VIAPanel {

	private static JTable table;
	private static JTable events;
	private JButton add;
	private JButton delete;
	private JButton member;
	private JButton back;
	private JLabel participantList;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAController controller;

	public ParticipantListPanel(JFrame frame, JPanel parentPanel) {
		super();
		this.controller = VIAController.getInstance();
		setLayout(new BorderLayout());
		this.frame = frame;
		this.parentPanel = parentPanel;
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		add = new VIAButtonSimple("ADD PARTICIPANT", 20);
		add.setEnabled(false);
		delete = new VIAButtonSimple("DELETE PARTICIPANT", 20);
		delete.setEnabled(false);
		member = new VIAButtonSimple("ADD MEMBER", 20);
		member.setEnabled(false);

		participantList = new VIALabel("PARTICIPANT LIST", 40);

		DefaultTableModel eventModel = VIAController.getInstance().getParticipantEventsTableModel();
		events = new JTable(eventModel);
		events.removeColumn(events.getColumnModel().getColumn(1));
		events.setPreferredScrollableViewportSize(new Dimension(200, 290));

		String[] columnNames = {"Name", "Email", "Member"};
		Object[][] data = {};

		DefaultTableModel participantModel = new DefaultTableModel(data, columnNames);
		table = new JTable(participantModel);
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.setPreferredScrollableViewportSize(new Dimension(320, 290));

		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;
		add.addActionListener(e -> {
			Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
			controller.showSignUpFormParticipant(currentPanel, event);
		});

		delete.addActionListener(e -> {
			Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
			Participant participant = (Participant) table.getModel().getValueAt(table.getSelectedRow(), 2);
			event.signOffParticipant(participant);
			DefaultTableModel participantModel = VIAController.getInstance().getParticipantTableModel(event);
			table.setModel(participantModel);
			table.removeColumn(table.getColumnModel().getColumn(2));
		});

		member.addActionListener(e -> controller.showMemberMultipleChoice(currentPanel));

		events.getSelectionModel().addListSelectionListener(e -> {
			add.setEnabled(true);
			member.setEnabled(true);

			Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
			DefaultTableModel participantModel = VIAController.getInstance().getParticipantTableModel(event);
			table.setModel(participantModel);
			table.removeColumn(table.getColumnModel().getColumn(2));
		});

		table.getSelectionModel().addListSelectionListener(e -> delete.setEnabled(true));

	}

	private void addComponentsToPanel() {
		JScrollPane scrollPaneOne = new JScrollPane(events);
		add(scrollPaneOne);

		JScrollPane scrollPaneTwo = new JScrollPane(table);
		add(scrollPaneTwo);

		JPanel left = new JPanel(new BorderLayout());

		JPanel tableEvent = new JPanel();
		tableEvent.add(scrollPaneOne);
		tableEvent.setOpaque(false);

		JPanel tableParticipant = new JPanel();
		tableParticipant.add(scrollPaneTwo);
		tableParticipant.setOpaque(false);

		JPanel tables = new JPanel(new BorderLayout());
		tables.add(tableEvent, BorderLayout.WEST);
		tables.add(tableParticipant, BorderLayout.EAST);
		tables.setOpaque(false);

		left.add(tables);
		left.setOpaque(false);

		JPanel addPanel = new JPanel();
		addPanel.add(add);
		addPanel.setOpaque(false);

		JPanel deletePanel = new JPanel();
		deletePanel.add(delete);
		deletePanel.setOpaque(false);

		JPanel memberPanel = new JPanel();
		memberPanel.add(member);
		memberPanel.setOpaque(false);

		JPanel right = new JPanel(new GridLayout(3, 1));
		right.add(memberPanel);
		right.add(addPanel);
		right.add(deletePanel);
		right.setOpaque(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(participantList);
		labelPanel.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		JPanel components = new JPanel(new BorderLayout());
		components.add(labelPanel, BorderLayout.NORTH);
		components.add(left, BorderLayout.WEST);
		components.add(right, BorderLayout.EAST);
		components.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void refreshTable() {
		if (table != null) {
			Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
			DefaultTableModel model = VIAController.getInstance().getParticipantTableModel(event);
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		}
	}

	static void addMembersToEvent(ArrayList<Member> members) {
		Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
		for (Member e : members)
			event.signUpParticipant(e);

		DefaultTableModel model = VIAController.getInstance().getParticipantTableModel(event);
		table.setModel(model);
		table.removeColumn(table.getColumnModel().getColumn(2));
	}

}
