package com.javarush.test.patterns.strategy;

/**
 * Created by Тарас on 08.06.2015.
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a model duck");
    }
}