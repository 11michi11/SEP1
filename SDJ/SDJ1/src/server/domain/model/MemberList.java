package server.domain.model;

import client.domain.mediator.ListOfMembers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class MemberList extends RemoteObservable implements Serializable, ListOfMembers {

    private static final long serialVersionUID = 1L;
    public ArrayList<Member> members;

    public MemberList() {
        this.members = new ArrayList<>();
    }

    public ArrayList<Member> getListOfMembersWhoHasntPaid() {
        ArrayList<Member> memberList = new ArrayList<>();
        for (Member e : members) {
            if (!e.hasPaid()) {
                memberList.add(e);
            }
        }
        return memberList;
    }

    public ArrayList<String> getListOfEmails() {
        ArrayList<String> emails = new ArrayList<>();
        for (Member e : members) {
            emails.add(e.getEmail());
        }
        return emails;
    }

    public ArrayList<String> getListOfEmailsWhoHasntPaid() {
        ArrayList<String> emails = new ArrayList<>();
        for (Member e : getListOfMembersWhoHasntPaid()) {
            emails.add(e.getEmail());
        }
        return emails;
    }

    public ArrayList<Member> getAllMembers() {
        return this.members;
    }

    public void deleteMember(Member member) {
        members.remove(member);
        notifyObservers(member);
    }

    public Member getMemberByID(int ID) throws MemberNotFoundException {
        for (Member e : this.members) {
            if (ID == e.getID()) {
                return e;
            }
        }
        throw new MemberNotFoundException("No such member");
    }

    public void addMember(Member member) {
        members.add(member);
        notifyObservers(member);
    }

    public int getSize() {
        return members.size();
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Member e : members)
            returnString.append(e.toString()).append("\n");

        return returnString.toString();
    }

    public void memberAdded(Member member){
         members.add(member);
    }

    public void load(ArrayList<Member> memebers){
    	this.members = memebers;
    }
}
