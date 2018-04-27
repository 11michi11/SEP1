package domain.mediator;

import domain.model.Member;
import domain.model.MemberList;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientModelManager implements ClientManager {

	private MemberList members;
	private Client clientCommunication;

	public ClientModelManager() {
		members = new MemberList();
		clientCommunication = new Client();
		try {
			members.reload(clientCommunication.getAllMembers());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void addMembers(Member member) {
		members.addMember(member);

	}

	@Override
	public ArrayList<Member> getAllMembers() {
		return members.getAllMembers();
	}

	@Override
	public ArrayList<String> getListOfMembersWhoHasntPaid() {
		return members.getListOfEmailsWhoHasntPaid();
	}

	public void reloadMembers(ArrayList<Member> allMembers) {
		members.reload(allMembers);
	}
}
