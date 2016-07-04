package com.javarush.test.volpis;

import java.io.File;

/**
 * Created by Тарас on 14.12.2015.
 */
public class SearchDeleteFile
{
    public static void listFilesForFolder(final File folder)
    {
        String photoURL = "http://localhost:8080/roadhunter/img/bridges/bridge_52.jpg";
        photoURL = photoURL.replaceFirst("http://localhost:8080/roadhunter/img/bridges/", "");
        System.out.println("url: " + photoURL);
        System.out.println();
        for (final File fileEntry : folder.listFiles())
        {
            if (fileEntry.getName().equals(photoURL)) {
                if(fileEntry.delete()){
                    System.out.println(fileEntry.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }

            //System.out.println(fileEntry.getName());

        }
    }

    public static void main(String[] args)
    {
        final File folder = new File("D:\\volpis\\roadhunter\\img\\bridges");
        listFilesForFolder(folder);
    }
}
