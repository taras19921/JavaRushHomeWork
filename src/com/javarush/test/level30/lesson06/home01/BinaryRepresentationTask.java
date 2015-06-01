package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Тарас on 22.05.2015.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private int i;
    public BinaryRepresentationTask(int i)
    {
        this.i = i;
    }

    @Override
    protected String compute()
    {
        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);

        if (b > 0)
        {
            BinaryRepresentationTask task1 = new BinaryRepresentationTask(b);
            task1.fork();
            BinaryRepresentationTask task2 = new BinaryRepresentationTask(a);
            task2.fork();
            return task1.join() + task2.join();
        }
        return result;
    }
}
