package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.mediator.ClientManager;
import domain.model.Member;
import view.ViewManager;

public class Controller {

	private static Controller controller;

	private ClientManager manager;

	private ViewManager view;

	void setManager(ClientManager manager) {
		this.manager = manager;
	}

	void setView(ViewManager view) {
		this.view = view;
	}

	void start() {
		view.showWelcomeMessage();
	}

	public static Controller getInstance() {
		if (controller == null)
			controller = new Controller();
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
		switch (sc.nextInt()) {
			case 1:
				view.showListOfMembers();
				break;
			case 2:
				view.showListOfMembersWhoHasPaid();
				break;
			case 0:
				return;
			default:
				view.showMsg("Wrong Input");
		}
		view.showWelcomeMessage();
	}

    public void showMsg(String msg) {
		view.showMsg(msg);
    }
}
