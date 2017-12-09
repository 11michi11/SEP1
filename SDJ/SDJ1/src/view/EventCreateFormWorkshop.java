package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventCreateFormWorkshop extends VIAPanel {

	private JLabel createForm;
	private JLabel title;
	private JButton category;
	private JLabel price;
	private JLabel places;
	private JLabel starDate;
	private JLabel endDate;
	private JLabel description;
	private JLabel finish;
	private JTextField fieldTitle;
	private JTextField fieldPrice;
	private JTextField fieldPlaces;
	private JTextField fieldStartDate;
	private JTextField fieldEndDate;
	private JComboBox fieldLecturer;
	private JButton lecturer;
	private JButton save;
	private VIAButtonBack back;
	private JComboBox categoryBox;
	private JTextArea descriptionArea;
	private JRadioButton finalized;
	private JRadioButton unfinalized;
	private JFrame frame;
	private JPanel parentPanel;

	public EventCreateFormWorkshop(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {

		String[] boxString = { "categoryTitle", "" };
		String[] boxLecturers = { "lecturerName", "" };

		createForm = new VIALabel("Create Form for WORKSHOP", 33);
		title = new JLabel("Title:");
		category = new VIAButtonExtraSmall("Category", 20);
		price = new JLabel("Price:");
		places = new JLabel("N� of Places:");
		starDate = new JLabel("Start date:");
		endDate = new JLabel("End date:");
		finish = new JLabel("Finalized");
		description = new VIALabel("DESCRIPTION:", 20);
		fieldTitle = new JTextField(8);
		fieldPrice = new JTextField(8);
		fieldPlaces = new JTextField(8);
		fieldStartDate = new JTextField(8);
		fieldEndDate = new JTextField(8);
		fieldLecturer = new JComboBox(boxLecturers);
		lecturer = new VIAButtonExtraSmall("Lecturers", 20);
		save = new VIAButtonExtraSmall("SAVE", 20);
		back = new VIAButtonBack(frame, parentPanel);
		categoryBox = new JComboBox(boxString);
		descriptionArea = new JTextArea(5, 55);

		finalized = new JRadioButton("YES");
		finalized.setSelected(true);
		finalized.setOpaque(false);

		unfinalized = new JRadioButton("NO");
		unfinalized.setOpaque(false);

		ButtonGroup group = new ButtonGroup();
		group.add(finalized);
		group.add(unfinalized);

	}

	public void registerEventHandlers() {
		lecturer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame lecturerChoice = new JFrame();
				lecturerChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lecturerChoice.setSize(900, 500);
				lecturerChoice.setTitle("VIA - Choice of lecturer for event");
				lecturerChoice.setContentPane(new LecturerMultipleChoiceList(lecturerChoice));
				lecturerChoice.setVisible(true);

			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE)
					frame.dispose();
				else
					back.goBack();
			}
		});

		category.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame categoryChoice = new JFrame();
				categoryChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				categoryChoice.setSize(900, 500);
				categoryChoice.setTitle("VIA - Choice of category for event");
				categoryChoice.setContentPane(new CategoryMultipleChoiceList(categoryChoice));
				categoryChoice.setVisible(true);
			}
		});

	}

	public void addComponentsToPanel() {

		JPanel leftLabel = new JPanel(new GridLayout(4, 1));
		leftLabel.add(title);
		leftLabel.add(price);
		leftLabel.add(places);
		leftLabel.add(finish);
		leftLabel.setOpaque(false);

		JPanel leftField = new JPanel(new GridLayout(4, 1));

		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldTitle);
		fieldOne.setOpaque(false);

		JPanel fieldTwo = new JPanel();
		fieldTwo.add(fieldPrice);
		fieldTwo.setOpaque(false);

		JPanel fieldThree = new JPanel();
		fieldThree.add(fieldPlaces);
		fieldThree.setOpaque(false);

		JPanel fieldFour = new JPanel();
		fieldFour.add(finalized);
		fieldFour.add(unfinalized);
		fieldFour.setOpaque(false);

		leftField.add(fieldOne);
		leftField.add(fieldTwo);
		leftField.add(fieldThree);
		leftField.add(fieldFour);
		leftField.setOpaque(false);

		JPanel left = new JPanel(new GridLayout(1, 2));
		left.add(leftLabel);
		left.add(leftField);
		left.setOpaque(false);

		JPanel leftSide = new JPanel();
		leftSide.add(left);
		leftSide.setOpaque(false);

		JPanel rightLabel = new JPanel(new GridLayout(4, 1));
		rightLabel.add(starDate);
		rightLabel.add(endDate);
		rightLabel.add(lecturer);
		rightLabel.add(category);
		rightLabel.setOpaque(false);

		JPanel rightField = new JPanel(new GridLayout(4, 1));

		JPanel fieldFive = new JPanel();
		fieldFive.add(fieldStartDate);
		fieldFive.setOpaque(false);

		JPanel fieldSix = new JPanel();
		fieldSix.add(fieldEndDate);
		fieldSix.setOpaque(false);

		JPanel fieldSeven = new JPanel();
		fieldSeven.add(fieldLecturer);
		fieldSeven.setOpaque(false);

		JPanel fieldEight = new JPanel();
		fieldEight.add(categoryBox);
		fieldEight.setOpaque(false);

		rightField.add(fieldFive);
		rightField.add(fieldSix);
		rightField.add(fieldSeven);
		rightField.add(fieldEight);
		rightField.setOpaque(false);

		JPanel right = new JPanel(new GridLayout(1, 2));
		right.add(rightLabel);
		right.add(rightField);
		right.setOpaque(false);

		JPanel rightSide = new JPanel();
		rightSide.add(right);
		rightSide.setOpaque(false);

		JPanel content = new JPanel(new GridLayout(1, 2));
		content.add(leftSide);
		content.add(rightSide);
		content.setOpaque(false);

		JPanel button = new JPanel();
		button.add(save);
		button.setOpaque(false);

		JPanel titul = new JPanel();
		titul.add(createForm);
		titul.setOpaque(false);

		JPanel components = new JPanel(new BorderLayout());
		components.add(titul, BorderLayout.NORTH);
		components.add(content, BorderLayout.CENTER);
		// components.add(button, BorderLayout.SOUTH);
		components.setOpaque(false);

		JPanel textArea = new JPanel(new BorderLayout());
		textArea.add(description, BorderLayout.NORTH);
		textArea.add(descriptionArea, BorderLayout.CENTER);
		textArea.setOpaque(false);

		JPanel downPart = new JPanel(new FlowLayout(FlowLayout.LEFT));
		downPart.add(textArea);
		downPart.add(save);
		downPart.setOpaque(false);

		JPanel down = new JPanel();
		down.add(downPart);
		down.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.add(components, BorderLayout.CENTER);
		finalPanel.add(down, BorderLayout.SOUTH);
		finalPanel.setOpaque(false);

		add(logo, BorderLayout.NORTH);
		add(finalPanel, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new EventCreateFormWorkshop(frame, new JPanel()));
				frame.setVisible(true);
			}
		});
	}
}
