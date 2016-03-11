package com.donbosco.giancarlo.focusedsingularity;


import org.hamcrest.CoreMatchers;

import org.hamcrest.core.Is;
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


    public class ATaskShould {

        Task task;

        @Before
        public void setUp() {
            task = new PomodoroTask("Testing", 3000L) {
                @Override
                protected void sleep(int timeOut) {
                }
            };
        }

        @Test
        public void HaveItsCurrentStateToWorkingOnCreation() throws Exception {
            TaskState expectedState = PomodoroTaskState.WORKING;

            assertThat(task.getCurrentState(), is(expectedState));
        }

        // Change Reference to another class, maybe a Fake for the Task

        // Test for on task start transition to working then break;
        // Or test that task is on working state when created;
    }

}
