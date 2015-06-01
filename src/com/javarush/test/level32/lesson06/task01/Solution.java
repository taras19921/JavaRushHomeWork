package com.javarush.test.level32.lesson06.task01;

import java.io.*;
import java.util.HashSet;
import java.util.Random;


/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Каждый сгенерированный символ пароля пишите сразу в ByteArrayOutputStream.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] symbols = new char[letters.length()];
        int i = 0;
        for (char letter : letters.toCharArray()) {
            symbols[i] = letter;
            i++;
        }

        Random generator = new Random();

        HashSet<Character> characters = new HashSet<>();
        do
        {
            int gen = generator.nextInt(123);
            if (gen < 48 || gen > 122)
                continue;
            char symb = (char)gen;
            for (Character j : symbols)
            {
                if (symb == j)
                {
                    characters.add(symb);
                }
            }
        } while (characters.size() < 5);
        do
        {
            int gen = generator.nextInt(122);
            if (gen < 48 || gen > 122)
                continue;
            char symb = (char)gen;
            if (Character.isLowerCase(symb))
            {
                characters.add(symb);
            }
        } while (characters.size() < 6);
        do
        {
            int gen = generator.nextInt(122);
            if (gen < 48 || gen > 122)
                continue;
            char symb = (char)gen;
            if (Character.isDigit(symb))
            {
                characters.add(symb);
            }
        } while (characters.size() < 7);
        do
        {
            int gen = generator.nextInt(122);
            if (gen < 48 || gen > 122)
                continue;
            char symb = (char)gen;
            if (Character.isUpperCase(symb))
            {
                characters.add(symb);
            }
        } while (characters.size() < 8);

        StringBuilder s = new StringBuilder("");

        for (Character b : characters){
            s.append(b);
        }

        ByteArrayOutputStream f = new ByteArrayOutputStream();
        String ans = s.toString();
        byte buf[] = ans.getBytes();
        try {
            f.write(buf);
        } catch(IOException e) {
        }

        return f;
    }
}
