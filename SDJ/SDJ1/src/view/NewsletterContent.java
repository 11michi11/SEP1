package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NewsletterContent extends VIAPanel{
	
	private JScrollPane scrollPane;
	private JLabel newsletter;
	private JTextArea content;
	private JFrame frame;
	private JPanel parentPanel;
	private JButton back;
	
	public NewsletterContent(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}
	public void initializeComponents() {
		
		content = new JTextArea(100,50);
		content.setEditable(false);
		scrollPane = new JScrollPane(content);
		newsletter = new VIALabel("NEWSLETTER CONTENT", 30);
		back = new VIAButtonBack(frame, parentPanel);
	}
	
	public void registerEventHandlers() {}
	
	public void addComponentsToPanel() {
		
		JScrollPane scrollPane = new JScrollPane(content);
		add(scrollPane);
		
		//JPanel ContentPanel = new JPanel();
		//ContentPanel.add(content, scrollPane);
		//ContentPanel.setOpaque(false);
		
		JPanel title = new JPanel();
		title.add(newsletter);
		title.setOpaque(false);
;
		
		add(title, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new NewsletterContent(frame, new JPanel()));
				frame.setVisible(true);
			}
		});
	}
	
	
	

}
