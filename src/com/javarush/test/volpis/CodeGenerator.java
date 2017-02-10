package com.javarush.test.volpis;

import java.util.Random;

public class CodeGenerator
{
    public static void main(String[] args) throws Exception
    {

        int [ ] nums = {1,2,3,4,5,6};
        System.out.println((nums[1] + nums[3]));

        /*CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.method1();*/
        /*String d = "d";
        Integer i = 1;
        Double f = 2.3;
        new HashMap<1, i>;*/


//        blur(new char[]{'f', 'd', 'a', 'v'}, "vdaf");

//        System.out.println();
//        int a1 = 5; double a2 = (float)a1;

        /*int x = 2/4;
        System.out.println(x);*/
        /*if (x = 1) System.out.println(x);
        else System.out.println(0);*/
        /*try {
            int x = 0;
            int y = 5 / x;
        } catch (Exception e) {
            System.out.println("Exception");
        } catch (ArithmeticException ae) {
            System.out.println(" Arithmetic Exception");
        }
        System.out.println("finished");*/
//        CodeGenerator codeGenerator = new CodeGenerator();
//        codeGenerator.method1();
//        String queryAds = " AND category_id = 1 AND sub_category_id = 1 AND city_id = 1 AND currency_id = 1 AND price >= 1.0 AND price <= 20.0";
//        String queryCarAds = " AND category_id = 1 AND sub_category_id = 1 AND city_id = 1 AND currency_id = 1 AND price >= 1.0 AND price <= 20.0";
//        System.out.println("queryAds: " + queryAds.length());
//        System.out.println("queryCarAds: " + queryCarAds.length());
        //System.out.println(codeGenerator());
//        percent20();
//        percent50();

    }

    public static void  blur ( char[] z, String st ) {
        if ( z.length < st.length() ) return;
        for ( int j=0; j < st.length(); j++ )
        {
            z[j] = st.charAt(j);
            System.out.println("z[j]: " + z[j]);
        }
    }

    public static int doSomething(int x) {
        if (x == 0) return 1;
        else return x + doSomething(x - 1);
    }

    public void method1() throws Exception
    {
        method2();
    }

    public void method2() throws Exception
    {
        throw method3();
    }

    public Exception method3()
    {
        return new Exception();
    }

    public static int percent50()
    {
        while (true)
        {
            int rand = new Random().nextInt(2) + 1;
            System.out.println("rand = " + rand);
        }

    }

    public static boolean percent20()
    {

        while (true)
        {
            int rand = new Random().nextInt(5) + 1;
            System.out.println("rand = " + rand);

        }
        /*while (true) {
            int rand = new Random().nextInt(2);
            if (rand == 1) {
                System.out.println("rand = " + rand);
                System.out.println("true");
                return true;
            } else if (rand == 0){
                System.out.println("rand = " + rand);
                System.out.println("false");
                return false;
            }

        }*/
    }

    public static String codeGenerator()
    {
        String code = null;
        Random generator = new Random();

        do
        {
            Integer codeInt = generator.nextInt(100000);
            String codeArr[] = codeInt.toString().split("");

            if (codeArr.length == 6)
            {
                code = codeInt.toString();
            }
        }
        while (code == null);

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
