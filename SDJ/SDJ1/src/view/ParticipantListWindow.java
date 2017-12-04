package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ParticipantListWindow extends JFrame{


	private JTable table;
	private JTextField search;
	private JButton add;
	private JLabel participantList;
	
		public ParticipantListWindow() {
		setLayout(new FlowLayout());
		setSize(750, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Member List");
		
		
		String[] columnNames= {"Name", "E-mail"};
		Object[][] data = {
				{"Matej", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Michal", "andasfsuf@gdgdfg.com"},
				{"Miska", "andasfsuf@gdgdfg.com"},
						
		};
		
		search = new JTextField(47);
		search.setText("SEARCH");
		
		add = new JButton("ADD PARTICIPANT");
		add.setFont(new Font("Arial", Font.PLAIN, 20));


		
		participantList = new JLabel("PARTICIPANT LIST");
		participantList.setFont(new Font("Arial", Font.PLAIN, 30));
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,100));
		
		VIAPanel components = new VIAPanel(new BorderLayout());
		
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
		labelPanel.add(participantList);
		labelPanel.setOpaque(false);
		
		JPanel logo = new JPanel();
		logo.setOpaque(false);
		
		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		JPanel componentsF = new JPanel(new BorderLayout());
		componentsF.add(labelPanel, BorderLayout.NORTH);
		componentsF.add(left, BorderLayout.WEST);
		componentsF.add(right, BorderLayout.EAST);
		componentsF.setOpaque(false);
		
		components.add(imgLab, BorderLayout.NORTH);
		components.add(componentsF, BorderLayout.CENTER);
		
		setContentPane(components);
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ParticipantListWindow main = new ParticipantListWindow();
				main.setVisible(true);
			}
		});

	}

}

