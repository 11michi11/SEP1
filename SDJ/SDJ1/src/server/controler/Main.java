package server.controler;

import server.domain.mediator.ModelManager;
import server.domain.mediator.Server;
import server.domain.mediator.VIAManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;

public class Main {

	public static void main(String[] args){
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			ModelManager manager = new VIAManager();
			VIAController controller = VIAController.getInstance();
			controller.setManager(manager);
			controller.start();
			try {
				LocateRegistry.createRegistry(1099);
				RemoteServer rmi = new Server(manager);
				Naming.rebind("server", rmi);
				System.out.println("Server is up");
			} catch (RemoteException | MalformedURLException e) {
				e.printStackTrace();
			}


	}

}
