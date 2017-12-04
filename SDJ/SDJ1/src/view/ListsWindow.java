package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListsWindow extends JFrame{
	
	private JButton memberList;
	private JButton lecturerList;
	private JButton eventList;
	private JButton participantList;
	
	public ListsWindow() {
		super("List Of");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}
	
	public void createComponents(){
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initializeComponents() {
		
		lecturerList = new JButton("LECTURER LIST");
		lecturerList.setFont(new Font("Arial", Font.PLAIN, 30));
		memberList = new JButton("MEMBER LIST");
		memberList.setFont(new Font("Arial", Font.PLAIN, 30));
		eventList = new JButton("EVENT LIST");
		eventList.setFont(new Font("Arial", Font.PLAIN, 30));
		participantList = new JButton("PARTICIPANT LIST");
		participantList.setFont(new Font("Arial", Font.PLAIN, 30));

		
		Dimension prefSize = new Dimension(300, 100);
		
		lecturerList.setPreferredSize(prefSize);
		memberList.setPreferredSize(prefSize);
		eventList.setPreferredSize(prefSize);
		participantList.setPreferredSize(prefSize);
		
		
	}
	
	public void registerEventHandlers() {}
	
	public void addComponentsToFrame() {
		
		VIAPanel components = new VIAPanel();
		
		JPanel first = new JPanel();
		first.add(memberList);
		first.add(eventList);
		first.setOpaque(false);
		
		JPanel second = new JPanel();
		second.add(lecturerList);
		second.add(participantList);
		second.setOpaque(false);
		
		JPanel logo = new JPanel();
		logo.setOpaque(false);
		
		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		components.add(imgLab);
		components.add(first);
		components.add(second);
		
		setContentPane(components);
	
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ListsWindow main = new ListsWindow();
				main.setVisible(true);
			}
		});
	}

	

}
