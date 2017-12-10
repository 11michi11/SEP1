package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.VIAController;
import model.InvalidDateInput;
import model.MyDate;

public class SignUpFormMember extends VIAPanel {

	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JLabel address;
	private JLabel phone;
	private JLabel dateOfMembership;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JTextField fieldAddress;
	private JTextField fieldPhone;
	private JTextField fieldDateOfMembership;
	private JButton add;
	private JFrame frame;
	private JPanel parentPanel;
	private VIAButtonBack back;

	public SignUpFormMember(JFrame frame, JPanel parentPanel) {
		super();
		this.frame = frame;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();

	}

	public void initializeComponents() {
		signUp = new VIALabel("Sign-Up Form for MEMBER", 40);
		name = new JLabel("Name:");
		email = new JLabel("E-mail:");
		address = new JLabel("Address:");
		phone = new JLabel("Phone:");
		dateOfMembership = new JLabel("Date of Membership:");
		fieldName = new JTextField(10);
		fieldEmail = new JTextField(10);
		fieldAddress = new JTextField(10);
		fieldPhone = new JTextField(10);
		fieldDateOfMembership = new JTextField(10);
		fieldDateOfMembership.setText("dd/mm/yyyy/hh:mm");

		add = new VIAButtonSmall("Add to list");

		back = new VIAButtonBack(frame, parentPanel);

	}

	public void registerEventHandlers() {

		fieldDateOfMembership.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (fieldDateOfMembership.getText().equals("dd/mm/yyyy/hh:mm"))
					fieldDateOfMembership.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (fieldDateOfMembership.getText().equals(""))
					fieldDateOfMembership.setText("dd/mm/yyyy/hh:mm");
			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (canFormBeSaved()) {
					Object[] configuration = new Object[5];
					configuration[0] = fieldName.getText();
					configuration[1] = fieldAddress.getText();
					configuration[2] = Integer.parseInt(fieldPhone.getText());
					configuration[3] = fieldEmail.getText();
					try {
						configuration[4] = new MyDate(fieldDateOfMembership.getText());
						VIAController.addMemberToList(configuration);
					} catch (InvalidDateInput e1) {
						JOptionPane.showMessageDialog(frame, "Invalid date format", "Date error",
								JOptionPane.PLAIN_MESSAGE);
					}


					if (parentPanel instanceof EventListPanel || parentPanel instanceof MemberListPanel) {
						frame.dispose();
					} else {
						back.goBack();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Fill all filed to save members", "Form error",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		back.changeListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (parentPanel instanceof EventListPanel || parentPanel instanceof MemberListPanel) {
					frame.dispose();
				} else {
					back.goBack();
				}
			}
		});
	}

	public void addComponentsToPanel() {
		JPanel leftLabel = new JPanel(new GridLayout(3, 1));
		leftLabel.add(name);
		leftLabel.add(email);
		leftLabel.add(address);

		leftLabel.setOpaque(false);

		JPanel leftField = new JPanel(new GridLayout(3, 1));

		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldName);
		fieldOne.setOpaque(false);

		JPanel fieldTwo = new JPanel();
		fieldTwo.add(fieldEmail);
		fieldTwo.setOpaque(false);

		JPanel fieldThree = new JPanel();
		fieldThree.add(fieldAddress);
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
		rightLabel.add(dateOfMembership);
		rightLabel.setOpaque(false);

		JPanel rightField = new JPanel(new GridLayout(2, 1));

		JPanel fieldFive = new JPanel();
		fieldFive.add(fieldPhone);
		fieldFive.setOpaque(false);

		JPanel fieldSix = new JPanel();
		fieldSix.add(fieldDateOfMembership);
		fieldSix.setOpaque(false);

		rightField.add(fieldFive);
		rightField.add(fieldSix);
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
				frame.setContentPane(new SignUpFormMember(frame, new JPanel()));
				frame.setVisible(true);
			}
		});

	}

	private boolean canFormBeSaved() {
		if (!fieldName.getText().equals("") && !fieldEmail.getText().equals("") && !fieldAddress.getText().equals("")
				&& !fieldDateOfMembership.getText().equals("dd/mm/yyyy/hh:mm"))
			return true;
		return false;

	}

}
