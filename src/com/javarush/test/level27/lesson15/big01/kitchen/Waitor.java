package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Тарас on 22.05.2015.
 */
public class Waitor implements Observer
{
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg.toString() + " was cooked by " + o.toString());
    }
}
