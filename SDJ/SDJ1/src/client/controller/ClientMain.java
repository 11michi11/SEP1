package client.controller;

import client.domain.mediator.ClientManager;
import client.domain.mediator.ClientModelManager;
import client.view.ConsoleView;
import client.view.ViewManager;

public class ClientMain {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(new SecurityManager());
		}
		Controller controller = Controller.getInstance();
		ViewManager view = new ConsoleView();
		controller.setView(view);
		ClientManager manager = new ClientModelManager(controller);
		controller.setManager(manager);
		controller.start();
	}
}
