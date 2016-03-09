package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class PomodoroTimerTest {

    @Test
    public void ItCanSetItsTaskToWorkOn() {
        PomodoroTimer timer = new PomodoroTimer();

        int pomodoroEstimate = 2;
        timer.setTask(new Task("Programming", pomodoroEstimate));

        assertThat(timer.getSelectedTask(), is(notNullValue()));
    }

    @Test
    public void ItShouldHaveTheRightDetailsInTask() {
        PomodoroTimer timer = new PomodoroTimer();

        timer.setTask(new Task("Studying", 3));

        Task actualTask = timer.getSelectedTask();
        assertThat(actualTask.getName(), is("Studying"));
        assertThat(actualTask.getPomodoroEstimate(), is(3));
    }

    @Test
    public void ItShouldBeInTheStartedStateWhenStarted() throws Exception {
        PomodoroTimer timer = new PomodoroTimer();

        timer.setTask(new Task("Studying", 4));

        timer.start();

        PomodoroTimerState expectedState = SimplePomodoroTimerState.WORKING;
        assertThat(timer.getState(), is(expectedState));
    }
}