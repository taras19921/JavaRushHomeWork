package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Тарас on 22.05.2015.
 */
public class Cook extends Observable implements Observer
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public void update(Observable o, Object oOrder) {
        Order order = (Order) oOrder;
        ConsoleHelper.writeMessage("Start cooking - " + order + String.format(", cooking time %dmin", order.getTotalCookingTime()));
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
