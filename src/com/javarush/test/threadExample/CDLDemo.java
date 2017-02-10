package com.javarush.test.threadExample;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Тарас on 09.02.2017.
 */
class CDLDemo
{
    public static void main(String args[])
    {
        CountDownLatch cdl = new CountDownLatch(5);
        System.out.println("Запуск");
        new MyThread(cdl);
        try
        {
            cdl.await();
        }
        catch (InterruptedException exc)
        {
            System.out.println(exc);
        }
        System.out.println("Завершение");
    }
}

class MyThread implements Runnable
{
    CountDownLatch latch;

    MyThread(CountDownLatch c)
    {
        latch = c;
        new Thread(this).start();
    }

    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.println(i);
            latch.countDown(); // обратный отсчет
        }
    }
}
