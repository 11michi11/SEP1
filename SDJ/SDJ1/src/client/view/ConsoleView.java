package client.view;

import client.controller.Controller;
import server.domain.model.Member;

import java.io.Serializable;
import java.rmi.RemoteException;

public class ConsoleView implements ViewManager, Serializable {
	
	private Controller controller;
	
	public ConsoleView() {
		controller = Controller.getInstance();
	}

	@Override
	public void showListOfMembers() {
		try {
			for(Member m : controller.getAllMembers())
			System.out.println(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showListOfMembersWhoHasPaid() {
		try {
			for(String s : controller.getListOfMembersWhoHasntPaid())
			System.out.println(s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void showWelcomeMessage() {
		System.out.println("1) All Members \n2) Members who hasnt paid");
		controller.handleUserInput();
	}

	@Override
	public void showMsg(String msg) {
		System.out.println(msg);
	}
	
	

}
