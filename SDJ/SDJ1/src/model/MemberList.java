package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.metal.MetalMenuBarUI;

public class MemberList implements Serializable {

	private ArrayList<Member> members;
	private Iterator<Member> iter;
	
	public MemberList() {
		this.members = new ArrayList<Member>();
		this.iter = members.iterator();
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
		for(Member e : this.members) {
			if (ID == e.getID()) {
				return e;
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

	public boolean hasNext() {
		return iter.hasNext();
	}

	public Member next() {
		return iter.next();
	}
	
	public ArrayList<Member> getAllMembers() {
		return this.members;

	}
	
	public String toString ()
	{
	   String returnString = "";
	   for (Member e: members)
	   {
	      returnString += e.toString()+"\n";
	   }
	   return returnString;
	}

}
