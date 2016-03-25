package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public interface Task {

    Long getTimeEstimateInSeconds();

    void addTime(long time);

    long getTimeSpent();
}
