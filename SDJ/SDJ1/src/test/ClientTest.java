package test;

import client.controller.Controller;
import client.domain.mediator.ClientManager;
import client.domain.mediator.ClientModelManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.controler.VIAController;
import server.domain.mediator.Server;
import server.domain.mediator.VIAManager;
import server.domain.model.Member;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

	private static VIAManager manager;

	@BeforeAll
	static void setup() {
		new Thread(() -> {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			manager = new VIAManager();
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
		}).start();
		try {
			Thread.currentThread().sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getAllMembersTest() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		Controller controller = Controller.getInstance();
		ClientManager manager = new ClientModelManager(controller);
		controller.setManager(manager);
		try {
			ArrayList<Member> list = ((ClientModelManager) manager).clientCommunication.getAllMembers();
			System.out.println(list);
			assertFalse(list.isEmpty());
			assertEquals(manager.getAllMembers(), list);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail("Remote exception");
		}
	}

	@Test
	void getAllMembersWhoHasntPaindTest() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		Controller controller = Controller.getInstance();
		ClientManager manager = new ClientModelManager(controller);
		controller.setManager(manager);
		try {
			ArrayList<Member> list = ((ClientModelManager) manager).clientCommunication.getListOfMembersWhoHasntPaid();
			System.out.println(list);
			assertFalse(list.isEmpty());
			assertEquals(manager.getListOfMembersWhoHasntPaid(), list);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail("Remote exception");
		}
	}

}