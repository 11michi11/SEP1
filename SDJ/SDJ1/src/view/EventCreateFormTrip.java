package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controler.VIAController;
import model.Event;
import model.InvalidDateInput;
import model.MyDate;
import model.Trip;

public class EventCreateFormTrip extends VIAPanel {

	private JLabel createForm;
	private JLabel title;
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
	private JTextField fieldLocation;
	private JLabel location;
	private JButton save;
	private VIAButtonBack back;
	private JTextArea descriptionArea;
	private JRadioButton finalized;
	private JRadioButton unfinalized;
	private JFrame frame;
	private JPanel parentPanel;
	private Event event;

	public EventCreateFormTrip(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public EventCreateFormTrip(JFrame frame, JPanel parentPanel, Event event) {
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

		String[] boxString = { "", "astronomy", "nature", "yoga" };
		String[] boxLecturers = { "lecturerName", "", "" };

		createForm = new VIALabel("Create Form for TRIP", 33);
		title = new JLabel("Title:");
		price = new JLabel("Price:");
		places = new JLabel("N° of Places:");
		starDate = new JLabel("Start date:");
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
		fieldLocation = new JTextField(8);
		location = new JLabel("Location:");
		save = new VIAButtonExtraSmall("SAVE", 20);
		back = new VIAButtonBack(frame, parentPanel);
		descriptionArea = new JTextArea(6, 55);

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
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> configuration = new HashMap<String, Object>();
				configuration.put("type", "Lecture");

				if (!fieldTitle.getText().equals(""))
					configuration.put("title", fieldTitle.getText());
				if (!fieldPrice.getText().equals(""))
					configuration.put("price", Double.parseDouble(fieldPrice.getText()));
				if (!fieldPlaces.getText().equals(""))
					configuration.put("capacity", Integer.parseInt(fieldPlaces.getText()));
				if (descriptionArea.getText().equals(""))
					configuration.put("descriptionArea", descriptionArea.getText());
				if (fieldLocation.getText().equals(""))
					configuration.put("location", fieldLocation.getText());

				configuration.put("finalized", finalized.isSelected());

				try {
					if (fieldStartDate.getText().equals(""))
						configuration.put("startDate", new MyDate(fieldStartDate.getText()));
					if (fieldEndDate.getText().equals(""))
						configuration.put("endDate", new MyDate(fieldEndDate.getText()));

					if (event == null)
						VIAController.addEventToList(configuration);
					else
						event.modify(configuration);

				} catch (InvalidDateInput ex) {
					JOptionPane.showMessageDialog(frame, "Invalid date format", "Date error",
							JOptionPane.PLAIN_MESSAGE);
				}

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

		fieldStartDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		fieldEndDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	public void addComponentsToPanel() {

		JPanel leftLabel = new JPanel(new GridLayout(4, 1));
		leftLabel.add(title);
		leftLabel.add(location);
		leftLabel.add(price);
		leftLabel.add(places);
		leftLabel.setOpaque(false);

		JPanel leftField = new JPanel(new GridLayout(4, 1));

		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldTitle);
		fieldOne.setOpaque(false);

		JPanel fieldTwo = new JPanel();
		fieldTwo.add(fieldLocation);
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

		JPanel rightLabel = new JPanel(new GridLayout(3, 1));
		rightLabel.add(starDate);
		rightLabel.add(endDate);
		rightLabel.add(finish);
		rightLabel.setOpaque(false);

		JPanel rightField = new JPanel(new GridLayout(3, 1));

		JPanel fieldFive = new JPanel();
		fieldFive.add(fieldStartDate);
		fieldFive.setOpaque(false);

		JPanel fieldSix = new JPanel();
		fieldSix.add(fieldEndDate);
		fieldSix.setOpaque(false);

		JPanel fieldEight = new JPanel();
		fieldEight.add(finalized);
		fieldEight.add(unfinalized);
		fieldEight.setOpaque(false);

		rightField.add(fieldFive);
		rightField.add(fieldSix);

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

	private void fillFieldWithEventData() {
		fieldTitle.setText(event.getTitle());
		fieldPrice.setText(String.valueOf(event.getPrice()));
		fieldPlaces.setText(String.valueOf(event.getCapacity()));
		fieldStartDate.setText(event.getStartDate().toString());
		fieldEndDate.setText(event.getEndDate().toString());
		location.setText(((Trip) event).getLocation());
		descriptionArea.setText(((Trip) event).getDescription());
	}

}
