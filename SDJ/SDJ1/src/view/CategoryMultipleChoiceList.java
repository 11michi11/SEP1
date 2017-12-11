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

public class CategoryMultipleChoiceList extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton choose;
	private JLabel lecturerList;
	private JFrame frame;
	private JPanel parentPanel;

	public CategoryMultipleChoiceList(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new FlowLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		String[] columnNames = { "Category", "" };
		Object[][] data = { { "Astrology" }, { "Meditation" }, { "Reincarnation" }, { "Health" },
				{ "Buddhism" }, { "Nature" }, { "Other" }

		};

		choose = new VIAButtonSmall("CHOOSE CATEGORY", 30);

		lecturerList = new VIALabel("CATEGORY LIST", 40);

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				case 1:
					return true;
				default:
					return false;
				}
			}
		};
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 125));
	}

	private void registerEventHandlers() {

		choose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Category> categories = new ArrayList<Category>();

				TableModel model = table.getModel();
				for (int i = 0; i < 7; i++)
					if (model.getValueAt(i, 1) != null && (boolean) model.getValueAt(i, 1) == true)
						categories.add(Category.fromNumberToCategory(i));

				assignCategories(categories);
				frame.dispose();
			}
		});
	}

	private void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel left = new JPanel(new BorderLayout());
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
				frame.setContentPane(new CategoryMultipleChoiceList(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

	private void assignCategories(ArrayList<Category> categories) {
		switch (parentPanel.getClass().getName()) {
		case "view.SignUpFormLecturer":
			SignUpFormLecturer.assignCategoriesToLecturerForm(categories);
			break;
		case "view.EventCreateFormSeminars":
			EventCreateFormSeminars.assignCategoriesToLecturerForm(categories);
			break;
		case "view.EventCreateFormWorkshop":
			EventCreateFormWorkshop.assignCategoriesToLecturerForm(categories);
			break;
		default:
			System.out.println("!!!");
		}
	}

}
