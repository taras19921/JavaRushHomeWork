package com.javarush.test.volpis;

import java.net.*;

public class SmsSending
{
    public static void main(String[] args) {
        try {
            String recipient = "0501025911";
            String message = "Hello World";
            String username = "admin";
            String password = "abc123";
            String originator = "0501025911";

            String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
                    "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8") +
                    "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
                    "&messagetype=SMS:TEXT" +
                    "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
                    "&originator=" + URLEncoder.encode(originator, "UTF-8") +
                    "&serviceprovider=GSMModem1" +
                    "&responseformat=html";



            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();

            System.out.println(uc.getResponseMessage());

            uc.disconnect();

        } catch(Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
}
