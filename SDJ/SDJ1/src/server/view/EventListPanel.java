package server.view;

import server.controler.VIAController;
import server.domain.model.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventListPanel extends VIAPanel {

	private static JTable table;
	private JScrollPane scrollPane;
	private JTextField search;
	private JLabel eventList;
	private JButton addEvent;
	private JButton signUpParticipant;
	private JButton signUpMember;
	private JButton modify;
	private JCheckBox notFinalized;
	private JCheckBox finished;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;
	private VIAController controller;

	public EventListPanel(JFrame frame, JPanel parentPanel) {
		super();
		controller = VIAController.getInstance();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		DefaultTableModel model = controller.getEventsTableModel();

		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));

		scrollPane = new JScrollPane(table);

		search = new JTextField(10);
		search.setText("SEARCH");

		eventList = new VIALabel("EVENT LIST", 40);

		notFinalized = new JCheckBox("Not finalized");
		notFinalized.setOpaque(false);
		finished = new JCheckBox("Finished");
		finished.setOpaque(false);

		addEvent = new VIAButtonSimple("ADD EVENT", 20);
		signUpParticipant = new VIAButtonSimple("SIGN UP PARTICIPANT", 20);
		signUpParticipant.setEnabled(false);
		signUpMember = new VIAButtonSimple("SIGN UP MEMBER", 20);
		signUpMember.setEnabled(false);
		modify = new VIAButtonSimple("MODIFY EVENT", 20);
		modify.setEnabled(false);
		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;

		search.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				search.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});

		notFinalized.addActionListener(e -> {
			if (notFinalized.isSelected()) {
				DefaultTableModel model = controller.getNotFinalizedEvents();
				table.setModel(model);
				table.removeColumn(table.getColumnModel().getColumn(2));
			} else {
				DefaultTableModel model = controller.getEventsTableModel();
				table.setModel(model);
				table.removeColumn(table.getColumnModel().getColumn(2));
			}
		});

		finished.addActionListener(e -> {
			if (finished.isSelected()) {
				DefaultTableModel model = controller.getFinishedEvents();
				table.setModel(model);
				table.removeColumn(table.getColumnModel().getColumn(2));
			} else {
				DefaultTableModel model = controller.getEventsTableModel();
				table.setModel(model);
				table.removeColumn(table.getColumnModel().getColumn(2));
			}
		});

		table.getSelectionModel().addListSelectionListener(event -> {
			signUpParticipant.setEnabled(true);
			signUpMember.setEnabled(true);
			if (table.getSelectedRow() != -1) {
				Event eventObj = (Event) table.getModel().getValueAt(table.getSelectedRow(), 2);
				if (!eventObj.isFinalized())
					modify.setEnabled(true);
				else
					modify.setEnabled(false);
			} else {
				modify.setEnabled(false);
			}
		});

		signUpParticipant.addActionListener(e -> {
			Event event = (Event) table.getModel().getValueAt(table.getSelectedRow(), 2);
			controller.showSignUpFormParticipant(currentPanel, event);
		});

		signUpMember.addActionListener(e -> controller.showSignUpFormMember(currentPanel));

		modify.addActionListener(e -> {
			Event event = (Event) table.getModel().getValueAt(table.getSelectedRow(), 2);
			controller.showModifyEventPanel(currentPanel, event);
		});

		addEvent.addActionListener(e -> controller.showEventsPanel(currentPanel));

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					String message = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
					JOptionPane.showMessageDialog(frame, message, "Start Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		search.addActionListener(e -> {
			DefaultTableModel model;
			if (notFinalized.isSelected())
				model = controller.getSearchedEventsWhenNotFinalized(search.getText());
			else
				model = controller.getSearchedEvents(search.getText());

			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		});
	}

	private void addComponentsToPanel() {
		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);

		JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addPanel.setOpaque(false);
		addPanel.add(addEvent);

		JPanel signMemberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		signMemberPanel.setOpaque(false);
		signMemberPanel.add(signUpMember);

		JPanel signParticipantPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		signParticipantPanel.setOpaque(false);
		signParticipantPanel.add(signUpParticipant);

		JPanel modifyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		modifyPanel.setOpaque(false);
		modifyPanel.add(modify);

		JPanel right = new JPanel(new GridLayout(4, 1));
		right.setOpaque(false);
		right.add(addPanel);
		right.add(signMemberPanel);
		right.add(signParticipantPanel);
		right.add(modifyPanel);

		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setOpaque(false);
		center.add(notFinalized);
		center.add(finished);

		JPanel labelPanel = new JPanel();
		labelPanel.setOpaque(false);
		labelPanel.add(eventList);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		JPanel eventForm = new JPanel(new BorderLayout());
		eventForm.add(labelPanel, BorderLayout.NORTH);
		eventForm.add(left, BorderLayout.WEST);
		eventForm.add(center, BorderLayout.CENTER);
		eventForm.add(right, BorderLayout.EAST);
		eventForm.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(eventForm, BorderLayout.CENTER);
	}

	public static void refreshTable() {
		if (table != null) {
			DefaultTableModel model = VIAController.getInstance().getEventsTableModel();
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		}
	}

}
