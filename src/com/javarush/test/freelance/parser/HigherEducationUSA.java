package com.javarush.test.freelance.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HigherEducationUSA
{

    private static String getEmailFromGoogle(String query, String firstName, String lastName) {

        String email = "";
        List<String> emails = new ArrayList<>();
        String request = "https://www.google.com/search?q=" + query + "&num=20";
        try {
        /*// Setup proxy
        String proxyAdress = "127.0.0.1";
        int proxyPort = 8080;


            URL url = new URL(request);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAdress, proxyPort));
            HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
            uc.connect();
            String line;
            StringBuffer tmp = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = in.readLine()) != null) {
                tmp.append(line);
            }
            Document doc = Jsoup.parse(String.valueOf(tmp));*/

            // need http protocol, set this as a Google bot agent :)
            Document doc = Jsoup
                    .connect(request)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(12000 * 100000).get();

            // get all links
            Elements st = doc.getElementsByClass("st");
            for (int i = 0; i < st.size(); i++) {
                try
                {
                    String splitByEt1 = st.get(i).text().split("@")[0];
                    String[] splitBySpaceArray1 = splitByEt1.split(" ");
                    String splitByEt2 = st.get(i).text().split("@")[1];
                    String[] splitBySpaceArray2 = splitByEt2.split(" ");
                    String splitBySpace1 = splitBySpaceArray1[splitBySpaceArray1.length - 1];

                    if (splitBySpace1.toLowerCase().contains(firstName.toLowerCase())
                            || splitBySpace1.toLowerCase().contains(lastName.toLowerCase()))
                    {
                        String splitBySpace2 = splitBySpaceArray2[0];
                        email = splitBySpace1 + "@" + splitBySpace2;
                        if (emails == null)
                            emails.add(email);
                        else
                            return email;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    i++;
                }

            }

        } catch (HttpStatusException hse)
        {
            System.out.println("HttpStatusException with request: " + request);
            // try again
            getEmailFromGoogle(query, firstName, lastName);
        }
        catch (SocketTimeoutException ste)
        {
            System.out.println("SocketTimeoutException with request: " + request);

            // try again
            getEmailFromGoogle(query, firstName, lastName);
        }
        catch (SocketException se)
        {
            System.out.println("SocketException with request: " + request);

            // try again
            getEmailFromGoogle(query, firstName, lastName);
        }
        catch (UnknownHostException uhe)
        {

            System.out.println("UnknownHostException with request: " + request);
            // try again
            getEmailFromGoogle(query, firstName, lastName);
        } catch (IOException e) {
            // try again
            getEmailFromGoogle(query, firstName, lastName);
        }

        return email;
    }

    public static void getEmails(File file)
    {

        FileInputStream fileInputStream = null;
        Workbook workbook = null;
        try
        {
            fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String firstName = row.getCell(0).toString();
                String lastName = row.getCell(1).toString();
                String company = row.getCell(2).toString();
                String query = firstName + " " + lastName + " " + company;

                String email = getEmailFromGoogle(query, firstName, lastName);
                System.out.println(email);
                Cell cell = row.createCell(8);
                cell.setCellValue(email);


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            FileOutputStream outFile;
            try
            {
                fileInputStream.close();
                outFile = new FileOutputStream(file);
                workbook.write(outFile);
                outFile.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args)
    {

        getEmails(new File("D:\\Freelance\\Micajah Dudley\\NACAC Attendee List Higher Ed.xlsx"));
    }
}
