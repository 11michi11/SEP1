package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EventListPanel extends VIAPanel {
	
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField search;
	private JLabel eventList;
	private JButton addEvent;
	private JButton signUpParticipant;
	private JButton signUpMember;
	private JCheckBox finalized;
	private JCheckBox finished;
	private JFrame frame;

	public EventListPanel(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}

	private void initializeComponents() {
		String[] columnNames = { "Name" };
		Object[][] data = { { "Event" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" },
				{ "Michal" }, { "Michal" }, };

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		
		scrollPane = new JScrollPane(table);
		
		search = new JTextField(10);
		search.setText("SEARCH");
		
		eventList = new JLabel("EVENT LIST");
		eventList.setFont(new Font("Arial", Font.PLAIN, 30));
		
		finalized = new JCheckBox("Finalized");
		finalized.setOpaque(false);
		finished = new JCheckBox("Finished");
		finished.setOpaque(false);
		
		addEvent = new VIAButtonTwo("ADD EVENT");
		addEvent.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpParticipant = new VIAButtonTwo("SIGN UP PARTICIPANT");
		signUpParticipant.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpMember = new VIAButtonTwo("SIGN UP MEMBER");
		signUpMember.setFont(new Font("Arial", Font.PLAIN, 20));
		
		Dimension prefSize = new Dimension(300,100);
		addEvent.setPreferredSize(prefSize);
		signUpMember.setPreferredSize(prefSize);
		signUpParticipant.setPreferredSize(prefSize);
	}
	
	private void registerEventHandlers() {
		// TODO Auto-generated method stub
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
		
		JPanel center = new JPanel(new GridLayout(2,  1));
		center.setOpaque(false);
		center.add(finalized);
		center.add(finished);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setOpaque(false);
		labelPanel.add(eventList);
		
		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		JPanel eventForm = new JPanel(new BorderLayout());
		eventForm.add(labelPanel, BorderLayout.NORTH);
		eventForm.add(left, BorderLayout.WEST);
		eventForm.add(center, BorderLayout.CENTER);
		eventForm.add(right, BorderLayout.EAST);
		eventForm.setOpaque(false);
		
		add(imgLab ,BorderLayout.NORTH);
		add(eventForm, BorderLayout.CENTER);
			
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new EventListPanel(frame));
				frame.setVisible(true);
			}
		});

	}

}
