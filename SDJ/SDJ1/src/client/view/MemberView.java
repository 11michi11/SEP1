package client.view;

import client.controller.Controller;
import server.controler.VIAController;
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

	public MemberView() {
		super();
		controller = Controller.getInstance();
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {
		memberList = new VIAButtonSimple("member list", 20);
		haventPaid = new VIAButtonSimple("members who havent paid", 15);

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
	public void showWelcomeMessage() {

	}

	@Override
	public void showMsg(String string) {

	}
}
