package client.view;

import client.controller.Controller;
import server.view.VIAButtonSimple;
import server.view.VIAPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MemberView extends VIAPanel implements  ViewManager {

	private static JTable table;
	private JButton memberList;
	private JButton haventPaid;
	private Controller controller;
	private JFrame frame;

	public MemberView() {
		super();
		controller = Controller.getInstance();
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setContentPane(this);


	}

	public void initializeComponents() {
		memberList = new VIAButtonSimple("member list", 20);
		haventPaid = new VIAButtonSimple("members who haven't paid", 15);

		DefaultTableModel model = controller.getMembersTableModel();
		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));

	}

	public void registerEventHandlers() {

	}

	public void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel right = new JPanel(new BorderLayout());
		right.add(memberList, BorderLayout.NORTH);
		right.add(haventPaid, BorderLayout.CENTER);
		right.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);

		JPanel components = new JPanel(new BorderLayout());
		components.add(scrollPane, BorderLayout.WEST);
		components.add(right, BorderLayout.EAST);
		components.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);

	}

	@Override
	public void showListOfMembers() {

	}

	@Override
	public void showListOfMembersWhoHasPaid() {

	}

	@Override
	public void startView() {
		frame.setVisible(true);
	}

	@Override
	public void showMsg(String string) {

	}
}
