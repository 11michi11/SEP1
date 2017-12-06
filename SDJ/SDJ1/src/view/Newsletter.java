package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Newsletter extends VIAPanel{
	
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel listOfNewsletter;
	private JButton generateText;
	private JLabel newsletter;
	private JTextArea info;
	private JLabel addInfo;
	private JFrame frame;

	
	
	public Newsletter(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}
	
	private void initializeComponents() {
		String[] columnNames = { "Previous newsletter", "New newsletter" };
		Object[][] data = { {"Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Miska", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
				{ "Matej", "andasfsuf@gdgdfg.com"},
		};
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 50));
		
		scrollPane = new JScrollPane(table);
		
		listOfNewsletter = new JLabel("LIST OF NEWSLETTER");	
		generateText = new VIAButton("GENERATE TEXT");
		newsletter = new JLabel("NEWSLETTER");
		newsletter.setFont(new Font("Arial", Font.PLAIN, 30));
		info = new JTextArea(10,30);
		addInfo = new JLabel("ADDITIONAL INFO");
				
		
	}
	
	private void registerEventHandlers() {
		// TODO Auto-generated method stub
		
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
		
		//add(left);
		//add(right);
		JPanel logo = new JPanel();
		logo.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		JPanel title = new JPanel();
		title.add(newsletter);
		title.setOpaque(false);
		
		JPanel north = new JPanel(new BorderLayout());
		north.add(imgLab, BorderLayout.NORTH);
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
	
		


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new Newsletter(frame));
				frame.setVisible(true);
			}
		});

	}
	

}
