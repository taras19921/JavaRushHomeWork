package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Тарас on 22.05.2015.
 */
public class MyThread extends Thread
{
    public static AtomicInteger counter = new AtomicInteger(1);

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        if (counter.get() < 10){
            setPriority(counter.getAndIncrement());
        }
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread()
    {
        if (counter.get() < 10){
            setPriority(counter.getAndIncrement());
        }
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(Runnable target)
    {
        super(target);
        if (counter.get() < 10){
            setPriority(counter.getAndIncrement());
        }
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        if (counter.get() < 10){
            setPriority(counter.getAndIncrement());
        }
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(String name)
    {
        super(name);
        if (counter.get() < 10){
            setPriority(counter.getAndIncrement());
        }
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        setPriority(countThread());
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        setPriority(countThread());
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        setPriority(countThread());
    }

    public int countThread()
    {
        int i;
        if (counter.get() < 10)
        {
            i = counter.getAndIncrement();
        } else
        {
            i = counter.get();
            counter.set(1);
        }
        return i;
    }
}