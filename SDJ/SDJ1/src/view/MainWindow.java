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

public class MainWindow extends JFrame {

	private JButton events;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;

	public MainWindow() {
		super("VIA - Management System");
		initializeFrame();
		createComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}

	private void initializeFrame() {
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void createComponents() {
		events = new VIAButton("EVENT");
		newsletter = new VIAButton("NEWSLETTER");
		signUp = new VIAButton("SIGN UP");
		listOf = new VIAButton("LIST OF");

		welcome = new JLabel("WELCOME");

		Dimension prefSize = new Dimension(400, 70);

		newsletter.setPreferredSize(prefSize);
		events.setPreferredSize(prefSize);
		signUp.setPreferredSize(prefSize);
		listOf.setPreferredSize(prefSize);

	}

	private void registerEventHandlers() {
		
		events.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel newContentPane = new EventWindow();
				newContentPane.setVisible(true);
				setContentPane(newContentPane);
				revalidate();
			}
		});
	}

	private void addComponentsToFrame() {

		VIAPanel contentPane = new VIAPanel(new BorderLayout());

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
		
		contentPane.add(imgLab, BorderLayout.NORTH);
		contentPane.add(buttons, BorderLayout.CENTER);

		setContentPane(contentPane);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow main = new MainWindow();
				main.setVisible(true);
			}
		});
	}

}
