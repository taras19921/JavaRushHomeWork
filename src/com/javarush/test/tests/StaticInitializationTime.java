package com.javarush.test.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тарас on 17.05.2015.
 */
public class StaticInitializationTime {
    static List<Character> alphabet;
    static {
        alphabet = new ArrayList<Character>();
        for (char c='a'; c<='z'; c++) alphabet.add(c);
        System.out.println(alphabet);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Before class loading");
        //Class.forName(C.class.getName());
        System.out.println("After class loading");
    }
}
