package view;

import javax.swing.JPanel;

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
}
