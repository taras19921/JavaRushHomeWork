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
    public static void main(String[] args) throws IOException
    {
        //fibonacci(8);
        //fibonacci3Number(8);
        //factorial(7);
        //sortArray();
        reverseNumber();

    }

    // Natural number
    public static void naturalNumber(int n)
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
        } while (natural.size() < n);
        System.out.println(natural);

    }

    // reverseNumber
    public static void reverseNumber() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Integer a = Integer.parseInt(br.readLine());
        StringBuilder s = new StringBuilder(br.readLine());
        System.out.println(s);
        s.reverse();
        System.out.println(s);
    }

    //Sorted array
    public static void sortArray() {
        int[] mas = {4, 2, 34, 22, 44, 1, 16, 55, 3, 8};
        int max = 0;
        for (int i = 0; i < mas.length; i++) {
            for (int j = i + 1; j < mas.length ; j++) {
                if (mas[j] < mas[i]) {
                    max = mas[i];
                    mas[i] = mas[j];
                    mas[j] = max;
                }
            }
        }
        for (int i = 0; i < mas.length; i++)
            System.out.print(mas[i] + " ");
    }

    //Fibonacci
    //1 2 3 5 8 13 21 34 55
    public static void fibonacci(int n) {
        //int[] mas = new int[n];
        int a = 0;
        int b = 1;

        for (int i = 0; i < n; i++)
        {
            int fact = a + b;
            System.out.print(fact + " ");
            //mas[i] = fact;
            a = b;
            b = fact;
        }
        System.out.println();
        /*for (int i = 0; i < mas.length; i++)
            System.out.println(mas[i]);
            */
    }

    //Factorial
    public static void factorial(int n)
    {
        int fact = 1;
        for (int i = 0; i < n; i++)
        {
            fact = fact * (i + 1);
            System.out.print(fact + " ");
        }
        //System.out.println(c);
    }

    //Fibonacci
    public static void fibonacci3Number(int n)
    {
        //int[] mas = new int[n];
        int a = 0;
        int b = 1;
        int c = 2;

        for (int i = 0; i < n; i++)
        {
            int d = a + b + c;
            System.out.print(d + " ");
            //mas[i] = c;
            a = b;
            b = c;
            c = d;
        }
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