package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class Task {

    private final int pomodoroEstimate;
    private final String name;
    private boolean started;

    public Task(String name, int pomodoroEstimate) {
        this.name = name;
        this.pomodoroEstimate = pomodoroEstimate;
    }

    public String getName() {
        return name;
    }

    public Integer getPomodoroEstimate() {
        return pomodoroEstimate;
    }

    public boolean isStarted() {
        return started;
    }

    public void requestStart() {
        started = true;
    }

    public void requestStop() {
        started = false;
    }
}
