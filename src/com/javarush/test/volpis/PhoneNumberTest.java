package com.javarush.test.volpis;

/**
 * Created by Тарас on 28.10.2015.
 */
public class PhoneNumberTest
{
    public static void main(String[] args)
    {
        //Only Digit Number

        String phoneNumber = "0636362578";
        char[] num_array = phoneNumber.toCharArray();
        StringBuffer deleteChar = new StringBuffer(phoneNumber);
        String onlyDigit = null;
        int k = 0;
        for (int i = 0; i < num_array.length; i++) {
            if (!Character.isDigit(num_array[i])) {
                int j = i;
                j = j - k;
                deleteChar.deleteCharAt(j);
                k++;
            }
            onlyDigit = String.valueOf(deleteChar);
        }
        System.out.println(onlyDigit);


        //verify 10 digit for PhoneNumber
        /*
        int countNumber = 10;
        String phoneNumber = "380672222222";
        String tenDigit = phoneNumber;
        if (phoneNumber.length() > countNumber)
            tenDigit = phoneNumber.substring(phoneNumber.length() - 10);
        /*
        String tenDigit = null;
        if (phoneNumber.length() > countNumber)
            tenDigit = phoneNumber.substring(phoneNumber.length() - 10, phoneNumber.length());
            */
        //System.out.println(tenDigit);
        /*char[] num_array = phoneNumber.toCharArray();

        StringBuffer deleteChar = new StringBuffer(phoneNumber);
        String tenDigit;
        int k = 0;
        for (int i = 0; i < num_array.length; i++)
        {
            int count = num_array.length - i;
            if (count == 10)
                break;
            int j = i;
            j = j - k;
            deleteChar.deleteCharAt(j);
            k++;
        }
        tenDigit = String.valueOf(deleteChar);
        System.out.println(tenDigit);
        //*/
        //System.out.println(number.length());
        /*
        System.out.println(num_array.length);
         */
        /*
        StringBuffer deleteChar = new StringBuffer(number);
        deleteChar.deleteCharAt(num_array[0]);
        System.out.println(deleteChar);
        */

        /*
        for (int i = 0; i < num_array.length; i++) {
            if ((num_array.length - i) == countNumber)
            {
                System.out.print(num_array[i]);
            }
        }
        */
    }
}
