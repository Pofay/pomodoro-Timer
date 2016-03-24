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
        public void ItCanHavePomodoroDurationAndBreakDurationSet() {
            PomodoroTimer timer = new PomodoroTimer();

            long pomodoroDuration = 8000L;
            long breakDuration = 5000L;
            timer.setTimerSettings(pomodoroDuration, breakDuration);

            assertThat(timer.getPomodoroDuration(), is(8000L));
            assertThat(timer.getBreakDuration(), is(5000L));
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

        @Before
        public void setUp() {
            timer = new SleeplessPomodoroTimer();

            taskSpy = new TaskSpy();

            timer.setTask(taskSpy);

            long pomodoroDuration = 6000L;
            long breakDuration = 3000L;

            timer.setTimerSettings(pomodoroDuration, breakDuration);
        }

        @Test
        public void ItShouldBeAbleToCallTick6TimesAfterExecute() throws Exception {
            timer.execute();

            int expectedTickCalls = 6;
            assertThat(taskSpy.numberOfTickCalls, is(expectedTickCalls));
        }
    }

    public class AddingTimeToTaskContext {
        @Test
        public void ItShouldAddTimeToATaskBasedOnPomodoroDuration() throws Exception {
            PomodoroTimer timer = new SleeplessPomodoroTimer();
            long timeEstimateInSeconds = 8000L;

            Task task = new PomodoroTask("Programming", timeEstimateInSeconds);

            timer.setTask(task);

            long pomodoroDuration = 4000L;
            long breakDuration = 0L;

            timer.setTimerSettings(pomodoroDuration, breakDuration);

            timer.execute();

            long expectedTimeSpent = 4000L;

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

        @Test
        public void ItShouldUpdateObserverOnStart() {
            TimerExecutor executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };

            PomodoroTimer timer = new SleeplessPomodoroTimer(executor);

            timer.setTask(new TaskDummy());

            ObserverSpy observerSpy = new ObserverSpy();
            timer.registerObserver(observerSpy);

            timer.setTimerSettings(3000L, 3000L);


            timer.start();

            assertThat(observerSpy.updateWasCalled, is(true));
        }

        @Test
        public void ItShouldCallUpdateTheSameTimesAsThePomodoroDuration() {
            TimerExecutor executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };

            PomodoroTimer timer = new SleeplessPomodoroTimer(executor);
            timer.setTimerSettings(8000L, 3000L);
            timer.setTask(new TaskDummy());

            ObserverSpy observerSpy = new ObserverSpy();

            timer.registerObserver(observerSpy);

            timer.start();

            assertThat(observerSpy.numberOfUpdateCalls, is(8));
        }

        @Test
        public void ItShouldCallUpdateTheSameTimesAsThePomodoroDuration2nd() {
            TimerExecutor executor = new TimerExecutor() {
                @Override
                public void start(PomodoroTimer timer) {
                    timer.execute();
                }

                @Override
                public void cancel() {

                }
            };

            PomodoroTimer timer = new SleeplessPomodoroTimer(executor);
            timer.setTimerSettings(6000L, 3000L);
            timer.setTask(new TaskDummy());

            ObserverSpy observerSpy = new ObserverSpy();

            timer.registerObserver(observerSpy);

            timer.start();

            assertThat(observerSpy.numberOfUpdateCalls, is(6));
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

            long pomodoroDuration = 4000L;
            long breakDuration = 0L;
            timer.setTimerSettings(pomodoroDuration, breakDuration);
        }

        @Test
        public void ItShouldPerformAWorkingCountDownOnExecute() throws Exception {
            timer.execute();

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

            long breakDuration = 4000L;
            long pomodoroDuration = 0L;
            timer.setTimerSettings(pomodoroDuration, breakDuration);

            timer.execute();
        }

        @Test
        public void ItShouldPerformBreakCountDownOn2ndSecondExecute() {
            timer.execute();

            assertThat(sequence, is("ssss"));
        }

    }


}