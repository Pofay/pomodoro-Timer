package com.donbosco.giancarlo.focusedsingularity;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private final long timeEstimateInSeconds;
    private final String name;
    private long timeSpent;
    private TaskState state;

    public PomodoroTask(String name, long timeEstimateInSeconds) {
        this.name = name;
        this.timeEstimateInSeconds = timeEstimateInSeconds;
        state = PomodoroTaskState.WORKING;
    }

    public String getName() {
        return name;
    }

    public Long getTimeEstimateInSeconds() {
        return timeEstimateInSeconds;
    }

    @Override
    public TaskState getCurrentState() {
        return PomodoroTaskState.WORKING;
    }

    @Override
    public void start() {
        state.execute(this);
    }

    @Override
    public Long getTimeSpent() {
        return timeSpent;
    }

    @Override
    public void addTimeSpent() {
        timeSpent += 1000L;
        sleep(1);
    }

    protected void sleep(int timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
