package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/22/2016.
 */
public class TimerExecutorImpl implements TimerExecutor {

    @Override
    public void start(PomodoroTimer timer) {
        timer.execute();
    }

    @Override
    public void cancel() {

    }
}
