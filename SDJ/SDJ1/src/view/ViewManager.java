package view;

import domain.model.Event;

import javax.swing.*;
import java.io.File;

public interface ViewManager {

	void showStartPanel();
	void showEventsPanel(JPanel currentPanel);
	void showNewsletterPanel(JPanel currentPanel);
	void showSignUpPanel(JPanel currentPanel);
	void showListsPanel(JPanel currentPanel);
	void showEventCreateFormLectures(JPanel currentPanel);
	void showEventCreateFormSeminars(JPanel currentPanel);
	void showEventCreateFormWorkshop(JPanel currentPanel);
	void showEventCreateFormTrip(JPanel currentPanel);
	void showLecturerChoiceWindow();
	void showLecturerMultipleChoiceListWindow(JPanel currentPanel);
	void showCategoryMultipleChoiceList(JPanel currentPanel);
	void showNewsletterContentWindow(File newsletter);
	void showLecturerListPanel(JPanel currentPanel);
	void showMemberListPanel(JPanel currentPanel);
	void showEventListPanel(JPanel currentPanel);
	void showParticipantListPanel(JPanel currentPanel);
	void showSignUpFormLecturer(JPanel currentPanel);
	void showSignUpFormMember(JPanel currentPanel);
	void showSignUpFormParticipant(JPanel currentPanel, Event event);
	void showModifyEventPanel(JPanel currentPanel, Event event);
	void showMemberMultipleChoice(JPanel currentPanel);
}
