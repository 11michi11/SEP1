package test;

import client.controller.Controller;
import client.domain.mediator.ClientManager;
import client.domain.mediator.ClientModelManager;
import client.view.MemberView;
import client.view.ViewManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.controler.VIAController;
import server.domain.mediator.Server;
import server.domain.mediator.VIAManager;
import server.domain.model.Member;
import server.domain.model.MyDate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObserverTest {

    private static VIAManager serverManager;

    @BeforeAll
    static void setup() {
        new Thread(() -> {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            serverManager = new VIAManager();
            VIAController controller = VIAController.getInstance();
            controller.setManager(serverManager);
            controller.start();
            try {
                LocateRegistry.createRegistry(1099);
                RemoteServer rmi = new Server(serverManager);
                Naming.rebind("server", rmi);
                System.out.println("Server is up");
            } catch (RemoteException | MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void observerTest() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Controller controller = Controller.getInstance();
        ClientManager manager = new ClientModelManager(controller);
        controller.setManager(manager);
        ViewManager view = new MemberView();
        controller.setView(view);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // sleeping gives time for RMI to start up
        controller.start();
        Member member = new Member("name", "address", 123, "email", MyDate.getDefaultDate());
        serverManager.addMember(member);
        assertTrue(manager.getAllMembers().contains(member));
    }


}
