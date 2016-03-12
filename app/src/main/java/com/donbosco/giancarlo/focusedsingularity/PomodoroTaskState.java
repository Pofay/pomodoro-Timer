package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public enum PomodoroTaskState implements TaskState {
    WORKING {
        @Override
        public void execute(Task task) {
            for (int i = 0; i < task.getPomodoroDuration(); i += 1000) {
                task.addTimeSpent();
            }
            task.setState(BREAK);
        }
    },
    BREAK {
        @Override
        public void execute(Task task) {

        }
    }

}
