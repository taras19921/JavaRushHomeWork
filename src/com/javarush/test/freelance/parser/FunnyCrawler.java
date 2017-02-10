package com.javarush.test.freelance.parser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunnyCrawler
{

    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN
            = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

    static
    {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    public static void main(String[] args)
    {

        FunnyCrawler obj = new FunnyCrawler();
        obj.getDataFromGoogle("Garrett Sublette Abilene Christian University");
    }

    public String getDomainName(String url)
    {

        String domainName = "";
        matcher = patternDomainName.matcher(url);
        if (matcher.find())
        {
            domainName = matcher.group(0).toLowerCase().trim();
        }
        return domainName;

    }

    private Set<String> getDataFromGoogle(String query)
    {

        Set<String> result = new HashSet<>();
        String request = "https://www.google.com/search?q=" + query + "&num=20";

        try
        {

            // need http protocol, set this as a Google bot agent :)
            Document doc = Jsoup
                    .connect(request)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();

            // get all links
            Elements st = doc.getElementsByClass("st");
            for (int i = 0; i < st.size(); i++)
            {
                try
                {
                    String splitByEt1 = st.get(i).text().split("@")[0];
                    String[] splitBySpaceArray1 = splitByEt1.split(" ");
                    String splitByEt2 = st.get(i).text().split("@")[1];
                    String[] splitBySpaceArray2 = splitByEt2.split(" ");
                    String splitBySpace1 = splitBySpaceArray1[splitBySpaceArray1.length - 1];
                    String firstName = "Garrett";
                    String lastName = "Sublette";
                    if (splitBySpace1.toLowerCase().contains(firstName.toLowerCase())
                            || splitBySpace1.toLowerCase().contains(lastName.toLowerCase()))
                    {
                        String splitBySpace2 = splitBySpaceArray2[0];
                        String email = splitBySpace1 + "@" + splitBySpace2;
                        System.out.println(email);
                        System.out.println(splitBySpace1);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    i++;
                }

            }

        }
        catch (HttpStatusException hse)
        {
            System.out.println("HttpStatusException with query: " + query);
            // try again
            getDataFromGoogle(query);
        }
        catch (SocketTimeoutException ste)
        {
            System.out.println("SocketTimeoutException with query: " + query);

//                return;
            // try again
            getDataFromGoogle(query);
        }
        catch (SocketException se)
        {
            System.out.println("SocketException with query: " + query);

//                return;
            // try again
            getDataFromGoogle(query);
        }
        catch (UnknownHostException uhe)
        {

            System.out.println("UnknownHostException with query: " + query);
            // try again
            getDataFromGoogle(query);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

}
