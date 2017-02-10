package com.javarush.test.freelance.parser;

public class WriteToFileExample
{

    public static final String SERVICE_PROVIDERS_PHOTO_PATH_LINUX = "/var/salamat/img/service_providers/";
    public static void main(String[] args) {

        String url = "http://46.30.43.39:8080/salamat/img/service_providers/Service_Provider147/Photo1464314427903_1.png";
        url = url.replace("http://46.30.43.39:8080/salamat/img/ads/", SERVICE_PROVIDERS_PHOTO_PATH_LINUX);
        System.out.println(url);
        /*try {

            String content = "This is the content to write into file";

            File file = new File("D:\\A.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
