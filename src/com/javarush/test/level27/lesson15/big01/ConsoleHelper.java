package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Тарас on 22.05.2015.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return  reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        String nextDish;
        writeMessage("Choose dishes : " + Dish.allDishesToString());
        while (true){
            nextDish = readString();
            if (nextDish.equals("exit")) break;
            try{
                dishes.add(Dish.valueOf(nextDish));
            }
            catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage(String.format("%s is not detected", nextDish));
            }
        }
        return dishes;
    }
}
