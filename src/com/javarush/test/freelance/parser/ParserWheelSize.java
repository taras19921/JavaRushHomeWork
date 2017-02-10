package com.javarush.test.freelance.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserWheelSize
{
    public static void main(String[] args)
    {
        //allPages("http://www.wheel-size.com/size/bmw/3-series/2016/");
        List<String> years = years("https://api.wheel-size.com/v1/years/?make=bmw&user_key=8d583d6974c68dedc23e92c658b957bd");
        List<String> models = models("https://api.wheel-size.com/v1/models/?make=bmw&user_key=8d583d6974c68dedc23e92c658b957bd");
        parseJson(years, models);
//        parseJson("https://api.wheel-size.com/v1/search/by_model/?make=bmw&year=2016&model=3-series&user_key=8d98677ed550804ef3a93ac9ff43cea2");
    }

    public static void parseJson(List<String> years, List<String> models)
    {

        Workbook workbook = null;
        FileInputStream file = null;
        try
        {
            int count = 1;
            for (int i = 0; i < years.size(); i++)
            {
                for (int j = 0; j < models.size(); j++)
                {
                    JSONParser parser = new JSONParser();
                    String response = Jsoup.connect("https://api.wheel-size.com/v1/search/by_model/?make=bmw&year=" + years.get(i) + "&model=" + models.get(j)
                            + "&user_key=8d583d6974c68dedc23e92c658b957bd").ignoreContentType(true).timeout(/*0*/10 * 1000).parser(Parser.htmlParser()).get().body().text();
                    JSONArray jsonArray = (JSONArray) parser.parse(response);

                    for (int k = 0; k < jsonArray.size(); k++)
                    {
                        file = new FileInputStream(new File("D:\\Freelance\\WheelSize\\BMW-3-Series.xlsx"));
                        workbook = new XSSFWorkbook(file);
                        Sheet sheet = workbook.getSheetAt(0);

                        JSONObject jsonObject = (JSONObject) jsonArray.get(k);
                        String subModel = jsonObject.get("trim").toString();

                        JSONArray wheels = (JSONArray) jsonObject.get("wheels");
                        JSONObject wheel = (JSONObject) wheels.get(0);

                        JSONObject front = (JSONObject) wheel.get("front");
                        String tire1 = front.get("tire").toString();
                        String rim1 = (String) front.get("rim");
                        String offset1 = "";
                        if (front.get("rim_offset") != null)
                            offset1 = "ET" + front.get("rim_offset").toString();
                        String bolt_pattern = "";
                        if (jsonObject.get("bolt_pattern") != null)
                            bolt_pattern = jsonObject.get("bolt_pattern").toString();
                        String thd = "";
                        if (jsonObject.get("lock_text") != null)
                            thd = jsonObject.get("lock_text").toString();
                        String cb = "";
                        if (jsonObject.get("centre_bore") != null)
                            cb = jsonObject.get("centre_bore").toString();

                        JSONObject rear = (JSONObject) wheel.get("rear");
                        String tire2 = "";
                        String offset2 = "";
                        String rim2 = "";
                        if (!tire2.equals(""))
                        {
                            rim2 = (String) rear.get("rim");
                            tire2 = rear.get("tire").toString();
                            offset2 = "ET" + rear.get("rim_offset").toString();
                        }
                        System.out.println("subModel: " + subModel);
                        System.out.println("tire1: " + tire1);
                        System.out.println("tire2: " + tire2);
                        System.out.println("rim1: " + rim1);
                        System.out.println("offset1: " + offset1);
                        System.out.println("rim2: " + rim2);
                        System.out.println("offset2: " + offset2);
                        System.out.println("bolt_pattern: " + bolt_pattern);
                        System.out.println("thd: " + thd);
                        System.out.println("cb: " + cb);
                        System.out.println();

                        //Create a new row in current sheet
                        Row row = sheet.createRow(count);
//Create a new cell in current row
                        Cell cell = row.createCell(0);
//Set value to new value
                        cell.setCellValue(years.get(i));
                        cell = row.createCell(1);
                        cell.setCellValue("BMW");
                        cell = row.createCell(2);
                        cell.setCellValue(models.get(j));
                        cell = row.createCell(3);
                        cell.setCellValue(subModel);
                        cell = row.createCell(4);
                        cell.setCellValue(tire1);
                        cell = row.createCell(5);
                        cell.setCellValue(tire2);
                        cell = row.createCell(6);
                        cell.setCellValue(rim1);
                        cell = row.createCell(7);
                        cell.setCellValue(offset1);
                        cell = row.createCell(8);
                        cell.setCellValue(rim2);
                        cell = row.createCell(9);
                        cell.setCellValue(offset2);
                        cell = row.createCell(10);
                        cell.setCellValue(bolt_pattern);
                        cell = row.createCell(11);
                        cell.setCellValue(thd);
                        cell = row.createCell(12);
                        cell.setCellValue(cb);

                        FileOutputStream outFile;

                        file.close();
                        outFile = new FileOutputStream(new File("D:\\Freelance\\WheelSize\\BMW-3-Series.xlsx"));
                        workbook.write(outFile);
                        outFile.close();
                        count++;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\WheelSize\\BMW-3-Series.xlsx"));
                workbook.write(outFile);
                outFile.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static List<String> models(String url)
    {
        List<String> models = new ArrayList<>();
        try
        {
            JSONParser parser = new JSONParser();
            String response = Jsoup.connect(url).ignoreContentType(true).timeout(/*0*/10 * 1000).parser(Parser.htmlParser()).get().body().text();

            JSONArray jsonArray = (JSONArray) parser.parse(response);
            for (int i = 0; i < jsonArray.size(); i++)
            {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String year = jsonObject.get("slug").toString();
                models.add(year);
            }
            System.out.println(models.size());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return models;
    }

    public static List<String> years(String url)
    {
        List<String> years = new ArrayList<>();
        try
        {
            JSONParser parser = new JSONParser();
            String response = Jsoup.connect(url).ignoreContentType(true).timeout(/*0*/10 * 1000).parser(Parser.htmlParser()).get().body().text();

            JSONArray jsonArray = (JSONArray) parser.parse(response);
            for (int i = 0; i < jsonArray.size(); i++)
            {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String year = jsonObject.get("name").toString();
                years.add(year);
            }
            System.out.println(years.size());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return years;
    }

    /*public static void parseJson(String page)
    {

        Workbook workbook = null;
        FileInputStream file = null;
        try
        {
            JSONParser parser = new JSONParser();
            String response = Jsoup.connect(page).ignoreContentType(true).timeout(*//*0*//*10 * 1000).parser(Parser.htmlParser()).get().body().text();
            JSONArray jsonArray = (JSONArray) parser.parse(response);

            file = new FileInputStream(new File("D:\\Freelance\\WheelSize\\BMW-3-Series.xlsx"));
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < jsonArray.size(); i++)
            {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String subModel = jsonObject.get("trim").toString();

                JSONArray wheels = (JSONArray) jsonObject.get("wheels");
                JSONObject wheel = (JSONObject) wheels.get(0);

                JSONObject front = (JSONObject) wheel.get("front");
                String tire1 = front.get("tire").toString();
                String rim1 = (String) front.get("rim");
                String offset1 = "ET" + front.get("rim_offset").toString();
                String bolt_pattern = jsonObject.get("bolt_pattern").toString();
                String thd = jsonObject.get("lock_text").toString();
                String cb = jsonObject.get("centre_bore").toString();

                JSONObject rear = (JSONObject) wheel.get("rear");
                String tire2 = "";
                String offset2 = "";
                String rim2 = "";
                if (!tire2.equals(""))
                {
                    rim2 = (String) rear.get("rim");
                    tire2 = rear.get("tire").toString();
                    offset2 = "ET" + rear.get("rim_offset").toString();
                }
                System.out.println("subModel: " + subModel);
                System.out.println("tire1: " + tire1);
                System.out.println("tire2: " + tire2);
                System.out.println("rim1: " + rim1);
                System.out.println("offset1: " + offset1);
                System.out.println("rim2: " + rim2);
                System.out.println("offset2: " + offset2);
                System.out.println("bolt_pattern: " + bolt_pattern);
                System.out.println("thd: " + thd);
                System.out.println("cb: " + cb);
                System.out.println();

                //Create a new row in current sheet
                Row row = sheet.createRow(i + 1);
//Create a new cell in current row
                Cell cell = row.createCell(0);
//Set value to new value
                cell.setCellValue(2016);
                cell = row.createCell(1);
                cell.setCellValue("BMW");
                cell = row.createCell(2);
                cell.setCellValue("3 Series");
                cell = row.createCell(3);
                cell.setCellValue(subModel);
                cell = row.createCell(4);
                cell.setCellValue(tire1);
                cell = row.createCell(5);
                cell.setCellValue(tire2);
                cell = row.createCell(6);
                cell.setCellValue(rim1);
                cell = row.createCell(7);
                cell.setCellValue(offset1);
                cell = row.createCell(8);
                cell.setCellValue(rim2);
                cell = row.createCell(9);
                cell.setCellValue(offset2);
                cell = row.createCell(10);
                cell.setCellValue(bolt_pattern);
                cell = row.createCell(11);
                cell.setCellValue(thd);
                cell = row.createCell(12);
                cell.setCellValue(cb);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\WheelSize\\BMW-3-Series.xlsx"));
                workbook.write(outFile);
                outFile.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }*/

    public static List<String> allPages(String page)
    {
        List<String> allPages = new ArrayList<>();
        try
        {
            Document document = Jsoup.connect(page).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements subModels = document.getElementsByClass("large-tag-view");
            String link = subModels.select("a").get(1).attr("href");
            System.out.println(link);

            /*Elements hrefs = document.getElementsByClass("sch2").select("a");
            for (Element href : hrefs)
            {
                String link = href.attr("href");
                allPages.add(link);
            }
            //}*/

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return allPages;
        }
    }
}
