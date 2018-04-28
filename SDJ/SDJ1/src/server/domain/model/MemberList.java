package server.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class MemberList extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Member> members;

    public MemberList() {
        this.members = new ArrayList<Member>();
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

    public ArrayList<String> getListOfEmailsWhoHasntPaid() {
        ArrayList<String> emails = new ArrayList<String>();
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
        setChanged();
        notifyObservers();
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
        setChanged();
        notifyObservers();
    }

    public int getSize() {
        return members.size();
    }

    public String toString() {
        String returnString = "";
        for (Member e : members)
            returnString += e.toString() + "\n";

        return returnString;
    }

    public void reload(ArrayList<Member> members){
         this.members = members;
    }
}
