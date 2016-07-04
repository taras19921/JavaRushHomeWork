package com.javarush.test.volpis;

import java.util.Random;


public class RandomString
{
    public static void main(String[] args)
    {
        Random ran = new Random();
        int count = 5;
        char data = ' ';
        String dat = "";

        for (int i = 0; i < count; i++)
        {
            data = (char) (ran.nextInt(25) + 97);
            dat = data + dat;
        }

        System.out.println(dat);
    }
}
