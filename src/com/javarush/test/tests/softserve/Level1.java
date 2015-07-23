package com.javarush.test.tests.softserve;

/**
 * Created by Тарас on 22.07.2015.
 */
public class Level1
{
    //long i = 032768;
    static char c1 = 064770;
    static char c2 = 'f';
    static char c3 = 0xbeef;
    static char c6 = '\uface';
    static int a = 034;

    public static void main(String[] args)
    {
        boolean a = true;
        boolean b = false;
        boolean c = true;
        if (a == true) {
            if (b == true) {
                System.out.println("1");
            } else
                System.out.println("2");
            if (c == true) {
                System.out.println("3");
            }
        } else
            System.out.println("4");

        //for (short i = 32767; i < 032768; i++);
    }
}
