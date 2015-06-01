package com.javarush.test.level28.lesson04.task02;

import java.util.concurrent.ThreadLocalRandom;

/* ThreadLocalRandom
Класс Solution будет использоваться трэдами.
Реализуйте логику всех методов, используйте класс ThreadLocalRandom.
getRandomIntegerBetweenNumbers должен возвращать случайный int между from и to
getRandomDouble должен возвращать случайный double
getRandomLongBetween0AndN должен возвращать случайный long между 0 и n
*/
public class Solution
{
    static ThreadLocalRandom instance = ThreadLocalRandom.current();

    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        //int r = ThreadLocalRandom.current() .nextInt(4, 77);
        return instance.current().nextInt(from, to);
    }

    public static double getRandomDouble() {
        return instance.current().nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        return instance.current().nextLong(n);
    }

    public static void main(String[] args) {
        System.out.println(getRandomIntegerBetweenNumbers(1, 5));
    }
}
