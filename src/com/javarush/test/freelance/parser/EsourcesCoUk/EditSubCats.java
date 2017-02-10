package com.javarush.test.freelance.parser.EsourcesCoUk;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditSubCats
{
    public static final String urlDB = "jdbc:mysql://localhost:3306/esources";
    public static final String user = "root";
    public static final String pass = "root";
    public static java.sql.Connection conn;
    public static Statement stmt;

    public static void main(String[] args)
    {
        update();
    }

    public static void update()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDB, user, pass);
            stmt = conn.createStatement();

            Long subSubCategoryId = null;
            String subSubCategory;
            String selectSubCategories = "SELECT id, sub_sub_category_title FROM product_international;";
            ResultSet rs = stmt.executeQuery(selectSubCategories);
            while (rs.next())
            {
                subSubCategory = rs.getString("sub_sub_category_title");
                if (subSubCategory.contains("&"))
                {
                    subSubCategoryId = rs.getLong("id");
                    subSubCategory = subSubCategory.replaceAll("&", "and");
                    String updateSubCategory = "UPDATE `product_international` SET `sub_sub_category_title` = '" + subSubCategory + "' WHERE `product_international`.`id` = " + subSubCategoryId + ";";
                    stmt.executeUpdate(updateSubCategory);
                    update();
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
