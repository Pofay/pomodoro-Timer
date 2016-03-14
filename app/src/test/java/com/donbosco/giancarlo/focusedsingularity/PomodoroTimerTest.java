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

        boolean executeTimerWasCalled;
        String ticks = "";

        @Test
        public void ItCallsItsRunMethodOnStart() {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                public void run() {
                    executeTimerWasCalled = true;
                }
            };

            timer.setPomodoroDuration(3000L);
            timer.setTask(new TaskDummy());

            timer.start();

            assertThat(executeTimerWasCalled, is(true));
        }

        @Test
        public void ItShouldPerformTicking() {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                public void run() {
                    ticks += "TsTsTs";
                }
            };

            timer.setTask(new TaskDummy());
            timer.setPomodoroDuration(3000L);

            timer.start();
            assertThat(ticks, is("TsTsTs"));
        }

        @Test
        public void ItsStateShouldBeSettable() {
            PomodoroTimer timer = new PomodoroTimer();
            timer.setState(SimpleTimerState.WORKING);

            TimerState workingState = SimpleTimerState.WORKING;
            assertThat(timer.getState(), is(workingState));
        }
    }

}