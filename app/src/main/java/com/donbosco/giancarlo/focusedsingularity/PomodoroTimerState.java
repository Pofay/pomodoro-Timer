package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public enum PomodoroTimerState implements TimerState {

    BREAK {
        @Override
        public void execute(PomodoroTimer timer) {
            timer.performBreakCountDown();
            timer.setState(WORKING);
            timer.cancel();
        }
    },

    WORKING {
        @Override
        public void execute(PomodoroTimer timer) {
            timer.performCountDown();
            timer.setState(BREAK);
        }
    }
}
