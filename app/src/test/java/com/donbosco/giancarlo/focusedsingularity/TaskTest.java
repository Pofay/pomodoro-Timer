package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/2/2016.
 */
public class TaskTest {

    @Test
    public void ItShouldBeAbleToStart(){
        Task task = new Task();

        task.start();

        assertThat(task.isStarted(), is(true));
    }

    @Ignore
    @Test
    public void ItShouldBeAbleToStop(){
        Task task = new Task();

        task.start();
        task.stop();

        assertThat(task.isStarted(), is(false));
    }


}
