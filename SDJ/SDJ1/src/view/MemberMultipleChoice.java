package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemberMultipleChoice extends VIAPanel {

	private JTable table;
	private JTextField search;
	private JButton add;
	private JLabel memberList;
	private JFrame frame;
	private JButton back;
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
		String[] columnNames = { "Name", "E-mail", "ID", "Choice" };
		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", true },

		};

		search = new JTextField(47);
		search.setText("SEARCH");

		add = new VIAButtonSmall("ADD MEMBERS TO EVENT", 25);

		memberList = new VIALabel("MEMBER LIST", 30);

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Boolean.class;
				default:
					return Boolean.class;
				}
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				if(col==3)
					return true;
				else
					return false;
			}
			
		};
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		
		back = new VIAButtonBack(frame, parentPanel);
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame member = new JFrame();
				member.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				member.setSize(900, 500);
				member.setTitle("VIA - Add new member");
				member.setContentPane(new SignUpFormMember(member, currentPanel));
				member.setVisible(true);
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new MemberMultipleChoice(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}
}
