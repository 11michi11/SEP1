package view;

import javax.swing.JPanel;

public interface ViewManager {

	public void showStartPanel();
	public void showEventsPanel(JPanel currentPanel);
	public void showNewsletterPanel(JPanel currentPanel);
	public void showSignUpPanel(JPanel currentPanel);
	public void showListsPanel(JPanel currentPanel 	);
}
