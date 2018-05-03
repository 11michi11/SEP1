package client.domain.mediator;

import server.domain.model.Member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ClientManager extends ListOfMembers{
    void updateMembers(Member member);
}
