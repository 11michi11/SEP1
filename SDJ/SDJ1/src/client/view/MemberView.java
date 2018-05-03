package client.view;

import client.controller.Controller;
import server.view.VIAButtonSimple;
import server.view.VIAPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serializable;

public class MemberView extends VIAPanel implements  ViewManager, Serializable {

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
		frame.setSize(900, 600);
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

		memberList.addActionListener(e ->showListOfMembers());
		haventPaid.addActionListener(e ->showListOfMembersWhoHasPaid());

	}

	public void addComponentsToPanel() {
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		JPanel membersWrapper = new JPanel();
		membersWrapper.add(memberList);
		membersWrapper.setOpaque(false);

		JPanel haventWrapper = new JPanel();
		haventWrapper.add(haventPaid);
		haventWrapper.setOpaque(false);

		JPanel right = new JPanel(new BorderLayout());
		right.add(membersWrapper, BorderLayout.NORTH);
		right.add(haventWrapper, BorderLayout.CENTER);
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
		table.setModel(controller.getMembersTableModel());
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.revalidate();
		System.out.println("1");
	}

	@Override
	public void showListOfMembersWhoHasPaid() {
		table.setModel(controller.getMembersWhoHasntPaidTableModel());
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.revalidate();
		System.out.println("2");
	}

	@Override
	public void startView() {
		frame.setVisible(true);
	}

	@Override
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
}
