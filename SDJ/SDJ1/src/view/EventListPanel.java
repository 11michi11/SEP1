package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controler.VIAController;
import model.Event;

public class EventListPanel extends VIAPanel {

	private static JTable table;
	private JScrollPane scrollPane;
	private JTextField search;
	private JLabel eventList;
	private JButton addEvent;
	private JButton signUpParticipant;
	private JButton signUpMember;
	private JCheckBox finalized;
	private JCheckBox finished;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;

	public EventListPanel(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}

	private void initializeComponents() {
		DefaultTableModel model = VIAController.getEventsTableModel();

		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));

		scrollPane = new JScrollPane(table);

		search = new JTextField(10);
		search.setText("SEARCH");

		eventList = new VIALabel("EVENT LIST", 40);

		finalized = new JCheckBox("Finalized");
		finalized.setOpaque(false);
		finished = new JCheckBox("Finished");
		finished.setOpaque(false);

		addEvent = new VIAButtonSimple("ADD EVENT", 20);
		signUpParticipant = new VIAButtonSimple("SIGN UP PARTICIPANT", 20);
		signUpMember = new VIAButtonSimple("SIGN UP MEMBER", 20);
		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;
		signUpParticipant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame participant = new JFrame();
				participant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				participant.setSize(900, 500);
				participant.setTitle("VIA - Sign up new participant");
				Event event = (Event) table.getModel().getValueAt(table.getSelectedRow(), 2);
				participant.setContentPane(new SignUpFormParticipant(participant, currentPanel, event.getID()));
				participant.setVisible(true);
			}
		});

		signUpMember.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame member = new JFrame();
				member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				member.setSize(900, 500);
				member.setTitle("VIA - Add members to event");
				member.setContentPane(new SignUpFormMember(member, currentPanel));
				member.setVisible(true);
			}
		});

		addEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame event = new JFrame();
				event.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				event.setSize(900, 500);
				event.setTitle("VIA - Crate new event");
				event.setContentPane(new EventPanel(event, currentPanel));
				event.setVisible(true);
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2) {
					JOptionPane.showMessageDialog(frame,
							"Event name:\n" + "Category:\n" + "Lecturer:\n" + "Location:\n" + "Price:\n"
									+ "N° of Places:\n" + "Start date:\n" + "End date:\n" + "Finalized:\n"
									+ "Description:\n",
							"Start Messege", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		search.addActionListener(new ActionListener() {
		    
		    @Override
		    public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = VIAController.getSearchedEvents(search.getText());
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		    }
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

		JPanel right = new JPanel(new GridLayout(3, 1));
		right.setOpaque(false);
		right.add(addPanel);
		right.add(signMemberPanel);
		right.add(signParticipantPanel);

		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setOpaque(false);
		center.add(finalized);
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
			DefaultTableModel model = VIAController.getEventsTableModel();
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(2));
		}
	}

}
