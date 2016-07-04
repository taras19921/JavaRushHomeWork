package com.javarush.test.tests.EPAM;

/**
 * Created by Тарас on 21.07.2015.
 */
class Parent
{
    public Parent()
    {
    }

    public Parent(String s){
        print("parent");
    }
    public static void print(String s){
        System.out.println("Parent: " + s);
    }
}

public class Child extends Parent
{
    public static void print(String s){
        System.out.println("Child: " + s);
    }
    public static void main(String[] args){
        Parent parent = new Child();
        print("created");
        Child child = new Child();
        print("created");
    }
}
