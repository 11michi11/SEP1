package client.domain.mediator;

import server.domain.model.Member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;

public interface RemoteClient extends Observer, Remote {
    ArrayList<Member> getAllMembers() throws RemoteException;

    ArrayList<Member> getListOfMembersWhoHasntPaid() throws RemoteException;
}
