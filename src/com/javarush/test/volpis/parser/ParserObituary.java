package com.javarush.test.volpis.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserObituary
{

    public static void main(String[] args)
    {
        List<String> nextPages = pages("http://www.obitsarchive.com/obituaries/usa/new-jersey/newark?lname=&fname=&formDate=apr%201%2C2016%20-%20jun%205%2C2016&kwinc=&sort=dsc&pub[0]=star-ledger&pub[1]=star-ledger-the-web-edition-articles");
        List<String> records = getRecords(nextPages);
        saveToFile(records);

    }

    public static List<String> getRecords(List<String> nextPages)
    {

        List<String> list = new ArrayList<>();

        for (String nextPage : nextPages)
        {

            try
            {
                Document page = Jsoup.connect(nextPage).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();

                Elements obituaries = page.getElementsByClass("field-content");

                for (Element obituary : obituaries)
                {
                    String title = obituary.text();
                    list.add(title);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return list;

    }

    public static void saveToFile(List<String> records)
    {

        try
        {
            File file = new File("D:\\A.txt");

            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            int count = 0;
            for (String item : records)
            {
                if (count > 0 && count % 4 == 0)
                {
                    bw.write("\n");
                    bw.write(item + "\n");
                    count++;
                }
                else
                {
                    bw.write(item + "\n");
                    count++;
                }
            }
            bw.close();

            System.out.println("Done");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<String> pages(String url)
    {

        List<String> nextPages = new ArrayList<>();
        nextPages.add(url);
        Document page;
        try
        {
            page = Jsoup.connect(url).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements pagerNext = page.getElementsByClass("pager-next");
            Element link = pagerNext.select("a").first();
            String relHref = "http://www.obitsarchive.com" + link.attr("href");
            for (int i = 0; i < 1000; i++)
            {
                Document nextPage = Jsoup.connect(relHref).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                pagerNext = nextPage.getElementsByClass("pager-next");
                link = pagerNext.select("a").first();
                relHref = "http://www.obitsarchive.com" + link.attr("href");
                nextPages.add(relHref);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return nextPages;
        }
//        return nextPages;
    }
}