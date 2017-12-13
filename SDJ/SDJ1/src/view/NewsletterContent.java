package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NewsletterContent extends VIAPanel {

    private JScrollPane scrollPane;
    private JLabel newsletterTitle;
    private JTextArea content;
    private JFrame frame;
    private JPanel parentPanel;
    private JButton back;
    private File newsletter;

    public NewsletterContent(JFrame frame, JPanel parentPanel, File newsletter) {
	super();
	this.frame = frame;
	this.newsletter = newsletter;
	this.parentPanel = parentPanel;
	setLayout(new BorderLayout());
	initializeComponents();
	registerEventHandlers();
	addComponentsToPanel();

    }

    public void initializeComponents() {

	content = new JTextArea(100, 50);
	content.setEditable(false);
	scrollPane = new JScrollPane(content);
	newsletterTitle = new VIALabel("NEWSLETTER CONTENT", 30);
	back = new VIAButtonBack(frame, parentPanel);
    }

    public void registerEventHandlers() {
    }

    public void addComponentsToPanel() {

	JScrollPane scrollPane = new JScrollPane(content);
	add(scrollPane);

	content.setText(parseNewsletterFile());

	JPanel title = new JPanel();
	title.add(newsletterTitle);
	title.setOpaque(false);

	add(title, BorderLayout.NORTH);
	add(scrollPane, BorderLayout.CENTER);
    }

    private String parseNewsletterFile() {
	StringBuilder sb = new StringBuilder("");

	try (Scanner in = new Scanner(newsletter)) {
	    while (in.hasNextLine())
		sb.append(in.nextLine() + "\n");

	} catch (FileNotFoundException e) {
	    System.out.println("File not found");
	    e.printStackTrace();
	}

	return sb.toString();
    }

}
