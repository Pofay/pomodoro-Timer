package com.donbosco.giancarlo.focusedsingularity;

import com.donbosco.giancarlo.focusedsingularity.Core.Entities.Task;

/**
 * Created by GianCarlo on 3/14/2016.
 */
public class TaskDummy implements Task {
    @Override
    public Long getTimeEstimateInSeconds() {
        return null;
    }

    @Override
    public void addTime(long time) {

    }


    @Override
    public long getTimeSpent() {
        return 0;
    }
}
