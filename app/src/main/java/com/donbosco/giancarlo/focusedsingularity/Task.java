package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class Task {
    private String tickSequence;
    private int burstDuration;
    private boolean started;

    public Task() {
        tickSequence = "";
    }

    public void start() {
        started = true;
        tick();
    }

    protected void tick() {
        //  if (burstDuration == 1) {
        //      tickSequence = "1s";
        //  } else if (burstDuration == 2)
        //      tickSequence = "1s2s";
        //  else
        //      tickSequence = "1s2s3s";
        int time = 1;
        while (time <= burstDuration) {
            tickSequence += String.valueOf(time++);
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

    public String getTickSequence() {
        return tickSequence;
    }
}
