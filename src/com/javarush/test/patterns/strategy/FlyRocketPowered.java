package com.javarush.test.patterns.strategy;

/**
 * Created by Тарас on 08.06.2015.
 */
public class FlyRocketPowered implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
