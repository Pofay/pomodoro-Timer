package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public interface Task {

    Long getTimeSpent();

    void addTimeSpent();

    Long getTimeEstimateInSeconds();

    TaskState getCurrentState();

    void start();

    void setState(TaskState state);

    long getPomodoroDuration();

    void setPomodoroDuration(long pomodoroDuration);

    void stop();

    void reset();

    int getPomodoroCount();
}
