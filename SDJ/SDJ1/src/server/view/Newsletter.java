package server.view;

import server.controler.VIAController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Newsletter extends VIAPanel {

	private static JTable table;
	private JLabel listOfNewsletter;
	private JButton generateText;
	private JLabel newsletter;
	private JTextArea info;
	private JLabel addInfo;
	private JFrame frame;
	private JPanel parentPanel;
	private JButton back;
	private VIAController controller;

	public Newsletter(JFrame frame, JPanel parentPanel) {
		super();
		controller = VIAController.getInstance();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		DefaultTableModel model = controller.getNewsletterTableModel();

		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(1));
		table.setPreferredScrollableViewportSize(new Dimension(450, 50));

		listOfNewsletter = new VIALabel("LIST OF NEWSLETTER", 20);
		generateText = new VIAButtonSimple("Generate text", 30);
		newsletter = new VIALabel("NEWSLETTER", 50);
		info = new JTextArea(10, 30);
		addInfo = new VIALabel("ADDITIONAL INFO", 20);
		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					TableModel model = table.getModel();
					int selRow = table.getSelectedRow();
					File newsletter = (File) model.getValueAt(selRow, 1);

					controller.showNewsletterContentWindow(newsletter);
				}
			}
		});

		generateText.addActionListener(e -> {
			try {
				VIAController.getInstance().generateNewsletter(info.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame, "File not found", "File error", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	private void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel left = new JPanel(new BorderLayout());
		left.add(listOfNewsletter, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		left.setOpaque(false);

		JPanel right = new JPanel(new BorderLayout());
		right.add(addInfo, BorderLayout.NORTH);
		right.add(info, BorderLayout.CENTER);
		right.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		JPanel title = new JPanel();
		title.add(newsletter);
		title.setOpaque(false);

		JPanel north = new JPanel(new BorderLayout());
		north.add(logo, BorderLayout.NORTH);
		north.add(title, BorderLayout.CENTER);
		north.setOpaque(false);

		JPanel textArea = new JPanel(new BorderLayout());
		textArea.add(right, BorderLayout.CENTER);
		textArea.add(generateText, BorderLayout.SOUTH);
		textArea.setOpaque(false);

		add(north, BorderLayout.NORTH);
		add(left, BorderLayout.WEST);
		add(textArea, BorderLayout.EAST);
	}

	public static void refreshTable() {
		if (table != null) {
			DefaultTableModel model = VIAController.getInstance().getNewsletterTableModel();
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(1));
		}
	}

}
