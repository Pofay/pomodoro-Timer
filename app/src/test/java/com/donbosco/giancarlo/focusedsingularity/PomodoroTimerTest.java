package com.donbosco.giancarlo.focusedsingularity;

import android.os.CountDownTimer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
    public void ItShouldBeAbleToStartItsCurrentTask() {
        PomodoroTimer timer = new PomodoroTimer();

        Task actualTask = new Task("Some Task", 4);

        timer.setTask(actualTask);

        timer.start();

        assertThat(actualTask.isStarted(), is(true));
    }

    // Needs to be moved to another context
    @Test
    public void ItShouldBeAbleToStopItsCurrentTask() {
        PomodoroTimer timer = new PomodoroTimer();

        Task actualTask = new Task("Code", 2);

        timer.setTask(actualTask);

        timer.start();
        assertThat("Should Start Task",actualTask.isStarted(), is(true));
        timer.stop();
        assertThat("Should Stop Task",actualTask.isStarted(), is(false));
    }


}