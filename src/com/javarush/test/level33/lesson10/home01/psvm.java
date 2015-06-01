package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Тарас on 12.05.2015.
 */
public class psvm
{
    public static void main(String[] args) throws IOException, JAXBException
    {
        Shop shop=new Shop();
        shop.names.add("S1");
        shop.names.add("S2");
        shop.count=12;
        shop.profit=123.4;
        shop.secretData.add("String1");
        shop.secretData.add("String2");
        shop.secretData.add("String3");
        shop.secretData.add("String4");
        shop.secretData.add("String5");
        StringWriter writer = new StringWriter();
        convertToXml(writer, shop);
        System.out.println(writer.toString());
        //toXmlWithComment(shop, "second", "it's a comment");
    }


    /*
     <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<shop>
    <goods>
        <names>S1</names>
        <names>S2</names>
    </goods>
    <count>12</count>
    <profit>123.4</profit>
    <secretData>String1</secretData>
    <secretData>String2</secretData>
    <secretData>String3</secretData>
    <secretData>String4</secretData>
    <secretData>String5</secretData>
</shop>

    */


    public static void convertToXml(StringWriter writer, Object obj) throws IOException, JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
    }
}