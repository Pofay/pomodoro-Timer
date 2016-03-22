package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private Task currentTask;
    private TimerState state;
    private TimerSettings settings;
    private TimerExecutor executor;

    public PomodoroTimer() {
        this(new TimerExecutorImpl());
    }

    public PomodoroTimer(TimerExecutor executor) {
        this.state = PomodoroTimerState.WORKING;
        this.executor = executor;
        this.settings = new TimerSettings(25L, 5L);
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getSelectedTask() {
        return currentTask;
    }


    public void setState(TimerState state) {
        this.state = state;
    }

    public void start() {
        executor.start(this);
    }

    public void execute() {
        state.execute(this);
    }

    public void cancel() {
        executor.cancel();
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

    public Long getPomodoroDuration() {
        return settings.pomodoroDuration;
    }


}
