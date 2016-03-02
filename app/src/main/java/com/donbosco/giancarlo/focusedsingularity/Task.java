package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class Task {
    private boolean started;

    public void start() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        started = false;
    }
}
