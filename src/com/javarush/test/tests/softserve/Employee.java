package com.javarush.test.tests.softserve;

import java.io.*;
import java.util.*;

/**
 * Created by Тарас on 03.06.2015.
 */

public class Employee implements Serializable
{
    private String name;
    private double experience;
    private double rate;

    public Employee(String name, double rate, double experience)
    {
        this.rate = rate;
        this.name = name;
        this.experience = experience;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public double getExperience()
    {
        return experience;
    }

    public double getSalary()
    {
        double salary = experience * rate;
        return salary;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setExperience(double experience)
    {
        this.experience = experience;
    }

    public String getName()
    {
        return name;
    }

    class Manager extends Employee {

        private double bonus;

        public Manager(String name, double rate, double experience, double bonus)
        {
            super(name, rate, experience);
            this.bonus = bonus;
        }

        public void setBonus(double bonus)
        {
            this.bonus = bonus;
        }

        @Override
        public double getSalary()
        {
            return super.getSalary() + bonus;
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Double> getSalaryEmployee = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("F", 5, 11));
        employees.add(new Employee("B", 4, 5));
        employees.add(new Employee("A", 2, 3));
        employees.add(new Employee("A", 5, 8));
        employees.add(new Employee("M", 5, 8));
        employees.add(new Employee("O", 1, 2));
        employees.add(new Employee("R", 3, 15));
        employees.add(new Employee("K", 5, 8));

        // список зарплат
        for (Employee e : employees)
        {
            getSalaryEmployee.add(e.getSalary());
        }

        //Масив відсортрованих зарплат
        Double sortSalary[] = new Double[getSalaryEmployee.size()];
        sortSalary = getSalaryEmployee.toArray(sortSalary);

        // сортування зарплат min to max
        for(int i = 0; i < sortSalary.length; i++)
        {
            for(int j = i + 1; j < sortSalary.length; j++)
            {
                if (sortSalary[j] < sortSalary[i])
                {
                    double max = sortSalary[i];
                    sortSalary[i] = sortSalary[j];
                    sortSalary[j]  = max;
                }
            }
        }

        //Колекція відсортрованих зарплат
        ArrayList<Double> sortList = new ArrayList(Arrays.asList(sortSalary));
            for(int i = 0; i < sortList.size(); i++)
            {
                for(int j = i + 1; j < sortList.size(); j++)
                {
                    //Пошук однакових зарплат
                    if (sortList.get(i).doubleValue() == sortList.get(j).doubleValue())
                    {
                        for (Employee e : employees)
                        {
                            if (e.getSalary() == sortList.get(i).doubleValue()) {
                                System.out.print(e.getName() + " ");
                            }
                        }
                        // видалення однакових зарплат
                        double sameSalary = sortList.get(i).doubleValue();
                        for(int k = 0; k < sortList.size(); k++)
                        {
                            if (sortList.get(k).doubleValue() == sameSalary)
                            {
                                sortList.remove(sortList.get(k));
                                k--;
                            }
                        }
                    }
                    continue;
                }
                System.out.print(sortList.get(i) + " ");
            }

        // Серіалізація масиву
        //В программе создается объект класса FileOutputStream, который ссылается
        //на файл по имени "serial", и для этого файлового потока создается объект класса ObjectOutputStream.
        //Класс ObjectOutputStream отвечает за запись объектов из потока
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("serial")))
            {
                //Метод writeObject () используется для сериализации объекта
                outputStream.writeObject(sortList);

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        System.out.println();

        // Десериализация объекта
        //Класс ObjectInputStream отвечает за чтение объектов из потока
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("serial")))
        {
            ArrayList<Double> readList = (ArrayList<Double>)inputStream.readObject();
                System.out.println("Після десереалізації " + readList);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        /*
        //Колекція зарплат
        System.out.println(getSalaryEmployee);

        //Колекція відсортованих зарплат
        for(int i = 0; i < sortSalary.length; i++)
        {
            System.out.print(sortSalary[i] + " ");
        }

        System.out.println();
        */
    }
}
