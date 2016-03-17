package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public class TaskSpy implements Task {
    public int numberOfTickCalls;

    @Override
    public Long getTimeEstimateInSeconds() {
        return null;
    }

    @Override
    public void addOneSecond() {
        numberOfTickCalls++;
    }

    @Override
    public long getTimeSpent() {
        return 0;
    }
}
