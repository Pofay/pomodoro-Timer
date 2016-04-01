package com.donbosco.giancarlo.focusedsingularity.Core.Entities;

/**
 * Created by GianCarlo on 3/14/2016.
 */
class PomodoroTaskDetails {
    public String name;
    public long timeEstimateInSeconds;

    public PomodoroTaskDetails(String name, long timeEstimateInSeconds) {
        this.name = name;
        this.timeEstimateInSeconds = timeEstimateInSeconds;
    }
}
