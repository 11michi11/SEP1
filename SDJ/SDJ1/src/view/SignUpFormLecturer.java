package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controler.VIAController;
import domain.model.Category;

public class SignUpFormLecturer extends VIAPanel {

	private static JComboBox categoryBox;
	private static ArrayList<Category> categories = new ArrayList<Category>();
	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JLabel phone;
	private JButton category;
	private JButton add;
	private VIAButtonBack back;
	private JCheckBox advertisment;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JTextField fieldPhone;
	private JFrame frame;
	private JPanel parentPanel;

	public SignUpFormLecturer(JFrame frame, JPanel parentPanel) {
		super();
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
		advertisment = new JCheckBox("Advertisment");
		advertisment.setOpaque(false);
		fieldName = new JTextField(8);
		fieldEmail = new JTextField(8);
		fieldPhone = new JTextField(8);
		categoryBox = new JComboBox(boxString);

		add = new VIAButtonSmall("Add to list");
		back = new VIAButtonBack(frame, parentPanel);
	}

	public void registerEventHandlers() {
		JPanel currentPanel = this;
		back.changeListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parentPanel instanceof LecturerListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (canFormBeSaved()) {
					Object[] configuration = new Object[5];
					configuration[0] = fieldName.getText();
					configuration[1] = fieldEmail.getText();
					configuration[2] = Integer.parseInt(fieldPhone.getText());
					configuration[3] = categories;
					configuration[4] = advertisment.isSelected();

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
			}
		});

		category.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame categoryChoice = new JFrame();
				categoryChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				categoryChoice.setSize(900, 500);
				categoryChoice.setLocationRelativeTo(null);
				categoryChoice.setResizable(false);
				categoryChoice.setTitle("VIA - Choice of category for event");
				categoryChoice.setContentPane(new CategoryMultipleChoiceList(categoryChoice, currentPanel));
				categoryChoice.setVisible(true);
			}
		});
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
		rightLabel.add(advertisment);
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
		;

		add(logo, BorderLayout.NORTH);
		add(components, BorderLayout.CENTER);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpFormLecturer(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

	private boolean canFormBeSaved() {
		if (!fieldName.getText().equals("") && !fieldEmail.getText().equals("") && categories.size() != 0)
			return true;
		return false;
	}

	public static void assignCategoriesToLecturerForm(ArrayList<Category> categoriesList) {
		categories = categoriesList;
		String[] boxString = new String[categoriesList.size()];
		for (int i = 0; i < categoriesList.size(); i++)
			boxString[i] = categoriesList.get(i).toString();

		DefaultComboBoxModel model = new DefaultComboBoxModel(boxString);
		categoryBox.setModel(model);
	}

}
