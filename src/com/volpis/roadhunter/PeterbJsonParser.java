package com.volpis.roadhunter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Тарас on 12.01.2017.
 */
public class PeterbJsonParser
{
    public static void main(String[] args) {

//        peterbJsonParser(new File("D:\\volpis\\roadhunter\\files\\json\\peterb.json"));
//        kenworthJsonParser(new File("D:\\volpis\\roadhunter\\files\\json\\kenworth.json"));
//        freightlinersJsonParser(new File("D:\\volpis\\roadhunter\\files\\json\\freightliners.json"));
        volvoJsonParser(new File("D:\\volpis\\roadhunter\\files\\json\\volvo.json"));
//        repair_shopsExcelParser(new File("D:\\volpis\\roadhunter\\files\\json\\repair_shops.xlsx"));
    }

    public static void repair_shopsExcelParser(File fileExcel) {

        JSONParser parser = new JSONParser();
        try
        {
            Workbook workbook = new XSSFWorkbook(fileExcel);

            Sheet sheet = workbook.getSheetAt(0);
            if (workbook != null) {
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    // Company name
                    String name = row.getCell(0).toString();
                    // Coordinates
                    Double lon = toDoubleNumber(row.getCell(2).toString());
                    Double lat = toDoubleNumber(row.getCell(3).toString());

                    // Phone number
                    String phone = row.getCell(4).toString().replaceAll("(\r\n|\n)", "");

                    String address = row.getCell(1).toString().replaceAll("(\r\n|\n)", "");
                    System.out.println();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void volvoJsonParser(File fileJSON) {

        JSONParser parser = new JSONParser();
        try
        {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileJSON));
            JSONObject countries = (JSONObject) jsonObject.get("countries");
            JSONObject us = (JSONObject) countries.get("US");
            JSONObject states = (JSONObject) us.get("states");

            String companyName = "volvo";
            Set statesSet = states.entrySet();
            Object[] statesArray = statesSet.toArray();
            for (int j = 0; j < statesArray.length; j++) {
                Map.Entry stateObject = (Map.Entry) statesArray[j];
                JSONObject dealersObject = (JSONObject) stateObject.getValue();
                JSONObject dealers = (JSONObject) dealersObject.get("dealers");
                if (dealers != null && dealers.size() != 0) {
                    Set dealersSet = dealers.entrySet();
                    Object[] dealersArray = dealersSet.toArray();
                    for (int k = 0; k < dealersArray.length; k++) {
                        Map.Entry dealerObject = (Map.Entry) dealersArray[k];
                        JSONObject dealer = (JSONObject) dealerObject.getValue();
                        // Company name
                        String name = dealer.get("COMPANY_DBA_NAME").toString();
                        // Coordinates
                        Double lon = toDoubleNumber(dealer.get("MAIN_LONGITUDE").toString());
                        Double lat = toDoubleNumber(dealer.get("MAIN_LATITUDE").toString());
                        // Phone number
                        String phone = dealer.get("REG_PHONE_NUMBER").toString();
                        // Fax number
                        String fax = "";

                        // City table
                        String address = dealer.get("MAIN_ADDRESS_LINE_1_TXT").toString();
                        String cityName = dealer.get("MAIN_CITY_NM").toString();
                        String state = dealer.get("MAIN_STATE_PROV_CD").toString();
                        String country = "US";
                        String zipCode = dealer.get("MAIN_POSTAL_CD").toString();
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void freightlinersJsonParser (File fileJSON) {

        JSONParser parser = new JSONParser();
        try
        {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(fileJSON));
            String companyName = "freightliners";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String phone = "";
                String fax = "";

                // Company name
                String name = jsonObject.get("Name").toString();
                // Coordinates
                Double lat = toDoubleNumber(jsonObject.get("Latitude").toString());
                Double lon = toDoubleNumber(jsonObject.get("Longitude").toString());
                JSONObject service = (JSONObject) jsonObject.get("Service");
                if (service != null) {
                    JSONArray fields = (JSONArray) service.get("Fields");
                    JSONObject fieldsObj = (JSONObject) fields.get(0);
                    // Phone number
                    phone = fieldsObj.get("Phone").toString();
                    // Fax number
                    fax = fieldsObj.get("Fax").toString();
                }
                // City table
                String cityName = jsonObject.get("City").toString();
                String state = jsonObject.get("State").toString();
                String zipCode = jsonObject.get("Zip").toString();
                System.out.println();
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void kenworthJsonParser (File fileJSON) {

        JSONParser parser = new JSONParser();
        try
        {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileJSON));
            JSONArray jsonArray = (JSONArray) jsonObject.get("dealers");
            String companyName = "kenworth";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                JSONObject obj = (JSONObject) object.get("obj");

                // Company name
                String name = obj.get("name").toString();
                // Coordinates
                JSONObject loc = (JSONObject) obj.get("loc");
                JSONArray coordinates = (JSONArray) loc.get("coordinates");
                Double lon = toDoubleNumber(coordinates.get(0).toString());
                Double lat = toDoubleNumber(coordinates.get(1).toString());
                // Phone number
                String phone = obj.get("phone").toString();
                // Fax number
                String fax = "";

                // City table
                String address = obj.get("address1").toString();
                String cityName = obj.get("city").toString();
                String state = obj.get("state").toString();
                String country = obj.get("country").toString();
                String zipCode = obj.get("zipcode").toString();
                System.out.println();
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
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void peterbJsonParser (File fileJSON) {

        JSONParser parser = new JSONParser();
        try
        {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(fileJSON));
            String companyName = "peterb";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                // Company name
                String name = jsonObject.get("Name").toString();
                // Coordinates
                Double lat = Double.parseDouble(jsonObject.get("Latitude").toString());
                Double lon = Double.parseDouble(jsonObject.get("Longitude").toString());
                // Phone number
                String phone = jsonObject.get("Phone").toString();
                // Fax number
                String fax = jsonObject.get("Fax").toString();

                // City table
                String address = jsonObject.get("AddressLine1").toString();
                String city = jsonObject.get("City").toString();
                String state = jsonObject.get("State").toString();
                String zip = jsonObject.get("PostalCode").toString();
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
    }

    private static Double toDoubleNumber(String row) {
        Double doubleNumber;
        if (row.equals(""))
            return null;
        try {
            doubleNumber = Double.parseDouble(row);
        } catch (Exception e) {
            return null;
        }
        return doubleNumber;
    }
}
