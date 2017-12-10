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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ParticipantListPanel extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton add;
	private JButton delete;
	private JLabel participantList;
	private JFrame frame;
	private JButton back;
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
		String[] columnNames = { "Event", "Name", "E-mail" };
		Object[][] data = { {"Title", "Matej", "andasfsuf@gdgdfg.com" }, {"Title", "Michal", "andasfsuf@gdgdfg.com" },
				{"Title", "Michal", "andasfsuf@gdgdfg.com" }, {"Title", "Michal", "andasfsuf@gdgdfg.com" },
				{"Title", "Michal", "andasfsuf@gdgdfg.com" }, {"Title", "Michal", "andasfsuf@gdgdfg.com" },
				{"Title", "Michal", "andasfsuf@gdgdfg.com" }, {"Title", "Miska", "andasfsuf@gdgdfg.com" },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD PARTICIPANT", 30);
		delete = new VIAButtonSmall("DELETE PARTICIPANT", 30);
		

		participantList = new VIALabel("PARTICIPANT LIST",40);

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		
		back = new VIAButtonBack(frame,parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame participant = new JFrame();
				participant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				participant.setSize(900, 500);
				participant.setTitle("VIA - Add new member");
				participant.setContentPane(new SignUpFormParticipant(participant, currentPanel));
				participant.setVisible(true);
			}
		});

	}

	private void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		left.setOpaque(false);

		JPanel addPanel = new JPanel();
		addPanel.add(add);
		addPanel.setOpaque(false);
		
		JPanel deletePanel = new JPanel();
		deletePanel.add(delete);
		deletePanel.setOpaque(false);

		JPanel right = new JPanel(new GridLayout(2, 1));
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new ParticipantListPanel(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

}
