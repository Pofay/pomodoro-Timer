package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private PomodoroTask currentTask;
    private Long pomodoroDuration;

    public void setTask(PomodoroTask task) {
        this.currentTask = task;
    }

    public PomodoroTask getSelectedTask() {
        return currentTask;
    }

    public void start() {

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
