package client.domain.mediator;

import server.domain.mediator.ServerManager;
import server.domain.model.Member;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

public class Client implements Serializable, RemoteClient {

	private static final long serialVersionUID = 1L;
	private ServerManager server;
	private ClientManager model;

	public Client(ClientManager model) {
		this.model = model;
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

	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException {
		return server.getListOfMembersWhoHasntPaid();
	}

	@Override
	public void update(Observable o, Object member) {
		model.updateMembers((Member) member);
	}
}
