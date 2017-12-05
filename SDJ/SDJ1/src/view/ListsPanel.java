package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListsPanel extends VIAPanel {

	private JButton memberList;
	private JButton lecturerList;
	private JButton eventList;
	private JButton participantList;
	private JFrame frame;

	public ListsPanel(JFrame frame) {
		super();
		this.frame = frame;
		setLayout(new GridLayout(3,1));
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
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

	public void registerEventHandlers() {
	}

	public void addComponentsToPanel() {
		JPanel first = new JPanel();
		first.add(memberList);
		first.add(eventList);
		first.setOpaque(false);

		JPanel second = new JPanel();
		second.add(lecturerList);
		second.add(participantList);
		second.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		add(imgLab);
		add(first);
		add(second);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new ListsPanel(frame));
				frame.setVisible(true);
			}
		});
	}

}
