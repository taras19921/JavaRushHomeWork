package com.volpis.roadhunter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Тарас on 21.12.2016.
 */
public class WeatherParser
{
    public static void main(String[] args)
    {
        parseWeather();
    }

    public static void parseWeather()
    {

        try
        {
            final String url = "http://alerts.weather.gov/cap/us.php?x=1";
            Document document = Jsoup.connect(url).parser(Parser.xmlParser()).get();
            if (document != null)
            {
                Elements entries = document.select("entry");
                int j = 0;
                for (org.jsoup.nodes.Element temp1 : entries)
                {
                    //System.out.println(j);
                    String summary = temp1.select("summary").text();
                    //System.out.println(summary);
                    String title = temp1.select("title").text();
                    String event = temp1.select("cap|event").text();
                    //System.out.println(title);
                    String polygon = temp1.select("cap|polygon").text();
                    //System.out.println(polygon);
                    j++;
                    //System.out.println();
                    Elements geocodes = temp1.select("cap|geocode");
                    int i = 0;
                    for (org.jsoup.nodes.Element geocode : geocodes.select("value"))
                    {
                        String areaDesc;
                        String[] geoId;
                        String geoId2 = "";
                        if (i == 0)
                        {
                            geoId = geocode.select("value").text().split(" ");
                            if (geoId != null && geoId.length != 0)
                            {
                                for (int k = 0; k < geoId.length; k++)
                                {
                                    int toIntNumber = toIntNumber(geoId[k]);
                                    if (k == geoId.length - 1)
                                        geoId2 += toIntNumber;
                                    else
                                        geoId2 += toIntNumber + " ";
                                }
                            } else
                                geoId2 += toIntNumber(geocode.select("value").text());
                            System.out.println("geoId2: " + geoId2);

                        } else if (i == 1)
                        {
                            areaDesc = geocode.select("value").text();

                        }
                        i++;
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("IO Exception!");
            System.err.println(e.getMessage());
        }
    }

    private static Integer toIntNumber(String number)
    {
        int intNumber;
        if (number.equals(""))
            return null;
        try
        {
            intNumber = Integer.parseInt(number);
        }
        catch (Exception e)
        {
            return null;
        }
        return intNumber;
    }
}
