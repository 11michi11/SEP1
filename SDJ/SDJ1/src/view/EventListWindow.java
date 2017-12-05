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

public class EventListWindow extends JFrame {
	
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField search;
	private JLabel eventList;
	private JButton addEvent;
	private JButton signUpParticipant;
	private JButton signUpMember;
	private JCheckBox finalized;
	private JCheckBox finished;

	public EventListWindow() {
		super("VIA - Events list");
		initializeFrame();
		createComponents();
		registerEventHandlers();
		addComponentsToFrame();

	}

	private void initializeFrame() {
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private void createComponents() {
		String[] columnNames = { "Name" };
		Object[][] data = { { "Event" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" }, { "Michal" },
				{ "Michal" }, { "Michal" }, };

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(250, 300));
		
		scrollPane = new JScrollPane(table);
		
		search = new JTextField(10);
		search.setText("SEARCH");
		
		eventList = new JLabel("EVENT LIST");
		eventList.setFont(new Font("Arial", Font.PLAIN, 30));
		
		finalized = new JCheckBox("Finalized");
		finalized.setOpaque(false);
		finished = new JCheckBox("Finished");
		finished.setOpaque(false);
		
		addEvent = new JButton("ADD EVENT");
		addEvent.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpParticipant = new JButton("SIGN UP PARTICIPANT");
		signUpParticipant.setFont(new Font("Arial", Font.PLAIN, 20));
		
		signUpMember = new JButton("SIGN UP MEMBER");
		signUpMember.setFont(new Font("Arial", Font.PLAIN, 20));
		
		Dimension prefSize = signUpParticipant.getPreferredSize();
		addEvent.setPreferredSize(prefSize);
		signUpMember.setPreferredSize(prefSize);
	}
	
	private void registerEventHandlers() {
		// TODO Auto-generated method stub
	}
	
	private void addComponentsToFrame() {
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
		
		VIAPanel contentPane = new VIAPanel(new BorderLayout());
		contentPane.add(imgLab ,BorderLayout.NORTH);
		contentPane.add(eventForm, BorderLayout.CENTER);
		
		setContentPane(contentPane);	
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
