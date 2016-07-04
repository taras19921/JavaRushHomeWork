package com.javarush.test.volpis;

import java.io.IOException;
import java.util.Random;

public class CodeGenerator
{
    public static void main(String[] args) throws IOException
    {
        String queryAds = " AND category_id = 1 AND sub_category_id = 1 AND city_id = 1 AND currency_id = 1 AND price >= 1.0 AND price <= 20.0";
        String queryCarAds = " AND category_id = 1 AND sub_category_id = 1 AND city_id = 1 AND currency_id = 1 AND price >= 1.0 AND price <= 20.0";
        System.out.println("queryAds: " + queryAds.length());
        System.out.println("queryCarAds: " + queryCarAds.length());
        //System.out.println(codeGenerator());
    }

    public static String codeGenerator()
    {
        String code = null;
        Random generator = new Random();

        do
        {
            Integer codeInt = generator.nextInt(100000);
            String codeArr[] = codeInt.toString().split("");

            if (codeArr.length == 6) {
                code = codeInt.toString();
            }
        } while (code == null);

        return code;

        /*

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
        */
    }

}
