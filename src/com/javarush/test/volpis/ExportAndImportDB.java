package com.javarush.test.volpis;

import java.io.IOException;

public class ExportAndImportDB
{

    public static void exportDB() throws IOException, InterruptedException
    {

        String dbName = "salamat_test";
        String dbUser = "root";
        String dbPass = "root";

        String executeCmd = "mysqldump -u " + dbUser + " -p "/* + dbPass + " "*/ + dbName + " > D:\\salamat_test.sql";

        Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();
        if(processComplete == 0){

            System.out.println("Backup taken successfully");

        } else {

            System.out.println("Could not take mysql backup");

        }

        /*Runtime runtime = Runtime.getRuntime();
        Process pr = runtime.exec("mysqldump -u root -p salamat_test > D:\\salamat_test.sql");*/

    }
    public static void main(String[] args) throws IOException, InterruptedException
    {
        exportDB();

    }
}
