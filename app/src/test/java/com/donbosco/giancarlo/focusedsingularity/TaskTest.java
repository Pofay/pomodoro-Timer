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
    public void ItShouldBeReadyToStartOnCreation() {
        FakeTask task = new FakeTask();

        assertThat(task.isRunnable(), is(true));
    }

    @Test
    public void ItShouldBeAbleToStop() {
        FakeTask task = new FakeTask();

        task.stop();

        assertThat(task.isRunnable(), is(false));
    }

    @Test
    public void ItsWorkingDurationIsSettable() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 25;

        task.setBurstDuration(durationInMinutes);

        assertThat(task.getBurstDuration(), is(25));
    }

    @Test
    public void ItShouldSleepInBetweenTicks() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 2;
        task.setBurstDuration(durationInMinutes);

        task.run();

        String expectedTickSequence = "1s2s";
        assertThat(task.tickSequence, is(expectedTickSequence));
        assertThat(task.durationRan, is(2));
    }

    @Test
    public void ItShouldBeAbleToResetItsRanDuration() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 5;
        task.setBurstDuration(durationInMinutes);

        task.run();

        task.reset();
        assertThat(task.durationRan, is(0));
    }

    @Test
    public void ItIsNoLongerRunnableOnStop() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 9;
        task.setBurstDuration(durationInMinutes);

        task.stop();
        task.run();

        assertThat(task.tickSequence, is(""));
        assertThat(task.isRunnable(), is(false));
    }

    @Test
    public void ItShouldBeRunnableAfterResetting() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 5;
        task.setBurstDuration(durationInMinutes);
        task.stop();

        task.reset();

        assertThat(task.isRunnable(), is(true));
    }
}
