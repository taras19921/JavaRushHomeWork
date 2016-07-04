package com.javarush.test.volpis.timer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тарас on 01.11.2015.
 */
public class MainClass
{
    static void print()
    {
        System.out.println("asdf");
    }

    public static void main(String[] args)
    {
        String graph = null;
        try {
            String accessToken = "EAACEdEose0cBAARmuQucgnInMB0wTGr43ZC2lcJFqilXu7FmgjJFeRQSN7FscgghIr0pOtWx2KKeuy7iu" +
                    "3ZABUovWiJbmj70Sk2qKy8nB7XnzL3A0reBZCZAU11FIqMATS6OYGsDv6RpxI9ybvDQXa3KSm4y1lfxpLwhZAG4HzwZDZD";
            String g = "https://graph.facebook.com/me?" + accessToken;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();
            graph = b.toString();
            System.out.println(graph);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in getting FB graph data. " + e);
        }
        /*String imageUrl = "http://46.30.43.39:8080/salamat/img/ads/Ad127/Photo1463078186044_2.png?s=32";
        String url = imageUrl.split("\\?")[0];
        System.out.println(url);
        int size = Integer.parseInt(imageUrl.split("=")[1]);
        System.out.println(size);*/
        //new MainClass().test(3);
        /*List l = new ArrayList();
        l.add("a");
        l.add("b");
        l.add("c");
        System.out.println(l);*/

        /*System.out.println("true" + "\n" + "dd");
        System.out.println("a");*/
    }

    public void test(int a) {
        for (int i = 0; i < a; System.out.println(i));
    }
}
