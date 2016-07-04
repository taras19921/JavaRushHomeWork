package com.javarush.test.volpis;

import java.util.HashMap;

/**
 * Created by Тарас on 05.01.2016.
 */
public class HashMapTest
{
    public static void main(String[] args)
    {
        String row = "12";
        String arraystr[] = row.split(",");
        for (int i = 0; i < arraystr.length; i++) {
            System.out.println(arraystr[i]);
        }
        /*HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "PIZZA HUT EXPRESS");
        map.put(2, "TACO BELL EXPRESS");
        if (map.containsKey(1))
            System.out.println(map.get(0));
        if (map.containsKey(3))
            System.out.println("3");
            */
        //System.out.println(map.get(1));
    }
}
