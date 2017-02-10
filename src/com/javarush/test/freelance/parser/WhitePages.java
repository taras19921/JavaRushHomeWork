package com.javarush.test.freelance.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;

public class WhitePages
{
    public static void main(String[] args)
    {
        try
        {
            Document document = Jsoup.connect("http://www.whitepages.com/name/PHYLLIS-M.-ARTHUR/Belleville-NJ").ignoreHttpErrors(true).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Element link = document.getElementsByClass("link-wrapper clickstream-link").first();
            String href = link.select("href").text();
            System.out.println(href);

//            Elements serpListUnstyled = document.getElementsByClass("serp-list unstyled");
//            Elements primaryContent = document.getElementsByClass("primary-content");


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
