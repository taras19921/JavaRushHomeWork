package com.javarush.test.freelance.parser.EsourcesCoUk;

import au.com.bytecode.opencsv.CSVWriter;
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

public class EsourcesCoUk
{

    public static void main(String[] args)
    {
        // Apparel & Clothing
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/1/");
        // Arts & Crafts
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/2/");
        // Automotive & Transport
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/3/");
        // Business Services
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/4/");
        // Business Suppliers
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/5/");
        // Computer & Software
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/6/");
        // Construction & DIY
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/7/");
        // Dropshippers & Dropshipping
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/1082/");
        // Electrical & Lighting
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/8/");
        // Electronics & Photo
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/9/");
        // Floral & Garden
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/10/");
        // Food & Beverages
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/11/");
        // Gifts & Giftware
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/12/");
        // Health & Beauty
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/13/");
        // Home Supplies
        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/14/");
        // Industrial & Materials
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/1080/");
        // Jewellery & Watches
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/15/");
        // Promotional Merchandise
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/16/");
        // Publishing & Print
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/17/");
        // Security & Protection
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/18/");
        // Sports & Leisure
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/19/");
        // Surplus & Stocklots
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/20/");
        // Telecom & Mobiles
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/21/");
        // Textiles & Fabrics
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/22/");
        // Toys & Games
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/23/");
        // Travel & Outdoors
//        List<String> pages = pages("http://www.esources.co.uk/wholesale-suppliers/24/");
        List<String> allPages = allPages(pages);
        /*List<String> list = new ArrayList<>();
        list.add(*//*"http://www.esources.co.uk/supplier/25035/436/"*//*"http://www.esources.co.uk/supplier/16945/");*/
        createAndSaveExcelSheet(allPages/*list*/);
//        saveToSCV(allPages);
    }

