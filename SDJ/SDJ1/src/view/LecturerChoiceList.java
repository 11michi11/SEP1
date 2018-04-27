package view;

import controler.VIAController;
import domain.model.Lecturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class LecturerChoiceList extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton choose;
	private JLabel lecturerList;
	private JFrame frame;

	public LecturerChoiceList(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new FlowLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		search = new JTextField(47);
		search.setText("SEARCH");
		choose = new VIAButtonSmall("CHOOSE LECTURER", 30);
		choose.setEnabled(false);
		lecturerList = new VIALabel("LECTURER LIST", 40);

		DefaultTableModel model = VIAController.getLecturersTableModel();

		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	}

	private void registerEventHandlers() {
		
		table.getSelectionModel().addListSelectionListener(event -> choose.setEnabled(true));

		choose.addActionListener(e -> {
			TableModel model = table.getModel();
			int selRow = table.getSelectedRow();
			Lecturer selected = (Lecturer) model.getValueAt(selRow, 5);
			EventCreateFormLectures.assignLecturerToForm(selected);
			frame.dispose();
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

}
