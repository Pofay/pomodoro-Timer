package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public enum PomodoroTimerState implements TimerState {

    BREAK {
        @Override
        public void start(PomodoroTimer timer) {
            timer.performBreakCountDown();
            timer.setState(WORKING);
        }

        @Override
        public void stop(PomodoroTimer timer) {

        }
    },

    WORKING {
        @Override
        public void start(PomodoroTimer timer) {
            timer.performCountDown();
            timer.setState(BREAK);
            timer.start();
        }

        @Override
        public void stop(PomodoroTimer timer) {

        }
    }
}
