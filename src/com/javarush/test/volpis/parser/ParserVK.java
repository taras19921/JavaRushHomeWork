package com.javarush.test.volpis.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParserVK
{

    private static final String APP_ID = "5465471";
    public static void main(String[] args)
    {
        try
        {
            Document page1 = Jsoup.connect("https://oauth.vk.com/authorize?client_id=" + APP_ID
                    + "&scope=email&redirect_uri=https://oauth.vk.com/blank.html&display=page&v=5.21&response_type=token")
                    .parser(Parser.htmlParser()).get();
            Elements inputValues = page1.select("input[type=hidden]");
            String ip_h = inputValues.select("input[name=ip_h]").attr("value");
            String lg_h = inputValues.select("input[name=lg_h]").attr("value");
            String to = inputValues.select("input[name=to]").attr("value");
            System.out.println("ip_h: " + ip_h);
            System.out.println("lg_h: " + lg_h);
            System.out.println("to: " + to);
            /*Document page2 = Jsoup.connect("https://login.vk.com/?act=login&_origin=http://m.vk.com&ip_h=" + ip_h)
                    .parser(Parser.htmlParser()).post();*/
            Document page2 = Jsoup.connect("https://login.vk.com/?act=login&ip_h=" + ip_h + "&lg_h=" + lg_h + "&to=" + to + "&soft=1")
                    .parser(Parser.htmlParser()).post();

            String title = page2.getElementsByTag("title").text();
            System.out.println("title: " + title);

            //Elements elements = page.getElementsByClass("form_item fi_fat");
            /*for (Element companyName : elements)
            {
                String name = companyName.text();
                String companyLink = companyName.attr("href");
                System.out.println("companyLink: " + companyLink);
                System.out.println("company name: " + name);
                Document pageDetail = Jsoup.connect(companyLink).parser(Parser.htmlParser()).get();
            }*/
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
