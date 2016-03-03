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
        FakeTask task = new FakeTask();

        assertThat(task.isStarted(), is(true));
    }

    @Test
    public void ItShouldBeAbleToStop() {
        FakeTask task = new FakeTask();

        task.stop();

        assertThat(task.isStarted(), is(false));
    }

    @Test
    public void ItsWorkingDurationIsSettable() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 25;

        task.setBurstDuration(durationInMinutes);

        assertThat(task.getBurstDuration(), is(25));
    }

    @Test
    public void ItShouldBeAbleToTickPerMinuteWithASleepOnStart() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 1;
        task.setBurstDuration(durationInMinutes);

        task.run();

        String expectedTickSequence = "1s";
        assertThat(task.getTickSequence(), is(expectedTickSequence));
        assertThat(task.ranDuration, is(1));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinuteForA2MinBurst() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 2;
        task.setBurstDuration(durationInMinutes);

        task.run();

        String expectedTickSequence = "1s2s";
        assertThat(task.getTickSequence(), is(expectedTickSequence));
        assertThat(task.ranDuration, is(2));
    }

    @Test
    public void ItShouldSleepInBetweenTicksPerMinForA3MinBurst() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 3;
        task.setBurstDuration(durationInMinutes);

        task.run();

        assertThat(task.getTickSequence(), is("1s2s3s"));
        assertThat(task.ranDuration, is(3));
    }

    @Test
    public void ItShouldBeAbleToResetItsRanDurationBackTo1() {

        FakeTask task = new FakeTask();

        int durationInMinutes = 5;
        task.setBurstDuration(durationInMinutes);

        task.run();

        task.reset();
        assertThat(task.ranDuration, is(1));
    }
}
