package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public interface Task {

    Long getTimeSpent();

    void addTimeSpent();

    Long getTimeEstimateInSeconds();
}
