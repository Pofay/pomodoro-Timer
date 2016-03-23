package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/22/2016.
 */
public class TimerExecutorCancelSpy implements TimerExecutor{
    public boolean cancelWasCalled;

    @Override
    public void start(FakePomodoroTimer timer) {
        timer.execute();
    }

    @Override
    public void cancel() {
        cancelWasCalled = true;
    }
}
