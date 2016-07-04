package com.javarush.test.volpis;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResize
{
    public static final String ADDS_PHOTO_PATH_LINUX = "/var/salamat/img/ads/";
    public static void main(String[] args)
    {
        System.out.println(ADDS_PHOTO_PATH_LINUX.length());
        
        //File image = new File("D:\\volpis\\roadhunter\\img\\bridges\\bridge_142.jpg");
        File image = new File("D:\\volpis\\roadhunter\\img\\bridges\\bridge_142.jpg");
        String pathLINUX = "/var";
        String pathWINDOWS = "D:/volpis";
        String photoURL = "http://46.30.42.122:8080/roadhunter/img/bridges/bridge_2.jpg".replace("http://46.30.42.122:8080", "");
        photoURL = pathLINUX + photoURL;
        //photoURL = pathWINDOWS + photoURL;
        //System.out.println(photoURL);
        /*
        BufferedImage img;
        try {
            img = ImageIO.read(image);
            int width = img.getWidth();
            System.out.println(width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
