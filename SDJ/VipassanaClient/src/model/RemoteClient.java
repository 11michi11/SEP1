package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteClient extends Remote {
    public ArrayList<Member> getAllMembers() throws RemoteException;

    public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;
}
