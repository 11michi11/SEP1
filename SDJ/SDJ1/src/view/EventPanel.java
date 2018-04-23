package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.VIAController;

public class EventPanel extends VIAPanel {

	private VIAButton lectures;
	private JButton seminars;
	private JButton workshop;
	private JButton trips;
	private VIAButtonBack back;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAController controller;

	public EventPanel(JFrame frame, JPanel parentPanel) {
		this.frame = frame;
		controller = VIAController.getInstance();
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

		lectures.addActionListener(e -> controller.showEventCreateFormLectures(currentPanel));

		seminars.addActionListener(e -> controller.showEventCreateFormSeminars(currentPanel));

		workshop.addActionListener(e -> controller.showEventCreateFormWorkshop(currentPanel));

		trips.addActionListener(e -> controller.showEventCreateFormTrip(currentPanel));

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parentPanel instanceof EventListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
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
}
