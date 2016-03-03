package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/3/2016.
 */
public interface Task extends Runnable {

    public boolean isStarted();

    public void stop();

    public void setBurstDuration(int durationInMinutes);

    public int getBurstDuration();

    void reset();
}
