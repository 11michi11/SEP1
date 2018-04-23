package model;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ServerManager server;
	
	public Client() {
		try {
		server = (ServerManager) Naming.lookup("rmi://localhost:1099/server");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
