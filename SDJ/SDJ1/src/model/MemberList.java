package model;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalMenuBarUI;

public class MemberList {

	private ArrayList<Member> members;

	public MemberList() {
		members = new ArrayList<Member>();
	}

	public ArrayList<Member> getListOfMembersWhoHasntPaid() {
		ArrayList<Member> memberList = new ArrayList<Member>();
		for (Member e : members) {
			if (!e.hasPaid()) {
				memberList.add(e);
			}
		}
		return memberList;
	}

	public ArrayList<String> getListOfEmails() {

		ArrayList<String> emails = new ArrayList<String>();
		for (Member e : members) {
			emails.add(e.getEmail());
		}
		return emails;
	}

	public Member getMemberByID(int ID) throws MemberNotFoundException {
		Member member;
		while (this.hasNext()) {
			member = this.getNext();
			if (ID == member.getID()) {
				return member;
			}
		}
		throw new MemberNotFoundException("No such member");
	}

	public void addMember(Member member) {
		members.add(member);
	}
	
	public int getSize() {
	    return members.size();
	}

	public boolean hasNext() { // isnt done yet
		return false;
	}

	public Member getNext() { // isnt done yet
		return null;
	}

}
