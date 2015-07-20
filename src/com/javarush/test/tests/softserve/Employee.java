package com.javarush.test.tests.softserve;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Тарас on 03.06.2015.
 */
public class Employee
{
    private String name;
    private double salary;
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
        salary = experience * rate;
        return salary;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
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

        // список зарплат
        for (Employee e : employees)
        {
            getSalaryEmployee.add(e.getSalary());
        }

        Double arraySalary[] = new Double[getSalaryEmployee.size()];
        arraySalary = getSalaryEmployee.toArray(arraySalary);

        // сортування зарплат max to min
        for(int i = 0; i <  arraySalary.length; i++)
        {
            for(int j = i + 1; j < arraySalary.length; j++)
            {
                if (arraySalary[i] < arraySalary[j])
                {
                    double min = arraySalary[i];
                    arraySalary[i] = arraySalary[j];
                    arraySalary[j]  = min;
                }
            }
        }

        System.out.println(getSalaryEmployee);

        for(int i = 0; i <  arraySalary.length; i++)
        {
            System.out.print(arraySalary[i] + " ");
        }

        // Серіалізація масиву
            try (ObjectOutputStream objOStream =
                          new ObjectOutputStream(new FileOutputStream("serial")))
            {
                objOStream.writeObject(arraySalary);
                FileOutputStream f = new FileOutputStream("filel.txt");
                //f.write(objOStream.);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }



        try
        {
            FileOutputStream f = new FileOutputStream("filel.txt");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}
