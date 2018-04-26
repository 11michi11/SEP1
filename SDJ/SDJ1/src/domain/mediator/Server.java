package domain.mediator;

import domain.model.Member;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observer;

public class Server extends UnicastRemoteObject implements ServerManager {

    private static final long serialVersionUID = 1L;
    private VIAManager manager;

    protected Server() throws RemoteException {
        super();
        manager = new VIAManager();
    }

    public void startServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            RemoteServer rmi = new Server();
            Naming.rebind("getAllMembers", rmi);
            System.out.println("Server is up");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Member> getAllMembers() throws RemoteException {
        return manager.getAllMembers();
    }

    @Override
    public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException {
        return manager.getMemberList().getListOfEmailsWhoHasntPaid();
    }

    @Override
    public void registerObserver(Observer observer) {
        manager.registerObserver(observer);
    }


}