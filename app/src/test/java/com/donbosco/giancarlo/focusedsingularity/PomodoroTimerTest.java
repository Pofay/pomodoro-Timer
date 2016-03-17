package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Ignore;
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

        @Ignore
        @Test
        public void ItShouldHaveTheRightDetailsInTask() {
            PomodoroTimer timer = new PomodoroTimer();

            int timeEstimateInSeconds = 30000;
            timer.setTask(new PomodoroTask("Studying", timeEstimateInSeconds));

            Task actualTask = timer.getSelectedTask();
            //assertThat(actualTask.getName(), is("Studying"));
            //assertThat(actualTask.getTimeEstimateInSeconds(), is(30000L));
        }

        @Test
        public void ItCanHaveItsPomodoroDurationSet() {
            PomodoroTimer timer = new PomodoroTimer();

            timer.setPomodoroDuration(20L);

            assertThat(timer.getPomodoroDuration(), is(20L));
        }
    }

    public class TimerExecutionContext {

        boolean runWasCalled;

        @Test
        public void ItCallsItsRunMethodOnStart() {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                public void run() {
                    runWasCalled = true;
                }
            };

            timer.setPomodoroDuration(3000L);
            timer.setTask(new TaskDummy());

            timer.start();

            assertThat(runWasCalled, is(true));
        }

        @Test
        public void ItShouldCallStateStartOnTimerStart() {
            PomodoroTimer timer = new PomodoroTimer();
            StateSpy stateSpy = new StateSpy();

            timer.setState(stateSpy);
            timer.setTask(new TaskDummy());
            timer.setPomodoroDuration(6000L);

            timer.start();
            assertThat(stateSpy.startWasCalled, is(true));
        }

        @Test
        public void ItShouldCallStateStopOnTimerStop() throws Exception {
            PomodoroTimer timer = new PomodoroTimer();
            StateSpy stateSpy = new StateSpy();

            timer.setState(stateSpy);
            timer.setTask(new TaskDummy());
            timer.setPomodoroDuration(7000L);

            timer.stop();
            assertThat(stateSpy.stopWasCalled, is(true));
        }

    }

}