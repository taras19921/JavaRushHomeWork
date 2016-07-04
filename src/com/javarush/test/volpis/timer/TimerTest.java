package com.javarush.test.volpis.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Тарас on 29.10.2015.
 */
public class TimerTest
{
    public static void main(String args[])
    {
        MyTimerTask task = new MyTimerTask();
        Timer timer = new Timer();

        /* Устанавливает начальную паузу в 1 секунду,
        затем повторяется каждые полсекунды.
        */
        timer.schedule(task, 5000, 10000);
        try
        {
            Thread.sleep(20000);
        }
        catch (InterruptedException exc)
        {
        }
        //timer.cancel();
    }

    static class MyTimerTask extends TimerTask
    {
            public void run()
            {
            for (int i = 0; i < 15; i++)
            System.out.print(i + " ");
        }
    }
}
