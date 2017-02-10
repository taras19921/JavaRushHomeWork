package com.javarush.test.freelance.parser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class McaGovInTest
{
    public static final String urlDB = "jdbc:mysql://localhost:3306/companies";
    public static final String user = "root";
    public static final String pass = "root";
    public static java.sql.Connection conn;
    public static Statement stmt;

    public static void main(String[] args)
    {

        getAllCIN("https://www.zaubacorp.com/company-list/p-1792-company.html");
    }

    public static void getAllCIN(String link)
    {

        System.out.println("Success!!!");
        String CIN;
        String last_page = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();
            Document page = null;
            while (true)
            {
                try
                {
                    page = Jsoup.connect(link).timeout(0).parser(Parser.htmlParser()).get();
                }
                catch (HttpStatusException hse)
                {
                    System.out.println("HttpStatusException with link: " + link);
                    // try connect again
                    getAllCIN(link);
                }
                catch (SocketTimeoutException ste)
                {
                    System.out.println("SocketTimeoutException with link: " + link);
                    // try connect again
                    getAllCIN(link);
                }
                catch (SocketException se)
                {
                    System.out.println("SocketException with link: " + link);
                    // try connect again
                    getAllCIN(link);
                }
                catch (UnknownHostException uhe)
                {
                    System.out.println("UnknownHostException with link: " + link);
                    // try connect again
                    getAllCIN(link);
                }

                Element table1 = page.getElementById("table").select("table").first();
                Elements rows1 = table1.select("tbody").select("tr");

                for (int row = 0; row < rows1.size(); row++)
                {
                    Element rowCIN = rows1.get(row);
                    CIN = rowCIN.select("td").get(0).text();
                    if (CIN.length() > 10)
                    {
                        String selectByCIN = "SELECT * FROM `cin` WHERE `cin` LIKE '" + CIN + "'";
                        ResultSet rs = stmt.executeQuery(selectByCIN);
                            /*if (!rs.next())
                                getCompanies(CIN);*/
                    } else if (CIN.startsWith("F"))
                    {
                        String selectByFRCN = "SELECT * FROM `fcrn` WHERE `fcrn` LIKE '" + CIN + "'";
                        ResultSet rs = stmt.executeQuery(selectByFRCN);
                            /*if (!rs.next())
                                getCompanies(CIN);*/
                    } else
                    {
                        String selectByLLPIN = "SELECT * FROM `llp` WHERE `llpin` LIKE '" + CIN + "'";
                        ResultSet rs = stmt.executeQuery(selectByLLPIN);
                            /*if (!rs.next())
                                getCompanies(CIN);*/
                    }
                }
                Elements links = page.select("a[rel=nofollow]");
                link = links.get(5).attr("href");
                last_page = link;

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("last_page: " + last_page);
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
