package domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.model.Member;

public interface ServerManager extends Remote {
	
	public ArrayList<Member> getAllMembers() throws RemoteException;
	
	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

}
