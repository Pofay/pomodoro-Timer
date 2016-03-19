package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
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

                @Override
                protected void sleep(int timeOut) {
                }
            };

            timer.setPomodoroDuration(3000L);
            timer.setTask(new TaskDummy());

            timer.start();

            assertThat(runWasCalled, is(true));
        }

        @Test
        public void ItShouldCallStateStartOnTimerStart() {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {
                }

            };
            StateSpy stateSpy = new StateSpy();

            timer.setState(stateSpy);
            timer.setTask(new TaskDummy());
            timer.setPomodoroDuration(6000L);

            timer.start();
            assertThat(stateSpy.startWasCalled, is(true));
        }

        @Test
        public void ItShouldCallStateStopOnTimerStop() throws Exception {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };

            StateSpy stateSpy = new StateSpy();

            timer.setState(stateSpy);
            timer.setTask(new TaskDummy());
            timer.setPomodoroDuration(7000L);

            timer.stop();
            assertThat(stateSpy.stopWasCalled, is(true));
        }

        @Test
        public void ItShouldBeAbleToCallTick6Times() throws Exception {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
            TaskSpy taskSpy = new TaskSpy();

            timer.setTask(taskSpy);
            timer.setPomodoroDuration(6000L);

            timer.start();

            assertThat(taskSpy.numberOfTickCalls, is(6));
        }

        @Test
        public void ItShouldBeAbleToCallTick7Times() {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
            TaskSpy taskSpy = new TaskSpy();

            timer.setTask(taskSpy);
            timer.setPomodoroDuration(7000L);

            timer.start();

            assertThat(taskSpy.numberOfTickCalls, is(7));
        }
    }

    public class AddingTimeSpentToTaskContext {
        @Test
        public void ItShouldAddTimeToATaskBasedOnPomodoroDuration() throws Exception {
            PomodoroTimer timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
            Task task = new PomodoroTask("Programming", 8000L);

            timer.setTask(task);
            timer.setPomodoroDuration(4000L);

            timer.start();

            assertThat(task.getTimeSpent(), is(4000L));
        }
    }

    public class TickingContext {

        String sequence = "";
        PomodoroTimer timer;

        @Before
        public void setUp() {
            timer = new PomodoroTimer() {
                @Override
                protected void tick() {
                    sequence += "T";
                }

                @Override
                protected void sleep(int timeOut) {
                    sequence += "s";
                }

            };

            timer.setTask(new TaskDummy());
        }

        @Test
        public void ItShouldTickAndSleepOnCountDown() throws Exception {
            timer.setPomodoroDuration(4000L);

            timer.performCountDown();
            assertThat(sequence, is("TsTsTsTs"));
        }

        @Test
        public void ItShouldPerformSleepingTicksOnPerformBreakCountDown() throws Exception {
            timer.setBreakDuration(5000L);

            timer.performBreakCountDown();

            assertThat(sequence, is("sssss"));
        }


        @Test
        public void PerformNormalTickingAndBreakTicking() throws Exception {
            timer.setBreakDuration(3000L);

            timer.setPomodoroDuration(3000L);

            timer.start();

            assertThat(sequence,is("TsTsTssss"));
        }

    }
}