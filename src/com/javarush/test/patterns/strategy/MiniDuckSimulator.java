package com.javarush.test.patterns.strategy;

/**
 * Created by Тарас on 08.06.2015.
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {

        MallardDuck	mallard = new MallardDuck();
        RubberDuck	rubberDuckie = new RubberDuck();
        DecoyDuck	decoy = new DecoyDuck();

        ModelDuck	model = new ModelDuck();

        mallard.performQuack();
        rubberDuckie.performQuack();
        decoy.performQuack();

        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}