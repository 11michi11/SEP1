package model;

import java.util.ArrayList;


public interface ClientManager{

    public ArrayList<Member> getAllMembers();

    public ArrayList<String> getListOfMembersWhoHasntPaid();

    public void reloadMembers(ArrayList<Member> allMembers);
}
