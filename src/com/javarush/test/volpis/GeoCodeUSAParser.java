package com.javarush.test.volpis;

import java.io.*;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeoCodeUSAParser
{
    public static void main(String[] args)
    {
        JSONParser parser = new JSONParser();
        File file = new File("D:\\volpis\\roadhunter\\geocode_USA.json");
        try
        {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("D:\\volpis\\roadhunter\\geocode_USA.json"));

            for (int i = 0; i < jsonArray.size(); i++)
            {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String hashcode = jsonObject.get("hashcode").toString();
                String title = jsonObject.get("title").toString();
                System.out.println(hashcode);
                System.out.println(title.length());
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


    }
}
