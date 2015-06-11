package com.javarush.test.tests.EPAM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Тарас on 10.06.2015.
 */
public class Tasks
{
    /*
    // reverseNumber
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Integer a = Integer.parseInt(br.readLine());
        StringBuilder s = new StringBuilder(br.readLine());
        System.out.println(s);
        s.reverse();
        System.out.println(s);
    }
    */

    /*
    // Natural number
    public static void main(String[] args) throws IOException
    {
        ArrayList<Integer> natural = new ArrayList<>();
        int i = 1;
        label1: do
        {

                for (int j = 2; j < i; j++)
                {
                    if ((i % j == 0))
                    {
                        i++;
                        continue label1;
                    }
                }
                natural.add(i);
                i++;
        } while (natural.size() < 30);
        System.out.println(natural);

    }
    */

    // Numbers of Fibonacci && Factorization
    public static void main(String[] args) throws IOException
    {
        Tasks tasks = new Tasks();
        //tasks.fibonacci(9);
        tasks.factorial(5);
        //System.out.println(tasks.fibonacci1(9));     //34
        //System.out.println(tasks.fibonacci1(5));     //5
        //System.out.println(tasks.fibonacci1(2));     //1
    }

    public void fibonacci(int n) {
        //int[] mas = new int[n];
        int a = 0;
        int b = 1;

        for (int i = 0; i < n; i++)
        {
            int c = a + b;
            System.out.println(c);
            //mas[i] = c;
            a = b;
            b = c;
        }
        /*for (int i = 0; i < mas.length; i++)
            System.out.println(mas[i]);
            */
    }

    public void factorial(int n)
    {
        int c = 1;
        for (int i = 0; i < n; i++)
        {
            c = c * (i + 1);
            System.out.println(c);
        }
        //System.out.println(c);
    }

        public int fibonacci1(int n) {
        if (n < 3)
            return 1;
        else
        {
            return fibonacci1(n - 2) + fibonacci1(n - 1);
        }
        }

    public int factorial1(int n)
    {
        if (n == 1) return 1;
        else if (n < 0) throw new RuntimeException("Underflow error in factorial");
        else if (n > 20) throw new RuntimeException("Overflow error in factorial");
        else if (n == 0) return 1;
        else return n * factorial1(n - 1);
    }
}