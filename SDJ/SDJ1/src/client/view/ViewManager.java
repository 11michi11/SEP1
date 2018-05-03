package client.view;

import java.io.Serializable;

public interface ViewManager extends Serializable {
	
	void showListOfMembers();
	void showListOfMembersWhoHasPaid();
	void startView();
	void showMsg(String string);
}
