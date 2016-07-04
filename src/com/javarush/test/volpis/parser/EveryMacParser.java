package com.javarush.test.volpis.parser;

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

public class EveryMacParser
{

    public static void main(String[] args)
    {
//        List<String> families = families("http://www.everymac.com/");
//        allProducts(families);
    }

    public static void allProducts(List<String> pages)
    {
        Workbook workbook = null;
        FileInputStream file = null;
        int count = 0;
        try
        {
            Document document;
            file = new FileInputStream(new File("D:\\Freelance\\Everymac\\EveryMac.xlsx"));
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (String page : pages)
            {
                document = Jsoup.connect("http://www.everymac.com/systems/apple/macbook/specs/macbook-core-m3-1.1-12-early-2016-specs.html#macspecs3").timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                Elements productNames = document.select("span[id=contentcenter_specs_externalnav_2]");
                Elements processors = document.select("span[id=contentcenter_specs_externalnav_3]");
                Elements switchgroups1 = document.getElementsByClass("switchgroup1");
                for (int i = 0; i < switchgroups1.size(); i++)
                {
                    String productName = productNames.get(i + 1).select("a").text();
                    System.out.println("productName: " + productName);
                    String processor = processors.get(i + 1).select("span").text();
                    System.out.println("processor: " + processor);
                    String intro = "";
                    String disc = "";
                    String order = "";
                    String model = "";
                    String family = "";
                    String id = "";
                    String RAM = "";
                    String VRAM = "";
                    String storage = "";
                    String optical = "";

                    String image = switchgroups1.get(i).select("img").first().absUrl("src");
                    System.out.println("image: " + image);

                    Element table = switchgroups1.get(i).select("table").first();
                    Elements rows = table.select("tr");

                    for (int j = 0; j < rows.size(); j++)
                    { //first row is the col names so skip it.
                        Element row = rows.get(j);
                        if (j == 0)
                        {
                            Elements cols = row.select("td");
                            intro = cols.get(1).text();
                            System.out.println("intro: " + intro);
                            disc = cols.get(3).text();
                            System.out.println("disc: " + disc);
                        } else if (j == 1)
                        {
                            Elements cols = row.select("td");
                            order = cols.get(1).text();
                            System.out.println("order: " + order);
                            model = cols.get(3).select("a").first().text();
                            System.out.println("model: " + model);
                        } else if (j == 2)
                        {
                            Elements cols = row.select("td");
                            family = cols.get(1).text();
                            System.out.println("family: " + family);
                            id = cols.get(3).text();
                            System.out.println("id: " + id);
                        } else if (j == 3)
                        {
                            Elements cols = row.select("td");
                            RAM = cols.get(1).text();
                            System.out.println("RAM: " + RAM);
                            VRAM = cols.get(3).text();
                            System.out.println("VRAM: " + VRAM);
                        } else if (j == 4)
                        {
                            Elements cols = row.select("td");

                            storage = cols.get(3).text();
                            System.out.println("storage: " + storage);
                            optical = cols.get(1).text();
                            System.out.println("optical: " + optical);
                        } else {
                            //Create a new row in current sheet
                            Row row1 = sheet.createRow(count + 1);
//Create a new cell in current row
                            Cell cell = row1.createCell(0);
                            cell.setCellValue(productName);
                            cell = row1.createCell(1);
                            cell.setCellValue(processor);
                            cell = row1.createCell(2);
                            cell.setCellValue(intro);
                            cell = row1.createCell(3);
                            cell.setCellValue(disc);
                            cell = row1.createCell(4);
                            cell.setCellValue(order);
                            cell = row1.createCell(5);
                            cell.setCellValue(model);
                            cell = row1.createCell(6);
                            cell.setCellValue(family);
                            cell = row1.createCell(7);
                            cell.setCellValue(id);
                            cell = row1.createCell(8);
                            cell.setCellValue(RAM);
                            cell = row1.createCell(9);
                            cell.setCellValue(VRAM);
                            cell = row1.createCell(10);
                            cell.setCellValue(storage);
                            cell = row1.createCell(11);
                            cell.setCellValue(optical);
                            cell = row1.createCell(12);
                            cell.setCellValue(image);
                        }
                    }
                    count++;
                }
            }
            System.out.println(count);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {

        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\Everymac\\EveryMac.xlsx"));
                workbook.write(outFile);
                outFile.close();
                System.out.println(count);
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

    /*public static void allProducts(List<String> pages)
    {
        Workbook workbook = null;
        FileInputStream file = null;
        int count = 0;
        try
        {
            Document document;
            file = new FileInputStream(new File("D:\\Freelance\\Everymac\\EveryMac.xlsx"));
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (String page : pages)
            {
                document = Jsoup.connect("http://www.everymac.com/systems/apple/macbook/specs/macbook-core-m3-1.1-12-early-2016-specs.html#macspecs3").timeout(0*//*10 * 1000*//*).parser(Parser.htmlParser()).get();
                Elements productNames = document.select("span[id=contentcenter_specs_externalnav_2]");
                Elements processors = document.select("span[id=contentcenter_specs_externalnav_3]");
                Elements switchgroups1 = document.getElementsByClass("switchgroup1");
                for (int i = 0; i < switchgroups1.size(); i++)
                {
                    String productName = productNames.get(i + 1).select("a").text();
                    System.out.println("productName: " + productName);
                    String processor = processors.get(i + 1).select("span").text();
                    System.out.println("processor: " + processor);
                    String intro = "";
                    String disc = "";
                    String order = "";
                    String model = "";
                    String family = "";
                    String id = "";
                    String RAM = "";
                    String VRAM = "";
                    String storage = "";
                    String optical = "";

                    String image = switchgroups1.get(i).select("img").first().absUrl("src");
                    System.out.println("image: " + image);

                    Element table = switchgroups1.get(i).select("table").first();
                    Elements rows = table.select("tr");

                    for (int j = 0; j < rows.size(); j++)
                    { //first row is the col names so skip it.
                        Element row = rows.get(j);
                        if (j == 0)
                        {
                            Elements cols = row.select("td");
                            intro = cols.get(1).text();
                            System.out.println("intro: " + intro);
                            disc = cols.get(3).text();
                            System.out.println("disc: " + disc);
                        } else if (j == 1)
                        {
                            Elements cols = row.select("td");
                            order = cols.get(1).text();
                            System.out.println("order: " + order);
                            model = cols.get(3).select("a").first().text();
                            System.out.println("model: " + model);
                        } else if (j == 2)
                        {
                            Elements cols = row.select("td");
                            family = cols.get(1).text();
                            System.out.println("family: " + family);
                            id = cols.get(3).text();
                            System.out.println("id: " + id);
                        } else if (j == 3)
                        {
                            Elements cols = row.select("td");
                            RAM = cols.get(1).text();
                            System.out.println("RAM: " + RAM);
                            VRAM = cols.get(3).text();
                            System.out.println("VRAM: " + VRAM);
                        } else if (j == 4)
                        {
                            Elements cols = row.select("td");

                            storage = cols.get(3).text();
                            System.out.println("storage: " + storage);
                            optical = cols.get(1).text();
                            System.out.println("optical: " + optical);
                        } else {
                            //Create a new row in current sheet
                            Row row1 = sheet.createRow(count + 1);
//Create a new cell in current row
                            Cell cell = row1.createCell(0);
                            cell.setCellValue(productName);
                            cell = row1.createCell(1);
                            cell.setCellValue(processor);
                            cell = row1.createCell(2);
                            cell.setCellValue(intro);
                            cell = row1.createCell(3);
                            cell.setCellValue(disc);
                            cell = row1.createCell(4);
                            cell.setCellValue(order);
                            cell = row1.createCell(5);
                            cell.setCellValue(model);
                            cell = row1.createCell(6);
                            cell.setCellValue(family);
                            cell = row1.createCell(7);
                            cell.setCellValue(id);
                            cell = row1.createCell(8);
                            cell.setCellValue(RAM);
                            cell = row1.createCell(9);
                            cell.setCellValue(VRAM);
                            cell = row1.createCell(10);
                            cell.setCellValue(storage);
                            cell = row1.createCell(11);
                            cell.setCellValue(optical);
                            cell = row1.createCell(12);
                            cell.setCellValue(image);
                        }
                    }
                    count++;
                }
            }
            System.out.println(count);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {

        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                file.close();
                outFile = new FileOutputStream(new File("D:\\Freelance\\Everymac\\EveryMac.xlsx"));
                workbook.write(outFile);
                outFile.close();
                System.out.println(count);
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

    public static List<String> families(String link)
    {

        List<String> families = new ArrayList<>();
        Document page;
        try
        {
            page = Jsoup.connect(link).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Element contentcenter_4colwrapper = page.getElementById("contentcenter_4colwrapper");
            Elements hrefs = contentcenter_4colwrapper.getElementsByTag("a");

            for (int i = 0; i < hrefs.size(); i++)
            {
                if (i % 2 != 0)
                {
                    families.add("http://www.everymac.com/" + hrefs.get(i).attr("href"));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println(families.get(families.size() - 1));
            return families;
        }
    }
}
