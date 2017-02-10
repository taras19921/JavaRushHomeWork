package com.javarush.test.freelance.parser.LinkedIn.main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Тарас on 12.12.2016.
 */
public class MySQLToExcel
{

    public static void main(String[] args)
    {
        mySQLToExcel(0, 2000);
    }

    public static void mySQLToExcel(int countFor, int countTo)
    {

        Workbook workbook;
        FileInputStream file;
        java.sql.Connection conn;

        String urlDB = "jdbc:mysql://localhost:3306/linkedin-companies";
        String user = "root";
        String pass = "root";

        boolean isData;
        boolean isTrue = true;

        try
        {
            while (isTrue)
            {
            file = new FileInputStream(new File("D:\\Freelance\\Project Manager\\Companies - LinkedIn.xlsx"));
            workbook = WorkbookFactory.create(file);
//            workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            Statement stmt = conn.createStatement();

            ResultSet rs;
//            String getAllCompaniesInfoQuery = "SELECT * FROM `company-info` WHERE `id` <= 50000 ORDER BY `id`;";

                isData = false;
                String getAllCompaniesInfoQuery = "SELECT * FROM `company-info` WHERE `id` > " + countFor
                        + " AND `id` < " + countTo + " ORDER BY `id`;";

                rs = stmt.executeQuery(getAllCompaniesInfoQuery);

                while (rs.next())
                {
                    isData = true;
                    String country = rs.getString("country");
                    String state = rs.getString("state");
                    String city = rs.getString("city");
                    String street = rs.getString("street");
                    String zip = rs.getString("zip");
                    String industry = rs.getString("industry");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String website = rs.getString("website");
                    String size = rs.getString("size");
                    String logo = rs.getString("logo");
                    String types = rs.getString("types");

                    //Create a new row in current sheet
                    Row row = sheet.createRow(countFor + 1);
//Create a new cell in current row
//Set value to new value
                    Cell cell = row.createCell(0);
                    cell.setCellValue(country);
                    cell = row.createCell(1);
                    cell.setCellValue(state);
                    cell = row.createCell(2);
                    cell.setCellValue(city);
                    cell = row.createCell(3);
                    cell.setCellValue(street);
                    cell = row.createCell(4);
                    cell.setCellValue(zip);
                    cell = row.createCell(5);
                    cell.setCellValue(industry);
                    cell = row.createCell(6);
                    cell.setCellValue(name);
                    cell = row.createCell(7);
                    cell.setCellValue(description);
                    cell = row.createCell(8);
                    cell.setCellValue(website);
                    cell = row.createCell(9);
                    cell.setCellValue(size);
                    cell = row.createCell(10);
                    cell.setCellValue(logo);
                    cell = row.createCell(11);
                    cell.setCellValue(types);

                    countFor++;
                }
                if (isData)
                {
                    if (((countFor + 1) % 2000) == 0)  {
                        System.out.println("countFor: " + countFor);
                        countTo += 2000;

                        FileOutputStream outFile;
                        conn.close();
                        file.close();
//
                        outFile = new FileOutputStream(new File("D:\\Freelance\\Project Manager\\Companies - LinkedIn.xlsx"));
                        workbook.write(outFile);
                        outFile.close();
                    } else {
                        countTo += 2000;
                        System.out.println("countFor: " + countFor);
                    }
                } else
                {
                    isTrue = false;
                    System.out.println("countFor: " + countFor);
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
        /*finally
        {
            FileOutputStream outFile;
            try
            {
                System.out.println("count: " + countFor);
                conn.close();
                file.close();
//
                outFile = new FileOutputStream(new File("D:\\Freelance\\Project Manager\\Companies - LinkedIn.xlsx"));
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
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }*/
    }
}
