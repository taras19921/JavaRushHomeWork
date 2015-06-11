package com.javarush.test.patterns.mvc;

/**
 * Created by Тарас on 10.06.2015.
 */
public class DJTestDrive
{
    public static void main (String[] args) {
        BeatModelInterface model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}
