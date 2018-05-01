package client.domain.mediator;

import client.controller.Controller;
import server.domain.model.Member;
import server.domain.model.MemberList;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientModelManager implements ClientManager, Serializable {

	private MemberList members;
	private RemoteClient clientCommunication;
	private Controller controller;

	public ClientModelManager(Controller controller) {
		members = new MemberList();
		this.controller = controller;
		clientCommunication = new Client(this);
		try {
			members.load(clientCommunication.getAllMembers());
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

	public void updateMembers(Member member) {
		members.memberAdded(member);
		controller.showMsg("List has been updated!");
	}
}
