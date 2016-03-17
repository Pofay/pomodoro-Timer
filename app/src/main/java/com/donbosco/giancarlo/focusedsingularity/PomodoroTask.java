package com.donbosco.giancarlo.focusedsingularity;

import java.util.concurrent.TimeUnit;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class PomodoroTask implements Task {

    private long pomodoroDuration;
    private long timeSpent;
    private TaskState state;
    private final PomodoroTaskDetails details;

    public PomodoroTask(String name, long timeEstimateInSeconds) {
        details = new PomodoroTaskDetails(name, timeEstimateInSeconds);
        reset();
    }

    public String getName() {
        return details.name;
    }

    public Long getTimeEstimateInSeconds() {
        return details.timeEstimateInSeconds;
    }

    @Override
    public void start() {
        state.execute(this);
    }

    @Override
    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public void stop() {
        state.stop(this);
    }

    @Override
    public void reset() {
        setState(PomodoroTaskState.WORKING);
    }

    @Override
    public void addOneSecond() {
        timeSpent += 1000L;
    }

}
