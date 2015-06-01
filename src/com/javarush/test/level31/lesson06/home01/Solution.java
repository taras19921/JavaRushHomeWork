package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        if (args.length == 2)
        {
            String zipFileDestination = args[1];
            String fileDir = args[0];
            Map<String, ByteArrayOutputStream> map = new HashMap<>();
            File fileToWrite = new File(fileDir);
            //Читаем все файлы в архиве и добавляем их байты в карту
            FileInputStream inputStream = new FileInputStream(zipFileDestination);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(inputStream));
            try {
                ZipEntry ze;
                while ((ze = zis.getNextEntry()) != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = zis.read(buffer)) != -1) {
                        baos.write(buffer, 0, count);
                    }
                    String filename = ze.getName();
                    byte[] bytes = baos.toByteArray();
                    // do something with 'filename' and 'bytes'...
                }
            } finally {
                zis.close();
            }
            //Добавляем в мапу байты файла для добавления
            String fileToWriteName = fileToWrite.getName();
            try (FileInputStream fileInputStream = new FileInputStream(fileToWrite))
            {
                int length = fileInputStream.available();
                byte[] bytes = new byte[length];
                fileInputStream.read(bytes);
                ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream(length);
                byteArrayOutputStream.write(bytes);
                if (map.containsKey(fileToWriteName))
                {
                    map.put(fileToWrite.getName(), byteArrayOutputStream);
                } else
                {
                    map.put("new/" + fileToWrite.getName(), byteArrayOutputStream);
                }
            }
            catch (IOException e)
            {}
            //Перебираем все элементы и записываем их в архив
            try
                    (FileOutputStream fileOutputStream = new FileOutputStream(zipFileDestination);
                     ZipOutputStream zip = new ZipOutputStream(fileOutputStream))
            {
                for (Map.Entry<String, ByteArrayOutputStream> entry : map.entrySet())
                {
                    String name = entry.getKey();
                    ByteArrayOutputStream byteArrayOutputStream = entry.getValue();
                    zip.putNextEntry(new ZipEntry(name));
                    zip.write(byteArrayOutputStream.toByteArray());
                    zip.closeEntry();
                }
            }
            catch (IOException e)
            {}
        }
    }
}
