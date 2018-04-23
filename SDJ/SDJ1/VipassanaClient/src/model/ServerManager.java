package model;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface ServerManager extends Remote {
	
	public ArrayList<Member> getAllMembers() throws RemoteException;

}
