package client.domain.mediator;

import java.rmi.RemoteException;

public interface RemoteObserver {

    void update(Object arg) throws RemoteException;
}
