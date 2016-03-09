package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public class PomodoroTimerStateTest {

    private PomodoroTimerState state;

    @Before
    public void WhenInWorkingState() {
        state = SimplePomodoroTimerState.WORKING;
    }

    @Ignore
    @Test
    public void ItShouldAddTimeSpentToATaskOnExecute() {
        Task task = new PomodoroTask("Programming", 2) {

            @Override
            protected void sleep(int timeOut) {
            }

        };

        state.execute(task);

        assertThat(task.getTimeSpent(), is(2000L));
    }

    @Ignore
    @Test
    public void ItShouldAddTimeSpentToATask() throws Exception {
        Task task = new PomodoroTask("Stuff", 1) {

            @Override
            protected void sleep(int timeOut) {
            }

        };


        state.execute(task);

        assertThat(task.getTimeSpent(), is(1000L));
    }
}
