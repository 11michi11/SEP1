package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;


//CLASS TO REDESIGN
public class SignUpForm extends VIAPanel {

	private JLabel labelName;
	private JLabel labelEmail;
	private JLabel labelAddress;
	private JLabel labelID;
	private JLabel labelPhone;
	private JLabel labelDateOfMembership;
	private JLabel labelPaymentYear;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldAddress;
	private JTextField textFieldID;
	private JTextField textFieldPhone;
	private JTextField textFieldDateOfMembership;
	private JTextField textFieldPaymentYear;
	private JButton addToList;
	private JPanel name1;
	private JPanel name2;
	private JPanel email1;
	private JPanel email2;
	private JPanel address1;
	private JPanel address2;
	private JPanel id1;
	private JPanel id2;
	private JPanel phone1;
	private JPanel phone2;
	private JPanel dateOfMembership1;
	private JPanel dateOfMembership2;
	private JPanel paymentYear1;
	private JPanel paymentYear2;
	private JFrame frame;
	
	public SignUpForm(JFrame frame) {
		super();
		this.frame = frame;
		initializeComponents();
		registerEventHandlers();
		addComponentsToFrame();
	}

	public void initializeComponents() {
		new JLabel("Sign-Up Form");
		labelName = new JLabel("Name:");
		labelEmail = new JLabel("E-mail:");
		labelAddress = new JLabel("Address:");
		labelID = new JLabel("ID:");
		labelPhone = new JLabel("Phone:");
		labelDateOfMembership = new JLabel("Date of membership:");
		labelPaymentYear = new JLabel("Payment year:");
		textFieldName = new JTextField(8);
		textFieldEmail = new JTextField(8);
		textFieldAddress = new JTextField(8);
		textFieldID = new JTextField(8);
		textFieldPhone = new JTextField(8);
		textFieldDateOfMembership = new JTextField(8);
		textFieldPaymentYear = new JTextField(8);
		addToList = new JButton("Add to list");

		Dimension prefSize = new Dimension(100, 50);
		addToList.setPreferredSize(prefSize);

	}

	public void registerEventHandlers() {

	}

	public void addComponentsToFrame() {
		JPanel left = new JPanel(new GridLayout(4, 1));

		name1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		name1.add(labelName);

		email1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		email1.add(labelEmail);

		address1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		address1.add(labelAddress);

		id1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		id1.add(labelID);

		left.add(name1);
		left.add(email1);
		left.add(address1);
		left.add(id1);

		JPanel left2 = new JPanel(new GridLayout(4, 1));

		name2 = new JPanel();
		name2.add(textFieldName);

		email2 = new JPanel();
		email2.add(textFieldEmail);

		address2 = new JPanel();
		address2.add(textFieldAddress);

		id2 = new JPanel();
		id2.add(textFieldID);

		left2.add(name2);
		left2.add(email2);
		left2.add(address2);
		left2.add(id2);

		JPanel right = new JPanel(new GridLayout(3, 2));

		phone1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phone1.add(labelPhone);

		phone2 = new JPanel();
		phone2.add(textFieldPhone);

		dateOfMembership1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dateOfMembership1.add(labelDateOfMembership);

		dateOfMembership2 = new JPanel();
		dateOfMembership2.add(textFieldDateOfMembership);

		paymentYear1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		paymentYear1.add(labelPaymentYear);

		paymentYear2 = new JPanel();
		paymentYear2.add(textFieldPaymentYear);

		right.add(phone1);
		right.add(phone2);
		right.add(dateOfMembership1);
		right.add(dateOfMembership2);
		right.add(paymentYear1);
		right.add(paymentYear2);

		JPanel button = new JPanel();
		button.add(addToList);

		add(left);
		add(left2);
		add(right);
		add(button);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(900, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SignUpForm(frame));
				frame.setVisible(true);
			}
		});

	}

}