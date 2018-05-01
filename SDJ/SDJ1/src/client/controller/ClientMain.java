package client.controller;

import client.domain.mediator.ClientManager;
import client.domain.mediator.ClientModelManager;
import client.view.MemberView;
import client.view.ViewManager;

public class ClientMain {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(new SecurityManager());
		}
		Controller controller = Controller.getInstance();
		ClientManager manager = new ClientModelManager(controller);
		controller.setManager(manager);
		ViewManager view = new MemberView();
		controller.setView(view);
		controller.start();
	}
}
