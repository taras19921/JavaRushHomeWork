package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    private static ArrayList<String> listWithFileNames = new ArrayList<>();

    public static List<String> getFileTree(String root) throws IOException
    {
        File f = new File(root);
        for (File s : f.listFiles())
        {
            if (s.isFile())
            {
                listWithFileNames.add(s.getAbsolutePath());
            } else if (s.isDirectory())
            {
                getFileTree(s.getAbsolutePath());
            }
        }
        return listWithFileNames;

    }
}
