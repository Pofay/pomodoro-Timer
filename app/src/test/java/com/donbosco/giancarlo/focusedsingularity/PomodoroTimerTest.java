package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
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

            long timeEstimateInMinutes = 25L;
            timer.setTask(new PomodoroTask("Programming", timeEstimateInMinutes));

            assertThat(timer.getSelectedTask(), is(notNullValue()));
        }

        @Test
        public void ItCanHavePomodoroDurationAndBreakDurationSet() {
            PomodoroTimer timer = new PomodoroTimer();

            long pomodoroDurationInMinutes = 8L;
            long breakDurationInMinutes = 5L;
            timer.setTimerSettings(pomodoroDurationInMinutes, breakDurationInMinutes);

            assertThat(timer.getPomodoroDuration(), is(8L));
            assertThat(timer.getBreakDuration(), is(5L));
        }
    }

    public class TimerToTimerStateInteractionContext {

        private PomodoroTimer timer;
        private StateSpy stateSpy;

        @Before
        public void setUp() {
            timer = new SleeplessPomodoroTimer();
            stateSpy = new StateSpy();
            timer.setState(stateSpy);
        }

        @Test
        public void ItsExecutionCallsStateExecute() {
            timer.execute();

            assertThat(stateSpy.executeWasCalled, is(true));
        }
    }

    public class TimerToTaskInteractionContext {

        private PomodoroTimer timer;
        private TaskSpy taskSpy;
        private TimerExecutor executor;

        @Before
        public void setUp() {
            executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };
            timer = new SleeplessPomodoroTimer(executor);

            taskSpy = new TaskSpy();
            timer.setTask(taskSpy);

            long pomodoroDurationInMinutes = 6L;
            long breakDurationInMinutes = 3L;

            timer.setTimerSettings(pomodoroDurationInMinutes, breakDurationInMinutes);
        }

        @Test
        public void ItShouldCallAddTime() throws Exception {
            timer.start();

            assertThat(taskSpy.addTimeWasCalled, is(true));
        }
    }

    public class AddingTimeToTaskContext {


        private TimerExecutor executor;
        private PomodoroTimer timer;
        private Task task;

        @Before
        public void setUp() {
            executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };
            timer = new SleeplessPomodoroTimer(executor);

            long timeEstimateInMinutes = 8L;
            task = new PomodoroTask("Programming", timeEstimateInMinutes);
            timer.setTask(task);
        }

        @Test
        public void ItShouldAddTimeToATaskBasedOnPomodoroDuration() throws Exception {
            long pomodoroDurationInMinutes = 4L;
            long breakDurationInMinutes = 0L;

            timer.setTimerSettings(pomodoroDurationInMinutes, breakDurationInMinutes);

            timer.start();

            long expectedTimeSpent = 4L;

            assertThat(task.getTimeSpent(), is(expectedTimeSpent));
        }
    }

    public class TimerExecutorContext {


        @Test
        public void ItShouldExecuteStateOnTimerStart() throws Exception {
            StateSpy stateSpy = new StateSpy();
            TimerExecutorCancelSpy cancelSpy = new TimerExecutorCancelSpy();
            PomodoroTimer timer = new SleeplessPomodoroTimer(cancelSpy);

            timer.setState(stateSpy);

            timer.start();

            assertThat(stateSpy.executeWasCalled, is(true));
        }

        @Test
        public void ItShouldCancelExecutorWhenFinishing2Executions() throws Exception {
            TimerExecutorCancelSpy executorCancelSpy = new TimerExecutorCancelSpy();
            PomodoroTimer timer = new SleeplessPomodoroTimer(executorCancelSpy);

            timer.setTask(new TaskDummy());

            timer.start();
            timer.start();

            assertThat(executorCancelSpy.cancelWasCalled, is(true));
        }
    }

    public class ObserverContext {

        private TimerExecutor executor;
        private PomodoroTimer timer;
        private ObserverSpy observerSpy;

        @Before
        public void setUp() {
            executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };

            timer = new SleeplessPomodoroTimer(executor);
            timer.setTask(new TaskDummy());

            observerSpy = new ObserverSpy();
            timer.registerObserver(observerSpy);

        }

        @Test
        public void ItShouldUpdateObserverOnStart() {
            timer.start();

            assertThat(observerSpy.updateWasCalled, is(true));
        }

    }

    public class PushModelObserserverContext {
        @Test
        public void ItShouldForwardChangesToTheObserverOnStart() {
            TimerExecutor executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };

            PomodoroTimer timer = new PomodoroTimer(executor) {
                @Override
                protected String parse(long millis) {
                    return "T";
                }

                @Override
                protected void sleep(int timeOut) {
                    notifyObserver("s");
                }
            };

            timer.setTimerSettings(4L, 4L);
            timer.setTask(new TaskDummy());
            ObserverSpy observerSpy = new ObserverSpy();
            timer.registerObserver(observerSpy);

            timer.start();

            assertThat(observerSpy.sequence, is("TsTsTsTs"));
        }
    }

    private class SleeplessPomodoroTimer extends PomodoroTimer {

        public SleeplessPomodoroTimer() {
            super();
        }

        public SleeplessPomodoroTimer(TimerExecutor executor) {
            super(executor);
        }

        @Override
        protected void sleep(int timeOut) {
        }
    }
}