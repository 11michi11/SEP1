package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EventListWindow extends JFrame {
	
	private JTable table;
	private JTextField search;
	private JLabel eventList;
	private JButton add;
	private JButton signUpParticipant;
	private JButton signUpMember;
	private JCheckBox finalized;
	private JCheckBox finished;

	public EventListWindow() {
		setSize(800, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("VIA - Events list");
		setLocationRelativeTo(null);

		String[] columnNames = { "Name" };
		Object[][] data = { { "Event" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" },
				{ "Michal" }, { "Michal" }, };

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(250, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		search = new JTextField(10);
		search.setText("SEARCH");
		
		eventList = new JLabel("EVENT LIST");
		eventList.setFont(new Font("Arial", Font.PLAIN, 30));
		
		finalized = new JCheckBox("Finalized");
		finalized.setOpaque(false);
		finished = new JCheckBox("Finished");
		finished.setOpaque(false);
		
		add = new JButton("ADD EVENT");
		add.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpParticipant = new JButton("SIGN UP PARTICIPANT");
		signUpParticipant.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpMember = new JButton("SIGN UP MEMBER");
		signUpMember.setFont(new Font("Arial", Font.PLAIN, 20));
		
		Dimension prefSize = signUpParticipant.getPreferredSize();
		add.setPreferredSize(prefSize);
		signUpMember.setPreferredSize(prefSize);
		
		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		
		JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addPanel.setOpaque(false);
		addPanel.add(add);
		
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
		
		JPanel center = new JPanel(new GridLayout(2,  1));
		center.setOpaque(false);
		center.add(finalized);
		center.add(finished);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setOpaque(false);
		labelPanel.add(eventList);
		
		VIAPanel components = new VIAPanel(new BorderLayout());
		components.add(labelPanel, BorderLayout.NORTH);
		components.add(left, BorderLayout.WEST);
		components.add(center, BorderLayout.CENTER);
		components.add(right, BorderLayout.EAST);
		
		setContentPane(components);
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				EventListWindow frame = new EventListWindow();
				frame.setVisible(true);
			}
		});

	}

}
