package com.javarush.test.volpis;

/**
 * Created by Тарас on 27.10.2015.
 */
public class DeleteTimeLocation
{
    public static void main(String[] args) throws InterruptedException
    {

        String content = "Belgium has raised its terrorism alert to its highest level." +
                " Three days of national mourning have been declared." +
                "\\n\\nPrime Minister Charles Michel called the latest attacks" +
                " \"blind, violent and cowardly\", adding: \"This is a day of tragedy, a black day..." +
                " I would like to call on everyone to show calmness and solidarity\"." +
                "\\n\\nEuropean Council President Donald Tusk said:" +
                " \"These attacks mark another low by the terrorists in the service of hatred and violence." +
                "\"\\n\\nUS President Barack Obama called the blasts \"outrageous attacks against innocent people\"." +
                "\\n\\nThe airport and the entire public transport system in Brussels are closed," +
                " although some train stations are due to reopen shortly.\\n\\n\"";

        System.out.println(content);
        System.out.println(content.replaceAll("\\\\n\\\\n", "\n\t"));



        /*File folder = new File("D:\\New");
        if (folder.isDirectory()) {
            System.out.println("It's a folder!");
            File[] files = folder.listFiles();
            if (files.length == 0)
                System.out.println("Empty!");
        }*/

        /*BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File("D:\\Photo1456477117876_1.png"));
            System.out.println("Width: " + bufferedImage.getWidth());
            System.out.println("Height: " + bufferedImage.getHeight());

        } catch (IOException e)
        {
            e.printStackTrace();
        }*/
        //Date date = new Date();
        //System.out.println(date.getTime());
        /*
        long startTime = (date.getTime() / 1000);
        System.out.println("startTime: " + startTime);
        Thread.sleep(10000);
        Date date1 = new Date();
        long stopTime = date1.getTime() / 1000;
        System.out.println("stopTime: " + stopTime);
        double timeFinish = (double) (stopTime - startTime) / 60;
        System.out.println("timeFinish: " + timeFinish);
        if (timeFinish > 5)
            System.out.println("set null");
        else
            System.out.println("continue");
        //*/
        //double a = 10.0 / 60.0;
        //System.out.println(a);
        //String formatedDouble = String.format("%.10f", a);
        //System.out.println(formatedDouble);
        /*long a = 30;
        if (timeFinish > a) {
            System.out.println("Delete");
        } else {
            System.out.println("Continue");
        }
        */
    }
}
