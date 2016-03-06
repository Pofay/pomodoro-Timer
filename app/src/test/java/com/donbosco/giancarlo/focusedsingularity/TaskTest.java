package com.donbosco.giancarlo.focusedsingularity;

import android.os.CountDownTimer;

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
        assertThat(task.elapsedDuration, is(2));
    }

    @Test
    public void ItShouldBeAbleToResetItsElapsedDuration() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 5;
        task.setBurstDuration(durationInMinutes);

        task.run();

        task.reset();
        assertThat(task.elapsedDuration, is(0));
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

    @Test
    public void ItShouldIncrementItsScoreAfterARun() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 5;
        task.setBurstDuration(durationInMinutes);

        task.run();

        assertThat(task.getScore(), is(1));
    }

    @Test
    public void ItShouldPerformA5MinBreakAfterCompletingARun() {
        FakeTask task = new FakeTask();

        int durationInMinutes = 8;
        task.setBurstDuration(durationInMinutes);

        task.run();

        assertThat(task.tickSequence, is("1s2s3s4s5s6s7s8s"));
        assertThat(task.breakSequence, is("1s2s3s4s5s"));
    }
}
