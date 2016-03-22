package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private Task currentTask;
    private TimerState state;
    private TimerSettings settings;

    public PomodoroTimer() {
        this.state = PomodoroTimerState.WORKING;
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getSelectedTask() {
        return currentTask;
    }

    public void start() {
        state.start(this);
    }

    public void setState(TimerState state) {
        this.state = state;
    }

    public void stop() {
    }

    public Long getPomodoroDuration() {
        return settings.pomodoroDuration;
    }

    public void performCountDown() {
        for (int i = 0; i < settings.pomodoroDuration / 1000; i++) {
            tick();
            sleep(1);
        }
    }

    public void performBreakCountDown() {
        for (int i = 0; i < settings.breakDuration / 1000; i++) {
            sleep(1);
        }
    }

    protected void tick() {
        currentTask.addOneSecond();
    }


    protected void sleep(int timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTimerSettings(long pomodoroDuration, long breakDuration) {
        this.settings = new TimerSettings(pomodoroDuration, breakDuration);
    }

    public long getBreakDuration() {
        return settings.breakDuration;
    }
}
