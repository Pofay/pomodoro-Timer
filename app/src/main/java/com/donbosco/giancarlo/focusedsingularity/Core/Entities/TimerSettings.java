package com.donbosco.giancarlo.focusedsingularity.Core.Entities;

/**
 * Created by GianCarlo on 3/22/2016.
 */
public class TimerSettings {
    public final long breakDuration;
    public final long pomodoroDuration;

    public TimerSettings(long pomodoroDuration, long breakDuration) {
        this.pomodoroDuration = pomodoroDuration;
        this.breakDuration = breakDuration;
    }
}
