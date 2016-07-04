package com.javarush.test.tests.EPAM;

/**
 * Created by Тарас on 21.07.2015.
 */
public class A
{
    public A()
    {
    }

    public A(String s) {
        System.out.print("A");
    }
}
class B extends A {
    public B() {
        System.out.print("Empty");
    }
    public B(String s) {
        System.out.print(s);
    }

    public static void main(String[] args) {
        new B("AB");
    }
}
