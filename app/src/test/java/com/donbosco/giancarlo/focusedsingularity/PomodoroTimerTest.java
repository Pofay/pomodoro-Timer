package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;

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

        int timeEstimateInSeconds = 2;
        timer.setTask(new PomodoroTask("Programming", timeEstimateInSeconds));

        assertThat(timer.getSelectedTask(), is(notNullValue()));
    }

    @Test
    public void ItShouldHaveTheRightDetailsInTask() {
        PomodoroTimer timer = new PomodoroTimer();

        int timeEstimateInSeconds = 3;
        timer.setTask(new PomodoroTask("Studying", timeEstimateInSeconds));

        PomodoroTask actualTask = timer.getSelectedTask();
        assertThat(actualTask.getName(), is("Studying"));
        assertThat(actualTask.getTimeEstimateInSeconds(), is(3L));
    }

}