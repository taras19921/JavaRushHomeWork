package com.javarush.test.volpis;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class EncodeBase64
{
    public static final String USER_PHOTO_PATH = "/gocompass/img/users";

    private static BufferedImage decodeToImage(String imageString)
    {

        BufferedImage image = null;
        byte[] imageByte;
        try
        {
            imageByte = Base64.decodeBase64(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return image;
    }

    public static String addImage(long id, String imageString)
    {
        String PATH = "D:\\";
        String user = "User1_";
        String imageURL = null;
        BufferedImage bufferedImage = decodeToImage(imageString);
        Date date = new Date();
        long milisec = date.getTime();

        File image = new File(PATH + user + milisec + ".jpg");
        try
        {
            if (!image.exists())
            {
                image.createNewFile();
            }
            ImageIO.write(bufferedImage, "jpg", image);
            imageURL = milisec + ".jpg";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return imageURL;
    }

    public static void main(String[] args) throws IOException
    {
        /*String image = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACU0lEQVR42qVTz2tTQRDemd19efmhoYl6aAsWkbYapInRevHgj7RRqfTUm1ZTqenBk+KfICgeRG+1ImgvhR4UQUh70ovioS2K4EnoQRS8aNPYxLz3dpyNBGKIF114vJ3ZnW9mvvkWsqdnSPzHAgsAIAT9IwwcOjNDNrgdpJMdBKbhkRKheQaZU8WAbWweCkD+k0AAbA+WUllYERhjbEwjcSY/XeGD6B+peAW+T4gIhsg0srLBIN/4gtaOjHmeb7hEgPRIcZFADCCKHjKkJOAXEoZQqUEOMFpLNIER2nHFVrUyTwG91gpvgVTbbI/QlZ2I9ya6RkFAt0Ta6wdmlRvirPIgChghwg0JYpcAoQMQy2iI93CEiXO5SR9aCEXROxESnxar1ugZnkzG4zpd/lB+lewPH6hyy65U5wDklZCj0P9Zf+J59RsWoAnyt0Eq/ny72Xfi0m5Xy+eO66Z+1KqT75fm5qHtcqtN/UenhqIx/YapuPhueW7BOtP54tNwJDpe2ypfWFt68Bg6pezrO+aur7+o7WF+4juSLwFoc7U0e9yCZvKXV9xIbIgJvfO2dP9qO4CdvUmdLOx3tHN7rTQ7lslNnQ9vTzyqVTYeft38fn1nPPFZShnyfX8LCO52qsD6KJ0vjBM4ZxEpJwxGWHpoDD1TjioEPF+rC6m16MBBlklb8Ww1mXzxI2PVedstJcR+69s0JM5iZBVh0JEDuzKj0/dYG8O+MQtSyZucMWQlbCXe+k5aAFLOYO7wgPJF0gmpa8oJjXn1oCIVxuxtLrsh7fZH9gtHdvclB6AI1QAAAABJRU5ErkJggg==";
        System.out.println(addImage(1, image));*/


        File file = new File("D:\\YuA7g8z8hGE.jpg");
        FileInputStream fin = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int count = 0;

        while ((count = fin.read(buffer)) != -1)
        {
            baos.write(buffer, 0, count);
        }

        byte[] fileContent = baos.toByteArray();

//all chars in encoded are guaranteed to be 7-bit ASCII
        byte[] encoded = Base64.encodeBase64(fileContent);
        String encodedFile = new String(encoded);
        System.out.println(encodedFile);
    }//

}
