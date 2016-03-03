package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class FakeTask implements Task {
    private String tickSequence;
    public int durationRan;
    private int burstDuration;
    private boolean started = true;


    public FakeTask() {
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

    @Override
    public void reset() {
        durationRan = 0;
    }

    public String getTickSequence() {
        return tickSequence;
    }
}
