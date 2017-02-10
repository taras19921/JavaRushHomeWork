package com.javarush.test.freelance.parser.project_manager_amazon;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AmazonParser
{

    private static final String FILE_FORMAT_XLSX = ".xlsx";
    private static final String FILE_FORMAT_XLS = ".xls";

    public static List<Product> products = new ArrayList<>();
    public static int count = 1;

    public static List<Product> parseAllPages(String url)
    {
        String last_product_url = "";

        try
        {
            try
            {
                System.out.println("url: " + url);
                Connection connection = Jsoup.connect(url)
                        .timeout(12000 * 100000)
                        /*.
                        method(Connection.Method.GET)
                        .execute()*/;
                Connection.Response response = connection.execute();
                HashMap<String, String> cookies = (HashMap<String, String>) response.cookies();
                Document page = null;
                if (response.statusCode() == 200)
                {
                    page = connection.cookies(cookies).get();
//                Element id1 = page.getElementById("atfResults")/*.select("table").first()*/;
                    Element atfResults = page.getElementById("atfResults").getElementById("s-results-list-atf");
//                Elements results = atfResults.getElementsByClass("s-result-item s-result-card-for-container s-carded-grid celwidget ");

                    if (atfResults != null)
                    {
                        int i = 0;
                        while (true)
                        {
                            Element result = atfResults.getElementById("result_" + i);
                            if (result != null)
                            {
                                Element href = result.getElementsByClass("s-item-container").first().getElementsByClass("a-text-normal").first()/*.getElementsByClass("a-column a-span12 a-text-left").first()*/;
                                String productUrl = href.attr("href");
                                System.out.println("link: " + productUrl);
                                products.add(parseAndSaveProductData(productUrl));
                                i++;
                                last_product_url = productUrl;
                            } else
                            {
                                // next page
                                Element nextPageId = page.getElementById("pagnNextLink");
                                if (nextPageId != null)
                                {
                                    String nextPage = nextPageId.attr("href");
                                    if (nextPage != null && !nextPage.isEmpty())
                                    {
                                        parseAllPages(nextPage);
                                        System.out.println("nextPage: " + nextPage);
                                    }
                                } else
                                    return products;
                            }
                        }
                    }
                }
            }
            catch (HttpStatusException hse)
            {
                System.out.println("HttpStatusException with link: " + url);
                // try connect again
                parseAllPages(url);
            }
            catch (SocketTimeoutException ste)
            {
                System.out.println("SocketTimeoutException with link: " + url);
                // try connect again
                parseAllPages(url);
            }
            catch (SocketException se)
            {
                System.out.println("SocketException with link: " + url);
                // try connect again
                parseAllPages(url);
            }
            catch (UnknownHostException uhe)
            {
                System.out.println("UnknownHostException with link: " + url);
                // try connect again
                parseAllPages(url);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("last_product_url: " + last_product_url);

        }
        return products;
    }

    public static Product parseAndSaveProductData(String productUrl)
    {
        Product product = new Product();
        try
        {
            Connection.Response response = null;

            String subCategory = "";
            String productTitle = "";
            String brandTitle = "";
            String priceTitle = "";
            List<String> descriptionTitleList = new ArrayList<>();
            List<String> imageUrls = new ArrayList<>();
            List<String> bulletPoints;

            try
            {
                response = Jsoup.connect(productUrl)
                        .timeout(12000 * 100000)//12000 sec
                        .method(Connection.Method.POST)
                        .execute();
            }
            catch (HttpStatusException hse)
            {
                System.out.println("HttpStatusException with productUrl: " + productUrl);
                // try again
                parseAndSaveProductData(productUrl);
            }
            catch (SocketTimeoutException ste)
            {
                System.out.println("SocketTimeoutException with productUrl: " + productUrl);
                // try again
                parseAndSaveProductData(productUrl);
            }
            catch (SocketException se)
            {
                System.out.println("SocketException with productUrl: " + productUrl);
                // try again
                parseAndSaveProductData(productUrl);
            }
            catch (UnknownHostException uhe)
            {
                System.out.println("UnknownHostException with productUrl: " + productUrl);
                // try again
                parseAndSaveProductData(productUrl);
            }

            if (response != null)
            {
                Document page = response.parse();
                try
                {
                    Element result = page.getElementById("wayfinding-breadcrumbs_feature_div");
                    if (result != null)
                    {
                        Elements a_list_item = result.getElementsByClass("a-list-item");
                        int count = 0;
                        if (a_list_item != null && a_list_item.size() != 0)
                        {
                            // Product subcategory
                            Element subCategoryDiv = a_list_item.get(a_list_item.size() - 1);
                            Element subCategoryHref = subCategoryDiv.getElementsByTag("a").first();
                            if (subCategoryHref != null)
                                subCategory = subCategoryDiv.getElementsByTag("a").first().text();
                            product.setSubCategory(subCategory);
                            System.out.println("subCategory: " + subCategory);

                            // Product title
                            Element productId = page.getElementById("productTitle");
                            if (productId != null)
                            {
                                productTitle = productId.text();
                                System.out.println("productTitle:" + productTitle);
                                product.setProductTitle(productTitle);
                            }

                            // Brand title
                            Element brandId = page.getElementById("brand");
                            if (brandId != null)
                            {
                                brandTitle = brandId.getElementsByTag("a").first().text();
                                product.setBrandTitle(brandTitle);
                                System.out.println("brandTitle:" + brandTitle);
                            }

                            // Price title
                            Element priceId = page.getElementById("snsPrice");
                            if (priceId != null)
                            {
                                Elements divPrice = priceId.getElementsByClass("a-size-large");
                                if (divPrice != null && divPrice.size() != 0)
                                {
                                    priceTitle = divPrice.get(divPrice.size() - 1).text();
                                    product.setPriceTitle(priceTitle);
                                    System.out.println("priceTitle:" + priceTitle);
                                }
                            }

                            // Description title
                            Element descriptionId = page.getElementById("fbExpandableSectionContent");
                            if (descriptionId != null)
                            {
                                Elements divDesc = descriptionId.getElementsByClass("a-vertical");
                                if (divDesc != null && divDesc.size() != 0)
                                {

                                    Elements classDesc = divDesc.first().getElementsByClass("showHiddenFeatureBullets");
                                    if (classDesc != null && classDesc.size() != 0)
                                    {
                                        for (int i = 0; i < classDesc.size(); i++)
                                        {
                                            Elements spanDesc = classDesc.get(i).getElementsByTag("span");
                                            if (spanDesc != null && spanDesc.size() != 0)
                                            {
                                                descriptionTitleList.add(spanDesc.first().text());
                                                System.out.println("descriptionTitle:" + descriptionTitleList);
                                            }
                                            product.setDescriptionTitle(descriptionTitleList);
                                        }
                                    }
                                }
                            }

                            // Images
                            Element imagesId = page.getElementById("altImages");
                            Elements buttonText = imagesId.getElementsByClass("a-button-text");
                            if (buttonText != null && buttonText.size() != 0)
                            {
                                for (int i = 0; i < 3; i++)
                                {
                                    Elements srcAttr = buttonText.get(i).getElementsByAttribute("src");
                                    if (srcAttr != null && srcAttr.size() != 0)
                                    {
                                        for (Element src : srcAttr)
                                        {
                                            String image = src.attr("src");
                                            if (image != null && !image.isEmpty())
                                            {
                                                imageUrls.add(image);
                                                System.out.println(imageUrls);
                                                break;
                                            }
                                        }
                                    }
                                }
                                product.setImageUrls(imageUrls);
                            }
                            createAndSaveExcelSheet(product);
                            count++;
                        }
                    }
                }
                catch (NullPointerException npe)
                {
                    System.out.println("NullPointerException with productUrl: " + productUrl);
                    parseAndSaveProductData(productUrl);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
//        products.add(product);
        return product;
    }

    public static void main(String[] args)
    {
        // Apparel & Clothing
        List<Product> links = parseAllPages("https://www.amazon.com/s/ref=sr_nr_n_4?fst=as%3Aoff&rh=n%3A3760911%2Cp_72%3A1248874011%2Cp_36%3A100-50000%2Cn%3A%2111055981%2Cn%3A10079992011%2Cn%3A14255252011&bbn=10079992011&ie=UTF8&qid=1478498946&rnid=10079992011");
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
//        saveToSCV(allPages);
    }

    public static void createAndSaveExcelSheet(Product product)
    {
        Workbook workbook = null;
        FileInputStream file = null;
        try
        {
//
            file = new FileInputStream(new File("D:\\Freelance\\Project Manager\\DRY-MOUTH-PRODUCT.xlsx"));
            workbook = new XSSFWorkbook(file);
//            workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            System.out.println("count: " + count);
            //iterating r number of rows

            //Create a new row in current sheet
            Row row = sheet.createRow(count);
//Create a new cell in current row
//Set value to new value
            Cell cell = row.createCell(0);
            cell.setCellValue(product.getSubCategory());
            cell = row.createCell(1);
            cell.setCellValue(product.getProductTitle());
            cell = row.createCell(2);
            cell.setCellValue(product.getBrandTitle());
            cell = row.createCell(3);
            cell.setCellValue(product.getPriceTitle());
            cell = row.createCell(4);
            String description = "";
            for (String desc : product.getDescriptionTitle())
                description += desc;
            cell.setCellValue(description);
            int i = 1;
            for (String image : product.getImageUrls())
            {
                cell = row.createCell(4 + i);
                String imageURL = "";
                if (image != null && !image.isEmpty())
                    imageURL += image;
                cell.setCellValue(imageURL);
                i++;
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
//
                outFile = new FileOutputStream(new File("D:\\Freelance\\Project Manager\\DRY-MOUTH-PRODUCT.xlsx"));
                workbook.write(outFile);
                outFile.close();
                count++;
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
