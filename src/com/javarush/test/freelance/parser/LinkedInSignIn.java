package com.javarush.test.freelance.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class LinkedInSignIn
{

    public static void main(String[] args) throws Exception {

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
                    .execute();

            Document document = response.parse();

            //            System.out.println(document)

            System.out.println("Welcome "
                    + document.select(".act-set-name-split-link").html());

            response = Jsoup.connect("https://www.linkedin.com/connected/?filter=recent&trk=nav_responsive_sub_nav_network#?filter=recent&trk=nav_responsive_sub_nav_network&")
                    .cookies(response.cookies())
                    .method(Connection.Method.GET)
                    .followRedirects(true)
                    .execute();

            document = response.parse();

            System.out.println("Welcome "
                    + document.select(".name").html());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}