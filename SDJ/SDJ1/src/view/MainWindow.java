package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends VIAPanel {

	private JButton events;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;
	private JFrame mainFrame;

	public MainWindow(JFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
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

		Dimension prefSize = new Dimension(350, 100);

		newsletter.setPreferredSize(prefSize);
		events.setPreferredSize(prefSize);
		signUp.setPreferredSize(prefSize);
		listOf.setPreferredSize(prefSize);

	}

	private void registerEventHandlers() {

		events.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventPanel(mainFrame);
				mainFrame.setContentPane(newContentPane);
				revalidate();
				System.out.println("!!!");
				System.out.println(mainFrame.getContentPane() instanceof EventPanel);
			}
		});
		
		newsletter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new Newsletter(mainFrame);
				mainFrame.setContentPane(newContentPane);
				revalidate();

			}
		});

		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new SignUpPanel(mainFrame);
				mainFrame.setContentPane(newContentPane);
				revalidate();

			}
		});

		listOf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new ListsPanel(mainFrame);
				mainFrame.setContentPane(newContentPane);
				revalidate();

			}
		});

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
