package com.javarush.test.freelance.parser.EsourcesCoUk;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class EsourcesCoUkInternational
{

    public static void main(String[] args)
    {
        // Apparel & Clothing
        List<String> pages = pages("https://www.esources.co.uk/international-suppliers/1/");
        // Arts & Crafts
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/2/");
        // Automotive & Transport
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/3/");
        // Business Services
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/4/");
        // Business Suppliers
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/5/");
        // Computer & Software
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/6/");
        // Construction & DIY
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/7/");
        // Dropshippers & Dropshipping
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/1082/");
        // Electrical & Lighting
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/8/");
        // Electronics & Photo
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/9/");
        // Floral & Garden
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/10/");
        // Food & Beverages
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/11/");
        // Gifts & Giftware
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/12/");
        // Health & Beauty
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/13/");
        // Home Supplies
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/14/");
        // Industrial & Materials
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/1080/");
        // Jewellery & Watches
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/15/");
        // Promotional Merchandise
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/16/");
        // Publishing & Print
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/17/");
        // Security & Protection
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/18/");
        // Sports & Leisure
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/19/");
        // Surplus & Stocklots
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/20/");
        // Telecom & Mobiles
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/21/");
        // Textiles & Fabrics
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/22/");
        // Toys & Games
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/23/");
        // Travel & Outdoors
//        List<String> pages = pages("http://www.esources.co.uk/international-suppliers/24/");
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
            for (int i = 0; i <= 940; i++)
            // Arts & Crafts
//            for (int i = 0; i <= 166; i++)
            // Automotive & Transport
//            for (int i = 0; i <= 253; i++)
            // Business Services
//            for (int i = 0; i <= 37; i++)
            // Business Suppliers
//            for (int i = 0; i <= 230; i++)
            // Computer & Software
//            for (int i = 0; i <= 364; i++)
            // Construction & DIY
//            for (int i = 0; i <= 270; i++)
            // Dropshippers & Dropshipping
//            for (int i = 0; i <= 251; i++)
            // Electrical & Lighting
//            for (int i = 0; i <= 355; i++)
            // Electronics & Photo
//            for (int i = 0; i <= 306; i++)
            // Floral & Garden
//            for (int i = 0; i <= 149; i++)
            // Food & Beverages
//            for (int i = 0; i <= 370; i++)
            // Gifts & Giftware
//            for (int i = 0; i <= 373; i++)
            // Health & Beauty
//            for (int i = 0; i <= 416; i++)
            // Home Supplies
//            for (int i = 0; i <= 978; i++)
            // Industrial & Materials
//            for (int i = 0; i <= 442; i++)
            // Jewellery & Watches
//            for (int i = 0; i <= 384; i++)
            // Promotional Merchandise
//            for (int i = 0; i <= 125; i++)
            // Publishing & Print
//            for (int i = 0; i <= 231; i++)
            // Security & Protection
//            for (int i = 0; i <= 137; i++)
            // Sports & Leisure
//            for (int i = 0; i <= 268; i++)
            // Surplus & Stocklots
//            for (int i = 0; i <= 126; i++)
            // Telecom & Mobiles
//            for (int i = 0; i <= 115; i++)
            // Telecom & Mobiles
//            for (int i = 0; i <= 135; i++)
            // Toys & Games
//            for (int i = 0; i <= 288; i++)
            // Travel & Outdoors
//            for (int i = 0; i <= 210; i++)
//            for (int i = 0; i < 1; i++)
            {
                try
                {
                    page = Jsoup.connect(link).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                }
                catch (HttpStatusException hse)
                {
                    System.out.println("HttpStatusException with link: " + link);
                    // try connect again
                    i--;
                    continue;
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + link);
                    // try connect again
                    i--;
                    continue;
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + link);
                    // try connect again
                    i--;
                    continue;
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + link);
                    // try connect again
                    i--;
                    continue;
                }
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
            for (int i = 0; i < pages.size(); i++)
            {
                try
                {
                    document = Jsoup.connect(pages.get(i)).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                }
                catch (HttpStatusException hse)
                {
                    System.out.println("HttpStatusException with link: " + pages.get(i));
                    // try connect again
                    i--;
                    continue;
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + pages.get(i));
                    // try connect again
                    i--;
                    continue;
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + pages.get(i));
                    // try connect again
                    i--;
                    continue;
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + pages.get(i));
                    // try connect again
                    i--;
                    continue;
                }
                Elements hrefs = document.getElementsByClass("sch2").select("a");
                for (Element href : hrefs)
                {
                    String link = href.attr("href");
                    allPages.add(link);
                }
                //}
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

    public static void createAndSaveExcelSheet(List<String> allPages)
    {
        Workbook workbook = null;
        FileInputStream file = null;
        int count = 0;
        try
        {
            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Apparel & Clothing.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Arts & Crafts.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Automotive & Transport.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Business Services.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Business Supplies.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Computer & Software.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Construction & DIY.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Dropshippers & Dropshipping.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Electrical & Lighting.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Electronics & Photo.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Floral & Garden.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Food & Beverages.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Gifts & Giftware.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Health & Beauty.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Home Supplies.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Industrial & Materials.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Jewellery & Watches.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Promotional Merchandise.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Publishing & Print.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Security & Protection.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Sports & Leisure.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Surplus & Stocklots.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Telecom & Mobiles.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Textiles & Fabrics.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Toys & Games.xlsx"));
//            file = new FileInputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Travel & Outdoors.xlsx"));
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Document document;

            for (int page = 0; page < allPages.size(); page++)
            {
                System.out.println();
                System.out.println(allPages.get(page));

                String category = "";
                String subCategory = "";
                String subSubCategory = "";
                String businessName = "";
                String products = "";
                String profile = "";
                String country = "";
                String city = "";
                String imageURL = "";
                String businessType = "";
                String contactPerson = "";
                String address = "";
                String postCode = "";
                String telephone = "";
                String mobile = "";

                try
                {
                    document = Jsoup.connect(allPages.get(page)).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
                }
                catch (HttpStatusException hse)
                {
                    System.out.println("HttpStatusException with link: " + allPages.get(page));
                    // try connect again
                    page--;
                    continue;
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + allPages.get(page));
                    // try connect again
                    page--;
                    continue;
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + allPages.get(page));
                    // try connect again
                    page--;
                    continue;
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + allPages.get(page));
                    // try connect again
                    page--;
                    continue;
                }

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
                if (category.equals("Apparel & Clothing"))
                {
                    count++;
                    System.out.println("category: " + category);
                    System.out.println("subCategory: " + subCategory);
                    System.out.println("subSubCategory: " + subSubCategory);

                    // business name
                    businessName = document.getElementById("pcompanyname").text();
                    System.out.println("businessName: " + businessName);

                    // city
                    city = document.getElementsByClass("plocation").text().replace("[", "").replace("]", "").replaceAll(" ", "").split(",")[0];
                    country = document.getElementsByClass("plocation").text().replace("[", "").replace("]", "").replaceAll(" ", "").split(",")[1];
                    System.out.println("city: " + city);

                    if (document.getElementById("supplierlogo") != null)
                    {
                        imageURL = document.getElementById("supplierlogo").attr("src");
                        System.out.println("imageURL: " + imageURL);
                    }

                    // business type
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
                    }
                    //iterating r number of rows

                    //Create a new row in current sheet
                    Row row = sheet.createRow(count);
                    //Create a new cell in current row
                    Cell cell = row.createCell(0);
                    //Set value to new value
                    cell.setCellValue(category);
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
                    cell.setCellValue(country);
                    cell = row.createCell(8);
                    cell.setCellValue(businessType);
                    cell = row.createCell(9);
                    cell.setCellValue(contactPerson);
                    cell = row.createCell(10);
                    cell.setCellValue(address);
                    cell = row.createCell(11);
                    cell.setCellValue(postCode);
                    cell = row.createCell(12);
                    cell.setCellValue(telephone);
                    cell = row.createCell(13);
                    cell.setCellValue(mobile);
                    cell = row.createCell(14);
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
                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Apparel & Clothing.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Arts & Crafts.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Automotive & Transport.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Business Services.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Business Supplies.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Computer & Software.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Construction & DIY.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Dropshippers & Dropshipping.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Electrical & Lighting.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Electronics & Photo.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Floral & Garden.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Food & Beverages.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Gifts & Giftware.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Health & Beauty.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Home Supplies.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Industrial & Materials.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Jewellery & Watches.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Promotional Merchandise.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Publishing & Print.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Security & Protection.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Sports & Leisure.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Surplus & Stocklots.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Telecom & Mobiles.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Textiles & Fabrics.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Toys & Games.xlsx"));
//                outFile = new FileOutputStream(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Travel & Outdoors.xlsx"));
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

