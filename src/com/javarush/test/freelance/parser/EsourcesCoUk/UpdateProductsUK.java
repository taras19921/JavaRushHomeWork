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

public class UpdateProductsUK
{
    public static final String urlDB = "jdbc:mysql://localhost:3306/esources";
    public static final String user = "root";
    public static final String pass = "root";
    public static java.sql.Connection conn;
    public static Statement stmt;

    public static void main(String[] args)
    {
        seveProducts(new File("D:\\Freelance\\Lowcostseo\\UK\\WS UK - Health & Beauty.xlsx"));
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
            int i = 11172;
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String category = row.getCell(0).toString();
                if (category.equals("Health & Beauty"))
                {
                    i++;
                    String imageURL = row.getCell(13).toString();
                    String updateProduct = "UPDATE `product_uk` SET `image_url` = '" + imageURL + "' WHERE `product_uk`.`id` = " + i + ";";
                    stmt.executeUpdate(updateProduct);

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

