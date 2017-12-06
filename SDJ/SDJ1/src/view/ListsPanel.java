package view;

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
		lecturerList = new VIAButtonThree("LECTURER LIST");
		lecturerList.setFont(new Font("Arial", Font.PLAIN, 30));
		memberList = new VIAButtonThree("MEMBER LIST");
		memberList.setFont(new Font("Arial", Font.PLAIN, 30));
		eventList = new VIAButtonThree("EVENT LIST");
		eventList.setFont(new Font("Arial", Font.PLAIN, 30));
		participantList = new VIAButtonThree("PARTICIPANT LIST");
		participantList.setFont(new Font("Arial", Font.PLAIN, 30));

		Dimension prefSize = new Dimension(350, 100);

		lecturerList.setPreferredSize(prefSize);
		memberList.setPreferredSize(prefSize);
		eventList.setPreferredSize(prefSize);
		participantList.setPreferredSize(prefSize);
	}

	public void registerEventHandlers() {
		
		lecturerList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new LecturerListPanel(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
		
		memberList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new MemberListPanel(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
		
		eventList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventListPanel(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
		
		participantList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new ParticipantListPanel(frame);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});
		
		
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
