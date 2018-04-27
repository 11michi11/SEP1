package domain.mediator;

import domain.model.Member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;

public interface ServerManager extends Remote {

	ArrayList<Member> getAllMembers() throws RemoteException;

	ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

	void registerObserver(RemoteClient observer)  throws RemoteException;

}
