package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private final long pomodoroEstimate;
    private final String name;
    private boolean started;
    private long timeSpent;

    public PomodoroTask(String name, long timeEstimateInSeconds) {
        this.name = name;
        this.pomodoroEstimate = timeEstimateInSeconds;
    }


    public String getName() {
        return name;
    }

    public Long getTimeEstimateInSeconds() {
        return pomodoroEstimate;
    }

    @Override
    public Long getTimeSpent() {
        return timeSpent;
    }

    @Override
    public void addTime() {
        timeSpent += 1000L;
    }
}
