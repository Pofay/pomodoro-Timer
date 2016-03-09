package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private final int pomodoroEstimate;
    private final String name;
    private boolean started;
    private long timeSpent;

    public PomodoroTask(String name, int pomodoroEstimate) {
        this.name = name;
        this.pomodoroEstimate = pomodoroEstimate;
    }


    public String getName() {
        return name;
    }

    public Integer getPomodoroEstimate() {
        return pomodoroEstimate;
    }

    @Override
    public Long getTimeSpent() {
        return 1000L;
    }

    @Override
    public void addTime() {
        timeSpent += 1000L;
    }
}
