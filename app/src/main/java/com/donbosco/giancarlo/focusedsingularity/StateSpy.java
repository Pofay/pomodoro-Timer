package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public class StateSpy implements TimerState{
    public boolean startWasCalled;

    @Override
    public void start(PomodoroTimer timer) {
        startWasCalled = true;
    }
}
