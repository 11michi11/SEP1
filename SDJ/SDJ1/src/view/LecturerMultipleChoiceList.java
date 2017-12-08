package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

public class LecturerMultipleChoiceList extends VIAPanel {
	
	private JTable table;
	private JTextField search;
	private JButton choose;
	private JLabel lecturerList;
	private JFrame frame;

	public LecturerMultipleChoiceList(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new FlowLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise", "Choice" };
		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, false },
				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		choose = new VIAButtonSmall("CHOOSE LECTURERS", 30);

		lecturerList = new VIALabel("LECTURER LIST", 40);


		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				case 5:
					return true;
				default:
					return false;
				}
			}
		};
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	}

	private void registerEventHandlers() {

		choose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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

		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.add(choose);
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
				frame.setContentPane(new LecturerMultipleChoiceList(frame));
				frame.setVisible(true);
			}
		});

	}

}
