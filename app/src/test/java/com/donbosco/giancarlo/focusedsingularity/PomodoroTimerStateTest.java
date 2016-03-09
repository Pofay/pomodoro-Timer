package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
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

    @Test
    public void ItShouldAddTimeSpentToATaskOnExecute() {
        Task task = new PomodoroTask("Programming", 2);

        state.execute(task);

        assertThat(task.getTimeSpent(), is(1000L));
    }

   
}
