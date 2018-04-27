package view;

import controler.VIAController;
import domain.model.*;
import domain.model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Objects;

public class EventCreateFormLectures extends VIAPanel {

	private static JTextField fieldLecturer;
	private static Lecturer lecturer;
	private JLabel createForm;
	private JLabel title;
	private JLabel category;
	private JLabel price;
	private JLabel places;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel description;
	private JLabel finish;
	private JTextField fieldTitle;
	private JTextField fieldPrice;
	private JTextField fieldPlaces;
	private JTextField fieldStartDate;
	private JTextField fieldEndDate;
	private JButton save;
	private VIAButtonBack back;
	private JTextArea descriptionArea;
	private JRadioButton finalized;
	private JRadioButton unfinalized;
	private JFrame frame;
	private JPanel parentPanel;
	private JComboBox<String> categoryBox;
	private JButton lecturerChoice;
	private Event event;
	private VIAController controller;

	public EventCreateFormLectures(JFrame frame, JPanel parentPanel) {
		super();
		controller = VIAController.getInstance();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public EventCreateFormLectures(JFrame frame, JPanel parentPanel, Event event) {
		super();
		this.frame = frame;
		this.event = event;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {

		String[] boxString = { "", "Astrology", "Meditation", "Reincarnation", "Health", "Buddhism", "Nature",
				"Other" };

		createForm = new VIALabel("Create Form for LECTURES", 33);
		title = new JLabel("Title:");
		category = new JLabel("Category:");
		price = new JLabel("Price:");
		places = new JLabel("N° of Places:");
		startDate = new JLabel("Start date:");
		endDate = new JLabel("End date:");
		finish = new JLabel("Finalized");
		description = new VIALabel("DESCRIPTION:", 20);
		fieldTitle = new JTextField(8);
		fieldPrice = new JTextField(8);
		fieldPlaces = new JTextField(8);
		fieldStartDate = new JTextField(10);
		fieldStartDate.setText("dd/mm/yyyy/hh:mm");
		fieldEndDate = new JTextField(10);
		fieldEndDate.setText("dd/mm/yyyy/hh:mm");
		fieldLecturer = new JTextField(10);
		lecturerChoice = new VIAButtonExtraSmall("Lecturers", 20);
		save = new VIAButtonExtraSmall("SAVE", 20);
		back = new VIAButtonBack(frame, parentPanel);
		categoryBox = new JComboBox<>(boxString);
		categoryBox.setSelectedIndex(2);
		descriptionArea = new JTextArea(6, 55);

		finalized = new JRadioButton("YES");
		finalized.setOpaque(false);

		unfinalized = new JRadioButton("NO");
		unfinalized.setSelected(true);
		unfinalized.setOpaque(false);

		ButtonGroup group = new ButtonGroup();
		group.add(finalized);
		group.add(unfinalized);

		if (event != null)
			fillFieldWithEventData();
	}

	public void registerEventHandlers() {
		lecturerChoice.addActionListener(e -> controller.showLecturerChoiceWindow());

		save.addActionListener(e -> {

			HashMap<String, Object> configuration = new HashMap<>();
			configuration.put("type", "Lecture");

			if (!fieldTitle.getText().equals(""))
				configuration.put("title", fieldTitle.getText());
			if (!fieldPrice.getText().equals(""))
				configuration.put("price", Double.parseDouble(fieldPrice.getText()));
			if (!fieldPlaces.getText().equals(""))
				configuration.put("capacity", Integer.parseInt(fieldPlaces.getText()));
			if (descriptionArea.getText().equals(""))
				configuration.put("descriptionArea", descriptionArea.getText());
			if (Objects.requireNonNull(categoryBox.getSelectedItem()).toString().equals(""))
				configuration.put("category", CategoryFactory.getCategory(categoryBox.getSelectedItem().toString()));
			if (lecturer != null)
				configuration.put("lecturer", lecturer);

			configuration.put("finalized", finalized.isSelected());

			boolean close = false;

			try {
				if (!fieldStartDate.getText().equals(""))
					configuration.put("startDate", new MyDate(fieldStartDate.getText()));
				if (!fieldEndDate.getText().equals(""))
					configuration.put("endDate", new MyDate(fieldEndDate.getText()));

				if (event == null)
					controller.addEventToList(configuration);
				else
					event.modify(configuration);

				close = true;
			} catch (InvalidDateInput ex) {
				JOptionPane.showMessageDialog(frame, "Invalid date format", "Date error",
						JOptionPane.PLAIN_MESSAGE);
			}
			if (close) {
				if (frame.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE)
					frame.dispose();
				else
					back.goBack();
			}
		});

		fieldStartDate.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (fieldStartDate.getText().equals("dd/mm/yyyy/hh:mm"))
					fieldStartDate.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (fieldStartDate.getText().equals(""))
					fieldStartDate.setText("dd/mm/yyyy/hh:mm");
			}
		});

		fieldEndDate.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (fieldEndDate.getText().equals("dd/mm/yyyy/hh:mm"))
					fieldEndDate.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (fieldEndDate.getText().equals(""))
					fieldEndDate.setText("dd/mm/yyyy/hh:mm");
			}
		});
	}

	public void addComponentsToPanel() {

		JPanel leftLabel = new JPanel(new GridLayout(4, 1));
		leftLabel.add(title);
		leftLabel.add(category);
		leftLabel.add(price);
		leftLabel.add(places);
		leftLabel.setOpaque(false);

		JPanel leftField = new JPanel(new GridLayout(4, 1));

		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldTitle);
		fieldOne.setOpaque(false);

		JPanel fieldTwo = new JPanel();
		fieldTwo.add(categoryBox);
		fieldTwo.setOpaque(false);

		JPanel fieldThree = new JPanel();
		fieldThree.add(fieldPrice);
		fieldThree.setOpaque(false);

		JPanel fieldFour = new JPanel();
		fieldFour.add(fieldPlaces);
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
		rightLabel.add(startDate);
		rightLabel.add(endDate);
		rightLabel.add(lecturerChoice);
		rightLabel.add(finish);
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
		fieldEight.add(finalized);
		fieldEight.add(unfinalized);
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

	static void assignLecturerToForm(Lecturer lecturerChosen) {
		lecturer = lecturerChosen;
		fieldLecturer.setText(lecturer.getName());
	}

	private void fillFieldWithEventData() {
		fieldTitle.setText(event.getTitle());
		fieldPrice.setText(String.valueOf(event.getPrice()));
		fieldPlaces.setText(String.valueOf(event.getCapacity()));
		fieldStartDate.setText(event.getStartDate().toString());
		fieldEndDate.setText(event.getEndDate().toString());
		assignLecturerToForm(((Lecture) event).getLecturer());
	}
}
