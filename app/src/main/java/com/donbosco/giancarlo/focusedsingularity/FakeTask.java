package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class FakeTask implements Task {
    public String tickSequence;
    public int durationRan;
    private int burstDuration;
    private boolean started;


    public FakeTask() {
        started = true;
        tickSequence = "";
    }

    public void run() {
        while ((durationRan < burstDuration) && started) {
            tickSequence += String.valueOf(++durationRan);
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
        durationRan = 0;
    }

}
