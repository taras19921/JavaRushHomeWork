package com.javarush.test.patterns.mvc;

/**
 * Created by Тарас on 10.06.2015.
 */
public class HeartController
{
    HeartModelInterface model;
    DJView view;

    /*public HeartController(HeartModelInterface model) {
        this.model = model;
        view = new DJView(this, new HeartAdapter(model));
        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
    }*/

    public void start() {}

    public void stop() {}

    public void increaseBPM() {}

    public void decreaseBPM() {}

    public void setBPM(int bpm) {}
}
