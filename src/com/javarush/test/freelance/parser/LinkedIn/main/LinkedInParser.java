package com.javarush.test.freelance.parser.LinkedIn.main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Тарас on 14.10.2016.
 */
public class LinkedInParser
{
    public static void main(String[] args)
    {
        try {

            String url = "https://www.linkedin.com/uas/login?goback=&trk=hb_signin";
            Connection.Response response = Jsoup
                    .connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            Document responseDocument = response.parse();
            Element loginCsrfParam = responseDocument
                    .select("input[name=loginCsrfParam]")
                    .first();

            response = Jsoup.connect("https://www.linkedin.com/uas/login-submit")
                    .cookies(response.cookies())
                    .data("loginCsrfParam", loginCsrfParam.attr("value"))
                    .data("session_key", "taras19921@gmail.com")
                    .data("session_password", "karate")
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute().url(new URL("https://www.linkedin.com/vsearch/c?keywords=Consumer%20Electronics&trk=tyah&trkInfo=clickedVertical%3Ahistory,entityType%3AqueryHistoryName,clickedEntityId%3Ahistory_Consumer%20Electronics,idx%3A0&rsid=3829475441476450626151&openFacets=N,CCR,JO,CS&orig=FCTD&f_CS=D"));

            response = Jsoup.connect("https://www.linkedin.com/vsearch/c?keywords=Consumer%20Electronics&trk=tyah&trkInfo=clickedVertical%3Ahistory,entityType%3AqueryHistoryName,clickedEntityId%3Ahistory_Consumer%20Electronics,idx%3A0&rsid=3829475441476450626151&openFacets=N,CCR,JO,CS&orig=FCTD&f_CS=D")
                    .cookies(response.cookies())
                    .execute();

            Document document = response.parse();

            //            System.out.println(document)

            System.out.println("Welcome "
                    + document.select(".act-set-name-split-link").html());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
