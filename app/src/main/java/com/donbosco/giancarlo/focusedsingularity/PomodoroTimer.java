package com.donbosco.giancarlo.focusedsingularity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer {

    private Task currentTask;
    private TimerState state;
    private TimerSettings settings;
    private TimerExecutor executor;
    private List<Observer> observers;

    public PomodoroTimer() {
        this(new TimerExecutorDummy());

    }

    public PomodoroTimer(TimerExecutor executor) {
        this.state = PomodoroTimerState.WORKING;
        this.executor = executor;
        this.settings = new TimerSettings(25L, 5L);
        observers = new ArrayList<>();
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
        for (int i = 0; i < settings.pomodoroDuration; i++) {
            tick();
            sleep(1);
        }
    }

    public void performBreakCountDown() {
        for (int i = 0; i < settings.breakDuration; i++) {
            sleep(1);
        }
    }

    protected void tick() {
        currentTask.addOneSecond();
        notifyObserver("");
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

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObserver(String timeChanged) {
        if (observers.size() != 0)
            updateObservers(timeChanged);
    }

    private void updateObservers(String timeChanged) {
        for (Observer observer : observers)
            observer.update(timeChanged);
    }
}
