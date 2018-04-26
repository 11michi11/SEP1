package model;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;


public interface ServerManager extends Remote {
	
	public ArrayList<Member> getAllMembers() throws RemoteException;

	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

	public void registerObserver(Observer observer);
}
