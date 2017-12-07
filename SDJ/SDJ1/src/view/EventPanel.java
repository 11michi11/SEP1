package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EventPanel extends VIAPanel {

	private VIAButton lectures;
	private JButton seminars;
	private JButton workshop;
	private JButton trips;
	private JButton back;
	private JFrame frame;
	private JPanel parentPanel;

	public EventPanel(JFrame frame, JPanel parentPanel) {
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {
		lectures = new VIAButtonBig("LECTURES");
		seminars = new VIAButtonBig("SEMINARS");
		workshop = new VIAButtonBig("WORKSHOP");
		trips = new VIAButtonBig("TRIPS");
		back = new VIAButtonBack(frame, parentPanel);

	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;

		lectures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventCreateFormLectures(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

		seminars.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventCreateFormSeminars(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

		workshop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventCreateFormWorkshop(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

		trips.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventCreateFormTrip(frame, currentPanel);
				frame.setContentPane(newContentPane);
				frame.revalidate();
			}
		});

	}

	public void addComponentsToPanel() {
		JPanel first = new JPanel();
		first.add(lectures);
		first.add(seminars);
		first.setOpaque(false);

		JPanel wrapLectures = new JPanel();
		wrapLectures.setOpaque(false);
		wrapLectures.add(lectures);

		JPanel wrapSeminars = new JPanel();
		wrapSeminars.setOpaque(false);
		wrapSeminars.add(seminars);

		JPanel wrapWorkshop = new JPanel();
		wrapWorkshop.setOpaque(false);
		wrapWorkshop.add(workshop);

		JPanel wrapTrip = new JPanel();
		wrapTrip.setOpaque(false);
		wrapTrip.add(trips);

		JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.setOpaque(false);

		buttons.add(wrapLectures);
		buttons.add(wrapSeminars);
		buttons.add(wrapWorkshop);
		buttons.add(wrapTrip);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		add(logo, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new EventPanel(frame, new JPanel()));
				frame.setVisible(true);
			}
		});
	}

}
