package com.javarush.test.patterns.strategy;

/**
 * Created by Тарас on 08.06.2015.
 */
public class MallardDuck extends Duck {

    public MallardDuck() {

        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();


    }

    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}