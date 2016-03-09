package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private PomodoroTask currentTask;
    private PomodoroTimerState state;
    private Long pomodoroDuration;

    public void setTask(PomodoroTask task) {
        this.currentTask = task;
    }

    public PomodoroTask getSelectedTask() {
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

    public void setPomodoroDuration(long pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public Long getPomodoroDuration() {
        return pomodoroDuration;
    }
}
