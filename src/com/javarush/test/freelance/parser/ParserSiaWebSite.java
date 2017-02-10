package com.javarush.test.freelance.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParserSiaWebSite
{

    public static void main(String[] args)
    {
        try
        {
//            Document document = Jsoup.connect("http://www.sia.ch/en/membership/member-directory/individual-members/m/207778/").timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Document document = Jsoup.connect("http://www.autotrader.co.uk/search/used/cars/postcode/n128ln/radius/1501/sort/default/onesearchad/new%2Cnearlynew%2Cused/seller-type/trade_adverts/page/1/searchcontext/default").timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements contetnt = document.getElementsByClass("search-result__content");
            Element link = contetnt.select("a").first();

            Elements tables = document.select("table"); //select the all tables.

            for (int table = 0; table < tables.size(); table++) {

                Element table1 = tables.get(table); //select table.
                Elements rows = table1.select("tr"); //select all tr.
                for (Element row : rows)
                {
                   row = table1.select("tr").first(); //select first tr.
                    String businessTypeValue = table1.select("tr").first().text();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
