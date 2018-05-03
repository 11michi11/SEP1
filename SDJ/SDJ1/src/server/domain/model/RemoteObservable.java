package server.domain.model;

import client.domain.mediator.RemoteObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteObservable {

    private ArrayList<RemoteObserver> observers = new ArrayList<>();

    public void addObserver(RemoteObserver o){
        observers.add(o);
    }

    public void notifyObservers(Object arg){
        observers.forEach(o -> {
            try {
                o.update(arg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}
