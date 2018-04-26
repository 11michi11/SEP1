package model;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client implements Serializable, ClientManager{
	
	private static final long serialVersionUID = 1L;
	private ClientManager server;
	private ClientModelManager client;
	
	public Client() {
		try {
		server = (ClientManager) Naming.lookup("rmi://localhost:1099/getAllMembers");
			
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
	

}
