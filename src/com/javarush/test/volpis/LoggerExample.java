package com.javarush.test.volpis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Тарас on 10.02.2016.
 */
public class LoggerExample
{
    public static void main(String[] args)
    {
        /*String LINUX = "/var/salamat/img/service_providers/";
        String photoURL = "http://46.30.43.39:8080/salamat/img/service_providers/Service_Provider68/Photo1455612036525_1.png ";
        String imageFolder = photoURL.split(" ")[0].replaceFirst("http://46.30.43.39:8080/salamat/img/service_providers/", "");
        imageFolder = imageFolder.split("/")[0];
        imageFolder = LINUX + imageFolder + "/";
        System.out.println(imageFolder);*/
        /*List<Date> dates = new ArrayList<>();
        dates.add(new Date());
        dates.add(new Date());
        String number = "+996700157442";
        String content = "Останнє";
        content = content.toLowerCase();
        String letter = "Ос";
        letter = letter.toLowerCase();
        if (content.toLowerCase().startsWith(letter.toLowerCase()))
            System.out.println(content);*/
        /*if (number.startsWith("+99")) {
            System.out.println(number);
        }*/

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try
        {

            // This block configure the logger with handler and formatter
            int limit = 1000000000; // 1 Gb
            fh = new FileHandler("D:/Log.log", limit, 1, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("limit: " + limit);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
