package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/22/2016.
 */
public interface TimerExecutor {
    void start(FakePomodoroTimer timer);

    void cancel();
}
