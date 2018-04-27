package domain.mediator;


import domain.model.Member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ClientManager extends Remote {

    public ArrayList<Member> getAllMembers() throws RemoteException;

    public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;

    public void reloadMembers(ArrayList<Member> allMembers);
}
