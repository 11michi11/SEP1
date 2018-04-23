package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import controler.VIAController;
import domain.model.Lecturer;

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
		search = new JTextField(47);
		search.setText("SEARCH");

		choose = new VIAButtonSmall("CHOOSE LECTURERS", 30);
		choose.setEnabled(false);

		lecturerList = new VIALabel("LECTURER LIST", 40);

		DefaultTableModel model = VIAController.getLecturersMultipleTableModel();
		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(6));
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	}

	private void registerEventHandlers() {

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				choose.setEnabled(true);
			}
		});

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
