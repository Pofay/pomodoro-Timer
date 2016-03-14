package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public enum SimpleTimerState implements TimerState {
    WORKING {
        @Override
        public void start(PomodoroTimer timer) {

        }
    }
}
