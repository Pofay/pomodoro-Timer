package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private long timeSpent;
    private final PomodoroTaskDetails details;

    public PomodoroTask(String name, long timeEstimateInSeconds) {
        details = new PomodoroTaskDetails(name, timeEstimateInSeconds);
    }

    public String getName() {
        return details.name;
    }

    public Long getTimeEstimateInSeconds() {
        return details.timeEstimateInSeconds;
    }

    @Override
    public void addTime(long time) {
        timeSpent += time;
    }


    @Override
    public long getTimeSpent() {
        return timeSpent;
    }

}
