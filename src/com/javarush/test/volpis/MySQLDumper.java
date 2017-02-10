package com.javarush.test.volpis;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySQLDumper
{
    private static String ip = "localhost";
    private static String databaseExport = "salamat";
//    private static String databaseImport = "salamat_test";
    private static String user = "root";
    private static String pass = "root";
//    private static String path = "/var/salamat.sql";
    private static String path = "D:\\";

    public static File sourceAds = new File("/var/salamat/img/ads");
    public static File sourceCarAds = new File("/var/salamat/img/car_ads");
    public static File sourceCategories = new File("/var/salamat/img/categories");
    public static File sourceMessages = new File("/var/salamat/img/messages");
    public static File sourceRealtyAds = new File("/var/salamat/img/realty_ads");
    public static File sourceSerProvs = new File("/var/salamat/img/service_providers");
    public static File sourceSerSeeks = new File("/var/salamat/img/service_seekers");
    public static File sourceTypes = new File("/var/salamat/img/types");
    public static File sourceUsers = new File("/var/salamat/img/users");

    public static void main(String args[]) throws IOException
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpEntity entity = MultipartEntityBuilder
                .create()
//                .addTextBody("number", "5555555555")
//                .addTextBody("clip", "rickroll")
                .addBinaryBody("sqlFile", new File(path + "salamat_test.sql 20160901.sql"), ContentType.create("application/octet-stream"), "salamat_test.sql 20160901.sql")
//                .addTextBody("tos", "agree")
                .build();

        HttpPost httpPost = new HttpPost("http://139.162.181.206:8080/salamat-reserve/backup");
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println("response: " + response.getEntity());


        /*HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httpPost = new HttpPost("http://139.162.181.206:8080/salamat-reserve/backup");

        MultipartEntity mpEntity = new MultipartEntity();
        File sqlBackupFile = exportDB();
        ContentBody sqlFile = new FileBody(sqlBackupFile, "multipart/form-data");
        mpEntity.addPart("sqlFile", sqlFile);
        httpPost.setEntity(mpEntity);
        httpPost.getRequestLine();
        try
        {
            httpclient.execute(httpPost);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

        /*File[] folders = exportFolders();
        ContentBody folderFile;
        for (int i = 0; i < folders.length; i++) {
            folderFile = new FileBody(folders[i], "multipart/form-data");
            mpEntity.addPart("folderFile" + (i + 1), folderFile);
        }*/
//        HttpPost httppost = new HttpPost("http://localhost:9000/upload");
//        File sourceAds = new File("D:\\volpis\\salamat\\img\\ads");

//        backupImportDB(databaseImport, user, pass, path);
    }

    public static File[] exportFolders() {

        File[] folders = {sourceAds, sourceCarAds, sourceCategories, sourceMessages,
                sourceRealtyAds, sourceSerProvs, sourceSerSeeks, sourceTypes, sourceUsers};
        /*File dest = new File("D:\\ads");
        try {
            FileUtils.copyDirectory(sourceAds, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return folders;
    }

    public static File exportDB() {
        Date dateNow = new Date();
        SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String date_to_string = dateformatyyyyMMdd.format(dateNow);
        System.out.println("date into yyyyMMdd format: " + date_to_string);
        String fileName = "salamat_test.sql";
        String fullName = path + " " + date_to_string + " " + fileName;
        String dumpCommand = "mysqldump " + databaseExport + " -h " + ip + " -u " + user + " -p" + pass;
        Runtime rt = Runtime.getRuntime();
        File fileExport = new File(fullName);
        PrintStream ps;
        try {
            Process child = rt.exec(dumpCommand);
            ps = new PrintStream(fileExport);
            InputStream in = child.getInputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                ps.write(ch);
//System.out.write(ch); //to view it by console
            }

            InputStream err = child.getErrorStream();
            while ((ch = err.read()) != -1) {
                System.out.write(ch);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return fileExport;
    }

    public static boolean backupImportDB(String dbName, String dbUserName, String dbPassword, String path)
    {
        String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUserName, "-p" + dbPassword, "-e", " source " + path};
        Process runtimeProcess;
        try
        {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0)
            {
                System.out.println("Import done successfully");
                return true;
            } else
            {
                System.out.println("Could not create the import");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean backupExportDB(String dbName, String dbUserName, String dbPassword, String path)
    {
        String executeCmd = "mysqldump -u" + dbUserName + " -p" + dbPassword + " --database " + dbName + " -r " + path;
        Process runtimeProcess;
        try
        {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0)
            {
                System.out.println("Backup created successfully");
                return true;
            } else
            {
                System.out.println("Could not create the backup");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }
}
