package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/6/2016.
 */
public class PomodoroTimer implements Runnable {

    private Task currentTask;
    private Long pomodoroDuration;
    private TimerState state;

    public PomodoroTimer() {
        this.state = PomodoroTimerState.WORKING;
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getSelectedTask() {
        return currentTask;
    }

    public void start() {
        executeTimer();
    }

    public void setState(TimerState state) {
        this.state = state;
    }

    protected void executeTimer() {
        run();
    }

    @Override
    public void run() {
        state.start(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void setPomodoroDuration(long pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public Long getPomodoroDuration() {
        return pomodoroDuration;
    }

    public void performCountDown() {
        for (int i = 0; i < 6; i++)
            tick();

    }

    protected void tick() {
        currentTask.addOneSecond();
    }


}
