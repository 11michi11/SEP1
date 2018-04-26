package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Client;
import model.ClientManager;
import model.ClientModelManager;
import model.Member;
import view.ConsoleView;
import view.ViewManager;

public class Controller {
	
	private static Controller controller;
	private ClientManager manager;
	private ViewManager view;
	
	private Controller(ClientManager manager, ViewManager view) {
		this.manager = manager;
		this.view = view;
		view.showWelcomeMessage();
	}
	
	public static Controller getInstance(ClientManager manager, ViewManager view){
	    if(controller == null)
		controller = new Controller(manager, view);
	    return controller;
	}
	public static Controller getInstance(){
		if(controller == null)
			throw new IllegalStateException("Controller not yet created");
		return controller;
	}
	
	public ArrayList<Member> getAllMembers() throws RemoteException {
		return manager.getAllMembers();
	}
	
	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException {
		return manager.getListOfMembersWhoHasntPaid();
	}

	public void handleUserInput() {
		Scanner sc = new Scanner(System.in);
		int response = sc.nextInt();
		if(response == 1) {
			view.showListOfMembers();
		} else if(response == 2) {
			view.showListOfMembersWhoHasPaid();
		} else {
			view.showMsg("Wrong Input");
		}

	}
}
