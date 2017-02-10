package com.javarush.test.freelance.parser.LinkedIn.main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Тарас on 11.12.2016.
 */
public class JsonToExcel {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        Workbook workbook = null;
        FileInputStream file = null;

        int count = 1;

        try {

            file = new FileInputStream(new File("D:\\Freelance\\Project Manager\\Companies - LinkedIn.xlsx"));
            workbook = new XSSFWorkbook(file);
//            workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Object obj = parser.parse(new FileReader("D:\\Freelance\\Project Manager\\company-info.json"));

            JSONArray jsonArray =  (JSONArray) obj;

            Iterator i = jsonArray.iterator();

            while (i.hasNext()) {
                JSONObject jsonObject = (JSONObject) i.next();

                String country = (String) jsonObject.get("country");
                String state = (String) jsonObject.get("state");
                String city = (String) jsonObject.get("city");
                String street = (String) jsonObject.get("street");
                String zip = (String) jsonObject.get("zip");
                String industry = (String) jsonObject.get("industry");
                String name = (String) jsonObject.get("name");
                String description = (String) jsonObject.get("description");
                String website = (String) jsonObject.get("website");
                String size = (String) jsonObject.get("size");
                String logo = (String) jsonObject.get("logo");
                String types = (String) jsonObject.get("types");

                //Create a new row in current sheet
                Row row = sheet.createRow(count);
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
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                System.out.println("count: " + count);
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
        }
    }
}
