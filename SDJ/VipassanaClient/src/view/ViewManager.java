package view;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Member;

public interface ViewManager {
	
	public void showListOfMembers();
	public void showListOfMembersWhoHasPaid();
	public void showWelcomeMessage();
	public void showMsg(String string);
}
