package com.volpis.roadhunter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тарас on 21.12.2016.
 */
public class UsCountiesParser
{

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParserConfigurationException, SAXException
    {
        FileInputStream file = new FileInputStream(new File("D:\\volpis\\roadhunter\\files\\United States Counties.csv"));
        parseUsCountiesToDB(file);
    }


    private static boolean parseUsCountiesToDB(FileInputStream file) throws ClassNotFoundException, SQLException,
            IOException, ParserConfigurationException, SAXException
    {

        String urlDB = "jdbc:mysql://localhost:3306/roadhunter";
        String user = "root";
        String pass = "root";
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection conn = DriverManager.getConnection(urlDB, user, pass);
        String query = " INSERT INTO `us_counties` (`county_name`, `state-county`," +
                " `geometry`, `geo_id`, `geo_id_2`, `fips_formula`)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        Reader in = new FileReader("D:\\volpis\\roadhunter\\files\\United States Counties.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        int i = 0;
        for (CSVRecord record : records)
        {

            if (i == 0)
            {
                i++;
                continue;
            }

            String countyName = record.get(0);
            String stateCounty = record.get(1);
            String geometry = record.get(4)/*.replaceAll("<Polygon><outerBoundaryIs><LinearRing><coordinates>", "")
                    .replaceAll("</coordinates></LinearRing></outerBoundaryIs></Polygon>", "")*/;
            List<String> coordinatesList = new ArrayList();
            Document doc =
                    DocumentBuilderFactory
                            .newInstance()
                            .newDocumentBuilder()
                            .parse(new InputSource(new StringReader(geometry)));
            if (doc != null)
            {
                NodeList nodes = doc.getElementsByTagName("*");
                for (int k = 0; k < nodes.getLength(); k++)
                {
                    if (nodes.item(k).getNodeName().equals("coordinates"))
                    {

                        Node coordinatesNode = nodes.item(k);
                        Node coordinatesChild = coordinatesNode.getFirstChild();
                        String coordinates = coordinatesChild.getNodeValue();
                        coordinates = coordinates.replaceAll("\n", "");
                        coordinates = coordinates.replaceAll("(\r\n|\n\r|\r|\n)", "");
                        coordinatesList.add(coordinates);

//                        System.out.println("name is : " + coordinatesNode.getNodeName());
//                        System.out.println("value is : " + coordinates);
                    }
                }
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String geometryJson = gson.toJson(coordinatesList).replaceAll("\n  ", "")/*.replaceAll("\"", "")*/;
            geometryJson = geometryJson.replaceAll("\n", "")/*.replaceAll("\"", "")*/;
            String geoId = record.get(6);
            Integer geoId2 = toIntNumber(record.get(7));
            if (geoId2 == null)
                geoId2 = 0;
            Integer fipsFormula = 0;
            Double toDoubleNumber = toDoubleNumber(record.get(11));
            if (toDoubleNumber != null)
                fipsFormula = toDoubleNumber.intValue();

            try
            {
                preparedStmt.setString(1, countyName);
                preparedStmt.setString(2, stateCounty);
                preparedStmt.setString(3, geometryJson);
                preparedStmt.setString(4, geoId);
                preparedStmt.setInt(5, geoId2);
                preparedStmt.setInt(6, fipsFormula);
                preparedStmt.execute();
            }
            catch (MySQLSyntaxErrorException mysql)
            {
                continue;
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static Integer toIntNumber(String number)
    {
        int intNumber;
        if (number.equals(""))
            return null;
        try
        {
            intNumber = Integer.parseInt(number);
        }
        catch (Exception e)
        {
            return null;
        }
        return intNumber;
    }

    private static Double toDoubleNumber(String row)
    {
        Double doubleNumber;
        if (row.equals(""))
            return null;
        try
        {
            doubleNumber = Double.parseDouble(row);
        }
        catch (Exception e)
        {
            return null;
        }
        return doubleNumber;
    }
}
