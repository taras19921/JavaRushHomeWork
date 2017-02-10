package com.javarush.test.volpis;

import java.util.Date;

/**
 * Created by Тарас on 27.10.2015.
 */
public class DeleteTimeLocation
{
    public static void main(String[] args) throws InterruptedException
    {

        /***** Checking days from creation ads *******/

        Date dateOfCreationAd = new Date();
        System.out.println("Date of creation ad: " + dateOfCreationAd);
        long createAdsMillisecond = dateOfCreationAd.getTime();
        Thread.sleep(10000);
        Date currentDate = new Date();
        System.out.println("Current Date: " + currentDate);
        long currentTimeMillisecond = currentDate.getTime();
        /*long adsDaysCount = createAdsMillisecond / 1000 *//*sec*//*;
        System.out.println("adsDaysCount: " + adsDaysCount);*/
        int adminDaysToStop = 8;
        int adminDaysToDelete = 5;
        long adsDaysCount = (currentTimeMillisecond - createAdsMillisecond) / 1000/*sec*/;
        System.out.println("adsDaysCount: " + adsDaysCount);
        System.out.println("adminDaysToStop: " + adminDaysToStop);
        boolean isActive = true;
        System.out.println();

        if (adsDaysCount > adminDaysToStop)
        {
            System.out.println("set not active");
            isActive = false;
            Date dateOfStopAds = new Date();
            long stopAdsMillisecond = dateOfStopAds.getTime();

            /***** Checking days from stopping ads *******/

            Thread.sleep(10000);

            if (dateOfStopAds != null)
            {
                Date currentDate1 = new Date();
                long currentTime1Millisecond = currentDate1.getTime();

                long daysFromAdsStopCount = (currentTime1Millisecond - stopAdsMillisecond) / 1000/*sec*/;
                System.out.println("daysFromAdsStopCount: " + daysFromAdsStopCount);
                System.out.println("adminDaysToDelete: " + adminDaysToDelete);

                if (daysFromAdsStopCount > adminDaysToDelete)
                {
                    System.out.println("Delete Ad!");
                }

            }
        } else
        {
            System.out.println("continue");
        }

        /*Date dateOfStopAd = new Date();
        System.out.println("Date of stopping ad: " + dateOfStopAd);
        long adsDaysCount = dateOfStopAd.getTime();
        System.out.println("stopAdsMillisecond: " + adsDaysCount);
        long timeFromCreatingAdsSec = (adsDaysCount - createAdsMillisecond) / 1000*//*sec*//*;
        System.out.println("timeFromCreatingSec: " + timeFromCreatingAdsSec);
        int stopAdsSec = 8;*/


//        double timeFinishMilisec = (double) (stopTimeMilisec - startTimeMilisec) / 60;

        //************* Replace spaces ********************/

        /*String content = "Belgium has raised its terrorism alert to its highest level." +
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

           //************* Folder ********************/

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

        //************* Timer ********************//

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
