package com.javarush.test.volpis;

import org.json.JSONArray;
import org.json.JSONException;
import sun.rmi.runtime.Log;

public class NextLine
{
    public static void main(String[] args)
    {
        String line14 = "281,\"Pilot Travel Center\",\"Girard\",\"OH\",\"I-80, Exit 226\",\"$1.839\",\"$2.039\",\"$2.239\",\"\",\"$2.359\",\"$2.799\",\"9\",\"\"";
        String line = "8,\"Pilot Travel Center\",\"Circleville\",\"OH\",\"US 23 Pittsburg Rd\",\"$1.839\",\"$2.039\",\"$2.239\",\"\",\"$2.299\",\"$2.799\",\"5\",\"\"";
        String[] val14 = line14.split(",");
        String[] val = line.split(",");
        System.out.println(line14);
        System.out.println(val14.length);
        System.out.println(line);
        System.out.println(val.length);
        int unleadedPriceIndex = 5;
        int midGradePriceIndex = 6;
        int superPriceIndex = 7;
        int propanePriceIndex = 8;
        int dieselPriceIndex = 9;
        int defPriceIndex = 10;

        if (val.length > 12) {
            int odd = val.length - 13;
            unleadedPriceIndex += odd;
            midGradePriceIndex += odd;
            superPriceIndex += odd;
            propanePriceIndex += odd;
            dieselPriceIndex += odd;
            defPriceIndex += odd;
        }

        String unleadedPrice = val[unleadedPriceIndex];
        String midGradePrice = val[midGradePriceIndex];
        String superPrice = val[superPriceIndex];
        String propanePrice = val[propanePriceIndex];
        String dieselPrice = val[dieselPriceIndex];
        String defPrice = val[defPriceIndex];
        System.out.println("unleadedPrice454: " + unleadedPrice);

        String row = "281,\"Pilot Travel Center\",\"Girard\",\"OH\",\"I-80, Exit 226\",\"$1.839\",\"$2.039\",\"$2.239\",\"\",\"$2.359\",\"$2.799\",\"9\",\"\"";
        row = row.replaceFirst(",", "\",");
        row = "[\"" + row + "]";
        System.out.println(row);


        try {
            JSONArray jsonArray = new JSONArray(row);
            for (int i = 0; i < jsonArray.length(); i++);
                //Log.d("mylogs", jsonArray.getString(i));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
