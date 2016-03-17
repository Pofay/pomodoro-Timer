package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/17/2016.
 */
public class TaskSpy implements Task{
    public int numberOfTickCalls;

    @Override
    public Long getTimeEstimateInSeconds() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void setState(TaskState state) {

    }


    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void addOneSecond() {
        numberOfTickCalls++;
    }
}
