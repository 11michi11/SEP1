package domain.mediator;

import com.sun.jmx.remote.util.EnvHelp;
import controller.Controller;
import domain.model.Member;
import domain.model.MemberList;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientModelManager implements ClientManager, Serializable {

	private MemberList members;
	private Client clientCommunication;
	private Controller controller;

	public ClientModelManager(Controller controller) {
		members = new MemberList();
		this.controller = controller;
		clientCommunication = new Client(this);
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
		controller.showMsg("List has been updated!");
	}
}
