package com.donbosco.giancarlo.focusedsingularity.Core.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer implements Subject {

    private Task currentTask;
    private TimerState state;
    private TimerSettings settings;
    private TimerExecutor executor;
    private List<Observer> observers;
    protected OutputPort outputPort;
    private boolean started;
    private int count;

    public PomodoroTimer() {
        this(new TimerExecutor() {

            @Override
            public void start(PomodoroTimer timer) {
                timer.execute();
            }

            @Override
            public void cancel() {

            }
        });

    }

    public PomodoroTimer(TimerExecutor executor) {
        this.state = PomodoroTimerState.WORKING;
        this.executor = executor;
        this.settings = new TimerSettings(5L, 5L);
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
        started = true;
        executor.start(this);
    }

    public void execute() {
        state.execute(this);
    }

    public void cancel() {
        started = false;
        executor.cancel();
        notifyObserver("00:00");
    }


    public void pomodoroCycle() {
        long millis = 0;
        long timeRan = countDown(millis);
        currentTask.addTime(timeRan);
        outputPort.setPomodoroCount(String.valueOf(count++));
    }

    public void breakCycle() {
        long millis = 0;
        countDown(millis);
    }

    private long countDown(long millis) {
        long timeRan = 0;
        for (; timeRan < settings.pomodoroDuration && started; timeRan++) {
            String timeFormat = parse(timeRan);
            notifyObserver(timeFormat);
            sleep(1);
        }
        return timeRan;
    }

    protected String parse(long millis) {
        return String.format("%d", millis);
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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void registerOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public void notifyObserver(String timeChanged) {
        if (observers.size() != 0)
            updateObservers(timeChanged);
    }

    private void updateObservers(String timeChanged) {
        for (Observer observer : observers)
            observer.update(timeChanged);
    }
}
