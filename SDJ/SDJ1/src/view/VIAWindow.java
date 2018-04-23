package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controler.VIAController;

public class VIAWindow implements ViewManager {

	private JFrame frame;

	public VIAWindow() {
		this.frame = new JFrame("VIA - Managment system");
		this.frame.setSize(900, 500);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new StartPanel(frame);
		this.frame.setContentPane(contentPane);

		this.frame.setVisible(true);

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
	}

	
	@Override
	public void showStartPanel() {
		JPanel newContentPane = new StartPanel(frame);
		frame.setContentPane(newContentPane);
		frame.revalidate();
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

}