    public static List<String> pages(String link)
    {

        List<String> pages = new ArrayList<>();
        pages.add(link);
        Document page;
        try
        {
            // Apparel & Clothing
//            for (int i = 0; i <= 300; i++)
            // Arts & Crafts
//            for (int i = 0; i <= 81; i++)
            // Automotive & Transport
//            for (int i = 0; i <= 71; i++)
            // Business Services
//            for (int i = 0; i <= 106; i++)
            // Business Suppliers
//            for (int i = 0; i <= 121; i++)
            // Computer & Software
//            for (int i = 0; i <= 93; i++)
            // Construction & DIY
//            for (int i = 0; i <= 136; i++)
            // Dropshippers & Dropshipping
//            for (int i = 0; i <= 57; i++)
            // Electrical & Lighting
//            for (int i = 0; i <= 123; i++)
            // Electronics & Photo
//            for (int i = 0; i <= 75; i++)
            // Floral & Garden
//            for (int i = 0; i <= 86; i++)
            // Food & Beverages
//            for (int i = 0; i <= 261; i++)
            // Gifts & Giftware
//            for (int i = 0; i <= 6; i++)
            // Health & Beauty
//            for (int i = 0; i <= 175; i++)
            // Home Supplies
            for (int i = 0; i <= 8/*350*/; i++)
            // Industrial & Materials
//            for (int i = 0; i <= 137; i++)
            // Jewellery & Watches
//            for (int i = 0; i <= 120; i++)
            // Promotional Merchandise
//            for (int i = 0; i <= 53; i++)
            // Publishing & Print
//            for (int i = 0; i <= 152; i++)
            // Security & Protection
//            for (int i = 0; i <= 44; i++)
            // Sports & Leisure
//            for (int i = 0; i <= 102; i++)
//            for (int i = 0; i <= 115; i++)
            // Telecom & Mobiles
//            for (int i = 0; i <= 32; i++)
            // Textiles & Fabrics
//            for (int i = 0; i <= 48; i++)
            // Toys & Games
//            for (int i = 0; i <= 81; i++)
            // Travel & Outdoors
//            for (int i = 0; i <= 67; i++)
//            for (int i = 0; i < 1; i++)
            {
                page = Jsoup.connect(link).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                Element results = page.getElementById("results");
                Elements links = results.getElementsByTag("a");

                link = links.get(links.size() - 1).attr("href");
                pages.add(link);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println(pages.get(pages.size() - 1));
            return pages;
        }
    }

    public static List<String> allPages(List<String> pages)
    {
        List<String> allPages = new ArrayList<>();
        try
        {
            Document document;
            for (String page : pages)
            {
                document = Jsoup.connect(page).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                Elements hrefs = document.getElementsByClass("sch2").select("a");
                for (Element href : hrefs)
                {
                    String link = href.attr("href");
                    allPages.add(link);
                }
            }
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

    public static List<String> saveToSCV(List<String> links)
    {
        String csv = "D:\\Freelance\\Lowcostseo\\esources_co_uk.csv";
        List<String[]> data = new ArrayList<>();
        Document document;
        try
        {
            CSVWriter writer = new CSVWriter(new FileWriter(csv));
            String[] fields = "Delivery Charge&Business name&Contact person&Address&Postcode&Telephone".split("&");
            writer.writeNext(fields);
            writer.close();

            String deliveryCharge = "";
            String businessName = "";
            String contactPerson = "";
            String address = "";
            String postCode = "";
            String telephone = "";
            for (String link : links)
            {
                document = Jsoup.connect(link).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                Element table1 = document.select("table").get(1); //select the first table.
                Elements rows1 = table1.select("tr");

                for (int i = 5; i < rows1.size(); i++)
                { //first row is the col names so skip it.
                    Element row = rows1.get(i);
                    Elements cols = row.select("td");
                    if (i == 13)
                    {

                        deliveryCharge = cols.get(0).text();
                        System.out.println("deliveryCharge: " + deliveryCharge);
                    }
                }
                Element table2 = document.select("table").get(3); //select the first table.
                Elements rows2 = table2.select("tr");

                if (rows2.size() > 2)
                {
                    for (int i = 0; i < rows2.size(); i++)
                    { //first row is the col names so skip it.
                        Element row = rows2.get(i);
                        Elements cols = row.select("td");
                        if (i == 0)
                        {
                            businessName = cols.get(0).text();
                            System.out.println("businessName: " + businessName);
                        } else if (i == 1)
                        {

                            contactPerson = cols.get(0).text();
                            System.out.println("contactPerson: " + contactPerson);
                        } else if (i == 2)
                        {

                            address = cols.get(0).text();
                            System.out.println("address: " + address);
                        } else if (i == 3)
                        {

                            postCode = cols.get(0).text();
                            System.out.println("postCode: " + postCode);
                        } else if (i == 4)
                        {

                            telephone = cols.select("p").first().text();
                            if (telephone.contains("P"))
                                telephone = telephone.split("P")[0];
                            System.out.println("telephone: " + telephone);
                        }
                    }
                } else
                {
                    table2 = document.select("table").get(2);
                    rows2 = table2.select("tr");
                    for (int i = 0; i < rows2.size(); i++)
                    { //first row is the col names so skip it.
                        Element row = rows2.get(i);
                        Elements cols = row.select("td");
                        if (i == 0)
                        {
                            businessName = cols.get(0).text();
                            System.out.println("businessName: " + businessName);
                        } else if (i == 1)
                        {

                            contactPerson = cols.get(0).text();
                            System.out.println("contactPerson: " + contactPerson);
                        } else if (i == 2)
                        {

                            address = cols.get(0).text();
                            System.out.println("address: " + address);
                        } else if (i == 3)
                        {

                            postCode = cols.get(0).text();
                            System.out.println("postCode: " + postCode);
                        } else if (i == 4)
                        {

                            telephone = cols.select("p").first().text();
                            if (telephone.contains("P"))
                                telephone = telephone.split("P")[0];
                            System.out.println("telephone: " + telephone);
                        }
                    }
                }
                writer = new CSVWriter(new FileWriter(csv, true));
                data.add(new String[]{deliveryCharge, businessName, contactPerson, address, postCode, telephone});
                writer.writeAll(data);
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }/*
        finally
        {
            return nextPages;
        }*/
        return null;
    }

    public static void createAndSaveExcelSheet(List<String> allPages)
    {
        Workbook workbook = null;
        FileInputStream file = null;
        try
        {
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Apparel & Clothing.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Arts & Crafts.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Automotive & Transport.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Business Services.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Business Supplies.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Computer & Software.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Construction & DIY.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Dropshippers & Dropshipping.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Electrical & Lighting.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Electronics & Photo.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Floral & Garden.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Food & Beverages.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Gifts & Giftware.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Health & Beauty.xlsx"));
            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Home Supplies.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Industrial & Materials.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Jewellery & Watches.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Promotional Merchandise.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Publishing & Print.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Security & Protection.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Sports & Leisure.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Surplus & Stocklots.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Telecom & Mobiles.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Textiles & Fabrics.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Toys & Games.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Travel & Outdoors.xlsx"));
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Document document;

            for (int page = 0; page < allPages.size(); page++)
            {
                System.out.println();

                System.out.println("page: " + page + 1);
                System.out.println(allPages.get(page));

                String category = "";
                String subCategory = "";
                String subSubCategory = "";
                String businessName = "";
                String products = "";
                String profile = "";
                String city = "";
                String imageURL = "";
                String businessType = "";
                String contactPerson = "";
                String address = "";
                String postCode = "";
                String telephone = "";
                String mobile = "";

                document = Jsoup.connect(allPages.get(page)).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();

                //category subCategory subSubCategory

                Elements breadcrumb3 = document.getElementById("breadcrumb3").select("a");

                for (int j = 2; j < breadcrumb3.size(); j++)
                {
                    if (j == 2)
                        category = breadcrumb3.get(j).text();
                    else if (j == 3)
                        subCategory = breadcrumb3.get(j).text();
                    else if (j == 4)
                        subSubCategory = breadcrumb3.get(j).text();
                }
                if (category.equals("Home Supplies"))
                {
                    System.out.println("category: " + category);
                    System.out.println("subCategory: " + subCategory);
                    System.out.println("subSubCategory: " + subSubCategory);

                    // business name
                    businessName = document.getElementById("pcompanyname").text();
                    System.out.println("businessName: " + businessName);

                    // city
                    city = document.getElementsByClass("plocation").text().replace("[", "").replace("]", "").replaceAll(" ", "");
                    System.out.println("city: " + city);

                    if (document.getElementById("supplierlogo") != null)
                    {
                        imageURL = document.getElementById("supplierlogo").attr("src");
                        System.out.println("imageURL: " + imageURL);
                    }

                /*// business type
                businessType = document.getElementsByClass("normal").text().split(" ")[0];
                System.out.println("businessType: " + businessType);

                int indexOfTable1 = 0;
                int indexOfTable2 = 0;
                Elements tables = document.select("table"); //select the all tables.

                for (int table = 0; table < tables.size(); table++)
                {

                    Element table1 = tables.get(table); //select table.
                    String businessTypeValue = table1.select("tr").first().text();
                    String companyValue = table1.select("tr").first().text();
                    if (businessTypeValue.contains("Business"))
                        indexOfTable1 = table;
                    else if (companyValue.contains("Company:"))
                        indexOfTable2 = table;
                    if (indexOfTable1 != 0 && indexOfTable2 != 0)
                        break;
                }

                if (indexOfTable1 != 0)
                {
                    Element table1 = document.select("table").get(indexOfTable1); //select the first table.
                    Elements rows1 = table1.select("tr");

                    // product

                    for (int i = 5; i < rows1.size(); i++)
                    { //first row is the col names so skip it.
                        Element row = rows1.get(i);
                        Elements cols = row.select("td");
                        if (i == 5)
                        {
                            String[] product = cols.get(0).text().split(",");
                            for (int item = 0; item < product.length; item++)
                            {
                                if (item > 11)
                                    break;
                                products += product[item] + ",";
                            }
                            products = products.replace(".,", ".");
                            System.out.println("products: " + products);
                        }
                    }

                    // profile
                    try
                    {
                        String[] profiles = document.getElementById("profile-text").select("p").first().text().substring(0, 300).split(" ");

                        for (String dot : profiles)
                        {
                            if (dot.contains("."))
                            {
                                profile += dot + new String(" ");
                                break;
                            }
                            profile += dot + new String(" ");
                        }
                    }
                    catch (StringIndexOutOfBoundsException e)
                    {
                        profile = document.getElementById("profile-text").select("p").first().text();
                    }
                    //profile = profile.replace(" ", "");
                    //profile = profile.split(".")[0];
                    System.out.println("profile: " + profile);
                }

                // table2

                if (indexOfTable2 != 0)
                {
                    Element table2 = document.select("table").get(indexOfTable2); //select the first table.
                    Elements rows2 = table2.select("tr");

                    if (rows2.size() > 2)
                    {
                        for (int i = 1; i < rows2.size(); i++)
                        { //first row is the col names so skip it.

                            Element row = rows2.get(i);
                            Elements cols = row.select("td");

                            if (i == 1)
                            {
                                // contactPerson
                                contactPerson = cols.get(0).text();
                                System.out.println("contactPerson: " + contactPerson);
                            } else if (i == 2)
                            {
                                // address
                                address = cols.get(0).text();
                                System.out.println("address: " + address);
                            } else if (i == 3)
                            {
                                // postCode
                                postCode = cols.get(0).text();
                                System.out.println("postCode: " + postCode);
                            } else if (i == 4)
                            {
                                // telephone
                                telephone = cols.select("p").first().text();
                                if (telephone.contains("P"))
                                    telephone = telephone.split("P")[0].replaceAll(" ", "");
                                System.out.println("telephone: " + telephone);
                            } else if (i == 5 || i == 6)
                            {
                                if (rows2.size() > 6)
                                {
                                    // mobile
                                    String mobileRow = row.select("p").text();
                                    if (mobileRow.contains("Mobile:"))
                                    {
                                        mobile = mobileRow.split(":")[1].replaceAll(" ", "");
                                        System.out.println("mobile: " + mobile);
                                    }
                                }

                            }
                        }
                    } else
                    {
                        table2 = document.select("table").get(2);
                        rows2 = table2.select("tr");
                        for (int i = 0; i < rows2.size(); i++)
                        { //first row is the col names so skip it.
                            Element row = rows2.get(i);
                            Elements cols = row.select("td");
                            if (i == 0)
                            {
                                businessName = cols.get(0).text();
                                System.out.println("businessName: " + businessName);
                            } else if (i == 1)
                            {

                                contactPerson = cols.get(0).text();
                                System.out.println("contactPerson: " + contactPerson);
                            } else if (i == 2)
                            {

                                address = cols.get(0).text();
                                System.out.println("address: " + address);
                            } else if (i == 3)
                            {

                                postCode = cols.get(0).text();
                                System.out.println("postCode: " + postCode);
                            } else if (i == 4)
                            {

                                telephone = cols.select("p").first().text();
                                if (telephone.contains("P"))
                                    telephone = telephone.split("P")[0].replaceAll(" ", "");
                                System.out.println("telephone: " + telephone);
                            } else if (i == 5 || i == 6)
                            {
                                if (rows2.size() > 6)
                                {
                                    // mobile
                                    String mobileRow = row.select("p").text();
                                    if (mobileRow.contains("Mobile:"))
                                    {
                                        mobile = mobileRow.split(":")[1].replaceAll(" ", "");
                                        System.out.println("mobile: " + mobile);
                                    }
                                }

                            }
                        }
                    }
                }*/
                    //iterating r number of rows

                    //Create a new row in current sheet
                    Row row = sheet.createRow(page + 1);
//Create a new cell in current row
//Set value to new value
                    Cell cell = row.createCell(0);
                /*cell.setCellValue(category);
                cell = row.createCell(1);
                cell.setCellValue(subCategory);
                cell = row.createCell(2);
                cell.setCellValue(subSubCategory);
                cell = row.createCell(3);
                cell.setCellValue(businessName);
                cell = row.createCell(4);
                cell.setCellValue(products);
                cell = row.createCell(5);
                cell.setCellValue(profile);
                cell = row.createCell(6);
                cell.setCellValue(city);
                cell = row.createCell(7);
                cell.setCellValue(businessType);
                cell = row.createCell(8);
                cell.setCellValue(contactPerson);
                cell = row.createCell(9);
                cell.setCellValue(address);
                cell = row.createCell(10);
                cell.setCellValue(postCode);
                cell = row.createCell(11);
                cell.setCellValue(telephone);
                cell = row.createCell(12);
                cell.setCellValue(mobile);*/
                    cell = row.createCell(13);
                    cell.setCellValue(imageURL);
                }
            }
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
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Apparel & Clothing.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Arts & Crafts.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Automotive & Transport.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Business Services.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Business Supplies.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Computer & Software.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Construction & DIY.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Dropshippers & Dropshipping.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Electrical & Lighting.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Electronics & Photo.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Floral & Garden.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Food & Beverages.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Gifts & Giftware.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Health & Beauty.xlsx"));
                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Home Supplies.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Industrial & Materials.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Jewellery & Watches.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Promotional Merchandise.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Publishing & Print.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Security & Protection.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Sports & Leisure.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Surplus & Stocklots.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Telecom & Mobiles.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Textiles & Fabrics.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Toys & Games.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Travel & Outdoors.xlsx"));
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
}