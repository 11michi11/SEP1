package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignUpPanel extends VIAPanel{
	
	private JButton member;
	private JButton lecturer;
	private JButton participant;
	private JFrame frame;
	
	public SignUpPanel(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();	
	}
	
	public void initializeComponents() {	
		member = new VIAButtonSmall("MEMBER");
		member.setFont(new Font("Arial", Font.PLAIN, 30));
		lecturer = new VIAButtonSmall("LECTURER");
		lecturer.setFont(new Font("Arial", Font.PLAIN, 30));
		participant = new VIAButtonSmall("PARTICIPANT");
		participant.setFont(new Font("Arial", Font.PLAIN, 30));
		
		Dimension prefSize = new Dimension(300, 100);
		
		member.setPreferredSize(prefSize);
		lecturer.setPreferredSize(prefSize);
		participant.setPreferredSize(prefSize);
	}
	
	public void registerEventHandlers() {
		
		member.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpFormMember(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
		
		participant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpFormParticipant(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
	}
	
	public void addComponentsToPanel() {		
		JPanel first = new JPanel();
		first.add(member);
		first.setOpaque(false);
		
		JPanel second = new JPanel();
		second.add(lecturer);
		second.setOpaque(false);
		
		JPanel third = new JPanel();
		third.add(participant);
		third.setOpaque(false);
		
		JPanel buttons = new JPanel(new GridLayout(3,1));
		buttons.add(first);
		buttons.add(second);
		buttons.add(third);
		buttons.setOpaque(false);
		
		JPanel logo = new JPanel();
		logo.setOpaque(false);
		
		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		add(imgLab, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpPanel(frame));
				frame.setVisible(true);
			}
		});
	}
}