package server.view;

import server.controler.VIAController;
import server.domain.model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class MemberMultipleChoice extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton add;
	private JButton back;
	private JLabel memberList;
	private JFrame frame;
	private JPanel parentPanel;

	public MemberMultipleChoice(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	private void initializeComponents() {
		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD MEMBERS TO EVENT", 25);
		add.setEnabled(false);

		memberList = new VIALabel("MEMBER LIST", 30);

		DefaultTableModel model = VIAController.getInstance().getMembersMultipleTableModel();
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));

		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		add.addActionListener(e -> {
			TableModel model = table.getModel();
			ArrayList<Member> members = new ArrayList<>();
			for (int i = 0; i < model.getRowCount(); i++)
				if (model.getValueAt(i, 3) != null && (boolean) model.getValueAt(i, 3))
					members.add((Member) model.getValueAt(i, 4));

			ParticipantListPanel.addMembersToEvent(members);
			frame.dispose();
		});

		table.getSelectionModel().addListSelectionListener(e -> add.setEnabled(true));
	}

	private void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		left.setOpaque(false);

		JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addPanel.add(add);
		addPanel.setOpaque(false);

		JPanel right = new JPanel();
		right.add(addPanel);
		right.setOpaque(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(memberList);
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
}
