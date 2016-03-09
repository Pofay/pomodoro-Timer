package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public enum SimplePomodoroTimerState implements PomodoroTimerState {
    WORKING {
        @Override
        public void execute(Task task) {
            for (int i = 0; i < task.getTimeEstimateInSeconds(); ++i) {
                task.addTime();
            }
        }
    }

}
