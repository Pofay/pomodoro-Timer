package com.donbosco.giancarlo.focusedsingularity;

import com.donbosco.giancarlo.focusedsingularity.Core.Entities.PomodoroTimer;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.TimerExecutor;

/**
 * Created by GianCarlo on 3/22/2016.
 */
public class TimerExecutorCancelSpy implements TimerExecutor {
    public boolean cancelWasCalled;

    @Override
    public void start(PomodoroTimer timer) {
        timer.execute();
    }

    @Override
    public void cancel() {
        cancelWasCalled = true;
    }
}
