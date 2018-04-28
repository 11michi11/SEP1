package server.domain.mediator;

import server.domain.model.Member;
import client.domain.mediator.RemoteClient;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerManager extends Remote {

    ArrayList<Member> getAllMembers() throws RemoteException;

    ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

    void registerObserver(RemoteClient observer)  throws RemoteException;

}
