package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	private JButton createEvent;
	private JButton newsletter;
	private JButton signUp;
	private JButton listOf;
	private JLabel welcome;

	public MainWindow() {
		super("Main Page");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}

	public void createComponents() {
		
		Font btnFont = new Font("Arial", Font.PLAIN, 40);

		createEvent = new VIAButton("EVENT");
		createEvent.setBackground(new Color(0,0,0,0));
		createEvent.setBorderPainted(false);
		createEvent.setFont(btnFont);

		newsletter = new JButton("NEWSLETTER");
		newsletter.setFont(btnFont);

		signUp = new JButton("SIGN UP");
		signUp.setFont(btnFont);

		listOf = new JButton("LIST OF");
		listOf.setFont(btnFont);

		welcome = new JLabel("WELCOME");

		Dimension prefSize = new Dimension(400, 70);

		newsletter.setPreferredSize(prefSize);
		createEvent.setPreferredSize(prefSize);
		signUp.setPreferredSize(prefSize);
		listOf.setPreferredSize(prefSize);

	}

	public void initializeComponents() {

		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void registerEventHandlers() {

	}

	public void addComponentsToFrame() {

		VIAPanel components = new VIAPanel(new BorderLayout());

		JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.setOpaque(false);

		JPanel wrapEvent = new JPanel();
		wrapEvent.setOpaque(false);
		wrapEvent.add(createEvent);

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

		JPanel welcomeLogo = new JPanel();
		welcomeLogo.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/logoGUI.png");
		JLabel imgLab = new JLabel(img);

		welcomeLogo.add(imgLab);
		components.add(welcomeLogo, BorderLayout.NORTH);
		components.add(buttons, BorderLayout.CENTER);

		setContentPane(components);
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
