package com.javarush.test.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Тарас on 10.02.2017.
 */
public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
    public AddServerImpl() throws RemoteException {
        LocateRegistry.createRegistry(1099);
    }
    public double add(double dl, double d2) throws RemoteException
    {
        return dl + d2;
    }
}
