package com.javarush.test.volpis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class WeatherParser
{
    public static void main(String[] args)
    {
        try
        {
            Document document = Jsoup.connect("http://alerts.weather.gov/cap/us.php?x=1").parser(Parser.xmlParser()).get();
            Elements entries = document.select("entry");
            int j = 0;
            for (org.jsoup.nodes.Element temp1 : entries)
            {
                System.out.println(j);
                String identifier = temp1.select("id").text();
                System.out.println(identifier);
                String title = temp1.select("title").text();
                System.out.println(title);
                Elements geocodes = temp1.select("cap|geocode");
                int i = 0;
                for (org.jsoup.nodes.Element geocode : geocodes.select("value"))
                {
                    if (i == 1)
                    {
                        for (String hashcode : geocode.text().split(" "))
                        {
                            System.out.println(hashcode);
                        /*HashLocation hashLocation = realm.where(HashLocation.class).equalTo("hashcode",txt).findFirst();
                        if (hashLocation!=null){
                            list.add(hashLocation);
                        }
                        *//*
                        }
                    }
                    i++;

                }
                String polygon = temp1.select("cap|polygon").text();
                System.out.println(polygon);
                j++;
            }*/
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
