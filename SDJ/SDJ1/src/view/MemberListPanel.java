package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberListPanel extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton add;
	private JButton mail;
	private JLabel memberList;
	private JFrame frame;

	public MemberListPanel(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();	
	}

	private void initializeComponents() {
		String[] columnNames = { "Name", "E-mail", "ID", "Paid" };
		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "yes" },
				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", "yes" },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD MEMBER");
		add.setFont(new Font("Arial", Font.PLAIN, 20));
		mail = new VIAButtonSmall("SEND REMAIND E-MAIL");
		mail.setFont(new Font("Arial", Font.PLAIN, 20));

		Dimension prefSize = new Dimension(300,100);
		add.setPreferredSize(prefSize);
		mail.setPreferredSize(prefSize);

		memberList = new JLabel("MEMBER LIST");
		memberList.setFont(new Font("Arial", Font.PLAIN, 30));

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

		JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
		south.add(mail);
		south.setOpaque(false);

		JPanel right = new JPanel(new BorderLayout());
		right.add(north, BorderLayout.NORTH);
		right.add(south, BorderLayout.CENTER);
		right.setOpaque(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(memberList);
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
				frame.setContentPane(new MemberListPanel(frame));
				frame.setVisible(true);
			}
		});

	}
}
