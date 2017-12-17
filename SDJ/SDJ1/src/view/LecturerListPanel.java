package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controler.VIAController;

public class LecturerListPanel extends VIAPanel {

	private static JTable table;
	private JTextField search;
	private JButton add;
	private JLabel lecturerList;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;
	private JScrollPane scrollPane;

	public LecturerListPanel(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		DefaultTableModel model = VIAController.getLecturersTableModel();

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD LECTURER", 30);

		lecturerList = new VIALabel("LECTURER LIST", 40);

		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));

		scrollPane = new JScrollPane(table);

		back = new VIAButtonBack(frame, parentPanel);

	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame lecturer = new JFrame();
				lecturer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lecturer.setSize(900, 500);
				lecturer.setLocationRelativeTo(null);
				lecturer.setResizable(false);
				lecturer.setTitle("VIA - Add new member");
				lecturer.setContentPane(new SignUpFormLecturer(lecturer, currentPanel));
				lecturer.setVisible(true);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = VIAController.getSearchedLecturers(search.getText());
				table.setModel(model);
				table.removeColumn(table.getColumnModel().getColumn(5));
			}
		});

		search.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				search.setText("");
			}

			public void focusLost(FocusEvent e) {

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
		north.add(add);
		north.setOpaque(false);

		JPanel right = new JPanel(new BorderLayout());
		right.add(north, BorderLayout.NORTH);
		right.setOpaque(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(lecturerList);
		labelPanel.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		JPanel components = new JPanel(new BorderLayout());
		components.add(labelPanel, BorderLayout.NORTH);
		components.add(left, BorderLayout.WEST);
		components.add(right, BorderLayout.EAST);
		components.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void refreshTable() {
		if (table != null) {
			DefaultTableModel model = VIAController.getLecturersTableModel();
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(5));
		}
	}

}
