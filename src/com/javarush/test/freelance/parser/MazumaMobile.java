package com.javarush.test.freelance.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Тарас on 16.09.2016.
 */
public class MazumaMobile
{
    public static void main(String[] args)
    {
        try
        {
            String threeMobileName;
            Double unlockedPrice = null;
            Double lockedPrice = null;
            String dataPrice;
            String transferValue;
            String URLS[] = {"https://www.linkedin.com/vsearch/c?keywords=Consumer%20Electronics&trk=tyah&trkInfo=clickedVertical%3Ahistory,entityType%3AqueryHistoryName,clickedEntityId%3Ahistory_Consumer%20Electronics,idx%3A0&rsid=3829475441476450626151&openFacets=N,CCR,JO,CS&orig=FCTD&f_CS=D"};
            for (int i = 0; i < URLS.length; i++) {

                Document document = Jsoup.connect(URLS[i]).
                        ignoreHttpErrors(true).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                threeMobileName = document.getElementsByClass("three-mobile").text();
                Elements dropdown = document.getElementsByClass("dropdown");
                Element select = dropdown.select("select").first();
                if (select != null)
                {
                    dataPrice = select.select("option").get(1).getElementsByTag("option").attr("data-price");
                    transferValue = document.getElementById("transferValue").select("span").text().substring(1);
                    unlockedPrice = Double.valueOf(dataPrice) + Double.valueOf(transferValue);
                    lockedPrice = Double.valueOf(transferValue);
                } else
                {
                    transferValue = document.getElementById("transferValue").select("span").text().substring(1);
                    unlockedPrice = Double.valueOf(transferValue);
                }
                System.out.println();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
