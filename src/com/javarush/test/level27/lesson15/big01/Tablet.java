package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Тарас on 22.05.2015.
 */
public class Tablet extends Observable
{
    private final int number;
    static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder(){
        try {
            final Order order = new Order(this);
            if (order.isEmpty()) return;

            ConsoleHelper.writeMessage(order.toString());

            setChanged();
            notifyObservers(order);

            new Thread(){
                @Override
                public void run() {
                    try {
                        new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
                    } catch (NoVideoAvailableException e) {
                        logger.log(Level.INFO, "No video is available for the order " + order);
                    }
                }
            }.start();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
