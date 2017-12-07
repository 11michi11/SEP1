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

public class LecturerListPanel extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton add;
	private JLabel lecturerList;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;

	public LecturerListPanel(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new FlowLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise" };
		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },
				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", "astronomy", "yes" },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD LECTURER");
		add.setFont(new Font("Arial", Font.PLAIN, 20));

		Dimension prefSize = new Dimension(300, 100);
		add.setPreferredSize(prefSize);

		lecturerList = new JLabel("LECTURER LIST");
		lecturerList.setFont(new Font("Arial", Font.PLAIN, 30));

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));

		back = new VIAButtonBack(frame, parentPanel);

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
		labelPanel.add(lecturerList);
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
				frame.setContentPane(new LecturerListPanel(frame));
				frame.setVisible(true);
			}
		});

	}

}
