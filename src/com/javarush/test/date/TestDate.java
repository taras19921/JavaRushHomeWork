package com.javarush.test.date;

import java.io.IOException;

/**
 * Created by Тарас on 29.12.2016.
 */
public class TestDate
{
    public static void main(String[] args) throws IOException
    {
        /*java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int fromDay = currentDay - 7;
        String monthName = getMonth(month);
        System.out.println("year: " + year);
        System.out.println("month: " + monthName);
        System.out.println("currentDay: " + currentDay);
        System.out.println("fromDay: " + fromDay);*/
//        System.out.println(EmojiUtils.getEmoji("\uD83D"));
//        System.out.println(EmojiParser.parseToAliases("\uD83D"));
        String urlServer = "http://localhost:8080/salamat/img/service_providers/";
        String urlImage = "http://localhost:8080/salamat/img/service_providers/Service_Provider174/Photo1484592952165_1.png";
        String urlPath = urlImage.replaceFirst(urlServer, "/var/salamat/img/service_providers/");
        System.out.println(urlPath);
//        System.out.println(EmojiParser.parseToAliases(StringEscapeUtils.unescapeJava("\uD83D")));
//        System.out.println(EmojiUtils.emojify("eded&#128045;"));
//        System.out.println(StringEscapeUtils.escapeHtml("\uD83D"));
//        System.out.println(EmojiUtils.getEmoji(StringEscapeUtils.escapeHtml("\uD83D")).getEmoji());
//        System.out.println(EmojiUtils.getEmoji("&#128045;").getEmoji());
    }

    public static String getMonth(int month){
        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        return monthNames[month];
    }
}
