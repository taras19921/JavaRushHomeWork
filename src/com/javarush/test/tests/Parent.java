package com.javarush.test.tests;

/**
 * Created by Тарас on 17.05.2015.
 */
public class Parent {
    private void whoAreYou()
    {
        System.out.println("I'm parent");
    }

    public static void main(String []args){
        Parent p = new Child();
        p.whoAreYou();
    }
}

class Child extends Parent {
    public void whoAreYou()
    {
        System.out.println("I'm child");
    }
}
