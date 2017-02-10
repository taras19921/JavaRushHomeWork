package com.javarush.test.rmi;

import java.rmi.Naming;

/**
 * Created by Тарас on 10.02.2017.
 */
public class AddClient
{

    public static void main(String args[]) {
        try {
            String addServerURL = "rmi://" + "localhost:1099" + "/AddServer";
            AddServerIntf addServerIntf =
                    (AddServerIntf) Naming.lookup(addServerURL);
            System.out.println("Первое число: " + 1);
            double dl = Double.valueOf(1).doubleValue();
            System.out.println("Второе число: " + 2);
            double d2 = Double.valueOf(2).doubleValue();
            System.out.println("Сумма: " + addServerIntf.add(dl, d2));
        } catch(Exception e) {
            System.out.println("Исключение: " + e);
        }
    }
}
