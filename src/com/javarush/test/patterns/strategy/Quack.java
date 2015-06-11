package com.javarush.test.patterns.strategy;

/**
 * Created by Тарас on 08.06.2015.
 */
public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}