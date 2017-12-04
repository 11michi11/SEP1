package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpFormMember extends JFrame{
	
	private JLabel signUp;
	private JLabel name;
	private JLabel email;
	private JLabel address;
	private JLabel id;
	private JLabel phone;
	private JLabel dateOfMembership;
	private JLabel payment;
	private JTextField fieldName;
	private JTextField fieldEmail;
	private JTextField fieldAddress;
	private JTextField fieldID;
	private JTextField fieldPhone;
	private JTextField fieldDateOfMembership;
	private JTextField fieldPaymentYear;
	private JButton add;
	
	public SignUpFormMember() {

		super("Sign-Up Form for Member");
		createComponents();
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();

	}

	public void createComponents() {
	
	signUp = new JLabel("Sign-Up Form for MEMBER");
	signUp.setFont(new Font("Arial", Font.PLAIN, 30));
	name = new JLabel("Name:");
	email = new JLabel("E-mail:");
	address = new JLabel("Address:");
	id = new JLabel("ID:");
	phone = new JLabel("Phone:");
	dateOfMembership = new JLabel("Date of Membership:");
	payment = new JLabel("Payment:");
	fieldName = new JTextField(8);
	fieldEmail = new JTextField(8);
	fieldAddress = new JTextField(8);
	fieldID = new JTextField(8);
	fieldPhone = new JTextField(8);
	fieldDateOfMembership = new JTextField(8);
	fieldPaymentYear = new JTextField(8);
	add = new JButton("Add to list");
	add.setFont(new Font("Arial", Font.PLAIN, 15));
	
	Dimension prefSize = new Dimension(100, 30);
	add.setPreferredSize(prefSize);
	
	
	
	}
	
	public void initializeComponents() {
		
		setSize(700, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerEventHandlers() {

	}

	public void addComponentsToFrame() {
		
		VIAPanel components = new VIAPanel(new BorderLayout());
		
		JPanel leftLabel = new JPanel(new GridLayout(4,1));
		leftLabel.add(name);
		leftLabel.add(email);
		leftLabel.add(address);
		leftLabel.add(id);
		leftLabel.setOpaque(false);
		
		
		JPanel leftField = new JPanel(new GridLayout(4,1));
		
		JPanel fieldOne = new JPanel();
		fieldOne.add(fieldName);
		fieldOne.setOpaque(false);
		
		
		JPanel fieldTwo = new JPanel();
		fieldTwo.add(fieldEmail);
		fieldTwo.setOpaque(false);
		
		JPanel fieldThree = new JPanel();
		fieldThree.add(fieldAddress);
		fieldThree.setOpaque(false);
		
		JPanel fieldFour = new JPanel();
		fieldFour.add(fieldID);
		fieldFour.setOpaque(false);
		
		leftField.add(fieldOne);
		leftField.add(fieldTwo);
		leftField.add(fieldThree);
		leftField.add(fieldFour);
		leftField.setOpaque(false);
		
		JPanel left = new JPanel(new GridLayout(1,2));
		left.add(leftLabel);
		left.add(leftField);
		left.setOpaque(false);
		
		JPanel leftSide = new JPanel();
		leftSide.add(left);
		leftSide.setOpaque(false);
		
		JPanel rightLabel = new JPanel(new GridLayout(3,1));
		rightLabel.add(phone);
		rightLabel.add(dateOfMembership);
		rightLabel.add(payment);
		rightLabel.setOpaque(false);
		
		
		JPanel rightField = new JPanel(new GridLayout(3,1));
		
		JPanel fieldFive = new JPanel();
		fieldFive.add(fieldPhone);
		fieldFive.setOpaque(false);
		
		JPanel fieldSix = new JPanel();
		fieldSix.add(fieldDateOfMembership);
		fieldSix.setOpaque(false);
		
		JPanel fieldSeven = new JPanel();
		fieldSeven.add(fieldPaymentYear);
		fieldSeven.setOpaque(false);
		
		rightField.add(fieldFive);
		rightField.add(fieldSix);
		rightField.add(fieldSeven);
		rightField.setOpaque(false);
		
		JPanel right = new JPanel(new GridLayout(1,2));
		right.add(rightLabel);
		right.add(rightField);
		right.setOpaque(false);
		
		JPanel rightSide = new JPanel();
		rightSide.add(right);
		rightSide.setOpaque(false);
		
		JPanel content = new JPanel(new GridLayout(1,2));
		content.add(leftSide);
		content.add(rightSide);
		content.setOpaque(false);
		
		JPanel button = new JPanel();
		button.add(add);
		button.setOpaque(false);
		
		JPanel titul = new JPanel();
		titul.add(signUp);
		titul.setOpaque(false);
		
		JPanel componentsF = new JPanel(new BorderLayout());
		componentsF.add(titul, BorderLayout.NORTH);
		componentsF.add(content, BorderLayout.CENTER);
		componentsF.add(button, BorderLayout.SOUTH);
		componentsF.setOpaque(false);
		
		JPanel logo = new JPanel();
		logo.setOpaque(false);
		
		ImageIcon img = new ImageIcon("src/resources/Logo.png");
		JLabel imgLab = new JLabel(img);
		
		components.add(imgLab, BorderLayout.NORTH);
		components.add(componentsF, BorderLayout.CENTER);
		
		
		setContentPane(components);
		
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SignUpFormMember main = new SignUpFormMember();
				main.setVisible(true);
			}
		});

	}

}
