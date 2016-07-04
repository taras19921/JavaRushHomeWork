package com.javarush.test.volpis.parser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class NestoriaCoUk
{

    public static void main(String[] args)
    {
        String url = "http://api.nestoria.co.uk/api?encoding=json&pretty=1&action=search_listings" +
                "&country=uk&listing_type=buy&place_name=w8-4aa&number_of_results=50";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        String result = null;
        try
        {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            // A Simple JSON Response Read
            InputStream inputStream = entity.getContent();
            result = convertStreamToString(inputStream);
            // now you have the string representation of the HTML request
            System.out.println("RESPONSE: " + result);
            inputStream.close();
            /*URL url = new URL("http://api.nestoria.co.uk/api?encoding=json&pretty=1&action=search_listings" +
                    "&country=uk&listing_type=buy&place_name=w8-4aa&number_of_results=50");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            System.out.println(body);*/

        }
        catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static HttpResponse connectPostResponse(URIBuilder uriBuilder)
    {

        URI uri = null;

        try
        {
            uri = uriBuilder.build();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(uri);
        HttpResponse response = null;

        try
        {
            String json = client.execute(request).toString();
            System.out.println(json.length());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        return response;
    }
}
