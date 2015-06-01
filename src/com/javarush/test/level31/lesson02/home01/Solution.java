package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        File folder = new File(args[0]);
        File changeFile = new File(args[1]);
        File newResultFile = new File(changeFile.getParent() + "/" + "allFilesContent.txt");
        changeFile.renameTo(newResultFile);
        List<File> list = getFilesTree(folder);
        Collections.sort(list, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        FileOutputStream fileOutputStream = new FileOutputStream(newResultFile);
        for(int f = 0; f < list.size();f++){
            FileInputStream fileInputStream = new FileInputStream(list.get(f));
            byte[]arr = new byte[fileInputStream.available()];
            fileInputStream.read(arr);
            fileOutputStream.write(arr);
            if(f < list.size()-1)
                fileOutputStream.write('\r');
            fileOutputStream.write('\n');
            fileInputStream.close();
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    public static List<File> getFilesTree(File file)
    {
        List<File> result = new ArrayList<>();
        Queue<File> myQueue = new ArrayDeque<>();
        myQueue.add(file);
        while (!myQueue.isEmpty())
        {
            File getFile = myQueue.poll();
            File[] list = getFile.listFiles();
            for (int i = 0; i < list.length; i++)
            {
                if(list[i].isDirectory()){
                    if(list[i].length()==0){
                        list[i].delete();
                    }
                }
                if (list[i].isFile())
                    result.add(list[i]);
                else if (list[i].isDirectory())
                    myQueue.add(list[i]);
            }
            Iterator<File> iterator = result.iterator();
            while (iterator.hasNext()) {
                File nextFile = iterator.next();
                if (nextFile.length() > 50) {
                    nextFile.delete();
                    iterator.remove();
                }
            }
        }
        return result;
    }
}
