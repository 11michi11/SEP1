package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Category;
import model.Lecturer;

public class LecturerMultipleChoiceList extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton choose;
	private JLabel lecturerList;
	private JFrame frame;
	private JPanel parentPanel;

	public LecturerMultipleChoiceList(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new FlowLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise", "Choice", "Lecturer" };
		Object[][] data = {
				{ "Matej", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },
				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", "astronomy", true, true,
						new Lecturer("Matej", "asd", 62, new ArrayList<Category>(), true) },

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
				case 5:
					return Boolean.class;
				case 6:
					return model.Lecturer.class;
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
		table.removeColumn(table.getColumnModel().getColumn(6));
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	}

	private void registerEventHandlers() {

		choose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel model = table.getModel();
				ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
				for (int i = 0; i < model.getRowCount(); i++)
					if (model.getValueAt(i, 5) != null && (boolean) model.getValueAt(i, 5) == true)
						lecturers.add((Lecturer) model.getValueAt(i, 6));

				assignLecturers(lecturers);
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
				frame.setContentPane(new LecturerMultipleChoiceList(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

	private void assignLecturers(ArrayList<Lecturer> lecturers) {
		switch (parentPanel.getClass().getName()) {
		case "view.EventCreateFormSeminars":
			EventCreateFormSeminars.assignLecturersToLecturerForm(lecturers);
			break;
		case "view.EventCreateFormWorkshop":
			EventCreateFormWorkshop.assignLecturersToLecturerForm(lecturers);
			break;
		}
	}

}
