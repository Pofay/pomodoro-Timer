package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private Task currentTask;
    private Long pomodoroDuration;

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getSelectedTask() {
        return currentTask;
    }

    public void start() {
        executeTimer();
    }

    protected void executeTimer() {
    }

    public void stop() {
    }

    public void setPomodoroDuration(long pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public Long getPomodoroDuration() {
        return pomodoroDuration;
    }
}
