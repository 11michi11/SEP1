package controller;

import model.ClientManager;
import model.ClientModelManager;
import view.ConsoleView;
import view.ViewManager;

public class ClientMain {

	public static void main(String[] args) {
		ViewManager view = new ConsoleView();
		ClientManager manager = new ClientModelManager();
		Controller.getInstance(manager, view);
	}
}
