package client.domain.mediator;

import server.domain.model.Member;

import java.util.ArrayList;

public interface ListOfMembers {
    ArrayList<Member> getAllMembers();
    void addMember(Member member);
    ArrayList<Member> getListOfMembersWhoHasntPaid();
}
