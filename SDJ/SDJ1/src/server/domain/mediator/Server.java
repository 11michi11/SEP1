package server.domain.mediator;

import server.domain.model.Member;
import client.domain.mediator.RemoteClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerManager {

	private static final long serialVersionUID = 1L;
	private ModelManager manager;

	public Server(ModelManager manager) throws RemoteException {
		super();
		this.manager = manager;
	}

	@Override
	public ArrayList<Member> getAllMembers() throws RemoteException {
		return manager.getAllMembers();
	}

	@Override
	public ArrayList<Member> getListOfMembersWhoHasntPaid() throws RemoteException {
		return manager.getMemberList().getListOfMembersWhoHasntPaid();
	}

	@Override
	public void registerObserver(RemoteClient observer) throws RemoteException {
		try {
			RemoteClient client = (RemoteClient) Naming.lookup("update");
			manager.registerObserver(client);
		} catch (NotBoundException | MalformedURLException e) {
			e.printStackTrace();
		}
	}


}