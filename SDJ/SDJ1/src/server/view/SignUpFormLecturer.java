package server.view;

import server.controler.VIAController;
import server.domain.model.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SignUpFormLecturer extends VIAPanel {

	private static JComboBox<String> categoryBox;
	private static ArrayList<Category> categories = new ArrayList<>();
	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JLabel phone;
	private JButton category;
	private JButton add;
	private VIAButtonBack back;
	private JCheckBox advertisement;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JTextField fieldPhone;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAController controller;

	public SignUpFormLecturer(JFrame frame, JPanel parentPanel) {
		super();
		controller = VIAController.getInstance();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {
		String[] boxString = { "Choose category" };

		signUp = new VIALabel("Sign-Up Form for LECTURER");
		name = new JLabel("Name:");
		email = new JLabel("E-mail:");
		phone = new JLabel("Phone:");
		category = new VIAButtonExtraSmall("Category", 20);
		advertisement = new JCheckBox("Advertisment");
		advertisement.setOpaque(false);
		fieldName = new JTextField(8);
		fieldEmail = new JTextField(8);
		fieldPhone = new JTextField(8);
		categoryBox = new JComboBox<>(boxString);

		add = new VIAButtonSmall("Add to list");
		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;
		back.changeListener(e -> {
			if (parentPanel instanceof LecturerListPanel) {
				frame.dispose();
			} else {
				back.goBack();
			}
		});

		add.addActionListener(e -> {
			if (canFormBeSaved()) {
				Object[] configuration = new Object[5];
				configuration[0] = fieldName.getText();
				configuration[1] = fieldEmail.getText();
				configuration[2] = Integer.parseInt(fieldPhone.getText());
				configuration[3] = categories;
				configuration[4] = advertisement.isSelected();

				VIAController.addLecturerToList(configuration);

				if (parentPanel instanceof EventListPanel || parentPanel instanceof LecturerListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Fill all filed to save lecturer", "Form error",
						JOptionPane.PLAIN_MESSAGE);
			}
		});

		category.addActionListener(e -> controller.showCategoryMultipleChoiceList(currentPanel));
	}

	public void addComponentsToPanel() {
		JPanel leftLabel = new JPanel(new GridLayout(4, 1));
		leftLabel.add(name);
		leftLabel.add(email);
		leftLabel.add(category);
		leftLabel.setOpaque(false);

		JPanel leftField = new JPanel(new GridLayout(4, 1));

		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldName);
		fieldOne.setOpaque(false);

		JPanel fieldTwo = new JPanel();
		fieldTwo.add(fieldEmail);
		fieldTwo.setOpaque(false);

		JPanel fieldThree = new JPanel();
		fieldThree.add(categoryBox);
		fieldThree.setOpaque(false);

		leftField.add(fieldOne);
		leftField.add(fieldTwo);
		leftField.add(fieldThree);
		leftField.setOpaque(false);

		JPanel left = new JPanel(new GridLayout(1, 2));
		left.add(leftLabel);
		left.add(leftField);
		left.setOpaque(false);

		JPanel leftSide = new JPanel();
		leftSide.add(left);
		leftSide.setOpaque(false);

		JPanel rightLabel = new JPanel(new GridLayout(2, 1));
		rightLabel.add(phone);
		rightLabel.add(advertisement);
		rightLabel.setOpaque(false);

		JPanel rightField = new JPanel(new GridLayout(2, 1));

		JPanel fieldFive = new JPanel();
		fieldFive.add(fieldPhone);
		fieldFive.setOpaque(false);

		rightField.add(fieldFive);
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
		button.add(add);
		button.setOpaque(false);

		JPanel titul = new JPanel();
		titul.add(signUp);
		titul.setOpaque(false);

		JPanel components = new JPanel(new BorderLayout());
		components.add(titul, BorderLayout.NORTH);
		components.add(content, BorderLayout.CENTER);
		components.add(button, BorderLayout.SOUTH);
		components.setOpaque(false);

		JPanel buttonBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonBack.add(back);
		buttonBack.setOpaque(false);

		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);

		JPanel logo = new JPanel(new BorderLayout());
		logo.setOpaque(false);
		logo.add(imgLab, BorderLayout.CENTER);
		logo.add(buttonBack, BorderLayout.WEST);

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	private boolean canFormBeSaved() {
		return !fieldName.getText().equals("") && !fieldEmail.getText().equals("") && categories.size() != 0;
	}

	static void assignCategoriesToLecturerForm(ArrayList<Category> categoriesList) {
		categories = categoriesList;
		String[] boxString = new String[categoriesList.size()];
		for (int i = 0; i < categoriesList.size(); i++)
			boxString[i] = categoriesList.get(i).toString();

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(boxString);
		categoryBox.setModel(model);
	}

}
