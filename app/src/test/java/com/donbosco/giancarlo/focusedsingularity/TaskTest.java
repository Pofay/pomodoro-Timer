package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class TaskTest {

    @Test
    public void ItShouldBeAbleToStart() {
        Task task = new Task();

        task.start();

        assertThat(task.isStarted(), is(true));
    }

    @Test
    public void ItShouldBeAbleToStop() {
        Task task = new Task();

        task.start();
        task.stop();

        assertThat(task.isStarted(), is(false));
    }

    @Test
    public void ItsWorkingDurationIsSettable() {
        Task task = new Task();

        int durationInMinutes = 25;

        task.setBurstDuration(durationInMinutes);

        assertThat(task.getBurstDuration(), is(25));
    }

    @Test
    public void ItShouldBeAbleToTickPerMinuteWithASleepOnStart() {
        Task task = new Task();

        int durationInMinutes = 1;
        task.setBurstDuration(durationInMinutes);

        task.start();

        String expectedTickSequence = "1s";
        assertThat(task.getTickSequence(), is(expectedTickSequence));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinuteForA2MinBurst() {
        Task task = new Task();

        int durationInMinutes = 2;
        task.setBurstDuration(durationInMinutes);

        task.start();

        assertThat(task.getTickSequence(), is("1s2s"));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinForA3MinBurst(){
        Task task = new Task();

        int durationInMinutes = 3;
        task.setBurstDuration(durationInMinutes);

        task.start();

        assertThat(task.getTickSequence(), is("1s2s3s"));
    }
}
