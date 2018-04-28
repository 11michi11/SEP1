package server.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewsletterContent extends VIAPanel {

	private JLabel newsletterTitle;
	private JTextArea content;
	private File newsletter;

	public NewsletterContent(File newsletter) {
		super();
		this.newsletter = newsletter;
		setLayout(new BorderLayout());
		initializeComponents();
		registerEventHandlers();
		addComponentsToPanel();
	}

	public void initializeComponents() {
		content = new JTextArea(100, 50);
		content.setEditable(false);
		newsletterTitle = new VIALabel("NEWSLETTER CONTENT", 30);
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
		StringBuilder sb = new StringBuilder();

		try (Scanner in = new Scanner(newsletter)) {
			while (in.hasNextLine())
				sb.append(in.nextLine()).append("\n");

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		return sb.toString();
	}

}
