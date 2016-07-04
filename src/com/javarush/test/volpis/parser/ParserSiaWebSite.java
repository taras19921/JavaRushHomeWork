package com.javarush.test.volpis.parser;

public class ParserSiaWebSite
{

    public static void main(String[] args)
    {
        /*try
        {
            Document document = Jsoup.connect("http://www.sia.ch/en/membership/member-directory/individual-members/m/207778/").timeout(0*//*10 * 1000*//*).parser(Parser.htmlParser()).get();

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
        }*/


    }


}
