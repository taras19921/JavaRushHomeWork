package com.javarush.test.volpis.timer;

import java.io.File;

/**
 * Created by Тарас on 29.03.2016.
 */
public class Folders
{

    private static final String SRC_FOLDER = "D:\\test";

    public static void main(String[] args)
    {
        File directory = new File(SRC_FOLDER);
        if (directory.exists())
        {
            System.out.println("Directory exist.");
            String[] files = directory.list();
            if (files.length == 0)
            {
                if (directory.isDirectory())
                {
                    directory.delete();
                    System.out.println("Directory is deleted : " + directory.getAbsolutePath());

                }
            } else
            {
                for (int i = 0; i < files.length; i++)
                {
                    File file = new File(directory.getAbsolutePath() + "\\" + files[i]);
                    if (file.isFile() && file.exists())
                    {
                        file.delete();
                        System.out.println("file is deleted!");
                    }
                }
                directory.delete();
                System.out.println("Directory is deleted : " + directory.getAbsolutePath());
            }
        }
    }
}
