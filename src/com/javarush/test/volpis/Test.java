package com.javarush.test.volpis;

public class Test
{
    public static void main(String[] args)
    {
        String polygon = "32.31,-88.03 32.32,-87.91 31.78,-88.09 31.6,-88.06 31.6,-88.1 31.78,-88.22 32.31,-88.03";
        String[] coords = polygon.split(" ");
        for (int i = 0; i < coords.length; i++) {
            Double lat = Double.valueOf(coords[i].split(",")[0]);
            Double lon = Double.valueOf(coords[i].split(",")[1]);
            System.out.println(lat + " " + lon);
        }
    }
}
