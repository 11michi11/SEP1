package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.VIAController;

public class StartPanel extends VIAPanel {

	private JButton events;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;
	private JFrame frame;
	private VIAController controller;

	public StartPanel(JFrame mainFrame) {
		super();
		controller = VIAController.getInstance();
		this.frame = mainFrame;
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
		welcome = new JLabel("WELCOME");
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
