package com.javarush.test.rmi;

import java.rmi.Naming;

/**
 * Created by Тарас on 10.02.2017.
 */
public class AddServer
{
    public static void main(String args[]) {
        try {
            AddServerImpl addServerImpl = new AddServerImpl();
            Naming.rebind("AddServer", addServerImpl);
        } catch(Exception e) {
            System.out.println("Исключение: " + e);
        }
    }
}
