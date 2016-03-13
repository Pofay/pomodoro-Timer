package com.donbosco.giancarlo.focusedsingularity;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/9/2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class TaskTest {


    public class ATask {

        String ticks = "";
        int tickCalls;
        Task task;

        @Before
        public void WithA3SecondEstimateAnd2SecondPomodoroDurationShould() {
            task = new PomodoroTask("Testing", 3000L) {
                @Override
                protected void tick() {
                    tickCalls++;
                    ticks += "T";
                }

                @Override
                protected void sleep(int timeOut) {
                    ticks += "s";
                }
            };

            task.setPomodoroDuration(2000L);
        }

        @Test
        public void HaveItsCurrentStateToWorkingOnCreation() throws Exception {
            TaskState expectedState = PomodoroTaskState.WORKING;

            assertThat(task.getCurrentState(), is(expectedState));
        }

        @Test
        public void Ticks2Times() {
            task.start();

            assertThat(tickCalls, is(2));
        }

        @Test
        public void SleepsInBetweenTicks(){
            task.start();

            assertThat(ticks, is("TsTs"));
        }

        @Test
        public void TransitionToBreakStateAfterCompletingStart() {
            task.start();

            TaskState expectedState = PomodoroTaskState.BREAK;
            assertThat(task.getCurrentState(), is(expectedState));
        }

    }

    public class TaskStoppedContext {

        private Task task;

        @Before
        public void WithATaskThatIsStopped() throws Exception {
            task = new PomodoroTask("Rolling", 6000L) {
                @Override
                protected void sleep(int timeOut) {
                }
            };

            task.start();
            task.stop();
        }

        @Test
        public void ItShouldBeInTheStoppedState() {

            TaskState expectedState = PomodoroTaskState.STOPPED;
            assertThat(task.getCurrentState(), is(expectedState));
        }

        @Test
        public void ItShouldNotAddTimeSpentWhenStartedOnStoppedState() {
            task.start();

            assertThat(task.getTimeSpent(), is(0L));
        }

        @Test
        public void ItShouldBeInWorkingStateAgainOnReset() {
            task.reset();

            TaskState expectedState = PomodoroTaskState.WORKING;
            assertThat(task.getCurrentState(), is(expectedState));
        }
    }
}
