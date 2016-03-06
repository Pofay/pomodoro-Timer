package com.donbosco.giancarlo.focusedsingularity;

import android.os.CountDownTimer;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class FakeTask implements Task {
    public String tickSequence;
    public int elapsedDuration;
    private int elapsedBreakDuration;
    private int burstDuration;
    private boolean started;
    private int score;
    public String breakSequence;


    public FakeTask() {
        reset();
        tickSequence = "";
        breakSequence = "";

    }

    public void run() {
        while ((elapsedDuration < burstDuration) && started) {
            tickSequence += String.valueOf(++elapsedDuration);
            sleep();
        }
        score++;
        while ((elapsedBreakDuration < 5 && started)) {
            breakSequence += String.valueOf(++elapsedBreakDuration);
            breakSleep();
        }
    }

    protected void sleep() {
        tickSequence += "s";
    }

    protected void breakSleep() {
        breakSequence += "s";
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

    public int getScore() {
        return score;
    }
}
