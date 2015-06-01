package com.javarush.test.threadExample.intGenerator;

/**
 * Created by Тарас on 12.05.2015.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    // Allow this to be canceled:
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
