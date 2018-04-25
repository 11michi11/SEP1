package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientModelManager implements ClientManager{
	
	private ArrayList<Member> members;
	private MemberList member;
	
	public ClientModelManager() {
		members = new ArrayList<Member>();
		
	}
	
	private void addMembers(Member member) {
		members.add(member);		
	}

	@Override
	public ArrayList<Member> getAllMembers() throws RemoteException {
		return member.getAllMembers();
	}
	
	@Override
	public ArrayList<String> getListOfMembersWhoHasntPaid() throws RemoteException {
		ArrayList<String> memberList = new ArrayList<String>();
		for (Member e : members) {
			if (!e.hasPaid()) {
				memberList.add(e.toString());
			}
		}
		return memberList;
	}

}
