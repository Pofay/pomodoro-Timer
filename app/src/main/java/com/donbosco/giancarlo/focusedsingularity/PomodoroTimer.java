package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer implements Runnable {

    private Task currentTask;
    private Long pomodoroDuration;
    private TimerState state;
    private long breakDuration;

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
        executeTimer();
    }

    public void setState(TimerState state) {
        this.state = state;
    }

    protected void executeTimer() {
        run();
    }

    @Override
    public void run() {
        state.start(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void setPomodoroDuration(long pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public Long getPomodoroDuration() {
        return pomodoroDuration;
    }

    public void performCountDown() {
        for (int i = 0; i < pomodoroDuration / 1000; i++) {
            tick();
            sleep(1);
        }
    }

    public void performBreakCountDown() {
        for (int i = 0; i < breakDuration / 1000; i++) {
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

    public void setBreakDuration(long breakDuration) {
        this.breakDuration = breakDuration;
    }
}
