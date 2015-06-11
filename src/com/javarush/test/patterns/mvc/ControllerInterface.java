package com.javarush.test.patterns.mvc;

/**
 * Created by Тарас on 10.06.2015.
 */
public interface ControllerInterface
{
    void start();
    void stop();
    void increaseBPM();
    void decreaseBPM();
    void setBPM(int bpm);
}
