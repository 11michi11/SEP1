package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteClient {
    public ArrayList<Member> getAllMembers() throws RemoteException;

    public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException;
}
