package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controler.VIAController;
import model.Member;
import model.MemberAlreadyPaidException;

public class MemberListPanel extends VIAPanel {

	private static JTable table;
	private JTextField search;
	private JButton add;
	private JButton mail;
	private JButton delete;
	private JLabel memberList;
	private JFrame frame;
	private JButton back;
	private JPanel parentPanel;
	private static int clickCount = 0;

	public MemberListPanel(JFrame frame, JPanel parentPanel) {
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

		add = new VIAButtonSimple("ADD MEMBER", 20);
		mail = new VIAButtonSimple("SEND REMAIND E-MAIL", 20);
		delete = new VIAButtonSimple("DELETE MEMBER", 20);
		delete.setEnabled(false);

		memberList = new VIALabel("MEMBER LIST", 40);

		DefaultTableModel model = VIAController.getMembersTableModel();
		table = new JTable(model);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));

		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				delete.setEnabled(true);
				
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
					} else if ((boolean) model.getValueAt(selRow, 3) == true && clickCount == 1)
						member.unPay();
					
					if(clickCount == 2)
						clickCount = 0;
				}
			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame member = new JFrame();
				member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				member.setSize(900, 500);
				member.setTitle("VIA - Add new member");
				member.setContentPane(new SignUpFormMember(member, currentPanel));
				member.setVisible(true);
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = VIAController.getSearchedMembers(search.getText());
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

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Member member = (Member) table.getModel().getValueAt(table.getSelectedRow(), 5);
				VIAController.deleteMember(member);
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

		JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deletePanel.add(delete);
		deletePanel.setOpaque(false);

		JPanel right = new JPanel(new GridLayout(3, 1));
		right.add(addPanel);
		right.add(deletePanel);
		right.add(mailPanel);
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
