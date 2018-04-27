package view;

import controler.VIAController;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends VIAPanel {

	private JButton events;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private VIAController controller;

	public StartPanel() {
		super();
		controller = VIAController.getInstance();
		setLayout(new BorderLayout());
		createComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}

	private void createComponents() {
		events = new VIAButtonBig("EVENT");
		newsletter = new VIAButtonBig("NEWSLETTER");
		signUp = new VIAButtonBig("SIGN UP");
		listOf = new VIAButtonBig("LIST OF");
	}

	private void registerEventHandlers() {
		JPanel currentPanel = this;

		events.addActionListener(e -> controller.showEventsPanel(currentPanel));

		newsletter.addActionListener(e -> controller.showNewsletterPanel(currentPanel));

		signUp.addActionListener(e -> controller.showSignUpPanel(currentPanel));

		listOf.addActionListener(e -> controller.showListsPanel(currentPanel));

	}

	private void addComponentsToFrame() {
		JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.setOpaque(false);

		JPanel wrapEvent = new JPanel();
		wrapEvent.setOpaque(false);
		wrapEvent.add(events);

		JPanel wrapSignup = new JPanel();
		wrapSignup.setOpaque(false);
		wrapSignup.add(signUp);

		JPanel wrapNewsletter = new JPanel();
		wrapNewsletter.setOpaque(false);
		wrapNewsletter.add(newsletter);

		JPanel wrapList = new JPanel();
		wrapList.setOpaque(false);
		wrapList.add(listOf);

		buttons.add(wrapEvent);
		buttons.add(wrapSignup);
		buttons.add(wrapNewsletter);
		buttons.add(wrapList);

		ImageIcon img = new ImageIcon("src/resources/logoGUI.png");
		JLabel imgLab = new JLabel(img);

		add(imgLab, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}
}
