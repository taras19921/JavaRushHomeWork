package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тарас on 12.05.2015.
 */

@XmlType(name = "shop")
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name = "goods",nillable = true)
    public List<String> names = new ArrayList<String>();
    public int count;
    public double profit;
    public List<String> secretData = new ArrayList<String>();

    public Shop()
    {

    }
}