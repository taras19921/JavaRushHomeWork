package com.javarush.test.freelance.parser.EsourcesCoUk;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubCategory
{

    public static final String urlDB = "jdbc:mysql://localhost:3306/esources";
    public static final String user = "root";
    public static final String pass = "root";
    public static java.sql.Connection conn;
    public static Statement stmt;

    public static void main(String[] args)
    {
//        allCities("http://www.esources.co.uk/wholesale-suppliers/1/");
        allSubCategoriesTest("http://www.esources.co.uk/wholesale-suppliers/1/");
    }

    public static List<String> allSubCategoriesTest(String page)
    {
        List<String> allSubCategories = new ArrayList<>();
        try
        {
            Document document;

            document = Jsoup.connect(page).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements hrefs = document.getElementsByClass("listwrap").select("li");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();
            for (int i = 0; i < hrefs.size(); i++)
            {
                if (hrefs.get(i).getElementsByClass("sub2list").first() == null)
                {
                    String subCategory = hrefs.get(i).select("a[href]").text();
                    if (subCategory.equals("All Counties"))
                    {
                        return allSubCategories;
                    }

                    allSubCategories.add(subCategory);
                    String insertSubCat = "INSERT INTO `sub_category_test` (`title`, `category_title`) " +
                            "VALUES (" + "'" + subCategory + "', '" + "Apparel & Clothing" + "');";
                    try
                    {
                        stmt.executeUpdate(insertSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                } else
                {
                    String subCategory = allSubCategories.get(allSubCategories.size() - 1);
                    String subSubCategory = hrefs.get(i).select("a[href]").text();

                    /*String selectByFRCN = "SELECT `title` FROM `sub_category` WHERE `title` LIKE '" + subCategory + "'";
                    ResultSet rs = stmt.executeQuery(selectByFRCN);
                    Long id = null;
                    while (rs.next())
                        id = rs.getLong("id");*/

                    String insertSubSubCat = "INSERT INTO `sub_sub_category_test` (`title`, `category_title`, `sub_category_title`) " +
                            "VALUES (" + "'" + subSubCategory + "', '" + "Apparel & Clothing" + "', '" + subCategory + "');";
                    try
                    {
                        stmt.executeUpdate(insertSubSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                }

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return allSubCategories;
        }
    }

    public static List<String> allCities(String page)
    {
        List<String> allSubCategories = new ArrayList<>();
        try
        {
            Document document;

            document = Jsoup.connect(page).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements listwrap = document.getElementsByClass("listwrap");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();
            for (int i = 5; i < listwrap.size(); i++)
            {
                Elements sub2list = listwrap.get(i).getElementsByClass("sub2list").select("li");
                for (int j = 0; j < sub2list.size(); j++)
                {
                    String city = sub2list.get(j).select("a[href]").text();
                    System.out.println(city);
                }
                if (listwrap.get(i).getElementsByClass("sub2list").first() == null)
                {
                    String subCategory = listwrap.get(i).select("a[href]").text();
                    if (subCategory.equals("All Counties"))
                    {
                        return allSubCategories;
                    }

                    allSubCategories.add(subCategory);
                    String insertSubCat = "INSERT INTO `sub_category` (`title`, `category_id`) " +
                            "VALUES (" + "'" + subCategory + "', " + 26 + ");";
                    try
                    {
                        stmt.executeUpdate(insertSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                } else
                {
                    String subCategory = allSubCategories.get(allSubCategories.size() - 1);
                    String subSubCategory = listwrap.get(i).select("a[href]").text();

                    String selectByFRCN = "SELECT `id` FROM `sub_category` WHERE `title` LIKE '" + subCategory + "'";
                    ResultSet rs = stmt.executeQuery(selectByFRCN);
                    Long id = null;
                    while (rs.next())
                        id = rs.getLong("id");

                    String insertSubSubCat = "INSERT INTO `sub_sub_category` (`title`, `category_id`, `sub_category_id`) " +
                            "VALUES (" + "'" + subSubCategory + "', " + 26 + ", " + id + ");";
                    try
                    {
                        stmt.executeUpdate(insertSubSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                }

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return allSubCategories;
        }
    }

    public static List<String> allSubCategories(String page)
    {
        List<String> allSubCategories = new ArrayList<>();
        try
        {
            Document document;

            document = Jsoup.connect(page).timeout(0/*10 * 1000*/).parser(Parser.htmlParser()).get();
            Elements hrefs = document.getElementsByClass("listwrap").select("li");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();
            for (int i = 0; i < hrefs.size(); i++)
            {
                if (hrefs.get(i).getElementsByClass("sub2list").first() == null)
                {
                    String subCategory = hrefs.get(i).select("a[href]").text();
                    if (subCategory.equals("All Counties"))
                    {
                        return allSubCategories;
                    }

                    allSubCategories.add(subCategory);
                    String insertSubCat = "INSERT INTO `sub_category` (`title`, `category_id`) " +
                            "VALUES (" + "'" + subCategory + "', " + 26 + ");";
                    try
                    {
                        stmt.executeUpdate(insertSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                } else
                {
                    String subCategory = allSubCategories.get(allSubCategories.size() - 1);
                    String subSubCategory = hrefs.get(i).select("a[href]").text();

                    String selectByFRCN = "SELECT `id` FROM `sub_category` WHERE `title` LIKE '" + subCategory + "'";
                    ResultSet rs = stmt.executeQuery(selectByFRCN);
                    Long id = null;
                    while (rs.next())
                        id = rs.getLong("id");

                    String insertSubSubCat = "INSERT INTO `sub_sub_category` (`title`, `category_id`, `sub_category_id`) " +
                            "VALUES (" + "'" + subSubCategory + "', " + 26 + ", " + id + ");";
                    try
                    {
                        stmt.executeUpdate(insertSubSubCat);
                    }
                    catch (MySQLSyntaxErrorException e)
                    {
                        System.out.println("MySQLSyntaxErrorException with subCategory: " + subCategory);
                    }
                    catch (MySQLIntegrityConstraintViolationException e)
                    {
                        System.out.println("MySQLIntegrityConstraintViolationException with subCategory: " + subCategory);
                    }
                }

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return allSubCategories;
        }
    }
}
