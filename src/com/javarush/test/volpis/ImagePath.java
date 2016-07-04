package com.javarush.test.volpis;

import java.io.File;

public class ImagePath
{
    public static void main(String[] args)
    {
        //String path = "D:\\volpis\\salamat\\img\\ads\\Ad50\\Photo1452710363341_1.jpg";
        /*String filePath = path.
                substring(26, path.lastIndexOf(""));*/
        String path = "/var/salamat/img/ads/Ad8/Photo1452710007351_1.jpg";
        String filePath = path.substring(21, path.lastIndexOf(""));
        System.out.println(filePath);
    }
}
