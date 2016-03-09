package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private Task currentTask;
    private PomodoroTimerState state;

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getSelectedTask() {
        return currentTask;
    }

    public void start() {
        state = SimplePomodoroTimerState.WORKING;
    }

    public void stop() {
    }

    public PomodoroTimerState getState() {
        return state;
    }
}
