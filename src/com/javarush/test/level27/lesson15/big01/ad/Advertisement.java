package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by Тарас on 22.05.2015.
 */
public class Advertisement
{
    private Object content; //Video
    private String name;    //Name
    private long initialAmount; //Advertisement total price

    public int getHits()
    {
        return hits;
    }

    private int hits;   //Number of needed payed plays
    private int duration;   //Duration of video in seconds
    private long amountPerOneDisplaying;    //Money for one displaying

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public int getDuration()
    {

        return duration;
    }

    public String getName()
    {

        return name;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration)
    {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount / hits;
    }
    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        hits--;
    }
}
