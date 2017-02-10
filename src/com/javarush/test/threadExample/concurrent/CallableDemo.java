package com.javarush.test.threadExample.concurrent;

import java.util.concurrent.*;

/**
 * Created by Тарас on 09.02.2017.
 */
public class CallableDemo
{
    public static void main(String args[]) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> f;
        Future<Double> f2;
        Future<Integer> f3;
        System.out.println("Запуск");
        f = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3, 4));
        f3 = es.submit(new Factorial(5));
        try {
            System.out.println("new Sum(10): " + f.get()) ;
            System.out.println("new Hypot(3, 4): " + f2 .get());
            System.out.println("new Factorial(5): " + f3.get()) ;
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        catch (ExecutionException exc) {
            System.out.println(exc);
        }
        es.shutdown();
        System.out.println("Завершение");
    }
}

// Три потока вычислений,
class Sum implements Callable<Integer> {
    int stop;
    Sum (int v) { stop = v; }
    public Integer call() {
        int sum = 0;
        for(int i = 1; i <= stop; i++) {
            sum += i;
        }
        return sum;
    }
}
class Hypot implements Callable<Double> {
    double side1, side2;
    Hypot(double s1, double s2) {
        side1 = s1 ;
        side2 = s2;
    }
    public Double call() {
        return Math.sqrt((side1*side1) + (side2*side2));
    }
}
class Factorial implements Callable<Integer>
{
    int stop;

    Factorial(int v)
    {
        stop = v;
    }

    public Integer call()
    {
        int fact = 1;
        for (int i = 2; i <= stop; i++)
        {
            fact *= i;
        }
        return fact;
    }
}
