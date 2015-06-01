package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by Тарас on 23.05.2015.
 */
public interface EventDataRow
{
    public EventType getType();
    public Date getDate();
    public int getTime();
}
