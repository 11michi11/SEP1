package client.domain.mediator;

import server.domain.model.Member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ClientManager extends Remote {

    ArrayList<Member> getAllMembers() throws RemoteException;

    ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

    void updateMembers(Member member);
}
