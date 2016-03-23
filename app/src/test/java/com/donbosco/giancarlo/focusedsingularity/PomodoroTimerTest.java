package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

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
            FakePomodoroTimer timer = new FakePomodoroTimer();

            int timeEstimateInSeconds = 25000;
            timer.setTask(new PomodoroTask("Programming", timeEstimateInSeconds));

            assertThat(timer.getSelectedTask(), is(notNullValue()));
        }

        @Test
        public void ItCanHavePomodoroDurationAndBreakDurationSet() {
            FakePomodoroTimer timer = new FakePomodoroTimer();

            long pomodoroDuration = 8000L;
            long breakDuration = 5000L;
            timer.setTimerSettings(pomodoroDuration, breakDuration);

            assertThat(timer.getPomodoroDuration(), is(8000L));
            assertThat(timer.getBreakDuration(), is(5000L));
        }
    }

    public class TimerToTimerStateInteractionContext {

        private FakePomodoroTimer timer;
        private StateSpy stateSpy;

        @Before
        public void setUp() {
            timer = new FakePomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {
                }

            };
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

        private FakePomodoroTimer timer;
        private TaskSpy taskSpy;

        @Before
        public void setUp() {
            timer = new FakePomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
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
            FakePomodoroTimer timer = new FakePomodoroTimer() {
                @Override
                protected void sleep(int timeOut) {

                }
            };
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

    public class InitialTickingContext {

        String sequence = "";
        FakePomodoroTimer timer;

        @Before
        public void WithATimerSetTo4Seconds() {
            timer = new FakePomodoroTimer() {
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

        private FakePomodoroTimer timer;

        String sequence = "";

        @Before
        public void WithATimerThatHasAlreadyPerformedItsWorkingCountDown() {
            timer = new FakePomodoroTimer() {
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

    public class TimerExecutorContext {

        @Test
        public void ItShouldExecuteStateOnTimerStart() throws Exception {
            StateSpy stateSpy = new StateSpy();
            TimerExecutorCancelSpy cancelSpy = new TimerExecutorCancelSpy();
            FakePomodoroTimer timer = new FakePomodoroTimer(cancelSpy) {
                @Override
                protected void sleep(int timeOut) {
                }
            };

            timer.setState(stateSpy);

            timer.start();

            assertThat(stateSpy.executeWasCalled, is(true));
        }

        @Test
        public void ItShouldCancelExecutorWhenFinishing2Executions() throws Exception {
            TimerExecutorCancelSpy executorCancelSpy = new TimerExecutorCancelSpy();
            FakePomodoroTimer timer = new FakePomodoroTimer(executorCancelSpy) {
                @Override
                protected void sleep(int timeOut) {
                }
            };

            timer.setTask(new TaskDummy());

            timer.start();
            timer.start();

            assertThat(executorCancelSpy.cancelWasCalled, is(true));
        }
    }

    public class TimeFormatContext {

        @Test
        public void MinuteFormat() {
            long millis = TimeUnit.MINUTES.toMillis(25L);

            long time = millis - TimeUnit.SECONDS.toMillis(1L);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time);

            String timeFormat = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(time),
                    TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes));

            assertThat(timeFormat, is("24:59"));
        }
    }

}