package com.javarush.test.freelance.parser.EsourcesCoUk;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class ProductsInternational
{
    public static final String urlDB = "jdbc:mysql://localhost:3306/esources";
    public static final String user = "root";
    public static final String pass = "root";
    public static java.sql.Connection conn;
    public static Statement stmt;

    public static void main(String[] args)
    {
        seveProducts(new File("D:\\Freelance\\Lowcostseo\\International\\WS International - Travel & Outdoors.xlsx"));
    }

    public static void seveProducts(File file)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String category = row.getCell(0).toString();
                if (category.equals("Travel & Outdoors"))
                {
                    category = category.replaceAll("&", "and");
                    String subCategory = row.getCell(1).toString().replaceAll("&", "and");;
                    String subSubCategory = row.getCell(2).toString().replaceAll("&", "and");;

                    String businessName = "";
                    String products = "";
                    String profile = "";
                    String city = "";
                    String country = "";
                    String imageURL = "";
                    String businessType = "";
                    String contactPerson = "";
                    String address = "";
                    String postCode = "";
                    String telephone = "";
                    String mobile = "";

                    businessName = row.getCell(3).toString().replaceAll("'", "");
                    products = row.getCell(4).toString().replaceAll("'", "");
                    profile = row.getCell(5).toString().replaceAll("'", "");
                    city = row.getCell(6).toString().replaceAll("'", "");
                    country = row.getCell(7).toString().replaceAll("'", "");
                    businessType = row.getCell(8).toString().replaceAll("'", "");
                    contactPerson = row.getCell(9).toString().replaceAll("'", "");
                    address = row.getCell(10).toString().replaceAll("'", "");
                    postCode = row.getCell(11).toString().replaceAll("'", "");
                    telephone = row.getCell(12).toString().replaceAll("'", "");
                    mobile = row.getCell(13).toString().replaceAll("'", "");
                    imageURL = row.getCell(14).toString();

                    String insertProduct = "INSERT INTO `product_international` (`name`, `products`, `profile`, `city`,  `country`, `business_type`," +
                            " `contact_person`, `address`, `post_code`, `telephone`, `mobile`," +
                            " `image_url`, `category_title`, `sub_category_title`, `sub_sub_category_title`) VALUES (";
                    insertProduct += "'" + businessName + "', '" + products + "', '" + profile + "', '" + city + "', '" + country + "', '" + businessType + "', '" + contactPerson + "', '"
                            + address + "', '" + postCode + "', '" + telephone + "', '" + mobile + "', '" + imageURL + "', '"
                            + category + "', '" + subCategory + "', '" + subSubCategory + "');";
                    stmt.executeUpdate(insertProduct);
                }
            }
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (InvalidFormatException e)
        {
            e.printStackTrace();
        }
    }
}
