package server.view;

import server.controler.VIAController;
import server.domain.model.Member;
import server.domain.model.MemberAlreadyPaidException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class MemberListPanel extends VIAPanel {

	private static JTable table;
	private static int clickCount = 0;
	private JTextField search;
	private JButton add;
	private JButton mail;
	private JButton delete;
	private JButton mailPaid;
	private JLabel memberList;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;
	private VIAController controller;

	public MemberListPanel(JFrame frame, JPanel parentPanel) {
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

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSimple("ADD MEMBER", 20);
		mail = new VIAButtonSimple("GENERATE EMAILS LIST", 20);
		delete = new VIAButtonSimple("DELETE MEMBER", 20);
		mailPaid = new VIAButtonSimple("EMAIL LIST OF WHO HASN'T PAID", 15);
		delete.setEnabled(false);

		memberList = new VIALabel("MEMBER LIST", 40);

		DefaultTableModel model = controller.getMembersTableModel();
		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));

		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;

		table.getSelectionModel().addListSelectionListener(event -> {
			delete.setEnabled(true);

			//Click count prevents double click bug
			clickCount++;

			TableModel model = table.getModel();
			int selRow = table.getSelectedRow();
			if (table.getSelectedColumn() == 3) {
				Member member = (Member) model.getValueAt(selRow, 5);
				if (!member.hasPaid() && clickCount == 1) {
					try {
						member.pay();
					} catch (MemberAlreadyPaidException e) {
						e.printStackTrace();
					}
				} else if ((boolean) model.getValueAt(selRow, 3) && clickCount == 1)
					member.unPay();

				if (clickCount == 2)
					clickCount = 0;
			}
		});

		add.addActionListener(e -> controller.showSignUpFormMember(currentPanel));

		search.addActionListener(e -> {
			DefaultTableModel model = controller.getSearchedMembers(search.getText());
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(5));
		});

		search.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				search.setText("");
			}

			public void focusLost(FocusEvent e) {

			}
		});

		delete.addActionListener(e -> {
			Member member = (Member) table.getModel().getValueAt(table.getSelectedRow(), 5);
			controller.deleteMember(member);
		});

		mail.addActionListener(e -> {
			try {
				controller.generateAllEmails();
				JOptionPane.showMessageDialog(frame, "File generated successfully", "File generated",
						JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Something went wrong. Contact administrator", "File error",
						JOptionPane.PLAIN_MESSAGE);
			}
		});

		mailPaid.addActionListener(e -> {
			try {
				controller.generateEmailsWhoHasntPaid();
				JOptionPane.showMessageDialog(frame, "File generated successfully", "File generated",
						JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Something went wrong. Contact administrator", "File error",
						JOptionPane.PLAIN_MESSAGE);
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

		JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addPanel.add(add);
		addPanel.setOpaque(false);

		JPanel mailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mailPanel.add(mail);
		mailPanel.setOpaque(false);

		JPanel mailPaidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mailPaidPanel.add(mailPaid);
		mailPaidPanel.setOpaque(false);

		JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deletePanel.add(delete);
		deletePanel.setOpaque(false);

		JPanel right = new JPanel(new GridLayout(4, 1));
		right.add(addPanel);
		right.add(deletePanel);
		right.add(mailPanel);
		right.add(mailPaidPanel);
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

	public static void refreshTable() {
		if (table != null) {
			DefaultTableModel model = VIAController.getMembersTableModel();
			table.setModel(model);
			table.removeColumn(table.getColumnModel().getColumn(5));
		}
	}
}
