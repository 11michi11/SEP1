package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberListWindow extends JFrame{
	
	private JTable table;
	private JTextField search;
	private JButton add;
	private JButton mail;
	private JLabel memberList;
	
	
	public MemberListWindow() {
		
		setLayout(new FlowLayout());
		setSize(800, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Member List");
		
		
		String[] columnNames= {"Name", "E-mail", "ID", "Paid"};
		Object[][] data = {
				{"Matej", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Michal", "andasfsuf@gdgdfg.com", "59599295", "yes"},
				{"Miska", "andasfsuf@gdgdfg.com", "59599295", "yes"},
						
		};
		
		search = new JTextField(47);
		search.setText("SEARCH");
		
		add = new JButton("ADD MEMBER");
		add.setFont(new Font("Arial", Font.PLAIN, 20));
		mail = new JButton("SEND REMAIND E-MAIL");
		mail.setFont(new Font("Arial", Font.PLAIN, 20));
		
		Dimension prefSize = mail.getPreferredSize();
		add.setPreferredSize(prefSize);
		
		memberList = new JLabel("MEMBER LIST");
		memberList.setFont(new Font("Arial", Font.PLAIN, 30));
			
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,100));
	//	table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		
		JPanel left = new JPanel(new BorderLayout());
		left.add(search, BorderLayout.NORTH);
		left.add(scrollPane, BorderLayout.CENTER);
		
		
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.add(add);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
		south.add(mail);
		
		JPanel right = new JPanel(new BorderLayout());
		right.add(north, BorderLayout.NORTH);
		right.add(south, BorderLayout.CENTER);
		
		JPanel labelPanel = new JPanel();
		labelPanel.add(memberList);
		
		
		
		JPanel component = new JPanel(new BorderLayout());
		component.add(labelPanel, BorderLayout.NORTH);
		component.add(left, BorderLayout.WEST);
		component.add(right, BorderLayout.EAST);
		
		setContentPane(component);
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MemberListWindow main = new MemberListWindow();
				main.setVisible(true);
			}
		});

	}
}
