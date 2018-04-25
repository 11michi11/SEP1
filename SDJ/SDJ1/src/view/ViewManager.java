package view;

import java.io.File;

import javax.swing.JPanel;

import domain.model.Event;
import domain.model.Lecturer;

public interface ViewManager {

	public void showStartPanel();
	public void showEventsPanel(JPanel currentPanel);
	public void showNewsletterPanel(JPanel currentPanel);
	public void showSignUpPanel(JPanel currentPanel);
	public void showListsPanel(JPanel currentPanel);
	public void showEventCreateFormLectures(JPanel currentPanel);
	public void showEventCreateFormSeminars(JPanel currentPanel);
	public void showEventCreateFormWorkshop(JPanel currentPanel);
	public void showEventCreateFormTrip(JPanel currentPanel);
	public void showLecturerChoiceWindow();
	public void showLecturerMultipleChoiceListWindow(JPanel currentPanel);
	public void showCategoryMultipleChoiceList(JPanel currentPanel);
	public void showNewsletterContentWindow(File newsletter);
	public void showLecturerListPanel(JPanel currentPanel);
	public void showMemberListPanel(JPanel currentPanel);
	public void showEventListPanel(JPanel currentPanel);
	public void showParticipantListPanel(JPanel currentPanel);
	public void showSignUpFormLecturer(JPanel currentPanel);
	public void showSignUpFormMember(JPanel currentPanel);
	public void showSignUpFormParticipant(JPanel currentPanel, Event event);
	public void showModifyEventPanel(JPanel currentPanel, Event event);
	public void showModifyEventPanel(JPanel currentPanel, Event event);
}
