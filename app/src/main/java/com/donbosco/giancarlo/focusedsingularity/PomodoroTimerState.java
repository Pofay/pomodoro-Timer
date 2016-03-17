package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public enum PomodoroTimerState implements TimerState{

    WORKING{
        @Override
        public void start(PomodoroTimer timer) {
            timer.performCountDown();

        }

        @Override
        public void stop(PomodoroTimer timer) {

        }
    }
}
