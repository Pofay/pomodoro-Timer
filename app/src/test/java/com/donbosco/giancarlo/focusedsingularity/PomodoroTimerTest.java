package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/2/2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class PomodoroTimerTest {


    public class BasicSetUpContext {
        @Test
        public void ItCanSetItsTaskToWorkOn() {
            PomodoroTimer timer = new PomodoroTimer();

            int timeEstimateInSeconds = 25000;
            timer.setTask(new PomodoroTask("Programming", timeEstimateInSeconds));

            assertThat(timer.getSelectedTask(), is(notNullValue()));
        }

        @Test
        public void ItShouldHaveTheRightDetailsInTask() {
            PomodoroTimer timer = new PomodoroTimer();

            int timeEstimateInSeconds = 30000;
            timer.setTask(new PomodoroTask("Studying", timeEstimateInSeconds));

            PomodoroTask actualTask = timer.getSelectedTask();
            assertThat(actualTask.getName(), is("Studying"));
            assertThat(actualTask.getTimeEstimateInSeconds(), is(30000L));
        }

        @Test
        public void ItCanHaveItsPomodoroDurationSet() {
            PomodoroTimer timer = new PomodoroTimer();

            timer.setPomodoroDuration(20L);

            assertThat(timer.getPomodoroDuration(), is(20L));
        }
    }


}