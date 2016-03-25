package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public class TaskSpy implements Task {
    public boolean addTimeWasCalled;

    @Override
    public Long getTimeEstimateInSeconds() {
        return null;
    }

    @Override
    public void addTime(long time) {
        addTimeWasCalled = true;
    }

    @Override
    public long getTimeSpent() {
        return 0;
    }
}
