package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
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

    public class TimerToTimerStateInteractionContext {

        private PomodoroTimer timer;
        private StateSpy stateSpy;

        @Before
        public void setUp() {
            timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {
                }

            };
            stateSpy = new StateSpy();
            timer.setState(stateSpy);
        }

        @Test
        public void ItShouldCallStateStartOnTimerStart() {
            timer.start();

            assertThat(stateSpy.startWasCalled, is(true));
        }
    }

    public class TimerToTaskInteractionContext {

        private PomodoroTimer timer;
        private TaskSpy taskSpy;

        @Before
        public void setUp() {
            timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
            taskSpy = new TaskSpy();
            timer.setTask(taskSpy);
        }

        @Test
        public void ItShouldBeAbleToCallTick6Times() throws Exception {
            timer.setPomodoroDuration(6000L);

            timer.start();

            assertThat(taskSpy.numberOfTickCalls, is(6));
        }
    }

    public class AddingTimeToTaskContext {
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

    public class InitialTickingContext {

        String sequence = "";
        PomodoroTimer timer;

        @Before
        public void WithATimerSetTo4Seconds() {
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

            timer.setPomodoroDuration(4000L);
        }

        @Test
        public void ItShouldPerformAWorkingCountDownOnStart() throws Exception {
            timer.start();

            assertThat(sequence, is("TsTsTsTs"));
        }
    }

    public class BreakTickingContext {

        private PomodoroTimer timer;

        String sequence = "";

        @Before
        public void WithATimerThatHasAlreadyPerformedItsWorkingCountDown() {
            timer = new PomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {
                    sequence += "s";
                }

            };

            timer.setPomodoroDuration(0L);

            timer.setBreakDuration(4000L);

            timer.start();
        }

        @Test
        public void ItShouldPerformBreakCountDownOn2ndStart() {
            timer.start();

            assertThat(sequence, is("ssss"));
        }

    }

}