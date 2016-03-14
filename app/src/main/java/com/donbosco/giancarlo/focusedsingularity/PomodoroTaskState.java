package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public enum PomodoroTaskState implements TaskState {
    WORKING {
        @Override
        public void execute(Task task) {
            task.performPomodoroCountDown();
            task.setState(BREAK);
        }

        @Override
        public void stop(Task task) {
            task.setState(STOPPED);
        }
    },
    BREAK {
        @Override
        public void execute(Task task) {

        }

        @Override
        public void stop(Task task) {
            task.setState(STOPPED);
        }
    },
    STOPPED {
        @Override
        public void execute(Task task) {
        }

        @Override
        public void stop(Task task) {
        }
    }

}
