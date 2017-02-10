package com.javarush.test.threadExample;

/**
 * Created by Тарас on 09.02.2017.
 */
public class CurrentThreadDemo
{
    public void test() {
        System.out.println("test()");
    }

    public void test1() {
        this.test();
    }
    public static void main(String args[])
    {

        new CurrentThreadDemo().test1();
        /*Thread t = Thread.currentThread();
        System.out.println("Текущий поток: " + t);
// изменить имя потока
        t.setName("Мой Thread1");
        System.out.println("После изменения имени: " + t);
        try
        {
            for (int n = 5; n > 0; n--)
            {
                System.out.println(n);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Главный поток прерван");
        }*/
    }
}
