package domain.mediator;
import domain.model.Member;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Client implements Serializable, RemoteClient{
	
	private static final long serialVersionUID = 1L;
	private ServerManager server;
	private ClientModelManager model;

	public Client() {
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
	
	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException{
		return server.getListOfMembersWhoHasntPaid();
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			model.reloadMembers(server.getAllMembers());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
