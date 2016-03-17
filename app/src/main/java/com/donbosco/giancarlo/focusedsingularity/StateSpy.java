package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public class StateSpy implements TimerState{
    public boolean startWasCalled;
    public boolean stopWasCalled;

    @Override
    public void start(PomodoroTimer timer) {
        startWasCalled = true;
    }

    @Override
    public void stop(PomodoroTimer timer) {
        stopWasCalled = true;
    }
}
