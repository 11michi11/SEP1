package domain.mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;

import domain.model.Category;
import domain.model.Event;
import domain.model.EventList;
import domain.model.EventNotFoundException;
import domain.model.Lecturer;
import domain.model.LecturerList;
import domain.model.Member;
import domain.model.MemberList;
import domain.model.MyDate;
import domain.model.Participant;

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
			Naming.rebind("server", rmi);
			System.out.println("Server is up");
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Member> getAllMembers() throws RemoteException {
		return manager.getAllMembers();
	}

	}