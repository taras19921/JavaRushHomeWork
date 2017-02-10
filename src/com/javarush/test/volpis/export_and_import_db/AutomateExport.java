package com.javarush.test.volpis.export_and_import_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutomateExport
{

    public static void main(String[] args)
    {

        Connection conn = connect(
                "jdbc:mysql://localhost:3306/salamat_test", "root", "root");

        /*if (args.length != 1)
        {
            System.out.println(
                    "Usage: java automateExport [outputfile path] ");
            return;
        }*/
        exportData(conn, "D:\\salamat_test.sql");
    }

    public static void exportData(Connection conn, String filename)
    {
        Statement stmt;
        String query;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //For comma separated file
            query = "SELECT id,title into OUTFILE  '" + filename +
                    "' FIELDS TERMINATED BY ',' FROM car_brand";
            stmt.executeQuery(query);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection connect(String db_connect_str,
                                     String db_userid, String db_password)
    {
        Connection conn;
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_str,
                    db_userid, db_password);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }
}
