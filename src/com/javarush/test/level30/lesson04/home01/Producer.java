package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Тарас on 14.05.2015.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;
    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    /*int counter = 1;
    while (true){
    if (counter <= 9){
        System.out.printf("Элемент 'ShareItem-%d' добавлен\n", counter);
        queue.offer(new ShareItem(String.format("ShareItem-%d", counter), counter));
        counter++;
    }
    try
    {
        Thread.sleep(100);
    }
    catch (InterruptedException e){return;}
    if (queue.hasWaitingConsumer()){
        System.out.println("Consumer в ожидании!");
    }
}*/

    @Override
    public void run()
    {
        for (int counter = 1; counter <= 9; counter++)
        {
            System.out.printf("Элемент 'ShareItem-%d' добавлен\n", counter);
            queue.offer(new ShareItem(String.format("ShareItem-%d", counter), counter));

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e){return;}
            if (queue.hasWaitingConsumer()){
                System.out.println("Consumer в ожидании!");
            }
        }

    }
}