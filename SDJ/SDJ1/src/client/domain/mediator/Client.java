package client.domain.mediator;

import server.domain.mediator.ServerManager;
import server.domain.model.Member;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;

public class Client extends UnicastRemoteObject implements Serializable, RemoteClient {

    private static final long serialVersionUID = 1L;
    private ServerManager server;
    private ClientManager model;

    public Client(ClientManager model) throws RemoteException {
        super();
        this.model = model;
        try {
            Naming.rebind("update", this);
            System.out.println("Client is up");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            server = (ServerManager) Naming.lookup("rmi://localhost:1099/server");
            server.registerObserver(this);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> getAllMembers() throws RemoteException {
        return server.getAllMembers();
    }

    public ArrayList<Member> getListOfMembersWhoHasntPaid() throws RemoteException {
        return server.getListOfMembersWhoHasntPaid();
    }

    @Override
    public void update(Object member) throws RemoteException {
        model.updateMembers((Member) member);
    }
}
