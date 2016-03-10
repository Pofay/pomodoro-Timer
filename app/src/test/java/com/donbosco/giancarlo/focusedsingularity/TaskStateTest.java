package com.donbosco.giancarlo.focusedsingularity;


import org.hamcrest.CoreMatchers;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public class TaskStateTest {


    @Test
    public void ItShouldExecuteFor3SecondsWhenSetToWorking() throws Exception {
        Task task = new PomodoroTask("Testing", 3000L) {
            @Override
            protected void sleep(int timeOut) {
            }
        };

        TaskState state = PomodoroTaskState.WORKING;

        state.execute(task);

        assertThat(task.getTimeSpent(), is(3000L));
    }

    @Test
    public void ItShouldExecuteATaskFor2SecondsWhenStateIsSetToWorking() {
        Task task = new PomodoroTask("Test", 2000L) {
            @Override
            protected void sleep(int timeOut) {

            }
        };

        TaskState state = PomodoroTaskState.WORKING;

        state.execute(task);

        assertThat(task.getTimeSpent(), is(2000L));
    }

    // Change Reference to another class, maybe a Fake for the Task
    @Test
    public void WhenTaskIsDoneItShouldTransitionToBreakState() {
        Task task = new PomodoroTask("Stuff", 4000L) {
            @Override
            protected void sleep(int timeOut) {
            }
        };

        TaskState state = PomodoroTaskState.WORKING;

        state.execute(task);

        TaskState expectedState = PomodoroTaskState.BREAK;
        assertThat(task.getCurrentState(), is(expectedState));
    }




}
