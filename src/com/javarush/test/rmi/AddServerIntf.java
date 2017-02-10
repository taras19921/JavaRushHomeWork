package com.javarush.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Тарас on 10.02.2017.
 */
public interface AddServerIntf extends Remote
{
    double add(double dl, double d2) throws RemoteException;
}
