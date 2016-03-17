package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public interface Task {

    Long getTimeEstimateInSeconds();

    void start();

    void setState(TaskState state);

    void stop();

    void reset();

    void addOneSecond();
}
