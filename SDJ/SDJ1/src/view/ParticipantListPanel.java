package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controler.VIAController;
import domain.model.Event;
import domain.model.Member;
import domain.model.Participant;

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

	public ParticipantListPanel(JFrame frame, JPanel parentPanel) {
		super();
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

		DefaultTableModel eventModel = VIAController.getParticipantEventsTableModel();
		events = new JTable(eventModel);
		events.removeColumn(events.getColumnModel().getColumn(1));
		events.setPreferredScrollableViewportSize(new Dimension(200, 290));

		String[] columnNames = { "Name", "Email", "Member" };
		Object[][] data = {};

		DefaultTableModel pariticipantModel = new DefaultTableModel(data, columnNames);
		table = new JTable(pariticipantModel);
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.setPreferredScrollableViewportSize(new Dimension(320, 290));

		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame participant = new JFrame();
				participant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				participant.setSize(900, 500);
				participant.setLocationRelativeTo(null);
				participant.setResizable(false);
				participant.setTitle("VIA - Add new member");
				Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
				participant.setContentPane(new SignUpFormParticipant(participant, currentPanel, event.getID()));
				participant.setVisible(true);
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
				Participant participant = (Participant) table.getModel().getValueAt(table.getSelectedRow(), 2);
				event.signOffParticipant(participant);
				DefaultTableModel pariticipantModel = VIAController.getParticipantTableModel(event);
				table.setModel(pariticipantModel);
				table.removeColumn(table.getColumnModel().getColumn(2));
			}
		});

		member.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame member = new JFrame();
				member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				member.setSize(900, 500);
				member.setLocationRelativeTo(null);
				member.setResizable(false);
				member.setTitle("VIA - Add new member");
				Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
				member.setContentPane(new MemberMultipleChoice(member, currentPanel, event));
				member.setVisible(true);

			}
		});

		events.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				add.setEnabled(true);
				member.setEnabled(true);

				Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
				DefaultTableModel pariticipantModel = VIAController.getParticipantTableModel(event);
				table.setModel(pariticipantModel);
				table.removeColumn(table.getColumnModel().getColumn(2));
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				delete.setEnabled(true);
			}
		});

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
			DefaultTableModel model = VIAController.getParticipantTableModel(event);
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		}
	}

	public static void addMembersToEvent(ArrayList<Member> members) {
		Event event = (Event) events.getModel().getValueAt(events.getSelectedRow(), 1);
		for (Member e : members)
			event.signUpParticipant(e);

		DefaultTableModel model = VIAController.getParticipantTableModel(event);
		table.setModel(model);
		table.removeColumn(table.getColumnModel().getColumn(2));
	}

}
