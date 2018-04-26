package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientModelManager implements ClientManager {
	
//	private ArrayList<Member> members;
	private MemberList members;
	
	public ClientModelManager() {
		members = new MemberList();
	}
	
	private void addMembers(Member member) {
		members.addMember(member);
	}

	@Override
	public ArrayList<Member> getAllMembers() throws RemoteException {
		return members.getAllMembers();
	}
	
	@Override
	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException {
		return members.getListOfEmailsWhoHasntPaid();
	}

    public void reloadMembers(ArrayList<Member> allMembers) {
		members.reload(allMembers);
    }
}
