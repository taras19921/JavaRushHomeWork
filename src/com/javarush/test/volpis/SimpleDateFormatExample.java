package com.javarush.test.volpis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatExample
{

    public static void main(String[] args)
    {

        String query = "SELECT * FROM advertisement WHERE city_id = 1 AND currency_id = 2 ORDER BY DATE DESC";
        //String query = "SELECT * FROM advertisement WHERE author_id = -1 AND city_id = 1 AND currency_id = 2 ORDER BY DATE DESC";
        //String query = "SELECT * FROM advertisement WHERE author_id = -1 AND currency_id = 2 AND city_id = 1 ORDER BY DATE DESC";
        //String query = "SELECT * FROM advertisement WHERE city_id = 1 AND currency_id = 2 ORDER BY DATE DESC";
        //String query = "SELECT * FROM advertisement WHERE currency_id = 2 AND city_id = 1 ORDER BY DATE DESC";
        int currencyId = 2;
        //String query = "SELECT * FROM advertisement WHERE currency_id = 2 ORDER BY DATE DESC";

        if (query.contains("AND"))
        {
            String replaceCurrency = " AND currency_id = " + currencyId;
            query = query.replace(replaceCurrency, "");
            System.out.println(query);
        } else
        {
            String replaceCurrency = "currency_id = " + currencyId;
            query = query.replace(replaceCurrency, "");
            System.out.println(query);
        }
        /*double latDB = 37.3885;
        double lonDB = -120.737;
        double latJson = 37.3876;
        double lonJson = -120.7382;
        double radius = 3961 * Math.acos(Math.cos(Math.toRadians(latJson)) * Math.cos(Math.toRadians(latDB)) * Math.cos(Math.toRadians(lonDB) - Math.toRadians(lonJson))
                + Math.sin(Math.toRadians(latJson))* Math.sin(Math.toRadians(latDB)));
        System.out.println(radius);*/

        /*String dateFormat = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        System.out.println(dateFormat);*/
    }
}
