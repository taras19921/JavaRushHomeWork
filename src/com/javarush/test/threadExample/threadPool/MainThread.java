package com.javarush.test.threadExample.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Тарас on 12.05.2015.
 */
public class MainThread
{
    public static void main(String[] args) {
        /*
        LiftOff launch = new LiftOff();
        launch.run();
        */

        // Create Thread object
        /*
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
        */

        //concurrency/CachedThreadPool
        /*
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
        */

        /*
        //concurrency/FixedThreadPool
        // Constructor argument is number of threads:
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
        */

        //concurrency/SingleThreadExecutor
        ExecutorService exec =
                Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();

    }
}
