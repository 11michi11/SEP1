package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventWindow extends VIAPanel {

	private JButton lectures;
	private JButton seminars;
	private JButton workshop;
	private JButton trips;

	public EventWindow() {
		setLayout(new GridLayout(3,1));
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}

	public void initializeComponents() {
		lectures = new JButton("LECTURES");
		lectures.setFont(new Font("Arial", Font.PLAIN, 30));
		seminars = new JButton("SEMINARS");
		seminars.setFont(new Font("Arial", Font.PLAIN, 30));
		workshop = new JButton("WORKSHOP");
		workshop.setFont(new Font("Arial", Font.PLAIN, 30));
		trips = new JButton("TRIPS");
		trips.setFont(new Font("Arial", Font.PLAIN, 30));

		Dimension prefSize = new Dimension(300, 100);

		lectures.setPreferredSize(prefSize);
		seminars.setPreferredSize(prefSize);
		workshop.setPreferredSize(prefSize);
		trips.setPreferredSize(prefSize);
	}

	public void registerEventHandlers() {
	}

	public void addComponentsToFrame() {

		
		JPanel first = new JPanel();
		first.add(lectures);
		first.add(seminars);
		first.setOpaque(false);

		JPanel second = new JPanel();
		second.add(workshop);
		second.add(trips);
		second.setOpaque(false);

		JPanel logo = new JPanel();
		logo.setOpaque(false);

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
				frame.setContentPane(new EventWindow());
				frame.setVisible(true);
			}
		});
	}

}