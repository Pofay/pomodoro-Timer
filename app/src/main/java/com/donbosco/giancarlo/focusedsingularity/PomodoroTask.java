package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private long pomodoroDuration;
    private long timeSpent;
    private TaskState state;
    private final PomodoroTaskDetails details;

    public PomodoroTask(String name, long timeEstimateInSeconds) {
        details = new PomodoroTaskDetails(name, timeEstimateInSeconds);
        reset();
    }

    public String getName() {
        return details.name;
    }

    public Long getTimeEstimateInSeconds() {
        return details.timeEstimateInSeconds;
    }

    @Override
    public TaskState getCurrentState() {
        return state;
    }

    @Override
    public void start() {
        state.execute(this);
    }

    @Override
    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public long getPomodoroDuration() {
        return pomodoroDuration;
    }

    @Override
    public void setPomodoroDuration(long pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    @Override
    public void stop() {
        state.stop(this);
    }

    @Override
    public void reset() {
        setState(PomodoroTaskState.WORKING);
    }

    @Override
    public int getPomodoroCount() {
        return 1;
    }

    @Override
    public void performPomodoroCountDown() {
        for (int i = 0; i < pomodoroDuration; i += 1000) {
            tick();
        }
    }

    protected void tick() {
        timeSpent += 1000L;
        sleep(1);
    }

    @Override
    public Long getTimeSpent() {
        return timeSpent;
    }

    protected void sleep(int timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
