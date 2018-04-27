package view;

import controler.VIAController;
import domain.model.Event;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

public class VIAWindow implements ViewManager {

	private JFrame frame;

	public VIAWindow() {
		this.frame = new JFrame("VIA - Managment system");
		this.frame.setSize(900, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				try {
					VIAController.performOpeningOperations();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					VIAController.performClosingOperations();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}
		});
		showStartPanel();
	}


	@Override
	public void showStartPanel() {
		JPanel newContentPane = new StartPanel();
		frame.setContentPane(newContentPane);
		frame.revalidate();
		frame.setVisible(true);
	}


	@Override
	public void showEventsPanel(JPanel currentPanel) {
		JPanel newContentPane = new EventPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showNewsletterPanel(JPanel currentPanel) {
		JPanel newContentPane = new Newsletter(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showSignUpPanel(JPanel currentPanel) {
		JPanel newContentPane = new SignUpPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showListsPanel(JPanel currentPanel) {
		JPanel newContentPane = new ListsPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showEventCreateFormLectures(JPanel currentPanel) {
		JPanel newContentPane = new EventCreateFormLectures(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showEventCreateFormSeminars(JPanel currentPanel) {
		JPanel newContentPane = new EventCreateFormSeminars(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showEventCreateFormWorkshop(JPanel currentPanel) {
		JPanel newContentPane = new EventCreateFormWorkshop(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showEventCreateFormTrip(JPanel currentPanel) {
		JPanel newContentPane = new EventCreateFormTrip(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showLecturerChoiceWindow() {
		JFrame lecturerChoice = new JFrame();
		lecturerChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lecturerChoice.setSize(900, 500);
		lecturerChoice.setLocationRelativeTo(null);
		lecturerChoice.setResizable(false);
		lecturerChoice.setTitle("VIA - Choice of lecturer for event");
		lecturerChoice.setContentPane(new LecturerChoiceList(lecturerChoice));
		lecturerChoice.setVisible(true);
	}


	@Override
	public void showLecturerMultipleChoiceListWindow(JPanel currentPanel) {
		JFrame lecturerChoice = new JFrame();
		lecturerChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lecturerChoice.setSize(900, 500);
		lecturerChoice.setLocationRelativeTo(null);
		lecturerChoice.setResizable(false);
		lecturerChoice.setTitle("VIA - Choice of lecturer for event");
		lecturerChoice.setContentPane(new LecturerMultipleChoiceList(lecturerChoice, currentPanel));
		lecturerChoice.setVisible(true);
	}


	@Override
	public void showCategoryMultipleChoiceList(JPanel currentPanel) {
		JFrame categoryChoice = new JFrame();
		categoryChoice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		categoryChoice.setSize(900, 500);
		categoryChoice.setLocationRelativeTo(null);
		categoryChoice.setResizable(false);
		categoryChoice.setTitle("VIA - Choice of category for event");
		categoryChoice.setContentPane(new CategoryMultipleChoiceList(categoryChoice, currentPanel));
		categoryChoice.setVisible(true);
	}


	@Override
	public void showNewsletterContentWindow(File newsletter) {
		JFrame newsletterFrame = new JFrame();
		newsletterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newsletterFrame.setSize(900, 500);
		newsletterFrame.setLocationRelativeTo(null);
		newsletterFrame.setResizable(false);
		newsletterFrame.setTitle("VIA - Add new member");
		newsletterFrame.setContentPane(new NewsletterContent(newsletter));
		newsletterFrame.setVisible(true);
	}


	@Override
	public void showLecturerListPanel(JPanel currentPanel) {
		JPanel newContentPane = new LecturerListPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showMemberListPanel(JPanel currentPanel) {
		JPanel newContentPane = new MemberListPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showEventListPanel(JPanel currentPanel) {
		JPanel newContentPane = new EventListPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showParticipantListPanel(JPanel currentPanel) {
		JPanel newContentPane = new ParticipantListPanel(frame, currentPanel);
		frame.setContentPane(newContentPane);
		frame.revalidate();
	}


	@Override
	public void showSignUpFormLecturer(JPanel currentPanel) {
		JFrame lecturer = new JFrame();
		lecturer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lecturer.setSize(900, 500);
		lecturer.setLocationRelativeTo(null);
		lecturer.setResizable(false);
		lecturer.setTitle("VIA - Add new member");
		lecturer.setContentPane(new SignUpFormLecturer(lecturer, currentPanel));
		lecturer.setVisible(true);
	}


	@Override
	public void showSignUpFormMember(JPanel currentPanel) {
		JFrame member = new JFrame();
		member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		member.setSize(900, 500);
		member.setLocationRelativeTo(null);
		member.setResizable(false);
		member.setTitle("VIA - Add new member");
		member.setContentPane(new SignUpFormMember(member, currentPanel));
		member.setVisible(true);
	}


	@Override
	public void showSignUpFormParticipant(JPanel currentPanel, Event event) {
		JFrame participant = new JFrame();
		participant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		participant.setSize(900, 500);
		participant.setLocationRelativeTo(null);
		participant.setResizable(false);
		participant.setTitle("VIA - Sign up new participant");
		participant.setContentPane(new SignUpFormParticipant(participant, currentPanel, event.getID()));
		participant.setVisible(true);
	}


	@Override
	public void showModifyEventPanel(JPanel currentPanel, Event event) {
		JFrame modifyEvent = new JFrame();
		modifyEvent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modifyEvent.setSize(900, 500);
		modifyEvent.setLocationRelativeTo(null);
		modifyEvent.setResizable(false);
		modifyEvent.setTitle("VIA - Modify event");
		switch (event.getClass().getName()) {
			case "model.Lecture":
				modifyEvent.setContentPane(new EventCreateFormLectures(modifyEvent, currentPanel, event));
				break;
			case "model.Seminar":
				modifyEvent.setContentPane(new EventCreateFormSeminars(modifyEvent, currentPanel, event));
				break;
			case "model.Workshop":
				modifyEvent.setContentPane(new EventCreateFormWorkshop(modifyEvent, currentPanel, event));
				break;
			case "model.Trip":
				modifyEvent.setContentPane(new EventCreateFormTrip(modifyEvent, currentPanel, event));
				break;
		}
		modifyEvent.setVisible(true);
	}

	@Override
	public void showMemberMultipleChoice(JPanel currentPanel) {
		JFrame member = new JFrame();
		member.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		member.setSize(900, 500);
		member.setLocationRelativeTo(null);
		member.setResizable(false);
		member.setTitle("VIA - Add new member");
		member.setContentPane(new MemberMultipleChoice(member, currentPanel));
		member.setVisible(true);
	}

}
