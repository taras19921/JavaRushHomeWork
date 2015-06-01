package com.javarush.test.level28.lesson15.big01.model;

/**
 * Created by Тарас on 23.05.2015.
 */
public class Provider
{
    private Strategy strategy;

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy)
    {

        this.strategy = strategy;
    }
}
