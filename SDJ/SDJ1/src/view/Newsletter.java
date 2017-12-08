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
import javax.swing.JTextArea;

public class Newsletter extends VIAPanel{
	
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel listOfNewsletter;
	private JButton generateText;
	private JLabel newsletter;
	private JTextArea info;
	private JLabel addInfo;
	private JFrame frame;
	private JPanel parentPanel;
	private JButton back;
	
	public Newsletter(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}
	
	private void initializeComponents() {
		String[] columnNames = { "Name of newsletter"};
		Object[][] data = { {"Michal", "andasfsuf@gdgdfg.com"},
				{ "Michal andasfsuf@gdgdfg.com"},
				{ "Michal andasfsuf@gdgdfg.com"},
				{ "Michaandasfsuf@gdgdfg.com"},
				{ "Michalandasfsuf@gdgdfg.com"},
				{ "Michalandasfsuf@gdgdfg.com"},
				{ "Miskaandasfsuf@gdgdfg.com"},
				{ "Miskaandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				{ "Miskandasfsuf@gdgdfg.com"},
				
		};
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 50));
		
		scrollPane = new JScrollPane(table);
		
		listOfNewsletter = new VIALabel("LIST OF NEWSLETTER", 20);	
		generateText = new VIAButtonSimple("Generate text");
		newsletter = new VIALabel("NEWSLETTER", 50);
		info = new JTextArea(10,30);
		addInfo = new VIALabel("ADDITIONAL INFO",20);
		back = new VIAButtonBack(frame, parentPanel);
				
		
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
		
		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);
		
		JPanel title = new JPanel();
		title.add(newsletter);
		title.setOpaque(false);
		
		JPanel north = new JPanel(new BorderLayout());
		north.add(logo, BorderLayout.NORTH);
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
				frame.setContentPane(new Newsletter(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}
	

}
