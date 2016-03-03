package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class TaskTest {

    @Test
    public void ItShouldBeReadyToStartOnCreation() {
        Task task = new Task();

        assertThat(task.isCancelled(), is(true));
    }

    @Test
    public void ItShouldBeAbleToStop() {
        Task task = new Task();

        task.stop();

        assertThat(task.isCancelled(), is(false));
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

        task.run();

        String expectedTickSequence = "1s";
        assertThat(task.getTickSequence(), is(expectedTickSequence));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinuteForA2MinBurst() {
        Task task = new Task();

        int durationInMinutes = 2;
        task.setBurstDuration(durationInMinutes);

        task.run();

        assertThat(task.getTickSequence(), is("1s2s"));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinForA3MinBurst() {
        Task task = new Task();

        int durationInMinutes = 3;
        task.setBurstDuration(durationInMinutes);

        task.run();

        assertThat(task.getTickSequence(), is("1s2s3s"));
    }
}
