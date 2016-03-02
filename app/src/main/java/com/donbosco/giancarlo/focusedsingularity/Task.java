package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class Task {
    private int burstDuration;
    private boolean started;

    public Task() {
    }

    public void start() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        started = false;
    }

    public void setBurstDuration(int durationInMinutes) {
        burstDuration = durationInMinutes;
    }

    public int getBurstDuration() {
        return burstDuration;
    }

    public int getTicks() {
        return 1;
    }
}
