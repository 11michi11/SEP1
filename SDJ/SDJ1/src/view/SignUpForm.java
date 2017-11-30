package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.GridLayout;

public class SignUpForm extends JFrame
{

   private JLabel labelSignUpForm;
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
   private JButton AddToList;
   private JPanel name;
   private JPanel email;
   private JPanel address;
   private JPanel id;
   private JPanel phone;
   private JPanel dateOfMembership;
   private JPanel paymentYear;

   public SignUpForm()
   {

      super("SignUpForm");
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();

   }

   public void createComponents()
   {

      labelSignUpForm = new JLabel("Sign-Up Form");
      labelName = new JLabel("Name:");
      labelEmail = new JLabel("E-mail:");
      labelAddress = new JLabel("Address:");
      labelID = new JLabel("ID:");
      labelPhone = new JLabel("Phone:");
      labelDateOfMembership = new JLabel("Date of membership:");
      labelPaymentYear = new JLabel("Payment year:");
      textFieldName = new JTextField(5);
      textFieldEmail = new JTextField(5);
      textFieldAddress = new JTextField(5);
      textFieldID = new JTextField(5);
      textFieldPhone = new JTextField(8);
      textFieldDateOfMembership = new JTextField(8);
      textFieldPaymentYear = new JTextField(8);
      AddToList = new JButton("Add to list");

   }

   public void initializeComponents()
   {

      setSize(500, 500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void registerEventHandlers()
   {

   }

   public void addComponentsToFrame()
   {
      JPanel pane = new JPanel();

      JPanel left = new JPanel(new GridLayout(4, 1));

      name = new JPanel();
      name.add(labelName);
      name.add(textFieldName);

      email = new JPanel();
      email.add(labelEmail);
      email.add(textFieldEmail);

      address = new JPanel();
      address.add(labelAddress);
      address.add(textFieldAddress);

      id = new JPanel();
      id.add(labelID);
      id.add(textFieldID);

      left.add(name);
      left.add(email);
      left.add(address);
      left.add(id);

      JPanel right = new JPanel(new GridLayout(3, 1));

      phone = new JPanel();
      phone.add(labelPhone);
      phone.add(textFieldPhone);

      dateOfMembership = new JPanel();
      dateOfMembership.add(labelDateOfMembership);
      dateOfMembership.add(textFieldDateOfMembership);

      paymentYear = new JPanel();
      paymentYear.add(labelPaymentYear);
      paymentYear.add(textFieldPaymentYear);

      right.add(phone);
      right.add(dateOfMembership);
      right.add(paymentYear);

      pane.add(left);
      pane.add(right);

      setContentPane(pane);

   }

   public static void main(String[] args)
   {

      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            SignUpForm main = new SignUpForm();
            main.setVisible(true);
         }
      });

   }

}