package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/3/2016.
 */
public interface Task extends Runnable {

    boolean isRunnable();

    void stop();

    void setBurstDuration(int durationInMinutes);

    int getBurstDuration();

    void reset();
}
