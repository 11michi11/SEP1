package controller;

import model.ServerManager;
import model.ClientModelManager;
import view.ConsoleView;
import view.ViewManager;

public class ClientMain {

	public static void main(String[] args) {
		ViewManager view = new ConsoleView();
		ServerManager manager = new ClientModelManager();
		Controller.getInstance(manager, view);
	}
}
