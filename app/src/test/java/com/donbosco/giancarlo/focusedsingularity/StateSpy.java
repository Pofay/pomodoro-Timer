package com.donbosco.giancarlo.focusedsingularity;

import com.donbosco.giancarlo.focusedsingularity.Core.Entities.PomodoroTimer;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.TimerState;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public class StateSpy implements TimerState {
    public boolean executeWasCalled;

    @Override
    public void execute(PomodoroTimer timer) {
        executeWasCalled = true;
    }

}
