package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class FakeTask implements Task {
    public String tickSequence;
    public int elapsedDuration;
    private int burstDuration;
    private boolean started;


    public FakeTask() {
        reset();
        tickSequence = "";
    }

    public void run() {
        while ((elapsedDuration < burstDuration) && started) {
            tickSequence += String.valueOf(++elapsedDuration);
            sleep();
        }
    }

    protected void sleep() {
        tickSequence += "s";
    }

    public boolean isRunnable() {
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

    @Override
    public void reset() {
        started = true;
        elapsedDuration = 0;
    }

}
