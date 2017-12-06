package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

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
	private JLabel participantList;
	private JFrame frame;

	public ParticipantListPanel(JFrame frame) {
		super();
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}

	private void initializeComponents() {
		String[] columnNames = { "Name", "E-mail" };
		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com" }, { "Michal", "andasfsuf@gdgdfg.com" },
				{ "Michal", "andasfsuf@gdgdfg.com" }, { "Michal", "andasfsuf@gdgdfg.com" },
				{ "Michal", "andasfsuf@gdgdfg.com" }, { "Michal", "andasfsuf@gdgdfg.com" },
				{ "Michal", "andasfsuf@gdgdfg.com" }, { "Miska", "andasfsuf@gdgdfg.com" },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonTwo("ADD PARTICIPANT");
		add.setFont(new Font("Arial", Font.PLAIN, 20));
		
		Dimension prefSize = new Dimension(300, 100);
		add.setPreferredSize(prefSize);

		participantList = new JLabel("PARTICIPANT LIST");
		participantList.setFont(new Font("Arial", Font.PLAIN, 30));

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
	}

	private void registerEventHandlers() {
		// TODO Auto-generated method stub

	}

	private void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		left.setOpaque(false);

		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.add(add);
		north.setOpaque(false);

		JPanel right = new JPanel(new BorderLayout());
		right.add(north, BorderLayout.NORTH);
		right.setOpaque(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(participantList);
		labelPanel.setOpaque(false);

		JPanel logo = new JPanel();
		logo.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel components = new JPanel(new BorderLayout());
		components.add(labelPanel, BorderLayout.NORTH);
		components.add(left, BorderLayout.WEST);
		components.add(right, BorderLayout.EAST);
		components.setOpaque(false);

		add(imgLab, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new ParticipantListPanel(frame));
				frame.setVisible(true);
			}
		});

	}

}
