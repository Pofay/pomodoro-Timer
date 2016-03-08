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
    public void ItCanCreateANewTask() {
        PomodoroTimer timer = new PomodoroTimer();

        timer.setTask(new Task());

        assertThat(timer.getSelectedTask(), is(notNullValue()));
    }

}