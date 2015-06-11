package com.javarush.test.patterns.mvc;

/**
 * Created by Тарас on 10.06.2015.
 */
public interface HeartModelInterface
{
    int getHeartRate();
    void registerObserver(BeatObserver o);
    void removeObserver(BeatObserver o);
    void registerObserver(BPMObserver o);
    void removeObserver(BPMObserver o);
}
