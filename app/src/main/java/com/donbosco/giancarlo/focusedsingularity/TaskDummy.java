package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public class TaskDummy implements Task {
    @Override
    public Long getTimeEstimateInSeconds() {
        return null;
    }

    @Override
    public void setState(TaskState state) {

    }


    @Override
    public void addOneSecond() {

    }
}
