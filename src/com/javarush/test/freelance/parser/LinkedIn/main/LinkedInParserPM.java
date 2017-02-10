package com.javarush.test.freelance.parser.LinkedIn.main;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.exceptions.OAuthParametersMissingException;
import org.scribe.exceptions.OAuthSignatureException;

import java.io.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkedInParserPM
{

    public static Connection.Response response;
    public static List<String> companiesByIndustryUrls = new ArrayList<>();
    public static List<String> companiesByCityUrls = new ArrayList<>();
    public static List<String> companiesUrls = new ArrayList<>();
    public static List<Company> companiesInfo = new ArrayList<>();
    public static int count = 0;

    public static void main(String[] args) throws IOException
    {
//        List<String> companiesByIndustry = companiesByIndustry("https://www.linkedin.com/directory/companies/");
//        List<String> companiesByCityUrls = companiesByCityUrls(companiesByIndustry);
//        List<String> companiesUrls = companiesUrls(companiesByCityUrls);
        List<Company> companiesInfo = companiesInfo(/*companiesUrls*/);
        createAndSaveExcelSheet(companiesInfo);
//        companies();
        System.out.println("Done!");
    }

    public static void saveCompaniesInfoFromAPI(int id, PreparedStatement preparedStmt)
    {
        List<String> types = new ArrayList<>();
        try
        {
            String url = "https://api.linkedin.com/v1/companies/" + id + ":(id,name,website-url,logo-url,employee-count-range,industries,specialties,locations,founded-year,ticker,description,company-type)?oauth2_access_token=AQWsgT3KkmNFMT1YPr9ThiWZeMrzf9sL-nYbNpTp8RDVmht-r7n2-LgM4oA85iZIfZGop74zdbki9Pfl8WWMez6rjSGsiDRHz9GXrzFpUEwTbKkRiMRb1En41A_oYx6NAGEwzFx-2mfS9gGW-WoXmm62SpC-tuo5kD8H9QIlih4yPFsQXHI&format=json";
            OAuthRequest request = new OAuthRequest(Verb.GET, url);
            Response response = request.send();
            JSONParser jsonParser = new JSONParser();
            try
            {
                JSONObject jsonObjectCompanyInfo = (JSONObject) jsonParser.parse(response.getBody());
                Long errorCode = (Long) jsonObjectCompanyInfo.get("errorCode");
                if (errorCode == null)
                {
//                    Location
                    JSONObject jsonObjectCompanyLocation = (JSONObject) jsonObjectCompanyInfo.get("locations");
                    JSONArray locations = (JSONArray) jsonObjectCompanyLocation.get("values");

                    JSONObject locationObj = (JSONObject)locations.get(0);

                    String city = (String) locationObj.get("city");
                    if (city == null)
                        city = "";
                    String zip = (String) locationObj.get("postalCode");
                    if (zip == null)
                        zip = "";
                    String street = (String) locationObj.get("street1");
                    if (street == null)
                        street = "";

                    // get a String from the JSON object
                    String country = (String) jsonObjectCompanyLocation.get("country");
                    if (country == null)
                        country = "";
                    String state = (String) jsonObjectCompanyLocation.get("state");
                    if (state == null)
                        state = "";
                    String companyName = (String) jsonObjectCompanyInfo.get("companyName");
                    if (companyName == null)
                        companyName = "";
                    String description = (String) jsonObjectCompanyInfo.get("description");
                    if (description == null)
                        description = "";
                    String industry = (String) jsonObjectCompanyInfo.get("industry");
                    if (industry == null)
                        industry = "";
                    String website = (String) jsonObjectCompanyInfo.get("website");
                    if (website == null)
                        website = "";
                    String size = (String) jsonObjectCompanyInfo.get("size");
                    if (size == null)
                        size = "";
                    String logo = (String) jsonObjectCompanyInfo.get("legacyLogo");
                    if (logo != null)
                        logo = "https://media.licdn.com/media" + logo;
                    else
                    {
                        logo = (String) jsonObjectCompanyInfo.get("legacySquareLogo");
                        if (logo != null)
                            logo = "https://media.licdn.com/media" + logo;
                        else
                            logo = "";
                    }
                    // get an array from the JSON object
                    JSONArray typesSpecialties = (JSONArray) jsonObjectCompanyInfo.get("specialties");
                    // take the elements of the json array
                    String type = "";
                    if (typesSpecialties != null && typesSpecialties.size() != 0)
                    {
                        for (int j = 0; j < typesSpecialties.size(); j++)
                            types.add(typesSpecialties.get(j).toString());
                    }
                    if (types != null && types.size() != 0)
                    {
                        for (int j = 0; j < types.size(); j++)
                        {

                            if (j == companiesInfo.size() - 2)
                                type += types.get(j);

                            type += types.get(j) + ", ";
                        }
                    }
                    String insertIntoCompanyIfoQuery = "INSERT INTO `company-info` (`country`, `state`, `city`, `street`," +
                            " `zip`, `industry`, `name`, `description`, `website`," +
                            " `size`, `logo`, `types`) VALUES (";
                    insertIntoCompanyIfoQuery += "'" + country + "', '" + state + "', '" + city + "', '" + street + "', '" + zip + "', '" + industry + "', '"
                            + companyName + "', '" + description + "', '" + website + "', '" + size + "', '" + logo + "', '"
                            + type + "');";
                    try
                    {
                        preparedStmt.setString(1, country);
                        preparedStmt.setString(2, state);
                        preparedStmt.setString(3, city);
                        preparedStmt.setString(4, street);
                        preparedStmt.setString(5, zip);
                        preparedStmt.setString(6, industry);
                        preparedStmt.setString(7, companyName);
                        preparedStmt.setString(8, description);
                        preparedStmt.setString(9, website);
                        preparedStmt.setString(10, size);
                        preparedStmt.setString(11, logo);
                        preparedStmt.setString(12, type);
                        preparedStmt.execute();
                        count++;
                        if (count % 1000 == 0)
                            System.out.println("company count: " + count);
//                                    Statement stmt = conn.createStatement();
//                                    stmt.executeUpdate(insertIntoCompanyIfoQuery);
                    }
                    catch (MySQLSyntaxErrorException mysql)
                    {
                    }
                    catch (SQLException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }
            catch (ParseException e1)
            {
                e1.printStackTrace();
            }

        }
        catch (OAuthParametersMissingException e)
        {
            e.printStackTrace();
        }
        catch (OAuthSignatureException e)
        {
            e.printStackTrace();
        }
        catch (OAuthException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Company> companies()
    {
        String urlDB = "jdbc:mysql://localhost:3306/linkedin-companies";
        String user = "root";
//        String pass = "ryna@0812";
        String pass = "root";
        List<String> urls = new ArrayList<>();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(urlDB, user, pass);

            String query = " INSERT INTO `company-info` (`country`, `state`, `city`, `street`," +
                    " `zip`, `industry`, `name`, `description`, `website`," +
                    " `size`, `logo`, `types`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(query);

            for (int i = 2000000; i < 15000000; i++)
                saveCompaniesInfoFromAPI(i, preparedStmt);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        return companiesInfo;
    }

    public static void createAndSaveExcelSheet(List<Company> companiesInfo)
    {
        System.out.println("companiesInfo.size()" + companiesInfo.size());
        Workbook workbook = null;
        FileInputStream file = null;
        try
        {
//
            file = new FileInputStream(new File("D:\\Freelance\\Project Manager\\Companies.xlsx"));
            workbook = new XSSFWorkbook(file);
//            workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            //iterating r number of rows

            for (int i = 0; i < companiesInfo.size(); i++)
            {

                //Create a new row in current sheet
                Row row = sheet.createRow(i + 1);
//Create a new cell in current row
//Set value to new value
                Cell cell = row.createCell(0);
                cell.setCellValue(companiesInfo.get(i).getCountry());
                cell = row.createCell(1);
                cell.setCellValue(companiesInfo.get(i).getState());
                cell = row.createCell(2);
                cell.setCellValue(companiesInfo.get(i).getCity());
                cell = row.createCell(3);
                cell.setCellValue(companiesInfo.get(i).getStreet());
                cell = row.createCell(4);
                cell.setCellValue(companiesInfo.get(i).getZip());
                cell = row.createCell(5);
                cell.setCellValue(companiesInfo.get(i).getIndustry());
                cell = row.createCell(6);
                cell.setCellValue(companiesInfo.get(i).getCompanyName());
                cell = row.createCell(7);
                cell.setCellValue(companiesInfo.get(i).getDescription());
                cell = row.createCell(8);
                cell.setCellValue(companiesInfo.get(i).getWebsite());
                cell = row.createCell(9);
                cell.setCellValue(companiesInfo.get(i).getSize());
                cell = row.createCell(10);
                cell.setCellValue(companiesInfo.get(i).getLogo());
                String types = "";
                if (companiesInfo.get(i).getTypes() != null && companiesInfo.get(i).getTypes().size() != 0)
                {
                    cell = row.createCell(11);
                    for (int j = 0; j < companiesInfo.get(i).getTypes().size(); j++)
                    {

                        if (j == companiesInfo.size() - 1)
                            types += companiesInfo.get(i).getTypes().get(j);

                        types += companiesInfo.get(i).getTypes().get(j) + ", ";
                    }
                    cell.setCellValue(types);
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
//
                outFile = new FileOutputStream(new File("D:\\Freelance\\Project Manager\\Companies.xlsx"));
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

    public static void parseCompaniesInfo(Connection.Response response, String url, java.sql.Connection conn, PreparedStatement preparedStmt)
    {
        List<String> types = new ArrayList<>();
        try
        {
            Connection.Response response1 = Jsoup.connect(url)
                    .cookies(response.cookies())
                    .timeout(12000 * 1000)
                    .execute();

            Document document = response1.parse();

            boolean isProductInfo = false;
            for (Element e : document.getAllElements())
            {
                for (Node n : e.childNodes())
                {
                    if (n instanceof Comment)
                    {
                        if (n.toString().contains("canSeeShareBox"))
                        {
                            String json = n.toString().replaceAll("<!--", "").replaceAll("-->", "");
                            JSONParser jsonParser = new JSONParser();
                            try
                            {
                                JSONObject jsonObjectCompanyInfo = (JSONObject) jsonParser.parse(json);
//                                Location
                                JSONObject jsonObjectCompanyLocation = (JSONObject) jsonObjectCompanyInfo.get("headquarters");
                                // get a String from the JSON object
                                String country = (String) jsonObjectCompanyLocation.get("country");
                                if (country == null)
                                    country = "";
                                String state = (String) jsonObjectCompanyLocation.get("state");
                                if (state == null)
                                    state = "";
                                String city = (String) jsonObjectCompanyLocation.get("city");
                                if (city == null)
                                    city = "";
                                String street = (String) jsonObjectCompanyLocation.get("street1");
                                if (street == null)
                                    street = "";
                                String zip = (String) jsonObjectCompanyLocation.get("zip");
                                if (zip == null)
                                    zip = "";
                                String companyName = (String) jsonObjectCompanyInfo.get("companyName");
                                if (companyName == null)
                                    companyName = "";
                                String description = (String) jsonObjectCompanyInfo.get("description");
                                if (description == null)
                                    description = "";
                                String industry = (String) jsonObjectCompanyInfo.get("industry");
                                if (industry == null)
                                    industry = "";
                                String website = (String) jsonObjectCompanyInfo.get("website");
                                if (website == null)
                                    website = "";
                                String size = (String) jsonObjectCompanyInfo.get("size");
                                if (size == null)
                                    size = "";
                                String logo = (String) jsonObjectCompanyInfo.get("legacyLogo");
                                if (logo != null)
                                    logo = "https://media.licdn.com/media" + logo;
                                else
                                {
                                    logo = (String) jsonObjectCompanyInfo.get("legacySquareLogo");
                                    if (logo != null)
                                        logo = "https://media.licdn.com/media" + logo;
                                    else
                                        logo = "";
                                }

                                isProductInfo = true;
                                // get an array from the JSON object
                                JSONArray typesSpecialties = (JSONArray) jsonObjectCompanyInfo.get("specialties");
                                // take the elements of the json array
                                String type = "";
                                if (typesSpecialties != null && typesSpecialties.size() != 0)
                                {
                                    for (int j = 0; j < typesSpecialties.size(); j++)
                                        types.add(typesSpecialties.get(j).toString());
                                }
                                if (types != null && types.size() != 0)
                                {
                                    for (int j = 0; j < types.size(); j++)
                                    {

                                        if (j == companiesInfo.size() - 2)
                                            type += types.get(j);

                                        type += types.get(j) + ", ";
                                    }
                                }
                                String insertIntoCompanyIfoQuery = "INSERT INTO `company-info` (`country`, `state`, `city`, `street`," +
                                        " `zip`, `industry`, `name`, `description`, `website`," +
                                        " `size`, `logo`, `types`) VALUES (";
                                insertIntoCompanyIfoQuery += "'" + country + "', '" + state + "', '" + city + "', '" + street + "', '" + zip + "', '" + industry + "', '"
                                        + companyName + "', '" + description + "', '" + website + "', '" + size + "', '" + logo + "', '"
                                        + type + "');";
                                try
                                {
                                    preparedStmt.setString(1, country);
                                    preparedStmt.setString(2, state);
                                    preparedStmt.setString(3, city);
                                    preparedStmt.setString(4, street);
                                    preparedStmt.setString(5, zip);
                                    preparedStmt.setString(6, industry);
                                    preparedStmt.setString(7, companyName);
                                    preparedStmt.setString(8, description);
                                    preparedStmt.setString(9, website);
                                    preparedStmt.setString(10, size);
                                    preparedStmt.setString(11, logo);
                                    preparedStmt.setString(12, type);
                                    preparedStmt.execute();
//                                    Statement stmt = conn.createStatement();
//                                    stmt.executeUpdate(insertIntoCompanyIfoQuery);
                                }
                                catch (MySQLSyntaxErrorException mysql)
                                {
                                    continue;
                                }
                                catch (SQLException e1)
                                {
                                    e1.printStackTrace();
                                }
                            }
                            catch (ParseException e1)
                            {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
                if (isProductInfo)
                    break;
            }
        }
        catch (HttpStatusException hse)
        {

            System.out.println("HttpStatusException with company: " + url);
            parseCompaniesInfo(response, url, conn, preparedStmt);

        }
        catch (SocketTimeoutException ste)
        {
            System.out.println("SocketTimeoutException with company: " + url);
            // try connect again
            parseCompaniesInfo(response, url, conn, preparedStmt);
        }
        catch (SocketException se)
        {
            System.out.println("SocketException with company: " + url);
            // try connect again
            parseCompaniesInfo(response, url, conn, preparedStmt);
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("UnknownHostException with company: " + url);
            // try connect again
            parseCompaniesInfo(response, url, conn, preparedStmt);
        }
        catch (IOException e)
        {
            System.out.println("IOException with company: " + url);
            // try connect again
//            parseCompaniesInfo(url, stmt);
        }
        catch (NullPointerException e)
        {
            System.out.println("NullPointerException company: " + url);
        }
    }

    public static List<Company> companiesInfo(/*List<String> companiesUrls*/)
    {
        int count = 0;

        response = getConnection();
        String urlDB = "jdbc:mysql://localhost:3306/linkedin-companies";
        String user = "root";
        String pass = "root";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(urlDB, user, pass);
            Statement stmt = conn.createStatement();

            ResultSet rs;
            String getAllCompaniesQuery = "SELECT * FROM `company` WHERE `id` > 386669 ORDER BY `id`;";
            String query = " INSERT INTO `company-info` (`country`, `state`, `city`, `street`," +
                    " `zip`, `industry`, `name`, `description`, `website`," +
                    " `size`, `logo`, `types`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(query);
            rs = stmt.executeQuery(getAllCompaniesQuery);

            while (rs.next())
            {

                String url = rs.getString("url");
                parseCompaniesInfo(response, url, conn, preparedStmt);
                count++;
                if (count % 1000 == 0)
                    System.out.println("company: " + count);
            }


            /*for (int i = 0; i < companiesUrls.size(); i++)
            {
                if (i % 500 == 0)
                    System.out.println("companiesInfo.size(): " + companiesInfo.size());
                Company company = new Company();
                List<String> types = new ArrayList<>();
                try
                {
                    Connection.Response response1 = Jsoup.connect(companiesUrls.get(i))
                            .cookies(response.cookies())
                            .timeout(12000 * 1000)
                            .execute();

                    Document document = response1.parse();

                    boolean isProductInfo = false;
                    for (Element e : document.getAllElements())
                    {
                        for (Node n : e.childNodes())
                        {
                            if (n instanceof Comment)
                            {
                                if (n.toString().contains("canSeeShareBox"))
                                {
                                    String json = n.toString().replaceAll("<!--", "").replaceAll("-->", "");
                                    JSONParser jsonParser = new JSONParser();
                                    try
                                    {
                                        JSONObject jsonObjectCompanyInfo = (JSONObject) jsonParser.parse(json);
//                                Location
                                        JSONObject jsonObjectCompanyLocation = (JSONObject) jsonObjectCompanyInfo.get("headquarters");
                                        // get a String from the JSON object
                                        String country = (String) jsonObjectCompanyLocation.get("country");
                                        if (country == null)
                                            country = "";
                                        String state = (String) jsonObjectCompanyLocation.get("state");
                                        if (state == null)
                                            state = "";
                                        String city = (String) jsonObjectCompanyLocation.get("city");
                                        if (city == null)
                                            city = "";
                                        String street = (String) jsonObjectCompanyLocation.get("street1");
                                        if (street == null)
                                            street = "";
                                        String zip = (String) jsonObjectCompanyLocation.get("zip");
                                        if (zip == null)
                                            zip = "";
                                        String companyName = (String) jsonObjectCompanyInfo.get("companyName");
                                        if (companyName == null)
                                            companyName = "";
                                        String description = (String) jsonObjectCompanyInfo.get("description");
                                        if (description == null)
                                            description = "";
                                        String industry = (String) jsonObjectCompanyInfo.get("industry");
                                        if (industry == null)
                                            industry = "";
                                        String website = (String) jsonObjectCompanyInfo.get("website");
                                        if (website == null)
                                            website = "";
                                        String size = (String) jsonObjectCompanyInfo.get("size");
                                        if (size == null)
                                            size = "";
                                        String logo = (String) jsonObjectCompanyInfo.get("legacyLogo");
                                        if (logo != null)
                                            logo = "https://media.licdn.com/media" + logo;
                                        else
                                        {
                                            logo = (String) jsonObjectCompanyInfo.get("legacySquareLogo");
                                            if (logo != null)
                                                logo = "https://media.licdn.com/media" + logo;
                                            else
                                                logo = "";
                                        }

                                        isProductInfo = true;
                                        // get an array from the JSON object
                                        JSONArray typesSpecialties = (JSONArray) jsonObjectCompanyInfo.get("specialties");
                                        // take the elements of the json array
                                        if (typesSpecialties != null && typesSpecialties.size() != 0)
                                        {
                                            for (int j = 0; j < typesSpecialties.size(); j++)
                                                types.add(typesSpecialties.get(j).toString());
                                        }
                                        company.setCountry(country);
                                        company.setState(state);
                                        company.setCity(city);
                                        company.setStreet(street);
                                        company.setZip(zip);
                                        company.setIndustry(industry);
                                        company.setCompanyName(companyName);
                                        company.setDescription(description);
                                        company.setWebsite(website);
                                        company.setSize(size);
                                        company.setLogo(logo);
                                        company.setTypes(types);
                                        String insertIntoCompanyIfoQuery = "INSERT INTO `company-info` (`country`, `company_name`, `state`, `city`, `street`," +
                                                " `zip`, `industry`, `name`, `description`, `website`," +
                                                " `size`, `logo`, `types`) VALUES (";
                                        insertIntoCompanyIfoQuery += "'" + country + "', '" + state + "', '" + city + "', '" + street + "', '" + zip + "', '" + industry + "', '"
                                                + companyName + "', '" + description + "', '" + website + "', '" + size + "', '" + logo + "', '"
                                                + types + "');";
                                        try
                                        {
                                            stmt.executeUpdate(insertIntoCompanyIfoQuery);
                                            companiesInfo.add(company);
                                        }
                                        catch (MySQLSyntaxErrorException mysql)
                                        {
                                            continue;
                                        }
                                    }
                                    catch (ParseException e1)
                                    {
                                        e1.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                        if (isProductInfo)
                            break;
                    }
                }
                catch (HttpStatusException hse)
                {
                    if (count > 10)
                    {
                        count = 0;
                        continue;
                    } else
                    {
                        System.out.println("HttpStatusException with link: " + companiesUrls.get(i));
                        i--;
                        count++;
                        continue;
                    }
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + i);
                    // try connect again
                    continue;
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + i);
                    // try connect again
                    continue;
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + i);
                    // try connect again
                    i--;
                    continue;
                }
                catch (IOException e)
                {
                    System.out.println("IOException with link: " + i);
                    // try connect again
                    continue;
                }
                catch (NullPointerException e)
                {
                    System.out.println("NullPointerException with link: " + i);
                    continue;
                }
            }*/
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        return companiesInfo;
    }

    public static List<String> companiesUrls(List<String> companiesByCityUrls)
    {
        System.out.println("companiesByCityUrls.size()" + companiesByCityUrls.size());
        int count = 0;

        String urlDB = "jdbc:mysql://localhost:3306/linkedin-companies";
        String user = "root";
        String pass = "root";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(urlDB, user, pass);
            Statement stmt = conn.createStatement();


            for (int i = 0; i < companiesByCityUrls.size(); i++)
            {
                if (i % 100 == 0)
                    System.out.println("companiesUrls.size(): " + companiesUrls.size());
                try
                {
                    Connection.Response response1 = Jsoup.connect(companiesByCityUrls.get(i))
                            .cookies(response.cookies())
                            .timeout(12000 * 1000)
                            .execute();

                    Document document = response1.parse();
                    Elements contents = document.getElementsByClass("content");
                    if (contents != null)
                    {
                        for (Element content : contents)
                        {

                            Element contentHref = content.getElementsByTag("a").first();
                            if (contentHref != null)
                            {
                                String companyUrl = contentHref.attr("href");
                                if (companyUrl != null && !companyUrl.isEmpty())
                                {
                                    String saveCompanyUrlQuery = "INSERT INTO `company` (`url`) VALUES ('" + companyUrl + "');";
                                    try
                                    {
                                        stmt.executeUpdate(saveCompanyUrlQuery);
                                        companiesUrls.add(companyUrl);
                                    }
                                    catch (MySQLSyntaxErrorException e)
                                    {
                                        System.out.println("MySQLSyntaxErrorException with companyUrl: " + companyUrl);
                                        e.printStackTrace();
                                        continue;
                                    }
                                }
                            }

                        }
                    }
                }
                catch (HttpStatusException hse)
                {
                    if (count > 10)
                    {
                        count = 0;
                        continue;
                    } else
                    {
                        System.out.println("HttpStatusException with link: " + companiesByCityUrls.get(i));
                        i--;
                        count++;
                        continue;
                    }
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + i);
                    // try connect again
                    i--;
                    continue;
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + companiesByCityUrls.get(i));
                    // try connect again
                    i--;
                    continue;
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + i);
                    // try connect again
                    i--;
                    continue;
                }
                catch (IOException e)
                {
                    System.out.println("IOException with link: " + companiesByCityUrls.get(i));
                    // try connect again
                    continue;
                }
                catch (NullPointerException e)
                {
                    System.out.println("NullPointerException with link: " + i);
                    continue;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return companiesUrls;
    }

    public static List<String> companiesByCityUrls(List<String> companiesByIndustryUrls)
    {
        System.out.println("companiesByIndustryUrls.size()" + companiesByIndustryUrls.size());
        int count = 0;

        for (int i = 56; i < companiesByIndustryUrls.size(); i++)
        {
            try
            {
                int j = 0;
                Connection.Response response1 = Jsoup.connect(companiesByIndustryUrls.get(i))
                        .cookies(response.cookies())
                        .timeout(12000 * 10000)
                        .execute();

                Document document = response1.parse();
                Elements contents = document.getElementsByClass("content");
                if (contents != null)
                {
                    for (Element content : contents)
                    {

                        Element contentHref = content.getElementsByTag("a").first();
                        if (contentHref != null)
                        {
                            /*if (j > 1)
                                return companiesByCityUrls;*/
                            String industryUrl = contentHref.attr("href");
                            if (industryUrl != null && !industryUrl.isEmpty())
                                companiesByCityUrls.add(industryUrl);
                            j++;
                        }

                    }
                }
            }
            catch (HttpStatusException hse)
            {
                if (count > 10)
                {
                    count = 0;
                    continue;
                } else
                {
                    System.out.println("HttpStatusException with link: " + i);
                    i--;
                    count++;
                    continue;
                }
            }
            catch (SocketTimeoutException ste)
            {
                System.out.println("SocketTimeoutException with link: " + i);
                // try connect again
                continue;
            }
            catch (SocketException se)
            {
                System.out.println("SocketException with link: " + i);
                // try connect again
                continue;
            }
            catch (UnknownHostException uhe)
            {
                System.out.println("UnknownHostException with link: " + i);
                // try connect again
                i--;
                continue;
            }
            catch (IOException e)
            {
                System.out.println("IOException with link: " + i);
                // try connect again
                continue;
            }
            catch (NullPointerException e)
            {
                System.out.println("NullPointerException with link: " + i);
                continue;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("IndexOutOfBoundsException with link: " + i);
                continue;
            }
        }

        return companiesByCityUrls;
    }

    public static List<String> companiesByIndustry(String link)
    {
        try
        {
            response = getConnection();
            int i = 0;

            Connection.Response response1 = Jsoup.connect(link)
                    .cookies(response.cookies())
                    .timeout(12000 * 1000)
                    .execute();

            Document document = response1.parse();
            Elements contents = document.getElementsByClass("content");
            if (contents != null)
            {
                for (Element content : contents)
                {
                    Element contentHref = content.getElementsByTag("a").first();
                    if (contentHref != null)
                    {
                        /*if (i > 0)
                            return companiesByIndustryUrls;*/
                        String industryUrl = contentHref.attr("href");
                        if (industryUrl != null && !industryUrl.isEmpty())
                            companiesByIndustryUrls.add(industryUrl);
                        i++;
                    }
                }

            }
        }
        catch (HttpStatusException hse)
        {
            System.out.println("HttpStatusException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }
        catch (SocketTimeoutException ste)
        {
            System.out.println("SocketTimeoutException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }
        catch (SocketException se)
        {
            System.out.println("SocketException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("UnknownHostException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }
        catch (IOException e)
        {
            System.out.println("IOException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }
        catch (NullPointerException e)
        {
            System.out.println("NullPointerException with link: " + link);
            // try connect again
            companiesByIndustry(link);
        }

        return companiesByIndustryUrls;
    }

    public static Connection.Response getConnection()
    {

        Connection.Response response = null;

        try
        {
            String url = "https://www.linkedin.com/uas/login?goback=&trk=hb_signin";
            response = Jsoup
                    .connect(url)
                    .timeout(12000)
                    .method(Connection.Method.GET)
                    .execute();

            Document responseDocument = response.parse();
            Element loginCsrfParam = responseDocument
                    .select("input[name=loginCsrfParam]")
                    .first();

            response = Jsoup.connect("https://www.linkedin.com/uas/login-submit")
                    .cookies(response.cookies())
                    .data("loginCsrfParam", loginCsrfParam.attr("value"))
//                    .data("session_key", "taras19921@ukr.net")
//                    .data("session_password", "fotokamera1")
//                    .data("session_key", "taras19921@gmail.com")
//                    .data("session_password", "karate")
                    .data("session_key", "taras2007921@ukr.net")
                    .data("session_password", "fotokamera1")
                    .timeout(12000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute();
        }
        catch (HttpStatusException hse)
        {
            getConnection();
        }
        catch (SocketTimeoutException ste)
        {
            getConnection();
        }
        catch (SocketException se)
        {
            getConnection();
        }
        catch (UnknownHostException uhe)
        {
            getConnection();
        }
        catch (IOException e)
        {
            getConnection();
        }
        catch (NullPointerException e)
        {
            getConnection();
        }

        return response;
    }
}
