package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        ByteArrayOutputStream arr = new ByteArrayOutputStream();
        FileOutputStream fo = new FileOutputStream(args[0]);
        ArrayList<File> f = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
        {
            f.add(new File(args[i]));
        }
        Collections.sort(f);
        for (File a : f)
        {
            Files.copy(a.toPath(), arr);
        }
        ByteArrayInputStream afa = new ByteArrayInputStream(arr.toByteArray());
        ZipInputStream zipInputStream = new ZipInputStream(afa);
        BufferedOutputStream out = new BufferedOutputStream(fo);
        ZipEntry entry = zipInputStream.getNextEntry();
        long len = entry.getSize();
        int n;
        long count = 0;
        byte[] bytes = new byte[1024];
        while ((entry = zipInputStream.getNextEntry())!=null)
        {
            int size = (int) entry.getSize();
            byte[] buffer = new byte[size];
            zipInputStream.read(buffer, 0, size);
            arr.write(buffer,0,size);
        }


    Files.write(Paths.get(args[0]),arr.toByteArray());
        /*
        while (((n = zipInputStream.read(bytes)) > 0) && count < len)
        {
            out.write(bytes, 0, n);
            count += n;
        }
        */
        fo.close();
        zipInputStream.close();
    }
}
