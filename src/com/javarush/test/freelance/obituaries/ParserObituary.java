package com.javarush.test.freelance.obituaries;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserObituary
{

    public static void main(String[] args)
    {
//        List<String> nextPages = pages("http://www.obitsarchive.com/obituaries/usa/new-jersey?page=1002&lname=&fname=&formDate=jun+01%2C+2016+-+aug+25%2C+2016&kwinc=&sort=dsc");
//        List<String> nextPages = pages("http://www.obitsarchive.com/obituaries/usa/new-jersey/newark?lname=&fname=&formDate=apr%201%2C2016%20-%20jun%205%2C2016&kwinc=&sort=dsc&pub[0]=star-ledger&pub[1]=star-ledger-the-web-edition-articles");
//        List<String> nextPages = pages("http://obitsarchive.com/obituaries/usa/new-jersey?lname=&fname=&formDate=jun+01%2C+2016+-+nov+01%2C+2016&kwinc=&sort=dsc");
        List<String> nextPages = pages("http://obitsarchive.com/obituaries/usa/new-jersey?lname=&fname=&formDate=dec+01%2C+2016+-+dec+24%2C+2016&kwinc=&sort=dsc");
        List<String> records = getRecords(nextPages);
        saveToExcel(records);
//        saveToTextFile(records);
//        saveToExcelSheet();

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

    public static void saveToExcelSheet()
    {

        Workbook workbook = null;
        Sheet sheet;
        FileInputStream file = null;
        BufferedReader br;

        try
        {

            int item = 1;
            int count = 1;
            String sCurrentLine;
            String name = "";
            String newspaperSource = "";
            String date;

            br = new BufferedReader(new FileReader("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.txt"));
            file = new FileInputStream(new File("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.xlsx"));
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(0);

            while ((sCurrentLine = br.readLine()) != null)
            {
                if (sCurrentLine.equals("Save to My Filebox")) ;
                else if (sCurrentLine.equals("")) ;
                else if (item == 1)
                {
                    name = sCurrentLine;
                    System.out.println(name);
                    item++;
                } else if (item == 2)
                {
                    newspaperSource = sCurrentLine;
                    System.out.println(newspaperSource);
                    item++;
                } else if (item == 3)
                {
                    date = sCurrentLine;
                    System.out.println(date);

                    item = 1;
                    //Create a new row in current sheet
                    Row row = sheet.createRow(count);
                    //Create a new cell in current row
                    Cell cell = row.createCell(0);
                    //Set value to new value
                    cell.setCellValue(name);
                    cell = row.createCell(1);
                    cell.setCellValue(newspaperSource);
                    cell = row.createCell(2);
                    cell.setCellValue(date);

                    count++;
                }
            }
            System.out.println("Done save to Excel file");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {

                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.xlsx"));
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

        System.out.println("Done");
    }

    public static void saveToExcel(List<String> records)
    {

        Workbook workbook = null;
        Sheet sheet;
        FileInputStream file = null;

        try
        {

            int item = 1;
            int count = 1;
            String name = "";
            String newspaperSource = "";
            String date;

            file = new FileInputStream(new File("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.xlsx"));
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(0);

            for (String record : records)
            {

                if (record.equals("Save to My Filebox")) ;
                else if (record.equals("")) ;
                else if (item == 1)
                {
                    name = record;
                    System.out.println(name);
                    item++;
                } else if (item == 2)
                {
                    newspaperSource = record;
                    System.out.println(newspaperSource);
                    item++;
                } else if (item == 3)
                {
                    date = record;
                    System.out.println(date);

                    item = 1;
                    //Create a new row in current sheet
                    Row row = sheet.createRow(count);
                    //Create a new cell in current row
                    Cell cell = row.createCell(0);
                    //Set value to new value
                    cell.setCellValue(name);
                    cell = row.createCell(1);
                    cell.setCellValue(newspaperSource);
                    cell = row.createCell(2);
                    cell.setCellValue(date);

                    count++;
                }
            }
            System.out.println("Done save to Excel file");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {

                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.xlsx"));
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

        System.out.println("Done");
    }

    public static void saveToTextFile(List<String> records)
    {

        try
        {
            File file = new File("D:\\Freelance\\Obituary\\obituaries-new-jersey-Dec 01, 2016 - Dec 24, 2016.txt");

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
                } else
                {
                    bw.write(item + "\n");
                    count++;
                }
            }
            bw.close();

            System.out.println("Done save to Txt file");

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
            for (int i = 0; i < 10000; i++)
//            for (int i = 0; i < 1; i++)
            {
                Document nextPage = Jsoup.connect(relHref).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                pagerNext = nextPage.getElementsByClass("pager-next");
                link = pagerNext.select("a").first();
                relHref = "http://www.obitsarchive.com" + link.attr("href");
                nextPages.add(relHref);
                if (i % 50 == 0)
                    System.out.println(relHref);
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

    }
}