package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class Task {
    private String tickSequence;
    private int ranDuration = 1;
    private int burstDuration;
    private boolean started = true;

    public Task() {
        tickSequence = "";
    }

    public void run() {
        while ((ranDuration <= burstDuration) && started) {
            tickSequence += String.valueOf(ranDuration++);
            sleep();
        }
    }

    protected void tick() {
    }

    protected void sleep() {
        tickSequence += "s";
    }

    public boolean isCancelled() {
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

    public String getTickSequence() {
        return tickSequence;
    }
}
